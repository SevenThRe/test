/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.Gson
 *  com.google.gson.JsonSyntaxException
 *  com.google.gson.stream.JsonReader
 */
package goblinbob.mobends.core.pack;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.JsonReader;
import goblinbob.mobends.core.Core;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;

public class PublicDatabase {
    public PackEntry[] packs;

    public static PublicDatabase downloadPublicDatabase(String databaseUrl) {
        try {
            URL publicDirectoryUrl = new URL(databaseUrl);
            JsonReader fileReader = new JsonReader((Reader)new InputStreamReader(publicDirectoryUrl.openStream()));
            Gson gson = new Gson();
            return (PublicDatabase)gson.fromJson(fileReader, PublicDatabase.class);
        }
        catch (JsonSyntaxException e2) {
            Core.LOG.warning("The downloaded database is not proper JSON.");
        }
        catch (IOException e3) {
            e3.printStackTrace();
        }
        return null;
    }

    public class PackEntry {
        public String name;
        public String displayName;
        public String author;
        public String description;
        public String uploadedDate;
        public String updatedDate;
        public String downloadLink;
        public String thumbnail;
    }
}

