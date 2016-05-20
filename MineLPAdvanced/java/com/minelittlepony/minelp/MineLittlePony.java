package com.minelittlepony.minelp;

import com.minelittlepony.minelp.gui.MineLittlePonyGUI;
import com.minelittlepony.minelp.gui.MineLittlePonyGUIMob;
import com.minelittlepony.minelp.hdskins.gui.EntityPonyModel;
import com.minelittlepony.minelp.hdskins.gui.GuiSkinsMineLP;
import com.minelittlepony.minelp.hdskins.gui.RenderPonyModel;
import com.minelittlepony.minelp.renderer.RenderPonySkeleton;
import com.minelittlepony.minelp.renderer.RenderPonyVillager;
import com.minelittlepony.minelp.renderer.RenderPonyZombie;
import com.minelittlepony.minelp.util.MineLPLogger;
import com.mumfrey.liteloader.InitCompleteListener;
import com.mumfrey.liteloader.core.LiteLoader;
import com.mumfrey.liteloader.util.ModUtilities;
import com.thevoxelbox.common.gui.SettingsPanelManager;
import com.thevoxelbox.common.util.ModConfig;
import com.thevoxelbox.hdskins.HDSkinManager;
import com.thevoxelbox.hdskins.gui.GuiSkins;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityVillager;
import org.lwjgl.input.Keyboard;

import java.io.File;

public class MineLittlePony
        implements InitCompleteListener {
    public static final String MOD_VERSION = "1710.0.0.1";
    public static final String MOD_NAME = "Mine Little Pony Advanced";
    public static final String SKIN_SERVER_URL = "skins.hnm.su/minelp";
    public static final String GATEWAY_URL = "skins.hnm.su/minelp";
    public static final String UPLOAD_URL = "http://skins.hnm.su/minelp/upload/";
    private static final KeyBinding guiKeybinding = new KeyBinding("Settings", 67, "Mine Little Pony");
    private static final KeyBinding skinKeybinding = new KeyBinding("Skin Manager", 59, "Mine Little Pony");
    private static MineLittlePony instance;
    private PonyConfig config;
    private PonyManager ponyManager;
    private String spUsername;

    public MineLittlePony() {
        instance = this;
    }

    public static MineLittlePony getInstance() {
        return instance;
    }

    public static ModConfig getConfig() {
        return MineLittlePony.getInstance().config;
    }

    public static String getSPUsername() {
        return MineLittlePony.getInstance().spUsername;
    }

    @Override
    public String getName() {
        return MOD_NAME;
    }

    @Override
    public String getVersion() {
        return MOD_VERSION;
    }

    @Override
    public void upgradeSettings(String version, File configPath, File oldConfigPath) {
    }

    @Override
    public void init(File configPath) {
        LiteLoader.getInput().registerKeyBinding(guiKeybinding);
        LiteLoader.getInput().registerKeyBinding(skinKeybinding);
        SettingsPanelManager.addSettingsPanel("Pony", MineLittlePonyGUI.class);
        SettingsPanelManager.addSettingsPanel("Pony Mobs", MineLittlePonyGUIMob.class);
        this.ponyManager = PonyManager.getInstance();
        this.config = new PonyConfig();
//        if (!this.config.isSet("oldSkinUploaded") || this.config.getIntPropertySafe("oldSkinUploaded") == 0) {
//            this.config.setProperty("oldSkinUploaded", 0);
//            GuiSkinsMineLP.enableLegacySkin();
//        }
        int readInt = this.config.getIntPropertySafe("ponylevel", 0, 2);
        this.ponyManager.setPonyLevel(readInt);
        MineLPLogger.info("Pony level is set to %d.", readInt);
        readInt = this.config.getIntPropertySafe("sizes", 0, 1);
        this.ponyManager.setUseSizes(readInt);
        Object[] arrobject = new Object[1];
        arrobject[0] = readInt == 0 ? "disabled" : "enabled";
        MineLPLogger.info("Different pony sizes are %s.", arrobject);
        readInt = this.config.getIntPropertySafe("ponyarmor", 0, 1);
        this.ponyManager.setPonyArmor(readInt);
        Object[] arrobject2 = new Object[1];
        arrobject2[0] = readInt == 0 ? "disabled" : "enabled";
        MineLPLogger.info("Pony armor is %s.", arrobject2);
        readInt = this.config.getIntPropertySafe("snuzzles", 0, 1);
        this.ponyManager.setShowSnuzzles(readInt);
        Object[] arrobject3 = new Object[1];
        arrobject3[0] = readInt == 0 ? "disabled (You are a bad pony)" : "enabled";
        MineLPLogger.info("Snuzzels are %s.", arrobject3);
        readInt = this.config.getIntPropertySafe("hd", 0, 1);
        this.ponyManager.setHD(readInt);
        Object[] arrobject4 = new Object[1];
        arrobject4[0] = readInt == 0 ? "disabled" : "enabled";
        MineLPLogger.info("MineLittlePony skin server is %s.", arrobject4);
        readInt = this.config.getIntPropertySafe("showscale", 0, 1);
        this.ponyManager.setShowScale(readInt);
        Object[] arrobject5 = new Object[1];
        arrobject5[0] = readInt == 0 ? "disabled" : "enabled";
        MineLPLogger.info("Show-accurate scaling is %s.", arrobject5);
        readInt = this.config.getIntPropertySafe("eqg", 0, 1);
        this.ponyManager.setEQG(readInt);
        Object[] arrobject6 = new Object[1];
        arrobject6[0] = readInt == 0 ? "disabled" : "enabled";
        MineLPLogger.info("Equestria Girls human models are %s.", arrobject6);
        readInt = this.config.getIntPropertySafe("villagers", 0, 1);
        this.ponyManager.setPonyVillagers(readInt);
        Object[] arrobject7 = new Object[1];
        arrobject7[0] = readInt == 0 ? "disabled" : "enabled";
        MineLPLogger.info("Pony villagers are %s.", arrobject7);
        readInt = this.config.getIntPropertySafe("zombies", 0, 1);
        this.ponyManager.setPonyZombies(readInt);
        Object[] arrobject8 = new Object[1];
        arrobject8[0] = readInt == 0 ? "disabled" : "enabled";
        MineLPLogger.info("Pony zombies are %s.", arrobject8);
        readInt = this.config.getIntPropertySafe("pigzombies", 0, 1);
        this.ponyManager.setPonyPigzombies(readInt);
        Object[] arrobject9 = new Object[1];
        arrobject9[0] = readInt == 0 ? "disabled" : "enabled";
        MineLPLogger.info("Pony pigzombies are %s.", arrobject9);
        readInt = this.config.getIntPropertySafe("skeletons", 0, 1);
        this.ponyManager.setPonySkeletons(readInt);
        Object[] arrobject10 = new Object[1];
        arrobject10[0] = readInt == 0 ? "disabled" : "enabled";
        MineLPLogger.info("Pony skeletons are %s.", arrobject10);
    }

    @Override
    public void onInitCompleted(Minecraft minecraft, LiteLoader loader) {
        this.spUsername = minecraft.getSession().getUsername();
		if (this.ponyManager.getHD() == 1) {
            HDSkinManager.clearSkinCache();
            HDSkinManager.setSkinUrl("skins.hnm.su/minelp");
            HDSkinManager.setGatewayURL("skins.hnm.su/minelp");
            MineLPLogger.info("Set MineLP skin server URL.");
        }
        ModUtilities.addRenderer(EntityPonyModel.class, new RenderPonyModel());
        if (this.ponyManager.getPonyVillagers() == 1) {
            ModUtilities.addRenderer(EntityVillager.class, new RenderPonyVillager());
            MineLPLogger.info("Villagers are now ponies.");
        }
        if (this.ponyManager.getPonyZombies() == 1) {
            ModUtilities.addRenderer(EntityZombie.class, new RenderPonyZombie());
            MineLPLogger.info("Zombies are now ponies.");
        }
        if (this.ponyManager.getPonyPigzombies() == 1) {
            ModUtilities.addRenderer(EntityPigZombie.class, new RenderPonyZombie());
            MineLPLogger.info("Zombie pigmen are now ponies.");
        }
        if (this.ponyManager.getPonySkeletons() == 1) {
            ModUtilities.addRenderer(EntitySkeleton.class, new RenderPonySkeleton());
            MineLPLogger.info("Skeletons are now ponies.");
        }
    }

    @Override
    public void onTick(Minecraft minecraft, float partialTicks, boolean inGame, boolean clock) {
        this.ponyManager.setPonyLevel(this.config.getIntProperty("ponylevel"));
        this.ponyManager.setUseSizes(this.config.getIntProperty("sizes"));
        this.ponyManager.setPonyArmor(this.config.getIntProperty("ponyarmor"));
        this.ponyManager.setShowSnuzzles(this.config.getIntProperty("snuzzles"));
        this.ponyManager.setShowScale(this.config.getIntProperty("showscale"));
        this.ponyManager.setEQG(this.config.getIntProperty("eqg"));
        if (inGame && minecraft.currentScreen == null && guiKeybinding.isPressed()) {
            minecraft.displayGuiScreen(new MineLittlePonyGUI());
        }
        if (!inGame && minecraft.currentScreen != null && (minecraft.currentScreen instanceof GuiMainMenu && Keyboard.isKeyDown(skinKeybinding.getKeyCode()) || minecraft.currentScreen instanceof GuiSkins)) {
            minecraft.displayGuiScreen(new GuiSkinsMineLP());
        }
    }
}

