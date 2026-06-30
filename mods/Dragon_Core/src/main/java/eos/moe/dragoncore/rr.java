/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.dragoncore;

import java.util.Objects;

public class rr {
    public float y = 0.0f;
    public float k;
    public float ALLATORIxDEMO;

    public rr(float a2, float a3) {
        rr a4;
        a4.k = a2;
        a4.ALLATORIxDEMO = a3;
    }

    public void ALLATORIxDEMO() {
        rr a2;
        a2.y += a2.k;
        if (a2.y >= a2.ALLATORIxDEMO) {
            a2.y = 0.0f;
        } else if (a2.y < 0.0f) {
            a2.y = a2.ALLATORIxDEMO;
        }
    }

    public boolean equals(Object a2) {
        rr a3;
        if (a3 == a2) {
            return true;
        }
        if (!(a2 instanceof rr)) {
            return false;
        }
        rr a4 = (rr)a2;
        return Math.abs(a4.k - a3.k) <= 1.0f && Math.abs(a4.ALLATORIxDEMO - a3.ALLATORIxDEMO) <= 1.0f;
    }

    public int hashCode() {
        rr a2;
        return Objects.hash(Float.valueOf(a2.k), Float.valueOf(a2.ALLATORIxDEMO));
    }
}

