/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.eu;
import eos.moe.dragoncore.wp;
import java.util.ArrayList;

public class by {
    public ArrayList<wp> k = new ArrayList();
    public int ALLATORIxDEMO;

    public by() {
        by a2;
    }

    public void ALLATORIxDEMO(eu a2) {
        by a3;
        for (wp a4 : a3.k) {
            a4.ALLATORIxDEMO(a2);
        }
    }

    public void ALLATORIxDEMO(boolean a2, float a3) {
        by a4;
        if (a4.ALLATORIxDEMO >= 0) {
            a4.k.get(a4.ALLATORIxDEMO).f(a2, a3);
        }
    }

    public void ALLATORIxDEMO(int a2) {
        by a3;
        a3.ALLATORIxDEMO = a2 >= a3.k.size() ? -1 : a2;
    }

    public wp ALLATORIxDEMO() {
        by a2;
        return a2.ALLATORIxDEMO < 0 || a2.ALLATORIxDEMO >= a2.k.size() ? null : a2.k.get(a2.ALLATORIxDEMO);
    }
}

