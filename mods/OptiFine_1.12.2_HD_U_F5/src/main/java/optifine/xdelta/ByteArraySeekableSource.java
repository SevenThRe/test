/*
 * Decompiled with CFR 0.152.
 */
package optifine.xdelta;

import java.io.IOException;
import optifine.xdelta.SeekableSource;

public class ByteArraySeekableSource
implements SeekableSource {
    byte[] source;
    long lastPos = 0L;

    public ByteArraySeekableSource(byte[] source) {
        this.source = source;
    }

    @Override
    public void seek(long pos) throws IOException {
        this.lastPos = pos;
    }

    @Override
    public int read(byte[] b2, int off, int len) throws IOException {
        int maxLength = this.source.length - (int)this.lastPos;
        if (maxLength <= 0) {
            return -1;
        }
        if (maxLength < len) {
            len = maxLength;
        }
        System.arraycopy(this.source, (int)this.lastPos, b2, off, len);
        this.lastPos += (long)len;
        return len;
    }

    @Override
    public long length() throws IOException {
        return this.source.length;
    }

    @Override
    public void close() throws IOException {
        this.source = null;
    }
}

