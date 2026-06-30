/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.util.EnumHandSide
 *  net.minecraft.util.math.MathHelper
 */
package goblinbob.mobends.standard.animation.bit.biped;

import goblinbob.mobends.core.animation.bit.AnimationBit;
import goblinbob.mobends.core.client.model.ModelPartTransform;
import goblinbob.mobends.standard.data.BipedEntityData;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.math.MathHelper;

public class BowAnimationBit
extends AnimationBit<BipedEntityData<?>> {
    private static final String[] ACTIONS = new String[]{"bow"};
    protected EnumHandSide actionHand = EnumHandSide.RIGHT;

    @Override
    public String[] getActions(BipedEntityData<?> entityData) {
        return ACTIONS;
    }

    public void setActionHand(EnumHandSide handSide) {
        this.actionHand = handSide;
    }

    @Override
    public void perform(BipedEntityData<?> data) {
        data.localOffset.slideToZero(0.3f);
        Object living = data.getEntity();
        float headPitch = ((Float)data.headPitch.get()).floatValue();
        float headYaw = ((Float)data.headYaw.get()).floatValue();
        boolean mainHandSwitch = this.actionHand == EnumHandSide.RIGHT;
        float handDirMtp = mainHandSwitch ? 1.0f : -1.0f;
        ModelPartTransform mainArm = mainHandSwitch ? data.rightArm : data.leftArm;
        ModelPartTransform offArm = mainHandSwitch ? data.leftArm : data.rightArm;
        ModelPartTransform mainForeArm = mainHandSwitch ? data.rightForeArm : data.leftForeArm;
        ModelPartTransform offForeArm = mainHandSwitch ? data.leftForeArm : data.rightForeArm;
        int aimedBowDuration = living != null ? Math.min(living.func_184612_cw(), 15) : 0;
        float bodyTwistY = (float)(aimedBowDuration - 10) / 5.0f * -25.0f * handDirMtp;
        float var2 = (float)aimedBowDuration / 10.0f;
        float var5 = headPitch - 90.0f;
        var5 = Math.max(var5, -160.0f);
        float bodyRotationY = -bodyTwistY + headYaw;
        if (data.isClimbing()) {
            float climbingRotation = data.getClimbingRotation();
            float renderRotationY = MathHelper.func_76142_g((float)(((EntityLivingBase)living).field_70177_z - headYaw - climbingRotation));
            bodyRotationY = MathHelper.func_76142_g((float)(headYaw + renderRotationY));
            data.head.rotation.setSmoothness(0.5f).orientX(headPitch);
        } else {
            data.head.rotation.setSmoothness(0.5f).orientX(headPitch).rotateY(headYaw - bodyRotationY);
        }
        data.body.rotation.setSmoothness(0.8f).orientY(bodyRotationY);
        mainArm.rotation.setSmoothness(0.8f).orientX(headPitch - 90.0f).rotateY(bodyTwistY);
        offArm.rotation.setSmoothness(1.0f).orientY(80.0f * handDirMtp).rotateZ((-MathHelper.func_76134_b((float)(headPitch / 180.0f * (float)Math.PI)) * 40.0f + 40.0f) * handDirMtp).rotateX(var5);
        mainForeArm.rotation.setSmoothness(1.0f).orientX(0.0f);
        offForeArm.rotation.orientX(var2 * -30.0f);
    }
}

