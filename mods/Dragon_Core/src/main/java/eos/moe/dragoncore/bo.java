/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.gm;
import eos.moe.dragoncore.nd;
import eos.moe.dragoncore.nn;
import eos.moe.dragoncore.zi;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

public class bo
extends gm {
    public List<gm> k = new ArrayList<gm>();
    public Map<String, zi> ALLATORIxDEMO = new HashMap<String, zi>();

    public bo(nd a2) {
        super(a2);
        bo a3;
    }

    @Override
    public double ALLATORIxDEMO() {
        bo a2;
        double a3 = 0.0;
        for (gm a4 : a2.k) {
            a3 = a4.ALLATORIxDEMO();
        }
        return a3;
    }

    public String toString() {
        bo a2;
        StringJoiner a3 = new StringJoiner("; ");
        for (gm a4 : a2.k) {
            a3.add(a4.toString());
            if (!(a4 instanceof nn) || !((nn)a4).ALLATORIxDEMO) continue;
            break;
        }
        return a3.toString();
    }
}

