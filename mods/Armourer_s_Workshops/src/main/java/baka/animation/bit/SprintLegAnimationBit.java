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

public class SprintLegAnimationBit
extends AnimationBit<PlayerData> {
    private static String[] ACTIONS = new String[]{"sprint"};

    @Override
    public String[] getActions(PlayerData entityData) {
        return ACTIONS;
    }

    @Override
    public void perform(PlayerData data) {
        data.localOffset.slideToZero(0.3f);
        data.globalOffset.slideToZero(0.1f);
        data.centerRotation.setSmoothness(0.3f).orientZero();
        data.renderRotation.setSmoothness(0.3f).orientZero();
        float PI = (float)Math.PI;
        float limbSwing = ((Float)data.limbSwing.get()).floatValue() * 0.6662f * 0.8f;
        float armSwingAmount = ((Float)data.limbSwingAmount.get()).floatValue() / (float)Math.PI * 180.0f * 1.1f;
        float legSwingAmount = 1.26f * ((Float)data.limbSwingAmount.get()).floatValue() / (float)Math.PI * 180.0f;
        data.rightLeg.rotation.setSmoothness(1.0f).orientX(-5.0f + MathHelper.func_76134_b((float)limbSwing) * legSwingAmount).rotateZ(2.0f);
        data.leftLeg.rotation.setSmoothness(1.0f).orientX(-5.0f + MathHelper.func_76134_b((float)(limbSwing + (float)Math.PI)) * legSwingAmount).rotateZ(-2.0f);
        float foreLegSwingAmount = 0.7f * ((Float)data.limbSwingAmount.get()).floatValue() / (float)Math.PI * 180.0f;
        float var = limbSwing / (float)Math.PI % 2.0f;
        data.leftForeLeg.rotation.setSmoothness(0.7f).orientX(40.0f + MathHelper.func_76134_b((float)(limbSwing + 1.8f)) * foreLegSwingAmount);
        data.rightForeLeg.rotation.setSmoothness(0.7f).orientX(40.0f + MathHelper.func_76134_b((float)(limbSwing + (float)Math.PI + 1.8f)) * foreLegSwingAmount);
        float bodyRotationY = MathHelper.func_76134_b((float)limbSwing) * -40.0f;
        float bodyRotationX = MathHelper.func_76134_b((float)(limbSwing * 2.0f)) * 10.0f + 10.0f;
        float var10 = ((Float)data.headYaw.get()).floatValue() * 0.3f;
        var10 = Math.max(-10.0f, Math.min(var10, 10.0f));
        data.globalOffset.slideY(MathHelper.func_76134_b((float)(limbSwing * 2.0f + 0.6f)) * 1.5f, 0.9f);
    }
}

