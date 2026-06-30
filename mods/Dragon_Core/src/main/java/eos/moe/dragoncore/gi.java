/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.util.concurrent.ListenableFuture
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.GuiScreen
 */
package eos.moe.dragoncore;

import com.google.common.util.concurrent.ListenableFuture;
import eos.moe.dragoncore.de;
import eos.moe.dragoncore.fm;
import eos.moe.dragoncore.fv;
import eos.moe.dragoncore.hja;
import eos.moe.dragoncore.i;
import eos.moe.dragoncore.jj;
import eos.moe.dragoncore.kn;
import eos.moe.dragoncore.nh;
import eos.moe.dragoncore.om;
import eos.moe.dragoncore.pe;
import eos.moe.dragoncore.pf;
import eos.moe.dragoncore.qg;
import eos.moe.dragoncore.ui;
import eos.moe.dragoncore.v;
import eos.moe.dragoncore.wi;
import eos.moe.dragoncore.wk;
import eos.moe.dragoncore.xn;
import java.util.Arrays;
import java.util.Locale;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class gi {
    public gi() {
        gi a2;
    }

    @i(f={"\u53d8\u91cf", "\u754c\u9762\u53d8\u91cf", "variable", "var"})
    public static v f(ui a2) {
        return a2.getMoLangRuntime().ALLATORIxDEMO;
    }

    @i(f={"\u8de8\u754c\u9762\u6267\u884c\u65b9\u6cd5", "Function_Screen_Execute"})
    public static v ALLATORIxDEMO(ui a2, String a3, String a4, v[] a5) {
        if (a2.w) {
            return pf.y;
        }
        ui a6 = null;
        GuiScreen a7 = Minecraft.getMinecraft().currentScreen;
        if (a3.equalsIgnoreCase("override") || a3.equalsIgnoreCase("subgui")) {
            a6 = wi.b.ALLATORIxDEMO();
        } else if (a3.equalsIgnoreCase("tooltip") || a3.equalsIgnoreCase("itemtip")) {
            a6 = hja.c.k;
        } else if (a7 instanceof ui && (a3.isEmpty() || ((ui)a7).wa.toLowerCase().equals(a3.toLowerCase(Locale.ROOT)))) {
            a6 = (ui)a7;
        } else if (de.c.containsKey(a3.toLowerCase(Locale.ROOT))) {
            a6 = de.c.get(a3.toLowerCase(Locale.ROOT));
        }
        if (a6 == null) {
            return pf.y;
        }
        nh a8 = a6.a.get(a4);
        if (a8 != null) {
            return fm.ALLATORIxDEMO(a8, a5);
        }
        return pf.y;
    }

    @i(f={"\u6267\u884c\u65b9\u6cd5", "Function_Execute"})
    public static v c(ui a2, String a3, v ... a4) {
        if (a2.w) {
            return pf.y;
        }
        nh a5 = a2.a.get(a3);
        if (a5 != null) {
            return fm.ALLATORIxDEMO(a5, a4);
        }
        return pf.y;
    }

    @i(f={"\u6267\u884c\u7ec4\u4ef6\u65b9\u6cd5", "Component_Function_Execute"})
    public static v ALLATORIxDEMO(ui a2, v a3, String a4) {
        jj a5;
        jj jj2 = a5 = a3 instanceof pe ? (jj)a3.ALLATORIxDEMO() : a2.findComponent(a3.c());
        if (a5 != null) {
            return a5.runAction(a4);
        }
        return wk.k;
    }

    @i(f={"\u5f02\u6b65\u6267\u884c\u65b9\u6cd5", "Function_Async_Execute"})
    public static void ALLATORIxDEMO(ui a2, String a3, v ... a4) {
        if (a2.w) {
            return;
        }
        nh a5 = a2.a.get(a3);
        if (a5 != null) {
            qg a6 = new qg(Arrays.asList(a4));
            Object a7 = a5.ALLATORIxDEMO().ALLATORIxDEMO().ALLATORIxDEMO();
            ((xn)a7).ALLATORIxDEMO("\u5c40\u90e8\u53d8\u91cf.\u53c2\u6570", a6);
            ((xn)a7).ALLATORIxDEMO("\u5c40\u90e8\u53d8\u91cf.args", a6);
            CompletableFuture<v> a8 = CompletableFuture.supplyAsync(() -> gi.ALLATORIxDEMO(a5, (xn)a7), om.q);
            a8.thenRun(() -> a2.i.remove(a8));
            a2.i.add(a8);
        }
    }

    @i(f={"\u89e3\u6790\u811a\u672c", "Function_Parse"})
    public static v ALLATORIxDEMO(ui a2, String a3) {
        return new fv(a2.parseExpression(a3));
    }

    @i(f={"\u4e3b\u7ebf\u7a0b\u6267\u884c\u65b9\u6cd5", "Function_Sync_Execute"})
    public static v ALLATORIxDEMO(ui a2, String a3, v ... a4) {
        if (a2.w) {
            return pf.y;
        }
        nh a5 = a2.a.get(a3);
        if (a5 != null) {
            if (Minecraft.getMinecraft().isCallingFromMinecraftThread()) {
                return gi.c(a2, a3, a4);
            }
            qg a6 = new qg(Arrays.asList(a4));
            Object a7 = a5.ALLATORIxDEMO().ALLATORIxDEMO().ALLATORIxDEMO();
            ((xn)a7).ALLATORIxDEMO("\u5c40\u90e8\u53d8\u91cf.\u53c2\u6570", a6);
            ((xn)a7).ALLATORIxDEMO("\u5c40\u90e8\u53d8\u91cf.args", a6);
            ListenableFuture a8 = Minecraft.getMinecraft().addScheduledTask(() -> gi.c(a5, (xn)a7));
            a8.addListener(() -> a2.i.remove(a8), (Executor)om.q);
            a2.i.add((Future<v>)a8);
        }
        return pf.y;
    }

    @i(f={"\u5207\u6362\u65b9\u6cd5\u96c6", "Function_Change"})
    public static void ALLATORIxDEMO(ui a2, String a3, int a4) {
        a2.reloadFunctions(a3, a4);
    }

    @i(f={"\u754c\u9762\u662f\u5426\u5df2\u5173\u95ed", "Screen_Is_Close"})
    public static boolean ALLATORIxDEMO(ui a2) {
        return a2.w;
    }

    @i(f={"\u53d6\u5f53\u524d\u6d88\u606f", "Message_Current"})
    public static v c(ui a2) {
        if (a2 == null) {
            return pf.y;
        }
        return new kn(de.y);
    }

    @i(f={"\u53d6\u6700\u540e\u4e00\u6761\u6d88\u606f", "Message_Last"})
    public static v ALLATORIxDEMO(ui a2) {
        if (a2 == null) {
            return pf.y;
        }
        return new kn(de.k);
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

