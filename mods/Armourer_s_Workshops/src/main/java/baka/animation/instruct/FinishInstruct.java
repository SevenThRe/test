/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.AbstractClientPlayer
 */
package baka.animation.instruct;

import baka.animation.instruct.InstructBase;
import baka.client.network.NetworkHandler;
import goblinbob.mobends.standard.data.PlayerData;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;

public class FinishInstruct
extends InstructBase {
    protected final String actionName;

    public FinishInstruct(String actionName) {
        this.actionName = actionName;
    }

    @Override
    public void perform(PlayerData data) {
        super.perform(data);
        if (((AbstractClientPlayer)data.getEntity()).getUniqueID().equals(Minecraft.getMinecraft().player.getUniqueID())) {
            NetworkHandler.finishEvent(this.actionName);
        }
    }
}

