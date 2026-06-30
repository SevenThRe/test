/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.cl;
import eos.moe.dragoncore.ed;
import eos.moe.dragoncore.gn;
import eos.moe.dragoncore.ho;
import eos.moe.dragoncore.jf;
import eos.moe.dragoncore.ld;
import eos.moe.dragoncore.oh;
import eos.moe.dragoncore.ok;
import eos.moe.dragoncore.qj;
import eos.moe.dragoncore.te;

public abstract class bh
extends Enum<bh> {
    public static final /* enum */ bh t = new ld("linear");
    public static final /* enum */ bh r = new te("quad_in");
    public static final /* enum */ bh x = new jf("quad_out");
    public static final /* enum */ bh v = new ho("quad_inout");
    public static final /* enum */ bh m = new qj("cubic_in");
    public static final /* enum */ bh c = new ok("cubic_out");
    public static final /* enum */ bh q = new ed("cubic_inout");
    public static final /* enum */ bh b = new gn("exp_in");
    public static final /* enum */ bh o = new cl("exp_out");
    public static final /* enum */ bh y = new oh("exp_inout");
    public final String k;
    private static final /* synthetic */ bh[] ALLATORIxDEMO;

    public static bh[] values() {
        return (bh[])ALLATORIxDEMO.clone();
    }

    public static bh valueOf(String a2) {
        return Enum.valueOf(bh.class, a2);
    }

    /*
     * WARNING - Possible parameter corruption
     * WARNING - void declaration
     */
    private /* synthetic */ bh(String string) {
        void a2;
        void var2_-1;
        void var1_-1;
        bh a3;
        a3.k = a2;
    }

    public abstract float ALLATORIxDEMO(float var1, float var2, float var3);

    public String ALLATORIxDEMO() {
        bh a2;
        return "mclib.interpolations." + a2.k;
    }

    public /* synthetic */ bh(String a2, int a3, String a4, ld a5) {
        a6(a2, a3, a4);
        bh a6;
    }

    static {
        ALLATORIxDEMO = new bh[]{t, r, x, v, m, c, q, b, o, y};
    }
}

