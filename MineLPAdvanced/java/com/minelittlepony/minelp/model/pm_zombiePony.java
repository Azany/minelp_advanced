/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.util.MathHelper
 */
package com.minelittlepony.minelp.model;

import net.minecraft.util.MathHelper;

public class pm_zombiePony
        extends pm_newPonyAdv {
    public pm_zombiePony(String texture) {
        super(texture);
    }

    @Override
    protected void rotateLegs(float move, float swing, float tick) {
        float leftLegRotateAngleX;
        float rightLegRotateAngleX;
        float rightArmRotateAngleX;
        float leftArmRotateAngleX;
        if (!this.isFlying || !this.isPegasus) {
            float swag = (float) Math.pow(swing, 16.0);
            this.getClass();
            float raQuad = 3.1415927f * swag * 0.5f;
            this.getClass();
            float laQuad = 3.1415927f * swag;
            this.getClass();
            float rlQuad = 3.1415927f * swag * 0.2f;
            this.getClass();
            float llQuad = 3.1415927f * swag * -0.4f;
            rightArmRotateAngleX = MathHelper.cos((float) (move * 0.6662f + 3.1415927f + raQuad)) * 0.45f * swing;
            leftArmRotateAngleX = MathHelper.cos((float) (move * 0.6662f + laQuad)) * 0.45f * swing;
            rightLegRotateAngleX = MathHelper.cos((float) (move * 0.6662f + rlQuad)) * 0.45f * swing;
            leftLegRotateAngleX = MathHelper.cos((float) (move * 0.6662f + 3.1415927f + llQuad)) * 0.45f * swing;
            this.RightArm.rotateAngleY = 0.0f;
            this.SteveArm.rotateAngleY = 0.0f;
            this.unicornarm.rotateAngleY = 0.0f;
            this.LeftArm.rotateAngleY = 0.0f;
            this.RightLeg.rotateAngleY = 0.0f;
            this.LeftLeg.rotateAngleY = 0.0f;
        } else {
            if (this.rainboom) {
                rightArmRotateAngleX = this.ROTATE_270;
                leftArmRotateAngleX = this.ROTATE_270;
                rightLegRotateAngleX = this.ROTATE_90;
                leftLegRotateAngleX = this.ROTATE_90;
            } else {
                rightArmRotateAngleX = MathHelper.sin((float) (0.0f - swing * 0.5f));
                leftArmRotateAngleX = MathHelper.sin((float) (0.0f - swing * 0.5f));
                rightLegRotateAngleX = MathHelper.sin((float) (swing * 0.5f));
                leftLegRotateAngleX = MathHelper.sin((float) (swing * 0.5f));
            }
            this.RightArm.rotateAngleY = 0.2f;
            this.SteveArm.rotateAngleY = 0.2f;
            this.LeftArm.rotateAngleY = -0.2f;
            this.RightLeg.rotateAngleY = -0.2f;
            this.LeftLeg.rotateAngleY = 0.2f;
        }
        this.RightArm.rotateAngleX = rightArmRotateAngleX;
        this.SteveArm.rotateAngleX = rightArmRotateAngleX;
        this.unicornarm.rotateAngleX = 0.0f;
        this.LeftArm.rotateAngleX = leftArmRotateAngleX;
        this.RightLeg.rotateAngleX = rightLegRotateAngleX;
        this.LeftLeg.rotateAngleX = leftLegRotateAngleX;
        this.RightArm.rotateAngleZ = 0.0f;
        this.SteveArm.rotateAngleZ = 0.0f;
        this.unicornarm.rotateAngleZ = 0.0f;
        this.LeftArm.rotateAngleZ = 0.0f;
        if (this.m == 0) {
            float var8 = MathHelper.sin((float) (this.swingProgress * 3.1415927f));
            float var9 = MathHelper.sin((float) ((1.0f - (1.0f - this.swingProgress) * (1.0f - this.swingProgress)) * 3.1415927f));
            if (MathHelper.sin((float) (move / 20.0f)) < 0.0f) {
                this.RightArm.rotateAngleZ = 0.0f;
                this.RightArm.rotateAngleY = 0.1f - var8 * 0.6f;
                this.RightArm.rotateAngleX = -1.5707964f;
                this.RightArm.rotateAngleX -= var8 * 1.2f - var9 * 0.4f;
                this.RightArm.rotateAngleZ += MathHelper.cos((float) (tick * 0.09f)) * 0.05f + 0.05f;
                this.RightArm.rotateAngleX += MathHelper.sin((float) (tick * 0.067f)) * 0.1f;
            } else {
                this.LeftArm.rotateAngleZ = 0.0f;
                this.LeftArm.rotateAngleY = -0.1f - var8 * 0.6f;
                this.LeftArm.rotateAngleX = -1.5707964f;
                this.LeftArm.rotateAngleX -= var8 * 1.2f - var9 * 0.4f;
                this.LeftArm.rotateAngleZ += MathHelper.cos((float) (tick * 0.09f)) * 0.05f + 0.05f;
                this.LeftArm.rotateAngleX += MathHelper.sin((float) (tick * 0.067f)) * 0.1f;
            }
        }
    }

    @Override
    protected void fixSpecialRotationPoints(float move) {
        if (this.m == 0) {
            if (MathHelper.sin((float) (move / 20.0f)) < 0.0f) {
                this.shiftRotationPoint(this.RightArm, 0.5f, 1.5f, 3.0f);
            } else {
                this.shiftRotationPoint(this.LeftArm, -0.5f, 1.5f, 3.0f);
            }
        }
    }
}

