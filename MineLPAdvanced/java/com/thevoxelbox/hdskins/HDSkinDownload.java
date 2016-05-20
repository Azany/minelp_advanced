
package com.thevoxelbox.hdskins;

import com.mumfrey.liteloader.util.log.LiteLoaderLogger;
import com.thevoxelbox.common.util.PrivateFields;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IImageBuffer;
import net.minecraft.client.renderer.ThreadDownloadImageData;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.net.HttpURLConnection;
import java.net.Proxy;
import java.net.URL;

class HDSkinDownload
        extends Thread {
    private final ThreadDownloadImageData image;
    private final IImageBuffer imageBuffer;
    private final String skinUrl;
    private final Thread originalThread;

    HDSkinDownload(ThreadDownloadImageData image, IImageBuffer imageBuffer, String url) {
        this.image = image;
        this.imageBuffer = imageBuffer != null ? imageBuffer : PrivateFields.imageBuffer.get(image);
        this.originalThread = PrivateFields.imageThread.get(image);
        this.skinUrl = url;
    }

    @Override
    public void run() {
        Proxy proxy = Minecraft.getMinecraft().getProxy();
        if (!this.tryDownload(proxy, this.skinUrl) && this.originalThread != null) {
            this.originalThread.run();
        }
    }

    boolean tryDownload(Proxy proxy, String strUrl) {
        HttpURLConnection httpConnection = null;
        try {
            LiteLoaderLogger.debug("Downloading HD Skin from %s", (Object[]) new Object[]{strUrl});
            URL url = new URL(strUrl);
            httpConnection = (HttpURLConnection) url.openConnection(proxy);
            httpConnection.addRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0)");
            httpConnection.setDoInput(true);
            httpConnection.setDoOutput(false);
            httpConnection.connect();
            if (httpConnection.getResponseCode() / 100 == 2) {
                BufferedImage bufferedImage = ImageIO.read(httpConnection.getInputStream());
                if (this.imageBuffer != null) {
                    bufferedImage = this.imageBuffer.parseUserSkin(bufferedImage);
                }
                this.image.setBufferedImage(bufferedImage);
                return true;
            }
        } catch (Exception ex) {
            return false;
        } finally {
            if (httpConnection != null) {
                httpConnection.disconnect();
            }
        }
        return false;
    }
}

