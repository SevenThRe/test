/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Joiner
 *  org.apache.logging.log4j.util.Strings
 */
package journeymap.common.properties.config;

import com.google.common.base.Joiner;
import java.util.Arrays;
import java.util.List;
import journeymap.common.Journeymap;
import journeymap.common.log.LogFormatter;
import journeymap.common.properties.Category;
import journeymap.common.properties.config.ConfigField;
import org.apache.logging.log4j.util.Strings;

public class StringField
extends ConfigField<String> {
    public static final String ATTR_VALUE_PROVIDER = "valueProvider";
    public static final String ATTR_VALUE_PATTERN = "pattern";
    public static final String ATTR_MULTILINE = "multiline";

    protected StringField() {
    }

    public StringField(Category category, String key) {
        this(category, key, null, null);
    }

    public StringField(Category category, String key, String[] validValues, String defaultValue) {
        super(category, key);
        if (validValues != null) {
            this.put("validValues", Joiner.on((String)",").join((Object[])validValues));
        }
        if (!Strings.isEmpty((CharSequence)defaultValue)) {
            this.defaultValue(defaultValue);
            this.setToDefault();
        }
    }

    public StringField(Category category, String key, Class<? extends ValuesProvider> valueProviderClass) {
        super(category, key);
        if (valueProviderClass != null) {
            this.put(ATTR_VALUE_PROVIDER, valueProviderClass);
            try {
                ValuesProvider valuesProvider = valueProviderClass.newInstance();
                this.validValues(valuesProvider.getStrings());
                this.defaultValue(valuesProvider.getDefaultString());
                this.setToDefault();
                if (!this.getValidValues().contains(this.getDefaultValue())) {
                    Journeymap.getLogger().error(String.format("Default value '%s' isn't in one of the valid values '%s' for %s", this.getDefaultValue(), this.getStringAttr("validValues"), this));
                }
            }
            catch (Throwable t) {
                Journeymap.getLogger().error(String.format("Couldn't use ValuesProvider %s: %s", valueProviderClass, LogFormatter.toString(t)));
            }
        }
    }

    @Override
    public String getDefaultValue() {
        return this.getStringAttr("default");
    }

    @Override
    public String get() {
        return this.getStringAttr("value");
    }

    public StringField set(String value) {
        super.set(value);
        return this;
    }

    public StringField pattern(String regexPattern) {
        this.put(ATTR_VALUE_PATTERN, regexPattern);
        return this;
    }

    public String getPattern() {
        return this.getStringAttr(ATTR_VALUE_PATTERN);
    }

    public Class<? extends ValuesProvider> getValuesProviderClass() {
        Class<?> value = this.get(ATTR_VALUE_PROVIDER);
        if (value == null) {
            return null;
        }
        if (value instanceof Class) {
            return value;
        }
        if (value instanceof String) {
            try {
                value = Class.forName((String)((Object)value));
                this.put(ATTR_VALUE_PROVIDER, value);
                return value;
            }
            catch (Exception e) {
                Journeymap.getLogger().warn(String.format("Couldn't get ValuesProvider Class %s : %s", value, e.getMessage()));
            }
        }
        return null;
    }

    @Override
    public boolean validate(boolean fix) {
        List<String> validValues;
        boolean patternValid;
        String pattern;
        boolean hasRequired = this.require("type");
        boolean hasCategory = this.getCategory() != null;
        boolean valid = hasRequired && hasCategory;
        String value = this.get();
        if (Strings.isNotEmpty((CharSequence)value) && Strings.isNotEmpty((CharSequence)(pattern = this.getPattern())) && !(patternValid = value.matches(pattern))) {
            Journeymap.getLogger().warn(String.format("Value '%s' doesn't match pattern '%s' for %s", value, pattern, this));
            if (fix && Strings.isNotEmpty((CharSequence)this.getDefaultValue())) {
                this.setToDefault();
                Journeymap.getLogger().warn(String.format("Value set to default '%s' for %s", this.getDefaultValue(), this));
            } else {
                valid = false;
            }
        }
        if ((validValues = this.getValidValues()) != null && !validValues.contains(value)) {
            Journeymap.getLogger().warn(String.format("Value '%s' isn't in one of the valid values '%s' for %s", value, this.getStringAttr("validValues"), this));
            String defaultValue = this.getDefaultValue();
            if (fix && Strings.isNotEmpty((CharSequence)defaultValue)) {
                this.setToDefault();
                Journeymap.getLogger().warn(String.format("Value set to default '%s' for %s", defaultValue, this));
            } else {
                valid = false;
            }
        }
        return valid;
    }

    public List<String> getValidValues() {
        String validValuesString = this.getStringAttr("validValues");
        if (!Strings.isEmpty((CharSequence)validValuesString)) {
            return Arrays.asList(validValuesString.split(","));
        }
        return null;
    }

    public StringField validValues(Iterable<String> values) {
        this.put("validValues", Joiner.on((String)",").join(values));
        return this;
    }

    public boolean isMultiline() {
        Boolean val = this.getBooleanAttr(ATTR_MULTILINE);
        return val == null ? false : val;
    }

    public StringField multiline(boolean isMultiline) {
        this.put(ATTR_MULTILINE, isMultiline);
        return this;
    }

    public static interface ValuesProvider {
        public List<String> getStrings();

        public String getDefaultString();
    }
}

