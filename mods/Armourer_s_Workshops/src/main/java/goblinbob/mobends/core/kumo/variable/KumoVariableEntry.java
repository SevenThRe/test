/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.resources.I18n
 */
package goblinbob.mobends.core.kumo.variable;

import goblinbob.mobends.core.kumo.variable.IKumoVariable;
import net.minecraft.client.resources.I18n;

public class KumoVariableEntry {
    private IKumoVariable variable;
    private String key;

    public KumoVariableEntry(IKumoVariable variable, String key) {
        this.variable = variable;
        this.key = key;
    }

    public String getLocalizedName() {
        return I18n.func_135052_a((String)String.format("mobends.variable.%s", this.key), (Object[])new Object[0]);
    }
}

