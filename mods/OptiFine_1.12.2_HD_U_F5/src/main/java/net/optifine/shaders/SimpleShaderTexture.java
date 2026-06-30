/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cdf
 *  cdt
 *  cep
 *  cff
 *  cfg
 *  cfi
 *  cfj
 *  cfl
 *  cfm
 *  cfo
 *  cfp
 *  cfr
 *  cfs
 *  cfv
 *  cfw
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonParser
 *  org.apache.commons.io.IOUtils
 */
package net.optifine.shaders;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import net.optifine.shaders.SMCLog;
import net.optifine.shaders.Shaders;
import org.apache.commons.io.IOUtils;

public class SimpleShaderTexture
extends cdf {
    private String texturePath;
    private static final cfg METADATA_SERIALIZER = SimpleShaderTexture.makeMetadataSerializer();

    public SimpleShaderTexture(String texturePath) {
        this.texturePath = texturePath;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void a(cep resourceManager) throws IOException {
        this.c();
        InputStream inputStream = Shaders.getShaderPackResourceStream(this.texturePath);
        if (inputStream == null) {
            throw new FileNotFoundException("Shader texture not found: " + this.texturePath);
        }
        try {
            BufferedImage bufferedimage = cdt.a((InputStream)inputStream);
            cfv tms = this.loadTextureMetadataSection();
            cdt.a((int)this.b(), (BufferedImage)bufferedimage, (boolean)tms.a(), (boolean)tms.b());
        }
        finally {
            IOUtils.closeQuietly((InputStream)inputStream);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private cfv loadTextureMetadataSection() {
        String pathMeta = this.texturePath + ".mcmeta";
        String sectionName = "texture";
        InputStream inMeta = Shaders.getShaderPackResourceStream(pathMeta);
        if (inMeta != null) {
            cfg ms = METADATA_SERIALIZER;
            BufferedReader brMeta = new BufferedReader(new InputStreamReader(inMeta));
            try {
                JsonObject jsonMeta = new JsonParser().parse((Reader)brMeta).getAsJsonObject();
                cfv meta = (cfv)ms.a(sectionName, jsonMeta);
                if (meta != null) {
                    cfv cfv2 = meta;
                    return cfv2;
                }
            }
            catch (RuntimeException re) {
                SMCLog.warning("Error reading metadata: " + pathMeta);
                SMCLog.warning("" + re.getClass().getName() + ": " + re.getMessage());
            }
            finally {
                IOUtils.closeQuietly((Reader)brMeta);
                IOUtils.closeQuietly((InputStream)inMeta);
            }
        }
        return new cfv(false, false);
    }

    private static cfg makeMetadataSerializer() {
        cfg ms = new cfg();
        ms.a((cff)new cfw(), cfv.class);
        ms.a((cff)new cfm(), cfl.class);
        ms.a((cff)new cfj(), cfi.class);
        ms.a((cff)new cfs(), cfr.class);
        ms.a((cff)new cfp(), cfo.class);
        return ms;
    }
}

