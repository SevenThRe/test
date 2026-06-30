/*
 * Decompiled with CFR 0.152.
 */
package journeymap.common.properties.config;

import journeymap.common.properties.Category;
import journeymap.common.properties.config.ConfigField;

public class BooleanField
extends ConfigField<Boolean> {
    public static final String ATTR_CATEGORY_MASTER = "isMaster";

    protected BooleanField() {
    }

    public BooleanField(Category category, boolean defaultValue) {
        this(category, null, defaultValue);
    }

    public BooleanField(Category category, String key, boolean defaultValue) {
        this(category, key, defaultValue, false);
    }

    public BooleanField(Category category, String key, boolean defaultValue, boolean isMaster) {
        super(category, key);
        this.defaultValue(defaultValue);
        this.setToDefault();
        this.categoryMaster(isMaster);
    }

    @Override
    public Boolean getDefaultValue() {
        return this.getBooleanAttr("default");
    }

    public BooleanField set(Boolean value) {
        this.put("value", value);
        return this;
    }

    @Override
    public Boolean get() {
        return this.getBooleanAttr("value");
    }

    public boolean toggle() {
        this.set(this.get() == false);
        return this.get();
    }

    public boolean toggleAndSave() {
        this.set(this.get() == false);
        this.save();
        return this.get();
    }

    public boolean isCategoryMaster() {
        return this.getBooleanAttr(ATTR_CATEGORY_MASTER);
    }

    public BooleanField categoryMaster(boolean isMaster) {
        this.put(ATTR_CATEGORY_MASTER, isMaster);
        return this;
    }
}

