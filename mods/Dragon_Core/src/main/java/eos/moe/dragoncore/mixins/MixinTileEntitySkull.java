/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.authlib.GameProfile
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.tileentity.TileEntitySkull
 *  net.minecraft.world.EnumSkyBlock
 */
package eos.moe.dragoncore.mixins;

import com.mojang.authlib.GameProfile;
import eos.moe.dragoncore.vu;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntitySkull;
import net.minecraft.world.EnumSkyBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={TileEntitySkull.class})
public class MixinTileEntitySkull
extends TileEntity {
    public MixinTileEntitySkull() {
        MixinTileEntitySkull a2;
    }

    @Inject(method={"updateGameProfile"}, at={@At(value="HEAD")}, cancellable=true)
    private static /* synthetic */ void mixin_updateGameProfile(GameProfile a2, CallbackInfoReturnable<GameProfile> a3) {
        String a4 = vu.f(a2);
        if (a4 != null && a4.contains("model:")) {
            a3.setReturnValue(a2);
        }
    }

    @Inject(method={"readFromNBT"}, at={@At(value="RETURN")})
    private /* synthetic */ void mixin_readFromNBT(NBTTagCompound a2, CallbackInfo a3) {
        MixinTileEntitySkull a4;
        if (a4.field_145850_b != null) {
            a4.field_145850_b.func_175642_b(EnumSkyBlock.BLOCK, a4.field_174879_c);
            a4.field_145850_b.func_180500_c(EnumSkyBlock.SKY, a4.field_174879_c);
        }
    }
}

