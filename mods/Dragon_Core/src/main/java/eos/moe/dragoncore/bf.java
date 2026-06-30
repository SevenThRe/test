/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.ModelLocator;
import eos.moe.dragoncore.bax;
import eos.moe.dragoncore.gv;
import eos.moe.dragoncore.jv;
import eos.moe.dragoncore.mt;
import eos.moe.dragoncore.xu;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class bf
implements Serializable {
    private static final long serialVersionUID = 5421534128042221098L;
    private List<gv> b;
    public bax a;
    private bax c;
    public boolean e;
    private boolean g;
    public float f;
    public String m;
    public String t;
    public List<bf> r;
    public Map<String, ModelLocator> locatorMap;

    public bf(List<gv> a2, bax a3, bax a4, boolean a5, boolean a6, float a7, String a8, String a9) {
        bf a10;
        a10.b = a2;
        a10.a = a3;
        a10.c = a4;
        a10.e = a5;
        a10.g = a6;
        a10.f = a7;
        a10.m = a8;
        a10.t = a9;
    }

    public mt bake(jv a2, bf a3) {
        bf a4;
        ArrayList<xu> a5 = new ArrayList<xu>(a4.b.size());
        for (gv a6 : a4.b) {
            a5.add(a6.bake(a2, a4));
        }
        mt a7 = new mt(a2, a4.c, a4.m, a5, a4.g);
        if (a3 != null) {
            a7.func_78793_a(a4.a.getX() - a3.a.getX(), -(a4.a.getY() - a3.a.getY()), a4.a.getZ() - a3.a.getZ());
        } else {
            a7.func_78793_a(a4.a.getX(), -a4.a.getY(), a4.a.getZ());
        }
        if (a4.r != null) {
            a7.field_78805_m = new ArrayList(a4.r.size());
            for (bf a8 : a4.r) {
                mt a9 = a8.bake(a2, a4);
                a9.m = a7;
                a7.field_78805_m.add(a9);
            }
        }
        return a7;
    }
}

