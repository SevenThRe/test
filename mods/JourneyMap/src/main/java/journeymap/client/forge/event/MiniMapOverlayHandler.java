/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.util.text.TextFormatting
 *  net.minecraftforge.client.event.RenderGameOverlayEvent
 *  net.minecraftforge.client.event.RenderGameOverlayEvent$ElementType
 *  net.minecraftforge.client.event.RenderGameOverlayEvent$Text
 *  net.minecraftforge.fml.client.FMLClientHandler
 *  net.minecraftforge.fml.common.Loader
 *  net.minecraftforge.fml.common.eventhandler.EventPriority
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package journeymap.client.forge.event;

import java.util.Collections;
import java.util.List;
import journeymap.client.Constants;
import journeymap.client.JourneymapClient;
import journeymap.client.forge.event.EventHandlerManager;
import journeymap.client.log.JMLogger;
import journeymap.client.log.StatTimer;
import journeymap.client.task.multi.MapPlayerTask;
import journeymap.client.ui.UIManager;
import journeymap.common.Journeymap;
import net.minecraft.client.Minecraft;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(value=Side.CLIENT)
public class MiniMapOverlayHandler
implements EventHandlerManager.EventHandler {
    private static final String DEBUG_PREFIX = TextFormatting.AQUA + "[JM] " + TextFormatting.RESET;
    private static final String DEBUG_SUFFIX = "";
    private static RenderGameOverlayEvent.ElementType EVENT_TYPE = RenderGameOverlayEvent.ElementType.ALL;
    private static boolean EVENT_PRE = true;
    private final Minecraft mc = FMLClientHandler.instance().getClient();
    private JourneymapClient jm;
    private long statTimerCheck;
    private List<String> statTimerReport = Collections.EMPTY_LIST;

    public static void checkEventConfig() {
        EVENT_TYPE = (RenderGameOverlayEvent.ElementType)Journeymap.getClient().getCoreProperties().renderOverlayEventTypeName.get();
        EVENT_PRE = Journeymap.getClient().getCoreProperties().renderOverlayPreEvent.get();
    }

    @SubscribeEvent(priority=EventPriority.NORMAL)
    public void onRenderOverlayDebug(RenderGameOverlayEvent.Text event) {
        try {
            if (this.mc.field_71474_y.field_74330_P && !"off".equalsIgnoreCase(Journeymap.getClient().getCoreProperties().logLevel.get())) {
                event.getLeft().add(null);
                if (Journeymap.getClient().getCoreProperties().mappingEnabled.get().booleanValue()) {
                    for (String line : MapPlayerTask.getDebugStats()) {
                        event.getLeft().add(DEBUG_PREFIX + line + DEBUG_SUFFIX);
                    }
                } else {
                    event.getLeft().add(Constants.getString("jm.common.enable_mapping_false_text") + DEBUG_SUFFIX);
                }
                if (this.mc.field_71474_y.field_74329_Q) {
                    if (System.currentTimeMillis() - this.statTimerCheck > 3000L) {
                        this.statTimerReport = StatTimer.getReportByTotalTime(DEBUG_PREFIX, DEBUG_SUFFIX);
                        this.statTimerCheck = System.currentTimeMillis();
                    }
                    event.getLeft().add(null);
                    for (String line : this.statTimerReport) {
                        event.getLeft().add(line);
                    }
                }
            }
        }
        catch (Throwable t) {
            JMLogger.logOnce("Unexpected error during onRenderOverlayEarly: " + t, t);
        }
    }

    @SubscribeEvent(priority=EventPriority.NORMAL)
    public void onRenderOverlay(RenderGameOverlayEvent event) {
        try {
            if (Loader.isModLoaded((String)"labymod") && event.isCancelable() == EVENT_PRE) {
                UIManager.INSTANCE.drawMiniMap();
                return;
            }
            if (event.getType() == EVENT_TYPE && event.isCancelable() == EVENT_PRE) {
                UIManager.INSTANCE.drawMiniMap();
            }
        }
        catch (Throwable t) {
            JMLogger.logOnce("Unexpected error during onRenderOverlayEarly: " + t, t);
        }
    }
}

