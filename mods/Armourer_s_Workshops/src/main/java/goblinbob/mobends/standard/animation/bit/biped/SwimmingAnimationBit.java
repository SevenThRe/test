/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.math.MathHelper
 */
package goblinbob.mobends.standard.animation.bit.biped;

import goblinbob.mobends.core.animation.bit.AnimationBit;
import goblinbob.mobends.core.client.event.DataUpdateHandler;
import goblinbob.mobends.core.util.Tween;
import goblinbob.mobends.standard.data.BipedEntityData;
import net.minecraft.util.math.MathHelper;

public class SwimmingAnimationBit
extends AnimationBit<BipedEntityData<?>> {
    private static final String[] ACTIONS = new String[]{"swimming", "swimming_surface"};
    private static final String[] ACTIONS_UNDERWATER = new String[]{"swimming", "swimming_deep"};
    private static final float PI = (float)Math.PI;
    private static final float PI_2 = (float)Math.PI * 2;
    private float transformTransition = 0.0f;
    private float transitionSpeed = 0.1f;

    @Override
    public String[] getActions(BipedEntityData<?> data) {
        if (data.isUnderwater()) {
            return ACTIONS_UNDERWATER;
        }
        return ACTIONS;
    }

    @Override
    public void onPlay(BipedEntityData<?> data) {
        this.transformTransition = 0.0f;
        this.transitionSpeed = 0.1f;
    }

    @Override
    public void perform(BipedEntityData<?> data) {
        float ticks = DataUpdateHandler.getTicks();
        float armSway = (MathHelper.func_76134_b((float)(ticks * 0.1625f)) + 1.0f) / 2.0f;
        float armSway2 = (-MathHelper.func_76126_a((float)(ticks * 0.1625f)) + 1.0f) / 2.0f;
        float legFlap = MathHelper.func_76134_b((float)(ticks * 0.4625f));
        float foreArmSway = ticks * 0.1625f % ((float)Math.PI * 2) / ((float)Math.PI * 2);
        float foreArmStretch = armSway * 2.0f;
        foreArmStretch -= 1.0f;
        foreArmStretch = Math.max(foreArmStretch, 0.0f);
        float t2 = (float)Tween.easeInOut(this.transformTransition, 3.0);
        if (data.isStillHorizontally() || data.isDrawingBow() || data.getTicksAfterAttack() < 10.0f || !data.isUnderwater()) {
            if (this.transformTransition > 0.0f) {
                this.transformTransition -= DataUpdateHandler.ticksPerFrame * this.transitionSpeed;
                this.transformTransition = Math.max(0.0f, this.transformTransition);
            }
            armSway = (MathHelper.func_76134_b((float)(ticks * 0.0825f)) + 1.0f) / 2.0f;
            armSway2 = (-MathHelper.func_76126_a((float)(ticks * 0.0825f)) + 1.0f) / 2.0f;
            legFlap = MathHelper.func_76134_b((float)(ticks * 0.2625f));
            data.leftArm.rotation.setSmoothness(0.3f).orientX(armSway2 * 30.0f - 15.0f).rotateZ(-armSway * 30.0f);
            data.rightArm.rotation.setSmoothness(0.3f).orientX(armSway2 * 30.0f - 15.0f).rotateZ(armSway * 30.0f);
            data.leftForeArm.rotation.setSmoothness(0.3f).orientX(armSway2 * -40.0f);
            data.rightForeArm.rotation.setSmoothness(0.3f).orientX(armSway2 * -40.0f);
            data.leftLeg.rotation.setSmoothness(0.3f).orientX(legFlap * 40.0f);
            data.rightLeg.rotation.setSmoothness(0.3f).orientX(-legFlap * 40.0f);
            data.leftForeLeg.rotation.setSmoothness(0.4f).orientX(5.0f);
            data.rightForeLeg.rotation.setSmoothness(0.4f).orientX(5.0f);
            data.body.rotation.orientX(armSway * 10.0f);
        } else {
            if (this.transformTransition < 1.0f) {
                this.transformTransition += DataUpdateHandler.ticksPerFrame * this.transitionSpeed;
                this.transformTransition = Math.min(this.transformTransition, 1.0f);
            }
            data.leftArm.rotation.setSmoothness(0.3f).orientX(armSway * -120.0f).rotateY(-90.0f * t2).rotateX(armSway * 20.0f);
            data.rightArm.rotation.setSmoothness(0.3f).orientX(armSway * -120.0f).rotateY(90.0f * t2).rotateX(armSway * 20.0f);
            data.leftForeArm.rotation.setSmoothness(0.3f).orientX(foreArmSway < 0.55f | (double)foreArmSway > 0.9 ? foreArmStretch * -60.0f : -60.0f);
            data.rightForeArm.rotation.setSmoothness(0.3f).orientX(foreArmSway < 0.55f | (double)foreArmSway > 0.9 ? foreArmStretch * -60.0f : -60.0f);
            data.leftLeg.rotation.setSmoothness(0.3f).orientX(legFlap * 40.0f);
            data.rightLeg.rotation.setSmoothness(0.3f).orientX(-legFlap * 40.0f);
            data.leftForeLeg.rotation.setSmoothness(0.4f).orientX(5.0f);
            data.rightForeLeg.rotation.setSmoothness(0.4f).orientX(5.0f);
            data.body.rotation.setSmoothness(0.5f).orientX(armSway * -20.0f);
            data.renderRightItemRotation.setSmoothness(0.3f).orientX(armSway * 50.0f);
        }
        data.head.rotation.setSmoothness(1.0f).orientX(((Float)data.headPitch.get()).floatValue()).rotateY(((Float)data.headYaw.get()).floatValue()).rotateX(-80.0f * t2);
        data.renderRotation.setSmoothness(0.7f).orientX(t2 * 80.0f);
        data.globalOffset.slideZ(-20.0f * t2, 0.7f);
        data.globalOffset.slideY(14.0f * t2, 0.7f);
        data.localOffset.slideToZero(0.3f);
    }
}

