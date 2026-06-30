/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.launchwrapper.IClassTransformer
 */
package optifine;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import net.minecraft.launchwrapper.IClassTransformer;
import optifine.HashUtils;
import optifine.IOptiFineResourceLocator;
import optifine.IResourceProvider;
import optifine.OptiFineResourceLocator;
import optifine.Patcher;
import optifine.Utils;

public class OptiFineClassTransformer
implements IClassTransformer,
IResourceProvider,
IOptiFineResourceLocator {
    private ZipFile ofZipFile = null;
    private Map<String, String> patchMap = null;
    private Pattern[] patterns = null;
    public static OptiFineClassTransformer instance = null;

    public OptiFineClassTransformer() {
        instance = this;
        try {
            OptiFineClassTransformer.dbg("OptiFine ClassTransformer");
            URL url = OptiFineClassTransformer.class.getProtectionDomain().getCodeSource().getLocation();
            URI uri = url.toURI();
            File file = new File(uri);
            this.ofZipFile = new ZipFile(file);
            OptiFineClassTransformer.dbg("OptiFine ZIP file: " + file);
            this.patchMap = Patcher.getConfigurationMap(this.ofZipFile);
            this.patterns = Patcher.getConfigurationPatterns(this.patchMap);
            OptiFineResourceLocator.setResourceLocator(this);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        if (this.ofZipFile == null) {
            OptiFineClassTransformer.dbg("*** Can not find the OptiFine JAR in the classpath ***");
            OptiFineClassTransformer.dbg("*** OptiFine will not be loaded! ***");
        }
    }

    public byte[] transform(String name, String transformedName, byte[] bytes) {
        String nameClass = String.valueOf(name) + ".class";
        byte[] ofBytes = this.getOptiFineResource(nameClass);
        if (ofBytes != null) {
            return ofBytes;
        }
        return bytes;
    }

    @Override
    public synchronized InputStream getOptiFineResourceStream(String name) {
        byte[] bytes = this.getOptiFineResource(name = Utils.removePrefix(name, "/"));
        if (bytes == null) {
            return null;
        }
        ByteArrayInputStream in = new ByteArrayInputStream(bytes);
        return in;
    }

    @Override
    public InputStream getResourceStream(String path) {
        path = Utils.ensurePrefix(path, "/");
        return this.getClass().getResourceAsStream(path);
    }

    public synchronized byte[] getOptiFineResource(String name) {
        byte[] bytes = this.getOptiFineResourceZip(name = Utils.removePrefix(name, "/"));
        if (bytes != null) {
            return bytes;
        }
        bytes = this.getOptiFineResourcePatched(name, this);
        if (bytes != null) {
            return bytes;
        }
        return null;
    }

    public synchronized byte[] getOptiFineResourceZip(String name) {
        byte[] bytes;
        block5: {
            if (this.ofZipFile == null) {
                return null;
            }
            ZipEntry ze = this.ofZipFile.getEntry(name = Utils.removePrefix(name, "/"));
            if (ze == null) {
                return null;
            }
            try {
                InputStream in = this.ofZipFile.getInputStream(ze);
                bytes = OptiFineClassTransformer.readAll(in);
                in.close();
                if ((long)bytes.length == ze.getSize()) break block5;
                OptiFineClassTransformer.dbg("Invalid size, name: " + name + ", zip: " + ze.getSize() + ", stream: " + bytes.length);
                return null;
            }
            catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
        return bytes;
    }

    public synchronized byte[] getOptiFineResourcePatched(String name, IResourceProvider resourceProvider) {
        if (this.patterns == null || this.patchMap == null || resourceProvider == null) {
            return null;
        }
        String patchName = "patch/" + (name = Utils.removePrefix(name, "/")) + ".xdelta";
        byte[] bytes = this.getOptiFineResourceZip(patchName);
        if (bytes == null) {
            return null;
        }
        try {
            byte[] md5Mod;
            String md5ModStr;
            String md5Str;
            byte[] bytesPatched = Patcher.applyPatch(name, bytes, this.patterns, this.patchMap, resourceProvider);
            String fullMd5Name = "patch/" + name + ".md5";
            byte[] bytesMd5 = this.getOptiFineResourceZip(fullMd5Name);
            if (bytesMd5 != null && !(md5Str = new String(bytesMd5, "ASCII")).equals(md5ModStr = HashUtils.toHexString(md5Mod = HashUtils.getHashMd5(bytesPatched)))) {
                throw new IOException("MD5 not matching, name: " + name + ", saved: " + md5Str + ", patched: " + md5ModStr);
            }
            return bytesPatched;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static byte[] readAll(InputStream is) throws IOException {
        int len;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buf = new byte[1024];
        while ((len = is.read(buf)) >= 0) {
            baos.write(buf, 0, len);
        }
        is.close();
        byte[] bytes = baos.toByteArray();
        return bytes;
    }

    private static void dbg(String str) {
        System.out.println(str);
    }
}

