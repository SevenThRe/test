/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.Gson
 *  com.google.gson.GsonBuilder
 *  org.apache.logging.log4j.Level
 */
package modinfo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Serializable;
import java.util.UUID;
import journeymap.client.io.FileHandler;
import modinfo.ModInfo;
import org.apache.logging.log4j.Level;

public class Config
implements Serializable {
    private static final String[] HEADERS = new String[]{"// ModInfo v%s - Configuration file for %s", "// ModInfo is a simple utility which helps the Mod developer support their mod.", "// For more information: https://github.com/MCModInfo/modinfo/blob/master/README.md"};
    private static final String PARENT_DIR = "config";
    private static final String FILE_PATTERN = "%s_ModInfo.cfg";
    private static final String ENABLED_STATUS_PATTERN = "Enabled (%s)";
    private static final String DISABLED_STATUS_PATTERN = "Disabled (%s)";
    private String modId;
    private Boolean enable;
    private String salt;
    private String status;
    private Boolean verbose;

    private Config() {
    }

    public static synchronized Config getInstance(String modId) {
        Config config;
        block4: {
            config = null;
            File configFile = Config.getFile(modId);
            if (configFile.exists()) {
                try {
                    Gson gson = new Gson();
                    config = (Config)gson.fromJson((Reader)new FileReader(configFile), Config.class);
                }
                catch (Exception e) {
                    ModInfo.LOGGER.log(Level.ERROR, "Can't read file " + configFile, (Object)e.getMessage());
                    if (!configFile.exists()) break block4;
                    configFile.delete();
                }
            }
        }
        if (config == null) {
            config = new Config();
        }
        super.validate(modId);
        return config;
    }

    static boolean isConfirmedDisabled(Config config) {
        return config.enable == false && Config.generateStatusString(config).equals(config.status);
    }

    static String generateStatusString(Config config) {
        return Config.generateStatusString(config.modId, config.enable);
    }

    static String generateStatusString(String modId, Boolean enable) {
        UUID uuid = ModInfo.createUUID(modId, enable.toString());
        String pattern = enable != false ? ENABLED_STATUS_PATTERN : DISABLED_STATUS_PATTERN;
        return String.format(pattern, uuid.toString());
    }

    private static File getFile(String modId) {
        File dir = new File(FileHandler.getMinecraftDirectory(), PARENT_DIR);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        return new File(dir, String.format(FILE_PATTERN, modId.replaceAll("%", "_")));
    }

    private void validate(String modId) {
        boolean dirty = false;
        if (!modId.equals(this.modId)) {
            this.modId = modId;
            dirty = true;
        }
        if (this.enable == null) {
            this.enable = Boolean.TRUE;
            dirty = true;
        }
        if (this.salt == null) {
            this.salt = Long.toHexString(System.currentTimeMillis());
            dirty = true;
        }
        if (this.verbose == null) {
            this.verbose = Boolean.FALSE;
            dirty = true;
        }
        if (dirty) {
            this.save();
        }
    }

    public void save() {
        File configFile = Config.getFile(this.modId);
        try {
            String lineEnding = System.getProperty("line.separator");
            StringBuilder sb = new StringBuilder();
            for (String line : HEADERS) {
                sb.append(line).append(lineEnding);
            }
            String header = String.format(sb.toString(), "0.2", this.modId);
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String json = gson.toJson((Object)this);
            FileWriter fw = new FileWriter(configFile);
            fw.write(header);
            fw.write(json);
            fw.flush();
            fw.close();
        }
        catch (IOException e) {
            ModInfo.LOGGER.log(Level.ERROR, "Can't save file " + configFile, (Throwable)e);
        }
    }

    public String getSalt() {
        return this.salt;
    }

    public String getModId() {
        return this.modId;
    }

    public Boolean isEnabled() {
        return this.enable;
    }

    public Boolean isVerbose() {
        return this.verbose;
    }

    public String getStatus() {
        return this.status;
    }

    void disable() {
        this.enable = false;
        this.confirmStatus();
    }

    public void confirmStatus() {
        String newStatus = Config.generateStatusString(this);
        if (!newStatus.equals(this.status)) {
            this.status = newStatus;
            this.save();
        }
    }
}

