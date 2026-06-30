/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.BlockStaticLiquid
 *  net.minecraft.block.state.IBlockState
 *  net.minecraft.client.Minecraft
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.util.math.Vec3d
 */
package eos.moe.dragoncore;

import java.util.List;
import net.minecraft.block.BlockStaticLiquid;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public abstract class gt<E extends Entity> {
    public int z;
    public E s;
    public double g;
    public double t;
    public double r;
    public double x;
    public double v;
    public double m;
    public double c;
    public double q;
    public double b;
    public boolean o = true;
    public Boolean y = null;
    public Boolean k = null;
    private static final float ALLATORIxDEMO = 30.0f;

    public void ALLATORIxDEMO(E a2) {
        a.s = a2;
    }

    public gt(E a2) {
        gt a3;
        a3.s = a2;
        if (a3.s != null) {
            a3.z = a2.getEntityId();
            a3.g = ((Entity)a3.s).posX;
            a3.t = ((Entity)a3.s).posY;
            a3.r = ((Entity)a3.s).posZ;
        }
        a3.x = 0.0;
        a3.c = 0.0;
        a3.v = 1.0;
        a3.q = 1.0;
        a3.m = 0.0;
        a3.b = 0.0;
    }

    public void c(boolean a2) {
        a.y = a2;
    }

    public void f() {
        a.y = null;
    }

    public void ALLATORIxDEMO(boolean a2) {
        a.k = a2;
    }

    public void c() {
        a.k = null;
    }

    public boolean k() {
        gt a2;
        if (a2.y != null) {
            return a2.y;
        }
        List a3 = ((Entity)a2.s).world.getCollisionBoxes(a2.s, a2.s.getEntityBoundingBox().offset(0.0, (double)-0.025f, 0.0));
        return a3.size() > 0;
    }

    public boolean d() {
        gt a2;
        List a3 = ((Entity)a2.s).world.getCollisionBoxes(a2.s, a2.s.getEntityBoundingBox().offset(a2.c, 0.0, a2.b));
        return a3.size() > 0;
    }

    public double e() {
        gt a2;
        return a2.g;
    }

    public double j() {
        gt a2;
        return a2.t;
    }

    public double i() {
        gt a2;
        return a2.r;
    }

    public double n() {
        gt a2;
        return a2.c;
    }

    public double y() {
        gt a2;
        return a2.q;
    }

    public double h() {
        gt a2;
        return a2.b;
    }

    public double s() {
        gt a2;
        return a2.x;
    }

    public double w() {
        gt a2;
        return a2.v;
    }

    public double z() {
        gt a2;
        return a2.m;
    }

    public boolean x() {
        gt a2;
        return a2.o;
    }

    public boolean f() {
        gt a2;
        double a3 = 0.0025;
        double a4 = a2.c * a2.c + a2.b * a2.b;
        return a2.k != null ? a2.k : a4 == 0.0;
    }

    public E ALLATORIxDEMO() {
        gt a2;
        return a2.s;
    }

    public float f() {
        gt a2;
        Vec3d a3 = a2.s.getLookVec();
        return (float)gt.ALLATORIxDEMO(a3.x, a3.z);
    }

    public static double ALLATORIxDEMO(double a2, double a3) {
        return Math.atan2(a2, a3) / Math.PI * 180.0;
    }

    private /* synthetic */ float c() {
        gt a2;
        return (float)gt.ALLATORIxDEMO(a2.c, a2.b);
    }

    public float ALLATORIxDEMO() {
        gt a2;
        if (a2.f()) {
            return 0.0f;
        }
        return a2.c() - a2.f();
    }

    public double k() {
        gt a2;
        if (a2.f()) {
            return 0.0;
        }
        Vec3d a3 = a2.s.getLookVec();
        Vec3d a4 = new Vec3d(a3.x, 0.0, a3.z).normalize();
        return a4.x * a2.c + a4.z * a2.b;
    }

    public double d() {
        gt a2;
        if (a2.f()) {
            return 0.0;
        }
        Vec3d a3 = a2.s.getLookVec().rotateYaw(-1.5707964f);
        Vec3d a4 = new Vec3d(a3.x, 0.0, a3.z).normalize();
        return a4.x * a2.c + a4.z * a2.b;
    }

    public boolean c() {
        gt a2;
        float a3 = a2.ALLATORIxDEMO();
        return a3 >= 30.0f && a3 <= 150.0f || a3 >= -150.0f && a3 <= -30.0f;
    }

    public boolean ALLATORIxDEMO() {
        gt a2;
        if (!a2.s.isInWater()) {
            return false;
        }
        int a3 = MathHelper.floor((double)((Entity)a2.s).posX);
        int a4 = MathHelper.floor((double)(((Entity)a2.s).posY + 2.0));
        int a5 = MathHelper.floor((double)((Entity)a2.s).posZ);
        IBlockState a6 = Minecraft.getMinecraft().world.getBlockState(new BlockPos(a3, a4, a5));
        return a6.getBlock() instanceof BlockStaticLiquid;
    }

    public double x() {
        gt a2;
        return Math.sqrt(a2.x * a2.x + a2.v * a2.v + a2.m * a2.m);
    }

    public double f() {
        gt a2;
        return Math.sqrt(a2.c * a2.c + a2.q * a2.q + a2.b * a2.b);
    }

    public double c() {
        gt a2;
        return Math.sqrt(a2.c * a2.c + a2.b * a2.b);
    }

    public double ALLATORIxDEMO() {
        gt a2;
        return Math.sqrt(a2.x * a2.x + a2.m * a2.m);
    }

    public void ALLATORIxDEMO() {
        gt a2;
        a2.x = a2.c;
        a2.v = a2.q;
        a2.m = a2.b;
        a2.c = ((Entity)a2.s).posX - a2.g;
        a2.q = ((Entity)a2.s).posY - a2.t;
        a2.b = ((Entity)a2.s).posZ - a2.r;
        a2.g = ((Entity)a2.s).posX;
        a2.t = ((Entity)a2.s).posY;
        a2.r = ((Entity)a2.s).posZ;
    }
}

