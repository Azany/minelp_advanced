package com.thevoxelbox.hdskins;

import net.minecraft.client.renderer.IImageBuffer;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageBufferDownloadHD
        implements IImageBuffer {
    public BufferedImage parseUserSkin(BufferedImage downloadedImage) {
        if (downloadedImage == null) {
            return null;
        }
        int imageWidth = downloadedImage.getWidth();
        int imageHeight = imageWidth / 2;
        if (downloadedImage.getHeight() == imageWidth / 2) {
            return downloadedImage;
        }
        BufferedImage scaledImage = new BufferedImage(imageWidth, imageHeight, 2);
        Graphics scaledImageGraphicsContext = scaledImage.getGraphics();
        scaledImageGraphicsContext.drawImage(downloadedImage, 0, 0, null);
        scaledImageGraphicsContext.dispose();
        return scaledImage;
    }

    public void func_152634_a() {
    }
}

