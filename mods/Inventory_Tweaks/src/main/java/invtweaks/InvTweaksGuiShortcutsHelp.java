/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.resources.I18n
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 *  org.lwjgl.input.Keyboard
 */
package invtweaks;

import invtweaks.InvTweaksConfig;
import invtweaks.InvTweaksObfuscation;
import java.util.LinkedList;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.input.Keyboard;

public class InvTweaksGuiShortcutsHelp
extends GuiScreen {
    private static final int ID_DONE = 0;
    private InvTweaksObfuscation obf;
    private GuiScreen parentScreen;
    private InvTweaksConfig config;

    public InvTweaksGuiShortcutsHelp(Minecraft mc_, GuiScreen parentScreen_, InvTweaksConfig config_) {
        this.obf = new InvTweaksObfuscation(mc_);
        this.parentScreen = parentScreen_;
        this.config = config_;
    }

    public void initGui() {
        LinkedList<GuiButton> controlList = new LinkedList<GuiButton>();
        controlList.add(new GuiButton(0, this.width / 2 - 100, this.height / 6 + 168, "Done"));
        this.buttonList = controlList;
    }

    public void drawScreen(int i, int j, float f) {
        this.drawDefaultBackground();
        this.drawCenteredString(this.obf.getFontRenderer(), "WARNING: Since 1.3.1, shortcuts won't work as expected. Looking for a workaround...", this.width / 2, 5, 0xFF0000);
        this.drawCenteredString(this.obf.getFontRenderer(), I18n.format((String)"invtweaks.help.shortcuts.title", (Object[])new Object[0]), this.width / 2, 20, 0xFFFFFF);
        String clickLabel = I18n.format((String)"invtweaks.help.shortcuts.click", (Object[])new Object[0]);
        int y = this.height / 6 - 2;
        this.drawShortcutLine(I18n.format((String)"invtweaks.help.shortcuts.onestack", (Object[])new Object[0]), "LSHIFT " + I18n.format((String)"invtweaks.help.shortcuts.or", (Object[])new Object[0]) + " RSHIFT + " + clickLabel, 0xFFFF00, y);
        this.drawShortcutLine("", this.buildUpOrDownLabel("shortcutKeyToUpperSection", this.obf.getKeyBindingForwardKeyCode(), I18n.format((String)"invtweaks.help.shortcuts.forward", (Object[])new Object[0])) + " + " + clickLabel, 0xFFFF00, y += 12);
        this.drawShortcutLine("", this.buildUpOrDownLabel("shortcutKeyToLowerSection", this.obf.getKeyBindingBackKeyCode(), I18n.format((String)"invtweaks.help.shortcuts.backwards", (Object[])new Object[0])) + " + " + clickLabel, 0xFFFF00, y += 12);
        this.drawShortcutLine(I18n.format((String)"invtweaks.help.shortcuts.oneitem", (Object[])new Object[0]), this.config.getProperty("shortcutKeyOneItem") + " + " + clickLabel, 0xFFFF00, y += 12);
        this.drawShortcutLine(I18n.format((String)"invtweaks.help.shortcuts.allitems", (Object[])new Object[0]), this.config.getProperty("shortcutKeyAllItems") + " + " + clickLabel, 0xFFFF00, y += 12);
        this.drawShortcutLine(I18n.format((String)"invtweaks.help.shortcuts.everything", (Object[])new Object[0]), this.config.getProperty("shortcutKeyEverything") + " + " + clickLabel, 0xFFFF00, y += 12);
        this.drawShortcutLine(I18n.format((String)"invtweaks.help.shortcuts.hotbar", (Object[])new Object[0]), "0-9 + " + clickLabel, 65331, y += 19);
        this.drawShortcutLine(I18n.format((String)"invtweaks.help.shortcuts.emptyslot", (Object[])new Object[0]), I18n.format((String)"invtweaks.help.shortcuts.rightclick", (Object[])new Object[0]), 65331, y += 12);
        this.drawShortcutLine(I18n.format((String)"invtweaks.help.shortcuts.drop", (Object[])new Object[0]), this.config.getProperty("shortcutKeyDrop") + " + " + clickLabel, 65331, y += 12);
        this.drawShortcutLine(I18n.format((String)"invtweaks.help.shortcuts.craftall", (Object[])new Object[0]), "LSHIFT, RSHIFT + " + clickLabel, 0xFF8800, y += 19);
        this.drawShortcutLine(I18n.format((String)"invtweaks.help.shortcuts.craftone", (Object[])new Object[0]), this.config.getProperty("shortcutKeyOneItem") + " + " + clickLabel, 0xFF8800, y += 12);
        String sortKeyName = this.getKeyName(this.config.getSortKeyCode(), "(Sort Key)");
        this.drawShortcutLine(I18n.format((String)"invtweaks.help.shortcuts.selectconfig", (Object[])new Object[0]), "0-9 + " + sortKeyName, 0x88FFFF, y += 19);
        super.drawScreen(i, j, f);
    }

    protected void actionPerformed(@NotNull GuiButton guibutton) {
        switch (guibutton.id) {
            case 0: {
                this.obf.displayGuiScreen(this.parentScreen);
            }
        }
    }

    protected void keyTyped(char c, int keyCode) {
        if (keyCode == 1) {
            this.obf.displayGuiScreen(this.parentScreen);
        }
    }

    private String buildUpOrDownLabel(@NotNull String shortcutProp, int keyCode, String defaultKeyName) {
        String shortcutLabel = this.config.getProperty(shortcutProp);
        String keyLabel = this.getKeyName(keyCode, defaultKeyName);
        if (keyLabel.equals(shortcutLabel)) {
            return keyLabel;
        }
        return keyLabel + "/" + shortcutLabel;
    }

    protected String getKeyName(int keyCode, String defaultValue) {
        try {
            return Keyboard.getKeyName((int)keyCode);
        }
        catch (Exception e) {
            return defaultValue;
        }
    }

    private void drawShortcutLine(@NotNull String label, @Nullable String value, int color, int y) {
        this.drawString(this.obf.getFontRenderer(), label, 30, y, -1);
        if (value != null) {
            this.drawString(this.obf.getFontRenderer(), value.contains("DEFAULT") ? "-" : value.replaceAll(", ", " " + I18n.format((String)"invtweaks.help.shortcuts.or", (Object[])new Object[0]) + " "), this.width / 2 - 30, y, color);
        }
    }
}

