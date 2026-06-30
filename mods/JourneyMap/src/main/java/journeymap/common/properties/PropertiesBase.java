/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.MoreObjects
 *  com.google.common.base.MoreObjects$ToStringHelper
 *  com.google.common.base.Objects
 *  com.google.common.base.Strings
 *  com.google.common.io.Files
 *  com.google.gson.ExclusionStrategy
 *  com.google.gson.FieldAttributes
 *  com.google.gson.Gson
 *  com.google.gson.GsonBuilder
 */
package journeymap.common.properties;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Strings;
import com.google.common.io.Files;
import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.File;
import java.lang.reflect.Field;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import journeymap.client.model.GridSpec;
import journeymap.common.Journeymap;
import journeymap.common.log.LogFormatter;
import journeymap.common.properties.Category;
import journeymap.common.properties.CategorySet;
import journeymap.common.properties.config.BooleanField;
import journeymap.common.properties.config.ConfigField;
import journeymap.common.properties.config.CustomField;
import journeymap.common.properties.config.EnumField;
import journeymap.common.properties.config.GsonHelper;
import journeymap.common.properties.config.IntegerField;
import journeymap.common.properties.config.StringField;
import journeymap.common.version.Version;

public abstract class PropertiesBase {
    protected static final Charset UTF8 = Charset.forName("UTF-8");
    protected Version configVersion = null;
    protected CategorySet categories = new CategorySet();
    protected transient File sourceFile = null;
    protected transient State currentState = State.New;
    private transient Map<String, ConfigField<?>> configFields;

    protected PropertiesBase() {
    }

    public Gson getGson(boolean verbose) {
        GsonBuilder gb = new GsonBuilder().setPrettyPrinting().serializeNulls().registerTypeAdapter(BooleanField.class, (Object)new GsonHelper.BooleanFieldSerializer(verbose)).registerTypeAdapter(IntegerField.class, (Object)new GsonHelper.IntegerFieldSerializer(verbose)).registerTypeAdapter(StringField.class, (Object)new GsonHelper.StringFieldSerializer(verbose)).registerTypeAdapter(CustomField.class, (Object)new GsonHelper.TextFieldSerializer(verbose)).registerTypeAdapter(EnumField.class, (Object)new GsonHelper.EnumFieldSerializer(verbose)).registerTypeAdapter(CategorySet.class, (Object)new GsonHelper.CategorySetSerializer(verbose)).registerTypeAdapter(Version.class, (Object)new GsonHelper.VersionSerializer(verbose)).registerTypeAdapter(GridSpec.class, (Object)new GsonHelper.GridSpecSerializer(verbose));
        List<ExclusionStrategy> exclusionStrategies = this.getExclusionStrategies(verbose);
        if (exclusionStrategies != null && !exclusionStrategies.isEmpty()) {
            gb.setExclusionStrategies(exclusionStrategies.toArray(new ExclusionStrategy[exclusionStrategies.size()]));
        }
        return gb.create();
    }

    public <T extends PropertiesBase> T fromJsonString(String jsonString, Class<T> propertiesClass, boolean verbose) {
        return (T)((PropertiesBase)this.getGson(verbose).fromJson(jsonString, propertiesClass));
    }

    public abstract String getName();

    public abstract File getFile();

    public abstract String[] getHeaders();

    public abstract String getFileName();

    public boolean isCurrent() {
        return Journeymap.JM_VERSION.equals(this.configVersion);
    }

    public <T extends PropertiesBase> T load() {
        return this.load(this.getFile(), false);
    }

    public <T extends PropertiesBase> T load(File configFile, boolean verbose) {
        this.ensureInit();
        boolean saveNeeded = false;
        if (!configFile.canRead() || configFile.length() == 0L) {
            this.postLoad(true);
            this.currentState = State.FirstLoaded;
            saveNeeded = true;
        } else {
            try {
                String jsonString = Files.toString((File)configFile, (Charset)UTF8);
                Object jsonInstance = this.fromJsonString(jsonString, this.getClass(), verbose);
                this.updateFrom((T)jsonInstance);
                this.postLoad(false);
                this.currentState = State.FileLoaded;
                saveNeeded = !this.isValid(false);
            }
            catch (Exception e) {
                this.error(String.format("Can't load config file %s", configFile), e);
                try {
                    File badPropFile = new File(configFile.getParentFile(), configFile.getName() + ".bad");
                    configFile.renameTo(badPropFile);
                }
                catch (Exception e3) {
                    this.error(String.format("Can't rename config file %s: %s", configFile, e3.getMessage()));
                }
            }
        }
        if (saveNeeded) {
            this.save(configFile, verbose);
        }
        return (T)this;
    }

    protected void postLoad(boolean isNew) {
        this.ensureInit();
    }

    public <T extends PropertiesBase> void updateFrom(T otherInstance) {
        for (Map.Entry<String, ConfigField<?>> otherEntry : otherInstance.getConfigFields().entrySet()) {
            String fieldName = otherEntry.getKey();
            ConfigField<?> otherField = otherEntry.getValue();
            if (Strings.isNullOrEmpty((String)fieldName) || otherField == null) {
                this.warn("Bad configField entry during updateFrom(): " + otherEntry);
                continue;
            }
            if (otherField.getAttributeMap() == null) {
                this.warn("Bad configField source (no attributes) during updateFrom(): " + fieldName);
                continue;
            }
            ConfigField<?> myField = this.getConfigField(fieldName);
            if (myField == null) {
                this.warn("configField target doesn't exist during updateFrom(): " + fieldName);
                continue;
            }
            if (myField.getAttributeMap() == null) {
                this.warn("Bad configField target (no attributes) during updateFrom(): " + fieldName);
                continue;
            }
            myField.getAttributeMap().putAll(otherField.getAttributeMap());
        }
        this.configVersion = otherInstance.configVersion;
    }

    protected void ensureInit() {
        if (this.configFields == null) {
            this.getConfigFields();
            this.currentState = State.Initialized;
        }
    }

    protected void preSave() {
        this.ensureInit();
    }

    public boolean save() {
        return this.save(this.getFile(), false);
    }

    public boolean save(File configFile, boolean verbose) {
        this.preSave();
        boolean saved = false;
        boolean canSave = this.isValid(true);
        if (!canSave) {
            this.error(String.format("Can't save invalid config to file: %s", this.getFileName()));
        } else {
            try {
                if (!configFile.exists()) {
                    this.info(String.format("Creating config file: %s", configFile));
                    if (!configFile.getParentFile().exists()) {
                        configFile.getParentFile().mkdirs();
                    }
                } else if (!this.isCurrent()) {
                    if (this.configVersion != null) {
                        this.info(String.format("Updating config file from version \"%s\" to \"%s\": %s", this.configVersion, Journeymap.JM_VERSION, configFile));
                    }
                    this.configVersion = Journeymap.JM_VERSION;
                }
                StringBuilder sb = new StringBuilder();
                String lineEnding = System.getProperty("line.separator");
                for (String line : this.getHeaders()) {
                    sb.append(line).append(lineEnding);
                }
                String header = sb.toString();
                String json = this.toJsonString(verbose);
                Files.write((CharSequence)(header + json), (File)configFile, (Charset)UTF8);
                saved = true;
            }
            catch (Exception e) {
                this.error(String.format("Can't save config file %s: %s", configFile, e), e);
            }
        }
        this.currentState = saved ? State.SavedOk : State.SavedError;
        return saved;
    }

    public String toJsonString(boolean verbose) {
        this.ensureInit();
        return this.getGson(verbose).toJson((Object)this);
    }

    public boolean isValid(boolean fix) {
        this.ensureInit();
        boolean valid = this.validateFields(fix);
        if (!this.isCurrent()) {
            if (fix) {
                this.configVersion = Journeymap.JM_VERSION;
                this.info(String.format("Setting config file to version \"%s\": %s", this.configVersion, this.getFileName()));
            } else {
                valid = false;
                this.info(String.format("Config file isn't current, has version \"%s\": %s", this.configVersion, this.getFileName()));
            }
        }
        this.currentState = valid ? State.Valid : State.Invalid;
        return valid;
    }

    protected ConfigField<?> getConfigField(String fieldName) {
        return this.getConfigFields().get(fieldName);
    }

    public Map<String, ConfigField<?>> getConfigFields() {
        if (this.configFields == null) {
            HashMap<String, ConfigField> map = new HashMap<String, ConfigField>();
            try {
                for (Field field : this.getClass().getFields()) {
                    Class<?> fieldType = field.getType();
                    if (!ConfigField.class.isAssignableFrom(fieldType)) continue;
                    ConfigField configField = (ConfigField)field.get(this);
                    if (configField != null) {
                        configField.setOwner(field.getName(), this);
                        Category category = configField.getCategory();
                        if (category != null) {
                            this.categories.add(category);
                        }
                    }
                    map.put(field.getName(), configField);
                }
            }
            catch (Throwable t) {
                this.error("Unexpected error getting fields: " + LogFormatter.toString(t));
            }
            this.configFields = Collections.unmodifiableMap(map);
        }
        return this.configFields;
    }

    public Category getCategoryByName(String name) {
        for (Category category : this.categories) {
            if (!category.getName().equalsIgnoreCase(name)) continue;
            return category;
        }
        return null;
    }

    protected boolean validateFields(boolean fix) {
        try {
            boolean valid = true;
            for (Map.Entry<String, ConfigField<?>> entry : this.getConfigFields().entrySet()) {
                ConfigField<?> configField = entry.getValue();
                if (configField == null) {
                    this.warn(String.format("%s.%s is null", this.getClass().getSimpleName(), entry.getKey()));
                    valid = false;
                    continue;
                }
                boolean fieldValid = configField.validate(fix);
                if (fieldValid) continue;
                valid = false;
            }
            return valid;
        }
        catch (Throwable t) {
            this.error("Unexpected error in validateFields: " + LogFormatter.toPartialString(t));
            return false;
        }
    }

    public List<ExclusionStrategy> getExclusionStrategies(boolean verbose) {
        ArrayList<ExclusionStrategy> strategies = new ArrayList<ExclusionStrategy>();
        if (!verbose) {
            strategies.add(new ExclusionStrategy(){

                public boolean shouldSkipField(FieldAttributes f) {
                    if (f.getDeclaringClass().equals(PropertiesBase.class)) {
                        return f.getName().equals("categories");
                    }
                    return false;
                }

                public boolean shouldSkipClass(Class<?> clazz) {
                    return false;
                }
            });
        }
        return strategies;
    }

    public long lastModified() {
        File file = this.getFile();
        if (file.canRead()) {
            return file.lastModified();
        }
        return 0L;
    }

    protected MoreObjects.ToStringHelper toStringHelper() {
        MoreObjects.ToStringHelper toStringHelper = MoreObjects.toStringHelper((Object)this).add("state", (Object)this.currentState).add("file", (Object)this.getFileName()).add("configVersion", (Object)this.configVersion);
        return toStringHelper;
    }

    public String toString() {
        MoreObjects.ToStringHelper toStringHelper = this.toStringHelper();
        for (Map.Entry<String, ConfigField<?>> entry : this.getConfigFields().entrySet()) {
            ConfigField<?> configField = entry.getValue();
            toStringHelper.add(entry.getKey(), configField.get());
        }
        return toStringHelper.toString();
    }

    public final boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PropertiesBase)) {
            return false;
        }
        PropertiesBase that = (PropertiesBase)o;
        return Objects.equal((Object)this.getFileName(), (Object)that.getFileName());
    }

    public final int hashCode() {
        return Objects.hashCode((Object[])new Object[]{this.getConfigFields()});
    }

    protected void info(String message) {
        Journeymap.getLogger().info(String.format("%s (%s) %s", new Object[]{this.getName(), this.currentState, message}));
    }

    protected void warn(String message) {
        Journeymap.getLogger().warn(String.format("%s (%s) %s", new Object[]{this.getName(), this.currentState, message}));
    }

    protected void error(String message) {
        Journeymap.getLogger().error(String.format("%s (%s) %s", new Object[]{this.getName(), this.currentState, message}));
    }

    protected void error(String message, Throwable throwable) {
        Journeymap.getLogger().error(String.format("%s (%s) %s : %s", new Object[]{this.getName(), this.currentState, message, LogFormatter.toString(throwable)}));
    }

    protected static enum State {
        New,
        Initialized,
        FirstLoaded,
        FileLoaded,
        Valid,
        Invalid,
        SavedOk,
        SavedError;

    }
}

