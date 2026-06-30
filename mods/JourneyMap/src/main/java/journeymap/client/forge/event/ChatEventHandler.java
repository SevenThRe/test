/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Strings
 *  net.minecraftforge.client.event.ClientChatReceivedEvent
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package journeymap.client.forge.event;

import com.google.common.base.Strings;
import journeymap.client.forge.event.EventHandlerManager;
import journeymap.client.waypoint.WaypointParser;
import journeymap.common.Journeymap;
import journeymap.common.log.LogFormatter;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(value=Side.CLIENT)
public class ChatEventHandler
implements EventHandlerManager.EventHandler {
    @SideOnly(value=Side.CLIENT)
    @SubscribeEvent
    public void invoke(ClientChatReceivedEvent event) {
        if (event.getMessage() != null) {
            try {
                String text = event.getMessage().getFormattedText();
                if (!Strings.isNullOrEmpty((String)text)) {
                    WaypointParser.parseChatForWaypoints(event, text);
                }
            }
            catch (Exception e) {
                Journeymap.getLogger().warn("Unexpected exception on ClientChatReceivedEvent: " + LogFormatter.toString(e));
            }
        }
    }
}

