/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.cache.CacheLoader
 *  com.google.common.collect.ImmutableMap
 *  net.minecraftforge.fml.client.FMLClientHandler
 */
package journeymap.client.data;

import com.google.common.cache.CacheLoader;
import com.google.common.collect.ImmutableMap;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import journeymap.client.Constants;
import journeymap.client.io.FileHandler;
import net.minecraftforge.fml.client.FMLClientHandler;

public class MessagesData
extends CacheLoader<Class, Map<String, Object>> {
    private static final String KEY_PREFIX = "jm.webmap.";

    public Map<String, Object> load(Class aClass) throws Exception {
        HashMap<String, Object> props = new HashMap<String, Object>();
        props.put("locale", Constants.getLocale());
        props.put("lang", FMLClientHandler.instance().getClient().gameSettings.language);
        Properties properties = FileHandler.getLangFile("en_US.lang");
        if (properties != null) {
            Enumeration<Object> allKeys = properties.keys();
            while (allKeys.hasMoreElements()) {
                String key = (String)allKeys.nextElement();
                if (!key.startsWith(KEY_PREFIX)) continue;
                String name = key.split(KEY_PREFIX)[1];
                String value = Constants.getString(key);
                props.put(name, value);
            }
        }
        return ImmutableMap.copyOf(props);
    }

    public long getTTL() {
        return TimeUnit.DAYS.toMillis(1L);
    }
}

