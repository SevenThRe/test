/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.armourers;

import eos.moe.armourers.c;
import eos.moe.armourers.ph;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class ra {
    private byte[] s;
    private byte[] m;
    private byte[] j;

    public long r(RandomAccessFile a2) throws IOException {
        ra a3;
        ra ra2 = a3;
        a2.readFully(ra2.j);
        return ra2.r(ra2.j, 0);
    }

    private /* synthetic */ void r(byte[] a2) {
        int n2;
        int n3 = n2 = 0;
        while (n3 < a2.length) {
            a2[n2++] = 0;
            n3 = n2;
        }
    }

    public long r(byte[] a2, int a3) {
        ra a4;
        if (a2.length - a3 < 8) {
            ra ra2 = a4;
            ra2.r(ra2.j);
        }
        System.arraycopy(a2, a3, a4.j, 0, a2.length < 8 ? a2.length - a3 : 8);
        long l2 = 0L;
        l2 = 0L | (long)(a4.j[7] & 0xFF);
        l2 <<= 8;
        l2 |= (long)(a4.j[6] & 0xFF);
        l2 <<= 8;
        l2 |= (long)(a4.j[5] & 0xFF);
        l2 <<= 8;
        l2 |= (long)(a4.j[4] & 0xFF);
        l2 <<= 8;
        l2 |= (long)(a4.j[3] & 0xFF);
        l2 <<= 8;
        l2 |= (long)(a4.j[2] & 0xFF);
        l2 <<= 8;
        l2 |= (long)(a4.j[1] & 0xFF);
        l2 <<= 8;
        return l2 |= (long)(a4.j[0] & 0xFF);
    }

    public void y(OutputStream a2, int a3) throws IOException {
        ra a4;
        ra ra2 = a4;
        ra2.r(ra2.s, 0, a3);
        a2.write(ra2.s);
    }

    public int y(byte[] a2, int a3) {
        return a2[a3] & 0xFF | (a2[1 + a3] & 0xFF) << 8 | (a2[2 + a3] & 0xFF | (a2[3 + a3] & 0xFF) << 8) << 16;
    }

    public int r(byte[] a2) {
        ra a3;
        return a3.y(a2, 0);
    }

    public long r(RandomAccessFile a2, int a3) throws IOException {
        ra a4;
        ra ra2 = a4;
        ra ra3 = a4;
        ra3.r(ra3.j);
        a2.readFully(ra2.j, 0, a3);
        return ra2.r(ra2.j, 0);
    }

    public int y(RandomAccessFile a2) throws IOException {
        ra a3;
        ra ra2 = a3;
        a2.readFully(ra2.m);
        return ra2.r(ra2.m);
    }

    public void r(byte[] a2, int a3, long a4) {
        int n2 = a3;
        int n3 = a3;
        a2[a3 + 7] = (byte)(a4 >>> 56);
        a2[a3 + 6] = (byte)(a4 >>> 48);
        a2[n3 + 5] = (byte)(a4 >>> 40);
        a2[n3 + 4] = (byte)(a4 >>> 32);
        a2[a3 + 3] = (byte)(a4 >>> 24);
        a2[n2 + 2] = (byte)(a4 >>> 16);
        a2[n2 + 1] = (byte)(a4 >>> 8);
        a2[a3] = (byte)(a4 & 0xFFL);
    }

    public long r(InputStream a2) throws IOException {
        ra a3;
        ra ra2 = a3;
        ra2.r(a2, a3.j, ra2.j.length);
        ra ra3 = a3;
        return ra3.r(ra3.j, 0);
    }

    public int r(RandomAccessFile a2) throws IOException {
        ra a3;
        ra ra2 = a3;
        a2.readFully(ra2.s);
        return ra2.r(ra2.s, 0);
    }

    public int r(byte[] a2, int a3) {
        return a2[a3] & 0xFF | (a2[1 + a3] & 0xFF) << 8;
    }

    private /* synthetic */ void r(InputStream a2, byte[] a3, int a4) throws IOException {
        if (c.r(a2, a3, 0, a4) != a4) {
            throw new ph("Could not fill buffer");
        }
    }

    public long r(InputStream a2, int a3) throws IOException {
        ra a4;
        ra ra2 = a4;
        ra ra3 = a4;
        ra ra4 = a4;
        ra4.r(ra4.j);
        ra2.r(a2, ra3.j, a3);
        return ra2.r(ra3.j, 0);
    }

    public void r(OutputStream a2, int a3) throws IOException {
        ra a4;
        ra ra2 = a4;
        ra2.y(ra2.m, 0, a3);
        a2.write(ra2.m);
    }

    public void y(byte[] a2, int a3, int a4) {
        int n2 = a3;
        a2[a3 + 3] = (byte)(a4 >>> 24);
        a2[n2 + 2] = (byte)(a4 >>> 16);
        a2[n2 + 1] = (byte)(a4 >>> 8);
        a2[a3] = (byte)(a4 & 0xFF);
    }

    public ra() {
        ra a2;
        ra ra2 = a2;
        a2.s = new byte[2];
        ra2.m = new byte[4];
        ra2.j = new byte[8];
    }

    public int y(InputStream a2) throws IOException {
        ra a3;
        ra ra2 = a3;
        ra2.r(a2, a3.s, ra2.s.length);
        ra ra3 = a3;
        return ra3.r(ra3.s, 0);
    }

    public int r(InputStream a2) throws IOException {
        ra a3;
        ra ra2 = a3;
        ra ra3 = a3;
        ra2.r(a2, ra3.m, 4);
        return ra2.r(ra3.m);
    }

    public void r(byte[] a2, int a3, int a4) {
        a2[a3 + 1] = (byte)(a4 >>> 8);
        a2[a3] = (byte)(a4 & 0xFF);
    }

    public void r(OutputStream a2, long a3) throws IOException {
        ra a4;
        ra ra2 = a4;
        ra2.r(ra2.j, 0, a3);
        a2.write(ra2.j);
    }
}

