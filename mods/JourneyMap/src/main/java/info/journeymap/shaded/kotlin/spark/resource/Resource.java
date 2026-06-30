/*
 * Decompiled with CFR 0.152.
 */
package info.journeymap.shaded.kotlin.spark.resource;

import info.journeymap.shaded.kotlin.spark.resource.InputStreamResource;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URL;

public interface Resource
extends InputStreamResource {
    public boolean exists();

    public boolean isReadable();

    public boolean isOpen();

    public URL getURL() throws IOException;

    public URI getURI() throws IOException;

    public File getFile() throws IOException;

    public long contentLength() throws IOException;

    public long lastModified() throws IOException;

    public Resource createRelative(String var1) throws IOException;

    public String getFilename();

    public String getDescription();
}

