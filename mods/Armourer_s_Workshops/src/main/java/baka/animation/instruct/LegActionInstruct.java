/*
 * Decompiled with CFR 0.152.
 */
package baka.animation.instruct;

import baka.animation.instruct.InstructBase;
import goblinbob.mobends.standard.data.PlayerData;

public class LegActionInstruct
extends InstructBase {
    private final boolean legAction;

    public LegActionInstruct(boolean legAction) {
        this.legAction = legAction;
    }

    @Override
    public void perform(PlayerData data) {
        super.perform(data);
    }

    @Override
    public boolean isFinish() {
        return true;
    }

    public boolean isLegAction() {
        return this.legAction;
    }
}

