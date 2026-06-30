/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  bpx
 *  bqj
 *  bua
 *  bus
 *  ccg
 *  cct
 *  vp
 */
package net.optifine.player;

import java.util.Map;
import java.util.Set;
import net.optifine.player.PlayerConfigurations;

public class PlayerItemsLayer
implements ccg {
    private cct renderPlayer = null;

    public PlayerItemsLayer(cct renderPlayer) {
        this.renderPlayer = renderPlayer;
    }

    public void a(vp entityLiving, float limbSwing, float limbSwingAmount, float partialTicks, float ticksExisted, float headYaw, float rotationPitch, float scale) {
        this.renderEquippedItems(entityLiving, scale, partialTicks);
    }

    protected void renderEquippedItems(vp entityLiving, float scale, float partialTicks) {
        if (!Config.isShowCapes()) {
            return;
        }
        if (!(entityLiving instanceof bua)) {
            return;
        }
        bua player = (bua)entityLiving;
        bus.c((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        bus.E();
        bus.q();
        bqj modelBipedMain = this.renderPlayer.h();
        PlayerConfigurations.renderPlayerItems((bpx)modelBipedMain, player, scale, partialTicks);
        bus.r();
    }

    public boolean a() {
        return false;
    }

    public static void register(Map renderPlayerMap) {
        Set keys = renderPlayerMap.keySet();
        boolean registered = false;
        for (Object key : keys) {
            Object renderer = renderPlayerMap.get(key);
            if (!(renderer instanceof cct)) continue;
            cct renderPlayer = (cct)renderer;
            renderPlayer.a((ccg)new PlayerItemsLayer(renderPlayer));
            registered = true;
        }
        if (!registered) {
            Config.warn("PlayerItemsLayer not registered");
        }
    }
}

