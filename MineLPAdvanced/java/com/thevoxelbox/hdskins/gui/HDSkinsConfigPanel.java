
package com.thevoxelbox.hdskins.gui;

import com.thevoxelbox.common.gui.GuiVoxelBoxSettingsPanel;
import com.thevoxelbox.common.interfaces.IVoxelPropertyProviderBoolean;
import com.thevoxelbox.common.util.properties.VoxelPropertyToggleButton;
import com.thevoxelbox.hdskins.HDSkinManager;

public class HDSkinsConfigPanel
        extends GuiVoxelBoxSettingsPanel
        implements IVoxelPropertyProviderBoolean {
    public HDSkinsConfigPanel() {
        this.properties.add(new VoxelPropertyToggleButton(this, "clear", "Clear local skin cache", 72, 8, 120, 70, 16));
    }

    public String getPanelTitle() {
        return "HD Skins Settings";
    }

    public String getStringProperty(String propertyName) {
        return null;
    }

    public String getOptionDisplayString(String propertyName) {
        return "Clear now";
    }

    public void toggleOption(String propertyName) {
        HDSkinManager.clearSkinCache();
    }

    public String getDefaultPropertyValue(String propertyName) {
        return null;
    }

    public void setProperty(String propertyName, boolean value) {
    }

    public boolean getBoolProperty(String propertyName) {
        return true;
    }
}

