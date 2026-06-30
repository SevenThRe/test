/*
 * Decompiled with CFR 0.152.
 */
package optifine.xdelta;

import java.io.IOException;
import optifine.xdelta.DiffWriter;

public class DebugDiffWriter
implements DiffWriter {
    byte[] buf = new byte[256];
    int buflen = 0;

    @Override
    public void addCopy(int offset, int length) throws IOException {
        if (this.buflen > 0) {
            this.writeBuf();
        }
        System.err.println("COPY off: " + offset + ", len: " + length);
    }

    @Override
    public void addData(byte b2) throws IOException {
        if (this.buflen < 256) {
            this.buf[this.buflen++] = b2;
        } else {
            this.writeBuf();
        }
    }

    private void writeBuf() {
        System.err.print("DATA: ");
        int ix = 0;
        while (ix < this.buflen) {
            if (this.buf[ix] == 10) {
                System.err.print("\\n");
            } else {
                System.err.print(String.valueOf((char)this.buf[ix]));
            }
            ++ix;
        }
        System.err.println("");
        this.buflen = 0;
    }

    @Override
    public void flush() throws IOException {
    }

    @Override
    public void close() throws IOException {
    }
}

