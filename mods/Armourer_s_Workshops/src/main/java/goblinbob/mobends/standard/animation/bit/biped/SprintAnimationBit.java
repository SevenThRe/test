/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.math.MathHelper
 */
package goblinbob.mobends.standard.animation.bit.biped;

import goblinbob.mobends.core.animation.bit.AnimationBit;
import goblinbob.mobends.standard.data.BipedEntityData;
import net.minecraft.util.math.MathHelper;

public class SprintAnimationBit<T extends BipedEntityData<?>>
extends AnimationBit<T> {
    private static String[] ACTIONS = new String[]{"sprint"};

    @Override
    public String[] getActions(T entityData) {
        return ACTIONS;
    }

    @Override
    public void perform(T data) {
        ((BipedEntityData)data).localOffset.slideToZero(0.3f);
        ((BipedEntityData)data).globalOffset.slideToZero(0.1f);
        ((BipedEntityData)data).centerRotation.setSmoothness(0.3f).orientZero();
        ((BipedEntityData)data).renderRotation.setSmoothness(0.3f).orientZero();
        ((BipedEntityData)data).renderRightItemRotation.setSmoothness(0.3f).orientZero();
        ((BipedEntityData)data).renderLeftItemRotation.setSmoothness(0.3f).orientZero();
        float headPitch = ((Float)((BipedEntityData)data).headPitch.get()).floatValue();
        float headYaw = ((Float)((BipedEntityData)data).headYaw.get()).floatValue();
        float PI = (float)Math.PI;
        float limbSwing = ((Float)((BipedEntityData)data).limbSwing.get()).floatValue() * 0.6662f * 0.8f;
        float armSwingAmount = ((Float)((BipedEntityData)data).limbSwingAmount.get()).floatValue() / (float)Math.PI * 180.0f * 1.1f;
        ((BipedEntityData)data).rightArm.rotation.setSmoothness(0.8f).orientX(MathHelper.func_76134_b((float)(limbSwing + (float)Math.PI)) * armSwingAmount).rotateZ(5.0f);
        ((BipedEntityData)data).leftArm.rotation.setSmoothness(0.8f).orientX(MathHelper.func_76134_b((float)limbSwing) * armSwingAmount).rotateZ(-5.0f);
        float legSwingAmount = 1.26f * ((Float)((BipedEntityData)data).limbSwingAmount.get()).floatValue() / (float)Math.PI * 180.0f;
        ((BipedEntityData)data).rightLeg.rotation.setSmoothness(1.0f).orientX(-5.0f + MathHelper.func_76134_b((float)limbSwing) * legSwingAmount).rotateZ(2.0f);
        ((BipedEntityData)data).leftLeg.rotation.setSmoothness(1.0f).orientX(-5.0f + MathHelper.func_76134_b((float)(limbSwing + (float)Math.PI)) * legSwingAmount).rotateZ(-2.0f);
        float foreLegSwingAmount = 0.7f * ((Float)((BipedEntityData)data).limbSwingAmount.get()).floatValue() / (float)Math.PI * 180.0f;
        float var = limbSwing / (float)Math.PI % 2.0f;
        ((BipedEntityData)data).leftForeLeg.rotation.setSmoothness(0.7f).orientX(40.0f + MathHelper.func_76134_b((float)(limbSwing + 1.8f)) * foreLegSwingAmount);
        ((BipedEntityData)data).rightForeLeg.rotation.setSmoothness(0.7f).orientX(40.0f + MathHelper.func_76134_b((float)(limbSwing + (float)Math.PI + 1.8f)) * foreLegSwingAmount);
        ((BipedEntityData)data).leftForeArm.rotation.setSmoothness(0.3f).orientX(var > 1.0f ? -10.0f : -45.0f);
        ((BipedEntityData)data).rightForeArm.rotation.setSmoothness(0.3f).orientX(var > 1.0f ? -45.0f : -10.0f);
        float bodyRotationY = MathHelper.func_76134_b((float)limbSwing) * -40.0f;
        float bodyRotationX = MathHelper.func_76134_b((float)(limbSwing * 2.0f)) * 10.0f + 10.0f;
        float var10 = headYaw * 0.3f;
        var10 = Math.max(-10.0f, Math.min(var10, 10.0f));
        ((BipedEntityData)data).body.rotation.setSmoothness(0.8f).orientY(bodyRotationY).rotateX(bodyRotationX).rotateZ(-var10);
        ((BipedEntityData)data).head.rotation.setSmoothness(0.5f).orientX(headPitch - bodyRotationX).rotateY(headYaw - bodyRotationY);
        ((BipedEntityData)data).globalOffset.slideY(MathHelper.func_76134_b((float)(limbSwing * 2.0f + 0.6f)) * 1.5f, 0.9f);
    }
}

