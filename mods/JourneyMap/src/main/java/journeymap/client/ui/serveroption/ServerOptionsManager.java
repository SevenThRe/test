/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  com.google.gson.JsonArray
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  net.minecraft.client.gui.GuiButton
 *  org.lwjgl.input.Keyboard
 */
package journeymap.client.ui.serveroption;

import com.google.common.collect.Lists;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import journeymap.client.Constants;
import journeymap.client.render.draw.DrawUtil;
import journeymap.client.ui.UIManager;
import journeymap.client.ui.component.Button;
import journeymap.client.ui.component.ButtonList;
import journeymap.client.ui.component.JmUI;
import journeymap.client.ui.component.Label;
import journeymap.client.ui.serveroption.ConfigDisplay;
import journeymap.common.Journeymap;
import journeymap.common.log.LogFormatter;
import journeymap.common.network.GetAllConfigs;
import journeymap.common.network.UpdateAllConfigs;
import net.minecraft.client.gui.GuiButton;
import org.lwjgl.input.Keyboard;

public class ServerOptionsManager
extends JmUI {
    private int index = 0;
    private JsonObject global;
    private JsonObject defaultDimension;
    private Map<Integer, JsonObject> dimensionMap;
    private List<String> dimIndexList = Lists.newArrayList();
    private JsonObject activeProperty;
    private ConfigDisplay configDisplay;
    private Button buttonNext;
    private Button buttonPrevious;
    private ButtonList topButtons;
    private Label labelSelector;
    private Label labelWorldId;
    private Button buttonClose;
    private Button buttonSave;
    private ButtonList bottomButtons;
    private final int hgap = 6;
    private final int vgap = 6;
    private int startY;
    private int centerX;
    private int topRowLeft;
    private int tileY;

    public ServerOptionsManager(JmUI returnDisplay) {
        super(Constants.getString("jm.server.edit.label.admin.edit"), returnDisplay);
        Keyboard.enableRepeatEvents((boolean)true);
        this.getData();
    }

    @Override
    public boolean func_73868_f() {
        return false;
    }

    private void getData() {
        try {
            new GetAllConfigs().send(result -> {
                if (result.getAsJson().get("global") != null) {
                    this.dimIndexList.add("global");
                    this.activeProperty = this.global = result.getAsJson().get("global").getAsJsonObject();
                    this.labelSelector = new Label(150, "jm.server.edit.label.selection.global", new Object[0]);
                    this.labelSelector.setTooltip(ServerOptionsManager.formattedToolTipHeader("jm.server.edit.label.selection.global") + Constants.getString("jm.server.edit.label.selection.global.tooltip"));
                    this.labelSelector.setHAlign(DrawUtil.HAlign.Center);
                }
                if (result.getAsJson().get("default_dimension") != null) {
                    this.dimIndexList.add("default_dimension");
                    this.defaultDimension = result.getAsJson().get("default_dimension").getAsJsonObject();
                }
                if (result.getAsJson().get("dimensions") != null) {
                    this.dimensionMap = this.buildDimensionMap(result.getAsJson().getAsJsonArray("dimensions"));
                }
            });
        }
        catch (Exception e) {
            Journeymap.getLogger().error("Error getting data", (Throwable)e);
        }
    }

    private Map<Integer, JsonObject> buildDimensionMap(JsonArray dims) {
        HashMap<Integer, JsonObject> dimMap = new HashMap<Integer, JsonObject>();
        for (JsonElement dim : dims) {
            JsonObject json = dim.getAsJsonObject();
            if (json.get("dimId") == null) continue;
            int dimId = json.get("dimId").getAsInt();
            this.dimIndexList.add(String.valueOf(dimId));
            dimMap.put(dimId, json);
        }
        return dimMap;
    }

    @Override
    public void func_73866_w_() {
        try {
            if (this.global != null) {
                this.field_146292_n.clear();
                this.buttonNext = new Button(Constants.getString("jm.server.edit.label.button.next"));
                this.buttonNext.setTooltip(ServerOptionsManager.formattedToolTipHeader("jm.server.edit.label.button.next.tooltip") + Constants.getString("jm.server.edit.label.button.next.tooltip"));
                this.buttonNext.func_175211_a(40);
                this.buttonPrevious = new Button(Constants.getString("jm.server.edit.label.button.previous"));
                this.buttonPrevious.setTooltip(ServerOptionsManager.formattedToolTipHeader("jm.server.edit.label.button.previous.tooltip") + Constants.getString("jm.server.edit.label.button.previous.tooltip"));
                this.buttonPrevious.func_175211_a(this.buttonNext.getWidth());
                if (this.global.get("world_id") != null) {
                    this.labelWorldId = new Label(304, "jm.server.edit.label.worldId", this.global.get("world_id").getAsString());
                    this.labelWorldId.setTooltip(ServerOptionsManager.formattedToolTipHeader("jm.server.edit.chkbox.world.id") + Constants.getString("jm.server.edit.label.worldId.tooltip"));
                } else {
                    this.labelWorldId = new Label(40, "jm.server.edit.label.worldId.singleplayer", new Object[0]);
                    this.labelWorldId.setTooltip(ServerOptionsManager.formattedToolTipHeader("jm.server.edit.label.worldId.singleplayer") + Constants.getString("jm.server.edit.label.worldId.singleplayer.tooltip"));
                }
                this.labelWorldId.setHAlign(DrawUtil.HAlign.Center);
                this.labelWorldId.func_175211_a(this.labelWorldId.getFitWidth(this.getFontRenderer()));
                this.buttonSave = new Button(Constants.getString("jm.waypoint.save"));
                this.buttonClose = new Button(Constants.getString("jm.server.edit.button.close"));
                this.bottomButtons = new ButtonList(this.buttonClose, this.buttonSave);
                this.bottomButtons.equalizeWidths(this.getFontRenderer());
                this.configDisplay = new ConfigDisplay(this.activeProperty, this.field_146289_q);
                this.topButtons = new ButtonList(this.buttonPrevious, this.labelSelector, this.buttonNext);
                this.field_146292_n.add(this.labelWorldId);
                this.field_146292_n.addAll(this.topButtons);
                this.field_146292_n.addAll(this.configDisplay.getButtons());
                this.field_146292_n.addAll(this.bottomButtons);
            }
        }
        catch (Throwable t) {
            Journeymap.getLogger().error(LogFormatter.toString(t));
            UIManager.INSTANCE.closeAll();
        }
    }

    @Override
    protected void layoutButtons() {
        this.startY = Math.max(40, (this.field_146295_m - 230) / 2);
        this.centerX = this.field_146294_l / 2;
        this.topRowLeft = this.centerX - 50;
        this.tileY = this.startY + 12;
        if (this.field_146292_n.isEmpty() && this.global != null) {
            this.func_73866_w_();
        }
        if (this.global != null && !this.field_146292_n.isEmpty() && this.topButtons != null && !this.topButtons.isEmpty()) {
            this.labelWorldId.setX(this.centerX - this.labelWorldId.getWidth() / 2);
            this.labelWorldId.setY(this.startY - 10);
            try {
                this.topButtons.layoutCenteredHorizontal(this.centerX, this.labelWorldId.getBottomY(), true, 6);
            }
            catch (Exception e) {
                System.out.println(this.topButtons.size());
            }
            this.configDisplay.draw(this.centerX, this.topButtons.getBottomY(), 6);
            int bottomY = Math.min(this.tileY + 128 + 12, this.field_146295_m - 10 - this.buttonClose.getHeight());
            this.bottomButtons.equalizeWidths(this.getFontRenderer(), 6, this.centerX - this.topRowLeft);
            this.bottomButtons.layoutCenteredHorizontal(this.centerX, bottomY + 20, true, 6);
        }
    }

    protected void func_146284_a(GuiButton guibutton) {
        try {
            if (guibutton == this.buttonSave) {
                this.save();
                this.closeAndReturn();
                return;
            }
            if (guibutton == this.buttonClose) {
                this.closeAndReturn();
                return;
            }
            if (guibutton == this.buttonNext) {
                ++this.index;
                this.nextProperty();
                return;
            }
            if (guibutton == this.buttonPrevious) {
                --this.index;
                this.nextProperty();
                return;
            }
        }
        catch (Throwable t) {
            this.logger.error("Error in SeverEditor.actionPerformed: " + LogFormatter.toString(t));
        }
    }

    private void save() {
        JsonObject updatedProperties = new JsonObject();
        JsonArray dims = new JsonArray();
        updatedProperties.add("global", (JsonElement)this.global);
        for (JsonObject dim : this.dimensionMap.values()) {
            dims.add((JsonElement)dim);
        }
        updatedProperties.add("dimensions", (JsonElement)dims);
        updatedProperties.add("default_dimension", (JsonElement)this.defaultDimension);
        new UpdateAllConfigs().send(updatedProperties);
    }

    private void nextProperty() {
        if (this.index < 0) {
            this.index = this.dimIndexList.size() - 1;
        } else if (this.index > this.dimIndexList.size() - 1) {
            this.index = 0;
        }
        if (this.index == 0) {
            this.labelSelector.field_146126_j = Constants.getString("jm.server.edit.label.selection.global");
            this.labelSelector.setTooltip(ServerOptionsManager.formattedToolTipHeader("jm.server.edit.label.selection.global") + Constants.getString("jm.server.edit.label.selection.global.tooltip"));
            this.activeProperty = this.global;
        } else if (this.index == 1) {
            this.labelSelector.field_146126_j = Constants.getString("jm.server.edit.label.selection.default");
            this.labelSelector.setTooltip(ServerOptionsManager.formattedToolTipHeader("jm.server.edit.label.selection.default") + Constants.getString("jm.server.edit.label.selection.default.tooltip"));
            this.activeProperty = this.defaultDimension;
        } else {
            String dimName = this.dimensionMap.get(Integer.valueOf(this.dimIndexList.get(this.index))).get("dimName").getAsString();
            this.labelSelector.field_146126_j = Constants.getString("jm.server.edit.label.selection.dimension", dimName, this.dimIndexList.get(this.index));
            this.labelSelector.setTooltip(ServerOptionsManager.formattedToolTipHeader("jm.theme.labelsource.dimension") + Constants.getString("jm.server.edit.label.selection.dimension.tooltip"));
            this.activeProperty = this.dimensionMap.get(Integer.valueOf(this.dimIndexList.get(this.index)));
        }
        this.func_73866_w_();
    }

    @Override
    public void func_73863_a(int x, int y, float par3) {
        try {
            super.func_73863_a(x, y, par3);
        }
        catch (Throwable t) {
            this.logger.error("Error in SeverEditor.drawScreen: " + LogFormatter.toString(t));
        }
    }

    @Override
    protected void closeAndReturn() {
        this.field_146292_n.clear();
        if (this.returnDisplay == null) {
            UIManager.INSTANCE.closeAll();
        } else {
            UIManager.INSTANCE.open(this.returnDisplay);
        }
    }

    static String formattedToolTipHeader(String key) {
        return "\u00a7b[" + Constants.getString(key) + "]\u00a7f\n";
    }
}

