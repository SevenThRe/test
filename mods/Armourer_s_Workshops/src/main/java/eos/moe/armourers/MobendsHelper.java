/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.entity.AbstractClientPlayer
 *  net.minecraft.client.renderer.entity.RenderLivingBase
 *  net.minecraft.client.renderer.entity.RenderPlayer
 *  net.minecraft.entity.EntityLivingBase
 */
package eos.moe.armourers;

import goblinbob.mobends.core.bender.EntityBender;
import goblinbob.mobends.core.bender.EntityBenderRegistry;
import goblinbob.mobends.core.client.model.IModelPart;
import goblinbob.mobends.dragon.AnimationHelper;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.EntityLivingBase;

public class MobendsHelper {
    public static void transToHand(RenderLivingBase<?> a2, EntityLivingBase a3, float a4) {
        EntityBender<EntityLivingBase> entityBender = EntityBenderRegistry.instance.getForEntity(a3);
        if (entityBender.isAnimated()) {
            RenderLivingBase<?> renderLivingBase = a2;
            AnimationHelper.preRender(a3, renderLivingBase, true);
            entityBender.applyMutation(renderLivingBase, a3, a4);
        }
    }

    public static void removeRenderEntity(RenderPlayer a2) {
        if (a2.func_177087_b().field_178723_h instanceof IModelPart) {
            ((IModelPart)a2.func_177087_b().field_178723_h).setRenderEntity(null);
        }
    }

    public MobendsHelper() {
        MobendsHelper a2;
    }

    public static boolean isAnimation() {
        return EntityBenderRegistry.instance.getForEntityClass(AbstractClientPlayer.class).isAnimated();
    }
}

