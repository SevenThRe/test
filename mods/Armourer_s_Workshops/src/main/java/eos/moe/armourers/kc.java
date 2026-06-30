/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.armourers;

import eos.moe.armourers.c;
import eos.moe.armourers.cb;
import eos.moe.armourers.l;
import eos.moe.armourers.nb;
import eos.moe.armourers.ph;
import eos.moe.armourers.zc;
import java.io.IOException;
import java.io.InputStream;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public abstract class kc<T extends l>
extends InputStream {
    private byte[] c;
    private nb v;
    private zc s;
    private T m;
    private byte[] j;

    @Override
    public void close() throws IOException {
        kc a2;
        a2.s.close();
    }

    public nb r() {
        kc a2;
        return a2.v;
    }

    public void r(InputStream a2) throws IOException {
    }

    public T r() {
        kc a2;
        return a2.m;
    }

    public byte[] r() {
        kc a2;
        return a2.c;
    }

    private /* synthetic */ void r(byte[] a2, int a3) {
        kc a4;
        if (a4.c != null) {
            System.arraycopy(a2, 0, a4.c, 0, a3);
        }
    }

    @Override
    public int read() throws IOException {
        kc a2;
        kc kc2 = a2;
        if (kc2.read(kc2.j) == -1) {
            return -1;
        }
        return a2.j[0] & 0xFF;
    }

    @Override
    public int read(byte[] a2, int a3, int a4) throws IOException {
        kc a5;
        if ((a4 = eos.moe.armourers.c.r(a5.s, a2, a3, a4)) > 0) {
            kc kc2 = a5;
            kc2.r(a2, a4);
            kc2.m.r(a2, a3, a4);
        }
        return a4;
    }

    private /* synthetic */ cb r(nb a2) throws ph {
        if (a2.r() != cb.m) {
            return a2.r();
        }
        if (a2.r() == null) {
            throw new ph("AesExtraDataRecord not present in localheader for aes encrypted data");
        }
        return a2.r().r();
    }

    public int r(byte[] a2) throws IOException {
        kc a3;
        return a3.s.r(a2);
    }

    public long r() {
        kc a2;
        return a2.s.r();
    }

    @Override
    public int read(byte[] a2) throws IOException {
        kc a3;
        return a3.read(a2, 0, a2.length);
    }

    public abstract T r(nb var1, char[] var2) throws IOException, ph;

    public kc(zc a2, nb a3, char[] a4) throws IOException, ph {
        kc a5;
        nb nb2 = a3;
        kc kc2 = a5;
        kc2.j = new byte[1];
        kc2.s = a2;
        a5.m = a5.r(nb2, a4);
        a5.v = nb2;
        if (a5.r(a3) == cb.j) {
            a5.c = new byte[4096];
        }
    }
}

