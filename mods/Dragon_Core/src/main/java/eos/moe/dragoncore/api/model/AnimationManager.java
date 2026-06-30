/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.dragoncore.api.model;

import eos.moe.dragoncore.api.model.AnimationEntityModel;

public interface AnimationManager {
    public void applyAnimation(AnimationEntityModel var1, float var2);

    public void applyAnimation(AnimationEntityModel var1);

    public boolean isOnPlayAnimation();

    public boolean needPlaySwordTrail();
}

