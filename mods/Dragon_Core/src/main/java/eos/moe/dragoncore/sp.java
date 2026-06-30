/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.util.concurrent.ListenableFuture
 *  net.minecraft.client.Minecraft
 */
package eos.moe.dragoncore;

import com.google.common.util.concurrent.ListenableFuture;
import eos.moe.dragoncore.fm;
import eos.moe.dragoncore.fv;
import eos.moe.dragoncore.i;
import eos.moe.dragoncore.kp;
import eos.moe.dragoncore.nh;
import eos.moe.dragoncore.om;
import eos.moe.dragoncore.pf;
import eos.moe.dragoncore.qg;
import eos.moe.dragoncore.v;
import eos.moe.dragoncore.xn;
import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import net.minecraft.client.Minecraft;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class sp {
    public sp() {
        sp a2;
    }

    @i(f={"\u53d6\u5b9e\u4f53\u540d"})
    public static String c(kp a2) {
        return a2.y.func_145748_c_().func_150260_c();
    }

    @i(f={"\u53d6\u5b9e\u4f53UUID"})
    public static String ALLATORIxDEMO(kp a2) {
        return a2.y.func_110124_au().toString();
    }

    @i(f={"\u53d6\u5b9e\u4f53\u9ad8\u5ea6"})
    public static double x(kp a2) {
        return a2.y.field_70131_O;
    }

    @i(f={"\u53d6\u5b9e\u4f53\u8840\u91cf"})
    public static double f(kp a2) {
        return a2.y.func_110143_aJ();
    }

    @i(f={"\u53d6\u5b9e\u4f53\u6700\u5927\u8840\u91cf"})
    public static double c(kp a2) {
        return a2.y.func_110138_aP();
    }

    @i(f={"\u53d6\u5b9e\u4f53\u8840\u91cf\u6bd4\u4f8b"})
    public static double ALLATORIxDEMO(kp a2) {
        return a2.y.func_110143_aJ() / a2.y.func_110138_aP();
    }

    @i(f={"\u6267\u884c\u65b9\u6cd5", "Function_Execute"})
    public static v c(kp a2, String a3, v ... a4) {
        if (a2.t) {
            return pf.y;
        }
        nh a5 = a2.c.get(a3);
        if (a5 != null) {
            return fm.ALLATORIxDEMO(a5, a4);
        }
        return pf.y;
    }

    @i(f={"\u5f02\u6b65\u6267\u884c\u65b9\u6cd5", "Function_Async_Execute"})
    public static void ALLATORIxDEMO(kp a2, String a3, v ... a4) {
        if (a2.t) {
            return;
        }
        nh a5 = a2.c.get(a3);
        if (a5 != null) {
            qg a6 = new qg(Arrays.asList(a4));
            Object a7 = a5.ALLATORIxDEMO().ALLATORIxDEMO().ALLATORIxDEMO();
            ((xn)a7).ALLATORIxDEMO("\u5c40\u90e8\u53d8\u91cf.\u53c2\u6570", a6);
            ((xn)a7).ALLATORIxDEMO("\u5c40\u90e8\u53d8\u91cf.args", a6);
            CompletableFuture<v> a8 = CompletableFuture.supplyAsync(() -> sp.ALLATORIxDEMO(a5, (xn)a7), om.q);
            a8.thenRun(() -> a2.v.remove(a8));
            a2.v.add(a8);
        }
    }

    @i(f={"\u89e3\u6790\u811a\u672c", "Function_Parse"})
    public static v ALLATORIxDEMO(kp a2, String a3) {
        return new fv(a2.ALLATORIxDEMO(a3));
    }

    @i(f={"\u4e3b\u7ebf\u7a0b\u6267\u884c\u65b9\u6cd5", "Function_Sync_Execute"})
    public static v ALLATORIxDEMO(kp a2, String a3, v ... a4) {
        if (a2.t) {
            return pf.y;
        }
        nh a5 = a2.c.get(a3);
        if (a5 != null) {
            if (Minecraft.func_71410_x().func_152345_ab()) {
                return sp.c(a2, a3, a4);
            }
            qg a6 = new qg(Arrays.asList(a4));
            Object a7 = a5.ALLATORIxDEMO().ALLATORIxDEMO().ALLATORIxDEMO();
            ((xn)a7).ALLATORIxDEMO("\u5c40\u90e8\u53d8\u91cf.\u53c2\u6570", a6);
            ((xn)a7).ALLATORIxDEMO("\u5c40\u90e8\u53d8\u91cf.args", a6);
            ListenableFuture a8 = Minecraft.func_71410_x().func_152343_a(() -> sp.c(a5, (xn)a7));
            a8.addListener(() -> a2.v.remove(a8), (Executor)om.q);
            a2.v.add((Future)a8);
        }
        return pf.y;
    }

    private static /* synthetic */ v c(nh a2, xn a3) throws Exception {
        return a2.ALLATORIxDEMO().ALLATORIxDEMO(a2.x(), a3);
    }

    private static /* synthetic */ v ALLATORIxDEMO(nh a2, xn a3) {
        try {
            return a2.ALLATORIxDEMO().ALLATORIxDEMO(a2.x(), a3);
        }
        catch (Exception a4) {
            a4.printStackTrace();
            return null;
        }
    }
}

