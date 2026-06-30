/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.cache.CacheLoader
 */
package journeymap.client.render.draw;

import com.google.common.cache.CacheLoader;
import java.awt.geom.Point2D;
import journeymap.client.model.Waypoint;
import journeymap.client.render.draw.DrawStep;
import journeymap.client.render.draw.DrawUtil;
import journeymap.client.render.map.GridRenderer;
import journeymap.client.render.texture.TextureCache;
import journeymap.client.render.texture.TextureImpl;
import journeymap.common.Journeymap;

public class DrawWayPointStep
implements DrawStep {
    public final Waypoint waypoint;
    final Integer color;
    final Integer fontColor;
    final TextureImpl texture;
    final boolean isEdit;
    Point2D.Double lastPosition;
    boolean lastOnScreen;
    boolean showLabel;

    public DrawWayPointStep(Waypoint waypoint) {
        this(waypoint, waypoint.getColor(), waypoint.isDeathPoint() ? 0xFF0000 : waypoint.getSafeColor(), false);
    }

    public DrawWayPointStep(Waypoint waypoint, Integer color, Integer fontColor, boolean isEdit) {
        this.waypoint = waypoint;
        this.color = color;
        this.fontColor = fontColor;
        this.isEdit = isEdit;
        this.texture = waypoint.getTexture();
    }

    public void setShowLabel(boolean showLabel) {
        this.showLabel = showLabel;
    }

    @Override
    public void draw(DrawStep.Pass pass, double xOffset, double yOffset, GridRenderer gridRenderer, double fontScale, double rotation) {
        if (!this.waypoint.isInPlayerDimension()) {
            return;
        }
        Point2D.Double pixel = this.getPosition(xOffset, yOffset, gridRenderer, true);
        if (gridRenderer.isOnScreen(pixel)) {
            if (this.showLabel && pass == DrawStep.Pass.Text) {
                Point2D labelPoint = gridRenderer.shiftWindowPosition(pixel.getX(), pixel.getY(), 0, rotation == 0.0 ? -this.texture.getHeight() : this.texture.getHeight());
                String waypointName = this.waypoint.getName();
                if (this.waypoint.isDeathPoint() && !Journeymap.getClient().getWaypointProperties().showDeathpointlabel.get().booleanValue()) {
                    waypointName = "";
                }
                DrawUtil.drawLabel(waypointName, labelPoint.getX(), labelPoint.getY(), DrawUtil.HAlign.Center, DrawUtil.VAlign.Middle, 0, 0.7f, this.fontColor, 1.0f, fontScale, false, rotation);
            } else if (this.isEdit && pass == DrawStep.Pass.Object) {
                TextureImpl editTex = TextureCache.getTexture(TextureCache.WaypointEdit);
                DrawUtil.drawColoredImage(editTex, this.color, 1.0f, pixel.getX() - (double)(editTex.getWidth() / 2), pixel.getY() - (double)(editTex.getHeight() / 2), -rotation);
            }
            if (pass == DrawStep.Pass.Object) {
                DrawUtil.drawColoredImage(this.texture, this.color, 1.0f, pixel.getX() - (double)(this.texture.getWidth() / 2), pixel.getY() - (double)(this.texture.getHeight() / 2), -rotation);
            }
        } else if (!this.isEdit && pass == DrawStep.Pass.Object) {
            gridRenderer.ensureOnScreen(pixel);
            DrawUtil.drawColoredImage(this.texture, this.color, 1.0f, pixel.getX() - (double)(this.texture.getWidth() / 2), pixel.getY() - (double)(this.texture.getHeight() / 2), -rotation);
        }
    }

    public void drawOffscreen(DrawStep.Pass pass, Point2D pixel, double rotation) {
        if (pass == DrawStep.Pass.Object) {
            DrawUtil.drawColoredImage(this.texture, this.color, 1.0f, pixel.getX() - (double)(this.texture.getWidth() / 2), pixel.getY() - (double)(this.texture.getHeight() / 2), -rotation);
        }
    }

    public Point2D.Double getPosition(double xOffset, double yOffset, GridRenderer gridRenderer, boolean forceUpdate) {
        if (!forceUpdate && this.lastPosition != null) {
            return this.lastPosition;
        }
        double x = this.waypoint.getX();
        double z = this.waypoint.getZ();
        double halfBlock = Math.pow(2.0, gridRenderer.getZoom()) / 2.0;
        Point2D.Double pixel = gridRenderer.getBlockPixelInGrid(x, z);
        pixel.setLocation(pixel.getX() + halfBlock + xOffset, pixel.getY() + halfBlock + yOffset);
        this.lastPosition = pixel;
        return pixel;
    }

    public boolean isOnScreen() {
        return this.lastOnScreen;
    }

    public void setOnScreen(boolean lastOnScreen) {
        this.lastOnScreen = lastOnScreen;
    }

    @Override
    public int getDisplayOrder() {
        return 0;
    }

    @Override
    public String getModId() {
        return this.waypoint.getOrigin();
    }

    public static class SimpleCacheLoader
    extends CacheLoader<Waypoint, DrawWayPointStep> {
        public DrawWayPointStep load(Waypoint waypoint) throws Exception {
            return new DrawWayPointStep(waypoint);
        }
    }
}

