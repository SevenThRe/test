/*
 * Decompiled with CFR 0.152.
 */
package goblinbob.mobends.standard;

import goblinbob.mobends.standard.data.PlayerData;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerDataManager {
    private static Map<UUID, PlayerData> map = new HashMap<UUID, PlayerData>();

    public static void put(UUID uuid, PlayerData playerController) {
        map.put(uuid, playerController);
    }

    public static PlayerData get(UUID uuid) {
        return map.get(uuid);
    }
}

