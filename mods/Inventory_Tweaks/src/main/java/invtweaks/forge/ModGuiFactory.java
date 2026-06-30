/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraftforge.fml.client.IModGuiFactory
 *  net.minecraftforge.fml.client.IModGuiFactory$RuntimeOptionCategoryElement
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package invtweaks.forge;

import invtweaks.InvTweaksGuiSettings;
import java.util.Set;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.fml.client.IModGuiFactory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ModGuiFactory
implements IModGuiFactory {
    public void initialize(Minecraft minecraftInstance) {
    }

    public boolean hasConfigGui() {
        return true;
    }

    @NotNull
    public GuiScreen createConfigGui(GuiScreen parentScreen) {
        return new InvTweaksGuiSettings(parentScreen);
    }

    @Nullable
    public Set<IModGuiFactory.RuntimeOptionCategoryElement> runtimeGuiCategories() {
        return null;
    }
}

