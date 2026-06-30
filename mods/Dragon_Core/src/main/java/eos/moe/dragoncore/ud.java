/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.HttpEntity
 *  org.apache.http.HttpResponse
 *  org.apache.http.client.methods.HttpGet
 *  org.apache.http.client.methods.HttpUriRequest
 *  org.apache.http.impl.client.CloseableHttpClient
 *  org.apache.http.impl.client.HttpClients
 */
package eos.moe.dragoncore;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class ud {
    public ud() {
        ud a2;
    }

    public static byte[] ALLATORIxDEMO(URI a2) throws IOException {
        int a3;
        HttpGet a4;
        CloseableHttpClient a5 = HttpClients.createDefault();
        HttpResponse a6 = a5.execute((HttpUriRequest)(a4 = new HttpGet(a2)));
        if (a6.getEntity() == null || a6.getStatusLine().getStatusCode() != 200) {
            throw new IOException("\u65e0\u6cd5\u4e0b\u8f7d\u6587\u4ef6 " + a2.toString() + ", status code: " + a6.getStatusLine().getStatusCode() + ", reason: " + a6.getStatusLine().getReasonPhrase());
        }
        HttpEntity a7 = a6.getEntity();
        InputStream a8 = a7.getContent();
        ByteArrayOutputStream a9 = new ByteArrayOutputStream();
        byte[] a10 = new byte[10240];
        while ((a3 = a8.read(a10)) != -1) {
            a9.write(a10, 0, a3);
        }
        a8.close();
        a9.close();
        return a9.toByteArray();
    }
}

