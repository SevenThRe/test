/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.texture.TextureManager
 *  net.minecraft.client.renderer.texture.TextureUtil
 *  net.minecraft.client.resources.IResource
 *  net.minecraft.client.resources.IResourceManager
 *  net.minecraft.util.ResourceLocation
 */
package journeymap.client.render.texture;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import javax.imageio.ImageIO;
import journeymap.client.io.FileHandler;
import journeymap.client.io.IconSetFileHandler;
import journeymap.client.io.ThemeLoader;
import journeymap.client.render.texture.IgnSkin;
import journeymap.client.render.texture.TextureImpl;
import journeymap.client.task.main.ExpireTextureTask;
import journeymap.client.ui.theme.Theme;
import journeymap.common.Journeymap;
import journeymap.common.thread.JMThreadFactory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.client.resources.IResource;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.util.ResourceLocation;

public class TextureCache {
    public static final ResourceLocation GridCheckers = TextureCache.uiImage("grid-checkers.png");
    public static final ResourceLocation GridDots = TextureCache.uiImage("grid-dots.png");
    public static final ResourceLocation GridSquares = TextureCache.uiImage("grid.png");
    public static final ResourceLocation GridRegionSquares = TextureCache.uiImage("grid-region.png");
    public static final ResourceLocation GridRegion = TextureCache.uiImage("region.png");
    public static final ResourceLocation ColorPicker = TextureCache.uiImage("colorpick.png");
    public static final ResourceLocation ColorPicker2 = TextureCache.uiImage("colorpick2.png");
    public static final ResourceLocation TileSampleDay = TextureCache.uiImage("tile-sample-day.png");
    public static final ResourceLocation TileSampleNight = TextureCache.uiImage("tile-sample-night.png");
    public static final ResourceLocation TileSampleUnderground = TextureCache.uiImage("tile-sample-underground.png");
    public static final ResourceLocation UnknownEntity = TextureCache.uiImage("unknown.png");
    public static final ResourceLocation Deathpoint = TextureCache.uiImage("waypoint-death.png");
    public static final ResourceLocation MobDot = TextureCache.uiImage("marker-dot-16.png");
    public static final ResourceLocation MobDot_Large = TextureCache.uiImage("marker-dot-32.png");
    public static final ResourceLocation MobDotArrow = TextureCache.uiImage("marker-dot-arrow-16.png");
    public static final ResourceLocation MobDotArrow_Large = TextureCache.uiImage("marker-dot-arrow-32.png");
    public static final ResourceLocation MobDotChevron = TextureCache.uiImage("marker-chevron-16.png");
    public static final ResourceLocation MobDotChevron_Large = TextureCache.uiImage("marker-chevron-32.png");
    public static final ResourceLocation MobIconArrow = TextureCache.uiImage("marker-icon-arrow-16.png");
    public static final ResourceLocation MobIconArrow_Large = TextureCache.uiImage("marker-icon-arrow-32.png");
    public static final ResourceLocation PlayerArrow = TextureCache.uiImage("marker-player-16.png");
    public static final ResourceLocation PlayerArrowBG = TextureCache.uiImage("marker-player-bg-16.png");
    public static final ResourceLocation PlayerArrow_Large = TextureCache.uiImage("marker-player-32.png");
    public static final ResourceLocation PlayerArrowBG_Large = TextureCache.uiImage("marker-player-bg-32.png");
    public static final ResourceLocation Logo = TextureCache.uiImage("ico/journeymap.png");
    public static final ResourceLocation MinimapSquare128 = TextureCache.uiImage("minimap/minimap-square-128.png");
    public static final ResourceLocation MinimapSquare256 = TextureCache.uiImage("minimap/minimap-square-256.png");
    public static final ResourceLocation MinimapSquare512 = TextureCache.uiImage("minimap/minimap-square-512.png");
    public static final ResourceLocation Patreon = TextureCache.uiImage("patreon.png");
    public static final ResourceLocation Discord = TextureCache.uiImage("discord.png");
    public static final ResourceLocation Waypoint = TextureCache.uiImage("waypoint.png");
    public static final ResourceLocation WaypointEdit = TextureCache.uiImage("waypoint-edit.png");
    public static final ResourceLocation WaypointOffscreen = TextureCache.uiImage("waypoint-offscreen.png");
    public static final Map<String, TextureImpl> playerSkins = Collections.synchronizedMap(new HashMap());
    public static final Map<String, TextureImpl> themeImages = Collections.synchronizedMap(new HashMap());
    private static ThreadPoolExecutor texExec = new ThreadPoolExecutor(2, 4, 15L, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(8), new JMThreadFactory("texture"), new ThreadPoolExecutor.CallerRunsPolicy());

    private static ResourceLocation uiImage(String fileName) {
        return new ResourceLocation("journeymap", "ui/img/" + fileName);
    }

    public static TextureImpl getTexture(ResourceLocation location) {
        boolean loaded;
        if (location == null) {
            return null;
        }
        TextureManager textureManager = Minecraft.func_71410_x().func_110434_K();
        Object textureObject = textureManager.func_110581_b(location);
        if (!(textureObject != null && textureObject instanceof TextureImpl || (loaded = textureManager.func_110579_a(location, textureObject = new TextureImpl(location))))) {
            textureObject = null;
        }
        return (TextureImpl)((Object)textureObject);
    }

    public static <T extends TextureImpl> Future<T> scheduleTextureTask(Callable<T> textureTask) {
        return texExec.submit(textureTask);
    }

    public static void reset() {
        playerSkins.clear();
        Arrays.asList(ColorPicker, ColorPicker2, Deathpoint, GridCheckers, GridDots, GridSquares, GridRegionSquares, GridRegion, Logo, MinimapSquare128, MinimapSquare256, MinimapSquare512, MobDot, MobDot_Large, MobDotArrow, MobDotArrow_Large, MobDotChevron, MobDotChevron_Large, MobIconArrow_Large, Patreon, PlayerArrow, PlayerArrow_Large, PlayerArrowBG, PlayerArrowBG, TileSampleDay, TileSampleNight, TileSampleUnderground, UnknownEntity, Waypoint, WaypointEdit, WaypointOffscreen).stream().map(TextureCache::getTexture);
        Arrays.asList(ColorPicker, ColorPicker2, GridCheckers, GridDots, GridSquares, GridRegion, GridRegionSquares, TileSampleDay, TileSampleNight, TileSampleUnderground, UnknownEntity).stream().map(TextureCache::getTexture);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static void purgeThemeImages(Map<String, TextureImpl> themeImages) {
        Map<String, TextureImpl> map = themeImages;
        synchronized (map) {
            ExpireTextureTask.queue(themeImages.values());
            themeImages.clear();
        }
    }

    public static BufferedImage resolveImage(ResourceLocation location) {
        if (location.func_110624_b().equals("fake")) {
            return null;
        }
        IResourceManager resourceManager = Minecraft.func_71410_x().func_110442_L();
        try {
            IResource resource = resourceManager.func_110536_a(location);
            InputStream is = resource.func_110527_b();
            return TextureUtil.func_177053_a((InputStream)is);
        }
        catch (FileNotFoundException e) {
            File imgFile;
            if ("journeymap".equals(location.func_110624_b()) && (imgFile = new File("../src/main/resources/assets/journeymap/" + location.func_110623_a())).exists()) {
                try {
                    return ImageIO.read(imgFile);
                }
                catch (IOException iOException) {
                    // empty catch block
                }
            }
            Journeymap.getLogger().warn("Image not found: " + e.getMessage());
            return null;
        }
        catch (Exception e) {
            Journeymap.getLogger().warn("Resource not readable with TextureUtil.readBufferedImage(): " + location);
            return null;
        }
    }

    public static TextureImpl getThemeTexture(Theme theme, String iconPath) {
        return TextureCache.getSizedThemeTexture(theme, iconPath, 0, 0, false, 1.0f, false);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static TextureImpl getSizedThemeTexture(Theme theme, String iconPath, int width, int height, boolean resize, float alpha, boolean retainImage) {
        String texName = String.format("%s/%s", theme.directory, iconPath);
        Map<String, TextureImpl> map = themeImages;
        synchronized (map) {
            TextureImpl tex = themeImages.get(texName);
            if (tex == null || tex.retainImage != retainImage || !tex.hasImage() && tex.retainImage || resize && (width != tex.width || height != tex.height) || tex.alpha != alpha) {
                File parentDir = ThemeLoader.getThemeIconDir();
                BufferedImage img = FileHandler.getIconFromFile(parentDir, theme.directory, iconPath);
                if (img == null) {
                    String resourcePath = String.format("theme/%s/%s", theme.directory, iconPath);
                    img = TextureCache.resolveImage(new ResourceLocation("journeymap", resourcePath));
                }
                if (img != null) {
                    if ((resize || alpha < 1.0f) && (alpha < 1.0f || img.getWidth() != width || img.getHeight() != height)) {
                        BufferedImage tmp = new BufferedImage(width, height, img.getType());
                        Graphics2D g = tmp.createGraphics();
                        g.setComposite(AlphaComposite.getInstance(3, alpha));
                        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
                        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                        g.drawImage(img, 0, 0, width, height, null);
                        g.dispose();
                        img = tmp;
                    }
                    if (tex != null) {
                        tex.queueForDeletion();
                    }
                    tex = new TextureImpl(img, retainImage);
                    tex.alpha = alpha;
                    themeImages.put(texName, tex);
                } else {
                    Journeymap.getLogger().error("Unknown theme image: " + texName);
                    IconSetFileHandler.ensureEntityIconSet("Default");
                    return TextureCache.getTexture(UnknownEntity);
                }
            }
            return tex;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static TextureImpl getScaledCopy(String texName, TextureImpl original, int width, int height, float alpha) {
        Map<String, TextureImpl> map = themeImages;
        synchronized (map) {
            TextureImpl tex = themeImages.get(texName);
            if (tex == null || !tex.hasImage() && tex.retainImage || width != tex.width || height != tex.height || tex.alpha != alpha) {
                BufferedImage img = original.getImage();
                if (img != null) {
                    if (alpha < 1.0f || img.getWidth() != width || img.getHeight() != height) {
                        BufferedImage tmp = new BufferedImage(width, height, img.getType());
                        Graphics2D g = tmp.createGraphics();
                        g.setComposite(AlphaComposite.getInstance(3, alpha));
                        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
                        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                        g.drawImage(img, 0, 0, width, height, null);
                        g.dispose();
                        img = tmp;
                    }
                    if (tex != null) {
                        tex.queueForDeletion();
                    }
                    tex = new TextureImpl(img);
                    tex.alpha = alpha;
                    themeImages.put(texName, tex);
                } else {
                    Journeymap.getLogger().error("Unable to get scaled image: " + texName);
                    return TextureCache.getTexture(UnknownEntity);
                }
            }
            return tex;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static TextureImpl getPlayerSkin(UUID playerId, String username) {
        TextureImpl tex = null;
        Map<String, TextureImpl> map = playerSkins;
        synchronized (map) {
            tex = playerSkins.get(username);
            if (tex != null) {
                return tex;
            }
            BufferedImage blank = new BufferedImage(24, 24, 2);
            tex = new TextureImpl(null, blank, true, false);
            playerSkins.put(username, tex);
        }
        TextureImpl playerSkinTex = tex;
        texExec.submit(() -> {
            BufferedImage img = IgnSkin.getFaceImage(playerId, username);
            if (img != null) {
                playerSkinTex.setImage(img, true);
            } else {
                Journeymap.getLogger().warn("Couldn't get a skin at all for " + username);
            }
            return null;
        });
        return playerSkinTex;
    }
}

