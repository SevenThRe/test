/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.dragoncore;

import java.util.Objects;

public class qd<A, B> {
    private A k;
    private B ALLATORIxDEMO;

    public qd(A a2, B a3) {
        qd a4;
        a4.k = a2;
        a4.ALLATORIxDEMO = a3;
    }

    public static <A, B> qd<A, B> ALLATORIxDEMO(A a2, B a3) {
        return new qd<A, B>(a2, a3);
    }

    public A c() {
        qd a2;
        return a2.k;
    }

    public B ALLATORIxDEMO() {
        qd a2;
        return a2.ALLATORIxDEMO;
    }

    public void c(A a2) {
        a.k = a2;
    }

    public void ALLATORIxDEMO(B a2) {
        a.ALLATORIxDEMO = a2;
    }

    public String toString() {
        qd a2;
        return "Pair{a=" + a2.k + ", b=" + a2.ALLATORIxDEMO + '}';
    }

    public boolean equals(Object a2) {
        qd a3;
        if (a3 == a2) {
            return true;
        }
        if (a2 == null || a3.getClass() != a2.getClass()) {
            return false;
        }
        qd a4 = (qd)a2;
        return Objects.equals(a3.k, a4.k) && Objects.equals(a3.ALLATORIxDEMO, a4.ALLATORIxDEMO);
    }

    public int hashCode() {
        qd a2;
        return Objects.hash(a2.k, a2.ALLATORIxDEMO);
    }
}

