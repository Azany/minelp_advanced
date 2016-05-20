
package com.thevoxelbox.hdskins.mod;

import com.mumfrey.liteloader.core.LiteLoader;
import com.mumfrey.liteloader.modconfig.ConfigPanel;
import com.mumfrey.liteloader.util.ModUtilities;
import com.thevoxelbox.hdskins.HDSkinManager;
import com.thevoxelbox.hdskins.gui.EntityPlayerModel;
import com.thevoxelbox.hdskins.gui.GuiSkins;
import com.thevoxelbox.hdskins.gui.HDSkinsConfigPanel;
import com.thevoxelbox.hdskins.gui.RenderPlayerModel;
import com.thevoxelbox.voxelmenu.IPanoramaRenderer;
import net.minecraft.client.Minecraft;

import java.io.File;
import java.lang.reflect.Method;

public class HDSkinsModCore
        implements HDSkinsMod {
    public static IPanoramaRenderer getPanoramaRenderer(IPanoramaRenderer fallbackRenderer) {
        try {
            Class voxelMenuCore = Class.forName("com.thevoxelbox.voxelmenu.VoxelMenuModCore");
            Method mGetPanoramaRenderer = voxelMenuCore.getDeclaredMethod("getPanoramaRenderer");
            IPanoramaRenderer panoramaRenderer = (IPanoramaRenderer) mGetPanoramaRenderer.invoke(null);
            if (panoramaRenderer != null) {
                return panoramaRenderer;
            }
        } catch (ClassNotFoundException ex) {
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return fallbackRenderer;
    }

    public String getName() {
        return "HD Skins";
    }

    public String getVersion() {
        return "4.0.0";
    }

    public void init(File configPath) {
        try {
            Class customMainMenuClass = Class.forName("com.thevoxelbox.voxelmenu.GuiMainMenuVoxelBox");
            Method mRegisterCustomScreen = customMainMenuClass.getDeclaredMethod("registerCustomScreen", Class.class, String.class);
            mRegisterCustomScreen.invoke(null, GuiSkins.class, "HD Skins Manager");
        } catch (ClassNotFoundException ex) {
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void upgradeSettings(String version, File configPath, File oldConfigPath) {
        HDSkinManager.clearSkinCache();
    }

    public Class<? extends ConfigPanel> getConfigPanelClass() {
        return HDSkinsConfigPanel.class;
    }

    public void onInitCompleted(Minecraft minecraft, LiteLoader loader) {
        ModUtilities.addRenderer(EntityPlayerModel.class, new RenderPlayerModel());
    }

    public void onTick(Minecraft minecraft, float partialTicks, boolean inGame, boolean clock) {
    }
}

