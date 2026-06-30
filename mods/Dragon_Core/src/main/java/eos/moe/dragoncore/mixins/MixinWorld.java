/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.state.IBlockState
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 */
package eos.moe.dragoncore.mixins;

import eos.moe.dragoncore.jja;
import eos.moe.dragoncore.nw;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={World.class})
public class MixinWorld {
    public MixinWorld() {
        MixinWorld a2;
    }

    @Inject(method={"removeEntity"}, at={@At(value="RETURN")})
    private /* synthetic */ void mixin_removeEntity(Entity a2, CallbackInfo a3) {
        if (a2 != null && !(a2 instanceof EntityItem)) {
            nw.ALLATORIxDEMO(a2.getUniqueID());
        }
    }

    @Redirect(method={"getRawLight"}, remap=false, at=@At(value="INVOKE", target="Lnet/minecraft/block/Block;getLightValue(Lnet/minecraft/block/state/IBlockState;Lnet/minecraft/world/IBlockAccess;Lnet/minecraft/util/math/BlockPos;)I"))
    private /* synthetic */ int mixin_removeEntity(Block a2, IBlockState a3, IBlockAccess a4, BlockPos a5) {
        return jja.ALLATORIxDEMO(a2, a3, a4, a5);
    }
}

