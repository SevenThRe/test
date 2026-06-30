/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.math.MathHelper
 */
package goblinbob.mobends.standard.animation.bit.biped;

import goblinbob.mobends.core.animation.bit.AnimationBit;
import goblinbob.mobends.core.client.event.DataUpdateHandler;
import goblinbob.mobends.standard.data.BipedEntityData;
import net.minecraft.util.math.MathHelper;

public class FallingAnimationBit
extends AnimationBit<BipedEntityData<?>> {
    private static final String[] ACTIONS = new String[]{"falling"};
    public static final float TICKS_BEFORE_FALLING = 10.0f;
    public static final float FALLING_TRANSITION_TICKS = 80.0f;

    @Override
    public String[] getActions(BipedEntityData<?> entityData) {
        return ACTIONS;
    }

    @Override
    public void perform(BipedEntityData<?> data) {
        data.centerRotation.setSmoothness(0.3f).orientZero();
        data.head.rotation.orientX(((Float)data.headPitch.get()).floatValue()).rotateY(((Float)data.headYaw.get()).floatValue());
        data.body.rotation.orientY(0.0f).setSmoothness(0.5f);
        float ticks = DataUpdateHandler.getTicks() * 0.5f;
        float rightArmDelay = 1.0f;
        float armSpan = 20.0f;
        float legSpan = 10.0f;
        float transition = (data.getTicksFalling() - 10.0f) / 80.0f;
        transition = MathHelper.clamp((float)transition, (float)0.0f, (float)1.0f);
        float s2 = 0.0f + transition * 0.9f;
        data.leftArm.rotation.setSmoothness(s2).orientZ(-90.0f + MathHelper.sin((float)ticks) * armSpan).rotateY(MathHelper.cos((float)ticks) * armSpan);
        data.rightArm.rotation.setSmoothness(s2).orientZ(90.0f + MathHelper.sin((float)(ticks + rightArmDelay)) * armSpan).rotateY(MathHelper.cos((float)(ticks + rightArmDelay)) * armSpan);
        data.leftForeArm.rotation.setSmoothness(s2).orientX(-15.0f);
        data.rightForeArm.rotation.setSmoothness(s2).orientX(-15.0f);
        data.leftLeg.rotation.setSmoothness(s2).orientX(MathHelper.sin((float)ticks) * legSpan).rotateZ(-20.0f + MathHelper.cos((float)ticks) * legSpan);
        data.rightLeg.rotation.setSmoothness(s2).orientX(MathHelper.sin((float)(ticks + rightArmDelay)) * legSpan).rotateZ(20.0f + MathHelper.cos((float)(ticks + rightArmDelay)) * legSpan);
        data.leftForeLeg.rotation.setSmoothness(s2).orientX(20.0f);
        data.rightForeLeg.rotation.setSmoothness(s2).orientX(20.0f);
        data.renderRotation.setSmoothness(s2).orientX(20.0f);
        data.head.rotation.setSmoothness(s2).rotateX(-20.0f);
    }
}

