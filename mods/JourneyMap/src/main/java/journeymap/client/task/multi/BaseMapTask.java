/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.util.math.ChunkPos
 *  net.minecraft.world.World
 *  net.minecraftforge.fml.client.FMLClientHandler
 *  org.apache.logging.log4j.Logger
 */
package journeymap.client.task.multi;

import java.io.File;
import java.util.Collection;
import java.util.Iterator;
import journeymap.client.JourneymapClient;
import journeymap.client.cartography.ChunkRenderController;
import journeymap.client.data.DataCache;
import journeymap.client.log.StatTimer;
import journeymap.client.model.ChunkMD;
import journeymap.client.model.MapType;
import journeymap.client.model.RegionCoord;
import journeymap.client.model.RegionImageCache;
import journeymap.client.task.multi.ITask;
import journeymap.common.Journeymap;
import journeymap.common.log.LogFormatter;
import net.minecraft.client.Minecraft;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.FMLClientHandler;
import org.apache.logging.log4j.Logger;

public abstract class BaseMapTask
implements ITask {
    static final Logger logger = Journeymap.getLogger();
    protected static ChunkPos[] keepAliveOffsets = new ChunkPos[]{new ChunkPos(0, -1), new ChunkPos(-1, 0), new ChunkPos(-1, -1)};
    final World world;
    final Collection<ChunkPos> chunkCoords;
    final boolean flushCacheWhenDone;
    final ChunkRenderController renderController;
    final int elapsedLimit;
    final MapType mapType;
    final boolean asyncFileWrites;

    public BaseMapTask(ChunkRenderController renderController, World world, MapType mapType, Collection<ChunkPos> chunkCoords, boolean flushCacheWhenDone, boolean asyncFileWrites, int elapsedLimit) {
        this.renderController = renderController;
        this.world = world;
        this.mapType = mapType;
        this.chunkCoords = chunkCoords;
        this.asyncFileWrites = asyncFileWrites;
        this.flushCacheWhenDone = flushCacheWhenDone;
        this.elapsedLimit = elapsedLimit;
    }

    public void initTask(Minecraft mc, JourneymapClient jm, File jmWorldDir, boolean threadLogging) throws InterruptedException {
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void performTask(Minecraft mc, JourneymapClient jm, File jmWorldDir, boolean threadLogging) throws InterruptedException {
        if (!this.mapType.isAllowed()) {
            this.complete(0, true, false);
            return;
        }
        StatTimer timer = StatTimer.get(this.getClass().getSimpleName() + ".performTask", 5, this.elapsedLimit).start();
        this.initTask(mc, jm, jmWorldDir, threadLogging);
        int count = 0;
        try {
            if (mc.field_71441_e == null) {
                this.complete(count, true, false);
                return;
            }
            Iterator<ChunkPos> chunkIter = this.chunkCoords.iterator();
            int currentDimension = FMLClientHandler.instance().getClient().field_71439_g.field_70170_p.field_73011_w.getDimension();
            if (currentDimension != this.mapType.dimension) {
                if (threadLogging) {
                    logger.debug("Dimension changed, map task obsolete.");
                }
                timer.cancel();
                this.complete(count, true, false);
                return;
            }
            ChunkPos playerChunk = new ChunkPos(FMLClientHandler.instance().getClient().field_71439_g.func_180425_c());
            while (chunkIter.hasNext()) {
                if (!jm.isMapping().booleanValue()) {
                    if (threadLogging) {
                        logger.debug("JM isn't mapping, aborting");
                    }
                    timer.cancel();
                    this.complete(count, true, false);
                    return;
                }
                if (Thread.interrupted()) {
                    throw new InterruptedException();
                }
                ChunkPos coord = chunkIter.next();
                ChunkMD chunkMd = DataCache.INSTANCE.getChunkMD(coord);
                if (chunkMd == null || !chunkMd.hasChunk()) continue;
                try {
                    RegionCoord rCoord = RegionCoord.fromChunkPos(jmWorldDir, this.mapType, chunkMd.getCoord().field_77276_a, chunkMd.getCoord().field_77275_b);
                    boolean rendered = this.renderController.renderChunk(rCoord, this.mapType, chunkMd);
                    if (!rendered) continue;
                    ++count;
                }
                catch (Throwable t) {
                    logger.warn("Error rendering chunk " + chunkMd + ": " + t.getMessage());
                }
            }
            if (!jm.isMapping().booleanValue()) {
                if (threadLogging) {
                    logger.debug("JM isn't mapping, aborting.");
                }
                timer.cancel();
                this.complete(count, true, false);
                return;
            }
            if (Thread.interrupted()) {
                timer.cancel();
                throw new InterruptedException();
            }
            RegionImageCache.INSTANCE.updateTextures(this.flushCacheWhenDone, this.asyncFileWrites);
            this.chunkCoords.clear();
            this.complete(count, false, false);
            timer.stop();
        }
        catch (InterruptedException t) {
            Journeymap.getLogger().warn("Task thread interrupted: " + this);
            timer.cancel();
            throw t;
        }
        catch (Throwable t) {
            String error = "Unexpected error in BaseMapTask: " + LogFormatter.toString(t);
            Journeymap.getLogger().error(error);
            this.complete(count, false, true);
            timer.cancel();
        }
        finally {
            if (threadLogging) {
                timer.report();
            }
        }
    }

    protected abstract void complete(int var1, boolean var2, boolean var3);

    public String toString() {
        return this.getClass().getSimpleName() + "{world=" + this.world + ", mapType=" + this.mapType + ", chunkCoords=" + this.chunkCoords + ", flushCacheWhenDone=" + this.flushCacheWhenDone + '}';
    }
}

