/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package com.teamderpy.shouldersurfing.config;

import com.teamderpy.shouldersurfing.util.ShoulderSurfingHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(value=Side.CLIENT)
public enum CrosshairType {
    ADAPTIVE;


    public boolean isHoldingSpecialItem() {
        return ShoulderSurfingHelper.isHoldingSpecialItem();
    }
}

