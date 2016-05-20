/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.MathHelper
 *  org.lwjgl.opengl.GL11
 */
package com.minelittlepony.minelp.model;

import com.minelittlepony.minelp.model.ModelPlayer;
import com.minelittlepony.minelp.renderer.AniParams;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import org.lwjgl.opengl.GL11;

public class pm_Human
extends ModelPlayer {
    public ModelRenderer head;
    public ModelRenderer helmet;
    public ModelRenderer body;
    public ModelRenderer leftarm;
    public ModelRenderer rightarm;
    public ModelRenderer leftleg;
    public ModelRenderer rightleg;
    public ModelRenderer j;
    public ModelRenderer cloak;

    public pm_Human(String texture) {
        super(texture);
    }

    @Override
    public void init() {
        this.init(0.0f);
    }

    @Override
    public void init(float yoffset) {
        this.init(yoffset, 0.0f);
    }

    @Override
    public void init(float yoffset, float stretch) {
        this.cloak = new ModelRenderer((ModelBase)this, 0, 0);
        this.cloak.addBox(-5.0f, 0.0f, -1.0f, 10, 16, 1, stretch);
        this.j = new ModelRenderer((ModelBase)this, 24, 0);
        this.j.addBox(-3.0f, -6.0f, -1.0f, 6, 6, 1, stretch);
        this.head = new ModelRenderer((ModelBase)this, 0, 0);
        this.head.addBox(-4.0f, -8.0f, -4.0f, 8, 8, 8, stretch);
        this.head.setRotationPoint(0.0f, 0.0f + yoffset, 0.0f);
        this.helmet = new ModelRenderer((ModelBase)this, 32, 0);
        this.helmet.addBox(-4.0f, -8.0f, -4.0f, 8, 8, 8, stretch + 0.5f);
        this.helmet.setRotationPoint(0.0f, 0.0f + yoffset, 0.0f);
        this.body = new ModelRenderer((ModelBase)this, 16, 16);
        this.body.addBox(-4.0f, 0.0f, -2.0f, 8, 12, 4, stretch);
        this.body.setRotationPoint(0.0f, 0.0f + yoffset, 0.0f);
        this.rightarm = new ModelRenderer((ModelBase)this, 40, 16);
        this.rightarm.addBox(-3.0f, -2.0f, -2.0f, 4, 12, 4, stretch);
        this.rightarm.setRotationPoint(-5.0f, 2.0f + yoffset, 0.0f);
        this.leftarm = new ModelRenderer((ModelBase)this, 40, 16);
        this.leftarm.mirror = true;
        this.leftarm.addBox(-1.0f, -2.0f, -2.0f, 4, 12, 4, stretch);
        this.leftarm.setRotationPoint(5.0f, 2.0f + yoffset, 0.0f);
        this.rightleg = new ModelRenderer((ModelBase)this, 0, 16);
        this.rightleg.addBox(-2.0f, 0.0f, -2.0f, 4, 12, 4, stretch);
        this.rightleg.setRotationPoint(-2.0f, 12.0f + yoffset, 0.0f);
        this.leftleg = new ModelRenderer((ModelBase)this, 0, 16);
        this.leftleg.mirror = true;
        this.leftleg.addBox(-2.0f, 0.0f, -2.0f, 4, 12, 4, stretch);
        this.leftleg.setRotationPoint(2.0f, 12.0f + yoffset, 0.0f);
    }

    @Override
    public void animate(AniParams ani, Entity player) {
        this.animate(ani);
    }

    @Override
    public void animate(AniParams ani) {
        this.head.rotateAngleY = ani.horz / 57.29578f;
        this.head.rotateAngleX = ani.vert / 57.29578f;
        this.helmet.rotateAngleY = this.head.rotateAngleY;
        this.helmet.rotateAngleX = this.head.rotateAngleX;
        this.rightarm.rotateAngleX = MathHelper.cos((float)(ani.move * 2.0f / 3.0f + 3.141593f)) * 2.0f * ani.swing * 0.5f;
        this.leftarm.rotateAngleX = MathHelper.cos((float)(ani.move * 2.0f / 3.0f)) * 2.0f * ani.swing * 0.5f;
        this.rightarm.rotateAngleZ = 0.0f;
        this.leftarm.rotateAngleZ = 0.0f;
        this.rightleg.rotateAngleX = MathHelper.cos((float)(ani.move * 2.0f / 3.0f)) * 1.4f * ani.swing;
        this.leftleg.rotateAngleX = MathHelper.cos((float)(ani.move * 2.0f / 3.0f + 3.141593f)) * 1.4f * ani.swing;
        this.rightleg.rotateAngleY = 0.0f;
        this.leftleg.rotateAngleY = 0.0f;
        if (this.isRiding) {
            this.rightarm.rotateAngleX += -0.6283185f;
            this.leftarm.rotateAngleX += -0.6283185f;
            this.rightleg.rotateAngleX = -1.256637f;
            this.leftleg.rotateAngleX = -1.256637f;
            this.rightleg.rotateAngleY = 0.3141593f;
            this.leftleg.rotateAngleY = -0.3141593f;
        }
        if (this.m != 0) {
            this.rightarm.rotateAngleX = this.rightarm.rotateAngleX * 0.5f - 0.3141593f;
        }
        this.rightarm.rotateAngleY = 0.0f;
        this.leftarm.rotateAngleY = 0.0f;
        if (this.swingProgress > -9990.0f) {
            float swingprogress = this.swingProgress;
            this.body.rotateAngleY = MathHelper.sin((float)(MathHelper.sqrt_float((float)swingprogress) * 3.141593f * 2.0f)) * 0.2f;
            this.rightarm.rotationPointZ = MathHelper.sin((float)this.body.rotateAngleY) * 5.0f;
            this.rightarm.rotationPointX = (- MathHelper.cos((float)this.body.rotateAngleY)) * 5.0f;
            this.leftarm.rotationPointZ = (- MathHelper.sin((float)this.body.rotateAngleY)) * 5.0f;
            this.leftarm.rotationPointX = MathHelper.cos((float)this.body.rotateAngleY) * 5.0f;
            this.rightarm.rotateAngleY += this.body.rotateAngleY;
            this.leftarm.rotateAngleY += this.body.rotateAngleY;
            this.leftarm.rotateAngleX += this.body.rotateAngleY;
            swingprogress = 1.0f - this.swingProgress;
            swingprogress *= swingprogress;
            swingprogress *= swingprogress;
            swingprogress = 1.0f - swingprogress;
            float f7 = MathHelper.sin((float)(swingprogress * 3.141593f));
            float f8 = MathHelper.sin((float)(this.swingProgress * 3.141593f)) * (- this.head.rotateAngleX - 0.7f) * 0.75f;
            this.rightarm.rotateAngleX = (float)((double)this.rightarm.rotateAngleX - ((double)f7 * 1.2 + (double)f8));
            this.rightarm.rotateAngleY += this.body.rotateAngleY * 2.0f;
            this.rightarm.rotateAngleZ = MathHelper.sin((float)(this.swingProgress * 3.141593f)) * -0.4f;
        }
        if (this.issneak) {
            this.body.rotateAngleX = 0.5f;
            this.rightleg.rotateAngleX -= 0.0f;
            this.leftleg.rotateAngleX -= 0.0f;
            this.rightarm.rotateAngleX += 0.4f;
            this.leftarm.rotateAngleX += 0.4f;
            this.rightleg.rotationPointZ = 4.0f;
            this.leftleg.rotationPointZ = 4.0f;
            this.rightleg.rotationPointY = 9.0f;
            this.leftleg.rotationPointY = 9.0f;
            this.head.rotationPointY = 1.0f;
            this.helmet.rotationPointY = 1.0f;
        } else {
            this.body.rotateAngleX = 0.0f;
            this.rightleg.rotationPointZ = 0.0f;
            this.leftleg.rotationPointZ = 0.0f;
            this.rightleg.rotationPointY = 12.0f;
            this.leftleg.rotationPointY = 12.0f;
            this.head.rotationPointY = 0.0f;
            this.helmet.rotationPointY = 0.0f;
        }
        this.rightarm.rotateAngleZ += MathHelper.cos((float)(ani.tick * 0.09f)) * 0.05f + 0.05f;
        this.leftarm.rotateAngleZ -= MathHelper.cos((float)(ani.tick * 0.09f)) * 0.05f + 0.05f;
        this.rightarm.rotateAngleX += MathHelper.sin((float)(ani.tick * 0.067f)) * 0.05f;
        this.leftarm.rotateAngleX -= MathHelper.sin((float)(ani.tick * 0.067f)) * 0.05f;
        if (this.o) {
            float f7 = 0.0f;
            float f9 = 0.0f;
            this.rightarm.rotateAngleZ = 0.0f;
            this.leftarm.rotateAngleZ = 0.0f;
            this.rightarm.rotateAngleY = - 0.1f - f7 * 0.6f + this.head.rotateAngleY;
            this.leftarm.rotateAngleY = 0.1f - f7 * 0.6f + this.head.rotateAngleY + 0.4f;
            this.rightarm.rotateAngleX = -1.570796f + this.head.rotateAngleX;
            this.leftarm.rotateAngleX = -1.570796f + this.head.rotateAngleX;
            this.rightarm.rotateAngleX -= f7 * 1.2f - f9 * 0.4f;
            this.leftarm.rotateAngleX -= f7 * 1.2f - f9 * 0.4f;
            float f2 = ani.tick;
            this.rightarm.rotateAngleZ += MathHelper.cos((float)(f2 * 0.09f)) * 0.05f + 0.05f;
            this.leftarm.rotateAngleZ -= MathHelper.cos((float)(f2 * 0.09f)) * 0.05f + 0.05f;
            this.rightarm.rotateAngleX += MathHelper.sin((float)(f2 * 0.067f)) * 0.05f;
            this.leftarm.rotateAngleX -= MathHelper.sin((float)(f2 * 0.067f)) * 0.05f;
        }
    }

    @Override
    public void render(AniParams ani, boolean thirdperson) {
        if (thirdperson) {
            this.head.render(this.scale);
            this.helmet.render(this.scale);
            this.body.render(this.scale);
            this.leftarm.render(this.scale);
            this.leftleg.render(this.scale);
            this.rightleg.render(this.scale);
        }
        this.rightarm.render(this.scale);
    }

    @Override
    public void specials(RenderManager renderman, EntityLivingBase entity) {
        this.renderDrop(renderman, entity, this.rightarm, 1.0f, -0.0625f, 0.4375f, 0.0625f);
        this.renderPumpkin(renderman, entity, this.head, 0.625f, 0.0f, -0.25f, 0.0f);
        this.renderSkull(renderman, entity, this.head, 0.625f, 0.0f, -0.001f, 0.0f);
    }

    @Override
    public void renderEars(EntityLivingBase entity, float par2) {
        for (int i = 0; i < 2; ++i) {
            float f1 = entity.prevRotationYaw + (entity.rotationYaw - entity.prevRotationYaw) * par2 - (entity.prevRenderYawOffset + (entity.renderYawOffset - entity.prevRenderYawOffset) * par2);
            float f2 = entity.prevRotationPitch + (entity.rotationPitch - entity.prevRotationPitch) * par2;
            GL11.glPushMatrix();
            GL11.glRotatef((float)f1, (float)0.0f, (float)1.0f, (float)0.0f);
            GL11.glRotatef((float)f2, (float)1.0f, (float)0.0f, (float)0.0f);
            GL11.glTranslatef((float)(0.375f * (float)(i * 2 - 1)), (float)0.0f, (float)0.0f);
            GL11.glTranslatef((float)0.0f, (float)-0.375f, (float)0.0f);
            GL11.glRotatef((float)(- f2), (float)1.0f, (float)0.0f, (float)0.0f);
            GL11.glRotatef((float)(- f1), (float)0.0f, (float)1.0f, (float)0.0f);
            float f7 = 1.333333f;
            GL11.glScalef((float)f7, (float)f7, (float)f7);
            this.j.rotateAngleY = this.head.rotateAngleY;
            this.j.rotateAngleX = this.head.rotateAngleX;
            this.j.rotationPointX = 0.0f;
            this.j.rotationPointY = 0.0f;
            this.j.render(0.0625f);
            GL11.glPopMatrix();
        }
    }

    @Override
    public void renderCloak(EntityPlayer player, float par2) {
        GL11.glPushMatrix();
        GL11.glTranslatef((float)0.0f, (float)0.0f, (float)0.125f);
        double d = player.field_71091_bM + (player.field_71094_bP - player.field_71091_bM) * (double)par2 - (player.prevPosX + (player.posX - player.prevPosX) * (double)par2);
        double d1 = player.field_71096_bN + (player.field_71095_bQ - player.field_71096_bN) * (double)par2 - (player.prevPosY + (player.posY - player.prevPosY) * (double)par2);
        double d2 = player.field_71097_bO + (player.field_71085_bR - player.field_71097_bO) * (double)par2 - (player.prevPosZ + (player.posZ - player.prevPosZ) * (double)par2);
        float f10 = player.prevRenderYawOffset + (player.renderYawOffset - player.prevRenderYawOffset) * par2;
        double d3 = MathHelper.sin((float)(f10 * 3.1415927f / 180.0f));
        double d4 = - MathHelper.cos((float)(f10 * 3.1415927f / 180.0f));
        float f12 = (float)d1 * 10.0f;
        if (f12 < -6.0f) {
            f12 = -6.0f;
        }
        if (f12 > 32.0f) {
            f12 = 32.0f;
        }
        float f13 = (float)(d * d3 + d2 * d4) * 100.0f;
        float f14 = (float)(d * d4 - d2 * d3) * 100.0f;
        if (f13 < 0.0f) {
            f13 = 0.0f;
        }
        float f15 = player.prevCameraYaw + (player.cameraYaw - player.prevCameraYaw) * par2;
        f12 += MathHelper.sin((float)((player.prevDistanceWalkedModified + (player.distanceWalkedModified - player.prevDistanceWalkedModified) * par2) * 6.0f)) * 32.0f * f15;
        if (player.isSneaking()) {
            f12 += 25.0f;
        }
        GL11.glRotatef((float)(6.0f + f13 / 2.0f + f12), (float)1.0f, (float)0.0f, (float)0.0f);
        GL11.glRotatef((float)(f14 / 2.0f), (float)0.0f, (float)0.0f, (float)1.0f);
        GL11.glRotatef((float)((- f14) / 2.0f), (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glRotatef((float)180.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        this.cloak.render(0.0625f);
        GL11.glPopMatrix();
    }

    @Override
    public void renderStaticCloak(EntityLiving player, float par2) {
        GL11.glPushMatrix();
        GL11.glTranslatef((float)0.0f, (float)0.0f, (float)0.125f);
        GL11.glRotatef((float)3.0f, (float)1.0f, (float)0.0f, (float)0.0f);
        GL11.glRotatef((float)2.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glRotatef((float)180.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        this.cloak.render(0.0625f);
        GL11.glPopMatrix();
    }
}

