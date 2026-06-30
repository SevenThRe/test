/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.ta;
import eos.moe.dragoncore.yk;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class ua {
    private byte[] y = new byte[2];
    private byte[] k = new byte[4];
    private byte[] ALLATORIxDEMO = new byte[8];

    public ua() {
        ua a2;
    }

    public long ALLATORIxDEMO(RandomAccessFile a2) throws IOException {
        ua a3;
        a2.readFully(a3.ALLATORIxDEMO);
        return a3.ALLATORIxDEMO(a3.ALLATORIxDEMO, 0);
    }

    public long ALLATORIxDEMO(RandomAccessFile a2, int a3) throws IOException {
        ua a4;
        a4.ALLATORIxDEMO(a4.ALLATORIxDEMO);
        a2.readFully(a4.ALLATORIxDEMO, 0, a3);
        return a4.ALLATORIxDEMO(a4.ALLATORIxDEMO, 0);
    }

    public long ALLATORIxDEMO(InputStream a2) throws IOException {
        ua a3;
        a3.ALLATORIxDEMO(a2, a3.ALLATORIxDEMO, a3.ALLATORIxDEMO.length);
        return a3.ALLATORIxDEMO(a3.ALLATORIxDEMO, 0);
    }

    public long ALLATORIxDEMO(InputStream a2, int a3) throws IOException {
        ua a4;
        a4.ALLATORIxDEMO(a4.ALLATORIxDEMO);
        a4.ALLATORIxDEMO(a2, a4.ALLATORIxDEMO, a3);
        return a4.ALLATORIxDEMO(a4.ALLATORIxDEMO, 0);
    }

    public long ALLATORIxDEMO(byte[] a2, int a3) {
        ua a4;
        if (a2.length - a3 < 8) {
            a4.ALLATORIxDEMO(a4.ALLATORIxDEMO);
        }
        System.arraycopy(a2, a3, a4.ALLATORIxDEMO, 0, a2.length < 8 ? a2.length - a3 : 8);
        long a5 = 0L;
        a5 |= (long)(a4.ALLATORIxDEMO[7] & 0xFF);
        a5 <<= 8;
        a5 |= (long)(a4.ALLATORIxDEMO[6] & 0xFF);
        a5 <<= 8;
        a5 |= (long)(a4.ALLATORIxDEMO[5] & 0xFF);
        a5 <<= 8;
        a5 |= (long)(a4.ALLATORIxDEMO[4] & 0xFF);
        a5 <<= 8;
        a5 |= (long)(a4.ALLATORIxDEMO[3] & 0xFF);
        a5 <<= 8;
        a5 |= (long)(a4.ALLATORIxDEMO[2] & 0xFF);
        a5 <<= 8;
        a5 |= (long)(a4.ALLATORIxDEMO[1] & 0xFF);
        a5 <<= 8;
        return a5 |= (long)(a4.ALLATORIxDEMO[0] & 0xFF);
    }

    public int c(RandomAccessFile a2) throws IOException {
        ua a3;
        a2.readFully(a3.k);
        return a3.ALLATORIxDEMO(a3.k);
    }

    public int c(InputStream a2) throws IOException {
        ua a3;
        a3.ALLATORIxDEMO(a2, a3.k, 4);
        return a3.ALLATORIxDEMO(a3.k);
    }

    public int ALLATORIxDEMO(byte[] a2) {
        ua a3;
        return a3.c(a2, 0);
    }

    public int c(byte[] a2, int a3) {
        return a2[a3] & 0xFF | (a2[1 + a3] & 0xFF) << 8 | (a2[2 + a3] & 0xFF | (a2[3 + a3] & 0xFF) << 8) << 16;
    }

    public int ALLATORIxDEMO(RandomAccessFile a2) throws IOException {
        ua a3;
        a2.readFully(a3.y);
        return a3.ALLATORIxDEMO(a3.y, 0);
    }

    public int ALLATORIxDEMO(InputStream a2) throws IOException {
        ua a3;
        a3.ALLATORIxDEMO(a2, a3.y, a3.y.length);
        return a3.ALLATORIxDEMO(a3.y, 0);
    }

    public int ALLATORIxDEMO(byte[] a2, int a3) {
        return a2[a3] & 0xFF | (a2[1 + a3] & 0xFF) << 8;
    }

    public void c(OutputStream a2, int a3) throws IOException {
        ua a4;
        a4.c(a4.y, 0, a3);
        a2.write(a4.y);
    }

    public void c(byte[] a2, int a3, int a4) {
        a2[a3 + 1] = (byte)(a4 >>> 8);
        a2[a3] = (byte)(a4 & 0xFF);
    }

    public void ALLATORIxDEMO(OutputStream a2, int a3) throws IOException {
        ua a4;
        a4.ALLATORIxDEMO(a4.k, 0, a3);
        a2.write(a4.k);
    }

    public void ALLATORIxDEMO(byte[] a2, int a3, int a4) {
        a2[a3 + 3] = (byte)(a4 >>> 24);
        a2[a3 + 2] = (byte)(a4 >>> 16);
        a2[a3 + 1] = (byte)(a4 >>> 8);
        a2[a3] = (byte)(a4 & 0xFF);
    }

    public void ALLATORIxDEMO(OutputStream a2, long a3) throws IOException {
        ua a4;
        a4.ALLATORIxDEMO(a4.ALLATORIxDEMO, 0, a3);
        a2.write(a4.ALLATORIxDEMO);
    }

    public void ALLATORIxDEMO(byte[] a2, int a3, long a4) {
        a2[a3 + 7] = (byte)(a4 >>> 56);
        a2[a3 + 6] = (byte)(a4 >>> 48);
        a2[a3 + 5] = (byte)(a4 >>> 40);
        a2[a3 + 4] = (byte)(a4 >>> 32);
        a2[a3 + 3] = (byte)(a4 >>> 24);
        a2[a3 + 2] = (byte)(a4 >>> 16);
        a2[a3 + 1] = (byte)(a4 >>> 8);
        a2[a3] = (byte)(a4 & 0xFFL);
    }

    private /* synthetic */ void ALLATORIxDEMO(InputStream a2, byte[] a3, int a4) throws IOException {
        int a5 = ta.ALLATORIxDEMO(a2, a3, 0, a4);
        if (a5 != a4) {
            throw new yk("Could not fill buffer");
        }
    }

    private /* synthetic */ void ALLATORIxDEMO(byte[] a2) {
        for (int a3 = 0; a3 < a2.length; ++a3) {
            a2[a3] = 0;
        }
    }
}

