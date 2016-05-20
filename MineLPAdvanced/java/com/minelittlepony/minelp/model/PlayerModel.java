/*
 * Decompiled with CFR 0_110.
 */
package com.minelittlepony.minelp.model;

import com.minelittlepony.minelp.model.ModelArmor;
import com.minelittlepony.minelp.model.ModelPlayer;
import java.text.DecimalFormat;

public class PlayerModel {
    public final int id;
    public String name;
    public String url;
    public ModelPlayer model;
    public ModelArmor armor;
    public float width = 0.6f;
    public float height = 1.8f;
    public float shadowsize = 0.5f;
    public float thirdpersondistance = 4.0f;
    public float yoffset = 1.62f;
    public float globalscale = 1.0f;

    public PlayerModel(String name, ModelPlayer model, int manual_id) {
        this.name = name;
        this.model = model;
        this.id = manual_id;
    }

    public PlayerModel setArmor(ModelArmor armor) {
        this.armor = armor;
        return this;
    }

    public PlayerModel setURL(String url) {
        this.url = url;
        return this;
    }

    public PlayerModel setShadow(float size) {
        this.shadowsize = size;
        return this;
    }

    public PlayerModel setSize(float width, float height) {
        this.width = width;
        this.height = height;
        return this;
    }

    public PlayerModel setOffset(float offset) {
        this.yoffset = offset;
        return this;
    }

    public PlayerModel setDistance(float distance) {
        this.thirdpersondistance = distance;
        return this;
    }

    public PlayerModel setScale(float scale) {
        this.globalscale = scale;
        return this;
    }

    public boolean hasArmor() {
        if (this.armor != null && this.armor.base != null && this.armor.path != null) {
            return true;
        }
        return false;
    }

    public String getSize(DecimalFormat df) {
        return df.format(this.width) + " * " + df.format(this.height) + " * " + df.format(this.width);
    }
}

