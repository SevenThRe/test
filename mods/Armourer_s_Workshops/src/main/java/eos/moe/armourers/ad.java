/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.resources.I18n
 */
package eos.moe.armourers;

import net.minecraft.client.resources.I18n;

public class ad {
    public ad() {
        ad a2;
    }

    public static String r(String a2) {
        return I18n.func_135052_a((String)a2, (Object[])new Object[0]).replace("&", "\u00a7");
    }

    public static String r(String a2, Object ... a3) {
        return I18n.func_135052_a((String)a2, (Object[])a3).replace("&", "\u00a7");
    }
}

