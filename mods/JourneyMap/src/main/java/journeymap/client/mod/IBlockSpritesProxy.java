/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.util.math.BlockPos
 */
package journeymap.client.mod;

import java.util.Collection;
import javax.annotation.Nullable;
import journeymap.client.cartography.color.ColoredSprite;
import journeymap.client.model.BlockMD;
import journeymap.client.model.ChunkMD;
import net.minecraft.util.math.BlockPos;

public interface IBlockSpritesProxy {
    @Nullable
    public Collection<ColoredSprite> getSprites(BlockMD var1, @Nullable ChunkMD var2, @Nullable BlockPos var3);
}

