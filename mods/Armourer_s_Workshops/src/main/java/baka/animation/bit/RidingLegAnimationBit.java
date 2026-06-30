/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.util.math.MathHelper
 */
package baka.animation.bit;

import goblinbob.mobends.core.animation.bit.AnimationBit;
import goblinbob.mobends.standard.data.PlayerData;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.MathHelper;

public class RidingLegAnimationBit
extends AnimationBit<PlayerData> {
    private static final String[] ACTIONS = new String[]{"riding"};
    private static final float PI = (float)Math.PI;

    @Override
    public String[] getActions(PlayerData entityData) {
        return ACTIONS;
    }

    @Override
    public void perform(PlayerData data) {
        Object living = data.getEntity();
        data.localOffset.slideToZero(0.3f);
        data.renderRotation.orientZero();
        data.centerRotation.setSmoothness(0.3f).orientZero();
        data.renderLeftItemRotation.orientZero();
        data.renderRightItemRotation.orientZero();
        data.leftLeg.rotation.orientX(-90.0f).rotateZ(-10.0f).rotateY(-25.0f);
        data.rightLeg.rotation.orientX(-90.0f).rotateZ(10.0f).rotateY(25.0f);
        data.leftForeLeg.rotation.orientX(60.0f);
        data.rightForeLeg.rotation.orientX(60.0f);
        Entity ridden = living.func_184187_bx();
        if (ridden instanceof EntityLivingBase) {
            EntityLivingBase riddenLiving = (EntityLivingBase)ridden;
            float relativeHeadYaw = MathHelper.func_76142_g((float)(((EntityLivingBase)living).field_70177_z - riddenLiving.field_70761_aq));
            float relativeYaw = MathHelper.func_76142_g((float)(((EntityLivingBase)living).field_70177_z - ((Float)data.headYaw.get()).floatValue() - riddenLiving.field_70761_aq));
            data.body.rotation.orientZ(MathHelper.func_76131_a((float)(-relativeHeadYaw * 0.25f), (float)-20.0f, (float)20.0f));
            data.leftLeg.rotation.rotateX(-MathHelper.func_76126_a((float)(relativeYaw / 180.0f * (float)Math.PI * 1.5f)) * 45.0f);
            data.rightLeg.rotation.rotateX(MathHelper.func_76126_a((float)(relativeYaw / 180.0f * (float)Math.PI * 1.5f)) * 45.0f);
        }
    }
}

