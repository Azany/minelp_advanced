/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  com.mojang.authlib.GameProfile
 *  net.minecraft.block.Block
 *  net.minecraft.client.model.ModelBiped
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.client.renderer.ItemRenderer
 *  net.minecraft.client.renderer.RenderBlocks
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.client.renderer.tileentity.TileEntitySkullRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.projectile.EntityFishHook
 *  net.minecraft.init.Items
 *  net.minecraft.item.EnumAction
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemBlock
 *  net.minecraft.item.ItemBow
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTUtil
 *  net.minecraft.util.StringUtils
 *  org.lwjgl.opengl.GL11
 *  org.lwjgl.opengl.GL14
 */
package com.minelittlepony.minelp.model;

import com.minelittlepony.minelp.PonyManager;
import com.minelittlepony.minelp.renderer.AniParams;
import com.minelittlepony.minelp.util.MineLPReflection;
import com.mojang.authlib.GameProfile;
import net.minecraft.block.Block;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySkullRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.util.StringUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL14;

import java.util.UUID;

public abstract class ModelPlayer
        extends ModelBiped {
    public final float pi = 3.141593f;
    public String texture;
    public boolean issneak = false;
    public boolean isArmour = false;
    public int glowColor = -12303190;
    public boolean isPegasus;
    public boolean isUnicorn;
    public boolean isMale;
    public int wantTail;
    public int size;
    public boolean isVillager;
    public int villagerProfession;
    public boolean isFlying;
    public boolean isGlow;
    public boolean isSleeping;
    public int l;
    public int m;
    public boolean o;
    protected float strech = 0.0f;
    protected float scale = 0.0625f;
    public int StylesofMane;
    public boolean isRainboomTail;
    public boolean isKirin;
    public boolean isGriffin;

    public ModelPlayer(String texture) {
    }

    public void setStrech(float strech) {
        this.strech = strech;
    }

    public abstract void init();

    public abstract void init(float var1);

    public abstract void init(float var1, float var2);

    public abstract void animate(AniParams var1, Entity var2);

    public abstract void animate(AniParams var1);

    public abstract void render(AniParams var1, boolean var2);

    public abstract void specials(RenderManager var1, EntityLivingBase var2);

    public void render(Entity player, float Move, float Moveswing, float Loop, float Right, float Down, float Scale) {
        PonyManager.getInstance();
        AniParams ani = new AniParams(Move, Moveswing, Loop, Right, Down);
        this.animate(ani, player);
        this.render(ani, true);
    }

    protected void renderPumpkin(RenderManager renderman, EntityLivingBase entity, ModelRenderer box, float scale, float posx, float posy, float posz) {
        ItemStack pumpkin = entity.getEquipmentInSlot(4);
        if (pumpkin == null) {
            return;
        }
        if (MineLPReflection.forgeAPI.installed && !(pumpkin.getItem() instanceof ItemBlock)) {
            return;
        }
        GL11.glPushMatrix();
        if (box != null) {
            box.postRender(0.0625f);
        }
        boolean is3D = false;
        if (MineLPReflection.forgeAPI.installed) {
            Object customRenderer = MineLPReflection.forgeAPI.invokeMethod("MinecraftForgeClient.getItemRenderer", null, pumpkin, MineLPReflection.forgeAPI.getObject("IItemRenderer$ItemRenderType.EQUIPPED"));
            is3D = customRenderer != null && (Boolean) MineLPReflection.forgeAPI.invokeMethod("IItemRenderer.shouldUseRenderHelper", customRenderer, MineLPReflection.forgeAPI.getObject("IItemRenderer$ItemRenderType.EQUIPPED"), pumpkin, MineLPReflection.forgeAPI.getObject("IItemRenderer$ItemRendererHelper.BLOCK_3D"));
        }
        if (MineLPReflection.forgeAPI.installed ? is3D || RenderBlocks.renderItemIn3d(Block.getBlockFromItem(pumpkin.getItem()).getRenderType()) : pumpkin.getItem() instanceof ItemBlock) {
            if (RenderBlocks.renderItemIn3d(Block.getBlockFromItem(pumpkin.getItem()).getRenderType())) {
                GL11.glTranslatef(posx, posy, posz);
                GL11.glRotatef(90.0f, 0.0f, 1.0f, 0.0f);
                GL11.glScalef(scale, -scale, scale);
            }
            renderman.itemRenderer.renderItem(entity, pumpkin, 0);
        }
        GL11.glPopMatrix();
    }

    protected void renderSkull(RenderManager renderman, EntityLivingBase entity, ModelRenderer box, float scale, float posx, float posy, float posz) {
        ItemStack skull = entity.getEquipmentInSlot(4);
        if (skull == null) {
            return;
        }
        GL11.glPushMatrix();
        if (box != null) {
            box.postRender(0.0625f);
        }
        if (skull.getItem() == Items.skull) {
            GL11.glScalef(1.0625f, -1.0625f, -1.0625f);
            GameProfile ownerProfile = null;
            if (skull.hasTagCompound()) {
                NBTTagCompound skullTagCompound = skull.getTagCompound();
                if (skullTagCompound.hasKey("SkullOwner", 10)) {
                    ownerProfile = NBTUtil.func_152459_a(skullTagCompound.getCompoundTag("SkullOwner"));
                } else if (skullTagCompound.hasKey("SkullOwner", 8) && !StringUtils.isNullOrEmpty(skullTagCompound.getString("SkullOwner"))) {
                    ownerProfile = new GameProfile(null, skullTagCompound.getString("SkullOwner"));
                }
            }
            GL11.glTranslatef(posx, posy, posz);
            TileEntitySkullRenderer.field_147536_b.func_152674_a(-0.5f, 0.0f, -0.5f, 1, 180.0f, skull.getMetadata(), ownerProfile);
        }
        GL11.glPopMatrix();
    }

    public void renderEars(EntityLivingBase entity, float par2) {
    }

    public void renderCloak(EntityPlayer player, float par2) {
    }

    public void renderStaticCloak(EntityLiving player, float par2) {
    }

    protected void renderDrop(RenderManager renderman, EntityLivingBase entity, ModelRenderer box, float scalefactor, float posx, float posy, float posz) {
        ItemStack drop = entity.getHeldItem();
        if (drop == null) {
            return;
        }
        GL11.glPushMatrix();
        if (box != null) {
            box.postRender(scalefactor * 0.0625f);
        }
        GL11.glTranslatef(posx, posy, posz);
        EnumAction playerAction = null;
        if (entity instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) entity;
            if (player.fishEntity != null) {
                drop = new ItemStack(Items.stick);
            }
            if (player.getItemInUseCount() > 0) {
                playerAction = drop.getItemUseAction();
            }
        }
        boolean is3D = false;
        boolean isBlock = false;
        if (MineLPReflection.forgeAPI.installed) {
            Object customRenderer = MineLPReflection.forgeAPI.invokeMethod("MinecraftForgeClient.getItemRenderer", null, drop, MineLPReflection.forgeAPI.getObject("IItemRenderer$ItemRenderType.EQUIPPED"));
            is3D = customRenderer != null && (Boolean) MineLPReflection.forgeAPI.invokeMethod("IItemRenderer.shouldUseRenderHelper", customRenderer, MineLPReflection.forgeAPI.getObject("IItemRenderer$ItemRenderType.EQUIPPED"), drop, MineLPReflection.forgeAPI.getObject("IItemRenderer$ItemRendererHelper.BLOCK_3D")) != false;
            boolean bl = isBlock = drop.getItem() instanceof ItemBlock && drop.getItemSpriteNumber() == 0;
        }
        if (MineLPReflection.forgeAPI.installed ? drop.getItem() instanceof ItemBlock && (is3D || isBlock && RenderBlocks.renderItemIn3d(Block.getBlockFromItem(drop.getItem()).getRenderType())) : drop.getItem() instanceof ItemBlock && RenderBlocks.renderItemIn3d(Block.getBlockFromItem(drop.getItem()).getRenderType())) {
            GL11.glTranslatef(0.0f, 0.1875f, -0.3125f);
            GL11.glRotatef(20.0f, 1.0f, 0.0f, 0.0f);
            GL11.glRotatef(45.0f, 0.0f, 1.0f, 0.0f);
            float scale0 = 0.375f * scalefactor;
            GL11.glScalef(-scale0, -scale0, scale0);
        } else if (drop.getItem() == Items.bow) {
            GL11.glTranslatef(0.0f, 0.125f, 0.3125f);
            GL11.glRotatef(-20.0f, 0.0f, 1.0f, 0.0f);
            float scale3 = 0.625f * scalefactor;
            GL11.glScalef(scale3, -scale3, scale3);
            GL11.glRotatef(-100.0f, 1.0f, 0.0f, 0.0f);
            GL11.glRotatef(45.0f, 0.0f, 1.0f, 0.0f);
        } else if (drop.getItem().isFull3D()) {
            if (drop.getItem().shouldRotateAroundWhenRendering()) {
                GL11.glRotatef(180.0f, 0.0f, 0.0f, 1.0f);
                GL11.glTranslatef(0.0f, -0.125f, 0.0f);
            }
            if (playerAction == EnumAction.block && entity instanceof EntityPlayer && ((EntityPlayer) entity).getItemInUseCount() > 0) {
                GL11.glTranslatef(0.05f, 0.0f, -0.1f);
                GL11.glRotatef(-50.0f, 0.0f, 1.0f, 0.0f);
                GL11.glRotatef(-10.0f, 1.0f, 0.0f, 0.0f);
                GL11.glRotatef(-60.0f, 0.0f, 0.0f, 1.0f);
            }
            GL11.glTranslatef(0.0f, 0.1875f, 0.0f);
            float scale1 = 0.625f * scalefactor;
            GL11.glScalef(scale1, -scale1, scale1);
            GL11.glRotatef(-100.0f, 1.0f, 0.0f, 0.0f);
            GL11.glRotatef(45.0f, 0.0f, 1.0f, 0.0f);
        } else {
            GL11.glTranslatef(0.25f, 0.1875f, -0.1875f);
            float scale2 = 0.375f * scalefactor;
            GL11.glScalef(scale2, scale2, scale2);
            GL11.glRotatef(60.0f, 0.0f, 0.0f, 1.0f);
            GL11.glRotatef(-90.0f, 1.0f, 0.0f, 0.0f);
            GL11.glRotatef(20.0f, 0.0f, 0.0f, 1.0f);
        }
        if (drop.getItem().requiresMultipleRenderPasses()) {
            int pass = 0;
            while (MineLPReflection.forgeAPI.installed ? pass < (Integer) MineLPReflection.forgeAPI.invokeMethod("Item.getRenderPasses", drop.getItem(), drop.getMetadata()) : pass <= 1) {
                int color = drop.getItem().getColorFromItemStack(drop, pass);
                float r = (float) (color >> 16 & 255) / 255.0f;
                float g = (float) (color >> 8 & 255) / 255.0f;
                float b = (float) (color & 255) / 255.0f;
                GL11.glColor4f(r, g, b, 1.0f);
                renderman.itemRenderer.renderItem(entity, drop, pass);
                ++pass;
            }
        } else {
            int color = drop.getItem().getColorFromItemStack(drop, 0);
            float r = (float) (color >> 16 & 255) / 255.0f;
            float g = (float) (color >> 8 & 255) / 255.0f;
            float b = (float) (color & 255) / 255.0f;
            GL11.glColor4f(r, g, b, 1.0f);
            renderman.itemRenderer.renderItem(entity, drop, 0);
        }
        if (this.isUnicorn && this.glowColor != 0) {
            this.renderItemGlow(renderman, entity, drop);
        }
        GL11.glPopMatrix();
    }

    public void renderItemGlow(RenderManager renderman, EntityLivingBase entity, ItemStack drop) {
        GL11.glPushMatrix();
        GL11.glPushAttrib(24577);
        GL11.glDisable(2896);
        float red = (float) (this.glowColor >> 16 & 255) / 255.0f;
        float green = (float) (this.glowColor >> 8 & 255) / 255.0f;
        float blue = (float) (this.glowColor & 255) / 255.0f;
        float alpha = 0.2f;
        GL11.glEnable(3042);
        GL14.glBlendColor(red, green, blue, alpha);
        GL11.glBlendFunc(32769, 1);
        GL11.glColor4f(red, green, blue, alpha);
        GL11.glScalef(1.1f, 1.1f, 1.1f);
        if (!(drop.getItem() instanceof ItemBlock) || !RenderBlocks.renderItemIn3d(Block.getBlockFromItem(drop.getItem()).getRenderType())) {
            GL11.glTranslatef(0.02f, -0.06f, -0.02f);
        }
        renderman.itemRenderer.renderItem(entity, drop, 0);
        GL11.glPopAttrib();
        GL11.glPopMatrix();
    }

    public void renderCloak(float par1) {
    }
}

