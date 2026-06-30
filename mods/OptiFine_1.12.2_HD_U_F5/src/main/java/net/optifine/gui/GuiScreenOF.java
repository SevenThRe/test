/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  bja
 *  blk
 *  bls
 */
package net.optifine.gui;

import java.io.IOException;
import java.util.List;

public class GuiScreenOF
extends blk {
    protected void actionPerformedRightClick(bja button) throws IOException {
    }

    protected void a(int mouseX, int mouseY, int mouseButton) throws IOException {
        bja btn;
        super.a(mouseX, mouseY, mouseButton);
        if (mouseButton == 1 && (btn = GuiScreenOF.getSelectedButton(mouseX, mouseY, this.n)) != null && btn.l) {
            btn.a(this.j.U());
            this.actionPerformedRightClick(btn);
        }
    }

    public static bja getSelectedButton(int x, int y, List<bja> listButtons) {
        for (int i = 0; i < listButtons.size(); ++i) {
            bja btn = listButtons.get(i);
            if (!btn.m) continue;
            int btnWidth = bls.getButtonWidth((bja)btn);
            int btnHeight = bls.getButtonHeight((bja)btn);
            if (x < btn.h || y < btn.i || x >= btn.h + btnWidth || y >= btn.i + btnHeight) continue;
            return btn;
        }
        return null;
    }
}

