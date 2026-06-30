/*
 * Decompiled with CFR 0.152.
 */
package optifine.xdelta;

import java.io.IOException;

public interface DiffWriter {
    public void addCopy(int var1, int var2) throws IOException;

    public void addData(byte var1) throws IOException;

    public void flush() throws IOException;

    public void close() throws IOException;
}

