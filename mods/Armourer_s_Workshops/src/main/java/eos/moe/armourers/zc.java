/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.armourers;

import java.io.IOException;
import java.io.InputStream;

public class zc
extends InputStream {
    private static final int c = 15;
    private InputStream v;
    private long s;
    private long m;
    private byte[] j;

    private /* synthetic */ int r(byte[] a2, int a3) throws IOException {
        int n2 = a2.length - a3;
        int n3 = 0;
        int n4 = a3;
        for (int i2 = 0; n4 < a2.length && n3 != -1 && i2 < 15; ++i2) {
            zc a4;
            if ((n3 += a4.v.read(a2, a3, n2)) > 0) {
                a3 += n3;
                n2 -= n3;
            }
            n4 = a3;
        }
        return a3;
    }

    public zc(InputStream a2, long a3) {
        zc a4;
        zc zc2 = a4;
        zc zc3 = a4;
        zc3.m = 0L;
        zc3.j = new byte[1];
        zc2.v = a2;
        zc2.s = a3;
    }

    public long r() {
        zc a2;
        return a2.m;
    }

    @Override
    public int read(byte[] a2, int a3, int a4) throws IOException {
        zc a5;
        if (a5.s != -1L) {
            if (a5.m >= a5.s) {
                return -1;
            }
            if ((long)a4 > a5.s - a5.m) {
                a4 = (int)(a5.s - a5.m);
            }
        }
        if ((a2 = a5.v.read(a2, a3, a4)) > 0) {
            a5.m += (long)a2;
        }
        return a2;
    }

    @Override
    public int read() throws IOException {
        zc a2;
        zc zc2 = a2;
        if (zc2.read(zc2.j) == -1) {
            return -1;
        }
        return a2.j[0];
    }

    public int r(byte[] a2) throws IOException {
        zc a3;
        int n2 = a3.v.read(a2);
        if (n2 != a2.length && (n2 = a3.r(a2, n2)) != a2.length) {
            throw new IOException("Cannot read fully into byte buffer");
        }
        return n2;
    }

    @Override
    public int read(byte[] a2) throws IOException {
        zc a3;
        return a3.read(a2, 0, a2.length);
    }

    @Override
    public void close() throws IOException {
        zc a2;
        a2.v.close();
    }
}

