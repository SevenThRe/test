/*
 * Decompiled with CFR 0.152.
 */
package journeymap.client.render.draw;

import journeymap.client.render.map.GridRenderer;

public interface DrawStep {
    public void draw(Pass var1, double var2, double var4, GridRenderer var6, double var7, double var9);

    public int getDisplayOrder();

    public String getModId();

    public static enum Pass {
        Object,
        Text,
        Tooltip;

    }
}

