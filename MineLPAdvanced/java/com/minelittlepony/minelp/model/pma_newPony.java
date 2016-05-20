/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelRenderer
 */
package com.minelittlepony.minelp.model;

import com.minelittlepony.minelp.renderer.PlaneRenderer;
import net.minecraft.client.model.ModelRenderer;

public class pma_newPony
        extends ModelArmor {
    public pma_newPony(String path) {
        super(path);
        this.modelArmorChestplate = new pm_newPonyArmor(path);
        this.modelArmor = new pm_newPonyArmor(path);
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
    public void boxes(boolean ponyArmor) {
        this.base = ModelArmor.slot != 2 ? this.modelArmorChestplate : this.modelArmor;
        ((pm_newPonyArmor) this.base).head.showModel = ModelArmor.slot == 0;
        ((pm_newPonyArmor) this.base).LeftArm.showModel = ModelArmor.slot == 2 || ModelArmor.slot == 3;
        ((pm_newPonyArmor) this.base).RightArm.showModel = ((pm_newPonyArmor) this.base).LeftArm.showModel;
        if (!ponyArmor) {
            ((pm_newPonyArmor) this.base).helmet.showModel = ModelArmor.slot == 0;
            ((pm_newPonyArmor) this.base).Body.showModel = ModelArmor.slot == 1 || ModelArmor.slot == 2;
            ((pm_newPonyArmor) this.base).Bodypiece.showModel = ModelArmor.slot == 1 || ModelArmor.slot == 2;
            ((pm_newPonyArmor) this.base).LeftLeg.showModel = ModelArmor.slot == 2 || ModelArmor.slot == 3;
            ((pm_newPonyArmor) this.base).RightLeg.showModel = ((pm_newPonyArmor) this.base).LeftLeg.showModel;
            ((pm_newPonyArmor)this.base).LeftLegJoint.showModel = ModelArmor.slot == 2 || ModelArmor.slot == 3;
            ((pm_newPonyArmor)this.base).RightLegJoint.showModel = ((pm_newPonyArmor)this.base).LeftLegJoint.showModel;
            for (ModelRenderer part2 : ((pm_newPonyArmor) this.base).extHead) {
                part2.showModel = false;
            }
            ((pm_newPonyArmor) this.base).extBody.showModel = false;
            for (ModelRenderer part2 : ((pm_newPonyArmor) this.base).extLegs) {
                part2.showModel = false;
            }
            for (PlaneRenderer var7 : ((pm_newPonyArmor)this.base).extSubLegs) {
                var7.showModel = false;
            }
        } else {
            ((pm_newPonyArmor) this.base).helmet.showModel = false;
            ((pm_newPonyArmor) this.base).Body.showModel = false;
            ((pm_newPonyArmor) this.base).Bodypiece.showModel = false;
            ((pm_newPonyArmor) this.base).LeftLeg.showModel = false;
            ((pm_newPonyArmor) this.base).RightLeg.showModel = false;
            ((pm_newPonyArmor)this.base).LeftLegJoint.showModel = false;
            ((pm_newPonyArmor)this.base).RightLegJoint.showModel = false;
            for (ModelRenderer part3 : ((pm_newPonyArmor) this.base).extHead) {
                part3.showModel = ModelArmor.slot == 0;
            }
            ((pm_newPonyArmor) this.base).extBody.showModel = ModelArmor.slot == 1 || ModelArmor.slot == 2;
            for (ModelRenderer part3 : ((pm_newPonyArmor) this.base).extLegs) {
                part3.showModel = ModelArmor.slot == 2 || ModelArmor.slot == 3;
            }
            for (PlaneRenderer var7 : ((pm_newPonyArmor)this.base).extSubLegs) {
                var7.showModel = ModelArmor.slot == 3;
            }
        }
    }
}

