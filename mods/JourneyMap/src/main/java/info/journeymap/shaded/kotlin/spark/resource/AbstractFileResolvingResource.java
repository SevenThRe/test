/*
 * Decompiled with CFR 0.152.
 */
package info.journeymap.shaded.kotlin.spark.resource;

import info.journeymap.shaded.kotlin.spark.resource.AbstractResource;
import info.journeymap.shaded.kotlin.spark.utils.ResourceUtils;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public abstract class AbstractFileResolvingResource
extends AbstractResource {
    @Override
    public File getFile() throws IOException {
        URL url = this.getURL();
        return ResourceUtils.getFile(url, this.getDescription());
    }

    @Override
    protected File getFileForLastModifiedCheck() throws IOException {
        URL url = this.getURL();
        if (ResourceUtils.isJarURL(url)) {
            URL actualUrl = ResourceUtils.extractJarFileURL(url);
            return ResourceUtils.getFile(actualUrl, "Jar URL");
        }
        return this.getFile();
    }

    @Override
    public boolean exists() {
        try {
            HttpURLConnection httpCon;
            URL url = this.getURL();
            if (ResourceUtils.isFileURL(url)) {
                return this.getFile().exists();
            }
            URLConnection con = url.openConnection();
            this.customizeConnection(con);
            HttpURLConnection httpURLConnection = httpCon = con instanceof HttpURLConnection ? (HttpURLConnection)con : null;
            if (httpCon != null) {
                int code = httpCon.getResponseCode();
                if (code == 200) {
                    return true;
                }
                if (code == 404) {
                    return false;
                }
            }
            if (con.getContentLength() >= 0) {
                return true;
            }
            if (httpCon != null) {
                httpCon.disconnect();
                return false;
            }
            InputStream is = this.getInputStream();
            is.close();
            return true;
        }
        catch (IOException ex) {
            return false;
        }
    }

    @Override
    public boolean isReadable() {
        try {
            URL url = this.getURL();
            if (ResourceUtils.isFileURL(url)) {
                File file = this.getFile();
                return file.canRead() && !file.isDirectory();
            }
            return true;
        }
        catch (IOException ex) {
            return false;
        }
    }

    @Override
    public long contentLength() throws IOException {
        URL url = this.getURL();
        if (ResourceUtils.isFileURL(url)) {
            return this.getFile().length();
        }
        URLConnection con = url.openConnection();
        this.customizeConnection(con);
        return con.getContentLength();
    }

    @Override
    public long lastModified() throws IOException {
        URL url = this.getURL();
        if (ResourceUtils.isFileURL(url) || ResourceUtils.isJarURL(url)) {
            return super.lastModified();
        }
        URLConnection con = url.openConnection();
        this.customizeConnection(con);
        return con.getLastModified();
    }

    protected void customizeConnection(URLConnection con) throws IOException {
        ResourceUtils.useCachesIfNecessary(con);
        if (con instanceof HttpURLConnection) {
            this.customizeConnection((HttpURLConnection)con);
        }
    }

    protected void customizeConnection(HttpURLConnection con) throws IOException {
        con.setRequestMethod("HEAD");
    }
}

