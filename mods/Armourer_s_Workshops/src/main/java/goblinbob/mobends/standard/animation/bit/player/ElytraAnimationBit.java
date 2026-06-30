/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.math.MathHelper
 */
package goblinbob.mobends.standard.animation.bit.player;

import goblinbob.mobends.core.animation.bit.AnimationBit;
import goblinbob.mobends.standard.data.PlayerData;
import net.minecraft.util.math.MathHelper;

public class ElytraAnimationBit
extends AnimationBit<PlayerData> {
    private static final String[] ACTIONS = new String[]{"elytra"};

    @Override
    public String[] getActions(PlayerData entityData) {
        return ACTIONS;
    }

    @Override
    public void perform(PlayerData data) {
        double magnitude = data.getInterpolatedMotionMagnitude();
        float headPitch = ((Float)data.headPitch.get()).floatValue();
        float headYaw = ((Float)data.headYaw.get()).floatValue();
        float headYawAbs = MathHelper.func_76135_e((float)headYaw);
        float speedFactor = MathHelper.func_76131_a((float)((float)magnitude), (float)0.0f, (float)0.2f) / 0.2f;
        data.head.rotation.setSmoothness(1.0f).orientY(headYaw).rotateX(-90.0f);
        data.body.rotation.setSmoothness(0.7f).orientX(0.0f);
        data.leftArm.rotation.setSmoothness(0.7f).orientX(0.0f).rotateZ(-60.0f + 55.0f * speedFactor - headYawAbs * 0.5f);
        data.rightArm.rotation.setSmoothness(0.7f).orientX(0.0f).rotateZ(60.0f - 55.0f * speedFactor + headYawAbs * 0.5f);
        data.leftForeArm.rotation.setSmoothness(0.7f).orientZero();
        data.rightForeArm.rotation.setSmoothness(0.7f).orientZero();
        data.leftLeg.rotation.setSmoothness(0.7f).orientZ(-5.0f);
        data.rightLeg.rotation.setSmoothness(0.7f).orientZ(5.0f);
        data.leftForeLeg.rotation.setSmoothness(0.7f).orientX(0.0f);
        data.rightForeLeg.rotation.setSmoothness(0.7f).orientX(0.0f);
        data.centerRotation.setSmoothness(1.0f).orientZero();
        data.renderRotation.setSmoothness(0.7f).orientX(0.0f);
        data.globalOffset.slideToZero(0.7f);
    }
}

