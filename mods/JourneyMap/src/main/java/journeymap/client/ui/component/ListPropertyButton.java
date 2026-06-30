/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.FontRenderer
 */
package journeymap.client.ui.component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import journeymap.client.ui.component.Button;
import journeymap.client.ui.component.IConfigFieldHolder;
import journeymap.common.properties.config.ConfigField;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;

public class ListPropertyButton<T>
extends Button
implements IConfigFieldHolder<ConfigField<T>> {
    protected final ConfigField<T> field;
    protected final List<T> values;
    protected final String baseLabel;
    protected final String glyph = "\u21d5";
    protected final String labelPattern = "%1$s : %2$s %3$s %2$s";

    public ListPropertyButton(Collection<T> values, String label, ConfigField<T> field) {
        super("");
        this.field = field;
        this.values = new ArrayList<T>(values);
        this.baseLabel = label;
        this.setValue(field.get());
        this.disabledLabelColor = 0x404040;
    }

    public void setValue(T value) {
        if (!this.field.get().equals(value)) {
            this.field.set(value);
            this.field.save();
        }
        this.displayString = this.getFormattedLabel(value.toString());
    }

    public ConfigField<T> getField() {
        return this.field;
    }

    public void nextOption() {
        int index = this.values.indexOf(this.field.get()) + 1;
        if (index == this.values.size()) {
            index = 0;
        }
        this.setValue(this.values.get(index));
    }

    public void prevOption() {
        int index = this.values.indexOf(this.field.get()) - 1;
        if (index == -1) {
            index = this.values.size() - 1;
        }
        this.setValue(this.values.get(index));
    }

    @Override
    public boolean mousePressed(Minecraft minecraft, int mouseX, int mouseY) {
        if (super.mousePressed(minecraft, mouseX, mouseY, false)) {
            this.nextOption();
            return this.checkClickListeners();
        }
        return false;
    }

    protected String getFormattedLabel(String value) {
        return String.format("%1$s : %2$s %3$s %2$s", this.baseLabel, "\u21d5", value);
    }

    @Override
    public int getFitWidth(FontRenderer fr) {
        int max = fr.getStringWidth(this.displayString);
        for (T value : this.values) {
            max = Math.max(max, fr.getStringWidth(this.getFormattedLabel(value.toString())));
        }
        return max + this.WIDTH_PAD;
    }

    @Override
    public boolean keyTyped(char c, int i) {
        if (this.isMouseOver()) {
            if (i == 203 || i == 208 || i == 74) {
                this.prevOption();
                return true;
            }
            if (i == 205 || i == 200 || i == 78) {
                this.nextOption();
                return true;
            }
        }
        return false;
    }

    @Override
    public void refresh() {
        this.setValue(this.field.get());
    }

    @Override
    public ConfigField<T> getConfigField() {
        return this.field;
    }
}

