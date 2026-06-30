/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  bid$a
 *  bjs
 */
package net.optifine.gui;

import net.optifine.gui.IOptionControl;

public class GuiOptionSliderOF
extends bjs
implements IOptionControl {
    private bid.a option = null;

    public GuiOptionSliderOF(int id, int x, int y, bid.a option) {
        super(id, x, y, option);
        this.option = option;
    }

    @Override
    public bid.a getOption() {
        return this.option;
    }
}

