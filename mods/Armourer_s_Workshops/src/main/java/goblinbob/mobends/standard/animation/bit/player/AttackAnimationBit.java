/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.entity.AbstractClientPlayer
 *  net.minecraft.init.Items
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.EnumHand
 */
package goblinbob.mobends.standard.animation.bit.player;

import goblinbob.mobends.core.animation.bit.AnimationBit;
import goblinbob.mobends.core.animation.layer.HardAnimationLayer;
import goblinbob.mobends.dragon.AnimationHelper;
import goblinbob.mobends.standard.animation.bit.biped.AttackSlashDownAnimationBit;
import goblinbob.mobends.standard.animation.bit.biped.AttackSlashInwardAnimationBit;
import goblinbob.mobends.standard.animation.bit.biped.AttackSlashOutwardAnimationBit;
import goblinbob.mobends.standard.animation.bit.biped.AttackSlashUpAnimationBit;
import goblinbob.mobends.standard.animation.bit.biped.AttackStanceSprintAnimationBit;
import goblinbob.mobends.standard.animation.bit.biped.AttackWhirlSlashAnimationBit;
import goblinbob.mobends.standard.animation.bit.biped.FistGuardAnimationBit;
import goblinbob.mobends.standard.animation.bit.player.AttackStanceAnimationBit;
import goblinbob.mobends.standard.animation.bit.player.PunchAnimationBit;
import goblinbob.mobends.standard.data.BipedEntityData;
import goblinbob.mobends.standard.data.PlayerData;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;

public class AttackAnimationBit
extends AnimationBit<PlayerData> {
    protected HardAnimationLayer<BipedEntityData<?>> layerBase = new HardAnimationLayer();
    protected AttackStanceAnimationBit bitAttackStance = new AttackStanceAnimationBit();
    protected AttackStanceSprintAnimationBit bitAttackStanceSprint = new AttackStanceSprintAnimationBit();
    protected AttackSlashUpAnimationBit bitAttackSlashUp = new AttackSlashUpAnimationBit();
    protected AttackSlashDownAnimationBit bitAttackSlashDown = new AttackSlashDownAnimationBit();
    protected AttackSlashInwardAnimationBit bitAttackSlashInward = new AttackSlashInwardAnimationBit();
    protected AttackSlashOutwardAnimationBit bitAttackSlashOutward = new AttackSlashOutwardAnimationBit();
    protected AttackWhirlSlashAnimationBit bitAttackWhirlSlash = new AttackWhirlSlashAnimationBit();
    protected FistGuardAnimationBit bitFistGuard = new FistGuardAnimationBit();
    protected PunchAnimationBit bitPunch = new PunchAnimationBit();

    @Override
    public String[] getActions(PlayerData entityData) {
        if (this.layerBase.isPlaying()) {
            return this.layerBase.getPerformedBit().getActions(entityData);
        }
        return null;
    }

    public boolean shouldPerformAttack(AbstractClientPlayer player) {
        ItemStack heldItemStack = player.getHeldItem(EnumHand.MAIN_HAND);
        return heldItemStack.getItem() != Items.AIR;
    }

    @Override
    public void perform(PlayerData playerData) {
        if (AnimationHelper.isOnPlayAnimation(playerData.getEntity())) {
            return;
        }
        AbstractClientPlayer player = (AbstractClientPlayer)playerData.getEntity();
        if (this.shouldPerformAttack(player)) {
            if (playerData.getTicksAfterAttack() < 10.0f) {
                if (playerData.getCurrentAttack() == 1) {
                    this.layerBase.playOrContinueBit(this.bitAttackSlashUp, playerData);
                } else if (playerData.getCurrentAttack() == 2) {
                    this.layerBase.playOrContinueBit(this.bitAttackSlashDown, playerData);
                } else if (playerData.getCurrentAttack() == 3) {
                    this.layerBase.playOrContinueBit(this.bitAttackSlashInward, playerData);
                } else if (playerData.getCurrentAttack() == 4) {
                    this.layerBase.playOrContinueBit(this.bitAttackSlashOutward, playerData);
                } else if (playerData.getCurrentAttack() == 5) {
                    this.layerBase.playOrContinueBit(this.bitAttackWhirlSlash, playerData);
                } else {
                    this.layerBase.clearAnimation();
                }
            } else if (playerData.getTicksAfterAttack() < 60.0f && playerData.isOnGround()) {
                if (player.isSprinting()) {
                    this.layerBase.playOrContinueBit(this.bitAttackStanceSprint, playerData);
                } else if (playerData.isStillHorizontally()) {
                    this.layerBase.playOrContinueBit(this.bitAttackStance, playerData);
                } else {
                    this.layerBase.clearAnimation();
                }
            } else {
                this.layerBase.clearAnimation();
            }
        } else if (playerData.getTicksAfterAttack() < 10.0f) {
            this.layerBase.playOrContinueBit(this.bitPunch, playerData);
        } else if (playerData.getTicksAfterAttack() < 60.0f && playerData.isStillHorizontally()) {
            this.layerBase.playOrContinueBit(this.bitFistGuard, playerData);
        } else {
            this.layerBase.clearAnimation();
        }
        this.layerBase.perform(playerData);
    }
}

