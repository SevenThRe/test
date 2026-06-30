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
    private int field_78781_i;
    @Shadow
    private GameType field_78779_k;
    @Shadow
    @Final
    private Minecraft field_78776_a;
    @Shadow
    @Final
    private NetHandlerPlayClient field_78774_b;
    @Shadow
    private boolean field_78778_j;
    @Shadow
    private float field_78770_f;
    @Shadow
    private float field_78780_h;
    @Shadow
    private BlockPos field_178895_c;
    private int Dragon_blockHitDelay;

    public MixinTest1() {
        MixinTest1 a2;
    }

    @Shadow
    protected abstract void func_78750_j();

    @Shadow
    public static void func_178891_a(Minecraft a2, PlayerControllerMP a3, BlockPos a4, EnumFacing a5) {
    }

    @Shadow
    protected abstract boolean func_178893_a(BlockPos var1);

    @Shadow
    public abstract boolean func_187103_a(BlockPos var1);

    @Shadow
    public abstract boolean func_180511_b(BlockPos var1, EnumFacing var2);

    @Inject(method={"clickBlock"}, at={@At(value="INVOKE", target="Lnet/minecraft/client/multiplayer/PlayerControllerMP;clickBlockCreative(Lnet/minecraft/client/Minecraft;Lnet/minecraft/client/multiplayer/PlayerControllerMP;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/util/EnumFacing;)V")})
    private /* synthetic */ void mixin_clickBlock(BlockPos a2, EnumFacing a3, CallbackInfoReturnable<Boolean> a4) {
        a.Dragon_blockHitDelay = 5;
    }

    @Override
    public boolean onDamage(BlockPos a2, EnumFacing a3) {
        MixinTest1 a4;
        a4.func_78750_j();
        if (a4.Dragon_blockHitDelay > 0) {
            --a4.Dragon_blockHitDelay;
            return true;
        }
        if (a4.field_78779_k.func_77145_d() && a4.field_78776_a.field_71441_e.func_175723_af().func_177746_a(a2)) {
            a4.Dragon_blockHitDelay = 5;
            a4.field_78776_a.func_193032_ao().func_193294_a(a4.field_78776_a.field_71441_e, a2, a4.field_78776_a.field_71441_e.func_180495_p(a2), 1.0f);
            a4.field_78774_b.func_147297_a((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.START_DESTROY_BLOCK, a2, a3));
            MixinTest1.func_178891_a(a4.field_78776_a, (PlayerControllerMP)a4, a2, a3);
            return true;
        }
        if (a4.func_178893_a(a2)) {
            IBlockState a5 = a4.field_78776_a.field_71441_e.func_180495_p(a2);
            Block a6 = a5.func_177230_c();
            if (a5.func_185904_a() == Material.field_151579_a) {
                a4.field_78778_j = false;
                return false;
            }
            a4.field_78770_f += a5.func_185903_a((EntityPlayer)a4.field_78776_a.field_71439_g, a4.field_78776_a.field_71439_g.field_70170_p, a2);
            if (a4.field_78780_h % 4.0f == 0.0f) {
                SoundType a7 = a6.getSoundType(a5, (World)a4.field_78776_a.field_71441_e, a2, (Entity)a4.field_78776_a.field_71439_g);
                a4.field_78776_a.func_147118_V().func_147682_a((ISound)new PositionedSoundRecord(a7.func_185846_f(), SoundCategory.NEUTRAL, (a7.func_185843_a() + 1.0f) / 8.0f, a7.func_185847_b() * 0.5f, a2));
            }
            a4.field_78780_h += 1.0f;
            a4.field_78776_a.func_193032_ao().func_193294_a(a4.field_78776_a.field_71441_e, a2, a5, MathHelper.func_76131_a((float)a4.field_78770_f, (float)0.0f, (float)1.0f));
            if (a4.field_78770_f >= 1.0f) {
                a4.field_78778_j = false;
                IPlayerControllerMP.send(a4.field_78774_b, a2, a3);
                a4.func_187103_a(a2);
                a4.field_78770_f = 0.0f;
                a4.field_78780_h = 0.0f;
                a4.Dragon_blockHitDelay = 5;
            }
            a4.field_78776_a.field_71441_e.func_175715_c(a4.field_78776_a.field_71439_g.func_145782_y(), a4.field_178895_c, (int)(a4.field_78770_f * 10.0f) - 1);
            return true;
        }
        return a4.func_180511_b(a2, a3);
    }
}

