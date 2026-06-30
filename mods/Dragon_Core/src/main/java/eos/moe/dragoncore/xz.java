/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.math.MathHelper
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.api.model.AnimationEntityModel;
import eos.moe.dragoncore.bs;
import eos.moe.dragoncore.do;
import eos.moe.dragoncore.fm;
import eos.moe.dragoncore.hka;
import eos.moe.dragoncore.hw;
import eos.moe.dragoncore.kq;
import eos.moe.dragoncore.nd;
import eos.moe.dragoncore.nh;
import eos.moe.dragoncore.oz;
import eos.moe.dragoncore.qv;
import eos.moe.dragoncore.qw;
import eos.moe.dragoncore.raa;
import eos.moe.dragoncore.ri;
import eos.moe.dragoncore.so;
import eos.moe.dragoncore.sr;
import eos.moe.dragoncore.ut;
import eos.moe.dragoncore.v;
import eos.moe.dragoncore.xf;
import eos.moe.dragoncore.xr;
import eos.moe.dragoncore.yr;
import eos.moe.dragoncore.yu;
import eos.moe.dragoncore.zw;
import java.util.AbstractMap;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.MathHelper;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class xz
extends qw {
    public String t;
    public UUID r;
    public Map<String, String> x;
    public Map<String, String> v;
    public Map<String, nh> m;
    public sr c;
    public Map<String, Double> q;
    public Map<String, Boolean> b;
    public yu o;
    public ut y;
    private Map<String, kq> k;
    private qv ALLATORIxDEMO;

    public xz(EntityLivingBase a2, ut a3, Map<String, kq> a4) {
        super(null, null);
        xz a5;
        a5.y = a3;
        a5.k = a4;
        a5.v = new HashMap<String, String>();
        a5.x = new HashMap<String, String>();
        a5.m = new HashMap<String, nh>();
        a5.o = a2 instanceof EntityPlayer ? new oz((EntityPlayer)a2) : new yu<EntityLivingBase>(a2);
        a5.q = new HashMap<String, Double>();
        a5.b = new HashMap<String, Boolean>();
        a5.ALLATORIxDEMO = hw.ALLATORIxDEMO();
        a5.ALLATORIxDEMO.ALLATORIxDEMO("\u65b9\u6cd5", "func", yr.ALLATORIxDEMO(a5));
    }

    public xz(zw a2, zw a3, Map<String, kq> a4) {
        super(a2, a3);
        xz a5;
        a5.k = a4;
    }

    @Override
    public void ALLATORIxDEMO(AnimationEntityModel a2, so a3, xr a4, long a5) {
        kq a6 = a4.ALLATORIxDEMO();
        a6.ALLATORIxDEMO(a2, a3, do.ALLATORIxDEMO(a4.ALLATORIxDEMO(a5), 0, a4.ALLATORIxDEMO().ALLATORIxDEMO()));
    }

    @Override
    public void applyAnimation(AnimationEntityModel a2) {
        xz a3;
        a3.applyAnimation(a2, 1.0f);
    }

    @Override
    public void applyAnimation(AnimationEntityModel a2, float a3) {
        boolean a4;
        xz a5;
        a5.ALLATORIxDEMO("preRender", new String[0]);
        raa.x.ALLATORIxDEMO();
        hka.ALLATORIxDEMO((Entity)a5.ALLATORIxDEMO(), a5);
        EntityLivingBase a6 = a5.ALLATORIxDEMO();
        float a7 = ri.ALLATORIxDEMO(a6.field_70760_ar, a6.field_70761_aq, a3);
        float a8 = ri.ALLATORIxDEMO(a6.field_70758_at, a6.field_70759_as, a3);
        float a9 = a8 - a7;
        boolean bl2 = a4 = a6.func_184218_aH() && a6.func_184187_bx() != null && a6.func_184187_bx().shouldRiderSit();
        if (a4 && a6.func_184187_bx() instanceof EntityLivingBase) {
            EntityLivingBase a10 = (EntityLivingBase)a6.func_184187_bx();
            a7 = ri.ALLATORIxDEMO(a10.field_70760_ar, a10.field_70761_aq, a3);
            a9 = a8 - a7;
            float a11 = MathHelper.func_76142_g((float)a9);
            if (a11 < -85.0f) {
                a11 = -85.0f;
            }
            if (a11 >= 85.0f) {
                a11 = 85.0f;
            }
            a7 = a8 - a11;
            if (a11 * a11 > 2500.0f) {
                a7 += a11 * 0.2f;
            }
            a9 = a8 - a7;
        }
        float a12 = a6.field_70127_C + (a6.field_70125_A - a6.field_70127_C) * a3;
        nd a13 = raa.x;
        a13.ALLATORIxDEMO("query.yaw", a9);
        if (a6.func_184599_cB() > 4) {
            a13.ALLATORIxDEMO("query.pitch", -45.0);
        } else {
            a13.ALLATORIxDEMO("query.pitch", a12);
        }
        a5.ALLATORIxDEMO(a2);
    }

    @Override
    public boolean needPlaySwordTrail() {
        xz a2;
        if (!a2.m.containsKey("swordTrail")) {
            return a2.isOnPlayAnimation();
        }
        return a2.m.get("swordTrail").c();
    }

    public String c() {
        xz a2;
        if (!a2.m.containsKey("texture")) {
            return null;
        }
        v a3 = a2.m.get("texture").ALLATORIxDEMO();
        return a3 instanceof xf ? a3.c() : null;
    }

    public String ALLATORIxDEMO() {
        xz a2;
        if (!a2.m.containsKey("glowTexture")) {
            return null;
        }
        v a3 = a2.m.get("glowTexture").ALLATORIxDEMO();
        return a3 instanceof xf ? a3.c() : null;
    }

    @Override
    public void ALLATORIxDEMO(AnimationEntityModel a2, so a3, xr a4) {
        xz a5;
        super.ALLATORIxDEMO(a2, a3, a4);
        if (a5.m != null) {
            a5.ALLATORIxDEMO(bs.r, a4.ALLATORIxDEMO().ALLATORIxDEMO());
        }
    }

    public nh ALLATORIxDEMO(String a2) {
        xz a3;
        if (a2 == null) {
            return null;
        }
        if (!a2.contains("\u53d8\u91cf.") && !a2.contains("\u65b9\u6cd5.")) {
            a2 = "'" + a2 + "'";
        }
        return hw.ALLATORIxDEMO(a3.ALLATORIxDEMO, a2);
    }

    public void c(String a2, String a3) {
        xz a4;
        nh a5 = hw.ALLATORIxDEMO(a4.ALLATORIxDEMO, a3);
        a4.m.put(a2, a5);
    }

    public String ALLATORIxDEMO(String a2, String a3) {
        xz a4;
        return a4.x.getOrDefault(a2, a3);
    }

    public void ALLATORIxDEMO(String a2, String a3) {
        xz a4;
        a4.x.put(a2, a3);
    }

    public Map<String, kq> x() {
        xz a2;
        return a2.k;
    }

    public Map<String, String> f() {
        xz a2;
        return a2.v;
    }

    public boolean ALLATORIxDEMO(bs a2, String ... a3) {
        xz a4;
        return a4.ALLATORIxDEMO(a2.ALLATORIxDEMO(), a3);
    }

    public boolean ALLATORIxDEMO(String a2, String ... a3) {
        xz a4;
        if (a4.ALLATORIxDEMO().field_70128_L) {
            return false;
        }
        try {
            nh a5 = a4.m.get(a2);
            if (a5 != null) {
                if (a3.length == 0) {
                    return fm.ALLATORIxDEMO(a5, new v[0]).ALLATORIxDEMO();
                }
                v[] a6 = (xf[])Arrays.stream(a3).map(xf::new).toArray(xf[]::new);
                v a7 = fm.ALLATORIxDEMO(a5, a6);
                return a7.ALLATORIxDEMO();
            }
        }
        catch (Exception a8) {
            a8.printStackTrace();
        }
        return false;
    }

    public void c(String a2) {
        xz a3;
        nh a4 = hw.ALLATORIxDEMO(a3.ALLATORIxDEMO, a2);
        fm.ALLATORIxDEMO(a4, new v[0]);
    }

    public EntityLivingBase ALLATORIxDEMO() {
        xz a2;
        return a2.o.ALLATORIxDEMO();
    }

    public Map.Entry<String, kq> ALLATORIxDEMO(String a2) {
        xz a3;
        kq a4 = a3.k.get(a2);
        if (a4 == null) {
            for (Map.Entry<String, kq> a5 : a3.k.entrySet()) {
                if (!a5.getKey().contains(a2)) continue;
                return a5;
            }
            return new AbstractMap.SimpleEntry<String, Object>(a2, null);
        }
        return new AbstractMap.SimpleEntry<String, kq>(a2, a4);
    }
}

