/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.TextureOffset
 *  net.minecraft.client.renderer.GLAllocation
 *  net.minecraft.client.renderer.Tessellator
 *  org.lwjgl.opengl.GL11
 */
package com.minelittlepony.minelp.renderer;

import com.minelittlepony.minelp.model.ScalableModelBox;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.TextureOffset;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.Tessellator;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;
import java.util.List;

public class ScalableModelRenderer {
    public final String boxName;
    public float textureWidth = 64.0f;
    public float textureHeight = 32.0f;
    public float rotationPointX;
    public float rotationPointY;
    public float rotationPointZ;
    public float rotateAngleX;
    public float rotateAngleY;
    public float rotateAngleZ;
    public boolean mirror;
    public boolean showModel = true;
    public boolean isHidden;
    public List<ScalableModelBox> cubeList = new ArrayList<ScalableModelBox>();
    public List<ScalableModelRenderer> childModels;
    public float offsetX;
    public float offsetY;
    public float offsetZ;
    private int textureOffsetX;
    private int textureOffsetY;
    private boolean compiled;
    private int displayList;
    private ModelBase baseModel;

    public ScalableModelRenderer(ModelBase par1ModelBase, String par2Str) {
        this.baseModel = par1ModelBase;
        par1ModelBase.boxList.add(this);
        this.boxName = par2Str;
        this.setTextureSize(par1ModelBase.textureWidth, par1ModelBase.textureHeight);
    }

    public ScalableModelRenderer(ModelBase par1ModelBase) {
        this(par1ModelBase, null);
    }

    public ScalableModelRenderer(ModelBase par1ModelBase, int par2, int par3) {
        this(par1ModelBase);
        this.setTextureOffset(par2, par3);
    }

    public void addChild(ScalableModelRenderer par1ModelRenderer) {
        if (this.childModels == null) {
            this.childModels = new ArrayList<ScalableModelRenderer>();
        }
        this.childModels.add(par1ModelRenderer);
    }

    public ScalableModelRenderer setTextureOffset(int par1, int par2) {
        this.textureOffsetX = par1;
        this.textureOffsetY = par2;
        return this;
    }

    public ScalableModelRenderer addBox(String par1Str, float par2, float par3, float par4, int par5, int par6, int par7) {
        par1Str = this.boxName + "." + par1Str;
        TextureOffset var8 = this.baseModel.getTextureOffset(par1Str);
        this.setTextureOffset(var8.textureOffsetX, var8.textureOffsetY);
        this.cubeList.add(new ScalableModelBox(this, this.textureOffsetX, this.textureOffsetY, par2, par3, par4, par5, par6, par7, 0.0f).func_78244_a(par1Str));
        return this;
    }

    public ScalableModelRenderer addBox(float par1, float par2, float par3, int par4, int par5, int par6) {
        this.cubeList.add(new ScalableModelBox(this, this.textureOffsetX, this.textureOffsetY, par1, par2, par3, par4, par5, par6, 0.0f));
        return this;
    }

    public void addBox(float par1, float par2, float par3, int par4, int par5, int par6, float par7) {
        this.cubeList.add(new ScalableModelBox(this, this.textureOffsetX, this.textureOffsetY, par1, par2, par3, par4, par5, par6, par7));
    }

    public void setRotationPoint(float par1, float par2, float par3) {
        this.rotationPointX = par1;
        this.rotationPointY = par2;
        this.rotationPointZ = par3;
    }

    public void render(float par1) {
        if (!this.isHidden && this.showModel) {
            if (!this.compiled) {
                this.compileDisplayList(par1);
            }
            GL11.glTranslatef((float) this.offsetX, (float) this.offsetY, (float) this.offsetZ);
            if (this.rotateAngleX == 0.0f && this.rotateAngleY == 0.0f && this.rotateAngleZ == 0.0f) {
                if (this.rotationPointX == 0.0f && this.rotationPointY == 0.0f && this.rotationPointZ == 0.0f) {
                    GL11.glCallList((int) this.displayList);
                    if (this.childModels != null) {
                        for (int var2 = 0; var2 < this.childModels.size(); ++var2) {
                            this.childModels.get(var2).render(par1);
                        }
                    }
                } else {
                    GL11.glTranslatef((float) (this.rotationPointX * par1), (float) (this.rotationPointY * par1), (float) (this.rotationPointZ * par1));
                    GL11.glCallList((int) this.displayList);
                    if (this.childModels != null) {
                        for (int var2 = 0; var2 < this.childModels.size(); ++var2) {
                            this.childModels.get(var2).render(par1);
                        }
                    }
                    GL11.glTranslatef((float) ((-this.rotationPointX) * par1), (float) ((-this.rotationPointY) * par1), (float) ((-this.rotationPointZ) * par1));
                }
            } else {
                GL11.glPushMatrix();
                GL11.glTranslatef((float) (this.rotationPointX * par1), (float) (this.rotationPointY * par1), (float) (this.rotationPointZ * par1));
                if (this.rotateAngleZ != 0.0f) {
                    GL11.glRotatef((float) (this.rotateAngleZ * 57.295776f), (float) 0.0f, (float) 0.0f, (float) 1.0f);
                }
                if (this.rotateAngleY != 0.0f) {
                    GL11.glRotatef((float) (this.rotateAngleY * 57.295776f), (float) 0.0f, (float) 1.0f, (float) 0.0f);
                }
                if (this.rotateAngleX != 0.0f) {
                    GL11.glRotatef((float) (this.rotateAngleX * 57.295776f), (float) 1.0f, (float) 0.0f, (float) 0.0f);
                }
                GL11.glCallList((int) this.displayList);
                if (this.childModels != null) {
                    for (int var2 = 0; var2 < this.childModels.size(); ++var2) {
                        this.childModels.get(var2).render(par1);
                    }
                }
                GL11.glPopMatrix();
            }
            GL11.glTranslatef((float) (-this.offsetX), (float) (-this.offsetY), (float) (-this.offsetZ));
        }
    }

    public void render(float par1, float scaleX, float scaleY, float scaleZ) {
        if (!this.isHidden && this.showModel) {
            if (!this.compiled) {
                this.compileDisplayList(par1);
            }
            GL11.glTranslatef((float) this.offsetX, (float) this.offsetY, (float) this.offsetZ);
            if (this.rotateAngleX == 0.0f && this.rotateAngleY == 0.0f && this.rotateAngleZ == 0.0f) {
                if (this.rotationPointX == 0.0f && this.rotationPointY == 0.0f && this.rotationPointZ == 0.0f) {
                    GL11.glPushMatrix();
                    GL11.glScalef((float) scaleX, (float) scaleY, (float) scaleZ);
                    GL11.glCallList((int) this.displayList);
                    if (this.childModels != null) {
                        for (int var2 = 0; var2 < this.childModels.size(); ++var2) {
                            this.childModels.get(var2).render(par1);
                        }
                    }
                    GL11.glPopMatrix();
                } else {
                    GL11.glTranslatef((float) (this.rotationPointX * par1), (float) (this.rotationPointY * par1), (float) (this.rotationPointZ * par1));
                    GL11.glPushMatrix();
                    GL11.glScalef((float) scaleX, (float) scaleY, (float) scaleZ);
                    GL11.glCallList((int) this.displayList);
                    if (this.childModels != null) {
                        for (int var2 = 0; var2 < this.childModels.size(); ++var2) {
                            this.childModels.get(var2).render(par1);
                        }
                    }
                    GL11.glPopMatrix();
                    GL11.glTranslatef((float) ((-this.rotationPointX) * par1), (float) ((-this.rotationPointY) * par1), (float) ((-this.rotationPointZ) * par1));
                }
            } else {
                GL11.glPushMatrix();
                GL11.glTranslatef((float) (this.rotationPointX * par1), (float) (this.rotationPointY * par1), (float) (this.rotationPointZ * par1));
                if (this.rotateAngleZ != 0.0f) {
                    GL11.glRotatef((float) (this.rotateAngleZ * 57.295776f), (float) 0.0f, (float) 0.0f, (float) 1.0f);
                }
                if (this.rotateAngleY != 0.0f) {
                    GL11.glRotatef((float) (this.rotateAngleY * 57.295776f), (float) 0.0f, (float) 1.0f, (float) 0.0f);
                }
                if (this.rotateAngleX != 0.0f) {
                    GL11.glRotatef((float) (this.rotateAngleX * 57.295776f), (float) 1.0f, (float) 0.0f, (float) 0.0f);
                }
                GL11.glPushMatrix();
                GL11.glScalef((float) scaleX, (float) scaleY, (float) scaleZ);
                GL11.glCallList((int) this.displayList);
                if (this.childModels != null) {
                    for (int var2 = 0; var2 < this.childModels.size(); ++var2) {
                        this.childModels.get(var2).render(par1);
                    }
                }
                GL11.glPopMatrix();
                GL11.glPopMatrix();
            }
            GL11.glTranslatef((float) (-this.offsetX), (float) (-this.offsetY), (float) (-this.offsetZ));
        }
    }

    public void renderWithRotation(float par1) {
        if (!this.isHidden && this.showModel) {
            if (!this.compiled) {
                this.compileDisplayList(par1);
            }
            GL11.glPushMatrix();
            GL11.glTranslatef((float) (this.rotationPointX * par1), (float) (this.rotationPointY * par1), (float) (this.rotationPointZ * par1));
            if (this.rotateAngleY != 0.0f) {
                GL11.glRotatef((float) (this.rotateAngleY * 57.295776f), (float) 0.0f, (float) 1.0f, (float) 0.0f);
            }
            if (this.rotateAngleX != 0.0f) {
                GL11.glRotatef((float) (this.rotateAngleX * 57.295776f), (float) 1.0f, (float) 0.0f, (float) 0.0f);
            }
            if (this.rotateAngleZ != 0.0f) {
                GL11.glRotatef((float) (this.rotateAngleZ * 57.295776f), (float) 0.0f, (float) 0.0f, (float) 1.0f);
            }
            GL11.glCallList((int) this.displayList);
            GL11.glPopMatrix();
        }
    }

    public void postRender(float par1) {
        if (!this.isHidden && this.showModel) {
            if (!this.compiled) {
                this.compileDisplayList(par1);
            }
            if (this.rotateAngleX == 0.0f && this.rotateAngleY == 0.0f && this.rotateAngleZ == 0.0f) {
                if (this.rotationPointX != 0.0f || this.rotationPointY != 0.0f || this.rotationPointZ != 0.0f) {
                    GL11.glTranslatef((float) (this.rotationPointX * par1), (float) (this.rotationPointY * par1), (float) (this.rotationPointZ * par1));
                }
            } else {
                GL11.glTranslatef((float) (this.rotationPointX * par1), (float) (this.rotationPointY * par1), (float) (this.rotationPointZ * par1));
                if (this.rotateAngleZ != 0.0f) {
                    GL11.glRotatef((float) (this.rotateAngleZ * 57.295776f), (float) 0.0f, (float) 0.0f, (float) 1.0f);
                }
                if (this.rotateAngleY != 0.0f) {
                    GL11.glRotatef((float) (this.rotateAngleY * 57.295776f), (float) 0.0f, (float) 1.0f, (float) 0.0f);
                }
                if (this.rotateAngleX != 0.0f) {
                    GL11.glRotatef((float) (this.rotateAngleX * 57.295776f), (float) 1.0f, (float) 0.0f, (float) 0.0f);
                }
            }
        }
    }

    private void compileDisplayList(float par1) {
        this.displayList = GLAllocation.generateDisplayLists((int) 1);
        GL11.glNewList((int) this.displayList, (int) 4864);
        Tessellator var2 = Tessellator.instance;
        for (int var3 = 0; var3 < this.cubeList.size(); ++var3) {
            this.cubeList.get(var3).render(var2, par1);
        }
        GL11.glEndList();
        this.compiled = true;
    }

    public ScalableModelRenderer setTextureSize(int par1, int par2) {
        this.textureWidth = par1;
        this.textureHeight = par2;
        return this;
    }
}

