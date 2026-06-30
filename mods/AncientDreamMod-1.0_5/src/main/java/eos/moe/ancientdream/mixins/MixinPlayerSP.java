/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.entity.EntityPlayerSP
 *  net.minecraft.init.Items
 *  net.minecraft.item.ItemStack
 */
package eos.moe.ancientdream.mixins;

import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.Slice;

@Mixin(value={EntityPlayerSP.class})
public class MixinPlayerSP {
    @Redirect(method={"onLivingUpdate"}, at=@At(value="INVOKE", target="Lnet/minecraft/client/entity/EntityPlayerSP;isHandActive()Z"), slice=@Slice(from=@At(value="INVOKE", target="Lnet/minecraft/client/tutorial/Tutorial;handleMovement(Lnet/minecraft/util/MovementInput;)V"), to=@At(value="INVOKE", target="Lnet/minecraft/client/entity/EntityPlayerSP;isRiding()Z")))
    private boolean mixin_onLivingUpdate(EntityPlayerSP entityPlayerSP) {
        ItemStack heldItem;
        if (entityPlayerSP.isHandActive() && !(heldItem = entityPlayerSP.getHeldItem(entityPlayerSP.getActiveHand())).isEmpty() && heldItem.getItem() == Items.BOW) {
            return false;
        }
        boolean onUseItem = entityPlayerSP.getEntityData().getBoolean("OnUseItem");
        if (onUseItem) {
            return true;
        }
        return entityPlayerSP.isHandActive();
    }
}

