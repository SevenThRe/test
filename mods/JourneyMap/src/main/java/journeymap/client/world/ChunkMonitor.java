/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.cache.CacheLoader
 *  javax.annotation.Nullable
 *  net.minecraft.block.state.IBlockState
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.SoundCategory
 *  net.minecraft.util.SoundEvent
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.ChunkPos
 *  net.minecraft.world.IWorldEventListener
 *  net.minecraft.world.World
 *  net.minecraft.world.chunk.Chunk
 *  net.minecraftforge.event.world.ChunkEvent$Load
 *  net.minecraftforge.event.world.ChunkEvent$Unload
 *  net.minecraftforge.event.world.WorldEvent$Unload
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package journeymap.client.world;

import com.google.common.cache.CacheLoader;
import javax.annotation.Nullable;
import journeymap.client.data.DataCache;
import journeymap.client.forge.event.EventHandlerManager;
import journeymap.client.model.ChunkMD;
import journeymap.common.Journeymap;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.IWorldEventListener;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.event.world.ChunkEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public enum ChunkMonitor implements IWorldEventListener,
EventHandlerManager.EventHandler
{
    INSTANCE;

    private World world;

    public void reset() {
        if (this.world != null) {
            this.world.removeEventListener((IWorldEventListener)INSTANCE);
        }
        this.world = null;
    }

    public void resetRenderTimes(ChunkPos pos) {
        ChunkMD chunkMD = DataCache.INSTANCE.getChunkMD(pos);
        if (chunkMD != null) {
            chunkMD.resetRenderTimes();
        }
    }

    @SideOnly(value=Side.CLIENT)
    @SubscribeEvent
    public void onChunkLoad(ChunkEvent.Load event) {
        Chunk chunk;
        if (!event.getWorld().isRemote) {
            return;
        }
        if (this.world == null) {
            this.world = event.getWorld();
            this.world.addEventListener((IWorldEventListener)this);
            event.getWorld();
        }
        if ((chunk = event.getChunk()) != null && chunk.isLoaded()) {
            DataCache.INSTANCE.addChunkMD(new ChunkMD(chunk));
        }
    }

    @SideOnly(value=Side.CLIENT)
    @SubscribeEvent
    public void onChunkUnload(ChunkEvent.Unload event) {
    }

    @SideOnly(value=Side.CLIENT)
    @SubscribeEvent
    public void onWorldUnload(WorldEvent.Unload event) {
        try {
            World world = event.getWorld();
            if (world == world) {
                this.reset();
            }
        }
        catch (Exception e) {
            Journeymap.getLogger().error("Error handling WorldEvent.Unload", (Throwable)e);
        }
    }

    public void notifyBlockUpdate(World worldIn, BlockPos pos, IBlockState oldState, IBlockState newState, int flags) {
        this.resetRenderTimes(new ChunkPos(pos));
    }

    public void notifyLightSet(BlockPos pos) {
        this.resetRenderTimes(new ChunkPos(pos));
    }

    public void markBlockRangeForRenderUpdate(int x1, int y1, int z1, int x2, int y2, int z2) {
        int cx1 = x1 >> 4;
        int cz1 = z1 >> 4;
        int cx2 = x2 >> 4;
        int cz2 = z2 >> 4;
        if (cx1 == cx2 && cz1 == cz2) {
            this.resetRenderTimes(new ChunkPos(cx1, cz1));
        } else {
            for (int chunkXPos = cx1; chunkXPos < cx2; ++chunkXPos) {
                for (int chunkZPos = cz1; chunkZPos < cz2; ++chunkZPos) {
                    this.resetRenderTimes(new ChunkPos(chunkXPos, chunkZPos));
                }
            }
        }
    }

    public void playSoundToAllNearExcept(@Nullable EntityPlayer player, SoundEvent soundIn, SoundCategory category, double x, double y, double z, float volume, float pitch) {
    }

    public void playRecord(SoundEvent soundIn, BlockPos pos) {
    }

    public void spawnParticle(int particleID, boolean ignoreRange, double xCoord, double yCoord, double zCoord, double xSpeed, double ySpeed, double zSpeed, int ... parameters) {
    }

    public void spawnParticle(int p_190570_1_, boolean p_190570_2_, boolean p_190570_3_, double p_190570_4_, double p_190570_6_, double p_190570_8_, double p_190570_10_, double p_190570_12_, double p_190570_14_, int ... p_190570_16_) {
    }

    public void onEntityAdded(Entity entityIn) {
    }

    public void onEntityRemoved(Entity entityIn) {
    }

    public void broadcastSound(int soundID, BlockPos pos, int data) {
    }

    public void playEvent(EntityPlayer player, int type, BlockPos blockPosIn, int data) {
    }

    public void sendBlockBreakProgress(int breakerId, BlockPos pos, int progress) {
    }

    private static class TimestampLoader
    extends CacheLoader<ChunkPos, Long> {
        private TimestampLoader() {
        }

        public Long load(ChunkPos key) throws Exception {
            return System.currentTimeMillis();
        }
    }
}

