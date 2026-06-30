/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.dragoncore;

import java.io.IOException;
import java.io.InputStream;

public class il
extends InputStream {
    private static final int b = 15;
    private InputStream o;
    private long y = 0L;
    private byte[] k = new byte[1];
    private long ALLATORIxDEMO;

    public il(InputStream a2, long a3) {
        il a4;
        a4.o = a2;
        a4.ALLATORIxDEMO = a3;
    }

    @Override
    public int read() throws IOException {
        il a2;
        int a3 = a2.read(a2.k);
        if (a3 == -1) {
            return -1;
        }
        return a2.k[0];
    }

    @Override
    public int read(byte[] a2) throws IOException {
        il a3;
        return a3.read(a2, 0, a2.length);
    }

    @Override
    public int read(byte[] a2, int a3, int a4) throws IOException {
        int a5;
        il a6;
        if (a6.ALLATORIxDEMO != -1L) {
            if (a6.y >= a6.ALLATORIxDEMO) {
                return -1;
            }
            if ((long)a4 > a6.ALLATORIxDEMO - a6.y) {
                a4 = (int)(a6.ALLATORIxDEMO - a6.y);
            }
        }
        if ((a5 = a6.o.read(a2, a3, a4)) > 0) {
            a6.y += (long)a5;
        }
        return a5;
    }

    public int ALLATORIxDEMO(byte[] a2) throws IOException {
        il a3;
        int a4 = a3.o.read(a2);
        if (a4 != a2.length && (a4 = a3.ALLATORIxDEMO(a2, a4)) != a2.length) {
            throw new IOException("Cannot read fully into byte buffer");
        }
        return a4;
    }

    private /* synthetic */ int ALLATORIxDEMO(byte[] a2, int a3) throws IOException {
        int a4 = a2.length - a3;
        int a5 = 0;
        for (int a6 = 0; a3 < a2.length && a5 != -1 && a6 < 15; ++a6) {
            il a7;
            if ((a5 += a7.o.read(a2, a3, a4)) <= 0) continue;
            a3 += a5;
            a4 -= a5;
        }
        return a3;
    }

    @Override
    public void close() throws IOException {
        il a2;
        a2.o.close();
    }

    public long ALLATORIxDEMO() {
        il a2;
        return a2.y;
    }
}

