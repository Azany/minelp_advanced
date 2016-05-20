/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.entity.Render
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.passive.EntityVillager
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package com.minelittlepony.minelp.renderer;

import com.minelittlepony.minelp.Pony;
import com.minelittlepony.minelp.PonyManager;
import com.minelittlepony.minelp.model.PMAPI;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class RenderPonyVillager
        extends RenderPonyMob {
    public RenderPonyVillager() {
        super(PMAPI.newPonyAdv);
    }

    protected void preRenderCallback(EntityVillager villager, float partialTicks) {
        if (villager.getGrowingAge() < 0) {
            this.mobModel.size = 0;
            this.shadowSize = 0.25f;
        } else {
            this.mobModel.size = 1;
            this.shadowSize = PonyManager.getInstance().getShowScale() == 1 ? 0.4f : 0.5f;
        }
        GL11.glScalef((float) 0.9375f, (float) 0.9375f, (float) 0.9375f);
    }

    protected void preRenderCallback(EntityLivingBase entity, float partialTicks) {
        this.preRenderCallback((EntityVillager) entity, partialTicks);
    }

    protected ResourceLocation getEntityTexture(EntityVillager villager) {
        Pony aVillagerPony = PonyManager.getInstance().getPonyFromResourceRegistry(villager);
        return aVillagerPony.getTextureResourceLocation();
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityLiving entity) {
        return this.getEntityTexture((EntityVillager) entity);
    }
}

