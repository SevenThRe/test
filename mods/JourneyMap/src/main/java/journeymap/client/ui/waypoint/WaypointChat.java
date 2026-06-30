/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiChat
 */
package journeymap.client.ui.waypoint;

import journeymap.client.model.Waypoint;
import net.minecraft.client.gui.GuiChat;

public class WaypointChat
extends GuiChat {
    public WaypointChat(Waypoint waypoint) {
        this(waypoint.toChatString());
    }

    public WaypointChat(String text) {
        super(text);
    }

    public void func_73866_w_() {
        super.func_73866_w_();
        this.field_146415_a.func_146196_d();
    }
}

