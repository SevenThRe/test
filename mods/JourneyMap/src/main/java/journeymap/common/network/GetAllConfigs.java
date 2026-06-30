/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonArray
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.util.text.ITextComponent
 *  net.minecraft.util.text.TextComponentString
 *  net.minecraftforge.common.DimensionManager
 *  net.minecraftforge.fml.common.FMLCommonHandler
 */
package journeymap.common.network;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import journeymap.common.network.impl.CompressedPacket;
import journeymap.common.network.impl.Response;
import journeymap.common.util.PlayerConfigController;
import journeymap.server.nbt.WorldNbtIDSaveHandler;
import journeymap.server.properties.DefaultDimensionProperties;
import journeymap.server.properties.DimensionProperties;
import journeymap.server.properties.GlobalProperties;
import journeymap.server.properties.PermissionProperties;
import journeymap.server.properties.PropertiesManager;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.fml.common.FMLCommonHandler;

public class GetAllConfigs
extends CompressedPacket {
    @Override
    protected JsonObject onServer(Response response) {
        EntityPlayerMP player = response.getContext().getServerHandler().player;
        if (PlayerConfigController.getInstance().canServerAdmin(player) || FMLCommonHandler.instance().getSide().isClient()) {
            return this.collectServerSettings();
        }
        player.sendMessage((ITextComponent)new TextComponentString("You do not have permission to modify Journeymap's server options!"));
        return null;
    }

    private JsonObject collectServerSettings() {
        JsonObject serverConfigs = new JsonObject();
        JsonArray dimensionConfigs = new JsonArray();
        JsonObject globalConfig = new JsonObject();
        JsonObject defaultDimConfig = new JsonObject();
        Integer[] dimensions = DimensionManager.getStaticDimensionIDs();
        GlobalProperties globalProperties = PropertiesManager.getInstance().getGlobalProperties();
        DefaultDimensionProperties defaultDimensionProperties = PropertiesManager.getInstance().getDefaultDimensionProperties();
        if (!FMLCommonHandler.instance().getSide().isClient()) {
            globalConfig.addProperty("useWorldId", globalProperties.useWorldId.get());
            globalConfig.addProperty("world_id", new WorldNbtIDSaveHandler().getWorldID());
        } else {
            globalConfig.addProperty("dimName", "global");
        }
        globalConfig.addProperty("op_can_track", globalProperties.opPlayerTrackingEnabled.get());
        globalConfig.addProperty("can_track", globalProperties.playerTrackingEnabled.get());
        globalConfig.addProperty("tracking_time", (Number)globalProperties.playerTrackingUpdateTime.get());
        this.getCommonProperties(globalProperties, globalConfig);
        defaultDimConfig.addProperty("enabled", defaultDimensionProperties.enabled.get());
        defaultDimConfig.addProperty("dimName", "default");
        this.getCommonProperties(defaultDimensionProperties, defaultDimConfig);
        Integer[] integerArray = dimensions;
        int n = integerArray.length;
        for (int i = 0; i < n; ++i) {
            int d = integerArray[i];
            JsonObject dim = new JsonObject();
            DimensionProperties dimensionProperties = PropertiesManager.getInstance().getDimProperties(d);
            dim.addProperty("enabled", dimensionProperties.enabled.get());
            dim.addProperty("dimId", (Number)d);
            dim.addProperty("dimName", DimensionManager.getProviderType((int)d).getName());
            this.getCommonProperties(dimensionProperties, dim);
            dimensionConfigs.add((JsonElement)dim);
        }
        serverConfigs.add("global", (JsonElement)globalConfig);
        serverConfigs.add("default_dimension", (JsonElement)defaultDimConfig);
        serverConfigs.add("dimensions", (JsonElement)dimensionConfigs);
        return serverConfigs;
    }

    private void getCommonProperties(PermissionProperties from, JsonObject to) {
        to.addProperty("can_teleport", from.teleportEnabled.get());
        to.addProperty("op_surface", from.opSurfaceMappingEnabled.get());
        to.addProperty("surface", from.surfaceMappingEnabled.get());
        to.addProperty("op_topo", from.opTopoMappingEnabled.get());
        to.addProperty("topo", from.topoMappingEnabled.get());
        to.addProperty("op_cave", from.opCaveMappingEnabled.get());
        to.addProperty("cave", from.caveMappingEnabled.get());
        to.addProperty("op_radar", from.opRadarEnabled.get());
        to.addProperty("radar", from.radarEnabled.get());
        to.addProperty("playerRadar", from.playerRadarEnabled.get());
        to.addProperty("villagerRadar", from.villagerRadarEnabled.get());
        to.addProperty("animalRadar", from.animalRadarEnabled.get());
        to.addProperty("mobRadar", from.mobRadarEnabled.get());
    }

    @Override
    protected JsonObject onClient(Response response) {
        return null;
    }
}

