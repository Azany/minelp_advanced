package com.minelittlepony.minelp.gui;

import com.minelittlepony.minelp.MineLittlePony;
import com.minelittlepony.minelp.PonyManager;
import com.thevoxelbox.common.gui.GuiVoxelBoxSettingsPanel;
import com.thevoxelbox.common.util.properties.VoxelPropertyLabel;

public class MineLittlePonyGUI
        extends GuiVoxelBoxSettingsPanel {
    public MineLittlePonyGUI() {
        PonyManager ponyManager = PonyManager.getInstance();
        this.config = MineLittlePony.getConfig();
        int col1 = 30;
        this.properties.add(new VoxelPropertyIntSlider(this.config, "ponylevel", "Pony Level", PANEL_LEFT, PANEL_TOP + 24));
        this.properties.add(new VoxelPropertyLabel("Pony Options", PANEL_LEFT + 15, PANEL_TOP + 58));
        this.properties.add(new FakeVoxelPropertyCheckBox(this.config, "hd", "Enable MineLP skin server (requires restart)", PANEL_LEFT + col1, PANEL_TOP + 72));
        this.properties.add(new FakeVoxelPropertyCheckBox(this.config, "sizes", "Allow all different sizes of pony", PANEL_LEFT + col1, PANEL_TOP + 90));
        this.properties.add(new FakeVoxelPropertyCheckBox(this.config, "ponyarmor", "Use Mine Little Pony compatible armor", PANEL_LEFT + col1, PANEL_TOP + 108));
        this.properties.add(new FakeVoxelPropertyCheckBox(this.config, "snuzzles", "Display snuzzles on ponies", PANEL_LEFT + col1, PANEL_TOP + 126));
        this.properties.add(new FakeVoxelPropertyCheckBox(this.config, "showscale", "Use show-accurate scaling", PANEL_LEFT + col1, PANEL_TOP + 144));
        this.properties.add(new FakeVoxelPropertyCheckBox(this.config, "eqg", "Turn humans into Equestria Girls", PANEL_LEFT + col1, PANEL_TOP + 162));
    }

    public String getPanelTitle() {
        return "Mine Little Pony Settings";
    }
}

