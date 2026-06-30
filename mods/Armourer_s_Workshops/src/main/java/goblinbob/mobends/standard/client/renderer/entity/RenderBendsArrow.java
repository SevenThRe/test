/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.entity.RenderArrow
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.entity.projectile.EntityArrow
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package goblinbob.mobends.standard.client.renderer.entity;

import goblinbob.mobends.standard.client.renderer.entity.ArrowTrailManager;
import goblinbob.mobends.standard.main.ModConfig;
import net.minecraft.client.renderer.entity.RenderArrow;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(value=Side.CLIENT)
public abstract class RenderBendsArrow<T extends EntityArrow>
extends RenderArrow<T> {
    public RenderBendsArrow(RenderManager renderManagerIn) {
        super(renderManagerIn);
    }

    public void doRender(T entity, double x2, double y2, double z2, float entityYaw, float partialTicks) {
        if (ModConfig.showArrowTrails) {
            ArrowTrailManager.renderTrail(entity, x2, y2, z2, partialTicks);
        }
        super.doRender(entity, x2, y2, z2, entityYaw, partialTicks);
    }
}

