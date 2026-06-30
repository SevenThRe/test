/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.bax;
import java.util.Objects;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class yz {
    private int o;
    private final bax y;
    private bax k;
    private String ALLATORIxDEMO;

    public yz(int a2, bax a3) {
        yz a4;
        a4.o = a2;
        a4.y = a3;
        a4.k = a3;
    }

    public yz(String a2, int a3, bax a4, bax a5) {
        yz a6;
        a6.ALLATORIxDEMO = a2;
        a6.o = a3;
        a6.y = a4;
        a6.k = a5;
    }

    public static yz ALLATORIxDEMO(int a2, bax a3) {
        return new yz(a2, a3);
    }

    public static yz ALLATORIxDEMO(String a2, int a3, bax a4, bax a5) {
        return new yz(a2, a3, a4, a5);
    }

    public int ALLATORIxDEMO() {
        yz a2;
        return a2.o;
    }

    public bax c() {
        yz a2;
        return a2.y;
    }

    public yz ALLATORIxDEMO(int a2) {
        yz a3;
        return new yz(a3.ALLATORIxDEMO, a2, a3.y, a3.k);
    }

    public yz ALLATORIxDEMO(String a2) {
        yz a3;
        return new yz(a2, a3.o, a3.y, a3.k);
    }

    public yz ALLATORIxDEMO() {
        yz a2;
        return new yz(a2.ALLATORIxDEMO, a2.o, a2.y, a2.k);
    }

    public bax ALLATORIxDEMO() {
        yz a2;
        return a2.k;
    }

    public String ALLATORIxDEMO() {
        yz a2;
        return a2.ALLATORIxDEMO;
    }

    public boolean equals(Object a2) {
        yz a3;
        if (a3 == a2) {
            return true;
        }
        if (a2 == null || a3.getClass() != a2.getClass()) {
            return false;
        }
        yz a4 = (yz)a2;
        return a3.o == a4.o && a3.y.equals(a4.y) && a3.k.equals(a4.k) && Objects.equals(a3.ALLATORIxDEMO, a4.ALLATORIxDEMO);
    }

    public int hashCode() {
        yz a2;
        return Objects.hash(a2.o, a2.y, a2.k, a2.ALLATORIxDEMO);
    }

    public void ALLATORIxDEMO(int a2) {
        a.o = a2;
    }

    public String toString() {
        yz a2;
        return "KeyFrame{startTime=" + a2.o + ", vec=" + a2.y + ", preVec=" + a2.k + ", lerp_mode='" + a2.ALLATORIxDEMO + '\'' + '}';
    }
}

