/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraftforge.fml.common.event.FMLInitializationEvent
 *  net.minecraftforge.fml.common.event.FMLLoadCompleteEvent
 *  net.minecraftforge.fml.common.event.FMLPostInitializationEvent
 *  net.minecraftforge.fml.common.event.FMLPreInitializationEvent
 *  net.minecraftforge.fml.common.network.NetworkRegistry
 *  net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper
 *  net.minecraftforge.fml.relauncher.Side
 */
package goblinbob.mobends.core;

import goblinbob.mobends.core.CoreClient;
import goblinbob.mobends.core.CoreServer;
import goblinbob.mobends.core.configuration.CoreConfig;
import goblinbob.mobends.core.module.IModule;
import goblinbob.mobends.core.network.msg.MessageConfigRequest;
import goblinbob.mobends.core.network.msg.MessageConfigResponse;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Logger;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLLoadCompleteEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public abstract class Core<T extends CoreConfig> {
    private static Core INSTANCE;
    public static final Logger LOG;
    private SimpleNetworkWrapper networkWrapper;
    private static final int MESSAGE_CONFIG_REQUEST = 0;
    private static final int MESSAGE_CONFIG_RESPONSE = 1;
    private Collection<IModule> modules = new ArrayList<IModule>();

    public abstract T getConfiguration();

    public void preInit(FMLPreInitializationEvent event) {
        this.networkWrapper = NetworkRegistry.INSTANCE.newSimpleChannel("mobends");
        this.networkWrapper.registerMessage(MessageConfigRequest.Handler.class, MessageConfigRequest.class, 0, Side.SERVER);
        this.networkWrapper.registerMessage(MessageConfigResponse.Handler.class, MessageConfigResponse.class, 1, Side.CLIENT);
        for (IModule module : this.modules) {
            module.preInit(event);
        }
    }

    public void init(FMLInitializationEvent event) {
    }

    public void postInit(FMLPostInitializationEvent event) {
    }

    public void complete(FMLLoadCompleteEvent event) {
    }

    public void registerModule(IModule module) {
        this.modules.add(module);
    }

    public void refreshModules() {
        for (IModule module : this.modules) {
            module.onRefresh();
        }
    }

    public static Core getInstance() {
        return INSTANCE;
    }

    public static void createAsClient() {
        if (INSTANCE == null) {
            INSTANCE = new CoreClient();
        }
    }

    public static void createAsServer() {
        if (INSTANCE == null) {
            INSTANCE = new CoreServer();
        }
    }

    public static SimpleNetworkWrapper getNetworkWrapper() {
        return Core.INSTANCE.networkWrapper;
    }

    public static void saveConfiguration() {
        ((CoreConfig)INSTANCE.getConfiguration()).save();
    }

    static {
        LOG = Logger.getLogger("mobends-core");
    }
}

