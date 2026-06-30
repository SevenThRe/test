/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.EnumHandSide
 */
package goblinbob.mobends.standard.animation.bit.biped;

import goblinbob.mobends.core.animation.bit.AnimationBit;
import goblinbob.mobends.standard.data.BipedEntityData;
import net.minecraft.util.EnumHandSide;

public class FistGuardAnimationBit
extends AnimationBit<BipedEntityData<?>> {
    private static final String[] ACTIONS = new String[]{"fist_guard"};

    @Override
    public String[] getActions(BipedEntityData<?> entityData) {
        return ACTIONS;
    }

    @Override
    public void perform(BipedEntityData<?> data) {
        Object living = data.getEntity();
        EnumHandSide primaryHand = living.getPrimaryHand();
        boolean mainHandSwitch = primaryHand == EnumHandSide.RIGHT;
        float handDirMtp = mainHandSwitch ? 1.0f : -1.0f;
        data.globalOffset.slideY(-2.0f);
        data.renderRotation.setSmoothness(0.3f).orientY(-20.0f * handDirMtp);
        data.rightArm.rotation.setSmoothness(0.3f).orientX(-90.0f).rotateZ(20.0f);
        data.rightForeArm.rotation.setSmoothness(0.3f).orientX(-80.0f);
        data.leftArm.rotation.setSmoothness(0.3f).orientX(-90.0f).rotateZ(-20.0f);
        data.leftForeArm.rotation.setSmoothness(0.3f).orientX(-80.0f);
        data.body.rotation.rotateX(10.0f);
        data.rightLeg.rotation.setSmoothness(0.3f).orientX(-30.0f).rotateZ(10.0f);
        data.leftLeg.rotation.setSmoothness(0.3f).orientX(-30.0f).rotateY(-25.0f).rotateZ(-10.0f);
        data.rightForeLeg.rotation.setSmoothness(0.3f).orientX(30.0f);
        data.leftForeLeg.rotation.setSmoothness(0.3f).orientX(30.0f);
        data.head.rotation.rotateX(-10.0f);
        data.head.rotation.rotateY(-20.0f * handDirMtp);
    }
}

