/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonParser
 *  javax.annotation.Nullable
 *  net.minecraftforge.common.ForgeVersion
 */
package com.teamderpy.shouldersurfing.asm;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nullable;
import net.minecraftforge.common.ForgeVersion;

public class Mappings {
    private final Map<String, ClassMapping> mappings;

    public Mappings(Map<String, ClassMapping> mapping) {
        this.mappings = mapping;
    }

    @Nullable
    public String map(String name, boolean obf) {
        ClassMapping mapping = this.mappings.get(name);
        if (mapping != null) {
            return mapping.get(obf);
        }
        return null;
    }

    @Nullable
    public String desc(String name, boolean obf) {
        ClassMapping mapping = this.mappings.get(name);
        if (mapping != null && mapping instanceof DescMapping) {
            return ((DescMapping)mapping).desc(this.mappings, obf);
        }
        return null;
    }

    public boolean isObfuscated(String klassName) {
        for (ClassMapping klass : this.mappings.values()) {
            if (!klass.getName().equals(klassName)) continue;
            return false;
        }
        return true;
    }

    public static Mappings load(String file) {
        InputStream is = Mappings.class.getClassLoader().getResourceAsStream(file);
        JsonObject json = new JsonParser().parse((Reader)new InputStreamReader(is)).getAsJsonObject();
        HashMap<String, ClassMapping> mappings = new HashMap<String, ClassMapping>();
        try {
            String version = ForgeVersion.class.getDeclaredField("mcVersion").get(ForgeVersion.class).toString();
            for (Map.Entry entry : json.getAsJsonObject("classes").entrySet()) {
                JsonObject object = ((JsonElement)entry.getValue()).getAsJsonObject();
                ClassMapping klass = new ClassMapping(object.get("name").getAsString(), Mappings.readObf(object, version));
                mappings.put((String)entry.getKey(), klass);
            }
            Mappings.loadDescMappings(json.getAsJsonObject("methods").entrySet(), mappings, version);
            Mappings.loadDescMappings(json.getAsJsonObject("fields").entrySet(), mappings, version);
        }
        catch (IllegalAccessException | IllegalArgumentException | NoSuchFieldException | NullPointerException | SecurityException ex2) {
            throw new RuntimeException("Unable to load mappings");
        }
        return new Mappings(mappings);
    }

    private static void loadDescMappings(Set<Map.Entry<String, JsonElement>> set, Map<String, ClassMapping> mappings, String version) {
        for (Map.Entry<String, JsonElement> entry : set) {
            JsonObject object = entry.getValue().getAsJsonObject();
            DescMapping klass = new DescMapping(object.get("name").getAsString(), object.get("desc").getAsString(), Mappings.readObf(object, version));
            mappings.put(entry.getKey(), klass);
        }
    }

    private static String readObf(JsonObject object, String version) {
        JsonElement obf = object.get("obf");
        if (obf.isJsonPrimitive()) {
            return obf.getAsString();
        }
        return obf.getAsJsonObject().get(version).getAsString();
    }

    private static class DescMapping
    extends ClassMapping {
        private final String desc;

        public DescMapping(String name, String desc, String obf) {
            super(name, obf);
            this.desc = desc;
        }

        public String desc(Map<String, ClassMapping> mappings, boolean obf) {
            String desc = this.desc;
            for (Map.Entry<String, ClassMapping> mapping : mappings.entrySet()) {
                desc = desc.replace("L$" + mapping.getKey() + ";", "L" + mapping.getValue().get(obf) + ";");
            }
            return desc;
        }
    }

    private static class ClassMapping {
        private final String name;
        private final String obf;

        public ClassMapping(String name, String obf) {
            this.name = name;
            this.obf = obf;
        }

        public String get(boolean obf) {
            return obf ? this.getObf() : this.getName();
        }

        public String getName() {
            return this.name;
        }

        public String getObf() {
            return this.obf;
        }
    }
}

