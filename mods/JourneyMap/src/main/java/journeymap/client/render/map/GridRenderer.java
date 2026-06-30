/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.MathHelper
 *  net.minecraftforge.fml.client.FMLClientHandler
 *  org.apache.logging.log4j.Logger
 *  org.lwjgl.BufferUtils
 *  org.lwjgl.opengl.GL11
 *  org.lwjgl.util.glu.GLU
 */
package journeymap.client.render.map;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;
import journeymap.client.api.display.Context;
import journeymap.client.api.event.ClientEvent;
import journeymap.client.api.event.DisplayUpdateEvent;
import journeymap.client.api.impl.ClientAPI;
import journeymap.client.api.impl.ClientEventManager;
import journeymap.client.api.util.UIState;
import journeymap.client.data.DataCache;
import journeymap.client.log.StatTimer;
import journeymap.client.model.GridSpec;
import journeymap.client.model.MapType;
import journeymap.client.model.RegionImageCache;
import journeymap.client.render.draw.DrawStep;
import journeymap.client.render.draw.DrawUtil;
import journeymap.client.render.map.Tile;
import journeymap.client.render.map.TileDrawStepCache;
import journeymap.client.render.map.TilePos;
import journeymap.common.Journeymap;
import net.minecraft.client.Minecraft;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.client.FMLClientHandler;
import org.apache.logging.log4j.Logger;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;

public class GridRenderer {
    private static boolean enabled = true;
    private static HashMap<String, String> messages = new HashMap();
    private final TilePos centerPos = new TilePos(0, 0);
    private final Logger logger = Journeymap.getLogger();
    private final boolean debug = this.logger.isDebugEnabled();
    private final TreeMap<TilePos, Tile> grid = new TreeMap();
    private final Point2D.Double centerPixelOffset = new Point2D.Double();
    private final int maxGlErrors = 20;
    private final Context.UI contextUi;
    StatTimer updateTilesTimer1 = StatTimer.get("GridRenderer.updateTiles(1)", 5, 500);
    StatTimer updateTilesTimer2 = StatTimer.get("GridRenderer.updateTiles(2)", 5, 500);
    private UIState uiState;
    private int glErrors = 0;
    private int gridSize;
    private double srcSize;
    private Rectangle2D.Double viewPort = null;
    private Rectangle2D.Double screenBounds = null;
    private AxisAlignedBB blockBounds = null;
    private int lastHeight = -1;
    private int lastWidth = -1;
    private MapType mapType;
    private String centerTileKey = "";
    private int zoom;
    private double centerBlockX;
    private double centerBlockZ;
    private File worldDir;
    private double currentRotation;
    private IntBuffer viewportBuf;
    private FloatBuffer modelMatrixBuf;
    private FloatBuffer projMatrixBuf;
    private FloatBuffer winPosBuf;
    private FloatBuffer objPosBuf;

    public GridRenderer(Context.UI contextUi, int gridSize) {
        this.contextUi = contextUi;
        this.uiState = UIState.newInactive(contextUi, FMLClientHandler.instance().getClient());
        this.viewportBuf = BufferUtils.createIntBuffer((int)16);
        this.modelMatrixBuf = BufferUtils.createFloatBuffer((int)16);
        this.projMatrixBuf = BufferUtils.createFloatBuffer((int)16);
        this.winPosBuf = BufferUtils.createFloatBuffer((int)16);
        this.objPosBuf = BufferUtils.createFloatBuffer((int)16);
        this.setGridSize(gridSize);
    }

    public static void addDebugMessage(String key, String message) {
        messages.put(key, message);
    }

    public static void removeDebugMessage(String key, String message) {
        messages.remove(key);
    }

    public static void clearDebugMessages() {
        messages.clear();
    }

    public static void setEnabled(boolean enabled) {
        GridRenderer.enabled = enabled;
        if (!enabled) {
            TileDrawStepCache.clear();
        }
    }

    public Context.UI getDisplay() {
        return this.contextUi;
    }

    public void setViewPort(Rectangle2D.Double viewPort) {
        this.viewPort = viewPort;
        this.screenBounds = null;
        this.updateBounds(this.lastWidth, this.lastHeight);
    }

    private void populateGrid(Tile centerTile) {
        int endRow = (this.gridSize - 1) / 2;
        int endCol = (this.gridSize - 1) / 2;
        int startRow = -endRow;
        int startCol = -endCol;
        for (int z = startRow; z <= endRow; ++z) {
            for (int x = startCol; x <= endCol; ++x) {
                TilePos pos = new TilePos(x, z);
                Tile tile = this.findNeighbor(centerTile, pos);
                this.grid.put(pos, tile);
            }
        }
    }

    public void move(int deltaBlockX, int deltaBlockZ) {
        this.center(this.worldDir, this.mapType, this.centerBlockX + (double)deltaBlockX, this.centerBlockZ + (double)deltaBlockZ, this.zoom);
    }

    public boolean center() {
        return this.center(this.worldDir, this.mapType, this.centerBlockX, this.centerBlockZ, this.zoom);
    }

    public boolean hasUnloadedTile() {
        return this.hasUnloadedTile(false);
    }

    public int getGridSize() {
        return this.gridSize;
    }

    public void setGridSize(int gridSize) {
        this.gridSize = gridSize;
        this.srcSize = gridSize * 512;
    }

    public boolean hasUnloadedTile(boolean preview) {
        for (Map.Entry<TilePos, Tile> entry : this.grid.entrySet()) {
            Tile tile;
            if (!this.isOnScreen(entry.getKey()) || (tile = entry.getValue()) != null && tile.hasTexture(this.mapType)) continue;
            return true;
        }
        return false;
    }

    public boolean center(File worldDir, MapType mapType, double blockX, double blockZ, int zoom) {
        int tileZ;
        boolean mapTypeChanged;
        boolean bl = mapTypeChanged = !Objects.equals(worldDir, this.worldDir) || !Objects.equals(mapType, this.mapType);
        if (!Objects.equals(worldDir, this.worldDir)) {
            this.worldDir = worldDir;
        }
        if (blockX == this.centerBlockX && blockZ == this.centerBlockZ && zoom == this.zoom && !mapTypeChanged && !this.grid.isEmpty()) {
            if (!Objects.equals(mapType.apiMapType, this.uiState.mapType)) {
                this.updateUIState(true);
            }
            return false;
        }
        this.centerBlockX = blockX;
        this.centerBlockZ = blockZ;
        this.zoom = zoom;
        int tileX = Tile.blockPosToTile((int)Math.floor(blockX), this.zoom);
        String newCenterKey = Tile.toCacheKey(tileX, tileZ = Tile.blockPosToTile((int)Math.floor(blockZ), this.zoom), zoom);
        boolean centerTileChanged = !newCenterKey.equals(this.centerTileKey);
        this.centerTileKey = newCenterKey;
        if (mapTypeChanged || centerTileChanged || this.grid.isEmpty()) {
            Tile newCenterTile = this.findTile(tileX, tileZ, zoom);
            this.populateGrid(newCenterTile);
            if (this.debug) {
                this.logger.debug("Centered on " + newCenterTile + " with pixel offsets of " + this.centerPixelOffset.x + "," + this.centerPixelOffset.y);
                Minecraft mc = FMLClientHandler.instance().getClient();
                BufferedImage tmp = new BufferedImage(mc.displayWidth, mc.displayHeight, 2);
                Graphics2D g = tmp.createGraphics();
                g.setStroke(new BasicStroke(1.0f));
                g.setColor(Color.GREEN);
                g.drawLine(mc.displayWidth / 2, 0, mc.displayWidth / 2, mc.displayHeight);
                g.drawLine(0, mc.displayHeight / 2, mc.displayWidth, mc.displayHeight / 2);
            }
        }
        this.updateUIState(true);
        return true;
    }

    public void updateTiles(MapType mapType, int zoom, boolean highQuality, int width, int height, boolean fullUpdate, double xOffset, double yOffset) {
        this.updateTilesTimer1.start();
        this.mapType = mapType;
        this.zoom = zoom;
        this.updateBounds(width, height);
        Tile centerTile = this.grid.get(this.centerPos);
        if (centerTile == null || centerTile.zoom != this.zoom) {
            int tileX = Tile.blockPosToTile((int)Math.floor(this.centerBlockX), this.zoom);
            int tileZ = Tile.blockPosToTile((int)Math.floor(this.centerBlockZ), this.zoom);
            centerTile = this.findTile(tileX, tileZ, this.zoom);
            this.populateGrid(centerTile);
        }
        Point2D blockPixelOffset = centerTile.blockPixelOffsetInTile(this.centerBlockX, this.centerBlockZ);
        double blockSizeOffset = Math.pow(2.0, zoom) / 2.0;
        int magic = (this.gridSize == 5 ? 2 : 1) * 512;
        double displayOffsetX = xOffset + (double)magic - (this.srcSize - (double)this.lastWidth) / 2.0;
        displayOffsetX = this.centerBlockX < 0.0 ? (displayOffsetX -= blockSizeOffset) : (displayOffsetX += blockSizeOffset);
        double displayOffsetY = yOffset + (double)magic - (this.srcSize - (double)this.lastHeight) / 2.0;
        displayOffsetY = this.centerBlockZ < 0.0 ? (displayOffsetY -= blockSizeOffset) : (displayOffsetY += blockSizeOffset);
        this.centerPixelOffset.setLocation(displayOffsetX + blockPixelOffset.getX(), displayOffsetY + blockPixelOffset.getY());
        this.updateTilesTimer1.stop();
        if (!fullUpdate) {
            return;
        }
        this.updateTilesTimer2.start();
        for (Map.Entry<TilePos, Tile> entry : this.grid.entrySet()) {
            TilePos pos = entry.getKey();
            Tile tile = entry.getValue();
            if (tile == null) {
                tile = this.findNeighbor(centerTile, pos);
                this.grid.put(pos, tile);
            }
            if (tile.hasTexture(this.mapType)) continue;
            tile.updateTexture(this.worldDir, this.mapType, highQuality);
        }
        this.updateTilesTimer2.stop();
    }

    public Point2D.Double getCenterPixelOffset() {
        return this.centerPixelOffset;
    }

    public AxisAlignedBB getBlockBounds() {
        return this.blockBounds;
    }

    public BlockPos getBlockAtPixel(Point2D.Double pixel) {
        double centerPixelX = (double)this.lastWidth / 2.0;
        double centerPixelZ = (double)this.lastHeight / 2.0;
        double deltaX = (centerPixelX - pixel.x) / this.uiState.blockSize;
        double deltaZ = (centerPixelZ - ((double)this.lastHeight - pixel.y)) / this.uiState.blockSize;
        int x = MathHelper.floor((double)(this.centerBlockX - deltaX));
        int z = MathHelper.floor((double)(this.centerBlockZ + deltaZ));
        int y = 0;
        y = DataCache.getPlayer().underground != false ? MathHelper.floor((double)DataCache.getPlayer().posY) : FMLClientHandler.instance().getClient().world.getSeaLevel();
        return new BlockPos(x, y, z);
    }

    public Point2D.Double getBlockPixelInGrid(BlockPos pos) {
        return this.getBlockPixelInGrid(pos.getX(), pos.getZ());
    }

    public Point2D.Double getBlockPixelInGrid(double blockX, double blockZ) {
        Minecraft mc = FMLClientHandler.instance().getClient();
        double localBlockX = blockX - this.centerBlockX;
        double localBlockZ = blockZ - this.centerBlockZ;
        int blockSize = (int)Math.pow(2.0, this.zoom);
        double pixelOffsetX = (double)mc.displayWidth / 2.0 + localBlockX * (double)blockSize;
        double pixelOffsetZ = (double)mc.displayHeight / 2.0 + localBlockZ * (double)blockSize;
        return new Point2D.Double(pixelOffsetX, pixelOffsetZ);
    }

    public void draw(List<? extends DrawStep> drawStepList, double xOffset, double yOffset, double fontScale, double rotation) {
        if (!enabled || drawStepList == null || drawStepList.isEmpty()) {
            return;
        }
        this.draw(xOffset, yOffset, fontScale, rotation, drawStepList.toArray(new DrawStep[drawStepList.size()]));
    }

    public void draw(double xOffset, double yOffset, double fontScale, double rotation, DrawStep ... drawSteps) {
        if (enabled) {
            for (DrawStep.Pass pass : DrawStep.Pass.values()) {
                for (DrawStep drawStep : drawSteps) {
                    drawStep.draw(pass, xOffset, yOffset, this, fontScale, rotation);
                }
            }
        }
    }

    public void draw(float alpha, double offsetX, double offsetZ, boolean showGrid) {
        double centerZ;
        double centerX;
        if (enabled && !this.grid.isEmpty()) {
            centerX = offsetX + this.centerPixelOffset.x;
            centerZ = offsetZ + this.centerPixelOffset.y;
            GridSpec gridSpec = showGrid ? Journeymap.getClient().getCoreProperties().gridSpecs.getSpec(this.mapType) : null;
            boolean somethingDrew = false;
            for (Map.Entry<TilePos, Tile> entry : this.grid.entrySet()) {
                TilePos pos = entry.getKey();
                Tile tile = entry.getValue();
                if (tile == null || !tile.draw(pos, centerX, centerZ, alpha, gridSpec)) continue;
                somethingDrew = true;
            }
            if (!somethingDrew) {
                RegionImageCache.INSTANCE.clear();
            }
        }
        if (!messages.isEmpty()) {
            centerX = offsetX + this.centerPixelOffset.x + (this.centerPos.endX - this.centerPos.startX) / 2.0;
            centerZ = offsetZ + this.centerPixelOffset.y + (this.centerPos.endZ - this.centerPos.startZ) / 2.0 - 60.0;
            for (String message : messages.values()) {
                DrawUtil.drawLabel(message, centerX, centerZ += 20.0, DrawUtil.HAlign.Center, DrawUtil.VAlign.Below, 0, 1.0f, 0xFFFFFF, 1.0f, 1.0, true);
            }
        }
    }

    public void clearGlErrors(boolean report) {
        int err;
        while ((err = GL11.glGetError()) != 0) {
            if (!report || this.glErrors > 20) continue;
            ++this.glErrors;
            if (this.glErrors < 20) {
                this.logger.warn("GL Error occurred during JourneyMap draw: " + err);
                continue;
            }
            this.logger.warn("GL Error reporting during JourneyMap will be suppressed after max errors: 20");
        }
    }

    public Point2D.Double getPixel(double blockX, double blockZ) {
        Point2D.Double pixel = this.getBlockPixelInGrid(blockX, blockZ);
        if (this.isOnScreen(pixel)) {
            return pixel;
        }
        return null;
    }

    public void ensureOnScreen(Point2D pixel) {
        if (this.screenBounds == null) {
            return;
        }
        double x = pixel.getX();
        if (x < this.screenBounds.x) {
            x = this.screenBounds.x;
        } else if (x > this.screenBounds.getMaxX()) {
            x = this.screenBounds.getMaxX();
        }
        double y = pixel.getY();
        if (y < this.screenBounds.y) {
            y = this.screenBounds.y;
        } else if (y > this.screenBounds.getMaxY()) {
            y = this.screenBounds.getMaxY();
        }
        pixel.setLocation(x, y);
    }

    private boolean isOnScreen(TilePos pos) {
        return true;
    }

    public boolean isOnScreen(Point2D.Double pixel) {
        return this.screenBounds.contains(pixel);
    }

    public boolean isOnScreen(Rectangle2D.Double bounds) {
        return this.screenBounds.intersects(bounds);
    }

    public boolean isOnScreen(double x, double y) {
        return this.screenBounds.contains(x, y);
    }

    public boolean isOnScreen(double startX, double startY, int width, int height) {
        if (this.screenBounds == null) {
            return false;
        }
        return this.screenBounds.intersects(startX, startY, width, height);
    }

    private void updateBounds(int width, int height) {
        if (this.screenBounds == null || this.lastWidth != width || this.lastHeight != height || this.blockBounds == null) {
            this.lastWidth = width;
            this.lastHeight = height;
            if (this.viewPort == null) {
                int pad = 32;
                this.screenBounds = new Rectangle2D.Double(-pad, -pad, width + pad, height + pad);
            } else {
                this.screenBounds = new Rectangle2D.Double(((double)width - this.viewPort.width) / 2.0, ((double)height - this.viewPort.height) / 2.0, this.viewPort.width, this.viewPort.height);
            }
            ClientAPI.INSTANCE.flagOverlaysForRerender();
        }
    }

    public void updateUIState(boolean isActive) {
        if (isActive && this.screenBounds == null) {
            return;
        }
        UIState newState = null;
        if (isActive) {
            int worldHeight = FMLClientHandler.instance().getClient().world.getActualHeight();
            int pad = 32;
            BlockPos upperLeft = this.getBlockAtPixel(new Point2D.Double(this.screenBounds.getMinX(), this.screenBounds.getMinY()));
            BlockPos lowerRight = this.getBlockAtPixel(new Point2D.Double(this.screenBounds.getMaxX(), this.screenBounds.getMaxY()));
            this.blockBounds = new AxisAlignedBB(upperLeft.add(-pad, 0, -pad), lowerRight.add(pad, worldHeight, pad));
            try {
                newState = new UIState(this.contextUi, true, this.mapType.dimension, this.zoom, this.mapType.apiMapType, new BlockPos(this.centerBlockX, 0.0, this.centerBlockZ), this.mapType.vSlice, this.blockBounds, this.screenBounds);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            newState = UIState.newInactive(this.uiState);
        }
        if (!newState.equals(this.uiState)) {
            this.uiState = newState;
            ClientEventManager clientEventManager = ClientAPI.INSTANCE.getClientEventManager();
            if (clientEventManager.canFireClientEvent(ClientEvent.Type.DISPLAY_UPDATE)) {
                clientEventManager.fireDisplayUpdateEvent(new DisplayUpdateEvent(this.uiState));
            }
        }
    }

    private Tile findNeighbor(Tile tile, TilePos pos) {
        if (pos.deltaX == 0 && pos.deltaZ == 0) {
            return tile;
        }
        return this.findTile(tile.tileX + pos.deltaX, tile.tileZ + pos.deltaZ, tile.zoom);
    }

    private Tile findTile(int tileX, int tileZ, int zoom) {
        return Tile.create(tileX, tileZ, zoom, this.worldDir, this.mapType, Journeymap.getClient().getCoreProperties().tileHighDisplayQuality.get());
    }

    public void setContext(File worldDir, MapType mapType) {
        this.worldDir = worldDir;
        this.mapType = mapType;
        TileDrawStepCache.setContext(worldDir, mapType);
    }

    public void updateRotation(double rotation) {
        this.currentRotation = rotation;
        GL11.glGetInteger((int)2978, (IntBuffer)this.viewportBuf);
        GL11.glGetFloat((int)2982, (FloatBuffer)this.modelMatrixBuf);
        GL11.glGetFloat((int)2983, (FloatBuffer)this.projMatrixBuf);
    }

    public Point2D shiftWindowPosition(double x, double y, int shiftX, int shiftY) {
        if (this.currentRotation % 360.0 == 0.0) {
            return new Point2D.Double(x + (double)shiftX, y + (double)shiftY);
        }
        GLU.gluProject((float)((float)x), (float)((float)y), (float)0.0f, (FloatBuffer)this.modelMatrixBuf, (FloatBuffer)this.projMatrixBuf, (IntBuffer)this.viewportBuf, (FloatBuffer)this.winPosBuf);
        GLU.gluUnProject((float)(this.winPosBuf.get(0) + (float)shiftX), (float)(this.winPosBuf.get(1) + (float)shiftY), (float)0.0f, (FloatBuffer)this.modelMatrixBuf, (FloatBuffer)this.projMatrixBuf, (IntBuffer)this.viewportBuf, (FloatBuffer)this.objPosBuf);
        return new Point2D.Float(this.objPosBuf.get(0), this.objPosBuf.get(1));
    }

    public Point2D.Double getWindowPosition(Point2D.Double matrixPixel) {
        if (this.currentRotation % 360.0 == 0.0) {
            return matrixPixel;
        }
        GLU.gluProject((float)((float)matrixPixel.getX()), (float)((float)matrixPixel.getY()), (float)0.0f, (FloatBuffer)this.modelMatrixBuf, (FloatBuffer)this.projMatrixBuf, (IntBuffer)this.viewportBuf, (FloatBuffer)this.winPosBuf);
        return new Point2D.Double(this.winPosBuf.get(0), this.winPosBuf.get(1));
    }

    public Point2D.Double getMatrixPosition(Point2D.Double windowPixel) {
        GLU.gluUnProject((float)((float)windowPixel.x), (float)((float)windowPixel.y), (float)0.0f, (FloatBuffer)this.modelMatrixBuf, (FloatBuffer)this.projMatrixBuf, (IntBuffer)this.viewportBuf, (FloatBuffer)this.objPosBuf);
        return new Point2D.Double(this.objPosBuf.get(0), this.objPosBuf.get(1));
    }

    public double getCenterBlockX() {
        return this.centerBlockX;
    }

    public double getCenterBlockZ() {
        return this.centerBlockZ;
    }

    public File getWorldDir() {
        return this.worldDir;
    }

    public MapType getMapType() {
        return this.mapType;
    }

    public int getZoom() {
        return this.zoom;
    }

    public boolean setZoom(int zoom) {
        return this.center(this.worldDir, this.mapType, this.centerBlockX, this.centerBlockZ, zoom);
    }

    public int getRenderSize() {
        return this.gridSize * 512;
    }

    public void clear() {
        this.grid.clear();
        messages.clear();
    }

    public int getWidth() {
        return this.lastWidth;
    }

    public int getHeight() {
        return this.lastHeight;
    }

    public UIState getUIState() {
        return this.uiState;
    }
}

