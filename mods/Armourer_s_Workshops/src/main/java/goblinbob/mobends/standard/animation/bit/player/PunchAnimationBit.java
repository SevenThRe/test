/*
 * Decompiled with CFR 0.152.
 */
package goblinbob.mobends.standard.animation.bit.player;

import goblinbob.mobends.core.animation.bit.AnimationBit;
import goblinbob.mobends.standard.data.PlayerData;

public class PunchAnimationBit
extends AnimationBit<PlayerData> {
    @Override
    public String[] getActions(PlayerData entityData) {
        return new String[]{"attack", "punch"};
    }

    @Override
    public void perform(PlayerData data) {
        data.rightArm.rotation.setSmoothness(0.3f).orientX(-90.0f).rotateZ(20.0f);
        data.leftArm.rotation.setSmoothness(0.3f).orientZ(-20.0f).rotateX(-90.0f);
        data.rightForeArm.rotation.setSmoothness(0.3f).orientX(-80.0f);
        data.leftForeArm.rotation.setSmoothness(0.3f).orientX(-80.0f);
        float renderRotationY = 0.0f;
        if (data.isStillHorizontally()) {
            renderRotationY = -20.0f;
            data.globalOffset.slideY(-2.0f);
            data.rightLeg.rotation.setSmoothness(0.3f).orientX(-30.0f).rotateZ(10.0f);
            data.leftLeg.rotation.setSmoothness(0.3f).orientX(-30.0f).rotateY(-25.0f).rotateZ(-10.0f);
            data.rightForeLeg.rotation.setSmoothness(0.3f).orientX(30.0f);
            data.leftForeLeg.rotation.setSmoothness(0.3f).orientX(30.0f);
        }
        if (data.getFistPunchArm()) {
            data.rightArm.rotation.setSmoothness(0.9f).orientY(-90.0f).rotateX(-90.0f + ((Float)data.headPitch.get()).floatValue()).rotateY(10.0f);
            data.rightForeArm.rotation.setSmoothness(0.9f).orientX(0.0f);
            data.body.rotation.setSmoothness(0.6f).orientY(-20.0f + renderRotationY);
            data.head.rotation.rotateY(20.0f);
        } else {
            data.leftArm.rotation.setSmoothness(0.9f).orientY(100.0f).rotateX(-90.0f + ((Float)data.headPitch.get()).floatValue()).rotateY(-16.0f);
            data.leftForeArm.rotation.setSmoothness(0.9f).orientX(0.0f);
            data.body.rotation.setSmoothness(0.6f).orientY(20.0f + renderRotationY);
            data.head.rotation.rotateY(-20.0f);
        }
        data.renderRotation.orientY(renderRotationY);
    }
}

