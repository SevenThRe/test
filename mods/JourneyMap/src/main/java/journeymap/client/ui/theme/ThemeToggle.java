/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 */
package journeymap.client.ui.theme;

import journeymap.client.Constants;
import journeymap.client.ui.theme.Theme;
import journeymap.client.ui.theme.ThemeButton;
import journeymap.common.properties.config.BooleanField;
import net.minecraft.client.Minecraft;

public class ThemeToggle
extends ThemeButton {
    public ThemeToggle(Theme theme, String rawlabel, String iconName) {
        super(theme, Constants.getString(rawlabel), Constants.getString(rawlabel), iconName, null);
    }

    public ThemeToggle(Theme theme, String labelOn, String labelOff, String iconName) {
        super(theme, labelOn, labelOff, iconName, null);
    }

    public ThemeToggle(Theme theme, String rawlabel, String iconName, BooleanField field) {
        super(theme, Constants.getString(rawlabel), Constants.getString(rawlabel), iconName, field);
        if (field != null) {
            this.setToggled(field.get());
        }
    }

    @Override
    public boolean func_146116_c(Minecraft minecraft, int mouseX, int mouseY) {
        if (this.toggled.booleanValue() && this.staysOn) {
            return false;
        }
        return super.func_146116_c(minecraft, mouseX, mouseY);
    }

    @Override
    protected String getPathPattern() {
        return "control/%stoggle_%s.png";
    }

    @Override
    protected Theme.Control.ButtonSpec getButtonSpec(Theme theme) {
        return theme.control.toggle;
    }
}

