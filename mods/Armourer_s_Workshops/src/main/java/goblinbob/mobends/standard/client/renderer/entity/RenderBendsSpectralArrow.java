/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.entity.Render
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.entity.projectile.EntitySpectralArrow
 *  net.minecraft.util.ResourceLocation
 *  net.minecraftforge.fml.client.registry.IRenderFactory
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package goblinbob.mobends.standard.client.renderer.entity;

import goblinbob.mobends.standard.client.renderer.entity.RenderBendsArrow;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.projectile.EntitySpectralArrow;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(value=Side.CLIENT)
public class RenderBendsSpectralArrow
extends RenderBendsArrow<EntitySpectralArrow> {
    public static final ResourceLocation RES_SPECTRAL_ARROW = new ResourceLocation("textures/entity/projectiles/spectral_arrow.png");

    public RenderBendsSpectralArrow(RenderManager manager) {
        super(manager);
    }

    protected ResourceLocation getEntityTexture(EntitySpectralArrow entity) {
        return RES_SPECTRAL_ARROW;
    }

    public static class Factory
    implements IRenderFactory {
        public Render createRenderFor(RenderManager manager) {
            return new RenderBendsSpectralArrow(manager);
        }
    }
}

