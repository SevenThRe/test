/*
 * Decompiled with CFR 0.152.
 */
package goblinbob.mobends.core.addon;

import goblinbob.mobends.core.addon.Addons;
import goblinbob.mobends.core.addon.IAddon;

public class AddonHelper {
    public static void registerAddon(String modId, IAddon addon) {
        Addons.registerAddon(modId, addon);
    }
}

