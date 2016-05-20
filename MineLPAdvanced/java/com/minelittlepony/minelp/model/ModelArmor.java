/*
 * Decompiled with CFR 0_110.
 */
package com.minelittlepony.minelp.model;

import com.minelittlepony.minelp.model.ModelPlayer;

public abstract class ModelArmor {
    public final String path;
    public static int slot;
    public ModelPlayer base;
    public ModelPlayer modelArmorChestplate;
    public ModelPlayer modelArmor;

    public ModelArmor(String path) {
        this.path = path;
    }

    public abstract float layer();

    public abstract int subimage();

    public abstract void boxes(boolean var1);
}

