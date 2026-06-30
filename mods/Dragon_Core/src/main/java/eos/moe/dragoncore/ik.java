/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.gg;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public final class ik {
    public static final ik b = new ik(0.0f, 0.0f, 0.0f, 1.0f);
    private float o;
    private float y;
    private float k;
    private float ALLATORIxDEMO;

    public ik(float a2, float a3, float a4, float a5) {
        ik a6;
        a6.o = a2;
        a6.y = a3;
        a6.k = a4;
        a6.ALLATORIxDEMO = a5;
    }

    public ik(gg a2, float a3, boolean a4) {
        ik a5;
        if (a4) {
            a3 *= (float)Math.PI / 180;
        }
        float a6 = ik.c(a3 / 2.0f);
        a5.o = a2.f() * a6;
        a5.y = a2.c() * a6;
        a5.k = a2.ALLATORIxDEMO() * a6;
        a5.ALLATORIxDEMO = ik.f(a3 / 2.0f);
    }

    public ik(float a2, float a3, float a4, boolean a5) {
        ik a6;
        if (a5) {
            a2 *= (float)Math.PI / 180;
            a3 *= (float)Math.PI / 180;
            a4 *= (float)Math.PI / 180;
        }
        float a7 = ik.c(0.5f * a2);
        float a8 = ik.f(0.5f * a2);
        float a9 = ik.c(0.5f * a3);
        float a10 = ik.f(0.5f * a3);
        float a11 = ik.c(0.5f * a4);
        float a12 = ik.f(0.5f * a4);
        a6.o = a7 * a10 * a12 + a8 * a9 * a11;
        a6.y = a8 * a9 * a12 - a7 * a10 * a11;
        a6.k = a7 * a9 * a12 + a8 * a10 * a11;
        a6.ALLATORIxDEMO = a8 * a10 * a12 - a7 * a9 * a11;
    }

    public ik(ik a2) {
        ik a3;
        a3.o = a2.o;
        a3.y = a2.y;
        a3.k = a2.k;
        a3.ALLATORIxDEMO = a2.ALLATORIxDEMO;
    }

    public boolean equals(Object a2) {
        ik a3;
        if (a3 == a2) {
            return true;
        }
        if (a2 != null && a3.getClass() == a2.getClass()) {
            ik a4 = (ik)a2;
            if (Float.compare(a4.o, a3.o) != 0) {
                return false;
            }
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
        ik a2;
        int a3 = Float.floatToIntBits(a2.o);
        a3 = 31 * a3 + Float.floatToIntBits(a2.y);
        a3 = 31 * a3 + Float.floatToIntBits(a2.k);
        a3 = 31 * a3 + Float.floatToIntBits(a2.ALLATORIxDEMO);
        return a3;
    }

    public String toString() {
        ik a2;
        StringBuilder a3 = new StringBuilder();
        a3.append("Quaternion[").append(a2.ALLATORIxDEMO()).append(" + ");
        a3.append(a2.x()).append("i + ");
        a3.append(a2.f()).append("j + ");
        a3.append(a2.c()).append("k]");
        return a3.toString();
    }

    public float x() {
        ik a2;
        return a2.o;
    }

    public float f() {
        ik a2;
        return a2.y;
    }

    public float c() {
        ik a2;
        return a2.k;
    }

    public float ALLATORIxDEMO() {
        ik a2;
        return a2.ALLATORIxDEMO;
    }

    public void ALLATORIxDEMO(ik a2) {
        ik a3;
        float a4 = a3.x();
        float a5 = a3.f();
        float a6 = a3.c();
        float a7 = a3.ALLATORIxDEMO();
        float a8 = a2.x();
        float a9 = a2.f();
        float a10 = a2.c();
        float a11 = a2.ALLATORIxDEMO();
        a3.o = a7 * a8 + a4 * a11 + a5 * a10 - a6 * a9;
        a3.y = a7 * a9 - a4 * a10 + a5 * a11 + a6 * a8;
        a3.k = a7 * a10 + a4 * a9 - a5 * a8 + a6 * a11;
        a3.ALLATORIxDEMO = a7 * a11 - a4 * a8 - a5 * a9 - a6 * a10;
    }

    public void ALLATORIxDEMO(float a2) {
        a.o *= a2;
        a.y *= a2;
        a.k *= a2;
        a.ALLATORIxDEMO *= a2;
    }

    public void c() {
        ik a2;
        a2.o = -a2.o;
        a2.y = -a2.y;
        a2.k = -a2.k;
    }

    public void ALLATORIxDEMO(float a2, float a3, float a4, float a5) {
        a.o = a2;
        a.y = a3;
        a.k = a4;
        a.ALLATORIxDEMO = a5;
    }

    private static /* synthetic */ float f(float a2) {
        return (float)Math.cos(a2);
    }

    private static /* synthetic */ float c(float a2) {
        return (float)Math.sin(a2);
    }

    public static float ALLATORIxDEMO(float a2) {
        float a3 = 0.5f * a2;
        int a4 = Float.floatToIntBits(a2);
        a4 = 1597463007 - (a4 >> 1);
        a2 = Float.intBitsToFloat(a4);
        a2 *= 1.5f - a3 * a2 * a2;
        return a2;
    }

    public void ALLATORIxDEMO() {
        ik a2;
        float a3 = a2.x() * a2.x() + a2.f() * a2.f() + a2.c() * a2.c() + a2.ALLATORIxDEMO() * a2.ALLATORIxDEMO();
        if (a3 > 1.0E-6f) {
            float a4 = ik.ALLATORIxDEMO(a3);
            a2.o *= a4;
            a2.y *= a4;
            a2.k *= a4;
            a2.ALLATORIxDEMO *= a4;
        } else {
            a2.o = 0.0f;
            a2.y = 0.0f;
            a2.k = 0.0f;
            a2.ALLATORIxDEMO = 0.0f;
        }
    }

    public ik ALLATORIxDEMO() {
        ik a2;
        return new ik(a2);
    }
}

