/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraftforge.common.config.ConfigElement
 *  net.minecraftforge.fml.client.config.GuiConfig
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package com.teamderpy.shouldersurfing.gui;

import com.teamderpy.shouldersurfing.config.Config;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.fml.client.config.GuiConfig;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(value=Side.CLIENT)
public class GuiShoulderSurfingConfig
extends GuiConfig {
    public GuiShoulderSurfingConfig(GuiScreen parent) {
        super(parent, new ConfigElement(Config.CLIENT.getConfig().getCategory("general")).getChildElements(), "shouldersurfing", false, false, "Shoulder Surfing");
    }
}

