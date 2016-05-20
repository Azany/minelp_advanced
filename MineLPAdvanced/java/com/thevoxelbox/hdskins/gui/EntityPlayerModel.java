/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  com.mojang.authlib.GameProfile
 *  com.thevoxelbox.common.util.TextureHelper
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.texture.DynamicTexture
 *  net.minecraft.client.renderer.texture.TextureManager
 *  net.minecraft.client.resources.SkinManager
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.world.World
 */
package com.thevoxelbox.hdskins.gui;

import com.mojang.authlib.GameProfile;
import com.thevoxelbox.common.util.TextureHelper;
import com.thevoxelbox.hdskins.HDSkinManager;
import com.thevoxelbox.hdskins.PreviewTexture;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.resources.SkinManager;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class EntityPlayerModel
        extends EntityLiving {
    public static final ResourceLocation NOSKIN = new ResourceLocation("hdskins", "textures/mob/noskin.png");
    public final GameProfile profile;
    public boolean isSwinging = false;
    protected boolean remoteSkin = false;
    protected boolean hasLocalTexture = false;
    private PreviewTexture remoteSkinTexture;
    private ResourceLocation remoteSkinResource;
    private ResourceLocation localSkinResource;
    private DynamicTexture localSkinTexture;
    private TextureManager textureManager;

    public EntityPlayerModel(GameProfile profile) {
        super(null);
        this.profile = profile;
        this.textureManager = Minecraft.getMinecraft().getTextureManager();
        this.remoteSkinResource = new ResourceLocation("skins/preview_" + this.profile.getName() + ".png");
        this.localSkinResource = NOSKIN;
        TextureHelper.releaseTexture(this.remoteSkinResource);
    }

    public void setRemoteSkin() {
        this.remoteSkin = true;
        if (this.remoteSkinTexture != null) {
            TextureHelper.releaseTexture(this.remoteSkinResource);
        }
        this.remoteSkinTexture = HDSkinManager.getPreviewTexture(this.remoteSkinResource, this.profile);
    }

    public void setLocalSkin(File skinTextureFile) {
        if (skinTextureFile.exists()) {
            BufferedImage bufferedImage;
            this.remoteSkin = false;
            if (this.localSkinTexture != null) {
                TextureHelper.releaseTexture(this.localSkinResource);
                this.localSkinTexture = null;
            }
            try {
                bufferedImage = ImageIO.read(skinTextureFile);
            } catch (IOException ex) {
                this.localSkinResource = NOSKIN;
                ex.printStackTrace();
                return;
            }
            this.localSkinTexture = new DynamicTexture(bufferedImage);
            this.localSkinResource = this.textureManager.getDynamicTextureLocation("localSkinPreview", this.localSkinTexture);
            this.hasLocalTexture = true;
        }
    }

    public boolean usingRemoteSkin() {
        return this.remoteSkin;
    }

    public boolean isUsingLocalTexture() {
        return !this.remoteSkin && this.hasLocalTexture;
    }

    public float getBrightness(float par1) {
        return 1.0f;
    }

    public boolean isTextureSetupComplete() {
        return this.remoteSkin && this.remoteSkinTexture != null && this.remoteSkinTexture.isTextureUploaded();
    }

    public void releaseTextures() {
        if (this.localSkinTexture != null) {
            TextureHelper.releaseTexture(this.localSkinResource);
            this.localSkinTexture = null;
            this.localSkinResource = NOSKIN;
            this.hasLocalTexture = false;
        }
    }

    public ResourceLocation getSkinTexture() {
        return this.remoteSkin ? (this.remoteSkinTexture != null ? this.remoteSkinResource : SkinManager.field_152793_a) : this.localSkinResource;
    }

    public boolean hasCloak() {
        return false;
    }

    public void swingArm() {
        if (!this.isSwinging || this.swingProgressInt >= 4 || this.swingProgressInt < 0) {
            this.swingProgressInt = -1;
            this.isSwinging = true;
        }
    }

    public void updateModel() {
        this.prevSwingProgress = this.swingProgress;
        if (this.isSwinging) {
            ++this.swingProgressInt;
            if (this.swingProgressInt >= 8) {
                this.swingProgressInt = 0;
                this.isSwinging = false;
            }
        } else {
            this.swingProgressInt = 0;
        }
        this.swingProgress = (float) this.swingProgressInt / 8.0f;
    }
}

