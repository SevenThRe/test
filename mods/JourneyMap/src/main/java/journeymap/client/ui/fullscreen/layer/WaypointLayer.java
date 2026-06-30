/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.Vec3d
 *  org.lwjgl.input.Mouse
 */
package journeymap.client.ui.fullscreen.layer;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import journeymap.client.data.DataCache;
import journeymap.client.data.WaypointsData;
import journeymap.client.model.Waypoint;
import journeymap.client.render.draw.DrawStep;
import journeymap.client.render.draw.DrawUtil;
import journeymap.client.render.draw.DrawWayPointStep;
import journeymap.client.render.map.GridRenderer;
import journeymap.client.ui.UIManager;
import journeymap.client.ui.fullscreen.Fullscreen;
import journeymap.client.ui.fullscreen.layer.LayerDelegate;
import net.minecraft.client.Minecraft;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import org.lwjgl.input.Mouse;

public class WaypointLayer
implements LayerDelegate.Layer {
    private final long hoverDelay = 100L;
    private final List<DrawStep> drawStepList = new ArrayList<DrawStep>(1);
    private final BlockOutlineDrawStep clickDrawStep = new BlockOutlineDrawStep(new BlockPos(0, 0, 0));
    BlockPos lastCoord = null;
    long startHover = 0L;
    DrawWayPointStep selectedWaypointStep = null;
    Waypoint selected = null;
    private Fullscreen fullscreen;

    public WaypointLayer(Fullscreen fullscreen) {
        this.fullscreen = fullscreen;
    }

    @Override
    public List<DrawStep> onMouseMove(Minecraft mc, GridRenderer gridRenderer, Point2D.Double mousePosition, BlockPos blockCoord, float fontScale, boolean isScrolling) {
        this.drawStepList.clear();
        if (!WaypointsData.isManagerEnabled()) {
            return this.drawStepList;
        }
        if (this.lastCoord == null) {
            this.lastCoord = blockCoord;
        }
        long now = Minecraft.getSystemTime();
        int proximity = (int)Math.max(1.0, 8.0 / gridRenderer.getUIState().blockSize);
        if (this.clickDrawStep.blockCoord != null && !blockCoord.equals((Object)this.clickDrawStep.blockCoord)) {
            this.unclick();
        } else {
            this.drawStepList.add(this.clickDrawStep);
        }
        AxisAlignedBB area = new AxisAlignedBB((double)(blockCoord.getX() - proximity), -1.0, (double)(blockCoord.getZ() - proximity), (double)(blockCoord.getX() + proximity), (double)(mc.world.getActualHeight() + 1), (double)(blockCoord.getZ() + proximity));
        if (!this.lastCoord.equals((Object)blockCoord)) {
            if (!area.contains(new Vec3d((double)this.lastCoord.getX(), 1.0, (double)this.lastCoord.getZ()))) {
                this.selected = null;
                this.lastCoord = blockCoord;
                this.startHover = now;
                return this.drawStepList;
            }
        } else if (this.selected != null) {
            this.select(this.selected);
            return this.drawStepList;
        }
        if (now - this.startHover < 100L) {
            return this.drawStepList;
        }
        int dimension = mc.player.dimension;
        Collection<Waypoint> waypoints = DataCache.INSTANCE.getWaypoints(false);
        ArrayList<Waypoint> proximal = new ArrayList<Waypoint>();
        for (Waypoint waypoint : waypoints) {
            if (!waypoint.isEnable() || !waypoint.isInPlayerDimension() || !area.contains(new Vec3d((double)waypoint.getX(), (double)waypoint.getY(), (double)waypoint.getZ()))) continue;
            proximal.add(waypoint);
        }
        if (!proximal.isEmpty()) {
            if (proximal.size() > 1) {
                this.sortByDistance(proximal, blockCoord, dimension);
            }
            this.select((Waypoint)proximal.get(0));
        }
        return this.drawStepList;
    }

    @Override
    public List<DrawStep> onMouseClick(Minecraft mc, GridRenderer gridRenderer, Point2D.Double mousePosition, BlockPos blockCoord, int button, boolean doubleClick, float fontScale) {
        if (!WaypointsData.isManagerEnabled()) {
            return this.drawStepList;
        }
        if (!this.drawStepList.contains(this.clickDrawStep)) {
            this.drawStepList.add(this.clickDrawStep);
        }
        if (!doubleClick) {
            this.click(gridRenderer, blockCoord);
        } else {
            if (this.selected != null) {
                UIManager.INSTANCE.openWaypointManager(this.selected, this.fullscreen);
                return this.drawStepList;
            }
            Waypoint waypoint = Waypoint.at(blockCoord, Waypoint.Type.Normal, mc.player.dimension);
            UIManager.INSTANCE.openWaypointEditor(waypoint, true, this.fullscreen);
        }
        return this.drawStepList;
    }

    @Override
    public boolean propagateClick() {
        return true;
    }

    private void sortByDistance(List<Waypoint> waypoints, final BlockPos blockCoord, int dimension) {
        Collections.sort(waypoints, new Comparator<Waypoint>(){

            @Override
            public int compare(Waypoint o1, Waypoint o2) {
                return Double.compare(this.getDistance(o1), this.getDistance(o2));
            }

            private double getDistance(Waypoint waypoint) {
                double dx = waypoint.getX() - blockCoord.getX();
                double dz = waypoint.getZ() - blockCoord.getZ();
                return Math.sqrt(dx * dx + dz * dz);
            }
        });
    }

    private void select(Waypoint waypoint) {
        this.selected = waypoint;
        this.selectedWaypointStep = new DrawWayPointStep(waypoint, waypoint.getColor(), 0xFFFFFF, true);
        this.drawStepList.add(this.selectedWaypointStep);
    }

    private void click(GridRenderer gridRenderer, BlockPos blockCoord) {
        this.clickDrawStep.blockCoord = this.lastCoord = blockCoord;
        this.clickDrawStep.pixel = gridRenderer.getBlockPixelInGrid(blockCoord);
        if (!this.drawStepList.contains(this.clickDrawStep)) {
            this.drawStepList.add(this.clickDrawStep);
        }
    }

    private void unclick() {
        this.clickDrawStep.blockCoord = null;
        this.drawStepList.remove(this.clickDrawStep);
    }

    class BlockOutlineDrawStep
    implements DrawStep {
        BlockPos blockCoord;
        Point2D.Double pixel;

        BlockOutlineDrawStep(BlockPos blockCoord) {
            this.blockCoord = blockCoord;
        }

        @Override
        public void draw(DrawStep.Pass pass, double xOffset, double yOffset, GridRenderer gridRenderer, double fontScale, double rotation) {
            if (pass != DrawStep.Pass.Object) {
                return;
            }
            if (this.blockCoord == null) {
                return;
            }
            if (Mouse.isButtonDown((int)0)) {
                return;
            }
            if (xOffset != 0.0 || yOffset != 0.0) {
                return;
            }
            double size = gridRenderer.getUIState().blockSize;
            double thick = gridRenderer.getZoom() < 2 ? 1.0 : 2.0;
            double x = this.pixel.x + xOffset;
            double y = this.pixel.y + yOffset;
            if (gridRenderer.isOnScreen(this.pixel)) {
                DrawUtil.drawRectangle(x - thick * thick, y - thick * thick, size + thick * 4.0, thick, 0, 0.6f);
                DrawUtil.drawRectangle(x - thick, y - thick, size + thick * thick, thick, 0xFFFFFF, 0.6f);
                DrawUtil.drawRectangle(x - thick * thick, y - thick, thick, size + thick * thick, 0, 0.6f);
                DrawUtil.drawRectangle(x - thick, y, thick, size, 0xFFFFFF, 0.6f);
                DrawUtil.drawRectangle(x + size, y, thick, size, 0xFFFFFF, 0.6f);
                DrawUtil.drawRectangle(x + size + thick, y - thick, thick, size + thick * thick, 0, 0.6f);
                DrawUtil.drawRectangle(x - thick, y + size, size + thick * thick, thick, 0xFFFFFF, 0.6f);
                DrawUtil.drawRectangle(x - thick * thick, y + size + thick, size + thick * 4.0, thick, 0, 0.6f);
            }
        }

        @Override
        public int getDisplayOrder() {
            return 0;
        }

        @Override
        public String getModId() {
            return "journeymap";
        }
    }
}

