/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.entity.AbstractClientPlayer
 *  net.minecraft.util.EnumHandSide
 *  net.minecraft.util.math.MathHelper
 */
package goblinbob.mobends.standard.animation.bit.player;

import goblinbob.mobends.core.animation.bit.AnimationBit;
import goblinbob.mobends.core.client.event.DataUpdateHandler;
import goblinbob.mobends.core.client.model.ModelPartTransform;
import goblinbob.mobends.core.math.SmoothOrientation;
import goblinbob.mobends.standard.data.PlayerData;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.math.MathHelper;

public class AttackStanceAnimationBit
extends AnimationBit<PlayerData> {
    protected final float PI = (float)Math.PI;
    protected final float kneelDuration = 0.15f;
    protected final float legSpreadSpeed = 0.1f;
    protected float legSpreadAnimation = 0.0f;

    @Override
    public String[] getActions(PlayerData entityData) {
        return new String[]{"attack_stance"};
    }

    @Override
    public void onPlay(PlayerData entityData) {
        this.legSpreadAnimation = 0.0f;
    }

    @Override
    public void perform(PlayerData data) {
        AbstractClientPlayer player = (AbstractClientPlayer)data.getEntity();
        EnumHandSide primaryHand = player.func_184591_cq();
        boolean mainHandSwitch = primaryHand == EnumHandSide.RIGHT;
        float handDirMtp = mainHandSwitch ? 1.0f : -1.0f;
        ModelPartTransform mainArm = mainHandSwitch ? data.rightArm : data.leftArm;
        ModelPartTransform offArm = mainHandSwitch ? data.leftArm : data.rightArm;
        ModelPartTransform mainForeArm = mainHandSwitch ? data.rightForeArm : data.leftForeArm;
        ModelPartTransform offForeArm = mainHandSwitch ? data.leftForeArm : data.rightForeArm;
        ModelPartTransform mainLeg = mainHandSwitch ? data.rightLeg : data.leftLeg;
        ModelPartTransform offLeg = mainHandSwitch ? data.leftLeg : data.rightLeg;
        ModelPartTransform mainForeLeg = mainHandSwitch ? data.rightForeLeg : data.leftForeLeg;
        ModelPartTransform offForeLeg = mainHandSwitch ? data.leftForeLeg : data.rightForeLeg;
        SmoothOrientation mainItemRotation = mainHandSwitch ? data.renderRightItemRotation : data.renderLeftItemRotation;
        float breath0 = (float)Math.sin((double)DataUpdateHandler.getTicks() / 5.0);
        float breath1 = (float)Math.cos((double)DataUpdateHandler.getTicks() / 5.7);
        data.renderRotation.setSmoothness(0.3f).orientY(-30.0f * handDirMtp);
        float bodyRotationX = 20.0f + breath0 * 2.0f;
        data.body.rotation.setSmoothness(0.3f).orientX(bodyRotationX);
        data.head.rotation.rotateY(-30.0f * handDirMtp);
        data.head.rotation.rotateX(-bodyRotationX);
        data.rightLeg.rotation.setSmoothness(0.3f).orientX(-30.0f).rotateZ(10.0f).rotateY(25.0f);
        data.leftLeg.rotation.setSmoothness(0.3f).orientX(-30.0f).rotateZ(-10.0f).rotateY(-25.0f);
        data.rightForeLeg.rotation.setSmoothness(0.3f).orientX(30.0f);
        data.leftForeLeg.rotation.setSmoothness(0.3f).orientX(30.0f);
        mainArm.getRotation().setSmoothness(0.3f).orientZ(60.0f * handDirMtp + breath0 * 5.0f).rotateY(breath1 * 5.0f);
        offArm.getRotation().setSmoothness(0.3f).orientZ(-60.0f * handDirMtp + breath1 * 5.0f);
        mainForeArm.getRotation().setSmoothness(0.3f).orientX(-20.0f);
        offForeArm.getRotation().setSmoothness(0.3f).orientX(-60.0f);
        mainItemRotation.setSmoothness(0.3f).orientX(65.0f);
        data.globalOffset.slideY(-2.0f);
        float touchdown = Math.min(data.getTicksAfterTouchdown() * 0.15f, 1.0f);
        if (touchdown < 1.0f) {
            data.body.rotation.setSmoothness(1.0f);
            data.body.rotation.orientX(5.0f * (1.0f - touchdown) + 15.0f);
            data.globalOffset.setY(-MathHelper.func_76126_a((float)(touchdown * (float)Math.PI)) * 2.0f - 2.0f);
        }
    }
}

