/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  bqx
 *  com.google.common.collect.Maps
 *  com.mumfrey.liteloader.transformers.AppendInsns
 *  com.mumfrey.liteloader.transformers.Obfuscated
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.AbstractClientPlayer
 *  net.minecraft.client.entity.EntityPlayerSP
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelBiped
 *  net.minecraft.client.renderer.entity.Render
 *  net.minecraft.client.renderer.entity.RenderBiped
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.client.renderer.entity.RenderPlayer
 *  net.minecraft.client.renderer.entity.RendererLivingEntity
 *  net.minecraft.client.renderer.texture.TextureUtil
 *  net.minecraft.client.resources.IResourceManager
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.item.EnumAction
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemArmor
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.world.World
 *  org.lwjgl.opengl.GL11
 *  sv
 */
package com.minelittlepony.minelp.renderer;

import com.google.common.collect.Maps;
import com.minelittlepony.minelp.Pony;
import com.minelittlepony.minelp.PonyManager;
import com.minelittlepony.minelp.model.*;
import com.minelittlepony.minelp.util.MineLPPrivateFields;
import com.minelittlepony.minelp.util.MineLPReflection;
import com.mumfrey.liteloader.transformers.AppendInsns;
import com.mumfrey.liteloader.transformers.Obfuscated;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.entity.RendererLivingEntity;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import java.io.IOException;
import java.util.HashSet;
import java.util.Map;

public class RenderPony
        extends RendererLivingEntity {
    private static final int RENDERPASS_FLAG_ENCHANTED = 15;
    private static final int RENDERPASS_FLAG_OVERLAY = 16;
    private static final Map<String, ResourceLocation> field_110859_k;
    private static final Map<String, ResourceLocation> ponyArmorMap;
    private static final Map<String, ResourceLocation> onlyPonyArmorMap;
    private static final String[] bipedArmorFilenamePrefix;
    private static RenderPlayer __TARGET;
    private static AniParams aniParams;
    private static HashSet<String> ponyArmors;

    static {
        field_110859_k = Maps.newHashMap();
        ponyArmorMap = Maps.newHashMap();
        onlyPonyArmorMap = Maps.newHashMap();
        bipedArmorFilenamePrefix = new String[]{"leather", "chainmail", "iron", "diamond", "gold"};
        ponyArmors = new HashSet();
        MineLPReflection.preCall();
        aniParams = new AniParams(0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        for (int i = 1; i <= 2; ++i) {
            for (int j = 0; j < bipedArmorFilenamePrefix.length; ++j) {
                String prefix = bipedArmorFilenamePrefix[j];
                ponyArmors.add("textures/models/armor/" + prefix + "_layer_" + i + ".png");
            }
        }
        ponyArmors.add("textures/models/armor/leather_layer_1_overlay.png");
        ponyArmors.add("textures/models/armor/leather_layer_2_overlay.png");
    }

    private PlayerModel playerModel;

    public RenderPony() {
        super(null, 0.5f);
        throw new InstantiationError("Overlay classes must not be instantiated");
    }

    @AppendInsns(value = "<init>")
    private void init() {
        this.playerModel = PMAPI.newPonyAdv;
        this.mainModel = this.playerModel.model;
        this.shadowSize = this.playerModel.shadowsize;
    }

    public int shouldRenderPass(AbstractClientPlayer player, int pass, float partialTicks) {
        Item item;
        boolean ponyArmor = false;
        this.playerModel = this.getModel(player);
        ItemStack armorItemStack = player.inventory.armorItemInSlot(3 - pass);
        if (armorItemStack != null && (item = armorItemStack.getItem()) instanceof ItemArmor) {
            int armorColor;
            ItemArmor armorItem = (ItemArmor) item;
            ModelArmor.slot = pass;
            ponyArmor = MineLPReflection.forgeAPI.installed ? this.bindForgeArmorTexture(player, armorItemStack, armorItem, pass, null) : this.bindPonyArmorTexture(armorItem, pass, null);
            this.playerModel.armor.boxes(ponyArmor);
            ModelPlayer renderModel = this.playerModel.armor.base;
            if (MineLPReflection.forgeAPI.installed) {
                renderModel = (ModelPlayer) MineLPReflection.forgeAPI.invokeMethod("ForgeHooksClient.getArmorModel", null, player, armorItemStack, pass, this.playerModel.armor.base);
            }
            this.setRenderPassModel(renderModel);
            if (renderModel != null) {
                renderModel.swingProgress = this.mainModel.swingProgress;
                renderModel.isRiding = this.mainModel.isRiding;
                renderModel.isChild = this.mainModel.isChild;
            }
            if ((armorColor = armorItem.getColor(armorItemStack)) != -1) {
                float armorColorRed = (float) (armorColor >> 16 & 255) / 255.0f;
                float armotColorGreen = (float) (armorColor >> 8 & 255) / 255.0f;
                float armorColorBlue = (float) (armorColor & 255) / 255.0f;
                GL11.glColor3f(armorColorRed, armotColorGreen, armorColorBlue);
                if (armorItemStack.isItemEnchanted()) {
                    return 31;
                }
                return 16;
            }
            GL11.glColor3f(1.0f, 1.0f, 1.0f);
            if (armorItemStack.isItemEnchanted()) {
                return 15;
            }
            return 1;
        }
        return -1;
    }

    public int shouldRenderPass(EntityLivingBase par1EntityLivingBase, int par2, float par3) {
        return this.shouldRenderPass((AbstractClientPlayer) par1EntityLivingBase, par2, par3);
    }

    public void func_82408_c(AbstractClientPlayer player, int pass, float partialTicks) {
        Item item;
        boolean ponyArmor = false;
        ItemStack armorItemStack = player.inventory.armorItemInSlot(3 - pass);
        if (armorItemStack != null && (item = armorItemStack.getItem()) instanceof ItemArmor) {
            ItemArmor armorItem = (ItemArmor) item;
            int maxLength = bipedArmorFilenamePrefix.length;
            if (MineLPReflection.forgeAPI.installed) {
                this.bindForgeArmorTexture(player, armorItemStack, (ItemArmor) item, pass, "overlay");
            } else {
                this.bindPonyArmorTexture(armorItem, pass, "overlay");
            }
            GL11.glColor3f(1.0f, 1.0f, 1.0f);
        }
    }

    public void func_82408_c(EntityLivingBase player, int pass, float partialTicks) {
        this.func_82408_c((AbstractClientPlayer) player, pass, partialTicks);
    }

    public void doRender(AbstractClientPlayer player, double xPosition, double yPosition, double zPosition, float yaw, float partialTicks) {
        ItemStack currentItemStack = player.inventory.getCurrentItem();
        Pony thePony = PonyManager.getInstance().getPonyFromResourceRegistry(player);
        this.playerModel = this.getModel(player);
        this.mainModel = this.playerModel.model;
        this.playerModel.model.m = currentItemStack == null ? 0 : 1;
        this.playerModel.armor.modelArmor.m = this.playerModel.model.m;
        this.playerModel.armor.modelArmorChestplate.m = this.playerModel.model.m;
        if (currentItemStack != null && player.getItemInUseCount() > 0) {
            EnumAction itemInUseAction = currentItemStack.getItemUseAction();
            if (itemInUseAction == EnumAction.block) {
                this.playerModel.model.m = 3;
                this.playerModel.armor.modelArmor.m = 3;
                this.playerModel.armor.modelArmorChestplate.m = 3;
            } else if (itemInUseAction == EnumAction.bow) {
                this.playerModel.model.o = true;
                this.playerModel.armor.modelArmor.o = true;
                this.playerModel.armor.modelArmorChestplate.o = true;
            }
        }
        this.playerModel.armor.modelArmor.issneak = this.playerModel.model.issneak = player.isSneaking();
        this.playerModel.armor.modelArmorChestplate.issneak = this.playerModel.model.issneak;
        this.playerModel.model.isFlying = thePony.isFlying = thePony.isPegasusFlying(player.posX, player.posY, player.posZ, player.fallDistance, MineLPPrivateFields.isJumping.get(player), this.renderManager.worldObj);
        this.playerModel.armor.modelArmor.isFlying = thePony.isFlying;
        this.playerModel.armor.modelArmorChestplate.isFlying = thePony.isFlying;
        this.playerModel.armor.modelArmor.isPegasus = this.playerModel.model.isPegasus = thePony.isPegasus();
        this.playerModel.armor.modelArmorChestplate.isPegasus = this.playerModel.model.isPegasus;
        if (this.playerModel.model instanceof pm_newPonyAdv) {
            ((pm_newPonyAdv) this.playerModel.model).setHasWings_Compression(thePony.isPegasus());
        }
        this.playerModel.armor.modelArmor.isUnicorn = this.playerModel.model.isUnicorn = thePony.isUnicorn();
        this.playerModel.armor.modelArmorChestplate.isUnicorn = this.playerModel.model.isUnicorn;
        this.playerModel.armor.modelArmor.isMale = this.playerModel.model.isMale = thePony.isMale();
        this.playerModel.armor.modelArmorChestplate.isMale = this.playerModel.model.isMale;
        this.playerModel.armor.modelArmor.size = this.playerModel.model.size = thePony.size();
        this.playerModel.armor.modelArmorChestplate.size = this.playerModel.model.size;
        this.shadowSize = PonyManager.getInstance().getShowScale() == 1 ? (this.playerModel != PMAPI.human && this.playerModel != PMAPI.eqg ? (thePony.size() == 0 ? 0.25f : (thePony.size() == 1 ? 0.4f : (thePony.size() == 2 ? 0.45f : (thePony.size() == 3 ? 0.5f : 0.5f)))) : 0.5f) : 0.5f;
        double yOrigin = yPosition - (double) player.yOffset;
        if (player.isSneaking() && !(player instanceof EntityPlayerSP)) {
            yOrigin -= 0.125;
        }
        this.playerModel.model.glowColor = thePony.glowColor();
        this.playerModel.armor.modelArmor.isSleeping = this.playerModel.model.isSleeping = player.isPlayerSleeping();
        this.playerModel.armor.modelArmorChestplate.isSleeping = this.playerModel.model.isSleeping;
        this.playerModel.armor.modelArmorChestplate.swingProgress = this.playerModel.armor.modelArmor.swingProgress = this.playerModel.model.swingProgress;
        this.playerModel.model.wantTail = thePony.wantTail();
        this.playerModel.model.isRainboomTail = thePony.RainboomTail;
        this.playerModel.model.StylesofMane = thePony.StylesofMane;
        this.playerModel.model.isVillager = false;
        this.playerModel.armor.modelArmor.isVillager = false;
        this.playerModel.armor.modelArmorChestplate.isVillager = false;
        super.doRender(player, xPosition, yOrigin, zPosition, yaw, partialTicks);
        this.playerModel.model.o = false;
        this.playerModel.armor.modelArmor.o = false;
        this.playerModel.armor.modelArmorChestplate.o = false;
        this.playerModel.model.issneak = false;
        this.playerModel.armor.modelArmor.issneak = false;
        this.playerModel.armor.modelArmorChestplate.issneak = false;
        this.playerModel.model.m = 0;
        this.playerModel.armor.modelArmor.m = 0;
        this.playerModel.armor.modelArmorChestplate.m = 0;
    }

    public void doRender(Entity entity, double xPosition, double yPosition, double zPosition, float yaw, float partialTicks) {
        this.doRender((AbstractClientPlayer) entity, xPosition, yPosition, zPosition, yaw, partialTicks);
    }

    public void renderEquippedItems(AbstractClientPlayer player, float partialTicks) {
        ModelPlayer mainModel = this.playerModel.model;
        GL11.glColor3f(1.0f, 1.0f, 1.0f);
        super.renderEquippedItems(player, partialTicks);
        super.renderArrowsStuckInEntity(player, partialTicks);
        if (player.getCommandSenderName().equals("deadmau5") && player.func_152123_o()) {
            mainModel.renderEars(player, partialTicks);
        }
        if (player.func_152122_n() && !player.isInvisible() && !player.getHideCape()) {
            this.bindTexture(player.getLocationCape());
            mainModel.renderCloak(player, partialTicks);
        }
        mainModel.specials(this.renderManager, player);
    }

    public void renderEquippedItems(EntityLivingBase entity, float partialTicks) {
        this.renderEquippedItems((AbstractClientPlayer) entity, partialTicks);
    }

    public void renderFirstPersonArm(EntityPlayer par1EntityPlayer) {
        GL11.glColor3f(1.0f, 1.0f, 1.0f);
        PonyManager.getInstance();
        ModelPlayer humanModel = PMAPI.human.model;
        humanModel.swingProgress = 0.0f;
        humanModel.animate(aniParams);
        humanModel.render(aniParams, false);
    }

    @AppendInsns(value = "renderLivingAt")
    @Obfuscated(value = {"a", "func_77039_a"})
    public void setupPlayerScale(AbstractClientPlayer player, double xPosition, double yPosition, double zPosition) {
        if (PonyManager.getInstance().getShowScale() == 1 && this.playerModel != PMAPI.human && this.playerModel != PMAPI.eqg) {
            if (this.playerModel.model.size == 2) {
                GL11.glScalef(0.9f, 0.9f, 0.9f);
            } else if (this.playerModel.model.size == 1 || this.playerModel.model.size == 0) {
                GL11.glScalef(0.8f, 0.8f, 0.8f);
            }
        }
    }

    public ResourceLocation getEntityTexture(AbstractClientPlayer player) {
        Pony thePony = PonyManager.getInstance().getPonyFromResourceRegistry(player);
        return thePony.getTextureResourceLocation();
    }

    public ResourceLocation getEntityTexture(Entity entity) {
        return this.getEntityTexture((AbstractClientPlayer) entity);
    }

    protected PlayerModel getModel(AbstractClientPlayer player) {
        Pony thePony = PonyManager.getInstance().getPonyFromResourceRegistry(player);
        return thePony.getModel();
    }

    protected boolean bindPonyArmorTexture(ItemArmor armorPiece, int slot, String overlay) {
        int maxLength = bipedArmorFilenamePrefix.length;
        String overlayText = "";
        if (overlay != null) {
            overlayText = String.format("_%s", overlay);
        }
        String path = this.playerModel.armor.path + bipedArmorFilenamePrefix[armorPiece.renderIndex < maxLength ? armorPiece.renderIndex : maxLength - 1] + "_layer_" + this.playerModel.armor.subimage() + overlayText + ".png";
        if (PonyManager.getInstance().getPonyArmor() == 1 && this.playerModel.model instanceof pm_newPonyAdv) {
            Object[] armorResourceAndState = this.getPonyResourceLocation(path);
            this.bindTexture((ResourceLocation) armorResourceAndState[0]);
            return (Boolean) armorResourceAndState[1];
        }
        this.bindTexture(RenderBiped.func_110858_a(armorPiece, slot, overlay));
        return false;
    }

    protected boolean bindForgeArmorTexture(Entity playerEntity, ItemStack armorStack, ItemArmor armorPiece, int slot, String overlay) {
        boolean ponyArmor;
        String path = "";
        try {
            Object[] arrobject = new Object[3];
            arrobject[0] = bipedArmorFilenamePrefix[armorPiece.renderIndex];
            arrobject[1] = slot == 2 ? 2 : 1;
            arrobject[2] = overlay == null ? "" : String.format("_%s", overlay);
            path = String.format("textures/models/armor/%s_layer_%d%s.png", arrobject);
        } catch (ArrayIndexOutOfBoundsException ex) {
            // empty catch block
        }
        path = (String) MineLPReflection.forgeAPI.invokeMethod("ForgeHooksClient.getArmorTexture", null, playerEntity, armorStack, path, slot, overlay);
        if (ponyArmors.contains(path)) {
            ponyArmor = this.bindPonyArmorTexture(armorPiece, slot, overlay);
        } else {
            ResourceLocation forgeResourceLocation;
            ponyArmor = false;
            if (PonyManager.getInstance().getPonyArmor() == 1 && this.playerModel.model instanceof pm_newPonyAdv) {
                Object[] armorResourceAndState = this.getPonyResourceLocation(path);
                forgeResourceLocation = (ResourceLocation) armorResourceAndState[0];
                ponyArmor = (Boolean) armorResourceAndState[1];
            } else {
                forgeResourceLocation = field_110859_k.get(path);
                if (forgeResourceLocation == null) {
                    forgeResourceLocation = new ResourceLocation(path);
                    field_110859_k.put(path, forgeResourceLocation);
                }
                ponyArmor = false;
            }
            this.bindTexture(forgeResourceLocation);
        }
        return ponyArmor;
    }

    protected Object[] getPonyResourceLocation(String path) {
        ResourceLocation ponyResourceLocation;
        boolean ponyArmor;
        ponyArmor = false;
        String ponyPath = path.replace(".png", "_pony.png");
        ponyResourceLocation = ponyArmorMap.get(path);
        if (ponyResourceLocation == null) {
            ResourceLocation ponyArmorResource = new ResourceLocation(ponyPath);
            try {
                TextureUtil.readImageData(Minecraft.getMinecraft().getResourceManager(), ponyArmorResource);
                if (ponyArmorResource != null) {
                    ponyResourceLocation = ponyArmorResource;
                    ponyArmorMap.put(path, ponyArmorResource);
                    onlyPonyArmorMap.put(path, ponyArmorResource);
                    ponyArmor = true;
                }
            } catch (IOException ex) {
                ponyResourceLocation = field_110859_k.get(path);
                if (ponyResourceLocation == null) {
                    ponyResourceLocation = new ResourceLocation(path);
                    field_110859_k.put(path, ponyResourceLocation);
                }
                ponyArmorMap.put(path, ponyResourceLocation);
                ponyArmor = false;
            }
        } else {
            ponyArmor = true;
            ponyResourceLocation = onlyPonyArmorMap.get(path);
            if (ponyResourceLocation == null) {
                ponyResourceLocation = ponyArmorMap.get(path);
                ponyArmor = false;
            }
        }
        return new Object[]{ponyResourceLocation, ponyArmor};
    }
}

