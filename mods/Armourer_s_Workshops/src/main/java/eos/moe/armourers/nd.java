/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.armourers;

import eos.moe.armourers.c;
import eos.moe.armourers.cb;
import eos.moe.armourers.kc;
import eos.moe.armourers.nb;
import eos.moe.armourers.ph;
import eos.moe.armourers.qb;
import eos.moe.armourers.tl;
import eos.moe.armourers.zc;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class nd
extends kc<tl> {
    private int t;
    private byte[] w;
    private byte[] r;
    private int l;
    private int c;
    private int v;
    private int s;
    private int m;
    private int j;

    @Override
    public void r(InputStream a2) throws IOException {
        nd a3;
        nd nd2 = a3;
        nd2.r(nd2.r(a2));
    }

    public nd(zc a2, nb a3, char[] a4) throws IOException {
        nd a5;
        nd nd2 = a5;
        nd nd3 = a5;
        nd nd4 = a5;
        nd nd5 = a5;
        super(a2, a3, a4);
        a5.w = new byte[1];
        nd5.r = new byte[16];
        nd5.j = 0;
        nd4.c = 0;
        nd4.m = 0;
        nd3.t = 0;
        nd3.l = 0;
        nd2.s = 0;
        nd2.v = 0;
    }

    public byte[] r(InputStream a2) throws IOException {
        byte[] byArray = new byte[10];
        if (eos.moe.armourers.c.r(a2, byArray) != 10) {
            throw new ph("Invalid AES Mac bytes. Could not read sufficient data");
        }
        return byArray;
    }

    private /* synthetic */ void r(byte[] a2, int a3) {
        nd a4;
        a4.s = a4.m < a4.c ? a4.m : a4.c;
        nd nd2 = a4;
        nd nd3 = a4;
        System.arraycopy(nd2.r, nd3.j, a2, a3, a4.s);
        nd3.r(nd2.s);
        nd2.y(nd2.s);
        nd2.l += a4.s;
        nd2.m -= a4.s;
        nd2.t += a4.s;
    }

    @Override
    public int read(byte[] a22, int a3, int a4) throws IOException {
        nd a5;
        a5.m = a4;
        a5.t = a3;
        a5.l = 0;
        if (a5.c != 0) {
            nd nd2 = a5;
            nd2.r(a22, nd2.t);
            if (nd2.l == a4) {
                return a5.l;
            }
        }
        if (a5.m < 16) {
            nd nd3 = a5;
            a5.v = super.read(a5.r, 0, nd3.r.length);
            a5.j = 0;
            if (a5.v == -1) {
                a5.c = 0;
                if (a5.l > 0) {
                    return a5.l;
                }
                return -1;
            }
            nd nd4 = a5;
            nd4.c = a5.v;
            nd4.r(a22, nd4.t);
            if (a5.l == a4) {
                return a5.l;
            }
        }
        nd nd5 = a5;
        int a22 = super.read(a22, nd5.t, nd5.m - a5.m % 16);
        if (a22 == -1) {
            if (a5.l > 0) {
                return a5.l;
            }
            return -1;
        }
        return a22 + a5.l;
    }

    private /* synthetic */ void r(byte[] a2) throws IOException {
        nd a3;
        if (a3.r().h() && cb.j.equals((Object)eos.moe.armourers.c.r(a3.r()))) {
            return;
        }
        byte[] byArray = ((tl)a3.r()).r();
        byte[] byArray2 = new byte[10];
        System.arraycopy(byArray, 0, byArray2, 0, 10);
        if (!Arrays.equals(a2, byArray2)) {
            throw new IOException("Reached end of data for this entry, but aes verification failed");
        }
    }

    @Override
    public tl r(nb a2, char[] a3) throws IOException {
        nd a4;
        return new tl(a2.r(), a3, a4.r(a2), a4.y());
    }

    private /* synthetic */ void y(int a2) {
        nd a3;
        nd nd2 = a3;
        nd2.c -= a2;
        if (nd2.c <= 0) {
            a3.c = 0;
        }
    }

    @Override
    public int read(byte[] a2) throws IOException {
        nd a3;
        return a3.read(a2, 0, a2.length);
    }

    private /* synthetic */ byte[] r(nb a2) throws IOException {
        nd a3;
        if (((qb)a2).r() == null) {
            throw new IOException("invalid aes extra data record");
        }
        Object object = a2 = (Object)new byte[((qb)a2).r().r().r()];
        a3.r((byte[])object);
        return object;
    }

    @Override
    public int read() throws IOException {
        nd a2;
        nd nd2 = a2;
        if (nd2.read(nd2.w) == -1) {
            return -1;
        }
        return a2.w[0];
    }

    private /* synthetic */ void r(int a2) {
        nd a3;
        nd nd2 = a3;
        nd2.j += a2;
        if (nd2.j >= 15) {
            a3.j = 15;
        }
    }

    private /* synthetic */ byte[] y() throws IOException {
        nd a2;
        byte[] byArray = new byte[2];
        a2.r(byArray);
        return byArray;
    }
}

