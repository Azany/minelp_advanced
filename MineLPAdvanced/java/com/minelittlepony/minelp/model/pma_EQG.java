/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelRenderer
 */
package com.minelittlepony.minelp.model;

import com.minelittlepony.minelp.model.ModelArmor;
import com.minelittlepony.minelp.model.ModelPlayer;
import com.minelittlepony.minelp.model.pm_EQG;
import com.minelittlepony.minelp.model.pma_Human;
import com.minelittlepony.minelp.renderer.ScalableModelRenderer;
import net.minecraft.client.model.ModelRenderer;

public class pma_EQG
extends pma_Human {
    public pma_EQG(String path) {
        super(path);
        this.modelArmorChestplate = new pm_EQG(path);
        this.modelArmor = new pm_EQG(path);
    }

    @Override
    public void boxes(boolean ignoreMe) {
        this.base = ModelArmor.slot != 2 ? this.modelArmorChestplate : this.modelArmor;
        ((pm_EQG)this.base).head.showModel = ModelArmor.slot == 0;
        ((pm_EQG)this.base).helmet.showModel = ModelArmor.slot == 0;
        ((pm_EQG)this.base).body.showModel = ModelArmor.slot == 1 || ModelArmor.slot == 2;
        ((pm_EQG)this.base).leftarm.showModel = ModelArmor.slot == 1;
        ((pm_EQG)this.base).rightarm.showModel = ((pm_EQG)this.base).leftarm.showModel;
        ((pm_EQG)this.base).leftleg.showModel = ModelArmor.slot == 2 || ModelArmor.slot == 3;
        ((pm_EQG)this.base).rightleg.showModel = ((pm_EQG)this.base).leftleg.showModel;
    }
}

