/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.math.MathHelper
 */
package goblinbob.mobends.standard.animation.bit.biped;

import goblinbob.mobends.core.animation.bit.AnimationBit;
import goblinbob.mobends.core.data.EntityData;
import goblinbob.mobends.core.data.LivingEntityData;
import goblinbob.mobends.standard.data.BipedEntityData;
import net.minecraft.util.math.MathHelper;

public class JumpAnimationBit<T extends BipedEntityData<?>>
extends AnimationBit<T> {
    private static final String[] ACTIONS = new String[]{"jump"};

    @Override
    public String[] getActions(T entityData) {
        return ACTIONS;
    }

    @Override
    public void onPlay(T data) {
        ((BipedEntityData)data).renderRotation.identity();
        ((BipedEntityData)data).centerRotation.identity();
        ((BipedEntityData)data).body.rotation.orientInstantX(20.0f);
        ((BipedEntityData)data).rightLeg.rotation.orientInstantX(0.0f);
        ((BipedEntityData)data).leftLeg.rotation.orientInstantX(0.0f);
        ((BipedEntityData)data).rightForeLeg.rotation.orientInstantX(0.0f);
        ((BipedEntityData)data).leftForeLeg.rotation.orientInstantX(0.0f);
        ((BipedEntityData)data).rightArm.rotation.orientInstantZ(2.0f);
        ((BipedEntityData)data).leftArm.rotation.orientInstantZ(-2.0f);
        ((BipedEntityData)data).rightForeArm.rotation.orientInstantX(-20.0f);
        ((BipedEntityData)data).leftForeArm.rotation.orientInstantX(-20.0f);
    }

    @Override
    public void perform(T data) {
        if (((EntityData)data).getPrevMotionY() < 0.0 && ((EntityData)data).getMotionY() > 0.0) {
            this.onPlay(data);
        }
        Object biped = ((BipedEntityData)data).getEntity();
        ((BipedEntityData)data).globalOffset.slideToZero(0.3f);
        ((BipedEntityData)data).renderRotation.setSmoothness(0.3f).orientZero();
        ((BipedEntityData)data).centerRotation.setSmoothness(0.7f).orientZero();
        ((BipedEntityData)data).renderRightItemRotation.setSmoothness(0.3f).orientZero();
        ((BipedEntityData)data).renderLeftItemRotation.setSmoothness(0.3f).orientZero();
        float bodyRotationX = Math.max(1.0f - ((LivingEntityData)data).getTicksInAir() * 0.1f, 0.0f);
        ((BipedEntityData)data).body.rotation.setSmoothness(0.2f).orientX(bodyRotationX);
        ((BipedEntityData)data).rightArm.rotation.setSmoothness(0.05f).orientZ(45.0f);
        ((BipedEntityData)data).leftArm.rotation.setSmoothness(0.05f).orientZ(-45.0f);
        ((BipedEntityData)data).rightForeArm.rotation.setSmoothness(0.3f).orientX(0.0f);
        ((BipedEntityData)data).leftForeArm.rotation.setSmoothness(0.3f).orientX(0.0f);
        ((BipedEntityData)data).head.rotation.orientX(((Float)((BipedEntityData)data).headPitch.get()).floatValue() - bodyRotationX).rotateY(((Float)((BipedEntityData)data).headYaw.get()).floatValue());
        if (!((EntityData)data).isStillHorizontally()) {
            float PI = (float)Math.PI;
            float limbSwing = ((Float)((BipedEntityData)data).limbSwing.get()).floatValue() * 0.6662f;
            float limbSwingAmount = 0.7f * ((Float)((BipedEntityData)data).limbSwingAmount.get()).floatValue() / (float)Math.PI * 180.0f;
            ((BipedEntityData)data).rightLeg.rotation.setSmoothness(1.0f).orientX(-5.0f + MathHelper.cos((float)limbSwing) * limbSwingAmount);
            ((BipedEntityData)data).leftLeg.rotation.setSmoothness(1.0f).orientX(-5.0f + MathHelper.cos((float)(limbSwing + (float)Math.PI)) * limbSwingAmount);
            float var = limbSwing / (float)Math.PI % 2.0f;
            ((BipedEntityData)data).leftForeLeg.rotation.setSmoothness(0.3f).orientX(var > 1.0f ? 45 : 0);
            ((BipedEntityData)data).rightForeLeg.rotation.setSmoothness(0.3f).orientX(var > 1.0f ? 0 : 45);
            ((BipedEntityData)data).leftForeArm.rotation.setSmoothness(0.3f).orientX((MathHelper.cos((float)(limbSwing + 1.5707964f)) / 2.0f + 0.5f) * -20.0f);
            ((BipedEntityData)data).rightForeArm.rotation.setSmoothness(0.3f).orientX((MathHelper.cos((float)limbSwing) / 2.0f + 0.5f) * -20.0f);
        } else {
            ((BipedEntityData)data).rightLeg.rotation.setSmoothness(0.1f).orientZ(10.0f);
            ((BipedEntityData)data).rightLeg.rotation.setSmoothness(0.3f).rotateX(-45.0f);
            ((BipedEntityData)data).leftLeg.rotation.setSmoothness(0.1f).orientZ(-10.0f);
            ((BipedEntityData)data).leftLeg.rotation.setSmoothness(0.3f).rotateX(-17.0f);
            ((BipedEntityData)data).rightForeLeg.rotation.setSmoothness(0.3f).orientX(70.0f);
            ((BipedEntityData)data).leftForeLeg.rotation.setSmoothness(0.3f).orientX(17.0f);
        }
    }
}

