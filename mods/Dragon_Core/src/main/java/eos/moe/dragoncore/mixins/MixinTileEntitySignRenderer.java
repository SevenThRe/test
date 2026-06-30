/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.tileentity.TileEntitySignRenderer
 *  net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
 *  net.minecraft.init.Blocks
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.tileentity.TileEntityChest
 *  net.minecraft.tileentity.TileEntitySign
 *  net.minecraft.util.EnumFacing
 *  net.minecraft.util.math.BlockPos
 */
package eos.moe.dragoncore.mixins;

import eos.moe.dragoncore.ey;
import eos.moe.dragoncore.interfaces.ITileEntityChest;
import eos.moe.dragoncore.renderer.SignRenderer;
import net.minecraft.client.renderer.tileentity.TileEntitySignRenderer;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntitySign;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={TileEntitySignRenderer.class})
public class MixinTileEntitySignRenderer
extends TileEntitySpecialRenderer<TileEntitySign> {
    public MixinTileEntitySignRenderer() {
        MixinTileEntitySignRenderer a2;
    }

    @Inject(method={"render(Lnet/minecraft/tileentity/TileEntitySign;DDDFIF)V"}, at={@At(value="HEAD")}, cancellable=true)
    public void mixin_render(TileEntitySign a2, double a3, double a4, double a5, float a6, int a7, float a8, CallbackInfo a9) {
        MixinTileEntitySignRenderer a10;
        boolean a11 = SignRenderer.renderModel(a10.getFontRenderer(), a2, a3, a4, a5, a6, a7, a8, a9);
        if (a11) {
            ey.ALLATORIxDEMO.add(a2);
            a9.cancel();
            TileEntityChest a12 = MixinTileEntitySignRenderer.getAttached(a2);
            if (a12 != null) {
                ((ITileEntityChest)a12).ignoreNextRender();
            }
        }
    }

    private static /* synthetic */ TileEntityChest getAttached(TileEntitySign a2) {
        try {
            if (a2.getBlockType() == Blocks.WALL_SIGN) {
                int a3 = a2.getBlockMetadata();
                EnumFacing a4 = null;
                switch (a3) {
                    case 2: {
                        a4 = EnumFacing.SOUTH;
                        break;
                    }
                    case 3: {
                        a4 = EnumFacing.NORTH;
                        break;
                    }
                    case 4: {
                        a4 = EnumFacing.EAST;
                        break;
                    }
                    case 5: {
                        a4 = EnumFacing.WEST;
                        break;
                    }
                    default: {
                        a4 = EnumFacing.DOWN;
                    }
                }
                BlockPos a5 = a2.getPos().offset(a4);
                TileEntity a6 = a2.getWorld().getTileEntity(a5);
                if (a6 instanceof TileEntityChest) {
                    return (TileEntityChest)a6;
                }
            }
        }
        catch (NullPointerException nullPointerException) {
            // empty catch block
        }
        return null;
    }
}

