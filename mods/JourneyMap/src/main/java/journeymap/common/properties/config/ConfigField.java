/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.MoreObjects
 *  com.google.common.base.Objects
 */
package journeymap.common.properties.config;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import journeymap.common.Journeymap;
import journeymap.common.log.LogFormatter;
import journeymap.common.properties.Category;
import journeymap.common.properties.PropertiesBase;

public abstract class ConfigField<T> {
    public static final String ATTR_TYPE = "type";
    public static final String ATTR_CATEGORY = "category";
    public static final String ATTR_KEY = "key";
    public static final String ATTR_LABEL = "label";
    public static final String ATTR_TOOLTIP = "tooltip";
    public static final String ATTR_ORDER = "order";
    public static final String ATTR_VALUE = "value";
    public static final String ATTR_DEFAULT = "default";
    public static final String ATTR_VALID_VALUES = "validValues";
    protected final transient Map<String, Object> attributes = new TreeMap<String, Object>();
    protected transient PropertiesBase owner;
    protected transient String fieldName;

    public ConfigField() {
        this.put(ATTR_TYPE, this.getClass().getSimpleName());
    }

    protected ConfigField(Category category) {
        this.put(ATTR_TYPE, this.getClass().getSimpleName());
        this.put(ATTR_CATEGORY, category);
    }

    protected ConfigField(Category category, String key) {
        this.put(ATTR_TYPE, this.getClass().getSimpleName());
        this.put(ATTR_CATEGORY, category);
        this.put(ATTR_KEY, key);
    }

    public String getStringAttr(String attrName) {
        Object value = this.attributes.get(attrName);
        if (value == null) {
            return null;
        }
        if (value instanceof Enum) {
            return ((Enum)value).name();
        }
        if (value instanceof Class) {
            return ((Class)value).getCanonicalName();
        }
        return value.toString();
    }

    public ConfigField<T> put(String attrName, Object value) {
        this.attributes.put(attrName, value);
        return this;
    }

    public abstract T getDefaultValue();

    public abstract T get();

    public ConfigField<T> set(T value) {
        this.put(ATTR_VALUE, value);
        return this;
    }

    public boolean validate(boolean fix) {
        boolean hasRequired = this.require(ATTR_TYPE, ATTR_VALUE, ATTR_DEFAULT);
        boolean hasCategory = this.getCategory() != null;
        return hasRequired && hasCategory;
    }

    public ConfigField<T> sortOrder(int order) {
        this.put(ATTR_ORDER, order);
        return this;
    }

    public String getKey() {
        return this.getStringAttr(ATTR_KEY);
    }

    public ConfigField<T> category(Category category) {
        this.attributes.put(ATTR_CATEGORY, category);
        return this;
    }

    public Category getCategory() {
        Object val = this.get(ATTR_CATEGORY);
        if (val instanceof Category) {
            return (Category)val;
        }
        if (val instanceof String && this.owner != null) {
            Category category = this.owner.getCategoryByName((String)val);
            this.category(category);
            return category;
        }
        return null;
    }

    public String getLabel() {
        return this.getStringAttr(ATTR_LABEL);
    }

    public ConfigField<T> label(String label) {
        this.attributes.put(ATTR_LABEL, label);
        return this;
    }

    public String getTooltip() {
        return this.getStringAttr(ATTR_TOOLTIP);
    }

    public String getType() {
        return this.getStringAttr(ATTR_TYPE);
    }

    public int getSortOrder() {
        Integer order = this.getIntegerAttr(ATTR_ORDER);
        if (order == null) {
            order = 100;
        }
        return order;
    }

    public Object get(String attrName) {
        return this.attributes.get(attrName);
    }

    public Integer getIntegerAttr(String attrName) {
        Object value = this.attributes.get(attrName);
        if (value instanceof Integer) {
            return (Integer)value;
        }
        if (value instanceof String) {
            try {
                value = Integer.parseInt((String)value);
                this.attributes.put(attrName, value);
                return (Integer)value;
            }
            catch (NumberFormatException e) {
                Journeymap.getLogger().warn(String.format("Couldn't get Integer %s from %s: %s", attrName, value, e.getMessage()));
            }
        }
        return null;
    }

    public Boolean getBooleanAttr(String attrName) {
        Object value = this.attributes.get(attrName);
        if (value instanceof Boolean) {
            return (Boolean)value;
        }
        if (value instanceof String) {
            try {
                value = Boolean.valueOf((String)value);
                this.attributes.put(attrName, value);
                return (Boolean)value;
            }
            catch (NumberFormatException e) {
                Journeymap.getLogger().warn(String.format("Couldn't get Boolean %s from %s: %s", attrName, value, e.getMessage()));
            }
        }
        return null;
    }

    public <E extends Enum> E getEnumAttr(String attrName, Class<E> enumType) {
        Object value = this.attributes.get(attrName);
        if (value instanceof Enum) {
            return (E)((Enum)value);
        }
        if (value instanceof String) {
            try {
                return Enum.valueOf(enumType, (String)value);
            }
            catch (Exception e) {
                Journeymap.getLogger().warn(String.format("Couldn't get %s as Enum %s with value %s: %s", attrName, enumType, value, LogFormatter.toString(e)));
            }
        }
        this.setToDefault();
        return (E)((Enum)this.getDefaultValue());
    }

    public void setToDefault() {
        this.set(this.getDefaultValue());
    }

    public ConfigField<T> defaultValue(T defaultValue) {
        if (defaultValue == null) {
            Journeymap.getLogger().warn("defaultValue shouldn't be null");
        }
        this.put(ATTR_DEFAULT, defaultValue);
        return this;
    }

    protected boolean require(String ... attrNames) {
        boolean pass = true;
        for (String attrName : attrNames) {
            Object attr = this.get(attrName);
            if (attr != null) continue;
            Journeymap.getLogger().warn(String.format("Missing required attribute '%s' in %s", attrName, this.getDeclaredField()));
            pass = false;
        }
        return pass;
    }

    public Map<String, Object> getAttributeMap() {
        return this.attributes;
    }

    public Set<String> getAttributeNames() {
        return this.attributes.keySet();
    }

    public PropertiesBase getOwner() {
        return this.owner;
    }

    public void setOwner(String fieldName, PropertiesBase properties) {
        this.fieldName = fieldName;
        this.owner = properties;
    }

    public boolean save() {
        if (this.owner != null) {
            return this.owner.save();
        }
        return false;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ConfigField)) {
            return false;
        }
        ConfigField that = (ConfigField)o;
        return Objects.equal((Object)this.getKey(), (Object)that.getKey()) && this.getCategory() == that.getCategory() && Objects.equal(this.get(), that.get());
    }

    public int hashCode() {
        return Objects.hashCode((Object[])new Object[]{this.getKey(), this.getCategory(), this.get()});
    }

    public String getDeclaredField() {
        if (this.owner == null) {
            return null;
        }
        return String.format("%s.%s", this.owner.getClass().getSimpleName(), this.fieldName);
    }

    public String toString() {
        return MoreObjects.toStringHelper((Object)this).add("on", (Object)this.getDeclaredField()).add("attributes", this.attributes).toString();
    }
}

