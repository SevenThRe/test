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
        double d0 = player.field_71091_bM + (player.field_71094_bP - player.field_71091_bM) * partialTicks - (player.field_70169_q + (player.field_70165_t - player.field_70169_q) * partialTicks);
        double d1 = player.field_71096_bN + (player.field_71095_bQ - player.field_71096_bN) * partialTicks - (player.field_70167_r + (player.field_70163_u - player.field_70167_r) * partialTicks);
        double d2 = player.field_71097_bO + (player.field_71085_bR - player.field_71097_bO) * partialTicks - (player.field_70166_s + (player.field_70161_v - player.field_70166_s) * partialTicks);
        double f2 = (double)player.field_70760_ar + (double)(player.field_70761_aq - player.field_70760_ar) * partialTicks;
        double d3 = Math.sin(f2 * 0.017453292);
        double d4 = -Math.cos(f2 * 0.017453292);
        double f1 = d1 * 10.0;
        f1 = MathHelper.func_151237_a((double)f1, (double)-6.0, (double)32.0);
        float f22 = (float)(d0 * d3 + d2 * d4) * 100.0f;
        float f3 = (float)(d0 * d4 - d2 * d3) * 100.0f;
        if (f22 < 0.0f) {
            f22 = 0.0f;
        }
        double f4 = (double)player.field_71107_bF + (double)(player.field_71109_bG - player.field_71107_bF) * partialTicks;
        f1 += Math.sin(((double)player.field_70141_P + (double)(player.field_70140_Q - player.field_70141_P) * partialTicks) * 6.0) * 32.0 * f4;
        if (player.func_70093_af()) {
            f1 += 25.0;
        }
        if (data.isFlying() && player.func_70051_ag()) {
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

