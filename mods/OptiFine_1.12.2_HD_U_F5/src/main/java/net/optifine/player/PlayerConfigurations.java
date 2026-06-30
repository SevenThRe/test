/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  bib
 *  bpx
 *  bua
 *  bud
 */
package net.optifine.player;

import java.util.HashMap;
import java.util.Map;
import net.optifine.http.FileDownloadThread;
import net.optifine.http.HttpUtils;
import net.optifine.player.PlayerConfiguration;
import net.optifine.player.PlayerConfigurationReceiver;

public class PlayerConfigurations {
    private static Map mapConfigurations = null;
    private static boolean reloadPlayerItems = Boolean.getBoolean("player.models.reload");
    private static long timeReloadPlayerItemsMs = System.currentTimeMillis();

    public static void renderPlayerItems(bpx modelBiped, bua player, float scale, float partialTicks) {
        PlayerConfiguration cfg = PlayerConfigurations.getPlayerConfiguration(player);
        if (cfg != null) {
            cfg.renderPlayerItems(modelBiped, player, scale, partialTicks);
        }
    }

    public static synchronized PlayerConfiguration getPlayerConfiguration(bua player) {
        String name;
        bud currentPlayer;
        if (reloadPlayerItems && System.currentTimeMillis() > timeReloadPlayerItemsMs + 5000L && (currentPlayer = bib.z().h) != null) {
            PlayerConfigurations.setPlayerConfiguration(currentPlayer.getNameClear(), null);
            timeReloadPlayerItemsMs = System.currentTimeMillis();
        }
        if ((name = player.getNameClear()) == null) {
            return null;
        }
        PlayerConfiguration pc = (PlayerConfiguration)PlayerConfigurations.getMapConfigurations().get(name);
        if (pc == null) {
            pc = new PlayerConfiguration();
            PlayerConfigurations.getMapConfigurations().put(name, pc);
            PlayerConfigurationReceiver pcl = new PlayerConfigurationReceiver(name);
            String url = HttpUtils.getPlayerItemsUrl() + "/users/" + name + ".cfg";
            FileDownloadThread fdt = new FileDownloadThread(url, pcl);
            fdt.start();
        }
        return pc;
    }

    public static synchronized void setPlayerConfiguration(String player, PlayerConfiguration pc) {
        PlayerConfigurations.getMapConfigurations().put(player, pc);
    }

    private static Map getMapConfigurations() {
        if (mapConfigurations == null) {
            mapConfigurations = new HashMap();
        }
        return mapConfigurations;
    }
}

