/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cer
 *  cey
 *  com.google.common.base.Splitter
 *  com.google.common.collect.Iterables
 *  nf
 *  org.apache.commons.io.Charsets
 *  org.apache.commons.io.IOUtils
 */
package net.optifine;

import com.google.common.base.Splitter;
import com.google.common.collect.Iterables;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Map;
import java.util.regex.Pattern;
import org.apache.commons.io.Charsets;
import org.apache.commons.io.IOUtils;

public class Lang {
    private static final Splitter splitter = Splitter.on((char)'=').limit(2);
    private static final Pattern pattern = Pattern.compile("%(\\d+\\$)?[\\d\\.]*[df]");

    public static void resourcesReloaded() {
        Map localeProperties = cey.getLocaleProperties();
        ArrayList<String> listFiles = new ArrayList<String>();
        String PREFIX = "optifine/lang/";
        String EN_US = "en_us";
        String SUFFIX = ".lang";
        listFiles.add(PREFIX + EN_US + SUFFIX);
        if (!Config.getGameSettings().aJ.equals(EN_US)) {
            listFiles.add(PREFIX + Config.getGameSettings().aJ + SUFFIX);
        }
        String[] files = listFiles.toArray(new String[listFiles.size()]);
        Lang.loadResources((cer)Config.getDefaultResourcePack(), files, localeProperties);
        cer[] resourcePacks = Config.getResourcePacks();
        for (int i = 0; i < resourcePacks.length; ++i) {
            cer rp = resourcePacks[i];
            Lang.loadResources(rp, files, localeProperties);
        }
    }

    private static void loadResources(cer rp, String[] files, Map localeProperties) {
        try {
            for (int i = 0; i < files.length; ++i) {
                InputStream in;
                String file = files[i];
                nf loc = new nf(file);
                if (!rp.b(loc) || (in = rp.a(loc)) == null) continue;
                Lang.loadLocaleData(in, localeProperties);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadLocaleData(InputStream is, Map localeProperties) throws IOException {
        for (String line : IOUtils.readLines((InputStream)is, (Charset)Charsets.UTF_8)) {
            String[] parts;
            if (line.isEmpty() || line.charAt(0) == '#' || (parts = (String[])Iterables.toArray((Iterable)splitter.split((CharSequence)line), String.class)) == null || parts.length != 2) continue;
            String key = parts[0];
            String value = pattern.matcher(parts[1]).replaceAll("%$1s");
            localeProperties.put(key, value);
        }
    }

    public static String get(String key) {
        return cey.a((String)key, (Object[])new Object[0]);
    }

    public static String get(String key, String def) {
        String str = cey.a((String)key, (Object[])new Object[0]);
        if (str == null || str.equals(key)) {
            return def;
        }
        return str;
    }

    public static String getOn() {
        return cey.a((String)"options.on", (Object[])new Object[0]);
    }

    public static String getOff() {
        return cey.a((String)"options.off", (Object[])new Object[0]);
    }

    public static String getFast() {
        return cey.a((String)"options.graphics.fast", (Object[])new Object[0]);
    }

    public static String getFancy() {
        return cey.a((String)"options.graphics.fancy", (Object[])new Object[0]);
    }

    public static String getDefault() {
        return cey.a((String)"generator.default", (Object[])new Object[0]);
    }
}

