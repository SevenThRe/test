/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  bja
 *  blk
 */
package net.optifine.gui;

import java.awt.Rectangle;

public interface TooltipProvider {
    public Rectangle getTooltipBounds(blk var1, int var2, int var3);

    public String[] getTooltipLines(bja var1, int var2);

    public boolean isRenderBorder();
}

