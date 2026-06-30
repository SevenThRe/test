/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityPlayerSP
 *  net.minecraft.util.math.Vec3d
 *  net.minecraftforge.fml.client.FMLClientHandler
 */
package journeymap.client.render.draw;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import journeymap.client.data.DataCache;
import journeymap.client.model.Waypoint;
import journeymap.client.render.draw.DrawWayPointStep;
import journeymap.client.render.map.GridRenderer;
import journeymap.common.Journeymap;
import journeymap.common.log.LogFormatter;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.client.FMLClientHandler;

public class WaypointDrawStepFactory {
    final List<DrawWayPointStep> drawStepList = new ArrayList<DrawWayPointStep>();

    public List<DrawWayPointStep> prepareSteps(Collection<Waypoint> waypoints, GridRenderer grid, boolean checkDistance, boolean showLabel) {
        Minecraft mc = FMLClientHandler.instance().getClient();
        EntityPlayerSP player = mc.field_71439_g;
        int dimension = player.field_71093_bK;
        int maxDistance = Journeymap.getClient().getWaypointProperties().maxDistance.get();
        checkDistance = checkDistance && maxDistance > 0;
        Vec3d playerVec = checkDistance ? player.func_174791_d() : null;
        this.drawStepList.clear();
        try {
            for (Waypoint waypoint : waypoints) {
                DrawWayPointStep wayPointStep;
                double actualDistance;
                if (!waypoint.isEnable() || !waypoint.isInPlayerDimension() || checkDistance && (actualDistance = playerVec.func_72438_d(waypoint.getPosition())) > (double)maxDistance || (wayPointStep = DataCache.INSTANCE.getDrawWayPointStep(waypoint)) == null) continue;
                this.drawStepList.add(wayPointStep);
                wayPointStep.setShowLabel(showLabel);
            }
        }
        catch (Throwable t) {
            Journeymap.getLogger().error("Error during prepareSteps: " + LogFormatter.toString(t));
        }
        return this.drawStepList;
    }
}

