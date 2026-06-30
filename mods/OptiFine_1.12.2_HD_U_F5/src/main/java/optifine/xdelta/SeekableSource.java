/*
 * Decompiled with CFR 0.152.
 */
package optifine.xdelta;

import java.io.IOException;

public interface SeekableSource {
    public void seek(long var1) throws IOException;

    public int read(byte[] var1, int var2, int var3) throws IOException;

    public void close() throws IOException;

    public long length() throws IOException;
}

