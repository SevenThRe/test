/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.dragoncore;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class ym {
    public byte[] b;
    public int o;
    public String y;
    public String k;
    public byte[] ALLATORIxDEMO;

    public ym() {
        ym a2;
        a2.y = null;
        a2.k = "UTF-8";
        a2.b = null;
        a2.o = 1000;
        a2.ALLATORIxDEMO = null;
    }

    public ym(String a2, String a3, byte[] a4, int a5) {
        a6(a2, a3, a4, a5, null);
        ym a6;
    }

    public ym(String a2, String a3, byte[] a4, int a5, byte[] a6) {
        ym a7;
        a7.y = a2;
        a7.k = a3;
        a7.b = a4;
        a7.o = a5;
        a7.ALLATORIxDEMO = a6;
    }

    public int ALLATORIxDEMO() {
        ym a2;
        return a2.o;
    }

    public void ALLATORIxDEMO(int a2) {
        a.o = a2;
    }

    public byte[] c() {
        ym a2;
        return a2.b;
    }

    public void c(byte[] a2) {
        a.b = a2;
    }

    public byte[] ALLATORIxDEMO() {
        ym a2;
        return a2.ALLATORIxDEMO;
    }

    public void ALLATORIxDEMO(byte[] a2) {
        a.ALLATORIxDEMO = a2;
    }

    public String c() {
        ym a2;
        return a2.y;
    }

    public void c(String a2) {
        a.y = a2;
    }

    public String ALLATORIxDEMO() {
        ym a2;
        return a2.k;
    }

    public void ALLATORIxDEMO(String a2) {
        a.k = a2;
    }
}

