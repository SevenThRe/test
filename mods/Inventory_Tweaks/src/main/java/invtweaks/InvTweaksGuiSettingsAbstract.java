/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.resources.I18n
 *  org.apache.logging.log4j.Logger
 *  org.jetbrains.annotations.NotNull
 *  org.lwjgl.util.Point
 */
package invtweaks;

import invtweaks.InvTweaks;
import invtweaks.InvTweaksConfig;
import invtweaks.InvTweaksObfuscation;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.util.Point;

public abstract class InvTweaksGuiSettingsAbstract
extends GuiScreen {
    protected static final Logger log = InvTweaks.log;
    protected static final int ID_DONE = 200;
    protected static String ON;
    protected static String OFF;
    protected static String LABEL_DONE;
    protected InvTweaksObfuscation obf;
    protected InvTweaksConfig config;
    protected GuiScreen parentScreen;

    public InvTweaksGuiSettingsAbstract(Minecraft mc_, GuiScreen parentScreen_, InvTweaksConfig config_) {
        LABEL_DONE = I18n.format((String)"invtweaks.settings.exit", (Object[])new Object[0]);
        ON = ": " + I18n.format((String)"invtweaks.settings.on", (Object[])new Object[0]);
        OFF = ": " + I18n.format((String)"invtweaks.settings.off", (Object[])new Object[0]);
        this.mc = mc_;
        this.obf = new InvTweaksObfuscation(mc_);
        this.parentScreen = parentScreen_;
        this.config = config_;
    }

    public void initGui() {
        List controlList = this.buttonList;
        Point p = new Point();
        this.moveToButtonCoords(1, p);
        controlList.add(new GuiButton(200, p.getX() + 55, this.height / 6 + 168, LABEL_DONE));
        this.buttonList = controlList;
    }

    public void drawScreen(int i, int j, float f) {
        this.drawDefaultBackground();
        this.drawCenteredString(this.obf.getFontRenderer(), I18n.format((String)"invtweaks.settings.title", (Object[])new Object[0]), this.width / 2, 20, 0xFFFFFF);
        super.drawScreen(i, j, f);
    }

    protected void actionPerformed(@NotNull GuiButton guibutton) {
        if (guibutton.id == 200) {
            this.obf.displayGuiScreen(this.parentScreen);
        }
    }

    protected void keyTyped(char c, int keyCode) {
        if (keyCode == 1) {
            this.obf.displayGuiScreen(this.parentScreen);
        }
    }

    protected void moveToButtonCoords(int buttonOrder, @NotNull Point p) {
        p.setX(this.width / 2 - 155 + (buttonOrder + 1) % 2 * 160);
        p.setY(this.height / 6 + buttonOrder / 2 * 24);
    }

    protected void toggleBooleanButton(@NotNull GuiButton guibutton, @NotNull String property, String label) {
        Boolean enabled = Boolean.valueOf(this.config.getProperty(property)) == false;
        this.config.setProperty(property, enabled.toString());
        guibutton.displayString = this.computeBooleanButtonLabel(property, label);
    }

    @NotNull
    protected String computeBooleanButtonLabel(@NotNull String property, String label) {
        String propertyValue = this.config.getProperty(property);
        Boolean enabled = Boolean.valueOf(propertyValue);
        return label + (enabled != false ? ON : OFF);
    }
}

