/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiButton
 */
package journeymap.client.ui.dialog;

import journeymap.client.Constants;
import journeymap.client.task.main.DeleteMapTask;
import journeymap.client.ui.component.Button;
import journeymap.client.ui.component.JmUI;
import net.minecraft.client.gui.GuiButton;

public class DeleteMapConfirmation
extends JmUI {
    Button buttonAll;
    Button buttonCurrent;
    Button buttonClose;

    public DeleteMapConfirmation() {
        this((JmUI)null);
    }

    public DeleteMapConfirmation(JmUI returnDisplay) {
        super(Constants.getString("jm.common.deletemap_dialog"), returnDisplay);
    }

    @Override
    public void func_73866_w_() {
        this.field_146292_n.clear();
        this.buttonAll = new Button(Constants.getString("jm.common.deletemap_dialog_all"));
        this.buttonCurrent = new Button(Constants.getString("jm.common.deletemap_dialog_this"));
        this.buttonClose = new Button(Constants.getString("jm.waypoint.cancel"));
        this.field_146292_n.add(this.buttonAll);
        this.field_146292_n.add(this.buttonCurrent);
        this.field_146292_n.add(this.buttonClose);
    }

    @Override
    protected void layoutButtons() {
        if (this.field_146292_n.isEmpty()) {
            this.func_73866_w_();
        }
        int x = this.field_146294_l / 2;
        int y = this.field_146295_m / 4;
        int vgap = 3;
        this.func_73732_a(this.getFontRenderer(), Constants.getString("jm.common.deletemap_dialog_text"), x, y, 0xFFFFFF);
        this.buttonAll.centerHorizontalOn(x).setY(y + 18);
        this.buttonCurrent.centerHorizontalOn(x).below(this.buttonAll, 3);
        this.buttonClose.centerHorizontalOn(x).below(this.buttonCurrent, 12);
    }

    protected void func_146284_a(GuiButton guibutton) {
        if (guibutton == this.buttonAll || guibutton == this.buttonCurrent) {
            DeleteMapTask.queue(guibutton == this.buttonAll);
            this.closeAndReturn();
        }
        if (guibutton == this.buttonClose) {
            this.closeAndReturn();
        }
    }

    @Override
    protected void func_73869_a(char c, int i) {
        switch (i) {
            case 1: {
                this.closeAndReturn();
            }
        }
    }
}

