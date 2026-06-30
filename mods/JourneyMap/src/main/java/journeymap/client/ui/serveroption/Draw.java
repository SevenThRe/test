/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonObject
 */
package journeymap.client.ui.serveroption;

import com.google.gson.JsonObject;
import journeymap.client.Constants;
import journeymap.client.ui.component.ButtonList;
import journeymap.client.ui.component.CheckBox;
import journeymap.client.ui.serveroption.ServerOption;
import journeymap.client.ui.serveroption.ServerOptionsManager;

public interface Draw {
    public void draw(int var1, int var2, int var3);

    public ButtonList getButtons();

    default public CheckBox checkBox(String key, String field, JsonObject properties) {
        if (properties.get(field) != null) {
            CheckBox checkBox = new CheckBox(Constants.getString(key), properties.get(field).getAsBoolean());
            checkBox.setTooltip(ServerOptionsManager.formattedToolTipHeader(key) + Constants.getString(key + ".tooltip"));
            checkBox.addToggleListener((button, toggled) -> {
                properties.remove(field);
                properties.addProperty(field, Boolean.valueOf(toggled));
                return true;
            });
            return checkBox;
        }
        CheckBox checkBox = new CheckBox("", true);
        checkBox.field_146124_l = false;
        return checkBox;
    }

    default public void updateToggleProperty(ServerOption option, JsonObject properties, String node, String opNode) {
        properties.remove(node);
        properties.remove(opNode);
        properties.addProperty(node, Boolean.valueOf(option.getFieldValue()));
        properties.addProperty(opNode, Boolean.valueOf(option.getOpFieldValue()));
    }

    default public String getToggleTooltipBase() {
        StringBuilder sb = new StringBuilder();
        sb.append(Constants.getString("jm.server.edit.toggle.base.all")).append("\n");
        sb.append(Constants.getString("jm.server.edit.toggle.base.op")).append("\n");
        sb.append(Constants.getString("jm.server.edit.toggle.base.none")).append("\n");
        return sb.toString();
    }
}

