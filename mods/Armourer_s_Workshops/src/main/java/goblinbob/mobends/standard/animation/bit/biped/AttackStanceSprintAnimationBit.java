/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.ItemSword
 *  net.minecraft.util.EnumHand
 *  net.minecraft.util.EnumHandSide
 */
package goblinbob.mobends.standard.animation.bit.biped;

import goblinbob.mobends.core.animation.bit.AnimationBit;
import goblinbob.mobends.core.client.model.ModelPartTransform;
import goblinbob.mobends.standard.data.BipedEntityData;
import net.minecraft.item.ItemSword;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumHandSide;

public class AttackStanceSprintAnimationBit
extends AnimationBit<BipedEntityData<?>> {
    private static final String[] ACTIONS = new String[]{"attack_stance_sprint"};

    @Override
    public String[] getActions(BipedEntityData<?> entityData) {
        return ACTIONS;
    }

    @Override
    public void perform(BipedEntityData<?> data) {
        ModelPartTransform offForeArm;
        data.localOffset.slideToZero(0.3f);
        Object living = data.getEntity();
        EnumHandSide primaryHand = living.func_184591_cq();
        boolean mainHandSwitch = primaryHand == EnumHandSide.RIGHT;
        float handDirMtp = mainHandSwitch ? 1.0f : -1.0f;
        ModelPartTransform mainArm = mainHandSwitch ? data.rightArm : data.leftArm;
        ModelPartTransform offArm = mainHandSwitch ? data.leftArm : data.rightArm;
        ModelPartTransform mainForeArm = mainHandSwitch ? data.rightForeArm : data.leftForeArm;
        ModelPartTransform modelPartTransform = offForeArm = mainHandSwitch ? data.leftForeArm : data.rightForeArm;
        if (living.func_184586_b(EnumHand.MAIN_HAND).func_77973_b() instanceof ItemSword) {
            data.swordTrail.add(data, 0.0f, 0.0f, -10.0f);
        }
        data.body.rotation.rotateY(20.0f * handDirMtp);
        data.head.rotation.rotateY(-20.0f * handDirMtp);
        mainArm.getRotation().orientZ(60.0f * handDirMtp);
        mainArm.getRotation().rotateY(60.0f);
        offArm.getRotation().rotateZ(-30.0f * handDirMtp);
        if (mainHandSwitch) {
            data.renderRightItemRotation.setSmoothness(0.3f).orientX(45.0f);
        } else {
            data.renderLeftItemRotation.setSmoothness(0.3f).orientX(45.0f);
        }
    }
}

