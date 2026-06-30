/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.BlockLiquid
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityPlayerSP
 *  net.minecraft.entity.Entity
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.world.World
 */
package com.rejahtavi.rfp2;

import com.rejahtavi.rfp2.RFP2Config;
import net.minecraft.block.BlockLiquid;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EntityPlayerDummy
extends Entity {
    public long lastTickUpdated;
    private boolean swimming = false;

    public EntityPlayerDummy(World world) {
        super(world);
        this.field_70158_ak = true;
        this.func_70105_a(0.0f, 2.0f);
        this.lastTickUpdated = world.func_82737_E();
    }

    public void func_70071_h_() {
        EntityPlayerSP player = Minecraft.func_71410_x().field_71439_g;
        if (player == null) {
            this.func_70106_y();
        } else {
            this.lastTickUpdated = this.field_70170_p.func_82737_E();
            this.func_70080_a(player.field_70165_t, player.field_70163_u, player.field_70161_v, player.field_70177_z, player.field_70125_A);
            BlockPos atHead = new BlockPos(player.field_70165_t, player.field_70163_u + (double)player.eyeHeight, player.field_70161_v);
            BlockPos atFeet = new BlockPos(player.field_70165_t, player.field_70163_u, player.field_70161_v);
            BlockPos belowFeet = atFeet.func_177977_b();
            boolean liquidAtHead = player.field_70170_p.func_180495_p(atHead).func_177230_c() instanceof BlockLiquid;
            boolean liquidAtFeet = player.field_70170_p.func_180495_p(atFeet).func_177230_c() instanceof BlockLiquid;
            boolean liquidBelowFeet = player.field_70170_p.func_180495_p(belowFeet).func_177230_c() instanceof BlockLiquid;
            if (this.swimming) {
                if (RFP2Config.compatibility.useAggressiveSwimmingCheck) {
                    if (!(liquidAtHead || liquidAtFeet || liquidBelowFeet)) {
                        this.swimming = false;
                    }
                } else if (!liquidAtHead && !liquidBelowFeet) {
                    this.swimming = false;
                }
            } else if (RFP2Config.compatibility.useAggressiveSwimmingCheck) {
                if (liquidAtHead || liquidAtFeet) {
                    this.swimming = true;
                }
            } else if (liquidAtHead && liquidAtFeet) {
                this.swimming = true;
            }
        }
    }

    public boolean isSwimming() {
        return this.swimming;
    }

    public void func_70088_a() {
    }

    public void func_70037_a(NBTTagCompound x2) {
    }

    public void func_70014_b(NBTTagCompound x2) {
    }
}

