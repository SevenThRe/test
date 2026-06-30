/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.lwjgl.util.vector.Matrix4f
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.ev;
import eos.moe.dragoncore.hq;
import eos.moe.dragoncore.iq;
import eos.moe.dragoncore.mz;
import eos.moe.dragoncore.o;
import eos.moe.dragoncore.pz;
import eos.moe.dragoncore.wp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.lwjgl.util.vector.Matrix4f;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class vr
implements o {
    public vr s;
    public String g;
    public int t;
    public vr r;
    public wp x;
    private Boolean v;
    public Matrix4f m;
    public Matrix4f c;
    public Matrix4f q;
    public Matrix4f b;
    public Matrix4f o;
    public ArrayList<vr> y;
    public HashMap<ev, Float> k;
    public HashMap<String, HashMap<Integer, Matrix4f>> ALLATORIxDEMO;

    public vr(String a2, int a3, vr a4, wp a5) {
        vr a6;
        a6.s = null;
        a6.q = new Matrix4f();
        a6.b = new Matrix4f();
        a6.o = new Matrix4f();
        a6.y = new ArrayList();
        a6.k = new HashMap();
        a6.ALLATORIxDEMO = new HashMap();
        a6.g = a2;
        a6.t = a3;
        a6.r = a4;
        a6.x = a5;
    }

    public vr(vr a2, vr a3, wp a4) {
        vr a5;
        a5.s = null;
        a5.q = new Matrix4f();
        a5.b = new Matrix4f();
        a5.o = new Matrix4f();
        a5.y = new ArrayList();
        a5.k = new HashMap();
        a5.ALLATORIxDEMO = new HashMap();
        a5.g = a2.g;
        a5.t = a2.t;
        a5.x = a4;
        a5.r = a3;
        for (Map.Entry<ev, Float> a6 : a2.k.entrySet()) {
            a5.k.put(a4.l.get(a6.getKey().c), a6.getValue());
        }
        a5.ALLATORIxDEMO = new HashMap<String, HashMap<Integer, Matrix4f>>(a2.ALLATORIxDEMO);
        a5.c = a2.c;
        a5.m = a2.m;
        a2.s = a5;
    }

    public void ALLATORIxDEMO(vr a2, ArrayList<vr> a3) {
        for (int a4 = 0; a4 < a2.y.size(); ++a4) {
            vr a5;
            vr a6 = a2.y.get(a4);
            a5.y.add(a3.get(a6.t));
            a3.get((int)a6.t).r = a5;
        }
    }

    public boolean ALLATORIxDEMO() {
        vr a2;
        return a2.v == null ? (a2.v = Boolean.valueOf(a2.r == null && a2.y.isEmpty())) : a2.v;
    }

    public void c(Matrix4f a2) {
        a.m = a2;
    }

    public void ALLATORIxDEMO(vr a2) {
        vr a3;
        a3.y.add(a2);
    }

    public void ALLATORIxDEMO(ev a2, float a3) {
        vr a4;
        if (a4.g.equals("blender_implicit")) {
            throw new UnsupportedOperationException("NO.");
        }
        a4.k.put(a2, Float.valueOf(a3));
    }

    private /* synthetic */ void ALLATORIxDEMO(Matrix4f a2) {
        vr a3;
        a3.m = Matrix4f.mul((Matrix4f)a2, (Matrix4f)a3.m, null);
        if (hq.g) {
            iq.ALLATORIxDEMO(a3.g + ' ' + a3.m);
        }
        a3.d();
    }

    public void d() {
        vr a2;
        for (vr a3 : a2.y) {
            a3.ALLATORIxDEMO(a2.m);
        }
    }

    public void x() {
        vr a2;
        a2.c = Matrix4f.invert((Matrix4f)a2.m, null);
    }

    public void f() {
        vr a2;
        a2.q.setIdentity();
    }

    public void ALLATORIxDEMO(mz a2, Matrix4f a3) {
        vr a4;
        HashMap<Integer, Matrix4f> a5 = a4.ALLATORIxDEMO.containsKey(a2.k.y) ? a4.ALLATORIxDEMO.get(a2.k.y) : new HashMap<Integer, Matrix4f>();
        a5.put(a2.ALLATORIxDEMO, a3);
        a4.ALLATORIxDEMO.put(a2.k.y, a5);
    }

    public void c() {
        Matrix4f a2;
        Matrix4f a3;
        Object a4;
        vr a5;
        if (a5.x.j.hasAnimations() && a5.x.r != null) {
            a4 = a5.x.r.m.get(a5.x.r.q);
            a3 = a4.o.size() > a5.t ? a4.o.get(a5.t) : new Matrix4f();
            a2 = a4.y.size() > a5.t ? a4.y.get(a5.t) : new Matrix4f();
        } else {
            a3 = a5.m;
            a2 = a5.c;
        }
        a4 = new Matrix4f();
        Matrix4f a6 = new Matrix4f();
        Matrix4f.mul((Matrix4f)a3, (Matrix4f)a2, (Matrix4f)a4);
        a5.q = a5.r != null ? Matrix4f.mul((Matrix4f)a5.r.q, (Matrix4f)a4, (Matrix4f)a5.ALLATORIxDEMO()) : a4;
        Matrix4f.mul((Matrix4f)a2, (Matrix4f)a5.q, (Matrix4f)a6);
        Matrix4f.invert((Matrix4f)a6, (Matrix4f)a5.o);
        for (vr a7 : a5.y) {
            a7.c();
        }
    }

    public Matrix4f ALLATORIxDEMO() {
        vr a2;
        return a2.q == null ? (a2.q = new Matrix4f()) : a2.q;
    }

    public void ALLATORIxDEMO() {
        vr a2;
        mz a3 = a2.x.ALLATORIxDEMO();
        if (a3 != null) {
            HashMap<Integer, Matrix4f> a4 = a2.ALLATORIxDEMO.get(a3.k.y);
            Matrix4f matrix4f = a4.get(a3.ALLATORIxDEMO);
            Matrix4f a6 = new Matrix4f();
            Matrix4f.mul((Matrix4f)matrix4f, (Matrix4f)a2.c, (Matrix4f)a6);
            a2.q = a2.q == null ? a6 : Matrix4f.mul((Matrix4f)a2.q, (Matrix4f)a6, (Matrix4f)a2.q);
        }
        for (Map.Entry<ev, Float> entry : a2.k.entrySet()) {
            entry.getKey().ALLATORIxDEMO(a2, entry.getValue().floatValue());
        }
        a2.f();
    }

    @Override
    public float ALLATORIxDEMO(float a2, pz a3) {
        return a2;
    }

    @Override
    public float ALLATORIxDEMO(pz a2) {
        return 0.0f;
    }
}

