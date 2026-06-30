/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  axx
 *  bxr
 *  rk
 */
package net.optifine.util;

public class RenderChunkUtils {
    public static int getCountBlocks(bxr renderChunk) {
        axx[] ebss = renderChunk.getChunk().h();
        if (ebss == null) {
            return 0;
        }
        int indexEbs = renderChunk.k().q() >> 4;
        axx ebs = ebss[indexEbs];
        if (ebs == null) {
            return 0;
        }
        return ebs.getBlockRefCount();
    }

    public static double getRelativeBufferSize(bxr renderChunk) {
        int blockCount = RenderChunkUtils.getCountBlocks(renderChunk);
        double vertexCountRel = RenderChunkUtils.getRelativeBufferSize(blockCount);
        return vertexCountRel;
    }

    public static double getRelativeBufferSize(int blockCount) {
        double countRel = (double)blockCount / 4096.0;
        double weight = (countRel *= 0.995) * 2.0 - 1.0;
        weight = rk.a((double)weight, (double)-1.0, (double)1.0);
        return rk.a((double)(1.0 - weight * weight));
    }
}

