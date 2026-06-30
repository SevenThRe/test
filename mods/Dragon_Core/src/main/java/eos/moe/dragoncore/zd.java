/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.go;
import eos.moe.dragoncore.ue;
import eos.moe.dragoncore.vh;
import java.util.concurrent.Executor;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

public class zd
implements Executor {
    public final long b;
    public final TimeUnit o;
    public final Executor y;
    private static final boolean k = ForkJoinPool.getCommonPoolParallelism() > 1;
    private static final Executor ALLATORIxDEMO = k ? ForkJoinPool.commonPool() : new ue();

    public zd(long a2, TimeUnit a3, Executor a4) {
        zd a5;
        a5.b = a2;
        a5.o = a3;
        a5.y = a4;
    }

    @Override
    public void execute(Runnable a2) {
        zd a3;
        vh.ALLATORIxDEMO(new go(a3.y, a2), a3.b, a3.o);
    }

    public static Executor ALLATORIxDEMO(long a2, TimeUnit a3) {
        if (a3 == null) {
            throw new NullPointerException();
        }
        return new zd(a2, a3, ALLATORIxDEMO);
    }

    public static Executor ALLATORIxDEMO(long a2, TimeUnit a3, Executor a4) {
        if (a3 == null || a4 == null) {
            throw new NullPointerException();
        }
        return new zd(a2, a3, a4);
    }
}

