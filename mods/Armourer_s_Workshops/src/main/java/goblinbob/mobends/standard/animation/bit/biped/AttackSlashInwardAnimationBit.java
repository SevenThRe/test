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
import goblinbob.mobends.standard.data.BipedEntityData;
import net.minecraft.item.ItemSword;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.math.MathHelper;
import org.lwjgl.util.vector.Vector3f;

public class AttackSlashInwardAnimationBit
extends AnimationBit<BipedEntityData<?>> {
    private static final String[] ACTIONS = new String[]{"attack", "attack_slash_inward"};

    @Override
    public String[] getActions(BipedEntityData<?> entityData) {
        return ACTIONS;
    }

    @Override
    public void onPlay(BipedEntityData<?> data) {
        data.swordTrail.reset();
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
        if (data.getTicksAfterAttack() < 4.0f && living.getHeldItem(EnumHand.MAIN_HAND).getItem() instanceof ItemSword) {
            data.swordTrail.add(data);
        }
        float attackState = data.getTicksAfterAttack() / 10.0f;
        float armSwing = attackState * 3.0f;
        armSwing = Math.min(armSwing, 1.0f);
        Vector3f bodyRot = new Vector3f(0.0f, 0.0f, 0.0f);
        bodyRot.x = 20.0f - armSwing * 20.0f;
        bodyRot.y = -70.0f * armSwing * handDirMtp;
        data.body.rotation.setSmoothness(0.9f).orientX(bodyRot.x).orientY(bodyRot.y);
        data.head.rotation.setSmoothness(0.9f).orientX(MathHelper.wrapDegrees((float)((Float)data.headPitch.get()).floatValue()) - bodyRot.x).rotateY(MathHelper.wrapDegrees((float)((Float)data.headYaw.get()).floatValue()) - bodyRot.y);
        mainArm.getRotation().setSmoothness(0.9f).orientZ(90.0f * handDirMtp).rotateY((60.0f - armSwing * 180.0f) * handDirMtp);
        offArm.getRotation().setSmoothness(0.3f).orientZ(-20.0f * handDirMtp);
        mainForeArm.getRotation().setSmoothness(0.3f).orientX(-10.0f);
        offForeArm.getRotation().setSmoothness(0.3f).orientX(-60.0f);
        if (data.isStillHorizontally() && !living.isRiding()) {
            data.rightLeg.rotation.orientZ(5.0f).rotateY(15.0f).rotateX(-20.0f);
            data.leftLeg.rotation.orientZ(-5.0f).rotateY(-15.0f).rotateX(-20.0f);
            data.rightForeLeg.rotation.orientX(25.0f);
            data.renderRotation.setSmoothness(0.3f).orientY(0.0f * handDirMtp);
            data.globalOffset.slideY(-1.0f);
        }
        mainItemRotation.setSmoothness(0.9f).orientInstantX(50.0f);
    }
}

