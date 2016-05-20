package com.minelittlepony.minelp;

import com.minelittlepony.minelp.model.PMAPI;
import com.minelittlepony.minelp.model.PlayerModel;
import com.minelittlepony.minelp.util.MineLPLogger;
import com.thevoxelbox.common.util.PrivateFields;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.ThreadDownloadImageData;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Pony {
    public static PonyManager ponyManager = PonyManager.getInstance();
    private static int ponyCount = 0;
    private final int dangerzone = 2;
    private final int ponyId = ++ponyCount;
    public PonyRace race = PonyRace.EARTH;
    public boolean advancedTexturing;
    public ResourceLocation textureResourceLocation;
    public boolean isSpPlayer = false;
    public boolean isPony = false;
    public boolean isPonySkin = false;
    public boolean isPegasus = false;
    public boolean isUnicorn = false;
    public boolean isFlying = false;
    public boolean isGlow = false;
    public int glowColor = -12303190;
    public boolean isMale = false;
    public int size = 1;
    public int wantTail = 0;
    public boolean isVillager = false;
    public int villagerProfession = 1;
    public float defaultYOffset = 1.62f;
    public int StylesofMane;
    public boolean RainboomTail;
    boolean pegasusFlying = false;
    private float previousFallDistance = 0.0f;
    private int skinCheckCount = 0;
    private boolean skinChecked = false;
    public boolean isKirin = false;
    public boolean isGriffin = false;


    public Pony(AbstractClientPlayer player) {
        this.textureResourceLocation = player.getLocationSkin();
        MineLPLogger.debug("+ Initialising new pony #%d for player %s (%s) with resource location %s.", this.ponyId, player.getCommandSenderName(), player.getUniqueID().toString(), this.textureResourceLocation.toString());
        this.checkSkin(this.textureResourceLocation);
    }

    public Pony(ResourceLocation aTextureResourceLocation) {
        this.textureResourceLocation = aTextureResourceLocation;
        MineLPLogger.debug("+ Initialising new pony #%d with resource location %s.", this.ponyId, this.textureResourceLocation.toString());
        this.checkSkin(this.textureResourceLocation);
    }

    public void invalidateSkinCheck() {
        this.resetValues();
        this.skinChecked = false;
    }

    public void checkSkin() {
        if (!this.skinChecked) {
            this.checkSkin(this.textureResourceLocation);
        }
    }

    public void checkSkin(ResourceLocation textureResourceLocation) {
        BufferedImage skinImage = this.getBufferedImage(textureResourceLocation);
        if (skinImage != null) {
            this.checkSkin(skinImage);
        }
    }

    public BufferedImage getBufferedImage(ResourceLocation textureResourceLocation) {
        BufferedImage skinImage = null;
        try {
            skinImage = ImageIO.read(Minecraft.getMinecraft().getResourceManager().getResource(textureResourceLocation).getInputStream());
            MineLPLogger.debug("Obtained skin from resource location %s", textureResourceLocation.toString());
            this.checkSkin(skinImage);
        } catch (Exception e) {
            try {
                ITextureObject texture = Minecraft.getMinecraft().getTextureManager().getTexture(textureResourceLocation);
                if (texture instanceof ThreadDownloadImageData && (skinImage = PrivateFields.downloadedImage.get(((ThreadDownloadImageData) texture))) != null) {
                    MineLPLogger.debug(e, "Successfully reflected downloadedImage from texture object");
                    this.checkSkin(skinImage);
                }
            } catch (Exception e2) {
                // empty catch block
            }
        }
        return skinImage;
    }

    public void checkSkin(BufferedImage bufferedimage) {
        Object[] arrobject = new Object[]{++this.skinCheckCount, this.ponyId, bufferedimage.toString()};
        MineLPLogger.debug("\tStart skin check #%d for pony #%d with image %s.", arrobject);
        this.resetValues();
        Color flagPix = new Color(bufferedimage.getRGB(0, 0), true);
        Color applejack = new Color(249, 177, 49, 255);
        Color dashie = new Color(136, 202, 240, 255);
        Color twilight = new Color(209, 159, 228, 255);
        Color celestia = new Color(254, 249, 252, 255);
        Color zecora = new Color(208, 204, 207, 255);
        Color changeling = new Color(40, 43, 41, 255);

        final Color kirin = new Color(111,222,111,255);
        final Color griffin = new Color(130,130,130,255);


        if (flagPix.equals(applejack)) {
            this.isPony = true;
            this.isPonySkin = true;
            this.race = PonyRace.EARTH;
        }
        if (flagPix.equals(zecora)) {
            this.isPony = true;
            this.isPonySkin = true;
            this.race = PonyRace.ZEBRA;
        }
        if (flagPix.equals(dashie)) {
            this.isPony = true;
            this.isPonySkin = true;
            this.isPegasus = true;
            this.race = PonyRace.PEGASUS;
        }
        if (flagPix.equals(twilight)) {
            this.isPony = true;
            this.isPonySkin = true;
            this.isUnicorn = true;
            this.race = PonyRace.UNICORN;
        }
        if (flagPix.equals(celestia)) {
            this.isPony = true;
            this.isPonySkin = true;
            this.isPegasus = true;
            this.isUnicorn = true;
            this.race = PonyRace.ALICORN;
        }
        if (flagPix.equals(changeling)) {
            this.isPony = true;
            this.isPonySkin = true;
            this.isPegasus = true;
            this.isUnicorn = true;
            this.race = PonyRace.CHANGELING;
        }
        if (flagPix.equals(kirin)) {
            this.isPony = true;
            this.isPonySkin = true;
            this.isPegasus = false;
            this.isUnicorn = false;
            this.isKirin=true;
            this.race = PonyRace.KIRIN;
        }
        if (flagPix.equals(griffin)) {
            this.isPony = true;
            this.isPonySkin = true;
            this.isPegasus = true;
            this.isGriffin = true;
            this.race = PonyRace.GRIFFIN;
        }
        Color tailcolor = new Color(bufferedimage.getRGB(1, 0), true);
        Color tailcolor1 = new Color(66, 88, 68, 255);
        Color tailcolor2 = new Color(70, 142, 136, 255);
        Color tailcolor3 = new Color(83, 75, 118, 255);
        Color tailcolor4 = new Color(138, 107, 127, 255);
        this.wantTail = tailcolor.equals(tailcolor1) ? 4 : (tailcolor.equals(tailcolor2) ? 3 : (tailcolor.equals(tailcolor3) ? 2 : (tailcolor.equals(tailcolor4) ? 1 : 0)));
        Color gendercolor = new Color(bufferedimage.getRGB(2, 0), true);
        Color gendercolor1 = new Color(255, 255, 255, 255);
        this.isMale = gendercolor.equals(gendercolor1);
        Color sizecolor = new Color(bufferedimage.getRGB(3, 0), true);
        Color scootaloo = new Color(255, 190, 83);
        Color bigmac = new Color(206, 50, 84);
        Color luna = new Color(42, 60, 120);
        if (ponyManager.getUseSizes() == 1) {
            this.size = sizecolor.equals(scootaloo) ? 0 : (sizecolor.equals(bigmac) ? 2 : (sizecolor.equals(luna) ? 3 : 1));
        }
        Color black = new Color(0, 0, 0);
        int scaleFactor = bufferedimage.getHeight() / 32;
        int tileSize = 8 * scaleFactor;
        Color advcutiecolor = new Color(bufferedimage.getRGB(tileSize / 2, 0), true);
        if (advcutiecolor.getAlpha() == 0) {
            this.advancedTexturing = false;
        } else {
            this.advancedTexturing = false;
            for (int x = tileSize / 2; x < tileSize; ++x) {
                for (int y = 0; y < tileSize; ++y) {
                    Color aColor = new Color(bufferedimage.getRGB(x, y), true);
                    if (aColor.equals(black)) continue;
                    this.advancedTexturing = true;
                }
            }
        }
        Color tempGlowColor = new Color(bufferedimage.getRGB(0, 1), true);
        this.glowColor = tempGlowColor.equals(black) || tempGlowColor.getAlpha() == 0 ? -12303190 : tempGlowColor.getRGB();


        Color StylesofManeColor = new Color(bufferedimage.getRGB(1, 1), true);
        Color LeftSideMane = new Color(10, 0, 0);
        Color RightSideMane = new Color(20, 0, 0);
        Color DoubleSidedMane = new Color(30, 0, 0);
        Color IrManeC = new Color(40, 0, 0);
        if (StylesofManeColor.equals(IrManeC)) {
            this.StylesofMane = 1;
        }
        if (StylesofManeColor.equals(LeftSideMane)) {
            this.StylesofMane = 2;
        }
        if (StylesofManeColor.equals(RightSideMane)) {
            this.StylesofMane = 3;
        }
        if (StylesofManeColor.equals(DoubleSidedMane)) {
            this.StylesofMane = 4;
        }
        Color rtail;
        this.RainboomTail = (rtail = new Color(bufferedimage.getRGB(0, 6), true)).getAlpha() != 0 && !rtail.equals(luna);

        this.skinChecked = true;
        MineLPLogger.debug("\tSkin check #%d for pony #%d completed. {IsPony:%b, Race:%s, FlagPixel:%s, AdvancedTexturing:%b}", this.skinCheckCount, this.ponyId, this.isPony, this.race.toString(), flagPix.toString(), this.advancedTexturing);
    }

    protected void resetValues() {
        this.isPony = false;
        this.isPonySkin = false;
        this.isPegasus = false;
        this.isUnicorn = false;
        this.isPonySkin = false;
        this.isMale = false;
        this.wantTail = 0;
        this.size = 1;
        this.StylesofMane = 0;
        this.RainboomTail = false;
    }

    public boolean isPony() {
        return this.isPony;
    }

    public boolean isPonySkin() {
        return this.isPonySkin;
    }

    public boolean isUnicorn() {
        return this.isUnicorn;
    }

    public boolean isPegasus() {
        return this.isPegasus;
    }

    public PonyRace getRace() {
        return this.race;
    }

    public int wantTail() {
        return this.wantTail;
    }

    public boolean isMale() {
        return this.isMale;
    }

    public int size() {
        if (ponyManager.getUseSizes() == 1) {
            return this.size;
        }
        return 1;
    }

    public boolean advancedTexturing() {
        return this.advancedTexturing;
    }

    public boolean isFlying() {
        return this.isFlying;
    }

    public boolean isGlow() {
        return this.isGlow;
    }

    public int glowColor() {
        return this.glowColor;
    }

    public int villagerProfession() {
        return this.villagerProfession;
    }

    public boolean isPegasusFlying(double posX, double posY, double posZ, float fallDistance, boolean isJumping, World equestria) {
        if (!this.isPegasus) {
            this.pegasusFlying = false;
            return false;
        }
        if (isJumping) {
            return true;
        }
        boolean falling = fallDistance > 0.0f;
        boolean levitating = fallDistance == this.previousFallDistance;
        boolean standingOnAir = falling && !levitating ? this.standingOnAir(posX, posY, posZ, 1.5f, equestria) : this.standingOnAir(posX, posY, posZ, 1.0f, equestria);
        if (!standingOnAir) {
            this.pegasusFlying = false;
            return false;
        }
        if (this.pegasusFlying) {
            return true;
        }
        if (levitating) {
            this.pegasusFlying = true;
            return true;
        }
        this.previousFallDistance = fallDistance;
        if (fallDistance < 2.0f) {
            return false;
        }
        this.pegasusFlying = true;
        return true;
    }

    public boolean standingOnAir(double posX, double posY, double posZ, float range, World equestria) {
        boolean foundSolidBlock = false;
        int y = this.isSpPlayer ? MathHelper.floor_double(posY - (double) this.defaultYOffset - 0.009999999776482582) : MathHelper.floor_double(posY - 0.009999999776482582);
        for (float shiftX = 0.0f - range; shiftX < range * 2.0f; shiftX += range) {
            for (float shiftZ = 0.0f - range; shiftZ < range * 2.0f; shiftZ += range) {

                int x = MathHelper.floor_double(posX + (double) shiftX);
                if (equestria.isAirBlock(x, y, MathHelper.floor_double(posZ + (double) shiftZ))) continue;
                foundSolidBlock = true;
            }
        }
        return !foundSolidBlock;
    }

    public PlayerModel getModel() {
        boolean is_a_pony = false;
        switch (ponyManager.getPonyLevel()) {
            case 0: {
                is_a_pony = false;
                break;
            }
            case 1: {
                if (!this.isPonySkin) {
                    is_a_pony = false;
                    break;
                }
                is_a_pony = true;
                break;
            }
            case 2: {
                is_a_pony = true;
            }
        }
        if (is_a_pony) {
            if (this.advancedTexturing) {
                return PMAPI.newPonyAdv;
            }
            return PMAPI.newPony;
        }
        if (ponyManager.getEQG() == 1) {
            return PMAPI.eqg;
        }
        return PMAPI.human;
    }

    public ResourceLocation getTextureResourceLocation() {
        return this.textureResourceLocation;
    }

    public void setVillager(int profession) {
        this.isVillager = true;
        this.villagerProfession = profession;
    }

    public void setIsPonySkin(boolean b) {
        this.isPonySkin = false;
    }
}

