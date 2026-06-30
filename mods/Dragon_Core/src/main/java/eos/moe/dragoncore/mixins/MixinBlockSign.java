/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.Gson
 *  net.minecraft.block.BlockWallSign
 *  net.minecraft.block.state.IBlockState
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.tileentity.TileEntitySign
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.world.IBlockAccess
 */
package eos.moe.dragoncore.mixins;

import com.google.gson.Gson;
import eos.moe.dragoncore.data.SignData;
import eos.moe.dragoncore.renderer.SignRenderer;
import net.minecraft.block.BlockWallSign;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntitySign;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={BlockWallSign.class})
public class MixinBlockSign {
    private static final Gson gson = new Gson();

    public MixinBlockSign() {
        MixinBlockSign a2;
    }

    @Inject(method={"getBoundingBox"}, at={@At(value="HEAD")}, cancellable=true)
    public void getBoundingBox(IBlockState a2, IBlockAccess a3, BlockPos a4, CallbackInfoReturnable<AxisAlignedBB> a5) {
        TileEntity a6 = a3.func_175625_s(a4);
        if (a6 instanceof TileEntitySign) {
            TileEntitySign a7 = (TileEntitySign)a6;
            if (a7.field_145915_a.length <= 1) {
                return;
            }
            if (a7.field_145915_a[1] == null) {
                return;
            }
            String a8 = a7.field_145915_a[1].func_150254_d();
            SignData a9 = SignRenderer.getSignData(a8);
            if (a9 == null) {
                return;
            }
            if (a9.getModel() == null) {
                return;
            }
            AxisAlignedBB a10 = new AxisAlignedBB(0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
            a5.setReturnValue(a10);
        }
    }
}

