/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.jx;
import eos.moe.dragoncore.mv;
import eos.moe.dragoncore.mx;
import eos.moe.dragoncore.ou;

public abstract class yq
extends Enum<yq> {
    public static final /* enum */ yq q = new ou();
    public static final /* enum */ yq b = new mv();
    public static final /* enum */ yq o = new mx();
    public static final /* enum */ yq y = new jx();
    public static int k;
    private static final /* synthetic */ yq[] ALLATORIxDEMO;

    public static yq[] values() {
        return (yq[])ALLATORIxDEMO.clone();
    }

    public static yq valueOf(String a2) {
        return Enum.valueOf(yq.class, a2);
    }

    /*
     * WARNING - Possible parameter corruption
     * WARNING - void declaration
     */
    private /* synthetic */ yq() {
        void var2_-1;
        void var1_-1;
        yq a2;
    }

    public abstract void c(Object ... var1);

    public abstract void ALLATORIxDEMO(Object ... var1);

    public /* synthetic */ yq(String a2, int a3, ou a4) {
        a5(a2, a3);
        yq a5;
    }

    static {
        ALLATORIxDEMO = new yq[]{q, b, o, y};
        k = 33986;
    }
}

