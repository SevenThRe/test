/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonObject
 *  net.minecraft.client.gui.FontRenderer
 */
package journeymap.client.ui.serveroption;

import com.google.gson.JsonObject;
import java.awt.Color;
import java.util.EnumSet;
import journeymap.client.Constants;
import journeymap.client.render.draw.DrawUtil;
import journeymap.client.ui.component.ButtonList;
import journeymap.client.ui.component.Label;
import journeymap.client.ui.component.ListPropertyButton;
import journeymap.client.ui.serveroption.Draw;
import journeymap.client.ui.serveroption.ServerOption;
import journeymap.client.ui.serveroption.ServerOptionsManager;
import journeymap.common.properties.Category;
import journeymap.common.properties.config.EnumField;
import net.minecraft.client.gui.FontRenderer;

public class MappingOptions
implements Draw {
    private ButtonList buttons;
    private JsonObject properties;
    private FontRenderer fontRenderer;
    private Label label;
    private ButtonList mappingToggleButtons;

    public MappingOptions(JsonObject properties, FontRenderer fontRenderer) {
        this.fontRenderer = fontRenderer;
        this.properties = properties;
        this.buttons = this.buildButtons();
    }

    private ButtonList buildButtons() {
        ButtonList list = new ButtonList();
        this.label = new Label(this.fontRenderer.func_78256_a(Constants.getString("jm.server.edit.mapping.label")) + 10, "jm.server.edit.mapping.label", new Object[0]);
        this.label.setHAlign(DrawUtil.HAlign.Center);
        this.label.func_175211_a(this.label.getFitWidth(this.fontRenderer));
        ServerOption surfaceOption = new ServerOption("surface", this.properties);
        ListPropertyButton<ServerOption.Option> surfaceOptionButton = new ListPropertyButton<ServerOption.Option>(EnumSet.allOf(ServerOption.Option.class), Constants.getString("jm.server.edit.mapping.toggle.surface.label"), new EnumField<ServerOption.Option>(Category.Hidden, "", surfaceOption.getOption()));
        surfaceOptionButton.addClickListener(button -> {
            surfaceOption.setOption((ServerOption.Option)((Object)((Object)surfaceOptionButton.getField().get())));
            this.updateToggleProperty(surfaceOption, this.properties, "surface", "op_surface");
            return true;
        });
        ServerOption topoOption = new ServerOption("topo", this.properties);
        ListPropertyButton<ServerOption.Option> topoOptionButton = new ListPropertyButton<ServerOption.Option>(EnumSet.allOf(ServerOption.Option.class), Constants.getString("jm.server.edit.mapping.toggle.topo.label"), new EnumField<ServerOption.Option>(Category.Hidden, "", topoOption.getOption()));
        topoOptionButton.addClickListener(button -> {
            topoOption.setOption((ServerOption.Option)((Object)((Object)topoOptionButton.getField().get())));
            this.updateToggleProperty(topoOption, this.properties, "topo", "op_topo");
            return true;
        });
        ServerOption caveOption = new ServerOption("cave", this.properties);
        ListPropertyButton<ServerOption.Option> caveOptionButton = new ListPropertyButton<ServerOption.Option>(EnumSet.allOf(ServerOption.Option.class), Constants.getString("jm.server.edit.mapping.toggle.cave.label"), new EnumField<ServerOption.Option>(Category.Hidden, "", caveOption.getOption()));
        caveOptionButton.addClickListener(button -> {
            caveOption.setOption((ServerOption.Option)((Object)((Object)caveOptionButton.getField().get())));
            this.updateToggleProperty(caveOption, this.properties, "cave", "op_cave");
            return true;
        });
        surfaceOptionButton.setTooltip(300, ServerOptionsManager.formattedToolTipHeader("jm.server.edit.mapping.toggle.surface.label") + this.getToggleTooltipBase(), Constants.getString("jm.server.edit.mapping.toggle.surface.tooltip"));
        topoOptionButton.setTooltip(300, ServerOptionsManager.formattedToolTipHeader("jm.server.edit.mapping.toggle.topo.label") + this.getToggleTooltipBase(), Constants.getString("jm.server.edit.mapping.toggle.topo.tooltip"));
        caveOptionButton.setTooltip(300, ServerOptionsManager.formattedToolTipHeader("jm.server.edit.mapping.toggle.cave.label") + this.getToggleTooltipBase(), Constants.getString("jm.server.edit.mapping.toggle.cave.tooltip"));
        this.mappingToggleButtons = new ButtonList(surfaceOptionButton, topoOptionButton, caveOptionButton);
        this.mappingToggleButtons.setWidths(120);
        list.add(this.label);
        list.addAll(this.mappingToggleButtons);
        return list;
    }

    @Override
    public void draw(int startX, int startY, int gap) {
        this.label.setX(startX - this.label.getWidth() / 2);
        this.label.setY(startY + 5);
        DrawUtil.drawRectangle(this.label.getX(), this.label.getBottomY() - 4, this.label.getWidth(), 1.0, new Color(255, 255, 255).getRGB(), 1.0f);
        this.mappingToggleButtons.layoutCenteredHorizontal(startX, this.label.getBottomY(), true, gap);
    }

    @Override
    public ButtonList getButtons() {
        return this.buttons;
    }
}

