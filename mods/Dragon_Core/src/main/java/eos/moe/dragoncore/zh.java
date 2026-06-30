/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.ae;
import eos.moe.dragoncore.eo;
import eos.moe.dragoncore.fe;
import eos.moe.dragoncore.ig;
import eos.moe.dragoncore.jl;
import eos.moe.dragoncore.km;
import eos.moe.dragoncore.oj;
import eos.moe.dragoncore.on;
import eos.moe.dragoncore.qi;
import eos.moe.dragoncore.uj;
import eos.moe.dragoncore.vd;
import eos.moe.dragoncore.vj;
import eos.moe.dragoncore.xd;
import eos.moe.dragoncore.xi;
import java.util.HashSet;
import java.util.Set;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public abstract class zh
extends Enum<zh> {
    public static final /* enum */ zh j = new km("+", 1);
    public static final /* enum */ zh i = new xi("-", 1);
    public static final /* enum */ zh l = new on("*", 2);
    public static final /* enum */ zh z = new eo("/", 2);
    public static final /* enum */ zh s = new xd("%", 2);
    public static final /* enum */ zh g = new qi("^", 3);
    public static final /* enum */ zh t = new oj("&&", 5);
    public static final /* enum */ zh r = new vj("||", 5);
    public static final /* enum */ zh x = new jl("<", 5);
    public static final /* enum */ zh v = new uj("<=", 5);
    public static final /* enum */ zh m = new ae(">=", 5);
    public static final /* enum */ zh c = new vd(">", 5);
    public static final /* enum */ zh q = new ig("==", 5);
    public static final /* enum */ zh b = new fe("!=", 5);
    public static final Set<String> o;
    public final String y;
    public final int k;
    private static final /* synthetic */ zh[] ALLATORIxDEMO;

    public static zh[] values() {
        return (zh[])ALLATORIxDEMO.clone();
    }

    public static zh valueOf(String a2) {
        return Enum.valueOf(zh.class, a2);
    }

    public static boolean ALLATORIxDEMO(double a2, double a3) {
        return Math.abs(a2 - a3) < 1.0E-5;
    }

    /*
     * WARNING - void declaration
     */
    private /* synthetic */ zh(String string2, int string2) {
        void a2;
        void a3;
        void var2_-1;
        void var1_-1;
        zh a4;
        a4.y = a3;
        a4.k = a2;
    }

    public abstract double ALLATORIxDEMO(double var1, double var3);

    public /* synthetic */ zh(String a2, int a3, String a4, int a5, km a6) {
        a7(a2, a3, a4, a5);
        zh a7;
    }

    static {
        ALLATORIxDEMO = new zh[]{j, i, l, z, s, g, t, r, x, v, m, c, q, b};
        o = new HashSet<String>();
        for (zh a2 : zh.values()) {
            o.add(a2.y);
        }
    }
}

