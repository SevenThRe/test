/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.e;
import eos.moe.dragoncore.f;
import java.util.ArrayList;

public class ia<T>
implements e<T> {
    private T y;
    private T k;
    private ArrayList<f<T>> ALLATORIxDEMO = new ArrayList();

    public ia(T a2) {
        ia a3;
        a3.y = a2;
        a3.k = a2;
    }

    @Override
    public void c(f<T> a2) {
        ia a3;
        a3.ALLATORIxDEMO.add(a2);
    }

    @Override
    public void ALLATORIxDEMO(f<T> a2) {
        ia a3;
        a3.ALLATORIxDEMO.remove(a2);
    }

    @Override
    public void ALLATORIxDEMO() {
        ia a2;
        for (f<T> a3 : a2.ALLATORIxDEMO) {
            a3.ALLATORIxDEMO(a2.k, a2.y);
        }
    }

    @Override
    public T ALLATORIxDEMO() {
        ia a2;
        return a2.y;
    }

    @Override
    public void ALLATORIxDEMO(T a2) {
        ia a3;
        a3.k = a3.y;
        a3.y = a2;
        a3.ALLATORIxDEMO();
    }

    public String toString() {
        ia a2;
        return a2.y.toString();
    }
}

