/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.r;
import eos.moe.dragoncore.ui;
import org.yaml.snakeyamla.configuration.ConfigurationSection;

public interface p
extends r {
    public void setValue(String var1, Object var2);

    public Object getValue(String var1);

    public ui getManager();

    public ConfigurationSection getSection();
}

