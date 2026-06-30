/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.util.ResourceLocation
 */
package goblinbob.mobends.core.util;

import goblinbob.mobends.core.kumo.KumoSerializer;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

public class GsonResources {
    private static Map<ResourceLocation, Object> cache = new HashMap<ResourceLocation, Object>();

    public static void clearCache() {
        cache.clear();
    }

    public static <T> T get(ResourceLocation location, Class<T> classOfT) throws IOException {
        InputStream stream = Minecraft.func_71410_x().func_110442_L().func_110536_a(location).func_110527_b();
        if (cache.containsKey(location)) {
            return (T)cache.get(location);
        }
        Object resource = KumoSerializer.INSTANCE.gson.fromJson((Reader)new InputStreamReader(stream), classOfT);
        cache.put(location, resource);
        return (T)resource;
    }
}

