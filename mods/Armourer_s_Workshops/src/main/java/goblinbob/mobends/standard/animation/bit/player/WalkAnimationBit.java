/*
 * Decompiled with CFR 0.152.
 */
package goblinbob.mobends.standard.animation.bit.player;

import goblinbob.mobends.standard.data.PlayerData;

public class WalkAnimationBit
extends goblinbob.mobends.standard.animation.bit.biped.WalkAnimationBit<PlayerData> {
    @Override
    public void perform(PlayerData data) {
        super.perform(data);
        if (data.getTicksAfterAttack() < 10.0f) {
            data.head.rotation.setSmoothness(0.5f).orientX(((Float)data.headPitch.get()).floatValue()).rotateY(((Float)data.headYaw.get()).floatValue());
        }
    }
}

