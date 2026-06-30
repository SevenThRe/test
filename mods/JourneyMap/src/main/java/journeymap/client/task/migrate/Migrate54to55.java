/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Joiner
 *  com.google.common.base.Strings
 *  com.google.gson.Gson
 *  com.google.gson.GsonBuilder
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package journeymap.client.task.migrate;

import com.google.common.base.Joiner;
import com.google.common.base.Strings;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.File;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;
import journeymap.client.Constants;
import journeymap.client.io.FileHandler;
import journeymap.client.properties.ClientPropertiesBase;
import journeymap.common.Journeymap;
import journeymap.common.log.LogFormatter;
import journeymap.common.migrate.MigrationTask;
import journeymap.common.properties.PropertiesBase;
import journeymap.common.version.Version;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Migrate54to55
implements MigrationTask {
    protected static final Charset UTF8 = Charset.forName("UTF-8");
    protected final transient Gson gson = new GsonBuilder().setPrettyPrinting().create();
    Logger logger = LogManager.getLogger((String)"journeymap");

    @Override
    public boolean isActive(Version currentVersion) {
        if (currentVersion.toMajorMinorString().equals("5.5")) {
            String optionsManagerViewed;
            if (Journeymap.getClient().getCoreProperties() == null) {
                Journeymap.getClient().loadConfigProperties();
            }
            if (Strings.isNullOrEmpty((String)(optionsManagerViewed = Journeymap.getClient().getCoreProperties().optionsManagerViewed.get()))) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Boolean call() throws Exception {
        return this.migrateConfigs();
    }

    private boolean migrateConfigs() {
        try {
            String path5_4 = Joiner.on((String)File.separator).join((Object)Constants.JOURNEYMAP_DIR, (Object)"config", new Object[]{"5.4"});
            File legacyConfigDir = new File(FileHandler.MinecraftDirectory, path5_4);
            if (!legacyConfigDir.canRead()) {
                return true;
            }
            this.logger.info("Migrating configs from 5.4 to 5.5");
            List<ClientPropertiesBase> propertiesList = Arrays.asList(Journeymap.getClient().getCoreProperties(), Journeymap.getClient().getFullMapProperties(), Journeymap.getClient().getMiniMapProperties(1), Journeymap.getClient().getMiniMapProperties(2), Journeymap.getClient().getWaypointProperties(), Journeymap.getClient().getWebMapProperties());
            for (PropertiesBase propertiesBase : propertiesList) {
                File oldConfigfile = new File(legacyConfigDir, propertiesBase.getFile().getName());
                if (!oldConfigfile.canRead()) continue;
                try {
                    propertiesBase.load(oldConfigfile, false);
                    propertiesBase.save();
                }
                catch (Throwable t) {
                    this.logger.error(String.format("Unexpected error in migrateConfigs(): %s", LogFormatter.toString(t)));
                }
            }
            Journeymap.getClient().getCoreProperties().optionsManagerViewed.set("5.4");
            return true;
        }
        catch (Throwable t) {
            this.logger.error(String.format("Unexpected error in migrateConfigs(): %s", LogFormatter.toString(t)));
            return false;
        }
    }
}

