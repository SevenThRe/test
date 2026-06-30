/*
 * Decompiled with CFR 0.152.
 */
package javazoom.jl.decoder;

import java.io.IOException;
import java.io.InputStream;
import javazoom.jl.decoder.Source;

public class InputStreamSource
implements Source {
    private final InputStream in;

    public InputStreamSource(InputStream inputStream) {
        if (inputStream == null) {
            throw new NullPointerException("in");
        }
        this.in = inputStream;
    }

    public int read(byte[] byArray, int n, int n2) throws IOException {
        int n3 = this.in.read(byArray, n, n2);
        return n3;
    }

    public boolean willReadBlock() {
        return true;
    }

    public boolean isSeekable() {
        return false;
    }

    public long tell() {
        return -1L;
    }

    public long seek(long l) {
        return -1L;
    }

    public long length() {
        return -1L;
    }
}

