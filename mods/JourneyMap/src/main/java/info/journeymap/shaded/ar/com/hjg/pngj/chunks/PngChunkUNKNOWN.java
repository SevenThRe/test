/*
 * Decompiled with CFR 0.152.
 */
package info.journeymap.shaded.ar.com.hjg.pngj.chunks;

import info.journeymap.shaded.ar.com.hjg.pngj.ImageInfo;
import info.journeymap.shaded.ar.com.hjg.pngj.chunks.ChunkRaw;
import info.journeymap.shaded.ar.com.hjg.pngj.chunks.PngChunk;
import info.journeymap.shaded.ar.com.hjg.pngj.chunks.PngChunkMultiple;

public class PngChunkUNKNOWN
extends PngChunkMultiple {
    public PngChunkUNKNOWN(String id, ImageInfo info) {
        super(id, info);
    }

    public PngChunk.ChunkOrderingConstraint getOrderingConstraint() {
        return PngChunk.ChunkOrderingConstraint.NONE;
    }

    public ChunkRaw createRawChunk() {
        return this.raw;
    }

    public void parseFromRaw(ChunkRaw c) {
    }

    public byte[] getData() {
        return this.raw.data;
    }

    public void setData(byte[] data) {
        this.raw.data = data;
    }
}

