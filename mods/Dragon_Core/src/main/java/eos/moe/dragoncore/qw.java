/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.world.World
 */
package eos.moe.dragoncore;

import blockbuster.BedrockScheme;
import blockbuster.emitter.BedrockEmitter;
import eos.moe.dragoncore.api.model.AnimationEndEntityModel;
import eos.moe.dragoncore.api.model.AnimationEntityModel;
import eos.moe.dragoncore.cp;
import eos.moe.dragoncore.da;
import eos.moe.dragoncore.dq;
import eos.moe.dragoncore.gga;
import eos.moe.dragoncore.hr;
import eos.moe.dragoncore.kq;
import eos.moe.dragoncore.kw;
import eos.moe.dragoncore.li;
import eos.moe.dragoncore.so;
import eos.moe.dragoncore.st;
import eos.moe.dragoncore.xr;
import eos.moe.dragoncore.zw;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import net.minecraft.client.Minecraft;
import net.minecraft.world.World;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public abstract class qw
implements da {
    private gga q;
    private final zw b;
    private final zw o;
    private Map<String, so> y;
    private List<so> k;
    private final Map<kw, BedrockEmitter> ALLATORIxDEMO = new HashMap<kw, BedrockEmitter>();

    public qw(zw a2, zw a3) {
        qw a4;
        a4.b = a2;
        a4.o = a3;
    }

    @Override
    public boolean ALLATORIxDEMO(String a2) {
        qw a3;
        return a3.y.get(a2) != null;
    }

    @Override
    public so ALLATORIxDEMO(String a2) {
        qw a3;
        so a4 = a3.y.get(a2);
        if (a4 == null) {
            throw new RuntimeException("\u672a\u627e\u5230\u52a8\u753b\u5c42 " + a2);
        }
        return a4;
    }

    @Override
    public Set<String> ALLATORIxDEMO() {
        qw a2;
        return a2.y.keySet();
    }

    @Override
    public void ALLATORIxDEMO(st a2, String a3) {
        qw a4;
        dq a5 = a2.ALLATORIxDEMO();
        if (a4.ALLATORIxDEMO(a3)) {
            kq a6;
            xr a7;
            so a8 = a4.ALLATORIxDEMO(a3);
            if (a5.ALLATORIxDEMO() && (a7 = a8.ALLATORIxDEMO()) != null && ((a6 = a5.ALLATORIxDEMO()).equals(a7.ALLATORIxDEMO()) || a7 instanceof hr && a6.equals(((hr)a7).c()))) {
                return;
            }
            a8.ALLATORIxDEMO(a5);
        }
    }

    @Override
    public void ALLATORIxDEMO(String a2) {
        qw a3;
        a3.ALLATORIxDEMO(a2, -1);
    }

    @Override
    public void ALLATORIxDEMO(String a2, int a3) {
        qw a4;
        if (a3 == -1) {
            a3 = cp.y;
        }
        if (a2 == null) {
            for (so a5 : a4.k) {
                a5.ALLATORIxDEMO(a3, null);
            }
        } else if (a4.ALLATORIxDEMO(a2)) {
            so a6 = a4.ALLATORIxDEMO(a2);
            xr a7 = a6.ALLATORIxDEMO();
            a6.ALLATORIxDEMO(a3, null);
            if (a7 != null) {
                a4.ALLATORIxDEMO(null, a6, a7);
            }
        }
    }

    @Override
    public boolean ALLATORIxDEMO(AnimationEntityModel a2) {
        qw a3;
        long a4 = System.currentTimeMillis();
        boolean a5 = false;
        for (so a6 : a3.k) {
            a6.ALLATORIxDEMO(a3, a2, a4);
            xr a7 = a6.ALLATORIxDEMO();
            if (a7 == null || a7.ALLATORIxDEMO() == null) continue;
            a3.ALLATORIxDEMO(a2, a6, a7, a4);
            a5 = true;
        }
        return a5;
    }

    public void ALLATORIxDEMO(AnimationEntityModel a2) {
        qw a3;
        long a4 = System.currentTimeMillis();
        for (so a5 : a3.k) {
            Set<kw> a6 = a5.c();
            for (kw a7 : a6) {
                a3.ALLATORIxDEMO.remove(a7);
            }
            a5.f().removeAll(a6);
            a6.clear();
            Set<kw> a8 = a5.ALLATORIxDEMO();
            for (kw a9 : a8) {
                BedrockScheme a10 = li.k.ALLATORIxDEMO(a9.f());
                BedrockEmitter a11 = new BedrockEmitter();
                a11.setScheme(a10);
                a11.setWorld((World)Minecraft.func_71410_x().field_71441_e);
                a3.ALLATORIxDEMO.put(a9, a11);
            }
            a5.f().addAll(a8);
            a8.clear();
        }
    }

    public Map<kw, BedrockEmitter> c() {
        qw a2;
        return a2.ALLATORIxDEMO;
    }

    public void ALLATORIxDEMO() {
    }

    public abstract void ALLATORIxDEMO(AnimationEntityModel var1, so var2, xr var3, long var4);

    public void ALLATORIxDEMO(AnimationEntityModel a2, so a3, xr a4) {
        qw a5;
        if (a5.o != null && !a5.isOnPlayAnimation() && !a4.ALLATORIxDEMO().ALLATORIxDEMO().contains("idle")) {
            a5.ALLATORIxDEMO(a5.o.ALLATORIxDEMO(), a5.o.ALLATORIxDEMO());
        }
        if (a2 instanceof AnimationEndEntityModel) {
            ((AnimationEndEntityModel)((Object)a2)).onAnimationEnd(a2, a4.ALLATORIxDEMO().ALLATORIxDEMO());
        }
    }

    public void ALLATORIxDEMO(LinkedHashMap<String, so> a2) {
        a.y = a2;
        a.k = new ArrayList<so>(a2.values());
    }

    @Override
    public boolean isOnPlayAnimation() {
        qw a2;
        for (so a3 : a2.k) {
            xr a4 = a3.ALLATORIxDEMO();
            if (a4 == null || a4.ALLATORIxDEMO() == null) continue;
            return true;
        }
        return false;
    }

    public boolean ALLATORIxDEMO() {
        qw a2;
        long a3 = System.currentTimeMillis();
        for (so a4 : a2.k) {
            xr a5 = a4.ALLATORIxDEMO();
            if (a5 == null || a5.ALLATORIxDEMO() == null || a5.ALLATORIxDEMO(a3) || !a5.ALLATORIxDEMO().ALLATORIxDEMO().endsWith("_ig")) continue;
            return true;
        }
        return false;
    }

    public zw c() {
        qw a2;
        return a2.b;
    }

    @Override
    public void ALLATORIxDEMO(gga a2) {
        a.q = a2;
    }

    @Override
    public gga ALLATORIxDEMO() {
        qw a2;
        return a2.q;
    }

    public zw ALLATORIxDEMO() {
        qw a2;
        return a2.o;
    }

    public Map<String, so> ALLATORIxDEMO() {
        qw a2;
        return a2.y;
    }
}

