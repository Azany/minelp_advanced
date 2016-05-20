
package com.minelittlepony.minelp.util;

import com.mumfrey.liteloader.core.runtime.Obf;

public class MineLPObf
        extends Obf {
    public static final MineLPObf bipedArmorFilenamePrefix = new MineLPObf("field_82424_k", "l", "bipedArmorFilenamePrefix");
    public static final MineLPObf isJumping = new MineLPObf("field_70703_bu", "bc", "isJumping");

    protected MineLPObf(String seargeName, String obfName, String mcpName) {
        super(seargeName, obfName, mcpName);
    }

    protected MineLPObf(String seargeName, String obfName) {
        super(seargeName, obfName, seargeName);
    }
}

