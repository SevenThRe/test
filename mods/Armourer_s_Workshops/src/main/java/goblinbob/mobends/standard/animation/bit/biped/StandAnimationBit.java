/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.math.MathHelper
 */
package goblinbob.mobends.standard.animation.bit.biped;

import goblinbob.mobends.core.animation.bit.AnimationBit;
import goblinbob.mobends.core.client.event.DataUpdateHandler;
import goblinbob.mobends.core.data.LivingEntityData;
import goblinbob.mobends.standard.data.BipedEntityData;
import net.minecraft.util.math.MathHelper;

public class StandAnimationBit<T extends BipedEntityData<?>>
extends AnimationBit<T> {
    private static final String[] ACTIONS = new String[]{"stand"};
    protected final float kneelDuration = 0.15f;

    @Override
    public String[] getActions(T entityData) {
        return ACTIONS;
    }

    @Override
    public void onPlay(T data) {
        float touchdown = Math.min(((LivingEntityData)data).getTicksAfterTouchdown() * 0.15f, 1.0f);
        if (touchdown < 0.5f) {
            ((BipedEntityData)data).body.rotation.orientInstant(20.0f, 1.0f, 0.0f, 0.0f);
            ((BipedEntityData)data).rightLeg.rotation.orient(-20.0f, 1.0f, 0.0f, 0.0f);
            ((BipedEntityData)data).leftLeg.rotation.orient(-45.0f, 1.0f, 0.0f, 0.0f);
            ((BipedEntityData)data).rightForeLeg.rotation.orient(60.0f, 1.0f, 0.0f, 0.0f);
            ((BipedEntityData)data).leftForeLeg.rotation.orient(60.0f, 1.0f, 0.0f, 0.0f);
        }
    }

    @Override
    public void perform(T data) {
        ((BipedEntityData)data).localOffset.slideToZero(0.3f);
        ((BipedEntityData)data).globalOffset.slideToZero(0.3f);
        ((BipedEntityData)data).renderRotation.setSmoothness(0.3f).orientZero();
        ((BipedEntityData)data).centerRotation.setSmoothness(0.3f).orientZero();
        ((BipedEntityData)data).renderRightItemRotation.setSmoothness(0.3f).orientZero();
        ((BipedEntityData)data).renderLeftItemRotation.setSmoothness(0.3f).orientZero();
        ((BipedEntityData)data).rightLeg.rotation.orient(0.0f, 1.0f, 0.0f, 0.0f);
        ((BipedEntityData)data).rightLeg.rotation.rotate(2.0f, 0.0f, 0.0f, 1.0f);
        ((BipedEntityData)data).rightLeg.rotation.rotate(5.0f, 0.0f, 1.0f, 0.0f);
        ((BipedEntityData)data).leftLeg.rotation.orient(0.0f, 1.0f, 0.0f, 0.0f);
        ((BipedEntityData)data).leftLeg.rotation.rotate(-2.0f, 0.0f, 0.0f, 1.0f);
        ((BipedEntityData)data).leftLeg.rotation.rotate(-5.0f, 0.0f, 1.0f, 0.0f);
        ((BipedEntityData)data).rightForeLeg.rotation.orient(4.0f, 1.0f, 0.0f, 0.0f);
        ((BipedEntityData)data).leftForeLeg.rotation.orient(4.0f, 1.0f, 0.0f, 0.0f);
        ((BipedEntityData)data).rightForeArm.rotation.orient(-4.0f, 1.0f, 0.0f, 0.0f);
        ((BipedEntityData)data).leftForeArm.rotation.orient(-4.0f, 1.0f, 0.0f, 0.0f);
        ((BipedEntityData)data).head.rotation.orientX(((Float)((BipedEntityData)data).headPitch.get()).floatValue()).rotateY(((Float)((BipedEntityData)data).headYaw.get()).floatValue());
        float PI = (float)Math.PI;
        float phase = DataUpdateHandler.getTicks() / 10.0f;
        ((BipedEntityData)data).body.rotation.setSmoothness(1.0f).orientX((MathHelper.func_76134_b((float)phase) - 1.0f) / 2.0f * -3.0f);
        ((BipedEntityData)data).rightArm.rotation.setSmoothness(0.4f).orientX(0.0f).rotateZ(MathHelper.func_76134_b((float)(phase + 1.5707964f)) * -2.5f + 2.5f);
        ((BipedEntityData)data).leftArm.rotation.setSmoothness(0.4f).orientX(0.0f).rotateZ(MathHelper.func_76134_b((float)(phase + 1.5707964f)) * 2.5f - 2.5f);
        float touchdown = Math.min(((LivingEntityData)data).getTicksAfterTouchdown() * 0.15f, 1.0f);
        if (touchdown < 1.0f) {
            ((BipedEntityData)data).body.rotation.setSmoothness(1.0f);
            ((BipedEntityData)data).body.rotation.orient(20.0f * (1.0f - touchdown), 1.0f, 0.0f, 0.0f);
            ((BipedEntityData)data).globalOffset.setY((float)(-Math.sin((double)touchdown * Math.PI)) * 2.0f);
        }
    }
}

