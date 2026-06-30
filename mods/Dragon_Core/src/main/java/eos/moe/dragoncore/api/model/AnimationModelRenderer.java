/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.dragoncore.api.model;

import eos.moe.dragoncore.bax;

public interface AnimationModelRenderer {
    default public void clear() {
    }

    default public boolean isApplyAnimation() {
        return false;
    }

    default public void setApplyAnimation(boolean a2) {
    }

    public void setRotateAngle(float var1, float var2, float var3);

    public void setOffsets(float var1, float var2, float var3);

    public bax getRotateAngle();

    public bax getOffsets();

    public bax getScaleFactor();

    public bax getStartRotationAngles();

    public void setScaleFactor(float var1, float var2, float var3);
}

