/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.ChunkPos
 */
package journeymap.client.ui.fullscreen.layer;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import journeymap.client.cartography.ChunkRenderController;
import journeymap.client.cartography.render.BaseRenderer;
import journeymap.client.data.DataCache;
import journeymap.client.io.FileHandler;
import journeymap.client.model.ChunkMD;
import journeymap.client.model.RegionCoord;
import journeymap.client.render.draw.DrawStep;
import journeymap.client.render.map.GridRenderer;
import journeymap.client.ui.fullscreen.Fullscreen;
import journeymap.client.ui.fullscreen.layer.BlockInfoLayer;
import journeymap.client.ui.fullscreen.layer.KeybindingInfoLayer;
import journeymap.client.ui.fullscreen.layer.ModOverlayLayer;
import journeymap.client.ui.fullscreen.layer.WaypointLayer;
import journeymap.common.Journeymap;
import journeymap.common.log.LogFormatter;
import net.minecraft.client.Minecraft;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;

public class LayerDelegate {
    long lastClick = 0L;
    BlockPos lastBlockPos = null;
    private List<DrawStep> drawSteps = new ArrayList<DrawStep>();
    private List<Layer> layers = new ArrayList<Layer>();

    public LayerDelegate(Fullscreen fullscreen) {
        this.layers.add(new ModOverlayLayer());
        this.layers.add(new BlockInfoLayer(fullscreen));
        this.layers.add(new WaypointLayer(fullscreen));
        this.layers.add(new KeybindingInfoLayer(fullscreen));
    }

    public void onMouseMove(Minecraft mc, GridRenderer gridRenderer, Point2D.Double mousePosition, float fontScale, boolean isScrolling) {
        if (this.lastBlockPos == null || !isScrolling) {
            this.lastBlockPos = this.getBlockPos(mc, gridRenderer, mousePosition);
        }
        this.drawSteps.clear();
        for (Layer layer : this.layers) {
            try {
                this.drawSteps.addAll(layer.onMouseMove(mc, gridRenderer, mousePosition, this.lastBlockPos, fontScale, isScrolling));
            }
            catch (Exception e) {
                Journeymap.getLogger().error(LogFormatter.toString(e));
            }
        }
    }

    public void onMouseClicked(Minecraft mc, GridRenderer gridRenderer, Point2D.Double mousePosition, int button, float fontScale) {
        this.lastBlockPos = gridRenderer.getBlockAtPixel(mousePosition);
        long sysTime = Minecraft.getSystemTime();
        boolean doubleClick = sysTime - this.lastClick < 450L;
        this.lastClick = sysTime;
        this.drawSteps.clear();
        for (Layer layer : this.layers) {
            try {
                this.drawSteps.addAll(layer.onMouseClick(mc, gridRenderer, mousePosition, this.lastBlockPos, button, doubleClick, fontScale));
                if (layer.propagateClick()) continue;
                break;
            }
            catch (Exception e) {
                Journeymap.getLogger().error(LogFormatter.toString(e));
            }
        }
    }

    public BlockPos getBlockPos(Minecraft mc, GridRenderer gridRenderer, Point2D.Double mousePosition) {
        ChunkRenderController crc;
        BlockPos seaLevel = gridRenderer.getBlockAtPixel(mousePosition);
        ChunkMD chunkMD = DataCache.INSTANCE.getChunkMD(seaLevel);
        if (chunkMD != null && (crc = Journeymap.getClient().getChunkRenderController()) != null) {
            ChunkPos chunkCoord = chunkMD.getCoord();
            RegionCoord rCoord = RegionCoord.fromChunkPos(FileHandler.getJMWorldDir(mc), gridRenderer.getMapType(), chunkCoord.x, chunkCoord.z);
            BaseRenderer chunkRenderer = crc.getRenderer(rCoord, gridRenderer.getMapType(), chunkMD);
            int blockY = chunkRenderer.getBlockHeight(chunkMD, seaLevel);
            return new BlockPos(seaLevel.getX(), blockY, seaLevel.getZ());
        }
        return seaLevel;
    }

    public List<DrawStep> getDrawSteps() {
        return this.drawSteps;
    }

    public static interface Layer {
        public List<DrawStep> onMouseMove(Minecraft var1, GridRenderer var2, Point2D.Double var3, BlockPos var4, float var5, boolean var6);

        public List<DrawStep> onMouseClick(Minecraft var1, GridRenderer var2, Point2D.Double var3, BlockPos var4, int var5, boolean var6, float var7);

        public boolean propagateClick();
    }
}

