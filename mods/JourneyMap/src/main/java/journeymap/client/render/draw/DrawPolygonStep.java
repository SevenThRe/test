/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.math.BlockPos
 */
package journeymap.client.render.draw;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import journeymap.client.api.display.PolygonOverlay;
import journeymap.client.api.model.TextProperties;
import journeymap.client.render.draw.BaseOverlayDrawStep;
import journeymap.client.render.draw.DrawStep;
import journeymap.client.render.draw.DrawUtil;
import journeymap.client.render.map.GridRenderer;
import net.minecraft.util.math.BlockPos;

public class DrawPolygonStep
extends BaseOverlayDrawStep<PolygonOverlay> {
    protected List<Point2D.Double> screenPoints = new ArrayList<Point2D.Double>();
    boolean onScreen;

    public DrawPolygonStep(PolygonOverlay polygon) {
        super(polygon);
    }

    @Override
    public void draw(DrawStep.Pass pass, double xOffset, double yOffset, GridRenderer gridRenderer, double fontScale, double rotation) {
        if (pass == DrawStep.Pass.Object) {
            if (((PolygonOverlay)this.overlay).getOuterArea().getPoints().isEmpty()) {
                this.onScreen = false;
                return;
            }
            this.onScreen = this.isOnScreen(xOffset, yOffset, gridRenderer, rotation);
            if (this.onScreen) {
                DrawUtil.drawPolygon(xOffset, yOffset, this.screenPoints, ((PolygonOverlay)this.overlay).getShapeProperties());
            }
        } else if (this.onScreen) {
            super.drawText(pass, xOffset, yOffset, gridRenderer, fontScale, rotation);
        }
    }

    @Override
    protected void updatePositions(GridRenderer gridRenderer, double rotation) {
        if (((PolygonOverlay)this.overlay).getOuterArea().getPoints().isEmpty()) {
            this.onScreen = false;
            return;
        }
        List<BlockPos> points = ((PolygonOverlay)this.overlay).getOuterArea().getPoints();
        this.screenPoints.clear();
        for (BlockPos pos : points) {
            Point2D.Double pixel = gridRenderer.getBlockPixelInGrid(pos);
            pixel.setLocation(pixel.getX(), pixel.getY());
            if (this.screenPoints.isEmpty()) {
                this.screenBounds.setRect(pixel.x, pixel.y, 1.0, 1.0);
            } else {
                this.screenBounds.add(pixel);
            }
            this.screenPoints.add(pixel);
        }
        TextProperties textProperties = ((PolygonOverlay)this.overlay).getTextProperties();
        this.labelPosition.setLocation(this.screenBounds.getCenterX() + (double)textProperties.getOffsetX(), this.screenBounds.getCenterY() + (double)textProperties.getOffsetY());
    }
}

