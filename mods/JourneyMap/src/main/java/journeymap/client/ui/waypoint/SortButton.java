/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 */
package journeymap.client.ui.waypoint;

import journeymap.client.ui.component.OnOffButton;
import journeymap.client.ui.waypoint.WaypointManagerItem;
import net.minecraft.client.Minecraft;

class SortButton
extends OnOffButton {
    final WaypointManagerItem.Sort sort;
    final String labelInactive;

    public SortButton(String label, WaypointManagerItem.Sort sort) {
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

