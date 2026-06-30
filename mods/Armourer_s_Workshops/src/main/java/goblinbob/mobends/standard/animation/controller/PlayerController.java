/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.entity.AbstractClientPlayer
 *  net.minecraft.client.model.ModelBiped$ArmPose
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.item.EnumAction
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemFood
 *  net.minecraft.item.ItemStack
 *  net.minecraft.item.ItemSword
 *  net.minecraft.util.EnumHand
 *  net.minecraft.util.EnumHandSide
 */
package goblinbob.mobends.standard.animation.controller;

import baka.animation.bit.AnimationPipeline;
import baka.animation.bit.FallingLegAnimationBit;
import baka.animation.bit.JumpLegAnimationBit;
import baka.animation.bit.RidingLegAnimationBit;
import baka.animation.bit.SittingLegAnimationBit;
import baka.animation.bit.SneakLegAnimationBit;
import baka.animation.bit.SprintLegAnimationBit;
import baka.animation.bit.SprintLegJumpAnimationBit;
import baka.animation.bit.WalkLegAnimationBit;
import baka.animation.instruct.LegActionInstruct;
import goblinbob.mobends.core.animation.bit.AnimationBit;
import goblinbob.mobends.core.animation.controller.IAnimationController;
import goblinbob.mobends.core.animation.keyframe.ArmatureMask;
import goblinbob.mobends.core.animation.layer.HardAnimationLayer;
import goblinbob.mobends.standard.animation.bit.biped.BowAnimationBit;
import goblinbob.mobends.standard.animation.bit.biped.EatingAnimationBit;
import goblinbob.mobends.standard.animation.bit.biped.FallingAnimationBit;
import goblinbob.mobends.standard.animation.bit.biped.HarvestAnimationBit;
import goblinbob.mobends.standard.animation.bit.biped.JumpAnimationBit;
import goblinbob.mobends.standard.animation.bit.biped.LadderClimbAnimationBit;
import goblinbob.mobends.standard.animation.bit.biped.RidingAnimationBit;
import goblinbob.mobends.standard.animation.bit.biped.ShieldAnimationBit;
import goblinbob.mobends.standard.animation.bit.biped.SittingAnimationBit;
import goblinbob.mobends.standard.animation.bit.biped.SneakAnimationBit;
import goblinbob.mobends.standard.animation.bit.biped.StandAnimationBit;
import goblinbob.mobends.standard.animation.bit.biped.SwimmingAnimationBit;
import goblinbob.mobends.standard.animation.bit.biped.TorchHoldingAnimationBit;
import goblinbob.mobends.standard.animation.bit.player.AttackAnimationBit;
import goblinbob.mobends.standard.animation.bit.player.CapeAnimationBit;
import goblinbob.mobends.standard.animation.bit.player.ElytraAnimationBit;
import goblinbob.mobends.standard.animation.bit.player.FlyingAnimationBit;
import goblinbob.mobends.standard.animation.bit.player.SleepingAnimationBit;
import goblinbob.mobends.standard.animation.bit.player.SprintAnimationBit;
import goblinbob.mobends.standard.animation.bit.player.SprintJumpAnimationBit;
import goblinbob.mobends.standard.animation.bit.player.WalkAnimationBit;
import goblinbob.mobends.standard.data.BipedEntityData;
import goblinbob.mobends.standard.data.PlayerData;
import goblinbob.mobends.standard.main.ModConfig;
import java.util.ArrayList;
import java.util.Collection;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumHandSide;

public class PlayerController
implements IAnimationController<PlayerData> {
    protected HardAnimationLayer<BipedEntityData<?>> layerBase = new HardAnimationLayer();
    protected HardAnimationLayer<BipedEntityData<?>> layerTorch = new HardAnimationLayer();
    protected HardAnimationLayer<BipedEntityData<?>> layerSneak = new HardAnimationLayer();
    protected HardAnimationLayer<BipedEntityData<?>> layerAction = new HardAnimationLayer();
    protected HardAnimationLayer<BipedEntityData<?>> layerCape = new HardAnimationLayer();
    protected AnimationBit<BipedEntityData<?>> bitStand = new StandAnimationBit();
    protected AnimationBit<BipedEntityData<?>> bitJump = new JumpAnimationBit();
    protected AnimationBit<BipedEntityData<?>> bitSneak = new SneakAnimationBit();
    protected AnimationBit<BipedEntityData<?>> bitLadderClimb = new LadderClimbAnimationBit();
    protected AnimationBit<BipedEntityData<?>> bitSwimming = new SwimmingAnimationBit();
    protected AnimationBit<BipedEntityData<?>> bitRiding = new RidingAnimationBit();
    protected AnimationBit<BipedEntityData<?>> bitSitting = new SittingAnimationBit();
    protected AnimationBit<BipedEntityData<?>> bitFalling = new FallingAnimationBit();
    protected AnimationBit<PlayerData> bitWalk = new WalkAnimationBit();
    protected AnimationBit<PlayerData> bitSprint = new SprintAnimationBit();
    protected AnimationBit<PlayerData> bitSprintJump = new SprintJumpAnimationBit();
    protected AnimationBit<BipedEntityData<?>> bitTorchHolding = new TorchHoldingAnimationBit();
    protected AnimationBit<PlayerData> bitAttack = new AttackAnimationBit();
    protected FlyingAnimationBit bitFlying = new FlyingAnimationBit();
    protected ElytraAnimationBit bitElytra = new ElytraAnimationBit();
    protected BowAnimationBit bitBow = new BowAnimationBit();
    protected EatingAnimationBit bitEating = new EatingAnimationBit();
    protected HarvestAnimationBit bitHarvest = new HarvestAnimationBit();
    protected ShieldAnimationBit bitShield = new ShieldAnimationBit();
    protected CapeAnimationBit bitCape = new CapeAnimationBit();
    protected SleepingAnimationBit bitSleeping = new SleepingAnimationBit();
    protected ArmatureMask upperBodyOnlyMask;
    private final AnimationPipeline pipeline = new AnimationPipeline(false, false);
    protected HardAnimationLayer<BipedEntityData<?>> layerCustom = new HardAnimationLayer();
    protected AnimationBit<PlayerData> bitLegFalling = new FallingLegAnimationBit();
    protected AnimationBit<PlayerData> bitLegWalk = new WalkLegAnimationBit();
    protected AnimationBit<PlayerData> bitLegSprint = new SprintLegAnimationBit();
    protected AnimationBit<PlayerData> bitLegSprintJump = new SprintLegJumpAnimationBit();
    protected AnimationBit<PlayerData> bitLegRiding = new RidingLegAnimationBit();
    protected AnimationBit<PlayerData> bitLegSitting = new SittingLegAnimationBit();
    protected AnimationBit<PlayerData> bitLegJump = new JumpLegAnimationBit();
    protected AnimationBit<PlayerData> bitLegSneak = new SneakLegAnimationBit();
    private ItemStack activeItem = ItemStack.field_190927_a;
    public long startTime = -1L;

    public ItemStack getActiveItem() {
        return this.activeItem;
    }

    public void setActiveItem(ItemStack activeItem) {
        this.activeItem = activeItem;
        this.startTime = !this.activeItem.func_190926_b() ? 0L : -1L;
    }

    public PlayerController() {
        this.upperBodyOnlyMask = new ArmatureMask(ArmatureMask.Mode.EXCLUDE_ONLY);
        this.upperBodyOnlyMask.exclude("root");
        this.upperBodyOnlyMask.exclude("head");
        this.upperBodyOnlyMask.exclude("leftLeg");
        this.upperBodyOnlyMask.exclude("leftForeLeg");
        this.upperBodyOnlyMask.exclude("rightLeg");
        this.upperBodyOnlyMask.exclude("rightForeLeg");
    }

    public static boolean isHoldingFood(Item activeItem) {
        return activeItem instanceof ItemFood;
    }

    public static boolean isHoldingBow(ModelBiped.ArmPose mainArmPose, ModelBiped.ArmPose offArmPose) {
        return mainArmPose == ModelBiped.ArmPose.BOW_AND_ARROW || offArmPose == ModelBiped.ArmPose.BOW_AND_ARROW;
    }

    public static boolean isShielding(ModelBiped.ArmPose mainArmPose, ModelBiped.ArmPose offArmPose) {
        return mainArmPose == ModelBiped.ArmPose.BLOCK || offArmPose == ModelBiped.ArmPose.BLOCK;
    }

    public static boolean isHoldingWeapon(Item heldItemMainhand) {
        ModConfig.ItemClassification classification = ModConfig.getItemClassification(heldItemMainhand);
        return classification == ModConfig.ItemClassification.WEAPON || classification == ModConfig.ItemClassification.UNKNOWN && heldItemMainhand instanceof ItemSword;
    }

    public void performActionAnimations(PlayerData data, AbstractClientPlayer player) {
        EnumHandSide activeHandSide;
        if (player.func_70089_S() && player.func_70608_bn()) {
            this.layerAction.clearAnimation();
            return;
        }
        EnumHandSide primaryHand = player.func_184591_cq();
        EnumHandSide offHand = primaryHand == EnumHandSide.RIGHT ? EnumHandSide.LEFT : EnumHandSide.RIGHT;
        ItemStack heldItemMainhand = player.func_184614_ca();
        ItemStack heldItemOffhand = player.func_184592_cb();
        Item activeItem = player.func_184607_cu().func_77973_b();
        ModelBiped.ArmPose armPoseMain = this.getAction(player, heldItemMainhand);
        ModelBiped.ArmPose armPoseOff = this.getAction(player, heldItemOffhand);
        EnumHandSide enumHandSide = activeHandSide = player.func_184600_cs() == EnumHand.MAIN_HAND ? primaryHand : offHand;
        if (PlayerController.isShielding(armPoseMain, armPoseOff)) {
            this.bitShield.setActionHand(armPoseMain == ModelBiped.ArmPose.BLOCK ? primaryHand : offHand);
            this.layerAction.playOrContinueBit(this.bitShield, data);
        } else if (!this.activeItem.func_190926_b()) {
            this.bitEating.setActionHand(primaryHand);
            this.layerAction.playOrContinueBit(this.bitEating, data);
        } else if (PlayerController.isHoldingFood(activeItem)) {
            this.bitEating.setActionHand(activeHandSide);
            this.layerAction.playOrContinueBit(this.bitEating, data);
        } else if (PlayerController.isHoldingBow(armPoseMain, armPoseOff)) {
            this.bitBow.setActionHand(armPoseMain == ModelBiped.ArmPose.BOW_AND_ARROW ? primaryHand : offHand);
            this.layerAction.playOrContinueBit(this.bitBow, data);
        } else if (!player.field_82175_bq) {
            this.layerAction.clearAnimation();
        }
    }

    @Override
    public Collection<String> perform(PlayerData data) {
        boolean legAction;
        AbstractClientPlayer player = (AbstractClientPlayer)data.getEntity();
        player.getEntityData().func_74757_a("OnUseItem", !this.activeItem.func_190926_b());
        ArrayList<String> actions = new ArrayList<String>();
        boolean customAction = !this.pipeline.isFinish();
        boolean bl = legAction = customAction && this.pipeline.isLegAction();
        if (legAction) {
            this.layerCustom.playOrContinueBit(this.pipeline, data);
            this.layerCustom.perform(data, actions);
            return actions;
        }
        this.layerCape.playOrContinueBit(this.bitCape, data);
        if (player.func_70089_S() && player.func_70608_bn()) {
            this.layerBase.playOrContinueBit(this.bitSleeping, data);
            this.layerSneak.clearAnimation();
        } else if (player.func_184218_aH()) {
            if (player.func_184187_bx() instanceof EntityLivingBase) {
                if (customAction) {
                    this.layerCustom.playOrContinueBit(this.pipeline, data);
                    this.layerBase.playOrContinueBit(this.bitLegRiding, data);
                } else {
                    this.layerBase.playOrContinueBit(this.bitRiding, data);
                }
            } else if (customAction) {
                this.layerCustom.playOrContinueBit(this.pipeline, data);
                this.layerBase.playOrContinueBit(this.bitLegSitting, data);
            } else {
                this.layerBase.playOrContinueBit(this.bitSitting, data);
            }
            this.layerSneak.clearAnimation();
        } else if (player.func_184599_cB() > 4) {
            this.layerBase.playOrContinueBit(this.bitElytra, data);
            this.layerSneak.clearAnimation();
            this.layerTorch.clearAnimation();
        } else if (data.isClimbing()) {
            this.layerBase.playOrContinueBit(this.bitLadderClimb, data);
            this.layerSneak.clearAnimation();
            this.layerTorch.clearAnimation();
        } else if (player.func_70090_H() && !customAction) {
            this.layerBase.playOrContinueBit(this.bitSwimming, data);
            this.layerSneak.clearAnimation();
            this.layerTorch.clearAnimation();
        } else if (!data.isOnGround() || data.getTicksAfterTouchdown() < 1.0f) {
            if (data.isFlying()) {
                this.layerBase.playOrContinueBit(this.bitFlying, data);
            } else if (data.getTicksFalling() > 10.0f) {
                if (customAction) {
                    this.layerBase.playOrContinueBit(this.bitLegFalling, data);
                    this.layerCustom.playOrContinueBit(this.pipeline, data);
                } else {
                    this.layerBase.playOrContinueBit(this.bitFalling, data);
                }
            } else if (player.func_70051_ag()) {
                if (customAction) {
                    this.layerBase.playOrContinueBit(this.bitLegSprintJump, data);
                    this.layerCustom.playOrContinueBit(this.pipeline, data);
                } else {
                    this.layerBase.playOrContinueBit(this.bitSprintJump, data);
                }
            } else if (customAction) {
                this.layerBase.playOrContinueBit(this.bitLegJump, data);
                this.layerCustom.playOrContinueBit(this.pipeline, data);
            } else {
                this.layerBase.playOrContinueBit(this.bitJump, data);
            }
            this.layerSneak.clearAnimation();
            this.layerTorch.clearAnimation();
        } else {
            if (data.isStillHorizontally()) {
                if (!customAction) {
                    this.layerBase.playOrContinueBit(this.bitStand, data);
                    this.layerTorch.playOrContinueBit(this.bitTorchHolding, data);
                }
            } else if (player.func_70051_ag()) {
                if (customAction) {
                    this.layerCustom.playOrContinueBit(this.pipeline, data);
                    this.layerBase.playOrContinueBit(this.bitLegSprint, data);
                } else {
                    this.layerBase.playOrContinueBit(this.bitSprint, data);
                    this.layerTorch.clearAnimation();
                }
            } else if (customAction) {
                this.layerCustom.playOrContinueBit(this.pipeline, data);
                this.layerBase.playOrContinueBit(this.bitLegWalk, data);
            } else {
                this.layerBase.playOrContinueBit(this.bitWalk, data);
                this.layerTorch.playOrContinueBit(this.bitTorchHolding, data);
            }
            if (player.func_70093_af()) {
                if (customAction) {
                    this.layerCustom.playOrContinueBit(this.pipeline, data);
                    this.layerSneak.playOrContinueBit(this.bitLegSneak, data);
                } else {
                    this.layerSneak.playOrContinueBit(this.bitSneak, data);
                }
            } else if (customAction) {
                this.layerCustom.playOrContinueBit(this.pipeline, data);
            } else {
                this.layerSneak.clearAnimation();
            }
        }
        this.performActionAnimations(data, player);
        if (!data.isStillHorizontally() || !customAction) {
            this.layerBase.perform(data, actions);
        }
        this.layerSneak.perform(data, actions);
        this.layerCape.perform(data, actions);
        if (!customAction) {
            this.layerTorch.perform(data, actions);
            this.layerAction.perform(data, actions);
        } else {
            this.layerCustom.perform(data, actions);
        }
        return actions;
    }

    public boolean isPlaying() {
        return this.layerBase.isPlaying(this.bitSprint) || this.layerBase.isPlaying(this.bitSprintJump);
    }

    public boolean isCustomPlaying() {
        return !this.pipeline.isFinish();
    }

    private ModelBiped.ArmPose getAction(AbstractClientPlayer player, ItemStack heldItem) {
        if (!heldItem.func_190926_b()) {
            if (player.func_184605_cv() > 0) {
                EnumAction enumaction = heldItem.func_77975_n();
                if (enumaction == EnumAction.BLOCK) {
                    return ModelBiped.ArmPose.BLOCK;
                }
                if (enumaction == EnumAction.BOW) {
                    return ModelBiped.ArmPose.BOW_AND_ARROW;
                }
            }
            return ModelBiped.ArmPose.ITEM;
        }
        return ModelBiped.ArmPose.EMPTY;
    }

    public void interrupt(AnimationPipeline next) {
        this.pipeline.clear();
        this.pipeline.push(next);
        this.pipeline.setLegAction(next.isLegAction());
    }

    public void queue(AnimationPipeline next) {
        if (this.pipeline.isLegAction() != next.isLegAction()) {
            this.pipeline.push(new LegActionInstruct(next.isLegAction()));
        }
        this.pipeline.push(next);
    }

    public void queueIfEmpty(AnimationPipeline next) {
        if (this.pipeline.isFinish()) {
            this.pipeline.push(next);
            this.pipeline.setLegAction(next.isLegAction());
        }
    }
}

