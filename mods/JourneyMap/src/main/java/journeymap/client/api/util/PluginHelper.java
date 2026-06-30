/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Strings
 *  javax.annotation.ParametersAreNonnullByDefault
 *  net.minecraftforge.fml.common.discovery.ASMDataTable
 *  net.minecraftforge.fml.common.discovery.ASMDataTable$ASMData
 *  net.minecraftforge.fml.common.event.FMLInitializationEvent
 *  net.minecraftforge.fml.common.event.FMLPreInitializationEvent
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package journeymap.client.api.util;

import com.google.common.base.Strings;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import javax.annotation.ParametersAreNonnullByDefault;
import journeymap.client.api.ClientPlugin;
import journeymap.client.api.IClientAPI;
import journeymap.client.api.IClientPlugin;
import net.minecraftforge.fml.common.discovery.ASMDataTable;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@ParametersAreNonnullByDefault
public enum PluginHelper {
    INSTANCE;

    public static final Logger LOGGER;
    public static final String PLUGIN_ANNOTATION_NAME;
    public static final String PLUGIN_INTERFACE_NAME;
    protected Map<String, IClientPlugin> plugins = null;
    protected boolean initialized;

    public Map<String, IClientPlugin> preInitPlugins(FMLPreInitializationEvent event) {
        if (this.plugins == null) {
            ASMDataTable asmDataTable = event.getAsmData();
            HashMap<String, IClientPlugin> discovered = new HashMap<String, IClientPlugin>();
            Set asmDataSet = asmDataTable.getAll(PLUGIN_ANNOTATION_NAME);
            for (ASMDataTable.ASMData asmData : asmDataSet) {
                String className = asmData.getClassName();
                try {
                    Class<?> pluginClass = Class.forName(className);
                    if (IClientPlugin.class.isAssignableFrom(pluginClass)) {
                        Class<IClientPlugin> interfaceImplClass = pluginClass.asSubclass(IClientPlugin.class);
                        IClientPlugin instance = interfaceImplClass.newInstance();
                        String modId = instance.getModId();
                        if (Strings.isNullOrEmpty((String)modId)) {
                            throw new Exception("IClientPlugin.getModId() must return a non-empty, non-null value");
                        }
                        if (discovered.containsKey(modId)) {
                            Class<?> otherPluginClass = ((IClientPlugin)discovered.get(modId)).getClass();
                            throw new Exception(String.format("Multiple plugins trying to use the same modId: %s and %s", interfaceImplClass, otherPluginClass));
                        }
                        discovered.put(modId, instance);
                        LOGGER.info(String.format("Found @%s: %s", PLUGIN_ANNOTATION_NAME, className));
                        continue;
                    }
                    LOGGER.error(String.format("Found @%s: %s, but it doesn't implement %s", PLUGIN_ANNOTATION_NAME, className, PLUGIN_INTERFACE_NAME));
                }
                catch (Exception e) {
                    LOGGER.error(String.format("Found @%s: %s, but failed to instantiate it: %s", PLUGIN_ANNOTATION_NAME, className, e.getMessage()), (Throwable)e);
                }
            }
            if (discovered.isEmpty()) {
                LOGGER.info("No plugins for JourneyMap API discovered.");
            }
            this.plugins = Collections.unmodifiableMap(discovered);
        }
        return this.plugins;
    }

    public Map<String, IClientPlugin> initPlugins(FMLInitializationEvent event, IClientAPI clientAPI) {
        if (this.plugins == null) {
            LOGGER.warn("Plugin discovery never occurred.", (Throwable)new IllegalStateException());
        } else if (!this.initialized) {
            LOGGER.info(String.format("Initializing plugins with Client API: %s", clientAPI.getClass().getName()));
            HashMap<String, IClientPlugin> discovered = new HashMap<String, IClientPlugin>(this.plugins);
            Iterator<IClientPlugin> iter = discovered.values().iterator();
            while (iter.hasNext()) {
                IClientPlugin plugin = iter.next();
                try {
                    plugin.initialize(clientAPI);
                    LOGGER.info(String.format("Initialized %s: %s", PLUGIN_INTERFACE_NAME, plugin.getClass().getName()));
                }
                catch (Exception e) {
                    LOGGER.error("Failed to initialize IClientPlugin: " + plugin.getClass().getName(), (Throwable)e);
                    iter.remove();
                }
            }
            this.plugins = Collections.unmodifiableMap(discovered);
            this.initialized = true;
        } else {
            LOGGER.warn("Plugins already initialized!", (Throwable)new IllegalStateException());
        }
        return this.plugins;
    }

    public Map<String, IClientPlugin> getPlugins() {
        return this.plugins;
    }

    static {
        LOGGER = LogManager.getLogger((String)"journeymap");
        PLUGIN_ANNOTATION_NAME = ClientPlugin.class.getCanonicalName();
        PLUGIN_INTERFACE_NAME = IClientPlugin.class.getSimpleName();
    }
}

