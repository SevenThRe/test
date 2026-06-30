/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.math.MathHelper
 */
package baka.animation.bit;

import goblinbob.mobends.core.animation.bit.AnimationBit;
import goblinbob.mobends.core.client.event.DataUpdateHandler;
import goblinbob.mobends.standard.data.PlayerData;
import net.minecraft.util.math.MathHelper;

public class FallingLegAnimationBit
extends AnimationBit<PlayerData> {
    private static final String[] ACTIONS = new String[]{"falling"};
    public static final float TICKS_BEFORE_FALLING = 10.0f;
    public static final float FALLING_TRANSITION_TICKS = 80.0f;

    @Override
    public String[] getActions(PlayerData entityData) {
        return ACTIONS;
    }

    @Override
    public void perform(PlayerData data) {
        data.centerRotation.setSmoothness(0.3f).orientZero();
        float ticks = DataUpdateHandler.getTicks() * 0.5f;
        float rightArmDelay = 1.0f;
        float legSpan = 10.0f;
        float transition = (data.getTicksFalling() - 10.0f) / 80.0f;
        transition = MathHelper.func_76131_a((float)transition, (float)0.0f, (float)1.0f);
        float s2 = 0.0f + transition * 0.9f;
        data.leftLeg.rotation.setSmoothness(s2).orientX(MathHelper.func_76126_a((float)ticks) * legSpan).rotateZ(-20.0f + MathHelper.func_76134_b((float)ticks) * legSpan);
        data.rightLeg.rotation.setSmoothness(s2).orientX(MathHelper.func_76126_a((float)(ticks + rightArmDelay)) * legSpan).rotateZ(20.0f + MathHelper.func_76134_b((float)(ticks + rightArmDelay)) * legSpan);
        data.leftForeLeg.rotation.setSmoothness(s2).orientX(20.0f);
        data.rightForeLeg.rotation.setSmoothness(s2).orientX(20.0f);
        data.renderRotation.setSmoothness(s2).orientX(20.0f);
    }
}

