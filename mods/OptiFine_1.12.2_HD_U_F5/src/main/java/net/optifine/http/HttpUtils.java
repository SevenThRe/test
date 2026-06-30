/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  bib
 */
package net.optifine.http;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.Set;

public class HttpUtils {
    private static String playerItemsUrl = null;
    public static final String SERVER_URL = "http://s.optifine.net";
    public static final String POST_URL = "http://optifine.net";

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static byte[] get(String urlStr) throws IOException {
        HttpURLConnection conn = null;
        try {
            int len;
            URL url = new URL(urlStr);
            conn = (HttpURLConnection)url.openConnection(bib.z().M());
            conn.setDoInput(true);
            conn.setDoOutput(false);
            conn.connect();
            if (conn.getResponseCode() / 100 != 2) {
                if (conn.getErrorStream() != null) {
                    Config.readAll(conn.getErrorStream());
                }
                throw new IOException("HTTP response: " + conn.getResponseCode());
            }
            InputStream in = conn.getInputStream();
            byte[] bytes = new byte[conn.getContentLength()];
            int pos = 0;
            do {
                if ((len = in.read(bytes, pos, bytes.length - pos)) >= 0) continue;
                throw new IOException("Input stream closed: " + urlStr);
            } while ((pos += len) < bytes.length);
            byte[] byArray = bytes;
            return byArray;
        }
        finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static String post(String urlStr, Map headers, byte[] content) throws IOException {
        HttpURLConnection conn = null;
        try {
            String line;
            URL url = new URL(urlStr);
            conn = (HttpURLConnection)url.openConnection(bib.z().M());
            conn.setRequestMethod("POST");
            if (headers != null) {
                Set keys = headers.keySet();
                for (String key : keys) {
                    String val = "" + headers.get(key);
                    conn.setRequestProperty(key, val);
                }
            }
            conn.setRequestProperty("Content-Type", "text/plain");
            conn.setRequestProperty("Content-Length", "" + content.length);
            conn.setRequestProperty("Content-Language", "en-US");
            conn.setUseCaches(false);
            conn.setDoInput(true);
            conn.setDoOutput(true);
            OutputStream os = conn.getOutputStream();
            os.write(content);
            os.flush();
            os.close();
            InputStream in = conn.getInputStream();
            InputStreamReader isr = new InputStreamReader(in, "ASCII");
            BufferedReader br = new BufferedReader(isr);
            StringBuffer sb = new StringBuffer();
            while ((line = br.readLine()) != null) {
                sb.append(line);
                sb.append('\r');
            }
            br.close();
            String string = sb.toString();
            return string;
        }
        finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
    }

    public static synchronized String getPlayerItemsUrl() {
        if (playerItemsUrl == null) {
            try {
                boolean local = Config.parseBoolean(System.getProperty("player.models.local"), false);
                if (local) {
                    File dirMc = bib.z().w;
                    File dirModels = new File(dirMc, "playermodels");
                    playerItemsUrl = dirModels.toURI().toURL().toExternalForm();
                }
            }
            catch (Exception e) {
                Config.warn("" + e.getClass().getName() + ": " + e.getMessage());
            }
            if (playerItemsUrl == null) {
                playerItemsUrl = SERVER_URL;
            }
        }
        return playerItemsUrl;
    }
}

