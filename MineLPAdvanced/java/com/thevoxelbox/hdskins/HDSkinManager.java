package com.thevoxelbox.hdskins;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.minecraft.InsecureTextureException;
import com.mojang.authlib.minecraft.MinecraftProfileTexture;
import com.mojang.authlib.minecraft.MinecraftSessionService;
import com.mumfrey.liteloader.core.LiteLoader;
import com.mumfrey.liteloader.transformers.event.EventInfo;
import com.mumfrey.liteloader.util.log.LiteLoaderLogger;
import com.thevoxelbox.common.util.PrivateFields;
import net.inkyquill.litemods.minelp.GlobalSettings;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.ThreadDownloadImageData;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.resources.SkinManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StringUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public final class HDSkinManager {
    private static String gatewayUrl = "skins.hnm.su/minelp";
    private static String skinUrl = "skins.hnm.su/minelp";
    private static final BiMap<String, String> playerHashes = HashBiMap.create();
    private static final Map<String, Map<MinecraftProfileTexture.Type, MinecraftProfileTexture>> cachedTextures = new HashMap<String, Map<MinecraftProfileTexture.Type, MinecraftProfileTexture>>();

    private HDSkinManager() {
    }

    public static void onDownloadSkin(EventInfo<ThreadDownloadImageData> e) {
        MinecraftProfileTexture skinTexture;
        ThreadDownloadImageData imageDownload = e.getSource();
        if (imageDownload == null) {
            return;
        }
        String imageUrl = PrivateFields.imageUrl.get(imageDownload);
        if (imageUrl == null) {
            return;
        }
        String hash = FilenameUtils.getBaseName((String)imageUrl);
        String uuid = HDSkinManager.resolvePlayerIdFromHash(hash);
        if (uuid == null) {
            if (!(imageDownload instanceof PreviewTexture)) {
                return;
            }
            uuid = Minecraft.getMinecraft().getSession().getPlayerID();
        }
        if ((skinTexture = HDSkinManager.getCachedTexturesForId(uuid).get(MinecraftProfileTexture.Type.SKIN)) == null || !skinTexture.getUrl().equals(imageUrl)) {
            LiteLoaderLogger.debug("Not a skin texture!", (Object[]) new Object[0]);
            return;
        }
        Thread imageThread = PrivateFields.imageThread.get(imageDownload);
        if (imageThread == null) {
            return;
        }
        HDSkinDownload hdThread = new HDSkinDownload(imageDownload, new ImageBufferDownloadHD(), HDSkinManager.getCustomSkinURLForId(uuid, imageDownload instanceof PreviewTexture));
        PrivateFields.imageThread.set(imageDownload, hdThread);
        hdThread.setDaemon(true);
        hdThread.start();
        e.cancel();
    }

    private static String resolvePlayerIdFromHash(String hash) {
        Minecraft minecraft = Minecraft.getMinecraft();
        WorldClient world = minecraft.theWorld;
        if (world != null) {
            for (Object player : world.playerEntities) {
                EntityPlayer pl = (EntityPlayer) player;

                GameProfile profile = pl.getGameProfile();
                Map textures = HDSkinManager.getTexturesForProfile(minecraft, profile);
                for (Object texture : textures.values()) {

                    MinecraftProfileTexture tex = (MinecraftProfileTexture) texture;

                    if (!hash.equals(tex.getHash())) continue;
                    String uuid = HDSkinManager.trimUUID(pl.getUniqueID());
                    playerHashes.put(hash, uuid);
                    return uuid;
                }

            }
        }
        return null;
    }

    private static Map<MinecraftProfileTexture.Type, MinecraftProfileTexture> getTexturesForProfile(Minecraft minecraft, GameProfile profile) {
        LiteLoaderLogger.debug((String)("Get textures for " + profile.getId()), (Object[])new Object[0]);
        Map<MinecraftProfileTexture.Type, MinecraftProfileTexture> cached = HDSkinManager.getCachedTexturesForId(HDSkinManager.trimUUID(profile.getId()));
        if (cached != null) {
            return cached;
        }
        MinecraftSessionService sessionService = minecraft.func_152347_ac();
        Map textures = null;
        try {
            textures = sessionService.getTextures(profile, true);
        } catch (InsecureTextureException ex) {
            textures = sessionService.getTextures(profile, false);
        }
        if ((textures == null || textures.isEmpty()) && profile.getId().equals(minecraft.getSession().func_148256_e().getId())) {
            textures = sessionService.getTextures(sessionService.fillProfileProperties(profile, false), false);
        }
        if (textures != null && !textures.isEmpty()) {
            LiteLoaderLogger.debug((String)("Store textures for " + profile.getId()), (Object[])new Object[0]);
            cachedTextures.put(HDSkinManager.trimUUID(profile.getId()), textures);
        }
        return textures;
    }

    private static Map<MinecraftProfileTexture.Type, MinecraftProfileTexture> getCachedTexturesForId(String uuid) {
        return cachedTextures.get(uuid);
    }

private static String trimUUID(UUID uuid) {
        return uuid.toString().replace("-", "");
    }

    public static void setSkinUrl(String skinUrl) {
        HDSkinManager.skinUrl = skinUrl;
    }

    public static void setGatewayURL(String gatewayURL) {
        gatewayUrl = gatewayURL;
    }

    public static String getSkinUrl() {
        return String.format("http://%s/", skinUrl);
    }

    public static String getGatewayUrl() {
        return String.format("http://%s/", gatewayUrl);
    }

    public static String getCustomSkinURLForId(String uuid, boolean gateway) {
        return String.format("http://%s/%s/%s.png", GlobalSettings.SkinServer, GlobalSettings.SkinFolder, StringUtils.stripControlCodes(uuid));
    }

//    public static String getCustomCloakURLForId(String uuid) {
//        return String.format("http://%s/capes/%s.png", skinUrl, StringUtils.stripControlCodes(uuid));
//    }

    public static PreviewTexture getPreviewTexture(ResourceLocation skinResource, GameProfile profile) {
        TextureManager textureManager = Minecraft.getMinecraft().getTextureManager();
        Object skinTexture = textureManager.getTexture(skinResource);
        if (skinTexture == null) {
            Map textures = HDSkinManager.getTexturesForProfile(Minecraft.getMinecraft(), profile);
            MinecraftProfileTexture skin = (MinecraftProfileTexture) textures.get(MinecraftProfileTexture.Type.SKIN);
            if (skin == null) {
                throw new RuntimeException("Could not get player skin URL from profile");
            }
            String url = skin.getUrl();
            skinTexture = new PreviewTexture(url, SkinManager.field_152793_a, new ImageBufferDownloadHD());
            textureManager.loadTexture(skinResource, (ITextureObject) skinTexture);
        }
        return (PreviewTexture) skinTexture;
    }

    public static void clearSkinCache() {
        LiteLoaderLogger.info("Clearing local player skin cache", (Object[]) new Object[0]);
        try {
            FileUtils.deleteDirectory(new File(LiteLoader.getAssetsDirectory(), "skins"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}

