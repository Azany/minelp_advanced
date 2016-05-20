package com.minelittlepony.minelp.model;

public final class PMAPI {
    public static PlayerModel newPony = new PlayerModel("newPony", new pm_newPony("/mob/char.png"), 0)
            .setArmor(new pma_newPony("minelittlepony:textures/models/armor/"))
            .setURL("http://skins.minecraft.net/MinecraftSkins/%NAME%.png")
            .setScale(0.9375f);
    public static PlayerModel newPonyAdv = new PlayerModel("newPonyAdv", new pm_newPonyAdv("/mob/char.png"), 0)
            .setArmor(new pma_newPony("minelittlepony:textures/models/armor/"))
            .setURL("http://skins.minecraft.net/MinecraftSkins/%NAME%.png")
            .setScale(0.9375f);
    public static PlayerModel zombiePony = new PlayerModel("zombiePony", new pm_zombiePony("/mob/char.png"), 0)
            .setArmor(new pma_zombiePony("minelittlepony:textures/models/armor/"))
            .setURL("http://skins.minecraft.net/MinecraftSkins/%NAME%.png")
            .setScale(0.9375f);
    public static PlayerModel skeletonPony = new PlayerModel("skeletonPony", new pm_skeletonPony("/mob/char.png"), 0)
            .setArmor(new pma_skeletonPony("minelittlepony:textures/models/armor/"))
            .setURL("http://skins.minecraft.net/MinecraftSkins/%NAME%.png")
            .setScale(0.9375f);
    public static PlayerModel human = new PlayerModel("Human", new pm_Human("/mob/char.png"), 1)
            .setArmor(new pma_Human("minecraft:textures/models/armor/"))
            .setURL("http://skins.minecraft.net/MinecraftSkins/%NAME%.png")
            .setScale(0.9375f);
    public static PlayerModel eqg = new PlayerModel("EQG", new pm_EQG("/mob/char.png"), 1)
            .setArmor(new pma_EQG("minecraft:textures/models/armor/"))
            .setURL("http://skins.minecraft.net/MinecraftSkins/%NAME%.png")
            .setScale(0.9375f);
}

