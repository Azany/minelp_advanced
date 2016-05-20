/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.monster.EntityPigZombie
 *  net.minecraft.entity.monster.EntityZombie
 *  net.minecraft.util.ResourceLocation
 */
package com.minelittlepony.minelp.renderer;

import com.minelittlepony.minelp.PonyManager;
import com.minelittlepony.minelp.model.PMAPI;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.util.ResourceLocation;

public class RenderPonyZombie
        extends RenderPonyMob {
    public RenderPonyZombie() {
        super(PMAPI.zombiePony);
    }

    protected void rotateCorpse(EntityZombie zombie, float xPosition, float yPosition, float zPosition) {
        if (zombie.isConverting()) {
            yPosition += (float) (Math.cos((double) zombie.ticksExisted * 3.25) * 3.141592653589793 * 0.25);
        }
        super.rotateCorpse((EntityLivingBase) zombie, xPosition, yPosition, zPosition);
    }

    protected void rotateCorpse(EntityLivingBase entity, float xPosition, float yPosition, float zPosition) {
        this.rotateCorpse((EntityZombie) entity, xPosition, yPosition, zPosition);
    }

    protected ResourceLocation getEntityTexture(EntityZombie zombie) {
        return zombie instanceof EntityPigZombie ? PonyManager.zombiePigmanPonyResource : (zombie.isVillager() ? PonyManager.zombieVillagerPonyResource : PonyManager.zombiePonyResource);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityLiving entity) {
        return this.getEntityTexture((EntityZombie) entity);
    }
}

