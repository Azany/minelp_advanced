package com.minelittlepony.minelp.hdskins.gui;

import com.minelittlepony.minelp.PonyManager;
import com.minelittlepony.minelp.util.MineLPLogger;
import com.mojang.authlib.GameProfile;
import com.mumfrey.liteloader.util.log.LiteLoaderLogger;
import com.thevoxelbox.common.util.upload.IUploadCompleteCallback;
import com.thevoxelbox.common.util.upload.ThreadMultipartPostUpload;
import com.thevoxelbox.common.util.upload.awt.IOpenFileCallback;
import com.thevoxelbox.common.util.upload.awt.ThreadOpenFilePNG;
import com.thevoxelbox.hdskins.gui.EntityPlayerModel;
import com.thevoxelbox.hdskins.mod.HDSkinsModCore;
import com.thevoxelbox.voxelmenu.IPanoramaRenderer;
import net.inkyquill.litemods.minelp.GlobalSettings;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Session;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.DoubleBuffer;
import java.util.HashMap;
import java.util.List;

public class GuiSkinsMineLP
extends GuiScreen
implements IUploadCompleteCallback,
IOpenFileCallback,
IPanoramaRenderer {
    private static final ResourceLocation vignette = new ResourceLocation("textures/misc/vignette.png");
    private static final int MAX_SKIN_DIMENSION = 8192;
   // private static final File legacySkinFile = new File(System.getenv("TEMP"), "legacySkinMineLP.png");
   private static final String skinServerId = "7853dfddc358333843ad55a2c7485c4aa0380a51";

    private int updateCounter = 0;
    private ResourceLocation viewportTexture;
    private IPanoramaRenderer panoramaRenderer;
    private static final ResourceLocation[] cubemapTextures = new ResourceLocation[]{new ResourceLocation("hdskins", "textures/cubemaps/cubemap0_0.png"), new ResourceLocation("hdskins", "textures/cubemaps/cubemap0_1.png"), new ResourceLocation("hdskins", "textures/cubemaps/cubemap0_2.png"), new ResourceLocation("hdskins", "textures/cubemaps/cubemap0_3.png"), new ResourceLocation("hdskins", "textures/cubemaps/cubemap0_4.png"), new ResourceLocation("hdskins", "textures/cubemaps/cubemap0_5.png")};
    private GuiButton btnBrowse;
    private GuiButton btnUpload;
    private GuiButton btnClear;
    private GuiButton btnBack;
   // private GuiButton btnFetchLegacy;
    private EntityPonyModel localPlayer;
    private EntityPonyModel remotePlayer;
    protected DoubleBuffer doubleBuffer;
    private String screenTitle;
    private String uploadError;
    private volatile String skinMessage = "Choose a file";
    private String skinUploadMessage = "Uploading skin please wait...";
    private volatile boolean fetchingSkin;
    private volatile boolean uploadingSkin;
    private volatile boolean pendingRemoteSkinRefresh;
    private volatile boolean throttledByMojang;
    private int refreshCounter = -1;
    private ThreadOpenFilePNG openFileThread;
    private ThreadMultipartPostUpload threadSkinUpload;
    private final Object skinLock = new Object();
    private File pendingSkinFile;
    private File selectedSkin;
    private BufferedImage pendingSkinImage;
    private float uploadOpacity = 0.0f;
    private float lastPartialTick;


    public GuiSkinsMineLP() {
        Minecraft minecraft = Minecraft.getMinecraft();
        this.screenTitle = "Skin Manager";
        GameProfile profile = minecraft.getSession().func_148256_e();
        this.localPlayer = new EntityPonyModel(profile);
        this.remotePlayer = new EntityPonyModel(profile);
        RenderManager.instance.renderEngine = minecraft.getTextureManager();
        RenderManager.instance.options = minecraft.gameSettings;
        RenderManager.instance.livingPlayer = this.localPlayer;
        this.setRemoteSkin();
        this.fetchingSkin = true;
        this.panoramaRenderer = HDSkinsModCore.getPanoramaRenderer(this);
    }

    public static boolean isPowerOfTwo(int number) {
        return number != 0 && (number & number - 1) == 0;
    }

   // public static void enableLegacySkin() {
   //     legacySkinEnabled = true;
   // }

    @Override
    public void updateScreen() {
        ++this.updateCounter;
        this.panoramaRenderer.updatePanorama();
        this.localPlayer.updateModel();
        this.remotePlayer.updateModel();
        if (this.fetchingSkin && this.remotePlayer.isTextureSetupComplete()) {
            this.fetchingSkin = false;
            this.btnClear.enabled = true;
        }
        synchronized (this.skinLock) {
            if (this.pendingSkinFile != null) {
                this.localPlayer.setLocalSkin(this.pendingSkinFile);
                this.selectedSkin = this.pendingSkinFile;
                this.pendingSkinFile = null;
                MineLPLogger.debug("Invalidating old local skin, checking updated local skin");
                PonyManager.getInstance().getPonyFromResourceRegistry(this.localPlayer.getSkinTexture()).checkSkin(this.pendingSkinImage);
                this.pendingSkinImage = null;
                this.btnUpload.enabled = true;
            }
        }
        if (this.pendingRemoteSkinRefresh) {
            this.pendingRemoteSkinRefresh = false;
            this.fetchingSkin = true;
            this.btnClear.enabled = false;
            this.setRemoteSkin();
            MineLPLogger.debug("Invalidating old remove skin, checking updated remote skin");
            PonyManager.getInstance().getPonyFromResourceRegistry(this.remotePlayer.getSkinTexture()).invalidateSkinCheck();
        }
        if (this.throttledByMojang) {
            if (this.refreshCounter == -1) {
                this.refreshCounter = 200;
            } else if (this.refreshCounter > 0) {
                --this.refreshCounter;
            } else {
                this.refreshCounter = -1;
                this.throttledByMojang = false;
                this.setRemoteSkin();
            }
        }
    }

    private void setRemoteSkin() {
        try {
            this.remotePlayer.setRemoteSkin();
        } catch (Exception ex) {
            ex.printStackTrace();
            this.throttledByMojang = true;
        }
    }

    @Override
    public void updatePanorama() {
    }

    @Override
    public int getUpdateCounter() {
        return this.updateCounter;
    }

    @Override
    public void setWorldAndResolution(Minecraft par1Minecraft, int par2, int par3) {
        super.setWorldAndResolution(par1Minecraft, par2, par3);
        this.panoramaRenderer.setPanoramaResolution(par1Minecraft, par2, par3);
    }

    @Override
    public void setPanoramaResolution(Minecraft minecraft, int width, int height) {
    }

    protected List<GuiButton> getControlList() {
        return this.buttonList;
    }

    @Override
    public void initGui() {
        super.initGui();
        this.panoramaRenderer.initPanoramaRenderer();
        this.getControlList().clear();
        this.btnBrowse = new GuiButton(0, 30, this.height - 36, 60, 20, "Browse...");
        this.getControlList().add(this.btnBrowse);
        this.btnUpload = new GuiButton(1, this.width / 2 - 24, this.height / 2 - 10, 48, 20, ">>");
        this.getControlList().add(this.btnUpload);
        this.btnClear = new GuiButton(2, this.width - 90, this.height - 36, 60, 20, "Clear");
        this.getControlList().add(this.btnClear);
        this.btnBack = new GuiButton(3, this.width / 2 - 50, this.height - 36, 100, 20, "Close");
        this.getControlList().add(this.btnBack);
       // this.btnFetchLegacy = new GuiButton(5, 30, 6, 100, 20, "Fetch Legacy Skin");
       // this.getControlList().add(this.btnFetchLegacy);
        this.btnUpload.enabled = false;
        this.btnBrowse.enabled = !this.mc.isFullScreen();
      //  this.btnFetchLegacy.visible = legacySkinEnabled;
    }

    @Override
    public void initPanoramaRenderer() {
        this.viewportTexture = this.mc.getTextureManager().getDynamicTextureLocation("skinpanorama", new DynamicTexture(256, 256));
    }

    @Override
    public void onGuiClosed() {
        super.onGuiClosed();
        this.localPlayer.releaseTextures();
        this.remotePlayer.releaseTextures();
        PonyManager.getInstance().getPonyFromResourceRegistry(this.localPlayer.getSkinTexture()).invalidateSkinCheck();
        PonyManager.getInstance().getPonyFromResourceRegistry(this.remotePlayer.getSkinTexture()).invalidateSkinCheck();
    }

    @Override
    public void onFileOpenDialogClosed(JFileChooser fileDialog, int dialogResult) {
        this.openFileThread = null;
        if (dialogResult == 0) {
            this.loadLocalFile(fileDialog.getSelectedFile());
        }
    }

    private void loadLocalFile(File skinFile) {
        BufferedImage chosenImage;
        if (!skinFile.exists()) {
            this.skinMessage = "File not readable";
            return;
        }
        try {
            chosenImage = ImageIO.read(skinFile);
        } catch (IOException e2) {
            this.skinMessage = "Error opening skin file";
            e2.printStackTrace();
            return;
        }
        if (chosenImage == null) {
            this.skinMessage = "Error opening skin file";
            return;
        }
        if (!GuiSkinsMineLP.isPowerOfTwo(chosenImage.getWidth()) || chosenImage.getWidth() != chosenImage.getHeight() * 2 || chosenImage.getWidth() > MAX_SKIN_DIMENSION || chosenImage.getHeight() > MAX_SKIN_DIMENSION) {
            this.skinMessage = "Not a valid skin file";
            return;
        }
        synchronized ( this.skinLock) {
            this.pendingSkinFile = skinFile;
            this.pendingSkinImage = chosenImage;
        }
    }

    @Override
    protected void actionPerformed(GuiButton guiButton) {
        if (this.openFileThread != null || this.uploadingSkin) {
            return;
        }
        if (this.uploadError != null) {
            this.uploadError = null;
            return;
        }
        if (guiButton.id == this.btnBrowse.id) {
            this.selectedSkin = null;
            this.localPlayer.releaseTextures();
          //  this.btnFetchLegacy.enabled = true;
            this.openFileThread = new ThreadOpenFilePNG(this.mc, "Choose skin", this);
            this.openFileThread.start();
        }
        if (guiButton.id == this.btnUpload.id) {
            if (this.selectedSkin != null) {
                this.uploadSkin(this.mc.getSession(), this.selectedSkin);
                this.btnUpload.enabled = false;
            } else {
                this.setUploadError("Please select a skin first");
            }
        }
        if (guiButton.id == this.btnClear.id && this.remotePlayer.isTextureSetupComplete()) {
            this.clearUploadedSkin(this.mc.getSession());
            boolean bl = this.btnUpload.enabled = this.selectedSkin != null;
        }
        if (guiButton.id == this.btnBack.id) {
            this.mc.displayGuiScreen(new GuiMainMenu());
        }
      //  if (guiButton.id == this.btnFetchLegacy.id && this.downloadLegacySkin()) {
      //      this.loadLocalFile(legacySkinFile);
      //      this.btnFetchLegacy.enabled = false;
      //  }
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int button) {
        if (this.uploadError != null) {
            this.uploadError = null;
            return;
        }
        super.mouseClicked(mouseX, mouseY, button);
        int top = 30;
        int bottom = this.height - 40;
        int mid = this.width / 2;
        if ((mouseX > 30 && mouseX < mid - 30 || mouseX > mid + 30 && mouseX < this.width - 30) && mouseY > top && mouseY < bottom) {
            this.localPlayer.swingArm();
            this.remotePlayer.swingArm();
        }
    }

    @Override
    protected void keyTyped(char keyChar, int keyCode) {
        if (this.openFileThread != null || this.uploadingSkin) {
            return;
        }
        super.keyTyped(keyChar, keyCode);
    }

    public void setupCubemapCamera() {
        GL11.glMatrixMode(5889);
        GL11.glPushMatrix();
        GL11.glLoadIdentity();
        GLU.gluPerspective(150.0f, 1.0f, 0.05f, 10.0f);
        GL11.glMatrixMode(5888);
        GL11.glPushMatrix();
        GL11.glLoadIdentity();
    }

    public void revertPanoramaMatrix() {
        GL11.glMatrixMode(5889);
        GL11.glPopMatrix();
        GL11.glMatrixMode(5888);
        GL11.glPopMatrix();
    }

    private void renderCubeMapTexture(int mouseX, int mouseY, float partialTick) {
        this.setupCubemapCamera();
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        GL11.glRotatef(180.0f, 1.0f, 0.0f, 0.0f);
        GL11.glEnable(3042);
        GL11.glDisable(3008);
        GL11.glDisable(2884);
        GL11.glDepthMask(false);
        GL11.glBlendFunc(770, 771);
        int blendIterations = 8;
        Tessellator tessellator = Tessellator.instance;
        for (int blendPass = 0; blendPass < blendIterations * blendIterations; ++blendPass) {
            GL11.glPushMatrix();
            float offsetX = ((float) (blendPass % blendIterations) / (float) blendIterations - 0.5f) / 64.0f;
            float offsetY = ((float) (blendPass / blendIterations) / (float) blendIterations - 0.5f) / 64.0f;
            float offsetZ = 0.0f;
            GL11.glTranslatef(offsetX, offsetY, offsetZ);
            GL11.glRotatef(MathHelper.sin((this.updateCounter + 200 + partialTick) / 400.0f) * 25.0f + 20.0f, 1.0f, 0.0f, 0.0f);
            GL11.glRotatef((-(float) (this.updateCounter + 200) + partialTick) * 0.1f, 0.0f, 1.0f, 0.0f);
            for (int cubeSide = 0; cubeSide < 6; ++cubeSide) {
                GL11.glPushMatrix();
                if (cubeSide == 1) {
                    GL11.glRotatef(90.0f, 0.0f, 1.0f, 0.0f);
                }
                if (cubeSide == 2) {
                    GL11.glRotatef(180.0f, 0.0f, 1.0f, 0.0f);
                }
                if (cubeSide == 3) {
                    GL11.glRotatef(-90.0f, 0.0f, 1.0f, 0.0f);
                }
                if (cubeSide == 4) {
                    GL11.glRotatef(90.0f, 1.0f, 0.0f, 0.0f);
                }
                if (cubeSide == 5) {
                    GL11.glRotatef(-90.0f, 1.0f, 0.0f, 0.0f);
                }
                this.mc.getTextureManager().bindTexture(cubemapTextures[cubeSide]);
                tessellator.startDrawingQuads();
                tessellator.setColorRGBA_I(16777215, 255 / (blendPass + 1));
                tessellator.addVertexWithUV(-1.0, -1.0, 1.0, 0.0, 0.0);
                tessellator.addVertexWithUV(1.0, -1.0, 1.0, 1.0, 0.0);
                tessellator.addVertexWithUV(1.0, 1.0, 1.0, 1.0, 1.0);
                tessellator.addVertexWithUV(-1.0, 1.0, 1.0, 0.0, 1.0);
                tessellator.draw();
                GL11.glPopMatrix();
            }
            GL11.glPopMatrix();
            GL11.glColorMask(true, true, true, false);
        }
        tessellator.setTranslation(0.0, 0.0, 0.0);
        GL11.glColorMask(true, true, true, true);
        GL11.glDepthMask(true);
        GL11.glEnable(2884);
        GL11.glEnable(3008);
        GL11.glEnable(2929);
        this.revertPanoramaMatrix();
    }

    private void rotateAndBlurCubemap(float partialTick) {
        this.mc.getTextureManager().bindTexture(this.viewportTexture);
        GL11.glCopyTexSubImage2D(3553, 0, 0, 0, 0, 0, 256, 256);
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glColorMask(true, true, true, false);
        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        int blurPasses = 4;
        for (int blurPass = 0; blurPass < blurPasses; ++blurPass) {
            tessellator.setColorRGBA_F(1.0f, 1.0f, 1.0f, 1.0f / (blurPass + 1));
            float var7 = (blurPass - blurPasses / 2) / 256.0f;
            tessellator.addVertexWithUV(this.width, this.height, this.zLevel, 0.0f + var7, 0.0);
            tessellator.addVertexWithUV(this.width, 0.0, this.zLevel, 1.0f + var7, 0.0);
            tessellator.addVertexWithUV(0.0, 0.0, this.zLevel, 1.0f + var7, 1.0);
            tessellator.addVertexWithUV(0.0, this.height, this.zLevel, 0.0f + var7, 1.0);
        }
        tessellator.draw();
        GL11.glColorMask(true, true, true, true);
        GL11.glDisable(3042);
    }

    @Override
    public boolean renderPanorama(int mouseX, int mouseY, float partialTicks) {
        GL11.glViewport(0, 0, 256, 256);
        this.renderCubeMapTexture(mouseX, mouseY, partialTicks);
        GL11.glDisable(3553);
        GL11.glEnable(3553);
        for (int pass = 0; pass < 8; ++pass) {
            this.rotateAndBlurCubemap(partialTicks);
        }
        GL11.glViewport(0, 0, this.mc.displayWidth, this.mc.displayHeight);
        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        float aspect = this.width > this.height ? 120.0f / this.width : 120.0f / this.height;
        float uSample = this.height * aspect / 256.0f;
        float vSample = this.width * aspect / 256.0f;
        GL11.glTexParameteri(3553, 10241, 9729);
        GL11.glTexParameteri(3553, 10240, 9729);
        tessellator.setColorRGBA_F(1.0f, 1.0f, 1.0f, 1.0f);
        tessellator.addVertexWithUV(0.0, this.height, this.zLevel, 0.5f - uSample, 0.5f + vSample);
        tessellator.addVertexWithUV(this.width, this.height, this.zLevel, 0.5f - uSample, 0.5f - vSample);
        tessellator.addVertexWithUV(this.width, 0.0, this.zLevel, 0.5f + uSample, 0.5f - vSample);
        tessellator.addVertexWithUV(0.0, 0.0, this.zLevel, 0.5f + uSample, 0.5f + vSample);
        tessellator.draw();
        return true;
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTick) {
        float deltaTime = this.updateCounter + partialTick - this.lastPartialTick;
        this.lastPartialTick = this.updateCounter + partialTick;
        GL11.glDisable(2912);
        this.mc.entityRenderer.disableLightmap(partialTick);
        this.panoramaRenderer.renderPanorama(mouseX, mouseY, partialTick);
        int top = 30;
        int bottom = this.height - 40;
        int mid = this.width / 2;
        int horizon = this.height / 2 + this.height / 5;
        GL11.glPushAttrib(1048575);
        Gui.drawRect(30, top, mid - 30, bottom, Integer.MIN_VALUE);
        Gui.drawRect(mid + 30, top, this.width - 30, bottom, Integer.MIN_VALUE);
        this.drawGradientRect(30, horizon, mid - 30, bottom, -2130706433, 16777215);
        this.drawGradientRect(mid + 30, horizon, this.width - 30, bottom, -2130706433, 16777215);
        super.drawScreen(mouseX, mouseY, partialTick);
        GL11.glPopAttrib();
        this.enableClipping(30, bottom);
        float yPos = this.height * 0.75f;
        float xPos1 = this.width * 0.25f;
        float xPos2 = this.width * 0.75f;
        float scale = this.height * 0.25f;
        this.renderPlayerModel(this.localPlayer, xPos1, yPos, scale, xPos1 - mouseX, yPos - scale * 1.8f - mouseY, partialTick);
        this.renderPlayerModel(this.remotePlayer, xPos2, yPos, scale, xPos2 - mouseX, yPos - scale * 1.8f - mouseY, partialTick);
        this.disableClipping();
        this.drawCenteredString(this.fontRendererObj, this.screenTitle, this.width / 2, 10, 16777215);
        this.fontRendererObj.drawStringWithShadow("Local Skin", 34, 34, 16777215);
        this.fontRendererObj.drawStringWithShadow("Server Skin", this.width / 2 + 34, 34, 16777215);
        GL11.glDisable(2929);
        GL11.glDepthMask(false);
        this.drawGradientRect(30, this.height - 60, mid - 30, bottom, 0, -520093697);
        this.drawGradientRect(mid + 30, this.height - 60, this.width - 30, bottom, 0, -520093697);
        int labelwidth = (this.width / 2 - 80) / 2;
        if (!this.localPlayer.isUsingLocalTexture()) {
            int stringWidth = this.fontRendererObj.getStringWidth(this.skinMessage) / 2;
            Gui.drawRect(40, this.height / 2 - 12, this.width / 2 - 40, this.height / 2 + 12, -1342177280);
            this.fontRendererObj.drawStringWithShadow(this.skinMessage, (int) (xPos1 - stringWidth), this.height / 2 - 4, 16777215);
        }
        if (this.fetchingSkin) {
            if (this.throttledByMojang) {
                String message1 = "\u00a7cMojang API Error!";
                String message2 = "Please wait 1 minute";
                int stringWidth1 = this.fontRendererObj.getStringWidth(message1) / 2;
                int stringWidth2 = this.fontRendererObj.getStringWidth(message2) / 2;
                Gui.drawRect(((int) (xPos2 - labelwidth)), this.height / 2 - 16, this.width - 40, this.height / 2 + 16, -1342177280);
                this.fontRendererObj.drawStringWithShadow(message1, (int) (xPos2 - stringWidth1), this.height / 2 - 10, 16777215);
                this.fontRendererObj.drawStringWithShadow(message2, (int) (xPos2 - stringWidth2), this.height / 2 + 2, 16777215);
            } else {
                String message = "Fetching skin...";
                int stringWidth = this.fontRendererObj.getStringWidth(message) / 2;
                Gui.drawRect(((int) (xPos2 - labelwidth)), this.height / 2 - 12, this.width - 40, this.height / 2 + 12, -1342177280);
                this.fontRendererObj.drawStringWithShadow(message, (int) (xPos2 - stringWidth), this.height / 2 - 4, 16777215);
            }
        }
        if (this.uploadingSkin || this.uploadOpacity > 0.0f) {
            if (!this.uploadingSkin) {
                this.uploadOpacity -= deltaTime * 0.05f;
            } else if (this.uploadOpacity < 1.0f) {
                this.uploadOpacity += deltaTime * 0.1f;
            }
            if (this.uploadOpacity > 1.0f) {
                this.uploadOpacity = 1.0f;
            }
            int opacity = Math.min(180, (int) (this.uploadOpacity * 180.0f)) & 255;
            if (this.uploadOpacity > 0.0f) {
                Gui.drawRect(0, 0, this.width, this.height, opacity << 24);
                if (this.uploadingSkin) {
                    this.drawCenteredString(this.fontRendererObj, this.skinUploadMessage, this.width / 2, this.height / 2, opacity << 24 | 16777215);
                }
            }
        }
        if (this.uploadError != null) {
            Gui.drawRect(0, 0, this.width, this.height, -1342177280);
            this.drawCenteredString(this.fontRendererObj, "Uploading skin failed", this.width / 2, this.height / 2 - 10, -171);
            this.drawCenteredString(this.fontRendererObj, this.uploadError, this.width / 2, this.height / 2 + 2, -43691);
        }
        GL11.glDepthMask(true);
        GL11.glEnable(2929);
    }

    protected void renderVignette(int mouseX, int mouseY, float partialTick) {
        GL11.glDisable(2929);
        GL11.glDepthMask(false);
        GL11.glBlendFunc(1, 774);
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        this.mc.getTextureManager().bindTexture(vignette);
        GL11.glLogicOp(5386);
        Tessellator var4 = Tessellator.instance;
        var4.startDrawingQuads();
        var4.addVertexWithUV(0.0, this.height, -90.0, 0.0, 1.0);
        var4.addVertexWithUV(this.width, this.height, -90.0, 1.0, 1.0);
        var4.addVertexWithUV(this.width, 0.0, -90.0, 1.0, 0.0);
        var4.addVertexWithUV(0.0, 0.0, -90.0, 0.0, 0.0);
        var4.draw();
        GL11.glDepthMask(true);
        GL11.glDisable(3058);
        GL11.glEnable(2929);
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        GL11.glBlendFunc(770, 771);
    }

    public void renderPlayerModel(EntityPlayerModel thePlayer, float xPosition, float yPosition, float scale, float mouseX, float mouseY, float partialTick) {
        GL11.glEnable(2903);
        GL11.glPushMatrix();
        GL11.glTranslatef(xPosition, yPosition, 300.0f);
        GL11.glScalef((-scale), scale, scale);
        GL11.glRotatef(180.0f, 0.0f, 0.0f, 1.0f);
        GL11.glRotatef(135.0f, 0.0f, 1.0f, 0.0f);
        RenderHelper.enableStandardItemLighting();
        GL11.glRotatef(-135.0f, 0.0f, 1.0f, 0.0f);
        GL11.glRotatef(15.0f, 1.0f, 0.0f, 0.0f);
        GL11.glRotatef((this.updateCounter + partialTick) * 2.5f, 0.0f, 1.0f, 0.0f);
        thePlayer.rotationPitch = (-(float) Math.atan(mouseY / 40.0f)) * 20.0f;
        GL11.glTranslatef(0.0f, thePlayer.yOffset, 0.0f);
        RenderManager.instance.playerViewY = 180.0f;
        RenderManager.instance.renderEntityWithPosYaw(thePlayer, 0.0, 0.0, 0.0, 1.0f, 1.0f);
        GL11.glPopMatrix();
        RenderHelper.disableStandardItemLighting();
        GL11.glDisable(32826);
    }

    protected final void enableClipping(int yTop, int yBottom) {
        if (this.doubleBuffer == null) {
            this.doubleBuffer = BufferUtils.createByteBuffer(32).asDoubleBuffer();
        }
        this.doubleBuffer.clear();
        this.doubleBuffer.put(0.0).put(1.0).put(0.0).put(-yTop).flip();
        GL11.glClipPlane(12288, this.doubleBuffer);
        this.doubleBuffer.clear();
        this.doubleBuffer.put(0.0).put(-1.0).put(0.0).put(yBottom).flip();
        GL11.glClipPlane(12289, this.doubleBuffer);
        GL11.glEnable(12288);
        GL11.glEnable(12289);
    }

    protected final void disableClipping() {
        GL11.glDisable(12289);
        GL11.glDisable(12288);
    }

    private boolean clearUploadedSkin(Session session) {
//        if (!this.registerServerConnection(session, skinServerId)) {
//            return false;
//        }
        HashMap<String, Object> sourceData = new HashMap<String,Object>();
        sourceData.put("user", session.getUsername());
        sourceData.put("uuid", session.getPlayerID());
        sourceData.put("clear", "1");
        sourceData.put("token", GlobalSettings.SkinUploadToken);
        this.uploadError = null;
        this.uploadingSkin = true;
        this.skinUploadMessage = "Sending request to server please wait...";
        this.threadSkinUpload = new ThreadMultipartPostUpload(String.format("http://%s/%s",GlobalSettings.SkinServer,GlobalSettings.SkinUpload), sourceData, this);
        this.threadSkinUpload.start();
        return true;
    }

    private boolean uploadSkin(Session session, File skinFile) {
//        if (!this.registerServerConnection(session, skinServerId)) {
//            return false;
//        }
        HashMap<String, Object> sourceData = new HashMap<String, Object>();
        sourceData.put("user", session.getUsername());
        sourceData.put("uuid", session.getPlayerID());
        sourceData.put("skin", skinFile);
        sourceData.put("token", GlobalSettings.SkinUploadToken);
        this.uploadError = null;
        this.uploadingSkin = true;
        this.skinUploadMessage = "Uploading skin please wait...";
        this.threadSkinUpload = new ThreadMultipartPostUpload(String.format("http://%s/%s",GlobalSettings.SkinServer,GlobalSettings.SkinUpload), sourceData, this);
        this.threadSkinUpload.start();
        return true;
    }

    private void setUploadError(String error) {
        this.uploadError = error.startsWith("ERROR: ") ? error.substring(7) : error;
        this.btnUpload.enabled = true;
    }

    @Override
    public void onUploadComplete(String response) {
        LiteLoaderLogger.info("Upload completed with: %s", response);
        this.uploadingSkin = false;
        this.threadSkinUpload = null;
        if (!response.equalsIgnoreCase("OK")) {
            this.setUploadError(response);
            return;
        }
        this.pendingRemoteSkinRefresh = true;
      //  if (!this.btnFetchLegacy.enabled) {
      //      this.btnFetchLegacy.visible = false;
      //      MineLittlePony.getInstance();
      //      MineLittlePony.getConfig().setProperty("oldSkinUploaded", 1);
      //  }
    }

//    private boolean registerServerConnection(Session session, String serverId) {
//        try {
//            MinecraftSessionService sessionService = new YggdrasilAuthenticationService(this.mc.getProxy(), UUID.randomUUID().toString()).createMinecraftSessionService();
//            sessionService.joinServer(session.func_148256_e(), session.getToken(), serverId);
//            return true;
//        } catch (AuthenticationException e) {
//            this.setUploadError(e.toString());
//            e.printStackTrace();
//            return false;
//        }
//    }

//    private boolean downloadLegacySkin() {
//        try {
//            int i;
//            URL url = new URL(String.format("http://www.minelittlepony.com/hd/skins/%s.png", this.mc.getSession().getUsername()));
//            BufferedInputStream in = new BufferedInputStream(url.openStream());
//            BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(legacySkinFile));
//            while ((i = in.read()) != -1) {
//                out.write(i);
//            }
//            in.close();
//            out.close();
//            return true;
//        } catch (FileNotFoundException e1) {
//            this.btnFetchLegacy.visible = false;
//            MineLittlePony.getInstance();
//            MineLittlePony.getConfig().setProperty("oldSkinUploaded", 1);
//            this.skinMessage = "No legacy skin available";
//        } catch (Exception e2) {
//            e2.printStackTrace();
//            this.skinMessage = "Error obtaining legacy skin";
//        }
//        return false;
//    }
}

