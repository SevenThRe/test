/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableMap
 */
package eos.moe.dragoncore;

import com.google.common.collect.ImmutableMap;
import java.util.Map;
import java.util.function.Function;

public interface n {
    public static final n b = () -> n.f(n::c);
    public static final n o = () -> n.f(n::ALLATORIxDEMO);
    public static final n y = () -> n.c(n::ALLATORIxDEMO);
    public static final n k = () -> n.ALLATORIxDEMO(n::ALLATORIxDEMO);
    public static final Map<String, n> ALLATORIxDEMO = ImmutableMap.of((Object)"LINEAR", (Object)b, (Object)"EASE_IN", (Object)o, (Object)"EASE_OUT", (Object)y, (Object)"EASE_IN_OUT", (Object)k);

    public Function<Double, Double> d();

    public static double c(double a2) {
        return a2;
    }

    public static Function<Double, Double> f(Function<Double, Double> a2) {
        return a2;
    }

    public static Function<Double, Double> c(Function<Double, Double> a2) {
        return a3 -> 1.0 - (Double)a2.apply(1.0 - a3);
    }

    public static Function<Double, Double> ALLATORIxDEMO(Function<Double, Double> a2) {
        return a3 -> {
            if (a3 < 0.5) {
                return (Double)a2.apply(a3 * 2.0) / 2.0;
            }
            return 1.0 - (Double)a2.apply((1.0 - a3) * 2.0) / 2.0;
        };
    }

    public static double ALLATORIxDEMO(double a2) {
        return 1.0 - Math.cos(a2 * Math.PI / 2.0);
    }
}

