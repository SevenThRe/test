/*
 * Decompiled with CFR 0.152.
 */
package journeymap.common.properties.config;

import java.util.EnumSet;
import java.util.Set;
import journeymap.common.Journeymap;
import journeymap.common.properties.Category;
import journeymap.common.properties.config.ConfigField;

public class EnumField<E extends Enum>
extends ConfigField<E> {
    public static final String ATTR_ENUM_TYPE = "enumType";

    protected EnumField() {
    }

    public EnumField(Category category, String key, E defaultValue) {
        super(category, key);
        this.put(ATTR_ENUM_TYPE, defaultValue.getClass().getName());
        this.defaultValue(defaultValue);
        this.setToDefault();
    }

    @Override
    public E getDefaultValue() {
        return this.getEnumAttr("default", this.getEnumClass());
    }

    @Override
    public EnumField<E> set(E value) {
        this.put("value", ((Enum)value).name());
        return this;
    }

    @Override
    public E get() {
        return this.getEnumAttr("value", this.getEnumClass());
    }

    public Class<E> getEnumClass() {
        Class<?> value = this.get(ATTR_ENUM_TYPE);
        if (value instanceof Class) {
            return value;
        }
        if (value instanceof String) {
            try {
                value = Class.forName((String)((Object)value));
                this.attributes.put(ATTR_ENUM_TYPE, value);
                return value;
            }
            catch (Exception e) {
                Journeymap.getLogger().warn(String.format("Couldn't get Enum Class %s : %s", ATTR_ENUM_TYPE, e.getMessage()));
            }
        }
        return null;
    }

    public Set<E> getValidValues() {
        Class<E> enumClass = this.getEnumClass();
        return EnumSet.allOf(enumClass);
    }

    @Override
    public boolean validate(boolean fix) {
        return this.require(ATTR_ENUM_TYPE) && super.validate(fix);
    }
}

