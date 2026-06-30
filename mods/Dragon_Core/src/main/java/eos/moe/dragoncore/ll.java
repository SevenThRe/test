/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.gg;
import eos.moe.dragoncore.ik;
import eos.moe.dragoncore.nk;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class ll {
    private float o;
    private float y;
    private float k;
    private float ALLATORIxDEMO;

    public ll() {
        ll a2;
    }

    public ll(float a2, float a3, float a4, float a5) {
        ll a6;
        a6.o = a2;
        a6.y = a3;
        a6.k = a4;
        a6.ALLATORIxDEMO = a5;
    }

    public ll(gg a2) {
        a3(a2.f(), a2.c(), a2.ALLATORIxDEMO(), 1.0f);
        ll a3;
    }

    public boolean equals(Object a2) {
        ll a3;
        if (a3 == a2) {
            return true;
        }
        if (a2 != null && a3.getClass() == a2.getClass()) {
            ll a4 = (ll)a2;
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
        ll a2;
        int a3 = Float.floatToIntBits(a2.o);
        a3 = 31 * a3 + Float.floatToIntBits(a2.y);
        a3 = 31 * a3 + Float.floatToIntBits(a2.k);
        return 31 * a3 + Float.floatToIntBits(a2.ALLATORIxDEMO);
    }

    public float x() {
        ll a2;
        return a2.o;
    }

    public float f() {
        ll a2;
        return a2.y;
    }

    public float c() {
        ll a2;
        return a2.k;
    }

    public float ALLATORIxDEMO() {
        ll a2;
        return a2.ALLATORIxDEMO;
    }

    public void d(float a2) {
        a.o *= a2;
        a.y *= a2;
        a.k *= a2;
        a.ALLATORIxDEMO *= a2;
    }

    public void ALLATORIxDEMO(gg a2) {
        a.o *= a2.f();
        a.y *= a2.c();
        a.k *= a2.ALLATORIxDEMO();
    }

    public void c(float a2, float a3, float a4, float a5) {
        a.o = a2;
        a.y = a3;
        a.k = a4;
        a.ALLATORIxDEMO = a5;
    }

    public void ALLATORIxDEMO(float a2, float a3, float a4, float a5) {
        a.o += a2;
        a.y += a3;
        a.k += a4;
        a.ALLATORIxDEMO += a5;
    }

    public float ALLATORIxDEMO(ll a2) {
        ll a3;
        return a3.o * a2.o + a3.y * a2.y + a3.k * a2.k + a3.ALLATORIxDEMO * a2.ALLATORIxDEMO;
    }

    public void ALLATORIxDEMO(nk a2) {
        ll a3;
        float a4 = a3.o;
        float a5 = a3.y;
        float a6 = a3.k;
        float a7 = a3.ALLATORIxDEMO;
        a3.o = a2.l * a4 + a2.z * a5 + a2.s * a6 + a2.g * a7;
        a3.y = a2.t * a4 + a2.r * a5 + a2.x * a6 + a2.v * a7;
        a3.k = a2.m * a4 + a2.c * a5 + a2.q * a6 + a2.b * a7;
        a3.ALLATORIxDEMO = a2.o * a4 + a2.y * a5 + a2.k * a6 + a2.ALLATORIxDEMO * a7;
    }

    public void ALLATORIxDEMO(ik a2) {
        ll a3;
        ik a4 = new ik(a2);
        a4.ALLATORIxDEMO(new ik(a3.x(), a3.f(), a3.c(), 0.0f));
        ik a5 = new ik(a2);
        a5.c();
        a4.ALLATORIxDEMO(a5);
        a3.c(a4.x(), a4.f(), a4.c(), a3.ALLATORIxDEMO());
    }

    public void ALLATORIxDEMO() {
        ll a2;
        a2.o /= a2.ALLATORIxDEMO;
        a2.y /= a2.ALLATORIxDEMO;
        a2.k /= a2.ALLATORIxDEMO;
        a2.ALLATORIxDEMO = 1.0f;
    }

    public void ALLATORIxDEMO(ll a2, float a3) {
        ll a4;
        float a5 = 1.0f - a3;
        a4.o = a4.o * a5 + a2.o * a3;
        a4.y = a4.y * a5 + a2.y * a3;
        a4.k = a4.k * a5 + a2.k * a3;
        a4.ALLATORIxDEMO = a4.ALLATORIxDEMO * a5 + a2.ALLATORIxDEMO * a3;
    }

    public String toString() {
        ll a2;
        return "[" + a2.o + ", " + a2.y + ", " + a2.k + ", " + a2.ALLATORIxDEMO + "]";
    }

    public void ALLATORIxDEMO(float[] a2) {
        a.o = a2[0];
        a.y = a2[1];
        a.k = a2[2];
        a.ALLATORIxDEMO = a2[3];
    }

    public void x(float a2) {
        a.o = a2;
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

