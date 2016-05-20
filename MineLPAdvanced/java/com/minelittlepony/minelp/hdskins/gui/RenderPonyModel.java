
package com.minelittlepony.minelp.hdskins.gui;

import com.minelittlepony.minelp.Pony;
import com.minelittlepony.minelp.PonyManager;
import com.minelittlepony.minelp.model.PMAPI;
import com.minelittlepony.minelp.model.PlayerModel;
import com.thevoxelbox.hdskins.gui.EntityPlayerModel;
import com.thevoxelbox.hdskins.gui.RenderPlayerModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;

public class RenderPonyModel
extends RenderPlayerModel {
    protected void renderModel(EntityLivingBase par1EntityLivingBase, float par2, float par3, float par4, float par5, float par6, float par7) {
        this.bindEntityTexture((Entity)par1EntityLivingBase);
        EntityPlayerModel playerModelEntity = (EntityPlayerModel)par1EntityLivingBase;
        Pony thePony = PonyManager.getInstance().getPonyFromResourceRegistry(this.getEntityTexture((Entity)playerModelEntity));
        thePony.checkSkin();
        PlayerModel pm = this.getModel(thePony);
        this.mainModel = pm.model;
        thePony.isFlying = false;
        pm.model.isFlying = false;
        pm.model.isPegasus = thePony.isPegasus();
        pm.model.isUnicorn = thePony.isUnicorn();
        pm.model.isMale = thePony.isMale();
        pm.model.size = thePony.size();
        pm.model.glowColor = thePony.glowColor();
        pm.model.wantTail = thePony.wantTail();
        pm.model.isVillager = false;
        pm.model.StylesofMane = thePony.StylesofMane;
        pm.model.isRainboomTail = thePony.RainboomTail;
        pm.model.isKirin = thePony.isKirin();
        pm.model.isGriffin = thePony.isGriffin();

        this.mainModel.render(par1EntityLivingBase, par2, par3, par4, par5, par6, 1.0f);
    }

    public PlayerModel getModel(Pony pony) {
        if (!pony.isPonySkin()) {
            return PMAPI.human;
        }
        if (pony.advancedTexturing()) {
            return PMAPI.newPonyAdv;
        }
        return PMAPI.newPony;
    }
}

