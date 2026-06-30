/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.ItemSword
 *  net.minecraft.util.EnumHand
 *  net.minecraft.util.EnumHandSide
 *  net.minecraft.util.math.MathHelper
 */
package goblinbob.mobends.standard.animation.bit.biped;

import goblinbob.mobends.core.animation.bit.AnimationBit;
import goblinbob.mobends.core.client.event.DataUpdateHandler;
import goblinbob.mobends.core.client.model.ModelPartTransform;
import goblinbob.mobends.core.math.SmoothOrientation;
import goblinbob.mobends.core.util.GUtil;
import goblinbob.mobends.standard.data.BipedEntityData;
import net.minecraft.item.ItemSword;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.math.MathHelper;

public class AttackSlashOutwardAnimationBit
extends AnimationBit<BipedEntityData<?>> {
    private static final String[] ACTIONS = new String[]{"attack", "attack_slash_down"};
    private float ticksPlayed;

    @Override
    public String[] getActions(BipedEntityData<?> entityData) {
        return ACTIONS;
    }

    @Override
    public void onPlay(BipedEntityData<?> data) {
        data.swordTrail.reset();
        this.ticksPlayed = 0.0f;
    }

    @Override
    public void perform(BipedEntityData<?> data) {
        SmoothOrientation mainItemRotation;
        data.localOffset.slideToZero(0.3f);
        Object living = data.getEntity();
        EnumHandSide primaryHand = living.func_184591_cq();
        boolean mainHandSwitch = primaryHand == EnumHandSide.RIGHT;
        float handDirMtp = mainHandSwitch ? 1.0f : -1.0f;
        ModelPartTransform mainArm = mainHandSwitch ? data.rightArm : data.leftArm;
        ModelPartTransform offArm = mainHandSwitch ? data.leftArm : data.rightArm;
        ModelPartTransform mainForeArm = mainHandSwitch ? data.rightForeArm : data.leftForeArm;
        ModelPartTransform offForeArm = mainHandSwitch ? data.leftForeArm : data.rightForeArm;
        SmoothOrientation smoothOrientation = mainItemRotation = mainHandSwitch ? data.renderRightItemRotation : data.renderLeftItemRotation;
        if (data.getTicksAfterAttack() < 4.0f && living.func_184586_b(EnumHand.MAIN_HAND).func_77973_b() instanceof ItemSword) {
            data.swordTrail.add(data);
        }
        float attackState = this.ticksPlayed / 10.0f;
        float armSwing = GUtil.clamp(attackState * 3.0f, 0.0f, 1.0f);
        float bodyRotationX = 20.0f - attackState * 20.0f;
        float bodyRotationY = (30.0f + 10.0f * attackState) * handDirMtp;
        data.body.rotation.setSmoothness(0.9f).orientX(bodyRotationX).orientY(bodyRotationY);
        data.head.rotation.setSmoothness(0.9f).orientX(MathHelper.func_76142_g((float)((Float)data.headPitch.get()).floatValue()) - bodyRotationX).rotateY(MathHelper.func_76142_g((float)((Float)data.headYaw.get()).floatValue()) - bodyRotationY);
        mainArm.getRotation().setSmoothness(0.3f).orientZ((70.0f + armSwing * 40.0f) * handDirMtp).rotateInstantY((-20.0f + armSwing * 70.0f) * handDirMtp);
        offArm.getRotation().setSmoothness(0.3f).orientZ(-80.0f * handDirMtp);
        mainForeArm.getRotation().setSmoothness(0.3f).orientX(-20.0f);
        offForeArm.getRotation().setSmoothness(0.3f).orientX(-60.0f);
        if (data.isStillHorizontally() && !living.func_184218_aH()) {
            data.rightLeg.rotation.setSmoothness(0.3f).orientX(-30.0f).rotateZ(10.0f).rotateY(25.0f);
            data.leftLeg.rotation.setSmoothness(0.3f).orientX(-30.0f).rotateZ(-10.0f).rotateY(-25.0f);
            data.rightForeLeg.rotation.setSmoothness(0.3f).orientX(30.0f);
            data.leftForeLeg.rotation.setSmoothness(0.3f).orientX(30.0f);
            data.head.rotation.rotateY(-30.0f * handDirMtp);
            data.globalOffset.slideY(-2.0f);
            data.renderRotation.setSmoothness(0.3f).orientY(-30.0f * handDirMtp);
        }
        mainItemRotation.orientInstantX(90.0f);
        this.ticksPlayed += DataUpdateHandler.ticksPerFrame;
    }
}

