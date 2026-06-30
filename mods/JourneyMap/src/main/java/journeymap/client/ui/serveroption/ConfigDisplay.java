/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonObject
 *  net.minecraft.client.gui.FontRenderer
 */
package journeymap.client.ui.serveroption;

import com.google.gson.JsonObject;
import journeymap.client.ui.component.ButtonList;
import journeymap.client.ui.serveroption.Draw;
import journeymap.client.ui.serveroption.MappingOptions;
import journeymap.client.ui.serveroption.RadarOptions;
import journeymap.client.ui.serveroption.TopBoxOptions;
import net.minecraft.client.gui.FontRenderer;

public class ConfigDisplay
implements Draw {
    private TopBoxOptions topBoxOptions;
    private RadarOptions radarOptions;
    private MappingOptions mappingOptions;

    public ConfigDisplay(JsonObject properties, FontRenderer fontRenderer) {
        this.topBoxOptions = new TopBoxOptions(properties, fontRenderer);
        this.radarOptions = new RadarOptions(properties, fontRenderer);
        this.mappingOptions = new MappingOptions(properties, fontRenderer);
    }

    @Override
    public void draw(int startX, int startY, int gap) {
        this.topBoxOptions.draw(startX, startY, gap);
        this.radarOptions.draw(startX, this.topBoxOptions.getButtons().getBottomY(), gap);
        this.mappingOptions.draw(startX, this.radarOptions.getButtons().getBottomY(), gap);
    }

    @Override
    public ButtonList getButtons() {
        ButtonList list = new ButtonList();
        list.addAll(this.topBoxOptions.getButtons());
        list.addAll(this.radarOptions.getButtons());
        list.addAll(this.mappingOptions.getButtons());
        return list;
    }
}

