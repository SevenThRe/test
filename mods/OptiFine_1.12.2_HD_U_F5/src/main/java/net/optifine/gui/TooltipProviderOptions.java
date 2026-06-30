/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  bid$a
 *  bja
 *  blk
 */
package net.optifine.gui;

import java.awt.Rectangle;
import java.util.ArrayList;
import net.optifine.Lang;
import net.optifine.gui.IOptionControl;
import net.optifine.gui.TooltipProvider;

public class TooltipProviderOptions
implements TooltipProvider {
    @Override
    public Rectangle getTooltipBounds(blk guiScreen, int x, int y) {
        int x1 = guiScreen.l / 2 - 150;
        int y1 = guiScreen.m / 6 - 7;
        if (y <= y1 + 98) {
            y1 += 105;
        }
        int x2 = x1 + 150 + 150;
        int y2 = y1 + 84 + 10;
        return new Rectangle(x1, y1, x2 - x1, y2 - y1);
    }

    @Override
    public boolean isRenderBorder() {
        return false;
    }

    @Override
    public String[] getTooltipLines(bja btn, int width) {
        if (!(btn instanceof IOptionControl)) {
            return null;
        }
        IOptionControl ctl = (IOptionControl)btn;
        bid.a option = ctl.getOption();
        String[] lines = TooltipProviderOptions.getTooltipLines(option.d());
        return lines;
    }

    public static String[] getTooltipLines(String key) {
        String lineKey;
        String line;
        ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < 10 && (line = Lang.get(lineKey = key + ".tooltip." + (i + 1), null)) != null; ++i) {
            list.add(line);
        }
        if (list.size() <= 0) {
            return null;
        }
        String[] lines = list.toArray(new String[list.size()]);
        return lines;
    }
}

