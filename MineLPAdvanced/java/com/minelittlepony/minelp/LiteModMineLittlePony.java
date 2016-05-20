package com.minelittlepony.minelp;

import com.thevoxelbox.common.VoxelCommonLiteMod;

public class LiteModMineLittlePony
        extends VoxelCommonLiteMod {
    public LiteModMineLittlePony() {
        super("com.minelittlepony.minelp.MineLittlePony");
    }

    @Override
    public String getVersion() {
        return MineLittlePony.MOD_VERSION;
    }

    @Override
    public String getName() {
        return MineLittlePony.MOD_NAME;
    }
}

