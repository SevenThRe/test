/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.SoundType
 *  net.minecraft.block.material.Material
 *  net.minecraft.block.state.IBlockState
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.audio.ISound
 *  net.minecraft.client.audio.PositionedSoundRecord
 *  net.minecraft.client.multiplayer.PlayerControllerMP
 *  net.minecraft.client.network.NetHandlerPlayClient
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.client.CPacketPlayerDigging
 *  net.minecraft.network.play.client.CPacketPlayerDigging$Action
 *  net.minecraft.util.EnumFacing
 *  net.minecraft.util.SoundCategory
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.world.GameType
 *  net.minecraft.world.World
 */
package eos.moe.dragoncore.mixins;

import eos.moe.dragoncore.interfaces.IPlayerControllerMP;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.multiplayer.PlayerControllerMP;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketPlayerDigging;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.GameType;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={PlayerControllerMP.class})
public abstract class MixinTest1
implements IPlayerControllerMP {
    @Shadow
    private int blockHitDelay;
    @Shadow
    private GameType currentGameType;
    @Shadow
    @Final
    private Minecraft mc;
    @Shadow
    @Final
    private NetHandlerPlayClient connection;
    @Shadow
    private boolean isHittingBlock;
    @Shadow
    private float curBlockDamageMP;
    @Shadow
    private float stepSoundTickCounter;
    @Shadow
    private BlockPos currentBlock;
    private int Dragon_blockHitDelay;

    public MixinTest1() {
        MixinTest1 a2;
    }

    @Shadow
    protected abstract void syncCurrentPlayItem();

    @Shadow
    public static void clickBlockCreative(Minecraft a2, PlayerControllerMP a3, BlockPos a4, EnumFacing a5) {
    }

    @Shadow
    protected abstract boolean isHittingPosition(BlockPos var1);

    @Shadow
    public abstract boolean onPlayerDestroyBlock(BlockPos var1);

    @Shadow
    public abstract boolean clickBlock(BlockPos var1, EnumFacing var2);

    @Inject(method={"clickBlock"}, at={@At(value="INVOKE", target="Lnet/minecraft/client/multiplayer/PlayerControllerMP;clickBlockCreative(Lnet/minecraft/client/Minecraft;Lnet/minecraft/client/multiplayer/PlayerControllerMP;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/util/EnumFacing;)V")})
    private /* synthetic */ void mixin_clickBlock(BlockPos a2, EnumFacing a3, CallbackInfoReturnable<Boolean> a4) {
        a.Dragon_blockHitDelay = 5;
    }

    @Override
    public boolean onDamage(BlockPos a2, EnumFacing a3) {
        MixinTest1 a4;
        a4.syncCurrentPlayItem();
        if (a4.Dragon_blockHitDelay > 0) {
            --a4.Dragon_blockHitDelay;
            return true;
        }
        if (a4.currentGameType.isCreative() && a4.mc.world.getWorldBorder().contains(a2)) {
            a4.Dragon_blockHitDelay = 5;
            a4.mc.getTutorial().onHitBlock(a4.mc.world, a2, a4.mc.world.getBlockState(a2), 1.0f);
            a4.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.START_DESTROY_BLOCK, a2, a3));
            MixinTest1.clickBlockCreative(a4.mc, (PlayerControllerMP)a4, a2, a3);
            return true;
        }
        if (a4.isHittingPosition(a2)) {
            IBlockState a5 = a4.mc.world.getBlockState(a2);
            Block a6 = a5.getBlock();
            if (a5.getMaterial() == Material.AIR) {
                a4.isHittingBlock = false;
                return false;
            }
            a4.curBlockDamageMP += a5.getPlayerRelativeBlockHardness((EntityPlayer)a4.mc.player, a4.mc.player.world, a2);
            if (a4.stepSoundTickCounter % 4.0f == 0.0f) {
                SoundType a7 = a6.getSoundType(a5, (World)a4.mc.world, a2, (Entity)a4.mc.player);
                a4.mc.getSoundHandler().playSound((ISound)new PositionedSoundRecord(a7.getHitSound(), SoundCategory.NEUTRAL, (a7.getVolume() + 1.0f) / 8.0f, a7.getPitch() * 0.5f, a2));
            }
            a4.stepSoundTickCounter += 1.0f;
            a4.mc.getTutorial().onHitBlock(a4.mc.world, a2, a5, MathHelper.clamp((float)a4.curBlockDamageMP, (float)0.0f, (float)1.0f));
            if (a4.curBlockDamageMP >= 1.0f) {
                a4.isHittingBlock = false;
                IPlayerControllerMP.send(a4.connection, a2, a3);
                a4.onPlayerDestroyBlock(a2);
                a4.curBlockDamageMP = 0.0f;
                a4.stepSoundTickCounter = 0.0f;
                a4.Dragon_blockHitDelay = 5;
            }
            a4.mc.world.sendBlockBreakProgress(a4.mc.player.getEntityId(), a4.currentBlock, (int)(a4.curBlockDamageMP * 10.0f) - 1);
            return true;
        }
        return a4.clickBlock(a2, a3);
    }
}

