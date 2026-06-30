/*
 * Decompiled with CFR 0.152.
 */
package baka.animation.controller;

import goblinbob.mobends.standard.animation.controller.PlayerController;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public abstract class CPCManager {
    protected static Map<UUID, PlayerController> customPlayerControllerMap = new HashMap<UUID, PlayerController>();

    public static void put(UUID uuid, PlayerController controller) {
        customPlayerControllerMap.put(uuid, controller);
    }

    public static PlayerController get(UUID uuid) {
        return customPlayerControllerMap.getOrDefault(uuid, null);
    }
}

