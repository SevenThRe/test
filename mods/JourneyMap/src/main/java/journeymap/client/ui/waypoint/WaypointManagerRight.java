/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.gui.GuiTextField
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraftforge.fml.client.FMLClientHandler
 *  org.lwjgl.input.Keyboard
 */
package journeymap.client.ui.waypoint;

import java.awt.Color;
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
import journeymap.client.ui.component.ScrollListPaneRight;
import journeymap.client.ui.fullscreen.Fullscreen;
import journeymap.client.ui.option.SlotMetadata;
import journeymap.client.ui.waypoint.DimensionsButton;
import journeymap.client.ui.waypoint.OnOffButtonCopy;
import journeymap.client.ui.waypoint.SortButtonRight;
import journeymap.client.ui.waypoint.WaypointManagerItemRight;
import journeymap.client.waypoint.WaypointStore;
import journeymap.common.properties.Category;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.client.FMLClientHandler;
import org.lwjgl.input.Keyboard;

public class WaypointManagerRight
extends JmUI {
    static String ASCEND = "\u25b2";
    static String DESCEND = "\u25bc";
    static int COLWAYPOINT = 0;
    static int COLLOCATION = 20;
    static int COLNAME = 60;
    static int DEFAULT_ITEMWIDTH = 460;
    private static WaypointManagerItemRight.Sort currentSort;
    private final String on = Constants.getString("jm.common.on");
    private final String off = Constants.getString("jm.common.off");
    protected int colWaypoint = 0;
    protected int colLocation = 20;
    protected int colName = 60;
    protected int itemWidth = 460;
    protected ScrollListPaneRight<WaypointManagerItemRight> itemScrollPane;
    protected int rowHeight = 16;
    Boolean canUserTeleport;
    private SortButtonRight buttonSortName;
    private SortButtonRight buttonSortDistance;
    private DimensionsButton buttonDimensions;
    private Button buttonClose;
    private Button buttonAdd;
    private Button buttonOptions;
    private OnOffButton buttonToggleAll;
    private ButtonList bottomButtons;
    private final Waypoint focusWaypoint;
    public final ArrayList<WaypointManagerItemRight> items = new ArrayList();
    private static boolean toggled;
    public GuiTextField nameField;
    public String filterName = "";

    public WaypointManagerRight() {
        this((Waypoint)null, (JmUI)null);
    }

    public WaypointManagerRight(JmUI returnDisplay) {
        this((Waypoint)null, returnDisplay);
    }

    public WaypointManagerRight(Waypoint focusWaypoint, JmUI returnDisplay) {
        super(Constants.getString("jm.waypoint.manage_title"), returnDisplay);
        this.focusWaypoint = focusWaypoint;
        this.nameField = new GuiTextField(0, Minecraft.func_71410_x().field_71466_p, this.field_146294_l - 69, 25, 48, 12);
        this.nameField.func_146193_g(-1);
        this.nameField.func_146204_h(-1);
        this.nameField.func_146185_a(true);
        this.nameField.func_146203_f(35);
    }

    @Override
    public void func_73866_w_() {
        Keyboard.enableRepeatEvents((boolean)true);
        try {
            this.field_146292_n.clear();
            this.canUserTeleport = CmdTeleportWaypoint.isPermitted(this.field_146297_k);
            FontRenderer fr = this.getFontRenderer();
            if (this.buttonSortDistance == null) {
                WaypointManagerItemRight.DistanceComparator distanceSort = new WaypointManagerItemRight.DistanceComparator((EntityPlayer)FMLClientHandler.instance().getClient().field_71439_g, true);
                String distanceLabel = Constants.getString("jm.waypoint.distance");
                this.buttonSortDistance = new SortButtonRight(distanceLabel, distanceSort);
                this.buttonSortDistance.setTextOnly(fr);
            }
            this.field_146292_n.add(this.buttonSortDistance);
            if (this.buttonSortName == null) {
                WaypointManagerItemRight.NameComparator nameSort = new WaypointManagerItemRight.NameComparator(true);
                this.buttonSortName = new SortButtonRight(Constants.getString("jm.waypoint.name"), nameSort);
                this.buttonSortName.setTextOnly(fr);
            }
            this.field_146292_n.add(this.buttonSortName);
            if (this.buttonToggleAll == null) {
                String enableOn = Constants.getString("jm.waypoint.enable_all", "", this.on);
                String enableOff = Constants.getString("jm.waypoint.enable_all", "", this.off);
                this.buttonToggleAll = new OnOffButtonCopy(enableOff, enableOn, true);
                this.buttonToggleAll.func_175211_a(150);
                this.buttonToggleAll.setDefaultStyle(false);
                this.buttonToggleAll.setDrawBackground(false);
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
            if (this.items.isEmpty()) {
                this.updateItems();
                if (currentSort == null) {
                    this.updateSort(this.buttonSortName);
                } else {
                    if (this.buttonSortDistance.sort.equals(currentSort)) {
                        this.buttonSortDistance.sort.ascending = WaypointManagerRight.currentSort.ascending;
                        this.buttonSortDistance.setActive(true);
                        this.buttonSortName.setActive(false);
                    }
                    if (this.buttonSortName.sort.equals(currentSort)) {
                        this.buttonSortName.sort.ascending = WaypointManagerRight.currentSort.ascending;
                        this.buttonSortName.setActive(true);
                        this.buttonSortDistance.setActive(false);
                    }
                }
            }
            if (this.itemScrollPane == null) {
                this.itemScrollPane = new ScrollListPaneRight(this, this.field_146297_k, this.field_146294_l, this.field_146295_m, 35, this.field_146295_m - 30, 20);
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
        this.buttonToggleAll.setDrawButton(true);
        this.buttonSortDistance.setDrawButton(true);
        this.buttonSortName.setDrawButton(true);
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
            this.layoutButtons();
            for (GuiButton guibutton : this.field_146292_n) {
                guibutton.func_191745_a(this.field_146297_k, x, y, 0.0f);
            }
            Color c = new Color(128, 128, 128, 255);
            int c1 = c.getBlue() | c.getGreen() << 8 | c.getRed() << 16 | c.getAlpha() << 24;
            this.nameField.field_146209_f = this.field_146294_l - 69;
            this.nameField.func_146194_f();
            if (this.nameField.func_146179_b().isEmpty()) {
                this.func_73731_b(this.field_146289_q, "\u641c\u7d22...", this.field_146294_l - 66, 27, c1);
            }
            int headerY = 35 - this.getFontRenderer().field_78288_b;
            WaypointManagerItemRight firstRow = this.items.get(0);
            if (firstRow.y > headerY + 16) {
                headerY = firstRow.y - 16;
            }
            this.buttonToggleAll.leftOf(firstRow.getLocationLeftX() + 130).setY(this.field_146295_m - 25);
            this.buttonSortDistance.centerHorizontalOn(firstRow.getLocationLeftX()).setY(headerY);
            this.colName = this.buttonSortDistance.getRightX() + 10;
            this.buttonSortName.setPosition(this.colName - 5, headerY);
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
        this.nameField.func_146192_a(mouseX, mouseY, mouseEvent);
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
        ScrollListPaneRight.ISlot parentSlot;
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
        String oldText = this.nameField.func_146179_b();
        this.nameField.func_146201_a(c, i);
        String newText = this.nameField.func_146179_b();
        if (!oldText.equals(newText)) {
            this.filterName = newText;
            this.updateItems();
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
        for (WaypointManagerItemRight item : this.items) {
            if (enable != item.waypoint.isEnable()) continue;
            enable = !enable;
            break;
        }
        for (WaypointManagerItemRight item : this.items) {
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

    public void updateItems() {
        this.items.clear();
        Integer currentDim = DimensionsButton.currentWorldProvider == null ? null : Integer.valueOf(DimensionsButton.currentWorldProvider.getDimension());
        FontRenderer fr = this.getFontRenderer();
        this.itemWidth = 0;
        Collection<Waypoint> waypoints = WaypointStore.INSTANCE.getAll();
        boolean allOn = true;
        for (Waypoint waypoint : waypoints) {
            WaypointManagerItemRight item = new WaypointManagerItemRight(waypoint, fr, this);
            item.getDistanceTo((EntityPlayer)this.field_146297_k.field_71439_g);
            if (currentDim != null && !item.waypoint.getDimensions().contains(currentDim) || !waypoint.getName().contains(this.filterName)) continue;
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

    protected void updateSort(SortButtonRight sortButton) {
        for (GuiButton button : this.field_146292_n) {
            if (!(button instanceof SortButtonRight)) continue;
            if (button == sortButton) {
                if (sortButton.sort.equals(currentSort)) {
                    sortButton.toggle();
                } else {
                    sortButton.setActive(true);
                }
                currentSort = sortButton.sort;
                continue;
            }
            ((SortButtonRight)button).setActive(false);
        }
        if (currentSort != null) {
            this.items.sort(currentSort);
        }
        if (this.itemScrollPane != null) {
            this.itemScrollPane.setSlots(this.items);
        }
    }

    protected void updateCount() {
        String itemCount = this.items.isEmpty() ? "0" : Integer.toString(this.items.size());
        String enableOn = Constants.getString("jm.waypoint.enable_all", itemCount, this.on);
        String enableOff = Constants.getString("jm.waypoint.enable_all", itemCount, this.off);
        this.buttonToggleAll.setLabels(enableOff, enableOn);
    }

    protected boolean isSelected(WaypointManagerItemRight item) {
        return this.itemScrollPane.func_148131_a(item.getSlotIndex());
    }

    protected int getMargin() {
        return 0;
    }

    public void removeWaypoint(WaypointManagerItemRight item) {
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

