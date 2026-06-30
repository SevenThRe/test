/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.init.Blocks
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.tileentity.TileEntitySign
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.world.World
 */
package eos.moe.dragoncore.mixins;

import eos.moe.dragoncore.cy;
import eos.moe.dragoncore.er;
import eos.moe.dragoncore.gq;
import eos.moe.dragoncore.interfaces.FormatCacheTextComponentBase;
import eos.moe.dragoncore.vu;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntitySign;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={TileEntity.class})
public abstract class MixinTileEntity {
    @Shadow(remap=false)
    @Final
    public static AxisAlignedBB INFINITE_EXTENT_AABB;
    @Shadow
    protected World world;

    public MixinTileEntity() {
        MixinTileEntity a2;
    }

    @Shadow
    public abstract Block getBlockType();

    @Shadow
    public abstract BlockPos getPos();

    @Inject(method={"getRenderBoundingBox"}, at={@At(value="HEAD")}, cancellable=true, remap=false)
    private /* synthetic */ void mixin_getRenderBoundingBox(CallbackInfoReturnable<AxisAlignedBB> a2) {
        MixinTileEntity a3;
        Block a4 = a3.getBlockType();
        BlockPos a5 = a3.getPos();
        TileEntity a6 = (TileEntity)a3;
        if (a4 == Blocks.SKULL) {
            gq a7 = cy.q.ALLATORIxDEMO(a6);
            if (a7 != null) {
                a2.setReturnValue(new AxisAlignedBB(-5.0, -5.0, -5.0, 5.0, 5.0, 5.0).offset(a5));
                return;
            }
            er a8 = vu.ALLATORIxDEMO(a6);
            if (a8 != null) {
                a2.setReturnValue(new AxisAlignedBB(-5.0, -5.0, -5.0, 5.0, 5.0, 5.0).offset(a5));
                return;
            }
        } else if (a6 instanceof TileEntitySign) {
            TileEntitySign a9 = (TileEntitySign)a6;
            if (a9.signText.length <= 1) {
                return;
            }
            if (a9.signText[1] == null) {
                return;
            }
            String a10 = FormatCacheTextComponentBase.of(a9.signText[1]).toStringCache();
            if (a10.startsWith("{")) {
                a2.setReturnValue(new AxisAlignedBB(-1.0, -1.0, -1.0, 2.0, 2.0, 2.0).offset(a5));
                return;
            }
        }
        if (a4 == Blocks.WALL_BANNER || a4 == Blocks.STANDING_BANNER || a4 == Blocks.STANDING_SIGN || a4 == Blocks.WALL_SIGN) {
            a2.setReturnValue(new AxisAlignedBB(a3.getPos().add(-1, 0, -1), a3.getPos().add(2, 2, 2)));
            return;
        }
    }
}

