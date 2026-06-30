/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.EnumHandSide
 *  net.minecraft.util.math.MathHelper
 */
package goblinbob.mobends.standard.animation.bit.biped;

import goblinbob.mobends.core.animation.bit.AnimationBit;
import goblinbob.mobends.core.client.event.DataUpdateHandler;
import goblinbob.mobends.core.client.model.ModelPartTransform;
import goblinbob.mobends.standard.data.BipedEntityData;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.math.MathHelper;

public class EatingAnimationBit
extends AnimationBit<BipedEntityData<?>> {
    private static final String[] ACTIONS = new String[]{"eating"};
    protected EnumHandSide actionHand = EnumHandSide.RIGHT;
    protected float bringUpAnimation;

    @Override
    public String[] getActions(BipedEntityData<?> data) {
        return ACTIONS;
    }

    public void setActionHand(EnumHandSide handSide) {
        this.actionHand = handSide;
    }

    @Override
    public void onPlay(BipedEntityData<?> data) {
        this.bringUpAnimation = 0.0f;
    }

    @Override
    public void perform(BipedEntityData<?> data) {
        ModelPartTransform mainForeArm;
        float ticks = DataUpdateHandler.getTicks();
        boolean mainHandSwitch = this.actionHand == EnumHandSide.RIGHT;
        float handDirMtp = mainHandSwitch ? 1.0f : -1.0f;
        ModelPartTransform mainArm = mainHandSwitch ? data.rightArm : data.leftArm;
        ModelPartTransform modelPartTransform = mainForeArm = mainHandSwitch ? data.rightForeArm : data.leftForeArm;
        if (this.bringUpAnimation < 1.0f) {
            this.bringUpAnimation += DataUpdateHandler.ticksPerFrame * 0.15f;
            this.bringUpAnimation = Math.min(this.bringUpAnimation, 1.0f);
        } else {
            float wiggle = MathHelper.func_76134_b((float)(ticks * 1.0f));
            data.head.rotation.orientX(wiggle * 5.0f).rotateY(15.0f * handDirMtp);
        }
        mainArm.rotation.orientX(this.bringUpAnimation * -80.0f).rotateZ(45.0f * this.bringUpAnimation * handDirMtp);
        mainForeArm.rotation.orientX(this.bringUpAnimation * -45.0f);
    }
}

