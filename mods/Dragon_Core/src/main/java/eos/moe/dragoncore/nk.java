/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.ik;
import java.nio.FloatBuffer;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public final class nk {
    private static final int i = 4;
    public float l;
    public float z;
    public float s;
    public float g;
    public float t;
    public float r;
    public float x;
    public float v;
    public float m;
    public float c;
    public float q;
    public float b;
    public float o;
    public float y;
    public float k;
    public float ALLATORIxDEMO;

    public nk() {
        nk a2;
    }

    public nk(nk a2) {
        nk a3;
        a3.l = a2.l;
        a3.z = a2.z;
        a3.s = a2.s;
        a3.g = a2.g;
        a3.t = a2.t;
        a3.r = a2.r;
        a3.x = a2.x;
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

    public nk(ik a2) {
        nk a3;
        float a4 = a2.x();
        float a5 = a2.f();
        float a6 = a2.c();
        float a7 = a2.ALLATORIxDEMO();
        float a8 = 2.0f * a4 * a4;
        float a9 = 2.0f * a5 * a5;
        float a10 = 2.0f * a6 * a6;
        a3.l = 1.0f - a9 - a10;
        a3.r = 1.0f - a10 - a8;
        a3.q = 1.0f - a8 - a9;
        a3.ALLATORIxDEMO = 1.0f;
        float a11 = a4 * a5;
        float a12 = a5 * a6;
        float a13 = a6 * a4;
        float a14 = a4 * a7;
        float a15 = a5 * a7;
        float a16 = a6 * a7;
        a3.t = 2.0f * (a11 + a16);
        a3.z = 2.0f * (a11 - a16);
        a3.m = 2.0f * (a13 - a15);
        a3.s = 2.0f * (a13 + a15);
        a3.c = 2.0f * (a12 + a14);
        a3.x = 2.0f * (a12 - a14);
    }

    public boolean c() {
        nk a2;
        nk a3 = new nk();
        a3.o = 1.0f;
        a3.y = 1.0f;
        a3.k = 1.0f;
        a3.ALLATORIxDEMO = 0.0f;
        nk a4 = a2.ALLATORIxDEMO();
        a4.x(a3);
        return nk.ALLATORIxDEMO(a4.l / a4.g) && nk.ALLATORIxDEMO(a4.t / a4.v) && nk.ALLATORIxDEMO(a4.m / a4.b) && nk.ALLATORIxDEMO(a4.z / a4.g) && nk.ALLATORIxDEMO(a4.r / a4.v) && nk.ALLATORIxDEMO(a4.c / a4.b) && nk.ALLATORIxDEMO(a4.s / a4.g) && nk.ALLATORIxDEMO(a4.x / a4.v) && nk.ALLATORIxDEMO(a4.q / a4.b);
    }

    private static /* synthetic */ boolean ALLATORIxDEMO(float a2) {
        return (double)Math.abs(a2 - (float)Math.round(a2)) <= 1.0E-5;
    }

    public boolean equals(Object a2) {
        nk a3;
        if (a3 == a2) {
            return true;
        }
        if (a2 != null && a3.getClass() == a2.getClass()) {
            nk a4 = (nk)a2;
            return Float.compare(a4.l, a3.l) == 0 && Float.compare(a4.z, a3.z) == 0 && Float.compare(a4.s, a3.s) == 0 && Float.compare(a4.g, a3.g) == 0 && Float.compare(a4.t, a3.t) == 0 && Float.compare(a4.r, a3.r) == 0 && Float.compare(a4.x, a3.x) == 0 && Float.compare(a4.v, a3.v) == 0 && Float.compare(a4.m, a3.m) == 0 && Float.compare(a4.c, a3.c) == 0 && Float.compare(a4.q, a3.q) == 0 && Float.compare(a4.b, a3.b) == 0 && Float.compare(a4.o, a3.o) == 0 && Float.compare(a4.y, a3.y) == 0 && Float.compare(a4.k, a3.k) == 0 && Float.compare(a4.ALLATORIxDEMO, a3.ALLATORIxDEMO) == 0;
        }
        return false;
    }

    public int hashCode() {
        nk a2;
        int a3 = a2.l != 0.0f ? Float.floatToIntBits(a2.l) : 0;
        a3 = 31 * a3 + (a2.z != 0.0f ? Float.floatToIntBits(a2.z) : 0);
        a3 = 31 * a3 + (a2.s != 0.0f ? Float.floatToIntBits(a2.s) : 0);
        a3 = 31 * a3 + (a2.g != 0.0f ? Float.floatToIntBits(a2.g) : 0);
        a3 = 31 * a3 + (a2.t != 0.0f ? Float.floatToIntBits(a2.t) : 0);
        a3 = 31 * a3 + (a2.r != 0.0f ? Float.floatToIntBits(a2.r) : 0);
        a3 = 31 * a3 + (a2.x != 0.0f ? Float.floatToIntBits(a2.x) : 0);
        a3 = 31 * a3 + (a2.v != 0.0f ? Float.floatToIntBits(a2.v) : 0);
        a3 = 31 * a3 + (a2.m != 0.0f ? Float.floatToIntBits(a2.m) : 0);
        a3 = 31 * a3 + (a2.c != 0.0f ? Float.floatToIntBits(a2.c) : 0);
        a3 = 31 * a3 + (a2.q != 0.0f ? Float.floatToIntBits(a2.q) : 0);
        a3 = 31 * a3 + (a2.b != 0.0f ? Float.floatToIntBits(a2.b) : 0);
        a3 = 31 * a3 + (a2.o != 0.0f ? Float.floatToIntBits(a2.o) : 0);
        a3 = 31 * a3 + (a2.y != 0.0f ? Float.floatToIntBits(a2.y) : 0);
        a3 = 31 * a3 + (a2.k != 0.0f ? Float.floatToIntBits(a2.k) : 0);
        return 31 * a3 + (a2.ALLATORIxDEMO != 0.0f ? Float.floatToIntBits(a2.ALLATORIxDEMO) : 0);
    }

    private static /* synthetic */ int ALLATORIxDEMO(int a2, int a3) {
        return a3 * 4 + a2;
    }

    public void x(FloatBuffer a2) {
        a.l = a2.get(nk.ALLATORIxDEMO(0, 0));
        a.z = a2.get(nk.ALLATORIxDEMO(0, 1));
        a.s = a2.get(nk.ALLATORIxDEMO(0, 2));
        a.g = a2.get(nk.ALLATORIxDEMO(0, 3));
        a.t = a2.get(nk.ALLATORIxDEMO(1, 0));
        a.r = a2.get(nk.ALLATORIxDEMO(1, 1));
        a.x = a2.get(nk.ALLATORIxDEMO(1, 2));
        a.v = a2.get(nk.ALLATORIxDEMO(1, 3));
        a.m = a2.get(nk.ALLATORIxDEMO(2, 0));
        a.c = a2.get(nk.ALLATORIxDEMO(2, 1));
        a.q = a2.get(nk.ALLATORIxDEMO(2, 2));
        a.b = a2.get(nk.ALLATORIxDEMO(2, 3));
        a.o = a2.get(nk.ALLATORIxDEMO(3, 0));
        a.y = a2.get(nk.ALLATORIxDEMO(3, 1));
        a.k = a2.get(nk.ALLATORIxDEMO(3, 2));
        a.ALLATORIxDEMO = a2.get(nk.ALLATORIxDEMO(3, 3));
    }

    public void f(FloatBuffer a2) {
        a.l = a2.get(nk.ALLATORIxDEMO(0, 0));
        a.z = a2.get(nk.ALLATORIxDEMO(1, 0));
        a.s = a2.get(nk.ALLATORIxDEMO(2, 0));
        a.g = a2.get(nk.ALLATORIxDEMO(3, 0));
        a.t = a2.get(nk.ALLATORIxDEMO(0, 1));
        a.r = a2.get(nk.ALLATORIxDEMO(1, 1));
        a.x = a2.get(nk.ALLATORIxDEMO(2, 1));
        a.v = a2.get(nk.ALLATORIxDEMO(3, 1));
        a.m = a2.get(nk.ALLATORIxDEMO(0, 2));
        a.c = a2.get(nk.ALLATORIxDEMO(1, 2));
        a.q = a2.get(nk.ALLATORIxDEMO(2, 2));
        a.b = a2.get(nk.ALLATORIxDEMO(3, 2));
        a.o = a2.get(nk.ALLATORIxDEMO(0, 3));
        a.y = a2.get(nk.ALLATORIxDEMO(1, 3));
        a.k = a2.get(nk.ALLATORIxDEMO(2, 3));
        a.ALLATORIxDEMO = a2.get(nk.ALLATORIxDEMO(3, 3));
    }

    public void c(FloatBuffer a2, boolean a3) {
        nk a4;
        if (a3) {
            a4.f(a2);
        } else {
            a4.x(a2);
        }
    }

    public void d(nk a2) {
        a.l = a2.l;
        a.z = a2.z;
        a.s = a2.s;
        a.g = a2.g;
        a.t = a2.t;
        a.r = a2.r;
        a.x = a2.x;
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
        nk a2;
        StringBuilder a3 = new StringBuilder();
        a3.append("Matrix4f:\n");
        a3.append(a2.l);
        a3.append(" ");
        a3.append(a2.z);
        a3.append(" ");
        a3.append(a2.s);
        a3.append(" ");
        a3.append(a2.g);
        a3.append("\n");
        a3.append(a2.t);
        a3.append(" ");
        a3.append(a2.r);
        a3.append(" ");
        a3.append(a2.x);
        a3.append(" ");
        a3.append(a2.v);
        a3.append("\n");
        a3.append(a2.m);
        a3.append(" ");
        a3.append(a2.c);
        a3.append(" ");
        a3.append(a2.q);
        a3.append(" ");
        a3.append(a2.b);
        a3.append("\n");
        a3.append(a2.o);
        a3.append(" ");
        a3.append(a2.y);
        a3.append(" ");
        a3.append(a2.k);
        a3.append(" ");
        a3.append(a2.ALLATORIxDEMO);
        a3.append("\n");
        return a3.toString();
    }

    public void c(FloatBuffer a2) {
        nk a3;
        a2.put(nk.ALLATORIxDEMO(0, 0), a3.l);
        a2.put(nk.ALLATORIxDEMO(0, 1), a3.z);
        a2.put(nk.ALLATORIxDEMO(0, 2), a3.s);
        a2.put(nk.ALLATORIxDEMO(0, 3), a3.g);
        a2.put(nk.ALLATORIxDEMO(1, 0), a3.t);
        a2.put(nk.ALLATORIxDEMO(1, 1), a3.r);
        a2.put(nk.ALLATORIxDEMO(1, 2), a3.x);
        a2.put(nk.ALLATORIxDEMO(1, 3), a3.v);
        a2.put(nk.ALLATORIxDEMO(2, 0), a3.m);
        a2.put(nk.ALLATORIxDEMO(2, 1), a3.c);
        a2.put(nk.ALLATORIxDEMO(2, 2), a3.q);
        a2.put(nk.ALLATORIxDEMO(2, 3), a3.b);
        a2.put(nk.ALLATORIxDEMO(3, 0), a3.o);
        a2.put(nk.ALLATORIxDEMO(3, 1), a3.y);
        a2.put(nk.ALLATORIxDEMO(3, 2), a3.k);
        a2.put(nk.ALLATORIxDEMO(3, 3), a3.ALLATORIxDEMO);
    }

    public void ALLATORIxDEMO(FloatBuffer a2) {
        nk a3;
        a2.put(nk.ALLATORIxDEMO(0, 0), a3.l);
        a2.put(nk.ALLATORIxDEMO(1, 0), a3.z);
        a2.put(nk.ALLATORIxDEMO(2, 0), a3.s);
        a2.put(nk.ALLATORIxDEMO(3, 0), a3.g);
        a2.put(nk.ALLATORIxDEMO(0, 1), a3.t);
        a2.put(nk.ALLATORIxDEMO(1, 1), a3.r);
        a2.put(nk.ALLATORIxDEMO(2, 1), a3.x);
        a2.put(nk.ALLATORIxDEMO(3, 1), a3.v);
        a2.put(nk.ALLATORIxDEMO(0, 2), a3.m);
        a2.put(nk.ALLATORIxDEMO(1, 2), a3.c);
        a2.put(nk.ALLATORIxDEMO(2, 2), a3.q);
        a2.put(nk.ALLATORIxDEMO(3, 2), a3.b);
        a2.put(nk.ALLATORIxDEMO(0, 3), a3.o);
        a2.put(nk.ALLATORIxDEMO(1, 3), a3.y);
        a2.put(nk.ALLATORIxDEMO(2, 3), a3.k);
        a2.put(nk.ALLATORIxDEMO(3, 3), a3.ALLATORIxDEMO);
    }

    public void ALLATORIxDEMO(FloatBuffer a2, boolean a3) {
        nk a4;
        if (a3) {
            a4.ALLATORIxDEMO(a2);
        } else {
            a4.c(a2);
        }
    }

    public void c() {
        a.l = 1.0f;
        a.z = 0.0f;
        a.s = 0.0f;
        a.g = 0.0f;
        a.t = 0.0f;
        a.r = 1.0f;
        a.x = 0.0f;
        a.v = 0.0f;
        a.m = 0.0f;
        a.c = 0.0f;
        a.q = 1.0f;
        a.b = 0.0f;
        a.o = 0.0f;
        a.y = 0.0f;
        a.k = 0.0f;
        a.ALLATORIxDEMO = 1.0f;
    }

    public float f() {
        nk a2;
        float a3 = a2.l * a2.r - a2.z * a2.t;
        float a4 = a2.l * a2.x - a2.s * a2.t;
        float a5 = a2.l * a2.v - a2.g * a2.t;
        float a6 = a2.z * a2.x - a2.s * a2.r;
        float a7 = a2.z * a2.v - a2.g * a2.r;
        float a8 = a2.s * a2.v - a2.g * a2.x;
        float a9 = a2.m * a2.y - a2.c * a2.o;
        float a10 = a2.m * a2.k - a2.q * a2.o;
        float a11 = a2.m * a2.ALLATORIxDEMO - a2.b * a2.o;
        float a12 = a2.c * a2.k - a2.q * a2.y;
        float a13 = a2.c * a2.ALLATORIxDEMO - a2.b * a2.y;
        float a14 = a2.q * a2.ALLATORIxDEMO - a2.b * a2.k;
        float a15 = a2.r * a14 - a2.x * a13 + a2.v * a12;
        float a16 = -a2.t * a14 + a2.x * a11 - a2.v * a10;
        float a17 = a2.t * a13 - a2.r * a11 + a2.v * a9;
        float a18 = -a2.t * a12 + a2.r * a10 - a2.x * a9;
        float a19 = -a2.z * a14 + a2.s * a13 - a2.g * a12;
        float a20 = a2.l * a14 - a2.s * a11 + a2.g * a10;
        float a21 = -a2.l * a13 + a2.z * a11 - a2.g * a9;
        float a22 = a2.l * a12 - a2.z * a10 + a2.s * a9;
        float a23 = a2.y * a8 - a2.k * a7 + a2.ALLATORIxDEMO * a6;
        float a24 = -a2.o * a8 + a2.k * a5 - a2.ALLATORIxDEMO * a4;
        float a25 = a2.o * a7 - a2.y * a5 + a2.ALLATORIxDEMO * a3;
        float a26 = -a2.o * a6 + a2.y * a4 - a2.k * a3;
        float a27 = -a2.c * a8 + a2.q * a7 - a2.b * a6;
        float a28 = a2.m * a8 - a2.q * a5 + a2.b * a4;
        float a29 = -a2.m * a7 + a2.c * a5 - a2.b * a3;
        float a30 = a2.m * a6 - a2.c * a4 + a2.q * a3;
        a2.l = a15;
        a2.t = a16;
        a2.m = a17;
        a2.o = a18;
        a2.z = a19;
        a2.r = a20;
        a2.c = a21;
        a2.y = a22;
        a2.s = a23;
        a2.x = a24;
        a2.q = a25;
        a2.k = a26;
        a2.g = a27;
        a2.v = a28;
        a2.b = a29;
        a2.ALLATORIxDEMO = a30;
        return a3 * a14 - a4 * a13 + a5 * a12 + a6 * a11 - a7 * a10 + a8 * a9;
    }

    public float c() {
        nk a2;
        float a3 = a2.l * a2.r - a2.z * a2.t;
        float a4 = a2.l * a2.x - a2.s * a2.t;
        float a5 = a2.l * a2.v - a2.g * a2.t;
        float a6 = a2.z * a2.x - a2.s * a2.r;
        float a7 = a2.z * a2.v - a2.g * a2.r;
        float a8 = a2.s * a2.v - a2.g * a2.x;
        float a9 = a2.m * a2.y - a2.c * a2.o;
        float a10 = a2.m * a2.k - a2.q * a2.o;
        float a11 = a2.m * a2.ALLATORIxDEMO - a2.b * a2.o;
        float a12 = a2.c * a2.k - a2.q * a2.y;
        float a13 = a2.c * a2.ALLATORIxDEMO - a2.b * a2.y;
        float a14 = a2.q * a2.ALLATORIxDEMO - a2.b * a2.k;
        return a3 * a14 - a4 * a13 + a5 * a12 + a6 * a11 - a7 * a10 + a8 * a9;
    }

    public void ALLATORIxDEMO() {
        nk a2;
        float a3 = a2.t;
        a2.t = a2.z;
        a2.z = a3;
        a3 = a2.m;
        a2.m = a2.s;
        a2.s = a3;
        a3 = a2.c;
        a2.c = a2.x;
        a2.x = a3;
        a3 = a2.o;
        a2.o = a2.g;
        a2.g = a3;
        a3 = a2.y;
        a2.y = a2.v;
        a2.v = a3;
        a3 = a2.k;
        a2.k = a2.b;
        a2.b = a3;
    }

    public boolean ALLATORIxDEMO() {
        nk a2;
        float a3 = a2.f();
        if (Math.abs(a3) > 1.0E-6f) {
            a2.ALLATORIxDEMO(a3);
            return true;
        }
        return false;
    }

    public void c(double a2, double a3, double a4) {
        nk a5;
        nk a6 = nk.c((float)a2, (float)a3, (float)a4);
        a5.x(a6);
    }

    public void x(nk a2) {
        nk a3;
        float a4 = a3.l * a2.l + a3.z * a2.t + a3.s * a2.m + a3.g * a2.o;
        float a5 = a3.l * a2.z + a3.z * a2.r + a3.s * a2.c + a3.g * a2.y;
        float a6 = a3.l * a2.s + a3.z * a2.x + a3.s * a2.q + a3.g * a2.k;
        float a7 = a3.l * a2.g + a3.z * a2.v + a3.s * a2.b + a3.g * a2.ALLATORIxDEMO;
        float a8 = a3.t * a2.l + a3.r * a2.t + a3.x * a2.m + a3.v * a2.o;
        float a9 = a3.t * a2.z + a3.r * a2.r + a3.x * a2.c + a3.v * a2.y;
        float a10 = a3.t * a2.s + a3.r * a2.x + a3.x * a2.q + a3.v * a2.k;
        float a11 = a3.t * a2.g + a3.r * a2.v + a3.x * a2.b + a3.v * a2.ALLATORIxDEMO;
        float a12 = a3.m * a2.l + a3.c * a2.t + a3.q * a2.m + a3.b * a2.o;
        float a13 = a3.m * a2.z + a3.c * a2.r + a3.q * a2.c + a3.b * a2.y;
        float a14 = a3.m * a2.s + a3.c * a2.x + a3.q * a2.q + a3.b * a2.k;
        float a15 = a3.m * a2.g + a3.c * a2.v + a3.q * a2.b + a3.b * a2.ALLATORIxDEMO;
        float a16 = a3.o * a2.l + a3.y * a2.t + a3.k * a2.m + a3.ALLATORIxDEMO * a2.o;
        float a17 = a3.o * a2.z + a3.y * a2.r + a3.k * a2.c + a3.ALLATORIxDEMO * a2.y;
        float a18 = a3.o * a2.s + a3.y * a2.x + a3.k * a2.q + a3.ALLATORIxDEMO * a2.k;
        float a19 = a3.o * a2.g + a3.y * a2.v + a3.k * a2.b + a3.ALLATORIxDEMO * a2.ALLATORIxDEMO;
        a3.l = a4;
        a3.z = a5;
        a3.s = a6;
        a3.g = a7;
        a3.t = a8;
        a3.r = a9;
        a3.x = a10;
        a3.v = a11;
        a3.m = a12;
        a3.c = a13;
        a3.q = a14;
        a3.b = a15;
        a3.o = a16;
        a3.y = a17;
        a3.k = a18;
        a3.ALLATORIxDEMO = a19;
    }

    public void ALLATORIxDEMO(ik a2) {
        nk a3;
        a3.x(new nk(a2));
    }

    public void ALLATORIxDEMO(float a2) {
        a.l *= a2;
        a.z *= a2;
        a.s *= a2;
        a.g *= a2;
        a.t *= a2;
        a.r *= a2;
        a.x *= a2;
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

    public void f(nk a2) {
        a.l += a2.l;
        a.z += a2.z;
        a.s += a2.s;
        a.g += a2.g;
        a.t += a2.t;
        a.r += a2.r;
        a.x += a2.x;
        a.v += a2.v;
        a.m += a2.m;
        a.c += a2.c;
        a.q += a2.q;
        a.b += a2.b;
        a.o += a2.o;
        a.y += a2.y;
        a.k += a2.k;
        a.ALLATORIxDEMO += a2.ALLATORIxDEMO;
    }

    public void c(nk a2) {
        a.l -= a2.l;
        a.z -= a2.z;
        a.s -= a2.s;
        a.g -= a2.g;
        a.t -= a2.t;
        a.r -= a2.r;
        a.x -= a2.x;
        a.v -= a2.v;
        a.m -= a2.m;
        a.c -= a2.c;
        a.q -= a2.q;
        a.b -= a2.b;
        a.o -= a2.o;
        a.y -= a2.y;
        a.k -= a2.k;
        a.ALLATORIxDEMO -= a2.ALLATORIxDEMO;
    }

    public float ALLATORIxDEMO() {
        nk a2;
        return a2.l + a2.r + a2.q + a2.ALLATORIxDEMO;
    }

    public static nk ALLATORIxDEMO(double a2, float a3, float a4, float a5) {
        float a6 = (float)(1.0 / Math.tan(a2 * 0.01745329238474369 / 2.0));
        nk a7 = new nk();
        a7.l = a6 / a3;
        a7.r = a6;
        a7.q = (a5 + a4) / (a4 - a5);
        a7.k = -1.0f;
        a7.b = 2.0f * a5 * a4 / (a4 - a5);
        return a7;
    }

    public static nk ALLATORIxDEMO(float a2, float a3, float a4, float a5) {
        nk a6 = new nk();
        a6.l = 2.0f / a2;
        a6.r = 2.0f / a3;
        float a7 = a5 - a4;
        a6.q = -2.0f / a7;
        a6.ALLATORIxDEMO = 1.0f;
        a6.g = -1.0f;
        a6.v = 1.0f;
        a6.b = -(a5 + a4) / a7;
        return a6;
    }

    public static nk ALLATORIxDEMO(float a2, float a3, float a4, float a5, float a6, float a7) {
        nk a8 = new nk();
        float a9 = a3 - a2;
        float a10 = a4 - a5;
        float a11 = a7 - a6;
        a8.l = 2.0f / a9;
        a8.r = 2.0f / a10;
        a8.q = -2.0f / a11;
        a8.g = -(a3 + a2) / a9;
        a8.v = -(a4 + a5) / a10;
        a8.b = -(a7 + a6) / a11;
        a8.ALLATORIxDEMO = 1.0f;
        return a8;
    }

    public nk ALLATORIxDEMO() {
        nk a2;
        return new nk(a2);
    }

    public void ALLATORIxDEMO(double a2, double a3, double a4) {
        nk a5;
        a5.g = (float)((double)a5.g + ((double)a5.l * a2 + (double)a5.z * a3 + (double)a5.s * a4));
        a5.v = (float)((double)a5.v + ((double)a5.t * a2 + (double)a5.r * a3 + (double)a5.x * a4));
        a5.b = (float)((double)a5.b + ((double)a5.m * a2 + (double)a5.c * a3 + (double)a5.q * a4));
        a5.ALLATORIxDEMO = (float)((double)a5.ALLATORIxDEMO + ((double)a5.o * a2 + (double)a5.y * a3 + (double)a5.k * a4));
    }

    public static nk c(float a2, float a3, float a4) {
        nk a5 = new nk();
        a5.l = a2;
        a5.r = a3;
        a5.q = a4;
        a5.ALLATORIxDEMO = 1.0f;
        return a5;
    }

    public static nk ALLATORIxDEMO(float a2, float a3, float a4) {
        nk a5 = new nk();
        a5.l = 1.0f;
        a5.r = 1.0f;
        a5.q = 1.0f;
        a5.ALLATORIxDEMO = 1.0f;
        a5.g = a2;
        a5.v = a3;
        a5.b = a4;
        return a5;
    }

    public nk(float[] a2) {
        nk a3;
        a3.l = a2[0];
        a3.z = a2[1];
        a3.s = a2[2];
        a3.g = a2[3];
        a3.t = a2[4];
        a3.r = a2[5];
        a3.x = a2[6];
        a3.v = a2[7];
        a3.m = a2[8];
        a3.c = a2[9];
        a3.q = a2[10];
        a3.b = a2[11];
        a3.o = a2[12];
        a3.y = a2[13];
        a3.k = a2[14];
        a3.ALLATORIxDEMO = a2[15];
    }

    public void ALLATORIxDEMO(nk a2) {
        nk a3;
        nk a4 = a2.ALLATORIxDEMO();
        a4.x(a3);
        a3.d(a4);
    }

    public void ALLATORIxDEMO(float a2, float a3, float a4) {
        a.l = 1.0f;
        a.r = 1.0f;
        a.q = 1.0f;
        a.ALLATORIxDEMO = 1.0f;
        a.g = a2;
        a.v = a3;
        a.b = a4;
    }
}

