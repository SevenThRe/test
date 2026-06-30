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

public class SneakLegAnimationBit
extends AnimationBit<PlayerData> {
    private static final String[] ACTIONS = new String[]{"sneak"};

    @Override
    public String[] getActions(PlayerData entityData) {
        return ACTIONS;
    }

    @Override
    public void perform(PlayerData data) {
        data.localOffset.slideToZero(0.3f);
        data.globalOffset.slideY(-1.3f);
        float PI = (float)Math.PI;
        float limbSwing = ((Float)data.limbSwing.get()).floatValue() * 0.6662f;
        float limbSwingAmount = ((Float)data.limbSwingAmount.get()).floatValue() * 1.4f * 1.1f / (float)Math.PI * 180.0f;
        float var = limbSwing / (float)Math.PI % 2.0f;
        data.rightLeg.rotation.setSmoothness(1.0f).orientX(MathHelper.cos((float)limbSwing) * limbSwingAmount - 5.0f).rotateZ(10.0f);
        data.leftLeg.rotation.setSmoothness(1.0f).orientX(MathHelper.cos((float)(limbSwing + (float)Math.PI)) * limbSwingAmount - 5.0f).rotateZ(-10.0f);
        data.leftForeLeg.rotation.setSmoothness(0.3f).orientX(var > 1.0f ? 45.0f : 10.0f);
        data.rightForeLeg.rotation.setSmoothness(0.3f).orientX(var > 1.0f ? 10.0f : 45.0f);
        float var2 = 25.0f + MathHelper.cos((float)(limbSwing * 2.0f)) * 5.0f;
    }
}

