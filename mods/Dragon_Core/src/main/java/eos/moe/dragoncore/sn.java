/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.cn;
import eos.moe.dragoncore.em;
import eos.moe.dragoncore.gc;
import eos.moe.dragoncore.hc;
import eos.moe.dragoncore.il;
import eos.moe.dragoncore.ta;
import eos.moe.dragoncore.vm;
import eos.moe.dragoncore.yk;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class sn
extends vm<cn> {
    private byte[] v = new byte[1];
    private byte[] m = new byte[16];
    private int c = 0;
    private int q = 0;
    private int b = 0;
    private int o = 0;
    private int y = 0;
    private int k = 0;
    private int ALLATORIxDEMO = 0;

    public sn(il a2, hc a3, char[] a4) throws IOException {
        super(a2, a3, a4);
        sn a5;
    }

    @Override
    public cn ALLATORIxDEMO(hc a2, char[] a3) throws IOException {
        sn a4;
        return new cn(a2.ALLATORIxDEMO(), a3, a4.ALLATORIxDEMO(a2), a4.c());
    }

    @Override
    public int read() throws IOException {
        sn a2;
        int a3 = a2.read(a2.v);
        if (a3 == -1) {
            return -1;
        }
        return a2.v[0];
    }

    @Override
    public int read(byte[] a2) throws IOException {
        sn a3;
        return a3.read(a2, 0, a2.length);
    }

    @Override
    public int read(byte[] a2, int a3, int a4) throws IOException {
        int a5;
        sn a6;
        a6.b = a4;
        a6.o = a3;
        a6.y = 0;
        if (a6.q != 0) {
            a6.ALLATORIxDEMO(a2, a6.o);
            if (a6.y == a4) {
                return a6.y;
            }
        }
        if (a6.b < 16) {
            a6.ALLATORIxDEMO = super.read(a6.m, 0, a6.m.length);
            a6.c = 0;
            if (a6.ALLATORIxDEMO == -1) {
                a6.q = 0;
                if (a6.y > 0) {
                    return a6.y;
                }
                return -1;
            }
            a6.q = a6.ALLATORIxDEMO;
            a6.ALLATORIxDEMO(a2, a6.o);
            if (a6.y == a4) {
                return a6.y;
            }
        }
        if ((a5 = super.read(a2, a6.o, a6.b - a6.b % 16)) == -1) {
            if (a6.y > 0) {
                return a6.y;
            }
            return -1;
        }
        return a5 + a6.y;
    }

    private /* synthetic */ void ALLATORIxDEMO(byte[] a2, int a3) {
        sn a4;
        a4.k = a4.b < a4.q ? a4.b : a4.q;
        System.arraycopy(a4.m, a4.c, a2, a3, a4.k);
        a4.c(a4.k);
        a4.ALLATORIxDEMO(a4.k);
        a4.y += a4.k;
        a4.b -= a4.k;
        a4.o += a4.k;
    }

    @Override
    public void ALLATORIxDEMO(InputStream a2) throws IOException {
        sn a3;
        a3.ALLATORIxDEMO(a3.ALLATORIxDEMO(a2));
    }

    private /* synthetic */ void ALLATORIxDEMO(byte[] a2) throws IOException {
        sn a3;
        if (a3.ALLATORIxDEMO().f() && gc.o.equals((Object)ta.ALLATORIxDEMO(a3.ALLATORIxDEMO()))) {
            return;
        }
        byte[] a4 = ((cn)a3.ALLATORIxDEMO()).ALLATORIxDEMO();
        byte[] a5 = new byte[10];
        System.arraycopy(a4, 0, a5, 0, 10);
        if (!Arrays.equals(a2, a5)) {
            throw new IOException("Reached end of data for this entry, but aes verification failed");
        }
    }

    public byte[] ALLATORIxDEMO(InputStream a2) throws IOException {
        byte[] a3 = new byte[10];
        int a4 = ta.ALLATORIxDEMO(a2, a3);
        if (a4 != 10) {
            throw new yk("Invalid AES Mac bytes. Could not read sufficient data");
        }
        return a3;
    }

    private /* synthetic */ byte[] ALLATORIxDEMO(hc a2) throws IOException {
        sn a3;
        if (a2.ALLATORIxDEMO() == null) {
            throw new IOException("invalid aes extra data record");
        }
        em a4 = a2.ALLATORIxDEMO();
        byte[] a5 = new byte[a4.ALLATORIxDEMO().f()];
        a3.ALLATORIxDEMO(a5);
        return a5;
    }

    private /* synthetic */ byte[] c() throws IOException {
        sn a2;
        byte[] a3 = new byte[2];
        a2.ALLATORIxDEMO(a3);
        return a3;
    }

    private /* synthetic */ void c(int a2) {
        sn a3;
        a3.c += a2;
        if (a3.c >= 15) {
            a3.c = 15;
        }
    }

    private /* synthetic */ void ALLATORIxDEMO(int a2) {
        sn a3;
        a3.q -= a2;
        if (a3.q <= 0) {
            a3.q = 0;
        }
    }
}

