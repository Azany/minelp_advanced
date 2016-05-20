
package com.minelittlepony.minelp.util;

import com.mumfrey.liteloader.client.util.PrivateFields;
import com.mumfrey.liteloader.core.runtime.Obf;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.entity.EntityLivingBase;

public class MineLPPrivateFields<P, T>
        extends PrivateFields<P, T> {
    public static final MineLPPrivateFields<RenderBiped, String[]> bipedArmorFilenamePrefix = new MineLPPrivateFields<RenderBiped, String[]>(RenderBiped.class, MineLPObf.bipedArmorFilenamePrefix);
    public static final MineLPPrivateFields<EntityLivingBase, Boolean> isJumping = new MineLPPrivateFields<EntityLivingBase, Boolean>(EntityLivingBase.class, MineLPObf.isJumping);

    protected MineLPPrivateFields(Class<P> owner, Obf obf) {
        super(owner, obf);
    }
}

