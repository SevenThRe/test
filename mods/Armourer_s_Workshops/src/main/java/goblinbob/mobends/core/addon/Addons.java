/*
 * Decompiled with CFR 0.152.
 */
package goblinbob.mobends.core.addon;

import goblinbob.mobends.core.CoreClient;
import goblinbob.mobends.core.addon.AddonAnimationRegistry;
import goblinbob.mobends.core.addon.IAddon;
import java.util.ArrayList;
import java.util.List;

public class Addons {
    private static final Addons INSTANCE = new Addons();
    private final List<IAddon> addons = new ArrayList<IAddon>();

    public static void registerAddon(String modId, IAddon addon) {
        if (Addons.INSTANCE.addons.contains(addon)) {
            return;
        }
        Addons.INSTANCE.addons.add(addon);
        if (CoreClient.getInstance() != null) {
            addon.registerContent(new AddonAnimationRegistry(modId));
        }
    }

    public static Iterable<IAddon> getRegistered() {
        return Addons.INSTANCE.addons;
    }

    public static void onRenderTick(float partialTicks) {
        Addons.INSTANCE.addons.forEach(addon -> addon.onRenderTick(partialTicks));
    }

    public static void onClientTick() {
        Addons.INSTANCE.addons.forEach(IAddon::onClientTick);
    }

    public static void onRefresh() {
        Addons.INSTANCE.addons.forEach(IAddon::onRefresh);
    }
}

