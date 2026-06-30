/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.tileentity.TileEntityChest
 */
package eos.moe.dragoncore.mixins;

import eos.moe.dragoncore.interfaces.ITileEntityChest;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(value={TileEntityChest.class})
public class MixinTileEntityChest
extends TileEntity
implements ITileEntityChest {
    private boolean ignoreRender;

    public MixinTileEntityChest() {
        MixinTileEntityChest a2;
    }

    @Override
    public void ignoreNextRender() {
        a.ignoreRender = true;
    }

    public double getMaxRenderDistanceSquared() {
        MixinTileEntityChest a2;
        if (a2.ignoreRender) {
            a2.ignoreRender = false;
            return -1.0;
        }
        return super.getMaxRenderDistanceSquared();
    }
}

