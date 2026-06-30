/*
 * Decompiled with CFR 0.152.
 */
package journeymap.client.render.draw;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import journeymap.client.api.display.MarkerOverlay;
import journeymap.client.api.model.MapImage;
import journeymap.client.api.model.TextProperties;
import journeymap.client.render.draw.BaseOverlayDrawStep;
import journeymap.client.render.draw.DrawStep;
import journeymap.client.render.draw.DrawUtil;
import journeymap.client.render.map.GridRenderer;
import journeymap.client.render.texture.TextureCache;
import journeymap.client.render.texture.TextureImpl;
import journeymap.common.Journeymap;

public class DrawMarkerStep
extends BaseOverlayDrawStep<MarkerOverlay> {
    private Point2D.Double markerPosition;
    private volatile Future<TextureImpl> iconFuture;
    private TextureImpl iconTexture;
    private boolean hasError;

    public DrawMarkerStep(MarkerOverlay marker) {
        super(marker);
    }

    @Override
    public void draw(DrawStep.Pass pass, double xOffset, double yOffset, GridRenderer gridRenderer, double fontScale, double rotation) {
        if (!this.isOnScreen(xOffset, yOffset, gridRenderer, rotation)) {
            return;
        }
        if (pass == DrawStep.Pass.Object) {
            this.ensureTexture();
            if (!this.hasError && this.iconTexture != null) {
                MapImage icon = ((MarkerOverlay)this.overlay).getIcon();
                DrawUtil.drawColoredSprite(this.iconTexture, icon.getDisplayWidth(), icon.getDisplayHeight(), icon.getTextureX(), icon.getTextureY(), icon.getTextureWidth(), icon.getTextureHeight(), icon.getColor(), icon.getOpacity(), this.markerPosition.x + xOffset - icon.getAnchorX(), this.markerPosition.y + yOffset - icon.getAnchorY(), 1.0f, (double)icon.getRotation() - rotation);
            }
        } else {
            super.drawText(pass, xOffset, yOffset, gridRenderer, fontScale, rotation);
        }
    }

    protected void ensureTexture() {
        if (this.iconTexture != null) {
            return;
        }
        try {
            if (this.iconFuture == null || this.iconFuture.isCancelled()) {
                this.iconFuture = TextureCache.scheduleTextureTask(new Callable<TextureImpl>(){

                    @Override
                    public TextureImpl call() throws Exception {
                        MapImage icon = ((MarkerOverlay)DrawMarkerStep.this.overlay).getIcon();
                        if (icon.getImageLocation() != null) {
                            return TextureCache.getTexture(icon.getImageLocation());
                        }
                        if (icon.getImage() != null) {
                            return new TextureImpl(icon.getImage());
                        }
                        return null;
                    }
                });
            } else if (this.iconFuture.isDone()) {
                this.iconTexture = this.iconFuture.get();
                if (this.iconTexture.isBindNeeded()) {
                    this.iconTexture.bindTexture();
                }
                this.iconFuture = null;
            }
        }
        catch (Exception e) {
            Journeymap.getLogger().error("Error getting MarkerOverlay image upperTexture: " + e, (Throwable)e);
            this.hasError = true;
        }
    }

    @Override
    protected void updatePositions(GridRenderer gridRenderer, double rotation) {
        int yShift;
        MapImage icon = ((MarkerOverlay)this.overlay).getIcon();
        this.markerPosition = gridRenderer.getBlockPixelInGrid(((MarkerOverlay)this.overlay).getPoint());
        int halfBlock = (int)this.lastUiState.blockSize / 2;
        this.markerPosition.setLocation(this.markerPosition.x + (double)halfBlock, this.markerPosition.y + (double)halfBlock);
        TextProperties textProperties = ((MarkerOverlay)this.overlay).getTextProperties();
        int xShift = rotation % 360.0 == 0.0 ? -textProperties.getOffsetX() : textProperties.getOffsetX();
        int n = yShift = rotation % 360.0 == 0.0 ? -textProperties.getOffsetY() : textProperties.getOffsetY();
        if (xShift != 0 && yShift != 0) {
            Point2D shiftedPoint = gridRenderer.shiftWindowPosition(this.markerPosition.x, this.markerPosition.y, xShift, yShift);
            this.labelPosition.setLocation(shiftedPoint.getX(), shiftedPoint.getY());
        } else {
            this.labelPosition.setLocation(this.markerPosition.x, this.markerPosition.y);
        }
        this.screenBounds.setRect(this.markerPosition.x, this.markerPosition.y, this.lastUiState.blockSize, this.lastUiState.blockSize);
        this.screenBounds.add(this.labelPosition);
        Rectangle2D.Double iconBounds = new Rectangle2D.Double(this.markerPosition.x - icon.getAnchorX(), this.markerPosition.y - icon.getAnchorY(), icon.getDisplayWidth(), icon.getDisplayHeight());
        this.screenBounds.add(iconBounds);
    }
}

