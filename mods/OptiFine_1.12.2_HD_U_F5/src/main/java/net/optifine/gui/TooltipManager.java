/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  bib
 *  bip
 *  bir
 *  bja
 *  blk
 */
package net.optifine.gui;

import java.awt.Rectangle;
import java.util.Arrays;
import java.util.List;
import net.optifine.gui.GuiScreenOF;
import net.optifine.gui.TooltipProvider;

public class TooltipManager {
    private blk guiScreen;
    private TooltipProvider tooltipProvider;
    private int lastMouseX = 0;
    private int lastMouseY = 0;
    private long mouseStillTime = 0L;

    public TooltipManager(blk guiScreen, TooltipProvider tooltipProvider) {
        this.guiScreen = guiScreen;
        this.tooltipProvider = tooltipProvider;
    }

    public void drawTooltips(int x, int y, List buttonList) {
        if (Math.abs(x - this.lastMouseX) > 5 || Math.abs(y - this.lastMouseY) > 5) {
            this.lastMouseX = x;
            this.lastMouseY = y;
            this.mouseStillTime = System.currentTimeMillis();
            return;
        }
        int activateDelay = 700;
        if (System.currentTimeMillis() < this.mouseStillTime + (long)activateDelay) {
            return;
        }
        bja btn = GuiScreenOF.getSelectedButton(x, y, buttonList);
        if (btn == null) {
            return;
        }
        Rectangle rect = this.tooltipProvider.getTooltipBounds(this.guiScreen, x, y);
        String[] lines = this.tooltipProvider.getTooltipLines(btn, rect.width);
        if (lines == null) {
            return;
        }
        if (lines.length > 8) {
            lines = Arrays.copyOf(lines, 8);
            int n = lines.length - 1;
            lines[n] = lines[n] + " ...";
        }
        if (this.tooltipProvider.isRenderBorder()) {
            int colBorder = -528449408;
            this.drawRectBorder(rect.x, rect.y, rect.x + rect.width, rect.y + rect.height, colBorder);
        }
        bir.a((int)rect.x, (int)rect.y, (int)(rect.x + rect.width), (int)(rect.y + rect.height), (int)-536870912);
        for (int i = 0; i < lines.length; ++i) {
            String line = lines[i];
            int col = 0xDDDDDD;
            if (line.endsWith("!")) {
                col = 0xFF2020;
            }
            bip fontRenderer = bib.z().k;
            fontRenderer.a(line, (float)(rect.x + 5), (float)(rect.y + 5 + i * 11), col);
        }
    }

    private void drawRectBorder(int x1, int y1, int x2, int y2, int col) {
        bir.a((int)x1, (int)(y1 - 1), (int)x2, (int)y1, (int)col);
        bir.a((int)x1, (int)y2, (int)x2, (int)(y2 + 1), (int)col);
        bir.a((int)(x1 - 1), (int)y1, (int)x1, (int)y2, (int)col);
        bir.a((int)x2, (int)y1, (int)(x2 + 1), (int)y2, (int)col);
    }
}

