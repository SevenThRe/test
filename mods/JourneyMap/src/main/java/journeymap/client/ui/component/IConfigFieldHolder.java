/*
 * Decompiled with CFR 0.152.
 */
package journeymap.client.ui.component;

import journeymap.common.properties.config.ConfigField;

public interface IConfigFieldHolder<T extends ConfigField> {
    public T getConfigField();
}

