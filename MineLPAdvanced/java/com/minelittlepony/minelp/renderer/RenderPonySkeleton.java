/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.monster.EntitySkeleton
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package com.minelittlepony.minelp.renderer;

import com.minelittlepony.minelp.PonyManager;
import com.minelittlepony.minelp.model.PMAPI;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class RenderPonySkeleton
        extends RenderPonyMob {
    public RenderPonySkeleton() {
        super(PMAPI.skeletonPony);
    }

    protected void preRenderCallback(EntitySkeleton skeleton, float partialTicks) {
        if (skeleton.getSkeletonType() == 1) {
            GL11.glScalef((float) 1.2f, (float) 1.2f, (float) 1.2f);
        }
    }

    protected void preRenderCallback(EntityLivingBase entity, float partialTicks) {
        this.preRenderCallback((EntitySkeleton) entity, partialTicks);
    }

    protected ResourceLocation getEntityTexture(EntitySkeleton skeleton) {
        return skeleton.getSkeletonType() == 1 ? PonyManager.skeletonWitherPonyResource : PonyManager.skeletonPonyResource;
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityLiving entity) {
        return this.getEntityTexture((EntitySkeleton) entity);
    }
}

