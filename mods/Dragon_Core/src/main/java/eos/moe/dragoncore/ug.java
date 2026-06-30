/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.zi;
import java.util.function.DoubleSupplier;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class ug
extends zi {
    private DoubleSupplier ALLATORIxDEMO;

    public ug(String a2, DoubleSupplier a3) {
        super(a2, 0.0);
        ug a4;
        a4.ALLATORIxDEMO = a3;
    }

    @Override
    public double ALLATORIxDEMO() {
        ug a2;
        return a2.ALLATORIxDEMO.getAsDouble();
    }

    public void ALLATORIxDEMO(DoubleSupplier a2) {
        a.ALLATORIxDEMO = a2;
    }

    public DoubleSupplier ALLATORIxDEMO() {
        ug a2;
        return a2.ALLATORIxDEMO;
    }
}

