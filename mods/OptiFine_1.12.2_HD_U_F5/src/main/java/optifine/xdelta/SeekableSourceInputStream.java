/*
 * Decompiled with CFR 0.152.
 */
package optifine.xdelta;

import java.io.IOException;
import java.io.InputStream;
import optifine.xdelta.SeekableSource;

public class SeekableSourceInputStream
extends InputStream {
    SeekableSource ss;

    public SeekableSourceInputStream(SeekableSource ss) {
        this.ss = ss;
    }

    @Override
    public int read() throws IOException {
        byte[] b2 = new byte[1];
        this.ss.read(b2, 0, 1);
        return b2[0];
    }

    @Override
    public int read(byte[] b2, int off, int len) throws IOException {
        return this.ss.read(b2, off, len);
    }

    @Override
    public void close() throws IOException {
        this.ss.close();
    }
}

