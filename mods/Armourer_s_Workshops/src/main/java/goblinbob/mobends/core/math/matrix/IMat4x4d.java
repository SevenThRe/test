/*
 * Decompiled with CFR 0.152.
 */
package goblinbob.mobends.core.math.matrix;

import goblinbob.mobends.core.math.matrix.IMatd;

public interface IMat4x4d
extends IMatd {
    @Override
    default public int getCols() {
        return 4;
    }

    @Override
    default public int getRows() {
        return 4;
    }

    public void copyFrom(IMat4x4d var1);
}

