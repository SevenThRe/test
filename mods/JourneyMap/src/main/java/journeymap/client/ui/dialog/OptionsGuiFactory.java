/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraftforge.fml.client.IModGuiFactory
 *  net.minecraftforge.fml.client.IModGuiFactory$RuntimeOptionCategoryElement
 */
package journeymap.client.ui.dialog;

import java.util.Set;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.fml.client.IModGuiFactory;

public class OptionsGuiFactory
implements IModGuiFactory {
    public void initialize(Minecraft minecraftInstance) {
    }

    public boolean hasConfigGui() {
        return false;
    }

    public GuiScreen createConfigGui(GuiScreen parentScreen) {
        return null;
    }

    public Set<IModGuiFactory.RuntimeOptionCategoryElement> runtimeGuiCategories() {
        return null;
    }
}

