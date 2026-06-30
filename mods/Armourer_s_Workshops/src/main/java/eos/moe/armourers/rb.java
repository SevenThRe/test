/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.armourers;

import eos.moe.armourers.ib;
import eos.moe.armourers.jb;
import eos.moe.armourers.ob;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class rb {
    private long z;
    private ib t;
    private ob w;
    private int r;
    private jb l;
    private Exception c;
    private long v;
    private boolean s;
    private boolean m;
    private String j;

    public void y(long a2) {
        a.z = a2;
    }

    public void r(jb a2) {
        a.l = a2;
    }

    public ib r() {
        rb a2;
        return a2.t;
    }

    public void r(ob a2) {
        a.w = a2;
    }

    private /* synthetic */ void z() {
        rb a2;
        rb rb2 = a2;
        rb2.w = ob.s;
        rb2.t = ib.j;
    }

    public void y(Exception a2) {
        rb a3;
        a3.l = jb.c;
        a3.c = a2;
        a3.z();
    }

    public void y(boolean a2) {
        a.m = a2;
    }

    public jb r() {
        rb a2;
        return a2.l;
    }

    public void r(String a2) {
        a.j = a2;
    }

    public ob r() {
        rb a2;
        return a2.w;
    }

    public Exception r() {
        rb a2;
        return a2.c;
    }

    public void r(long a22) {
        rb a3;
        rb rb2 = a3;
        rb2.v += a22;
        if (rb2.z > 0L) {
            rb rb3 = a3;
            a3.r = (int)(rb3.v * 100L / a3.z);
            if (rb3.r > 100) {
                a3.r = 100;
            }
        }
        rb rb4 = a3;
        while (rb4.m) {
            try {
                Thread.sleep(150L);
                rb4 = a3;
            }
            catch (InterruptedException a22) {
                rb4 = a3;
            }
        }
    }

    public void y() {
        rb a2;
        rb rb2 = a2;
        rb rb3 = a2;
        a2.z();
        rb3.j = null;
        rb3.z = 0L;
        rb2.v = 0L;
        rb2.r = 0;
    }

    public long y() {
        rb a2;
        return a2.z;
    }

    public String r() {
        rb a2;
        return a2.j;
    }

    public long r() {
        rb a2;
        return a2.v;
    }

    public void r() {
        rb a2;
        a2.l = jb.v;
        a2.r = 100;
        a2.z();
    }

    public boolean y() {
        rb a2;
        return a2.m;
    }

    public int r() {
        rb a2;
        return a2.r;
    }

    public void r(Exception a2) {
        a.c = a2;
    }

    public rb() {
        rb a2;
        rb rb2 = a2;
        rb2.z();
    }

    public boolean r() {
        rb a2;
        return a2.s;
    }

    public void r(ib a2) {
        a.t = a2;
    }

    public void r(boolean a2) {
        a.s = a2;
    }

    public void r(int a2) {
        a.r = a2;
    }
}

