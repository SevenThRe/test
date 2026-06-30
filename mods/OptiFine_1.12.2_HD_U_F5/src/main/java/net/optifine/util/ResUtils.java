/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  ced
 *  ceg
 *  cer
 *  ces
 *  nf
 */
package net.optifine.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.Properties;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import net.optifine.reflect.Reflector;
import net.optifine.util.PropertiesOrdered;
import net.optifine.util.StrUtils;

public class ResUtils {
    public static String[] collectFiles(String prefix, String suffix) {
        return ResUtils.collectFiles(new String[]{prefix}, new String[]{suffix});
    }

    public static String[] collectFiles(String[] prefixes, String[] suffixes) {
        LinkedHashSet<String> setPaths = new LinkedHashSet<String>();
        cer[] rps = Config.getResourcePacks();
        for (int i = 0; i < rps.length; ++i) {
            cer rp = rps[i];
            String[] ps = ResUtils.collectFiles(rp, prefixes, suffixes, null);
            setPaths.addAll(Arrays.asList(ps));
        }
        String[] paths = setPaths.toArray(new String[setPaths.size()]);
        return paths;
    }

    public static String[] collectFiles(cer rp, String prefix, String suffix, String[] defaultPaths) {
        return ResUtils.collectFiles(rp, new String[]{prefix}, new String[]{suffix}, defaultPaths);
    }

    public static String[] collectFiles(cer rp, String[] prefixes, String[] suffixes) {
        return ResUtils.collectFiles(rp, prefixes, suffixes, null);
    }

    public static String[] collectFiles(cer rp, String[] prefixes, String[] suffixes, String[] defaultPaths) {
        if (rp instanceof ceg) {
            return ResUtils.collectFilesFixed(rp, defaultPaths);
        }
        if (rp instanceof ces) {
            cer rpBase = (cer)Reflector.getFieldValue(rp, Reflector.LegacyV2Adapter_pack);
            if (rpBase == null) {
                Config.warn("LegacyV2Adapter base resource pack not found: " + rp);
                return new String[0];
            }
            rp = rpBase;
        }
        if (!(rp instanceof ced)) {
            Config.warn("Unknown resource pack type: " + rp);
            return new String[0];
        }
        ced arp = (ced)rp;
        File tpFile = arp.a;
        if (tpFile == null) {
            return new String[0];
        }
        if (tpFile.isDirectory()) {
            return ResUtils.collectFilesFolder(tpFile, "", prefixes, suffixes);
        }
        if (tpFile.isFile()) {
            return ResUtils.collectFilesZIP(tpFile, prefixes, suffixes);
        }
        Config.warn("Unknown resource pack file: " + tpFile);
        return new String[0];
    }

    private static String[] collectFilesFixed(cer rp, String[] paths) {
        if (paths == null) {
            return new String[0];
        }
        ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < paths.length; ++i) {
            String path = paths[i];
            if (!ResUtils.isLowercase(path)) {
                Config.warn("Skipping non-lowercase path: " + path);
                continue;
            }
            nf loc = new nf(path);
            if (!rp.b(loc)) continue;
            list.add(path);
        }
        String[] pathArr = list.toArray(new String[list.size()]);
        return pathArr;
    }

    private static String[] collectFilesFolder(File tpFile, String basePath, String[] prefixes, String[] suffixes) {
        ArrayList<String> list = new ArrayList<String>();
        String prefixAssets = "assets/minecraft/";
        File[] files = tpFile.listFiles();
        if (files == null) {
            return new String[0];
        }
        for (int i = 0; i < files.length; ++i) {
            File file = files[i];
            if (file.isFile()) {
                String name = basePath + file.getName();
                if (!name.startsWith(prefixAssets) || !StrUtils.startsWith(name = name.substring(prefixAssets.length()), prefixes) || !StrUtils.endsWith(name, suffixes)) continue;
                if (!ResUtils.isLowercase(name)) {
                    Config.warn("Skipping non-lowercase path: " + name);
                    continue;
                }
                list.add(name);
                continue;
            }
            if (!file.isDirectory()) continue;
            String dirPath = basePath + file.getName() + "/";
            String[] names = ResUtils.collectFilesFolder(file, dirPath, prefixes, suffixes);
            for (int n = 0; n < names.length; ++n) {
                String name = names[n];
                list.add(name);
            }
        }
        String[] names = list.toArray(new String[list.size()]);
        return names;
    }

    private static String[] collectFilesZIP(File tpFile, String[] prefixes, String[] suffixes) {
        ArrayList<String> list = new ArrayList<String>();
        String prefixAssets = "assets/minecraft/";
        try {
            ZipFile zf = new ZipFile(tpFile);
            Enumeration<? extends ZipEntry> en = zf.entries();
            while (en.hasMoreElements()) {
                ZipEntry ze = en.nextElement();
                String name = ze.getName();
                if (!name.startsWith(prefixAssets) || !StrUtils.startsWith(name = name.substring(prefixAssets.length()), prefixes) || !StrUtils.endsWith(name, suffixes)) continue;
                if (!ResUtils.isLowercase(name)) {
                    Config.warn("Skipping non-lowercase path: " + name);
                    continue;
                }
                list.add(name);
            }
            zf.close();
            String[] names = list.toArray(new String[list.size()]);
            return names;
        }
        catch (IOException e) {
            e.printStackTrace();
            return new String[0];
        }
    }

    private static boolean isLowercase(String str) {
        return str.equals(str.toLowerCase(Locale.ROOT));
    }

    public static Properties readProperties(String path, String module) {
        nf loc = new nf(path);
        try {
            InputStream in = Config.getResourceStream(loc);
            if (in == null) {
                return null;
            }
            PropertiesOrdered props = new PropertiesOrdered();
            props.load(in);
            in.close();
            Config.dbg("" + module + ": Loading " + path);
            return props;
        }
        catch (FileNotFoundException e) {
            return null;
        }
        catch (IOException e) {
            Config.warn("" + module + ": Error reading " + path);
            return null;
        }
    }

    public static Properties readProperties(InputStream in, String module) {
        if (in == null) {
            return null;
        }
        try {
            PropertiesOrdered props = new PropertiesOrdered();
            props.load(in);
            in.close();
            return props;
        }
        catch (FileNotFoundException e) {
            return null;
        }
        catch (IOException e) {
            return null;
        }
    }
}

