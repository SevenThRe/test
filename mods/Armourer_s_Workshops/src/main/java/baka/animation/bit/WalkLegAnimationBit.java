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

public class WalkLegAnimationBit
extends AnimationBit<PlayerData> {
    private static final String[] ACTIONS = new String[]{"walk"};
    protected final float KNEEL_DURATION = 0.15f;

    @Override
    public String[] getActions(PlayerData entityData) {
        return ACTIONS;
    }

    @Override
    public void perform(PlayerData data) {
        data.localOffset.slideToZero(0.3f);
        data.globalOffset.slideToZero(0.3f);
        data.centerRotation.setSmoothness(0.3f).orientZero();
        data.renderRotation.setSmoothness(0.3f).orientZero();
        float PI = (float)Math.PI;
        float limbSwing = ((Float)data.limbSwing.get()).floatValue() * 0.6662f;
        float legSwingAmount = 0.7f * ((Float)data.limbSwingAmount.get()).floatValue() / (float)Math.PI * 180.0f;
        data.rightLeg.rotation.setSmoothness(1.0f).orientX(-5.0f + MathHelper.cos((float)limbSwing) * legSwingAmount).rotateZ(2.0f);
        data.leftLeg.rotation.setSmoothness(1.0f).orientX(-5.0f + MathHelper.cos((float)(limbSwing + (float)Math.PI)) * legSwingAmount).rotateZ(-2.0f);
        float var = limbSwing / (float)Math.PI % 2.0f;
        data.leftForeLeg.rotation.setSmoothness(0.5f).orientX(var > 1.0f ? 45.0f : 0.0f);
        data.rightForeLeg.rotation.setSmoothness(0.5f).orientX(var > 1.0f ? 0.0f : 45.0f);
        data.globalOffset.slideY(MathHelper.cos((float)(limbSwing * 2.0f)) * 0.6f);
        float touchdown = Math.min(data.getTicksAfterTouchdown() * 0.15f, 1.0f);
        if (touchdown < 1.0f) {
            data.globalOffset.setY((float)(-Math.sin((double)touchdown * Math.PI)) * 2.0f);
        }
    }
}

