/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.entity.AbstractClientPlayer
 *  net.minecraft.client.renderer.GlStateManager
 */
package goblinbob.mobends.standard.client.renderer.entity.mutated;

import goblinbob.mobends.core.data.EntityData;
import goblinbob.mobends.standard.client.renderer.entity.mutated.BipedRenderer;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.GlStateManager;

public class PlayerRenderer
extends BipedRenderer<AbstractClientPlayer> {
    @Override
    protected void transformLocally(AbstractClientPlayer entity, EntityData<?> data, float partialTicks) {
        if (entity.isSneaking()) {
            if (entity.capabilities.isFlying) {
                GlStateManager.translate((float)0.0f, (float)0.25f, (float)0.0f);
            } else {
                GlStateManager.translate((float)0.0f, (float)0.3125f, (float)0.0f);
            }
        }
    }
}

