/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.ancientdream.client.utils.yaml;

import eos.moe.ancientdream.client.utils.yaml.YamlDeserializer;
import eos.moe.ancientdream.yaml.snakeyaml.configuration.ConfigurationSection;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class YamlHelper {
    public static <T> T loadFrom(ConfigurationSection yaml, Class<T> class_, YamlDeserializer deserializer) {
        try {
            T t = class_.newInstance();
            for (Field declaredField : class_.getDeclaredFields()) {
                String name = declaredField.getName();
                declaredField.setAccessible(true);
                if (Modifier.isPrivate(declaredField.getModifiers()) || Modifier.isPublic(declaredField.getModifiers())) {
                    if (declaredField.getType() == String.class) {
                        declaredField.set(t, String.valueOf(yaml.get(name, "")));
                        continue;
                    }
                    Object o = yaml.get(name);
                    if (o == null) {
                        throw new NullPointerException("\u65e0\u6cd5\u89e3\u6790\u8282\u70b9 " + name + " \u503c\u4e3anull");
                    }
                    declaredField.set(t, o);
                    continue;
                }
                if (deserializer == null) continue;
                declaredField.set(t, deserializer.deserialize(t, name, yaml.get(name)));
            }
            return t;
        }
        catch (Throwable e) {
            System.out.println("\u5f02\u5e38\u62a5\u9519: " + class_);
            e.printStackTrace();
            return null;
        }
    }
}

