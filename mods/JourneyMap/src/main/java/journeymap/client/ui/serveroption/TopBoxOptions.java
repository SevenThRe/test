/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonObject
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraftforge.fml.client.FMLClientHandler
 */
package journeymap.client.ui.serveroption;

import com.google.gson.JsonObject;
import java.util.EnumSet;
import journeymap.client.Constants;
import journeymap.client.ui.component.ButtonList;
import journeymap.client.ui.component.CheckBox;
import journeymap.client.ui.component.IntSliderButton;
import journeymap.client.ui.component.ListPropertyButton;
import journeymap.client.ui.serveroption.Draw;
import journeymap.client.ui.serveroption.ServerOption;
import journeymap.client.ui.serveroption.ServerOptionsManager;
import journeymap.common.properties.Category;
import journeymap.common.properties.config.EnumField;
import journeymap.common.properties.config.IntegerField;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraftforge.fml.client.FMLClientHandler;

public class TopBoxOptions
implements Draw {
    private ButtonList buttons;
    private JsonObject properties;
    private FontRenderer fontRenderer;

    public TopBoxOptions(JsonObject properties, FontRenderer fontRenderer) {
        this.fontRenderer = fontRenderer;
        this.properties = properties;
        this.buttons = this.buildButtons();
    }

    @Override
    public ButtonList getButtons() {
        return this.buttons;
    }

    private ButtonList buildButtons() {
        ButtonList list = new ButtonList();
        if (this.properties.get("enabled") != null) {
            list.add(this.checkBox("jm.server.edit.chkbox.enable", "enabled", this.properties));
            CheckBox dimTeleportCheckBox = this.checkBox("jm.server.edit.chkbox.teleport", "can_teleport", this.properties);
            dimTeleportCheckBox.setTooltip(ServerOptionsManager.formattedToolTipHeader("jm.server.edit.chkbox.teleport") + Constants.getString("jm.server.edit.chkbox.teleport.dimension.tooltip") + "\n\n" + Constants.getString("jm.server.edit.chkbox.teleport.dimension.tooltip2"));
            list.add(dimTeleportCheckBox);
        } else {
            list.add(this.checkBox("jm.server.edit.chkbox.teleport", "can_teleport", this.properties));
            if (!FMLClientHandler.instance().getClient().func_71356_B() || Minecraft.func_71410_x().func_71401_C() != null && Minecraft.func_71410_x().func_71401_C().func_71344_c()) {
                if (!FMLClientHandler.instance().getClient().func_71356_B()) {
                    CheckBox worldIdCheckBox = this.checkBox("jm.server.edit.chkbox.world.id", "useWorldId", this.properties);
                    worldIdCheckBox.setTooltip(ServerOptionsManager.formattedToolTipHeader("jm.server.edit.chkbox.world.id") + Constants.getString("jm.server.edit.chkbox.world.id.tooltip") + "\n\n" + Constants.getString("jm.server.edit.chkbox.world.id.tooltip2") + "\n\n" + Constants.getString("jm.server.edit.chkbox.world.id.tooltip3"));
                    list.add(worldIdCheckBox);
                }
                IntegerField sliderFieldValue = new IntegerField(Category.Hidden, "", journeymap.common.network.Constants.TRACKING_MIN, journeymap.common.network.Constants.TRACKING_MAX, journeymap.common.network.Constants.TRACKING_DEFUALT);
                sliderFieldValue.set(this.properties.get("tracking_time").getAsInt());
                IntSliderButton trackingUpdateSlider = new IntSliderButton(sliderFieldValue, Constants.getString("jm.server.edit.slider.update.pre"), Constants.getString("jm.server.edit.slider.update.post"));
                trackingUpdateSlider.setTooltip(ServerOptionsManager.formattedToolTipHeader("jm.server.edit.tracking.update.label") + Constants.getString("jm.server.edit.slider.update.tooltip"));
                trackingUpdateSlider.field_146125_m = false;
                ServerOption option = new ServerOption("can_track", this.properties);
                ListPropertyButton<ServerOption.Option> tracking = new ListPropertyButton<ServerOption.Option>(EnumSet.allOf(ServerOption.Option.class), Constants.getString("jm.server.edit.tracking.label"), new EnumField<ServerOption.Option>(Category.Hidden, "", option.getOption()));
                tracking.addClickListener(button -> {
                    option.setOption((ServerOption.Option)((Object)((Object)tracking.getField().get())));
                    this.updateToggleProperty(option, this.properties, "can_track", "op_can_track");
                    this.resetTrackingSlider((ServerOption.Option)((Object)((Object)tracking.getField().get())), tracking, trackingUpdateSlider);
                    return true;
                });
                tracking.setTooltip(300, ServerOptionsManager.formattedToolTipHeader("jm.server.edit.tracking.label") + this.getToggleTooltipBase(), Constants.getString("jm.server.edit.tracking.tooltip1"), "", Constants.getString("jm.server.edit.tracking.tooltip2"), "", Constants.getString("jm.server.edit.tracking.tooltip3"), "", Constants.getString("jm.server.edit.tracking.tooltip4"));
                trackingUpdateSlider.addClickListener(button -> {
                    this.properties.remove("tracking_time");
                    this.properties.addProperty("tracking_time", (Number)trackingUpdateSlider.getValue());
                    return true;
                });
                trackingUpdateSlider.func_175211_a(this.fontRenderer.func_78256_a(Constants.getString("jm.server.edit.slider.update.pre") + journeymap.common.network.Constants.TRACKING_MAX + Constants.getString("jm.server.edit.slider.update.post")) + 10);
                this.resetTrackingSlider(tracking.getField().get(), tracking, trackingUpdateSlider);
                list.add(tracking);
                list.add(trackingUpdateSlider);
            }
        }
        return list;
    }

    private void resetTrackingSlider(ServerOption.Option options2, ListPropertyButton tracking, IntSliderButton trackingUpdateSlider) {
        if (ServerOption.Option.NONE.equals((Object)options2)) {
            trackingUpdateSlider.setVisible(false);
        } else {
            trackingUpdateSlider.setVisible(true);
        }
        tracking.func_175211_a(this.fontRenderer.func_78256_a(Constants.getString("jm.server.edit.tracking.label") + " : * " + options2.displayName() + " * ") + 10);
    }

    @Override
    public void draw(int startX, int startY, int gap) {
        this.buttons.layoutCenteredHorizontal(startX, startY + 5, true, gap - 2, true);
    }
}

