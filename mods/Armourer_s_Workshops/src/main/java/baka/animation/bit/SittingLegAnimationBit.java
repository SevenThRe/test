/*
 * Decompiled with CFR 0.152.
 */
package baka.animation.bit;

import goblinbob.mobends.core.animation.bit.AnimationBit;
import goblinbob.mobends.standard.data.PlayerData;

public class SittingLegAnimationBit
extends AnimationBit<PlayerData> {
    private static final String[] ACTIONS = new String[]{"sitting"};

    @Override
    public String[] getActions(PlayerData entityData) {
        return ACTIONS;
    }

    @Override
    public void perform(PlayerData data) {
        data.centerRotation.setSmoothness(0.3f).orientZero();
        data.leftLeg.rotation.orientX(-90.0f).rotateZ(-10.0f).rotateY(-15.0f);
        data.rightLeg.rotation.orientX(-90.0f).rotateZ(10.0f).rotateY(15.0f);
        data.leftForeLeg.rotation.orientX(10.0f);
        data.rightForeLeg.rotation.orientX(10.0f);
        data.renderRotation.orientZero();
    }
}

