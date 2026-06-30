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

public class PngChunkSRGB
extends PngChunkSingle {
    public static final String ID = "sRGB";
    public static final int RENDER_INTENT_Perceptual = 0;
    public static final int RENDER_INTENT_Relative_colorimetric = 1;
    public static final int RENDER_INTENT_Saturation = 2;
    public static final int RENDER_INTENT_Absolute_colorimetric = 3;
    private int intent;

    public PngChunkSRGB(ImageInfo info) {
        super(ID, info);
    }

    public PngChunk.ChunkOrderingConstraint getOrderingConstraint() {
        return PngChunk.ChunkOrderingConstraint.BEFORE_PLTE_AND_IDAT;
    }

    public void parseFromRaw(ChunkRaw c) {
        if (c.len != 1) {
            throw new PngjException("bad chunk length " + c);
        }
        this.intent = PngHelperInternal.readInt1fromByte(c.data, 0);
    }

    public ChunkRaw createRawChunk() {
        ChunkRaw c = null;
        c = this.createEmptyChunk(1, true);
        c.data[0] = (byte)this.intent;
        return c;
    }

    public int getIntent() {
        return this.intent;
    }

    public void setIntent(int intent) {
        this.intent = intent;
    }
}

