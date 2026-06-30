/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.armourers;

import eos.moe.armourers.cb;
import eos.moe.armourers.cc;
import eos.moe.armourers.ec;
import eos.moe.armourers.ga;
import eos.moe.armourers.gc;
import eos.moe.armourers.hc;
import eos.moe.armourers.jc;
import eos.moe.armourers.kb;
import eos.moe.armourers.lb;
import eos.moe.armourers.mc;
import eos.moe.armourers.nb;
import eos.moe.armourers.nc;
import eos.moe.armourers.pb;
import eos.moe.armourers.ph;
import eos.moe.armourers.ra;
import eos.moe.armourers.uc;
import eos.moe.armourers.ue;
import eos.moe.armourers.vl;
import eos.moe.armourers.wm;
import eos.moe.armourers.xc;
import eos.moe.armourers.yb;
import eos.moe.armourers.zb;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.zip.CRC32;

public class vc
extends OutputStream {
    private gc e;
    private ue b;
    private vl g;
    private pb z;
    private Charset t;
    private ra w;
    private kb r;
    private char[] l;
    private boolean c;
    private nb v;
    private jc s;
    private CRC32 m;
    private long j;

    public vc(OutputStream a2) throws IOException {
        a3(a2, null, ga.d);
        vc a3;
    }

    public vc(OutputStream a2, char[] a3) throws IOException {
        a4(a2, a3, ga.d);
        vc a4;
    }

    private /* synthetic */ kb r(kb a2, gc a3) {
        if (a2 == null) {
            a2 = new kb();
        }
        if (a3.r()) {
            kb kb2 = a2;
            kb2.y(true);
            kb2.y(a3.z());
        }
        return a2;
    }

    private /* synthetic */ mc r(ec a2, zb a3) throws IOException {
        vc a4;
        if (!a3.s()) {
            return new nc(a2, a3, null);
        }
        if (a4.l == null || a4.l.length == 0) {
            throw new ph("password not set");
        }
        if (a3.r() == yb.j) {
            return new hc(a2, a3, a4.l);
        }
        if (a3.r() == yb.m) {
            return new xc(a2, a3, a4.l);
        }
        throw new ph("Invalid encryption method");
    }

    public pb r() throws IOException {
        vc a2;
        vc vc2 = a2;
        vc2.s.r();
        long l2 = vc2.s.r();
        vc2.z.r(l2);
        vc2.v.r(l2);
        vc2.z.h(a2.j);
        vc2.v.h(a2.j);
        if (vc2.r(vc2.z)) {
            vc vc3 = a2;
            a2.z.y(vc3.m.getValue());
            vc3.v.y(a2.m.getValue());
        }
        vc vc4 = a2;
        vc4.r.y().add(a2.v);
        a2.r.r().r().add(a2.z);
        if (vc4.v.h()) {
            vc vc5 = a2;
            vc5.g.r(vc5.v, (OutputStream)a2.e);
        }
        vc vc6 = a2;
        vc6.y();
        return vc6.z;
    }

    private /* synthetic */ boolean r(pb a2) {
        boolean bl;
        boolean bl2 = bl = a2.r() && a2.r().equals((Object)yb.j);
        if (!bl) {
            return true;
        }
        return a2.r().r().equals((Object)lb.v);
    }

    private /* synthetic */ jc r(mc a2, zb a3) {
        if (a3.r() == cb.j) {
            return new cc(a2, a3.r());
        }
        return new uc(a2);
    }

    @Override
    public void write(byte[] a2, int a3, int a4) throws IOException {
        vc a5;
        vc vc2 = a5;
        vc2.z();
        vc2.m.update(a2, a3, a4);
        vc2.s.write(a2, a3, a4);
        vc2.j += (long)a4;
    }

    public vc(OutputStream a2, char[] a3, Charset a4, kb a5) throws IOException {
        vc a6;
        vc vc2 = a6;
        vc vc3 = a6;
        a6.b = new ue();
        vc3.g = new vl();
        a6.m = new CRC32();
        vc2.w = new ra();
        vc2.j = 0L;
        if (a4 == null) {
            a4 = ga.d;
        }
        a6.e = new gc(a2);
        a6.l = a3;
        a6.t = a4;
        a6.r = a6.r(a5, a6.e);
        a6.c = false;
        a6.r();
    }

    @Override
    public void write(byte[] a2) throws IOException {
        vc a3;
        a3.write(a2, 0, a2.length);
    }

    public void r(String a2) throws IOException {
        vc a3;
        vc vc2 = a3;
        vc2.z();
        vc2.r.r().r(a2);
    }

    @Override
    public void close() throws IOException {
        vc a2;
        vc vc2 = a2;
        a2.r.r().r(a2.e.h());
        vc vc3 = a2;
        vc2.g.r(vc3.r, vc3.e, a2.t);
        vc2.e.close();
        vc2.c = true;
    }

    public void z(zb a2) throws IOException {
        vc a3;
        vc vc2 = a3;
        zb zb2 = a2;
        a3.r(zb2);
        vc2.y(zb2);
        vc2.s = vc2.r(a2);
    }

    public vc(OutputStream a2, Charset a3) throws IOException {
        a4(a2, null, a3);
        vc a4;
    }

    private /* synthetic */ void y(zb a2) throws IOException {
        vc a3;
        vc vc2 = a3;
        vc vc3 = a3;
        a3.z = vc2.b.r(a2, a3.e.r(), a3.e.r(), vc3.t, vc3.w);
        vc2.z.x(a3.e.y());
        vc2.v = vc2.b.r(a3.z);
        vc vc4 = a3;
        vc vc5 = a3;
        vc2.g.r(vc4.r, vc4.v, vc5.e, vc5.t);
    }

    public vc(OutputStream a2, char[] a3, Charset a4) throws IOException {
        a5(a2, a3, a4, new kb());
        vc a5;
    }

    private /* synthetic */ void z() throws IOException {
        vc a2;
        if (a2.c) {
            throw new IOException("Stream is closed");
        }
    }

    private /* synthetic */ void y() throws IOException {
        vc a2;
        vc vc2 = a2;
        vc2.j = 0L;
        vc2.m.reset();
        vc2.s.close();
    }

    private /* synthetic */ void r(zb a2) {
        vc a3;
        if (a2.r() == cb.s && a2.r() < 0L && !a3.r(a2.r()) && a2.y()) {
            throw new IllegalArgumentException("uncompressed size should be set for zip entries of compression type store");
        }
    }

    private /* synthetic */ boolean r(String a2) {
        return a2.endsWith("/") || a2.endsWith("\\");
    }

    private /* synthetic */ void r() throws IOException {
        vc a2;
        if (!a2.e.r()) {
            return;
        }
        vc vc2 = a2;
        vc2.w.r((OutputStream)vc2.e, (int)wm.l.r());
    }

    private /* synthetic */ jc r(zb a2) throws IOException {
        vc a3;
        ec ec2 = new ec(a3.e);
        vc vc2 = a3;
        return vc2.r(vc2.r(ec2, a2), a2);
    }

    @Override
    public void write(int a2) throws IOException {
        vc a3;
        byte[] byArray = new byte[1];
        byArray[0] = (byte)a2;
        a3.write(byArray);
    }
}

