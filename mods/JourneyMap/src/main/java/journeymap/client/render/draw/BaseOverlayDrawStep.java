/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Strings
 *  javax.annotation.Nullable
 */
package journeymap.client.render.draw;

import com.google.common.base.Strings;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.Objects;
import javax.annotation.Nullable;
import journeymap.client.api.display.Context;
import journeymap.client.api.display.Displayable;
import journeymap.client.api.display.Overlay;
import journeymap.client.api.model.TextProperties;
import journeymap.client.api.util.UIState;
import journeymap.client.render.draw.DrawStep;
import journeymap.client.render.draw.DrawUtil;
import journeymap.client.render.draw.OverlayDrawStep;
import journeymap.client.render.map.GridRenderer;

public abstract class BaseOverlayDrawStep<T extends Overlay>
implements OverlayDrawStep {
    public final T overlay;
    protected Rectangle2D.Double screenBounds = new Rectangle2D.Double();
    protected Point2D.Double titlePosition = new Point2D.Double();
    protected Point2D.Double labelPosition = new Point2D.Double();
    protected UIState lastUiState = null;
    protected boolean dragging = false;
    protected boolean enabled = true;
    protected String[] labelLines;
    protected String[] titleLines;

    protected BaseOverlayDrawStep(T overlay) {
        this.overlay = overlay;
    }

    protected abstract void updatePositions(GridRenderer var1, double var2);

    protected void drawText(DrawStep.Pass pass, double xOffset, double yOffset, GridRenderer gridRenderer, double fontScale, double rotation) {
        TextProperties textProperties = ((Overlay)this.overlay).getTextProperties();
        if (textProperties.isActiveIn(gridRenderer.getUIState())) {
            if (pass == DrawStep.Pass.Text) {
                if (this.labelPosition != null) {
                    if (this.labelLines == null) {
                        this.updateTextFields();
                    }
                    if (this.labelLines != null) {
                        double x = this.labelPosition.x + xOffset;
                        double y = this.labelPosition.y + yOffset;
                        DrawUtil.drawLabels(this.labelLines, x, y, DrawUtil.HAlign.Center, DrawUtil.VAlign.Middle, textProperties.getBackgroundColor(), textProperties.getBackgroundOpacity(), textProperties.getColor(), textProperties.getOpacity(), (double)textProperties.getScale() * fontScale, textProperties.hasFontShadow(), rotation);
                    }
                }
            } else if (pass == DrawStep.Pass.Tooltip && this.titlePosition != null) {
                if (this.titleLines == null) {
                    this.updateTextFields();
                }
                if (this.titleLines != null) {
                    double x = this.titlePosition.x + 5.0 + xOffset;
                    double y = this.titlePosition.y + yOffset;
                    DrawUtil.drawLabels(this.titleLines, x, y, DrawUtil.HAlign.Right, DrawUtil.VAlign.Above, textProperties.getBackgroundColor(), textProperties.getBackgroundOpacity(), textProperties.getColor(), textProperties.getOpacity(), (double)textProperties.getScale() * fontScale, textProperties.hasFontShadow(), rotation);
                }
            }
        }
    }

    @Override
    public boolean isOnScreen(double xOffset, double yOffset, GridRenderer gridRenderer, double rotation) {
        if (!this.enabled) {
            return false;
        }
        UIState uiState = gridRenderer.getUIState();
        if (!((Overlay)this.overlay).isActiveIn(uiState)) {
            return false;
        }
        boolean draggingDone = false;
        if (xOffset != 0.0 || yOffset != 0.0) {
            this.dragging = true;
        } else {
            draggingDone = this.dragging;
            this.dragging = false;
        }
        if (draggingDone || uiState.ui == Context.UI.Minimap || ((Overlay)this.overlay).getNeedsRerender() || !Objects.equals(uiState, this.lastUiState)) {
            this.lastUiState = uiState;
            this.updatePositions(gridRenderer, rotation);
            ((Overlay)this.overlay).clearFlagForRerender();
        }
        if (this.screenBounds == null) {
            return false;
        }
        return gridRenderer.isOnScreen(this.screenBounds);
    }

    protected void updateTextFields() {
        if (this.labelPosition != null) {
            String labelText = ((Overlay)this.overlay).getLabel();
            this.labelLines = !Strings.isNullOrEmpty((String)labelText) ? labelText.split("\n") : null;
        }
        if (this.titlePosition != null) {
            String titleText = ((Overlay)this.overlay).getTitle();
            this.titleLines = !Strings.isNullOrEmpty((String)titleText) ? titleText.split("\n") : null;
        }
    }

    @Override
    public void setTitlePosition(@Nullable Point2D.Double titlePosition) {
        this.titlePosition = titlePosition;
    }

    @Override
    public int getDisplayOrder() {
        return ((Overlay)this.overlay).getDisplayOrder();
    }

    @Override
    public String getModId() {
        return ((Displayable)this.overlay).getModId();
    }

    @Override
    public Rectangle2D.Double getBounds() {
        return this.screenBounds;
    }

    @Override
    public Overlay getOverlay() {
        return this.overlay;
    }

    @Override
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}

