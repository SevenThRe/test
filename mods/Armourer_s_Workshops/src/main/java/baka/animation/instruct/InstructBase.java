/*
 * Decompiled with CFR 0.152.
 */
package baka.animation.instruct;

import goblinbob.mobends.standard.data.PlayerData;

public abstract class InstructBase {
    protected boolean finish = false;
    protected boolean performed = false;

    public void perform(PlayerData data) {
        this.finish = true;
        this.performed = true;
    }

    public boolean isFinish() {
        return this.finish;
    }

    public void start() {
    }

    public long getWaitTime(PlayerData data) {
        return 0L;
    }

    public boolean isPerformed() {
        return this.performed;
    }
}

