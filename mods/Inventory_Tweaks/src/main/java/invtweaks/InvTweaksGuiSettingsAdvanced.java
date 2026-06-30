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
import invtweaks.InvTweaksGuiSettings;
import invtweaks.InvTweaksGuiSettingsAbstract;
import invtweaks.InvTweaksGuiTooltipButton;
import invtweaks.forge.InvTweaksMod;
import java.awt.Desktop;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.util.Point;

public class InvTweaksGuiSettingsAdvanced
extends InvTweaksGuiSettingsAbstract {
    private static final int ID_SORT_ON_PICKUP = 1;
    private static final int ID_AUTO_EQUIP_ARMOR = 2;
    private static final int ID_ENABLE_SOUNDS = 3;
    private static final int ID_CHESTS_BUTTONS = 4;
    private static final int ID_SERVER_ASSIST = 5;
    private static final int ID_EDITSHORTCUTS = 100;
    private static String labelChestButtons;
    private static String labelSortOnPickup;
    private static String labelEquipArmor;
    private static String labelEnableSounds;
    private static String labelServerAssist;

    public InvTweaksGuiSettingsAdvanced(Minecraft mc_, GuiScreen parentScreen_, InvTweaksConfig config_) {
        super(mc_, parentScreen_, config_);
        labelSortOnPickup = I18n.format((String)"invtweaks.settings.advanced.sortonpickup", (Object[])new Object[0]);
        labelEquipArmor = I18n.format((String)"invtweaks.settings.advanced.autoequip", (Object[])new Object[0]);
        labelEnableSounds = I18n.format((String)"invtweaks.settings.advanced.sounds", (Object[])new Object[0]);
        labelChestButtons = I18n.format((String)"invtweaks.settings.chestbuttons", (Object[])new Object[0]);
        labelServerAssist = I18n.format((String)"invtweaks.settings.advanced.serverassist", (Object[])new Object[0]);
    }

    @Override
    public void initGui() {
        super.initGui();
        List controlList = this.buttonList;
        Point p = new Point();
        int i = 0;
        this.moveToButtonCoords(1, p);
        controlList.add(new GuiButton(100, p.getX() + 55, this.height / 6 + 144, I18n.format((String)"invtweaks.settings.advanced.mappingsfile", (Object[])new Object[0])));
        i += 2;
        this.moveToButtonCoords(i++, p);
        InvTweaksGuiTooltipButton sortOnPickupBtn = new InvTweaksGuiTooltipButton(1, p.getX(), p.getY(), this.computeBooleanButtonLabel("enableSortingOnPickup", labelSortOnPickup), I18n.format((String)"invtweaks.settings.advanced.sortonpickup.tooltip", (Object[])new Object[0]));
        controlList.add(sortOnPickupBtn);
        this.moveToButtonCoords(i++, p);
        InvTweaksGuiTooltipButton enableSoundsBtn = new InvTweaksGuiTooltipButton(3, p.getX(), p.getY(), this.computeBooleanButtonLabel("enableSounds", labelEnableSounds), I18n.format((String)"invtweaks.settings.advanced.sounds.tooltip", (Object[])new Object[0]));
        controlList.add(enableSoundsBtn);
        this.moveToButtonCoords(i++, p);
        controlList.add(new InvTweaksGuiTooltipButton(4, p.getX(), p.getY(), this.computeBooleanButtonLabel("showChestButtons", labelChestButtons), I18n.format((String)"invtweaks.settings.chestbuttons.tooltip", (Object[])new Object[0])));
        this.moveToButtonCoords(i++, p);
        InvTweaksGuiTooltipButton autoEquipArmorBtn = new InvTweaksGuiTooltipButton(2, p.getX(), p.getY(), this.computeBooleanButtonLabel("enableAutoEquipArmor", labelEquipArmor), I18n.format((String)"invtweaks.settings.advanced.autoequip.tooltip", (Object[])new Object[0]));
        controlList.add(autoEquipArmorBtn);
        this.moveToButtonCoords(i++, p);
        InvTweaksGuiTooltipButton serverAssistBtn = new InvTweaksGuiTooltipButton(5, p.getX(), p.getY(), this.computeBooleanButtonLabel("enableServerItemSwap", labelServerAssist), I18n.format((String)"invtweaks.settings.advanced.serverassist.tooltip", (Object[])new Object[0]));
        controlList.add(serverAssistBtn);
        if (!Desktop.isDesktopSupported()) {
            controlList.stream().forEach(button -> {
                if (button.id == 100) {
                    button.enabled = false;
                }
            });
        }
        this.buttonList = controlList;
    }

    @Override
    public void drawScreen(int i, int j, float f) {
        super.drawScreen(i, j, f);
        int x = this.width / 2;
        this.drawCenteredString(this.obf.getFontRenderer(), I18n.format((String)"invtweaks.settings.pvpwarning.pt1", (Object[])new Object[0]), x, 40, 0x999999);
        this.drawCenteredString(this.obf.getFontRenderer(), I18n.format((String)"invtweaks.settings.pvpwarning.pt2", (Object[])new Object[0]), x, 50, 0x999999);
    }

    @Override
    protected void actionPerformed(@NotNull GuiButton guibutton) {
        switch (guibutton.id) {
            case 1: {
                this.toggleBooleanButton(guibutton, "enableSortingOnPickup", labelSortOnPickup);
                break;
            }
            case 2: {
                this.toggleBooleanButton(guibutton, "enableAutoEquipArmor", labelEquipArmor);
                break;
            }
            case 3: {
                this.toggleBooleanButton(guibutton, "enableSounds", labelEnableSounds);
                break;
            }
            case 4: {
                this.toggleBooleanButton(guibutton, "showChestButtons", labelChestButtons);
                break;
            }
            case 5: {
                this.toggleBooleanButton(guibutton, "enableServerItemSwap", labelServerAssist);
                InvTweaksMod.proxy.setServerAssistEnabled(!InvTweaks.getConfigManager().getConfig().getProperty("enableServerItemSwap").equals("false"));
                break;
            }
            case 100: {
                try {
                    Desktop.getDesktop().open(InvTweaksConst.CONFIG_PROPS_FILE);
                }
                catch (Exception e) {
                    InvTweaks.logInGameErrorStatic("invtweaks.settings.advanced.mappingsfile.error", e);
                }
                break;
            }
            case 200: {
                this.obf.displayGuiScreen(new InvTweaksGuiSettings(this.mc, this.parentScreen, this.config));
            }
        }
    }
}

