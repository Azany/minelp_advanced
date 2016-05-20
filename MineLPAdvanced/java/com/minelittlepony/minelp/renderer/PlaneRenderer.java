/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.client.model.PositionTextureVertex
 *  net.minecraft.client.model.TexturedQuad
 *  net.minecraft.client.renderer.GLAllocation
 *  net.minecraft.client.renderer.Tessellator
 *  org.lwjgl.opengl.GL11
 */
package com.minelittlepony.minelp.renderer;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.model.PositionTextureVertex;
import net.minecraft.client.model.TexturedQuad;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.Tessellator;
import org.lwjgl.opengl.GL11;

public class PlaneRenderer {
    public float textureWidth = 64.0f;
    public float textureHeight = 32.0f;
    public float rotationPointX;
    public float rotationPointY;
    public float rotationPointZ;
    public float rotateAngleX;
    public float rotateAngleY;
    public float rotateAngleZ;
    public float field_35977_i;
    public float field_35975_j;
    public float field_35976_k;
    public float field_35973_l;
    public float field_35974_m;
    public float field_35972_n;
    public boolean mirror = false;
    public boolean mirrory = false;
    public boolean mirrorxy = false;
    public boolean showModel = true;
    public boolean isHidden = false;
    private PositionTextureVertex[] corners;
    private TexturedQuad[] faces;
    private int textureOffsetX;
    private int textureOffsetY;
    private boolean compiled = false;
    private int displayList = 0;

    public PlaneRenderer(ModelBase modelbase, int i, int j) {
        this.textureOffsetX = i;
        this.textureOffsetY = j;
    }

    public void addBackPlane(float f, float f1, float f2, int i, int j, int k) {
        this.addBackPlane(f, f1, f2, i, j, k, 0.0f);
    }

    public void addSidePlane(float f, float f1, float f2, int i, int j, int k) {
        this.addSidePlane(f, f1, f2, i, j, k, 0.0f);
    }

    public void addTopPlane(float f, float f1, float f2, int i, int j, int k) {
        this.addTopPlane(f, f1, f2, i, j, k, 0.0f);
    }

    public void addBottomPlane(float f, float f1, float f2, int i, int j, int k) {
        this.addBottomPlane(f, f1, f2, i, j, k, 0.0f);
    }

    public void addBackPlane(float f, float f1, float f2, int i, int j, int k, float f3) {
        this.field_35977_i = f;
        this.field_35975_j = f1;
        this.field_35976_k = f2;
        this.field_35973_l = f + (float) i;
        this.field_35974_m = f1 + (float) j;
        this.field_35972_n = f2 + (float) k;
        this.corners = new PositionTextureVertex[8];
        this.faces = new TexturedQuad[1];
        float f4 = f + (float) i;
        float f5 = f1 + (float) j;
        float f6 = f2 + (float) k;
        f -= f3;
        f1 -= f3;
        f2 -= f3;
        f4 += f3;
        f5 += f3;
        f6 += f3;
        if (this.mirror) {
            float f7 = f4;
            f4 = f;
            f = f7;
        }
        PositionTextureVertex positiontexturevertex = new PositionTextureVertex(f, f1, f2, 0.0f, 0.0f);
        PositionTextureVertex positiontexturevertex1 = new PositionTextureVertex(f4, f1, f2, 0.0f, 8.0f);
        PositionTextureVertex positiontexturevertex2 = new PositionTextureVertex(f4, f5, f2, 8.0f, 8.0f);
        PositionTextureVertex positiontexturevertex3 = new PositionTextureVertex(f, f5, f2, 8.0f, 0.0f);
        PositionTextureVertex positiontexturevertex4 = new PositionTextureVertex(f, f1, f6, 0.0f, 0.0f);
        PositionTextureVertex positiontexturevertex5 = new PositionTextureVertex(f4, f1, f6, 0.0f, 8.0f);
        PositionTextureVertex positiontexturevertex6 = new PositionTextureVertex(f4, f5, f6, 8.0f, 8.0f);
        PositionTextureVertex positiontexturevertex7 = new PositionTextureVertex(f, f5, f6, 8.0f, 0.0f);
        this.corners[0] = positiontexturevertex;
        this.corners[1] = positiontexturevertex1;
        this.corners[2] = positiontexturevertex2;
        this.corners[3] = positiontexturevertex3;
        this.corners[4] = positiontexturevertex4;
        this.corners[5] = positiontexturevertex5;
        this.corners[6] = positiontexturevertex6;
        this.corners[7] = positiontexturevertex7;
        this.faces[0] = new TexturedQuad(new PositionTextureVertex[]{positiontexturevertex1, positiontexturevertex, positiontexturevertex3, positiontexturevertex2}, this.textureOffsetX, this.textureOffsetY, this.textureOffsetX + i, this.textureOffsetY + j, this.textureWidth, this.textureHeight);
        if (this.mirror) {
            this.faces[0].flipFace();
        }
    }

    public void addSidePlane(float f, float f1, float f2, int i, int j, int k, float f3) {
        this.field_35977_i = f;
        this.field_35975_j = f1;
        this.field_35976_k = f2;
        this.field_35973_l = f + (float) i;
        this.field_35974_m = f1 + (float) j;
        this.field_35972_n = f2 + (float) k;
        this.corners = new PositionTextureVertex[8];
        this.faces = new TexturedQuad[1];
        float f4 = f + (float) i;
        float f5 = f1 + (float) j;
        float f6 = f2 + (float) k;
        f -= f3;
        f1 -= f3;
        f2 -= f3;
        f4 += f3;
        f5 += f3;
        f6 += f3;
        if (this.mirror) {
            float f7 = f4;
            f4 = f;
            f = f7;
        }
        PositionTextureVertex positiontexturevertex = new PositionTextureVertex(f, f1, f2, 0.0f, 0.0f);
        PositionTextureVertex positiontexturevertex1 = new PositionTextureVertex(f4, f1, f2, 0.0f, 8.0f);
        PositionTextureVertex positiontexturevertex2 = new PositionTextureVertex(f4, f5, f2, 8.0f, 8.0f);
        PositionTextureVertex positiontexturevertex3 = new PositionTextureVertex(f, f5, f2, 8.0f, 0.0f);
        PositionTextureVertex positiontexturevertex4 = new PositionTextureVertex(f, f1, f6, 0.0f, 0.0f);
        PositionTextureVertex positiontexturevertex5 = new PositionTextureVertex(f4, f1, f6, 0.0f, 8.0f);
        PositionTextureVertex positiontexturevertex6 = new PositionTextureVertex(f4, f5, f6, 8.0f, 8.0f);
        PositionTextureVertex positiontexturevertex7 = new PositionTextureVertex(f, f5, f6, 8.0f, 0.0f);
        this.corners[0] = positiontexturevertex;
        this.corners[1] = positiontexturevertex1;
        this.corners[2] = positiontexturevertex2;
        this.corners[3] = positiontexturevertex3;
        this.corners[4] = positiontexturevertex4;
        this.corners[5] = positiontexturevertex5;
        this.corners[6] = positiontexturevertex6;
        this.corners[7] = positiontexturevertex7;
        this.faces[0] = new TexturedQuad(new PositionTextureVertex[]{positiontexturevertex5, positiontexturevertex1, positiontexturevertex2, positiontexturevertex6}, this.textureOffsetX, this.textureOffsetY, this.textureOffsetX + k, this.textureOffsetY + j, this.textureWidth, this.textureHeight);
        if (this.mirror) {
            this.faces[0].flipFace();
        }
    }

    public void addTopPlane(float f, float f1, float f2, int i, int j, int k, float f3) {
        float f7;
        this.field_35977_i = f;
        this.field_35975_j = f1;
        this.field_35976_k = f2;
        this.field_35973_l = f + (float) i;
        this.field_35974_m = f1 + (float) j;
        this.field_35972_n = f2 + (float) k;
        this.corners = new PositionTextureVertex[8];
        this.faces = new TexturedQuad[1];
        float f4 = f + (float) i;
        float f5 = f1 + (float) j;
        float f6 = f2 + (float) k;
        f -= f3;
        f1 -= f3;
        f2 -= f3;
        f4 += f3;
        f5 += f3;
        f6 += f3;
        if (this.mirror) {
            f7 = f4;
            f4 = f;
            f = f7;
        }
        if (this.mirrory) {
            f7 = f6;
            f6 = f2;
            f2 = f7;
        }
        if (this.mirrorxy) {
            f7 = f6;
            f6 = f2;
            f2 = f7;
            f7 = f4;
            f4 = f;
            f = f7;
        }
        PositionTextureVertex positiontexturevertex = new PositionTextureVertex(f, f1, f2, 0.0f, 0.0f);
        PositionTextureVertex positiontexturevertex1 = new PositionTextureVertex(f4, f1, f2, 0.0f, 8.0f);
        PositionTextureVertex positiontexturevertex2 = new PositionTextureVertex(f4, f5, f2, 8.0f, 8.0f);
        PositionTextureVertex positiontexturevertex3 = new PositionTextureVertex(f, f5, f2, 8.0f, 0.0f);
        PositionTextureVertex positiontexturevertex4 = new PositionTextureVertex(f, f1, f6, 0.0f, 0.0f);
        PositionTextureVertex positiontexturevertex5 = new PositionTextureVertex(f4, f1, f6, 0.0f, 8.0f);
        PositionTextureVertex positiontexturevertex6 = new PositionTextureVertex(f4, f5, f6, 8.0f, 8.0f);
        PositionTextureVertex positiontexturevertex7 = new PositionTextureVertex(f, f5, f6, 8.0f, 0.0f);
        this.corners[0] = positiontexturevertex;
        this.corners[1] = positiontexturevertex1;
        this.corners[2] = positiontexturevertex2;
        this.corners[3] = positiontexturevertex3;
        this.corners[4] = positiontexturevertex4;
        this.corners[5] = positiontexturevertex5;
        this.corners[6] = positiontexturevertex6;
        this.corners[7] = positiontexturevertex7;
        this.faces[0] = new TexturedQuad(new PositionTextureVertex[]{positiontexturevertex5, positiontexturevertex4, positiontexturevertex, positiontexturevertex1}, this.textureOffsetX, this.textureOffsetY, this.textureOffsetX + i, this.textureOffsetY + k, this.textureWidth, this.textureHeight);
        if (this.mirror || this.mirrory) {
            this.faces[0].flipFace();
        }
    }

    public void addBottomPlane(float f, float f1, float f2, int i, int j, int k, float f3) {
        float f7;
        this.field_35977_i = f;
        this.field_35975_j = f1;
        this.field_35976_k = f2;
        this.field_35973_l = f + (float) i;
        this.field_35974_m = f1 + (float) j;
        this.field_35972_n = f2 + (float) k;
        this.corners = new PositionTextureVertex[8];
        this.faces = new TexturedQuad[1];
        float f4 = f + (float) i;
        float f5 = f1 + (float) j;
        float f6 = f2 + (float) k;
        f -= f3;
        f1 -= f3;
        f2 -= f3;
        f4 += f3;
        f5 += f3;
        f6 += f3;
        if (this.mirror) {
            f7 = f4;
            f4 = f;
            f = f7;
        }
        if (this.mirrory) {
            f7 = f6;
            f6 = f2;
            f2 = f7;
        }
        if (this.mirrorxy) {
            f7 = f6;
            f6 = f2;
            f2 = f7;
            f7 = f4;
            f4 = f;
            f = f7;
        }
        PositionTextureVertex positiontexturevertex = new PositionTextureVertex(f, f1, f2, 0.0f, 0.0f);
        PositionTextureVertex positiontexturevertex1 = new PositionTextureVertex(f4, f1, f2, 0.0f, 8.0f);
        PositionTextureVertex positiontexturevertex2 = new PositionTextureVertex(f4, f5, f2, 8.0f, 8.0f);
        PositionTextureVertex positiontexturevertex3 = new PositionTextureVertex(f, f5, f2, 8.0f, 0.0f);
        PositionTextureVertex positiontexturevertex4 = new PositionTextureVertex(f, f1, f6, 0.0f, 0.0f);
        PositionTextureVertex positiontexturevertex5 = new PositionTextureVertex(f4, f1, f6, 0.0f, 8.0f);
        PositionTextureVertex positiontexturevertex6 = new PositionTextureVertex(f4, f5, f6, 8.0f, 8.0f);
        PositionTextureVertex positiontexturevertex7 = new PositionTextureVertex(f, f5, f6, 8.0f, 0.0f);
        this.corners[0] = positiontexturevertex;
        this.corners[1] = positiontexturevertex1;
        this.corners[2] = positiontexturevertex2;
        this.corners[3] = positiontexturevertex3;
        this.corners[4] = positiontexturevertex4;
        this.corners[5] = positiontexturevertex5;
        this.corners[6] = positiontexturevertex6;
        this.corners[7] = positiontexturevertex7;
        this.faces[0] = new TexturedQuad(new PositionTextureVertex[]{positiontexturevertex2, positiontexturevertex3, positiontexturevertex7, positiontexturevertex6}, this.textureOffsetX, this.textureOffsetY, this.textureOffsetX + i, this.textureOffsetY + k, this.textureWidth, this.textureHeight);
        if (this.mirror || this.mirrory) {
            this.faces[0].flipFace();
        }
    }

    public void setRotationPoint(float f, float f1, float f2) {
        this.rotationPointX = f;
        this.rotationPointY = f1;
        this.rotationPointZ = f2;
    }

    public void render(float f) {
        if (this.isHidden) {
            return;
        }
        if (!this.showModel) {
            return;
        }
        if (!this.compiled) {
            this.compileDisplayList(f);
        }
        if (this.rotateAngleX != 0.0f || this.rotateAngleY != 0.0f || this.rotateAngleZ != 0.0f) {
            GL11.glPushMatrix();
            GL11.glTranslatef((float) (this.rotationPointX * f), (float) (this.rotationPointY * f), (float) (this.rotationPointZ * f));
            if (this.rotateAngleZ != 0.0f) {
                GL11.glRotatef((float) (this.rotateAngleZ * 57.29578f), (float) 0.0f, (float) 0.0f, (float) 1.0f);
            }
            if (this.rotateAngleY != 0.0f) {
                GL11.glRotatef((float) (this.rotateAngleY * 57.29578f), (float) 0.0f, (float) 1.0f, (float) 0.0f);
            }
            if (this.rotateAngleX != 0.0f) {
                GL11.glRotatef((float) (this.rotateAngleX * 57.29578f), (float) 1.0f, (float) 0.0f, (float) 0.0f);
            }
            GL11.glCallList((int) this.displayList);
            GL11.glPopMatrix();
        } else if (this.rotationPointX != 0.0f || this.rotationPointY != 0.0f || this.rotationPointZ != 0.0f) {
            GL11.glTranslatef((float) (this.rotationPointX * f), (float) (this.rotationPointY * f), (float) (this.rotationPointZ * f));
            GL11.glCallList((int) this.displayList);
            GL11.glTranslatef((float) ((-this.rotationPointX) * f), (float) ((-this.rotationPointY) * f), (float) ((-this.rotationPointZ) * f));
        } else {
            GL11.glCallList((int) this.displayList);
        }
    }

    public void renderWithRotation(float f) {
        if (this.isHidden) {
            return;
        }
        if (!this.showModel) {
            return;
        }
        if (!this.compiled) {
            this.compileDisplayList(f);
        }
        GL11.glPushMatrix();
        GL11.glTranslatef((float) (this.rotationPointX * f), (float) (this.rotationPointY * f), (float) (this.rotationPointZ * f));
        if (this.rotateAngleY != 0.0f) {
            GL11.glRotatef((float) (this.rotateAngleY * 57.29578f), (float) 0.0f, (float) 1.0f, (float) 0.0f);
        }
        if (this.rotateAngleX != 0.0f) {
            GL11.glRotatef((float) (this.rotateAngleX * 57.29578f), (float) 1.0f, (float) 0.0f, (float) 0.0f);
        }
        if (this.rotateAngleZ != 0.0f) {
            GL11.glRotatef((float) (this.rotateAngleZ * 57.29578f), (float) 0.0f, (float) 0.0f, (float) 1.0f);
        }
        GL11.glCallList((int) this.displayList);
        GL11.glPopMatrix();
    }

    public void postRender(float f) {
        if (this.isHidden) {
            return;
        }
        if (!this.showModel) {
            return;
        }
        if (!this.compiled) {
            this.compileDisplayList(f);
        }
        if (this.rotateAngleX != 0.0f || this.rotateAngleY != 0.0f || this.rotateAngleZ != 0.0f) {
            GL11.glTranslatef((float) (this.rotationPointX * f), (float) (this.rotationPointY * f), (float) (this.rotationPointZ * f));
            if (this.rotateAngleZ != 0.0f) {
                GL11.glRotatef((float) (this.rotateAngleZ * 57.29578f), (float) 0.0f, (float) 0.0f, (float) 1.0f);
            }
            if (this.rotateAngleY != 0.0f) {
                GL11.glRotatef((float) (this.rotateAngleY * 57.29578f), (float) 0.0f, (float) 1.0f, (float) 0.0f);
            }
            if (this.rotateAngleX != 0.0f) {
                GL11.glRotatef((float) (this.rotateAngleX * 57.29578f), (float) 1.0f, (float) 0.0f, (float) 0.0f);
            }
        } else if (this.rotationPointX != 0.0f || this.rotationPointY != 0.0f || this.rotationPointZ != 0.0f) {
            GL11.glTranslatef((float) (this.rotationPointX * f), (float) (this.rotationPointY * f), (float) (this.rotationPointZ * f));
        }
    }

    private void compileDisplayList(float f) {
        this.displayList = GLAllocation.generateDisplayLists((int) 1);
        GL11.glNewList((int) this.displayList, (int) 4864);
        Tessellator tessellator = Tessellator.instance;
        for (int i = 0; i < this.faces.length; ++i) {
            this.faces[i].draw(tessellator, f);
        }
        GL11.glEndList();
        this.compiled = true;
    }

    public PlaneRenderer setTextureSize(int i, int j) {
        this.textureWidth = i;
        this.textureHeight = j;
        return this;
    }

    public void setToModel(ModelRenderer modelrenderer) {
        this.rotationPointX = modelrenderer.rotationPointX;
        this.rotationPointY = modelrenderer.rotationPointY;
        this.rotationPointZ = modelrenderer.rotationPointZ;
        this.rotateAngleX = modelrenderer.rotateAngleX;
        this.rotateAngleY = modelrenderer.rotateAngleY;
        this.rotateAngleZ = modelrenderer.rotateAngleZ;
    }
}

