/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.math.MathHelper
 */
package baka.animation.bit;

import goblinbob.mobends.core.animation.bit.AnimationBit;
import goblinbob.mobends.standard.data.PlayerData;
import net.minecraft.util.math.MathHelper;

public class JumpLegAnimationBit
extends AnimationBit<PlayerData> {
    private static final String[] ACTIONS = new String[]{"jump"};

    @Override
    public String[] getActions(PlayerData entityData) {
        return ACTIONS;
    }

    @Override
    public void onPlay(PlayerData data) {
        data.renderRotation.identity();
        data.centerRotation.identity();
        data.rightLeg.rotation.orientInstantX(0.0f);
        data.leftLeg.rotation.orientInstantX(0.0f);
        data.rightForeLeg.rotation.orientInstantX(0.0f);
        data.leftForeLeg.rotation.orientInstantX(0.0f);
    }

    @Override
    public void perform(PlayerData data) {
        if (data.getPrevMotionY() < 0.0 && data.getMotionY() > 0.0) {
            this.onPlay(data);
        }
        data.globalOffset.slideToZero(0.3f);
        data.renderRotation.setSmoothness(0.3f).orientZero();
        data.centerRotation.setSmoothness(0.7f).orientZero();
        if (!data.isStillHorizontally()) {
            float PI = (float)Math.PI;
            float limbSwing = ((Float)data.limbSwing.get()).floatValue() * 0.6662f;
            float limbSwingAmount = 0.7f * ((Float)data.limbSwingAmount.get()).floatValue() / (float)Math.PI * 180.0f;
            data.rightLeg.rotation.setSmoothness(1.0f).orientX(-5.0f + MathHelper.func_76134_b((float)limbSwing) * limbSwingAmount);
            data.leftLeg.rotation.setSmoothness(1.0f).orientX(-5.0f + MathHelper.func_76134_b((float)(limbSwing + (float)Math.PI)) * limbSwingAmount);
            float var = limbSwing / (float)Math.PI % 2.0f;
            data.leftForeLeg.rotation.setSmoothness(0.3f).orientX(var > 1.0f ? 45 : 0);
            data.rightForeLeg.rotation.setSmoothness(0.3f).orientX(var > 1.0f ? 0 : 45);
        } else {
            data.rightLeg.rotation.setSmoothness(0.1f).orientZ(10.0f);
            data.rightLeg.rotation.setSmoothness(0.3f).rotateX(-45.0f);
            data.leftLeg.rotation.setSmoothness(0.1f).orientZ(-10.0f);
            data.leftLeg.rotation.setSmoothness(0.3f).rotateX(-17.0f);
            data.rightForeLeg.rotation.setSmoothness(0.3f).orientX(70.0f);
            data.leftForeLeg.rotation.setSmoothness(0.3f).orientX(17.0f);
        }
    }
}

