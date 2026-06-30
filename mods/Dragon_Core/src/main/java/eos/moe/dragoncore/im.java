/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.dragoncore;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class im {
    public boolean y;
    public long k;
    public long ALLATORIxDEMO;

    public im(long a2) {
        im a3;
        a3.ALLATORIxDEMO = a2;
    }

    public long ALLATORIxDEMO() {
        im a2;
        return a2.k - System.currentTimeMillis();
    }

    public void c() {
        im a2;
        a2.ALLATORIxDEMO(a2.ALLATORIxDEMO);
    }

    public void ALLATORIxDEMO(long a2) {
        a.y = true;
        a.k = System.currentTimeMillis() + a2;
    }

    public void ALLATORIxDEMO() {
        a.y = false;
    }

    public boolean x() {
        im a2;
        boolean a3 = a2.f();
        if (a3) {
            a2.ALLATORIxDEMO();
        }
        return a3;
    }

    public boolean f() {
        im a2;
        return a2.y && a2.c();
    }

    public boolean c() {
        im a2;
        return System.currentTimeMillis() >= a2.k;
    }

    public boolean ALLATORIxDEMO() {
        im a2;
        if (!a2.y) {
            a2.c();
        }
        return a2.x();
    }
}

