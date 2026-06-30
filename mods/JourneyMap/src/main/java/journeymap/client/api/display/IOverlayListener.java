/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.math.BlockPos
 */
package journeymap.client.api.display;

import java.awt.geom.Point2D;
import journeymap.client.api.util.UIState;
import net.minecraft.util.math.BlockPos;

public interface IOverlayListener {
    public void onActivate(UIState var1);

    public void onDeactivate(UIState var1);

    public void onMouseMove(UIState var1, Point2D.Double var2, BlockPos var3);

    public void onMouseOut(UIState var1, Point2D.Double var2, BlockPos var3);

    public boolean onMouseClick(UIState var1, Point2D.Double var2, BlockPos var3, int var4, boolean var5);
}

