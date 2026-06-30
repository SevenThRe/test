/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonObject
 *  net.minecraft.client.Minecraft
 *  net.minecraftforge.fml.client.FMLClientHandler
 *  net.minecraftforge.fml.common.Loader
 *  net.minecraftforge.fml.common.Mod$EventHandler
 *  net.minecraftforge.fml.common.event.FMLInitializationEvent
 *  net.minecraftforge.fml.common.event.FMLInterModComms$IMCEvent
 *  net.minecraftforge.fml.common.event.FMLPostInitializationEvent
 *  net.minecraftforge.fml.common.event.FMLPreInitializationEvent
 *  net.minecraftforge.fml.common.registry.EntityRegistry
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package journeymap.client;

import com.google.gson.JsonObject;
import java.io.File;
import java.util.Map;
import journeymap.client.Constants;
import journeymap.client.api.impl.ClientAPI;
import journeymap.client.api.impl.IMCHandler;
import journeymap.client.api.util.PluginHelper;
import journeymap.client.cartography.ChunkRenderController;
import journeymap.client.cartography.color.ColorPalette;
import journeymap.client.data.DataCache;
import journeymap.client.forge.event.EventHandlerManager;
import journeymap.client.io.FileHandler;
import journeymap.client.io.IconSetFileHandler;
import journeymap.client.io.ThemeLoader;
import journeymap.client.log.ChatLog;
import journeymap.client.log.JMLogger;
import journeymap.client.log.StatTimer;
import journeymap.client.mod.impl.Pixelmon;
import journeymap.client.model.RegionImageCache;
import journeymap.client.properties.CoreProperties;
import journeymap.client.properties.FullMapProperties;
import journeymap.client.properties.MiniMapProperties;
import journeymap.client.properties.TopoProperties;
import journeymap.client.properties.WaypointProperties;
import journeymap.client.properties.WebMapProperties;
import journeymap.client.render.map.TileDrawStepCache;
import journeymap.client.service.webmap.Webmap;
import journeymap.client.task.main.IMainThreadTask;
import journeymap.client.task.main.MainTaskController;
import journeymap.client.task.main.MappingMonitorTask;
import journeymap.client.task.multi.ITaskManager;
import journeymap.client.task.multi.TaskController;
import journeymap.client.ui.UIManager;
import journeymap.client.ui.fullscreen.Fullscreen;
import journeymap.client.waypoint.WaypointStore;
import journeymap.client.world.ChunkMonitor;
import journeymap.common.CommonProxy;
import journeymap.common.Journeymap;
import journeymap.common.log.LogFormatter;
import journeymap.common.migrate.Migration;
import journeymap.common.network.GetClientConfig;
import journeymap.common.version.VersionCheck;
import modinfo.ModInfo;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLInterModComms;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SideOnly(value=Side.CLIENT)
public class JourneymapClient
implements CommonProxy {
    public static final String FULL_VERSION = "1.12.2-" + Journeymap.JM_VERSION;
    public static final String MOD_NAME = "JourneyMap " + FULL_VERSION;
    private boolean journeyMapServerConnection = false;
    private boolean forgeServerConnection = false;
    private boolean playerTrackingEnabled = false;
    private boolean teleportEnabled = false;
    private boolean modInfoReported = false;
    private boolean serverAdmin = false;
    private volatile CoreProperties coreProperties;
    private volatile FullMapProperties fullMapProperties;
    private volatile MiniMapProperties miniMapProperties1;
    private volatile MiniMapProperties miniMapProperties2;
    private volatile TopoProperties topoProperties;
    private volatile WebMapProperties webMapProperties;
    private volatile WaypointProperties waypointProperties;
    private volatile Boolean initialized = false;
    private volatile String currentWorldId = null;
    private Logger logger;
    private boolean threadLogging = false;
    private final MainTaskController mainThreadTaskController = new MainTaskController();
    private TaskController multithreadTaskController;
    private ChunkRenderController chunkRenderController;

    public CoreProperties getCoreProperties() {
        return this.coreProperties;
    }

    public FullMapProperties getFullMapProperties() {
        return this.fullMapProperties;
    }

    public TopoProperties getTopoProperties() {
        return this.topoProperties;
    }

    public void disable() {
        this.initialized = false;
        EventHandlerManager.unregisterAll();
        this.stopMapping();
        ClientAPI.INSTANCE.purge();
        DataCache.INSTANCE.purge();
    }

    public MiniMapProperties getMiniMapProperties(int which) {
        switch (which) {
            case 2: {
                this.miniMapProperties2.setActive(true);
                this.miniMapProperties1.setActive(false);
                return this.getMiniMapProperties2();
            }
        }
        this.miniMapProperties1.setActive(true);
        this.miniMapProperties2.setActive(false);
        return this.getMiniMapProperties1();
    }

    public MiniMapProperties getActiveMiniMapProperties() {
        if (this.miniMapProperties1.isActive()) {
            return this.getMiniMapProperties1();
        }
        return this.getMiniMapProperties2();
    }

    public int getActiveMinimapId() {
        if (this.miniMapProperties1.isActive()) {
            return 1;
        }
        return 2;
    }

    public MiniMapProperties getMiniMapProperties1() {
        return this.miniMapProperties1;
    }

    public MiniMapProperties getMiniMapProperties2() {
        return this.miniMapProperties2;
    }

    public WebMapProperties getWebMapProperties() {
        return this.webMapProperties;
    }

    public WaypointProperties getWaypointProperties() {
        return this.waypointProperties;
    }

    @Override
    public void preInitialize(FMLPreInitializationEvent event) throws Throwable {
        try {
            PluginHelper.INSTANCE.preInitPlugins(event);
        }
        catch (Throwable t) {
            t.printStackTrace();
        }
    }

    @Override
    public void initialize(FMLInitializationEvent event) throws Throwable {
        StatTimer timer = null;
        try {
            timer = StatTimer.getDisposable("elapsed").start();
            boolean migrationOk = new Migration("journeymap.client.task.migrate").performTasks();
            this.logger = JMLogger.init();
            this.logger.info("initialize ENTER");
            if (this.initialized.booleanValue()) {
                this.logger.warn("Already initialized, aborting");
                return;
            }
            EntityRegistry.instance();
            this.loadConfigProperties();
            JMLogger.logProperties();
            this.threadLogging = false;
            PluginHelper.INSTANCE.initPlugins(event, ClientAPI.INSTANCE);
            this.logger.info("initialize EXIT, " + (timer == null ? "" : timer.getLogReportString()));
        }
        catch (Throwable t) {
            if (this.logger == null) {
                this.logger = LogManager.getLogger((String)"journeymap");
            }
            this.logger.error(LogFormatter.toString(t));
            throw t;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void postInitialize(FMLPostInitializationEvent event) {
        StatTimer timer = null;
        try {
            this.logger.debug("postInitialize ENTER");
            timer = StatTimer.getDisposable("elapsed").start();
            this.queueMainThreadTask(new MappingMonitorTask());
            EventHandlerManager.registerHandlers();
            IconSetFileHandler.initialize();
            ThemeLoader.initialize(true);
            if (this.webMapProperties.enabled.get().booleanValue()) {
                Webmap.INSTANCE.start();
            }
            ChatLog.announceMod(false);
            this.initialized = true;
            VersionCheck.getVersionAvailable();
            String pixelmonModId = "Pixelmon";
            if (Loader.isModLoaded((String)pixelmonModId) || Loader.isModLoaded((String)pixelmonModId.toLowerCase())) {
                this.logger.info(pixelmonModId + " is loaded in class path. Initializing icon display.");
                new Pixelmon(true);
            }
            this.logger.debug("postInitialize EXIT, " + (timer == null ? "" : timer.stopAndReport()));
        }
        catch (Throwable t) {
            try {
                if (this.logger == null) {
                    this.logger = LogManager.getLogger((String)"journeymap");
                }
                this.logger.error(LogFormatter.toString(t));
                this.logger.debug("postInitialize EXIT, " + (timer == null ? "" : timer.stopAndReport()));
            }
            catch (Throwable throwable) {
                this.logger.debug("postInitialize EXIT, " + (timer == null ? "" : timer.stopAndReport()));
                throw throwable;
            }
        }
        JMLogger.setLevelFromProperties();
    }

    @Override
    public boolean checkModLists(Map<String, String> modList, Side side) {
        return true;
    }

    @Override
    public boolean isUpdateCheckEnabled() {
        return this.getCoreProperties().checkUpdates.get();
    }

    @Mod.EventHandler
    public void handleIMC(FMLInterModComms.IMCEvent event) {
        IMCHandler.handle(event);
    }

    public Boolean isInitialized() {
        return this.initialized;
    }

    public Boolean isMapping() {
        return this.initialized != false && this.multithreadTaskController != null && this.multithreadTaskController.isActive() != false;
    }

    public Boolean isThreadLogging() {
        return this.threadLogging;
    }

    public Webmap getJmServer() {
        return Webmap.INSTANCE;
    }

    public void queueOneOff(Runnable runnable) throws Exception {
        if (this.multithreadTaskController != null) {
            this.multithreadTaskController.queueOneOff(runnable);
        }
    }

    public void toggleTask(Class<? extends ITaskManager> managerClass, boolean enable, Object params) {
        if (this.multithreadTaskController != null) {
            this.multithreadTaskController.toggleTask(managerClass, enable, params);
        }
    }

    public boolean isTaskManagerEnabled(Class<? extends ITaskManager> managerClass) {
        if (this.multithreadTaskController != null) {
            return this.multithreadTaskController.isTaskManagerEnabled(managerClass);
        }
        return false;
    }

    public boolean isMainThreadTaskActive() {
        if (this.mainThreadTaskController != null) {
            return this.mainThreadTaskController.isActive();
        }
        return false;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void startMapping() {
        JourneymapClient journeymapClient = this;
        synchronized (journeymapClient) {
            boolean created;
            Minecraft mc = FMLClientHandler.instance().getClient();
            if (mc == null || mc.field_71441_e == null || !this.initialized.booleanValue() || !this.coreProperties.mappingEnabled.get().booleanValue()) {
                return;
            }
            File worldDir = FileHandler.getJMWorldDir(mc, this.currentWorldId);
            if (worldDir == null) {
                return;
            }
            if (!worldDir.exists() && !(created = worldDir.mkdirs())) {
                JMLogger.logOnce("CANNOT CREATE DATA DIRECTORY FOR WORLD: " + worldDir.getPath(), null);
                return;
            }
            this.reset();
            if (!this.modInfoReported && mc.field_71474_y.field_74355_t) {
                new ModInfo("UA-28839029-5", "en_US", "journeymap", MOD_NAME, FULL_VERSION, true);
                this.modInfoReported = true;
            }
            this.multithreadTaskController = new TaskController();
            this.multithreadTaskController.enableTasks();
            long totalMB = Runtime.getRuntime().totalMemory() / 1024L / 1024L;
            long freeMB = Runtime.getRuntime().freeMemory() / 1024L / 1024L;
            String memory = String.format("Memory: %sMB total, %sMB free", totalMB, freeMB);
            int dimension = mc.field_71441_e.field_73011_w.getDimension();
            this.logger.info(String.format("Mapping started in %s%sDIM%s. %s ", FileHandler.getJMWorldDir(mc, this.currentWorldId), File.separator, dimension, memory));
            if (this.isJourneyMapServerConnection() || FMLClientHandler.instance().getClient().func_71356_B()) {
                new GetClientConfig().send();
            }
            ClientAPI.INSTANCE.getClientEventManager().fireMappingEvent(true, dimension);
            UIManager.INSTANCE.getMiniMap().reset();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void stopMapping() {
        JourneymapClient journeymapClient = this;
        synchronized (journeymapClient) {
            ChunkMonitor.INSTANCE.reset();
            Minecraft mc = FMLClientHandler.instance().getClient();
            if (this.isMapping().booleanValue() && mc != null) {
                this.logger.info(String.format("Mapping halted in %s%sDIM%s", FileHandler.getJMWorldDir(mc, this.currentWorldId), File.separator, mc.field_71441_e.field_73011_w.getDimension()));
                RegionImageCache.INSTANCE.flushToDiskAsync(true);
                ColorPalette colorPalette = ColorPalette.getActiveColorPalette();
                if (colorPalette != null) {
                    colorPalette.writeToFile();
                }
            }
            if (this.multithreadTaskController != null) {
                this.multithreadTaskController.disableTasks();
                this.multithreadTaskController.clear();
                this.multithreadTaskController = null;
            }
            if (mc != null) {
                int dimension = mc.field_71441_e != null ? mc.field_71441_e.field_73011_w.getDimension() : 0;
                ClientAPI.INSTANCE.getClientEventManager().fireMappingEvent(false, dimension);
            }
        }
    }

    private void reset() {
        if (!FMLClientHandler.instance().getClient().func_71356_B() && this.currentWorldId == null) {
            new GetClientConfig().send(null, response -> {
                JsonObject settings = response.getAsJson().get("settings").getAsJsonObject();
                if (settings.get("world_id") != null) {
                    this.setCurrentWorldId(settings.get("world_id").getAsString());
                }
            });
        }
        this.loadConfigProperties();
        DataCache.INSTANCE.purge();
        ChunkMonitor.INSTANCE.reset();
        this.chunkRenderController = new ChunkRenderController();
        Fullscreen.state().requireRefresh();
        Fullscreen.state().follow.set(true);
        StatTimer.resetAll();
        TileDrawStepCache.clear();
        UIManager.INSTANCE.getMiniMap().reset();
        UIManager.INSTANCE.reset();
        WaypointStore.INSTANCE.reset();
    }

    public void queueMainThreadTask(IMainThreadTask task) {
        this.mainThreadTaskController.addTask(task);
    }

    public void performMainThreadTasks() {
        this.mainThreadTaskController.performTasks();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void performMultithreadTasks() {
        try {
            JourneymapClient journeymapClient = this;
            synchronized (journeymapClient) {
                if (this.isMapping().booleanValue()) {
                    this.multithreadTaskController.performTasks();
                }
            }
        }
        catch (Throwable t) {
            String error = "Error in JourneyMap.performMultithreadTasks(): " + t.getMessage();
            ChatLog.announceError(error);
            this.logger.error(LogFormatter.toString(t));
        }
    }

    public ChunkRenderController getChunkRenderController() {
        return this.chunkRenderController;
    }

    public void saveConfigProperties() {
        if (this.coreProperties != null) {
            this.coreProperties.save();
        }
        if (this.fullMapProperties != null) {
            this.fullMapProperties.save();
        }
        if (this.miniMapProperties1 != null) {
            this.miniMapProperties1.save();
        }
        if (this.miniMapProperties2 != null) {
            this.miniMapProperties2.save();
        }
        if (this.miniMapProperties2 != null) {
            this.miniMapProperties2.save();
        }
        if (this.topoProperties != null) {
            this.topoProperties.save();
        }
        if (this.webMapProperties != null) {
            this.webMapProperties.save();
        }
        if (this.waypointProperties != null) {
            this.waypointProperties.save();
        }
    }

    public void loadConfigProperties() {
        this.saveConfigProperties();
        this.coreProperties = (CoreProperties)new CoreProperties().load();
        this.fullMapProperties = (FullMapProperties)new FullMapProperties().load();
        this.miniMapProperties1 = (MiniMapProperties)new MiniMapProperties(1).load();
        this.miniMapProperties2 = (MiniMapProperties)new MiniMapProperties(2).load();
        this.topoProperties = (TopoProperties)new TopoProperties().load();
        this.webMapProperties = (WebMapProperties)new WebMapProperties().load();
        this.waypointProperties = (WaypointProperties)new WaypointProperties().load();
    }

    public String getCurrentWorldId() {
        return this.currentWorldId;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void setCurrentWorldId(String worldId) {
        JourneymapClient journeymapClient = this;
        synchronized (journeymapClient) {
            Minecraft mc = FMLClientHandler.instance().getClient();
            if (!mc.func_71356_B()) {
                boolean directoryUnchanged;
                File currentWorldDirectory = FileHandler.getJMWorldDirForWorldId(mc, this.currentWorldId);
                File newWorldDirectory = FileHandler.getJMWorldDir(mc, worldId);
                boolean worldIdUnchanged = Constants.safeEqual(worldId, this.currentWorldId);
                boolean bl = directoryUnchanged = currentWorldDirectory != null && newWorldDirectory != null && currentWorldDirectory.getPath().equals(newWorldDirectory.getPath());
                if (worldIdUnchanged && directoryUnchanged && worldId != null) {
                    Journeymap.getLogger().info("World UID hasn't changed: " + worldId);
                    return;
                }
                boolean wasMapping = this.isMapping();
                if (wasMapping) {
                    this.stopMapping();
                }
                this.currentWorldId = worldId;
                Journeymap.getLogger().info("World UID is set to: " + worldId);
            }
        }
    }

    public boolean isForgeServerConnection() {
        return this.forgeServerConnection;
    }

    public void setForgeServerConnection(boolean forgeServerConnection) {
        this.forgeServerConnection = forgeServerConnection;
    }

    public boolean isJourneyMapServerConnection() {
        return this.journeyMapServerConnection;
    }

    public void setJourneyMapServerConnection(boolean journeyMapServerConnection) {
        Journeymap.getLogger().debug("Connection initiated with Journeymap Server: " + journeyMapServerConnection);
        this.journeyMapServerConnection = journeyMapServerConnection;
    }

    public boolean isPlayerTrackingEnabled() {
        return this.playerTrackingEnabled;
    }

    public void setPlayerTrackingEnabled(boolean playerTrackingEnabled) {
        if (FMLClientHandler.instance().getClient().func_71356_B()) {
            this.playerTrackingEnabled = false;
            return;
        }
        Journeymap.getLogger().debug("Expanded Radar Enabled:" + playerTrackingEnabled);
        this.playerTrackingEnabled = playerTrackingEnabled;
    }

    public boolean isTeleportEnabled() {
        return this.teleportEnabled;
    }

    public void setTeleportEnabled(boolean teleportEnabled) {
        Journeymap.getLogger().debug("Teleport Enabled:" + teleportEnabled);
        this.teleportEnabled = teleportEnabled;
    }

    public boolean isServerAdmin() {
        return this.serverAdmin || FMLClientHandler.instance().getClient().func_71356_B();
    }

    public void setServerAdmin(boolean serverAdmin) {
        if (serverAdmin) {
            Journeymap.getLogger().debug("Server Admin Enabled:" + serverAdmin);
        }
        this.serverAdmin = serverAdmin;
    }
}

