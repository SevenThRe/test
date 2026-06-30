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
            if (RegionLoader.getRegionFile(mc, coord.field_77276_a, coord.field_77275_b).exists()) {
                if (loader.func_191063_a(coord.field_77276_a, coord.field_77275_b)) {
                    Chunk chunk = loader.func_75815_a((World)mc.field_71441_e, coord.field_77276_a, coord.field_77275_b);
                    if (chunk != null) {
                        if (!chunk.func_177410_o()) {
                            chunk.func_177417_c(true);
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
        if (world != null && (provider = world.func_72863_F()) != null && (theChunk = provider.func_186026_b(chunkX, chunkZ)) != null && theChunk.func_177410_o() && !(theChunk instanceof EmptyChunk)) {
            return new ChunkMD(theChunk);
        }
        return null;
    }
}

