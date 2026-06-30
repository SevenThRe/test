/*
 * Decompiled with CFR 0.152.
 */
package goblinbob.mobends.core.addon;

import goblinbob.mobends.core.addon.AddonAnimationRegistry;

public interface IAddon {
    public void registerContent(AddonAnimationRegistry var1);

    public String getDisplayName();

    default public void onRenderTick(float partialTicks) {
    }

    default public void onClientTick() {
    }

    default public void onRefresh() {
    }
}

