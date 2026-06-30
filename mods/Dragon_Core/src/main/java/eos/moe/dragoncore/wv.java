/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.au;
import eos.moe.dragoncore.i;
import eos.moe.dragoncore.lr;
import java.lang.reflect.Method;
import java.util.Locale;

public class wv {
    public wv() {
        wv a2;
    }

    public static void ALLATORIxDEMO(Class<?> a2, Object a3, lr a4) {
        for (Method a5 : a2.getDeclaredMethods()) {
            if (!a5.isAnnotationPresent(i.class)) continue;
            i a6 = a5.getAnnotation(i.class);
            au a7 = new au(a5, a6.c(), a3);
            for (String a8 : a6.f()) {
                a4.ALLATORIxDEMO().put(a8.toLowerCase(Locale.ROOT), a7);
            }
        }
    }
}

