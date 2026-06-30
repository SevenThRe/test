/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.BlockSkull
 *  net.minecraft.block.state.IBlockState
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.world.IBlockAccess
 */
package eos.moe.dragoncore.mixins;

import eos.moe.dragoncore.cy;
import eos.moe.dragoncore.er;
import eos.moe.dragoncore.gq;
import eos.moe.dragoncore.vu;
import net.minecraft.block.BlockSkull;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={BlockSkull.class})
public abstract class MixinBlockSkull {
    public MixinBlockSkull() {
        MixinBlockSkull a2;
    }

    @Inject(method={"getBoundingBox"}, at={@At(value="HEAD")}, cancellable=true)
    private /* synthetic */ void mixin_getBoundingBox(IBlockState a2, IBlockAccess a3, BlockPos a4, CallbackInfoReturnable<AxisAlignedBB> a5) {
        TileEntity a6 = a3.func_175625_s(a4);
        gq a7 = cy.q.ALLATORIxDEMO(a6);
        if (a7 != null) {
            a5.setReturnValue(a7.ALLATORIxDEMO());
            return;
        }
        String a8 = vu.c(a6);
        if (a8 != null && a8.startsWith("none")) {
            a5.setReturnValue(new AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 1.0, 1.0));
            return;
        }
        er a9 = vu.ALLATORIxDEMO(a6);
        if (a9 != null) {
            a5.setReturnValue(a9.y);
        }
    }

    public int getLightValue(IBlockState a2, IBlockAccess a3, BlockPos a4) {
        TileEntity a5 = a3.func_175625_s(a4);
        if (a5 != null) {
            gq a6 = cy.q.ALLATORIxDEMO(a5);
            if (a6 != null) {
                return a6.ALLATORIxDEMO();
            }
            int a7 = vu.ALLATORIxDEMO(a5);
            if (a7 > 0) {
                return a7;
            }
        }
        return a2.func_185906_d();
    }
}

