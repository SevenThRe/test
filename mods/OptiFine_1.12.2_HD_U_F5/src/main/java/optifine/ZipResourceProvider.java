/*
 * Decompiled with CFR 0.152.
 */
package optifine;

import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import optifine.IResourceProvider;
import optifine.Utils;

public class ZipResourceProvider
implements IResourceProvider {
    private ZipFile zipFile = null;

    public ZipResourceProvider(ZipFile zipFile) {
        this.zipFile = zipFile;
    }

    @Override
    public InputStream getResourceStream(String path) throws IOException {
        ZipEntry zipEntry = this.zipFile.getEntry(path = Utils.removePrefix(path, "/"));
        if (zipEntry == null) {
            return null;
        }
        InputStream in = this.zipFile.getInputStream(zipEntry);
        return in;
    }
}

