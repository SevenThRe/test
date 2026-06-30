/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  net.minecraft.client.Minecraft
 */
package eos.moe.dragoncore;

import com.google.common.collect.Lists;
import eos.moe.dragoncore.b;
import eos.moe.dragoncore.br;
import eos.moe.dragoncore.hl;
import eos.moe.dragoncore.iv;
import eos.moe.dragoncore.kp;
import eos.moe.dragoncore.m;
import eos.moe.dragoncore.pf;
import eos.moe.dragoncore.ui;
import eos.moe.dragoncore.v;
import eos.moe.dragoncore.vp;
import eos.moe.dragoncore.xn;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.Minecraft;

public class qv {
    private final xn q;
    public static final hl b = new hl();
    public static final iv o = new iv(new File(Minecraft.getMinecraft().gameDir, "config" + File.separator + "DragonCore" + File.separator + "PlayerData" + File.separator + Minecraft.getMinecraft().getSession().getUsername() + ".yml"));
    public static final iv y = new iv(new File(Minecraft.getMinecraft().gameDir, "config" + File.separator + "DragonCore" + File.separator + "ClientData.yml"));
    public static final iv k = new iv(new File(System.getProperty("user.home"), "DragonCore" + File.separator + "ClientData.yml"));
    public hl ALLATORIxDEMO;

    public qv() {
        qv a2;
        a2.q = new xn();
        a2.ALLATORIxDEMO();
    }

    public qv(ui a2, hl a3) {
        qv a4;
        a4.q = new xn(a2);
        a4.ALLATORIxDEMO = a3;
        a4.ALLATORIxDEMO();
    }

    public qv(kp a2) {
        qv a3;
        a3.q = new xn(a2);
        a3.ALLATORIxDEMO();
    }

    private /* synthetic */ void ALLATORIxDEMO() {
        qv a2;
        if (a2.ALLATORIxDEMO == null) {
            a2.ALLATORIxDEMO = new hl();
        }
        a2.ALLATORIxDEMO("\u5c40\u90e8\u53d8\u91cf", "temp", new hl());
        a2.ALLATORIxDEMO("\u754c\u9762\u53d8\u91cf", "variable", a2.ALLATORIxDEMO);
        a2.ALLATORIxDEMO("\u53d8\u91cf", "var", a2.ALLATORIxDEMO);
        a2.ALLATORIxDEMO("\u5168\u5c40\u53d8\u91cf", "global", b);
        a2.ALLATORIxDEMO("\u672c\u5730\u53d8\u91cf", "client", y);
        a2.ALLATORIxDEMO("\u7528\u6237\u53d8\u91cf", "player", o);
        a2.ALLATORIxDEMO("\u7cfb\u7edf\u53d8\u91cf", "system", k);
        a2.ALLATORIxDEMO("\u6570\u7ec4", "array", new vp());
    }

    public void ALLATORIxDEMO(String a2, String a3, m a4) {
        qv a5;
        a5.q.ALLATORIxDEMO().put(a2, a4);
        a5.q.ALLATORIxDEMO().put(a3, a4);
    }

    public v ALLATORIxDEMO(b a2) {
        qv a3;
        return a3.ALLATORIxDEMO(Lists.newArrayList((Object[])new b[]{a2}));
    }

    public v ALLATORIxDEMO(List<b> a2) {
        qv a3;
        Object a4 = a3.q.ALLATORIxDEMO();
        return a3.ALLATORIxDEMO(a2, (xn)a4);
    }

    public v ALLATORIxDEMO(List<b> a2, xn a3) {
        v a4 = new pf(0.0);
        br a5 = new br();
        for (b a6 : new ArrayList<b>(a2)) {
            if (a5.ALLATORIxDEMO() != null) break;
            a4 = a6.ALLATORIxDEMO(a5, a3);
        }
        if (a4 == null) {
            return new pf(0.0);
        }
        return a5.ALLATORIxDEMO() != null ? a5.ALLATORIxDEMO() : a4;
    }

    public xn ALLATORIxDEMO() {
        qv a2;
        return a2.q;
    }
}

