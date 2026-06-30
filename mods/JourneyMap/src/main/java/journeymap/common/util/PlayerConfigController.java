/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  net.minecraft.client.Minecraft
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraftforge.fml.common.FMLCommonHandler
 */
package journeymap.common.util;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import journeymap.client.feature.FeatureManager;
import journeymap.common.Journeymap;
import journeymap.common.network.impl.Response;
import journeymap.server.Constants;
import journeymap.server.JourneymapServer;
import journeymap.server.config.ForgeConfig;
import journeymap.server.nbt.WorldNbtIDSaveHandler;
import journeymap.server.properties.DimensionProperties;
import journeymap.server.properties.GlobalProperties;
import journeymap.server.properties.PermissionProperties;
import journeymap.server.properties.Permissions;
import journeymap.server.properties.PropertiesManager;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.FMLCommonHandler;

public class PlayerConfigController {
    private static PlayerConfigController INSTANCE;

    public static PlayerConfigController getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new PlayerConfigController();
        }
        return INSTANCE;
    }

    public JsonObject getPlayerConfig(EntityPlayerMP player) {
        JsonObject config = new JsonObject();
        JsonObject settings = new JsonObject();
        if (PropertiesManager.getInstance().getGlobalProperties().useWorldId.get().booleanValue() && (!FMLCommonHandler.instance().getSide().isClient() || Minecraft.getMinecraft().getIntegratedServer() != null && Minecraft.getMinecraft().getIntegratedServer().getPublic())) {
            WorldNbtIDSaveHandler worldSaveHandler = new WorldNbtIDSaveHandler();
            String worldID = worldSaveHandler.getWorldID();
            settings.addProperty("world_id", worldID);
        }
        settings.addProperty("can_teleport", Boolean.valueOf(this.canTeleport(player)));
        settings.addProperty("can_track", Boolean.valueOf(this.canPlayerTrack(player)));
        settings.addProperty("server_admin", Boolean.valueOf(this.canServerAdmin(player)));
        config.add("settings", (JsonElement)settings);
        config.addProperty("dim", this.getDimProperties(player));
        return config;
    }

    public boolean canServerAdmin(EntityPlayerMP player) {
        String[] admins;
        for (String admin : admins = ForgeConfig.playerNames) {
            if (!player.getUniqueID().toString().equals(admin) && !player.getName().equalsIgnoreCase(admin) && !Constants.debugOverride((Entity)player)) continue;
            return true;
        }
        if (JourneymapServer.isOp((EntityPlayer)player)) {
            return ForgeConfig.opAccess;
        }
        return false;
    }

    private String getDimProperties(EntityPlayerMP player) {
        DimensionProperties dimensionProperties = PropertiesManager.getInstance().getDimProperties(player.dimension);
        try {
            PermissionProperties prop = dimensionProperties.enabled.get() != false ? (DimensionProperties)dimensionProperties.clone() : (GlobalProperties)PropertiesManager.getInstance().getGlobalProperties().clone();
            if (JourneymapServer.isOp((EntityPlayer)player)) {
                prop.radarEnabled.set(prop.opRadarEnabled.get());
                prop.caveMappingEnabled.set(prop.opCaveMappingEnabled.get());
                prop.surfaceMappingEnabled.set(prop.opSurfaceMappingEnabled.get());
                prop.topoMappingEnabled.set(prop.opTopoMappingEnabled.get());
            }
            return prop.toJsonString(false);
        }
        catch (CloneNotSupportedException e) {
            Journeymap.getLogger().error("CloneNotSupportedException: ", (Throwable)e);
            return null;
        }
    }

    private boolean canTeleport(EntityPlayerMP player) {
        if (PropertiesManager.getInstance().getDimProperties((int)player.dimension).enabled.get().booleanValue()) {
            return PropertiesManager.getInstance().getDimProperties((int)player.dimension).teleportEnabled.get();
        }
        if (PropertiesManager.getInstance().getGlobalProperties().teleportEnabled.get().booleanValue()) {
            return true;
        }
        return JourneymapServer.isOp((EntityPlayer)player);
    }

    private boolean canPlayerTrack(EntityPlayerMP player) {
        if (PropertiesManager.getInstance().getGlobalProperties().playerTrackingEnabled.get().booleanValue()) {
            return true;
        }
        return PropertiesManager.getInstance().getGlobalProperties().opPlayerTrackingEnabled.get() != false && JourneymapServer.isOp((EntityPlayer)player);
    }

    public void updateClientConfigs(Response response) {
        if (response.getAsJson().get("settings") != null) {
            JsonObject settings = response.getAsJson().get("settings").getAsJsonObject();
            if (settings.get("world_id") != null) {
                Journeymap.getClient().setCurrentWorldId(settings.get("world_id").getAsString());
            }
            if (settings.get("can_teleport") != null) {
                Journeymap.getClient().setTeleportEnabled(settings.get("can_teleport").getAsBoolean());
            }
            if (settings.get("can_track") != null) {
                Journeymap.getClient().setPlayerTrackingEnabled(settings.get("can_track").getAsBoolean());
            }
            if (settings.get("server_admin") != null) {
                Journeymap.getClient().setServerAdmin(settings.get("server_admin").getAsBoolean());
            }
            String dimProperties = response.getAsJson().get("dim").getAsString();
            PermissionProperties prop = (PermissionProperties)new Permissions().load(dimProperties, false);
            FeatureManager.INSTANCE.updateDimensionFeatures(prop);
        }
    }
}

