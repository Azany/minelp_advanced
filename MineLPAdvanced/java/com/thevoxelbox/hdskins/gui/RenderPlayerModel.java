
package com.thevoxelbox.hdskins.gui;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class RenderPlayerModel
        extends RenderLiving {
    public RenderPlayerModel() {
        super(new ModelBiped(), 0.0f);
    }

    protected ResourceLocation getEntityTexture(Entity var1) {
        return ((EntityPlayerModel) var1).getSkinTexture();
    }

    public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9) {
        GL11.glPushAttrib(1048575);
        this.doRender((EntityLiving) par1Entity, par2, par4, par6, par8, par9);
        GL11.glPopAttrib();
        GL11.glPushMatrix();
        GL11.glScalef(1.0f, -1.0f, 1.0f);
        GL11.glPushAttrib(1048575);
        this.doRender((EntityLiving) par1Entity, par2, par4, par6, par8, par9);
        GL11.glPopAttrib();
        GL11.glPopMatrix();
    }

    protected void renderEquippedItems(EntityLivingBase par1EntityLiving, float par2) {
        this.renderCloak((EntityPlayerModel) par1EntityLiving, par2);
    }

    protected void renderCloak(EntityPlayerModel entity, float par2) {
        super.renderEquippedItems(entity, par2);
    }
}

