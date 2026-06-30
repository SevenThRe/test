/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  it.unimi.dsi.fastutil.floats.Float2FloatFunction
 *  net.minecraft.client.renderer.Vector3d
 *  net.minecraft.util.math.MathHelper
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.fj;
import eos.moe.dragoncore.ik;
import it.unimi.dsi.fastutil.floats.Float2FloatFunction;
import net.minecraft.client.renderer.Vector3d;
import net.minecraft.util.math.MathHelper;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public final class gg {
    public static gg v = new gg(-1.0f, 0.0f, 0.0f);
    public static gg m = new gg(1.0f, 0.0f, 0.0f);
    public static gg c = new gg(0.0f, -1.0f, 0.0f);
    public static gg q = new gg(0.0f, 1.0f, 0.0f);
    public static gg b = new gg(0.0f, 0.0f, -1.0f);
    public static gg o = new gg(0.0f, 0.0f, 1.0f);
    private float y;
    private float k;
    private float ALLATORIxDEMO;

    public gg() {
        gg a2;
    }

    public gg(float a2, float a3, float a4) {
        gg a5;
        a5.y = a2;
        a5.k = a3;
        a5.ALLATORIxDEMO = a4;
    }

    public gg(Vector3d a2) {
        a3((float)a2.x, (float)a2.y, (float)a2.z);
        gg a3;
    }

    public boolean equals(Object a2) {
        gg a3;
        if (a3 == a2) {
            return true;
        }
        if (a2 != null && a3.getClass() == a2.getClass()) {
            gg a4 = (gg)a2;
            if (Float.compare(a4.y, a3.y) != 0) {
                return false;
            }
            if (Float.compare(a4.k, a3.k) != 0) {
                return false;
            }
            return Float.compare(a4.ALLATORIxDEMO, a3.ALLATORIxDEMO) == 0;
        }
        return false;
    }

    public int hashCode() {
        gg a2;
        int a3 = Float.floatToIntBits(a2.y);
        a3 = 31 * a3 + Float.floatToIntBits(a2.k);
        return 31 * a3 + Float.floatToIntBits(a2.ALLATORIxDEMO);
    }

    public float f() {
        gg a2;
        return a2.y;
    }

    public float c() {
        gg a2;
        return a2.k;
    }

    public float ALLATORIxDEMO() {
        gg a2;
        return a2.ALLATORIxDEMO;
    }

    public void x(float a2) {
        a.y *= a2;
        a.k *= a2;
        a.ALLATORIxDEMO *= a2;
    }

    public void f(float a2, float a3, float a4) {
        a.y *= a2;
        a.k *= a3;
        a.ALLATORIxDEMO *= a4;
    }

    public void ALLATORIxDEMO(float a2, float a3) {
        gg a4;
        a4.y = MathHelper.clamp((float)a4.y, (float)a2, (float)a3);
        a4.k = MathHelper.clamp((float)a4.k, (float)a2, (float)a3);
        a4.ALLATORIxDEMO = MathHelper.clamp((float)a4.ALLATORIxDEMO, (float)a2, (float)a3);
    }

    public void c(float a2, float a3, float a4) {
        a.y = a2;
        a.k = a3;
        a.ALLATORIxDEMO = a4;
    }

    public void ALLATORIxDEMO(float a2, float a3, float a4) {
        a.y += a2;
        a.k += a3;
        a.ALLATORIxDEMO += a4;
    }

    public void f(gg a2) {
        a.y += a2.y;
        a.k += a2.k;
        a.ALLATORIxDEMO += a2.ALLATORIxDEMO;
    }

    public void c(gg a2) {
        a.y -= a2.y;
        a.k -= a2.k;
        a.ALLATORIxDEMO -= a2.ALLATORIxDEMO;
    }

    public float ALLATORIxDEMO(gg a2) {
        gg a3;
        return a3.y * a2.y + a3.k * a2.k + a3.ALLATORIxDEMO * a2.ALLATORIxDEMO;
    }

    public boolean ALLATORIxDEMO() {
        gg a2;
        float a3 = a2.y * a2.y + a2.k * a2.k + a2.ALLATORIxDEMO * a2.ALLATORIxDEMO;
        if ((double)a3 < 1.0E-5) {
            return false;
        }
        float a4 = ik.ALLATORIxDEMO(a3);
        a2.y *= a4;
        a2.k *= a4;
        a2.ALLATORIxDEMO *= a4;
        return true;
    }

    public void ALLATORIxDEMO(gg a2) {
        gg a3;
        float a4 = a3.y;
        float a5 = a3.k;
        float a6 = a3.ALLATORIxDEMO;
        float a7 = a2.f();
        float a8 = a2.c();
        float a9 = a2.ALLATORIxDEMO();
        a3.y = a5 * a9 - a6 * a8;
        a3.k = a6 * a7 - a4 * a9;
        a3.ALLATORIxDEMO = a4 * a8 - a5 * a7;
    }

    public void ALLATORIxDEMO(fj a2) {
        gg a3;
        float a4 = a3.y;
        float a5 = a3.k;
        float a6 = a3.ALLATORIxDEMO;
        a3.y = a2.v * a4 + a2.m * a5 + a2.c * a6;
        a3.k = a2.q * a4 + a2.b * a5 + a2.o * a6;
        a3.ALLATORIxDEMO = a2.y * a4 + a2.k * a5 + a2.ALLATORIxDEMO * a6;
    }

    public void ALLATORIxDEMO(ik a2) {
        gg a3;
        ik a4 = new ik(a2);
        a4.ALLATORIxDEMO(new ik(a3.f(), a3.c(), a3.ALLATORIxDEMO(), 0.0f));
        ik a5 = new ik(a2);
        a5.c();
        a4.ALLATORIxDEMO(a5);
        a3.c(a4.x(), a4.f(), a4.c());
    }

    public void ALLATORIxDEMO(gg a2, float a3) {
        gg a4;
        float a5 = 1.0f - a3;
        a4.y = a4.y * a5 + a2.y * a3;
        a4.k = a4.k * a5 + a2.k * a3;
        a4.ALLATORIxDEMO = a4.ALLATORIxDEMO * a5 + a2.ALLATORIxDEMO * a3;
    }

    public ik c(float a2) {
        gg a3;
        return new ik(a3, a2, false);
    }

    public ik ALLATORIxDEMO(float a2) {
        gg a3;
        return new ik(a3, a2, true);
    }

    public gg ALLATORIxDEMO() {
        gg a2;
        return new gg(a2.y, a2.k, a2.ALLATORIxDEMO);
    }

    public void ALLATORIxDEMO(Float2FloatFunction a2) {
        gg a3;
        a3.y = a2.get(a3.y);
        a3.k = a2.get(a3.k);
        a3.ALLATORIxDEMO = a2.get(a3.ALLATORIxDEMO);
    }

    public String toString() {
        gg a2;
        return "[" + a2.y + ", " + a2.k + ", " + a2.ALLATORIxDEMO + "]";
    }

    public gg(float[] a2) {
        gg a3;
        a3.ALLATORIxDEMO(a2);
    }

    public void ALLATORIxDEMO(float[] a2) {
        a.y = a2[0];
        a.k = a2[1];
        a.ALLATORIxDEMO = a2[2];
    }

    public void f(float a2) {
        a.y = a2;
    }

    public void c(float a2) {
        a.k = a2;
    }

    public void ALLATORIxDEMO(float a2) {
        a.ALLATORIxDEMO = a2;
    }
}

