package com.minelittlepony.minelp;

import com.minelittlepony.minelp.util.MineLPLogger;
import com.thevoxelbox.common.util.ModConfig;

public class PonyConfig
        extends ModConfig {
    public PonyConfig() {
        super("Mine Little Pony", "minelittlepony.properties");
    }

    @Override
    protected void setDefaults() {
        this.defaults.setProperty("ponylevel", "2");
        this.defaults.setProperty("sizes", "1");
        this.defaults.setProperty("ponyarmor", "1");
        this.defaults.setProperty("snuzzles", "1");
        this.defaults.setProperty("hd", "1");
        this.defaults.setProperty("showscale", "1");
        this.defaults.setProperty("eqg", "0");
        this.defaults.setProperty("villagers", "1");
        this.defaults.setProperty("zombies", "1");
        this.defaults.setProperty("pigzombies", "1");
        this.defaults.setProperty("skeletons", "1");
        this.defaults.setProperty("oldSkinUploaded", "0");
    }

    @Override
    public String getOptionDisplayString(String binding) {
        return "";
    }

    public int getIntPropertySafe(String key) {
        return this.getIntPropertySafe(key, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public int getIntPropertySafe(String key, int minValue, int maxValue) {
        int value;
        try {
            value = this.getIntProperty(key);
        } catch (Exception e) {
            try {
                boolean boolValue = this.getBoolProperty(key);
                value = boolValue ? 1 : 0;
            } catch (Exception e2) {
                int defaultValue = this.getDefaultIntProperty(key);
                this.setProperty(key, defaultValue);
                MineLPLogger.error("Invalid value for config key \"%s\", using default value %d", key, defaultValue);
                return defaultValue;
            }
        }
        if (value >= minValue && value <= maxValue) {
            return value;
        }
        int defaultValue = value = this.getDefaultIntProperty(key);
        this.setProperty(key, defaultValue);
        MineLPLogger.error("Invalid value for config key \"%s\", using default value %d. Found %d, expected value between %d and %d.", key, defaultValue, value, minValue, maxValue);
        return defaultValue;
    }

    public boolean isSet(String key) {
        return this.config.containsKey(key);
    }
}

