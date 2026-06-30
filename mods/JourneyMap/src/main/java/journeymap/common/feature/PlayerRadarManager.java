/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonArray
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  com.mojang.authlib.GameProfile
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityOtherPlayerMP
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.world.World
 *  net.minecraftforge.fml.client.FMLClientHandler
 */
package journeymap.common.feature;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mojang.authlib.GameProfile;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.FMLClientHandler;

public class PlayerRadarManager {
    private final Map<UUID, EntityPlayer> playersOnServer;
    private static PlayerRadarManager INSTANCE;
    private final Object lock = new Object();

    private PlayerRadarManager() {
        this.playersOnServer = new HashMap<UUID, EntityPlayer>();
    }

    public static PlayerRadarManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new PlayerRadarManager();
        }
        return INSTANCE;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public Map<UUID, EntityPlayer> getPlayers() {
        Object object = this.lock;
        synchronized (object) {
            return this.playersOnServer;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void addPlayer(EntityPlayer player) {
        Object object = this.lock;
        synchronized (object) {
            this.playersOnServer.put(player.func_110124_au(), player);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void clearNetworkPlayers() {
        Object object = this.lock;
        synchronized (object) {
            this.playersOnServer.clear();
        }
    }

    private void updateClientPlayer(JsonObject player) {
        EntityPlayer clientPlayer = this.getPlayers().get(UUID.fromString(player.get("playerId").getAsString()));
        if (clientPlayer != null) {
            this.updatePlayer(clientPlayer, player);
        } else {
            this.addPlayer(this.buildPlayerFromJson(player));
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void updatePlayers(JsonArray playerList) {
        Object object = this.lock;
        synchronized (object) {
            for (JsonElement p : playerList) {
                JsonObject player = p.getAsJsonObject();
                this.updateClientPlayer(player);
            }
        }
    }

    private void updatePlayer(EntityPlayer player, JsonObject json) {
        player.field_70165_t = json.get("posX").getAsInt();
        player.field_70163_u = json.get("posY").getAsInt();
        player.field_70161_v = json.get("posZ").getAsInt();
        player.field_70176_ah = json.get("chunkX").getAsInt();
        player.field_70162_ai = json.get("chunkY").getAsInt();
        player.field_70164_aj = json.get("chunkZ").getAsInt();
        player.field_70759_as = json.get("rotation").getAsFloat();
        player.func_70095_a(json.get("sneaking").getAsBoolean());
        player.field_71093_bK = json.get("dim").getAsInt();
    }

    private EntityPlayer buildPlayerFromJson(JsonObject player) {
        Minecraft mc = FMLClientHandler.instance().getClient();
        UUID playerUUID = UUID.fromString(player.get("playerId").getAsString());
        String playerName = player.get("name").getAsString();
        if (!playerUUID.equals(mc.field_71439_g.func_110124_au())) {
            EntityOtherPlayerMP playerMp = new EntityOtherPlayerMP((World)mc.field_71441_e, new GameProfile(playerUUID, playerName));
            this.updatePlayer((EntityPlayer)playerMp, player);
            playerMp.func_184221_a(playerUUID);
            playerMp.field_70175_ag = true;
            return playerMp;
        }
        return null;
    }
}

