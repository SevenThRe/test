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

public class ItemAliases {
    private static int[] itemAliases = null;
    private static boolean updateOnResourcesReloaded;
    private static final int NO_ALIAS = Integer.MIN_VALUE;

    public static int getItemAliasId(int itemId) {
        if (itemAliases == null) {
            return itemId;
        }
        if (itemId < 0 || itemId >= itemAliases.length) {
            return itemId;
        }
        int aliasId = itemAliases[itemId];
        if (aliasId == Integer.MIN_VALUE) {
            return itemId;
        }
        return aliasId;
    }

    public static void resourcesReloaded() {
        if (!updateOnResourcesReloaded) {
            return;
        }
        updateOnResourcesReloaded = false;
        ItemAliases.update(Shaders.getShaderPack());
    }

    public static void update(IShaderPack shaderPack) {
        ItemAliases.reset();
        if (shaderPack == null) {
            return;
        }
        if (Reflector.Loader_getActiveModList.exists() && Config.getResourceManager() == null) {
            Config.dbg("[Shaders] Delayed loading of item mappings after resources are loaded");
            updateOnResourcesReloaded = true;
            return;
        }
        ArrayList<Integer> listItemAliases = new ArrayList<Integer>();
        String path = "/shaders/item.properties";
        InputStream in = shaderPack.getResourceAsStream(path);
        if (in != null) {
            ItemAliases.loadItemAliases(in, path, listItemAliases);
        }
        ItemAliases.loadModItemAliases(listItemAliases);
        if (listItemAliases.size() <= 0) {
            return;
        }
        itemAliases = ItemAliases.toArray(listItemAliases);
    }

    private static void loadModItemAliases(List<Integer> listItemAliases) {
        String[] modIds = ReflectorForge.getForgeModIds();
        for (int i = 0; i < modIds.length; ++i) {
            String modId = modIds[i];
            try {
                nf loc = new nf(modId, "shaders/item.properties");
                InputStream in = Config.getResourceStream(loc);
                ItemAliases.loadItemAliases(in, loc.toString(), listItemAliases);
                continue;
            }
            catch (IOException iOException) {
                // empty catch block
            }
        }
    }

    private static void loadItemAliases(InputStream in, String path, List<Integer> listItemAliases) {
        if (in == null) {
            return;
        }
        try {
            in = MacroProcessor.process(in, path);
            PropertiesOrdered props = new PropertiesOrdered();
            props.load(in);
            in.close();
            Config.dbg("[Shaders] Parsing item mappings: " + path);
            ConnectedParser cp = new ConnectedParser("Shaders");
            Set<Object> keys = ((Properties)props).keySet();
            for (String string : keys) {
                String val = props.getProperty(string);
                String prefix = "item.";
                if (!string.startsWith(prefix)) {
                    Config.warn("[Shaders] Invalid item ID: " + string);
                    continue;
                }
                String aliasIdStr = StrUtils.removePrefix(string, prefix);
                int aliasId = Config.parseInt(aliasIdStr, -1);
                if (aliasId < 0) {
                    Config.warn("[Shaders] Invalid item alias ID: " + aliasId);
                    continue;
                }
                int[] itemIds = cp.parseItems(val);
                if (itemIds == null || itemIds.length < 1) {
                    Config.warn("[Shaders] Invalid item ID mapping: " + string + "=" + val);
                    continue;
                }
                for (int i = 0; i < itemIds.length; ++i) {
                    int itemId = itemIds[i];
                    ItemAliases.addToList(listItemAliases, itemId, aliasId);
                }
            }
        }
        catch (IOException e) {
            Config.warn("[Shaders] Error reading: " + path);
        }
    }

    private static void addToList(List<Integer> list, int index, int val) {
        while (list.size() <= index) {
            list.add(Integer.MIN_VALUE);
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
        itemAliases = null;
    }
}

