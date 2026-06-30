/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.cg;
import eos.moe.dragoncore.cj;
import eos.moe.dragoncore.dc;
import eos.moe.dragoncore.ec;
import eos.moe.dragoncore.ei;
import eos.moe.dragoncore.gc;
import eos.moe.dragoncore.hc;
import eos.moe.dragoncore.kf;
import eos.moe.dragoncore.la;
import eos.moe.dragoncore.lc;
import eos.moe.dragoncore.mg;
import eos.moe.dragoncore.oc;
import eos.moe.dragoncore.pd;
import eos.moe.dragoncore.rk;
import eos.moe.dragoncore.ti;
import eos.moe.dragoncore.ua;
import eos.moe.dragoncore.uh;
import eos.moe.dragoncore.uk;
import eos.moe.dragoncore.ul;
import eos.moe.dragoncore.xe;
import eos.moe.dragoncore.yk;
import eos.moe.dragoncore.zc;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.zip.CRC32;

public class if
extends OutputStream {
    private ti g;
    private char[] t;
    private dc r;
    private cg x;
    private ec v;
    private hc m;
    private ul c = new ul();
    private mg q = new mg();
    private CRC32 b = new CRC32();
    private ua o = new ua();
    private long y = 0L;
    private Charset k;
    private boolean ALLATORIxDEMO;

    public if(OutputStream a2) throws IOException {
        a3(a2, null, la.k);
        if a3;
    }

    public if(OutputStream a2, Charset a3) throws IOException {
        a4(a2, null, a3);
        if a4;
    }

    public if(OutputStream a2, char[] a3) throws IOException {
        a4(a2, a3, la.k);
        if a4;
    }

    public if(OutputStream a2, char[] a3, Charset a4) throws IOException {
        a5(a2, a3, a4, new dc());
        if a5;
    }

    public if(OutputStream a2, char[] a3, Charset a4, dc a5) throws IOException {
        if a6;
        if (a4 == null) {
            a4 = la.k;
        }
        a6.g = new ti(a2);
        a6.t = a3;
        a6.k = a4;
        a6.r = a6.ALLATORIxDEMO(a5, a6.g);
        a6.ALLATORIxDEMO = false;
        a6.ALLATORIxDEMO();
    }

    public void f(lc a2) throws IOException {
        if a3;
        a3.ALLATORIxDEMO(a2);
        a3.c(a2);
        a3.x = a3.ALLATORIxDEMO(a2);
    }

    @Override
    public void write(int a2) throws IOException {
        if a3;
        a3.write(new byte[]{(byte)a2});
    }

    @Override
    public void write(byte[] a2) throws IOException {
        if a3;
        a3.write(a2, 0, a2.length);
    }

    @Override
    public void write(byte[] a2, int a3, int a4) throws IOException {
        if a5;
        a5.f();
        a5.b.update(a2, a3, a4);
        a5.x.write(a2, a3, a4);
        a5.y += (long)a4;
    }

    public ec ALLATORIxDEMO() throws IOException {
        if a2;
        a2.x.ALLATORIxDEMO();
        long a3 = a2.x.ALLATORIxDEMO();
        a2.v.c(a3);
        a2.m.c(a3);
        a2.v.ALLATORIxDEMO(a2.y);
        a2.m.ALLATORIxDEMO(a2.y);
        if (a2.ALLATORIxDEMO(a2.v)) {
            a2.v.f(a2.b.getValue());
            a2.m.f(a2.b.getValue());
        }
        a2.r.c().add(a2.m);
        a2.r.ALLATORIxDEMO().ALLATORIxDEMO().add(a2.v);
        if (a2.m.f()) {
            a2.q.ALLATORIxDEMO(a2.m, (OutputStream)a2.g);
        }
        a2.c();
        return a2.v;
    }

    @Override
    public void close() throws IOException {
        if a2;
        a2.r.ALLATORIxDEMO().c(a2.g.c());
        a2.q.c(a2.r, a2.g, a2.k);
        a2.g.close();
        a2.ALLATORIxDEMO = true;
    }

    public void ALLATORIxDEMO(String a2) throws IOException {
        if a3;
        a3.f();
        a3.r.ALLATORIxDEMO().ALLATORIxDEMO(a2);
    }

    private /* synthetic */ void f() throws IOException {
        if a2;
        if (a2.ALLATORIxDEMO) {
            throw new IOException("Stream is closed");
        }
    }

    private /* synthetic */ dc ALLATORIxDEMO(dc a2, ti a3) {
        if (a2 == null) {
            a2 = new dc();
        }
        if (a3.ALLATORIxDEMO()) {
            a2.f(true);
            a2.ALLATORIxDEMO(a3.f());
        }
        return a2;
    }

    private /* synthetic */ void c(lc a2) throws IOException {
        if a3;
        a3.v = a3.c.ALLATORIxDEMO(a2, a3.g.ALLATORIxDEMO(), a3.g.ALLATORIxDEMO(), a3.k, a3.o);
        a3.v.d(a3.g.x());
        a3.m = a3.c.ALLATORIxDEMO(a3.v);
        a3.q.ALLATORIxDEMO(a3.r, a3.m, a3.g, a3.k);
    }

    private /* synthetic */ void c() throws IOException {
        if a2;
        a2.y = 0L;
        a2.b.reset();
        a2.x.close();
    }

    private /* synthetic */ void ALLATORIxDEMO() throws IOException {
        if a2;
        if (!a2.g.ALLATORIxDEMO()) {
            return;
        }
        a2.o.ALLATORIxDEMO((OutputStream)a2.g, (int)ei.c.ALLATORIxDEMO());
    }

    private /* synthetic */ cg ALLATORIxDEMO(lc a2) throws IOException {
        if a3;
        cj a4 = new cj(a3.g);
        rk a5 = a3.ALLATORIxDEMO(a4, a2);
        return a3.ALLATORIxDEMO(a5, a2);
    }

    private /* synthetic */ rk ALLATORIxDEMO(cj a2, lc a3) throws IOException {
        if a4;
        if (!a3.z()) {
            return new pd(a2, a3, null);
        }
        if (a4.t == null || a4.t.length == 0) {
            throw new yk("password not set");
        }
        if (a3.ALLATORIxDEMO() == zc.k) {
            return new uk(a2, a3, a4.t);
        }
        if (a3.ALLATORIxDEMO() == zc.o) {
            return new uh(a2, a3, a4.t);
        }
        throw new yk("Invalid encryption method");
    }

    private /* synthetic */ cg ALLATORIxDEMO(rk a2, lc a3) {
        if (a3.ALLATORIxDEMO() == gc.o) {
            return new xe(a2, a3.ALLATORIxDEMO());
        }
        return new kf(a2);
    }

    private /* synthetic */ void ALLATORIxDEMO(lc a2) {
        if a3;
        if (a2.ALLATORIxDEMO() == gc.b && a2.ALLATORIxDEMO() < 0L && !a3.ALLATORIxDEMO(a2.f()) && a2.f()) {
            throw new IllegalArgumentException("uncompressed size should be set for zip entries of compression type store");
        }
    }

    private /* synthetic */ boolean ALLATORIxDEMO(ec a2) {
        boolean a3;
        boolean bl2 = a3 = a2.x() && a2.ALLATORIxDEMO().equals((Object)zc.k);
        if (!a3) {
            return true;
        }
        return a2.ALLATORIxDEMO().ALLATORIxDEMO().equals((Object)oc.o);
    }

    private /* synthetic */ boolean ALLATORIxDEMO(String a2) {
        return a2.endsWith("/") || a2.endsWith("\\");
    }
}

