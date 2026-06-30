/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.util.math.RayTraceResult$Type
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package com.teamderpy.shouldersurfing.config;

import net.minecraft.client.Minecraft;
import net.minecraft.util.math.RayTraceResult;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(value=Side.CLIENT)
public enum CrosshairVisibility {
    ALWAYS,
    NEVER,
    WHEN_AIMING,
    WHEN_IN_RANGE,
    WHEN_AIMING_OR_IN_RANGE;


    public boolean doRender(boolean isAiming) {
        if (this == NEVER) {
            return false;
        }
        if (this == WHEN_AIMING) {
            return isAiming;
        }
        if (this == WHEN_IN_RANGE) {
            return Minecraft.getMinecraft().objectMouseOver != null && !RayTraceResult.Type.MISS.equals((Object)Minecraft.getMinecraft().objectMouseOver.typeOfHit);
        }
        return this != WHEN_AIMING_OR_IN_RANGE || WHEN_IN_RANGE.doRender(isAiming) || WHEN_AIMING.doRender(isAiming);
    }
}

