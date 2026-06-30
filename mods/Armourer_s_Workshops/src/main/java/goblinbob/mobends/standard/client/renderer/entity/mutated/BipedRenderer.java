/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.entity.EntityLivingBase
 */
package goblinbob.mobends.standard.client.renderer.entity.mutated;

import goblinbob.mobends.core.client.MutatedRenderer;
import goblinbob.mobends.core.data.EntityData;
import goblinbob.mobends.standard.data.BipedEntityData;
import goblinbob.mobends.standard.main.ModConfig;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.EntityLivingBase;

public class BipedRenderer<T extends EntityLivingBase>
extends MutatedRenderer<T> {
    @Override
    public void renderLocalAccessories(T entity, EntityData<?> data, float partialTicks) {
        float scale = 0.0625f;
        if (data instanceof BipedEntityData) {
            BipedEntityData bipedData = (BipedEntityData)data;
            if (ModConfig.showSwordTrail) {
                GlStateManager.pushMatrix();
                GlStateManager.scale((float)scale, (float)scale, (float)scale);
                bipedData.swordTrail.render();
                bipedData.leftSwordTrail.render();
                GlStateManager.color((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                GlStateManager.popMatrix();
            }
        }
    }

    @Override
    protected void transformLocally(T entity, EntityData<?> data, float partialTicks) {
        if (entity.isSneaking()) {
            GlStateManager.translate((float)0.0f, (float)0.3125f, (float)0.0f);
        }
    }
}

