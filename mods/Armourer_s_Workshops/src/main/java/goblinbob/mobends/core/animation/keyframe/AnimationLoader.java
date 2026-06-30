/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.Gson
 *  com.google.gson.stream.JsonReader
 *  net.minecraft.client.Minecraft
 *  net.minecraft.util.ResourceLocation
 */
package goblinbob.mobends.core.animation.keyframe;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import goblinbob.mobends.core.animation.keyframe.BinaryAnimationLoader;
import goblinbob.mobends.core.animation.keyframe.KeyframeAnimation;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

public class AnimationLoader {
    private static Map<String, KeyframeAnimation> internalRegistry = new HashMap<String, KeyframeAnimation>();
    private static Map<ResourceLocation, KeyframeAnimation> cachedAnimations = new HashMap<ResourceLocation, KeyframeAnimation>();

    public static void clearCache() {
        internalRegistry.clear();
        cachedAnimations.clear();
    }

    public static KeyframeAnimation loadFromFile(File file) throws IOException {
        if (file.getName().endsWith(".json")) {
            JsonReader fileReader = new JsonReader((Reader)new FileReader(file));
            return (KeyframeAnimation)new Gson().fromJson(fileReader, KeyframeAnimation.class);
        }
        return BinaryAnimationLoader.loadFromBinaryInputStream(new BufferedInputStream(new FileInputStream(file)));
    }

    public static KeyframeAnimation loadFromString(String animationJson) {
        return (KeyframeAnimation)new Gson().fromJson(animationJson, KeyframeAnimation.class);
    }

    public static KeyframeAnimation loadFromResource(ResourceLocation location) throws IOException {
        InputStream stream = Minecraft.getMinecraft().getResourceManager().getResource(location).getInputStream();
        if (cachedAnimations.containsKey(location)) {
            return cachedAnimations.get(location);
        }
        KeyframeAnimation animation = null;
        animation = location.getPath().endsWith(".json") ? (KeyframeAnimation)new Gson().fromJson((Reader)new InputStreamReader(stream), KeyframeAnimation.class) : BinaryAnimationLoader.loadFromBinaryInputStream(stream);
        if (animation != null) {
            cachedAnimations.put(location, animation);
        }
        return animation;
    }

    public static KeyframeAnimation loadFromPath(String key) throws IOException {
        int colonIndex = key.indexOf(":");
        if (colonIndex != -1) {
            String domain = key.substring(0, colonIndex);
            String path = key.substring(colonIndex + 1);
            return AnimationLoader.loadFromResource(new ResourceLocation(domain, path));
        }
        return internalRegistry.get(key);
    }
}

