/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraftforge.fml.client.IModGuiFactory
 *  net.minecraftforge.fml.client.IModGuiFactory$RuntimeOptionCategoryElement
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package com.teamderpy.shouldersurfing.gui;

import com.teamderpy.shouldersurfing.gui.GuiShoulderSurfingConfig;
import java.util.Set;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.fml.client.IModGuiFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(value=Side.CLIENT)
public class GuiShoulderSurfingConfigFactory
implements IModGuiFactory {
    public void initialize(Minecraft minecraft) {
    }

    @Deprecated
    public Class<? extends GuiScreen> mainConfigGuiClass() {
        return GuiShoulderSurfingConfig.class;
    }

    public Set<IModGuiFactory.RuntimeOptionCategoryElement> runtimeGuiCategories() {
        return null;
    }

    public boolean hasConfigGui() {
        return true;
    }

    public GuiScreen createConfigGui(GuiScreen parentScreen) {
        return new GuiShoulderSurfingConfig(parentScreen);
    }
}

