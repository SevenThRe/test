/*
 * Decompiled with CFR 0.152.
 */
package goblinbob.mobends.core.animation.layer;

import goblinbob.mobends.core.animation.bit.AnimationBit;
import goblinbob.mobends.core.animation.layer.AnimationLayer;
import goblinbob.mobends.core.data.EntityData;

public class HardAnimationLayer<T extends EntityData<?>>
extends AnimationLayer<T> {
    protected AnimationBit<T> performedBit;
    protected AnimationBit<T> previousBit;

    public void playBit(AnimationBit<? extends T> bit, T entityData) {
        this.previousBit = this.performedBit;
        this.performedBit = bit;
        this.performedBit.setupForPlay(this, entityData);
    }

    public void playOrContinueBit(AnimationBit<? extends T> bit, T entityData) {
        if (!this.isPlaying(bit)) {
            this.playBit(bit, entityData);
        }
    }

    @Override
    public void perform(T entityData) {
        if (this.performedBit != null) {
            this.performedBit.perform(entityData);
        }
    }

    public boolean isPlaying(AnimationBit<? extends T> bit) {
        return bit == this.performedBit;
    }

    public boolean isPlaying() {
        return this.performedBit != null;
    }

    public void clearAnimation() {
        this.performedBit = null;
    }

    public AnimationBit<T> getPerformedBit() {
        return this.performedBit;
    }

    @Override
    public String[] getActions(T entityData) {
        if (this.isPlaying()) {
            return this.getPerformedBit().getActions(entityData);
        }
        return new String[0];
    }
}

