/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonPrimitive
 */
package eos.moe.dragoncore;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import eos.moe.dragoncore.eg;
import eos.moe.dragoncore.nd;
import eos.moe.dragoncore.nn;
import eos.moe.dragoncore.u;
import eos.moe.dragoncore.zh;

public abstract class gm
implements u {
    public nd ALLATORIxDEMO;

    public static boolean f(gm a2) {
        return gm.ALLATORIxDEMO(a2, 0.0);
    }

    public static boolean c(gm a2) {
        return gm.ALLATORIxDEMO(a2, 1.0);
    }

    public static boolean ALLATORIxDEMO(gm a2, double a3) {
        if (a2 instanceof nn) {
            nn a4 = (nn)a2;
            return a4.k instanceof eg && zh.ALLATORIxDEMO(a4.k.ALLATORIxDEMO(), a3);
        }
        return false;
    }

    public static boolean ALLATORIxDEMO(gm a2) {
        if (a2 instanceof nn) {
            nn a3 = (nn)a2;
            return a3.k instanceof eg;
        }
        return false;
    }

    public gm(nd a2) {
        gm a3;
        a3.ALLATORIxDEMO = a2;
    }

    public JsonElement ALLATORIxDEMO() {
        gm a2;
        return new JsonPrimitive(a2.toString());
    }
}

