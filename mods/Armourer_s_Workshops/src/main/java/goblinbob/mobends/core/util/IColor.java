/*
 * Decompiled with CFR 0.152.
 */
package goblinbob.mobends.core.util;

import goblinbob.mobends.core.util.IColorRead;

public interface IColor
extends IColorRead {
    public void setR(float var1);

    public void setG(float var1);

    public void setB(float var1);

    public void setA(float var1);

    public void set(float var1, float var2, float var3, float var4);

    public void add(float var1, float var2, float var3, float var4);
}

