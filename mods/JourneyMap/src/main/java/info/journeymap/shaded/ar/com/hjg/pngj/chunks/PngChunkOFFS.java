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

public class PngChunkOFFS
extends PngChunkSingle {
    public static final String ID = "oFFs";
    private long posX;
    private long posY;
    private int units;

    public PngChunkOFFS(ImageInfo info) {
        super(ID, info);
    }

    public PngChunk.ChunkOrderingConstraint getOrderingConstraint() {
        return PngChunk.ChunkOrderingConstraint.BEFORE_IDAT;
    }

    public ChunkRaw createRawChunk() {
        ChunkRaw c = this.createEmptyChunk(9, true);
        PngHelperInternal.writeInt4tobytes((int)this.posX, c.data, 0);
        PngHelperInternal.writeInt4tobytes((int)this.posY, c.data, 4);
        c.data[8] = (byte)this.units;
        return c;
    }

    public void parseFromRaw(ChunkRaw chunk) {
        if (chunk.len != 9) {
            throw new PngjException("bad chunk length " + chunk);
        }
        this.posX = PngHelperInternal.readInt4fromBytes(chunk.data, 0);
        if (this.posX < 0L) {
            this.posX += 0x100000000L;
        }
        this.posY = PngHelperInternal.readInt4fromBytes(chunk.data, 4);
        if (this.posY < 0L) {
            this.posY += 0x100000000L;
        }
        this.units = PngHelperInternal.readInt1fromByte(chunk.data, 8);
    }

    public int getUnits() {
        return this.units;
    }

    public void setUnits(int units) {
        this.units = units;
    }

    public long getPosX() {
        return this.posX;
    }

    public void setPosX(long posX) {
        this.posX = posX;
    }

    public long getPosY() {
        return this.posY;
    }

    public void setPosY(long posY) {
        this.posY = posY;
    }
}

