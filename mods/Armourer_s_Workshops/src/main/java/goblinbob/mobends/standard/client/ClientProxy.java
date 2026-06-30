/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.projectile.EntitySpectralArrow
 *  net.minecraft.entity.projectile.EntityTippedArrow
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.fml.client.registry.RenderingRegistry
 */
package goblinbob.mobends.standard.client;

import baka.client.network.NetworkHandler;
import goblinbob.mobends.core.Core;
import goblinbob.mobends.standard.client.event.RenderingEventHandler;
import goblinbob.mobends.standard.client.renderer.entity.RenderBendsSpectralArrow;
import goblinbob.mobends.standard.client.renderer.entity.RenderBendsTippedArrow;
import goblinbob.mobends.standard.main.CommonProxy;
import net.minecraft.entity.projectile.EntitySpectralArrow;
import net.minecraft.entity.projectile.EntityTippedArrow;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class ClientProxy
extends CommonProxy {
    @Override
    public void preInit() {
        RenderingRegistry.registerEntityRenderingHandler(EntitySpectralArrow.class, RenderBendsSpectralArrow::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityTippedArrow.class, RenderBendsTippedArrow::new);
    }

    @Override
    public void init() {
        MinecraftForge.EVENT_BUS.register((Object)new RenderingEventHandler());
    }

    @Override
    public void postInit() {
        NetworkHandler.init();
    }

    @Override
    public void createCore() {
        Core.createAsClient();
    }
}

