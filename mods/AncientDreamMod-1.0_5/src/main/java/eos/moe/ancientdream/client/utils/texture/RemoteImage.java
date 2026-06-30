/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.texture.DynamicTexture
 *  net.minecraft.client.renderer.texture.TextureManager
 *  net.minecraft.util.ResourceLocation
 */
package eos.moe.ancientdream.client.utils.texture;

import eos.moe.ancientdream.AncientDream;
import eos.moe.ancientdream.client.utils.LocalCache;
import eos.moe.ancientdream.client.utils.texture.BuiltinImage;
import eos.moe.ancientdream.client.utils.texture.TextureProvider;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import javax.imageio.ImageIO;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.util.ResourceLocation;

public class RemoteImage
implements TextureProvider {
    protected static ExecutorService EXECUTOR = Executors.newCachedThreadPool(new ThreadFactory(){
        private ThreadGroup group = new ThreadGroup("Pangu Remote Image Fetcher Threads");
        private int index = 1;

        @Override
        public Thread newThread(Runnable r) {
            return new Thread(this.group, r, "Remote Image Thread #" + this.index++);
        }
    });
    protected String urlPath;
    protected URL url;
    protected String id;
    protected boolean exception = false;
    protected File cachedFilePath;
    protected DynamicTexture dynamicTexture;
    protected Future<BufferedImage> bufferedImage;
    protected ResourceLocation resourceLocation;
    private static Map<String, RemoteImage> cachedImages = new HashMap<String, RemoteImage>();

    protected RemoteImage(String urlPath) throws MalformedURLException {
        this.urlPath = urlPath;
        this.url = new URL(urlPath);
        this.id = Base64.getEncoder().encodeToString(urlPath.getBytes());
        this.cachedFilePath = this.createCachedFilePath();
        this.bufferedImage = EXECUTOR.submit(() -> {
            if (!this.cachedFilePath.exists()) {
                AncientDream.getLogger().debug("Start fetching image from " + this.url.toString());
                this.saveImage();
                AncientDream.getLogger().debug("Saved " + urlPath + " to " + this.cachedFilePath.getAbsolutePath());
            } else {
                AncientDream.getLogger().debug("Loading image " + urlPath + " from local " + this.cachedFilePath.getAbsolutePath());
            }
            return this.readImage();
        });
    }

    @Override
    public ResourceLocation getTexture() {
        if (!this.bufferedImage.isDone()) {
            return null;
        }
        if (this.exception) {
            return null;
        }
        if (this.resourceLocation == null) {
            LocalCache.markFileUsed(this.cachedFilePath.toPath());
            try {
                DynamicTexture dynamicTexture;
                TextureManager getTextureManager = Minecraft.func_71410_x().func_110434_K();
                String string = "ancientdream_remote_image" + this.id;
                this.dynamicTexture = dynamicTexture = new DynamicTexture(this.bufferedImage.get());
                this.resourceLocation = getTextureManager.func_110578_a(string, dynamicTexture);
            }
            catch (Exception e) {
                AncientDream.getLogger().error("Couldn't load remote image from url " + this.url.toString(), (Throwable)e);
                this.bufferedImage = new Future<BufferedImage>(){

                    @Override
                    public boolean cancel(boolean mayInterruptIfRunning) {
                        return false;
                    }

                    @Override
                    public boolean isCancelled() {
                        return false;
                    }

                    @Override
                    public boolean isDone() {
                        return false;
                    }

                    @Override
                    public BufferedImage get() {
                        return null;
                    }

                    @Override
                    public BufferedImage get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
                        return null;
                    }
                };
                this.exception = true;
            }
        }
        return this.resourceLocation;
    }

    @Override
    public ResourceLocation getTexture(ResourceLocation loading) {
        ResourceLocation texture = this.getTexture();
        if (texture == null || texture == TextureManager.field_194008_a) {
            return loading;
        }
        return texture;
    }

    @Override
    public ResourceLocation getTexture(ResourceLocation loading, ResourceLocation error) {
        if (!this.bufferedImage.isDone()) {
            return loading;
        }
        return this.getTexture(error);
    }

    public BufferedImage getBufferedImage() {
        if (!this.bufferedImage.isDone()) {
            return null;
        }
        try {
            return this.bufferedImage.get();
        }
        catch (Exception ex) {
            return null;
        }
    }

    protected BufferedImage readImage() throws IOException {
        return ImageIO.read(this.cachedFilePath);
    }

    protected void saveImage() throws IOException {
        int length;
        DataInputStream dataInputStream = new DataInputStream(this.url.openStream());
        FileOutputStream fileOutputStream = new FileOutputStream(this.cachedFilePath);
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        while ((length = dataInputStream.read(buffer)) > 0) {
            output.write(buffer, 0, length);
        }
        fileOutputStream.write(output.toByteArray());
        fileOutputStream.flush();
        dataInputStream.close();
        fileOutputStream.close();
    }

    public File createCachedFilePath() {
        return LocalCache.getCachePath("images", this.id);
    }

    public static TextureProvider of(String url, ResourceLocation missingTexture) {
        RemoteImage image = RemoteImage.of(url);
        if (image == null) {
            return new BuiltinImage(missingTexture);
        }
        return image;
    }

    public static RemoteImage of(String url) {
        RemoteImage remoteImage = cachedImages.get(url);
        if (remoteImage == null) {
            try {
                remoteImage = new RemoteImage(url);
                cachedImages.put(url, remoteImage);
            }
            catch (Exception e) {
                AncientDream.getLogger().error("Couldn't load remote resourceLocation", (Throwable)e);
                return null;
            }
        }
        return remoteImage;
    }

    public String getUrlPath() {
        return this.urlPath;
    }

    public URL getUrl() {
        return this.url;
    }

    public String getId() {
        return this.id;
    }

    public File getCachedFilePath() {
        return this.cachedFilePath;
    }

    public DynamicTexture getDynamicTexture() {
        return this.dynamicTexture;
    }
}

