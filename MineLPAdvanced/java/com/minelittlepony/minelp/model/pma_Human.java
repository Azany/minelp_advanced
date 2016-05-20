/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelRenderer
 */
package com.minelittlepony.minelp.model;

import com.minelittlepony.minelp.model.ModelArmor;
import com.minelittlepony.minelp.model.ModelPlayer;
import com.minelittlepony.minelp.model.pm_Human;
import net.minecraft.client.model.ModelRenderer;

public class pma_Human
extends ModelArmor {
    public pma_Human(String path) {
        super(path);
        this.modelArmorChestplate = new pm_Human(path);
        this.modelArmor = new pm_Human(path);
    }

    @Override
    public float layer() {
        return 1.0f;
    }

    @Override
    public int subimage() {
        return ModelArmor.slot == 2 ? 2 : 1;
    }

    @Override
    public void boxes(boolean ignoreMe) {
        this.base = ModelArmor.slot != 2 ? this.modelArmorChestplate : this.modelArmor;
        ((pm_Human)this.base).head.showModel = ModelArmor.slot == 0;
        ((pm_Human)this.base).helmet.showModel = ModelArmor.slot == 0;
        ((pm_Human)this.base).body.showModel = ModelArmor.slot == 1 || ModelArmor.slot == 2;
        ((pm_Human)this.base).leftarm.showModel = ModelArmor.slot == 1;
        ((pm_Human)this.base).rightarm.showModel = ((pm_Human)this.base).leftarm.showModel;
        ((pm_Human)this.base).leftleg.showModel = ModelArmor.slot == 2 || ModelArmor.slot == 3;
        ((pm_Human)this.base).rightleg.showModel = ((pm_Human)this.base).leftleg.showModel;
    }
}

