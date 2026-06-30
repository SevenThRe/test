/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.math.MathHelper
 */
package goblinbob.mobends.standard.animation.bit.player;

import goblinbob.mobends.core.animation.bit.AnimationBit;
import goblinbob.mobends.core.client.event.DataUpdateHandler;
import goblinbob.mobends.standard.data.PlayerData;
import net.minecraft.util.math.MathHelper;

public class SleepingAnimationBit
extends AnimationBit<PlayerData> {
    private static final String[] ACTIONS = new String[]{"sleeping"};

    @Override
    public String[] getActions(PlayerData entityData) {
        return ACTIONS;
    }

    @Override
    public void perform(PlayerData data) {
        data.localOffset.slideToZero(0.3f);
        data.globalOffset.slideToZero(0.3f);
        data.renderRotation.setSmoothness(0.3f).orientZero();
        data.centerRotation.setSmoothness(0.3f).orientZero();
        data.renderRightItemRotation.setSmoothness(0.3f).orientZero();
        data.renderLeftItemRotation.setSmoothness(0.3f).orientZero();
        data.rightLeg.rotation.orient(0.0f, 1.0f, 0.0f, 0.0f);
        data.rightLeg.rotation.rotate(2.0f, 0.0f, 0.0f, 1.0f);
        data.rightLeg.rotation.rotate(5.0f, 0.0f, 1.0f, 0.0f);
        data.leftLeg.rotation.orient(0.0f, 1.0f, 0.0f, 0.0f);
        data.leftLeg.rotation.rotate(-2.0f, 0.0f, 0.0f, 1.0f);
        data.leftLeg.rotation.rotate(-5.0f, 0.0f, 1.0f, 0.0f);
        data.rightForeLeg.rotation.orient(4.0f, 1.0f, 0.0f, 0.0f);
        data.leftForeLeg.rotation.orient(4.0f, 1.0f, 0.0f, 0.0f);
        data.rightForeArm.rotation.orient(-4.0f, 1.0f, 0.0f, 0.0f);
        data.leftForeArm.rotation.orient(-4.0f, 1.0f, 0.0f, 0.0f);
        float PI = (float)Math.PI;
        float phase = DataUpdateHandler.getTicks() / 10.0f;
        data.head.rotation.setSmoothness(1.0f).orientX((MathHelper.cos((float)phase) - 1.0f) / 2.0f * -3.0f);
        data.rightArm.rotation.setSmoothness(0.4f).orientX(0.0f).rotateZ(2.5f);
        data.leftArm.rotation.setSmoothness(0.4f).orientX(0.0f).rotateZ(-2.5f);
    }
}

