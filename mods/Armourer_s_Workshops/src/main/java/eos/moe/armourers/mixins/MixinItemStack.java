/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.ItemStack
 */
package eos.moe.armourers.mixins;

import eos.moe.armourers.interfaces.ISkinStorage;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(value={ItemStack.class})
public class MixinItemStack
implements ISkinStorage {
    private String skin;

    @Override
    public void setSkin(String a2) {
        a.skin = a2;
    }

    @Override
    public String getSkin() {
        MixinItemStack a2;
        return a2.skin;
    }

    public MixinItemStack() {
        MixinItemStack a2;
    }
}

