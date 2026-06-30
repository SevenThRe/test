/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ListMultimap
 *  com.google.common.collect.Multimap
 *  com.google.common.collect.MultimapBuilder
 *  javax.annotation.ParametersAreNonnullByDefault
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.settings.KeyBinding
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraftforge.client.event.GuiScreenEvent$KeyboardInputEvent$Post
 *  net.minecraftforge.client.event.GuiScreenEvent$MouseInputEvent$Post
 *  net.minecraftforge.client.settings.IKeyConflictContext
 *  net.minecraftforge.client.settings.KeyConflictContext
 *  net.minecraftforge.client.settings.KeyModifier
 *  net.minecraftforge.fml.client.FMLClientHandler
 *  net.minecraftforge.fml.client.registry.ClientRegistry
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.gameevent.InputEvent$KeyInputEvent
 *  org.apache.logging.log4j.Logger
 *  org.lwjgl.input.Keyboard
 *  org.lwjgl.input.Mouse
 */
package journeymap.client.forge.event;

import com.google.common.collect.ListMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.MultimapBuilder;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.ParametersAreNonnullByDefault;
import journeymap.client.Constants;
import journeymap.client.forge.event.EventHandlerManager;
import journeymap.client.log.ChatLog;
import journeymap.client.model.Waypoint;
import journeymap.client.ui.UIManager;
import journeymap.client.ui.dialog.OptionsManager;
import journeymap.client.ui.fullscreen.Fullscreen;
import journeymap.client.ui.minimap.MiniMap;
import journeymap.client.ui.waypoint.WaypointManager;
import journeymap.common.Journeymap;
import journeymap.common.log.LogFormatter;
import journeymap.common.properties.Category;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.client.settings.IKeyConflictContext;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.client.settings.KeyModifier;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import org.apache.logging.log4j.Logger;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

@ParametersAreNonnullByDefault
public enum KeyEventHandler implements EventHandlerManager.EventHandler
{
    INSTANCE;

    public KeyBinding kbMapZoomin;
    public KeyBinding kbMapZoomout;
    public KeyBinding kbMapToggleType;
    public KeyBinding kbCreateWaypoint;
    public KeyBinding kbToggleAllWaypoints;
    public KeyBinding kbFullscreenCreateWaypoint;
    public KeyBinding kbFullscreenChatPosition;
    public KeyBinding kbFullscreenToggle;
    public KeyBinding kbWaypointManager;
    public KeyBinding kbMinimapToggle;
    public KeyBinding kbMinimapPreset;
    public KeyBinding kbFullmapOptionsManager;
    public KeyBinding kbFullmapPanNorth;
    public KeyBinding kbFullmapPanSouth;
    public KeyBinding kbFullmapPanEast;
    public KeyBinding kbFullmapPanWest;
    private Comparator<KeyBindingAction> kbaComparator = Comparator.comparingInt(KeyBindingAction::order);
    private final ListMultimap<Integer, KeyBindingAction> minimapPreviewActions = MultimapBuilder.hashKeys().arrayListValues(2).build();
    private final ListMultimap<Integer, KeyBindingAction> inGameActions = MultimapBuilder.hashKeys().arrayListValues(2).build();
    private final ListMultimap<Integer, KeyBindingAction> inGuiActions = MultimapBuilder.hashKeys().arrayListValues(2).build();
    private Minecraft mc = FMLClientHandler.instance().getClient();
    private boolean sortActionsNeeded = true;
    private Logger logger = Journeymap.getLogger();

    private KeyEventHandler() {
        this.kbMapZoomin = this.register("key.journeymap.zoom_in", (IKeyConflictContext)KeyConflictContext.UNIVERSAL, KeyModifier.NONE, 13);
        this.setAction(this.minimapPreviewActions, this.kbMapZoomin, () -> MiniMap.state().zoomIn());
        this.setAction(this.inGuiActions, this.kbMapZoomin, () -> this.getFullscreen().zoomIn());
        this.kbMapZoomout = this.register("key.journeymap.zoom_out", (IKeyConflictContext)KeyConflictContext.UNIVERSAL, KeyModifier.NONE, 12);
        this.setAction(this.minimapPreviewActions, this.kbMapZoomout, () -> MiniMap.state().zoomOut());
        this.setAction(this.inGuiActions, this.kbMapZoomout, () -> this.getFullscreen().zoomOut());
        this.kbMapToggleType = this.register("key.journeymap.minimap_type", (IKeyConflictContext)KeyConflictContext.UNIVERSAL, KeyModifier.NONE, 26);
        this.setAction(this.minimapPreviewActions, this.kbMapToggleType, () -> MiniMap.state().toggleMapType());
        this.setAction(this.inGuiActions, this.kbMapToggleType, () -> this.getFullscreen().toggleMapType());
        this.kbMinimapPreset = this.register("key.journeymap.minimap_preset", (IKeyConflictContext)KeyConflictContext.IN_GAME, KeyModifier.NONE, 43);
        this.setAction(this.minimapPreviewActions, this.kbMinimapPreset, UIManager.INSTANCE::switchMiniMapPreset);
        this.inGameActions.putAll(this.minimapPreviewActions);
        this.kbCreateWaypoint = this.register("key.journeymap.create_waypoint", (IKeyConflictContext)KeyConflictContext.IN_GAME, KeyModifier.NONE, 48);
        this.setAction(this.inGameActions, this.kbCreateWaypoint, () -> UIManager.INSTANCE.openWaypointEditor(Waypoint.of((EntityPlayer)this.mc.player), true, null));
        this.kbToggleAllWaypoints = this.register("key.journeymap.toggle_waypoints", (IKeyConflictContext)KeyConflictContext.IN_GAME, KeyModifier.NONE, 44);
        this.setAction(this.inGameActions, this.kbToggleAllWaypoints, () -> WaypointManager.toggleAllWaypoints());
        this.kbFullscreenCreateWaypoint = this.register("key.journeymap.fullscreen_create_waypoint", (IKeyConflictContext)KeyConflictContext.GUI, KeyModifier.NONE, 48);
        this.setAction(this.inGuiActions, this.kbFullscreenCreateWaypoint, () -> this.getFullscreen().createWaypointAtMouse());
        this.kbFullscreenChatPosition = this.register("key.journeymap.fullscreen_chat_position", (IKeyConflictContext)KeyConflictContext.GUI, KeyModifier.NONE, 46);
        this.setAction(this.inGuiActions, this.kbFullscreenChatPosition, () -> this.getFullscreen().chatPositionAtMouse());
        this.kbFullscreenToggle = this.register("key.journeymap.map_toggle_alt", (IKeyConflictContext)KeyConflictContext.UNIVERSAL, KeyModifier.NONE, 36);
        this.setAction(this.inGameActions, this.kbFullscreenToggle, UIManager.INSTANCE::openFullscreenMap);
        this.setAction(this.inGuiActions, this.kbFullscreenToggle, UIManager.INSTANCE::closeAll);
        this.kbWaypointManager = this.register("key.journeymap.fullscreen_waypoints", (IKeyConflictContext)KeyConflictContext.UNIVERSAL, KeyModifier.CONTROL, 48);
        this.setAction(this.inGameActions, this.kbWaypointManager, () -> UIManager.INSTANCE.openWaypointManager(null, null));
        this.setAction(this.inGuiActions, this.kbWaypointManager, () -> UIManager.INSTANCE.openWaypointManager(null, this.getFullscreen()));
        this.kbMinimapToggle = this.register("key.journeymap.minimap_toggle_alt", (IKeyConflictContext)KeyConflictContext.IN_GAME, KeyModifier.CONTROL, 36);
        this.setAction(this.inGameActions, this.kbMinimapToggle, UIManager.INSTANCE::toggleMinimap);
        this.kbFullmapOptionsManager = this.register("key.journeymap.fullscreen_options", (IKeyConflictContext)KeyConflictContext.GUI, KeyModifier.NONE, 24);
        this.setAction(this.inGuiActions, this.kbFullmapOptionsManager, () -> UIManager.INSTANCE.openOptionsManager(this.getFullscreen(), new Category[0]));
        this.kbFullmapPanNorth = this.register("key.journeymap.fullscreen.north", (IKeyConflictContext)KeyConflictContext.GUI, KeyModifier.NONE, 200);
        this.setAction(this.inGuiActions, this.kbFullmapPanNorth, () -> this.getFullscreen().moveCanvas(0, -16));
        this.kbFullmapPanSouth = this.register("key.journeymap.fullscreen.south", (IKeyConflictContext)KeyConflictContext.GUI, KeyModifier.NONE, 208);
        this.setAction(this.inGuiActions, this.kbFullmapPanSouth, () -> this.getFullscreen().moveCanvas(0, 16));
        this.kbFullmapPanEast = this.register("key.journeymap.fullscreen.east", (IKeyConflictContext)KeyConflictContext.GUI, KeyModifier.NONE, 205);
        this.setAction(this.inGuiActions, this.kbFullmapPanEast, () -> this.getFullscreen().moveCanvas(16, 0));
        this.kbFullmapPanWest = this.register("key.journeymap.fullscreen.west", (IKeyConflictContext)KeyConflictContext.GUI, KeyModifier.NONE, 203);
        this.setAction(this.inGuiActions, this.kbFullmapPanWest, () -> this.getFullscreen().moveCanvas(-16, 0));
    }

    private void setAction(ListMultimap<Integer, KeyBindingAction> multimap, KeyBinding keyBinding, Runnable action) {
        multimap.put((Object)keyBinding.getKeyCode(), (Object)new KeyBindingAction(keyBinding, action));
    }

    private KeyBinding register(String description, IKeyConflictContext keyConflictContext, KeyModifier keyModifier, int keyCode) {
        String category = keyConflictContext == KeyConflictContext.GUI ? Constants.getString("jm.common.hotkeys_keybinding_fullscreen_category") : Constants.getString("jm.common.hotkeys_keybinding_category");
        UpdateAwareKeyBinding kb = new UpdateAwareKeyBinding(description, keyConflictContext, keyModifier, keyCode, category);
        try {
            ClientRegistry.registerKeyBinding((KeyBinding)kb);
        }
        catch (Throwable t) {
            ChatLog.announceError("Unexpected error when registering keybinding : " + (Object)((Object)kb));
        }
        return kb;
    }

    @SubscribeEvent
    public void onGameKeyboardEvent(InputEvent.KeyInputEvent event) {
        int key = Keyboard.getEventKey();
        if (Keyboard.isKeyDown((int)key)) {
            this.onInputEvent((Multimap<Integer, KeyBindingAction>)this.inGameActions, key, true);
        }
    }

    @SubscribeEvent
    public void onGuiKeyboardEvent(GuiScreenEvent.KeyboardInputEvent.Post event) {
        int key = Keyboard.getEventKey();
        if (Keyboard.isKeyDown((int)key)) {
            if (this.inFullscreenWithoutChat()) {
                this.onInputEvent((Multimap<Integer, KeyBindingAction>)this.inGuiActions, key, true);
            } else if (this.inMinimapPreview() && this.onInputEvent((Multimap<Integer, KeyBindingAction>)this.minimapPreviewActions, key, false)) {
                ((OptionsManager)this.mc.currentScreen).refreshMinimapOptions();
            }
        }
    }

    @SubscribeEvent
    public void onGuiMouseEvent(GuiScreenEvent.MouseInputEvent.Post event) {
        int key = -100 + Mouse.getEventButton();
        if (!Mouse.isButtonDown((int)key)) {
            if (this.inFullscreenWithoutChat()) {
                this.onInputEvent((Multimap<Integer, KeyBindingAction>)this.inGuiActions, key, true);
            } else if (this.inMinimapPreview() && this.onInputEvent((Multimap<Integer, KeyBindingAction>)this.minimapPreviewActions, key, false)) {
                ((OptionsManager)this.mc.currentScreen).refreshMinimapOptions();
            }
        }
    }

    public List<KeyBinding> getInGuiKeybindings() {
        List<KeyBinding> list = this.inGuiActions.values().stream().map(KeyBindingAction::getKeyBinding).collect(Collectors.toList());
        list.sort(Comparator.comparing(kb -> Constants.getString(kb.getKeyDescription())));
        return list;
    }

    private boolean onInputEvent(Multimap<Integer, KeyBindingAction> multimap, int key, boolean useContext) {
        try {
            if (this.sortActionsNeeded) {
                this.sortActions();
            }
            for (KeyBindingAction kba : multimap.get((Object)key)) {
                if (!kba.isActive(key, useContext)) continue;
                this.logger.debug("Firing " + kba);
                kba.getAction().run();
                return true;
            }
        }
        catch (Exception e) {
            this.logger.error("Error checking keybinding", (Object)LogFormatter.toPartialString(e));
        }
        return false;
    }

    private void sortActions() {
        this.sortActions(this.minimapPreviewActions);
        this.sortActions(this.inGameActions);
        this.sortActions(this.inGuiActions);
        this.sortActionsNeeded = false;
    }

    private void sortActions(ListMultimap<Integer, KeyBindingAction> multimap) {
        ArrayList copy = new ArrayList(multimap.values());
        multimap.clear();
        for (KeyBindingAction kba : copy) {
            multimap.put((Object)kba.getKeyBinding().getKeyCode(), (Object)kba);
        }
        for (Integer key : multimap.keySet()) {
            multimap.get((Object)key).sort(this.kbaComparator);
            Journeymap.getLogger().debug((Object)multimap.get((Object)key));
        }
    }

    private Fullscreen getFullscreen() {
        return UIManager.INSTANCE.openFullscreenMap();
    }

    private boolean inFullscreenWithoutChat() {
        return this.mc.currentScreen instanceof Fullscreen && !((Fullscreen)this.mc.currentScreen).isChatOpen();
    }

    private boolean inMinimapPreview() {
        return this.mc.currentScreen instanceof OptionsManager && ((OptionsManager)this.mc.currentScreen).previewMiniMap();
    }

    class UpdateAwareKeyBinding
    extends KeyBinding {
        UpdateAwareKeyBinding(String description, IKeyConflictContext keyConflictContext, KeyModifier keyModifier, int keyCode, String category) {
            super(description, keyConflictContext, keyModifier, keyCode, category);
        }

        public void setKeyCode(int keyCode) {
            super.setKeyCode(keyCode);
            KeyEventHandler.this.sortActionsNeeded = true;
        }

        public void setKeyModifierAndCode(KeyModifier keyModifier, int keyCode) {
            super.setKeyModifierAndCode(keyModifier, keyCode);
            KeyEventHandler.this.sortActionsNeeded = true;
        }
    }

    static class KeyBindingAction {
        KeyBinding keyBinding;
        Runnable action;

        public KeyBindingAction(KeyBinding keyBinding, Runnable action) {
            this.keyBinding = keyBinding;
            this.action = action;
        }

        boolean isActive(int key, boolean useContext) {
            if (useContext) {
                return this.keyBinding.isActiveAndMatches(key);
            }
            return this.keyBinding.getKeyCode() == key && this.keyBinding.getKeyModifier().isActive();
        }

        Runnable getAction() {
            return this.action;
        }

        KeyBinding getKeyBinding() {
            return this.keyBinding;
        }

        int order() {
            return this.keyBinding.getKeyModifier().ordinal();
        }

        public String toString() {
            return "KeyBindingAction{" + this.keyBinding.getDisplayName() + " = " + Constants.getString(this.keyBinding.getKeyDescription()) + '}';
        }
    }
}

