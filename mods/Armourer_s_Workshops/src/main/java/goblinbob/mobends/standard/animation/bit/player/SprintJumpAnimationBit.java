/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.math.MathHelper
 */
package goblinbob.mobends.standard.animation.bit.player;

import goblinbob.mobends.core.animation.bit.AnimationBit;
import goblinbob.mobends.core.client.event.DataUpdateHandler;
import goblinbob.mobends.core.client.model.ModelPartTransform;
import goblinbob.mobends.standard.data.PlayerData;
import net.minecraft.util.math.MathHelper;

public class SprintJumpAnimationBit
extends AnimationBit<PlayerData> {
    private float relax = 0.0f;

    @Override
    public String[] getActions(PlayerData entityData) {
        return new String[]{"sprint_jump"};
    }

    @Override
    public void onPlay(PlayerData entityData) {
        this.relax = 0.0f;
    }

    @Override
    public void perform(PlayerData data) {
        boolean sprintLegSwitch;
        if (data.getPrevMotionY() < 0.0 && data.getMotionY() > 0.0) {
            this.onPlay(data);
        }
        float legSwitchMtp = (sprintLegSwitch = data.getSprintJumpLeg()) ? 1.0f : -1.0f;
        ModelPartTransform mainArm = sprintLegSwitch ? data.rightArm : data.leftArm;
        ModelPartTransform offArm = sprintLegSwitch ? data.leftArm : data.rightArm;
        ModelPartTransform mainLeg = sprintLegSwitch ? data.rightLeg : data.leftLeg;
        ModelPartTransform offLeg = sprintLegSwitch ? data.leftLeg : data.rightLeg;
        ModelPartTransform mainForeLeg = sprintLegSwitch ? data.rightForeLeg : data.leftForeLeg;
        ModelPartTransform offForeLeg = sprintLegSwitch ? data.leftForeLeg : data.rightForeLeg;
        float bodyRotationY = 20.0f * legSwitchMtp;
        float bodyLean = MathHelper.clamp((float)((float)data.getMotionY()), (float)-0.2f, (float)0.2f);
        bodyLean = bodyLean * -100.0f + 20.0f;
        if (this.relax < 1.0f) {
            this.relax += DataUpdateHandler.ticksPerFrame * 0.1f;
            this.relax = Math.min(this.relax, 1.0f);
        }
        float relaxAngle = MathHelper.sqrt((float)MathHelper.sqrt((float)this.relax));
        data.centerRotation.setSmoothness(0.3f).orientZero();
        data.globalOffset.slideToZero(0.5f);
        data.body.rotation.setSmoothness(0.3f).orientX(bodyLean).rotateY(bodyRotationY);
        data.rightLeg.rotation.setSmoothness(0.8f).orientZ(5.0f);
        data.leftLeg.rotation.setSmoothness(0.8f).orientZ(-5.0f);
        data.rightArm.rotation.setSmoothness(0.3f).orientZ(10.0f);
        data.leftArm.rotation.setSmoothness(0.3f).orientZ(-10.0f);
        mainLeg.getRotation().rotateX(-45.0f);
        offLeg.getRotation().rotateX(45.0f);
        mainArm.getRotation().rotateX(50.0f);
        offArm.getRotation().rotateX(-50.0f);
        mainForeLeg.getRotation().orientX(80.0f - relaxAngle * 80.0f);
        offForeLeg.getRotation().orientX(relaxAngle * 70.0f);
        data.head.rotation.orientX(((Float)data.headPitch.get()).floatValue() - 20.0f);
        data.head.rotation.rotateY(((Float)data.headYaw.get()).floatValue() - bodyRotationY);
    }
}

