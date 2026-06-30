/*
 * Decompiled with CFR 0.152.
 */
package goblinbob.mobends.core.math.matrix;

import goblinbob.mobends.core.math.matrix.IMatd;

public interface IMat3x3d
extends IMatd {
    @Override
    default public int getCols() {
        return 3;
    }

    @Override
    default public int getRows() {
        return 3;
    }

    public void copyFrom(IMat3x3d var1);
}

