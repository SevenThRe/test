/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ComparisonChain
 *  com.google.common.collect.Ordering
 *  com.google.gson.annotations.Since
 */
package journeymap.client.cartography.color;

import com.google.common.collect.ComparisonChain;
import com.google.common.collect.Ordering;
import com.google.gson.annotations.Since;
import java.util.Comparator;
import journeymap.client.cartography.color.RGB;
import journeymap.client.model.BlockMD;
import journeymap.common.Journeymap;

class BlockStateColor
implements Comparable<BlockStateColor> {
    @Since(value=5.45)
    String block;
    @Since(value=5.45)
    String state;
    @Since(value=5.2)
    String name;
    @Since(value=5.2)
    String color;
    @Since(value=5.2)
    Float alpha;

    BlockStateColor(BlockMD blockMD) {
        this(blockMD, blockMD.getTextureColor());
    }

    BlockStateColor(BlockMD blockMD, Integer color) {
        if (Journeymap.getClient().getCoreProperties().verboseColorPalette.get().booleanValue()) {
            this.block = blockMD.getBlockId();
            this.state = blockMD.getBlockStateId();
            this.name = blockMD.getName();
        }
        this.color = RGB.toHexString(color);
        if (blockMD.getAlpha() != 1.0f) {
            this.alpha = Float.valueOf(blockMD.getAlpha());
        }
    }

    BlockStateColor(String color, Float alpha) {
        this.color = color;
        this.alpha = Float.valueOf(alpha == null ? 1.0f : alpha.floatValue());
    }

    @Override
    public int compareTo(BlockStateColor that) {
        Ordering ordering = Ordering.natural().nullsLast();
        return ComparisonChain.start().compare((Object)this.name, (Object)that.name, (Comparator)ordering).compare((Object)this.block, (Object)that.block, (Comparator)ordering).compare((Object)this.state, (Object)that.state, (Comparator)ordering).compare((Object)this.color, (Object)that.color, (Comparator)ordering).compare((Object)this.alpha, (Object)that.alpha, (Comparator)ordering).result();
    }
}

