/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.logging.log4j.Level
 */
package modinfo.mp.v1;

import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.atomic.AtomicInteger;
import modinfo.Config;
import modinfo.ModInfo;
import modinfo.mp.v1.Message;
import modinfo.mp.v1.Payload;
import org.apache.logging.log4j.Level;

public class Client {
    public static final String ENDPOINT = "http://www.google-analytics.com/collect";
    private final String VERBOSE_PATTERN = "ModInfo (%s): %s";
    private final String trackingId;
    private final UUID clientId;
    private final Config config;
    private final String userAgent;
    private final ExecutorService service;
    private int retries = 5;
    private int connectTimeout = 5000;
    private int readTimeout = 2000;
    private AtomicInteger messageCount = new AtomicInteger(0);

    public Client(String trackingId, UUID clientId, Config config, String defaultUserLanguage) {
        this.trackingId = trackingId;
        this.clientId = clientId;
        this.config = config;
        this.userAgent = this.createUserAgent(defaultUserLanguage);
        this.service = Executors.newFixedThreadPool(2);
        if (config.isVerbose().booleanValue()) {
            this.showVerboseMessage("User-Agent: " + this.userAgent);
        }
    }

    public Future send(Payload payload) {
        return this.send(payload, null);
    }

    public Future send(Payload payload, Message.Callback callback) {
        if (this.config.isEnabled().booleanValue()) {
            payload.put(Payload.Parameter.Version, "1");
            payload.put(Payload.Parameter.TrackingId, this.trackingId);
            payload.put(Payload.Parameter.ClientId, this.clientId.toString());
            payload.put(Payload.Parameter.CustomMetric1, Integer.toString(this.messageCount.incrementAndGet()));
            Message message = new Message(ENDPOINT, payload, this.userAgent, this.retries, this.connectTimeout, this.readTimeout);
            FutureTask<Object> future = new FutureTask<Object>(this.getRunnableWrapper(message, payload, callback), null);
            this.service.submit(future);
            return future;
        }
        return null;
    }

    private Runnable getRunnableWrapper(final Message message, final Payload payload, final Message.Callback callback) {
        return new Runnable(){

            @Override
            public void run() {
                Object result = null;
                try {
                    result = message.call();
                }
                catch (Throwable t) {
                    ModInfo.LOGGER.log(Level.ERROR, "ModInfo couldn't send message", t);
                }
                try {
                    if (Client.this.config.isVerbose().booleanValue() && Boolean.TRUE.equals(result)) {
                        Client.this.showVerboseMessage(payload.toVerboseString());
                    }
                }
                catch (Throwable t) {
                    ModInfo.LOGGER.log(Level.ERROR, "ModInfo couldn't do verbose output", t);
                }
                try {
                    if (callback != null) {
                        callback.onResult(result);
                    }
                }
                catch (Throwable t) {
                    ModInfo.LOGGER.log(Level.ERROR, "ModInfo couldn't use callback", t);
                }
            }
        };
    }

    private String createUserAgent(String defaultUserLanguage) {
        String agent = null;
        try {
            String lang;
            String arch;
            String version;
            String os = System.getProperty("os.name");
            if (os == null) {
                os = "";
            }
            if ((version = System.getProperty("os.version")) == null) {
                version = "";
            }
            if ((arch = System.getProperty("os.arch")) == null) {
                arch = "";
            }
            if (arch.equals("amd64")) {
                arch = "WOW64";
            }
            if ((lang = String.format("%s_%s", System.getProperty("user.language"), System.getProperty("user.country"))).contains("null")) {
                lang = defaultUserLanguage;
            }
            if (os.startsWith("Mac")) {
                version = version.replace(".", "_");
                agent = String.format("Mozilla/5.0 (Macintosh; U; Intel Mac OS X %s; %s)", version, lang);
            } else {
                agent = os.startsWith("Win") ? String.format("Mozilla/5.0 (Windows; U; Windows NT %s; %s; %s)", version, arch, lang) : (os.startsWith("Linux") ? String.format("Mozilla/5.0 (Linux; U; Linux %s; %s; %s)", version, arch, lang) : String.format("Mozilla/5.0 (%s; U; %s %s; %s, %s)", os, os, version, arch, lang));
            }
        }
        catch (Throwable t) {
            ModInfo.LOGGER.log(Level.ERROR, "ModInfo couldn't create useragent string", t);
            agent = "Mozilla/5.0 (Unknown)";
        }
        return agent;
    }

    private void showVerboseMessage(String message) {
        System.out.println(String.format("ModInfo (%s): %s", this.config.getModId(), message));
    }

    protected void finalize() throws Throwable {
        super.finalize();
        if (this.service != null) {
            this.service.shutdown();
        }
    }
}

