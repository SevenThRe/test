/*
 * Decompiled with CFR 0.152.
 */
package info.journeymap.shaded.ar.com.hjg.pngj;

import info.journeymap.shaded.ar.com.hjg.pngj.PngjException;
import info.journeymap.shaded.ar.com.hjg.pngj.PngjExceptionInternal;
import info.journeymap.shaded.ar.com.hjg.pngj.chunks.ChunkRaw;

public abstract class ChunkReader {
    public final ChunkReaderMode mode;
    private final ChunkRaw chunkRaw;
    private boolean crcCheck;
    protected int read = 0;
    private int crcn = 0;

    public ChunkReader(int clen, String id, long offsetInPng, ChunkReaderMode mode) {
        if (mode == null || id.length() != 4 || clen < 0) {
            throw new PngjExceptionInternal("Bad chunk paramenters: " + (Object)((Object)mode));
        }
        this.mode = mode;
        this.chunkRaw = new ChunkRaw(clen, id, mode == ChunkReaderMode.BUFFER);
        this.chunkRaw.setOffset(offsetInPng);
        this.crcCheck = mode != ChunkReaderMode.SKIP;
    }

    public ChunkRaw getChunkRaw() {
        return this.chunkRaw;
    }

    public final int feedBytes(byte[] buf, int off, int len) {
        int bytesForData;
        if (len == 0) {
            return 0;
        }
        if (len < 0) {
            throw new PngjException("negative length??");
        }
        if (this.read == 0 && this.crcn == 0 && this.crcCheck) {
            this.chunkRaw.updateCrc(this.chunkRaw.idbytes, 0, 4);
        }
        if ((bytesForData = this.chunkRaw.len - this.read) > len) {
            bytesForData = len;
        }
        if (bytesForData > 0 || this.crcn == 0) {
            if (this.crcCheck && this.mode != ChunkReaderMode.BUFFER && bytesForData > 0) {
                this.chunkRaw.updateCrc(buf, off, bytesForData);
            }
            if (this.mode == ChunkReaderMode.BUFFER) {
                if (this.chunkRaw.data != buf && bytesForData > 0) {
                    System.arraycopy(buf, off, this.chunkRaw.data, this.read, bytesForData);
                }
            } else if (this.mode == ChunkReaderMode.PROCESS) {
                this.processData(this.read, buf, off, bytesForData);
            }
            this.read += bytesForData;
            off += bytesForData;
            len -= bytesForData;
        }
        int crcRead = 0;
        if (this.read == this.chunkRaw.len) {
            crcRead = 4 - this.crcn;
            if (crcRead > len) {
                crcRead = len;
            }
            if (crcRead > 0) {
                if (buf != this.chunkRaw.crcval) {
                    System.arraycopy(buf, off, this.chunkRaw.crcval, this.crcn, crcRead);
                }
                this.crcn += crcRead;
                if (this.crcn == 4) {
                    if (this.crcCheck) {
                        if (this.mode == ChunkReaderMode.BUFFER) {
                            this.chunkRaw.updateCrc(this.chunkRaw.data, 0, this.chunkRaw.len);
                        }
                        this.chunkRaw.checkCrc();
                    }
                    this.chunkDone();
                }
            }
        }
        return bytesForData + crcRead;
    }

    public final boolean isDone() {
        return this.crcn == 4;
    }

    public void setCrcCheck(boolean crcCheck) {
        if (this.read != 0 && crcCheck && !this.crcCheck) {
            throw new PngjException("too late!");
        }
        this.crcCheck = crcCheck;
    }

    protected abstract void processData(int var1, byte[] var2, int var3, int var4);

    protected abstract void chunkDone();

    public int hashCode() {
        int prime = 31;
        int result = 1;
        result = 31 * result + (this.chunkRaw == null ? 0 : this.chunkRaw.hashCode());
        return result;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        ChunkReader other = (ChunkReader)obj;
        return !(this.chunkRaw == null ? other.chunkRaw != null : !this.chunkRaw.equals(other.chunkRaw));
    }

    public String toString() {
        return this.chunkRaw.toString();
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    public static enum ChunkReaderMode {
        BUFFER,
        PROCESS,
        SKIP;

    }
}

