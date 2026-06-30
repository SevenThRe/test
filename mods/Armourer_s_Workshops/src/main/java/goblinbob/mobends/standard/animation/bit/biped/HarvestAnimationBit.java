/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.EnumHandSide
 *  net.minecraft.util.math.MathHelper
 */
package goblinbob.mobends.standard.animation.bit.biped;

import goblinbob.mobends.core.animation.bit.AnimationBit;
import goblinbob.mobends.core.client.model.ModelPartTransform;
import goblinbob.mobends.standard.data.BipedEntityData;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.math.MathHelper;

public class HarvestAnimationBit
extends AnimationBit<BipedEntityData<?>> {
    private static final String[] ACTIONS = new String[]{"harvest"};
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
        float headPitch = ((Float)data.headPitch.get()).floatValue();
        float headYaw = ((Float)data.headYaw.get()).floatValue();
        boolean mainHandSwitch = this.actionHand == EnumHandSide.RIGHT;
        float sideMultiplier = this.actionHand == EnumHandSide.RIGHT ? 1.0f : -1.0f;
        ModelPartTransform mainArm = mainHandSwitch ? data.rightArm : data.leftArm;
        ModelPartTransform offArm = mainHandSwitch ? data.leftArm : data.rightArm;
        ModelPartTransform mainForeArm = mainHandSwitch ? data.rightForeArm : data.leftForeArm;
        ModelPartTransform offForeArm = mainHandSwitch ? data.leftForeArm : data.rightForeArm;
        data.localOffset.slideToZero(0.3f);
        data.centerRotation.setSmoothness(0.3f).orientZero();
        float swingProgress = ((Float)data.swingProgress.get()).floatValue();
        float bodyYaw = MathHelper.func_76126_a((float)(MathHelper.func_76129_c((float)swingProgress) * ((float)Math.PI * 2))) * 30.0f * sideMultiplier;
        data.body.rotation.setSmoothness(0.8f).orientY(bodyYaw);
        float bodyPitch = 0.0f;
        if (data.getEntity().func_70093_af()) {
            data.body.rotation.rotateX(20.0f);
            bodyPitch = 20.0f;
        }
        data.head.rotation.setSmoothness(0.8f).orientX(headPitch - bodyPitch).rotateY(headYaw - bodyYaw);
        mainArm.rotation.orientInstantX(MathHelper.func_76126_a((float)(MathHelper.func_76129_c((float)swingProgress) * ((float)Math.PI * 2))) * 50.0f - 30.0f);
        mainArm.rotation.localRotateZ(MathHelper.func_76134_b((float)(MathHelper.func_76129_c((float)swingProgress) * ((float)Math.PI * 2))) * -20.0f + 10.0f).finish();
    }
}

