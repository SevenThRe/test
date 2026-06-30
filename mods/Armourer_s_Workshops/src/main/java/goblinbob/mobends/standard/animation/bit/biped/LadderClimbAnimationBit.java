/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.util.math.MathHelper
 */
package goblinbob.mobends.standard.animation.bit.biped;

import goblinbob.mobends.core.animation.bit.AnimationBit;
import goblinbob.mobends.core.util.GUtil;
import goblinbob.mobends.standard.data.BipedEntityData;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.MathHelper;

public class LadderClimbAnimationBit
extends AnimationBit<BipedEntityData<?>> {
    private static final String[] ACTIONS = new String[]{"ladder_climb"};

    @Override
    public String[] getActions(BipedEntityData<?> entityData) {
        return ACTIONS;
    }

    @Override
    public void perform(BipedEntityData<?> data) {
        Object living = data.getEntity();
        data.centerRotation.setSmoothness(0.3f).orientZero();
        float legAnimationOffset = (float)Math.PI;
        float progress = data.getClimbingCycle();
        float armSwingRight = (float)Math.sin(progress) * 0.5f + 0.5f;
        float armSwingLeft = (float)Math.sin((double)progress + Math.PI) * 0.5f + 0.5f;
        float armSwingRight2 = (float)Math.sin(progress - 0.3f) * 0.5f + 0.5f;
        float armSwingLeft2 = (float)Math.sin((double)progress + Math.PI - (double)0.3f) * 0.5f + 0.5f;
        float armSwingDouble = (float)Math.sin(progress * 2.0f) * 0.5f + 0.5f;
        float armSwingDouble2 = (float)Math.sin(progress * 2.0f - 1.8f) * 0.5f + 0.5f;
        float legSwingRight = (float)Math.sin(progress + (float)Math.PI) * 0.5f + 0.5f;
        float legSwingLeft = (float)Math.sin((double)(progress + (float)Math.PI) + Math.PI) * 0.5f + 0.5f;
        float legSwingRight2 = (float)Math.sin(progress + (float)Math.PI + 0.3f) * 0.5f + 0.5f;
        float legSwingLeft2 = (float)Math.sin((double)(progress + (float)Math.PI) + Math.PI + (double)0.3f) * 0.5f + 0.5f;
        float armOrientX = -45.0f;
        float climbingRotation = data.getClimbingRotation();
        float renderRotationY = MathHelper.func_76142_g((float)(((EntityLivingBase)living).field_70177_z - ((Float)data.headYaw.get()).floatValue() - climbingRotation));
        data.renderRotation.setSmoothness(0.6f).orientY(renderRotationY);
        data.localOffset.slideZ(armSwingDouble2, 0.6f);
        data.body.rotation.setSmoothness(0.5f).orientX(armSwingDouble * 10.0f);
        data.rightArm.rotation.setSmoothness(0.5f).orientX(-135.0f + armSwingRight * 70.0f);
        data.leftArm.rotation.setSmoothness(0.5f).orientX(-135.0f + armSwingLeft * 70.0f);
        data.rightForeArm.rotation.setSmoothness(0.5f).orientX(armSwingRight2 * -80.0f);
        data.leftForeArm.rotation.setSmoothness(0.5f).orientX(armSwingLeft2 * -80.0f);
        data.rightLeg.rotation.setSmoothness(0.5f).orientX(-45.0f - legSwingRight * 50.0f);
        data.leftLeg.rotation.setSmoothness(0.5f).orientX(-45.0f - legSwingLeft * 50.0f);
        data.rightForeLeg.rotation.setSmoothness(0.5f).orientX(20.0f + legSwingRight2 * 90.0f);
        data.leftForeLeg.rotation.setSmoothness(0.5f).orientX(20.0f + legSwingLeft2 * 90.0f);
        data.head.rotation.orientX(((Float)data.headPitch.get()).floatValue()).rotateY(GUtil.clamp(MathHelper.func_76142_g((float)(((Float)data.headYaw.get()).floatValue() + renderRotationY)), -90.0f, 90.0f));
        float ledgeClimbStart = 0.6f;
        if (data.getLedgeHeight() >= 0.6f) {
            float armRotX = data.getLedgeHeight() - 0.6f;
            data.body.rotation.setSmoothness(0.5f).orientX(armRotX * 50.0f);
            data.rightArm.rotation.setSmoothness(0.5f).orientX(-100.0f + armRotX * 40.0f);
            data.leftArm.rotation.setSmoothness(0.5f).orientX(-100.0f + armRotX * 40.0f);
            data.rightForeArm.rotation.setSmoothness(0.5f).orientX(-10.0f);
            data.leftForeArm.rotation.setSmoothness(0.5f).orientX(-10.0f);
        }
    }
}

