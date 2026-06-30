/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.util.math.BlockPos
 */
package journeymap.client.ui.fullscreen.layer;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import journeymap.client.api.display.IOverlayListener;
import journeymap.client.api.display.Overlay;
import journeymap.client.api.impl.ClientAPI;
import journeymap.client.api.util.UIState;
import journeymap.client.render.draw.DrawStep;
import journeymap.client.render.draw.OverlayDrawStep;
import journeymap.client.render.map.GridRenderer;
import journeymap.client.ui.fullscreen.layer.LayerDelegate;
import journeymap.common.Journeymap;
import net.minecraft.client.Minecraft;
import net.minecraft.util.math.BlockPos;

public class ModOverlayLayer
implements LayerDelegate.Layer {
    protected List<OverlayDrawStep> allDrawSteps = new ArrayList<OverlayDrawStep>();
    protected List<OverlayDrawStep> visibleSteps = new ArrayList<OverlayDrawStep>();
    protected List<OverlayDrawStep> touchedSteps = new ArrayList<OverlayDrawStep>();
    protected BlockPos lastCoord;
    protected Point2D.Double lastMousePosition;
    protected UIState lastUiState;
    protected boolean propagateClick;

    private void ensureCurrent(Minecraft mc, GridRenderer gridRenderer, Point2D.Double mousePosition, BlockPos blockCoord) {
        boolean uiStateChange;
        UIState currentUiState = gridRenderer.getUIState();
        boolean bl = uiStateChange = !Objects.equals(this.lastUiState, currentUiState);
        if (uiStateChange || !Objects.equals(blockCoord, this.lastCoord) || this.lastMousePosition == null) {
            this.lastCoord = blockCoord;
            this.lastUiState = currentUiState;
            this.lastMousePosition = mousePosition;
            this.allDrawSteps.clear();
            ClientAPI.INSTANCE.getDrawSteps(this.allDrawSteps, currentUiState);
            this.updateOverlayState(gridRenderer, mousePosition, blockCoord, uiStateChange);
        }
    }

    @Override
    public List<DrawStep> onMouseMove(Minecraft mc, GridRenderer gridRenderer, Point2D.Double mousePosition, BlockPos blockCoord, float fontScale, boolean isScrolling) {
        this.ensureCurrent(mc, gridRenderer, mousePosition, blockCoord);
        if (!this.touchedSteps.isEmpty()) {
            for (OverlayDrawStep overlayDrawStep : this.touchedSteps) {
                try {
                    Overlay overlay = overlayDrawStep.getOverlay();
                    IOverlayListener listener = overlay.getOverlayListener();
                    this.fireOnMouseMove(listener, mousePosition, blockCoord);
                    overlayDrawStep.setTitlePosition(mousePosition);
                }
                catch (Throwable t) {
                    Journeymap.getLogger().error(t.getMessage(), t);
                }
            }
        }
        return Collections.emptyList();
    }

    @Override
    public List<DrawStep> onMouseClick(Minecraft mc, GridRenderer gridRenderer, Point2D.Double mousePosition, BlockPos blockCoord, int button, boolean doubleClick, float fontScale) {
        this.ensureCurrent(mc, gridRenderer, mousePosition, blockCoord);
        this.propagateClick = true;
        if (!this.touchedSteps.isEmpty()) {
            for (OverlayDrawStep overlayDrawStep : this.touchedSteps) {
                try {
                    Overlay overlay = overlayDrawStep.getOverlay();
                    IOverlayListener listener = overlay.getOverlayListener();
                    if (listener == null) continue;
                    boolean continueClick = this.fireOnMouseClick(listener, mousePosition, blockCoord, button, doubleClick);
                    overlayDrawStep.setTitlePosition(mousePosition);
                    if (continueClick) continue;
                    this.propagateClick = false;
                    break;
                }
                catch (Throwable t) {
                    Journeymap.getLogger().error(t.getMessage(), t);
                }
            }
        }
        return Collections.emptyList();
    }

    @Override
    public boolean propagateClick() {
        return this.propagateClick;
    }

    private void updateOverlayState(GridRenderer gridRenderer, Point2D.Double mousePosition, BlockPos blockCoord, boolean uiStateChange) {
        for (OverlayDrawStep overlayDrawStep : this.allDrawSteps) {
            Overlay overlay = overlayDrawStep.getOverlay();
            IOverlayListener listener = overlay.getOverlayListener();
            boolean currentlyActive = this.visibleSteps.contains(overlayDrawStep);
            boolean currentlyTouched = this.touchedSteps.contains(overlayDrawStep);
            if (overlayDrawStep.isOnScreen(0.0, 0.0, gridRenderer, 0.0)) {
                if (!currentlyActive) {
                    this.visibleSteps.add(overlayDrawStep);
                    this.fireActivate(listener);
                } else if (uiStateChange) {
                    this.fireActivate(listener);
                }
                Rectangle2D.Double bounds = overlayDrawStep.getBounds();
                if (bounds != null && bounds.contains(mousePosition)) {
                    if (currentlyTouched) continue;
                    this.touchedSteps.add(overlayDrawStep);
                    continue;
                }
                if (!currentlyTouched) continue;
                this.touchedSteps.remove(overlayDrawStep);
                overlayDrawStep.setTitlePosition(null);
                this.fireOnMouseOut(listener, mousePosition, blockCoord);
                continue;
            }
            if (currentlyTouched) {
                this.touchedSteps.remove(overlayDrawStep);
                overlayDrawStep.setTitlePosition(null);
                this.fireOnMouseOut(listener, mousePosition, blockCoord);
            }
            if (!currentlyActive) continue;
            this.visibleSteps.remove(overlayDrawStep);
            overlayDrawStep.setTitlePosition(null);
            this.fireDeActivate(listener);
        }
    }

    private void fireActivate(IOverlayListener listener) {
        if (listener != null) {
            try {
                listener.onActivate(this.lastUiState);
            }
            catch (Throwable t) {
                t.printStackTrace();
            }
        }
    }

    private void fireDeActivate(IOverlayListener listener) {
        if (listener != null) {
            try {
                listener.onDeactivate(this.lastUiState);
            }
            catch (Throwable t) {
                t.printStackTrace();
            }
        }
    }

    private void fireOnMouseMove(IOverlayListener listener, Point2D.Double mousePosition, BlockPos blockCoord) {
        if (listener != null) {
            try {
                listener.onMouseMove(this.lastUiState, mousePosition, blockCoord);
            }
            catch (Throwable t) {
                t.printStackTrace();
            }
        }
    }

    private boolean fireOnMouseClick(IOverlayListener listener, Point2D.Double mousePosition, BlockPos blockCoord, int button, boolean doubleClick) {
        if (listener != null) {
            try {
                return listener.onMouseClick(this.lastUiState, mousePosition, blockCoord, button, doubleClick);
            }
            catch (Throwable t) {
                t.printStackTrace();
            }
        }
        return true;
    }

    private void fireOnMouseOut(IOverlayListener listener, Point2D.Double mousePosition, BlockPos blockCoord) {
        if (listener != null) {
            try {
                listener.onMouseOut(this.lastUiState, mousePosition, blockCoord);
            }
            catch (Throwable t) {
                t.printStackTrace();
            }
        }
    }
}

