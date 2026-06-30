/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.ExclusionStrategy
 *  com.google.gson.FieldAttributes
 */
package journeymap.server.properties;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import java.io.File;
import java.util.List;
import journeymap.common.properties.Category;
import journeymap.common.properties.PropertiesBase;
import journeymap.server.Constants;
import journeymap.server.properties.ServerCategory;

public abstract class ServerPropertiesBase
extends PropertiesBase
implements Cloneable {
    protected final String displayName;
    protected final String description;

    protected ServerPropertiesBase(String displayName, String description) {
        this.displayName = displayName;
        this.description = description;
    }

    @Override
    public String[] getHeaders() {
        return new String[]{"// JourneyMap server configuration file. Modify at your own risk!", "// To restore the default settings, simply delete this file before starting Minecraft server", "// For more information, go to: http://journeymap.info/JourneyMapServer", "//", String.format("// %s : %s ", this.displayName, this.description)};
    }

    @Override
    public <T extends PropertiesBase> void updateFrom(T otherInstance) {
        super.updateFrom(otherInstance);
    }

    public <T extends PropertiesBase> T load(String jsonString, boolean verbose) {
        this.ensureInit();
        try {
            Object jsonInstance = this.fromJsonString(jsonString, this.getClass(), verbose);
            this.updateFrom((T)jsonInstance);
            this.postLoad(false);
            this.currentState = PropertiesBase.State.FileLoaded;
            if (!this.isValid(true)) {
                return null;
            }
            return (T)this;
        }
        catch (Exception e) {
            this.error(String.format("Can't load JSON string: %s", jsonString), e);
            return null;
        }
    }

    @Override
    public Category getCategoryByName(String name) {
        Category category = super.getCategoryByName(name);
        if (category == null) {
            category = ServerCategory.valueOf(name);
        }
        return category;
    }

    @Override
    public List<ExclusionStrategy> getExclusionStrategies(boolean verbose) {
        List<ExclusionStrategy> strategies = super.getExclusionStrategies(verbose);
        if (!verbose) {
            strategies.add(new ExclusionStrategy(){

                public boolean shouldSkipField(FieldAttributes f) {
                    if (f.getDeclaringClass().equals(ServerPropertiesBase.class)) {
                        return f.getName().equals("displayName") || f.getName().equals("description");
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

    @Override
    public boolean isValid(boolean fix) {
        boolean valid = super.isValid(fix);
        return valid;
    }

    @Override
    public String getFileName() {
        return String.format("journeymap.server.%s.config", this.getName());
    }

    @Override
    public File getFile() {
        if (this.sourceFile == null) {
            this.sourceFile = new File(Constants.SERVER_CONFIG_DIR, this.getFileName());
        }
        return this.sourceFile;
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

