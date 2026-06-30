/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Joiner
 */
package net.optifine.shaders;

import com.google.common.base.Joiner;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayDeque;
import java.util.Enumeration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import net.optifine.shaders.IShaderPack;
import net.optifine.util.StrUtils;

public class ShaderPackZip
implements IShaderPack {
    protected File packFile;
    protected ZipFile packZipFile;
    protected String baseFolder;

    public ShaderPackZip(String name, File file) {
        this.packFile = file;
        this.packZipFile = null;
        this.baseFolder = "";
    }

    @Override
    public void close() {
        if (this.packZipFile == null) {
            return;
        }
        try {
            this.packZipFile.close();
        }
        catch (Exception exception) {
            // empty catch block
        }
        this.packZipFile = null;
    }

    @Override
    public InputStream getResourceAsStream(String resName) {
        try {
            ZipEntry entry;
            String name;
            if (this.packZipFile == null) {
                this.packZipFile = new ZipFile(this.packFile);
                this.baseFolder = this.detectBaseFolder(this.packZipFile);
            }
            if ((name = StrUtils.removePrefix(resName, "/")).contains("..")) {
                name = this.resolveRelative(name);
            }
            if ((entry = this.packZipFile.getEntry(this.baseFolder + name)) == null) {
                return null;
            }
            return this.packZipFile.getInputStream(entry);
        }
        catch (Exception excp) {
            return null;
        }
    }

    private String resolveRelative(String name) {
        ArrayDeque<String> stack = new ArrayDeque<String>();
        String[] parts = Config.tokenize(name, "/");
        for (int i = 0; i < parts.length; ++i) {
            String part = parts[i];
            if (part.equals("..")) {
                if (stack.isEmpty()) {
                    return "";
                }
                stack.removeLast();
                continue;
            }
            stack.add(part);
        }
        String path = Joiner.on((char)'/').join(stack);
        return path;
    }

    private String detectBaseFolder(ZipFile zip) {
        ZipEntry entryShaders = zip.getEntry("shaders/");
        if (entryShaders != null && entryShaders.isDirectory()) {
            return "";
        }
        Pattern patternFolderShaders = Pattern.compile("([^/]+/)shaders/");
        Enumeration<? extends ZipEntry> entries = zip.entries();
        while (entries.hasMoreElements()) {
            String base;
            ZipEntry entry = entries.nextElement();
            String name = entry.getName();
            Matcher matcher = patternFolderShaders.matcher(name);
            if (!matcher.matches() || (base = matcher.group(1)) == null) continue;
            if (base.equals("shaders/")) {
                return "";
            }
            return base;
        }
        return "";
    }

    @Override
    public boolean hasDirectory(String resName) {
        try {
            if (this.packZipFile == null) {
                this.packZipFile = new ZipFile(this.packFile);
                this.baseFolder = this.detectBaseFolder(this.packZipFile);
            }
            String name = StrUtils.removePrefix(resName, "/");
            ZipEntry entry = this.packZipFile.getEntry(this.baseFolder + name);
            return entry != null;
        }
        catch (IOException e) {
            return false;
        }
    }

    @Override
    public String getName() {
        return this.packFile.getName();
    }
}

