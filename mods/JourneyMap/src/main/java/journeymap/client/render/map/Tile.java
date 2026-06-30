/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.math.ChunkPos
 *  org.apache.logging.log4j.Logger
 */
package journeymap.client.render.map;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.io.File;
import java.util.ArrayList;
import journeymap.client.Constants;
import journeymap.client.io.RegionImageHandler;
import journeymap.client.log.ChatLog;
import journeymap.client.model.GridSpec;
import journeymap.client.model.MapType;
import journeymap.client.model.RegionImageCache;
import journeymap.client.properties.CoreProperties;
import journeymap.client.render.map.TileDrawStep;
import journeymap.client.render.map.TileDrawStepCache;
import journeymap.client.render.map.TilePos;
import journeymap.client.ui.fullscreen.Fullscreen;
import journeymap.client.ui.minimap.MiniMap;
import journeymap.common.Journeymap;
import net.minecraft.util.math.ChunkPos;
import org.apache.logging.log4j.Logger;

public class Tile {
    public static final int TILESIZE = 512;
    public static final int LOAD_RADIUS = 768;
    static String debugGlSettings = "";
    final int zoom;
    final int tileX;
    final int tileZ;
    final ChunkPos ulChunk;
    final ChunkPos lrChunk;
    final Point ulBlock;
    final Point lrBlock;
    final ArrayList<TileDrawStep> drawSteps = new ArrayList();
    private final Logger logger = Journeymap.getLogger();
    private final int theHashCode;
    private final String theCacheKey;
    int renderType = 0;
    int textureFilter = 0;
    int textureWrap = 0;

    private Tile(int tileX, int tileZ, int zoom) {
        this.tileX = tileX;
        this.tileZ = tileZ;
        this.zoom = zoom;
        this.theCacheKey = Tile.toCacheKey(tileX, tileZ, zoom);
        this.theHashCode = this.theCacheKey.hashCode();
        int distance = 32 / (int)Math.pow(2.0, zoom);
        this.ulChunk = new ChunkPos(tileX * distance, tileZ * distance);
        this.lrChunk = new ChunkPos(this.ulChunk.field_77276_a + distance - 1, this.ulChunk.field_77275_b + distance - 1);
        this.ulBlock = new Point(this.ulChunk.field_77276_a * 16, this.ulChunk.field_77275_b * 16);
        this.lrBlock = new Point(this.lrChunk.field_77276_a * 16 + 15, this.lrChunk.field_77275_b * 16 + 15);
        this.updateRenderType();
    }

    public static Tile create(int tileX, int tileZ, int zoom, File worldDir, MapType mapType, boolean highQuality) {
        Tile tile = new Tile(tileX, tileZ, zoom);
        tile.updateTexture(worldDir, mapType, highQuality);
        return tile;
    }

    public static int blockPosToTile(int b, int zoom) {
        int tile = b >> 9 - zoom;
        return tile;
    }

    public static int tileToBlock(int t, int zoom) {
        return t << 9 - zoom;
    }

    public static String toCacheKey(int tileX, int tileZ, int zoom) {
        return "" + tileX + "," + tileZ + "@" + zoom;
    }

    public static void switchTileRenderType() {
        CoreProperties coreProperties = Journeymap.getClient().getCoreProperties();
        int type = coreProperties.tileRenderType.incrementAndGet();
        if (type > 4) {
            type = 1;
            coreProperties.tileRenderType.set(type);
        }
        coreProperties.save();
        String msg = String.format("%s: %s (%s)", Constants.getString("jm.advanced.tile_render_type"), type, debugGlSettings);
        ChatLog.announceError(msg);
        Tile.resetTileDisplay();
    }

    public static void switchTileDisplayQuality() {
        CoreProperties coreProperties = Journeymap.getClient().getCoreProperties();
        boolean high = coreProperties.tileHighDisplayQuality.get() == false;
        coreProperties.tileHighDisplayQuality.set(high);
        coreProperties.save();
        ChatLog.announceError(Constants.getString("jm.common.tile_display_quality") + ": " + (high ? Constants.getString("jm.common.on") : Constants.getString("jm.common.off")));
        Tile.resetTileDisplay();
    }

    private static void resetTileDisplay() {
        TileDrawStepCache.instance().invalidateAll();
        RegionImageCache.INSTANCE.clear();
        MiniMap.state().requireRefresh();
        Fullscreen.state().requireRefresh();
    }

    public boolean updateTexture(File worldDir, MapType mapType, boolean highQuality) {
        this.updateRenderType();
        this.drawSteps.clear();
        this.drawSteps.addAll(RegionImageHandler.getTileDrawSteps(worldDir, this.ulChunk, this.lrChunk, mapType, this.zoom, highQuality));
        return this.drawSteps.size() > 1;
    }

    public boolean hasTexture(MapType mapType) {
        if (this.drawSteps.isEmpty()) {
            return false;
        }
        for (TileDrawStep tileDrawStep : this.drawSteps) {
            if (!tileDrawStep.hasTexture(mapType)) continue;
            return true;
        }
        return false;
    }

    public void clear() {
        this.drawSteps.clear();
    }

    private void updateRenderType() {
        this.renderType = Journeymap.getClient().getCoreProperties().tileRenderType.get();
        switch (this.renderType) {
            case 4: {
                this.textureFilter = 9728;
                this.textureWrap = 33071;
                debugGlSettings = "GL_NEAREST, GL_CLAMP_TO_EDGE";
                break;
            }
            case 3: {
                this.textureFilter = 9728;
                this.textureWrap = 33648;
                debugGlSettings = "GL_NEAREST, GL_MIRRORED_REPEAT";
                break;
            }
            case 2: {
                this.textureFilter = 9729;
                this.textureWrap = 33071;
                debugGlSettings = "GL_LINEAR, GL_CLAMP_TO_EDGE";
                break;
            }
            default: {
                this.textureFilter = 9729;
                this.textureWrap = 33648;
                debugGlSettings = "GL_LINEAR, GL_MIRRORED_REPEAT";
            }
        }
    }

    public String toString() {
        return "Tile [ r" + this.tileX + ", r" + this.tileZ + " (zoom " + this.zoom + ") ]";
    }

    public String cacheKey() {
        return this.theCacheKey;
    }

    public int hashCode() {
        return this.theHashCode;
    }

    public Point2D blockPixelOffsetInTile(double x, double z) {
        if (x < (double)this.ulBlock.x || Math.floor(x) > (double)this.lrBlock.x || z < (double)this.ulBlock.y || Math.floor(z) > (double)this.lrBlock.y) {
            throw new RuntimeException("Block " + x + "," + z + " isn't in " + this);
        }
        double localBlockX = (double)this.ulBlock.x - x;
        if (x < 0.0) {
            localBlockX += 1.0;
        }
        double localBlockZ = (double)this.ulBlock.y - z;
        if (z < 0.0) {
            localBlockZ += 1.0;
        }
        int blockSize = (int)Math.pow(2.0, this.zoom);
        double pixelOffsetX = 256.0 + localBlockX * (double)blockSize - (double)(blockSize / 2);
        double pixelOffsetZ = 256.0 + localBlockZ * (double)blockSize - (double)(blockSize / 2);
        return new Point2D.Double(pixelOffsetX, pixelOffsetZ);
    }

    boolean draw(TilePos pos, double offsetX, double offsetZ, float alpha, GridSpec gridSpec) {
        boolean somethingDrew = false;
        for (TileDrawStep tileDrawStep : this.drawSteps) {
            boolean ok = tileDrawStep.draw(pos, offsetX, offsetZ, alpha, this.textureFilter, this.textureWrap, gridSpec);
            if (!ok) continue;
            somethingDrew = true;
        }
        return somethingDrew;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        Tile tile = (Tile)o;
        if (this.tileX != tile.tileX) {
            return false;
        }
        if (this.tileZ != tile.tileZ) {
            return false;
        }
        return this.zoom == tile.zoom;
    }
}

