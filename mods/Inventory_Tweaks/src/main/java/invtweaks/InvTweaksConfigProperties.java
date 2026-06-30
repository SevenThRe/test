/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package invtweaks;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Properties;
import org.jetbrains.annotations.NotNull;

public class InvTweaksConfigProperties
extends Properties {
    private static final long serialVersionUID = 1L;
    private final List<String> keys = new ArrayList<String>();

    @Override
    @NotNull
    public Enumeration<Object> keys() {
        return Collections.enumeration(new LinkedHashSet<String>(this.keys));
    }

    @Override
    public Object put(@NotNull String key, @NotNull Object value) {
        this.keys.add(key);
        return super.put(key, value);
    }

    public void sortKeys() {
        Collections.sort(this.keys);
    }
}

