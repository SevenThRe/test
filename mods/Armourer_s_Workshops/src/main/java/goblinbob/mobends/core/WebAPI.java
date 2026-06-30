/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.Gson
 */
package goblinbob.mobends.core;

import com.google.gson.Gson;
import goblinbob.mobends.core.util.ErrorReporter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WebAPI {
    public static WebAPI INSTANCE = new WebAPI();
    private static String apiUrl = "https://raw.githubusercontent.com/mobends/mobends-resources/master/static-api.json";
    private boolean initialized = false;
    private APIData data;

    private void initialize() {
        if (this.initialized) {
            return;
        }
        try {
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            BufferedReader json = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            this.data = (APIData)new Gson().fromJson((Reader)json, APIData.class);
        }
        catch (IOException e2) {
            ErrorReporter.showErrorToPlayer("Couldn't fetch the WebAPI data. Some features may be disabled. Contact the developers if this is a prolonged issue.");
            e2.printStackTrace();
        }
        this.initialized = true;
    }

    public String getOfficialAnimationEditorUrl() {
        this.initialize();
        if (this.data == null) {
            return null;
        }
        return this.data.officialAnimationEditorUrl;
    }

    private static class APIData {
        String officialAnimationEditorUrl = null;

        private APIData() {
        }
    }
}

