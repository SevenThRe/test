/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.dragoncore;

import java.util.ArrayList;
import java.util.List;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class ph<T> {
    private List<ph<T>> o;
    private ph<T> y;
    private T k;
    private boolean ALLATORIxDEMO;

    public ph() {
        a2(null);
        ph<Object> a2;
    }

    public ph(T a2) {
        ph a3;
        a3.o = new ArrayList<ph<T>>();
        a3.k = a2;
    }

    public boolean f() {
        ph a2;
        return a2.ALLATORIxDEMO;
    }

    public void ALLATORIxDEMO(boolean a2) {
        a.ALLATORIxDEMO = a2;
    }

    public ph(ph<T> a2) {
        a3(a2, null);
        ph<Object> a3;
    }

    public ph(ph<T> a2, T a3) {
        ph a4;
        a4.y = a2;
        a4.o = new ArrayList<ph<T>>();
        a4.k = a3;
    }

    public void ALLATORIxDEMO(ph<T> a2) {
        ph a3;
        a3.o.add(a2);
    }

    public boolean ALLATORIxDEMO(ph<T> a2) {
        ph a3;
        return a3.o.remove(a2);
    }

    public List<ph<T>> ALLATORIxDEMO() {
        ph a2;
        return a2.o;
    }

    public ph<T> ALLATORIxDEMO() {
        ph a2;
        return a2.y;
    }

    public T ALLATORIxDEMO() {
        ph a2;
        return a2.k;
    }

    public String toString() {
        ph a2;
        return "" + a2.k;
    }

    public boolean c() {
        ph a2;
        return a2.o.size() > 0;
    }

    public boolean ALLATORIxDEMO() {
        ph a2;
        return a2.y != null;
    }
}

