/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.util.math.BlockPos
 */
package journeymap.client.render.map;

import java.util.EnumSet;
import java.util.Stack;
import journeymap.client.api.display.Context;
import journeymap.client.api.display.DisplayType;
import journeymap.client.api.display.PolygonOverlay;
import journeymap.client.api.impl.ClientAPI;
import journeymap.client.api.model.MapPolygon;
import journeymap.client.api.model.ShapeProperties;
import journeymap.client.api.model.TextProperties;
import journeymap.client.io.nbt.RegionLoader;
import journeymap.client.model.RegionCoord;
import journeymap.client.ui.fullscreen.Fullscreen;
import net.minecraft.client.Minecraft;
import net.minecraft.util.math.BlockPos;

public class RegionRenderer {
    private static RegionRenderer instance;
    public static boolean TOGGLED;

    private RegionRenderer() {
    }

    public static void render(boolean toggled) {
        if (instance == null) {
            instance = new RegionRenderer();
        }
        TOGGLED = toggled;
        if (toggled) {
            ClientAPI.INSTANCE.flagOverlaysForRerender();
            for (RegionCoord rc : instance.getRegions()) {
                PolygonOverlay overlay = instance.createOverlay(rc);
                ClientAPI.INSTANCE.show(overlay);
            }
        } else {
            ClientAPI.INSTANCE.removeAll("journeymap", DisplayType.Polygon);
        }
    }

    private Stack<RegionCoord> getRegions() {
        try {
            Minecraft minecraft = Minecraft.getMinecraft();
            RegionLoader regionLoader = new RegionLoader(minecraft, Fullscreen.state().getMapType(), true);
            return regionLoader.getRegions();
        }
        catch (Exception e) {
            throw new RuntimeException("Unable to load regions", e);
        }
    }

    protected PolygonOverlay createOverlay(RegionCoord rCoord) {
        String displayId = "Region Display" + rCoord;
        String groupName = "Region";
        String label = "x:" + rCoord.regionX + ", z:" + rCoord.regionZ;
        ShapeProperties shapeProps = new ShapeProperties().setStrokeWidth(2.0f).setStrokeColor(0xFF0000).setStrokeOpacity(0.7f).setFillOpacity(0.2f);
        TextProperties textProps = new TextProperties().setBackgroundColor(34).setBackgroundOpacity(0.5f).setColor(65280).setOpacity(1.0f).setFontShadow(true);
        int x = rCoord.getMinChunkX() << 4;
        int y = 70;
        int z = rCoord.getMinChunkZ() << 4;
        int maxX = (rCoord.getMaxChunkX() << 4) + 15;
        int maxZ = (rCoord.getMaxChunkZ() << 4) + 15;
        BlockPos sw = new BlockPos(x + 1, y, maxZ + 1);
        BlockPos se = new BlockPos(maxX + 1, y, maxZ + 1);
        BlockPos ne = new BlockPos(maxX + 1, y, z + 1);
        BlockPos nw = new BlockPos(x + 1, y, z + 1);
        MapPolygon regionPolygon = new MapPolygon(sw, se, ne, nw);
        PolygonOverlay overlay = new PolygonOverlay("journeymap", displayId, rCoord.dimension, shapeProps, regionPolygon);
        overlay.setOverlayGroupName(groupName).setLabel(label).setTextProperties(textProps).setActiveUIs(EnumSet.of(Context.UI.Fullscreen, Context.UI.Webmap)).setActiveMapTypes(EnumSet.of(Context.MapType.Any));
        return overlay;
    }

    static {
        TOGGLED = false;
    }
}

