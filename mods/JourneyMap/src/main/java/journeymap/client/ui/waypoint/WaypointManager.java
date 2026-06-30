/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraftforge.fml.client.FMLClientHandler
 */
package journeymap.client.ui.waypoint;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import journeymap.client.Constants;
import journeymap.client.command.CmdTeleportWaypoint;
import journeymap.client.log.JMLogger;
import journeymap.client.model.Waypoint;
import journeymap.client.properties.ClientCategory;
import journeymap.client.ui.UIManager;
import journeymap.client.ui.component.Button;
import journeymap.client.ui.component.ButtonList;
import journeymap.client.ui.component.JmUI;
import journeymap.client.ui.component.OnOffButton;
import journeymap.client.ui.component.ScrollListPane;
import journeymap.client.ui.fullscreen.Fullscreen;
import journeymap.client.ui.option.SlotMetadata;
import journeymap.client.ui.waypoint.DimensionsButton;
import journeymap.client.ui.waypoint.SortButton;
import journeymap.client.ui.waypoint.WaypointManagerItem;
import journeymap.client.waypoint.WaypointStore;
import journeymap.common.properties.Category;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.client.FMLClientHandler;

public class WaypointManager
extends JmUI {
    static String ASCEND = "\u25b2";
    static String DESCEND = "\u25bc";
    static int COLWAYPOINT = 0;
    static int COLLOCATION = 20;
    static int COLNAME = 60;
    static int DEFAULT_ITEMWIDTH = 460;
    private static WaypointManagerItem.Sort currentSort;
    private final String on = Constants.getString("jm.common.on");
    private final String off = Constants.getString("jm.common.off");
    protected int colWaypoint = 0;
    protected int colLocation = 20;
    protected int colName = 60;
    protected int itemWidth = 460;
    protected ScrollListPane<WaypointManagerItem> itemScrollPane;
    protected int rowHeight = 16;
    Boolean canUserTeleport;
    private SortButton buttonSortName;
    private SortButton buttonSortDistance;
    private DimensionsButton buttonDimensions;
    private Button buttonClose;
    private Button buttonAdd;
    private Button buttonOptions;
    private OnOffButton buttonToggleAll;
    private ButtonList bottomButtons;
    private final Waypoint focusWaypoint;
    private final ArrayList<WaypointManagerItem> items = new ArrayList();
    private static boolean toggled;

    public WaypointManager() {
        this((Waypoint)null, (JmUI)null);
    }

    public WaypointManager(JmUI returnDisplay) {
        this((Waypoint)null, returnDisplay);
    }

    public WaypointManager(Waypoint focusWaypoint, JmUI returnDisplay) {
        super(Constants.getString("jm.waypoint.manage_title"), returnDisplay);
        this.focusWaypoint = focusWaypoint;
    }

    @Override
    public void func_73866_w_() {
        try {
            this.field_146292_n.clear();
            this.canUserTeleport = CmdTeleportWaypoint.isPermitted(this.field_146297_k);
            FontRenderer fr = this.getFontRenderer();
            if (this.buttonSortDistance == null) {
                WaypointManagerItem.DistanceComparator distanceSort = new WaypointManagerItem.DistanceComparator((EntityPlayer)FMLClientHandler.instance().getClient().field_71439_g, true);
                String distanceLabel = Constants.getString("jm.waypoint.distance");
                this.buttonSortDistance = new SortButton(distanceLabel, distanceSort);
                this.buttonSortDistance.setTextOnly(fr);
            }
            this.field_146292_n.add(this.buttonSortDistance);
            if (this.buttonSortName == null) {
                WaypointManagerItem.NameComparator nameSort = new WaypointManagerItem.NameComparator(true);
                this.buttonSortName = new SortButton(Constants.getString("jm.waypoint.name"), nameSort);
                this.buttonSortName.setTextOnly(fr);
            }
            this.field_146292_n.add(this.buttonSortName);
            if (this.buttonToggleAll == null) {
                String enableOn = Constants.getString("jm.waypoint.enable_all", "", this.on);
                String enableOff = Constants.getString("jm.waypoint.enable_all", "", this.off);
                this.buttonToggleAll = new OnOffButton(enableOff, enableOn, true);
                this.buttonToggleAll.setTextOnly(this.getFontRenderer());
            }
            this.field_146292_n.add(this.buttonToggleAll);
            if (this.buttonDimensions == null) {
                this.buttonDimensions = new DimensionsButton();
            }
            if (this.buttonAdd == null) {
                this.buttonAdd = new Button(Constants.getString("jm.waypoint.new"));
                this.buttonAdd.fitWidth(this.getFontRenderer());
                this.buttonAdd.func_175211_a(this.buttonAdd.getWidth() * 2);
            }
            if (this.buttonOptions == null) {
                this.buttonOptions = new Button(Constants.getString("jm.common.options_button"));
                this.buttonOptions.fitWidth(this.getFontRenderer());
            }
            this.buttonClose = new Button(Constants.getString("jm.common.close"));
            this.bottomButtons = new ButtonList(this.buttonOptions, this.buttonAdd, this.buttonClose);
            this.field_146292_n.addAll(this.bottomButtons);
            if (this.items.isEmpty()) {
                this.updateItems();
                if (currentSort == null) {
                    this.updateSort(this.buttonSortName);
                } else {
                    if (this.buttonSortDistance.sort.equals(currentSort)) {
                        this.buttonSortDistance.sort.ascending = WaypointManager.currentSort.ascending;
                        this.buttonSortDistance.setActive(true);
                        this.buttonSortName.setActive(false);
                    }
                    if (this.buttonSortName.sort.equals(currentSort)) {
                        this.buttonSortName.sort.ascending = WaypointManager.currentSort.ascending;
                        this.buttonSortName.setActive(true);
                        this.buttonSortDistance.setActive(false);
                    }
                }
            }
            if (this.itemScrollPane == null) {
                this.itemScrollPane = new ScrollListPane(this, this.field_146297_k, this.field_146294_l, this.field_146295_m, 35, this.field_146295_m - 30, 20);
            } else {
                this.itemScrollPane.func_148122_a(this.field_146294_l, this.field_146295_m, 35, this.field_146295_m - 30);
                this.itemScrollPane.updateSlots();
            }
            this.itemScrollPane.setSlots(this.items);
            if (!this.items.isEmpty()) {
                this.itemScrollPane.scrollTo(this.items.get(0));
            }
        }
        catch (Throwable t) {
            JMLogger.logOnce("Error in OptionsManager.initGui(): " + t, t);
        }
    }

    @Override
    protected void layoutButtons() {
        if (this.field_146292_n.isEmpty() || this.itemScrollPane == null) {
            this.func_73866_w_();
        }
        this.buttonToggleAll.setDrawButton(!this.items.isEmpty());
        this.buttonSortDistance.setDrawButton(!this.items.isEmpty());
        this.buttonSortName.setDrawButton(!this.items.isEmpty());
        this.bottomButtons.equalizeWidths(this.getFontRenderer());
        int bottomButtonWidth = Math.min(this.bottomButtons.getWidth(3) + 25, this.itemScrollPane.func_148139_c());
        this.bottomButtons.equalizeWidths(this.getFontRenderer(), 3, bottomButtonWidth);
        this.bottomButtons.layoutCenteredHorizontal(this.field_146294_l / 2, this.field_146295_m - 25, true, 4);
    }

    @Override
    public void func_73863_a(int x, int y, float par3) {
        if (this.field_146297_k == null) {
            return;
        }
        if (this.field_146292_n.isEmpty() || this.itemScrollPane == null) {
            this.func_73866_w_();
        }
        try {
            this.itemScrollPane.func_148122_a(this.field_146294_l, this.field_146295_m, 35, this.field_146295_m - 30);
            Object[] lastTooltip = this.itemScrollPane.lastTooltip;
            long lastTooltipTime = this.itemScrollPane.lastTooltipTime;
            this.itemScrollPane.lastTooltip = null;
            this.itemScrollPane.func_148128_a(x, y, par3);
            super.func_73863_a(x, y, par3);
            if (!this.items.isEmpty()) {
                int headerY = 35 - this.getFontRenderer().field_78288_b;
                WaypointManagerItem firstRow = this.items.get(0);
                if (firstRow.y > headerY + 16) {
                    headerY = firstRow.y - 16;
                }
                this.buttonToggleAll.centerHorizontalOn(firstRow.getButtonEnableCenterX()).setY(headerY);
                this.buttonSortDistance.centerHorizontalOn(firstRow.getLocationLeftX()).setY(headerY);
                this.colName = this.buttonSortDistance.getRightX() + 10;
                this.buttonSortName.setPosition(this.colName - 5, headerY);
            }
            this.buttonToggleAll.drawUnderline();
            for (List<SlotMetadata> toolbar : this.getToolbars().values()) {
                for (SlotMetadata slotMetadata : toolbar) {
                    slotMetadata.getButton().secondaryDrawButton();
                }
            }
            if (this.itemScrollPane.lastTooltip != null && Arrays.equals(this.itemScrollPane.lastTooltip, lastTooltip)) {
                this.itemScrollPane.lastTooltipTime = lastTooltipTime;
                if (System.currentTimeMillis() - this.itemScrollPane.lastTooltipTime > this.itemScrollPane.hoverDelay) {
                    Button button = this.itemScrollPane.lastTooltipMetadata.getButton();
                    this.drawHoveringText(this.itemScrollPane.lastTooltip, x, button.getBottomY() + 15);
                }
            }
        }
        catch (Throwable t) {
            JMLogger.logOnce("Error in OptionsManager.drawScreen(): " + t, t);
        }
    }

    @Override
    public void func_146278_c(int layer) {
    }

    protected void func_73864_a(int mouseX, int mouseY, int mouseEvent) throws IOException {
        boolean pressed;
        super.func_73864_a(mouseX, mouseY, mouseEvent);
        if (mouseEvent == 0 && (pressed = this.itemScrollPane.func_148179_a(mouseX, mouseY, mouseEvent))) {
            this.checkPressedButton();
        }
    }

    @Override
    protected void func_146286_b(int mouseX, int mouseY, int state) {
        super.func_146286_b(mouseX, mouseY, state);
        this.itemScrollPane.func_148181_b(mouseX, mouseY, state);
    }

    protected void func_146273_a(int mouseX, int mouseY, int lastButtonClicked, long timeSinceMouseClick) {
        super.func_146273_a(mouseX, mouseY, lastButtonClicked, timeSinceMouseClick);
        this.checkPressedButton();
    }

    public void func_146274_d() throws IOException {
        super.func_146274_d();
        this.itemScrollPane.func_178039_p();
    }

    protected void checkPressedButton() {
        ScrollListPane.ISlot parentSlot;
        SlotMetadata slotMetadata = this.itemScrollPane.getLastPressed();
        if (slotMetadata != null) {
            // empty if block
        }
        if ((parentSlot = this.itemScrollPane.getLastPressedParentSlot()) != null) {
            // empty if block
        }
    }

    protected void func_146284_a(GuiButton guibutton) {
        if (guibutton == this.buttonClose) {
            this.refreshAndClose();
            return;
        }
        if (guibutton == this.buttonSortName) {
            this.updateSort(this.buttonSortName);
            return;
        }
        if (guibutton == this.buttonSortDistance) {
            this.updateSort(this.buttonSortDistance);
            return;
        }
        if (guibutton == this.buttonDimensions) {
            this.buttonDimensions.nextValue();
            this.updateItems();
            this.field_146292_n.clear();
            return;
        }
        if (guibutton == this.buttonAdd) {
            Waypoint waypoint = Waypoint.of((EntityPlayer)this.field_146297_k.field_71439_g);
            UIManager.INSTANCE.openWaypointEditor(waypoint, true, this);
            return;
        }
        if (guibutton == this.buttonToggleAll) {
            boolean state = this.buttonToggleAll.getToggled();
            state = this.toggleItems(state);
            this.buttonToggleAll.setToggled(state);
            this.field_146292_n.clear();
            return;
        }
        if (guibutton == this.buttonOptions) {
            UIManager.INSTANCE.openOptionsManager(this, ClientCategory.Waypoint, ClientCategory.WaypointBeacon);
        }
    }

    @Override
    protected void func_73869_a(char c, int i) {
        boolean keyUsed;
        if (i == 1) {
            this.closeAndReturn();
        }
        if (keyUsed = this.itemScrollPane.keyTyped(c, i)) {
            return;
        }
        if (i == 200) {
            this.itemScrollPane.func_148145_f(-this.rowHeight);
        }
        if (i == 208) {
            this.itemScrollPane.func_148145_f(this.rowHeight);
        }
        if (i == 201) {
            this.itemScrollPane.func_148145_f(-this.itemScrollPane.field_148158_l);
        }
        if (i == 209) {
            this.itemScrollPane.func_148145_f(this.itemScrollPane.field_148158_l);
        }
        if (i == 199) {
            this.itemScrollPane.func_148145_f(-this.itemScrollPane.func_148148_g());
        }
        if (i == 207) {
            this.itemScrollPane.func_148145_f(this.itemScrollPane.func_148148_g());
        }
    }

    protected boolean toggleItems(boolean enable) {
        for (WaypointManagerItem item : this.items) {
            if (enable != item.waypoint.isEnable()) continue;
            enable = !enable;
            break;
        }
        for (WaypointManagerItem item : this.items) {
            if (item.waypoint.isEnable() == enable) continue;
            item.enableWaypoint(enable);
        }
        return !enable;
    }

    public static void toggleAllWaypoints() {
        toggled = !toggled;
        Collection<Waypoint> waypoints = WaypointStore.INSTANCE.getAll();
        for (Waypoint waypoint : waypoints) {
            waypoint.setEnable(toggled);
            waypoint.setDirty();
        }
    }

    protected void updateItems() {
        this.items.clear();
        Integer currentDim = DimensionsButton.currentWorldProvider == null ? null : Integer.valueOf(DimensionsButton.currentWorldProvider.getDimension());
        FontRenderer fr = this.getFontRenderer();
        this.itemWidth = 0;
        Collection<Waypoint> waypoints = WaypointStore.INSTANCE.getAll();
        boolean allOn = true;
        for (Waypoint waypoint : waypoints) {
            WaypointManagerItem item = new WaypointManagerItem(waypoint, fr, this);
            item.getDistanceTo((EntityPlayer)this.field_146297_k.field_71439_g);
            if (currentDim != null && !item.waypoint.getDimensions().contains(currentDim)) continue;
            this.items.add(item);
            if (!allOn) continue;
            allOn = waypoint.isEnable();
        }
        this.itemWidth = this.items.isEmpty() ? 460 : this.items.get((int)0).internalWidth;
        this.buttonToggleAll.setToggled(!allOn);
        this.updateCount();
        if (currentSort != null) {
            Collections.sort(this.items, currentSort);
        }
    }

    protected void updateSort(SortButton sortButton) {
        for (GuiButton button : this.field_146292_n) {
            if (!(button instanceof SortButton)) continue;
            if (button == sortButton) {
                if (sortButton.sort.equals(currentSort)) {
                    sortButton.toggle();
                } else {
                    sortButton.setActive(true);
                }
                currentSort = sortButton.sort;
                continue;
            }
            ((SortButton)button).setActive(false);
        }
        if (currentSort != null) {
            this.items.sort(currentSort);
        }
        if (this.itemScrollPane != null) {
            this.itemScrollPane.setSlots(this.items);
        }
    }

    protected void updateCount() {
        String itemCount = this.items.isEmpty() ? "" : Integer.toString(this.items.size());
        String enableOn = Constants.getString("jm.waypoint.enable_all", itemCount, this.on);
        String enableOff = Constants.getString("jm.waypoint.enable_all", itemCount, this.off);
        this.buttonToggleAll.setLabels(enableOff, enableOn);
    }

    protected boolean isSelected(WaypointManagerItem item) {
        return this.itemScrollPane.func_148131_a(item.getSlotIndex());
    }

    protected int getMargin() {
        return this.field_146294_l > this.itemWidth + 2 ? (this.field_146294_l - this.itemWidth) / 2 : 0;
    }

    public void removeWaypoint(WaypointManagerItem item) {
        WaypointStore.INSTANCE.remove(item.waypoint);
        this.items.remove(item);
    }

    protected void refreshAndClose() {
        this.closeAndReturn();
    }

    @Override
    protected void closeAndReturn() {
        this.bottomButtons.setEnabled(false);
        WaypointStore.INSTANCE.bulkSave();
        Fullscreen.state().requireRefresh();
        this.bottomButtons.setEnabled(true);
        if (this.returnDisplay == null) {
            UIManager.INSTANCE.closeAll();
        } else {
            UIManager.INSTANCE.open(this.returnDisplay);
        }
    }

    Map<Category, List<SlotMetadata>> getToolbars() {
        return Collections.EMPTY_MAP;
    }

    static {
        toggled = true;
    }
}

