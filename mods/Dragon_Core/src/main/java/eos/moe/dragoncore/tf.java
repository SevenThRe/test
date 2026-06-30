/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.ec;
import eos.moe.dragoncore.ei;
import eos.moe.dragoncore.gc;
import eos.moe.dragoncore.hc;
import eos.moe.dragoncore.il;
import eos.moe.dragoncore.jm;
import eos.moe.dragoncore.la;
import eos.moe.dragoncore.mf;
import eos.moe.dragoncore.mi;
import eos.moe.dragoncore.oc;
import eos.moe.dragoncore.oe;
import eos.moe.dragoncore.rn;
import eos.moe.dragoncore.sn;
import eos.moe.dragoncore.ta;
import eos.moe.dragoncore.tc;
import eos.moe.dragoncore.vm;
import eos.moe.dragoncore.xc;
import eos.moe.dragoncore.xm;
import eos.moe.dragoncore.yk;
import eos.moe.dragoncore.zc;
import eos.moe.dragoncore.zk;
import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.nio.charset.Charset;
import java.util.List;
import java.util.zip.CRC32;
import java.util.zip.DataFormatException;

public class tf
extends InputStream {
    private PushbackInputStream v;
    private mf m;
    private mi c = new mi();
    private char[] q;
    private hc b;
    private CRC32 o = new CRC32();
    private byte[] y;
    private boolean k = false;
    private Charset ALLATORIxDEMO;

    public tf(InputStream a2) {
        a3(a2, null, la.k);
        tf a3;
    }

    public tf(InputStream a2, Charset a3) {
        a4(a2, null, a3);
        tf a4;
    }

    public tf(InputStream a2, char[] a3) {
        a4(a2, a3, la.k);
        tf a4;
    }

    public tf(InputStream a2, char[] a3, Charset a4) {
        tf a5;
        if (a4 == null) {
            a4 = la.k;
        }
        a5.v = new PushbackInputStream(a2, 4096);
        a5.q = a3;
        a5.ALLATORIxDEMO = a4;
    }

    public hc ALLATORIxDEMO() throws IOException {
        tf a2;
        return a2.ALLATORIxDEMO((ec)null);
    }

    public hc ALLATORIxDEMO(ec a2) throws IOException {
        tf a3;
        if (a3.b != null) {
            a3.ALLATORIxDEMO();
        }
        a3.b = a3.c.ALLATORIxDEMO((InputStream)a3.v, a3.ALLATORIxDEMO);
        if (a3.b == null) {
            return null;
        }
        a3.ALLATORIxDEMO(a3.b);
        a3.o.reset();
        if (a2 != null) {
            a3.b.f(a2.f());
            a3.b.c(a2.c());
            a3.b.ALLATORIxDEMO(a2.ALLATORIxDEMO());
            a3.k = true;
        } else {
            a3.k = false;
        }
        a3.m = a3.ALLATORIxDEMO(a3.b);
        return a3.b;
    }

    @Override
    public int read() throws IOException {
        tf a2;
        byte[] a3 = new byte[1];
        int a4 = a2.read(a3);
        if (a4 == -1) {
            return -1;
        }
        return a3[0] & 0xFF;
    }

    @Override
    public int read(byte[] a2) throws IOException {
        tf a3;
        return a3.read(a2, 0, a2.length);
    }

    @Override
    public int read(byte[] a2, int a3, int a4) throws IOException {
        tf a5;
        if (a4 < 0) {
            throw new IllegalArgumentException("Negative read length");
        }
        if (a4 == 0) {
            return 0;
        }
        if (a5.b == null) {
            return -1;
        }
        try {
            int a6 = a5.m.read(a2, a3, a4);
            if (a6 == -1) {
                a5.d();
            } else {
                a5.o.update(a2, a3, a6);
            }
            return a6;
        }
        catch (IOException a7) {
            if (a7.getCause() != null && a7.getCause() instanceof DataFormatException && a5.ALLATORIxDEMO(a5.b)) {
                throw new yk(a7.getMessage(), a7.getCause(), zk.c);
            }
            throw a7;
        }
    }

    @Override
    public void close() throws IOException {
        tf a2;
        if (a2.m != null) {
            a2.m.close();
        }
    }

    @Override
    public int available() throws IOException {
        tf a2;
        return a2.v.available();
    }

    private /* synthetic */ void d() throws IOException {
        tf a2;
        a2.m.ALLATORIxDEMO(a2.v);
        a2.m.ALLATORIxDEMO((InputStream)a2.v);
        a2.x();
        a2.f();
        a2.c();
    }

    private /* synthetic */ mf ALLATORIxDEMO(hc a2) throws IOException {
        tf a3;
        il a4 = new il(a3.v, a3.ALLATORIxDEMO(a2));
        vm a5 = a3.ALLATORIxDEMO(a4, a2);
        return a3.ALLATORIxDEMO(a5, a2);
    }

    private /* synthetic */ vm ALLATORIxDEMO(il a2, hc a3) throws IOException {
        tf a4;
        if (!a3.x()) {
            return new rn(a2, a3, a4.q);
        }
        if (a3.ALLATORIxDEMO() == zc.k) {
            return new sn(a2, a3, a4.q);
        }
        return new jm(a2, a3, a4.q);
    }

    private /* synthetic */ mf ALLATORIxDEMO(vm a2, hc a3) {
        gc a4 = ta.ALLATORIxDEMO(a3);
        if (a4 == gc.o) {
            return new xm(a2);
        }
        return new oe(a2);
    }

    private /* synthetic */ void x() throws IOException {
        tf a2;
        if (!a2.b.f() || a2.k) {
            return;
        }
        tc a3 = a2.c.ALLATORIxDEMO((InputStream)a2.v, a2.ALLATORIxDEMO(a2.b.ALLATORIxDEMO()));
        a2.b.c(a3.c());
        a2.b.ALLATORIxDEMO(a3.ALLATORIxDEMO());
        a2.b.f(a3.f());
    }

    private /* synthetic */ void ALLATORIxDEMO(hc a2) throws IOException {
        tf a3;
        if (!a3.ALLATORIxDEMO(a2.ALLATORIxDEMO()) && a2.ALLATORIxDEMO() == gc.b && a2.ALLATORIxDEMO() < 0L) {
            throw new IOException("Invalid local file header for: " + a2.ALLATORIxDEMO() + ". Uncompressed size has to be set for entry of compression type store which is not a directory");
        }
    }

    private /* synthetic */ boolean ALLATORIxDEMO(List<xc> a2) {
        if (a2 == null) {
            return false;
        }
        for (xc a3 : a2) {
            if (a3.ALLATORIxDEMO() != ei.o.ALLATORIxDEMO()) continue;
            return true;
        }
        return false;
    }

    private /* synthetic */ void f() throws IOException {
        tf a2;
        if (a2.b.ALLATORIxDEMO() == zc.k && a2.b.ALLATORIxDEMO().ALLATORIxDEMO().equals((Object)oc.y)) {
            return;
        }
        if (a2.b.f() != a2.o.getValue()) {
            zk a3 = zk.b;
            if (a2.ALLATORIxDEMO(a2.b)) {
                a3 = zk.c;
            }
            throw new yk("Reached end of entry, but crc verification failed for " + a2.b.ALLATORIxDEMO(), a3);
        }
    }

    private /* synthetic */ void c() {
        tf a2;
        a2.b = null;
        a2.o.reset();
    }

    private /* synthetic */ boolean ALLATORIxDEMO(String a2) {
        return a2.endsWith("/") || a2.endsWith("\\");
    }

    private /* synthetic */ long ALLATORIxDEMO(hc a2) {
        tf a3;
        if (ta.ALLATORIxDEMO(a2).equals((Object)gc.b)) {
            return a2.ALLATORIxDEMO();
        }
        if (a2.f() && !a3.k) {
            return -1L;
        }
        return a2.c() - (long)a3.ALLATORIxDEMO(a2);
    }

    private /* synthetic */ int ALLATORIxDEMO(hc a2) {
        if (!a2.x()) {
            return 0;
        }
        if (a2.ALLATORIxDEMO().equals((Object)zc.k)) {
            return 12 + a2.ALLATORIxDEMO().ALLATORIxDEMO().f();
        }
        if (a2.ALLATORIxDEMO().equals((Object)zc.o)) {
            return 12;
        }
        return 0;
    }

    private /* synthetic */ void ALLATORIxDEMO() throws IOException {
        tf a2;
        if (a2.b.ALLATORIxDEMO() || a2.b.c() == 0L) {
            return;
        }
        if (a2.y == null) {
            a2.y = new byte[512];
        }
        while (a2.read(a2.y) != -1) {
        }
    }

    private /* synthetic */ boolean ALLATORIxDEMO(hc a2) {
        return a2.x() && zc.o.equals((Object)a2.ALLATORIxDEMO());
    }
}

