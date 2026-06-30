/*
 * Decompiled with CFR 0.152.
 */
package info.journeymap.shaded.ar.com.hjg.pngj.chunks;

import info.journeymap.shaded.ar.com.hjg.pngj.ImageInfo;
import info.journeymap.shaded.ar.com.hjg.pngj.chunks.PngChunk;

public abstract class PngChunkMultiple
extends PngChunk {
    protected PngChunkMultiple(String id, ImageInfo imgInfo) {
        super(id, imgInfo);
    }

    public final boolean allowsMultiple() {
        return true;
    }
}

