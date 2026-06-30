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
        this.ignoreFrustumCheck = true;
        this.setSize(0.0f, 2.0f);
        this.lastTickUpdated = world.getTotalWorldTime();
    }

    public void onUpdate() {
        EntityPlayerSP player = Minecraft.getMinecraft().player;
        if (player == null) {
            this.setDead();
        } else {
            this.lastTickUpdated = this.world.getTotalWorldTime();
            this.setPositionAndRotation(player.posX, player.posY, player.posZ, player.rotationYaw, player.rotationPitch);
            BlockPos atHead = new BlockPos(player.posX, player.posY + (double)player.eyeHeight, player.posZ);
            BlockPos atFeet = new BlockPos(player.posX, player.posY, player.posZ);
            BlockPos belowFeet = atFeet.down();
            boolean liquidAtHead = player.world.getBlockState(atHead).getBlock() instanceof BlockLiquid;
            boolean liquidAtFeet = player.world.getBlockState(atFeet).getBlock() instanceof BlockLiquid;
            boolean liquidBelowFeet = player.world.getBlockState(belowFeet).getBlock() instanceof BlockLiquid;
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

    public void entityInit() {
    }

    public void readEntityFromNBT(NBTTagCompound x2) {
    }

    public void writeEntityToNBT(NBTTagCompound x2) {
    }
}

