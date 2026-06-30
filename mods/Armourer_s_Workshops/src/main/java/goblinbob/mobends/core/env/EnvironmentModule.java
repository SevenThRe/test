/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.Gson
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  net.minecraftforge.fml.common.event.FMLPreInitializationEvent
 */
package goblinbob.mobends.core.env;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import goblinbob.mobends.core.Core;
import goblinbob.mobends.core.env.EnvironmentConfig;
import goblinbob.mobends.core.module.IModule;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Map;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class EnvironmentModule {
    private static EnvironmentModule INSTANCE;
    private static final EnvironmentConfig DEFAULT_CONFIG;
    private final File localConfigFile;
    private EnvironmentConfig config = DEFAULT_CONFIG;

    public EnvironmentModule(File configDirectory) {
        File modConfigDirectory = new File(configDirectory, "mobends");
        this.localConfigFile = new File(modConfigDirectory, "env.json");
        this.resolveConfig();
    }

    private void resolveConfig() {
        JsonObject localConfig = EnvironmentModule.getLocalEnvironment(this.localConfigFile);
        if (localConfig != null) {
            Gson gson = new Gson();
            JsonObject propertyMap = gson.toJsonTree((Object)DEFAULT_CONFIG).getAsJsonObject();
            for (Map.Entry entry : localConfig.entrySet()) {
                propertyMap.add((String)entry.getKey(), (JsonElement)entry.getValue());
            }
            this.config = (EnvironmentConfig)gson.fromJson((JsonElement)propertyMap, EnvironmentConfig.class);
        }
    }

    private static JsonObject getLocalEnvironment(File envFile) {
        try {
            if (envFile.isFile()) {
                return (JsonObject)new Gson().fromJson((Reader)new BufferedReader(new FileReader(envFile)), JsonObject.class);
            }
        }
        catch (IOException e2) {
            Core.LOG.warning("Couldn't load the local environment configuration.");
        }
        return null;
    }

    public static EnvironmentConfig getConfig() {
        return EnvironmentModule.INSTANCE.config;
    }

    static {
        DEFAULT_CONFIG = new EnvironmentConfig();
        EnvironmentModule.DEFAULT_CONFIG.apiUrl = "https://mobends.com";
    }

    public static class Factory
    implements IModule {
        @Override
        public void preInit(FMLPreInitializationEvent event) {
            INSTANCE = new EnvironmentModule(event.getModConfigurationDirectory());
        }

        @Override
        public void onRefresh() {
            INSTANCE.resolveConfig();
        }
    }
}

