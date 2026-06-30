/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.renderer.RenderHelper
 *  net.minecraftforge.fml.client.FMLClientHandler
 */
package journeymap.client.ui.dialog;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import journeymap.client.Constants;
import journeymap.client.cartography.color.ColorManager;
import journeymap.client.data.DataCache;
import journeymap.client.forge.event.KeyEventHandler;
import journeymap.client.io.ThemeLoader;
import journeymap.client.log.ChatLog;
import journeymap.client.log.JMLogger;
import journeymap.client.mod.ModBlockDelegate;
import journeymap.client.model.BlockMD;
import journeymap.client.properties.ClientCategory;
import journeymap.client.properties.CoreProperties;
import journeymap.client.render.draw.DrawUtil;
import journeymap.client.render.map.TileDrawStepCache;
import journeymap.client.service.webmap.Webmap;
import journeymap.client.task.main.SoftResetTask;
import journeymap.client.task.multi.MapPlayerTask;
import journeymap.client.task.multi.RenderSpec;
import journeymap.client.ui.UIManager;
import journeymap.client.ui.component.Button;
import journeymap.client.ui.component.ButtonList;
import journeymap.client.ui.component.CheckBox;
import journeymap.client.ui.component.IConfigFieldHolder;
import journeymap.client.ui.component.IntSliderButton;
import journeymap.client.ui.component.JmUI;
import journeymap.client.ui.component.ScrollListPane;
import journeymap.client.ui.fullscreen.Fullscreen;
import journeymap.client.ui.minimap.MiniMap;
import journeymap.client.ui.option.CategorySlot;
import journeymap.client.ui.option.OptionSlotFactory;
import journeymap.client.ui.option.SlotMetadata;
import journeymap.client.waypoint.WaypointStore;
import journeymap.common.Journeymap;
import journeymap.common.properties.Category;
import journeymap.common.properties.PropertiesBase;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraftforge.fml.client.FMLClientHandler;

public class OptionsManager
extends JmUI {
    protected static Set<Category> openCategories = new HashSet<Category>();
    protected final int inGameMinimapId;
    protected Category[] initialCategories;
    protected CheckBox minimap1PreviewButton;
    protected CheckBox minimap2PreviewButton;
    protected Button buttonClose;
    protected Button buttonAbout;
    protected Button buttonServer;
    protected Button renderStatsButton;
    protected Button editGridMinimap1Button;
    protected Button editGridMinimap2Button;
    protected Button editGridFullscreenButton;
    protected SlotMetadata renderStatsSlotMetadata;
    protected CategorySlot cartographyCategorySlot;
    protected ScrollListPane<CategorySlot> optionsListPane;
    protected Map<Category, List<SlotMetadata>> toolbars;
    protected Set<Category> changedCategories = new HashSet<Category>();
    protected boolean forceMinimapUpdate;
    protected ButtonList editGridButtons = new ButtonList();

    public OptionsManager() {
        this((GuiScreen)null);
    }

    public OptionsManager(GuiScreen returnDisplay) {
        this(returnDisplay, openCategories.toArray(new Category[0]));
    }

    public OptionsManager(GuiScreen returnDisplay, Category ... initialCategories) {
        super(String.format("JourneyMap %s %s", Journeymap.JM_VERSION, Constants.getString("jm.common.options")), returnDisplay);
        this.initialCategories = initialCategories;
        this.inGameMinimapId = Journeymap.getClient().getActiveMinimapId();
    }

    @Override
    public void func_73866_w_() {
        try {
            String tooltip;
            String name;
            this.field_146292_n.clear();
            if (this.editGridMinimap1Button == null) {
                name = Constants.getString("jm.common.grid_edit");
                tooltip = Constants.getString("jm.common.grid_edit.tooltip");
                this.editGridMinimap1Button = new Button(name);
                this.editGridMinimap1Button.setTooltip(tooltip);
                this.editGridMinimap1Button.setDrawBackground(false);
                this.editGridMinimap2Button = new Button(name);
                this.editGridMinimap2Button.setTooltip(tooltip);
                this.editGridMinimap2Button.setDrawBackground(false);
                this.editGridFullscreenButton = new Button(name);
                this.editGridFullscreenButton.setTooltip(tooltip);
                this.editGridFullscreenButton.setDrawBackground(false);
                this.editGridButtons = new ButtonList(this.editGridMinimap1Button, this.editGridMinimap2Button, this.editGridFullscreenButton);
            }
            if (this.minimap1PreviewButton == null) {
                name = String.format("%s %s", Constants.getString("jm.minimap.preview"), "1");
                tooltip = Constants.getString("jm.minimap.preview.tooltip", KeyEventHandler.INSTANCE.kbMinimapPreset.getDisplayName());
                this.minimap1PreviewButton = new CheckBox(name, false);
                this.minimap1PreviewButton.setTooltip(tooltip);
                if (FMLClientHandler.instance().getClient().field_71441_e == null) {
                    this.minimap1PreviewButton.setEnabled(false);
                }
            }
            if (this.minimap2PreviewButton == null) {
                name = String.format("%s %s", Constants.getString("jm.minimap.preview"), "2");
                tooltip = Constants.getString("jm.minimap.preview.tooltip", KeyEventHandler.INSTANCE.kbMinimapPreset.getDisplayName());
                this.minimap2PreviewButton = new CheckBox(name, false);
                this.minimap2PreviewButton.setTooltip(tooltip);
                if (FMLClientHandler.instance().getClient().field_71441_e == null) {
                    this.minimap2PreviewButton.setEnabled(false);
                }
            }
            if (this.renderStatsButton == null) {
                this.renderStatsButton = new LabelButton(150, "jm.common.renderstats", 0, 0, 0);
                this.renderStatsButton.setEnabled(false);
            }
            if (this.optionsListPane == null) {
                ArrayList<CategorySlot> categorySlots = new ArrayList<CategorySlot>();
                Minecraft mc = this.field_146297_k;
                int width = this.field_146294_l;
                int height = this.field_146295_m;
                ((Object)((Object)this)).getClass();
                this.optionsListPane = new ScrollListPane(this, mc, width, height, 35, this.field_146295_m - 30, 20);
                this.optionsListPane.setAlignTop(true);
                this.optionsListPane.setSlots(OptionSlotFactory.getSlots(this.getToolbars()));
                if (this.initialCategories != null) {
                    for (Category initialCategory : this.initialCategories) {
                        for (CategorySlot categorySlot : this.optionsListPane.getRootSlots()) {
                            if (categorySlot.getCategory() != initialCategory) continue;
                            categorySlot.setSelected(true);
                            categorySlots.add(categorySlot);
                        }
                    }
                }
                for (ScrollListPane.ISlot iSlot : this.optionsListPane.getRootSlots()) {
                    if (!(iSlot instanceof CategorySlot)) continue;
                    CategorySlot categorySlot2 = (CategorySlot)iSlot;
                    Category category = categorySlot2.getCategory();
                    if (category == null) {
                        // empty if block
                    }
                    ResetButton resetButton = new ResetButton(category);
                    SlotMetadata resetSlotMetadata = new SlotMetadata((Button)resetButton, 1);
                    if (category == ClientCategory.MiniMap1) {
                        if (FMLClientHandler.instance().getClient().field_71441_e != null) {
                            categorySlot2.getAllChildMetadata().add(new SlotMetadata((Button)this.minimap1PreviewButton, 4));
                        }
                        categorySlot2.getAllChildMetadata().add(new SlotMetadata(this.editGridMinimap1Button, 3));
                        continue;
                    }
                    if (category == ClientCategory.MiniMap2) {
                        if (FMLClientHandler.instance().getClient().field_71441_e != null) {
                            categorySlot2.getAllChildMetadata().add(new SlotMetadata((Button)this.minimap2PreviewButton, 4));
                        }
                        categorySlot2.getAllChildMetadata().add(new SlotMetadata(this.editGridMinimap2Button, 3));
                        continue;
                    }
                    if (category == ClientCategory.FullMap) {
                        categorySlot2.getAllChildMetadata().add(new SlotMetadata(this.editGridMinimap2Button, 3));
                        continue;
                    }
                    if (category != ClientCategory.Cartography) continue;
                    this.cartographyCategorySlot = categorySlot2;
                    this.renderStatsSlotMetadata = new SlotMetadata(this.renderStatsButton, Constants.getString("jm.common.renderstats.title"), Constants.getString("jm.common.renderstats.tooltip"), 2);
                    categorySlot2.getAllChildMetadata().add(this.renderStatsSlotMetadata);
                }
                this.optionsListPane.updateSlots();
                if (!categorySlots.isEmpty()) {
                    this.optionsListPane.scrollTo((ScrollListPane.ISlot)categorySlots.get(0));
                }
            } else {
                this.optionsListPane.func_148122_a(this.field_146294_l, this.field_146295_m, 35, this.field_146295_m - 30);
                this.optionsListPane.updateSlots();
            }
            this.buttonClose = new Button(Constants.getString("jm.common.close"));
            this.buttonAbout = new Button(Constants.getString("jm.common.splash_about"));
            this.buttonServer = new Button(Constants.getString("jm.server.edit.label.admin.edit"));
            this.buttonServer.addClickListener(button -> {
                UIManager.INSTANCE.openServerEditor(this);
                return Boolean.TRUE;
            });
            this.buttonServer.field_146125_m = Journeymap.getClient().isServerAdmin();
            ButtonList bottomRow = new ButtonList(this.buttonClose);
            bottomRow.equalizeWidths(this.getFontRenderer());
            bottomRow.setWidths(Math.max(150, this.buttonAbout.getWidth()));
            bottomRow.layoutCenteredHorizontal(this.field_146294_l / 2, this.field_146295_m - 25, true, 4);
            this.field_146292_n.addAll(bottomRow);
        }
        catch (Throwable t) {
            JMLogger.logOnce("Error in OptionsManager.initGui(): " + t, t);
        }
    }

    @Override
    protected void layoutButtons() {
        if (this.field_146292_n.isEmpty()) {
            this.func_73866_w_();
        }
    }

    @Override
    public void func_73863_a(int x, int y, float par3) {
        try {
            if (this.forceMinimapUpdate) {
                if (this.minimap1PreviewButton.isActive()) {
                    UIManager.INSTANCE.switchMiniMapPreset(1);
                } else if (this.minimap2PreviewButton.isActive()) {
                    UIManager.INSTANCE.switchMiniMapPreset(2);
                }
            }
            if (this.field_146297_k.field_71441_e != null) {
                this.updateRenderStats();
            }
            Object[] lastTooltip = this.optionsListPane.lastTooltip;
            long lastTooltipTime = this.optionsListPane.lastTooltipTime;
            this.optionsListPane.lastTooltip = null;
            this.optionsListPane.func_148128_a(x, y, par3);
            super.func_73863_a(x, y, par3);
            if (this.previewMiniMap()) {
                UIManager.INSTANCE.getMiniMap().drawMap(true);
                RenderHelper.func_74518_a();
            }
            if (this.optionsListPane.lastTooltip != null && Arrays.equals(this.optionsListPane.lastTooltip, lastTooltip)) {
                this.optionsListPane.lastTooltipTime = lastTooltipTime;
                if (System.currentTimeMillis() - this.optionsListPane.lastTooltipTime > this.optionsListPane.hoverDelay) {
                    Button button = this.optionsListPane.lastTooltipMetadata.getButton();
                    this.drawHoveringText(this.optionsListPane.lastTooltip, x, button.getBottomY() + 15);
                }
            }
        }
        catch (Throwable t) {
            JMLogger.logOnce("Error in OptionsManager.drawScreen(): " + t, t);
        }
    }

    public void func_146274_d() throws IOException {
        super.func_146274_d();
        this.optionsListPane.func_178039_p();
    }

    private void updateRenderStats() {
        RenderSpec.getSurfaceSpec();
        RenderSpec.getTopoSpec();
        RenderSpec.getUndergroundSpec();
        for (ScrollListPane.ISlot iSlot : this.optionsListPane.getRootSlots()) {
            CategorySlot categorySlot;
            if (!(iSlot instanceof CategorySlot) || (categorySlot = (CategorySlot)iSlot).getCategory() != ClientCategory.Cartography) continue;
            CoreProperties coreProperties = Journeymap.getClient().getCoreProperties();
            for (SlotMetadata slotMetadata : categorySlot.getAllChildMetadata()) {
                if (!(slotMetadata.getButton() instanceof IConfigFieldHolder)) continue;
                Object property = ((IConfigFieldHolder)((Object)slotMetadata.getButton())).getConfigField();
                boolean limitButtonRange = false;
                if (property == coreProperties.renderDistanceCaveMax) {
                    limitButtonRange = true;
                    slotMetadata.getButton().resetLabelColors();
                } else if (property == coreProperties.renderDistanceSurfaceMax) {
                    limitButtonRange = true;
                    slotMetadata.getButton().resetLabelColors();
                }
                if (!limitButtonRange) continue;
                IntSliderButton button = (IntSliderButton)slotMetadata.getButton();
                button.maxValue = this.field_146297_k.field_71474_y.field_151451_c;
                if (button.getValue() <= this.field_146297_k.field_71474_y.field_151451_c) continue;
                button.setValue(this.field_146297_k.field_71474_y.field_151451_c);
            }
        }
        String string = this.renderStatsButton.field_146126_j = Journeymap.getClient().getCoreProperties().mappingEnabled.get() != false ? MapPlayerTask.getSimpleStats() : Constants.getString("jm.common.enable_mapping_false_text");
        if (this.cartographyCategorySlot != null) {
            this.renderStatsButton.func_175211_a(this.cartographyCategorySlot.getCurrentColumnWidth());
        }
    }

    @Override
    public void func_146278_c(int layer) {
    }

    protected void func_73864_a(int mouseX, int mouseY, int mouseEvent) throws IOException {
        super.func_73864_a(mouseX, mouseY, mouseEvent);
        boolean pressed = this.optionsListPane.func_148179_a(mouseX, mouseY, mouseEvent);
        if (pressed) {
            this.checkPressedButton();
        }
    }

    @Override
    protected void func_146286_b(int mouseX, int mouseY, int mouseEvent) {
        super.func_146286_b(mouseX, mouseY, mouseEvent);
        this.optionsListPane.func_148181_b(mouseX, mouseY, mouseEvent);
    }

    protected void func_146273_a(int mouseX, int mouseY, int lastButtonClicked, long timeSinceMouseClick) {
        super.func_146273_a(mouseX, mouseY, lastButtonClicked, timeSinceMouseClick);
        this.checkPressedButton();
    }

    protected void checkPressedButton() {
        CategorySlot categorySlot;
        SlotMetadata slotMetadata = this.optionsListPane.getLastPressed();
        if (slotMetadata != null) {
            if (slotMetadata.getButton() instanceof ResetButton) {
                this.resetOptions(((ResetButton)slotMetadata.getButton()).category);
            }
            if (slotMetadata.getName().equals(Constants.getString("jm.common.ui_theme"))) {
                ThemeLoader.getCurrentTheme(true);
                if (this.previewMiniMap()) {
                    UIManager.INSTANCE.getMiniMap().updateDisplayVars(true);
                }
            }
            if (this.editGridButtons.contains(slotMetadata.getButton())) {
                UIManager.INSTANCE.openGridEditor(this);
                return;
            }
            if (slotMetadata.getButton() == this.minimap1PreviewButton) {
                this.minimap2PreviewButton.setToggled(false);
                UIManager.INSTANCE.switchMiniMapPreset(1);
                UIManager.INSTANCE.getMiniMap().resetInitTime();
            }
            if (slotMetadata.getButton() == this.minimap2PreviewButton) {
                this.minimap1PreviewButton.setToggled(false);
                UIManager.INSTANCE.switchMiniMapPreset(2);
                UIManager.INSTANCE.getMiniMap().resetInitTime();
            }
        }
        if ((categorySlot = (CategorySlot)this.optionsListPane.getLastPressedParentSlot()) != null) {
            Category category = categorySlot.getCategory();
            this.changedCategories.add(category);
            if (category == ClientCategory.MiniMap1 || category == ClientCategory.MiniMap2) {
                this.refreshMinimapOptions();
                DataCache.INSTANCE.resetRadarCaches();
                UIManager.INSTANCE.getMiniMap().updateDisplayVars(true);
            }
            if (category == ClientCategory.Cartography) {
                Journeymap.getClient().getCoreProperties().save();
                RenderSpec.resetRenderSpecs();
            }
        }
    }

    protected void func_146284_a(GuiButton button) {
        if (button == this.buttonClose) {
            this.closeAndReturn();
            return;
        }
        if (button == this.buttonAbout) {
            UIManager.INSTANCE.openSplash(this);
            return;
        }
        if (button == this.minimap1PreviewButton) {
            this.minimap2PreviewButton.setToggled(false);
            UIManager.INSTANCE.switchMiniMapPreset(1);
        }
        if (button == this.minimap2PreviewButton) {
            this.minimap1PreviewButton.setToggled(false);
            UIManager.INSTANCE.switchMiniMapPreset(2);
        }
    }

    @Override
    protected void func_73869_a(char c, int key) {
        switch (key) {
            case 1: {
                if (this.previewMiniMap()) {
                    this.minimap1PreviewButton.setToggled(false);
                    this.minimap2PreviewButton.setToggled(false);
                    break;
                }
                this.closeAndReturn();
            }
        }
        boolean optionUpdated = this.optionsListPane.keyTyped(c, key);
        if (optionUpdated && this.previewMiniMap()) {
            UIManager.INSTANCE.getMiniMap().updateDisplayVars(true);
        }
    }

    protected void resetOptions(Category category) {
        HashSet<PropertiesBase> updatedProperties = new HashSet<PropertiesBase>();
        for (CategorySlot categorySlot : this.optionsListPane.getRootSlots()) {
            if (!category.equals(categorySlot.getCategory())) continue;
            for (SlotMetadata slotMetadata : categorySlot.getAllChildMetadata()) {
                PropertiesBase properties;
                slotMetadata.resetToDefaultValue();
                if (!slotMetadata.hasConfigField() || (properties = slotMetadata.getProperties()) == null) continue;
                updatedProperties.add(properties);
            }
        }
        for (PropertiesBase properties2 : updatedProperties) {
            properties2.save();
        }
        RenderSpec.resetRenderSpecs();
    }

    public boolean previewMiniMap() {
        return this.minimap1PreviewButton.getToggled() != false || this.minimap2PreviewButton.getToggled() != false;
    }

    public void refreshMinimapOptions() {
        HashSet<Category> cats = new HashSet<Category>();
        cats.add(ClientCategory.MiniMap1);
        cats.add(ClientCategory.MiniMap2);
        for (CategorySlot categorySlot : this.optionsListPane.getRootSlots()) {
            if (!cats.contains(categorySlot.getCategory())) continue;
            for (SlotMetadata slotMetadata : categorySlot.getAllChildMetadata()) {
                slotMetadata.getButton().refresh();
            }
        }
    }

    @Override
    protected void closeAndReturn() {
        Journeymap.getClient().getCoreProperties().optionsManagerViewed.set(Journeymap.JM_VERSION.toString());
        Journeymap.getClient().saveConfigProperties();
        if (this.field_146297_k.field_71441_e != null) {
            UIManager.INSTANCE.getMiniMap().setMiniMapProperties(Journeymap.getClient().getMiniMapProperties(this.inGameMinimapId));
            for (Category category : this.changedCategories) {
                if (category == ClientCategory.MiniMap1) {
                    DataCache.INSTANCE.resetRadarCaches();
                    UIManager.INSTANCE.getMiniMap().reset();
                    continue;
                }
                if (category == ClientCategory.MiniMap2) {
                    DataCache.INSTANCE.resetRadarCaches();
                    continue;
                }
                if (category == ClientCategory.FullMap) {
                    DataCache.INSTANCE.resetRadarCaches();
                    ThemeLoader.getCurrentTheme(true);
                    continue;
                }
                if (category == ClientCategory.WebMap) {
                    DataCache.INSTANCE.resetRadarCaches();
                    if (Journeymap.getClient().getWebMapProperties().enabled.get().booleanValue()) {
                        Webmap.INSTANCE.start();
                    } else {
                        Webmap.INSTANCE.stop();
                    }
                    ChatLog.announceMod(true);
                    continue;
                }
                if (category == ClientCategory.Waypoint) {
                    WaypointStore.INSTANCE.reset();
                    continue;
                }
                if (category == ClientCategory.WaypointBeacon) continue;
                if (category == ClientCategory.Cartography) {
                    ColorManager.INSTANCE.reset();
                    ModBlockDelegate.INSTANCE.reset();
                    BlockMD.reset();
                    RenderSpec.resetRenderSpecs();
                    TileDrawStepCache.instance().invalidateAll();
                    MiniMap.state().requireRefresh();
                    Fullscreen.state().requireRefresh();
                    MapPlayerTask.forceNearbyRemap();
                    continue;
                }
                if (category != ClientCategory.Advanced) continue;
                SoftResetTask.queue();
                if (Journeymap.getClient().getWebMapProperties().enabled.get().booleanValue()) {
                    Webmap.INSTANCE.start();
                } else {
                    Webmap.INSTANCE.stop();
                }
                ChatLog.announceMod(false);
            }
            UIManager.INSTANCE.getMiniMap().reset();
            UIManager.INSTANCE.getMiniMap().updateDisplayVars(true);
        }
        if (this.returnDisplay != null && this.returnDisplay instanceof Fullscreen) {
            ((Fullscreen)this.returnDisplay).reset();
        }
        openCategories.clear();
        for (CategorySlot categorySlot : this.optionsListPane.getRootSlots()) {
            if (!categorySlot.isSelected()) continue;
            openCategories.add(categorySlot.getCategory());
        }
        super.closeAndReturn();
    }

    Map<Category, List<SlotMetadata>> getToolbars() {
        if (this.toolbars == null) {
            this.toolbars = new HashMap<Category, List<SlotMetadata>>();
            for (Category category : ClientCategory.values) {
                String name = Constants.getString("jm.config.reset");
                String tooltip = Constants.getString("jm.config.reset.tooltip");
                SlotMetadata toolbarSlotMetadata = new SlotMetadata(new ResetButton(category), name, tooltip);
                this.toolbars.put(category, Arrays.asList(toolbarSlotMetadata));
            }
        }
        return this.toolbars;
    }

    public static class LabelButton
    extends Button {
        DrawUtil.HAlign hAlign = DrawUtil.HAlign.Left;

        public LabelButton(int width, String key, Object ... labelArgs) {
            super(Constants.getString(key, labelArgs));
            this.setTooltip(Constants.getString(key + ".tooltip"));
            this.setDrawBackground(false);
            this.setDrawFrame(false);
            this.setEnabled(false);
            this.setLabelColors(0xC0C0C0, 0xC0C0C0, 0xC0C0C0);
            this.func_175211_a(width);
        }

        @Override
        public int getFitWidth(FontRenderer fr) {
            return this.field_146120_f;
        }

        @Override
        public void fitWidth(FontRenderer fr) {
        }

        public void setHAlign(DrawUtil.HAlign hAlign) {
            this.hAlign = hAlign;
        }

        @Override
        public void func_191745_a(Minecraft minecraft, int mouseX, int mouseY, float ticks) {
            int labelX = 0;
            switch (this.hAlign) {
                case Left: {
                    labelX = this.getRightX();
                    break;
                }
                case Right: {
                    labelX = this.getX();
                    break;
                }
                default: {
                    labelX = this.getCenterX();
                }
            }
            DrawUtil.drawLabel(this.field_146126_j, labelX, this.getMiddleY(), this.hAlign, DrawUtil.VAlign.Middle, null, 0.0f, this.labelColor, 1.0f, 1.0, this.drawLabelShadow);
        }
    }

    public static class ResetButton
    extends Button {
        public final Category category;

        public ResetButton(Category category) {
            super(Constants.getString("jm.config.reset"));
            this.category = category;
            this.setTooltip(Constants.getString("jm.config.reset.tooltip"));
            this.setDrawBackground(false);
            this.setLabelColors(0xFF0000, 0xFF0000, null);
        }
    }
}

