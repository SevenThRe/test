/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.util.ResourceLocation
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.bt;
import eos.moe.dragoncore.cp;
import eos.moe.dragoncore.dt;
import eos.moe.dragoncore.hka;
import eos.moe.dragoncore.hr;
import eos.moe.dragoncore.i;
import eos.moe.dragoncore.oz;
import eos.moe.dragoncore.so;
import eos.moe.dragoncore.v;
import eos.moe.dragoncore.wk;
import eos.moe.dragoncore.xf;
import eos.moe.dragoncore.xk;
import eos.moe.dragoncore.xr;
import eos.moe.dragoncore.xz;
import eos.moe.dragoncore.yu;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class qs {
    public qs() {
        qs a2;
    }

    @i(f={"\u7ed1\u5b9a\u8d34\u56fe"})
    public static void ALLATORIxDEMO(bt a2) {
        v a3 = a2.ALLATORIxDEMO(0);
        if (a3 instanceof xf && !a3.c().isEmpty()) {
            hka.ALLATORIxDEMO(new ResourceLocation("dragoncore", "models/entities/" + a3.c()));
        }
        if (a2.ALLATORIxDEMO() >= 5) {
            GlStateManager.color((float)((float)a2.ALLATORIxDEMO(1) / 255.0f), (float)((float)a2.ALLATORIxDEMO(2) / 255.0f), (float)((float)a2.ALLATORIxDEMO(3) / 255.0f), (float)((float)a2.ALLATORIxDEMO(4) / 255.0f));
        }
    }

    @i(f={"\u53d6\u52a8\u753b\u5df2\u64ad\u653e\u65f6\u957f"})
    public static int c(xz a2, String a3) {
        String a4 = dt.k.getAnimationLayer(a2, a3).ALLATORIxDEMO();
        so a5 = a2.ALLATORIxDEMO(a4);
        xr a6 = a5.ALLATORIxDEMO();
        if (a6 != null && a6.ALLATORIxDEMO() != null && (a6.ALLATORIxDEMO().ALLATORIxDEMO() || !a6.ALLATORIxDEMO(System.currentTimeMillis()))) {
            if (a6 instanceof hr) {
                return -2;
            }
            if (a6.ALLATORIxDEMO().ALLATORIxDEMO().contains(a3)) {
                return a6.c();
            }
        }
        return -1;
    }

    @i(f={"\u64ad\u653e\u52a8\u753b"})
    public static void ALLATORIxDEMO(xz a2, String a3, v a4, float a5) {
        int a6 = a4 == wk.k ? cp.y : (int)a4.ALLATORIxDEMO();
        dt.k.startAnimation(a2, a3, a6, a5);
    }

    @i(f={"\u8bbe\u7f6e\u9aa8\u9abc\u53ef\u89c6"})
    public static void ALLATORIxDEMO(xz a2, String a3, boolean a4) {
        a2.b.put(a3, a4);
    }

    @i(f={"\u505c\u6b62\u52a8\u753b"})
    public static void c(xz a2, String a3, v a4) {
        int a5 = a4 == wk.k ? cp.y : (int)a4.ALLATORIxDEMO();
        dt.k.stopAnimation(a2, a3, a5);
    }

    @i(f={"\u52a8\u753b\u662f\u5426\u64ad\u653e\u4e2d"})
    public static boolean f(xz a2, String a3) {
        return dt.k.isPlayingAnimation(a2, a3);
    }

    @i(f={"\u53d6\u52a8\u753b\u6240\u5728\u52a8\u753b\u5c42"})
    public static String ALLATORIxDEMO(xz a2, String a3) {
        return dt.k.getAnimationLayer(a2, a3).ALLATORIxDEMO();
    }

    @i(f={"\u79fb\u9664\u6b7b\u4ea1\u8ba1\u6570"})
    public static void f(xz a2) {
        a2.ALLATORIxDEMO().deathTime = 0;
    }

    @i(f={"\u505c\u6b62\u52a8\u753b\u5c42"})
    public static void ALLATORIxDEMO(xz a2, String a3, v a4) {
        int a5 = a4 == wk.k ? cp.y : (int)a4.ALLATORIxDEMO();
        a2.ALLATORIxDEMO(a3, a5);
    }

    @i(f={"\u52a8\u753b\u5c42\u662f\u5426\u64ad\u653e\u4e2d"})
    public static boolean c(xz a2, String a3) {
        so a4 = a2.ALLATORIxDEMO().get(a3);
        return a4 != null && a4.ALLATORIxDEMO() != null;
    }

    @i(f={"\u662f\u5426\u6709\u52a8\u753b"})
    public static boolean ALLATORIxDEMO(xz a2, String a3) {
        return a2.ALLATORIxDEMO(a3).getValue() != null;
    }

    @i(f={"\u8bbe\u7f6e\u52a8\u753b\u53d8\u91cf"})
    public static void ALLATORIxDEMO(xz a2, String a3, double a4) {
        a2.q.put(a3, a4);
    }

    @i(f={"\u53d6\u52a8\u753b\u5c42\u8fd0\u884c\u65f6\u95f4"})
    public int ALLATORIxDEMO(xz a2, String a3) {
        xr a4;
        so a5 = a2.ALLATORIxDEMO().get(a3);
        if (a5 != null && (a4 = a5.ALLATORIxDEMO()) != null) {
            return a4.c();
        }
        return 0;
    }

    @i(f={"\u79fb\u9664\u5b9e\u4f53"})
    public static void c(xz a2) {
        Minecraft.getMinecraft().world.removeEntity((Entity)a2.ALLATORIxDEMO());
    }

    @i(f={"\u53d6\u5b9e\u4f53UUID"})
    public static String c(xz a2) {
        return a2.ALLATORIxDEMO().getUniqueID().toString();
    }

    @i(f={"\u53d6\u5b9e\u4f53\u540d"})
    public static String ALLATORIxDEMO(xz a2) {
        return a2.t;
    }

    @i(f={"\u53d6yaw"})
    public static double s(xz a2) {
        return a2.ALLATORIxDEMO().rotationYawHead;
    }

    @i(f={"\u53d6pitch"})
    public static double w(xz a2) {
        return a2.ALLATORIxDEMO().rotationPitch;
    }

    @i(f={"\u53d6\u8840\u91cf"})
    public static double z(xz a2) {
        return a2.ALLATORIxDEMO().getHealth();
    }

    @i(f={"\u53d6\u6700\u5927\u8840\u91cf"})
    public static double k(xz a2) {
        return a2.ALLATORIxDEMO().getMaxHealth();
    }

    @i(f={"\u53d6\u8840\u91cf\u6bd4\u4f8b"})
    public static double d(xz a2) {
        EntityLivingBase a3 = a2.ALLATORIxDEMO();
        return a3.getHealth() / a3.getMaxHealth();
    }

    @i(f={"\u53d6\u9ad8\u5ea6"})
    public static double x(xz a2) {
        return a2.ALLATORIxDEMO().height;
    }

    @i(f={"\u662f\u5426\u9a91\u4e58"})
    public static boolean i(xz a2) {
        return a2.ALLATORIxDEMO().isRiding();
    }

    @i(f={"\u9a91\u4e58\u751f\u7269\u662f\u5426\u4e3a\u751f\u547d\u4f53"})
    public static boolean n(xz a2) {
        EntityLivingBase a3 = a2.ALLATORIxDEMO();
        return a3.getRidingEntity() instanceof EntityLivingBase;
    }

    @i(f={"\u8bbe\u7f6e\u98de\u884c\u72b6\u6001"})
    public static void ALLATORIxDEMO(xz a2, boolean a3) {
        if (a2.o instanceof oz) {
            oz a4 = (oz)a2.o;
            a4.x(a3);
        }
    }

    @i(f={"\u79fb\u9664\u98de\u884c\u72b6\u6001"})
    public static void ALLATORIxDEMO(xz a2) {
        if (a2.o instanceof oz) {
            oz a3 = (oz)a2.o;
            a3.z();
        }
    }

    @i(f={"\u662f\u5426\u9798\u7fc5\u98de\u884c\u4e2d"})
    public static boolean y(xz a2) {
        return a2.ALLATORIxDEMO().getTicksElytraFlying() > 4;
    }

    @i(f={"\u662f\u5426\u6500\u722c\u4e2d"})
    public static boolean h(xz a2) {
        return a2.o.s();
    }

    @i(f={"\u662f\u5426\u79fb\u52a8\u4e2d"})
    public static boolean s(xz a2) {
        return !qs.f(a2);
    }

    @i(f={"\u662f\u5426\u5728\u6c34\u4e2d"})
    public static boolean w(xz a2) {
        return a2.ALLATORIxDEMO().isInWater();
    }

    @i(f={"\u662f\u5426\u5728\u6c34\u4e0b"})
    public static boolean z(xz a2) {
        return (boolean)a2.o.ALLATORIxDEMO();
    }

    @i(f={"\u662f\u5426\u8df3\u8dc3\u4e2d"})
    public static boolean k(xz a2) {
        return a2.ALLATORIxDEMO("jump", "false").equals("true");
    }

    @i(f={"\u662f\u5426\u5728\u5730\u9762"})
    public static boolean d(xz a2) {
        yu a3 = a2.o;
        return a3.x();
    }

    @i(f={"\u662f\u5426\u4e0b\u5760\u4e2d"})
    public static boolean x(xz a2) {
        yu a3 = a2.o;
        return a3.d() > 6.0f;
    }

    @i(f={"\u662f\u5426\u9759\u6b62\u4e2d"})
    public static boolean f(xz a2) {
        yu a3 = a2.o;
        return a3.f();
    }

    @i(f={"\u662f\u5426\u5954\u8dd1\u4e2d"})
    public static boolean c(xz a2) {
        yu a3 = a2.o;
        return a3.ALLATORIxDEMO().isSprinting();
    }

    @i(f={"\u662f\u5426\u8e72\u4e0b"})
    public static boolean ALLATORIxDEMO(xz a2) {
        return a2.ALLATORIxDEMO().isSneaking();
    }

    @i(f={"\u53d6\u6b63\u5411\u52a8\u91cf"})
    public static double f(xz a2) {
        return a2.o.k();
    }

    @i(f={"\u53d6\u6a2a\u5411\u52a8\u91cf"})
    public static double c(xz a2) {
        return a2.o.d();
    }

    @i(f={"motionY"})
    public static double ALLATORIxDEMO(xz a2) {
        return a2.o.y();
    }

    @i(f={"\u53d6\u4e3b\u624b\u7269\u54c1"})
    public static v c(xz a2) {
        return new xk(a2.ALLATORIxDEMO().getHeldItemMainhand());
    }

    @i(f={"\u53d6\u526f\u624b\u7269\u54c1"})
    public static v ALLATORIxDEMO(xz a2) {
        return new xk(a2.ALLATORIxDEMO().getHeldItemOffhand());
    }

    @i(f={"\u53d6\u9ed8\u8ba4\u8fc7\u6e21\u65f6\u95f4"})
    public static int ALLATORIxDEMO() {
        return cp.y;
    }
}

