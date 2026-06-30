/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.entity.AbstractClientPlayer
 *  net.minecraft.util.math.MathHelper
 */
package goblinbob.mobends.standard.animation.bit.player;

import goblinbob.mobends.core.animation.bit.AnimationBit;
import goblinbob.mobends.core.client.event.DataUpdateHandler;
import goblinbob.mobends.standard.data.PlayerData;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.util.math.MathHelper;

public class FlyingAnimationBit
extends AnimationBit<PlayerData> {
    private static final String[] ACTIONS = new String[]{"flying"};
    private static final float PI = (float)Math.PI;
    private static final float PI_2 = (float)Math.PI * 2;
    private static final double STILL_MOTION_THRESHOLD = 0.1;
    private float transformTransition = 0.0f;

    @Override
    public String[] getActions(PlayerData entityData) {
        return ACTIONS;
    }

    @Override
    public void perform(PlayerData data) {
        AbstractClientPlayer player = (AbstractClientPlayer)data.getEntity();
        double magnitude = data.getInterpolatedMotionMagnitude();
        float ticks = DataUpdateHandler.getTicks();
        float forwardMomentum = MathHelper.clamp((float)((float)data.getForwardMomentum()), (float)-1.0f, (float)1.0f);
        float sideMomentum = MathHelper.clamp((float)((float)data.getSidewaysMomentum()), (float)-1.0f, (float)1.0f);
        double xzMomentum = data.getInterpolatedXZMotionMagnitude();
        float headPitch = ((Float)data.headPitch.get()).floatValue();
        float headYaw = ((Float)data.headYaw.get()).floatValue();
        float headYawAbs = MathHelper.abs((float)headYaw);
        float yMomentumAngle = (float)MathHelper.atan2((double)xzMomentum, (double)data.getMotionY()) * 180.0f / (float)Math.PI;
        if (player.isSprinting() && !data.isDrawingBow() && data.getTicksAfterAttack() >= 10.0f) {
            float speedFactor = MathHelper.clamp((float)((float)magnitude), (float)0.0f, (float)0.2f) / 0.2f;
            data.centerRotation.setSmoothness(1.0f).orientX(yMomentumAngle * speedFactor).rotateZ(headYaw);
            float bodyRotationX = MathHelper.clamp((float)(headPitch * 0.8f), (float)-60.0f, (float)0.0f);
            data.head.rotation.setSmoothness(1.0f).orientY(headYaw).rotateX(headPitch - bodyRotationX - yMomentumAngle * speedFactor);
            data.body.rotation.setSmoothness(0.7f).orientX(bodyRotationX);
            data.leftArm.rotation.setSmoothness(0.7f).orientX(-bodyRotationX).rotateZ(-60.0f + 55.0f * speedFactor - headYawAbs * 0.5f);
            data.rightArm.rotation.setSmoothness(0.7f).orientX(-bodyRotationX).rotateZ(60.0f - 55.0f * speedFactor + headYawAbs * 0.5f);
            data.leftForeArm.rotation.setSmoothness(0.7f).orientZero();
            data.rightForeArm.rotation.setSmoothness(0.7f).orientZero();
            data.leftLeg.rotation.setSmoothness(0.7f).orientZ(-5.0f);
            data.rightLeg.rotation.setSmoothness(0.7f).orientZ(5.0f);
            data.leftForeLeg.rotation.setSmoothness(0.7f).orientX(0.0f);
            data.rightForeLeg.rotation.setSmoothness(0.7f).orientX(0.0f);
        } else if (magnitude < 0.1) {
            float armSway = (MathHelper.cos((float)(ticks * 0.0825f)) + 1.0f) / 2.0f;
            float armSway2 = (-MathHelper.sin((float)(ticks * 0.0825f)) + 1.0f) / 2.0f;
            float legFlap = MathHelper.cos((float)(ticks * 0.125f));
            float legFlap2 = MathHelper.sin((float)(ticks * 0.125f));
            float foreArmSway = ticks * 0.1625f % ((float)Math.PI * 2) / ((float)Math.PI * 2);
            float foreArmStretch = armSway * 2.0f;
            foreArmStretch -= 1.0f;
            foreArmStretch = Math.max(foreArmStretch, 0.0f);
            data.leftArm.rotation.setSmoothness(0.3f).orientX(armSway2 * 30.0f - 15.0f).rotateZ(-armSway * 30.0f);
            data.rightArm.rotation.setSmoothness(0.3f).orientX(armSway2 * 30.0f - 15.0f).rotateZ(armSway * 30.0f);
            data.leftForeArm.rotation.setSmoothness(0.3f).orientX(armSway2 * -40.0f);
            data.rightForeArm.rotation.setSmoothness(0.3f).orientX(armSway2 * -40.0f);
            data.leftLeg.rotation.setSmoothness(0.3f).orientZ(-5.0f + legFlap * 3.0f).rotateX(-25.0f + legFlap2 * 5.0f);
            data.rightLeg.rotation.setSmoothness(0.3f).orientZ(5.0f - legFlap * 3.0f).rotateX(-6.0f + legFlap2 * 5.0f);
            data.leftForeLeg.rotation.setSmoothness(0.4f).orientX(20.0f - legFlap2 * 15.0f);
            data.rightForeLeg.rotation.setSmoothness(0.4f).orientX(5.0f);
            data.body.rotation.orientX(armSway * 10.0f);
            data.centerRotation.orientZero();
            data.head.rotation.setSmoothness(1.0f).orientX(headPitch).rotateY(headYaw);
        } else {
            data.centerRotation.orientZero();
            data.centerRotation.rotateX(forwardMomentum * 50.0f);
            data.body.rotation.orientZero();
            data.leftArm.rotation.orientX(forwardMomentum * 90.0f).localRotateZ(sideMomentum * -80.0f - 20.0f);
            data.rightArm.rotation.orientX(forwardMomentum * 90.0f).localRotateZ(sideMomentum * -80.0f + 20.0f);
            data.leftForeArm.rotation.orientZero();
            data.rightForeArm.rotation.orientZero();
            data.leftLeg.rotation.orientX(-45.0f).localRotateZ(sideMomentum * -40.0f - 5.0f);
            data.rightLeg.rotation.orientX(-6.0f).localRotateZ(sideMomentum * -40.0f + 5.0f);
            data.leftForeLeg.rotation.orientX(30.0f);
            data.rightForeLeg.rotation.orientX(10.0f);
            data.head.rotation.setSmoothness(1.0f).orientX(headPitch).rotateX(-forwardMomentum * 50.0f);
            if (!data.isDrawingBow()) {
                data.centerRotation.localRotateY(-headYaw);
            }
        }
        data.renderRotation.setSmoothness(0.7f).orientX(0.0f);
        data.globalOffset.slideToZero(0.7f);
    }
}

