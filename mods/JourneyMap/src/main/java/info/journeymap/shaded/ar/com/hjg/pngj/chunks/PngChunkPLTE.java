/*
 * Decompiled with CFR 0.152.
 */
package info.journeymap.shaded.ar.com.hjg.pngj.chunks;

import info.journeymap.shaded.ar.com.hjg.pngj.ImageInfo;
import info.journeymap.shaded.ar.com.hjg.pngj.PngjException;
import info.journeymap.shaded.ar.com.hjg.pngj.chunks.ChunkRaw;
import info.journeymap.shaded.ar.com.hjg.pngj.chunks.PngChunk;
import info.journeymap.shaded.ar.com.hjg.pngj.chunks.PngChunkSingle;

public class PngChunkPLTE
extends PngChunkSingle {
    public static final String ID = "PLTE";
    private int nentries = 0;
    private int[] entries;

    public PngChunkPLTE(ImageInfo info) {
        super(ID, info);
    }

    public PngChunk.ChunkOrderingConstraint getOrderingConstraint() {
        return PngChunk.ChunkOrderingConstraint.NA;
    }

    public ChunkRaw createRawChunk() {
        int len = 3 * this.nentries;
        int[] rgb = new int[3];
        ChunkRaw c = this.createEmptyChunk(len, true);
        int i = 0;
        for (int n = 0; n < this.nentries; ++n) {
            this.getEntryRgb(n, rgb);
            c.data[i++] = (byte)rgb[0];
            c.data[i++] = (byte)rgb[1];
            c.data[i++] = (byte)rgb[2];
        }
        return c;
    }

    public void parseFromRaw(ChunkRaw chunk) {
        this.setNentries(chunk.len / 3);
        int i = 0;
        for (int n = 0; n < this.nentries; ++n) {
            this.setEntry(n, chunk.data[i++] & 0xFF, chunk.data[i++] & 0xFF, chunk.data[i++] & 0xFF);
        }
    }

    public void setNentries(int n) {
        this.nentries = n;
        if (this.nentries < 1 || this.nentries > 256) {
            throw new PngjException("invalid pallette - nentries=" + this.nentries);
        }
        if (this.entries == null || this.entries.length != this.nentries) {
            this.entries = new int[this.nentries];
        }
    }

    public int getNentries() {
        return this.nentries;
    }

    public void setEntry(int n, int r, int g, int b) {
        this.entries[n] = r << 16 | g << 8 | b;
    }

    public int getEntry(int n) {
        return this.entries[n];
    }

    public void getEntryRgb(int n, int[] rgb) {
        this.getEntryRgb(n, rgb, 0);
    }

    public void getEntryRgb(int n, int[] rgb, int offset) {
        int v = this.entries[n];
        rgb[offset + 0] = (v & 0xFF0000) >> 16;
        rgb[offset + 1] = (v & 0xFF00) >> 8;
        rgb[offset + 2] = v & 0xFF;
    }

    public int minBitDepth() {
        if (this.nentries <= 2) {
            return 1;
        }
        if (this.nentries <= 4) {
            return 2;
        }
        if (this.nentries <= 16) {
            return 4;
        }
        return 8;
    }
}

