/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.GuiButton
 */
package journeymap.client.ui.dialog;

import journeymap.client.Constants;
import journeymap.client.JourneymapClient;
import journeymap.client.properties.ClientCategory;
import journeymap.client.task.main.IMainThreadTask;
import journeymap.client.task.multi.MapRegionTask;
import journeymap.client.ui.UIManager;
import journeymap.client.ui.component.Button;
import journeymap.client.ui.component.ButtonList;
import journeymap.client.ui.component.JmUI;
import journeymap.client.ui.fullscreen.Fullscreen;
import journeymap.common.Journeymap;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;

public class AutoMapConfirmation
extends JmUI {
    Button buttonOptions;
    Button buttonAll;
    Button buttonMissing;
    Button buttonClose;

    public AutoMapConfirmation() {
        this((JmUI)null);
    }

    public AutoMapConfirmation(JmUI returnDisplay) {
        super(Constants.getString("jm.common.automap_dialog"), returnDisplay);
    }

    @Override
    public void initGui() {
        this.buttonList.clear();
        this.buttonOptions = new Button(Constants.getString("jm.common.options_button"));
        this.buttonAll = new Button(Constants.getString("jm.common.automap_dialog_all"));
        this.buttonMissing = new Button(Constants.getString("jm.common.automap_dialog_missing"));
        this.buttonClose = new Button(Constants.getString("jm.common.close"));
        this.buttonList.add(this.buttonOptions);
        this.buttonList.add(this.buttonAll);
        this.buttonList.add(this.buttonMissing);
        this.buttonList.add(this.buttonClose);
    }

    @Override
    protected void layoutButtons() {
        if (this.buttonList.isEmpty()) {
            this.initGui();
        }
        int x = this.width / 2;
        int lineHeight = this.fontRenderer.FONT_HEIGHT + 3;
        int y = 35 + lineHeight * 2;
        this.drawCenteredString(this.getFontRenderer(), Constants.getString("jm.common.automap_dialog_summary_1"), x, y, 0xFFFFFF);
        this.drawCenteredString(this.getFontRenderer(), Constants.getString("jm.common.automap_dialog_summary_2"), x, y += lineHeight, 0xFFFFFF);
        this.buttonOptions.centerHorizontalOn(x).centerVerticalOn(y += lineHeight * 2);
        this.drawCenteredString(this.getFontRenderer(), Constants.getString("jm.common.automap_dialog_text"), x, y += lineHeight * 3, 0xFFFF00);
        ButtonList buttons = new ButtonList(this.buttonAll, this.buttonMissing);
        buttons.equalizeWidths(this.fontRenderer, 4, 200);
        buttons.layoutCenteredHorizontal(x, y += lineHeight * 2, true, 4);
        this.buttonClose.centerHorizontalOn(x).below(this.buttonMissing, lineHeight);
    }

    protected void actionPerformed(GuiButton guibutton) {
        if (guibutton == this.buttonOptions) {
            UIManager.INSTANCE.openOptionsManager(this, ClientCategory.Cartography);
            return;
        }
        if (guibutton != this.buttonClose) {
            Boolean arg;
            boolean enable;
            if (guibutton == this.buttonAll) {
                enable = true;
                arg = Boolean.TRUE;
            } else if (guibutton == this.buttonMissing) {
                enable = true;
                arg = Boolean.FALSE;
            } else {
                enable = false;
                arg = null;
            }
            MapRegionTask.MAP_TYPE = Fullscreen.state().getMapType();
            Journeymap.getClient().queueMainThreadTask(new IMainThreadTask(){

                @Override
                public IMainThreadTask perform(Minecraft mc, JourneymapClient jm) {
                    Journeymap.getClient().toggleTask(MapRegionTask.Manager.class, enable, arg);
                    return null;
                }

                @Override
                public String getName() {
                    return "Automap";
                }
            });
        }
        this.closeAndReturn();
    }

    @Override
    protected void keyTyped(char c, int i) {
        switch (i) {
            case 1: {
                this.closeAndReturn();
            }
        }
    }
}

