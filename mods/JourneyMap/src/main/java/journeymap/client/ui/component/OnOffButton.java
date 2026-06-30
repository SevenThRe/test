/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.FontRenderer
 */
package journeymap.client.ui.component;

import java.util.ArrayList;
import java.util.Iterator;
import journeymap.client.ui.component.Button;
import journeymap.common.Journeymap;
import journeymap.common.log.LogFormatter;
import net.minecraft.client.gui.FontRenderer;

public class OnOffButton
extends Button {
    protected Boolean toggled = true;
    protected String labelOn;
    protected String labelOff;
    protected ArrayList<ToggleListener> toggleListeners = new ArrayList(0);

    public OnOffButton(String labelOn, String labelOff, boolean toggled) {
        this(0, labelOn, labelOff, toggled);
    }

    public OnOffButton(int id, String labelOn, String labelOff, boolean toggled) {
        super(toggled ? labelOn : labelOff);
        this.labelOn = labelOn;
        this.labelOff = labelOff;
        this.setToggled(toggled);
        this.finishInit();
    }

    public void setLabels(String labelOn, String labelOff) {
        this.labelOn = labelOn;
        this.labelOff = labelOff;
        this.updateLabel();
    }

    @Override
    protected void updateLabel() {
        if (this.labelOn != null && this.labelOff != null) {
            this.field_146126_j = this.getToggled() != false ? this.labelOn : this.labelOff;
        }
    }

    public void toggle() {
        this.setToggled(this.getToggled() == false);
    }

    @Override
    public int getFitWidth(FontRenderer fr) {
        int max = fr.func_78256_a(this.field_146126_j);
        if (this.labelOn != null) {
            max = Math.max(max, fr.func_78256_a(this.labelOn));
        }
        if (this.labelOff != null) {
            max = Math.max(max, fr.func_78256_a(this.labelOff));
        }
        return max + this.WIDTH_PAD;
    }

    @Override
    public boolean isActive() {
        return this.isEnabled() && this.toggled != false;
    }

    public Boolean getToggled() {
        return this.toggled;
    }

    public void setToggled(Boolean toggled) {
        this.setToggled(toggled, true);
    }

    public void setToggled(Boolean toggled, boolean notifyToggleListener) {
        if (this.toggled == toggled || !this.isEnabled() || !this.field_146125_m) {
            return;
        }
        boolean allowChange = true;
        try {
            if (notifyToggleListener && !this.toggleListeners.isEmpty()) {
                ToggleListener listener;
                Iterator<ToggleListener> iterator2 = this.toggleListeners.iterator();
                while (iterator2.hasNext() && (allowChange = (listener = iterator2.next()).onToggle(this, toggled))) {
                }
            }
        }
        catch (Throwable t) {
            Journeymap.getLogger().error("Error trying to toggle button '" + this.field_146126_j + "': " + LogFormatter.toString(t));
            allowChange = false;
        }
        if (allowChange) {
            this.toggled = toggled;
            this.updateLabel();
        }
    }

    public void addToggleListener(ToggleListener toggleListener) {
        this.toggleListeners.add(toggleListener);
    }

    public static interface ToggleListener {
        public boolean onToggle(OnOffButton var1, boolean var2);
    }
}

