/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.Matrix4f
 *  org.apache.commons.lang3.tuple.Triple
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.gg;
import eos.moe.dragoncore.ik;
import eos.moe.dragoncore.qd;
import net.minecraft.client.renderer.Matrix4f;
import org.apache.commons.lang3.tuple.Triple;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public final class fj {
    private static final float g = 3.0f + 2.0f * (float)Math.sqrt(2.0);
    private static final float t = (float)Math.cos(0.39269908169872414);
    private static final float r = (float)Math.sin(0.39269908169872414);
    private static final float x = 1.0f / (float)Math.sqrt(2.0);
    public float v;
    public float m;
    public float c;
    public float q;
    public float b;
    public float o;
    public float y;
    public float k;
    public float ALLATORIxDEMO;

    public fj() {
        fj a2;
    }

    public fj(ik a2) {
        fj a3;
        float a4 = a2.x();
        float a5 = a2.f();
        float a6 = a2.c();
        float a7 = a2.ALLATORIxDEMO();
        float a8 = 2.0f * a4 * a4;
        float a9 = 2.0f * a5 * a5;
        float a10 = 2.0f * a6 * a6;
        a3.v = 1.0f - a9 - a10;
        a3.b = 1.0f - a10 - a8;
        a3.ALLATORIxDEMO = 1.0f - a8 - a9;
        float a11 = a4 * a5;
        float a12 = a5 * a6;
        float a13 = a6 * a4;
        float a14 = a4 * a7;
        float a15 = a5 * a7;
        float a16 = a6 * a7;
        a3.q = 2.0f * (a11 + a16);
        a3.m = 2.0f * (a11 - a16);
        a3.y = 2.0f * (a13 - a15);
        a3.c = 2.0f * (a13 + a15);
        a3.k = 2.0f * (a12 + a14);
        a3.o = 2.0f * (a12 - a14);
    }

    public static fj ALLATORIxDEMO(float a2, float a3, float a4) {
        fj a5 = new fj();
        a5.v = a2;
        a5.b = a3;
        a5.ALLATORIxDEMO = a4;
        return a5;
    }

    public fj(Matrix4f a2) {
        fj a3;
        a3.v = a2.m00;
        a3.m = a2.m01;
        a3.c = a2.m02;
        a3.q = a2.m10;
        a3.b = a2.m11;
        a3.o = a2.m12;
        a3.y = a2.m20;
        a3.k = a2.m21;
        a3.ALLATORIxDEMO = a2.m22;
    }

    public fj(fj a2) {
        fj a3;
        a3.v = a2.v;
        a3.m = a2.m;
        a3.c = a2.c;
        a3.q = a2.q;
        a3.b = a2.b;
        a3.o = a2.o;
        a3.y = a2.y;
        a3.k = a2.k;
        a3.ALLATORIxDEMO = a2.ALLATORIxDEMO;
    }

    private static /* synthetic */ qd<Float, Float> ALLATORIxDEMO(float a2, float a3, float a4) {
        float a5 = 2.0f * (a2 - a4);
        if (g * a3 * a3 < a5 * a5) {
            float a6 = ik.ALLATORIxDEMO(a3 * a3 + a5 * a5);
            return qd.ALLATORIxDEMO(Float.valueOf(a6 * a3), Float.valueOf(a6 * a5));
        }
        return qd.ALLATORIxDEMO(Float.valueOf(r), Float.valueOf(t));
    }

    private static /* synthetic */ qd<Float, Float> ALLATORIxDEMO(float a2, float a3) {
        float a4;
        float a5 = (float)Math.hypot(a2, a3);
        float a6 = a5 > 1.0E-6f ? a3 : 0.0f;
        float a7 = Math.abs(a2) + Math.max(a5, 1.0E-6f);
        if (a2 < 0.0f) {
            a4 = a6;
            a6 = a7;
            a7 = a4;
        }
        a4 = ik.ALLATORIxDEMO(a7 * a7 + a6 * a6);
        return qd.ALLATORIxDEMO(Float.valueOf(a6 *= a4), Float.valueOf(a7 *= a4));
    }

    private static /* synthetic */ ik ALLATORIxDEMO(fj a2) {
        float a3;
        float a4;
        float a5;
        ik a6;
        Float a7;
        Float a8;
        qd<Float, Float> a9;
        fj a10 = new fj();
        ik a11 = ik.b.ALLATORIxDEMO();
        if (a2.m * a2.m + a2.q * a2.q > 1.0E-6f) {
            a9 = fj.ALLATORIxDEMO(a2.v, 0.5f * (a2.m + a2.q), a2.b);
            a8 = a9.c();
            a7 = a9.ALLATORIxDEMO();
            a6 = new ik(0.0f, 0.0f, a8.floatValue(), a7.floatValue());
            a5 = a7.floatValue() * a7.floatValue() - a8.floatValue() * a8.floatValue();
            a4 = -2.0f * a8.floatValue() * a7.floatValue();
            a3 = a7.floatValue() * a7.floatValue() + a8.floatValue() * a8.floatValue();
            a11.ALLATORIxDEMO(a6);
            a10.ALLATORIxDEMO();
            a10.v = a5;
            a10.b = a5;
            a10.q = -a4;
            a10.m = a4;
            a10.ALLATORIxDEMO = a3;
            a2.c(a10);
            a10.c();
            a10.c(a2);
            a2.f(a10);
        }
        if (a2.c * a2.c + a2.y * a2.y > 1.0E-6f) {
            a9 = fj.ALLATORIxDEMO(a2.v, 0.5f * (a2.c + a2.y), a2.ALLATORIxDEMO);
            float a12 = -a9.c().floatValue();
            a7 = a9.ALLATORIxDEMO();
            a6 = new ik(0.0f, a12, 0.0f, a7.floatValue());
            a5 = a7.floatValue() * a7.floatValue() - a12 * a12;
            a4 = -2.0f * a12 * a7.floatValue();
            a3 = a7.floatValue() * a7.floatValue() + a12 * a12;
            a11.ALLATORIxDEMO(a6);
            a10.ALLATORIxDEMO();
            a10.v = a5;
            a10.ALLATORIxDEMO = a5;
            a10.y = a4;
            a10.c = -a4;
            a10.b = a3;
            a2.c(a10);
            a10.c();
            a10.c(a2);
            a2.f(a10);
        }
        if (a2.o * a2.o + a2.k * a2.k > 1.0E-6f) {
            a9 = fj.ALLATORIxDEMO(a2.b, 0.5f * (a2.o + a2.k), a2.ALLATORIxDEMO);
            a8 = a9.c();
            a7 = a9.ALLATORIxDEMO();
            a6 = new ik(a8.floatValue(), 0.0f, 0.0f, a7.floatValue());
            a5 = a7.floatValue() * a7.floatValue() - a8.floatValue() * a8.floatValue();
            a4 = -2.0f * a8.floatValue() * a7.floatValue();
            a3 = a7.floatValue() * a7.floatValue() + a8.floatValue() * a8.floatValue();
            a11.ALLATORIxDEMO(a6);
            a10.ALLATORIxDEMO();
            a10.b = a5;
            a10.ALLATORIxDEMO = a5;
            a10.k = -a4;
            a10.o = a4;
            a10.v = a3;
            a2.c(a10);
            a10.c();
            a10.c(a2);
            a2.f(a10);
        }
        return a11;
    }

    public void c() {
        fj a2;
        float a3 = a2.m;
        a2.m = a2.q;
        a2.q = a3;
        a3 = a2.c;
        a2.c = a2.y;
        a2.y = a3;
        a3 = a2.o;
        a2.o = a2.k;
        a2.k = a3;
    }

    public Triple<ik, gg, ik> ALLATORIxDEMO() {
        fj a2;
        ik a3 = ik.b.ALLATORIxDEMO();
        ik a4 = ik.b.ALLATORIxDEMO();
        fj a5 = a2.ALLATORIxDEMO();
        a5.c();
        a5.c(a2);
        for (int a6 = 0; a6 < 5; ++a6) {
            a4.ALLATORIxDEMO(fj.ALLATORIxDEMO(a5));
        }
        a4.ALLATORIxDEMO();
        fj a7 = new fj(a2);
        a7.c(new fj(a4));
        float a8 = 1.0f;
        qd<Float, Float> a9 = fj.ALLATORIxDEMO(a7.v, a7.q);
        Float a10 = a9.c();
        Float a11 = a9.ALLATORIxDEMO();
        float a12 = a11.floatValue() * a11.floatValue() - a10.floatValue() * a10.floatValue();
        float a13 = -2.0f * a10.floatValue() * a11.floatValue();
        float a14 = a11.floatValue() * a11.floatValue() + a10.floatValue() * a10.floatValue();
        ik a15 = new ik(0.0f, 0.0f, a10.floatValue(), a11.floatValue());
        a3.ALLATORIxDEMO(a15);
        fj a16 = new fj();
        a16.ALLATORIxDEMO();
        a16.v = a12;
        a16.b = a12;
        a16.q = a13;
        a16.m = -a13;
        a16.ALLATORIxDEMO = a14;
        a8 *= a14;
        a16.c(a7);
        a9 = fj.ALLATORIxDEMO(a16.v, a16.y);
        float a17 = -a9.c().floatValue();
        Float a18 = a9.ALLATORIxDEMO();
        float a19 = a18.floatValue() * a18.floatValue() - a17 * a17;
        float a20 = -2.0f * a17 * a18.floatValue();
        float a21 = a18.floatValue() * a18.floatValue() + a17 * a17;
        ik a22 = new ik(0.0f, a17, 0.0f, a18.floatValue());
        a3.ALLATORIxDEMO(a22);
        fj a23 = new fj();
        a23.ALLATORIxDEMO();
        a23.v = a19;
        a23.ALLATORIxDEMO = a19;
        a23.y = -a20;
        a23.c = a20;
        a23.b = a21;
        a8 *= a21;
        a23.c(a16);
        a9 = fj.ALLATORIxDEMO(a23.b, a23.k);
        Float a24 = a9.c();
        Float a25 = a9.ALLATORIxDEMO();
        float a26 = a25.floatValue() * a25.floatValue() - a24.floatValue() * a24.floatValue();
        float a27 = -2.0f * a24.floatValue() * a25.floatValue();
        float a28 = a25.floatValue() * a25.floatValue() + a24.floatValue() * a24.floatValue();
        ik a29 = new ik(a24.floatValue(), 0.0f, 0.0f, a25.floatValue());
        a3.ALLATORIxDEMO(a29);
        fj a30 = new fj();
        a30.ALLATORIxDEMO();
        a30.b = a26;
        a30.ALLATORIxDEMO = a26;
        a30.k = a27;
        a30.o = -a27;
        a30.v = a28;
        a8 *= a28;
        a30.c(a23);
        a8 = 1.0f / a8;
        a3.ALLATORIxDEMO((float)Math.sqrt(a8));
        gg a31 = new gg(a30.v * a8, a30.b * a8, a30.ALLATORIxDEMO * a8);
        return Triple.of((Object)a3, (Object)a31, (Object)a4);
    }

    public boolean equals(Object a2) {
        fj a3;
        if (a3 == a2) {
            return true;
        }
        if (a2 != null && a3.getClass() == a2.getClass()) {
            fj a4 = (fj)a2;
            return Float.compare(a4.v, a3.v) == 0 && Float.compare(a4.m, a3.m) == 0 && Float.compare(a4.c, a3.c) == 0 && Float.compare(a4.q, a3.q) == 0 && Float.compare(a4.b, a3.b) == 0 && Float.compare(a4.o, a3.o) == 0 && Float.compare(a4.y, a3.y) == 0 && Float.compare(a4.k, a3.k) == 0 && Float.compare(a4.ALLATORIxDEMO, a3.ALLATORIxDEMO) == 0;
        }
        return false;
    }

    public int hashCode() {
        fj a2;
        int a3 = a2.v != 0.0f ? Float.floatToIntBits(a2.v) : 0;
        a3 = 31 * a3 + (a2.m != 0.0f ? Float.floatToIntBits(a2.m) : 0);
        a3 = 31 * a3 + (a2.c != 0.0f ? Float.floatToIntBits(a2.c) : 0);
        a3 = 31 * a3 + (a2.q != 0.0f ? Float.floatToIntBits(a2.q) : 0);
        a3 = 31 * a3 + (a2.b != 0.0f ? Float.floatToIntBits(a2.b) : 0);
        a3 = 31 * a3 + (a2.o != 0.0f ? Float.floatToIntBits(a2.o) : 0);
        a3 = 31 * a3 + (a2.y != 0.0f ? Float.floatToIntBits(a2.y) : 0);
        a3 = 31 * a3 + (a2.k != 0.0f ? Float.floatToIntBits(a2.k) : 0);
        return 31 * a3 + (a2.ALLATORIxDEMO != 0.0f ? Float.floatToIntBits(a2.ALLATORIxDEMO) : 0);
    }

    public void f(fj a2) {
        a.v = a2.v;
        a.m = a2.m;
        a.c = a2.c;
        a.q = a2.q;
        a.b = a2.b;
        a.o = a2.o;
        a.y = a2.y;
        a.k = a2.k;
        a.ALLATORIxDEMO = a2.ALLATORIxDEMO;
    }

    public String toString() {
        fj a2;
        StringBuilder a3 = new StringBuilder();
        a3.append("Matrix3f:\n");
        a3.append(a2.v);
        a3.append(" ");
        a3.append(a2.m);
        a3.append(" ");
        a3.append(a2.c);
        a3.append("\n");
        a3.append(a2.q);
        a3.append(" ");
        a3.append(a2.b);
        a3.append(" ");
        a3.append(a2.o);
        a3.append("\n");
        a3.append(a2.y);
        a3.append(" ");
        a3.append(a2.k);
        a3.append(" ");
        a3.append(a2.ALLATORIxDEMO);
        a3.append("\n");
        return a3.toString();
    }

    public void ALLATORIxDEMO() {
        a.v = 1.0f;
        a.m = 0.0f;
        a.c = 0.0f;
        a.q = 0.0f;
        a.b = 1.0f;
        a.o = 0.0f;
        a.y = 0.0f;
        a.k = 0.0f;
        a.ALLATORIxDEMO = 1.0f;
    }

    public float ALLATORIxDEMO() {
        fj a2;
        float a3 = a2.b * a2.ALLATORIxDEMO - a2.o * a2.k;
        float a4 = -(a2.q * a2.ALLATORIxDEMO - a2.o * a2.y);
        float a5 = a2.q * a2.k - a2.b * a2.y;
        float a6 = -(a2.m * a2.ALLATORIxDEMO - a2.c * a2.k);
        float a7 = a2.v * a2.ALLATORIxDEMO - a2.c * a2.y;
        float a8 = -(a2.v * a2.k - a2.m * a2.y);
        float a9 = a2.m * a2.o - a2.c * a2.b;
        float a10 = -(a2.v * a2.o - a2.c * a2.q);
        float a11 = a2.v * a2.b - a2.m * a2.q;
        float a12 = a2.v * a3 + a2.m * a4 + a2.c * a5;
        a2.v = a3;
        a2.q = a4;
        a2.y = a5;
        a2.m = a6;
        a2.b = a7;
        a2.k = a8;
        a2.c = a9;
        a2.o = a10;
        a2.ALLATORIxDEMO = a11;
        return a12;
    }

    public boolean ALLATORIxDEMO() {
        fj a2;
        float a3 = a2.ALLATORIxDEMO();
        if (Math.abs(a3) > 1.0E-6f) {
            a2.ALLATORIxDEMO(a3);
            return true;
        }
        return false;
    }

    public void ALLATORIxDEMO(int a2, int a3, float a4) {
        if (a2 == 0) {
            if (a3 == 0) {
                a.v = a4;
            } else if (a3 == 1) {
                a.m = a4;
            } else {
                a.c = a4;
            }
        } else if (a2 == 1) {
            if (a3 == 0) {
                a.q = a4;
            } else if (a3 == 1) {
                a.b = a4;
            } else {
                a.o = a4;
            }
        } else if (a3 == 0) {
            a.y = a4;
        } else if (a3 == 1) {
            a.k = a4;
        } else {
            a.ALLATORIxDEMO = a4;
        }
    }

    public void c(fj a2) {
        fj a3;
        float a4 = a3.v * a2.v + a3.m * a2.q + a3.c * a2.y;
        float a5 = a3.v * a2.m + a3.m * a2.b + a3.c * a2.k;
        float a6 = a3.v * a2.c + a3.m * a2.o + a3.c * a2.ALLATORIxDEMO;
        float a7 = a3.q * a2.v + a3.b * a2.q + a3.o * a2.y;
        float a8 = a3.q * a2.m + a3.b * a2.b + a3.o * a2.k;
        float a9 = a3.q * a2.c + a3.b * a2.o + a3.o * a2.ALLATORIxDEMO;
        float a10 = a3.y * a2.v + a3.k * a2.q + a3.ALLATORIxDEMO * a2.y;
        float a11 = a3.y * a2.m + a3.k * a2.b + a3.ALLATORIxDEMO * a2.k;
        float a12 = a3.y * a2.c + a3.k * a2.o + a3.ALLATORIxDEMO * a2.ALLATORIxDEMO;
        a3.v = a4;
        a3.m = a5;
        a3.c = a6;
        a3.q = a7;
        a3.b = a8;
        a3.o = a9;
        a3.y = a10;
        a3.k = a11;
        a3.ALLATORIxDEMO = a12;
    }

    public void ALLATORIxDEMO(ik a2) {
        fj a3;
        a3.c(new fj(a2));
    }

    public void ALLATORIxDEMO(float a2) {
        a.v *= a2;
        a.m *= a2;
        a.c *= a2;
        a.q *= a2;
        a.b *= a2;
        a.o *= a2;
        a.y *= a2;
        a.k *= a2;
        a.ALLATORIxDEMO *= a2;
    }

    public fj ALLATORIxDEMO() {
        fj a2;
        return new fj(a2);
    }

    public void ALLATORIxDEMO(fj a2) {
        fj a3;
        fj a4 = a2.ALLATORIxDEMO();
        a4.c(a3);
        a3.f(a4);
    }
}

