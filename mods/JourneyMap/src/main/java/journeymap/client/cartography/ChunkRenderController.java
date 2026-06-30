/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.logging.log4j.Level
 */
package journeymap.client.cartography;

import journeymap.client.cartography.render.BaseRenderer;
import journeymap.client.cartography.render.CaveRenderer;
import journeymap.client.cartography.render.EndCaveRenderer;
import journeymap.client.cartography.render.EndSurfaceRenderer;
import journeymap.client.cartography.render.NetherRenderer;
import journeymap.client.cartography.render.SurfaceRenderer;
import journeymap.client.cartography.render.TopoRenderer;
import journeymap.client.model.ChunkMD;
import journeymap.client.model.MapType;
import journeymap.client.model.RegionCoord;
import journeymap.client.model.RegionImageCache;
import journeymap.client.model.RegionImageSet;
import journeymap.client.render.ComparableBufferedImage;
import journeymap.common.Journeymap;
import journeymap.common.log.LogFormatter;
import org.apache.logging.log4j.Level;

public class ChunkRenderController {
    private final SurfaceRenderer overWorldSurfaceRenderer = new SurfaceRenderer();
    private final BaseRenderer netherRenderer;
    private final SurfaceRenderer endSurfaceRenderer;
    private final BaseRenderer endCaveRenderer;
    private final BaseRenderer topoRenderer;
    private final BaseRenderer overWorldCaveRenderer = new CaveRenderer(this.overWorldSurfaceRenderer);

    public ChunkRenderController() {
        this.netherRenderer = new NetherRenderer();
        this.endSurfaceRenderer = new EndSurfaceRenderer();
        this.endCaveRenderer = new EndCaveRenderer(this.endSurfaceRenderer);
        this.topoRenderer = new TopoRenderer();
    }

    public BaseRenderer getRenderer(RegionCoord rCoord, MapType mapType, ChunkMD chunkMd) {
        block8: {
            try {
                RegionImageSet regionImageSet = RegionImageCache.INSTANCE.getRegionImageSet(rCoord);
                if (mapType.isUnderground()) {
                    ComparableBufferedImage image = regionImageSet.getChunkImage(chunkMd, mapType);
                    if (image != null) {
                        switch (rCoord.dimension) {
                            case -1: {
                                return this.netherRenderer;
                            }
                            case 1: {
                                return this.endCaveRenderer;
                            }
                        }
                        return this.overWorldCaveRenderer;
                    }
                    break block8;
                }
                return this.overWorldSurfaceRenderer;
            }
            catch (Throwable t) {
                Journeymap.getLogger().error("Unexpected error in ChunkRenderController: " + LogFormatter.toPartialString(t));
            }
        }
        return null;
    }

    public boolean renderChunk(RegionCoord rCoord, MapType mapType, ChunkMD chunkMd) {
        if (!Journeymap.getClient().isMapping().booleanValue()) {
            return false;
        }
        boolean renderOkay = false;
        try {
            RegionImageSet regionImageSet = RegionImageCache.INSTANCE.getRegionImageSet(rCoord);
            if (mapType.isUnderground()) {
                ComparableBufferedImage chunkSliceImage = regionImageSet.getChunkImage(chunkMd, mapType);
                if (chunkSliceImage != null) {
                    switch (rCoord.dimension) {
                        case -1: {
                            renderOkay = this.netherRenderer.render(chunkSliceImage, chunkMd, mapType.vSlice);
                            break;
                        }
                        case 1: {
                            renderOkay = this.endCaveRenderer.render(chunkSliceImage, chunkMd, mapType.vSlice);
                            break;
                        }
                        default: {
                            renderOkay = this.overWorldCaveRenderer.render(chunkSliceImage, chunkMd, mapType.vSlice);
                        }
                    }
                    if (renderOkay) {
                        regionImageSet.setChunkImage(chunkMd, mapType, chunkSliceImage);
                    }
                }
            } else if (mapType.isTopo()) {
                ComparableBufferedImage imageTopo = regionImageSet.getChunkImage(chunkMd, MapType.topo(rCoord.dimension));
                renderOkay = this.topoRenderer.render(imageTopo, chunkMd, null);
                if (renderOkay) {
                    regionImageSet.setChunkImage(chunkMd, MapType.topo(rCoord.dimension), imageTopo);
                }
            } else {
                ComparableBufferedImage imageNight;
                ComparableBufferedImage imageDay = regionImageSet.getChunkImage(chunkMd, MapType.day(rCoord.dimension));
                renderOkay = this.overWorldSurfaceRenderer.render(imageDay, imageNight = regionImageSet.getChunkImage(chunkMd, MapType.night(rCoord.dimension)), chunkMd);
                if (renderOkay) {
                    regionImageSet.setChunkImage(chunkMd, MapType.day(rCoord.dimension), imageDay);
                    regionImageSet.setChunkImage(chunkMd, MapType.night(rCoord.dimension), imageNight);
                }
            }
        }
        catch (ArrayIndexOutOfBoundsException e) {
            Journeymap.getLogger().log(Level.WARN, LogFormatter.toString(e));
            return false;
        }
        catch (Throwable t) {
            Journeymap.getLogger().error("Unexpected error in ChunkRenderController: " + LogFormatter.toString(t));
        }
        if (!renderOkay && Journeymap.getLogger().isDebugEnabled()) {
            Journeymap.getLogger().debug(String.format("Chunk %s render failed for %s", chunkMd.getCoord(), mapType));
        }
        return renderOkay;
    }
}

