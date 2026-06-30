/*
 * Decompiled with CFR 0.152.
 */
package journeymap.client.ui.theme;

import java.awt.geom.Point2D;
import journeymap.client.Constants;
import journeymap.client.properties.MiniMapProperties;
import journeymap.client.render.draw.DrawUtil;
import journeymap.client.render.texture.TextureImpl;
import journeymap.client.ui.theme.Theme;

public class ThemeCompassPoints {
    final String textNorth = Constants.getString("jm.minimap.compass.n");
    final String textSouth = Constants.getString("jm.minimap.compass.s");
    final String textEast = Constants.getString("jm.minimap.compass.e");
    final String textWest = Constants.getString("jm.minimap.compass.w");
    final Point2D pointNorth;
    final Point2D pointSouth;
    final Point2D pointWest;
    final Point2D pointEast;
    final boolean showNorth;
    final boolean showSouth;
    final boolean showEast;
    final boolean showWest;
    final float fontScale;
    final int compassLabelHeight;
    final Theme.LabelSpec compassLabel;
    final Theme.ColorSpec compassPoint;
    final TextureImpl compassPointTex;
    final int xOffset;
    final int yOffset;
    final double shiftVert;
    final double shiftHorz;
    final int labelShiftVert;
    private double x;
    private double y;

    public ThemeCompassPoints(int x, int y, int halfWidth, int halfHeight, Theme.Minimap.MinimapSpec minimapSpec, MiniMapProperties miniMapProperties, TextureImpl compassPointTex, int labelHeight) {
        this.x = x;
        this.y = y;
        this.pointNorth = new Point2D.Double(x + halfWidth, y);
        this.pointSouth = new Point2D.Double(x + halfWidth, y + halfHeight + halfHeight);
        this.pointWest = new Point2D.Double(x, y + halfHeight);
        this.pointEast = new Point2D.Double(x + halfWidth + halfWidth, y + halfHeight);
        this.fontScale = miniMapProperties.compassFontScale.get().intValue();
        this.compassLabelHeight = labelHeight;
        this.compassLabel = minimapSpec.compassLabel;
        this.compassPoint = minimapSpec.compassPoint;
        this.compassPointTex = compassPointTex;
        if (this.compassPointTex != null) {
            this.shiftVert = minimapSpec.compassPointOffset * (double)this.fontScale;
            this.shiftHorz = minimapSpec.compassPointOffset * (double)this.fontScale;
            this.pointNorth.setLocation(this.pointNorth.getX(), this.pointNorth.getY() - this.shiftVert);
            this.pointSouth.setLocation(this.pointSouth.getX(), this.pointSouth.getY() + this.shiftVert);
            this.pointWest.setLocation(this.pointWest.getX() - this.shiftHorz, this.pointWest.getY());
            this.pointEast.setLocation(this.pointEast.getX() + this.shiftHorz, this.pointEast.getY());
            this.xOffset = (int)((float)compassPointTex.getWidth() * this.fontScale / 2.0f);
            this.yOffset = (int)((float)compassPointTex.getHeight() * this.fontScale / 2.0f);
        } else {
            this.xOffset = 0;
            this.yOffset = 0;
            this.shiftHorz = 0.0;
            this.shiftVert = 0.0;
        }
        this.labelShiftVert = 0;
        this.showNorth = minimapSpec.compassShowNorth;
        this.showSouth = minimapSpec.compassShowSouth;
        this.showEast = minimapSpec.compassShowEast;
        this.showWest = minimapSpec.compassShowWest;
    }

    public static float getCompassPointScale(int compassLabelHeight, Theme.Minimap.MinimapSpec minimapSpec, TextureImpl compassPointTex) {
        return (float)(compassLabelHeight + minimapSpec.compassPointLabelPad) / ((float)compassPointTex.getHeight() * 1.0f);
    }

    public void setPosition(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void drawPoints(double rotation) {
        if (this.compassPointTex != null) {
            int color = this.compassPoint.getColor();
            float alpha = this.compassPoint.alpha;
            if (this.showNorth) {
                DrawUtil.drawColoredImage(this.compassPointTex, color, alpha, this.pointNorth.getX() - (double)this.xOffset, this.pointNorth.getY() - (double)this.yOffset, this.fontScale, 0.0);
            }
            if (this.showSouth) {
                DrawUtil.drawColoredImage(this.compassPointTex, color, alpha, this.pointSouth.getX() - (double)this.xOffset, this.pointSouth.getY() - (double)this.yOffset, this.fontScale, 180.0);
            }
            if (this.showWest) {
                DrawUtil.drawColoredImage(this.compassPointTex, color, alpha, this.pointWest.getX() - (double)this.xOffset, this.pointWest.getY() - (double)this.yOffset, this.fontScale, -90.0);
            }
            if (this.showEast) {
                DrawUtil.drawColoredImage(this.compassPointTex, color, alpha, this.pointEast.getX() - (double)this.xOffset, this.pointEast.getY() - (double)this.yOffset, this.fontScale, 90.0);
            }
        }
    }

    public void drawLabels(double rotation) {
        if (this.showNorth) {
            DrawUtil.drawLabel(this.textNorth, this.compassLabel, this.pointNorth.getX(), this.pointNorth.getY() + (double)this.labelShiftVert, DrawUtil.HAlign.Center, DrawUtil.VAlign.Middle, this.fontScale, rotation);
        }
        if (this.showSouth) {
            DrawUtil.drawLabel(this.textSouth, this.compassLabel, this.pointSouth.getX(), this.pointSouth.getY() + (double)this.labelShiftVert, DrawUtil.HAlign.Center, DrawUtil.VAlign.Middle, this.fontScale, rotation);
        }
        if (this.showWest) {
            DrawUtil.drawLabel(this.textWest, this.compassLabel, this.pointWest.getX(), this.pointWest.getY() + (double)this.labelShiftVert, DrawUtil.HAlign.Center, DrawUtil.VAlign.Middle, this.fontScale, rotation);
        }
        if (this.showEast) {
            DrawUtil.drawLabel(this.textEast, this.compassLabel, this.pointEast.getX(), this.pointEast.getY() + (double)this.labelShiftVert, DrawUtil.HAlign.Center, DrawUtil.VAlign.Middle, this.fontScale, rotation);
        }
    }
}

