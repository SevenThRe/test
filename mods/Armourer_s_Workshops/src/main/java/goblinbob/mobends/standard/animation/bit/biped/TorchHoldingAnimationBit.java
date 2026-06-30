/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.Item
 *  net.minecraft.util.EnumHand
 *  net.minecraft.util.EnumHandSide
 */
package goblinbob.mobends.standard.animation.bit.biped;

import goblinbob.mobends.core.animation.bit.AnimationBit;
import goblinbob.mobends.core.client.model.ModelPartTransform;
import goblinbob.mobends.standard.data.BipedEntityData;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumHandSide;

public class TorchHoldingAnimationBit
extends AnimationBit<BipedEntityData<?>> {
    private static final String[] ACTIONS = new String[]{"torch_holding"};

    @Override
    public String[] getActions(BipedEntityData<?> data) {
        return ACTIONS;
    }

    private EnumHandSide getTorchHand(EntityLivingBase living) {
        EnumHandSide mainHand = living.getPrimaryHand();
        EnumHandSide offHand = mainHand == EnumHandSide.LEFT ? EnumHandSide.RIGHT : EnumHandSide.LEFT;
        Item mainItem = living.getHeldItem(EnumHand.MAIN_HAND).getItem();
        Item offItem = living.getHeldItem(EnumHand.OFF_HAND).getItem();
        Item torch = Item.getItemFromBlock((Block)Blocks.TORCH);
        if (mainItem.equals(torch)) {
            return mainHand;
        }
        if (offItem.equals(torch)) {
            return offHand;
        }
        return null;
    }

    @Override
    public void perform(BipedEntityData<?> data) {
        Object living = data.getEntity();
        EnumHandSide torchHand = this.getTorchHand((EntityLivingBase)living);
        if (torchHand == null) {
            return;
        }
        ModelPartTransform mainArm = torchHand == EnumHandSide.RIGHT ? data.rightArm : data.leftArm;
        ModelPartTransform mainForeArm = torchHand == EnumHandSide.RIGHT ? data.rightForeArm : data.leftForeArm;
        mainArm.getRotation().orientX(-90.0f + ((Float)data.headPitch.get()).floatValue() * 0.5f).rotateY(((Float)data.headYaw.get()).floatValue() * 0.7f);
        mainForeArm.getRotation().orientX(-5.0f);
    }
}

