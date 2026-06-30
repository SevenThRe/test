/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.item.EntityArmorStand
 *  net.minecraft.item.ItemStack
 *  net.minecraft.network.datasync.DataParameter
 *  net.minecraft.world.World
 */
package eos.moe.dragoncore.mixins;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.item.ItemStack;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={EntityArmorStand.class})
public abstract class MixinArmorStand
extends EntityLivingBase {
    public MixinArmorStand(World a2) {
        super(a2);
        MixinArmorStand a3;
    }

    @Inject(method={"notifyDataManagerChange"}, at={@At(value="HEAD")})
    private /* synthetic */ void notify(DataParameter<?> a2, CallbackInfo a3) {
        MixinArmorStand a4;
        Object a5;
        if (HAND_STATES.equals(a2) && (a5 = a4.dataManager.get(a2)) instanceof ItemStack) {
            a4.resetActiveHand();
        }
    }
}

