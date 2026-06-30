/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.logging.log4j.Level
 */
package eos.moe.armourers;

import eos.moe.armourers.sg;
import org.apache.logging.log4j.Level;

public class bm {
    public static void r(Object a2) {
        sg.r().log(Level.INFO, String.valueOf(a2));
    }

    public bm() {
        bm a2;
    }

    public static void r(Level a2, Object a3) {
        sg.r().log(a2, String.valueOf(a3));
    }
}

