/*
 * Decompiled with CFR 0.152.
 */
package baka.animation.instruct;

import baka.animation.instruct.InstructBase;
import goblinbob.mobends.standard.data.PlayerData;

public class DelayInstruct
extends InstructBase {
    protected long stopTime = -1L;
    private final long delayMs;

    public DelayInstruct(long delayMs) {
        this.delayMs = delayMs;
    }

    @Override
    public void start() {
        if (this.stopTime == -1L) {
            this.stopTime = System.currentTimeMillis() + this.delayMs;
        }
    }

    @Override
    public void perform(PlayerData data) {
        super.perform(data);
    }

    @Override
    public boolean isFinish() {
        return this.stopTime != -1L && System.currentTimeMillis() >= this.stopTime;
    }

    @Override
    public long getWaitTime(PlayerData data) {
        return this.stopTime - System.currentTimeMillis();
    }
}

