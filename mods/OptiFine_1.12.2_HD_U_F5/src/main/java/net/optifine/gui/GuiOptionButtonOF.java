/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  bid$a
 *  bjn
 */
package net.optifine.gui;

import net.optifine.gui.IOptionControl;

public class GuiOptionButtonOF
extends bjn
implements IOptionControl {
    private bid.a option = null;

    public GuiOptionButtonOF(int id, int x, int y, bid.a option, String text) {
        super(id, x, y, option, text);
        this.option = option;
    }

    @Override
    public bid.a getOption() {
        return this.option;
    }
}

