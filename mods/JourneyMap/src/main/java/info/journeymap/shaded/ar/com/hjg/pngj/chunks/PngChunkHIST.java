/*
 * Decompiled with CFR 0.152.
 */
package info.journeymap.shaded.ar.com.hjg.pngj.chunks;

import info.journeymap.shaded.ar.com.hjg.pngj.ImageInfo;
import info.journeymap.shaded.ar.com.hjg.pngj.PngHelperInternal;
import info.journeymap.shaded.ar.com.hjg.pngj.PngjException;
import info.journeymap.shaded.ar.com.hjg.pngj.chunks.ChunkRaw;
import info.journeymap.shaded.ar.com.hjg.pngj.chunks.PngChunk;
import info.journeymap.shaded.ar.com.hjg.pngj.chunks.PngChunkSingle;

public class PngChunkHIST
extends PngChunkSingle {
    public static final String ID = "hIST";
    private int[] hist = new int[0];

    public PngChunkHIST(ImageInfo info) {
        super(ID, info);
    }

    public PngChunk.ChunkOrderingConstraint getOrderingConstraint() {
        return PngChunk.ChunkOrderingConstraint.AFTER_PLTE_BEFORE_IDAT;
    }

    public void parseFromRaw(ChunkRaw c) {
        if (!this.imgInfo.indexed) {
            throw new PngjException("only indexed images accept a HIST chunk");
        }
        int nentries = c.data.length / 2;
        this.hist = new int[nentries];
        for (int i = 0; i < this.hist.length; ++i) {
            this.hist[i] = PngHelperInternal.readInt2fromBytes(c.data, i * 2);
        }
    }

    public ChunkRaw createRawChunk() {
        if (!this.imgInfo.indexed) {
            throw new PngjException("only indexed images accept a HIST chunk");
        }
        ChunkRaw c = null;
        c = this.createEmptyChunk(this.hist.length * 2, true);
        for (int i = 0; i < this.hist.length; ++i) {
            PngHelperInternal.writeInt2tobytes(this.hist[i], c.data, i * 2);
        }
        return c;
    }

    public int[] getHist() {
        return this.hist;
    }

    public void setHist(int[] hist) {
        this.hist = hist;
    }
}

