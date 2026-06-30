/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.world.biome.Biome
 *  net.minecraftforge.fml.client.FMLClientHandler
 */
package journeymap.client.ui.fullscreen.layer;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import journeymap.client.Constants;
import journeymap.client.data.DataCache;
import journeymap.client.io.ThemeLoader;
import journeymap.client.model.BlockMD;
import journeymap.client.model.ChunkMD;
import journeymap.client.model.MapType;
import journeymap.client.model.RegionCoord;
import journeymap.client.properties.FullMapProperties;
import journeymap.client.render.draw.DrawStep;
import journeymap.client.render.draw.DrawUtil;
import journeymap.client.render.map.GridRenderer;
import journeymap.client.ui.fullscreen.Fullscreen;
import journeymap.client.ui.fullscreen.layer.LayerDelegate;
import journeymap.client.ui.option.LocationFormat;
import journeymap.client.ui.theme.Theme;
import journeymap.client.world.JmBlockAccess;
import journeymap.common.Journeymap;
import net.minecraft.client.Minecraft;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.client.FMLClientHandler;

public class BlockInfoLayer
implements LayerDelegate.Layer {
    private final List<DrawStep> drawStepList = new ArrayList<DrawStep>(1);
    LocationFormat locationFormat = new LocationFormat();
    LocationFormat.LocationFormatKeys locationFormatKeys;
    BlockPos lastCoord = null;
    PlayerInfoStep playerInfoStep;
    BlockInfoStep blockInfoStep;
    private boolean isSinglePlayer;
    private final Fullscreen fullscreen;
    private final Minecraft mc;

    public BlockInfoLayer(Fullscreen fullscreen) {
        this.fullscreen = fullscreen;
        this.blockInfoStep = new BlockInfoStep();
        this.playerInfoStep = new PlayerInfoStep();
        this.mc = FMLClientHandler.instance().getClient();
        this.isSinglePlayer = this.mc.isSingleplayer();
    }

    @Override
    public List<DrawStep> onMouseMove(Minecraft mc, GridRenderer gridRenderer, Point2D.Double mousePosition, BlockPos blockPos, float fontScale, boolean isScrolling) {
        Rectangle2D.Double optionsToolbarRect = this.fullscreen.getOptionsToolbarBounds();
        Rectangle2D.Double menuToolbarRect = this.fullscreen.getMenuToolbarBounds();
        if (optionsToolbarRect == null || menuToolbarRect == null) {
            return Collections.EMPTY_LIST;
        }
        if (this.drawStepList.isEmpty()) {
            this.drawStepList.add(this.playerInfoStep);
            this.drawStepList.add(this.blockInfoStep);
        }
        this.playerInfoStep.update(mc.displayWidth / 2, optionsToolbarRect.getMaxY());
        if (!blockPos.equals((Object)this.lastCoord)) {
            FullMapProperties fullMapProperties = Journeymap.getClient().getFullMapProperties();
            this.locationFormatKeys = this.locationFormat.getFormatKeys(fullMapProperties.locationFormat.get());
            this.lastCoord = blockPos;
            ChunkMD chunkMD = DataCache.INSTANCE.getChunkMD(blockPos);
            String info = "";
            if (chunkMD != null && chunkMD.hasChunk()) {
                BlockMD blockMD = chunkMD.getBlockMD(blockPos.up());
                if (blockMD == null || blockMD.isIgnore()) {
                    blockMD = chunkMD.getBlockMD(blockPos.down());
                }
                Biome biome = JmBlockAccess.INSTANCE.getBiome(blockPos);
                RegionCoord regionCoord = RegionCoord.fromChunkPos(null, MapType.none(), chunkMD.getChunk().x, chunkMD.getChunk().z);
                String region = "Region: x:" + regionCoord.regionX + " z:" + regionCoord.regionZ;
                info = this.locationFormatKeys.format(fullMapProperties.locationFormatVerbose.get(), blockPos.getX(), blockPos.getZ(), blockPos.getY(), blockPos.getY() >> 4) + " " + biome.getBiomeName() + " " + region;
                if (!blockMD.isIgnore()) {
                    info = String.format("%s \u25a0 %s", blockMD.getName(), info);
                }
            } else {
                Biome biome;
                info = Constants.getString("jm.common.location_xz_verbose", blockPos.getX(), blockPos.getZ());
                if (this.isSinglePlayer && (biome = JmBlockAccess.INSTANCE.getBiome(blockPos, null)) != null) {
                    info = info + " " + biome.getBiomeName();
                }
            }
            this.blockInfoStep.update(info, gridRenderer.getWidth() / 2, menuToolbarRect.getMinY());
        }
        return this.drawStepList;
    }

    @Override
    public List<DrawStep> onMouseClick(Minecraft mc, GridRenderer gridRenderer, Point2D.Double mousePosition, BlockPos blockCoord, int button, boolean doubleClick, float fontScale) {
        return Collections.EMPTY_LIST;
    }

    @Override
    public boolean propagateClick() {
        return true;
    }

    class BlockInfoStep
    implements DrawStep {
        private Theme.LabelSpec labelSpec;
        private double x;
        private double y;
        private String text;

        BlockInfoStep() {
        }

        void update(String text, double x, double y) {
            Theme theme = ThemeLoader.getCurrentTheme();
            this.labelSpec = theme.fullscreen.statusLabel;
            this.text = text;
            this.x = x;
            this.y = y - (double)(theme.container.toolbar.horizontal.margin * BlockInfoLayer.this.fullscreen.getScreenScaleFactor());
        }

        @Override
        public void draw(DrawStep.Pass pass, double xOffset, double yOffset, GridRenderer gridRenderer, double fontScale, double rotation) {
            if (pass == DrawStep.Pass.Text) {
                DrawUtil.drawLabel(this.text, this.labelSpec, this.x, this.y, DrawUtil.HAlign.Center, DrawUtil.VAlign.Above, fontScale, 0.0);
            }
        }

        @Override
        public int getDisplayOrder() {
            return 0;
        }

        @Override
        public String getModId() {
            return "journeymap";
        }
    }

    class PlayerInfoStep
    implements DrawStep {
        private Theme.LabelSpec labelSpec;
        private String prefix;
        private double x;
        private double y;

        PlayerInfoStep() {
        }

        void update(double x, double y) {
            Theme theme = ThemeLoader.getCurrentTheme();
            this.labelSpec = theme.fullscreen.statusLabel;
            if (this.prefix == null) {
                this.prefix = ((BlockInfoLayer)BlockInfoLayer.this).mc.player.getName() + " \u25a0 ";
            }
            this.x = x;
            this.y = y + (double)(theme.container.toolbar.horizontal.margin * BlockInfoLayer.this.fullscreen.getScreenScaleFactor());
        }

        @Override
        public void draw(DrawStep.Pass pass, double xOffset, double yOffset, GridRenderer gridRenderer, double fontScale, double rotation) {
            if (pass == DrawStep.Pass.Text) {
                DrawUtil.drawLabel(this.prefix + Fullscreen.state().playerLastPos, this.labelSpec, this.x, this.y, DrawUtil.HAlign.Center, DrawUtil.VAlign.Below, fontScale, 0.0);
            }
        }

        @Override
        public int getDisplayOrder() {
            return 0;
        }

        @Override
        public String getModId() {
            return "journeymap";
        }
    }
}

