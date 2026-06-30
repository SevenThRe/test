/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.b;
import eos.moe.dragoncore.br;
import eos.moe.dragoncore.pf;
import eos.moe.dragoncore.v;
import eos.moe.dragoncore.xn;
import eos.moe.dragoncore.zd;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import net.minecraft.client.Minecraft;

public class ur
implements b {
    private static final Executor o = a2 -> Minecraft.getMinecraft().addScheduledTask(a2);
    public b y;
    public b k;
    public b ALLATORIxDEMO;

    public ur(b a2, b a3, b a4) {
        ur a5;
        a5.k = a3;
        a5.ALLATORIxDEMO = a4;
        a5.y = a2;
    }

    @Override
    public v ALLATORIxDEMO(br a2, xn a3) {
        ur a4;
        boolean a5 = a4.ALLATORIxDEMO.ALLATORIxDEMO(a2, a3).ALLATORIxDEMO();
        int a6 = (int)a4.k.ALLATORIxDEMO(a2, a3).ALLATORIxDEMO();
        Executor a7 = a5 ? zd.ALLATORIxDEMO(a6, TimeUnit.MILLISECONDS, o) : zd.ALLATORIxDEMO(a6, TimeUnit.MILLISECONDS);
        Object a8 = a3.ALLATORIxDEMO();
        CompletableFuture.runAsync(() -> a4.ALLATORIxDEMO(a2, (xn)a8), a7);
        return pf.y;
    }

    private /* synthetic */ void ALLATORIxDEMO(br a2, xn a3) {
        ur a4;
        a4.y.ALLATORIxDEMO(a2, a3);
    }
}

