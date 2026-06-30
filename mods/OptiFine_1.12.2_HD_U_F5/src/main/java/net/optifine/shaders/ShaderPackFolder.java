/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.shaders;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import net.optifine.shaders.IShaderPack;
import net.optifine.util.StrUtils;

public class ShaderPackFolder
implements IShaderPack {
    protected File packFile;

    public ShaderPackFolder(String name, File file) {
        this.packFile = file;
    }

    @Override
    public void close() {
    }

    @Override
    public InputStream getResourceAsStream(String resName) {
        try {
            String name = StrUtils.removePrefixSuffix(resName, "/", "/");
            File resFile = new File(this.packFile, name);
            if (!resFile.exists()) {
                return null;
            }
            return new BufferedInputStream(new FileInputStream(resFile));
        }
        catch (Exception excp) {
            return null;
        }
    }

    @Override
    public boolean hasDirectory(String name) {
        File resFile = new File(this.packFile, name.substring(1));
        if (!resFile.exists()) {
            return false;
        }
        return resFile.isDirectory();
    }

    @Override
    public String getName() {
        return this.packFile.getName();
    }
}

