/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.util.math.ChunkPos
 *  net.minecraft.world.World
 *  net.minecraft.world.chunk.Chunk
 *  net.minecraft.world.chunk.EmptyChunk
 *  net.minecraft.world.chunk.IChunkProvider
 *  net.minecraft.world.chunk.storage.AnvilChunkLoader
 *  org.apache.logging.log4j.Logger
 */
package journeymap.client.io.nbt;

import java.io.IOException;
import journeymap.client.io.nbt.RegionLoader;
import journeymap.client.model.ChunkMD;
import journeymap.common.Journeymap;
import net.minecraft.client.Minecraft;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.EmptyChunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.chunk.storage.AnvilChunkLoader;
import org.apache.logging.log4j.Logger;

public class ChunkLoader {
    private static Logger logger = Journeymap.getLogger();

    public static ChunkMD getChunkMD(AnvilChunkLoader loader, Minecraft mc, ChunkPos coord, boolean forceRetain) {
        try {
            if (RegionLoader.getRegionFile(mc, coord.x, coord.z).exists()) {
                if (loader.isChunkGeneratedAt(coord.x, coord.z)) {
                    Chunk chunk = loader.loadChunk((World)mc.world, coord.x, coord.z);
                    if (chunk != null) {
                        if (!chunk.isLoaded()) {
                            chunk.markLoaded(true);
                        }
                        return new ChunkMD(chunk, forceRetain);
                    }
                    logger.warn("AnvilChunkLoader returned null for chunk: " + coord);
                }
            } else {
                logger.warn("Region doesn't exist for chunk: " + coord);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ChunkMD getChunkMdFromMemory(World world, int chunkX, int chunkZ) {
        Chunk theChunk;
        IChunkProvider provider;
        if (world != null && (provider = world.getChunkProvider()) != null && (theChunk = provider.getLoadedChunk(chunkX, chunkZ)) != null && theChunk.isLoaded() && !(theChunk instanceof EmptyChunk)) {
            return new ChunkMD(theChunk);
        }
        return null;
    }
}

