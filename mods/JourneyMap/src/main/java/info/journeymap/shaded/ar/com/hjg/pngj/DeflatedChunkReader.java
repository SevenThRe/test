/*
 * Decompiled with CFR 0.152.
 */
package info.journeymap.shaded.ar.com.hjg.pngj;

import info.journeymap.shaded.ar.com.hjg.pngj.ChunkReader;
import info.journeymap.shaded.ar.com.hjg.pngj.DeflatedChunksSet;

public class DeflatedChunkReader
extends ChunkReader {
    protected final DeflatedChunksSet deflatedChunksSet;
    protected boolean alsoBuffer = false;

    public DeflatedChunkReader(int clen, String chunkid, boolean checkCrc, long offsetInPng, DeflatedChunksSet iDatSet) {
        super(clen, chunkid, offsetInPng, ChunkReader.ChunkReaderMode.PROCESS);
        this.deflatedChunksSet = iDatSet;
        iDatSet.appendNewChunk(this);
    }

    protected void processData(int offsetInchunk, byte[] buf, int off, int len) {
        if (len > 0) {
            this.deflatedChunksSet.processBytes(buf, off, len);
            if (this.alsoBuffer) {
                System.arraycopy(buf, off, this.getChunkRaw().data, this.read, len);
            }
        }
    }

    protected void chunkDone() {
    }

    public void setAlsoBuffer() {
        if (this.read > 0) {
            throw new RuntimeException("too late");
        }
        this.alsoBuffer = true;
        this.getChunkRaw().allocData();
    }
}

