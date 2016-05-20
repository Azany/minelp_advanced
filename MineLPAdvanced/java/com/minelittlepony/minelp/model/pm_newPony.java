
package com.minelittlepony.minelp.model;

import com.minelittlepony.minelp.renderer.HornGlowRenderer;
import com.minelittlepony.minelp.renderer.PlaneRenderer;
import net.minecraft.client.model.ModelRenderer;

public class pm_newPony
        extends pm_newPonyAdv {
    public pm_newPony(String texture) {
        super(texture);
    }

    @Override
    protected void initHeadTextures() {
        this.cloak = new ModelRenderer(this, 0, 0);
        this.head = new ModelRenderer(this, 0, 0);
        this.headpiece[0] = new ModelRenderer(this, 12, 16);
        this.headpiece[1] = new ModelRenderer(this, 12, 16);
        this.headpiece[1].mirror = true;
        this.headpiece[2] = new ModelRenderer(this, 56, 0);
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


        //!Kirin
        for(int i = 0; i<this.KirinHorns.length; ++i)
            this.KirinHorns[i] = new PlaneRenderer(this,56,24);

        for(int i = 0; i<this.KirinMoustache.length; ++i) {
            this.KirinMoustache[i] = new PlaneRenderer(this, 38, 0);
            if(i>2)
                this.KirinMoustache[i].mirror=true;
        }


    }

    @Override
    protected void initBodyTextures() {
        this.Body = new ModelRenderer(this, 16, 16);
        this.Bodypiece[0] = new PlaneRenderer(this, 24, 0);
        this.Bodypiece[1] = new PlaneRenderer(this, 24, 0);
        this.Bodypiece[2] = new PlaneRenderer(this, 32, 20);
        this.Bodypiece[2].mirrorxy = true;
        this.Bodypiece[3] = new PlaneRenderer(this, 24, 28);
        this.Bodypiece[4] = new PlaneRenderer(this, 16, 28);
        this.Bodypiece[5] = new PlaneRenderer(this, 32, 0);
        this.Bodypiece[6] = new PlaneRenderer(this, 32, 0);
        this.Bodypiece[7] = new PlaneRenderer(this, 32, 0);
        this.Bodypiece[7].mirror = true;
        this.Bodypiece[8] = new PlaneRenderer(this, 32, 0);
        this.Bodypiece[9] = new PlaneRenderer(this, 32, 0);
        this.BodypieceNeck[0] = new PlaneRenderer(this, 24, 0);
        this.BodypieceNeck[1] = new PlaneRenderer(this, 24, 0);
        this.BodypieceNeck[2] = new PlaneRenderer(this, 24, 0);
        this.BodypieceNeck[3] = new PlaneRenderer(this, 24, 0);
        this.LeftPlotPiece[0] = new PlaneRenderer(this, 32, 26);
        this.LeftPlotPiece[0].mirrorxy = true;
        this.LeftPlotPiece[1] = new PlaneRenderer(this, 30, 0);
        this.LeftPlotPiece[2] = new PlaneRenderer(this, 29, 0);
        this.LeftPlotPiece[3] = new PlaneRenderer(this, 32, 26);
        this.LeftPlotPiece[4] = new PlaneRenderer(this, 36, 16);
        this.LeftPlotPiece[5] = new PlaneRenderer(this, 4, 0);
        this.LeftPlotPiece[6] = new PlaneRenderer(this, 36, 16);
        this.LeftPlotPiece[7] = new PlaneRenderer(this, 40, 16);
        this.LeftPlotPiece[8] = new PlaneRenderer(this, 52, 16);
        this.LeftPlotPiece[9] = new PlaneRenderer(this, 52, 16);
        this.LeftPlotPiece[10] = new PlaneRenderer(this, 36, 16);
        this.LeftPlotPiece[11] = new PlaneRenderer(this, 40, 16);
        this.RightPlotPiece[0] = new PlaneRenderer(this, 36, 26);
        this.RightPlotPiece[0].mirrorxy = true;
        this.RightPlotPiece[1] = new PlaneRenderer(this, 30, 0);
        this.RightPlotPiece[2] = new PlaneRenderer(this, 29, 0);
        this.RightPlotPiece[3] = new PlaneRenderer(this, 36, 26);
        this.RightPlotPiece[4] = new PlaneRenderer(this, 36, 16);
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

    @Override
    protected void initLegTextures() {
        this.RightArm = new ModelRenderer(this, 40, 16);
        this.RightShoulder = new ModelRenderer(this, 24, 22);
        this.LeftArm = new ModelRenderer(this, 40, 16);
        this.LeftArm.mirror = true;
        this.LeftShoulder = new ModelRenderer(this, 13, 22);
        this.LeftShoulder.mirror = true;
        this.RightLeg = new ModelRenderer(this, 40, 18);
        this.LeftLeg = new ModelRenderer(this, 40, 18);
        this.LeftLeg.mirror = true;
        this.RightLegJoint = new ModelRenderer(this, 40, 16);
        this.LeftLegJoint = new ModelRenderer(this, 40, 16);
        this.LeftLegJoint.mirror = true;
        this.RightSubLeg[0] = new PlaneRenderer(this, 44, 22);
        this.LeftSubLeg[0] = new PlaneRenderer(this, 44, 22);
        this.LeftSubLeg[0].mirror = true;
        this.RightSubLeg[1] = new PlaneRenderer(this, 52, 22);
        this.LeftSubLeg[1] = new PlaneRenderer(this, 52, 22);
        this.LeftSubLeg[1].mirror = true;
        this.RightSubLeg[2] = new PlaneRenderer(this, 48, 16);
        this.LeftSubLeg[2] = new PlaneRenderer(this, 48, 16);
        this.LeftSubLeg[2].mirror = true;
        this.SteveArm = new ModelRenderer(this, 40, 16);
        this.unicornarm = new ModelRenderer(this, 40, 16);
    }

    @Override
    protected void initBodyPositions(float var1, float var2) {
        this.Body.addBox(-4.0f, 4.0f, -2.0f, 8, 8, 4, var2);
        this.Body.setRotationPoint(this.HEAD_RP_X, this.HEAD_RP_Y + var1, this.HEAD_RP_Z);
        this.Bodypiece[0].addSidePlane(-4.0f + this.BODY_CENTRE_X, -4.0f + this.BODY_CENTRE_Y, -4.0f + this.BODY_CENTRE_Z, 0, 8, 8, var2);
        this.Bodypiece[0].setRotationPoint(this.HEAD_RP_X, this.HEAD_RP_Y + var1, this.HEAD_RP_Z);
        this.Bodypiece[1].addSidePlane(4.0f + this.BODY_CENTRE_X, -4.0f + this.BODY_CENTRE_Y, -4.0f + this.BODY_CENTRE_Z, 0, 8, 8, var2);
        this.Bodypiece[1].setRotationPoint(this.HEAD_RP_X, this.HEAD_RP_Y + var1, this.HEAD_RP_Z);
        this.Bodypiece[2].addTopPlane(-4.0f + this.BODY_CENTRE_X, -4.0f + this.BODY_CENTRE_Y, -4.0f + this.BODY_CENTRE_Z, 8, 0, 8, var2);
        this.Bodypiece[2].setRotationPoint(this.HEAD_RP_X, this.HEAD_RP_Y + var1, this.HEAD_RP_Z);
        this.Bodypiece[3].addBottomPlane(-4.0f + this.BODY_CENTRE_X, 4.0f + this.BODY_CENTRE_Y, -4.0f + this.BODY_CENTRE_Z, 8, 0, 8, var2);
        this.Bodypiece[3].setRotationPoint(this.HEAD_RP_X, this.HEAD_RP_Y + var1, this.HEAD_RP_Z);
        this.Bodypiece[4].addTopPlane(-1.0f + this.BODY_CENTRE_X, 2.0f + this.BODY_CENTRE_Y, 2.0f + this.BODY_CENTRE_Z, 2, 0, 6, var2);
        this.Bodypiece[4].setRotationPoint(this.HEAD_RP_X, this.HEAD_RP_Y + var1, this.HEAD_RP_Z);
        this.Bodypiece[5].addBottomPlane(-1.0f + this.BODY_CENTRE_X, 4.0f + this.BODY_CENTRE_Y, 2.0f + this.BODY_CENTRE_Z, 2, 0, 6, var2);
        this.Bodypiece[5].setRotationPoint(this.HEAD_RP_X, this.HEAD_RP_Y + var1, this.HEAD_RP_Z);
        this.Bodypiece[6].addSidePlane(-1.0f + this.BODY_CENTRE_X, 2.0f + this.BODY_CENTRE_Y, 2.0f + this.BODY_CENTRE_Z, 0, 2, 6, var2);
        this.Bodypiece[6].setRotationPoint(this.HEAD_RP_X, this.HEAD_RP_Y + var1, this.HEAD_RP_Z);
        this.Bodypiece[7].addSidePlane(1.0f + this.BODY_CENTRE_X, 2.0f + this.BODY_CENTRE_Y, 2.0f + this.BODY_CENTRE_Z, 0, 2, 6, var2);
        this.Bodypiece[7].setRotationPoint(this.HEAD_RP_X, this.HEAD_RP_Y + var1, this.HEAD_RP_Z);
        this.Bodypiece[8].addBackPlane(-1.0f + this.BODY_CENTRE_X, 2.0f + this.BODY_CENTRE_Y, 8.0f + this.BODY_CENTRE_Z, 2, 2, 0, var2);
        this.Bodypiece[8].setRotationPoint(this.HEAD_RP_X, this.HEAD_RP_Y + var1, this.HEAD_RP_Z);
        this.BodypieceNeck[0].addBackPlane(-2.0f + this.BODY_CENTRE_X, -6.8f + this.BODY_CENTRE_Y, -8.8f + this.BODY_CENTRE_Z, 4, 4, 0, var2);
        this.BodypieceNeck[0].setRotationPoint(this.HEAD_RP_X, this.HEAD_RP_Y + var1, this.HEAD_RP_Z);
        this.BodypieceNeck[1].addBackPlane(-2.0f + this.BODY_CENTRE_X, -6.8f + this.BODY_CENTRE_Y, -4.8f + this.BODY_CENTRE_Z, 4, 4, 0, var2);
        this.BodypieceNeck[1].setRotationPoint(this.HEAD_RP_X, this.HEAD_RP_Y + var1, this.HEAD_RP_Z);
        this.BodypieceNeck[2].addSidePlane(-2.0f + this.BODY_CENTRE_X, -6.8f + this.BODY_CENTRE_Y, -8.8f + this.BODY_CENTRE_Z, 0, 4, 4, var2);
        this.BodypieceNeck[2].setRotationPoint(this.HEAD_RP_X, this.HEAD_RP_Y + var1, this.HEAD_RP_Z);
        this.BodypieceNeck[3].addSidePlane(2.0f + this.BODY_CENTRE_X, -6.8f + this.BODY_CENTRE_Y, -8.8f + this.BODY_CENTRE_Z, 0, 4, 4, var2);
        this.BodypieceNeck[3].setRotationPoint(this.HEAD_RP_X, this.HEAD_RP_Y + var1, this.HEAD_RP_Z);
        this.BodypieceNeck[0].rotateAngleX = this.NeckRotX;
        this.BodypieceNeck[1].rotateAngleX = this.NeckRotX;
        this.BodypieceNeck[2].rotateAngleX = this.NeckRotX;
        this.BodypieceNeck[3].rotateAngleX = this.NeckRotX;
        this.LeftPlotPiece[0].addTopPlane(0.0f + this.BODY_CENTRE_X, -4.0f + this.BODY_CENTRE_Y, 2.0f + this.BODY_CENTRE_Z, 4, 0, 6, var2);
        this.LeftPlotPiece[0].setRotationPoint(0.0f, 0.0f + var1, 0.0f);
        this.LeftPlotPiece[1].addSidePlane(4.0f + this.BODY_CENTRE_X, -4.0f + this.BODY_CENTRE_Y, 2.0f + this.BODY_CENTRE_Z, 0, 8, 2, var2);
        this.LeftPlotPiece[1].setRotationPoint(0.0f, 0.0f + var1, 0.0f);
        this.LeftPlotPiece[2].addBackPlane(3.0f + this.BODY_CENTRE_X, -4.0f + this.BODY_CENTRE_Y, 2.0f + this.BODY_CENTRE_Z, 1, 8, 0, var2);
        this.LeftPlotPiece[2].setRotationPoint(0.0f, 0.0f + var1, 0.0f);
        this.LeftPlotPiece[3].addBackPlane(0.0f + this.BODY_CENTRE_X, -4.0f + this.BODY_CENTRE_Y, 2.0f + this.BODY_CENTRE_Z, 4, 1, 0, var2);
        this.LeftPlotPiece[3].setRotationPoint(0.0f, 0.0f + var1, 0.0f);
        this.LeftPlotPiece[4].addSidePlane(4.0f + this.BODY_CENTRE_X, -4.0f + this.BODY_CENTRE_Y, 2.0f + this.BODY_CENTRE_Z, 8, 0, 4, var2);
        this.LeftPlotPiece[4].setRotationPoint(4.0f, 0.0f + var1, 0.0f);
        this.LeftPlotPiece[5].addSidePlane(4.0f + this.BODY_CENTRE_X, -4.0f + this.BODY_CENTRE_Y, 4.0f + this.BODY_CENTRE_Z, 0, 8, 4, var2);
        this.LeftPlotPiece[5].setRotationPoint(0.0f, 0.0f + var1, 0.0f);
        this.LeftPlotPiece[6].addBackPlane(0.0f + this.BODY_CENTRE_X, -4.0f + this.BODY_CENTRE_Y, 8.0f + this.BODY_CENTRE_Z, 4, 4, 0, var2);
        this.LeftPlotPiece[6].setRotationPoint(0.0f, 0.0f + var1, 0.0f);
        this.LeftPlotPiece[7].addBackPlane(0.0f + this.BODY_CENTRE_X, 0.0f + this.BODY_CENTRE_Y, 8.0f + this.BODY_CENTRE_Z, 4, 4, 0, var2);
        this.LeftPlotPiece[7].setRotationPoint(0.0f, 0.0f + var1, 0.0f);
        this.LeftPlotPiece[8].addBottomPlane(0.0f + this.BODY_CENTRE_X, 4.0f + this.BODY_CENTRE_Y, 2.0f + this.BODY_CENTRE_Z, 4, 0, 2, var2);
        this.LeftPlotPiece[8].setRotationPoint(0.0f, 0.0f + var1, 0.0f);
        this.LeftPlotPiece[9].addBottomPlane(0.0f + this.BODY_CENTRE_X, 4.0f + this.BODY_CENTRE_Y, 4.0f + this.BODY_CENTRE_Z, 4, 0, 4, var2);
        this.LeftPlotPiece[9].setRotationPoint(0.0f, 0.0f + var1, 0.0f);
        this.LeftPlotPiece[10].addSidePlane(0.0f + this.BODY_CENTRE_X, -4.0f + this.BODY_CENTRE_Y, 4.0f + this.BODY_CENTRE_Z, 0, 4, 4, var2);
        this.LeftPlotPiece[10].setRotationPoint(0.0f, 0.0f + var1, 0.0f);
        this.LeftPlotPiece[11].addSidePlane(0.0f + this.BODY_CENTRE_X, 0.0f + this.BODY_CENTRE_Y, 4.0f + this.BODY_CENTRE_Z, 0, 4, 4, var2);
        this.LeftPlotPiece[11].setRotationPoint(0.0f, 0.0f + var1, 0.0f);
        this.RightPlotPiece[0].addTopPlane(-4.0f + this.BODY_CENTRE_X, -4.0f + this.BODY_CENTRE_Y, 2.0f + this.BODY_CENTRE_Z, 4, 0, 6, var2);
        this.RightPlotPiece[0].setRotationPoint(0.0f, 0.0f + var1, 0.0f);
        this.RightPlotPiece[1].addSidePlane(-4.0f + this.BODY_CENTRE_X, -4.0f + this.BODY_CENTRE_Y, 2.0f + this.BODY_CENTRE_Z, 0, 8, 2, var2);
        this.RightPlotPiece[1].setRotationPoint(0.0f, 0.0f + var1, 0.0f);
        this.RightPlotPiece[2].addBackPlane(-4.0f + this.BODY_CENTRE_X, -4.0f + this.BODY_CENTRE_Y, 2.0f + this.BODY_CENTRE_Z, 1, 8, 0, var2);
        this.RightPlotPiece[2].setRotationPoint(0.0f, 0.0f + var1, 0.0f);
        this.RightPlotPiece[3].addBackPlane(-4.0f + this.BODY_CENTRE_X, -4.0f + this.BODY_CENTRE_Y, 2.0f + this.BODY_CENTRE_Z, 4, 1, 0, var2);
        this.RightPlotPiece[3].setRotationPoint(0.0f, 0.0f + var1, 0.0f);
        this.RightPlotPiece[4].addSidePlane(-4.0f + this.BODY_CENTRE_X, 0.0f + this.BODY_CENTRE_Y, 0.0f + this.BODY_CENTRE_Z, 8, 0, 4, var2);
        this.RightPlotPiece[4].setRotationPoint(0.0f, 0.0f + var1, 0.0f);
        this.RightPlotPiece[5].addSidePlane(-4.0f + this.BODY_CENTRE_X, -4.0f + this.BODY_CENTRE_Y, 4.0f + this.BODY_CENTRE_Z, 0, 8, 4, var2);
        this.RightPlotPiece[5].setRotationPoint(0.0f, 0.0f + var1, 0.0f);
        this.RightPlotPiece[6].addBackPlane(-4.0f + this.BODY_CENTRE_X, -4.0f + this.BODY_CENTRE_Y, 8.0f + this.BODY_CENTRE_Z, 4, 4, 0, var2);
        this.RightPlotPiece[6].setRotationPoint(0.0f, 0.0f + var1, 0.0f);
        this.RightPlotPiece[7].addBackPlane(-4.0f + this.BODY_CENTRE_X, 0.0f + this.BODY_CENTRE_Y, 8.0f + this.BODY_CENTRE_Z, 4, 4, 0, var2);
        this.RightPlotPiece[7].setRotationPoint(0.0f, 0.0f + var1, 0.0f);
        this.RightPlotPiece[8].addBottomPlane(-4.0f + this.BODY_CENTRE_X, 4.0f + this.BODY_CENTRE_Y, 2.0f + this.BODY_CENTRE_Z, 4, 0, 2, var2);
        this.RightPlotPiece[8].setRotationPoint(0.0f, 0.0f + var1, 0.0f);
        this.RightPlotPiece[9].addBottomPlane(-4.0f + this.BODY_CENTRE_X, 4.0f + this.BODY_CENTRE_Y, 4.0f + this.BODY_CENTRE_Z, 4, 0, 4, var2);
        this.RightPlotPiece[9].setRotationPoint(0.0f, 0.0f + var1, 0.0f);
        this.RightPlotPiece[10].addSidePlane(0.0f + this.BODY_CENTRE_X, -4.0f + this.BODY_CENTRE_Y, 4.0f + this.BODY_CENTRE_Z, 0, 4, 4, var2);
        this.RightPlotPiece[10].setRotationPoint(0.0f, 0.0f + var1, 0.0f);
        this.RightPlotPiece[11].addSidePlane(0.0f + this.BODY_CENTRE_X, 0.0f + this.BODY_CENTRE_Y, 4.0f + this.BODY_CENTRE_Z, 0, 4, 4, var2);
        this.RightPlotPiece[11].setRotationPoint(0.0f, 0.0f + var1, 0.0f);
        this.RightArm.addBox(-2.0f + this.THIRDP_ARM_CENTRE_X, -6.0f + this.THIRDP_ARM_CENTRE_Y, -2.0f + this.THIRDP_ARM_CENTRE_Z, 4, 12, 4, var2);
        this.RightArm.setRotationPoint(-3.0f, 8.0f + var1, 0.0f);
        this.RightShoulder.addBox(6.0f + this.THIRDP_ARM_CENTRE_X, -9.0f + this.THIRDP_ARM_CENTRE_Y, -1.0f + this.THIRDP_ARM_CENTRE_Z, 1, 3, 3, var2);
        this.RightShoulder.setRotationPoint(-3.0f, 8.0f + var1, 0.0f);
        this.LeftArm.addBox(-2.0f + this.THIRDP_ARM_CENTRE_X, -6.0f + this.THIRDP_ARM_CENTRE_Y, -2.0f + this.THIRDP_ARM_CENTRE_Z, 4, 12, 4, var2);
        this.LeftArm.setRotationPoint(3.0f, 8.0f + var1, 0.0f);
        this.LeftShoulder.addBox(-7.0f + this.THIRDP_ARM_CENTRE_X, -9.0f + this.THIRDP_ARM_CENTRE_Y, -1.0f + this.THIRDP_ARM_CENTRE_Z, 1, 3, 3, var2);
        this.LeftShoulder.setRotationPoint(3.0f, 8.0f + var1, 0.0f);
        this.RightLeg.addBox(-2.0f + this.THIRDP_ARM_CENTRE_X, -4.0f + this.THIRDP_ARM_CENTRE_Y, -1.0f + this.THIRDP_ARM_CENTRE_Z, 4, 10, 4, var2);
        this.RightLeg.setRotationPoint(-3.0f, 0.0f + var1, 0.0f);
        this.LeftLeg.addBox(-2.0f + this.THIRDP_ARM_CENTRE_X, -4.0f + this.THIRDP_ARM_CENTRE_Y, -1.0f + this.THIRDP_ARM_CENTRE_Z, 4, 10, 4, var2);
        this.LeftLeg.setRotationPoint(3.0f, 0.0f + var1, 0.0f);
        this.RightLegJoint.addBox(-2.0f + this.THIRDP_ARM_CENTRE_X, -6.0f + this.THIRDP_ARM_CENTRE_Y, -2.0f + this.THIRDP_ARM_CENTRE_Z, 4, 2, 4, var2);
        this.RightLegJoint.setRotationPoint(-3.0f, 0.0f + var1, 0.0f);
        this.LeftLegJoint.addBox(-2.0f + this.THIRDP_ARM_CENTRE_X, -6.0f + this.THIRDP_ARM_CENTRE_Y, -2.0f + this.THIRDP_ARM_CENTRE_Z, 4, 2, 4, var2);
        this.LeftLegJoint.setRotationPoint(3.0f, 0.0f + var1, 0.0f);
        this.RightSubLeg[0].addBottomPlane(-2.0f + this.THIRDP_ARM_CENTRE_X, -3.99f + this.THIRDP_ARM_CENTRE_Y, -2.0f + this.THIRDP_ARM_CENTRE_Z, 4, 0, 1, var2);
        this.RightSubLeg[0].setRotationPoint(-3.0f, 0.0f + var1, 0.0f);
        this.LeftSubLeg[0].addBottomPlane(-2.0f + this.THIRDP_ARM_CENTRE_X, -3.99f + this.THIRDP_ARM_CENTRE_Y, -2.0f + this.THIRDP_ARM_CENTRE_Z, 4, 0, 1, var2);
        this.LeftSubLeg[0].setRotationPoint(3.0f, 0.0f + var1, 0.0f);
        this.RightSubLeg[1].addBottomPlane(-2.0f + this.THIRDP_ARM_CENTRE_X, -4.01f + this.THIRDP_ARM_CENTRE_Y, 2.0f + this.THIRDP_ARM_CENTRE_Z, 4, 0, 1, var2);
        this.RightSubLeg[1].setRotationPoint(-3.0f, 0.0f + var1, 0.0f);
        this.LeftSubLeg[1].addBottomPlane(-2.0f + this.THIRDP_ARM_CENTRE_X, -4.01f + this.THIRDP_ARM_CENTRE_Y, 2.0f + this.THIRDP_ARM_CENTRE_Z, 4, 0, 1, var2);
        this.LeftSubLeg[1].setRotationPoint(3.0f, 0.0f + var1, 0.0f);
        this.RightSubLeg[2].addBottomPlane(-2.0f + this.THIRDP_ARM_CENTRE_X, 6.0f + this.THIRDP_ARM_CENTRE_Y, -1.0f + this.THIRDP_ARM_CENTRE_Z, 4, 0, 4, var2);
        this.RightSubLeg[2].setRotationPoint(-3.0f, 0.0f + var1, 0.0f);
        this.LeftSubLeg[2].addBottomPlane(-2.0f + this.THIRDP_ARM_CENTRE_X, 6.0f + this.THIRDP_ARM_CENTRE_Y, -1.0f + this.THIRDP_ARM_CENTRE_Z, 4, 0, 4, var2);
        this.LeftSubLeg[2].setRotationPoint(3.0f, 0.0f + var1, 0.0f);
        this.SteveArm.addBox(-2.0f + this.FIRSTP_ARM_CENTRE_X, -6.0f + this.FIRSTP_ARM_CENTRE_Y, -2.0f + this.FIRSTP_ARM_CENTRE_Z, 4, 12, 4, var2);
        this.SteveArm.setRotationPoint(-5.0f, 2.0f + var1, 0.0f);
        this.unicornarm.addBox(-2.0f + this.FIRSTP_ARM_CENTRE_X, -6.0f + this.FIRSTP_ARM_CENTRE_Y, -2.0f + this.FIRSTP_ARM_CENTRE_Z, 4, 12, 4, var2);
        this.unicornarm.setRotationPoint(-5.0f, 2.0f + var1, 0.0f);
    }
}

