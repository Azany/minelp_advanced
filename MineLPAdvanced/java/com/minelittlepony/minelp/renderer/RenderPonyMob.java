/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  bqx
 *  com.google.common.collect.Maps
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelBiped
 *  net.minecraft.client.renderer.entity.Render
 *  net.minecraft.client.renderer.entity.RenderBiped
 *  net.minecraft.client.renderer.entity.RenderLiving
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.client.renderer.entity.RendererLivingEntity
 *  net.minecraft.client.renderer.texture.TextureUtil
 *  net.minecraft.client.resources.IResourceManager
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.monster.EntityPigZombie
 *  net.minecraft.entity.monster.EntitySkeleton
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemArmor
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package com.minelittlepony.minelp.renderer;

import com.google.common.collect.Maps;
import com.minelittlepony.minelp.PonyManager;
import com.minelittlepony.minelp.model.ModelArmor;
import com.minelittlepony.minelp.model.ModelPlayer;
import com.minelittlepony.minelp.model.PlayerModel;
import com.minelittlepony.minelp.model.pm_newPonyAdv;
import com.minelittlepony.minelp.util.MineLPReflection;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import java.io.IOException;
import java.util.HashSet;
import java.util.Map;

public abstract class RenderPonyMob
        extends RenderLiving {
    private static final int RENDERPASS_FLAG_ENCHANTED = 15;
    private static final int RENDERPASS_FLAG_OVERLAY = 16;
    private static final Map<String, ResourceLocation> field_110859_k = Maps.newHashMap();
    private static final Map<String, ResourceLocation> ponyArmorMap = Maps.newHashMap();
    private static final Map<String, ResourceLocation> onlyPonyArmorMap = Maps.newHashMap();
    private static final String[] bipedArmorFilenamePrefix = new String[]{"leather", "chainmail", "iron", "diamond", "gold"};
    private static HashSet<String> ponyArmors = new HashSet<String>();

    static {
        MineLPReflection.preCall();
        for (int i = 1; i <= 2; ++i) {
            for (int j = 0; j < bipedArmorFilenamePrefix.length; ++j) {
                String prefix = bipedArmorFilenamePrefix[j];
                ponyArmors.add("textures/models/armor/" + prefix + "_layer_" + i + ".png");
            }
        }
        ponyArmors.add("textures/models/armor/leather_layer_1_overlay.png");
        ponyArmors.add("textures/models/armor/leather_layer_2_overlay.png");
    }

    protected ModelPlayer mobModel;
    protected PlayerModel playerModel;

    public RenderPonyMob(PlayerModel playerModel) {
        super(playerModel.model, playerModel.shadowsize);
        this.mobModel = playerModel.model;
        this.playerModel = playerModel;
    }

    protected int shouldRenderPass(EntityLiving entity, int pass, float partialTicks) {
        Item item;
        boolean ponyArmor = false;
        ItemStack armorItemStack = entity.func_130225_q(3 - pass);
        if (armorItemStack != null && (item = armorItemStack.getItem()) instanceof ItemArmor) {
            int armorColor;
            ItemArmor armorItem = (ItemArmor) item;
            ModelArmor.slot = pass;
            ponyArmor = MineLPReflection.forgeAPI.installed ? this.bindForgeArmorTexture(entity, armorItemStack, armorItem, pass, null) : this.bindPonyArmorTexture(armorItem, pass, null);
            this.playerModel.armor.boxes(ponyArmor);
            ModelPlayer renderModel = this.playerModel.armor.base;
            if (MineLPReflection.forgeAPI.installed) {
                renderModel = (ModelPlayer) MineLPReflection.forgeAPI.invokeMethod("ForgeHooksClient.getArmorModel", null, entity, armorItemStack, pass, this.playerModel.armor.base);
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

    protected int shouldRenderPass(EntityLivingBase entity, int pass, float partialTicks) {
        return this.shouldRenderPass((EntityLiving) entity, pass, partialTicks);
    }

    protected void func_82408_c(EntityLiving entity, int pass, float partialTicks) {
        Item item;
        boolean ponyArmor = false;
        ItemStack armorItemStack = entity.func_130225_q(3 - pass);
        if (armorItemStack != null && (item = armorItemStack.getItem()) instanceof ItemArmor) {
            ItemArmor armorItem = (ItemArmor) item;
            int maxLength = bipedArmorFilenamePrefix.length;
            if (MineLPReflection.forgeAPI.installed) {
                this.bindForgeArmorTexture(entity, armorItemStack, (ItemArmor) item, pass, "overlay");
            } else {
                this.bindPonyArmorTexture(armorItem, pass, "overlay");
            }
            GL11.glColor3f(1.0f, 1.0f, 1.0f);
        }
    }

    protected void func_82408_c(EntityLivingBase entity, int pass, float partialTicks) {
        this.func_82408_c((EntityLiving) entity, pass, partialTicks);
    }

    public void doRender(EntityLiving entity, double xPosition, double yPosition, double zPosition, float yaw, float partialTicks) {
        ItemStack heldItem = entity.getHeldItem();
        this.playerModel.model.m = heldItem == null ? 0 : 1;
        this.playerModel.armor.modelArmor.m = this.playerModel.model.m;
        this.playerModel.armor.modelArmorChestplate.m = this.playerModel.model.m;
        if (entity.isChild()) {
            this.playerModel.model.size = 0;
            this.playerModel.armor.modelArmor.size = 0;
            this.playerModel.armor.modelArmorChestplate.size = 0;
        } else {
            this.playerModel.model.size = 1;
            this.playerModel.armor.modelArmor.size = 1;
            this.playerModel.armor.modelArmorChestplate.size = 1;
        }
        this.playerModel.model.issneak = false;
        this.playerModel.armor.modelArmor.issneak = false;
        this.playerModel.armor.modelArmorChestplate.issneak = false;
        this.playerModel.model.isFlying = false;
        this.playerModel.armor.modelArmor.isFlying = false;
        this.playerModel.armor.modelArmorChestplate.isFlying = false;
        this.playerModel.model.isPegasus = false;
        this.playerModel.armor.modelArmor.isPegasus = false;
        this.playerModel.armor.modelArmorChestplate.isPegasus = false;
        if (this.playerModel.model instanceof pm_newPonyAdv) {
            ((pm_newPonyAdv) this.playerModel.model).setHasWings_Compression(false);
        }
        if (entity instanceof EntitySkeleton) {
            this.playerModel.model.glowColor = 0;
            this.playerModel.armor.modelArmor.glowColor = 0;
            this.playerModel.armor.modelArmorChestplate.glowColor = 0;
            switch (entity.getEntityId() % 3) {
                case 0: {
                    this.playerModel.model.glowColor = -10066211;
                    this.playerModel.armor.modelArmor.glowColor = -10066211;
                    this.playerModel.armor.modelArmorChestplate.glowColor = -10066211;
                }
                case 1: {
                    this.playerModel.model.isUnicorn = true;
                    this.playerModel.armor.modelArmor.isUnicorn = true;
                    this.playerModel.armor.modelArmorChestplate.isUnicorn = true;
                    break;
                }
                case 2: {
                    this.playerModel.model.isUnicorn = false;
                    this.playerModel.armor.modelArmor.isUnicorn = false;
                    this.playerModel.armor.modelArmorChestplate.isUnicorn = false;
                }
            }
        } else {
            this.playerModel.model.isUnicorn = false;
            this.playerModel.armor.modelArmor.isUnicorn = false;
            this.playerModel.armor.modelArmorChestplate.isUnicorn = false;
        }
        if (entity instanceof EntityPigZombie) {
            this.playerModel.model.isMale = true;
            this.playerModel.armor.modelArmor.isMale = true;
            this.playerModel.armor.modelArmorChestplate.isMale = true;
        } else {
            this.playerModel.model.isMale = false;
            this.playerModel.armor.modelArmor.isMale = false;
            this.playerModel.armor.modelArmorChestplate.isMale = false;
        }
        this.playerModel.model.wantTail = entity instanceof EntitySkeleton ? 4 : 0;
        this.playerModel.model.isSleeping = false;
        this.playerModel.armor.modelArmor.isSleeping = false;
        this.playerModel.armor.modelArmorChestplate.isSleeping = false;
        this.playerModel.model.isVillager = false;
        if (PonyManager.getInstance().getShowScale() == 1) {
            this.shadowSize = 0.4f;
        }
        double yOrigin = yPosition - (double) entity.yOffset;
        if (entity.isSneaking()) {
            yOrigin -= 0.125;
        }
        super.doRender(entity, xPosition, yOrigin, zPosition, yaw, partialTicks);
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
        this.doRender((EntityLiving) entity, xPosition, yPosition, zPosition, yaw, partialTicks);
    }

    protected void renderEquippedItems(EntityLiving entity, float partialTicks) {
        ModelPlayer mainModel = this.playerModel.model;
        GL11.glColor3f(1.0f, 1.0f, 1.0f);
        super.renderEquippedItems(entity, partialTicks);
        super.renderArrowsStuckInEntity(entity, partialTicks);
        mainModel.specials(this.renderManager, entity);
    }

    protected void renderEquippedItems(EntityLivingBase entity, float partialTicks) {
        this.renderEquippedItems((EntityLiving) entity, partialTicks);
    }

    protected void renderLivingAt(EntityLivingBase entity, double xPosition, double yPosition, double zPosition) {
        super.renderLivingAt(entity, xPosition, yPosition, zPosition);
        if (PonyManager.getInstance().getShowScale() == 1) {
            GL11.glScalef(0.8f, 0.8f, 0.8f);
        }
    }

    protected abstract ResourceLocation getEntityTexture(EntityLiving var1);

    protected ResourceLocation getEntityTexture(Entity var1) {
        return this.getEntityTexture((EntityLiving) var1);
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

