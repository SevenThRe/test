/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  com.google.gson.JsonArray
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.world.World
 *  net.minecraftforge.event.entity.EntityJoinWorldEvent
 *  net.minecraftforge.fml.common.FMLCommonHandler
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.gameevent.PlayerEvent$PlayerLoggedInEvent
 *  net.minecraftforge.fml.common.gameevent.TickEvent$Phase
 *  net.minecraftforge.fml.common.gameevent.TickEvent$ServerTickEvent
 */
package journeymap.server.events;

import com.google.common.collect.Lists;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;
import java.util.UUID;
import journeymap.common.network.GetClientConfig;
import journeymap.common.network.GetPlayerLocations;
import journeymap.common.util.PlayerConfigController;
import journeymap.server.JourneymapServer;
import journeymap.server.properties.GlobalProperties;
import journeymap.server.properties.PropertiesManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class ForgeEvents {
    private static int playerUpdateTicks = 5;

    @SubscribeEvent
    public void onServerTickEvent(TickEvent.ServerTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            return;
        }
        if (PropertiesManager.getInstance().getGlobalProperties().playerTrackingEnabled.get().booleanValue() && FMLCommonHandler.instance().getMinecraftServerInstance().func_184103_al().func_181057_v().size() > 1) {
            playerUpdateTicks = PropertiesManager.getInstance().getGlobalProperties().playerTrackingUpdateTime.get();
            World world = FMLCommonHandler.instance().getMinecraftServerInstance().func_130014_f_();
            if (world != null && world.func_72820_D() % (long)playerUpdateTicks == 0L) {
                this.sendPlayersOnRadarToPlayers();
            }
        }
    }

    @SubscribeEvent
    public void onEntityJoinWorldEvent(EntityJoinWorldEvent event) {
        if (event.getEntity() instanceof EntityPlayerMP) {
            this.sendConfigsToPlayer((EntityPlayerMP)event.getEntity());
        }
    }

    @SubscribeEvent
    public void onPlayerLoggedInEvent(PlayerEvent.PlayerLoggedInEvent event) {
        if (event.player instanceof EntityPlayerMP) {
            this.sendConfigsToPlayer((EntityPlayerMP)event.player);
        }
    }

    private void sendConfigsToPlayer(EntityPlayerMP player) {
        JsonObject config = PlayerConfigController.getInstance().getPlayerConfig(player);
        new GetClientConfig().sendToPlayer(config, player);
    }

    private void sendPlayersOnRadarToPlayers() {
        GlobalProperties prop = PropertiesManager.getInstance().getGlobalProperties();
        boolean sendToEveryone = prop.playerTrackingEnabled.get();
        boolean sendToOps = prop.opPlayerTrackingEnabled.get();
        for (EntityPlayerMP player : FMLCommonHandler.instance().getMinecraftServerInstance().func_184103_al().func_181057_v()) {
            boolean playerRadarEnabled = PropertiesManager.getInstance().getDimProperties((int)player.field_71093_bK).playerRadarEnabled.get();
            boolean receiverOp = JourneymapServer.isOp((EntityPlayer)player);
            if ((!sendToEveryone || !playerRadarEnabled) && (!sendToOps || !receiverOp)) continue;
            try {
                this.sendPlayerTrackingData(player);
            }
            catch (ConcurrentModificationException concurrentModificationException) {}
        }
    }

    private void sendPlayerTrackingData(EntityPlayerMP entityPlayerMP) {
        int receiverDimension = entityPlayerMP.field_71093_bK;
        boolean receiverOp = JourneymapServer.isOp((EntityPlayer)entityPlayerMP);
        ArrayList serverPlayers = new ArrayList(FMLCommonHandler.instance().getMinecraftServerInstance().func_184103_al().func_181057_v());
        ArrayList<JsonObject> playerList = new ArrayList<JsonObject>();
        if (serverPlayers != null || serverPlayers.size() > 1) {
            for (EntityPlayerMP playerMp : serverPlayers) {
                boolean sneaking = playerMp.func_70093_af();
                int dimension = playerMp.field_71093_bK;
                UUID playerId = playerMp.func_110124_au();
                if (entityPlayerMP.func_110124_au().equals(playerId) || sneaking) continue;
                playerList.add(this.buildJsonPlayer((EntityPlayer)playerMp, receiverOp));
            }
            this.sendPlayerList(playerList, entityPlayerMP);
        }
    }

    private void sendPlayerList(List<JsonObject> allPlayers, EntityPlayerMP player) {
        List partitionedPlayerList = Lists.partition(allPlayers, (int)10);
        for (List playerList : partitionedPlayerList) {
            JsonArray playerArray = new JsonArray();
            for (JsonObject playerJsonObject : playerList) {
                playerArray.add((JsonElement)playerJsonObject);
            }
            JsonObject payload = new JsonObject();
            payload.add("players", (JsonElement)playerArray);
            new GetPlayerLocations().sendToPlayer(payload, player);
        }
    }

    private JsonObject buildJsonPlayer(EntityPlayer playerMp, boolean receiverOp) {
        boolean sneaking = playerMp.func_70093_af();
        UUID playerId = playerMp.func_110124_au();
        if (receiverOp) {
            sneaking = false;
        }
        JsonObject player = new JsonObject();
        player.addProperty("name", playerMp.func_70005_c_());
        player.addProperty("posX", (Number)playerMp.func_180425_c().func_177958_n());
        player.addProperty("posY", (Number)playerMp.func_180425_c().func_177956_o());
        player.addProperty("posZ", (Number)playerMp.func_180425_c().func_177952_p());
        player.addProperty("chunkX", (Number)playerMp.field_70176_ah);
        player.addProperty("chunkY", (Number)playerMp.field_70162_ai);
        player.addProperty("chunkZ", (Number)playerMp.field_70164_aj);
        player.addProperty("rotation", (Number)Float.valueOf(playerMp.field_70759_as));
        player.addProperty("sneaking", Boolean.valueOf(sneaking));
        player.addProperty("playerId", playerId.toString());
        player.addProperty("dim", (Number)playerMp.field_71093_bK);
        return player;
    }
}

