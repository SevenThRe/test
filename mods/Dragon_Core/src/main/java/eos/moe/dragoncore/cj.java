/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.dragoncore;

import java.io.IOException;
import java.io.OutputStream;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class cj
extends OutputStream {
    private long y = 0L;
    private OutputStream k;
    private boolean ALLATORIxDEMO;

    public cj(OutputStream a2) {
        cj a3;
        a3.k = a2;
        a3.ALLATORIxDEMO = false;
    }

    @Override
    public void write(int a2) throws IOException {
        cj a3;
        a3.write(new byte[]{(byte)a2});
    }

    @Override
    public void write(byte[] a2) throws IOException {
        cj a3;
        a3.write(a2, 0, a2.length);
    }

    @Override
    public void write(byte[] a2, int a3, int a4) throws IOException {
        cj a5;
        if (a5.ALLATORIxDEMO) {
            throw new IllegalStateException("ZipEntryOutputStream is closed");
        }
        a5.k.write(a2, a3, a4);
        a5.y += (long)a4;
    }

    public void ALLATORIxDEMO() throws IOException {
        a.ALLATORIxDEMO = true;
    }

    public long ALLATORIxDEMO() {
        cj a2;
        return a2.y;
    }

    @Override
    public void close() throws IOException {
    }
}

