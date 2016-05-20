
package com.thevoxelbox.hdskins.asm;

class Obf
        extends com.mumfrey.liteloader.core.runtime.Obf {
    public static final Obf tdid = new Obf("net.minecraft.client.renderer.ThreadDownloadImageData", "bpr");
    public static final Obf mgr = new Obf("com.thevoxelbox.hdskins.HDSkinManager");
    public static final Obf dlskin = new Obf("func_152433_a", "a");

    public Obf(String all) {
        super(all, all, all);
    }

    public Obf(String srg, String obf) {
        super(srg, obf, srg);
    }
}

