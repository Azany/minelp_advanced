
package com.minelittlepony.minelp;

import com.minelittlepony.minelp.model.PMAPI;
import com.minelittlepony.minelp.util.MineLPLogger;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.util.ResourceLocation;

import java.util.*;

public class PonyManager {
    public static final String RESOURCE_NAMESPACE = "minelittlepony";
    public static final ResourceLocation zombiePonyResource = new ResourceLocation(RESOURCE_NAMESPACE, "textures/entity/zombie/zombie_pony.png");
    public static final ResourceLocation zombieVillagerPonyResource = new ResourceLocation(RESOURCE_NAMESPACE, "textures/entity/zombie/zombie_villager_pony.png");
    public static final ResourceLocation zombiePigmanPonyResource = new ResourceLocation(RESOURCE_NAMESPACE, "textures/entity/zombie_pigman_pony.png");
    public static final ResourceLocation skeletonPonyResource = new ResourceLocation(RESOURCE_NAMESPACE, "textures/entity/skeleton/skeleton_pony.png");
    public static final ResourceLocation skeletonWitherPonyResource = new ResourceLocation(RESOURCE_NAMESPACE, "textures/entity/skeleton/skeleton_wither_pony.png");
    public static final ResourceLocation defaultPonyResourceLocation = new ResourceLocation(RESOURCE_NAMESPACE, "textures/entity/pony/charpony.png");
    private static final int MAX_BGPONY_COUNT = 141;
    public static List<ResourceLocation> backgroundPonyResourceLocations = new ArrayList<ResourceLocation>();
    public static List<ResourceLocation> villagerResourceLocations;
    private static int numberOfPonies;
    private static PonyManager instance;

    static {
        for (int check = 0; check < MAX_BGPONY_COUNT; ++check) {
            backgroundPonyResourceLocations.add(new ResourceLocation(RESOURCE_NAMESPACE, "textures/entity/pony/bpony_" + check + ".png"));
        }
        numberOfPonies = backgroundPonyResourceLocations.size();
        MineLPLogger.info("Detected %d of %d background ponies installed.", numberOfPonies, MAX_BGPONY_COUNT);
        villagerResourceLocations = new ArrayList<ResourceLocation>();
        villagerResourceLocations.add(new ResourceLocation(RESOURCE_NAMESPACE, "textures/entity/villager/farmer_pony.png"));
        villagerResourceLocations.add(new ResourceLocation(RESOURCE_NAMESPACE, "textures/entity/villager/librarian_pony.png"));
        villagerResourceLocations.add(new ResourceLocation(RESOURCE_NAMESPACE, "textures/entity/villager/priest_pony.png"));
        villagerResourceLocations.add(new ResourceLocation(RESOURCE_NAMESPACE, "textures/entity/villager/smith_pony.png"));
        villagerResourceLocations.add(new ResourceLocation(RESOURCE_NAMESPACE, "textures/entity/villager/butcher_pony.png"));
        villagerResourceLocations.add(new ResourceLocation(RESOURCE_NAMESPACE, "textures/entity/villager/villager_pony.png"));
    }

    private Map<ResourceLocation, Pony> ponyResourceRegistry = new HashMap<ResourceLocation, Pony>();
    private Map<ResourceLocation, Pony> backgroudPonyResourceRegistry = new HashMap<ResourceLocation, Pony>();
    private int ponyLevel = 2;
    private int useSizes = 1;
    private int ponyArmor = 1;
    private int showSnuzzles = 1;
    private int showScale = 1;
    private int ponyVillagers = 1;
    private int ponyZombies = 1;
    private int ponyPigzombies = 1;
    private int ponySkeletons = 1;
    private int equestriaGirls = 0;
    private int useHDSkinServer = 1;

    private PonyManager() {
        MineLPLogger.info("Initializing models...");
        PMAPI.newPony.model.init();
        PMAPI.newPony.armor.modelArmorChestplate.init(0.0f, 1.0f);
        PMAPI.newPony.armor.modelArmor.init(0.0f, 0.5f);
        PMAPI.newPonyAdv.model.init();
        PMAPI.newPonyAdv.armor.modelArmorChestplate.init(0.0f, 1.0f);
        PMAPI.newPonyAdv.armor.modelArmor.init(0.0f, 0.5f);
        PMAPI.zombiePony.model.init();
        PMAPI.zombiePony.armor.modelArmorChestplate.init(0.0f, 1.0f);
        PMAPI.zombiePony.armor.modelArmor.init(0.0f, 0.5f);
        PMAPI.skeletonPony.model.init();
        PMAPI.skeletonPony.armor.modelArmorChestplate.init(0.0f, 1.0f);
        PMAPI.skeletonPony.armor.modelArmor.init(0.0f, 0.5f);
        PMAPI.human.model.init();
        PMAPI.human.armor.modelArmorChestplate.init(0.0f, 1.0f);
        PMAPI.human.armor.modelArmor.init(0.0f, 0.5f);
        PMAPI.eqg.model.init();
        PMAPI.eqg.armor.modelArmorChestplate.init(0.0f, 1.0f);
        PMAPI.eqg.armor.modelArmor.init(0.0f, 0.5f);
        MineLPLogger.info("Done initializing models.");
    }

    public static PonyManager getInstance() {
        if (instance == null) {
            instance = new PonyManager();
        }
        return instance;
    }

    private Pony getPonyFromResourceRegistry(ResourceLocation skinResourceLocation, AbstractClientPlayer player) {
        Pony myLittlePony;
        if (!this.ponyResourceRegistry.containsKey(skinResourceLocation)) {
            myLittlePony = player != null ? new Pony(player) : new Pony(skinResourceLocation);
            this.ponyResourceRegistry.put(skinResourceLocation, myLittlePony);
        } else {
            myLittlePony = this.ponyResourceRegistry.get(skinResourceLocation);
        }
        return myLittlePony;
    }

    public Pony getPonyFromResourceRegistry(ResourceLocation skinResourceLocation) {
        return this.getPonyFromResourceRegistry(skinResourceLocation, null);
    }

    public Pony getPonyFromResourceRegistry(AbstractClientPlayer player) {
        Pony myLittlePony = this.getPonyFromResourceRegistry(player.getLocationSkin(), player);
        if (this.ponyLevel == 2 && !myLittlePony.isPonySkin()) {
            myLittlePony = this.getPonyFromBackgroundResourceRegistry(player);
        }
        if (player.getCommandSenderName().equals(MineLittlePony.getSPUsername())) {
            myLittlePony.isSpPlayer = true;
        }
        return myLittlePony;
    }

    public Pony getPonyFromResourceRegistry(EntityVillager entity) {
        ResourceLocation villagerResourceLocation;
        int profession = entity.getProfession();
        try {
            villagerResourceLocation = villagerResourceLocations.get(profession);
        } catch (IndexOutOfBoundsException e) {
            villagerResourceLocation = villagerResourceLocations.get(5);
        }
        Pony myLittlePony = this.getPonyFromResourceRegistry(villagerResourceLocation);
        myLittlePony.setVillager(profession);
        return myLittlePony;
    }

    private ResourceLocation getBackgroundPonyResource(String username) {
        if (numberOfPonies > 0) {
            int backgroundIndex = username.hashCode() % this.getNumberOfPonies();
            if (backgroundIndex < 0) {
                backgroundIndex += this.getNumberOfPonies();
            }
            return backgroundPonyResourceLocations.get(backgroundIndex);
        }
        return defaultPonyResourceLocation;
    }

    public Pony getPonyFromBackgroundResourceRegistry(AbstractClientPlayer player) {
        Pony myLittlePony;
        ResourceLocation textureResourceLocation = Objects.equals(player.getCommandSenderName(), MineLittlePony.getSPUsername()) ? defaultPonyResourceLocation : this.getBackgroundPonyResource(player.getCommandSenderName());
        if (!this.backgroudPonyResourceRegistry.containsKey(textureResourceLocation)) {
            myLittlePony = new Pony(textureResourceLocation);
            this.backgroudPonyResourceRegistry.put(textureResourceLocation, myLittlePony);
        } else {
            myLittlePony = this.backgroudPonyResourceRegistry.get(textureResourceLocation);
        }
        return myLittlePony;
    }

    public int getEQG() {
        return this.equestriaGirls;
    }

    public void setEQG(int equestriaGirls) {
        this.equestriaGirls = equestriaGirls;
    }

    public int getHD() {
        return this.useHDSkinServer;
    }

    public void setHD(int useHDSkinServer) {
        this.useHDSkinServer = useHDSkinServer;
    }

    public int getNumberOfPonies() {
        return numberOfPonies;
    }

    public int getPonyArmor() {
        return this.ponyArmor;
    }

    public void setPonyArmor(int ponyArmor) {
        this.ponyArmor = ponyArmor;
    }

    public int getPonyLevel() {
        return this.ponyLevel;
    }

    public void setPonyLevel(int ponyLevel) {
        this.ponyLevel = ponyLevel;
    }

    public int getPonyPigzombies() {
        return this.ponyPigzombies;
    }

    public void setPonyPigzombies(int ponyPigzombies) {
        this.ponyPigzombies = ponyPigzombies;
    }

    public int getPonySkeletons() {
        return this.ponySkeletons;
    }

    public void setPonySkeletons(int ponySkeletons) {
        this.ponySkeletons = ponySkeletons;
    }

    public int getPonyVillagers() {
        return this.ponyVillagers;
    }

    public void setPonyVillagers(int ponyVillagers) {
        this.ponyVillagers = ponyVillagers;
    }

    public int getPonyZombies() {
        return this.ponyZombies;
    }

    public void setPonyZombies(int ponyZombies) {
        this.ponyZombies = ponyZombies;
    }

    public int getShowScale() {
        return this.showScale;
    }

    public void setShowScale(int showScale) {
        this.showScale = showScale;
    }

    public int getShowSnuzzles() {
        return this.showSnuzzles;
    }

    public void setShowSnuzzles(int showSnuzzles) {
        this.showSnuzzles = showSnuzzles;
    }

    public int getUseSizes() {
        return this.useSizes;
    }

    public void setUseSizes(int useSizes) {
        this.useSizes = useSizes;
    }
}

