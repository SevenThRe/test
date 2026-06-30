/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.resources.I18n
 *  org.jetbrains.annotations.NotNull
 */
package invtweaks;

import invtweaks.InvTweaksConfig;
import invtweaks.InvTweaksGuiSettings;
import invtweaks.InvTweaksGuiSettingsAbstract;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import org.jetbrains.annotations.NotNull;

public class InvTweaksGuiModNotWorking
extends InvTweaksGuiSettingsAbstract {
    public InvTweaksGuiModNotWorking(Minecraft mc_, GuiScreen parentScreen_, InvTweaksConfig config_) {
        super(mc_, parentScreen_, config_);
    }

    @Override
    public void drawScreen(int i, int j, float f) {
        super.drawScreen(i, j, f);
        int x = this.width / 2;
        this.drawCenteredString(this.obf.getFontRenderer(), I18n.format((String)"invtweaks.help.bugsorting.pt1", (Object[])new Object[0]), x, 80, 0xBBBBBB);
        this.drawCenteredString(this.obf.getFontRenderer(), I18n.format((String)"invtweaks.help.bugsorting.pt2", (Object[])new Object[0]), x, 95, 0xBBBBBB);
        this.drawCenteredString(this.obf.getFontRenderer(), I18n.format((String)"invtweaks.help.bugsorting.pt3", (Object[])new Object[0]), x, 110, 0xBBBBBB);
        this.drawCenteredString(this.obf.getFontRenderer(), I18n.format((String)"invtweaks.help.bugsorting.pt4", (Object[])new Object[0]), x, 150, 0xFFFF99);
    }

    @Override
    protected void actionPerformed(@NotNull GuiButton guibutton) {
        switch (guibutton.id) {
            case 200: {
                this.obf.displayGuiScreen(new InvTweaksGuiSettings(this.mc, this.parentScreen, this.config));
            }
        }
    }
}

