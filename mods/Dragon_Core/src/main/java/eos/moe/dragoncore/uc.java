/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.bc;
import eos.moe.dragoncore.mc;
import eos.moe.dragoncore.vc;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class uc {
    private mc x;
    private long v;
    private long m;
    private int c;
    private vc q;
    private String b;
    private bc o;
    private Exception y;
    private boolean k;
    private boolean ALLATORIxDEMO;

    public uc() {
        uc a2;
        a2.ALLATORIxDEMO();
    }

    public void c(long a2) {
        uc a3;
        a3.m += a2;
        if (a3.v > 0L) {
            a3.c = (int)(a3.m * 100L / a3.v);
            if (a3.c > 100) {
                a3.c = 100;
            }
        }
        while (a3.ALLATORIxDEMO) {
            try {
                Thread.sleep(150L);
            }
            catch (InterruptedException interruptedException) {}
        }
    }

    public void f() {
        uc a2;
        a2.o = bc.b;
        a2.c = 100;
        a2.ALLATORIxDEMO();
    }

    public void c(Exception a2) {
        uc a3;
        a3.o = bc.y;
        a3.y = a2;
        a3.ALLATORIxDEMO();
    }

    public void c() {
        uc a2;
        a2.ALLATORIxDEMO();
        a2.b = null;
        a2.v = 0L;
        a2.m = 0L;
        a2.c = 0;
    }

    private /* synthetic */ void ALLATORIxDEMO() {
        a.q = vc.v;
        a.x = mc.y;
    }

    public mc ALLATORIxDEMO() {
        uc a2;
        return a2.x;
    }

    public void ALLATORIxDEMO(mc a2) {
        a.x = a2;
    }

    public long c() {
        uc a2;
        return a2.v;
    }

    public void ALLATORIxDEMO(long a2) {
        a.v = a2;
    }

    public long ALLATORIxDEMO() {
        uc a2;
        return a2.m;
    }

    public int ALLATORIxDEMO() {
        uc a2;
        return a2.c;
    }

    public void ALLATORIxDEMO(int a2) {
        a.c = a2;
    }

    public vc ALLATORIxDEMO() {
        uc a2;
        return a2.q;
    }

    public void ALLATORIxDEMO(vc a2) {
        a.q = a2;
    }

    public String ALLATORIxDEMO() {
        uc a2;
        return a2.b;
    }

    public void ALLATORIxDEMO(String a2) {
        a.b = a2;
    }

    public bc ALLATORIxDEMO() {
        uc a2;
        return a2.o;
    }

    public void ALLATORIxDEMO(bc a2) {
        a.o = a2;
    }

    public Exception ALLATORIxDEMO() {
        uc a2;
        return a2.y;
    }

    public void ALLATORIxDEMO(Exception a2) {
        a.y = a2;
    }

    public boolean c() {
        uc a2;
        return a2.k;
    }

    public void c(boolean a2) {
        a.k = a2;
    }

    public boolean ALLATORIxDEMO() {
        uc a2;
        return a2.ALLATORIxDEMO;
    }

    public void ALLATORIxDEMO(boolean a2) {
        a.ALLATORIxDEMO = a2;
    }
}

