/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.entity.AbstractClientPlayer
 *  net.minecraft.util.math.MathHelper
 */
package goblinbob.mobends.standard.animation.bit.player;

import goblinbob.mobends.core.animation.bit.AnimationBit;
import goblinbob.mobends.core.client.event.DataUpdateHandler;
import goblinbob.mobends.standard.data.PlayerData;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.util.math.MathHelper;

public class CapeAnimationBit
extends AnimationBit<PlayerData> {
    @Override
    public String[] getActions(PlayerData entityData) {
        return null;
    }

    @Override
    public void perform(PlayerData data) {
        AbstractClientPlayer player = (AbstractClientPlayer)data.getEntity();
        data.cape.rotation.orientX(0.0f);
        double partialTicks = DataUpdateHandler.partialTicks;
        double d0 = player.prevChasingPosX + (player.chasingPosX - player.prevChasingPosX) * partialTicks - (player.prevPosX + (player.posX - player.prevPosX) * partialTicks);
        double d1 = player.prevChasingPosY + (player.chasingPosY - player.prevChasingPosY) * partialTicks - (player.prevPosY + (player.posY - player.prevPosY) * partialTicks);
        double d2 = player.prevChasingPosZ + (player.chasingPosZ - player.prevChasingPosZ) * partialTicks - (player.prevPosZ + (player.posZ - player.prevPosZ) * partialTicks);
        double f2 = (double)player.prevRenderYawOffset + (double)(player.renderYawOffset - player.prevRenderYawOffset) * partialTicks;
        double d3 = Math.sin(f2 * 0.017453292);
        double d4 = -Math.cos(f2 * 0.017453292);
        double f1 = d1 * 10.0;
        f1 = MathHelper.clamp((double)f1, (double)-6.0, (double)32.0);
        float f22 = (float)(d0 * d3 + d2 * d4) * 100.0f;
        float f3 = (float)(d0 * d4 - d2 * d3) * 100.0f;
        if (f22 < 0.0f) {
            f22 = 0.0f;
        }
        double f4 = (double)player.prevCameraYaw + (double)(player.cameraYaw - player.prevCameraYaw) * partialTicks;
        f1 += Math.sin(((double)player.prevDistanceWalkedModified + (double)(player.distanceWalkedModified - player.prevDistanceWalkedModified) * partialTicks) * 6.0) * 32.0 * f4;
        if (player.isSneaking()) {
            f1 += 25.0;
        }
        if (data.isFlying() && player.isSprinting()) {
            data.cape.rotation.setSmoothness(0.5f).orientX(0.0f);
            data.setCapeWaveSpeed(4.0f);
        } else {
            data.cape.rotation.setSmoothness(0.5f).orientX((float)((double)(6.0f + f22 / 2.0f) + f1));
            data.cape.rotation.rotateZ(f3 / 2.0f);
            data.cape.rotation.rotateY(-f3 / 2.0f);
            data.setCapeWaveSpeed(1.0f);
        }
    }
}

