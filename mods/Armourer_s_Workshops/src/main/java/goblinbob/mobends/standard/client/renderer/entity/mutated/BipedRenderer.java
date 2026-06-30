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
                GlStateManager.func_179094_E();
                GlStateManager.func_179152_a((float)scale, (float)scale, (float)scale);
                bipedData.swordTrail.render();
                bipedData.leftSwordTrail.render();
                GlStateManager.func_179131_c((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                GlStateManager.func_179121_F();
            }
        }
    }

    @Override
    protected void transformLocally(T entity, EntityData<?> data, float partialTicks) {
        if (entity.func_70093_af()) {
            GlStateManager.func_179109_b((float)0.0f, (float)0.3125f, (float)0.0f);
        }
    }
}

