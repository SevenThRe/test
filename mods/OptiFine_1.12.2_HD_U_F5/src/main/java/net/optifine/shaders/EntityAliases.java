/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  nf
 */
package net.optifine.shaders;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import net.optifine.config.ConnectedParser;
import net.optifine.reflect.Reflector;
import net.optifine.reflect.ReflectorForge;
import net.optifine.shaders.IShaderPack;
import net.optifine.shaders.Shaders;
import net.optifine.shaders.config.MacroProcessor;
import net.optifine.util.PropertiesOrdered;
import net.optifine.util.StrUtils;

public class EntityAliases {
    private static int[] entityAliases = null;
    private static boolean updateOnResourcesReloaded;

    public static int getEntityAliasId(int entityId) {
        if (entityAliases == null) {
            return -1;
        }
        if (entityId < 0 || entityId >= entityAliases.length) {
            return -1;
        }
        int aliasId = entityAliases[entityId];
        return aliasId;
    }

    public static void resourcesReloaded() {
        if (!updateOnResourcesReloaded) {
            return;
        }
        updateOnResourcesReloaded = false;
        EntityAliases.update(Shaders.getShaderPack());
    }

    public static void update(IShaderPack shaderPack) {
        EntityAliases.reset();
        if (shaderPack == null) {
            return;
        }
        if (Reflector.Loader_getActiveModList.exists() && Config.getResourceManager() == null) {
            Config.dbg("[Shaders] Delayed loading of entity mappings after resources are loaded");
            updateOnResourcesReloaded = true;
            return;
        }
        ArrayList<Integer> listEntityAliases = new ArrayList<Integer>();
        String path = "/shaders/entity.properties";
        InputStream in = shaderPack.getResourceAsStream(path);
        if (in != null) {
            EntityAliases.loadEntityAliases(in, path, listEntityAliases);
        }
        EntityAliases.loadModEntityAliases(listEntityAliases);
        if (listEntityAliases.size() <= 0) {
            return;
        }
        entityAliases = EntityAliases.toArray(listEntityAliases);
    }

    private static void loadModEntityAliases(List<Integer> listEntityAliases) {
        String[] modIds = ReflectorForge.getForgeModIds();
        for (int i = 0; i < modIds.length; ++i) {
            String modId = modIds[i];
            try {
                nf loc = new nf(modId, "shaders/entity.properties");
                InputStream in = Config.getResourceStream(loc);
                EntityAliases.loadEntityAliases(in, loc.toString(), listEntityAliases);
                continue;
            }
            catch (IOException iOException) {
                // empty catch block
            }
        }
    }

    private static void loadEntityAliases(InputStream in, String path, List<Integer> listEntityAliases) {
        if (in == null) {
            return;
        }
        try {
            in = MacroProcessor.process(in, path);
            PropertiesOrdered props = new PropertiesOrdered();
            props.load(in);
            in.close();
            Config.dbg("[Shaders] Parsing entity mappings: " + path);
            ConnectedParser cp = new ConnectedParser("Shaders");
            Set<Object> keys = ((Properties)props).keySet();
            for (String string : keys) {
                String val = props.getProperty(string);
                String prefix = "entity.";
                if (!string.startsWith(prefix)) {
                    Config.warn("[Shaders] Invalid entity ID: " + string);
                    continue;
                }
                String aliasIdStr = StrUtils.removePrefix(string, prefix);
                int aliasId = Config.parseInt(aliasIdStr, -1);
                if (aliasId < 0) {
                    Config.warn("[Shaders] Invalid entity alias ID: " + aliasId);
                    continue;
                }
                int[] entityIds = cp.parseEntities(val);
                if (entityIds == null || entityIds.length < 1) {
                    Config.warn("[Shaders] Invalid entity ID mapping: " + string + "=" + val);
                    continue;
                }
                for (int i = 0; i < entityIds.length; ++i) {
                    int entityId = entityIds[i];
                    EntityAliases.addToList(listEntityAliases, entityId, aliasId);
                }
            }
        }
        catch (IOException e) {
            Config.warn("[Shaders] Error reading: " + path);
        }
    }

    private static void addToList(List<Integer> list, int index, int val) {
        while (list.size() <= index) {
            list.add(-1);
        }
        list.set(index, val);
    }

    private static int[] toArray(List<Integer> list) {
        int[] arr2 = new int[list.size()];
        for (int i = 0; i < arr2.length; ++i) {
            arr2[i] = list.get(i);
        }
        return arr2;
    }

    public static void reset() {
        entityAliases = null;
    }
}

