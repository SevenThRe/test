/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.ItemSword
 *  net.minecraft.util.EnumHand
 *  net.minecraft.util.EnumHandSide
 *  net.minecraft.util.math.MathHelper
 *  org.lwjgl.util.vector.Vector3f
 */
package goblinbob.mobends.standard.animation.bit.biped;

import goblinbob.mobends.core.animation.bit.AnimationBit;
import goblinbob.mobends.core.client.model.ModelPartTransform;
import goblinbob.mobends.core.math.SmoothOrientation;
import goblinbob.mobends.core.util.GUtil;
import goblinbob.mobends.standard.data.BipedEntityData;
import net.minecraft.item.ItemSword;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.math.MathHelper;
import org.lwjgl.util.vector.Vector3f;

public class AttackWhirlSlashAnimationBit
extends AnimationBit<BipedEntityData<?>> {
    private static final String[] ACTIONS = new String[]{"attack", "attack_2"};

    @Override
    public String[] getActions(BipedEntityData<?> entityData) {
        return ACTIONS;
    }

    @Override
    public void perform(BipedEntityData<?> data) {
        SmoothOrientation mainItemRotation;
        data.localOffset.slideToZero(0.3f);
        Object living = data.getEntity();
        EnumHandSide primaryHand = living.getPrimaryHand();
        boolean mainHandSwitch = primaryHand == EnumHandSide.RIGHT;
        float handDirMtp = mainHandSwitch ? 1.0f : -1.0f;
        ModelPartTransform mainArm = mainHandSwitch ? data.rightArm : data.leftArm;
        ModelPartTransform offArm = mainHandSwitch ? data.leftArm : data.rightArm;
        ModelPartTransform mainForeArm = mainHandSwitch ? data.rightForeArm : data.leftForeArm;
        ModelPartTransform offForeArm = mainHandSwitch ? data.leftForeArm : data.rightForeArm;
        SmoothOrientation smoothOrientation = mainItemRotation = mainHandSwitch ? data.renderRightItemRotation : data.renderLeftItemRotation;
        if (data.getTicksAfterAttack() < 0.5f) {
            data.swordTrail.reset();
        }
        if (living.getHeldItem(EnumHand.MAIN_HAND) != null && living.getHeldItem(EnumHand.MAIN_HAND).getItem() instanceof ItemSword) {
            data.swordTrail.add(data);
        }
        float attackState = data.getTicksAfterAttack() / 10.0f;
        float armSwing = attackState * 2.0f;
        armSwing = Math.min(armSwing, 1.0f);
        float var5 = GUtil.clamp(attackState * 1.6f, 0.0f, 1.0f);
        Vector3f bodyRot = new Vector3f(0.0f, 0.0f, 0.0f);
        bodyRot.x = 20.0f - attackState * 20.0f;
        bodyRot.y = 20.0f * attackState * handDirMtp;
        data.body.rotation.setSmoothness(0.9f).orientX(bodyRot.x).orientY(bodyRot.y);
        data.head.rotation.orientX(MathHelper.wrapDegrees((float)((Float)data.headPitch.get()).floatValue()) - bodyRot.x).rotateY(MathHelper.wrapDegrees((float)((Float)data.headYaw.get()).floatValue()) - bodyRot.y - 30.0f);
        offArm.getRotation().setSmoothness(0.3f).orientZ(20.0f * handDirMtp);
        offArm.getRotation().setSmoothness(0.3f).orientZ(-80.0f * handDirMtp);
        mainArm.getRotation().setSmoothness(0.3f).orientZ(-(-10.0f - var5 * 120.0f) * handDirMtp).rotateInstantY(-20.0f + armSwing * 70.0f);
        mainForeArm.getRotation().setSmoothness(0.3f).orientX(-20.0f);
        offForeArm.getRotation().setSmoothness(0.3f).orientX(-60.0f);
        if (data.isStillHorizontally()) {
            data.rightLeg.rotation.setSmoothness(0.3f).orientX(-30.0f).rotateZ(10.0f).rotateY(25.0f);
            data.leftLeg.rotation.setSmoothness(0.3f).orientX(-30.0f).rotateZ(-10.0f).rotateY(-25.0f);
            data.rightForeLeg.rotation.setSmoothness(0.3f).orientX(30.0f);
            data.leftForeLeg.rotation.setSmoothness(0.3f).orientX(30.0f);
        }
        data.globalOffset.slideY(-2.0f);
        mainItemRotation.setSmoothness(0.9f).orientX(90.0f * attackState);
        float renderRotationY = 30.0f + 360.0f * var5;
        data.renderRotation.orientInstantY(MathHelper.wrapDegrees((float)(-renderRotationY * handDirMtp)));
    }
}

