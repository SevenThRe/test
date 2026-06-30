/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.math.ChunkPos
 */
package journeymap.client.io;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import journeymap.client.Constants;
import journeymap.client.io.FileHandler;
import journeymap.client.model.GridSpec;
import journeymap.client.model.MapType;
import journeymap.client.model.RegionCoord;
import journeymap.client.model.RegionImageCache;
import journeymap.client.render.map.TileDrawStep;
import journeymap.client.render.map.TileDrawStepCache;
import journeymap.common.Journeymap;
import journeymap.common.log.LogFormatter;
import net.minecraft.util.math.ChunkPos;

public class RegionImageHandler {
    public static File getImageDir(RegionCoord rCoord, MapType mapType) {
        File dimDir = rCoord.dimDir.toFile();
        File subDir = null;
        subDir = mapType.isUnderground() ? new File(dimDir, Integer.toString(mapType.vSlice)) : new File(dimDir, mapType.name());
        if (!subDir.exists()) {
            subDir.mkdirs();
        }
        return subDir;
    }

    @Deprecated
    public static File getDimensionDir(File worldDir, int dimension) {
        File dimDir = new File(worldDir, "DIM" + dimension);
        if (!dimDir.exists()) {
            dimDir.mkdirs();
        }
        return dimDir;
    }

    public static File getRegionImageFile(RegionCoord rCoord, MapType mapType, boolean allowLegacy) {
        StringBuffer sb = new StringBuffer();
        sb.append(rCoord.regionX).append(",").append(rCoord.regionZ).append(".png");
        File regionFile = new File(RegionImageHandler.getImageDir(rCoord, mapType), sb.toString());
        return regionFile;
    }

    public static BufferedImage createBlankImage(int width, int height) {
        BufferedImage img = new BufferedImage(width, height, 2);
        Graphics2D g = RegionImageHandler.initRenderingHints(img.createGraphics());
        g.setColor(Color.black);
        g.setComposite(AlphaComposite.Clear);
        g.drawImage(img, 0, 0, width, height, null);
        g.dispose();
        return img;
    }

    public static BufferedImage readRegionImage(File regionFile, boolean returnNull) {
        BufferedImage image = null;
        if (regionFile.canRead()) {
            try {
                image = ImageIO.read(regionFile);
            }
            catch (Exception e) {
                String error = "Region file produced error: " + regionFile + ": " + LogFormatter.toPartialString(e);
                Journeymap.getLogger().error(error);
            }
        }
        return image;
    }

    public static BufferedImage getImage(File file) {
        try {
            return ImageIO.read(file);
        }
        catch (IOException e) {
            String error = "Could not get image from file: " + file + ": " + e.getMessage();
            Journeymap.getLogger().error(error);
            return null;
        }
    }

    public static synchronized BufferedImage getMergedChunks(File worldDir, ChunkPos startCoord, ChunkPos endCoord, MapType mapType, Boolean useCache, BufferedImage image, Integer imageWidth, Integer imageHeight, boolean allowNullImage, boolean showGrid) {
        int scale = 1;
        scale = Math.max(scale, 1);
        int initialWidth = Math.min(512, (endCoord.field_77276_a - startCoord.field_77276_a + 1) * 16 / scale);
        int initialHeight = Math.min(512, (endCoord.field_77275_b - startCoord.field_77275_b + 1) * 16 / scale);
        Object blank = null;
        image = RegionImageHandler.createBlankImage(initialWidth, initialHeight);
        Graphics2D g2D = image.createGraphics();
        RegionImageCache cache = RegionImageCache.INSTANCE;
        RegionCoord rc = null;
        BufferedImage regionImage = null;
        int rx1 = RegionCoord.getRegionPos(startCoord.field_77276_a);
        int rx2 = RegionCoord.getRegionPos(endCoord.field_77276_a);
        int rz1 = RegionCoord.getRegionPos(startCoord.field_77275_b);
        int rz2 = RegionCoord.getRegionPos(endCoord.field_77275_b);
        boolean imageDrawn = false;
        for (int rx = rx1; rx <= rx2; ++rx) {
            for (int rz = rz1; rz <= rz2; ++rz) {
                rc = new RegionCoord(worldDir, rx, rz, mapType.dimension);
                regionImage = cache.getRegionImageSet(rc).getImage(mapType);
                if (regionImage == null) continue;
                int rminCx = Math.max(rc.getMinChunkX(), startCoord.field_77276_a);
                int rminCz = Math.max(rc.getMinChunkZ(), startCoord.field_77275_b);
                int rmaxCx = Math.min(rc.getMaxChunkX(), endCoord.field_77276_a);
                int rmaxCz = Math.min(rc.getMaxChunkZ(), endCoord.field_77275_b);
                int xoffset = rc.getMinChunkX() * 16;
                int yoffset = rc.getMinChunkZ() * 16;
                int sx1 = rminCx * 16 - xoffset;
                int sy1 = rminCz * 16 - yoffset;
                int sx2 = sx1 + (rmaxCx - rminCx + 1) * 16;
                int sy2 = sy1 + (rmaxCz - rminCz + 1) * 16;
                xoffset = startCoord.field_77276_a * 16;
                yoffset = startCoord.field_77275_b * 16;
                int dx1 = startCoord.field_77276_a * 16 - xoffset;
                int dy1 = startCoord.field_77275_b * 16 - yoffset;
                int dx2 = dx1 + (endCoord.field_77276_a - startCoord.field_77276_a + 1) * 16;
                int dy2 = dy1 + (endCoord.field_77275_b - startCoord.field_77275_b + 1) * 16;
                g2D.drawImage(regionImage, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, null);
                imageDrawn = true;
            }
        }
        if (imageDrawn && showGrid) {
            if (mapType.isDay()) {
                g2D.setColor(Color.black);
                g2D.setComposite(AlphaComposite.getInstance(10, 0.25f));
            } else {
                g2D.setColor(Color.gray);
                g2D.setComposite(AlphaComposite.getInstance(10, 0.1f));
            }
            for (int x = 0; x <= initialWidth; x += 16) {
                g2D.drawLine(x, 0, x, initialHeight);
            }
            for (int z = 0; z <= initialHeight; z += 16) {
                g2D.drawLine(0, z, initialWidth, z);
            }
        }
        g2D.dispose();
        if (allowNullImage && !imageDrawn) {
            return null;
        }
        if (imageHeight != null && imageWidth != null && (initialHeight != imageHeight || initialWidth != imageWidth)) {
            BufferedImage scaledImage = RegionImageHandler.createBlankImage(imageWidth, imageHeight);
            Graphics2D g = RegionImageHandler.initRenderingHints(scaledImage.createGraphics());
            g.drawImage(image, 0, 0, imageWidth, imageHeight, null);
            g.dispose();
            return scaledImage;
        }
        return image;
    }

    public static synchronized BufferedImage getMergedChunks(File worldDir, ChunkPos startCoord, ChunkPos endCoord, MapType mapType, int scale, boolean showGrid) {
        GridSpec gridSpec;
        scale = Math.max(scale, 1);
        int initialWidth = Math.min(512, (endCoord.field_77276_a - startCoord.field_77276_a + 1) * 16 / scale);
        int initialHeight = Math.min(512, (endCoord.field_77275_b - startCoord.field_77275_b + 1) * 16 / scale);
        BufferedImage blank = null;
        BufferedImage image = RegionImageHandler.createBlankImage(initialWidth, initialHeight);
        Graphics2D g2D = image.createGraphics();
        int rx1 = RegionCoord.getRegionPos(startCoord.field_77276_a);
        int rx2 = RegionCoord.getRegionPos(endCoord.field_77276_a);
        int rz1 = RegionCoord.getRegionPos(startCoord.field_77275_b);
        int rz2 = RegionCoord.getRegionPos(endCoord.field_77275_b);
        for (int rx = rx1; rx <= rx2; ++rx) {
            for (int rz = rz1; rz <= rz2; ++rz) {
                RegionCoord rc = new RegionCoord(worldDir, rx, rz, mapType.dimension);
                BufferedImage regionImage = RegionImageCache.INSTANCE.getRegionImageSet(rc).getImage(mapType);
                if (regionImage == null) {
                    if (blank == null) {
                        blank = RegionImageHandler.createBlankImage(512, 512);
                    }
                    regionImage = blank;
                }
                int rminCx = Math.max(rc.getMinChunkX(), startCoord.field_77276_a);
                int rminCz = Math.max(rc.getMinChunkZ(), startCoord.field_77275_b);
                int rmaxCx = Math.min(rc.getMaxChunkX(), endCoord.field_77276_a);
                int rmaxCz = Math.min(rc.getMaxChunkZ(), endCoord.field_77275_b);
                int sx1 = (rminCx - rc.getMinChunkX()) * 16;
                int sy1 = (rminCz - rc.getMinChunkZ()) * 16;
                int sx2 = sx1 + (rmaxCx - rminCx + 1) * 16;
                int sy2 = sy1 + (rmaxCz - rminCz + 1) * 16;
                int dx1 = (rminCx - startCoord.field_77276_a) * 16;
                int dy1 = (rminCz - startCoord.field_77275_b) * 16;
                int dx2 = dx1 + (sx2 - sx1);
                int dy2 = dy1 + (sy2 - sy1);
                g2D.drawImage(regionImage, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, null);
            }
        }
        if (showGrid && (gridSpec = Journeymap.getClient().getCoreProperties().gridSpecs.getSpec(mapType)) != null) {
            BufferedImage gridImage = gridSpec.getTexture().getImage();
            g2D.setXORMode(new Color(gridSpec.getColor()));
            g2D.setComposite(AlphaComposite.getInstance(10, gridSpec.alpha));
            g2D.drawImage(gridImage, 0, 0, initialWidth, initialHeight, null);
        }
        g2D.dispose();
        if (scale > 1) {
            int scaledWidth = Math.min(512, initialWidth * scale);
            int scaledHeight = Math.min(512, initialHeight * scale);
            BufferedImage scaledImage = RegionImageHandler.createBlankImage(scaledWidth, scaledHeight);
            Graphics2D g = RegionImageHandler.initRenderingHints(scaledImage.createGraphics());
            g.drawImage(image, 0, 0, scaledWidth, scaledHeight, null);
            g.dispose();
            return scaledImage;
        }
        return image;
    }

    public static synchronized List<TileDrawStep> getTileDrawSteps(File worldDir, ChunkPos startCoord, ChunkPos endCoord, MapType mapType, Integer zoom, boolean highQuality) {
        boolean isUnderground = mapType.isUnderground();
        int rx1 = RegionCoord.getRegionPos(startCoord.field_77276_a);
        int rx2 = RegionCoord.getRegionPos(endCoord.field_77276_a);
        int rz1 = RegionCoord.getRegionPos(startCoord.field_77275_b);
        int rz2 = RegionCoord.getRegionPos(endCoord.field_77275_b);
        ArrayList<TileDrawStep> drawSteps = new ArrayList<TileDrawStep>();
        for (int rx = rx1; rx <= rx2; ++rx) {
            for (int rz = rz1; rz <= rz2; ++rz) {
                RegionCoord rc = new RegionCoord(worldDir, rx, rz, mapType.dimension);
                int rminCx = Math.max(rc.getMinChunkX(), startCoord.field_77276_a);
                int rminCz = Math.max(rc.getMinChunkZ(), startCoord.field_77275_b);
                int rmaxCx = Math.min(rc.getMaxChunkX(), endCoord.field_77276_a);
                int rmaxCz = Math.min(rc.getMaxChunkZ(), endCoord.field_77275_b);
                int xoffset = rc.getMinChunkX() * 16;
                int yoffset = rc.getMinChunkZ() * 16;
                int sx1 = rminCx * 16 - xoffset;
                int sy1 = rminCz * 16 - yoffset;
                int sx2 = sx1 + (rmaxCx - rminCx + 1) * 16;
                int sy2 = sy1 + (rmaxCz - rminCz + 1) * 16;
                drawSteps.add(TileDrawStepCache.getOrCreate(mapType, rc, zoom, highQuality, sx1, sy1, sx2, sy2));
            }
        }
        return drawSteps;
    }

    public static File getBlank512x512ImageFile() {
        File dataDir = new File(FileHandler.MinecraftDirectory, Constants.DATA_DIR);
        File tmpFile = new File(dataDir, "blank512x512.png");
        if (!tmpFile.canRead()) {
            BufferedImage image = RegionImageHandler.createBlankImage(512, 512);
            try {
                dataDir.mkdirs();
                ImageIO.write((RenderedImage)image, "png", tmpFile);
                tmpFile.setReadOnly();
                tmpFile.deleteOnExit();
            }
            catch (IOException e) {
                Journeymap.getLogger().error("Could not create blank temp file " + tmpFile + ": " + LogFormatter.toString(e));
            }
        }
        return tmpFile;
    }

    public static Graphics2D initRenderingHints(Graphics2D g) {
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
        g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        return g;
    }
}

