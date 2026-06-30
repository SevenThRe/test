/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.io.CharStreams
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonParser
 *  org.apache.http.HttpResponse
 *  org.apache.http.client.config.RequestConfig
 *  org.apache.http.client.methods.HttpGet
 *  org.apache.http.client.methods.HttpUriRequest
 *  org.apache.http.impl.client.CloseableHttpClient
 *  org.apache.http.impl.client.HttpClientBuilder
 */
package journeymap.common.version;

import com.google.common.io.CharStreams;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import journeymap.common.Journeymap;
import journeymap.common.thread.JMThreadFactory;
import journeymap.common.version.Version;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

public class VersionCheck {
    private static volatile ExecutorService executorService;
    private static volatile Boolean updateCheckEnabled;
    private static volatile Boolean versionIsCurrent;
    private static volatile Boolean versionIsChecked;
    private static volatile String versionAvailable;
    private static volatile String downloadUrl;

    public static Boolean getVersionIsCurrent() {
        if (versionIsChecked == null) {
            VersionCheck.checkVersion();
        }
        return versionIsCurrent;
    }

    public static Boolean getVersionIsChecked() {
        if (versionIsChecked == null) {
            VersionCheck.checkVersion();
        }
        return versionIsChecked;
    }

    public static String getVersionAvailable() {
        if (versionIsChecked == null) {
            VersionCheck.checkVersion();
        }
        return versionAvailable;
    }

    public static String getDownloadUrl() {
        if (versionIsChecked == null) {
            VersionCheck.checkVersion();
        }
        return downloadUrl;
    }

    private static synchronized void checkVersion() {
        versionIsChecked = false;
        versionIsCurrent = true;
        versionAvailable = "0";
        if (!updateCheckEnabled.booleanValue()) {
            Journeymap.getLogger().info("Update check disabled in properties file.");
        } else {
            executorService = Executors.newSingleThreadExecutor(new JMThreadFactory("VersionCheck"));
            executorService.submit(new Runnable(){

                /*
                 * WARNING - Removed try catching itself - possible behaviour change.
                 */
                @Override
                public void run() {
                    String currentVersion = Journeymap.JM_VERSION.toString();
                    boolean currentIsRelease = Journeymap.JM_VERSION.isRelease();
                    InputStreamReader in = null;
                    try {
                        URI uri = URI.create("https://api.cfwidget.com/minecraft/mc-mods/journeymap");
                        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(6000).setSocketTimeout(6000).setRedirectsEnabled(true).build();
                        CloseableHttpClient httpClient = HttpClientBuilder.create().setDefaultRequestConfig(requestConfig).build();
                        HttpResponse response = httpClient.execute((HttpUriRequest)new HttpGet(uri));
                        if (response.getStatusLine().getStatusCode() / 200 == 1) {
                            in = new InputStreamReader(response.getEntity().getContent());
                            String rawResponse = CharStreams.toString((Readable)in);
                            JsonObject project = new JsonParser().parse(rawResponse).getAsJsonObject();
                            JsonElement version = project.get("versions").getAsJsonObject().get("1.12.2");
                            if (version == null) {
                                Journeymap.getLogger().warn("No versions found online for 1.12.2");
                            } else {
                                Iterator files = version.getAsJsonArray().iterator();
                                while (files.hasNext()) {
                                    JsonObject file = ((JsonElement)files.next()).getAsJsonObject();
                                    try {
                                        String name;
                                        JsonElement type = file.get("type");
                                        if (currentIsRelease && !"release".equals(type.getAsString()) || !(name = file.get("name").getAsString()).contains("1.12.2") || !(name = name.split("1.12.2")[1]).contains("-")) continue;
                                        String fileVersion = name.split("-")[1];
                                        String url = "http://minecraft.curseforge.com/projects/journeymap/files/" + file.get("id").getAsString();
                                        if (VersionCheck.isCurrent(currentVersion, fileVersion)) continue;
                                        downloadUrl = url;
                                        versionAvailable = fileVersion;
                                        versionIsCurrent = false;
                                        versionIsChecked = true;
                                        Journeymap.getLogger().info(String.format("Newer version online: JourneyMap %s for Minecraft %s on %s", versionAvailable, "1.12.2", downloadUrl));
                                        break;
                                    }
                                    catch (Exception e) {
                                        Journeymap.getLogger().error("Could not parse download info: " + file, (Throwable)e);
                                    }
                                }
                            }
                            if (!versionIsChecked.booleanValue()) {
                                versionAvailable = currentVersion;
                                versionIsCurrent = true;
                                versionIsChecked = true;
                                downloadUrl = "http://minecraft.curseforge.com/projects/journeymap/files/";
                            }
                        } else {
                            Journeymap.getLogger().error(String.format("Version check to %s returned: %s ", uri, response.getStatusLine()));
                        }
                    }
                    catch (Throwable e) {
                        Journeymap.getLogger().error("Could not check version URL", e);
                        updateCheckEnabled = false;
                    }
                    finally {
                        if (in != null) {
                            try {
                                in.close();
                                executorService.shutdown();
                                executorService = null;
                            }
                            catch (IOException uri) {}
                        }
                    }
                    if (!versionIsCurrent.booleanValue()) {
                        // empty if block
                    }
                }
            });
        }
    }

    private static boolean isCurrent(String thisVersionStr, String availableVersionStr) {
        if (thisVersionStr.equals(availableVersionStr)) {
            return true;
        }
        Version thisVersion = Version.from(thisVersionStr, null);
        Version availableVersion = Version.from(availableVersionStr, null);
        return !availableVersion.isNewerThan(thisVersion);
    }

    static {
        updateCheckEnabled = Journeymap.proxy.isUpdateCheckEnabled();
        versionIsCurrent = true;
    }
}

