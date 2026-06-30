/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.ResourceLocation
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.api.model.AnimationEntityModel;
import eos.moe.dragoncore.api.model.AnimationModel;
import eos.moe.dragoncore.api.model.AnimationModelRenderer;
import eos.moe.dragoncore.bax;
import eos.moe.dragoncore.dp;
import eos.moe.dragoncore.dr;
import eos.moe.dragoncore.ea;
import eos.moe.dragoncore.hs;
import eos.moe.dragoncore.ip;
import eos.moe.dragoncore.kq;
import eos.moe.dragoncore.nt;
import eos.moe.dragoncore.qd;
import eos.moe.dragoncore.yz;
import eos.moe.dragoncore.zu;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import net.minecraft.util.ResourceLocation;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class lu
extends kq {
    private static final kq c = new zu();
    private static final hs q = new nt(c);
    private final int b;
    private final String o;
    private final kq y;
    private final ResourceLocation k;
    private List<ip> ALLATORIxDEMO = new ArrayList<ip>();

    private /* synthetic */ lu(int a2, String a3, kq a4) {
        lu a5;
        a5.b = a2;
        a5.o = a3;
        a5.k = new ResourceLocation("dragoncore", "internal/" + a5.ALLATORIxDEMO());
        a5.y = a4;
    }

    private static /* synthetic */ kq ALLATORIxDEMO(kq a2, AnimationModel a3, int a4) {
        lu a5 = new lu(a4, "idle_to_" + a2.ALLATORIxDEMO(), a2);
        hs a7 = a2.ALLATORIxDEMO();
        a2.ALLATORIxDEMO(a6 -> {
            AnimationModelRenderer a7 = a3.getPiece((String)a6);
            if (a7 != null) {
                yz a8 = yz.ALLATORIxDEMO(0, new bax(0.0f, 0.0f, 0.0f));
                yz a9 = a7.ALLATORIxDEMO(a7, (String)a6, dp.o, a4);
                qd<yz, yz> a10 = qd.ALLATORIxDEMO(a8, a9);
                a8 = yz.ALLATORIxDEMO(0, a7.getOffsets());
                a9 = a7.ALLATORIxDEMO(a7, (String)a6, dp.y, a4);
                qd<yz, yz> a11 = qd.ALLATORIxDEMO(a8, a9);
                a8 = yz.ALLATORIxDEMO(0, a7.getScaleFactor());
                a9 = a7.ALLATORIxDEMO(a7, (String)a6, dp.k, a4);
                qd<yz, yz> a12 = qd.ALLATORIxDEMO(a8, a9);
                a5.ALLATORIxDEMO.add(new ip((String)a6, a10, a11, a12));
            }
        });
        return a5;
    }

    private static /* synthetic */ kq ALLATORIxDEMO(kq a2, AnimationModel a3, int a4, int a5) {
        lu a6 = new lu(a5, (a2 != null ? a2.ALLATORIxDEMO() : "idle") + "_to_idle", null);
        if (a2 != null) {
            hs a7 = a2.ALLATORIxDEMO();
            a6.ALLATORIxDEMO = a7.ALLATORIxDEMO(c, a3, a4, a5);
        }
        return a6;
    }

    public static kq ALLATORIxDEMO(kq a2, int a3, kq a4, AnimationModel a5, int a6) {
        if (a4 == null) {
            return lu.ALLATORIxDEMO(a2, a5, a2 != null ? a3 : 0, a6);
        }
        if (a2 == null) {
            return lu.ALLATORIxDEMO(a4, a5, a6);
        }
        return lu.ALLATORIxDEMO(a2, a4, a5, a3, a6);
    }

    public static kq ALLATORIxDEMO(kq a2, kq a3, int a4) {
        String a5 = a2 != null ? a2.ALLATORIxDEMO() : "idle";
        String a6 = a3 != null ? a3.ALLATORIxDEMO() : "idle";
        return new lu(a4, a5 + "_to_" + a6, a3);
    }

    private static /* synthetic */ kq ALLATORIxDEMO(kq a2, kq a3, AnimationModel a4, int a5, int a6) {
        hs a7 = a2.ALLATORIxDEMO();
        List<ip> a8 = a7.ALLATORIxDEMO(a3, a4, a5, a6);
        if (a8 == null) {
            return lu.ALLATORIxDEMO(a3, a4, a6);
        }
        lu a9 = new lu(a6, a2.ALLATORIxDEMO() + "_to_" + a3.ALLATORIxDEMO(), a3);
        a9.ALLATORIxDEMO = a8;
        return a9;
    }

    @Override
    public void ALLATORIxDEMO(AnimationEntityModel a2, ea a3, int a4) {
        lu a6;
        AnimationModel a7 = a2.getBaseModel();
        if (a6.ALLATORIxDEMO != null && a4 <= a6.b) {
            a6.ALLATORIxDEMO.forEach(a5 -> {
                AnimationModelRenderer a6 = a7.getPiece(ip.ALLATORIxDEMO(a5));
                if (a6 != null) {
                    a6.setApplyAnimation(true);
                    a5.ALLATORIxDEMO(a6, a3, a4);
                }
            });
        }
    }

    @Override
    public void ALLATORIxDEMO(Consumer<String> a2) {
        lu a4;
        a4.ALLATORIxDEMO.forEach(a3 -> a2.accept(ip.ALLATORIxDEMO(a3)));
    }

    @Override
    public kq ALLATORIxDEMO() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean f() {
        return false;
    }

    @Override
    public boolean c() {
        return false;
    }

    @Override
    public float ALLATORIxDEMO() {
        return 1.0f;
    }

    @Override
    public boolean ALLATORIxDEMO() {
        return false;
    }

    @Override
    public hs ALLATORIxDEMO() {
        lu a2;
        return new dr(a2);
    }

    @Override
    public int ALLATORIxDEMO() {
        lu a2;
        return a2.b;
    }

    @Override
    public String ALLATORIxDEMO() {
        lu a2;
        return a2.o;
    }

    @Override
    public ResourceLocation ALLATORIxDEMO() {
        lu a2;
        return a2.k;
    }

    public kq c() {
        lu a2;
        return a2.y;
    }

    public static /* synthetic */ hs c() {
        return q;
    }

    public static /* synthetic */ List ALLATORIxDEMO(lu a2) {
        return a2.ALLATORIxDEMO;
    }
}

