/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityPlayerSP
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.renderer.RenderHelper
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.ITabCompleter
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.util.text.TextFormatting
 *  net.minecraftforge.fml.client.FMLClientHandler
 *  org.apache.logging.log4j.Level
 *  org.apache.logging.log4j.Logger
 *  org.lwjgl.input.Keyboard
 *  org.lwjgl.input.Mouse
 */
package journeymap.client.ui.fullscreen;

import java.awt.Color;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;
import javax.annotation.Nullable;
import journeymap.client.Constants;
import journeymap.client.api.display.Context;
import journeymap.client.api.display.Overlay;
import journeymap.client.api.display.PolygonOverlay;
import journeymap.client.api.impl.ClientAPI;
import journeymap.client.api.model.MapPolygon;
import journeymap.client.api.model.ShapeProperties;
import journeymap.client.api.util.UIState;
import journeymap.client.data.DataCache;
import journeymap.client.data.WaypointsData;
import journeymap.client.feature.Feature;
import journeymap.client.feature.FeatureManager;
import journeymap.client.io.MapSaver;
import journeymap.client.io.ThemeLoader;
import journeymap.client.log.ChatLog;
import journeymap.client.log.StatTimer;
import journeymap.client.model.BlockMD;
import journeymap.client.model.EntityDTO;
import journeymap.client.model.MapState;
import journeymap.client.model.MapType;
import journeymap.client.model.Waypoint;
import journeymap.client.properties.CoreProperties;
import journeymap.client.properties.FullMapProperties;
import journeymap.client.properties.MiniMapProperties;
import journeymap.client.render.draw.DrawUtil;
import journeymap.client.render.draw.RadarDrawStepFactory;
import journeymap.client.render.draw.WaypointDrawStepFactory;
import journeymap.client.render.map.GridRenderer;
import journeymap.client.render.map.RegionRenderer;
import journeymap.client.render.texture.TextureCache;
import journeymap.client.render.texture.TextureImpl;
import journeymap.client.task.main.EnsureCurrentColorsTask;
import journeymap.client.task.multi.MapRegionTask;
import journeymap.client.task.multi.SaveMapTask;
import journeymap.client.ui.UIManager;
import journeymap.client.ui.component.Button;
import journeymap.client.ui.component.ButtonList;
import journeymap.client.ui.component.IntSliderButton;
import journeymap.client.ui.component.JmUI;
import journeymap.client.ui.component.OnOffButton;
import journeymap.client.ui.component.TextBoxButton;
import journeymap.client.ui.dialog.AutoMapConfirmation;
import journeymap.client.ui.dialog.DeleteMapConfirmation;
import journeymap.client.ui.dialog.FullscreenActions;
import journeymap.client.ui.fullscreen.MapChat;
import journeymap.client.ui.fullscreen.layer.LayerDelegate;
import journeymap.client.ui.minimap.EntityDisplay;
import journeymap.client.ui.minimap.Shape;
import journeymap.client.ui.option.LocationFormat;
import journeymap.client.ui.theme.Theme;
import journeymap.client.ui.theme.ThemeButton;
import journeymap.client.ui.theme.ThemeToggle;
import journeymap.client.ui.theme.ThemeToolbar;
import journeymap.client.ui.waypoint.WaypointManagerRight;
import journeymap.client.waypoint.WaypointStore;
import journeymap.common.Journeymap;
import journeymap.common.log.LogFormatter;
import journeymap.common.version.VersionCheck;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ITabCompleter;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.client.FMLClientHandler;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

public class Fullscreen
extends JmUI
implements ITabCompleter {
    static MapState state = new MapState();
    static GridRenderer gridRenderer = new GridRenderer(Context.UI.Fullscreen, 5);
    WaypointDrawStepFactory waypointRenderer = new WaypointDrawStepFactory();
    RadarDrawStepFactory radarRenderer = new RadarDrawStepFactory();
    LayerDelegate layerDelegate;
    FullMapProperties fullMapProperties = Journeymap.getClient().getFullMapProperties();
    CoreProperties coreProperties = Journeymap.getClient().getCoreProperties();
    boolean firstLayoutPass = true;
    Boolean isScrolling = false;
    int msx;
    int msy;
    int mx;
    int my;
    Logger logger = Journeymap.getLogger();
    MapChat chat;
    ThemeButton buttonFollow;
    ThemeButton buttonZoomIn;
    ThemeButton buttonZoomOut;
    ThemeButton buttonSearch;
    TextBoxButton searchTextX;
    TextBoxButton searchTextZ;
    ThemeButton buttonExecuteSearch;
    ThemeToolbar searchToolBar;
    ThemeButton buttonDay;
    ThemeButton buttonNight;
    ThemeButton buttonTopo;
    ThemeButton buttonLayers;
    ThemeButton buttonCaves;
    ThemeButton buttonAlert;
    ThemeButton buttonOptions;
    ThemeButton buttonClose;
    ThemeButton buttonTheme;
    ThemeButton buttonWaypointManager;
    ThemeButton buttonMobs;
    ThemeButton buttonAnimals;
    ThemeButton buttonPets;
    ThemeButton buttonVillagers;
    ThemeButton buttonPlayers;
    ThemeButton buttonGrid;
    ThemeButton buttonKeys;
    ThemeButton buttonAutomap;
    ThemeButton buttonSavemap;
    ThemeButton buttonDeletemap;
    ThemeButton buttonDisable;
    ThemeButton buttonResetPalette;
    ThemeButton buttonBrowser;
    ThemeButton buttonAbout;
    ThemeButton overlayRenderButton;
    ThemeToolbar mapTypeToolbar;
    ThemeToolbar optionsToolbar;
    ThemeToolbar menuToolbar;
    ThemeToolbar zoomToolbar;
    int bgColor = 0x222222;
    Theme.LabelSpec statusLabelSpec;
    StatTimer drawScreenTimer = StatTimer.get("Fullscreen.drawScreen");
    StatTimer drawMapTimer = StatTimer.get("Fullscreen.drawScreen.drawMap", 50);
    StatTimer drawMapTimerWithRefresh = StatTimer.get("Fullscreen.drawMap+refreshState", 5);
    LocationFormat locationFormat = new LocationFormat();
    List<Overlay> tempOverlays = new ArrayList<Overlay>();
    private IntSliderButton sliderCaveLayer;
    private List<String> autoMapOnTooltip;
    private List<String> autoMapOffTooltip;
    private Rectangle2D.Double mapTypeToolbarBounds;
    private Rectangle2D.Double optionsToolbarBounds;
    private Rectangle2D.Double menuToolbarBounds;
    WaypointManagerRight manager;

    public Fullscreen() {
        super(null);
        this.field_146297_k = FMLClientHandler.instance().getClient();
        this.layerDelegate = new LayerDelegate(this);
        this.manager = new WaypointManagerRight();
        if (Journeymap.getClient().getFullMapProperties().showCaves.get().booleanValue() && DataCache.getPlayer().underground.booleanValue() && Fullscreen.state.follow.get() && FeatureManager.isAllowed(Feature.MapCaves)) {
            state.setMapType(MapType.underground(DataCache.getPlayer()));
        }
    }

    public static synchronized MapState state() {
        return state;
    }

    public static synchronized UIState uiState() {
        return gridRenderer.getUIState();
    }

    public void reset() {
        state.requireRefresh();
        gridRenderer.clear();
        this.field_146292_n.clear();
    }

    @Override
    public void func_73866_w_() {
        this.fullMapProperties = Journeymap.getClient().getFullMapProperties();
        state.requireRefresh();
        state.refresh(this.field_146297_k, (EntityPlayer)this.field_146297_k.field_71439_g, this.fullMapProperties);
        MapType mapType = state.getMapType();
        Keyboard.enableRepeatEvents((boolean)true);
        if (mapType.dimension != this.field_146297_k.field_71439_g.field_71093_bK) {
            gridRenderer.clear();
        }
        this.initButtons();
        String thisVersion = Journeymap.JM_VERSION.toString();
        String splashViewed = Journeymap.getClient().getCoreProperties().splashViewed.get();
        if (splashViewed == null || !thisVersion.equals(splashViewed)) {
            UIManager.INSTANCE.openSplash(this);
        }
        this.manager.func_73866_w_();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void func_73863_a(int width, int height, float f) {
        try {
            this.func_146278_c(0);
            this.drawMap();
            this.drawScreenTimer.start();
            this.layoutButtons();
            List<String> tooltip = null;
            if (this.firstLayoutPass) {
                this.layoutButtons();
                this.updateMapType(state.getMapType());
                this.firstLayoutPass = false;
            } else {
                for (int k = 0; k < this.field_146292_n.size(); ++k) {
                    Button button;
                    GuiButton guibutton = (GuiButton)this.field_146292_n.get(k);
                    guibutton.func_191745_a(this.field_146297_k, width, height, f);
                    if (tooltip != null || !(guibutton instanceof Button) || !(button = (Button)guibutton).mouseOver(this.mx, this.my)) continue;
                    tooltip = button.getTooltip();
                }
            }
            if (!WaypointStore.INSTANCE.getAll().isEmpty()) {
                Color c = new Color(34, 36, 54, 255);
                int c1 = c.getBlue() | c.getGreen() << 8 | c.getRed() << 16 | c.getAlpha() << 24;
                Fullscreen.func_73734_a((int)(this.field_146294_l - 174), (int)0, (int)this.field_146294_l, (int)this.field_146295_m, (int)c1);
                Fullscreen.func_73734_a((int)(this.field_146294_l - 176), (int)0, (int)(this.field_146294_l - 174), (int)this.field_146295_m, (int)-1);
                this.manager.func_73863_a(width, height, f);
            }
            if (this.chat != null) {
                this.chat.func_73863_a(width, height, f);
            }
            if (tooltip != null && !tooltip.isEmpty()) {
                this.drawHoveringText(tooltip, this.mx, this.my, this.getFontRenderer());
                RenderHelper.func_74518_a();
            }
        }
        catch (Throwable e) {
            this.logger.log(Level.ERROR, "Unexpected exception in jm.fullscreen.drawScreen(): " + LogFormatter.toString(e));
            UIManager.INSTANCE.closeAll();
        }
        finally {
            this.drawScreenTimer.stop();
        }
    }

    protected void func_146284_a(GuiButton guibutton) {
        if (guibutton instanceof ThemeToolbar) {
            return;
        }
        if (guibutton instanceof OnOffButton) {
            ((OnOffButton)guibutton).toggle();
        }
        if (this.optionsToolbar.contains(guibutton)) {
            this.refreshState();
        }
    }

    @Override
    public void func_146280_a(Minecraft minecraft, int width, int height) {
        super.func_146280_a(minecraft, width, height);
        state.requireRefresh();
        if (this.chat == null) {
            this.chat = new MapChat("", true);
        }
        if (this.chat != null) {
            this.chat.func_146280_a(minecraft, width, height);
        }
        this.func_73866_w_();
        this.refreshState();
        this.drawMap();
        this.manager.func_146280_a(minecraft, width, height);
    }

    void initButtons() {
        if (this.field_146292_n.isEmpty()) {
            this.firstLayoutPass = true;
            Theme theme = ThemeLoader.getCurrentTheme();
            MapType mapType = state.getMapType();
            this.bgColor = theme.fullscreen.background.getColor();
            this.statusLabelSpec = theme.fullscreen.statusLabel;
            this.buttonDay = new ThemeToggle(theme, "jm.fullscreen.map_day", "day");
            this.buttonNight = new ThemeToggle(theme, "jm.fullscreen.map_night", "night");
            this.buttonTopo = new ThemeToggle(theme, "jm.fullscreen.map_topo", "topo");
            this.buttonLayers = new ThemeToggle(theme, "jm.fullscreen.map_cave_layers", "layers");
            this.buttonDay.setToggled(mapType.isDay(), false);
            this.buttonDay.setStaysOn(true);
            this.buttonDay.addToggleListener((button, toggled) -> {
                if (button.field_146124_l) {
                    this.updateMapType(MapType.day(state.getDimension()));
                }
                return button.field_146124_l;
            });
            this.buttonNight.setToggled(mapType.isNight(), false);
            this.buttonNight.setStaysOn(true);
            this.buttonNight.addToggleListener((button, toggled) -> {
                if (button.field_146124_l) {
                    this.updateMapType(MapType.night(state.getDimension()));
                }
                return button.field_146124_l;
            });
            this.buttonTopo.setDrawButton(this.coreProperties.mapTopography.get());
            this.buttonTopo.setToggled(mapType.isTopo(), false);
            this.buttonTopo.setStaysOn(true);
            this.buttonTopo.addToggleListener((button, toggled) -> {
                if (button.field_146124_l) {
                    this.updateMapType(MapType.topo(state.getDimension()));
                }
                return button.field_146124_l;
            });
            this.buttonLayers.setEnabled(FeatureManager.isAllowed(Feature.MapCaves));
            this.buttonLayers.setToggled(mapType.isUnderground(), false);
            this.buttonLayers.setStaysOn(true);
            this.buttonLayers.addToggleListener((button, toggled) -> {
                if (button.field_146124_l) {
                    this.updateMapType(MapType.underground(DataCache.getPlayer()));
                }
                return button.field_146124_l;
            });
            FontRenderer fontRenderer = this.getFontRenderer();
            this.sliderCaveLayer = new IntSliderButton(state.getLastSlice(), Constants.getString("jm.fullscreen.map_cave_layers.button") + " ", "");
            this.sliderCaveLayer.func_175211_a(this.sliderCaveLayer.getFitWidth(fontRenderer) + fontRenderer.func_78256_a("0"));
            this.sliderCaveLayer.setDefaultStyle(false);
            this.sliderCaveLayer.setDrawBackground(true);
            Theme.Control.ButtonSpec buttonSpec = this.buttonLayers.getButtonSpec();
            this.sliderCaveLayer.setBackgroundColors(buttonSpec.buttonDisabled.getColor(), buttonSpec.buttonOff.getColor(), buttonSpec.buttonOff.getColor());
            this.sliderCaveLayer.setLabelColors(buttonSpec.iconHoverOff.getColor(), buttonSpec.iconHoverOn.getColor(), buttonSpec.iconDisabled.getColor());
            this.sliderCaveLayer.addClickListener(button -> {
                state.setMapType(MapType.underground(this.sliderCaveLayer.getValue(), state.getDimension()));
                this.refreshState();
                return Boolean.TRUE;
            });
            this.field_146292_n.add(this.sliderCaveLayer);
            this.buttonSearch = new ThemeButton(theme, "jm.fullscreen.search", "search");
            this.buttonSearch.addToggleListener((button, toggled) -> {
                this.toggleSearchBar(toggled);
                return true;
            });
            this.searchTextX = new TextBoxButton("x:", fontRenderer, 40, 20, true, true);
            this.searchTextZ = new TextBoxButton("z:", fontRenderer, 40, 20, true, true);
            this.buttonExecuteSearch = new ThemeButton(theme, "jm.fullscreen.search_execute", "follow");
            this.buttonExecuteSearch.addToggleListener((button, toggled) -> {
                this.executeSearch();
                return true;
            });
            this.searchTextX.setVisible(false);
            this.searchTextZ.setVisible(false);
            this.buttonExecuteSearch.setVisible(false);
            this.buttonFollow = new ThemeButton(theme, "jm.fullscreen.follow", "follow");
            this.buttonFollow.addToggleListener((button, toggled) -> {
                this.toggleFollow();
                return true;
            });
            ThemeButton buttonZoomIn = this.buttonZoomIn = new ThemeButton(theme, "jm.fullscreen.zoom_in", "zoomin");
            int intValue = this.fullMapProperties.zoomLevel.get();
            state.getClass();
            buttonZoomIn.setEnabled(intValue < 5);
            this.buttonZoomIn.addToggleListener((button, toggled) -> {
                this.zoomIn();
                return true;
            });
            this.buttonZoomIn.setDisplayClickToggle(false);
            ThemeButton buttonZoomOut = this.buttonZoomOut = new ThemeButton(theme, "jm.fullscreen.zoom_out", "zoomout");
            int intValue2 = this.fullMapProperties.zoomLevel.get();
            state.getClass();
            buttonZoomOut.setEnabled(intValue2 > 0);
            this.buttonZoomOut.addToggleListener((button, toggled) -> {
                this.zoomOut();
                return true;
            });
            this.buttonZoomOut.setDisplayClickToggle(false);
            this.buttonWaypointManager = new ThemeButton(theme, "jm.waypoint.waypoints_button", "waypoints");
            this.buttonWaypointManager.setDrawButton(WaypointsData.isManagerEnabled());
            this.buttonWaypointManager.addToggleListener((button, toggled) -> {
                UIManager.INSTANCE.openWaypointManager(null, this);
                return true;
            });
            this.buttonTheme = new ThemeButton(theme, "jm.common.ui_theme", "theme");
            this.buttonTheme.addToggleListener((button, toggled) -> {
                ThemeLoader.loadNextTheme();
                UIManager.INSTANCE.getMiniMap().reset();
                this.field_146292_n.clear();
                return false;
            });
            String[] tooltips = new String[]{TextFormatting.ITALIC + Constants.getString("jm.common.ui_theme_name", theme.name), TextFormatting.ITALIC + Constants.getString("jm.common.ui_theme_author", theme.author)};
            this.buttonTheme.setAdditionalTooltips(Arrays.asList(tooltips));
            this.buttonOptions = new ThemeButton(theme, "jm.common.options_button", "options");
            this.buttonOptions.addToggleListener((button, toggled) -> {
                try {
                    UIManager.INSTANCE.openOptionsManager();
                    this.field_146292_n.clear();
                    return true;
                }
                catch (Exception e) {
                    e.printStackTrace();
                    return false;
                }
            });
            String versionAvailable = Constants.getString("jm.common.new_version_available", VersionCheck.getVersionAvailable());
            this.buttonAlert = new ThemeButton(theme, versionAvailable, versionAvailable, false, "alert");
            this.buttonAlert.setDrawButton(VersionCheck.getVersionIsChecked() != false && VersionCheck.getVersionIsCurrent() == false);
            this.buttonAlert.setToggled(true);
            this.buttonAlert.addToggleListener((button, toggled) -> {
                FullscreenActions.launchDownloadWebsite();
                this.buttonAlert.setDrawButton(false);
                return true;
            });
            this.buttonClose = new ThemeButton(theme, "jm.common.close", "close");
            this.buttonClose.addToggleListener((button, toggled) -> {
                UIManager.INSTANCE.closeAll();
                return true;
            });
            this.buttonCaves = new ThemeToggle(theme, "jm.common.show_caves", "caves", this.fullMapProperties.showCaves);
            this.buttonCaves.setTooltip(Constants.getString("jm.common.show_caves.tooltip"));
            this.buttonCaves.setDrawButton(state.isCaveMappingAllowed());
            this.buttonCaves.addToggleListener((button, toggled) -> {
                EntityDTO player = DataCache.getPlayer();
                if (toggled && player.underground.booleanValue()) {
                    this.updateMapType(MapType.underground(player));
                }
                return true;
            });
            this.buttonMobs = new ThemeToggle(theme, "jm.common.show_mobs", "monsters", this.fullMapProperties.showMobs);
            this.buttonMobs.setTooltip(Constants.getString("jm.common.show_mobs.tooltip"));
            this.buttonMobs.setDrawButton(FeatureManager.isAllowed(Feature.RadarMobs));
            this.buttonAnimals = new ThemeToggle(theme, "jm.common.show_animals", "animals", this.fullMapProperties.showAnimals);
            this.buttonAnimals.setTooltip(Constants.getString("jm.common.show_animals.tooltip"));
            this.buttonAnimals.setDrawButton(FeatureManager.isAllowed(Feature.RadarAnimals));
            this.buttonPets = new ThemeToggle(theme, "jm.common.show_pets", "pets", this.fullMapProperties.showPets);
            this.buttonPets.setTooltip(Constants.getString("jm.common.show_pets.tooltip"));
            this.buttonPets.setDrawButton(FeatureManager.isAllowed(Feature.RadarAnimals));
            this.buttonVillagers = new ThemeToggle(theme, "jm.common.show_villagers", "villagers", this.fullMapProperties.showVillagers);
            this.buttonVillagers.setTooltip(Constants.getString("jm.common.show_villagers.tooltip"));
            this.buttonVillagers.setDrawButton(FeatureManager.isAllowed(Feature.RadarVillagers));
            this.buttonPlayers = new ThemeToggle(theme, "jm.common.show_players", "players", this.fullMapProperties.showPlayers);
            this.buttonPlayers.setTooltip(Constants.getString("jm.common.show_players.tooltip"));
            this.buttonPlayers.setDrawButton(!this.field_146297_k.func_71356_B() && FeatureManager.isAllowed(Feature.RadarPlayers));
            this.buttonGrid = new ThemeToggle(theme, "jm.common.show_grid", "grid", this.fullMapProperties.showGrid);
            this.buttonGrid.setTooltip(Constants.getString("jm.common.show_grid_shift.tooltip"));
            this.buttonGrid.setTooltip(Constants.getString("jm.common.show_grid_shift.tooltip"));
            this.buttonGrid.addToggleListener((button, toggled) -> {
                boolean shiftDown;
                boolean bl = shiftDown = Keyboard.isKeyDown((int)42) || Keyboard.isKeyDown((int)54);
                if (shiftDown) {
                    UIManager.INSTANCE.openGridEditor(this);
                    this.buttonGrid.setValue(true);
                    return false;
                }
                return true;
            });
            this.buttonKeys = new ThemeToggle(theme, "jm.common.show_keys", "keys", this.fullMapProperties.showKeys);
            this.buttonKeys.setTooltip(Constants.getString("jm.common.show_keys.tooltip"));
            this.buttonAbout = new ThemeButton(theme, "jm.common.splash_about", "about");
            this.buttonAbout.addToggleListener((button, toggled) -> {
                UIManager.INSTANCE.openSplash(this);
                return true;
            });
            this.overlayRenderButton = new ThemeButton(theme, "jm.debug.region_info_toggle", "server");
            this.overlayRenderButton.addToggleListener((button, toggled) -> {
                RegionRenderer.render(toggled);
                return true;
            });
            this.buttonSavemap = new ThemeButton(theme, "jm.common.save_map", "savemap");
            this.buttonSavemap.addToggleListener((button, toggled) -> {
                this.buttonSavemap.setEnabled(false);
                try {
                    MapSaver mapSaver = new MapSaver(state.getWorldDir(), state.getMapType());
                    if (mapSaver.isValid()) {
                        Journeymap.getClient().toggleTask(SaveMapTask.Manager.class, true, mapSaver);
                        ChatLog.announceI18N("jm.common.save_filename", mapSaver.getSaveFileName());
                    }
                }
                finally {
                    this.buttonSavemap.setToggled(false);
                    this.buttonSavemap.setEnabled(true);
                }
                return true;
            });
            this.buttonBrowser = new ThemeButton(theme, "jm.common.use_browser", "browser");
            boolean webMapEnabled = Journeymap.getClient().getWebMapProperties().enabled.get();
            this.buttonBrowser.setEnabled(webMapEnabled);
            this.buttonBrowser.setDrawButton(webMapEnabled);
            this.buttonBrowser.addToggleListener((button, toggled) -> {
                FullscreenActions.launchLocalhost();
                return true;
            });
            boolean automapRunning = Journeymap.getClient().isTaskManagerEnabled(MapRegionTask.Manager.class);
            String autoMapOn = Constants.getString("jm.common.automap_stop_title");
            String autoMapOff = Constants.getString("jm.common.automap_title");
            this.autoMapOnTooltip = fontRenderer.func_78271_c(Constants.getString("jm.common.automap_stop_text"), 200);
            this.autoMapOffTooltip = fontRenderer.func_78271_c(Constants.getString("jm.common.automap_text"), 200);
            this.buttonAutomap = new ThemeToggle(theme, autoMapOn, autoMapOff, "automap");
            this.buttonAutomap.setEnabled(FMLClientHandler.instance().getClient().func_71356_B() && Journeymap.getClient().getCoreProperties().mappingEnabled.get() != false);
            this.buttonAutomap.setToggled(automapRunning, false);
            this.buttonAutomap.addToggleListener((button, toggled) -> {
                if (toggled) {
                    UIManager.INSTANCE.open(AutoMapConfirmation.class, this);
                } else {
                    Journeymap.getClient().toggleTask(MapRegionTask.Manager.class, false, null);
                    this.buttonAutomap.setToggled(false, false);
                    this.field_146292_n.clear();
                }
                return true;
            });
            this.buttonDeletemap = new ThemeButton(theme, "jm.common.deletemap_title", "delete");
            this.buttonDeletemap.setAdditionalTooltips(fontRenderer.func_78271_c(Constants.getString("jm.common.deletemap_text"), 200));
            this.buttonDeletemap.addToggleListener((button, toggled) -> {
                UIManager.INSTANCE.open(DeleteMapConfirmation.class, this);
                return false;
            });
            this.buttonDisable = new ThemeToggle(theme, "jm.common.enable_mapping_false", "disable");
            this.buttonDisable.addToggleListener((button, toggled) -> {
                Journeymap.getClient().getCoreProperties().mappingEnabled.set(!toggled);
                if (Journeymap.getClient().getCoreProperties().mappingEnabled.get().booleanValue()) {
                    DataCache.INSTANCE.invalidateChunkMDCache();
                    ChatLog.announceI18N("jm.common.enable_mapping_true_text", new Object[0]);
                } else {
                    Journeymap.getClient().stopMapping();
                    BlockMD.reset();
                    ChatLog.announceI18N("jm.common.enable_mapping_false_text", new Object[0]);
                }
                return true;
            });
            this.buttonResetPalette = new ThemeButton(theme, "jm.common.colorreset_title", "reset");
            this.buttonResetPalette.setAdditionalTooltips(fontRenderer.func_78271_c(Constants.getString("jm.common.colorreset_text"), 200));
            this.buttonResetPalette.addToggleListener((button, toggled) -> {
                Journeymap.getClient().queueMainThreadTask(new EnsureCurrentColorsTask(true, true));
                return false;
            });
            this.mapTypeToolbar = new ThemeToolbar(theme, this.buttonTopo, this.buttonNight, this.buttonDay);
            this.mapTypeToolbar.addAllButtons(this);
            this.optionsToolbar = new ThemeToolbar(theme, new Button[0]);
            this.optionsToolbar.addAllButtons(this);
            this.optionsToolbar.field_146125_m = false;
            this.menuToolbar = new ThemeToolbar(theme, this.buttonWaypointManager, this.buttonOptions, this.overlayRenderButton);
            this.menuToolbar.addAllButtons(this);
            this.menuToolbar.field_146125_m = false;
            this.zoomToolbar = new ThemeToolbar(theme, this.buttonSearch, this.buttonFollow, this.buttonZoomIn, this.buttonZoomOut);
            this.zoomToolbar.setLayout(ButtonList.Layout.Vertical, ButtonList.Direction.LeftToRight);
            this.zoomToolbar.addAllButtons(this);
            this.searchToolBar = new ThemeToolbar(theme, this.searchTextX, this.searchTextZ, this.buttonExecuteSearch);
            this.searchToolBar.setLayout(ButtonList.Layout.CenteredHorizontal, ButtonList.Direction.LeftToRight);
            this.searchToolBar.addAllButtons(this);
        }
    }

    @Override
    protected void layoutButtons() {
        boolean showCaveLayers;
        if (this.buttonDay != null && !this.buttonDay.hasValidTextures()) {
            this.field_146292_n.clear();
        }
        if (this.field_146292_n.isEmpty()) {
            this.initButtons();
        }
        this.menuToolbar.setDrawToolbar(!this.isChatOpen());
        MapType mapType = state.getMapType();
        this.buttonDay.setEnabled(state.isSurfaceMappingAllowed());
        this.buttonDay.setToggled(this.buttonDay.field_146124_l && mapType.isDay());
        this.buttonNight.setEnabled(state.isSurfaceMappingAllowed());
        this.buttonNight.setToggled(this.buttonNight.field_146124_l && mapType.isNight());
        this.buttonTopo.setEnabled(state.isTopoMappingAllowed());
        this.buttonTopo.setToggled(this.buttonTopo.field_146124_l && mapType.isTopo());
        this.buttonCaves.setEnabled(state.isCaveMappingAllowed());
        this.buttonCaves.setToggled(this.buttonCaves.field_146124_l && mapType.isUnderground());
        this.buttonFollow.setEnabled(!Fullscreen.state.follow.get());
        boolean automapRunning = Journeymap.getClient().isTaskManagerEnabled(MapRegionTask.Manager.class);
        boolean mappingEnabled = Journeymap.getClient().getCoreProperties().mappingEnabled.get();
        this.buttonDisable.setToggled(!mappingEnabled, false);
        this.buttonAutomap.setToggled(automapRunning, false);
        this.buttonAutomap.setEnabled(mappingEnabled);
        this.buttonAutomap.setAdditionalTooltips(automapRunning ? this.autoMapOnTooltip : this.autoMapOffTooltip);
        boolean webMapEnabled = Journeymap.getClient().getWebMapProperties().enabled.get();
        this.buttonBrowser.setEnabled(webMapEnabled && mappingEnabled);
        this.buttonBrowser.setDrawButton(webMapEnabled);
        boolean mainThreadActive = Journeymap.getClient().isMainThreadTaskActive();
        this.buttonResetPalette.setEnabled(!mainThreadActive && mappingEnabled);
        this.buttonDeletemap.setEnabled(!mainThreadActive);
        this.buttonDisable.setEnabled(!mainThreadActive);
        this.overlayRenderButton.setVisible(System.getProperty("journeymap.map_testing") != null);
        int padding = this.mapTypeToolbar.getToolbarSpec().padding;
        this.zoomToolbar.layoutCenteredVertical(this.zoomToolbar.getHMargin(), this.field_146295_m / 2, true, padding);
        this.searchToolBar.layoutHorizontal(this.zoomToolbar.getRightX() + 2, this.zoomToolbar.getY() + 1, true, 7, true);
        this.searchTextX.setX(this.searchTextX.getX() + 3);
        this.searchTextZ.setX(this.searchTextZ.getX() + 2);
        this.buttonExecuteSearch.setDisplayClickToggle(false);
        int topY = this.mapTypeToolbar.getVMargin();
        int margin = this.mapTypeToolbar.getHMargin();
        this.buttonClose.leftOf(this.field_146294_l - this.zoomToolbar.getHMargin()).below(this.mapTypeToolbar.getVMargin());
        this.buttonAlert.leftOf(this.field_146294_l - this.zoomToolbar.getHMargin()).below(this.buttonClose, padding);
        int toolbarsWidth = this.mapTypeToolbar.getWidth() + this.optionsToolbar.getWidth() + margin + padding;
        int startX = (this.field_146294_l - toolbarsWidth) / 2;
        Rectangle2D.Double oldBounds = this.mapTypeToolbar.getBounds();
        this.mapTypeToolbar.layoutHorizontal(startX + this.mapTypeToolbar.getWidth(), topY, false, padding);
        if (!this.mapTypeToolbar.getBounds().equals(oldBounds)) {
            this.mapTypeToolbarBounds = null;
        }
        oldBounds = this.optionsToolbar.getBounds();
        this.optionsToolbar.layoutHorizontal(this.mapTypeToolbar.getRightX() + margin, topY, true, padding);
        this.optionsToolbar.field_146125_m = true;
        if (!this.optionsToolbar.getBounds().equals(oldBounds)) {
            this.optionsToolbarBounds = null;
        }
        oldBounds = this.menuToolbar.getBounds();
        this.menuToolbar.layoutCenteredHorizontal(this.field_146294_l / 2, this.field_146295_m - this.menuToolbar.field_146121_g - this.menuToolbar.getVMargin(), true, padding);
        if (!this.menuToolbar.getBounds().equals(oldBounds)) {
            this.menuToolbarBounds = null;
        }
        if (showCaveLayers = this.buttonLayers.getToggled().booleanValue()) {
            Rectangle2D.Double bounds = this.getMapTypeToolbarBounds();
            if (bounds != null) {
                boolean alreadyVisible = this.sliderCaveLayer.isVisible() && Mouse.isButtonDown((int)0);
                this.sliderCaveLayer.setDrawButton(alreadyVisible || bounds.contains(this.mx, this.my));
            }
        } else {
            this.sliderCaveLayer.setDrawButton(false);
        }
        if (this.sliderCaveLayer.isVisible()) {
            this.sliderCaveLayer.below(this.buttonLayers, 1).centerHorizontalOn(this.buttonLayers.getCenterX());
            int slice = this.sliderCaveLayer.getValue();
            int minY = Math.max(slice << 4, 0);
            int maxY = (slice + 1 << 4) - 1;
            this.sliderCaveLayer.setTooltip(Constants.getString("jm.fullscreen.map_cave_layers.button.tooltip", minY, maxY));
        }
    }

    @Nullable
    public Rectangle2D.Double getOptionsToolbarBounds() {
        if (this.optionsToolbar != null && this.optionsToolbar.isVisible()) {
            Rectangle2D.Double unscaled = this.optionsToolbar.getBounds();
            this.optionsToolbarBounds = new Rectangle2D.Double(unscaled.x * (double)this.scaleFactor, unscaled.y * (double)this.scaleFactor, unscaled.width * (double)this.scaleFactor, unscaled.height * (double)this.scaleFactor);
        }
        return this.optionsToolbarBounds;
    }

    @Nullable
    public Rectangle2D.Double getMenuToolbarBounds() {
        if (this.menuToolbar != null && this.menuToolbar.isVisible()) {
            Rectangle2D.Double unscaled = this.menuToolbar.getBounds();
            this.menuToolbarBounds = new Rectangle2D.Double(unscaled.x * (double)this.scaleFactor, unscaled.y * (double)this.scaleFactor, unscaled.width * (double)this.scaleFactor, unscaled.height * (double)this.scaleFactor);
        }
        return this.menuToolbarBounds;
    }

    @Nullable
    public Rectangle2D.Double getMapTypeToolbarBounds() {
        if (this.mapTypeToolbar != null && this.mapTypeToolbar.isVisible()) {
            Rectangle2D.Double unscaled = this.mapTypeToolbar.getBounds();
            this.mapTypeToolbarBounds = new Rectangle2D.Double(unscaled.x * (double)this.scaleFactor, unscaled.y * (double)this.scaleFactor, unscaled.width * (double)this.scaleFactor, unscaled.height * (double)this.scaleFactor);
            this.mapTypeToolbarBounds.add(this.sliderCaveLayer.getBounds());
        }
        return this.mapTypeToolbarBounds;
    }

    public void func_146274_d() throws IOException {
        try {
            boolean empty = WaypointStore.INSTANCE.getAll().isEmpty();
            if (!empty) {
                this.manager.func_146274_d();
            }
            if (this.chat != null && !this.chat.isHidden()) {
                this.chat.func_146274_d();
            }
            this.mx = Mouse.getEventX() * this.field_146294_l / this.field_146297_k.field_71443_c;
            this.my = this.field_146295_m - Mouse.getEventY() * this.field_146295_m / this.field_146297_k.field_71440_d - 1;
            if (!empty && this.mx > this.field_146294_l - 170) {
                return;
            }
            if (Mouse.getEventButtonState()) {
                this.func_73864_a(this.mx, this.my, Mouse.getEventButton());
            } else {
                int wheel = Mouse.getEventDWheel();
                if (wheel > 0) {
                    this.zoomIn();
                } else if (wheel < 0) {
                    this.zoomOut();
                } else {
                    this.func_146286_b(this.mx, this.my, Mouse.getEventButton());
                }
            }
        }
        catch (Throwable t) {
            Journeymap.getLogger().error(LogFormatter.toPartialString(t));
        }
    }

    protected void func_73864_a(int mouseX, int mouseY, int mouseButton) throws IOException {
        try {
            if (this.chat != null && !this.chat.isHidden()) {
                this.chat.func_73864_a(mouseX, mouseY, mouseButton);
            }
            super.func_73864_a(mouseX, mouseY, mouseButton);
            Point2D.Double mousePosition = new Point2D.Double(Mouse.getEventX(), gridRenderer.getHeight() - Mouse.getEventY());
            if (mouseButton == 1) {
                this.layerDelegate.onMouseClicked(this.field_146297_k, gridRenderer, mousePosition, mouseButton, this.getMapFontScale());
            }
        }
        catch (Throwable t) {
            Journeymap.getLogger().error(LogFormatter.toPartialString(t));
        }
    }

    @Override
    protected void func_146286_b(int mouseX, int mouseY, int which) {
        try {
            super.func_146286_b(mouseX, mouseY, which);
            if (this.isMouseOverButton(mouseX, mouseY) || this.sliderCaveLayer.isVisible()) {
                return;
            }
            int blockSize = (int)Math.pow(2.0, this.fullMapProperties.zoomLevel.get().intValue());
            if (Mouse.isButtonDown((int)0) && !this.isScrolling.booleanValue()) {
                this.isScrolling = true;
                this.msx = this.mx;
                this.msy = this.my;
            } else if (!Mouse.isButtonDown((int)0) && this.isScrolling.booleanValue() && !this.isMouseOverButton(this.msx, this.msy)) {
                this.isScrolling = false;
                int mouseDragX = (this.mx - this.msx) * Math.max(1, this.scaleFactor) / blockSize;
                int mouseDragY = (this.my - this.msy) * Math.max(1, this.scaleFactor) / blockSize;
                this.msx = this.mx;
                this.msy = this.my;
                try {
                    gridRenderer.move(-mouseDragX, -mouseDragY);
                    gridRenderer.updateTiles(state.getMapType(), state.getZoom(), state.isHighQuality(), this.field_146297_k.field_71443_c, this.field_146297_k.field_71440_d, false, 0.0, 0.0);
                    gridRenderer.setZoom(this.fullMapProperties.zoomLevel.get());
                }
                catch (Exception e) {
                    this.logger.error("Error moving grid: " + e);
                }
                this.setFollow(false);
                this.refreshState();
            }
            Point2D.Double mousePosition = new Point2D.Double(Mouse.getEventX(), gridRenderer.getHeight() - Mouse.getEventY());
            this.layerDelegate.onMouseMove(this.field_146297_k, gridRenderer, mousePosition, this.getMapFontScale(), this.isScrolling);
        }
        catch (Throwable t) {
            Journeymap.getLogger().error(LogFormatter.toPartialString(t));
        }
    }

    public void toggleMapType() {
        this.updateMapType(state.toggleMapType());
    }

    private void updateMapType(MapType newType) {
        if (!newType.isAllowed()) {
            newType = state.getMapType();
        }
        state.setMapType(newType);
        this.buttonDay.setToggled(newType.isDay(), false);
        this.buttonNight.setToggled(newType.isNight(), false);
        this.buttonTopo.setToggled(newType.isTopo(), false);
        this.buttonLayers.setToggled(newType.isUnderground(), false);
        if (newType.isUnderground()) {
            this.sliderCaveLayer.setValue(newType.vSlice);
        }
        state.requireRefresh();
    }

    public void zoomIn() {
        int intValue = this.fullMapProperties.zoomLevel.get();
        state.getClass();
        if (intValue < 5) {
            this.setZoom(this.fullMapProperties.zoomLevel.get() + 1);
        }
    }

    public void zoomOut() {
        int intValue = this.fullMapProperties.zoomLevel.get();
        state.getClass();
        if (intValue > 0) {
            this.setZoom(this.fullMapProperties.zoomLevel.get() - 1);
        }
    }

    private void setZoom(int zoom) {
        if (state.setZoom(zoom)) {
            ThemeButton buttonZoomOut = this.buttonZoomOut;
            int intValue = this.fullMapProperties.zoomLevel.get();
            state.getClass();
            buttonZoomOut.setEnabled(intValue > 0);
            ThemeButton buttonZoomIn = this.buttonZoomIn;
            int intValue2 = this.fullMapProperties.zoomLevel.get();
            state.getClass();
            buttonZoomIn.setEnabled(intValue2 < 5);
            this.refreshState();
        }
    }

    void toggleSearchBar(boolean toggled) {
        this.searchToolBar.setEnabled(toggled);
        this.searchToolBar.setVisible(toggled);
        this.searchTextZ.setVisible(toggled);
        this.searchTextX.setVisible(toggled);
        this.buttonExecuteSearch.setVisible(toggled);
    }

    void executeSearch() {
        this.buttonExecuteSearch.setToggled(true, false);
        try {
            int x = Integer.parseInt(this.searchTextX.getText());
            int z = Integer.parseInt(this.searchTextZ.getText());
            this.centerOn(x, z);
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    void toggleFollow() {
        boolean isFollow = !Fullscreen.state.follow.get();
        this.setFollow(isFollow);
        if (isFollow && this.field_146297_k.field_71439_g != null) {
            this.sliderCaveLayer.setValue(this.field_146297_k.field_71439_g.field_70162_ai);
            if (state.getMapType().isUnderground()) {
                this.sliderCaveLayer.checkClickListeners();
            }
        }
    }

    void setFollow(Boolean follow) {
        Fullscreen.state.follow.set(follow);
        if (follow.booleanValue()) {
            state.resetMapType();
            this.refreshState();
        }
    }

    public void createWaypointAtMouse() {
        Point2D.Double mousePosition = new Point2D.Double(Mouse.getEventX(), gridRenderer.getHeight() - Mouse.getEventY());
        BlockPos blockPos = this.layerDelegate.getBlockPos(this.field_146297_k, gridRenderer, mousePosition);
        Waypoint waypoint = Waypoint.at(blockPos, Waypoint.Type.Normal, this.field_146297_k.field_71439_g.field_71093_bK);
        UIManager.INSTANCE.openWaypointEditor(waypoint, true, this);
    }

    public void chatPositionAtMouse() {
        Point2D.Double mousePosition = new Point2D.Double(Mouse.getEventX(), gridRenderer.getHeight() - Mouse.getEventY());
        BlockPos blockPos = this.layerDelegate.getBlockPos(this.field_146297_k, gridRenderer, mousePosition);
        Waypoint waypoint = Waypoint.at(blockPos, Waypoint.Type.Normal, state.getDimension());
        this.openChat(waypoint.toChatString());
    }

    public boolean isChatOpen() {
        return this.chat != null && !this.chat.isHidden();
    }

    @Override
    public void func_73869_a(char c, int key) throws IOException {
        String oldText = this.manager.nameField.func_146179_b();
        this.manager.nameField.func_146201_a(c, key);
        String newText = this.manager.nameField.func_146179_b();
        if (!oldText.equals(newText)) {
            this.manager.filterName = newText;
            this.manager.items.clear();
            this.manager.func_73866_w_();
        }
        if (this.searchTextX.func_146115_a()) {
            this.searchTextX.keyTyped(c, key);
            return;
        }
        if (this.searchTextZ.func_146115_a()) {
            this.searchTextZ.keyTyped(c, key);
            return;
        }
        if (this.isChatOpen()) {
            this.chat.func_73869_a(c, key);
            return;
        }
        if (this.field_146297_k.field_71474_y.field_74310_D.func_151463_i() == key) {
            this.openChat("");
            return;
        }
        if (this.field_146297_k.field_71474_y.field_74323_J.func_151463_i() == key) {
            this.openChat("/");
            return;
        }
        if (1 == key) {
            UIManager.INSTANCE.closeAll();
        }
    }

    public void func_73876_c() {
        super.func_73876_c();
        this.manager.func_73876_c();
        if (this.chat != null) {
            this.chat.func_73876_c();
        }
    }

    @Override
    public void func_146278_c(int layer) {
        DrawUtil.drawRectangle(0.0, 0.0, this.field_146294_l, this.field_146295_m, this.bgColor, 1.0f);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    void drawMap() {
        boolean refreshReady = this.isRefreshReady();
        StatTimer timer = refreshReady ? this.drawMapTimerWithRefresh : this.drawMapTimer;
        MapType mapType = state.getMapType();
        timer.start();
        try {
            Point2D.Double playerPixel;
            this.sizeDisplay(false);
            int xOffset = 0;
            int yOffset = 0;
            if (this.isScrolling.booleanValue()) {
                int blockSize = (int)Math.pow(2.0, this.fullMapProperties.zoomLevel.get().intValue());
                int mouseDragX = (this.mx - this.msx) * Math.max(1, this.scaleFactor) / blockSize;
                int mouseDragY = (this.my - this.msy) * Math.max(1, this.scaleFactor) / blockSize;
                xOffset = mouseDragX * blockSize;
                yOffset = mouseDragY * blockSize;
            } else if (refreshReady) {
                this.refreshState();
            } else {
                gridRenderer.setContext(state.getWorldDir(), mapType);
            }
            gridRenderer.clearGlErrors(false);
            gridRenderer.updateRotation(0.0);
            if (Fullscreen.state.follow.get()) {
                gridRenderer.center(state.getWorldDir(), mapType, this.field_146297_k.field_71439_g.field_70165_t, this.field_146297_k.field_71439_g.field_70161_v, this.fullMapProperties.zoomLevel.get());
            }
            gridRenderer.updateTiles(mapType, state.getZoom(), state.isHighQuality(), this.field_146297_k.field_71443_c, this.field_146297_k.field_71440_d, false, 0.0, 0.0);
            gridRenderer.draw(1.0f, xOffset, yOffset, this.fullMapProperties.showGrid.get());
            gridRenderer.draw(state.getDrawSteps(), (double)xOffset, (double)yOffset, (double)this.getMapFontScale(), 0.0);
            gridRenderer.draw(state.getDrawWaypointSteps(), (double)xOffset, (double)yOffset, (double)this.getMapFontScale(), 0.0);
            if (this.fullMapProperties.showSelf.get().booleanValue() && (playerPixel = gridRenderer.getPixel(this.field_146297_k.field_71439_g.field_70165_t, this.field_146297_k.field_71439_g.field_70161_v)) != null) {
                boolean large = ((EntityDisplay)this.fullMapProperties.playerDisplay.get()).isLarge();
                TextureImpl bgTex = large ? TextureCache.getTexture(TextureCache.PlayerArrowBG_Large) : TextureCache.getTexture(TextureCache.PlayerArrowBG);
                TextureImpl fgTex = large ? TextureCache.getTexture(TextureCache.PlayerArrow_Large) : TextureCache.getTexture(TextureCache.PlayerArrow);
                DrawUtil.drawColoredEntity(((Point2D)playerPixel).getX() + (double)xOffset, ((Point2D)playerPixel).getY() + (double)yOffset, bgTex, 0xFFFFFF, 1.0f, 1.0f, this.field_146297_k.field_71439_g.field_70759_as);
                int playerColor = this.coreProperties.getColor(this.coreProperties.colorSelf);
                DrawUtil.drawColoredEntity(((Point2D)playerPixel).getX() + (double)xOffset, ((Point2D)playerPixel).getY() + (double)yOffset, fgTex, playerColor, 1.0f, 1.0f, this.field_146297_k.field_71439_g.field_70759_as);
            }
            gridRenderer.draw(this.layerDelegate.getDrawSteps(), (double)xOffset, (double)yOffset, (double)this.getMapFontScale(), 0.0);
            this.drawLogo();
            this.sizeDisplay(true);
        }
        finally {
            timer.stop();
            gridRenderer.clearGlErrors(true);
        }
    }

    private int getMapFontScale() {
        return this.fullMapProperties.fontScale.get();
    }

    public void centerOn(Waypoint waypoint) {
        if (waypoint.getDimensions().contains(this.field_146297_k.field_71439_g.field_71093_bK)) {
            if (!waypoint.isPersistent()) {
                this.addTempMarker(waypoint);
            }
            this.centerOn(waypoint.getX(), waypoint.getZ());
        }
    }

    public void centerOn(int x, int z) {
        Fullscreen.state.follow.set(false);
        state.requireRefresh();
        gridRenderer.center(state.getWorldDir(), state.getMapType(), x, z, this.fullMapProperties.zoomLevel.get());
        this.refreshState();
        this.func_73876_c();
    }

    public void addTempMarker(Waypoint waypoint) {
        try {
            BlockPos pos = waypoint.getBlockPos();
            PolygonOverlay polygonOverlay = new PolygonOverlay("journeymap", waypoint.getName(), this.field_146297_k.field_71439_g.field_71093_bK, new ShapeProperties().setStrokeColor(255).setStrokeOpacity(1.0f).setStrokeWidth(1.5f), new MapPolygon(pos.func_177982_a(-1, 0, 2), pos.func_177982_a(2, 0, 2), pos.func_177982_a(2, 0, -1), pos.func_177982_a(-1, 0, -1)));
            polygonOverlay.setActiveMapTypes(EnumSet.allOf(Context.MapType.class));
            polygonOverlay.setActiveUIs(EnumSet.of(Context.UI.Fullscreen));
            polygonOverlay.setLabel(waypoint.getName());
            this.tempOverlays.add(polygonOverlay);
            ClientAPI.INSTANCE.show(polygonOverlay);
        }
        catch (Throwable t) {
            Journeymap.getLogger().error("Error showing temp location marker: " + LogFormatter.toPartialString(t));
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    void refreshState() {
        EntityPlayerSP player = this.field_146297_k.field_71439_g;
        if (player == null) {
            this.logger.warn("Could not get player");
            return;
        }
        StatTimer timer = StatTimer.get("Fullscreen.refreshState");
        timer.start();
        try {
            this.menuToolbarBounds = null;
            this.optionsToolbarBounds = null;
            this.fullMapProperties = Journeymap.getClient().getFullMapProperties();
            state.refresh(this.field_146297_k, (EntityPlayer)player, this.fullMapProperties);
            MapType mapType = state.getMapType();
            gridRenderer.setContext(state.getWorldDir(), mapType);
            if (Fullscreen.state.follow.get()) {
                gridRenderer.center(state.getWorldDir(), mapType, this.field_146297_k.field_71439_g.field_70165_t, this.field_146297_k.field_71439_g.field_70161_v, this.fullMapProperties.zoomLevel.get());
            } else {
                gridRenderer.setZoom(this.fullMapProperties.zoomLevel.get());
            }
            gridRenderer.updateTiles(mapType, state.getZoom(), state.isHighQuality(), this.field_146297_k.field_71443_c, this.field_146297_k.field_71440_d, true, 0.0, 0.0);
            state.generateDrawSteps(this.field_146297_k, gridRenderer, this.waypointRenderer, this.radarRenderer, this.fullMapProperties, false);
            LocationFormat.LocationFormatKeys locationFormatKeys = this.locationFormat.getFormatKeys(this.fullMapProperties.locationFormat.get());
            Fullscreen.state.playerLastPos = locationFormatKeys.format(this.fullMapProperties.locationFormatVerbose.get(), MathHelper.func_76128_c((double)this.field_146297_k.field_71439_g.field_70165_t), MathHelper.func_76128_c((double)this.field_146297_k.field_71439_g.field_70161_v), MathHelper.func_76128_c((double)this.field_146297_k.field_71439_g.func_174813_aQ().field_72338_b), this.field_146297_k.field_71439_g.field_70162_ai) + " " + state.getPlayerBiome();
            state.updateLastRefresh();
        }
        finally {
            timer.stop();
        }
        Point2D.Double mousePosition = new Point2D.Double(Mouse.getEventX(), gridRenderer.getHeight() - Mouse.getEventY());
        this.layerDelegate.onMouseMove(this.field_146297_k, gridRenderer, mousePosition, this.getMapFontScale(), this.isScrolling);
    }

    public void openChat(String defaultText) {
        if (this.chat != null) {
            this.chat.setText(defaultText);
            this.chat.setHidden(false);
        } else {
            this.chat = new MapChat(defaultText, false);
            this.chat.func_146280_a(this.field_146297_k, this.field_146294_l, this.field_146295_m);
        }
    }

    @Override
    public void close() {
        for (Overlay temp : this.tempOverlays) {
            ClientAPI.INSTANCE.remove(temp);
        }
        gridRenderer.updateUIState(false);
        if (this.chat != null) {
            this.chat.close();
        }
        this.manager.close();
    }

    public void func_146281_b() {
        Keyboard.enableRepeatEvents((boolean)false);
    }

    boolean isRefreshReady() {
        return this.isScrolling == false && (state.shouldRefresh(this.field_146297_k, this.fullMapProperties) || gridRenderer.hasUnloadedTile());
    }

    public int getScreenScaleFactor() {
        return this.scaleFactor;
    }

    public void moveCanvas(int deltaBlockX, int deltaBlockz) {
        this.refreshState();
        gridRenderer.move(deltaBlockX, deltaBlockz);
        gridRenderer.updateTiles(state.getMapType(), state.getZoom(), state.isHighQuality(), this.field_146297_k.field_71443_c, this.field_146297_k.field_71440_d, true, 0.0, 0.0);
        ClientAPI.INSTANCE.flagOverlaysForRerender();
        this.setFollow(false);
    }

    public void showCaveLayers() {
        if (!state.isUnderground()) {
            this.updateMapType(MapType.underground(3, state.getDimension()));
        }
    }

    @Override
    protected void drawLogo() {
        if (this.logo.isDefunct()) {
            this.logo = TextureCache.getTexture(TextureCache.Logo);
        }
        DrawUtil.sizeDisplay(this.field_146297_k.field_71443_c, this.field_146297_k.field_71440_d);
        Theme.Container.Toolbar toolbar = ThemeLoader.getCurrentTheme().container.toolbar;
        float scale = this.scaleFactor * 2;
        DrawUtil.sizeDisplay(this.field_146294_l, this.field_146295_m);
        DrawUtil.drawImage(this.logo, toolbar.horizontal.margin, toolbar.vertical.margin, false, 1.0f / scale, 0.0);
    }

    @Override
    public boolean func_73868_f() {
        return false;
    }

    public void setTheme(String name) {
        try {
            MiniMapProperties mmp = Journeymap.getClient().getMiniMapProperties(Journeymap.getClient().getActiveMinimapId());
            mmp.shape.set(Shape.Rectangle);
            mmp.sizePercent.set(20);
            mmp.save();
            Theme theme = ThemeLoader.getThemeByName(name);
            ThemeLoader.setCurrentTheme(theme);
            UIManager.INSTANCE.getMiniMap().reset();
            ChatLog.announceI18N("jm.common.ui_theme_applied", new Object[0]);
            UIManager.INSTANCE.closeAll();
        }
        catch (Exception e) {
            Journeymap.getLogger().error("Could not load Theme: " + LogFormatter.toString(e));
        }
    }

    public void func_184072_a(String ... newCompletions) {
        this.chat.func_184072_a(newCompletions);
    }
}

