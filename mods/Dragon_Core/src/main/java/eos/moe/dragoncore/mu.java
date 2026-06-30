/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.b;
import eos.moe.dragoncore.bw;
import eos.moe.dragoncore.uz;
import eos.moe.dragoncore.yo;
import java.util.List;
import java.util.function.Predicate;

public class mu {
    public mu() {
        mu a2;
    }

    public static List<b> ALLATORIxDEMO(List<b> a2, Predicate<b> a3) {
        yo a4 = new yo();
        uz a5 = new uz(a3);
        a4.ALLATORIxDEMO().add(a5);
        a4.c(a2);
        return a5.ALLATORIxDEMO();
    }

    public static b ALLATORIxDEMO(List<b> a2, Predicate<b> a3) {
        yo a4 = new yo();
        bw a5 = new bw(a3);
        a4.ALLATORIxDEMO().add(a5);
        a4.c(a2);
        return a5.ALLATORIxDEMO();
    }
}

