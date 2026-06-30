/*
 * Decompiled with CFR 0.152.
 */
package journeymap.common.properties.config;

import journeymap.common.properties.Category;
import journeymap.common.properties.config.ConfigField;

public class IntegerField
extends ConfigField<Integer> {
    public static final String ATTR_MIN = "min";
    public static final String ATTR_MAX = "max";

    protected IntegerField() {
    }

    public IntegerField(Category category, String key, int minValue, int maxValue, int defaultValue) {
        this(category, key, minValue, maxValue, defaultValue, 100);
    }

    public IntegerField(Category category, String key, int minValue, int maxValue, int defaultValue, int sortOrder) {
        super(category, key);
        this.range(minValue, maxValue);
        this.defaultValue(defaultValue);
        this.setToDefault();
        this.sortOrder(sortOrder);
    }

    @Override
    public Integer getDefaultValue() {
        return this.getIntegerAttr("default");
    }

    @Override
    public Integer get() {
        return this.getIntegerAttr("value");
    }

    @Override
    public boolean validate(boolean fix) {
        boolean valid = super.validate(fix);
        valid = this.require(ATTR_MIN, ATTR_MAX) && valid;
        Integer value = this.get();
        if (value == null || value < this.getMinValue() || value > this.getMaxValue()) {
            if (fix) {
                this.setToDefault();
            } else {
                valid = false;
            }
        }
        return valid;
    }

    public IntegerField range(int min, int max) {
        this.put(ATTR_MIN, min);
        this.put(ATTR_MAX, max);
        return this;
    }

    public int getMinValue() {
        return this.getIntegerAttr(ATTR_MIN);
    }

    public int getMaxValue() {
        return this.getIntegerAttr(ATTR_MAX);
    }

    public Integer incrementAndGet() {
        Integer value = Math.min(this.getMaxValue(), this.get() + 1);
        this.set(value);
        return value;
    }

    public Integer decrementAndGet() {
        Integer value = Math.max(this.getMinValue(), this.get() - 1);
        this.set(value);
        return value;
    }
}

