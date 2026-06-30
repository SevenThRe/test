/*
 * Decompiled with CFR 0.152.
 */
package goblinbob.mobends.standard.animation.bit.biped;

import goblinbob.mobends.core.animation.bit.AnimationBit;
import goblinbob.mobends.standard.data.BipedEntityData;

public class SittingAnimationBit
extends AnimationBit<BipedEntityData<?>> {
    private static final String[] ACTIONS = new String[]{"sitting"};

    @Override
    public String[] getActions(BipedEntityData<?> entityData) {
        return ACTIONS;
    }

    @Override
    public void perform(BipedEntityData<?> data) {
        data.centerRotation.setSmoothness(0.3f).orientZero();
        data.head.rotation.orientX(((Float)data.headPitch.get()).floatValue()).rotateY(((Float)data.headYaw.get()).floatValue());
        data.body.rotation.orientY(0.0f).setSmoothness(0.5f);
        data.leftLeg.rotation.orientX(-90.0f).rotateZ(-10.0f).rotateY(-15.0f);
        data.rightLeg.rotation.orientX(-90.0f).rotateZ(10.0f).rotateY(15.0f);
        data.leftForeLeg.rotation.orientX(10.0f);
        data.rightForeLeg.rotation.orientX(10.0f);
        data.leftArm.rotation.orientX(0.0f).rotateZ(-10.0f);
        data.leftForeArm.rotation.orientX(-10.0f);
        data.rightArm.rotation.orientX(0.0f).rotateZ(10.0f);
        data.rightForeArm.rotation.orientX(-10.0f);
        data.renderRotation.orientZero();
        data.renderLeftItemRotation.orientZero();
        data.renderRightItemRotation.orientZero();
    }
}

