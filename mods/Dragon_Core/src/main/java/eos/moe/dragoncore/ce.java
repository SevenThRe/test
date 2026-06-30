/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.ModelLocator;
import eos.moe.dragoncore.bf;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class ce
implements Serializable {
    private static final long serialVersionUID = 6573645918001262565L;
    public String n;
    public int i;
    public int w;
    public List<bf> b;
    public Map<String, ModelLocator> locatorMap;

    public ce(String a2, int a3, int a4, List<bf> a5, Map<String, ModelLocator> a6) {
        ce a7;
        a7.n = a2;
        a7.i = a3;
        a7.w = a4;
        a7.b = a5;
        a7.locatorMap = a6;
    }
}

