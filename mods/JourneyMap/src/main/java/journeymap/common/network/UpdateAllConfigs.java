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
 *  net.minecraftforge.fml.common.FMLCommonHandler
 */
package journeymap.common.network;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import journeymap.common.Journeymap;
import journeymap.common.network.impl.CompressedPacket;
import journeymap.common.network.impl.Response;
import journeymap.common.properties.PropertiesBase;
import journeymap.common.util.PlayerConfigController;
import journeymap.server.properties.DefaultDimensionProperties;
import journeymap.server.properties.DimensionProperties;
import journeymap.server.properties.GlobalProperties;
import journeymap.server.properties.PermissionProperties;
import journeymap.server.properties.PropertiesManager;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.fml.common.FMLCommonHandler;

public class UpdateAllConfigs
extends CompressedPacket {
    @Override
    protected JsonObject onServer(Response response) {
        EntityPlayerMP player = response.getContext().getServerHandler().field_147369_b;
        if (PlayerConfigController.getInstance().canServerAdmin(player) || FMLCommonHandler.instance().getSide().isClient()) {
            Object properties;
            JsonObject prop = response.getAsJson();
            if (prop.get("global") != null) {
                JsonObject global = prop.get("global").getAsJsonObject();
                properties = PropertiesManager.getInstance().getGlobalProperties();
                if (!FMLCommonHandler.instance().getSide().isClient()) {
                    if (global.get("useWorldId") != null) {
                        ((GlobalProperties)properties).useWorldId.set(global.get("useWorldId").getAsBoolean());
                    }
                    if (global.get("op_can_track") != null) {
                        ((GlobalProperties)properties).opPlayerTrackingEnabled.set(global.get("op_can_track").getAsBoolean());
                    }
                    if (global.get("can_track") != null) {
                        ((GlobalProperties)properties).playerTrackingEnabled.set(global.get("can_track").getAsBoolean());
                    }
                    if (global.get("tracking_time") != null) {
                        ((GlobalProperties)properties).playerTrackingUpdateTime.set(global.get("tracking_time").getAsInt());
                    }
                }
                this.updateCommonProperties((PermissionProperties)properties, global);
                ((PropertiesBase)properties).save();
            }
            if (prop.get("default_dimension") != null) {
                JsonObject dDim = prop.get("default_dimension").getAsJsonObject();
                properties = PropertiesManager.getInstance().getDefaultDimensionProperties();
                ((DefaultDimensionProperties)properties).enabled.set(dDim.get("enabled").getAsBoolean());
                this.updateCommonProperties((PermissionProperties)properties, dDim);
                ((PropertiesBase)properties).save();
            }
            if (prop.get("dimensions") != null) {
                JsonArray dimArray = prop.get("dimensions").getAsJsonArray();
                for (JsonElement element : dimArray) {
                    JsonObject dimProp = element.getAsJsonObject();
                    DimensionProperties properties2 = PropertiesManager.getInstance().getDimProperties(dimProp.get("dimId").getAsInt());
                    properties2.enabled.set(dimProp.get("enabled").getAsBoolean());
                    this.updateCommonProperties(properties2, dimProp);
                    properties2.save();
                }
            }
            for (EntityPlayerMP playerTo : FMLCommonHandler.instance().getMinecraftServerInstance().func_184103_al().func_181057_v()) {
                JsonObject config = PlayerConfigController.getInstance().getPlayerConfig(playerTo);
                this.sendToPlayer(config, playerTo);
            }
        } else {
            player.func_145747_a((ITextComponent)new TextComponentString("You do not have permission to modify Journeymap's server options!"));
        }
        return null;
    }

    private void updateCommonProperties(PermissionProperties to, JsonObject from) {
        to.teleportEnabled.set(from.get("can_teleport").getAsBoolean());
        to.opSurfaceMappingEnabled.set(from.get("op_surface").getAsBoolean());
        to.surfaceMappingEnabled.set(from.get("surface").getAsBoolean());
        to.opTopoMappingEnabled.set(from.get("op_topo").getAsBoolean());
        to.topoMappingEnabled.set(from.get("topo").getAsBoolean());
        to.opCaveMappingEnabled.set(from.get("op_cave").getAsBoolean());
        to.caveMappingEnabled.set(from.get("cave").getAsBoolean());
        to.opRadarEnabled.set(from.get("op_radar").getAsBoolean());
        to.radarEnabled.set(from.get("radar").getAsBoolean());
        to.playerRadarEnabled.set(from.get("playerRadar").getAsBoolean());
        to.villagerRadarEnabled.set(from.get("villagerRadar").getAsBoolean());
        to.animalRadarEnabled.set(from.get("animalRadar").getAsBoolean());
        to.mobRadarEnabled.set(from.get("mobRadar").getAsBoolean());
    }

    @Override
    protected JsonObject onClient(Response response) {
        Journeymap.getClient().setJourneyMapServerConnection(true);
        PlayerConfigController.getInstance().updateClientConfigs(response);
        return null;
    }
}

