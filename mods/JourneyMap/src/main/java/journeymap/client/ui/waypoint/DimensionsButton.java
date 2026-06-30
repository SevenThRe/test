/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraftforge.fml.client.FMLClientHandler
 */
package journeymap.client.ui.waypoint;

import java.util.List;
import journeymap.client.Constants;
import journeymap.client.data.WorldData;
import journeymap.client.ui.component.Button;
import journeymap.client.waypoint.WaypointStore;
import net.minecraft.client.gui.FontRenderer;
import net.minecraftforge.fml.client.FMLClientHandler;

class DimensionsButton
extends Button {
    static boolean needInit = true;
    static WorldData.DimensionProvider currentWorldProvider;
    final List<WorldData.DimensionProvider> dimensionProviders = WorldData.getDimensionProviders(WaypointStore.INSTANCE.getLoadedDimensions());

    public DimensionsButton() {
        super(0, 0, "");
        if (needInit || currentWorldProvider != null) {
            currentWorldProvider = new WorldData.WrappedProvider(FMLClientHandler.instance().getClient().player.world.provider);
            needInit = false;
        }
        this.updateLabel();
        this.fitWidth(FMLClientHandler.instance().getClient().fontRenderer);
    }

    @Override
    protected void updateLabel() {
        String dimName = currentWorldProvider != null ? WorldData.getSafeDimensionName(currentWorldProvider) : Constants.getString("jm.waypoint.dimension_all");
        this.displayString = Constants.getString("jm.waypoint.dimension", dimName);
    }

    @Override
    public int getFitWidth(FontRenderer fr) {
        int maxWidth = 0;
        for (WorldData.DimensionProvider dimensionProvider : this.dimensionProviders) {
            String name = Constants.getString("jm.waypoint.dimension", WorldData.getSafeDimensionName(dimensionProvider));
            maxWidth = Math.max(maxWidth, FMLClientHandler.instance().getClient().fontRenderer.getStringWidth(name));
        }
        return maxWidth + 12;
    }

    public void nextValue() {
        int index;
        if (currentWorldProvider == null) {
            index = 0;
        } else {
            index = -1;
            int currentDimension = currentWorldProvider.getDimension();
            for (WorldData.DimensionProvider dimensionProvider : this.dimensionProviders) {
                if (currentDimension != dimensionProvider.getDimension()) continue;
                index = this.dimensionProviders.indexOf(dimensionProvider) + 1;
                break;
            }
        }
        currentWorldProvider = index >= this.dimensionProviders.size() || index < 0 ? null : this.dimensionProviders.get(index);
        this.updateLabel();
    }
}

