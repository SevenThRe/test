/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 */
package journeymap.client.ui.waypoint;

import journeymap.client.ui.component.OnOffButton;
import journeymap.client.ui.waypoint.WaypointManagerItemRight;
import net.minecraft.client.Minecraft;

public class SortButtonRight
extends OnOffButton {
    final WaypointManagerItemRight.Sort sort;
    final String labelInactive;

    public SortButtonRight(String label, WaypointManagerItemRight.Sort sort) {
        super(String.format("%s %s", label, "\u25b2"), String.format("%s %s", label, "\u25bc"), sort.ascending);
        this.labelInactive = label;
        this.sort = sort;
    }

    @Override
    public void toggle() {
        this.sort.ascending = !this.sort.ascending;
        this.setActive(true);
    }

    @Override
    public void drawButton(Minecraft minecraft, int mouseX, int mouseY, float f) {
        super.drawButton(minecraft, mouseX, mouseY, f);
        super.drawUnderline();
    }

    public void setActive(boolean active) {
        if (active) {
            this.setToggled(this.sort.ascending);
        } else {
            this.displayString = String.format("%s %s", this.labelInactive, " ");
        }
    }
}

