/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package journeymap.client.render.draw;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import javax.annotation.Nullable;
import journeymap.client.api.display.Overlay;
import journeymap.client.render.draw.DrawStep;
import journeymap.client.render.map.GridRenderer;

public interface OverlayDrawStep
extends DrawStep {
    public Overlay getOverlay();

    public Rectangle2D.Double getBounds();

    public boolean isOnScreen(double var1, double var3, GridRenderer var5, double var6);

    public void setTitlePosition(@Nullable Point2D.Double var1);

    public void setEnabled(boolean var1);
}

