/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.util.MathHelper
 */
package com.minelittlepony.minelp.model;

import com.minelittlepony.minelp.renderer.AniParams;
import com.minelittlepony.minelp.renderer.PlaneRenderer;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;

public class pm_newPonyArmor
        extends pm_newPonyAdv {
    public ModelRenderer Bodypiece;
    public ModelRenderer extBody;
    public ModelRenderer[] extHead;
    public ModelRenderer[] extLegs;
    public PlaneRenderer[] extSubLegs;

    public pm_newPonyArmor(String texture) {
        super(texture);
        this.isArmour = true;
    }

    @Override
    public void animate(AniParams aniparams) {
        this.checkRainboom(aniparams.swing);
        this.rotateHead(aniparams.horz, aniparams.vert);
        float bodySwingRotation = 0.0f;
        if (this.swingProgress > -9990.0f && !this.isUnicorn) {
            bodySwingRotation = MathHelper.sin((float) (MathHelper.sqrt_float((float) this.swingProgress) * 3.1415927f * 2.0f)) * 0.2f;
        }
        this.Body.rotateAngleY = bodySwingRotation * 0.2f;
        this.Bodypiece.rotateAngleY = bodySwingRotation * 0.2f;
        this.extBody.rotateAngleY = bodySwingRotation * 0.2f;
        this.setLegs(aniparams.move, aniparams.swing, aniparams.tick);
        this.holdItem();
        this.swingItem(this.swingProgress);
        if (this.issneak && !this.isFlying) {
            this.adjustBody(this.BODY_ROTATE_ANGLE_X_SNEAK, this.BODY_RP_Y_SNEAK, this.BODY_RP_Z_SNEAK);
            this.sneakLegs();
            this.setHead(0.0f, 6.0f, -2.0f);
        } else {
            this.adjustBody(this.BODY_ROTATE_ANGLE_X_NOTSNEAK, this.BODY_RP_Y_NOTSNEAK, this.BODY_RP_Z_NOTSNEAK);
            this.RightLeg.rotationPointY = this.FRONT_LEG_RP_Y_NOTSNEAK;
            this.LeftLeg.rotationPointY = this.FRONT_LEG_RP_Y_NOTSNEAK;
            this.extLegs[0].rotationPointY = this.FRONT_LEG_RP_Y_NOTSNEAK;
            this.extLegs[1].rotationPointY = this.FRONT_LEG_RP_Y_NOTSNEAK;
            this.RightLegJoint.rotationPointY = this.FRONT_LEG_RP_Y_NOTSNEAK;
            this.LeftLegJoint.rotationPointY = this.FRONT_LEG_RP_Y_NOTSNEAK;
            this.extLegs[2].rotationPointY = this.FRONT_LEG_RP_Y_NOTSNEAK;
            this.extLegs[3].rotationPointY = this.FRONT_LEG_RP_Y_NOTSNEAK;
            this.extSubLegs[0].rotationPointY = this.FRONT_LEG_RP_Y_NOTSNEAK;
            this.extSubLegs[1].rotationPointY = this.FRONT_LEG_RP_Y_NOTSNEAK;
            this.extSubLegs[2].rotationPointY = this.FRONT_LEG_RP_Y_NOTSNEAK;
            this.extSubLegs[3].rotationPointY = this.FRONT_LEG_RP_Y_NOTSNEAK;
            this.extSubLegs[4].rotationPointY = this.FRONT_LEG_RP_Y_NOTSNEAK;
            this.extSubLegs[5].rotationPointY = this.FRONT_LEG_RP_Y_NOTSNEAK;
            this.swingArms(aniparams.tick);
            this.setHead(0.0f, 0.0f, 0.0f);
        }
        if (this.isSleeping) {
            this.isRiding = false;
            this.issneak = false;
            this.isFlying = false;
            this.ponySleep();
        }
        if (this.isRiding) {
            this.isSleeping = false;
            this.issneak = false;
            this.isFlying = false;
            this.ponySit();
        }
        if (this.o) {
            this.aimBow(aniparams.tick);
        }
        this.fixSpecialRotationPoints(aniparams.move);
    }

    @Override
    protected void setHead(float posX, float posY, float posZ) {
        this.setRotationPoint(this.head, posX, posY, posZ);
        this.setRotationPoint(this.helmet, posX, posY, posZ);
        this.setRotationPoint(this.extHead[0], posX, posY, posZ);
        this.setRotationPoint(this.extHead[1], posX, posY, posZ);
    }

    @Override
    protected void rotateHead(float horz, float vert) {
        float headRotateAngleY;
        float headRotateAngleX;
        if (this.isSleeping) {
            headRotateAngleY = 1.4f;
            headRotateAngleX = 0.1f;
        } else {
            headRotateAngleY = horz / 57.29578f;
            headRotateAngleX = vert / 57.29578f;
        }
        if (headRotateAngleX > 0.5f) {
            headRotateAngleX = 0.5f;
        }
        if (headRotateAngleX < -0.5f) {
            headRotateAngleX = -0.5f;
        }
        this.head.rotateAngleY = headRotateAngleY;
        this.head.rotateAngleX = headRotateAngleX;
        this.extHead[0].rotateAngleY = headRotateAngleY;
        this.extHead[0].rotateAngleX = headRotateAngleX;
        this.extHead[1].rotateAngleY = headRotateAngleY;
        this.extHead[1].rotateAngleX = headRotateAngleX;
        this.helmet.rotateAngleY = headRotateAngleY;
        this.helmet.rotateAngleX = headRotateAngleX;
    }

    @Override
    protected void adjustBody(float rotateAngleX, float rotationPointY, float rotationPointZ) {
        this.Body.rotateAngleX = rotateAngleX;
        this.Body.rotationPointY = rotationPointY;
        this.Body.rotationPointZ = rotationPointZ;
        this.Bodypiece.rotateAngleX = rotateAngleX;
        this.Bodypiece.rotationPointY = rotationPointY;
        this.Bodypiece.rotationPointZ = rotationPointZ;
        this.extBody.rotateAngleX = rotateAngleX;
        this.extBody.rotationPointY = rotationPointY;
        this.extBody.rotationPointZ = rotationPointZ;
    }

    protected void ridingPony() {
        this.setHead(this.head.rotationPointX + 0.0f, this.head.rotationPointY + this.RIDING_SHIFT_Y, this.head.rotationPointZ + this.RIDING_SHIFT_Z);
        this.shiftRotationPoint(this.Body, 0.0f, this.RIDING_SHIFT_Y, this.RIDING_SHIFT_Z);
        this.shiftRotationPoint(this.Bodypiece, 0.0f, this.RIDING_SHIFT_Y, this.RIDING_SHIFT_Z);
        this.shiftRotationPoint(this.extBody, 0.0f, this.RIDING_SHIFT_Y, this.RIDING_SHIFT_Z);
        this.shiftRotationPoint(this.LeftArm, 0.0f, this.RIDING_SHIFT_Y, this.RIDING_SHIFT_Z);
        this.shiftRotationPoint(this.LeftShoulder, 0.0f, this.RIDING_SHIFT_Y, this.RIDING_SHIFT_Z);
        this.shiftRotationPoint(this.RightArm, 0.0f, this.RIDING_SHIFT_Y, this.RIDING_SHIFT_Z);
        this.shiftRotationPoint(this.RightShoulder, 0.0f, this.RIDING_SHIFT_Y, this.RIDING_SHIFT_Z);
        this.shiftRotationPoint(this.LeftLeg, 0.0f, this.RIDING_SHIFT_Y, this.RIDING_SHIFT_Z);
        this.shiftRotationPoint(this.RightLeg, 0.0f, this.RIDING_SHIFT_Y, this.RIDING_SHIFT_Z);
        this.shiftRotationPoint(this.LeftLegJoint, 0.0f, this.RIDING_SHIFT_Y, this.RIDING_SHIFT_Z);
        this.shiftRotationPoint(this.RightLegJoint, 0.0f, this.RIDING_SHIFT_Y, this.RIDING_SHIFT_Z);
        this.shiftRotationPoint(this.extLegs[0], 0.0f, this.RIDING_SHIFT_Y, this.RIDING_SHIFT_Z);
        this.shiftRotationPoint(this.extLegs[1], 0.0f, this.RIDING_SHIFT_Y, this.RIDING_SHIFT_Z);
        this.shiftRotationPoint(this.extLegs[2], 0.0f, this.RIDING_SHIFT_Y, this.RIDING_SHIFT_Z);
        this.shiftRotationPoint(this.extLegs[3], 0.0f, this.RIDING_SHIFT_Y, this.RIDING_SHIFT_Z);
        this.shiftRotationPoint(this.extSubLegs[0], 0.0f, this.RIDING_SHIFT_Y, this.RIDING_SHIFT_Z);
        this.shiftRotationPoint(this.extSubLegs[1], 0.0f, this.RIDING_SHIFT_Y, this.RIDING_SHIFT_Z);
        this.shiftRotationPoint(this.extSubLegs[2], 0.0f, this.RIDING_SHIFT_Y, this.RIDING_SHIFT_Z);
        this.shiftRotationPoint(this.extSubLegs[3], 0.0f, this.RIDING_SHIFT_Y, this.RIDING_SHIFT_Z);
        this.shiftRotationPoint(this.extSubLegs[4], 0.0f, this.RIDING_SHIFT_Y, this.RIDING_SHIFT_Z);
        this.shiftRotationPoint(this.extSubLegs[5], 0.0f, this.RIDING_SHIFT_Y, this.RIDING_SHIFT_Z);
    }

    @Override
    protected void renderHead() {
        this.head.render(this.scale);
        this.extHead[0].render(this.scale);
        this.extHead[1].render(this.scale);
        this.helmet.render(this.scale);
    }

    @Override
    protected void renderHorn() {
    }

    @Override
    protected void renderPlot() {
    }

    @Override
    protected void renderNeck() {
    }

    @Override
    protected void renderBody() {
        this.Body.render(this.scale);
        this.Bodypiece.render(this.scale);
        this.extBody.render(this.scale);
    }

    @Override
    protected void renderTail() {
    }

    @Override
    protected void renderLegs() {
        this.LeftArm.render(this.scale);
        this.RightArm.render(this.scale);
        this.LeftLeg.render(this.scale);
        this.RightLeg.render(this.scale);
        this.LeftLegJoint.render(this.scale);
        this.RightLegJoint.render(this.scale);
        this.extLegs[0].render(this.scale);
        this.extLegs[1].render(this.scale);
        this.extLegs[2].render(this.scale);
        this.extLegs[3].render(this.scale);
        this.extSubLegs[0].render(this.scale);
        this.extSubLegs[1].render(this.scale);
        this.extSubLegs[2].render(this.scale);
        this.extSubLegs[3].render(this.scale);
        this.extSubLegs[4].render(this.scale);
        this.extSubLegs[5].render(this.scale);
    }

    @Override
    protected void initTextures() {
        this.extHead = new ModelRenderer[2];
        this.extLegs = new ModelRenderer[4];
        this.extSubLegs = new PlaneRenderer[6];
        this.RightSubLeg = new PlaneRenderer[3];
        this.LeftSubLeg = new PlaneRenderer[3];
        this.initHeadTextures();
        this.initBodyTextures();
        this.initLegTextures();
    }

    @Override
    protected void initHeadTextures() {
        this.head = new ModelRenderer((ModelBase) this, 0, 0);
        this.helmet = new ModelRenderer((ModelBase) this, 32, 0);
        this.extHead[0] = new ModelRenderer((ModelBase) this, 0, 0);
        this.extHead[1] = new ModelRenderer((ModelBase) this, 0, 4);
    }

    @Override
    protected void initBodyTextures() {
        this.Body = new ModelRenderer((ModelBase) this, 16, 16);
        this.Bodypiece = new ModelRenderer((ModelBase) this, 0, 0);
        this.extBody = new ModelRenderer((ModelBase) this, 16, 8);
    }

    @Override
    protected void initLegTextures() {
        this.RightArm = new ModelRenderer(this, 0, 16);
        this.RightShoulder = new ModelRenderer(this, 24, 22);
        this.LeftArm = new ModelRenderer(this, 0, 16);
        this.LeftArm.mirror = true;
        this.LeftShoulder = new ModelRenderer(this, 13, 22);
        this.LeftShoulder.mirror = true;
        this.RightLeg = new ModelRenderer(this, 0, 18);
        this.LeftLeg = new ModelRenderer(this, 0, 18);
        this.LeftLeg.mirror = true;
        this.RightLegJoint = new ModelRenderer(this, 0, 16);
        this.LeftLegJoint = new ModelRenderer(this, 0, 16);
        this.LeftLegJoint.mirror = true;
        this.RightSubLeg[0] = new PlaneRenderer(this, 4, 22);
        this.LeftSubLeg[0] = new PlaneRenderer(this, 4, 22);
        this.LeftSubLeg[0].mirror = true;
        this.RightSubLeg[1] = new PlaneRenderer(this, 12, 22);
        this.LeftSubLeg[1] = new PlaneRenderer(this, 12, 22);
        this.LeftSubLeg[1].mirror = true;
        this.RightSubLeg[2] = new PlaneRenderer(this, 8, 16);
        this.LeftSubLeg[2] = new PlaneRenderer(this, 8, 16);
        this.LeftSubLeg[2].mirror = true;
        this.SteveArm = new ModelRenderer(this, 0, 16);
        this.unicornarm = new ModelRenderer(this, 0, 16);
        this.extLegs[0] = new ModelRenderer(this, 48, 10);
        this.extLegs[1] = new ModelRenderer(this, 48, 10);
        this.extLegs[1].mirror = true;
        this.extLegs[2] = new ModelRenderer(this, 48, 8);
        this.extLegs[3] = new ModelRenderer(this, 48, 8);
        this.extLegs[3].mirror = true;
        this.extSubLegs[0] = new PlaneRenderer(this, 52, 15);
        this.extSubLegs[1] = new PlaneRenderer(this, 52, 15);
        this.extSubLegs[1].mirror = true;
        this.extSubLegs[2] = new PlaneRenderer(this, 60, 15);
        this.extSubLegs[3] = new PlaneRenderer(this, 60, 15);
        this.extSubLegs[3].mirror = true;
        this.extSubLegs[4] = new PlaneRenderer(this, 56, 8);
        this.extSubLegs[5] = new PlaneRenderer(this, 56, 8);
        this.extSubLegs[5].mirror = true;
    }

    @Override
    protected void initPositions(float yOffset, float stretch) {
        this.initHeadPositions(yOffset, stretch);
        this.initBodyPositions(yOffset, stretch);
        this.initLegPositions(yOffset, stretch);
    }

    @Override
    protected void initHeadPositions(float yOffset, float stretch) {
        this.head.addBox(-4.0f + this.HEAD_CENTRE_X, -4.0f + this.HEAD_CENTRE_Y, -4.0f + this.HEAD_CENTRE_Z, 8, 8, 8, stretch * 1.1f);
        this.head.setRotationPoint(this.HEAD_RP_X, this.HEAD_RP_Y + yOffset, this.HEAD_RP_Z);
        this.extHead[0].addBox(-4.0f + this.HEAD_CENTRE_X, -6.0f + this.HEAD_CENTRE_Y, 1.0f + this.HEAD_CENTRE_Z, 2, 2, 2, stretch * 0.5f);
        this.extHead[0].setRotationPoint(this.HEAD_RP_X, this.HEAD_RP_Y + yOffset, this.HEAD_RP_Z);
        this.extHead[1].addBox(2.0f + this.HEAD_CENTRE_X, -6.0f + this.HEAD_CENTRE_Y, 1.0f + this.HEAD_CENTRE_Z, 2, 2, 2, stretch * 0.5f);
        this.extHead[1].setRotationPoint(this.HEAD_RP_X, this.HEAD_RP_Y + yOffset, this.HEAD_RP_Z);
        this.helmet.addBox(-4.0f + this.HEAD_CENTRE_X, -4.0f + this.HEAD_CENTRE_Y, -4.0f + this.HEAD_CENTRE_Z, 8, 8, 8, stretch * 1.1f + 0.5f);
        this.helmet.setRotationPoint(this.HEAD_RP_X, this.HEAD_RP_Y + yOffset, this.HEAD_RP_Z);
    }

    @Override
    protected void initBodyPositions(float yOffset, float stretch) {
        this.Body.addBox(-4.0f, 4.0f, -2.0f, 8, 8, 4, stretch);
        this.Body.setRotationPoint(this.HEAD_RP_X, this.HEAD_RP_Y + yOffset, this.HEAD_RP_Z);
        this.Bodypiece.addBox(-4.0f, 4.0f, 6.0f, 8, 8, 8, stretch);
        this.Bodypiece.setRotationPoint(this.HEAD_RP_X, this.HEAD_RP_Y + yOffset, this.HEAD_RP_Z);
        this.extBody.addBox(-4.0f, 4.0f, -2.0f, 8, 8, 16, stretch);
        this.extBody.setRotationPoint(this.HEAD_RP_X, this.HEAD_RP_Y + yOffset, this.HEAD_RP_Z);
    }

    @Override
    protected void initLegPositions(float var1, float var2) {
        super.initLegPositions(var1, var2);
        this.extLegs[0].addBox(-2.0f + this.THIRDP_ARM_CENTRE_X, -4.0f + this.THIRDP_ARM_CENTRE_Y, -1.0f + this.THIRDP_ARM_CENTRE_Z, 4, 10, 4, var2);
        this.extLegs[0].setRotationPoint(-3.0f, 0.0f + var1, 0.0f);
        this.extLegs[1].addBox(-2.0f + this.THIRDP_ARM_CENTRE_X, -4.0f + this.THIRDP_ARM_CENTRE_Y, -1.0f + this.THIRDP_ARM_CENTRE_Z, 4, 10, 4, var2);
        this.extLegs[1].setRotationPoint(3.0f, 0.0f + var1, 0.0f);
        this.extLegs[2].addBox(-2.0f + this.THIRDP_ARM_CENTRE_X, -6.0f + this.THIRDP_ARM_CENTRE_Y, -2.0f + this.THIRDP_ARM_CENTRE_Z, 4, 2, 4, var2);
        this.extLegs[2].setRotationPoint(-3.0f, 0.0f + var1, 0.0f);
        this.extLegs[3].addBox(-2.0f + this.THIRDP_ARM_CENTRE_X, -6.0f + this.THIRDP_ARM_CENTRE_Y, -2.0f + this.THIRDP_ARM_CENTRE_Z, 4, 2, 4, var2);
        this.extLegs[3].setRotationPoint(3.0f, 0.0f + var1, 0.0f);
        this.extSubLegs[0].addBottomPlane(-2.0f + this.THIRDP_ARM_CENTRE_X, -4.0f + this.THIRDP_ARM_CENTRE_Y, -2.0f + this.THIRDP_ARM_CENTRE_Z, 4, 0, 1, var2);
        this.extSubLegs[0].setRotationPoint(-3.0f, 0.0f + var1, 0.0f);
        this.extSubLegs[1].addBottomPlane(-2.0f + this.THIRDP_ARM_CENTRE_X, -4.0f + this.THIRDP_ARM_CENTRE_Y, -2.0f + this.THIRDP_ARM_CENTRE_Z, 4, 0, 1, var2);
        this.extSubLegs[1].setRotationPoint(3.0f, 0.0f + var1, 0.0f);
        this.extSubLegs[2].addBottomPlane(-2.0f + this.THIRDP_ARM_CENTRE_X, -4.0f + this.THIRDP_ARM_CENTRE_Y, 2.0f + this.THIRDP_ARM_CENTRE_Z, 4, 0, 1, var2);
        this.extSubLegs[2].setRotationPoint(-3.0f, 0.0f + var1, 0.0f);
        this.extSubLegs[3].addBottomPlane(-2.0f + this.THIRDP_ARM_CENTRE_X, -4.0f + this.THIRDP_ARM_CENTRE_Y, 2.0f + this.THIRDP_ARM_CENTRE_Z, 4, 0, 1, var2);
        this.extSubLegs[3].setRotationPoint(3.0f, 0.0f + var1, 0.0f);
        this.extSubLegs[4].addBottomPlane(-2.0f + this.THIRDP_ARM_CENTRE_X, 6.0f + this.THIRDP_ARM_CENTRE_Y, -1.0f + this.THIRDP_ARM_CENTRE_Z, 4, 0, 4, var2);
        this.extSubLegs[4].setRotationPoint(-3.0f, 0.0f + var1, 0.0f);
        this.extSubLegs[5].addBottomPlane(-2.0f + this.THIRDP_ARM_CENTRE_X, 6.0f + this.THIRDP_ARM_CENTRE_Y, -1.0f + this.THIRDP_ARM_CENTRE_Z, 4, 0, 4, var2);
        this.extSubLegs[5].setRotationPoint(3.0f, 0.0f + var1, 0.0f);
    }

    protected void syncLegs() {
        int var1 = 0;
        this.extLegs[0].rotateAngleX = this.RightLeg.rotateAngleX;
        this.extLegs[0].rotateAngleY = this.RightLeg.rotateAngleY;
        this.extLegs[0].rotateAngleZ = this.RightLeg.rotateAngleZ;
        this.extLegs[0].rotationPointX = this.RightLeg.rotationPointX;
        this.extLegs[0].rotationPointY = this.RightLeg.rotationPointY;
        this.extLegs[0].rotationPointZ = this.RightLeg.rotationPointZ;
        this.extLegs[1].rotateAngleX = this.LeftLeg.rotateAngleX;
        this.extLegs[1].rotateAngleY = this.LeftLeg.rotateAngleY;
        this.extLegs[1].rotateAngleZ = this.LeftLeg.rotateAngleZ;
        this.extLegs[1].rotationPointX = this.LeftLeg.rotationPointX;
        this.extLegs[1].rotationPointY = this.LeftLeg.rotationPointY;
        this.extLegs[1].rotationPointZ = this.LeftLeg.rotationPointZ;
        this.extLegs[2].rotateAngleX = this.RightLegJoint.rotateAngleX;
        this.extLegs[2].rotateAngleY = this.RightLegJoint.rotateAngleY;
        this.extLegs[2].rotateAngleZ = this.RightLegJoint.rotateAngleZ;
        this.extLegs[2].rotationPointX = this.RightLegJoint.rotationPointX;
        this.extLegs[2].rotationPointY = this.RightLegJoint.rotationPointY;
        this.extLegs[2].rotationPointZ = this.RightLegJoint.rotationPointZ;
        this.extLegs[3].rotateAngleX = this.LeftLegJoint.rotateAngleX;
        this.extLegs[3].rotateAngleY = this.LeftLegJoint.rotateAngleY;
        this.extLegs[3].rotateAngleZ = this.LeftLegJoint.rotateAngleZ;
        this.extLegs[3].rotationPointX = this.LeftLegJoint.rotationPointX;
        this.extLegs[3].rotationPointY = this.LeftLegJoint.rotationPointY;
        this.extLegs[3].rotationPointZ = this.LeftLegJoint.rotationPointZ;
        for (int var2 = 0; var2 < 3; ++var2) {
            this.extSubLegs[var2 + var1].rotateAngleX = this.LeftSubLeg[var2].rotateAngleX;
            this.extSubLegs[var2 + var1].rotateAngleY = this.LeftSubLeg[var2].rotateAngleY;
            this.extSubLegs[var2 + var1].rotateAngleZ = this.LeftSubLeg[var2].rotateAngleZ;
            this.extSubLegs[var2 + var1].rotationPointX = this.LeftSubLeg[var2].rotationPointX;
            this.extSubLegs[var2 + var1].rotationPointY = this.LeftSubLeg[var2].rotationPointY;
            this.extSubLegs[var2 + var1].rotationPointZ = this.LeftSubLeg[var2].rotationPointZ;
            this.extSubLegs[var2 + 1 + var1].rotateAngleX = this.RightSubLeg[var2].rotateAngleX;
            this.extSubLegs[var2 + 1 + var1].rotateAngleY = this.RightSubLeg[var2].rotateAngleY;
            this.extSubLegs[var2 + 1 + var1].rotateAngleZ = this.RightSubLeg[var2].rotateAngleZ;
            this.extSubLegs[var2 + 1 + var1].rotationPointX = this.RightSubLeg[var2].rotationPointX;
            this.extSubLegs[var2 + 1 + var1].rotationPointY = this.RightSubLeg[var2].rotationPointY;
            this.extSubLegs[var2 + 1 + var1].rotationPointZ = this.RightSubLeg[var2].rotationPointZ;
            ++var1;
        }

    }

    @Override
    protected void rotateLegs(float move, float swing, float tick) {
        super.rotateLegs(move, swing, tick);
        this.syncLegs();
    }

    @Override
    protected void ponySit() {
        super.ponySit();
        this.syncLegs();
    }

    @Override
    protected void adjustLegs() {
        super.adjustLegs();
        this.syncLegs();
    }

    @Override
    protected void sneakLegs() {
        super.sneakLegs();
        this.syncLegs();
    }

    @Override
    protected void ponySleep() {
        super.ponySleep();
        this.syncLegs();
    }

    @Override
    protected void fixSpecialRotationPoints(float move) {
    }

    @Override
    public void specials(RenderManager renderman, EntityLivingBase entity) {
        if (!this.isSleeping) {
            this.renderDrop(renderman, entity, this.RightArm, 1.0f, -0.0625f, 0.8375f, 0.0625f);
        }
        this.renderPumpkin(renderman, entity, this.head, 0.725f, 0.0f, -0.25f, 0.0f);
    }
}

