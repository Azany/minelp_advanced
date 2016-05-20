
package com.thevoxelbox.voxelmenu;

import net.minecraft.client.Minecraft;

public interface IPanoramaRenderer {
    public void setPanoramaResolution(Minecraft var1, int var2, int var3);

    public void initPanoramaRenderer();

    public void updatePanorama();

    public boolean renderPanorama(int var1, int var2, float var3);

    public int getUpdateCounter();
}

