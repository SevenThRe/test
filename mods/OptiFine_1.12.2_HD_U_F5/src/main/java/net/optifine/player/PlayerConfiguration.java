/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  bpx
 *  bua
 */
package net.optifine.player;

import net.optifine.player.PlayerItemModel;

public class PlayerConfiguration {
    private PlayerItemModel[] playerItemModels = new PlayerItemModel[0];
    private boolean initialized = false;

    public void renderPlayerItems(bpx modelBiped, bua player, float scale, float partialTicks) {
        if (!this.initialized) {
            return;
        }
        for (int i = 0; i < this.playerItemModels.length; ++i) {
            PlayerItemModel model = this.playerItemModels[i];
            model.render(modelBiped, player, scale, partialTicks);
        }
    }

    public boolean isInitialized() {
        return this.initialized;
    }

    public void setInitialized(boolean initialized) {
        this.initialized = initialized;
    }

    public PlayerItemModel[] getPlayerItemModels() {
        return this.playerItemModels;
    }

    public void addPlayerItemModel(PlayerItemModel playerItemModel) {
        this.playerItemModels = (PlayerItemModel[])Config.addObjectToArray(this.playerItemModels, playerItemModel);
    }
}

