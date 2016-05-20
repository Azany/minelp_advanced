/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  com.thevoxelbox.common.util.PrivateFields
 *  net.minecraft.client.renderer.IImageBuffer
 *  net.minecraft.client.renderer.ThreadDownloadImageData
 *  net.minecraft.util.ResourceLocation
 */
package com.thevoxelbox.hdskins;

import com.thevoxelbox.common.util.PrivateFields;
import net.minecraft.client.renderer.IImageBuffer;
import net.minecraft.client.renderer.ThreadDownloadImageData;
import net.minecraft.util.ResourceLocation;

public class PreviewTexture
        extends ThreadDownloadImageData {
    public PreviewTexture(String url, ResourceLocation fallbackTexture, IImageBuffer imageBuffer) {
        super(null, url, fallbackTexture, imageBuffer);
    }

    public boolean isTextureUploaded() {
        return PrivateFields.downloadedImage.get(this) != null && this.getGlTextureId() > -1;
    }
}

