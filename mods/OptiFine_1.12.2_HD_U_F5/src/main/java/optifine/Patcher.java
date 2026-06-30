/*
 * Decompiled with CFR 0.152.
 */
package optifine;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;
import optifine.Differ;
import optifine.HashUtils;
import optifine.IResourceProvider;
import optifine.Utils;
import optifine.ZipResourceProvider;
import optifine.xdelta.GDiffPatcher;
import optifine.xdelta.PatchException;

public class Patcher {
    public static final String CONFIG_FILE = "patch.cfg";
    public static final String CONFIG_FILE2 = "patch2.cfg";
    public static final String CONFIG_FILE3 = "patch3.cfg";
    public static final String PREFIX_PATCH = "patch/";
    public static final String SUFFIX_DELTA = ".xdelta";
    public static final String SUFFIX_MD5 = ".md5";

    public static void main(String[] args) throws Exception {
        if (args.length < 3) {
            Utils.dbg("Usage: Patcher <base.jar> <diff.jar> <mod.jar>");
            return;
        }
        File baseFile = new File(args[0]);
        File diffFile = new File(args[1]);
        File modFile = new File(args[2]);
        if (baseFile.getName().equals("AUTO")) {
            baseFile = Differ.detectBaseFile(diffFile);
        }
        if (!baseFile.exists() || !baseFile.isFile()) {
            throw new IOException("Base file not found: " + baseFile);
        }
        if (!diffFile.exists() || !diffFile.isFile()) {
            throw new IOException("Diff file not found: " + modFile);
        }
        Patcher.process(baseFile, diffFile, modFile);
    }

    public static void process(File baseFile, File diffFile, File modFile) throws Exception {
        ZipFile diffZip = new ZipFile(diffFile);
        Map<String, String> cfgMap = Patcher.getConfigurationMap(diffZip);
        Pattern[] patterns = Patcher.getConfigurationPatterns(cfgMap);
        ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(modFile));
        ZipFile baseZip = new ZipFile(baseFile);
        ZipResourceProvider zrp = new ZipResourceProvider(baseZip);
        Enumeration<? extends ZipEntry> diffZipEntries = diffZip.entries();
        while (diffZipEntries.hasMoreElements()) {
            ZipEntry diffZipEntry = diffZipEntries.nextElement();
            InputStream in = diffZip.getInputStream(diffZipEntry);
            byte[] bytes = Utils.readAll(in);
            String name = diffZipEntry.getName();
            if (name.startsWith(PREFIX_PATCH) && name.endsWith(SUFFIX_DELTA)) {
                byte[] md5Mod;
                String md5ModStr;
                byte[] md5;
                String md5Str;
                name = name.substring(PREFIX_PATCH.length());
                name = name.substring(0, name.length() - SUFFIX_DELTA.length());
                byte[] bytesMod = Patcher.applyPatch(name, bytes, patterns, cfgMap, zrp);
                String nameMd5 = PREFIX_PATCH + name + SUFFIX_MD5;
                ZipEntry diffZipEntryMd5 = diffZip.getEntry(nameMd5);
                if (diffZipEntryMd5 != null && !(md5Str = new String(md5 = Utils.readAll(diffZip.getInputStream(diffZipEntryMd5)), "ASCII")).equals(md5ModStr = HashUtils.toHexString(md5Mod = HashUtils.getHashMd5(bytesMod)))) {
                    throw new Exception("MD5 not matching, name: " + name + ", saved: " + md5Str + ", patched: " + md5ModStr);
                }
                ZipEntry zipEntryMod = new ZipEntry(name);
                zipOut.putNextEntry(zipEntryMod);
                zipOut.write(bytesMod);
                zipOut.closeEntry();
                Utils.dbg("Mod: " + name);
                continue;
            }
            if (name.startsWith(PREFIX_PATCH) && name.endsWith(SUFFIX_MD5)) continue;
            ZipEntry zipEntrySame = new ZipEntry(name);
            zipOut.putNextEntry(zipEntrySame);
            zipOut.write(bytes);
            zipOut.closeEntry();
            Utils.dbg("Same: " + zipEntrySame.getName());
        }
        zipOut.close();
    }

    public static byte[] applyPatch(String name, byte[] bytesDiff, Pattern[] patterns, Map<String, String> cfgMap, IResourceProvider resourceProvider) throws IOException, PatchException {
        String baseName = Patcher.getPatchBase(name = Utils.removePrefix(name, "/"), patterns, cfgMap);
        if (baseName == null) {
            throw new IOException("No patch base, name: " + name + ", patterns: " + Utils.arrayToCommaSeparatedString(patterns));
        }
        InputStream baseIn = resourceProvider.getResourceStream(baseName);
        if (baseIn == null) {
            throw new IOException("Base resource not found: " + baseName);
        }
        byte[] baseBytes = Utils.readAll(baseIn);
        ByteArrayInputStream patchStream = new ByteArrayInputStream(bytesDiff);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        new GDiffPatcher(baseBytes, (InputStream)patchStream, (OutputStream)outputStream);
        outputStream.close();
        return outputStream.toByteArray();
    }

    public static Pattern[] getConfigurationPatterns(Map<String, String> cfgMap) {
        String[] cfgKeys = cfgMap.keySet().toArray(new String[0]);
        Pattern[] patterns = new Pattern[cfgKeys.length];
        int i = 0;
        while (i < cfgKeys.length) {
            String key = cfgKeys[i];
            patterns[i] = Pattern.compile(key);
            ++i;
        }
        return patterns;
    }

    public static Map<String, String> getConfigurationMap(ZipFile modZip) throws IOException {
        Map<String, String> cfgMap = Patcher.getConfigurationMap(modZip, CONFIG_FILE);
        Map<String, String> cfgMap2 = Patcher.getConfigurationMap(modZip, CONFIG_FILE2);
        Map<String, String> cfgMap3 = Patcher.getConfigurationMap(modZip, CONFIG_FILE3);
        cfgMap.putAll(cfgMap2);
        cfgMap.putAll(cfgMap3);
        return cfgMap;
    }

    public static Map<String, String> getConfigurationMap(ZipFile modZip, String pathConfig) throws IOException {
        LinkedHashMap<String, String> cfgMap = new LinkedHashMap<String, String>();
        if (modZip == null) {
            return cfgMap;
        }
        ZipEntry entryPatch = modZip.getEntry(pathConfig);
        if (entryPatch == null) {
            return cfgMap;
        }
        InputStream inPatch = modZip.getInputStream(entryPatch);
        String[] lines = Utils.readLines(inPatch, "ASCII");
        inPatch.close();
        int i = 0;
        while (i < lines.length) {
            String line = lines[i].trim();
            if (!line.startsWith("#") && line.length() > 0) {
                String[] parts = Utils.tokenize(line, "=");
                if (parts.length != 2) {
                    throw new IOException("Invalid patch configuration: " + line);
                }
                String key = parts[0].trim();
                String val = parts[1].trim();
                cfgMap.put(key, val);
            }
            ++i;
        }
        return cfgMap;
    }

    public static String getPatchBase(String name, Pattern[] patterns, Map<String, String> cfgMap) {
        name = Utils.removePrefix(name, "/");
        int i = 0;
        while (i < patterns.length) {
            Pattern pattern = patterns[i];
            Matcher matcher = pattern.matcher(name);
            if (matcher.matches()) {
                String base = cfgMap.get(pattern.pattern());
                if (base != null && base.trim().equals("*")) {
                    return name;
                }
                return base;
            }
            ++i;
        }
        return null;
    }
}

