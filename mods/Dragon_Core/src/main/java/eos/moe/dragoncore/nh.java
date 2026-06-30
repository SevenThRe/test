/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 */
package eos.moe.dragoncore;

import com.google.common.collect.ImmutableList;
import eos.moe.dragoncore.aw;
import eos.moe.dragoncore.ay;
import eos.moe.dragoncore.az;
import eos.moe.dragoncore.b;
import eos.moe.dragoncore.bu;
import eos.moe.dragoncore.c;
import eos.moe.dragoncore.ca;
import eos.moe.dragoncore.du;
import eos.moe.dragoncore.dv;
import eos.moe.dragoncore.dy;
import eos.moe.dragoncore.ep;
import eos.moe.dragoncore.es;
import eos.moe.dragoncore.ez;
import eos.moe.dragoncore.iz;
import eos.moe.dragoncore.kv;
import eos.moe.dragoncore.lq;
import eos.moe.dragoncore.lt;
import eos.moe.dragoncore.ly;
import eos.moe.dragoncore.mr;
import eos.moe.dragoncore.nq;
import eos.moe.dragoncore.oo;
import eos.moe.dragoncore.op;
import eos.moe.dragoncore.oq;
import eos.moe.dragoncore.os;
import eos.moe.dragoncore.pf;
import eos.moe.dragoncore.pp;
import eos.moe.dragoncore.pv;
import eos.moe.dragoncore.qg;
import eos.moe.dragoncore.qp;
import eos.moe.dragoncore.qv;
import eos.moe.dragoncore.tq;
import eos.moe.dragoncore.v;
import eos.moe.dragoncore.vt;
import eos.moe.dragoncore.vx;
import eos.moe.dragoncore.w;
import eos.moe.dragoncore.wk;
import eos.moe.dragoncore.xq;
import eos.moe.dragoncore.ys;
import eos.moe.dragoncore.zp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class nh {
    private static final Map<ez, c> v = new HashMap<ez, c>();
    private static final Map<ez, w> m = new HashMap<ez, w>();
    private kv c;
    private final List<vx> q;
    private qv b;
    private List<b> o;
    public boolean y;
    public v k;
    private final iz ALLATORIxDEMO;

    public String f() {
        nh a2;
        return a2.c.ALLATORIxDEMO();
    }

    public kv ALLATORIxDEMO() {
        nh a2;
        return a2.c;
    }

    public List<vx> s() {
        nh a2;
        return a2.q;
    }

    public qv ALLATORIxDEMO() {
        nh a2;
        return a2.b;
    }

    public nh(qv a2, kv a3) {
        nh a4;
        a4.q = new ArrayList<vx>();
        a4.ALLATORIxDEMO = new iz("");
        a4.c = a3;
        a4.b = a2;
    }

    public nh() {
        nh a2;
        a2.q = new ArrayList<vx>();
        a2.ALLATORIxDEMO = new iz("");
    }

    public nh(kv a2) {
        nh a3;
        a3.q = new ArrayList<vx>();
        a3.ALLATORIxDEMO = new iz("");
        a3.c = a2;
    }

    public v ALLATORIxDEMO() {
        nh a2;
        if (a2.b == null) {
            throw new NullPointerException("MoLangRuntime is null");
        }
        if (a2.k != null) {
            if (a2.k == wk.k) {
                return a2.b.ALLATORIxDEMO(a2.x());
            }
            return a2.k;
        }
        try {
            a2.x();
        }
        catch (Exception a3) {
            a2.o = new ArrayList<b>();
            a3.printStackTrace();
            return pf.ALLATORIxDEMO(0);
        }
        if (a2.o != null) {
            a2.k = a2.o.size() == 0 ? pf.ALLATORIxDEMO(0) : (a2.o.size() == 1 ? ((a = a2.o.get(0)) instanceof iz || a instanceof ys ? a.ALLATORIxDEMO(null, null) : wk.k) : wk.k);
        }
        if (a2.k == wk.k) {
            return a2.b.ALLATORIxDEMO(a2.x());
        }
        return a2.k;
    }

    public double c() {
        nh a2;
        return a2.ALLATORIxDEMO().ALLATORIxDEMO();
    }

    public boolean c() {
        nh a2;
        return a2.ALLATORIxDEMO().ALLATORIxDEMO() != 0.0;
    }

    public int c() {
        nh a2;
        return (int)a2.ALLATORIxDEMO().ALLATORIxDEMO();
    }

    public String c() {
        nh a2;
        return a2.ALLATORIxDEMO().c();
    }

    public List<String> w() {
        nh a2;
        v a3 = a2.ALLATORIxDEMO();
        if (a3 instanceof qg) {
            qg a4 = (qg)a3;
            return a4.ALLATORIxDEMO().stream().map(v::c).collect(Collectors.toList());
        }
        return ImmutableList.of((Object)a2.ALLATORIxDEMO().c());
    }

    public List<v> z() {
        nh a2;
        v a3 = a2.ALLATORIxDEMO();
        if (a3 instanceof qg) {
            qg a4 = (qg)a3;
            return new ArrayList<v>(a4.ALLATORIxDEMO());
        }
        return ImmutableList.of((Object)a2.ALLATORIxDEMO());
    }

    public void c() {
    }

    public double ALLATORIxDEMO() {
        nh a2;
        return a2.c();
    }

    public boolean ALLATORIxDEMO() {
        nh a2;
        return a2.c();
    }

    public int ALLATORIxDEMO() {
        nh a2;
        return a2.c();
    }

    public String ALLATORIxDEMO() {
        nh a2;
        return a2.c();
    }

    public List<String> k() {
        nh a2;
        return a2.w();
    }

    public List<v> d() {
        nh a2;
        return a2.z();
    }

    public List<b> x() {
        nh a2;
        if (a2.o != null) {
            return a2.o;
        }
        return a2.f();
    }

    private synchronized /* synthetic */ List<b> f() {
        b a2;
        nh a3;
        if (a3.o != null) {
            return a3.o;
        }
        ArrayList<b> a4 = new ArrayList<b>();
        vx a5 = a3.ALLATORIxDEMO();
        if (a5 == null || a5.ALLATORIxDEMO() == ez.y) {
            a4.add(new iz(""));
            a3.o = a4;
            return a4;
        }
        if (a3.c.ALLATORIxDEMO().startsWith("string|")) {
            a4.add(new iz(a3.c.ALLATORIxDEMO().substring(7)));
            a3.o = a4;
            return a4;
        }
        if (a3.c.ALLATORIxDEMO().contains("component") || a3.c.ALLATORIxDEMO().contains("\u5f53\u524d\u7ec4\u4ef6")) {
            a3.y = true;
        }
        while ((a2 = a3.ALLATORIxDEMO()) != null) {
            a4.add(a2);
        }
        a3.o = a4;
        return a4;
    }

    public b ALLATORIxDEMO() {
        nh a2;
        return a2.ALLATORIxDEMO(ep.l);
    }

    public b c(ep a2) {
        nh a3;
        b a4 = a3.ALLATORIxDEMO(a2);
        return a4 == null ? a3.ALLATORIxDEMO : a4;
    }

    public b ALLATORIxDEMO(ep a2) {
        nh a3;
        vx a4 = a3.c();
        if (a4.ALLATORIxDEMO().equals((Object)ez.y)) {
            return null;
        }
        if (a4.ALLATORIxDEMO().equals((Object)ez.w)) {
            return a3.ALLATORIxDEMO(a2);
        }
        c a5 = v.get((Object)a4.ALLATORIxDEMO());
        if (a5 == null) {
            ca.l.z("\u8bed\u6cd5\u9519\u8bef->" + a3.c.ALLATORIxDEMO());
            throw new RuntimeException("Cannot parse " + a4.ALLATORIxDEMO().name() + " expression -> \u65e0\u6cd5\u5904\u7406\u7684\u8868\u8fbe\u5f0f" + a3.c.ALLATORIxDEMO());
        }
        b a6 = a5.ALLATORIxDEMO(a3, a4);
        a3.ALLATORIxDEMO(a6, a4);
        if (a6 instanceof nq) {
            return a3.c(a6, a2);
        }
        return a3.ALLATORIxDEMO(a6, a2);
    }

    private /* synthetic */ b c(b a2, ep a3) {
        nh a4;
        vx a5 = a4.ALLATORIxDEMO();
        if (a5 != null && a5.ALLATORIxDEMO() == ez.p) {
            a4.c();
            a5 = a4.ALLATORIxDEMO();
        }
        if (a5 != null && a5.ALLATORIxDEMO() == ez.ka) {
            b a6 = new op().ALLATORIxDEMO(a4, a5, a2);
            a4.ALLATORIxDEMO(a6, a5);
            return a6;
        }
        return a2;
    }

    public b ALLATORIxDEMO(b a2, ep a3) {
        nh a4;
        while (a3.ordinal() < a4.ALLATORIxDEMO().ordinal()) {
            vx a5 = a4.c();
            a2 = m.get((Object)a5.ALLATORIxDEMO()).ALLATORIxDEMO(a4, a5, a2);
            a4.ALLATORIxDEMO(a2, a5);
        }
        return a2;
    }

    public void ALLATORIxDEMO(b a2, vx a3) {
        a2.ALLATORIxDEMO().put("position", a3.ALLATORIxDEMO());
    }

    public ep ALLATORIxDEMO() {
        w a2;
        nh a3;
        vx a4 = a3.ALLATORIxDEMO();
        if (a4 != null && (a2 = m.get((Object)a4.ALLATORIxDEMO())) != null) {
            return a2.ALLATORIxDEMO();
        }
        return ep.l;
    }

    public List<b> c() {
        nh a2;
        ArrayList<b> a3 = new ArrayList<b>();
        if (a2.ALLATORIxDEMO(ez.qa) && !a2.ALLATORIxDEMO(ez.wa)) {
            do {
                a3.add(a2.ALLATORIxDEMO());
            } while (a2.ALLATORIxDEMO(ez.xa));
            a2.ALLATORIxDEMO(ez.wa);
        }
        return a3;
    }

    public String c(String a2) {
        CharSequence[] a3 = a2.split("\\.");
        switch (a3[0]) {
            case "q": {
                a3[0] = "query";
                break;
            }
            case "v": {
                a3[0] = "variable";
                break;
            }
            case "t": {
                a3[0] = "temp";
                break;
            }
            case "c": {
                a3[0] = "context";
                break;
            }
            case "u": {
                a3[0] = "ui";
            }
            case "g": {
                a3[0] = "global";
            }
        }
        return String.join((CharSequence)".", a3);
    }

    public String ALLATORIxDEMO(String a2) {
        return a2.split("\\.")[0];
    }

    public vx c() {
        nh a2;
        return a2.ALLATORIxDEMO((ez)null);
    }

    public vx ALLATORIxDEMO(ez a2) {
        nh a3;
        a3.c.c();
        vx a4 = a3.ALLATORIxDEMO();
        if (a2 != null && !a4.ALLATORIxDEMO().equals((Object)a2)) {
            ca.l.z("\u8bed\u6cd5\u9519\u8bef->" + a3.c.ALLATORIxDEMO());
            throw new RuntimeException("Expected token " + a2.name() + " and " + a4.ALLATORIxDEMO().name() + " given -> \u65e0\u6cd5\u5904\u7406\u7684\u8868\u8fbe\u5f0f" + a3.c.ALLATORIxDEMO());
        }
        return a3.q.remove(0);
    }

    public boolean ALLATORIxDEMO(ez a2) {
        nh a3;
        return a3.ALLATORIxDEMO(a2, true);
    }

    public boolean ALLATORIxDEMO(ez a2, boolean a3) {
        nh a4;
        vx a5 = a4.ALLATORIxDEMO();
        if (a5 == null || !a5.ALLATORIxDEMO().equals((Object)a2)) {
            return false;
        }
        if (a3) {
            a4.c();
        }
        return true;
    }

    public vx ALLATORIxDEMO() {
        nh a2;
        return a2.ALLATORIxDEMO(0);
    }

    public vx ALLATORIxDEMO(int a2) {
        nh a3;
        while (a2 >= a3.q.size()) {
            a3.q.add(a3.c.ALLATORIxDEMO());
        }
        return a3.q.get(a2);
    }

    public void ALLATORIxDEMO(qv a2) {
        a.b = a2;
    }

    public void ALLATORIxDEMO() {
        nh a2;
        a2.c.ALLATORIxDEMO();
        a2.q.clear();
    }

    public List<b> ALLATORIxDEMO() {
        nh a2;
        return a2.o;
    }

    public void ALLATORIxDEMO(List<b> a2) {
        a.o = a2;
    }

    static {
        v.put(ez.o, new oo());
        v.put(ez.q, new os());
        v.put(ez.c, new xq());
        v.put(ez.b, new dv());
        v.put(ez.v, new tq());
        v.put(ez.m, new tq());
        v.put(ez.i, new pp());
        v.put(ez.l, new oq());
        v.put(ez.z, new ay());
        v.put(ez.t, new zp());
        v.put(ez.g, new ly());
        v.put(ez.r, new vt());
        v.put(ez.n, new lq());
        v.put(ez.a, new lq());
        v.put(ez.s, new es());
        v.put(ez.x, new lt());
        v.put(ez.qa, new aw());
        v.put(ez.ka, new du());
        v.put(ez.ma, new mr());
        v.put(ez.aa, new qp());
        v.put(ez.j, new dy());
        m.put(ez.p, new op());
        m.put(ez.fa, new az());
        m.put(ez.ba, new pv(ep.c));
        m.put(ez.aa, new pv(ep.q));
        m.put(ez.ma, new pv(ep.q));
        m.put(ez.f, new pv(ep.b));
        m.put(ez.h, new pv(ep.b));
        m.put(ez.d, new pv(ep.b));
        m.put(ez.ra, new pv(ep.m));
        m.put(ez.ha, new pv(ep.m));
        m.put(ez.pa, new pv(ep.m));
        m.put(ez.la, new pv(ep.m));
        m.put(ez.da, new pv(ep.m));
        m.put(ez.ca, new pv(ep.m));
        m.put(ez.ia, new pv(ep.x));
        m.put(ez.sa, new pv(ep.v));
        m.put(ez.ga, new pv(ep.r));
        m.put(ez.za, new pv(ep.y));
        m.put(ez.ta, new bu());
    }
}

