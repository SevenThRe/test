/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.resources.I18n
 *  org.jetbrains.annotations.NotNull
 *  org.lwjgl.util.Point
 */
package invtweaks;

import invtweaks.InvTweaks;
import invtweaks.InvTweaksConfig;
import invtweaks.InvTweaksConst;
import invtweaks.InvTweaksGuiModNotWorking;
import invtweaks.InvTweaksGuiSettingsAbstract;
import invtweaks.InvTweaksGuiSettingsAdvanced;
import invtweaks.InvTweaksGuiShortcutsHelp;
import invtweaks.InvTweaksGuiTooltipButton;
import invtweaks.InvTweaksObfuscation;
import java.awt.Desktop;
import java.net.URL;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.util.Point;

public class InvTweaksGuiSettings
extends InvTweaksGuiSettingsAbstract {
    private static final int ID_MIDDLE_CLICK = 1;
    private static final int ID_BEFORE_BREAK = 2;
    private static final int ID_SHORTCUTS = 3;
    private static final int ID_SHORTCUTS_HELP = 4;
    private static final int ID_AUTO_REFILL = 5;
    private static final int ID_MORE_OPTIONS = 6;
    private static final int ID_BUG_SORTING = 7;
    private static final int ID_EDITRULES = 100;
    private static final int ID_EDITTREE = 101;
    private static final int ID_HELP = 102;
    private static String labelMiddleClick;
    private static String labelShortcuts;
    private static String labelAutoRefill;
    private static String labelAutoRefillBeforeBreak;
    private static String labelMoreOptions;
    private static String labelBugSorting;

    public InvTweaksGuiSettings(GuiScreen parentScreen_) {
        this(Minecraft.getMinecraft(), parentScreen_, InvTweaks.getConfigManager().getConfig());
    }

    public InvTweaksGuiSettings(Minecraft mc_, GuiScreen parentScreen_, InvTweaksConfig config_) {
        super(mc_, parentScreen_, config_);
        labelMiddleClick = I18n.format((String)"invtweaks.settings.middleclick", (Object[])new Object[0]);
        labelShortcuts = I18n.format((String)"invtweaks.settings.shortcuts", (Object[])new Object[0]);
        labelAutoRefill = I18n.format((String)"invtweaks.settings.autorefill", (Object[])new Object[0]);
        labelAutoRefillBeforeBreak = I18n.format((String)"invtweaks.settings.beforebreak", (Object[])new Object[0]);
        labelMoreOptions = I18n.format((String)"invtweaks.settings.moreoptions", (Object[])new Object[0]);
        labelBugSorting = I18n.format((String)"invtweaks.help.bugsorting", (Object[])new Object[0]);
    }

    @Override
    public void initGui() {
        super.initGui();
        List controlList = this.buttonList;
        Point p = new Point();
        int i = 0;
        this.moveToButtonCoords(1, p);
        controlList.add(new GuiButton(100, p.getX() + 55, this.height / 6 + 96, I18n.format((String)"invtweaks.settings.rulesfile", (Object[])new Object[0])));
        controlList.add(new GuiButton(101, p.getX() + 55, this.height / 6 + 120, I18n.format((String)"invtweaks.settings.treefile", (Object[])new Object[0])));
        controlList.add(new GuiButton(102, p.getX() + 55, this.height / 6 + 144, I18n.format((String)"invtweaks.settings.onlinehelp", (Object[])new Object[0])));
        this.moveToButtonCoords(i++, p);
        controlList.add(new InvTweaksGuiTooltipButton(4, p.getX() + 130, p.getY(), 20, 20, "?", "Shortcuts help"));
        InvTweaksGuiTooltipButton shortcutsBtn = new InvTweaksGuiTooltipButton(3, p.getX(), p.getY(), 130, 20, this.computeBooleanButtonLabel("enableShortcuts", labelShortcuts), I18n.format((String)"invtweaks.settings.shortcuts.tooltip", (Object[])new Object[0]));
        controlList.add(shortcutsBtn);
        this.moveToButtonCoords(i++, p);
        InvTweaksGuiTooltipButton beforeBreakBtn = new InvTweaksGuiTooltipButton(2, p.getX(), p.getY(), this.computeBooleanButtonLabel("autoRefillBeforeBreak", labelAutoRefillBeforeBreak), I18n.format((String)"invtweaks.settings.beforebreak.tooltip", (Object[])new Object[0]));
        controlList.add(beforeBreakBtn);
        this.moveToButtonCoords(i++, p);
        InvTweaksGuiTooltipButton autoRefillBtn = new InvTweaksGuiTooltipButton(5, p.getX(), p.getY(), this.computeBooleanButtonLabel("enableAutoRefill", labelAutoRefill), I18n.format((String)"invtweaks.settings.autorefill.tooltip", (Object[])new Object[0]));
        controlList.add(autoRefillBtn);
        this.moveToButtonCoords(i++, p);
        controlList.add(new InvTweaksGuiTooltipButton(6, p.getX(), p.getY(), labelMoreOptions, I18n.format((String)"invtweaks.settings.moreoptions.tooltip", (Object[])new Object[0])));
        controlList.add(new InvTweaksGuiTooltipButton(7, 5, this.height - 20, 100, 20, labelBugSorting, null, false));
        this.moveToButtonCoords(i++, p);
        InvTweaksGuiTooltipButton middleClickBtn = new InvTweaksGuiTooltipButton(1, p.getX(), p.getY(), this.computeBooleanButtonLabel("enableMiddleClick", labelMiddleClick), I18n.format((String)"invtweaks.settings.middleclick.tooltip", (Object[])new Object[0]));
        controlList.add(middleClickBtn);
        if (!Desktop.isDesktopSupported()) {
            controlList.stream().filter(InvTweaksObfuscation::isGuiButton).forEach(o -> {
                if (o.id >= 100 && o.id <= 102) {
                    o.enabled = false;
                }
            });
        }
        this.buttonList = controlList;
    }

    @Override
    protected void actionPerformed(@NotNull GuiButton guibutton) {
        super.actionPerformed(guibutton);
        switch (guibutton.id) {
            case 1: {
                this.toggleBooleanButton(guibutton, "enableMiddleClick", labelMiddleClick);
                break;
            }
            case 5: {
                this.toggleBooleanButton(guibutton, "enableAutoRefill", labelAutoRefill);
                break;
            }
            case 2: {
                this.toggleBooleanButton(guibutton, "autoRefillBeforeBreak", labelAutoRefillBeforeBreak);
                break;
            }
            case 3: {
                this.toggleBooleanButton(guibutton, "enableShortcuts", labelShortcuts);
                break;
            }
            case 4: {
                this.obf.displayGuiScreen(new InvTweaksGuiShortcutsHelp(this.mc, this, this.config));
                break;
            }
            case 6: {
                this.obf.displayGuiScreen(new InvTweaksGuiSettingsAdvanced(this.mc, this.parentScreen, this.config));
                break;
            }
            case 7: {
                this.obf.displayGuiScreen(new InvTweaksGuiModNotWorking(this.mc, this.parentScreen, this.config));
                break;
            }
            case 100: {
                try {
                    Desktop.getDesktop().open(InvTweaksConst.CONFIG_RULES_FILE);
                }
                catch (Exception e) {
                    InvTweaks.logInGameErrorStatic("invtweaks.settings.rulesfile.error", e);
                }
                break;
            }
            case 101: {
                try {
                    Desktop.getDesktop().open(InvTweaksConst.CONFIG_TREE_FILE);
                }
                catch (Exception e) {
                    InvTweaks.logInGameErrorStatic("invtweaks.settings.treefile.error", e);
                }
                break;
            }
            case 102: {
                try {
                    Desktop.getDesktop().browse(new URL("http://inventory-tweaks.readthedocs.org").toURI());
                    break;
                }
                catch (Exception e) {
                    InvTweaks.logInGameErrorStatic("invtweaks.settings.onlinehelp.error", e);
                }
            }
        }
    }
}

