/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.util.math.BlockPos
 */
package journeymap.client.mod;

import javax.annotation.Nullable;
import journeymap.client.model.BlockMD;
import journeymap.client.model.ChunkMD;
import net.minecraft.util.math.BlockPos;

public interface IBlockColorProxy {
    @Nullable
    public int deriveBlockColor(BlockMD var1, @Nullable ChunkMD var2, @Nullable BlockPos var3);

    public int getBlockColor(ChunkMD var1, BlockMD var2, BlockPos var3);
}

