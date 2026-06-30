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
import journeymap.client.ui.component.CheckBox;
import journeymap.client.ui.component.Label;
import journeymap.client.ui.component.ListPropertyButton;
import journeymap.client.ui.serveroption.Draw;
import journeymap.client.ui.serveroption.ServerOption;
import journeymap.client.ui.serveroption.ServerOptionsManager;
import journeymap.common.properties.Category;
import journeymap.common.properties.config.EnumField;
import net.minecraft.client.gui.FontRenderer;

public class RadarOptions
implements Draw {
    private ButtonList buttons;
    private JsonObject properties;
    private FontRenderer fontRenderer;
    private Label label;
    private ListPropertyButton<ServerOption.Option> radarPropertyButton;
    private ButtonList checkBoxList;

    public RadarOptions(JsonObject properties, FontRenderer fontRenderer) {
        this.fontRenderer = fontRenderer;
        this.properties = properties;
        this.buttons = this.createRadarButtons();
    }

    private ButtonList createRadarButtons() {
        ButtonList list = new ButtonList();
        this.label = new Label(this.fontRenderer.getStringWidth(Constants.getString("jm.server.edit.radar.label")) + 10, "jm.server.edit.radar.label", new Object[0]);
        this.label.setHAlign(DrawUtil.HAlign.Center);
        this.label.setWidth(this.label.getFitWidth(this.fontRenderer));
        CheckBox playerChkBx = this.checkBox("jm.server.edit.radar.chkbox.player", "playerRadar", this.properties);
        CheckBox villagerChkBx = this.checkBox("jm.server.edit.radar.chkbox.villager", "villagerRadar", this.properties);
        CheckBox animalChkBx = this.checkBox("jm.server.edit.radar.chkbox.animal", "animalRadar", this.properties);
        CheckBox mobChkBx = this.checkBox("jm.server.edit.radar.chkbox.mob", "mobRadar", this.properties);
        this.checkBoxList = new ButtonList(playerChkBx, villagerChkBx, animalChkBx, mobChkBx);
        ServerOption option = new ServerOption("radar", this.properties);
        this.radarPropertyButton = new ListPropertyButton<ServerOption.Option>(EnumSet.allOf(ServerOption.Option.class), Constants.getString("jm.server.edit.radar.toggle.label"), new EnumField<ServerOption.Option>(Category.Hidden, "", option.getOption()));
        this.radarPropertyButton.addClickListener(button -> {
            option.setOption(this.radarPropertyButton.getField().get());
            this.updateToggleProperty(option, this.properties, "radar", "op_radar");
            this.updateCheckBoxes(this.radarPropertyButton.getField().get());
            return true;
        });
        this.radarPropertyButton.setWidth(this.fontRenderer.getStringWidth(this.label.displayString) + 40);
        this.radarPropertyButton.setTooltip(300, ServerOptionsManager.formattedToolTipHeader("jm.server.edit.radar.toggle.label") + this.getToggleTooltipBase(), Constants.getString("jm.server.edit.radar.toggle.tooltip1"), Constants.getString("jm.server.edit.radar.toggle.tooltip2"));
        this.updateCheckBoxes(this.radarPropertyButton.getField().get());
        list.add(this.label);
        list.add(this.radarPropertyButton);
        list.addAll(this.checkBoxList);
        return list;
    }

    private void updateCheckBoxes(ServerOption.Option options2) {
        if (ServerOption.Option.ALL.equals((Object)options2)) {
            this.checkBoxList.setVisible(true);
        } else {
            this.checkBoxList.setVisible(false);
        }
    }

    @Override
    public void draw(int startX, int startY, int gap) {
        this.label.setX(startX - this.label.getWidth() / 2);
        this.label.setY(startY + 5);
        DrawUtil.drawRectangle(this.label.getX(), this.label.getBottomY() - 4, this.label.getWidth(), 1.0, new Color(255, 255, 255).getRGB(), 1.0f);
        this.radarPropertyButton.setX(startX - this.radarPropertyButton.getWidth() / 2);
        this.radarPropertyButton.setY(this.label.getBottomY());
        this.checkBoxList.layoutCenteredHorizontal(startX, this.radarPropertyButton.getBottomY() + gap, true, gap);
    }

    @Override
    public ButtonList getButtons() {
        return this.buttons;
    }
}

