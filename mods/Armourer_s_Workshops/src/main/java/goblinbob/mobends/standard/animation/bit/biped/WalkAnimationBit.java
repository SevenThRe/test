/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.math.MathHelper
 */
package goblinbob.mobends.standard.animation.bit.biped;

import goblinbob.mobends.core.animation.bit.AnimationBit;
import goblinbob.mobends.core.data.LivingEntityData;
import goblinbob.mobends.standard.data.BipedEntityData;
import net.minecraft.util.math.MathHelper;

public class WalkAnimationBit<T extends BipedEntityData<?>>
extends AnimationBit<T> {
    private static final String[] ACTIONS = new String[]{"walk"};
    protected final float KNEEL_DURATION = 0.15f;

    @Override
    public String[] getActions(T entityData) {
        return ACTIONS;
    }

    @Override
    public void perform(T data) {
        ((BipedEntityData)data).localOffset.slideToZero(0.3f);
        ((BipedEntityData)data).globalOffset.slideToZero(0.3f);
        ((BipedEntityData)data).centerRotation.setSmoothness(0.3f).orientZero();
        ((BipedEntityData)data).renderRotation.setSmoothness(0.3f).orientZero();
        ((BipedEntityData)data).renderRightItemRotation.setSmoothness(0.3f).orientZero();
        ((BipedEntityData)data).renderLeftItemRotation.setSmoothness(0.3f).orientZero();
        float PI = (float)Math.PI;
        float limbSwing = ((Float)((BipedEntityData)data).limbSwing.get()).floatValue() * 0.6662f;
        float armSwingAmount = ((Float)((BipedEntityData)data).limbSwingAmount.get()).floatValue() * 0.5f / (float)Math.PI * 180.0f;
        ((BipedEntityData)data).rightArm.rotation.setSmoothness(0.8f).orientX(MathHelper.cos((float)(limbSwing + (float)Math.PI)) * armSwingAmount).rotateZ(5.0f);
        ((BipedEntityData)data).leftArm.rotation.setSmoothness(0.8f).orientX(MathHelper.cos((float)limbSwing) * armSwingAmount).rotateZ(-5.0f);
        float legSwingAmount = 0.7f * ((Float)((BipedEntityData)data).limbSwingAmount.get()).floatValue() / (float)Math.PI * 180.0f;
        ((BipedEntityData)data).rightLeg.rotation.setSmoothness(1.0f).orientX(-5.0f + MathHelper.cos((float)limbSwing) * legSwingAmount).rotateZ(2.0f);
        ((BipedEntityData)data).leftLeg.rotation.setSmoothness(1.0f).orientX(-5.0f + MathHelper.cos((float)(limbSwing + (float)Math.PI)) * legSwingAmount).rotateZ(-2.0f);
        float var = limbSwing / (float)Math.PI % 2.0f;
        ((BipedEntityData)data).leftForeLeg.rotation.setSmoothness(0.5f).orientX(var > 1.0f ? 45.0f : 0.0f);
        ((BipedEntityData)data).rightForeLeg.rotation.setSmoothness(0.5f).orientX(var > 1.0f ? 0.0f : 45.0f);
        ((BipedEntityData)data).leftForeArm.rotation.setSmoothness(0.8f).orientX(MathHelper.cos((float)(limbSwing + 1.5707964f)) * -10.0f - 10.0f);
        ((BipedEntityData)data).rightForeArm.rotation.setSmoothness(0.8f).orientX(MathHelper.cos((float)limbSwing) * -10.0f - 10.0f);
        float bodyRotationY = MathHelper.cos((float)limbSwing) * -20.0f;
        float bodyRotationX = MathHelper.cos((float)(limbSwing * 2.0f)) * 5.0f + 3.0f;
        float var10 = ((Float)((BipedEntityData)data).headYaw.get()).floatValue() * 0.1f;
        var10 = Math.max(-10.0f, Math.min(var10, 10.0f));
        ((BipedEntityData)data).body.rotation.setSmoothness(0.5f).orientY(bodyRotationY).rotateX(bodyRotationX).rotateZ(-var10);
        ((BipedEntityData)data).head.rotation.setSmoothness(0.5f).orientX(((Float)((BipedEntityData)data).headPitch.get()).floatValue() - bodyRotationX).rotateY(((Float)((BipedEntityData)data).headYaw.get()).floatValue() - bodyRotationY);
        ((BipedEntityData)data).globalOffset.slideY(MathHelper.cos((float)(limbSwing * 2.0f)) * 0.6f);
        float touchdown = Math.min(((LivingEntityData)data).getTicksAfterTouchdown() * 0.15f, 1.0f);
        if (touchdown < 1.0f) {
            ((BipedEntityData)data).body.rotation.setSmoothness(1.0f);
            ((BipedEntityData)data).body.rotation.orient(20.0f * (1.0f - touchdown), 1.0f, 0.0f, 0.0f);
            ((BipedEntityData)data).globalOffset.setY((float)(-Math.sin((double)touchdown * Math.PI)) * 2.0f);
        }
    }
}

