/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.world.World
 */
package eos.moe.dragoncore.mixins;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(value={EntityPlayer.class})
public abstract class MixinEntityPlayer
extends EntityLivingBase {
    public MixinEntityPlayer(World a2) {
        super(a2);
        MixinEntityPlayer a3;
    }
}

