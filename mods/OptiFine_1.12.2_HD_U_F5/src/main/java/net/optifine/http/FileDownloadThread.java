/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  bib
 */
package net.optifine.http;

import net.optifine.http.HttpPipeline;
import net.optifine.http.IFileDownloadListener;

public class FileDownloadThread
extends Thread {
    private String urlString = null;
    private IFileDownloadListener listener = null;

    public FileDownloadThread(String urlString, IFileDownloadListener listener) {
        this.urlString = urlString;
        this.listener = listener;
    }

    @Override
    public void run() {
        try {
            byte[] bytes = HttpPipeline.get(this.urlString, bib.z().M());
            this.listener.fileDownloadFinished(this.urlString, bytes, null);
        }
        catch (Exception e) {
            this.listener.fileDownloadFinished(this.urlString, null, e);
        }
    }

    public String getUrlString() {
        return this.urlString;
    }

    public IFileDownloadListener getListener() {
        return this.listener;
    }
}

