/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.logging.log4j.Level
 */
package modinfo.mp.v1;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.Callable;
import modinfo.ModInfo;
import modinfo.mp.v1.Payload;
import org.apache.logging.log4j.Level;

public class Message
implements Callable<Object> {
    private final String endpoint;
    private final Payload payload;
    private final String userAgent;
    private final int retries;
    private final int connectionTimeout;
    private final int readTimeout;

    Message(String endpoint, Payload payload, String userAgent, int retries, int connectionTimeout, int readTimeout) {
        this.endpoint = endpoint;
        this.payload = payload;
        this.userAgent = userAgent;
        this.retries = retries;
        this.connectionTimeout = connectionTimeout;
        this.readTimeout = readTimeout;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public Object call() {
        String defaultAgent = System.setProperty("http.agent", "");
        int remainingRetries = Math.max(1, this.retries);
        Exception exception = null;
        Integer responseCode = null;
        while (responseCode == null && remainingRetries > 0) {
            try {
                String payloadString = this.payload.toUrlEncodedString();
                URL url = new URL(this.endpoint);
                HttpURLConnection con = (HttpURLConnection)url.openConnection();
                con.setRequestMethod("POST");
                con.setRequestProperty("User-Agent", this.userAgent);
                con.setConnectTimeout(this.connectionTimeout);
                con.setReadTimeout(this.readTimeout);
                con.setUseCaches(false);
                con.setDoOutput(true);
                DataOutputStream wr = new DataOutputStream(con.getOutputStream());
                wr.writeBytes(payloadString);
                wr.flush();
                wr.close();
                responseCode = con.getResponseCode();
            }
            catch (MalformedURLException ex) {
                exception = ex;
                ModInfo.LOGGER.log(Level.ERROR, "ModInfo got a bad URL: " + this.endpoint);
                break;
            }
            catch (IOException ex) {
                exception = ex;
                ModInfo.LOGGER.log(Level.ERROR, "ModInfo can't send message", (Throwable)ex);
            }
            finally {
                --remainingRetries;
            }
        }
        if (defaultAgent != null && defaultAgent.length() > 0) {
            System.setProperty("http.agent", defaultAgent);
        }
        if (responseCode != null) {
            return Boolean.TRUE;
        }
        if (exception == null) {
            exception = new Exception("ModInfo got a null response from endpoint");
        }
        return exception;
    }

    public static interface Callback {
        public void onResult(Object var1);
    }
}

