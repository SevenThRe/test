/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.gui.ScaledResolution
 *  net.minecraft.util.Tuple
 */
package journeymap.client.ui.minimap;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import journeymap.client.io.ThemeLoader;
import journeymap.client.model.MapType;
import journeymap.client.properties.MiniMapProperties;
import journeymap.client.render.draw.DrawUtil;
import journeymap.client.render.texture.TextureCache;
import journeymap.client.render.texture.TextureImpl;
import journeymap.client.ui.minimap.LabelVars;
import journeymap.client.ui.minimap.Orientation;
import journeymap.client.ui.minimap.Position;
import journeymap.client.ui.minimap.Shape;
import journeymap.client.ui.option.LocationFormat;
import journeymap.client.ui.theme.Theme;
import journeymap.client.ui.theme.ThemeCompassPoints;
import journeymap.client.ui.theme.ThemeLabelSource;
import journeymap.client.ui.theme.ThemeMinimapFrame;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.Tuple;

public class DisplayVars {
    final Position position;
    final Shape shape;
    final Orientation orientation;
    final double fontScale;
    final int displayWidth;
    final int displayHeight;
    final float terrainAlpha;
    final ScaledResolution scaledResolution;
    final int minimapWidth;
    final int minimapHeight;
    final int textureX;
    final int textureY;
    final int translateX;
    final int translateY;
    final double reticleSegmentLength;
    final Point2D.Double centerPoint;
    final boolean showCompass;
    final boolean showReticle;
    final List<Tuple<LabelVars, ThemeLabelSource>> labels = new ArrayList<Tuple<LabelVars, ThemeLabelSource>>(4);
    final Theme theme;
    final ThemeMinimapFrame minimapFrame;
    final ThemeCompassPoints minimapCompassPoints;
    final Theme.Minimap.MinimapSpec minimapSpec;
    final LocationFormat.LocationFormatKeys locationFormatKeys;
    final boolean locationFormatVerbose;
    final boolean frameRotates;
    int marginX;
    int marginY;
    MapTypeStatus mapTypeStatus;
    MapPresetStatus mapPresetStatus;

    DisplayVars(Minecraft mc, MiniMapProperties miniMapProperties) {
        int startY;
        this.scaledResolution = new ScaledResolution(mc);
        this.showCompass = miniMapProperties.showCompass.get();
        this.showReticle = miniMapProperties.showReticle.get();
        this.position = (Position)miniMapProperties.position.get();
        this.orientation = (Orientation)miniMapProperties.orientation.get();
        this.displayWidth = mc.displayWidth;
        this.displayHeight = mc.displayHeight;
        this.terrainAlpha = Math.max(0.0f, Math.min(1.0f, (float)miniMapProperties.terrainAlpha.get().intValue() / 100.0f));
        this.locationFormatKeys = new LocationFormat().getFormatKeys(miniMapProperties.locationFormat.get());
        this.locationFormatVerbose = miniMapProperties.locationFormatVerbose.get();
        this.theme = ThemeLoader.getCurrentTheme();
        switch ((Shape)miniMapProperties.shape.get()) {
            case Rectangle: {
                if (this.theme.minimap.square != null) {
                    this.shape = Shape.Rectangle;
                    this.minimapSpec = this.theme.minimap.square;
                    double ratio = (double)mc.displayWidth * 1.0 / (double)mc.displayHeight;
                    this.minimapHeight = miniMapProperties.getSize();
                    this.minimapWidth = (int)((double)this.minimapHeight * ratio);
                    this.reticleSegmentLength = (double)this.minimapWidth / 1.5;
                    break;
                }
            }
            case Circle: {
                if (this.theme.minimap.circle != null) {
                    this.shape = Shape.Circle;
                    this.minimapSpec = this.theme.minimap.circle;
                    this.minimapWidth = miniMapProperties.getSize();
                    this.minimapHeight = miniMapProperties.getSize();
                    this.reticleSegmentLength = this.minimapHeight / 2;
                    break;
                }
            }
            default: {
                this.shape = Shape.Square;
                this.minimapSpec = this.theme.minimap.square;
                this.minimapWidth = miniMapProperties.getSize();
                this.minimapHeight = miniMapProperties.getSize();
                this.reticleSegmentLength = Math.sqrt(this.minimapHeight * this.minimapHeight + this.minimapWidth * this.minimapWidth) / 2.0;
            }
        }
        this.fontScale = miniMapProperties.fontScale.get().intValue();
        FontRenderer fontRenderer = mc.fontRenderer;
        int topInfoLabelsHeight = this.getInfoLabelAreaHeight(fontRenderer, this.minimapSpec.labelTop, (ThemeLabelSource)miniMapProperties.info1Label.get(), (ThemeLabelSource)miniMapProperties.info2Label.get());
        int bottomInfoLabelsHeight = this.getInfoLabelAreaHeight(fontRenderer, this.minimapSpec.labelBottom, (ThemeLabelSource)miniMapProperties.info3Label.get(), (ThemeLabelSource)miniMapProperties.info4Label.get());
        int compassFontScale = miniMapProperties.compassFontScale.get();
        int compassLabelHeight = 0;
        if (this.showCompass) {
            compassLabelHeight = DrawUtil.getLabelHeight(fontRenderer, this.minimapSpec.compassLabel.shadow) * compassFontScale;
        }
        this.minimapFrame = new ThemeMinimapFrame(this.theme, this.minimapSpec, miniMapProperties, this.minimapWidth, this.minimapHeight);
        this.marginX = this.marginY = this.minimapSpec.margin;
        int halfWidth = this.minimapWidth / 2;
        int halfHeight = this.minimapHeight / 2;
        if (this.showCompass) {
            double compassPointMargin;
            boolean compassExists;
            boolean bl = compassExists = this.minimapSpec.compassPoint != null && this.minimapSpec.compassPoint.width > 0;
            if (compassExists) {
                TextureImpl compassPointTex = this.minimapFrame.getCompassPoint();
                float compassPointScale = ThemeCompassPoints.getCompassPointScale(compassLabelHeight, this.minimapSpec, compassPointTex);
                compassPointMargin = (float)(compassPointTex.getWidth() / 2) * compassPointScale;
            } else {
                compassPointMargin = compassLabelHeight;
            }
            this.marginX = (int)Math.max((double)this.marginX, Math.ceil(compassPointMargin));
            this.marginY = (int)Math.max((double)this.marginY, Math.ceil(compassPointMargin) + (double)(compassLabelHeight / 2));
        }
        switch (this.position) {
            case BottomRight: {
                if (!this.minimapSpec.labelBottomInside) {
                    this.marginY += bottomInfoLabelsHeight;
                }
                this.textureX = mc.displayWidth - this.minimapWidth - this.marginX;
                this.textureY = mc.displayHeight - this.minimapHeight - this.marginY;
                this.translateX = mc.displayWidth / 2 - halfWidth - this.marginX;
                this.translateY = mc.displayHeight / 2 - halfHeight - this.marginY;
                break;
            }
            case TopLeft: {
                if (!this.minimapSpec.labelTopInside) {
                    this.marginY = Math.max(this.marginY, topInfoLabelsHeight + 2 * this.minimapSpec.margin);
                }
                this.textureX = this.marginX;
                this.textureY = this.marginY;
                this.translateX = -(mc.displayWidth / 2) + halfWidth + this.marginX;
                this.translateY = -(mc.displayHeight / 2) + halfHeight + this.marginY;
                break;
            }
            case BottomLeft: {
                if (!this.minimapSpec.labelBottomInside) {
                    this.marginY += bottomInfoLabelsHeight;
                }
                this.textureX = this.marginX;
                this.textureY = mc.displayHeight - this.minimapHeight - this.marginY;
                this.translateX = -(mc.displayWidth / 2) + halfWidth + this.marginX;
                this.translateY = mc.displayHeight / 2 - halfHeight - this.marginY;
                break;
            }
            case TopCenter: {
                if (!this.minimapSpec.labelTopInside) {
                    this.marginY = Math.max(this.marginY, topInfoLabelsHeight + 2 * this.minimapSpec.margin);
                }
                this.textureX = (mc.displayWidth - this.minimapWidth) / 2;
                this.textureY = this.marginY;
                this.translateX = 0;
                this.translateY = -(mc.displayHeight / 2) + halfHeight + this.marginY;
                break;
            }
            case Center: {
                this.textureX = (mc.displayWidth - this.minimapWidth) / 2;
                this.textureY = (mc.displayHeight - this.minimapHeight) / 2;
                this.translateX = 0;
                this.translateY = 0;
                break;
            }
            default: {
                if (!this.minimapSpec.labelTopInside) {
                    this.marginY = Math.max(this.marginY, topInfoLabelsHeight + 2 * this.minimapSpec.margin);
                }
                this.textureX = mc.displayWidth - this.minimapWidth - this.marginX;
                this.textureY = this.marginY;
                this.translateX = mc.displayWidth / 2 - halfWidth - this.marginX;
                this.translateY = -(mc.displayHeight / 2) + halfHeight + this.marginY;
            }
        }
        this.minimapFrame.setPosition(this.textureX, this.textureY);
        this.centerPoint = new Point2D.Double(this.textureX + halfWidth, this.textureY + halfHeight);
        this.minimapCompassPoints = new ThemeCompassPoints(this.textureX, this.textureY, halfWidth, halfHeight, this.minimapSpec, miniMapProperties, this.minimapFrame.getCompassPoint(), compassLabelHeight);
        this.frameRotates = this.shape == Shape.Circle ? ((Theme.Minimap.MinimapCircle)this.minimapSpec).rotates : false;
        int centerX = (int)Math.floor(this.textureX + this.minimapWidth / 2);
        if (topInfoLabelsHeight > 0) {
            startY = this.minimapSpec.labelTopInside ? this.textureY + this.minimapSpec.margin : this.textureY - this.minimapSpec.margin - topInfoLabelsHeight;
            this.positionLabels(fontRenderer, centerX, startY, this.minimapSpec.labelTop, (ThemeLabelSource)miniMapProperties.info1Label.get(), (ThemeLabelSource)miniMapProperties.info2Label.get());
        }
        if (bottomInfoLabelsHeight > 0) {
            startY = this.textureY + this.minimapHeight;
            this.positionLabels(fontRenderer, centerX, startY += this.minimapSpec.labelBottomInside ? -this.minimapSpec.margin - bottomInfoLabelsHeight : this.minimapSpec.margin, this.minimapSpec.labelBottom, (ThemeLabelSource)miniMapProperties.info3Label.get(), (ThemeLabelSource)miniMapProperties.info4Label.get());
        }
        ThemeLabelSource.resetCaches();
    }

    private int getInfoLabelAreaHeight(FontRenderer fontRenderer, Theme.LabelSpec labelSpec, ThemeLabelSource ... themeLabelSources) {
        int labelHeight = this.getInfoLabelHeight(fontRenderer, labelSpec);
        int areaHeight = 0;
        for (ThemeLabelSource themeLabelSource : themeLabelSources) {
            areaHeight += themeLabelSource.isShown() ? labelHeight : 0;
        }
        return areaHeight;
    }

    private int getInfoLabelHeight(FontRenderer fontRenderer, Theme.LabelSpec labelSpec) {
        return (int)((double)(DrawUtil.getLabelHeight(fontRenderer, labelSpec.shadow) + labelSpec.margin) * this.fontScale);
    }

    private void positionLabels(FontRenderer fontRenderer, int centerX, int startY, Theme.LabelSpec labelSpec, ThemeLabelSource ... themeLabelSources) {
        int labelHeight = this.getInfoLabelHeight(fontRenderer, labelSpec);
        int labelY = startY;
        for (ThemeLabelSource themeLabelSource : themeLabelSources) {
            if (!themeLabelSource.isShown()) continue;
            LabelVars labelVars = new LabelVars(this, centerX, labelY, DrawUtil.HAlign.Center, DrawUtil.VAlign.Below, this.fontScale, labelSpec);
            Tuple tuple = new Tuple((Object)labelVars, (Object)themeLabelSource);
            this.labels.add((Tuple<LabelVars, ThemeLabelSource>)tuple);
            labelY += labelHeight;
        }
    }

    public void drawInfoLabels(long currentTimeMillis) {
        for (Tuple<LabelVars, ThemeLabelSource> label : this.labels) {
            ((LabelVars)label.getFirst()).draw(((ThemeLabelSource)label.getSecond()).getLabelText(currentTimeMillis));
        }
    }

    MapPresetStatus getMapPresetStatus(MapType mapType, int miniMapId) {
        if (this.mapPresetStatus == null || !mapType.equals(this.mapPresetStatus.mapType) || miniMapId != this.mapPresetStatus.miniMapId) {
            this.mapPresetStatus = new MapPresetStatus(mapType, miniMapId);
        }
        return this.mapPresetStatus;
    }

    MapTypeStatus getMapTypeStatus(MapType mapType) {
        if (this.mapTypeStatus == null || !mapType.equals(this.mapTypeStatus.mapType)) {
            this.mapTypeStatus = new MapTypeStatus(mapType);
        }
        return this.mapTypeStatus;
    }

    class MapTypeStatus {
        private MapType mapType;
        private String name;
        private TextureImpl tex;
        private Integer color;
        private Integer opposite;
        private double x;
        private double y;
        private float bgScale;
        private float scaleHeightOffset;

        MapTypeStatus(MapType mapType) {
            this.mapType = mapType;
            this.name = mapType.isUnderground() ? "caves" : mapType.name();
            this.tex = TextureCache.getThemeTexture(DisplayVars.this.theme, String.format("icon/%s.png", this.name));
            this.color = 0xFFFFFF;
            this.opposite = 0x404040;
            this.bgScale = 1.15f;
            this.scaleHeightOffset = ((float)this.tex.getHeight() * this.bgScale - (float)this.tex.getHeight()) / 2.0f;
        }

        void draw(Point2D.Double mapCenter, float alpha, double rotation) {
            this.x = mapCenter.getX() - (double)(this.tex.getWidth() / 2);
            this.y = mapCenter.getY() - (double)this.tex.getHeight() - 8.0;
            DrawUtil.drawColoredImage(this.tex, this.opposite, alpha, mapCenter.getX() - (double)((float)this.tex.getWidth() * this.bgScale / 2.0f), mapCenter.getY() - (double)((float)this.tex.getHeight() * this.bgScale) + (double)this.scaleHeightOffset - 8.0, this.bgScale, rotation);
            DrawUtil.drawColoredImage(this.tex, this.color, alpha, this.x, this.y, 1.0f, 0.0);
        }
    }

    class MapPresetStatus {
        private int miniMapId;
        private int scale = 4;
        private MapType mapType;
        private String name;
        private Integer color;

        MapPresetStatus(MapType mapType, int miniMapId) {
            this.miniMapId = miniMapId;
            this.mapType = mapType;
            this.color = 0xFFFFFF;
            this.name = Integer.toString(miniMapId);
        }

        void draw(Point2D.Double mapCenter, float alpha, double rotation) {
            DrawUtil.drawLabel(this.name, mapCenter.getX(), mapCenter.getY() + 8.0, DrawUtil.HAlign.Center, DrawUtil.VAlign.Below, 0, 0.0f, this.color, alpha, this.scale, true, rotation);
        }
    }
}

