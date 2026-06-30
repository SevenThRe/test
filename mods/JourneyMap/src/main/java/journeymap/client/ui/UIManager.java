/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.GuiChat
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.gui.inventory.GuiInventory
 *  net.minecraft.client.settings.KeyBinding
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraftforge.fml.client.FMLClientHandler
 *  org.apache.logging.log4j.Level
 *  org.apache.logging.log4j.Logger
 */
package journeymap.client.ui;

import journeymap.client.data.WaypointsData;
import journeymap.client.log.ChatLog;
import journeymap.client.model.Waypoint;
import journeymap.client.properties.MiniMapProperties;
import journeymap.client.ui.component.JmUI;
import journeymap.client.ui.dialog.AboutDialog;
import journeymap.client.ui.dialog.GridEditor;
import journeymap.client.ui.dialog.OptionsManager;
import journeymap.client.ui.fullscreen.Fullscreen;
import journeymap.client.ui.minimap.MiniMap;
import journeymap.client.ui.serveroption.ServerOptionsManager;
import journeymap.client.ui.waypoint.WaypointEditor;
import journeymap.client.ui.waypoint.WaypointManager;
import journeymap.common.Journeymap;
import journeymap.common.log.LogFormatter;
import journeymap.common.properties.Category;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.client.FMLClientHandler;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;

public enum UIManager {
    INSTANCE;

    private final Logger logger = Journeymap.getLogger();
    private final MiniMap miniMap;
    Minecraft minecraft = FMLClientHandler.instance().getClient();

    private UIManager() {
        MiniMap tmp;
        try {
            int preset = Journeymap.getClient().getMiniMapProperties1().isActive() ? 1 : 2;
            tmp = new MiniMap(Journeymap.getClient().getMiniMapProperties(preset));
        }
        catch (Throwable e) {
            this.logger.error("Unexpected error: " + LogFormatter.toString(e));
            if (e instanceof LinkageError) {
                ChatLog.announceError(e.getMessage() + " : JourneyMap is not compatible with this build of Forge!");
            }
            tmp = new MiniMap(new MiniMapProperties(1));
        }
        this.miniMap = tmp;
    }

    public static void handleLinkageError(LinkageError error) {
        Journeymap.getLogger().error(LogFormatter.toString(error));
        try {
            ChatLog.announceError(error.getMessage() + " : JourneyMap is not compatible with this build of Forge!");
        }
        catch (Throwable t) {
            t.printStackTrace();
        }
    }

    public void closeAll() {
        try {
            this.closeCurrent();
        }
        catch (LinkageError e) {
            UIManager.handleLinkageError(e);
        }
        catch (Throwable e) {
            this.logger.error("Unexpected error: " + LogFormatter.toString(e));
        }
        this.minecraft.displayGuiScreen(null);
        this.minecraft.setIngameFocus();
    }

    public void closeCurrent() {
        try {
            if (this.minecraft.currentScreen != null && this.minecraft.currentScreen instanceof JmUI) {
                this.logger.debug("Closing " + this.minecraft.currentScreen.getClass());
                ((JmUI)this.minecraft.currentScreen).close();
            }
        }
        catch (LinkageError e) {
            UIManager.handleLinkageError(e);
        }
        catch (Throwable e) {
            this.logger.error("Unexpected error: " + LogFormatter.toString(e));
        }
    }

    public void openInventory() {
        this.logger.debug("Opening inventory");
        this.closeAll();
        this.minecraft.displayGuiScreen((GuiScreen)new GuiInventory((EntityPlayer)this.minecraft.player));
    }

    public <T extends JmUI> T open(Class<T> uiClass, JmUI returnDisplay) {
        try {
            return (T)((Object)((JmUI)this.open((GuiScreen)uiClass.getConstructor(JmUI.class).newInstance(new Object[]{returnDisplay}))));
        }
        catch (LinkageError e) {
            UIManager.handleLinkageError(e);
            return null;
        }
        catch (Throwable e) {
            try {
                return (T)((Object)((JmUI)this.open((GuiScreen)uiClass.getConstructor(new Class[0]).newInstance(new Object[0]))));
            }
            catch (Throwable e2) {
                this.logger.log(Level.ERROR, "1st unexpected exception creating UI: " + LogFormatter.toString(e));
                this.logger.log(Level.ERROR, "2nd unexpected exception creating UI: " + LogFormatter.toString(e2));
                this.closeCurrent();
                return null;
            }
        }
    }

    public <T extends JmUI> T open(Class<T> uiClass) {
        try {
            if (MiniMap.uiState().active) {
                MiniMap.updateUIState(false);
            }
            JmUI ui = (JmUI)((Object)uiClass.newInstance());
            return (T)((Object)this.open(ui));
        }
        catch (LinkageError e) {
            UIManager.handleLinkageError(e);
            return null;
        }
        catch (Throwable e) {
            this.logger.log(Level.ERROR, "Unexpected exception creating UI: " + LogFormatter.toString(e));
            this.closeCurrent();
            return null;
        }
    }

    public <T extends GuiScreen> T open(T ui) {
        this.closeCurrent();
        this.logger.debug("Opening UI " + ui.getClass().getSimpleName());
        try {
            this.minecraft.displayGuiScreen(ui);
            KeyBinding.unPressAllKeys();
        }
        catch (LinkageError e) {
            UIManager.handleLinkageError(e);
            return null;
        }
        catch (Throwable t) {
            this.logger.error(String.format("Unexpected exception opening UI %s: %s", ui.getClass(), LogFormatter.toString(t)));
        }
        return ui;
    }

    public void toggleMinimap() {
        try {
            this.setMiniMapEnabled(!this.isMiniMapEnabled());
        }
        catch (LinkageError e) {
            UIManager.handleLinkageError(e);
        }
        catch (Throwable t) {
            this.logger.error(String.format("Unexpected exception in toggleMinimap: %s", LogFormatter.toString(t)));
        }
    }

    public boolean isMiniMapEnabled() {
        try {
            return this.miniMap.getCurrentMinimapProperties().enabled.get();
        }
        catch (LinkageError e) {
            UIManager.handleLinkageError(e);
        }
        catch (Throwable t) {
            this.logger.error(String.format("Unexpected exception in isMiniMapEnabled: %s", LogFormatter.toString(t)));
        }
        return false;
    }

    public void setMiniMapEnabled(boolean enable) {
        try {
            this.miniMap.getCurrentMinimapProperties().enabled.set(enable);
            this.miniMap.getCurrentMinimapProperties().save();
        }
        catch (LinkageError e) {
            UIManager.handleLinkageError(e);
        }
        catch (Throwable t) {
            this.logger.error(String.format("Unexpected exception in setMiniMapEnabled: %s", LogFormatter.toString(t)));
        }
    }

    public void drawMiniMap() {
        this.minecraft.profiler.startSection("journeymap");
        try {
            boolean doDraw = false;
            if (this.miniMap.getCurrentMinimapProperties().enabled.get().booleanValue()) {
                GuiScreen currentScreen = this.minecraft.currentScreen;
                boolean bl = doDraw = currentScreen == null || currentScreen instanceof GuiChat;
                if (doDraw) {
                    if (!MiniMap.uiState().active) {
                        if (MiniMap.state().getLastMapTypeChange() == 0L) {
                            this.miniMap.reset();
                        } else {
                            MiniMap.state().requireRefresh();
                        }
                    }
                    this.miniMap.drawMap();
                }
            }
            if (doDraw && !MiniMap.uiState().active) {
                MiniMap.updateUIState(true);
            }
        }
        catch (LinkageError e) {
            UIManager.handleLinkageError(e);
        }
        catch (Throwable e) {
            Journeymap.getLogger().error("Error drawing minimap: " + LogFormatter.toString(e));
        }
        finally {
            this.minecraft.profiler.endSection();
        }
    }

    public MiniMap getMiniMap() {
        return this.miniMap;
    }

    public Fullscreen openFullscreenMap() {
        if (this.minecraft.currentScreen instanceof Fullscreen) {
            return (Fullscreen)this.minecraft.currentScreen;
        }
        KeyBinding.unPressAllKeys();
        return (Fullscreen)((Object)this.open((GuiScreen)Fullscreen.class));
    }

    public void openFullscreenMap(Waypoint waypoint) {
        try {
            if (waypoint.isInPlayerDimension()) {
                Fullscreen map = (Fullscreen)((Object)this.open((GuiScreen)Fullscreen.class));
                map.centerOn(waypoint);
            }
        }
        catch (LinkageError e) {
            UIManager.handleLinkageError(e);
        }
        catch (Throwable e) {
            Journeymap.getLogger().error("Error opening map on waypoint: " + LogFormatter.toString(e));
        }
    }

    public void openOptionsManager() {
        this.open((GuiScreen)OptionsManager.class);
    }

    public void openOptionsManager(JmUI returnDisplay, Category ... initialCategories) {
        try {
            this.open(new OptionsManager(returnDisplay, initialCategories));
        }
        catch (LinkageError e) {
            UIManager.handleLinkageError(e);
        }
        catch (Throwable e) {
            this.logger.log(Level.ERROR, "Unexpected exception creating MasterOptions with return class: " + LogFormatter.toString(e));
        }
    }

    public void openSplash(JmUI returnDisplay) {
        this.open(AboutDialog.class, returnDisplay);
    }

    public void openWaypointManager(Waypoint waypoint, JmUI returnDisplay) {
        if (WaypointsData.isManagerEnabled()) {
            try {
                WaypointManager manager = new WaypointManager(waypoint, returnDisplay);
                this.open(manager);
            }
            catch (LinkageError e) {
                UIManager.handleLinkageError(e);
            }
            catch (Throwable e) {
                Journeymap.getLogger().error("Error opening waypoint manager: " + LogFormatter.toString(e));
            }
        }
    }

    public void openWaypointEditor(Waypoint waypoint, boolean isNew, JmUI returnDisplay) {
        if (WaypointsData.isManagerEnabled()) {
            try {
                WaypointEditor editor = new WaypointEditor(waypoint, isNew, returnDisplay);
                this.open(editor);
            }
            catch (LinkageError e) {
                UIManager.handleLinkageError(e);
            }
            catch (Throwable e) {
                Journeymap.getLogger().error("Error opening waypoint editor: " + LogFormatter.toString(e));
            }
        }
    }

    public void openGridEditor(JmUI returnDisplay) {
        try {
            GridEditor editor = new GridEditor(returnDisplay);
            this.open(editor);
        }
        catch (LinkageError e) {
            UIManager.handleLinkageError(e);
        }
        catch (Throwable e) {
            Journeymap.getLogger().error("Error opening grid editor: " + LogFormatter.toString(e));
        }
    }

    public void openServerEditor(JmUI returnDisplay) {
        try {
            ServerOptionsManager editor = new ServerOptionsManager(returnDisplay);
            this.open(editor);
        }
        catch (LinkageError e) {
            UIManager.handleLinkageError(e);
        }
        catch (Throwable e) {
            Journeymap.getLogger().error("Error opening server manager: " + LogFormatter.toString(e));
        }
    }

    public void reset() {
        try {
            Fullscreen.state().requireRefresh();
            this.miniMap.reset();
        }
        catch (LinkageError e) {
            UIManager.handleLinkageError(e);
        }
        catch (Throwable e) {
            Journeymap.getLogger().error("Error during reset: " + LogFormatter.toString(e));
        }
    }

    public void switchMiniMapPreset() {
        try {
            int currentPreset = this.miniMap.getCurrentMinimapProperties().getId();
            this.switchMiniMapPreset(currentPreset == 1 ? 2 : 1);
        }
        catch (LinkageError e) {
            UIManager.handleLinkageError(e);
        }
        catch (Throwable e) {
            Journeymap.getLogger().error("Error during switchMiniMapPreset: " + LogFormatter.toString(e));
        }
    }

    public void switchMiniMapPreset(int which) {
        try {
            this.miniMap.setMiniMapProperties(Journeymap.getClient().getMiniMapProperties(which));
            MiniMap.state().requireRefresh();
        }
        catch (LinkageError e) {
            UIManager.handleLinkageError(e);
        }
        catch (Throwable e) {
            Journeymap.getLogger().error("Error during switchMiniMapPreset: " + LogFormatter.toString(e));
        }
    }
}

