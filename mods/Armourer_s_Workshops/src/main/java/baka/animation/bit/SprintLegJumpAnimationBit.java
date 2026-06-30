/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.math.MathHelper
 */
package baka.animation.bit;

import goblinbob.mobends.core.animation.bit.AnimationBit;
import goblinbob.mobends.core.client.event.DataUpdateHandler;
import goblinbob.mobends.core.client.model.ModelPartTransform;
import goblinbob.mobends.standard.data.PlayerData;
import net.minecraft.util.math.MathHelper;

public class SprintLegJumpAnimationBit
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
        ModelPartTransform offForeLeg;
        boolean sprintLegSwitch;
        if (data.getPrevMotionY() < 0.0 && data.getMotionY() > 0.0) {
            this.onPlay(data);
        }
        ModelPartTransform mainLeg = (sprintLegSwitch = data.getSprintJumpLeg()) ? data.rightLeg : data.leftLeg;
        ModelPartTransform offLeg = sprintLegSwitch ? data.leftLeg : data.rightLeg;
        ModelPartTransform mainForeLeg = sprintLegSwitch ? data.rightForeLeg : data.leftForeLeg;
        ModelPartTransform modelPartTransform = offForeLeg = sprintLegSwitch ? data.leftForeLeg : data.rightForeLeg;
        if (this.relax < 1.0f) {
            this.relax += DataUpdateHandler.ticksPerFrame * 0.1f;
            this.relax = Math.min(this.relax, 1.0f);
        }
        float relaxAngle = MathHelper.func_76129_c((float)MathHelper.func_76129_c((float)this.relax));
        data.centerRotation.setSmoothness(0.3f).orientZero();
        data.globalOffset.slideToZero(0.5f);
        data.rightLeg.rotation.setSmoothness(0.8f).orientZ(5.0f);
        data.leftLeg.rotation.setSmoothness(0.8f).orientZ(-5.0f);
        mainLeg.getRotation().rotateX(-45.0f);
        offLeg.getRotation().rotateX(45.0f);
        mainForeLeg.getRotation().orientX(80.0f - relaxAngle * 80.0f);
        offForeLeg.getRotation().orientX(relaxAngle * 70.0f);
    }
}

