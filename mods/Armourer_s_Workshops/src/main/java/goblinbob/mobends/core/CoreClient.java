/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.fml.common.event.FMLInitializationEvent
 *  net.minecraftforge.fml.common.event.FMLLoadCompleteEvent
 *  net.minecraftforge.fml.common.event.FMLPostInitializationEvent
 *  net.minecraftforge.fml.common.event.FMLPreInitializationEvent
 */
package goblinbob.mobends.core;

import goblinbob.mobends.core.Core;
import goblinbob.mobends.core.bender.EntityBenderRegistry;
import goblinbob.mobends.core.client.event.DataUpdateHandler;
import goblinbob.mobends.core.client.event.EntityRenderHandler;
import goblinbob.mobends.core.client.event.FluxHandler;
import goblinbob.mobends.core.client.event.KeyboardHandler;
import goblinbob.mobends.core.client.event.WorldJoinHandler;
import goblinbob.mobends.core.configuration.CoreClientConfig;
import javax.annotation.Nullable;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLLoadCompleteEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CoreClient
extends Core<CoreClientConfig> {
    private static CoreClient INSTANCE;
    private CoreClientConfig configuration;

    CoreClient() {
        INSTANCE = this;
    }

    @Override
    public CoreClientConfig getConfiguration() {
        return this.configuration;
    }

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);
        this.configuration = new CoreClientConfig(event.getSuggestedConfigurationFile());
    }

    @Override
    public void init(FMLInitializationEvent event) {
        super.init(event);
        KeyboardHandler.initKeyBindings();
        MinecraftForge.EVENT_BUS.register((Object)new DataUpdateHandler());
        MinecraftForge.EVENT_BUS.register((Object)new KeyboardHandler());
        MinecraftForge.EVENT_BUS.register((Object)new FluxHandler());
        MinecraftForge.EVENT_BUS.register((Object)new WorldJoinHandler());
    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {
        super.postInit(event);
        EntityBenderRegistry.instance.applyConfiguration(this.configuration);
    }

    @Override
    public void complete(FMLLoadCompleteEvent event) {
        MinecraftForge.EVENT_BUS.register((Object)new EntityRenderHandler());
    }

    @Nullable
    public static CoreClient getInstance() {
        return INSTANCE;
    }
}

