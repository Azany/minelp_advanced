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

import com.minelittlepony.minelp.PonyManager;
import com.minelittlepony.minelp.renderer.AniParams;
import com.minelittlepony.minelp.renderer.CompressiveRendering;
import com.minelittlepony.minelp.renderer.HornGlowRenderer;
import com.minelittlepony.minelp.renderer.PlaneRenderer;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import org.lwjgl.opengl.GL11;

import java.util.Random;

public class pm_newPonyAdv
        extends ModelPlayer {
    protected final float Pi = 3.1415927f;
    public int tailstop = 0;
    public ModelRenderer cloak;
    public ModelRenderer head;
    public ModelRenderer[] headpiece;
    public HornGlowRenderer[] hornglow;
    public ModelRenderer helmet;
    public ModelRenderer Body;
    public PlaneRenderer[] Bodypiece;
    public PlaneRenderer[] BodypieceNeck;
    public PlaneRenderer[] MuzzleFemale;
    public PlaneRenderer[] MuzzleMale;
    public ModelRenderer RightArm;
    public ModelRenderer LeftArm;
    public ModelRenderer RightLeg;
    public ModelRenderer LeftLeg;
    public ModelRenderer SteveArm;
    public ModelRenderer unicornarm;
    public PlaneRenderer[] Tail;
    public ModelRenderer[] LeftWing;
    public ModelRenderer[] RightWing;
    public ModelRenderer[] LeftWingExt;
    public ModelRenderer[] RightWingExt;
    public CompressiveRendering CompressiveLeftWing;
    public CompressiveRendering CompressiveRightWing;
    //===> GO!
    public ModelRenderer[] Mane;
    public PlaneRenderer[] RightExtMane;
    public PlaneRenderer[] LeftExtMane;
    public ModelRenderer[] Irokeeze;
    public PlaneRenderer[] LeftPlotPiece;
    public PlaneRenderer[] RightPlotPiece;
    public ModelRenderer RightShoulder;
    public ModelRenderer LeftShoulder;
    public ModelRenderer RightLegJoint;
    public ModelRenderer LeftLegJoint;
    public PlaneRenderer[] RightSubLeg;
    public PlaneRenderer[] LeftSubLeg;
    public PlaneRenderer[] RainbowTail;
    protected boolean rainboom;
    protected float NeckRotX = 0.166f;
    protected float HEAD_CENTRE_X = 0.0f;
    protected float HEAD_CENTRE_Y = -1.0f;
    protected float HEAD_CENTRE_Z = -2.0f;
    protected float BODY_CENTRE_X = 0.0f;
    protected float BODY_CENTRE_Y = 8.0f;
    protected float BODY_CENTRE_Z = 6.0f;
    protected float THIRDP_ARM_CENTRE_X = 0.0f;
    protected float THIRDP_ARM_CENTRE_Y = 10.0f;
    protected float THIRDP_ARM_CENTRE_Z = 0.0f;
    protected float FIRSTP_ARM_CENTRE_X = -1.0f;
    protected float FIRSTP_ARM_CENTRE_Y = 4.0f;
    protected float FIRSTP_ARM_CENTRE_Z = 0.0f;
    protected float HEAD_RP_X = 0.0f;
    protected float HEAD_RP_Y = 0.0f;
    protected float HEAD_RP_Z = 0.0f;
    protected float BODY_RP_Y_SNEAK = 7.0f;
    protected float BODY_RP_Y_NOTSNEAK = 0.0f;
    protected float BODY_RP_Z_SNEAK = -4.0f;
    protected float BODY_RP_Z_NOTSNEAK = 0.0f;
    protected float FRONT_LEG_RP_Y_SNEAK = 7.0f;
    protected float FRONT_LEG_RP_Y_NOTSNEAK = 8.0f;
    protected float WING_FOLDED_RP_Y = 13.0f;
    protected float WING_FOLDED_RP_Z = -3.0f;
    protected float LEFT_WING_RP_Y_SNEAK = 10.5f;
    protected float LEFT_WING_RP_Y_NOTSNEAK = 5.5f;
    protected float LEFT_WING_RP_Z_SNEAK = 2.0f;
    protected float LEFT_WING_RP_Z_NOTSNEAK = 3.0f;
    protected float RIGHT_WING_RP_Y_SNEAK = 11.5f;
    protected float RIGHT_WING_RP_Y_NOTSNEAK = 6.5f;
    protected float RIGHT_WING_RP_Z_SNEAK = 2.0f;
    protected float RIGHT_WING_RP_Z_NOTSNEAK = 3.0f;
    protected float TAIL_RP_X = 0.0f;
    protected float TAIL_RP_Y = 0.8f;
    protected float TAIL_RP_Z = 0.0f;
    protected float TAIL_RP_Z_SNEAK = 10.0f;
    protected float TAIL_RP_Z_NOTSNEAK = 14.0f;
    protected float LEFT_WING_EXT_RP_X = 4.5f;
    protected float LEFT_WING_EXT_RP_Y = 5.0f;
    protected float LEFT_WING_EXT_RP_Z = 6.0f;
    protected float RIGHT_WING_EXT_RP_X = -4.5f;
    protected float RIGHT_WING_EXT_RP_Y = 5.0f;
    protected float RIGHT_WING_EXT_RP_Z = 6.0f;
    protected float BODY_ROTATE_ANGLE_X_SNEAK = 0.4f;
    protected float BODY_ROTATE_ANGLE_X_NOTSNEAK = 0.0f;
    protected float EXT_WING_ROTATE_ANGLE_X = 2.5f;
    protected float LEFT_WING_ROTATE_ANGLE_Z_SNEAK = -6.0f;
    protected float RIGHT_WING_ROTATE_ANGLE_Z_SNEAK = 6.0f;
    protected float SNEAK_LEG_X_ROTATION_ADJUSTMENT = 0.4f;
    protected float ROTATE_270 = 4.712f;
    protected float ROTATE_90 = 1.571f;
    protected float RIDING_SHIFT_Y = -10.0f;
    protected float RIDING_SHIFT_Z = -10.0f;
    protected float PreviousMove = 0.0f;
    protected float RainboomLength = 0.0f;
    protected float AnimSecs = 0.0f;
    private float WingRotateAngleZ;

    //<==== STOP!


    public pm_newPonyAdv(String texture) {
        super(texture);
    }

    @Override
    public void init() {
        this.init(0.0f);
    }

    @Override
    public void init(float yOffset) {
        this.init(yOffset, 0.0f);
    }

    @Override
    public void init(float yOffset, float stretch) {
        this.initTextures();
        this.initPositions(yOffset, stretch);
    }

    @Override
    public void animate(AniParams aniparams, Entity entityplayer) {
        this.animate(aniparams);
    }

    @Override
    public void animate(AniParams aniparams) {
        int k;
        this.checkRainboom(aniparams.swing);
        this.rotateHead(aniparams.horz, aniparams.vert);
        this.swingTailZ(aniparams.move, aniparams.swing);
        float bodySwingRotation = 0.0f;
        if (!(this.swingProgress <= -9990.0f || this.isUnicorn && this.glowColor != 0)) {
            bodySwingRotation = MathHelper.sin(MathHelper.sqrt_float(this.swingProgress) * Pi * 2.0f) * 0.2f;
        }
        this.Body.rotateAngleY = bodySwingRotation * 0.2f;
        for (k = 0; k < this.Bodypiece.length; ++k) {
            this.Bodypiece[k].rotateAngleY = bodySwingRotation * 0.2f;
        }
        for (int kk = 0; kk < this.BodypieceNeck.length; ++kk) {
            this.BodypieceNeck[kk].rotateAngleY = bodySwingRotation * 0.2f;
        }
        for (int var4 = 0; var4 < this.LeftPlotPiece.length; ++var4) {
            this.LeftPlotPiece[var4].rotateAngleY = bodySwingRotation * 0.2f;
            this.RightPlotPiece[var4].rotateAngleY = bodySwingRotation * 0.2f;
        }
        for (int l = 0; l < this.LeftWing.length; ++l) {
            this.LeftWing[l].rotateAngleY = bodySwingRotation * 0.2f;
        }
        for (int i1 = 0; i1 < this.RightWing.length; ++i1) {
            this.RightWing[i1].rotateAngleY = bodySwingRotation * 0.2f;
        }
        this.tailstop = 0;
        this.tailstop = this.Tail.length - this.wantTail * 5;
        if (this.tailstop <= 1) {
            this.tailstop = 0;
        }
        for (int j1 = 0; j1 < this.tailstop; ++j1) {
            this.Tail[j1].rotateAngleY = bodySwingRotation;
        }
        this.setLegs(aniparams.move, aniparams.swing, aniparams.tick);
        this.holdItem();
        this.swingItem(this.swingProgress);
        if (this.issneak && !this.isFlying && !this.isRiding) {
            this.adjustBody(this.BODY_ROTATE_ANGLE_X_SNEAK, this.BODY_RP_Y_SNEAK, this.BODY_RP_Z_SNEAK);
            this.adjustNeck(0.0f, 5.0f, -1.0f);
            this.rotatePlot(aniparams.move, aniparams.swing, this.BODY_RP_Y_SNEAK, this.BODY_RP_Z_SNEAK);
            this.animatePegasusWingsSneaking();
            this.sneakLegs();
            this.setHead(0.0f, 6.0f, -2.0f);
            this.sneakTail();
        } else {
            this.adjustBody(this.BODY_ROTATE_ANGLE_X_NOTSNEAK, this.BODY_RP_Y_NOTSNEAK, this.BODY_RP_Z_NOTSNEAK);
            this.adjustNeck(0.0f, 0.0f, 0.4f);
            this.rotatePlot(aniparams.move, aniparams.swing, 0.0f, 0.0f);
            if (this.isPegasus) {
                this.animatePegasusWingsNotSneaking(aniparams.tick);
                if (!this.isFlying) {
                    this.adjustPegasusWings(this.ROTATE_90, 13.5f, -2.0f);
                } else {
                    this.adjustPegasusWings(this.ROTATE_90, 7.25f, 4.5f);
                }
            }
            this.RightLeg.rotationPointY = this.FRONT_LEG_RP_Y_NOTSNEAK;
            this.LeftLeg.rotationPointY = this.FRONT_LEG_RP_Y_NOTSNEAK;
            this.RightLegJoint.rotationPointY = this.FRONT_LEG_RP_Y_NOTSNEAK;
            this.LeftLegJoint.rotationPointY = this.FRONT_LEG_RP_Y_NOTSNEAK;
            for (int var4 = 0; var4 < this.RightSubLeg.length; ++var4) {
                this.RightSubLeg[var4].rotationPointY = this.FRONT_LEG_RP_Y_NOTSNEAK;
                this.LeftSubLeg[var4].rotationPointY = this.FRONT_LEG_RP_Y_NOTSNEAK;
            }
            this.swingArms(aniparams.tick);
            this.setHead(0.0f, 0.0f, 0.0f);
            this.tailstop = 0;
            this.tailstop = this.Tail.length - this.wantTail * 5;
            if (this.tailstop <= 1) {
                this.tailstop = 0;
            }
            for (int k6 = 0; k6 < this.tailstop; ++k6) {
                this.setRotationPoint(this.Tail[k6], this.TAIL_RP_X, this.TAIL_RP_Y, this.TAIL_RP_Z_NOTSNEAK);
                this.Tail[k6].rotateAngleX = this.rainboom ? this.ROTATE_90 + 0.1f * MathHelper.sin(aniparams.move) : 0.5f * aniparams.swing;
            }
            if (!this.rainboom) {
                this.swingTailX(aniparams.tick);
                this.swingExtManeX(aniparams.tick);
                this.swingExtManeY(aniparams.tick);
            }
        }
        if (this.rainboom && !this.isRiding) {
            this.tailstop = 0;
            this.tailstop = this.Tail.length - this.wantTail * 5;
            if (this.tailstop <= 1) {
                this.tailstop = 0;
            }
            for (int k1 = 0; k1 < this.tailstop; ++k1) {
                this.Tail[k1].rotationPointY += 6.0f;
                this.Tail[k1].rotationPointZ += 1.0f;
            }
        }
        this.fixSpecialRotations();
        if (this.rainboom && !this.isRiding && this.isRainboomTail) {
            this.swingRainbowTailY(aniparams.tick);
        }
        if (this.isRiding) {
            this.issneak = false;
            this.isFlying = false;
            this.isSleeping = false;
            this.ponySit();
        } else {
            this.PreviousMove = aniparams.move;
        }
        if (this.isSleeping) {
            this.issneak = false;
            this.isFlying = false;
            this.isRiding = false;
            this.ponySleep();
        } else {
            this.PreviousMove = aniparams.move;
        }
        if (this.o) {
            this.aimBow(aniparams.tick);
        }
        //this.fixSpecialRotations();
        this.fixSpecialRotationPoints(aniparams.move);
    }

    protected void checkRainboom(float swing) {
        this.rainboom = this.isPegasus && this.isFlying && swing >= 0.9999f;
    }

    protected void setHead(float posX, float posY, float posZ) {
        int j6;
        int i;
        this.setRotationPoint(this.head, posX, posY, posZ);
        this.setRotationPoint(this.helmet, posX, posY, posZ);
        for (i = 0; i < this.headpiece.length; ++i) {
            this.setRotationPoint(this.headpiece[i], posX, posY, posZ);
        }
        for (i = 0; i < this.hornglow.length; ++i) {
            this.setRotationPoint(this.hornglow[i], posX, posY, posZ);
        }
        for (int var5 = 0; var5 < this.Mane.length; ++var5) {
            this.setRotationPoint(this.Mane[var5], posX, posY, posZ);
        }
        for (int var5 = 0; var5 < this.Irokeeze.length; ++var5) {
            this.setRotationPoint(this.Irokeeze[var5], posX, posY, posZ);
        }
        for (int var5 = 0; var5 < this.RightExtMane.length; ++var5) {
            this.setRotationPoint(this.RightExtMane[var5], posX, posY, posZ);
        }
        for (int var5 = 0; var5 < this.LeftExtMane.length; ++var5) {
            this.setRotationPoint(this.LeftExtMane[var5], posX, posY, posZ);
        }
        if (this.isMale) {
            for (j6 = 0; j6 < this.MuzzleMale.length; ++j6) {
                this.setRotationPoint(this.MuzzleMale[j6], posX, posY, posZ);
            }
        } else {
            for (j6 = 0; j6 < this.MuzzleFemale.length; ++j6) {
                this.setRotationPoint(this.MuzzleFemale[j6], posX, posY, posZ);
            }
        }
    }

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
        if (this.isMale) {
            for (int i = 0; i < this.MuzzleMale.length; ++i) {
                this.MuzzleMale[i].rotateAngleY = headRotateAngleY;
                this.MuzzleMale[i].rotateAngleX = headRotateAngleX;
            }
        } else {
            for (int i = 0; i < this.MuzzleFemale.length; ++i) {
                this.MuzzleFemale[i].rotateAngleY = headRotateAngleY;
                this.MuzzleFemale[i].rotateAngleX = headRotateAngleX;
            }
        }
        this.headpiece[0].rotateAngleY = headRotateAngleY;
        this.headpiece[0].rotateAngleX = headRotateAngleX;
        this.headpiece[1].rotateAngleY = headRotateAngleY;
        this.headpiece[1].rotateAngleX = headRotateAngleX;
        this.headpiece[2].rotateAngleY = headRotateAngleY;
        this.headpiece[2].rotateAngleX = headRotateAngleX;
        this.hornglow[0].rotateAngleY = headRotateAngleY;
        this.hornglow[0].rotateAngleX = headRotateAngleX;
        this.hornglow[1].rotateAngleY = headRotateAngleY;
        this.hornglow[1].rotateAngleX = headRotateAngleX;

        for (int var6 = 0; var6 < this.Mane.length; ++var6) {
            this.Mane[var6].rotateAngleX = headRotateAngleX;
            this.Mane[var6].rotateAngleY = headRotateAngleY;
        }
        for (int var6 = 0; var6 < this.Irokeeze.length; ++var6) {
            this.Irokeeze[var6].rotateAngleX = headRotateAngleX;
            this.Irokeeze[var6].rotateAngleY = headRotateAngleY;
        }
        for (int var6 = 0; var6 < this.RightExtMane.length; ++var6) {
            this.RightExtMane[var6].rotateAngleX = headRotateAngleX;
            this.RightExtMane[var6].rotateAngleY = headRotateAngleY;
        }
        for (int var6 = 0; var6 < this.LeftExtMane.length; ++var6) {
            this.LeftExtMane[var6].rotateAngleX = headRotateAngleX;
            this.LeftExtMane[var6].rotateAngleY = headRotateAngleY;
        }

        this.helmet.rotateAngleY = headRotateAngleY;
        this.helmet.rotateAngleX = headRotateAngleX;
        this.headpiece[2].rotateAngleX = headRotateAngleX + 0.5f;
        this.hornglow[0].rotateAngleX = headRotateAngleX + 0.5f;
        this.hornglow[1].rotateAngleX = headRotateAngleX + 0.5f;
        this.Irokeeze[0].rotateAngleX = headRotateAngleX - 1.469856f;
        this.Irokeeze[1].rotateAngleX = headRotateAngleX - 1.719511f;
        this.Irokeeze[2].rotateAngleX = headRotateAngleX + 3.067235f;
    }

    protected void setLegs(float move, float swing, float tick) {
        this.rotateLegs(move, swing, tick);
        this.adjustLegs();
    }

    protected void rotatePlot(float var1, float var2, float var3, float var4) {
        float var6;
        float var5;
        float var9 = (float) Math.pow(var2, 16.0);
        this.getClass();
        float var10 = Pi * var9 * 0.2f;
        this.getClass();
        float var11 = Pi * var9 * -0.4f;
        float var7 = this.issneak && !this.isFlying ? this.BODY_ROTATE_ANGLE_X_SNEAK : 0.0f;
        if (this.isFlying && this.isPegasus) {
            if (this.rainboom) {
                var5 = 0.2f;
                var6 = 0.2f;
                var3 += 2.5f;
                var4 -= 2.0f;
            } else {
                var5 = MathHelper.sin(var2 * 0.05f);
                var6 = MathHelper.sin(var2 * 0.05f);
                if (var3 < var3 + 2.5f) {
                    var3 += var2 * 0.5f;
                }
                if (var4 > var4 - 2.0f) {
                    var4 -= var2 * 0.5f;
                }
            }
        } else {
            float var8 = var9 > 0.5f && var9 < 2.0f ? 0.05f : 0.03f;
            var5 = MathHelper.cos(var1 * 0.6662f + var10) * var8 * var2;
            var6 = MathHelper.cos(var1 * 0.6662f + Pi + var11) * var8 * var2;
        }
        for (int var12 = 0; var12 < this.LeftPlotPiece.length; ++var12) {
            this.LeftPlotPiece[var12].rotateAngleX = var6 + var7;
            this.LeftPlotPiece[var12].rotationPointY = var3;
            this.LeftPlotPiece[var12].rotationPointZ = var4;
            this.RightPlotPiece[var12].rotateAngleX = var5 + var7;
            this.RightPlotPiece[var12].rotationPointY = var3;
            this.RightPlotPiece[var12].rotationPointZ = var4;
        }
    }


    protected void rotateLegs(float move, float swing, float tick) {
        float leftLegRotateAngleX;
        float rightLegRotateAngleX;
        float rightArmRotateAngleX;
        float leftArmRotateAngleX;
        if (this.isFlying && this.isPegasus) {
            if (this.rainboom) {
                rightArmRotateAngleX = this.ROTATE_270;
                leftArmRotateAngleX = this.ROTATE_270;
                rightLegRotateAngleX = this.ROTATE_90;
                leftLegRotateAngleX = this.ROTATE_90;
            } else {
                rightArmRotateAngleX = MathHelper.sin(0.0f - swing * 0.5f);
                leftArmRotateAngleX = MathHelper.sin(0.0f - swing * 0.5f);
                rightLegRotateAngleX = MathHelper.sin(swing * 0.5f);
                leftLegRotateAngleX = MathHelper.sin(swing * 0.5f);
            }
            this.RightArm.rotateAngleY = 0.2f;
            this.SteveArm.rotateAngleY = 0.2f;
            this.LeftArm.rotateAngleY = -0.2f;
            this.RightLeg.rotateAngleY = -0.2f;
            this.LeftLeg.rotateAngleY = 0.2f;
            this.RightShoulder.rotateAngleY = -0.2f;
            this.LeftShoulder.rotateAngleY = 0.2f;
            this.RightLegJoint.rotateAngleY = -0.2f;
            this.LeftLegJoint.rotateAngleY = 0.2f;
            for (int var13 = 0; var13 < this.RightSubLeg.length; ++var13) {
                this.RightSubLeg[var13].rotateAngleY = -0.2f;
                this.LeftSubLeg[var13].rotateAngleY = 0.2f;
            }
        } else {
            float swag = (float) Math.pow(swing, 16.0);
            this.getClass();
            float raQuad = Pi * swag * 0.5f;
            this.getClass();
            float laQuad = Pi * swag;
            this.getClass();
            float rlQuad = Pi * swag * 0.2f;
            this.getClass();
            float llQuad = Pi * swag * -0.4f;
            rightArmRotateAngleX = MathHelper.cos(move * 0.6662f + Pi + raQuad) * 0.45f * swing;
            leftArmRotateAngleX = MathHelper.cos(move * 0.6662f + laQuad) * 0.45f * swing;
            rightLegRotateAngleX = MathHelper.cos(move * 0.6662f + rlQuad) * 0.45f * swing;
            leftLegRotateAngleX = MathHelper.cos(move * 0.6662f + Pi + llQuad) * 0.45f * swing;
            this.RightArm.rotateAngleY = 0.0f;
            this.SteveArm.rotateAngleY = 0.0f;
            this.unicornarm.rotateAngleY = 0.0f;
            this.LeftArm.rotateAngleY = 0.0f;
            this.RightLeg.rotateAngleY = 0.0f;
            this.LeftLeg.rotateAngleY = 0.0f;

            this.RightShoulder.rotateAngleY = 0.0f;
            this.LeftShoulder.rotateAngleY = 0.0f;
            this.RightLegJoint.rotateAngleY = 0.0f;
            this.LeftLegJoint.rotateAngleY = 0.0f;
            for (int var12 = 0; var12 < this.RightSubLeg.length; ++var12) {
                this.RightSubLeg[var12].rotateAngleY = 0.0f;
                this.LeftSubLeg[var12].rotateAngleY = 0.0f;
            }
        }
        this.RightArm.rotateAngleX = rightArmRotateAngleX;
        this.RightShoulder.rotateAngleX = rightArmRotateAngleX;
        this.SteveArm.rotateAngleX = rightArmRotateAngleX;
        this.unicornarm.rotateAngleX = 0.0f;
        this.LeftArm.rotateAngleX = leftArmRotateAngleX;
        this.LeftShoulder.rotateAngleX = leftArmRotateAngleX;
        this.RightLeg.rotateAngleX = rightLegRotateAngleX;
        this.RightLegJoint.rotateAngleX = rightLegRotateAngleX;
        this.LeftLeg.rotateAngleX = leftLegRotateAngleX;
        this.LeftLegJoint.rotateAngleX = leftLegRotateAngleX;
        for (int var13 = 0; var13 < this.RightSubLeg.length; ++var13) {
            this.RightSubLeg[var13].rotateAngleX = rightLegRotateAngleX;
            this.LeftSubLeg[var13].rotateAngleX = leftLegRotateAngleX;
        }
        this.RightArm.rotateAngleZ = 0.0f;
        this.SteveArm.rotateAngleZ = 0.0f;
        this.unicornarm.rotateAngleZ = 0.0f;
        this.LeftArm.rotateAngleZ = 0.0f;
        this.RightShoulder.rotateAngleZ = 0.0f;
        this.LeftShoulder.rotateAngleZ = 0.0f;
        this.RightLegJoint.rotateAngleZ = 0.0f;
        this.LeftLegJoint.rotateAngleZ = 0.0f;
        for (int var13 = 0; var13 < this.RightSubLeg.length; ++var13) {
            this.RightSubLeg[var13].rotateAngleZ = 0.0f;
            this.LeftSubLeg[var13].rotateAngleZ = 0.0f;
        }
    }

    protected void adjustLegs() {
        float sinBodyRotateAngleYFactor = MathHelper.sin(this.Body.rotateAngleY) * 5.0f;
        float cosBodyRotateAngleYFactor = MathHelper.cos(this.Body.rotateAngleY) * 5.0f;
        float legOutset = 4.0f;
        float var4;

        if (this.issneak && !this.isFlying && !this.isRiding) {
            legOutset = 0.0f;
        }
        if (this.isSleeping) {
            legOutset = 2.6f;
        }
        if (this.rainboom) {
            this.RightArm.rotationPointZ = sinBodyRotateAngleYFactor + 2.0f;
            this.SteveArm.rotationPointZ = sinBodyRotateAngleYFactor + 2.0f;
            this.LeftArm.rotationPointZ = 0.0f - sinBodyRotateAngleYFactor + 2.0f;
            var4 = -0.5f;
        } else {
            this.RightArm.rotationPointZ = sinBodyRotateAngleYFactor + 1.0f;
            this.SteveArm.rotationPointZ = sinBodyRotateAngleYFactor + 1.0f;
            this.LeftArm.rotationPointZ = 0.0f - sinBodyRotateAngleYFactor + 1.0f;
            var4 = 0.0f;
        }
        this.RightArm.rotationPointX = 0.0f - cosBodyRotateAngleYFactor - 1.0f + legOutset;
        this.SteveArm.rotationPointX = 0.0f - cosBodyRotateAngleYFactor;
        this.LeftArm.rotationPointX = cosBodyRotateAngleYFactor + 1.0f - legOutset;
        this.RightLegJoint.rotationPointX = this.RightLeg.rotationPointX = 0.0f - cosBodyRotateAngleYFactor - 1.0f + legOutset;
        this.LeftLegJoint.rotationPointX = this.LeftLeg.rotationPointX = cosBodyRotateAngleYFactor + 1.0f - legOutset;
        for (int var5 = 0; var5 < this.RightSubLeg.length; ++var5) {
            this.RightSubLeg[var5].rotationPointX = this.RightLeg.rotationPointX;
            this.LeftSubLeg[var5].rotationPointX = this.LeftLeg.rotationPointX;
        }
        this.RightArm.rotateAngleY += this.Body.rotateAngleY;
        this.RightShoulder.rotateAngleY += this.Body.rotateAngleY;
        this.LeftArm.rotateAngleY += this.Body.rotateAngleY;
        this.LeftShoulder.rotateAngleY += this.Body.rotateAngleY;
        this.LeftArm.rotateAngleX += this.Body.rotateAngleY;
        this.RightArm.rotationPointY = 8.0f;
        this.RightShoulder.rotationPointY = 8.0f;
        this.LeftArm.rotationPointY = 8.0f;
        this.LeftShoulder.rotationPointY = 8.0f;
        this.RightLegJoint.rotationPointZ = this.RightLeg.rotationPointZ = 10.0f + var4;
        this.LeftLegJoint.rotationPointZ = this.LeftLeg.rotationPointZ = 10.0f + var4;
        for (int var5 = 0; var5 < this.RightSubLeg.length; ++var5) {
            this.RightSubLeg[var5].rotationPointZ = this.RightLeg.rotationPointZ;
            this.LeftSubLeg[var5].rotationPointZ = this.LeftLeg.rotationPointZ;
        }
    }

    protected void swingTailZ(float move, float swing) {
        this.tailstop = 0;
        this.tailstop = this.Tail.length - this.wantTail * 5;
        if (this.tailstop <= 1) {
            this.tailstop = 0;
        }
        for (int j = 0; j < this.tailstop; ++j) {
            this.Tail[j].rotateAngleZ = this.rainboom ? 0.0f : MathHelper.cos(move * 0.8f) * 0.2f * swing;
        }
    }

    protected void swingTailX(float tick) {
        float sinTickFactor = MathHelper.sin(tick * 0.067f) * 0.05f;
        this.tailstop = 0;
        this.tailstop = this.Tail.length - this.wantTail * 5;
        if (this.tailstop <= 1) {
            this.tailstop = 0;
        }
        for (int l6 = 0; l6 < this.tailstop; ++l6) {
            this.Tail[l6].rotateAngleX += sinTickFactor;
        }
    }

    protected void swingExtManeX(float var1) {
        float var2 = MathHelper.sin(var1 * 0.033f) * 0.02f;
        switch (this.StylesofMane) {
            case 1: {
                for (ModelRenderer aIrokeeze : this.Irokeeze) {
                    aIrokeeze.rotateAngleX += var2;
                }
                return;
            }
            case 2: {
                for (int var3 = 0; var3 < this.LeftExtMane.length; ++var3) {
                    this.LeftExtMane[var3].rotateAngleX += var2 * ((float) var3 / 5.0f);
                }
                return;
            }
            case 3: {
                for (int var3 = 0; var3 < this.RightExtMane.length; ++var3) {
                    this.RightExtMane[var3].rotateAngleX += var2 * ((float) var3 / 5.0f);
                }
                return;
            }
            case 4: {
                for (int var3 = 0; var3 < this.RightExtMane.length; ++var3) {
                    this.RightExtMane[var3].rotateAngleX += var2 * ((float) var3 / 5.0f);
                    this.LeftExtMane[var3].rotateAngleX += var2 * ((float) var3 / 5.0f);
                }
                break;
            }
        }
    }

    protected void swingExtManeY(float var1) {
        float var2 = MathHelper.sin(var1 * 0.033f) * 0.02f;
        switch (this.StylesofMane) {
            case 1: {
                for (ModelRenderer aIrokeeze : this.Irokeeze) {
                    aIrokeeze.rotateAngleY += var2;
                }
                return;
            }
            case 2: {
                for (PlaneRenderer aLeftExtMane : this.LeftExtMane) {
                    aLeftExtMane.rotateAngleY += var2;
                }
                return;
            }
            case 3: {
                for (PlaneRenderer aRightExtMane : this.RightExtMane) {
                    aRightExtMane.rotateAngleY += var2;
                }
                return;
            }
            case 4: {
                for (int var3 = 0; var3 < this.RightExtMane.length; ++var3) {
                    this.RightExtMane[var3].rotateAngleY += var2;
                    this.LeftExtMane[var3].rotateAngleY += var2;
                }
                break;
            }
        }
    }

    protected void swingRainbowTailY(float var1) {
        int var3;
        float[] var2 = new float[10];
        for (var3 = 0; var3 < 10; ++var3) {
            var2[var3] = MathHelper.sin(var1 + (float) var3);
        }
        for (var3 = 0; var3 < 10; ++var3) {
            this.RainbowTail[var3].rotationPointY = var2[var3] + (float) var3 * 0.1f;
            this.RainbowTail[var3 + 10].rotationPointY = var2[var3] + (float) var3 * 0.1f;
            this.RainbowTail[var3 + 20].rotationPointY = var2[var3] + (float) var3 * 0.1f;
            this.RainbowTail[var3 + 30].rotationPointY = var2[var3] + (float) var3 * 0.1f;
            this.RainbowTail[var3 + 40].rotationPointY = var2[var3] + (float) var3 * 0.1f;
            this.RainbowTail[var3 + 50].rotationPointY = var2[var3] + (float) var3 * 0.1f;
            this.RainbowTail[var3 + 60].rotationPointY = var2[var3] + (float) var3 * 0.1f;
        }
    }


    protected void holdItem() {
        if (!(this.m == 0 || this.rainboom || this.isUnicorn && this.glowColor != 0)) {
            this.RightArm.rotateAngleX = this.RightArm.rotateAngleX * 0.5f - 0.3141593f;
            this.SteveArm.rotateAngleX = this.SteveArm.rotateAngleX * 0.5f - 0.3141593f;
        }
    }

    protected void swingItem(float swingProgress) {
        if (swingProgress > -9990.0f && !this.isSleeping) {

            float f16 = 1.0f - swingProgress;
            f16 *= f16 * f16;
            f16 = 1.0f - f16;
            float f22 = MathHelper.sin(f16 * Pi);
            float f28 = MathHelper.sin(swingProgress * Pi);
            float f33 = f28 * (-this.head.rotateAngleX - 0.7f) * 0.75f;
            if (this.isUnicorn && this.glowColor != 0 && this.m != 0) {
                this.unicornarm.rotateAngleX = (float) ((double) this.unicornarm.rotateAngleX - ((double) f22 * 1.2 + (double) f33));
                this.unicornarm.rotateAngleY += this.Body.rotateAngleY * 2.0f;
                this.unicornarm.rotateAngleZ = f28 * -0.4f;
            } else {
                this.RightArm.rotateAngleX = (float) ((double) this.RightArm.rotateAngleX - ((double) f22 * 1.2 + (double) f33));
                this.RightArm.rotateAngleY += this.Body.rotateAngleY * 2.0f;
                this.RightArm.rotateAngleZ = f28 * -0.4f;
                this.SteveArm.rotateAngleX = (float) ((double) this.SteveArm.rotateAngleX - ((double) f22 * 1.2 + (double) f33));
                this.SteveArm.rotateAngleY += this.Body.rotateAngleY * 2.0f;
                this.SteveArm.rotateAngleZ = f28 * -0.4f;
            }
        }
    }

    protected void swingArms(float tick) {
        if (this.m != 0 && !this.isSleeping) {
            float cosTickFactor = MathHelper.cos(tick * 0.09f) * 0.05f + 0.05f;
            float sinTickFactor = MathHelper.sin(tick * 0.067f) * 0.05f;
            if (!this.isUnicorn || this.glowColor == 0) {
                this.RightArm.rotateAngleZ += cosTickFactor;
                this.RightArm.rotateAngleX += sinTickFactor;
                this.SteveArm.rotateAngleZ += cosTickFactor;
                this.SteveArm.rotateAngleX += sinTickFactor;
            } else {
                this.unicornarm.rotateAngleZ += cosTickFactor;
                this.unicornarm.rotateAngleX += sinTickFactor;
            }
        }
    }

    protected void adjustBody(float rotateAngleX, float rotationPointY, float rotationPointZ) {
        this.adjustBodyComponents(rotateAngleX, rotationPointY, rotationPointZ);
        this.adjustNeck(rotateAngleX, rotationPointY, rotationPointZ);
    }

    protected void adjustPlotComponents(float var1, float var2, float var3) {
        for (int var4 = 0; var4 < this.LeftPlotPiece.length; ++var4) {
            this.LeftPlotPiece[var4].rotateAngleX = var1;
            this.LeftPlotPiece[var4].rotationPointY = var2;
            this.LeftPlotPiece[var4].rotationPointZ = var3;
            this.RightPlotPiece[var4].rotateAngleX = var1;
            this.RightPlotPiece[var4].rotationPointY = var2;
            this.RightPlotPiece[var4].rotationPointZ = var3;
        }
    }

    protected void adjustBodyComponents(float rotateAngleX, float rotationPointY, float rotationPointZ) {
        int k3;
        this.Body.rotateAngleX = rotateAngleX;
        this.Body.rotationPointY = rotationPointY;
        this.Body.rotationPointZ = rotationPointZ;
        for (k3 = 0; k3 < this.Bodypiece.length; ++k3) {
            this.Bodypiece[k3].rotateAngleX = rotateAngleX;
            this.Bodypiece[k3].rotationPointY = rotationPointY;
            this.Bodypiece[k3].rotationPointZ = rotationPointZ;
        }
    }

    protected void adjustNeck(float rotateAngleX, float rotationPointY, float rotationPointZ) {
        for (PlaneRenderer aBodypieceNeck : this.BodypieceNeck) {
            aBodypieceNeck.rotateAngleX = this.NeckRotX + rotateAngleX;
            aBodypieceNeck.rotationPointY = rotationPointY;
            aBodypieceNeck.rotationPointZ = rotationPointZ;
        }
    }

    protected void sneakLegs() {
        this.RightArm.rotateAngleX -= this.SNEAK_LEG_X_ROTATION_ADJUSTMENT;
        this.SteveArm.rotateAngleX += this.SNEAK_LEG_X_ROTATION_ADJUSTMENT;
        this.unicornarm.rotateAngleX += this.SNEAK_LEG_X_ROTATION_ADJUSTMENT;
        this.LeftArm.rotateAngleX -= this.SNEAK_LEG_X_ROTATION_ADJUSTMENT;
        this.RightLeg.rotationPointY = this.FRONT_LEG_RP_Y_SNEAK;
        this.LeftLeg.rotationPointY = this.FRONT_LEG_RP_Y_SNEAK;
        this.RightLegJoint.rotationPointY = this.FRONT_LEG_RP_Y_SNEAK;
        this.LeftLegJoint.rotationPointY = this.FRONT_LEG_RP_Y_SNEAK;
        for (int var1 = 0; var1 < this.RightSubLeg.length; ++var1) {
            this.RightSubLeg[var1].rotationPointY = this.FRONT_LEG_RP_Y_SNEAK;
            this.LeftSubLeg[var1].rotationPointY = this.FRONT_LEG_RP_Y_SNEAK;
        }
    }

    protected void sneakTail() {
        this.tailstop = 0;
        this.tailstop = this.Tail.length - this.wantTail * 5;
        if (this.tailstop <= 1) {
            this.tailstop = 0;
        }
        for (int i7 = 0; i7 < this.tailstop; ++i7) {
            this.setRotationPoint(this.Tail[i7], this.TAIL_RP_X, this.TAIL_RP_Y, this.TAIL_RP_Z_SNEAK);
            this.Tail[i7].rotateAngleX = 0.0f;
        }
    }

    protected void ponySleep() {
        float headPosX;
        float headPosY;
        float headPosZ;
        this.RightArm.rotateAngleX = this.ROTATE_270;
        this.RightShoulder.rotateAngleX = this.ROTATE_270;
        this.LeftArm.rotateAngleX = this.ROTATE_270;
        this.LeftShoulder.rotateAngleX = this.ROTATE_270;
        this.RightLeg.rotateAngleX = this.ROTATE_90;
        this.LeftLeg.rotateAngleX = this.ROTATE_90;
        this.RightLegJoint.rotateAngleX = this.ROTATE_90;
        this.LeftLegJoint.rotateAngleX = this.ROTATE_90;
        for (int var1 = 0; var1 < this.RightSubLeg.length; ++var1) {
            this.RightSubLeg[var1].rotateAngleX = this.ROTATE_90;
            this.LeftSubLeg[var1].rotateAngleX = this.ROTATE_90;
        }
        if (this.issneak) {
            headPosY = 2.0f;
            headPosZ = -1.0f;
            headPosX = 1.0f;
        } else {
            headPosY = 2.0f;
            headPosZ = 1.0f;
            headPosX = 1.0f;
        }
        this.setHead(headPosX, headPosY, headPosZ);
        this.shiftRotationPoint(this.RightArm, 0.0f, 2.0f, 6.0f);
        this.shiftRotationPoint(this.RightShoulder, 0.0f, 2.0f, 6.0f);
        this.shiftRotationPoint(this.LeftArm, 0.0f, 2.0f, 6.0f);
        this.shiftRotationPoint(this.LeftShoulder, 0.0f, 2.0f, 6.0f);
        this.shiftRotationPoint(this.RightLeg, 0.0f, 2.0f, -8.0f);
        this.shiftRotationPoint(this.RightLegJoint, 0.0f, 2.0f, -8.0f);
        this.shiftRotationPoint(this.LeftLeg, 0.0f, 2.0f, -8.0f);
        this.shiftRotationPoint(this.LeftLegJoint, 0.0f, 2.0f, -8.0f);
        for (int var4 = 0; var4 < this.RightSubLeg.length; ++var4) {
            this.shiftRotationPoint(this.RightSubLeg[var4], 0.0f, 2.0f, -8.0f);
            this.shiftRotationPoint(this.LeftSubLeg[var4], 0.0f, 2.0f, -8.0f);
        }
    }

    protected void ponySit() {
        int var3;
        float var2 = 0.2f;
        if (this.size == 0) {
            var2 = 0.5f;
        }
        if (this.size == 1 && !this.isMale) {
            var2 = 0.2f;
        }
        if (this.size == 2) {
            var2 = 0.2f;
        }
        if (this.size == 3) {
            var2 = 0.2f;
        }
        this.setHead(0.0f, 3.5f + var2, 1.2f);
        this.adjustBody(-0.4f, 4.0f + var2, 3.0f);
        if (!this.isArmour) {
            this.adjustNeck(0.0f, 2.5f + var2, 1.2f);
        }
        if (!this.isArmour) {
            this.adjustPlotComponents(0.0f, 8.0f + var2, 0.0f);
        }
        if (!this.isArmour) {
            this.adjustPegasusWings(1.1f, 15.25f + var2, -4.5f);
        }
        this.RightArm.rotateAngleX -= this.SNEAK_LEG_X_ROTATION_ADJUSTMENT;
        this.RightArm.rotationPointY += 2.0f + var2;
        this.RightShoulder.rotateAngleX -= this.SNEAK_LEG_X_ROTATION_ADJUSTMENT;
        this.RightShoulder.rotationPointY += 2.0f + var2;
        this.SteveArm.rotateAngleX += this.SNEAK_LEG_X_ROTATION_ADJUSTMENT;
        this.unicornarm.rotateAngleX += this.SNEAK_LEG_X_ROTATION_ADJUSTMENT;
        this.LeftArm.rotateAngleX -= this.SNEAK_LEG_X_ROTATION_ADJUSTMENT;
        this.LeftArm.rotationPointY += 2.0f + var2;
        this.LeftShoulder.rotateAngleX -= this.SNEAK_LEG_X_ROTATION_ADJUSTMENT;
        this.LeftShoulder.rotationPointY += 2.0f + var2;
        this.RightLeg.rotateAngleX = this.ROTATE_270;
        this.RightLeg.rotateAngleY = 0.5f;
        this.RightLegJoint.rotateAngleX = this.ROTATE_270;
        this.RightLegJoint.rotateAngleY = 0.5f;
        this.LeftLeg.rotateAngleX = this.ROTATE_270;
        this.LeftLeg.rotateAngleY = -0.5f;
        this.LeftLegJoint.rotateAngleX = this.ROTATE_270;
        this.LeftLegJoint.rotateAngleY = -0.5f;
        for (var3 = 0; var3 < this.RightSubLeg.length; ++var3) {
            this.RightSubLeg[var3].rotateAngleX = this.ROTATE_270;
            this.RightSubLeg[var3].rotateAngleY = 0.5f;
            this.LeftSubLeg[var3].rotateAngleX = this.ROTATE_270;
            this.LeftSubLeg[var3].rotateAngleY = -0.5f;
        }
        this.shiftRotationPoint(this.RightLeg, 2.0f, 13.0f + var2, 5.0f);
        this.shiftRotationPoint(this.RightLegJoint, 2.0f, 13.0f + var2, 5.0f);
        this.shiftRotationPoint(this.LeftLeg, -2.0f, 13.0f + var2, 5.0f);
        this.shiftRotationPoint(this.LeftLegJoint, -2.0f, 13.0f + var2, 5.0f);
        for (var3 = 0; var3 < this.RightSubLeg.length; ++var3) {
            this.shiftRotationPoint(this.RightSubLeg[var3], 2.0f, 13.0f + var2, 5.0f);
            this.shiftRotationPoint(this.LeftSubLeg[var3], -2.0f, 13.0f + var2, 5.0f);
        }
        float var1 = this.isRiding ? -1.0f : 0.0f;
        this.tailstop = 0;
        if (!this.isArmour) {
            this.tailstop = this.Tail.length - this.wantTail * 5;
            if (this.tailstop <= 1) {
                this.tailstop = 0;
            }
            for (var3 = 0; var3 < this.tailstop; ++var3) {
                this.Tail[var3].rotationPointX = -3.5f;
                this.Tail[var3].rotationPointY = 12.0f + var2;
                this.Tail[var3].rotationPointZ += 2.0f + var1;
                this.Tail[var3].rotateAngleX = -0.5f;
                this.Tail[var3].rotateAngleZ = -1.1f;
            }
        }
        this.Bodypiece[5].rotateAngleX += 0.4f;
        this.Bodypiece[6].rotateAngleX += 0.4f;
        this.Bodypiece[7].rotateAngleX += 0.4f;
        this.Bodypiece[8].rotateAngleX += 0.4f;
        this.Bodypiece[9].rotateAngleX += 0.4f;
    }

    protected void aimBow(float tick) {
        if (this.isUnicorn && this.glowColor != 0) {
            this.aimBowUnicorn(tick);
        } else {
            this.aimBowPony(tick);
        }
    }

    protected void aimBowPony(float tick) {
        this.RightArm.rotateAngleZ = 0.0f;
        this.RightArm.rotateAngleY = -0.06f + this.head.rotateAngleY;
        this.RightArm.rotateAngleX = this.ROTATE_270 + this.head.rotateAngleX;
        this.RightArm.rotateAngleZ += MathHelper.cos(tick * 0.09f) * 0.05f + 0.05f;
        this.RightArm.rotateAngleX += MathHelper.sin(tick * 0.067f) * 0.05f;
        this.shiftRotationPoint(this.RightArm, 0.0f, 0.0f, 1.0f);
    }

    protected void aimBowUnicorn(float tick) {
        this.unicornarm.rotateAngleZ = 0.0f;
        this.unicornarm.rotateAngleY = -0.06f + this.head.rotateAngleY;
        this.unicornarm.rotateAngleX = this.ROTATE_270 + this.head.rotateAngleX;
        this.unicornarm.rotateAngleZ += MathHelper.cos(tick * 0.09f) * 0.05f + 0.05f;
        this.unicornarm.rotateAngleX += MathHelper.sin(tick * 0.067f) * 0.05f;
    }

    protected void animatePegasusWingsSneaking() {
        for (ModelRenderer aLeftWingExt : this.LeftWingExt) {
            aLeftWingExt.rotationPointY = this.LEFT_WING_RP_Y_SNEAK;
            aLeftWingExt.rotationPointZ = this.LEFT_WING_RP_Z_SNEAK;
            aLeftWingExt.rotateAngleX = this.EXT_WING_ROTATE_ANGLE_X;
            aLeftWingExt.rotateAngleZ = this.LEFT_WING_ROTATE_ANGLE_Z_SNEAK;
        }
        for (ModelRenderer aRightWingExt : this.RightWingExt) {
            aRightWingExt.rotationPointY = this.RIGHT_WING_RP_Y_SNEAK;
            aRightWingExt.rotationPointZ = this.RIGHT_WING_RP_Z_SNEAK;
            aRightWingExt.rotateAngleX = this.EXT_WING_ROTATE_ANGLE_X;
            aRightWingExt.rotateAngleZ = this.RIGHT_WING_ROTATE_ANGLE_Z_SNEAK;
        }
    }

    protected void animatePegasusWingsNotSneaking(float tick) {
        if (!this.isFlying) {
            for (ModelRenderer aLeftWing : this.LeftWing) {
                aLeftWing.rotationPointY = this.WING_FOLDED_RP_Y;
                aLeftWing.rotationPointZ = this.WING_FOLDED_RP_Z;
            }
            for (ModelRenderer aRightWing : this.RightWing) {
                aRightWing.rotationPointY = this.WING_FOLDED_RP_Y;
                aRightWing.rotationPointZ = this.WING_FOLDED_RP_Z;
            }
        } else {
            this.WingRotateAngleZ = MathHelper.sin(tick * 0.536f) * 1.0f;
            for (ModelRenderer aLeftWingExt : this.LeftWingExt) {
                aLeftWingExt.rotateAngleX = this.EXT_WING_ROTATE_ANGLE_X;
                aLeftWingExt.rotateAngleZ = -this.WingRotateAngleZ - this.ROTATE_270 - 0.4f;
                aLeftWingExt.rotationPointY = this.LEFT_WING_RP_Y_NOTSNEAK;
                aLeftWingExt.rotationPointZ = this.LEFT_WING_RP_Z_NOTSNEAK;
            }
            for (ModelRenderer aRightWingExt : this.RightWingExt) {
                aRightWingExt.rotateAngleX = this.EXT_WING_ROTATE_ANGLE_X;
                aRightWingExt.rotateAngleZ = this.WingRotateAngleZ + this.ROTATE_270 + 0.4f;
                aRightWingExt.rotationPointY = this.RIGHT_WING_RP_Y_NOTSNEAK;
                aRightWingExt.rotationPointZ = this.RIGHT_WING_RP_Z_NOTSNEAK;
            }
        }
    }

    protected void adjustPegasusWings(float var1, float var2, float var3) {
        if (!this.isFlying) {
            for (int var4 = 0; var4 < this.LeftWing.length; ++var4) {
                this.LeftWing[var4].rotateAngleX = var1;
                this.LeftWing[var4].rotationPointY = var2;
                this.LeftWing[var4].rotationPointZ = var3;
                this.RightWing[var4].rotateAngleX = var1;
                this.RightWing[var4].rotationPointY = var2;
                this.RightWing[var4].rotationPointZ = var3;
            }
        } else {
            for (int var4 = 0; var4 < this.LeftWingExt.length; ++var4) {
                this.LeftWingExt[var4].rotationPointY = var2;
                this.LeftWingExt[var4].rotationPointZ = var3;
                this.RightWingExt[var4].rotationPointY = var2;
                this.RightWingExt[var4].rotationPointZ = var3;
            }
        }
    }

    protected void fixSpecialRotations() {
        this.LeftWingExt[1].rotateAngleX -= 0.85f;
        this.LeftWingExt[2].rotateAngleX -= 0.75f;
        this.LeftWingExt[3].rotateAngleX -= 0.5f;
        this.LeftWingExt[5].rotateAngleX -= 0.85f;
        this.RightWingExt[1].rotateAngleX -= 0.85f;
        this.RightWingExt[2].rotateAngleX -= 0.75f;
        this.RightWingExt[3].rotateAngleX -= 0.5f;
        this.RightWingExt[5].rotateAngleX -= 0.85f;
        this.Bodypiece[5].rotateAngleX += 0.5f;
        this.Bodypiece[6].rotateAngleX += 0.5f;
        this.Bodypiece[7].rotateAngleX += 0.5f;
        this.Bodypiece[8].rotateAngleX += 0.5f;
        this.Bodypiece[9].rotateAngleX += 0.5f;
        //this.Bodypiece[10].rotateAngleX += 0.5f;
        //this.Bodypiece[11].rotateAngleX += 0.5f;
        //this.Bodypiece[12].rotateAngleX += 0.5f;
        //this.Bodypiece[13].rotateAngleX += 0.5f;
    }

    protected void fixSpecialRotationPoints(float move) {
    }

    public void shiftRotationPoint(PlaneRenderer aPlaneRenderer, float shiftX, float shiftY, float shiftZ) {
        aPlaneRenderer.rotationPointX += shiftX;
        aPlaneRenderer.rotationPointY += shiftY;
        aPlaneRenderer.rotationPointZ += shiftZ;
    }

    public void shiftRotationPoint(ModelRenderer aRenderer, float shiftX, float shiftY, float shiftZ) {
        aRenderer.rotationPointX += shiftX;
        aRenderer.rotationPointY += shiftY;
        aRenderer.rotationPointZ += shiftZ;
    }

    public void setRotationPoint(HornGlowRenderer aRenderer, float setX, float setY, float setZ) {
        aRenderer.rotationPointX = setX;
        aRenderer.rotationPointY = setY;
        aRenderer.rotationPointZ = setZ;
    }

    public void setRotationPoint(PlaneRenderer aPlaneRenderer, float setX, float setY, float setZ) {
        aPlaneRenderer.rotationPointX = setX;
        aPlaneRenderer.rotationPointY = setY;
        aPlaneRenderer.rotationPointZ = setZ;
    }

    public void setRotationPoint(ModelRenderer aRenderer, float setX, float setY, float setZ) {
        aRenderer.rotationPointX = setX;
        aRenderer.rotationPointY = setY;
        aRenderer.rotationPointZ = setZ;
    }

    @Override
    public void render(AniParams aniparams, boolean flag) {
        if (flag) {
            if (this.isRiding && !this.isArmour) {
                GL11.glTranslatef(0.0f, -0.56f, -0.46f);
            }
            if (this.isSleeping && !this.isArmour) {
                GL11.glRotatef(90.0f, 0.0f, 1.0f, 0.0f);
                GL11.glRotatef(270.0f, 0.0f, 0.0f, 1.0f);
                GL11.glRotatef(90.0f, 0.0f, 1.0f, 0.0f);
                GL11.glRotatef(180.0f, 0.0f, 0.0f, 1.0f);
                GL11.glRotatef(180.0f, 0.0f, 1.0f, 0.0f);
            }
            if (this.size == 0) {
                if (this.issneak && !this.isFlying && !this.isArmour) {
                    GL11.glTranslatef(0.0f, -0.12f, 0.0f);
                }
                if (this.isSleeping && !this.isArmour) {
                    GL11.glTranslatef(0.0f, -1.0f, 0.25f);
                }
                GL11.glPushMatrix();
                GL11.glTranslatef(0.0f, 0.7f, 0.0f);
                //GL11.glScalef(0.9f, 0.9f, 0.9f);
                GL11.glScalef(0.8f, 0.8f, 0.8f);
                this.renderHead();
                this.renderHorn();
                if (this.issneak && !this.isFlying) {
                    GL11.glTranslatef(0.0f, -0.01f, 0.15f);
                }
                this.renderNeck();
                GL11.glPopMatrix();
                GL11.glPushMatrix();
                GL11.glTranslatef(0.0f, 0.76f, -0.04f);
                GL11.glScalef(0.6f, 0.6f, 0.6f);
                this.renderBody();
                this.renderPlot();
                this.renderTail();
                GL11.glPopMatrix();
                GL11.glPushMatrix();
                GL11.glTranslatef(0.0f, 0.89f, 0.0f);
                GL11.glScalef(0.6f, 0.41f, 0.6f);
                if (this.issneak && !this.isFlying) {
                    GL11.glTranslatef(0.0f, 0.12f, 0.0f);
                }
                if (this.rainboom) {
                    GL11.glTranslatef(0.0f, -0.08f, 0.0f);
                }
                this.renderLegs();
                GL11.glPopMatrix();
            } else if (this.size == 2) {
                if (this.isSleeping && !this.isArmour) {
                    GL11.glTranslatef(0.0f, -0.47f, 0.2f);
                }
                GL11.glPushMatrix();
                //GL11.glTranslatef(0.0f, -0.17f, -0.04f);
                GL11.glTranslatef(0.0f, -0.05f, -0.04f);
                if (this.isSleeping && !this.isArmour) {
                    GL11.glTranslatef(0.0f, 0.0f, -0.1f);
                }
                if (this.issneak && !this.isFlying) {
                    GL11.glTranslatef(0.0f, 0.15f, 0.0f);
                }
                GL11.glPopMatrix();
                GL11.glPushMatrix();
                GL11.glTranslatef(0.0f, 0.04f, 0.0f);
                this.renderHead();
                GL11.glScalef(1.0f, 1.05f, 1.0f);
                this.renderHorn();
                GL11.glPopMatrix();
                GL11.glPushMatrix();
                GL11.glTranslatef(0.0f, -0.15f, -0.07f);
                if (this.issneak && !this.isFlying) {
                    GL11.glTranslatef(0.0f, 0.0f, -0.05f);
                }
                GL11.glPopMatrix();
                GL11.glPushMatrix();
                GL11.glTranslatef(0.0f, -0.1f, -0.05f);
                GL11.glScalef(1.05f, 1.3f, 1.05f);
                this.renderNeck();
                GL11.glPopMatrix();
                GL11.glPushMatrix();
                //GL11.glTranslatef(0.0f, -0.2f, -0.04f);
                //GL11.glScalef(1.15f, 1.2f, 1.2f);
                GL11.glTranslatef(0.0f, 0.0f, -0.04f);
                GL11.glScalef(1.05f, 1.05f, 1.05f);
                this.renderBody();
                this.renderPlot();
                GL11.glPopMatrix();
                GL11.glPushMatrix();
                //GL11.glTranslatef(0.0f, -0.2f, 0.08f);
                GL11.glTranslatef(0.0f, 0.0f, 0.08f);
                this.renderTail();
                GL11.glPopMatrix();
                GL11.glPushMatrix();
                //GL11.glTranslatef(0.0f, -0.14f, 0.0f);
                //GL11.glScalef(1.15f, 1.12f, 1.15f);
                GL11.glTranslatef(0.0f, 0.0f, 0.0f);
                GL11.glScalef(1.1f, 1.05f, 1.05f);
                this.renderLegs();
                GL11.glPopMatrix();
            } else if (this.size == 3) {
                if (this.isSleeping && !this.isArmour) {
                    GL11.glTranslatef(0.0f, -0.43f, 0.25f);
                }
                GL11.glPushMatrix();
                GL11.glTranslatef(0.0f, -0.15f, 0.01f);
                if (this.issneak && !this.isFlying) {
                    GL11.glTranslatef(0.0f, 0.05f, 0.0f);
                }
                this.renderHead();
                GL11.glScalef(1.0f, 1.2f, 1.0f);
                this.renderHorn();
                GL11.glPopMatrix();
                GL11.glPushMatrix();
                GL11.glTranslatef(0.0f, -0.19f, -0.01f);
                //GL11.glScalef(1.0f, 1.1f, 1.0f);
                GL11.glScalef(0.95f, 1.1f, 0.95f);
                if (this.issneak && !this.isFlying) {
                    GL11.glTranslatef(0.0f, -0.06f, -0.04f);
                }
                GL11.glPopMatrix();
                GL11.glPushMatrix();
                GL11.glTranslatef(0.0f, -0.1f, -0.015f);
                GL11.glScalef(0.9f, 1.1f, 0.9f);
                this.renderNeck();
                GL11.glPopMatrix();
                GL11.glPushMatrix();
                GL11.glTranslatef(0.0f, -0.1f, 0.0f);
                GL11.glScalef(1.0f, 1.0f, 1.0f);
                this.renderBody();
                this.renderPlot();
                this.renderTail();
                GL11.glPopMatrix();
                GL11.glPushMatrix();
                GL11.glTranslatef(0.0f, -0.25f, 0.03f);
                GL11.glScalef(1.0f, 1.18f, 1.0f);
                if (this.rainboom) {
                    GL11.glTranslatef(0.0f, 0.05f, 0.0f);
                }
                this.renderLegs();
                GL11.glPopMatrix();
            } else {
                if (this.isSleeping && !this.isArmour) {
                    GL11.glTranslatef(0.0f, -0.535f, 0.25f);
                }
                if (this.isMale) {
                    if (this.issneak && !this.isFlying && !this.isArmour) {
                        GL11.glTranslatef(0.0f, -0.12f, 0.0f);
                    }
                    if (this.isSleeping && !this.isArmour) {
                        GL11.glTranslatef(0.0f, -4.0f, 0.25f);
                    }
                    GL11.glPushMatrix();
                    GL11.glScalef(0.9f, 0.9f, 0.9f);
                    GL11.glTranslatef(0.0f, 0.225f, 0.0f);
                    this.renderHead();
                    this.renderHorn();
                    GL11.glPopMatrix();
                    GL11.glPushMatrix();
                    GL11.glTranslatef(0.0f, 0.23f, 0.0f);
                    GL11.glScalef(0.85f, 0.85f, 0.85f);
                    this.renderTail();
                    if (this.issneak && !this.isFlying) {
                        GL11.glTranslatef(0.0f, -0.01f, 0.15f);
                    }
                    GL11.glPopMatrix();
                    GL11.glPushMatrix();
                    GL11.glTranslatef(0.0f, 0.25f, 5.0E-4f);
                    GL11.glScalef(0.845f, 1.1f, 0.845f);
                    this.renderNeck();
                    GL11.glPopMatrix();
                    GL11.glPushMatrix();
                    GL11.glTranslatef(0.0f, 0.22f, 0.0f);
                    GL11.glScalef(0.85f, 0.85f, 0.85f);
                    this.renderBody();
                    this.renderPlot();
                    GL11.glPopMatrix();
                    GL11.glPushMatrix();
                    if (this.rainboom && !this.isArmour && !this.isRiding && !this.isSleeping && this.isRainboomTail) {
                        if (aniparams.move - this.PreviousMove < 0.1f) {
                            if (this.RainboomLength < 10.0f) {
                                this.RainboomLength += 0.05f;
                            }
                        } else {
                            this.RainboomLength = 0.0f;
                        }
                        GL11.glTranslatef(0.0f, 0.0f, -1.0f - this.RainboomLength * 1.35f);
                        GL11.glScalef(1.0f, 1.5f, 1.0f + this.RainboomLength);
                        this.renderRainbowTail();
                        GL11.glPopMatrix();
                        GL11.glPushMatrix();
                    } else {
                        this.RainboomLength = 0.0f;
                    }
                    GL11.glTranslatef(0.0f, 0.22f, 0.0f);
                    GL11.glScalef(0.85f, 0.85f, 0.85f);
                    if (this.issneak && !this.isFlying) {
                        GL11.glTranslatef(0.0f, 0.12f, 0.0f);
                    }
                    if (this.rainboom) {
                        GL11.glTranslatef(0.0f, -0.08f, 0.0f);
                    }
                    this.renderLegs();
                    GL11.glPopMatrix();
                } else {
                    if (this.issneak && !this.isFlying && !this.isArmour) {
                        GL11.glTranslatef(0.0f, -0.12f, 0.0f);
                    }
                    if (this.isSleeping && !this.isArmour) {
                        GL11.glTranslatef(0.0f, -4.0f, 0.25f);
                    }
                    GL11.glPushMatrix();
                    GL11.glScalef(0.9f, 0.9f, 0.9f);
                    GL11.glTranslatef(0.0f, 0.275f, 0.0f);
                    this.renderHead();
                    this.renderHorn();
                    GL11.glPopMatrix();
                    GL11.glPushMatrix();
                    GL11.glTranslatef(0.0f, 0.25f, -0.1f);
                    GL11.glScalef(0.92f, 0.92f, 0.92f);
                    this.renderTail();
                    if (this.issneak && !this.isFlying) {
                        GL11.glTranslatef(0.0f, -0.01f, 0.15f);
                    }
                    GL11.glPopMatrix();
                    GL11.glPushMatrix();
                    GL11.glTranslatef(0.0f, 0.25f, 0.002f);
                    GL11.glScalef(0.78f, 1.0f, 0.78f);
                    this.renderNeck();
                    GL11.glPopMatrix();
                    GL11.glPushMatrix();
                    GL11.glTranslatef(0.0f, 0.3f, 0.0f);
                    GL11.glScalef(0.8f, 0.8f, 0.8f);
                    this.renderBody();
                    this.renderPlot();
                    GL11.glPopMatrix();
                    GL11.glPushMatrix();
                    if (this.rainboom && !this.isArmour && !this.isRiding && !this.isSleeping && this.isRainboomTail) {
                        if (aniparams.move - this.PreviousMove < 0.1f) {
                            if (this.RainboomLength < 10.0f) {
                                this.RainboomLength += 0.05f;
                            }
                        } else {
                            this.RainboomLength = 0.0f;
                        }
                        GL11.glTranslatef(0.0f, 0.0f, -1.0f - this.RainboomLength * 1.35f);
                        GL11.glScalef(1.0f, 1.5f, 1.0f + this.RainboomLength);
                        this.renderRainbowTail();
                        GL11.glPopMatrix();
                        GL11.glPushMatrix();
                    } else {
                        this.RainboomLength = 0.0f;
                    }
                    GL11.glTranslatef(0.0f, 0.3f, 0.0f);
                    GL11.glScalef(0.8f, 0.8f, 0.8f);
                    if (this.issneak && !this.isFlying) {
                        GL11.glTranslatef(0.0f, 0.12f, 0.0f);
                    }
                    if (this.rainboom) {
                        GL11.glTranslatef(0.0f, -0.08f, 0.0f);
                    }
                    this.renderLegs();
                    GL11.glPopMatrix();
                }
            }
        } else {
            this.SteveArm.render(this.scale);
        }
    }

    protected void renderHead() {
        this.head.render(this.scale);
        this.headpiece[0].render(this.scale);
        this.headpiece[1].render(this.scale);
        if (PonyManager.getInstance().getShowSnuzzles() == 1) {
            int i;
            if (this.isMale) {
                for (i = 0; i < this.MuzzleMale.length; ++i) {
                    this.MuzzleMale[i].render(this.scale);
                }
            } else {
                for (i = 0; i < this.MuzzleFemale.length; ++i) {
                    this.MuzzleFemale[i].render(this.scale);
                }
            }
        }

        for (ModelRenderer aMane : this.Mane) {
            aMane.render(this.scale);
        }
        switch (this.StylesofMane) {
            case 1: {
                for (ModelRenderer aIrokeeze : this.Irokeeze) {
                    aIrokeeze.render(this.scale);
                }
                break;
            }
            case 2: {
                for (PlaneRenderer aLeftExtMane : this.LeftExtMane) {
                    aLeftExtMane.render(this.scale);
                }
                break;
            }
            case 3: {
                for (PlaneRenderer aRightExtMane : this.RightExtMane) {
                    aRightExtMane.render(this.scale);
                }
                break;
            }
            case 4: {
                for (int var1 = 0; var1 < this.RightExtMane.length; ++var1) {
                    this.RightExtMane[var1].render(this.scale);
                    this.LeftExtMane[var1].render(this.scale);
                }
                break;
            }
        }

        this.helmet.render(this.scale);
    }

    protected void renderHorn() {
        if (this.isUnicorn) {
            this.headpiece[2].render(this.scale);
            if (this.m != 0 && this.glowColor != 0) {
                GL11.glPushAttrib(24577);
                GL11.glDisable(3553);
                GL11.glDisable(2896);
                GL11.glEnable(3042);
                float red = (float) (this.glowColor >> 16 & 255) / 255.0f;
                float green = (float) (this.glowColor >> 8 & 255) / 255.0f;
                float blue = (float) (this.glowColor & 255) / 255.0f;
                GL11.glBlendFunc(770, 1);
                GL11.glColor4f(red, green, blue, 0.4f);
                this.hornglow[0].render(this.scale);
                GL11.glColor4f(red, green, blue, 0.2f);
                this.hornglow[1].render(this.scale);
                GL11.glPopAttrib();
            }
        }
    }

    protected void renderNeck() {
        for (PlaneRenderer aBodypieceNeck : this.BodypieceNeck) {
            aBodypieceNeck.render(this.scale);
        }
    }

    protected void renderPlot() {
        for (int var1 = 0; var1 < this.LeftPlotPiece.length; ++var1) {
            this.LeftPlotPiece[var1].render(this.scale);
            this.RightPlotPiece[var1].render(this.scale);
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    protected void renderBody() {
        int j;
        this.Body.render(this.scale);
        for (j = 0; j < this.Bodypiece.length; ++j) {
            this.Bodypiece[j].render(this.scale);
        }
        if (!this.isPegasus) return;
        if (this.isFlying || this.issneak) {
            this.setExtendingWings(false);
            for (ModelRenderer aLeftWingExt : this.LeftWingExt) {
                aLeftWingExt.render(this.scale);
            }

            for (ModelRenderer aRightWingExt : this.RightWingExt) {
                aRightWingExt.render(this.scale);
            }
            return;
        }
        this.setExtendingWings(true);
        for (ModelRenderer aLeftWing : this.LeftWing) {
            aLeftWing.render(this.scale);
        }

        for (ModelRenderer aRightWing : this.RightWing) {
            aRightWing.render(this.scale);

        }
    }

    protected void renderTail() {
        int tailstop = this.Tail.length - this.wantTail * 5;
        if (tailstop <= 1) {
            tailstop = 0;
        }
        for (int k = 0; k < tailstop; ++k) {
            this.Tail[k].render(this.scale);
        }
    }

    protected void renderLegs() {
        this.LeftArm.render(this.scale);
        this.RightArm.render(this.scale);
        this.LeftLeg.render(this.scale);
        this.LeftLegJoint.render(this.scale);
        this.RightLeg.render(this.scale);
        this.RightLegJoint.render(this.scale);
        this.LeftSubLeg[0].render(this.scale);
        this.LeftSubLeg[1].render(this.scale);
        this.LeftSubLeg[2].render(this.scale);
        this.RightSubLeg[0].render(this.scale);
        this.RightSubLeg[1].render(this.scale);
        this.RightSubLeg[2].render(this.scale);
    }

    protected void renderRainbowTail() {
        for (PlaneRenderer aRainbowTail : this.RainbowTail) {
            aRainbowTail.render(this.scale);
        }
    }

    protected void initTextures() {
        this.Tail = new PlaneRenderer[21];
        this.headpiece = new ModelRenderer[3];
        this.hornglow = new HornGlowRenderer[2];
        this.Mane = new ModelRenderer[5];
        this.Irokeeze = new ModelRenderer[3];
        this.RightExtMane = new PlaneRenderer[5];
        this.LeftExtMane = new PlaneRenderer[5];
        this.MuzzleFemale = new PlaneRenderer[10];
        this.MuzzleMale = new PlaneRenderer[5];
        //this.Bodypiece = new PlaneRenderer[14];
        this.Bodypiece = new PlaneRenderer[11];
        this.RightSubLeg = new PlaneRenderer[3];
        this.LeftSubLeg = new PlaneRenderer[3];
        this.BodypieceNeck = new PlaneRenderer[4];
        this.LeftPlotPiece = new PlaneRenderer[12];
        this.RightPlotPiece = new PlaneRenderer[12];
        this.LeftWing = new ModelRenderer[3];
        this.RightWing = new ModelRenderer[3];
        this.LeftWingExt = new ModelRenderer[7];
        this.RightWingExt = new ModelRenderer[7];
        this.RainbowTail = new PlaneRenderer[70];
        this.initHeadTextures();
        this.initMuzzleTextures();
        this.initBodyTextures();
        this.initLegTextures();
        this.initTailTextures();
        this.initWingTextures();
        this.initRainbowTailTextures();
    }

    protected void initHeadTextures() {
        this.cloak = new ModelRenderer(this, 0, 0);
        this.head = new ModelRenderer(this, 0, 0);
        this.headpiece[0] = new ModelRenderer(this, 12, 16);
        this.headpiece[1] = new ModelRenderer(this, 12, 16);
        this.headpiece[1].mirror = true;
        this.headpiece[2] = new ModelRenderer(this, 0, 3);
        this.hornglow[0] = new HornGlowRenderer(this, 0, 3);
        this.hornglow[1] = new HornGlowRenderer(this, 0, 3);
        for (int var1 = 0; var1 < this.Mane.length; ++var1) {
            this.Mane[var1] = new ModelRenderer(this, 32, 0);
        }
        for (int var1 = 0; var1 < this.Irokeeze.length; ++var1) {
            this.Irokeeze[var1] = new ModelRenderer(this, 56, 0);
        }
        for (int var1 = 0; var1 < this.RightExtMane.length; ++var1) {
            this.RightExtMane[var1] = new PlaneRenderer(this, 56, 0);
        }
        for (int var1 = 0; var1 < this.LeftExtMane.length; ++var1) {
            this.LeftExtMane[var1] = new PlaneRenderer(this, 56, 0);
        }
        this.helmet = new ModelRenderer(this, 32, 0);
        this.boxList.remove(this.headpiece[2]);
    }

    protected void initMuzzleTextures() {
        this.MuzzleFemale[0] = new PlaneRenderer(this, 10, 14);
        this.MuzzleFemale[1] = new PlaneRenderer(this, 11, 13);
        this.MuzzleFemale[2] = new PlaneRenderer(this, 9, 14);
        this.MuzzleFemale[3] = new PlaneRenderer(this, 14, 14);
        this.MuzzleFemale[4] = new PlaneRenderer(this, 11, 12);
        this.MuzzleFemale[5] = new PlaneRenderer(this, 18, 7);
        this.MuzzleFemale[6] = new PlaneRenderer(this, 9, 14);
        this.MuzzleFemale[7] = new PlaneRenderer(this, 14, 14);
        this.MuzzleFemale[8] = new PlaneRenderer(this, 11, 12);
        this.MuzzleFemale[9] = new PlaneRenderer(this, 12, 12);
        this.MuzzleMale[0] = new PlaneRenderer(this, 10, 13);
        this.MuzzleMale[1] = new PlaneRenderer(this, 10, 13);
        this.MuzzleMale[2] = new PlaneRenderer(this, 18, 7);
        this.MuzzleMale[3] = new PlaneRenderer(this, 10, 13);
        this.MuzzleMale[4] = new PlaneRenderer(this, 13, 13);
    }

    protected void initBodyTextures() {
        this.Body = new ModelRenderer(this, 16, 16);
        this.Bodypiece[0] = new PlaneRenderer(this, 24, 0);
        this.Bodypiece[1] = new PlaneRenderer(this, 24, 0);
        this.Bodypiece[2] = new PlaneRenderer(this, 32, 20);
        this.Bodypiece[3] = new PlaneRenderer(this, 24, 28);
        this.Bodypiece[3].mirrorxy = true;
        this.Bodypiece[4] = new PlaneRenderer(this, 16, 28);
        this.Bodypiece[4].mirrorxy = true;
        this.Bodypiece[5] = new PlaneRenderer(this, 32, 0);
        this.Bodypiece[6] = new PlaneRenderer(this, 32, 0);
        this.Bodypiece[7] = new PlaneRenderer(this, 32, 0);
        this.Bodypiece[7].mirror = true;
        this.Bodypiece[8] = new PlaneRenderer(this, 32, 0);
        this.Bodypiece[9] = new PlaneRenderer(this, 32, 0);
        this.Bodypiece[10] = new PlaneRenderer(this, 24, 28);
        this.BodypieceNeck[0] = new PlaneRenderer(this, 0, 16);
        this.BodypieceNeck[1] = new PlaneRenderer(this, 0, 16);
        this.BodypieceNeck[2] = new PlaneRenderer(this, 0, 16);
        this.BodypieceNeck[3] = new PlaneRenderer(this, 0, 16);
        this.LeftPlotPiece[0] = new PlaneRenderer(this, 36, 20);
        this.LeftPlotPiece[1] = new PlaneRenderer(this, 30, 0);
        this.LeftPlotPiece[2] = new PlaneRenderer(this, 29, 0);
        this.LeftPlotPiece[3] = new PlaneRenderer(this, 36, 24);
        this.LeftPlotPiece[4] = new PlaneRenderer(this, 36, 16);
        this.LeftPlotPiece[5] = new PlaneRenderer(this, 4, 0);
        this.LeftPlotPiece[6] = new PlaneRenderer(this, 36, 16);
        this.LeftPlotPiece[7] = new PlaneRenderer(this, 40, 16);
        this.LeftPlotPiece[8] = new PlaneRenderer(this, 52, 16);
        this.LeftPlotPiece[9] = new PlaneRenderer(this, 52, 16);
        this.LeftPlotPiece[10] = new PlaneRenderer(this, 36, 16);
        this.LeftPlotPiece[11] = new PlaneRenderer(this, 40, 16);
        this.RightPlotPiece[0] = new PlaneRenderer(this, 32, 20);
        this.RightPlotPiece[1] = new PlaneRenderer(this, 30, 0);
        this.RightPlotPiece[2] = new PlaneRenderer(this, 29, 0);
        this.RightPlotPiece[3] = new PlaneRenderer(this, 32, 24);
        this.RightPlotPiece[4] = new PlaneRenderer(this, 32, 16);
        this.RightPlotPiece[5] = new PlaneRenderer(this, 4, 0);
        this.RightPlotPiece[6] = new PlaneRenderer(this, 36, 16);
        this.RightPlotPiece[6].mirror = true;
        this.RightPlotPiece[7] = new PlaneRenderer(this, 40, 16);
        this.RightPlotPiece[7].mirror = true;
        this.RightPlotPiece[8] = new PlaneRenderer(this, 52, 16);
        this.RightPlotPiece[8].mirror = true;
        this.RightPlotPiece[9] = new PlaneRenderer(this, 52, 16);
        this.RightPlotPiece[9].mirror = true;
        this.RightPlotPiece[10] = new PlaneRenderer(this, 36, 16);
        this.RightPlotPiece[10].mirror = true;
        this.RightPlotPiece[11] = new PlaneRenderer(this, 40, 16);
        this.RightPlotPiece[11].mirror = true;
    }

    protected void initLegTextures() {
        this.RightArm = new ModelRenderer(this, 40, 16);
        this.RightShoulder = new ModelRenderer(this, 24, 22);
        this.LeftArm = new ModelRenderer(this, 40, 16);
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
        this.RightSubLeg[1].mirror = true;
        this.LeftSubLeg[1] = new PlaneRenderer(this, 12, 22);
        this.RightSubLeg[2] = new PlaneRenderer(this, 8, 16);
        this.LeftSubLeg[2] = new PlaneRenderer(this, 8, 16);
        this.LeftSubLeg[2].mirror = true;
        this.SteveArm = new ModelRenderer(this, 40, 16);
        this.unicornarm = new ModelRenderer(this, 40, 16);
    }

    protected void initTailTextures() {
        this.Tail[0] = new PlaneRenderer(this, 32, 0);
        this.Tail[1] = new PlaneRenderer(this, 36, 0);
        this.Tail[2] = new PlaneRenderer(this, 32, 0);
        this.Tail[3] = new PlaneRenderer(this, 36, 0);
        this.Tail[4] = new PlaneRenderer(this, 32, 0);
        this.Tail[5] = new PlaneRenderer(this, 32, 0);
        this.Tail[6] = new PlaneRenderer(this, 36, 4);
        this.Tail[7] = new PlaneRenderer(this, 32, 4);
        this.Tail[8] = new PlaneRenderer(this, 36, 4);
        this.Tail[9] = new PlaneRenderer(this, 32, 4);
        this.Tail[10] = new PlaneRenderer(this, 32, 0);
        this.Tail[11] = new PlaneRenderer(this, 36, 0);
        this.Tail[12] = new PlaneRenderer(this, 32, 0);
        this.Tail[13] = new PlaneRenderer(this, 36, 0);
        this.Tail[14] = new PlaneRenderer(this, 32, 0);
        this.Tail[15] = new PlaneRenderer(this, 32, 0);
        this.Tail[16] = new PlaneRenderer(this, 36, 4);
        this.Tail[17] = new PlaneRenderer(this, 32, 4);
        this.Tail[18] = new PlaneRenderer(this, 36, 4);
        this.Tail[19] = new PlaneRenderer(this, 32, 4);
        this.Tail[20] = new PlaneRenderer(this, 32, 0);
    }

    protected void initWingTextures() {
        this.LeftWing[0] = new ModelRenderer(this, 56, 16);
        this.LeftWing[0].mirror = true;
        this.LeftWing[1] = new ModelRenderer(this, 56, 16);
        this.LeftWing[1].mirror = true;
        this.LeftWing[2] = new ModelRenderer(this, 56, 16);
        this.LeftWing[2].mirror = true;
        this.RightWing[0] = new ModelRenderer(this, 56, 16);
        this.RightWing[1] = new ModelRenderer(this, 56, 16);
        this.RightWing[2] = new ModelRenderer(this, 56, 16);
        this.LeftWingExt[0] = new ModelRenderer(this, 56, 19);
        this.LeftWingExt[0].mirror = true;
        this.LeftWingExt[1] = new ModelRenderer(this, 56, 19);
        this.LeftWingExt[1].mirror = true;
        this.LeftWingExt[2] = new ModelRenderer(this, 56, 19);
        this.LeftWingExt[2].mirror = true;
        this.LeftWingExt[3] = new ModelRenderer(this, 56, 19);
        this.LeftWingExt[3].mirror = true;
        this.LeftWingExt[4] = new ModelRenderer(this, 56, 19);
        this.LeftWingExt[4].mirror = true;
        this.LeftWingExt[5] = new ModelRenderer(this, 56, 19);
        this.LeftWingExt[5].mirror = true;
        this.LeftWingExt[6] = new ModelRenderer(this, 56, 19);
        this.LeftWingExt[6].mirror = true;
        this.RightWingExt[0] = new ModelRenderer(this, 56, 19);
        this.RightWingExt[0].mirror = true;
        this.RightWingExt[1] = new ModelRenderer(this, 56, 19);
        this.RightWingExt[1].mirror = true;
        this.RightWingExt[2] = new ModelRenderer(this, 56, 19);
        this.RightWingExt[2].mirror = true;
        this.RightWingExt[3] = new ModelRenderer(this, 56, 19);
        this.RightWingExt[3].mirror = true;
        this.RightWingExt[4] = new ModelRenderer(this, 56, 19);
        this.RightWingExt[4].mirror = true;
        this.RightWingExt[5] = new ModelRenderer(this, 56, 19);
        this.RightWingExt[5].mirror = true;
        this.RightWingExt[6] = new ModelRenderer(this, 56, 19);
        this.RightWingExt[6].mirror = true;
        this.compressWings();
    }

    protected void initRainbowTailTextures() {
        int var1;
        for (var1 = 0; var1 < 10; ++var1) {
            this.RainbowTail[var1] = new PlaneRenderer(this, 0, 6);
        }
        for (var1 = 10; var1 < 20; ++var1) {
            this.RainbowTail[var1] = new PlaneRenderer(this, 1, 6);
        }
        for (var1 = 20; var1 < 30; ++var1) {
            this.RainbowTail[var1] = new PlaneRenderer(this, 2, 6);
        }
        for (var1 = 30; var1 < 40; ++var1) {
            this.RainbowTail[var1] = new PlaneRenderer(this, 3, 6);
        }
        for (var1 = 40; var1 < 50; ++var1) {
            this.RainbowTail[var1] = new PlaneRenderer(this, 0, 7);
        }
        for (var1 = 50; var1 < 60; ++var1) {
            this.RainbowTail[var1] = new PlaneRenderer(this, 1, 7);
        }
        for (var1 = 60; var1 < 70; ++var1) {
            this.RainbowTail[var1] = new PlaneRenderer(this, 2, 7);
        }
    }


    protected void initPositions(float yOffset, float stretch) {
        this.initHeadPositions(yOffset, stretch);
        this.initMuzzlePositions(yOffset, stretch);
        this.initBodyPositions(yOffset, stretch);
        this.initLegPositions(yOffset, stretch);
        this.initPlotPositions(yOffset, stretch);
        this.initTailPositions(yOffset, stretch);
        this.initWingPositions(yOffset, stretch);
        this.initRainbowTailPositions(yOffset, stretch);
    }

    protected void initRightExtManePosition(float var1, float var2) {
        this.RightExtMane[0].addSidePlane(-5.2f + this.HEAD_CENTRE_X, 5.0f + this.HEAD_CENTRE_Y, -4.0f + this.HEAD_CENTRE_Z, 0, 8, 8, var2 + 0.5f);
        this.RightExtMane[0].setRotationPoint(this.HEAD_RP_X, this.HEAD_RP_Y + var1, this.HEAD_RP_Z);
        this.RightExtMane[1].addSidePlane(-5.4f + this.HEAD_CENTRE_X, 5.2f + this.HEAD_CENTRE_Y, -4.0f + this.HEAD_CENTRE_Z, 0, 8, 8, var2 + 0.6f);
        this.RightExtMane[1].setRotationPoint(this.HEAD_RP_X, this.HEAD_RP_Y + var1, this.HEAD_RP_Z);
        this.RightExtMane[2].addSidePlane(-5.6f + this.HEAD_CENTRE_X, 5.4f + this.HEAD_CENTRE_Y, -4.0f + this.HEAD_CENTRE_Z, 0, 8, 8, var2 + 0.7f);
        this.RightExtMane[2].setRotationPoint(this.HEAD_RP_X, this.HEAD_RP_Y + var1, this.HEAD_RP_Z);
        this.RightExtMane[3].addSidePlane(-5.8f + this.HEAD_CENTRE_X, 5.6f + this.HEAD_CENTRE_Y, -4.0f + this.HEAD_CENTRE_Z, 0, 8, 8, var2 + 0.8f);
        this.RightExtMane[3].setRotationPoint(this.HEAD_RP_X, this.HEAD_RP_Y + var1, this.HEAD_RP_Z);
        this.RightExtMane[4].addSidePlane(-6.0f + this.HEAD_CENTRE_X, 5.8f + this.HEAD_CENTRE_Y, -4.0f + this.HEAD_CENTRE_Z, 0, 8, 8, var2 + 0.9f);
        this.RightExtMane[4].setRotationPoint(this.HEAD_RP_X, this.HEAD_RP_Y + var1, this.HEAD_RP_Z);
    }

    protected void initLeftExtManePosition(float var1, float var2) {
        this.LeftExtMane[0].addSidePlane(4.0f + this.HEAD_CENTRE_X, 5.0f + this.HEAD_CENTRE_Y, -4.0f + this.HEAD_CENTRE_Z, 0, 8, 8, var2 + 0.5f);
        this.LeftExtMane[0].setRotationPoint(this.HEAD_RP_X, this.HEAD_RP_Y + var1, this.HEAD_RP_Z);
        this.LeftExtMane[1].addSidePlane(4.0f + this.HEAD_CENTRE_X, 5.2f + this.HEAD_CENTRE_Y, -4.0f + this.HEAD_CENTRE_Z, 0, 8, 8, var2 + 0.6f);
        this.LeftExtMane[1].setRotationPoint(this.HEAD_RP_X, this.HEAD_RP_Y + var1, this.HEAD_RP_Z);
        this.LeftExtMane[2].addSidePlane(4.0f + this.HEAD_CENTRE_X, 5.4f + this.HEAD_CENTRE_Y, -4.0f + this.HEAD_CENTRE_Z, 0, 8, 8, var2 + 0.7f);
        this.LeftExtMane[2].setRotationPoint(this.HEAD_RP_X, this.HEAD_RP_Y + var1, this.HEAD_RP_Z);
        this.LeftExtMane[3].addSidePlane(4.0f + this.HEAD_CENTRE_X, 5.6f + this.HEAD_CENTRE_Y, -4.0f + this.HEAD_CENTRE_Z, 0, 8, 8, var2 + 0.8f);
        this.LeftExtMane[3].setRotationPoint(this.HEAD_RP_X, this.HEAD_RP_Y + var1, this.HEAD_RP_Z);
        this.LeftExtMane[4].addSidePlane(4.0f + this.HEAD_CENTRE_X, 5.8f + this.HEAD_CENTRE_Y, -4.0f + this.HEAD_CENTRE_Z, 0, 8, 8, var2 + 0.9f);
        this.LeftExtMane[4].setRotationPoint(this.HEAD_RP_X, this.HEAD_RP_Y + var1, this.HEAD_RP_Z);
    }

    protected void initIrokeezePositions(float var1, float var2) {
        this.Irokeeze[0].addBox(-1.0f + this.HEAD_CENTRE_X, 1.0f + this.HEAD_CENTRE_Y, -6.4f + this.HEAD_CENTRE_Z, 2, 5, 3, var2 + 0.1f);
        this.Irokeeze[0].setRotationPoint(this.HEAD_RP_X, this.HEAD_RP_Y + var1, this.HEAD_RP_Z + 3.1415927f);
        this.Irokeeze[1].addBox(-1.0f + this.HEAD_CENTRE_X, -2.2f + this.HEAD_CENTRE_Y, -6.2f + this.HEAD_CENTRE_Z, 2, 5, 3, var2 + 0.1f);
        this.Irokeeze[1].setRotationPoint(this.HEAD_RP_X, this.HEAD_RP_Y + var1, this.HEAD_RP_Z + 3.1415927f);
        this.Irokeeze[2].addBox(-1.0f + this.HEAD_CENTRE_X, 1.0f + this.HEAD_CENTRE_Y, -1.9f + this.HEAD_CENTRE_Z, 2, 6, 2, var2);
        this.Irokeeze[2].setRotationPoint(this.HEAD_RP_X, this.HEAD_RP_Y + var1, this.HEAD_RP_Z);
    }

    protected void initHeadPositions(float yOffset, float stretch) {
        this.cloak.addBox(-5.0f, 0.0f, -1.0f, 10, 16, 1, stretch);
        this.head.addBox(-4.0f + this.HEAD_CENTRE_X, -4.0f + this.HEAD_CENTRE_Y, -4.0f + this.HEAD_CENTRE_Z, 8, 8, 8, stretch);
        this.head.setRotationPoint(this.HEAD_RP_X, this.HEAD_RP_Y + yOffset, this.HEAD_RP_Z);
        this.headpiece[0].addBox(-4.0f + this.HEAD_CENTRE_X, -6.0f + this.HEAD_CENTRE_Y, 1.0f + this.HEAD_CENTRE_Z, 2, 2, 2, stretch);
        this.headpiece[0].setRotationPoint(this.HEAD_RP_X, this.HEAD_RP_Y + yOffset, this.HEAD_RP_Z);
        this.headpiece[1].addBox(2.0f + this.HEAD_CENTRE_X, -6.0f + this.HEAD_CENTRE_Y, 1.0f + this.HEAD_CENTRE_Z, 2, 2, 2, stretch);
        this.headpiece[1].setRotationPoint(this.HEAD_RP_X, this.HEAD_RP_Y + yOffset, this.HEAD_RP_Z);
        this.headpiece[2].addBox(-0.5f + this.HEAD_CENTRE_X, -10.0f + this.HEAD_CENTRE_Y, -1.5f + this.HEAD_CENTRE_Z, 1, 4, 1, stretch);
        this.headpiece[2].setRotationPoint(this.HEAD_RP_X, this.HEAD_RP_Y + yOffset, this.HEAD_RP_Z);
        this.hornglow[0].addBox(-0.5f + this.HEAD_CENTRE_X, -10.0f + this.HEAD_CENTRE_Y, -1.5f + this.HEAD_CENTRE_Z, 1, 4, 1, stretch + 0.5f);
        this.hornglow[0].setRotationPoint(this.HEAD_RP_X, this.HEAD_RP_Y + yOffset, this.HEAD_RP_Z);
        this.hornglow[1].addBox(-0.5f + this.HEAD_CENTRE_X, -10.0f + this.HEAD_CENTRE_Y, -1.5f + this.HEAD_CENTRE_Z, 1, 3, 1, stretch + 0.8f);
        this.hornglow[1].setRotationPoint(this.HEAD_RP_X, this.HEAD_RP_Y + yOffset, this.HEAD_RP_Z);
        this.Mane[0].addBox(-4.0f + this.HEAD_CENTRE_X, -4.0f + this.HEAD_CENTRE_Y, -4.0f + this.HEAD_CENTRE_Z, 8, 8, 8, stretch + 0.5f);
        this.Mane[0].setRotationPoint(this.HEAD_RP_X, this.HEAD_RP_Y + yOffset, this.HEAD_RP_Z);
        this.Mane[1].addBox(-4.0f + this.HEAD_CENTRE_X, -4.0f + this.HEAD_CENTRE_Y, -4.0f + this.HEAD_CENTRE_Z, 8, 8, 8, stretch + 0.6f);
        this.Mane[1].setRotationPoint(this.HEAD_RP_X, this.HEAD_RP_Y + yOffset, this.HEAD_RP_Z);
        this.Mane[2].addBox(-4.0f + this.HEAD_CENTRE_X, -4.0f + this.HEAD_CENTRE_Y, -4.0f + this.HEAD_CENTRE_Z, 8, 8, 8, stretch + 0.7f);
        this.Mane[2].setRotationPoint(this.HEAD_RP_X, this.HEAD_RP_Y + yOffset, this.HEAD_RP_Z);
        this.Mane[3].addBox(-4.0f + this.HEAD_CENTRE_X, -4.0f + this.HEAD_CENTRE_Y, -4.0f + this.HEAD_CENTRE_Z, 8, 8, 8, stretch + 0.8f);
        this.Mane[3].setRotationPoint(this.HEAD_RP_X, this.HEAD_RP_Y + yOffset, this.HEAD_RP_Z);
        this.Mane[4].addBox(-4.0f + this.HEAD_CENTRE_X, -4.0f + this.HEAD_CENTRE_Y, -4.0f + this.HEAD_CENTRE_Z, 8, 8, 8, stretch + 0.9f);
        this.Mane[4].setRotationPoint(this.HEAD_RP_X, this.HEAD_RP_Y + yOffset, this.HEAD_RP_Z);
        this.initIrokeezePositions(yOffset, stretch);
        this.initRightExtManePosition(yOffset, stretch);
        this.initLeftExtManePosition(yOffset, stretch);
        this.helmet.addBox(-4.0f + this.HEAD_CENTRE_X, -4.0f + this.HEAD_CENTRE_Y, -4.0f + this.HEAD_CENTRE_Z, 8, 8, 8, stretch + 0.5f);
        this.helmet.setRotationPoint(this.HEAD_RP_X, this.HEAD_RP_Y + yOffset, this.HEAD_RP_Z);
    }

    protected void initMuzzlePositions(float yOffset, float stretch) {
        this.MuzzleFemale[0].addBackPlane(-2.0f + this.HEAD_CENTRE_X, 2.0f + this.HEAD_CENTRE_Y, -5.0f + this.HEAD_CENTRE_Z, 4, 2, 0, stretch);
        this.MuzzleFemale[0].setRotationPoint(this.HEAD_RP_X, this.HEAD_RP_Y + yOffset, this.HEAD_RP_Z);
        this.MuzzleFemale[1].addBackPlane(-1.0f + this.HEAD_CENTRE_X, 1.0f + this.HEAD_CENTRE_Y, -5.0f + this.HEAD_CENTRE_Z, 2, 1, 0, stretch);
        this.MuzzleFemale[1].setRotationPoint(this.HEAD_RP_X, this.HEAD_RP_Y + yOffset, this.HEAD_RP_Z);
        this.MuzzleFemale[2].addTopPlane(-2.0f + this.HEAD_CENTRE_X, 2.0f + this.HEAD_CENTRE_Y, -5.0f + this.HEAD_CENTRE_Z, 1, 0, 1, stretch);
        this.MuzzleFemale[2].setRotationPoint(this.HEAD_RP_X, this.HEAD_RP_Y + yOffset, this.HEAD_RP_Z);
        this.MuzzleFemale[3].addTopPlane(1.0f + this.HEAD_CENTRE_X, 2.0f + this.HEAD_CENTRE_Y, -5.0f + this.HEAD_CENTRE_Z, 1, 0, 1, stretch);
        this.MuzzleFemale[3].setRotationPoint(this.HEAD_RP_X, this.HEAD_RP_Y + yOffset, this.HEAD_RP_Z);
        this.MuzzleFemale[4].addTopPlane(-1.0f + this.HEAD_CENTRE_X, 1.0f + this.HEAD_CENTRE_Y, -5.0f + this.HEAD_CENTRE_Z, 2, 0, 1, stretch);
        this.MuzzleFemale[4].setRotationPoint(this.HEAD_RP_X, this.HEAD_RP_Y + yOffset, this.HEAD_RP_Z);
        this.MuzzleFemale[5].addBottomPlane(-2.0f + this.HEAD_CENTRE_X, 4.0f + this.HEAD_CENTRE_Y, -5.0f + this.HEAD_CENTRE_Z, 4, 0, 1, stretch);
        this.MuzzleFemale[5].setRotationPoint(this.HEAD_RP_X, this.HEAD_RP_Y + yOffset, this.HEAD_RP_Z);
        this.MuzzleFemale[6].addSidePlane(-2.0f + this.HEAD_CENTRE_X, 2.0f + this.HEAD_CENTRE_Y, -5.0f + this.HEAD_CENTRE_Z, 0, 2, 1, stretch);
        this.MuzzleFemale[6].setRotationPoint(this.HEAD_RP_X, this.HEAD_RP_Y + yOffset, this.HEAD_RP_Z);
        this.MuzzleFemale[7].addSidePlane(2.0f + this.HEAD_CENTRE_X, 2.0f + this.HEAD_CENTRE_Y, -5.0f + this.HEAD_CENTRE_Z, 0, 2, 1, stretch);
        this.MuzzleFemale[7].setRotationPoint(this.HEAD_RP_X, this.HEAD_RP_Y + yOffset, this.HEAD_RP_Z);
        this.MuzzleFemale[8].addSidePlane(-1.0f + this.HEAD_CENTRE_X, 1.0f + this.HEAD_CENTRE_Y, -5.0f + this.HEAD_CENTRE_Z, 0, 1, 1, stretch);
        this.MuzzleFemale[8].setRotationPoint(this.HEAD_RP_X, this.HEAD_RP_Y + yOffset, this.HEAD_RP_Z);
        this.MuzzleFemale[9].addSidePlane(1.0f + this.HEAD_CENTRE_X, 1.0f + this.HEAD_CENTRE_Y, -5.0f + this.HEAD_CENTRE_Z, 0, 1, 1, stretch);
        this.MuzzleFemale[9].setRotationPoint(this.HEAD_RP_X, this.HEAD_RP_Y + yOffset, this.HEAD_RP_Z);
        this.MuzzleMale[0].addBackPlane(-2.0f + this.HEAD_CENTRE_X, 1.0f + this.HEAD_CENTRE_Y, -5.0f + this.HEAD_CENTRE_Z, 4, 3, 0, stretch);
        this.MuzzleMale[0].setRotationPoint(this.HEAD_RP_X, this.HEAD_RP_Y + yOffset, this.HEAD_RP_Z);
        this.MuzzleMale[1].addTopPlane(-2.0f + this.HEAD_CENTRE_X, 1.0f + this.HEAD_CENTRE_Y, -5.0f + this.HEAD_CENTRE_Z, 4, 0, 1, stretch);
        this.MuzzleMale[1].setRotationPoint(this.HEAD_RP_X, this.HEAD_RP_Y + yOffset, this.HEAD_RP_Z);
        this.MuzzleMale[2].addBottomPlane(-2.0f + this.HEAD_CENTRE_X, 4.0f + this.HEAD_CENTRE_Y, -5.0f + this.HEAD_CENTRE_Z, 4, 0, 1, stretch);
        this.MuzzleMale[2].setRotationPoint(this.HEAD_RP_X, this.HEAD_RP_Y + yOffset, this.HEAD_RP_Z);
        this.MuzzleMale[3].addSidePlane(-2.0f + this.HEAD_CENTRE_X, 1.0f + this.HEAD_CENTRE_Y, -5.0f + this.HEAD_CENTRE_Z, 0, 3, 1, stretch);
        this.MuzzleMale[3].setRotationPoint(this.HEAD_RP_X, this.HEAD_RP_Y + yOffset, this.HEAD_RP_Z);
        this.MuzzleMale[4].addSidePlane(2.0f + this.HEAD_CENTRE_X, 1.0f + this.HEAD_CENTRE_Y, -5.0f + this.HEAD_CENTRE_Z, 0, 3, 1, stretch);
        this.MuzzleMale[4].setRotationPoint(this.HEAD_RP_X, this.HEAD_RP_Y + yOffset, this.HEAD_RP_Z);
    }

    protected void initBodyPositions(float yOffset, float stretch) {
        this.Body.addBox(-4.0f, 4.5f, -2.0f, 8, 8, 4, stretch - 0.2f);
        this.Body.setRotationPoint(this.HEAD_RP_X, this.HEAD_RP_Y + yOffset, this.HEAD_RP_Z);
        this.Bodypiece[0].addSidePlane(-3.6f + this.BODY_CENTRE_X, -3.5f + this.BODY_CENTRE_Y, -4.6f + this.BODY_CENTRE_Z, 0, 8, 8, stretch - 0.2f);
        this.Bodypiece[0].setRotationPoint(this.HEAD_RP_X, this.HEAD_RP_Y + yOffset, this.HEAD_RP_Z);
        this.Bodypiece[1].addSidePlane(4.0f + this.BODY_CENTRE_X, -3.5f + this.BODY_CENTRE_Y, -4.6f + this.BODY_CENTRE_Z, 0, 8, 8, stretch - 0.2f);
        this.Bodypiece[1].setRotationPoint(this.HEAD_RP_X, this.HEAD_RP_Y + yOffset, this.HEAD_RP_Z);
        this.Bodypiece[2].addTopPlane(-4.0f + this.BODY_CENTRE_X, -3.5f + this.BODY_CENTRE_Y, -4.6f + this.BODY_CENTRE_Z, 8, 0, 12, stretch - 0.2f);
        this.Bodypiece[2].setRotationPoint(this.HEAD_RP_X, this.HEAD_RP_Y + yOffset, this.HEAD_RP_Z);
        this.Bodypiece[3].addBottomPlane(-4.0f + this.BODY_CENTRE_X, 4.5f + this.BODY_CENTRE_Y, -1.2f + this.BODY_CENTRE_Z, 8, 0, 4, stretch - 0.2f);
        this.Bodypiece[3].setRotationPoint(this.HEAD_RP_X, this.HEAD_RP_Y + yOffset, this.HEAD_RP_Z);
        this.Bodypiece[4].addBottomPlane(-4.0f + this.BODY_CENTRE_X, 4.5f + this.BODY_CENTRE_Y, -4.6f + this.BODY_CENTRE_Z, 8, 0, 4, stretch - 0.2f);
        this.Bodypiece[4].setRotationPoint(this.HEAD_RP_X, this.HEAD_RP_Y + yOffset, this.HEAD_RP_Z);
        this.Bodypiece[5].addTopPlane(-1.0f + this.BODY_CENTRE_X, 2.0f + this.BODY_CENTRE_Y, 2.0f + this.BODY_CENTRE_Z, 2, 0, 6, stretch);
        this.Bodypiece[5].setRotationPoint(this.HEAD_RP_X, this.HEAD_RP_Y + yOffset, this.HEAD_RP_Z);
        this.Bodypiece[6].addBottomPlane(-1.0f + this.BODY_CENTRE_X, 4.0f + this.BODY_CENTRE_Y, 2.0f + this.BODY_CENTRE_Z, 2, 0, 6, stretch);
        this.Bodypiece[6].setRotationPoint(this.HEAD_RP_X, this.HEAD_RP_Y + yOffset, this.HEAD_RP_Z);
        this.Bodypiece[7].addSidePlane(-1.0f + this.BODY_CENTRE_X, 2.0f + this.BODY_CENTRE_Y, 2.0f + this.BODY_CENTRE_Z, 0, 2, 6, stretch);
        this.Bodypiece[7].setRotationPoint(this.HEAD_RP_X, this.HEAD_RP_Y + yOffset, this.HEAD_RP_Z);
        this.Bodypiece[8].addSidePlane(1.0f + this.BODY_CENTRE_X, 2.0f + this.BODY_CENTRE_Y, 2.0f + this.BODY_CENTRE_Z, 0, 2, 6, stretch);
        this.Bodypiece[8].setRotationPoint(this.HEAD_RP_X, this.HEAD_RP_Y + yOffset, this.HEAD_RP_Z);
        this.Bodypiece[9].addBackPlane(-1.0f + this.BODY_CENTRE_X, 2.0f + this.BODY_CENTRE_Y, 8.0f + this.BODY_CENTRE_Z, 2, 2, 0, stretch);
        this.Bodypiece[9].setRotationPoint(this.HEAD_RP_X, this.HEAD_RP_Y + yOffset, this.HEAD_RP_Z);
        this.Bodypiece[10].addBottomPlane(-4.0f + this.BODY_CENTRE_X, 4.15f + this.BODY_CENTRE_Y, 1.6f + this.BODY_CENTRE_Z, 8, 0, 4, stretch - 0.2f);
        this.Bodypiece[10].setRotationPoint(this.HEAD_RP_X, this.HEAD_RP_Y + yOffset, this.HEAD_RP_Z);
        this.BodypieceNeck[0].addBackPlane(-2.0f + this.BODY_CENTRE_X, -6.8f + this.BODY_CENTRE_Y, -8.8f + this.BODY_CENTRE_Z, 4, 4, 0, stretch);
        this.BodypieceNeck[0].setRotationPoint(this.HEAD_RP_X, this.HEAD_RP_Y + yOffset, this.HEAD_RP_Z);
        this.BodypieceNeck[1].addBackPlane(-2.0f + this.BODY_CENTRE_X, -6.8f + this.BODY_CENTRE_Y, -4.8f + this.BODY_CENTRE_Z, 4, 4, 0, stretch);
        this.BodypieceNeck[1].setRotationPoint(this.HEAD_RP_X, this.HEAD_RP_Y + yOffset, this.HEAD_RP_Z);
        this.BodypieceNeck[2].addSidePlane(-2.0f + this.BODY_CENTRE_X, -6.8f + this.BODY_CENTRE_Y, -8.8f + this.BODY_CENTRE_Z, 0, 4, 4, stretch);
        this.BodypieceNeck[2].setRotationPoint(this.HEAD_RP_X, this.HEAD_RP_Y + yOffset, this.HEAD_RP_Z);
        this.BodypieceNeck[3].addSidePlane(2.0f + this.BODY_CENTRE_X, -6.8f + this.BODY_CENTRE_Y, -8.8f + this.BODY_CENTRE_Z, 0, 4, 4, stretch);
        this.BodypieceNeck[3].setRotationPoint(this.HEAD_RP_X, this.HEAD_RP_Y + yOffset, this.HEAD_RP_Z);
        this.BodypieceNeck[0].rotateAngleX = this.NeckRotX;
        this.BodypieceNeck[1].rotateAngleX = this.NeckRotX;
        this.BodypieceNeck[2].rotateAngleX = this.NeckRotX;
        this.BodypieceNeck[3].rotateAngleX = this.NeckRotX;
    }

    protected void initLegPositions(float yOffset, float stretch) {
        this.RightArm.addBox(-2.0f + this.THIRDP_ARM_CENTRE_X, -6.0f + this.THIRDP_ARM_CENTRE_Y, -2.0f + this.THIRDP_ARM_CENTRE_Z, 4, 12, 4, stretch);
        this.RightArm.setRotationPoint(-3.0f, 8.0f + yOffset, 0.0f);
        this.RightShoulder.addBox(6.0f + this.THIRDP_ARM_CENTRE_X, -8.8f + this.THIRDP_ARM_CENTRE_Y, -1.0f + this.THIRDP_ARM_CENTRE_Z, 1, 3, 3, stretch);
        this.RightShoulder.setRotationPoint(-3.0f, 8.0f + yOffset, 0.0f);
        this.LeftArm.addBox(-2.0f + this.THIRDP_ARM_CENTRE_X, -6.0f + this.THIRDP_ARM_CENTRE_Y, -2.0f + this.THIRDP_ARM_CENTRE_Z, 4, 12, 4, stretch);
        this.LeftArm.setRotationPoint(3.0f, 8.0f + yOffset, 0.0f);
        this.LeftShoulder.addBox(-7.0f + this.THIRDP_ARM_CENTRE_X, -8.8f + this.THIRDP_ARM_CENTRE_Y, -1.0f + this.THIRDP_ARM_CENTRE_Z, 1, 3, 3, stretch);
        this.LeftShoulder.setRotationPoint(3.0f, 8.0f + yOffset, 0.0f);
        this.RightLeg.addBox(-2.0f + this.THIRDP_ARM_CENTRE_X, -4.0f + this.THIRDP_ARM_CENTRE_Y, -1.0f + this.THIRDP_ARM_CENTRE_Z, 4, 10, 4, stretch);
        this.RightLeg.setRotationPoint(-3.0f, 0.0f + yOffset, 0.0f);
        this.LeftLeg.addBox(-2.0f + this.THIRDP_ARM_CENTRE_X, -4.0f + this.THIRDP_ARM_CENTRE_Y, -1.0f + this.THIRDP_ARM_CENTRE_Z, 4, 10, 4, stretch);
        this.LeftLeg.setRotationPoint(3.0f, 0.0f + yOffset, 0.0f);
        this.RightLegJoint.addBox(-2.0f + this.THIRDP_ARM_CENTRE_X, -6.0f + this.THIRDP_ARM_CENTRE_Y, -2.0f + this.THIRDP_ARM_CENTRE_Z, 4, 2, 4, stretch);
        this.RightLegJoint.setRotationPoint(-3.0f, 0.0f + yOffset, 0.0f);
        this.LeftLegJoint.addBox(-2.0f + this.THIRDP_ARM_CENTRE_X, -6.0f + this.THIRDP_ARM_CENTRE_Y, -2.0f + this.THIRDP_ARM_CENTRE_Z, 4, 2, 4, stretch);
        this.LeftLegJoint.setRotationPoint(3.0f, 0.0f + yOffset, 0.0f);
        this.RightSubLeg[0].addBottomPlane(-2.0f + this.THIRDP_ARM_CENTRE_X, -3.99f + this.THIRDP_ARM_CENTRE_Y, -2.0f + this.THIRDP_ARM_CENTRE_Z, 4, 0, 1, stretch);
        this.RightSubLeg[0].setRotationPoint(-3.0f, 0.0f + yOffset, 0.0f);
        this.LeftSubLeg[0].addBottomPlane(-2.0f + this.THIRDP_ARM_CENTRE_X, -3.99f + this.THIRDP_ARM_CENTRE_Y, -2.0f + this.THIRDP_ARM_CENTRE_Z, 4, 0, 1, stretch);
        this.LeftSubLeg[0].setRotationPoint(3.0f, 0.0f + yOffset, 0.0f);
        this.RightSubLeg[1].addBottomPlane(-2.0f + this.THIRDP_ARM_CENTRE_X, -4.01f + this.THIRDP_ARM_CENTRE_Y, 2.0f + this.THIRDP_ARM_CENTRE_Z, 4, 0, 1, stretch);
        this.RightSubLeg[1].setRotationPoint(-3.0f, 0.0f + yOffset, 0.0f);
        this.LeftSubLeg[1].addBottomPlane(-2.0f + this.THIRDP_ARM_CENTRE_X, -4.01f + this.THIRDP_ARM_CENTRE_Y, 2.0f + this.THIRDP_ARM_CENTRE_Z, 4, 0, 1, stretch);
        this.LeftSubLeg[1].setRotationPoint(3.0f, 0.0f + yOffset, 0.0f);
        this.RightSubLeg[2].addBottomPlane(-2.0f + this.THIRDP_ARM_CENTRE_X, 6.0f + this.THIRDP_ARM_CENTRE_Y, -1.0f + this.THIRDP_ARM_CENTRE_Z, 4, 0, 4, stretch);
        this.RightSubLeg[2].setRotationPoint(-3.0f, 0.0f + yOffset, 0.0f);
        this.LeftSubLeg[2].addBottomPlane(-2.0f + this.THIRDP_ARM_CENTRE_X, 6.0f + this.THIRDP_ARM_CENTRE_Y, -1.0f + this.THIRDP_ARM_CENTRE_Z, 4, 0, 4, stretch);
        this.LeftSubLeg[2].setRotationPoint(3.0f, 0.0f + yOffset, 0.0f);
        this.SteveArm.addBox(-2.0f + this.FIRSTP_ARM_CENTRE_X, -6.0f + this.FIRSTP_ARM_CENTRE_Y, -2.0f + this.FIRSTP_ARM_CENTRE_Z, 4, 12, 4, stretch);
        this.SteveArm.setRotationPoint(-5.0f, 2.0f + yOffset, 0.0f);
        this.unicornarm.addBox(-2.0f + this.FIRSTP_ARM_CENTRE_X, -6.0f + this.FIRSTP_ARM_CENTRE_Y, -2.0f + this.FIRSTP_ARM_CENTRE_Z, 4, 12, 4, stretch);
        this.unicornarm.setRotationPoint(-5.0f, 2.0f + yOffset, 0.0f);
    }

    protected void initPlotPositions(float yOffset, float stretch) {
        this.LeftPlotPiece[0].addTopPlane(0.0f + this.BODY_CENTRE_X, -4.0f + this.BODY_CENTRE_Y, 2.0f + this.BODY_CENTRE_Z, 4, 0, 6, stretch);
        this.LeftPlotPiece[0].setRotationPoint(0.0f, 0.0f + yOffset, 0.0f);
        this.LeftPlotPiece[1].addSidePlane(4.0f + this.BODY_CENTRE_X, -4.0f + this.BODY_CENTRE_Y, 2.0f + this.BODY_CENTRE_Z, 0, 8, 2, stretch);
        this.LeftPlotPiece[1].setRotationPoint(0.0f, 0.0f + yOffset, 0.0f);
        this.LeftPlotPiece[2].addBackPlane(3.0f + this.BODY_CENTRE_X, -4.0f + this.BODY_CENTRE_Y, 2.0f + this.BODY_CENTRE_Z, 1, 8, 0, stretch);
        this.LeftPlotPiece[2].setRotationPoint(0.0f, 0.0f + yOffset, 0.0f);
        this.LeftPlotPiece[3].addBackPlane(0.0f + this.BODY_CENTRE_X, -4.0f + this.BODY_CENTRE_Y, 2.0f + this.BODY_CENTRE_Z, 4, 1, 0, stretch);
        this.LeftPlotPiece[3].setRotationPoint(0.0f, 0.0f + yOffset, 0.0f);
        this.LeftPlotPiece[4].addSidePlane(4.0f + this.BODY_CENTRE_X, -4.0f + this.BODY_CENTRE_Y, 2.0f + this.BODY_CENTRE_Z, 8, 0, 4, stretch);
        this.LeftPlotPiece[4].setRotationPoint(4.0f, 0.0f + yOffset, 0.0f);
        this.LeftPlotPiece[5].addSidePlane(4.0f + this.BODY_CENTRE_X, -4.0f + this.BODY_CENTRE_Y, 4.0f + this.BODY_CENTRE_Z, 0, 8, 4, stretch);
        this.LeftPlotPiece[5].setRotationPoint(0.0f, 0.0f + yOffset, 0.0f);
        this.LeftPlotPiece[6].addBackPlane(0.0f + this.BODY_CENTRE_X, -4.0f + this.BODY_CENTRE_Y, 8.0f + this.BODY_CENTRE_Z, 4, 4, 0, stretch);
        this.LeftPlotPiece[6].setRotationPoint(0.0f, 0.0f + yOffset, 0.0f);
        this.LeftPlotPiece[7].addBackPlane(0.0f + this.BODY_CENTRE_X, 0.0f + this.BODY_CENTRE_Y, 8.0f + this.BODY_CENTRE_Z, 4, 4, 0, stretch);
        this.LeftPlotPiece[7].setRotationPoint(0.0f, 0.0f + yOffset, 0.0f);
        this.LeftPlotPiece[8].addBottomPlane(0.0f + this.BODY_CENTRE_X, 4.0f + this.BODY_CENTRE_Y, 2.0f + this.BODY_CENTRE_Z, 4, 0, 2, stretch);
        this.LeftPlotPiece[8].setRotationPoint(0.0f, 0.0f + yOffset, 0.0f);
        this.LeftPlotPiece[9].addBottomPlane(0.0f + this.BODY_CENTRE_X, 4.0f + this.BODY_CENTRE_Y, 4.0f + this.BODY_CENTRE_Z, 4, 0, 4, stretch);
        this.LeftPlotPiece[9].setRotationPoint(0.0f, 0.0f + yOffset, 0.0f);
        this.LeftPlotPiece[10].addSidePlane(0.0f + this.BODY_CENTRE_X, -4.0f + this.BODY_CENTRE_Y, 4.0f + this.BODY_CENTRE_Z, 0, 4, 4, stretch);
        this.LeftPlotPiece[10].setRotationPoint(0.0f, 0.0f + yOffset, 0.0f);
        this.LeftPlotPiece[11].addSidePlane(0.0f + this.BODY_CENTRE_X, 0.0f + this.BODY_CENTRE_Y, 4.0f + this.BODY_CENTRE_Z, 0, 4, 4, stretch);
        this.LeftPlotPiece[11].setRotationPoint(0.0f, 0.0f + yOffset, 0.0f);
        this.RightPlotPiece[0].addTopPlane(-4.0f + this.BODY_CENTRE_X, -4.0f + this.BODY_CENTRE_Y, 2.0f + this.BODY_CENTRE_Z, 4, 0, 6, stretch);
        this.RightPlotPiece[0].setRotationPoint(0.0f, 0.0f + yOffset, 0.0f);
        this.RightPlotPiece[1].addSidePlane(-4.0f + this.BODY_CENTRE_X, -4.0f + this.BODY_CENTRE_Y, 2.0f + this.BODY_CENTRE_Z, 0, 8, 2, stretch);
        this.RightPlotPiece[1].setRotationPoint(0.0f, 0.0f + yOffset, 0.0f);
        this.RightPlotPiece[2].addBackPlane(-4.0f + this.BODY_CENTRE_X, -4.0f + this.BODY_CENTRE_Y, 2.0f + this.BODY_CENTRE_Z, 1, 8, 0, stretch);
        this.RightPlotPiece[2].setRotationPoint(0.0f, 0.0f + yOffset, 0.0f);
        this.RightPlotPiece[3].addBackPlane(-4.0f + this.BODY_CENTRE_X, -4.0f + this.BODY_CENTRE_Y, 2.0f + this.BODY_CENTRE_Z, 4, 1, 0, stretch);
        this.RightPlotPiece[3].setRotationPoint(0.0f, 0.0f + yOffset, 0.0f);
        this.RightPlotPiece[4].addSidePlane(-4.0f + this.BODY_CENTRE_X, 0.0f + this.BODY_CENTRE_Y, 0.0f + this.BODY_CENTRE_Z, 8, 0, 4, stretch);
        this.RightPlotPiece[4].setRotationPoint(0.0f, 0.0f + yOffset, 0.0f);
        this.RightPlotPiece[5].addSidePlane(-4.0f + this.BODY_CENTRE_X, -4.0f + this.BODY_CENTRE_Y, 4.0f + this.BODY_CENTRE_Z, 0, 8, 4, stretch);
        this.RightPlotPiece[5].setRotationPoint(0.0f, 0.0f + yOffset, 0.0f);
        this.RightPlotPiece[6].addBackPlane(-4.0f + this.BODY_CENTRE_X, -4.0f + this.BODY_CENTRE_Y, 8.0f + this.BODY_CENTRE_Z, 4, 4, 0, stretch);
        this.RightPlotPiece[6].setRotationPoint(0.0f, 0.0f + yOffset, 0.0f);
        this.RightPlotPiece[7].addBackPlane(-4.0f + this.BODY_CENTRE_X, 0.0f + this.BODY_CENTRE_Y, 8.0f + this.BODY_CENTRE_Z, 4, 4, 0, stretch);
        this.RightPlotPiece[7].setRotationPoint(0.0f, 0.0f + yOffset, 0.0f);
        this.RightPlotPiece[8].addBottomPlane(-4.0f + this.BODY_CENTRE_X, 4.0f + this.BODY_CENTRE_Y, 2.0f + this.BODY_CENTRE_Z, 4, 0, 2, stretch);
        this.RightPlotPiece[8].setRotationPoint(0.0f, 0.0f + yOffset, 0.0f);
        this.RightPlotPiece[9].addBottomPlane(-4.0f + this.BODY_CENTRE_X, 4.0f + this.BODY_CENTRE_Y, 4.0f + this.BODY_CENTRE_Z, 4, 0, 4, stretch);
        this.RightPlotPiece[9].setRotationPoint(0.0f, 0.0f + yOffset, 0.0f);
        this.RightPlotPiece[10].addSidePlane(0.0f + this.BODY_CENTRE_X, -4.0f + this.BODY_CENTRE_Y, 4.0f + this.BODY_CENTRE_Z, 0, 4, 4, stretch);
        this.RightPlotPiece[10].setRotationPoint(0.0f, 0.0f + yOffset, 0.0f);
        this.RightPlotPiece[11].addSidePlane(0.0f + this.BODY_CENTRE_X, 0.0f + this.BODY_CENTRE_Y, 4.0f + this.BODY_CENTRE_Z, 0, 4, 4, stretch);
        this.RightPlotPiece[11].setRotationPoint(0.0f, 0.0f + yOffset, 0.0f);
    }


    protected void initTailPositions(float yOffset, float stretch) {
        this.Tail[0].addTopPlane(-2.0f, 1.0f, 2.0f, 4, 0, 4, stretch);
        this.Tail[0].setRotationPoint(this.TAIL_RP_X, this.TAIL_RP_Y + yOffset, this.TAIL_RP_Z);
        this.Tail[1].addSidePlane(-2.0f, 1.0f, 2.0f, 0, 4, 4, stretch);
        this.Tail[1].setRotationPoint(this.TAIL_RP_X, this.TAIL_RP_Y + yOffset, this.TAIL_RP_Z);
        this.Tail[2].addBackPlane(-2.0f, 1.0f, 2.0f, 4, 4, 0, stretch);
        this.Tail[2].setRotationPoint(this.TAIL_RP_X, this.TAIL_RP_Y + yOffset, this.TAIL_RP_Z);
        this.Tail[3].addSidePlane(2.0f, 1.0f, 2.0f, 0, 4, 4, stretch);
        this.Tail[3].setRotationPoint(this.TAIL_RP_X, this.TAIL_RP_Y + yOffset, this.TAIL_RP_Z);
        this.Tail[4].addBackPlane(-2.0f, 1.0f, 6.0f, 4, 4, 0, stretch);
        this.Tail[4].setRotationPoint(this.TAIL_RP_X, this.TAIL_RP_Y + yOffset, this.TAIL_RP_Z);
        this.Tail[5].addTopPlane(-2.0f, 5.0f, 2.0f, 4, 0, 4, stretch);
        this.Tail[5].setRotationPoint(this.TAIL_RP_X, this.TAIL_RP_Y + yOffset, this.TAIL_RP_Z);
        this.Tail[6].addSidePlane(-2.0f, 5.0f, 2.0f, 0, 4, 4, stretch);
        this.Tail[6].setRotationPoint(this.TAIL_RP_X, this.TAIL_RP_Y + yOffset, this.TAIL_RP_Z);
        this.Tail[7].addBackPlane(-2.0f, 5.0f, 2.0f, 4, 4, 0, stretch);
        this.Tail[7].setRotationPoint(this.TAIL_RP_X, this.TAIL_RP_Y + yOffset, this.TAIL_RP_Z);
        this.Tail[8].addSidePlane(2.0f, 5.0f, 2.0f, 0, 4, 4, stretch);
        this.Tail[8].setRotationPoint(this.TAIL_RP_X, this.TAIL_RP_Y + yOffset, this.TAIL_RP_Z);
        this.Tail[9].addBackPlane(-2.0f, 5.0f, 6.0f, 4, 4, 0, stretch);
        this.Tail[9].setRotationPoint(this.TAIL_RP_X, this.TAIL_RP_Y + yOffset, this.TAIL_RP_Z);
        this.Tail[10].addTopPlane(-2.0f, 9.0f, 2.0f, 4, 0, 4, stretch);
        this.Tail[10].setRotationPoint(this.TAIL_RP_X, this.TAIL_RP_Y + yOffset, this.TAIL_RP_Z);
        this.Tail[11].addSidePlane(-2.0f, 9.0f, 2.0f, 0, 4, 4, stretch);
        this.Tail[11].setRotationPoint(this.TAIL_RP_X, this.TAIL_RP_Y + yOffset, this.TAIL_RP_Z);
        this.Tail[12].addBackPlane(-2.0f, 9.0f, 2.0f, 4, 4, 0, stretch);
        this.Tail[12].setRotationPoint(this.TAIL_RP_X, this.TAIL_RP_Y + yOffset, this.TAIL_RP_Z);
        this.Tail[13].addSidePlane(2.0f, 9.0f, 2.0f, 0, 4, 4, stretch);
        this.Tail[13].setRotationPoint(this.TAIL_RP_X, this.TAIL_RP_Y + yOffset, this.TAIL_RP_Z);
        this.Tail[14].addBackPlane(-2.0f, 9.0f, 6.0f, 4, 4, 0, stretch);
        this.Tail[14].setRotationPoint(this.TAIL_RP_X, this.TAIL_RP_Y + yOffset, this.TAIL_RP_Z);
        this.Tail[15].addTopPlane(-2.0f, 13.0f, 2.0f, 4, 0, 4, stretch);
        this.Tail[15].setRotationPoint(this.TAIL_RP_X, this.TAIL_RP_Y + yOffset, this.TAIL_RP_Z);
        this.Tail[16].addSidePlane(-2.0f, 13.0f, 2.0f, 0, 4, 4, stretch);
        this.Tail[16].setRotationPoint(this.TAIL_RP_X, this.TAIL_RP_Y + yOffset, this.TAIL_RP_Z);
        this.Tail[17].addBackPlane(-2.0f, 13.0f, 2.0f, 4, 4, 0, stretch);
        this.Tail[17].setRotationPoint(this.TAIL_RP_X, this.TAIL_RP_Y + yOffset, this.TAIL_RP_Z);
        this.Tail[18].addSidePlane(2.0f, 13.0f, 2.0f, 0, 4, 4, stretch);
        this.Tail[18].setRotationPoint(this.TAIL_RP_X, this.TAIL_RP_Y + yOffset, this.TAIL_RP_Z);
        this.Tail[19].addBackPlane(-2.0f, 13.0f, 6.0f, 4, 4, 0, stretch);
        this.Tail[19].setRotationPoint(this.TAIL_RP_X, this.TAIL_RP_Y + yOffset, this.TAIL_RP_Z);
        this.Tail[20].addTopPlane(-2.0f, 17.0f, 2.0f, 4, 0, 4, stretch);
        this.Tail[20].setRotationPoint(this.TAIL_RP_X, this.TAIL_RP_Y + yOffset, this.TAIL_RP_Z);
    }

    protected void initWingPositions(float yOffset, float stretch) {
        this.LeftWing[0].addBox(4.0f, 5.0f, 2.0f, 2, 6, 2, stretch);
        this.LeftWing[0].setRotationPoint(this.HEAD_RP_X, this.WING_FOLDED_RP_Y + yOffset, this.WING_FOLDED_RP_Z);
        this.LeftWing[0].rotateAngleX = this.ROTATE_90;
        this.LeftWing[1].addBox(4.0f, 5.0f, 4.0f, 2, 8, 2, stretch);
        this.LeftWing[1].setRotationPoint(this.HEAD_RP_X, this.WING_FOLDED_RP_Y + yOffset, this.WING_FOLDED_RP_Z);
        this.LeftWing[2].addBox(4.0f, 5.0f, 6.0f, 2, 6, 2, stretch);
        this.LeftWing[2].setRotationPoint(this.HEAD_RP_X, this.WING_FOLDED_RP_Y + yOffset, this.WING_FOLDED_RP_Z);
        this.RightWing[0].addBox(-6.0f, 5.0f, 2.0f, 2, 6, 2, stretch);
        this.RightWing[0].setRotationPoint(this.HEAD_RP_X, this.WING_FOLDED_RP_Y + yOffset, this.WING_FOLDED_RP_Z);
        this.RightWing[1].addBox(-6.0f, 5.0f, 4.0f, 2, 8, 2, stretch);
        this.RightWing[1].setRotationPoint(this.HEAD_RP_X, this.WING_FOLDED_RP_Y + yOffset, this.WING_FOLDED_RP_Z);
        this.RightWing[2].addBox(-6.0f, 5.0f, 6.0f, 2, 6, 2, stretch);
        this.RightWing[2].setRotationPoint(this.HEAD_RP_X, this.WING_FOLDED_RP_Y + yOffset, this.WING_FOLDED_RP_Z);
        this.LeftWingExt[0].addBox(0.0f, 0.0f, 0.0f, 1, 8, 2, stretch + 0.1f);
        this.LeftWingExt[0].setRotationPoint(this.LEFT_WING_EXT_RP_X, this.LEFT_WING_EXT_RP_Y + yOffset, this.LEFT_WING_EXT_RP_Z);
        this.LeftWingExt[1].addBox(0.0f, 8.0f, 0.0f, 1, 6, 2, stretch + 0.1f);
        this.LeftWingExt[1].setRotationPoint(this.LEFT_WING_EXT_RP_X, this.LEFT_WING_EXT_RP_Y + yOffset, this.LEFT_WING_EXT_RP_Z);
        this.LeftWingExt[2].addBox(0.0f, -1.2f, -0.2f, 1, 8, 2, stretch - 0.2f);
        this.LeftWingExt[2].setRotationPoint(this.LEFT_WING_EXT_RP_X, this.LEFT_WING_EXT_RP_Y + yOffset, this.LEFT_WING_EXT_RP_Z);
        this.LeftWingExt[3].addBox(0.0f, 1.8f, 1.3f, 1, 8, 2, stretch - 0.1f);
        this.LeftWingExt[3].setRotationPoint(this.LEFT_WING_EXT_RP_X, this.LEFT_WING_EXT_RP_Y + yOffset, this.LEFT_WING_EXT_RP_Z);
        this.LeftWingExt[4].addBox(0.0f, 5.0f, 2.0f, 1, 8, 2, stretch);
        this.LeftWingExt[4].setRotationPoint(this.LEFT_WING_EXT_RP_X, this.LEFT_WING_EXT_RP_Y + yOffset, this.LEFT_WING_EXT_RP_Z);
        this.LeftWingExt[5].addBox(0.0f, 0.0f, -0.2f, 1, 6, 2, stretch + 0.3f);
        this.LeftWingExt[5].setRotationPoint(this.LEFT_WING_EXT_RP_X, this.LEFT_WING_EXT_RP_Y + yOffset, this.LEFT_WING_EXT_RP_Z);
        this.LeftWingExt[6].addBox(0.0f, 0.0f, 0.2f, 1, 3, 2, stretch + 0.2f);
        this.LeftWingExt[6].setRotationPoint(this.LEFT_WING_EXT_RP_X, this.LEFT_WING_EXT_RP_Y + yOffset, this.LEFT_WING_EXT_RP_Z);
        this.RightWingExt[0].addBox(0.0f, 0.0f, 0.0f, 1, 8, 2, stretch + 0.1f);
        this.RightWingExt[0].setRotationPoint(this.RIGHT_WING_EXT_RP_X, this.RIGHT_WING_EXT_RP_Y + yOffset, this.RIGHT_WING_EXT_RP_Z);
        this.RightWingExt[1].addBox(0.0f, 8.0f, 0.0f, 1, 6, 2, stretch + 0.1f);
        this.RightWingExt[1].setRotationPoint(this.RIGHT_WING_EXT_RP_X, this.RIGHT_WING_EXT_RP_Y + yOffset, this.RIGHT_WING_EXT_RP_Z);
        this.RightWingExt[2].addBox(0.0f, -1.2f, -0.2f, 1, 8, 2, stretch - 0.2f);
        this.RightWingExt[2].setRotationPoint(this.RIGHT_WING_EXT_RP_X, this.RIGHT_WING_EXT_RP_Y + yOffset, this.RIGHT_WING_EXT_RP_Z);
        this.RightWingExt[3].addBox(0.0f, 1.8f, 1.3f, 1, 8, 2, stretch - 0.1f);
        this.RightWingExt[3].setRotationPoint(this.RIGHT_WING_EXT_RP_X, this.RIGHT_WING_EXT_RP_Y + yOffset, this.RIGHT_WING_EXT_RP_Z);
        this.RightWingExt[4].addBox(0.0f, 5.0f, 2.0f, 1, 8, 2, stretch);
        this.RightWingExt[4].setRotationPoint(this.RIGHT_WING_EXT_RP_X, this.RIGHT_WING_EXT_RP_Y + yOffset, this.RIGHT_WING_EXT_RP_Z);
        this.RightWingExt[5].addBox(0.0f, 0.0f, -0.2f, 1, 6, 2, stretch + 0.3f);
        this.RightWingExt[5].setRotationPoint(this.RIGHT_WING_EXT_RP_X, this.RIGHT_WING_EXT_RP_Y + yOffset, this.RIGHT_WING_EXT_RP_Z);
        this.RightWingExt[6].addBox(0.0f, 0.0f, 0.2f, 1, 3, 2, stretch + 0.2f);
        this.RightWingExt[6].setRotationPoint(this.RIGHT_WING_EXT_RP_X, this.RIGHT_WING_EXT_RP_Y + yOffset, this.RIGHT_WING_EXT_RP_Z);
    }

    protected void initRainbowTailPositions(float yOffset, float stretch) {
        int var3;
        for (var3 = 0; var3 < 10; ++var3) {
            this.RainbowTail[var3].addSidePlane(this.BODY_CENTRE_X, 3.0f + this.BODY_CENTRE_Y, this.BODY_CENTRE_Z + (float) var3, 0, 1, 1, stretch);
            this.RainbowTail[var3].setRotationPoint(0.0f, 0.0f + yOffset, 10.0f + this.BODY_CENTRE_Z);
        }
        for (var3 = 10; var3 < 20; ++var3) {
            this.RainbowTail[var3].addSidePlane(this.BODY_CENTRE_X, 2.0f + this.BODY_CENTRE_Y, this.BODY_CENTRE_Z + (float) (var3 - 10), 0, 1, 1, stretch);
            this.RainbowTail[var3].setRotationPoint(0.0f, 0.0f + yOffset, 10.0f + this.BODY_CENTRE_Z);
        }
        for (var3 = 20; var3 < 30; ++var3) {
            this.RainbowTail[var3].addSidePlane(this.BODY_CENTRE_X, 1.0f + this.BODY_CENTRE_Y, this.BODY_CENTRE_Z + (float) (var3 - 20), 0, 1, 1, stretch);
            this.RainbowTail[var3].setRotationPoint(0.0f, 0.0f + yOffset, 10.0f + this.BODY_CENTRE_Z);
        }
        for (var3 = 30; var3 < 40; ++var3) {
            this.RainbowTail[var3].addSidePlane(this.BODY_CENTRE_X, 0.0f + this.BODY_CENTRE_Y, this.BODY_CENTRE_Z + (float) (var3 - 30), 0, 1, 1, stretch);
            this.RainbowTail[var3].setRotationPoint(0.0f, 0.0f + yOffset, 10.0f + this.BODY_CENTRE_Z);
        }
        for (var3 = 40; var3 < 50; ++var3) {
            this.RainbowTail[var3].addSidePlane(this.BODY_CENTRE_X, -1.0f + this.BODY_CENTRE_Y, this.BODY_CENTRE_Z + (float) (var3 - 40), 0, 1, 1, stretch);
            this.RainbowTail[var3].setRotationPoint(0.0f, 0.0f + yOffset, 10.0f + this.BODY_CENTRE_Z);
        }
        for (var3 = 50; var3 < 60; ++var3) {
            this.RainbowTail[var3].addSidePlane(this.BODY_CENTRE_X, -2.0f + this.BODY_CENTRE_Y, this.BODY_CENTRE_Z + (float) (var3 - 50), 0, 1, 1, stretch);
            this.RainbowTail[var3].setRotationPoint(0.0f, 0.0f + yOffset, 10.0f + this.BODY_CENTRE_Z);
        }
        for (var3 = 60; var3 < 70; ++var3) {
            this.RainbowTail[var3].addSidePlane(this.BODY_CENTRE_X, -3.0f + this.BODY_CENTRE_Y, this.BODY_CENTRE_Z + (float) (var3 - 60), 0, 1, 1, stretch);
            this.RainbowTail[var3].setRotationPoint(0.0f, 0.0f + yOffset, 10.0f + this.BODY_CENTRE_Z);
        }
    }

    @Override
    public void specials(RenderManager rendermanager, EntityLivingBase entity) {
        if (!this.isSleeping) {
            if (this.isUnicorn && this.glowColor != 0) {
                if (this.o) {
                    this.renderDrop(rendermanager, entity, this.unicornarm, 1.0f, 0.15f, 0.9375f, 0.0625f);
                } else if (this.size == 0) {
                    this.renderDrop(rendermanager, entity, this.unicornarm, 1.0f, 0.35f, 0.5375f, -0.8f);
                } else {
                    this.renderDrop(rendermanager, entity, this.unicornarm, 1.0f, 0.35f, 0.5375f, -0.45f);
                }
            } else if (this.size == 0) {
                this.renderDrop(rendermanager, entity, this.RightArm, 1.0f, 0.08f, 0.8375f, 0.0625f);
            } else {
                this.renderDrop(rendermanager, entity, this.RightArm, 1.0f, -0.0625f, 0.8375f, 0.0625f);
            }
        }
        if (this.size == 0) {
            GL11.glPushMatrix();
            GL11.glTranslatef(0.0f, 0.76f, 0.0f);
            GL11.glScalef(0.9f, 0.9f, 0.9f);
            this.renderPumpkin(rendermanager, entity, this.head, 0.725f, 0.0f, -0.08f, -0.15f);
            this.renderSkull(rendermanager, entity, this.head, 0.725f, 0.0f, -0.185f, 0.103f);
            GL11.glPopMatrix();
        } else if (this.size == 2) {
            GL11.glPushMatrix();
            GL11.glTranslatef(0.0f, -0.17f, -0.04f);
            if (this.isSleeping) {
                GL11.glTranslatef(0.0f, 0.0f, -0.1f);
            }
            this.renderPumpkin(rendermanager, entity, this.head, 0.725f, 0.0f, -0.08f, -0.15f);
            this.renderSkull(rendermanager, entity, this.head, 0.725f, 0.0f, -0.185f, 0.103f);
            GL11.glPopMatrix();
        } else if (this.size == 3) {
            GL11.glPushMatrix();
            GL11.glTranslatef(0.0f, -0.17f, -0.02f);
            this.renderPumpkin(rendermanager, entity, this.head, 0.725f, 0.0f, -0.08f, -0.15f);
            this.renderSkull(rendermanager, entity, this.head, 0.725f, 0.0f, -0.185f, 0.103f);
            GL11.glPopMatrix();
        } else {
            this.renderPumpkin(rendermanager, entity, this.head, 0.725f, 0.0f, -0.08f, -0.15f);
            this.renderSkull(rendermanager, entity, this.head, 0.725f, 0.0f, -0.185f, 0.103f);
        }
    }

    @Override
    public void renderCloak(EntityPlayer player, float par2) {
       /* GL11.glPushMatrix();
        GL11.glTranslatef(0.0f, 0.24f, 0.0f);
        if (this.size == 0) {
            GL11.glTranslatef(0.0f, 0.67f, -0.04f);
            GL11.glScalef(0.6f, 0.6f, 0.6f);
        } else if (this.size == 2) {
            GL11.glTranslatef(0.0f, -0.14f, -0.1f);
            GL11.glScalef(1.15f, 1.2f, 1.2f);
            if (this.issneak && !this.isFlying) {
                GL11.glTranslatef(0.0f, 0.03f, 0.0f);
            }
        } else if (this.size == 3) {
            GL11.glTranslatef(0.0f, -0.09f, 0.0f);
            GL11.glScalef(1.0f, 1.0f, 1.0f);
            if (this.issneak && !this.isFlying) {
                GL11.glTranslatef(0.0f, 0.03f, 0.0f);
            }
        }
        if (this.issneak && !this.isFlying) {
            GL11.glTranslatef(0.0f, 0.4f, -0.12f);
        }
        double d = player.field_71091_bM + (player.field_71094_bP - player.field_71091_bM) * (double)par2 - (player.prevPosX + (player.posX - player.prevPosX) * (double)par2);
        double d1 = player.field_71096_bN + (player.field_71095_bQ - player.field_71096_bN) * (double)par2 - (player.prevPosY + (player.posY - player.prevPosY) * (double)par2);
        double d2 = player.field_71097_bO + (player.field_71085_bR - player.field_71097_bO) * (double)par2 - (player.prevPosZ + (player.posZ - player.prevPosZ) * (double)par2);
        float f10 = player.prevRenderYawOffset + (player.renderYawOffset - player.prevRenderYawOffset) * par2;
        double d3 = MathHelper.sin(f10 * Pi / 180.0f);
        double d4 = - MathHelper.cos(f10 * Pi / 180.0f);
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
        f12 += MathHelper.sin((player.prevDistanceWalkedModified + (player.distanceWalkedModified - player.prevDistanceWalkedModified) * par2) * 6.0f) * 32.0f * f15;
        if (player.isSneaking()) {
            f12 += 25.0f;
        }
        GL11.glRotatef(2.0f + f13 / 12.0f + f12, 1.0f, 0.0f, 0.0f);
        GL11.glRotatef(f14 / 2.0f, 0.0f, 0.0f, 1.0f);
        GL11.glRotatef((- f14) / 2.0f, 0.0f, 1.0f, 0.0f);
        GL11.glRotatef(180.0f, 0.0f, 0.0f, 1.0f);
        GL11.glRotatef(90.0f, 1.0f, 0.0f, 0.0f);
        this.cloak.render(0.0625f);
        GL11.glPopMatrix();*/
    }

    @Override
    public void renderStaticCloak(EntityLiving player, float par2) {
       /* GL11.glPushMatrix();
        GL11.glTranslatef(0.0f, 0.24f, 0.0f);
        if (this.size == 0) {
            GL11.glTranslatef(0.0f, 0.67f, -0.04f);
            GL11.glScalef(0.6f, 0.6f, 0.6f);
        } else if (this.size == 2) {
            GL11.glTranslatef(0.0f, -0.14f, -0.1f);
            GL11.glScalef(1.15f, 1.2f, 1.2f);
            if (this.issneak && !this.isFlying) {
                GL11.glTranslatef(0.0f, 0.03f, 0.0f);
            }
        } else if (this.size == 3) {
            GL11.glTranslatef(0.0f, -0.09f, 0.0f);
            GL11.glScalef(1.0f, 1.0f, 1.0f);
            if (this.issneak && !this.isFlying) {
                GL11.glTranslatef(0.0f, 0.03f, 0.0f);
            }
        }
        if (this.issneak && !this.isFlying) {
            GL11.glTranslatef(0.0f, 0.4f, -0.12f);
        }
        GL11.glRotatef(3.0f, 1.0f, 0.0f, 0.0f);
        GL11.glRotatef(2.0f, 0.0f, 1.0f, 0.0f);
        GL11.glRotatef(180.0f, 0.0f, 0.0f, 1.0f);
        GL11.glRotatef(90.0f, 1.0f, 0.0f, 0.0f);
        this.cloak.render(0.0625f);
        GL11.glPopMatrix();*/
    }

    protected void compressWings() {
        this.CompressiveLeftWing = new CompressiveRendering(this);
        this.CompressiveRightWing = new CompressiveRendering(this);
        this.CompressiveLeftWing.addCompressed(this.LeftWing[0]);
        this.CompressiveLeftWing.addCompressed(this.LeftWing[1]);
        this.CompressiveLeftWing.addCompressed(this.LeftWing[2]);
        this.CompressiveRightWing.addCompressed(this.RightWing[0]);
        this.CompressiveRightWing.addCompressed(this.RightWing[1]);
        this.CompressiveRightWing.addCompressed(this.RightWing[2]);
        this.CompressiveLeftWing.addExpanded(this.LeftWingExt[0]);
        this.CompressiveLeftWing.addExpanded(this.LeftWingExt[1]);
        this.CompressiveLeftWing.addExpanded(this.LeftWingExt[2]);
        this.CompressiveLeftWing.addExpanded(this.LeftWingExt[3]);
        this.CompressiveLeftWing.addExpanded(this.LeftWingExt[4]);
        this.CompressiveLeftWing.addExpanded(this.LeftWingExt[5]);
        this.CompressiveLeftWing.addExpanded(this.LeftWingExt[6]);
        this.CompressiveRightWing.addExpanded(this.RightWingExt[0]);
        this.CompressiveRightWing.addExpanded(this.RightWingExt[1]);
        this.CompressiveRightWing.addExpanded(this.RightWingExt[2]);
        this.CompressiveRightWing.addExpanded(this.RightWingExt[3]);
        this.CompressiveRightWing.addExpanded(this.RightWingExt[4]);
        this.CompressiveRightWing.addExpanded(this.RightWingExt[5]);
        this.CompressiveRightWing.addExpanded(this.RightWingExt[6]);
        this.CompressiveLeftWing.setChance(2);
        this.CompressiveRightWing.setChance(2);
    }

    public ModelRenderer getRandomModelBox(Random par1Random) {
        Object part = this.boxList.get(par1Random.nextInt(this.boxList.size()));
        if (part instanceof ModelRenderer) {
            return (ModelRenderer) part;
        }
        return ((CompressiveRendering) part).getARenderer(par1Random);
    }

    public void setExtendingWings(boolean isCompressed) {
        this.CompressiveLeftWing.setIsCompressed(isCompressed);
        this.CompressiveRightWing.setIsCompressed(isCompressed);
    }

    public void setHasWings_Compression(boolean pegasus) {
        if (pegasus) {
            this.CompressiveLeftWing.init_Safe();
            this.CompressiveRightWing.init_Safe();
        } else {
            this.CompressiveLeftWing.deInit_Safe();
            this.CompressiveRightWing.deInit_Safe();
        }
    }
}

