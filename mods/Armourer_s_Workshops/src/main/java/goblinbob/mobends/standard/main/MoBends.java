/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraftforge.fml.common.Mod
 *  net.minecraftforge.fml.common.Mod$EventHandler
 *  net.minecraftforge.fml.common.Mod$Instance
 *  net.minecraftforge.fml.common.SidedProxy
 *  net.minecraftforge.fml.common.event.FMLInitializationEvent
 *  net.minecraftforge.fml.common.event.FMLLoadCompleteEvent
 *  net.minecraftforge.fml.common.event.FMLPostInitializationEvent
 *  net.minecraftforge.fml.common.event.FMLPreInitializationEvent
 */
package goblinbob.mobends.standard.main;

import goblinbob.mobends.core.Core;
import goblinbob.mobends.core.addon.AddonHelper;
import goblinbob.mobends.core.addon.Addons;
import goblinbob.mobends.core.animation.keyframe.AnimationLoader;
import goblinbob.mobends.core.bender.EntityBenderRegistry;
import goblinbob.mobends.core.data.EntityDatabase;
import goblinbob.mobends.core.pack.PackDataProvider;
import goblinbob.mobends.core.util.GsonResources;
import goblinbob.mobends.standard.DefaultAddon;
import goblinbob.mobends.standard.main.CommonProxy;
import java.util.logging.Logger;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLLoadCompleteEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid="mobends", acceptedMinecraftVersions="[1.12.2]")
public class MoBends {
    @SidedProxy(serverSide="goblinbob.mobends.standard.main.CommonProxy", clientSide="goblinbob.mobends.standard.client.ClientProxy")
    public static CommonProxy proxy;
    @Mod.Instance(value="mobends")
    public static MoBends instance;
    public static final Logger LOG;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        proxy.createCore();
        Core.getInstance().preInit(event);
        proxy.preInit();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        Core.getInstance().init(event);
        proxy.init();
        AddonHelper.registerAddon("mobends", new DefaultAddon());
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        Core.getInstance().postInit(event);
        proxy.postInit();
    }

    @Mod.EventHandler
    public void complete(FMLLoadCompleteEvent event) {
        Core.getInstance().complete(event);
    }

    public static void refreshSystems() {
        AnimationLoader.clearCache();
        GsonResources.clearCache();
        PackDataProvider.INSTANCE.clearCache();
        EntityDatabase.instance.refresh();
        EntityBenderRegistry.instance.refreshMutators();
        Addons.onRefresh();
        Core.getInstance().refreshModules();
    }

    static {
        LOG = Logger.getLogger("mobends");
    }
}

