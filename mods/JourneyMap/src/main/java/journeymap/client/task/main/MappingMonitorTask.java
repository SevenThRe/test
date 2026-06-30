/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.GuiGameOver
 *  net.minecraft.client.gui.GuiMainMenu
 *  net.minecraft.client.gui.GuiMultiplayer
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.gui.GuiWorldSelection
 *  org.apache.logging.log4j.Logger
 */
package journeymap.client.task.main;

import journeymap.client.JourneymapClient;
import journeymap.client.log.ChatLog;
import journeymap.client.task.main.IMainThreadTask;
import journeymap.client.ui.fullscreen.Fullscreen;
import journeymap.common.Journeymap;
import journeymap.common.log.LogFormatter;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGameOver;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiWorldSelection;
import org.apache.logging.log4j.Logger;

public class MappingMonitorTask
implements IMainThreadTask {
    private static String NAME = "Tick." + MappingMonitorTask.class.getSimpleName();
    Logger logger = Journeymap.getLogger();
    private int lastDimension = 0;

    @Override
    public IMainThreadTask perform(Minecraft mc, JourneymapClient jm) {
        try {
            boolean isGamePaused;
            boolean isDead;
            if (!jm.isInitialized().booleanValue()) {
                return this;
            }
            boolean bl = isDead = mc.currentScreen != null && mc.currentScreen instanceof GuiGameOver;
            if (mc.world == null) {
                GuiScreen guiScreen;
                if (jm.isMapping().booleanValue()) {
                    jm.stopMapping();
                }
                if (((guiScreen = mc.currentScreen) instanceof GuiMainMenu || guiScreen instanceof GuiWorldSelection || guiScreen instanceof GuiMultiplayer) && jm.getCurrentWorldId() != null) {
                    this.logger.info("World ID has been reset.");
                    jm.setCurrentWorldId(null);
                }
                return this;
            }
            if (this.lastDimension != mc.player.dimension) {
                this.lastDimension = mc.player.dimension;
                if (jm.isMapping().booleanValue()) {
                    jm.stopMapping();
                }
            } else if (!jm.isMapping().booleanValue() && !isDead && Journeymap.getClient().getCoreProperties().mappingEnabled.get().booleanValue()) {
                jm.startMapping();
            }
            boolean bl2 = isGamePaused = mc.currentScreen != null && !(mc.currentScreen instanceof Fullscreen);
            if (isGamePaused && !jm.isMapping().booleanValue()) {
                return this;
            }
            if (!isGamePaused) {
                ChatLog.showChatAnnouncements(mc);
            }
            if (!jm.isMapping().booleanValue() && Journeymap.getClient().getCoreProperties().mappingEnabled.get().booleanValue()) {
                jm.startMapping();
            }
        }
        catch (Throwable t) {
            this.logger.error("Error in JourneyMap.performMainThreadTasks(): " + LogFormatter.toString(t));
        }
        return this;
    }

    @Override
    public String getName() {
        return NAME;
    }
}

