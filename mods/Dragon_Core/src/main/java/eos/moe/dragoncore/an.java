/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  mezz.jei.config.Config
 *  mezz.jei.config.ConfigValues
 */
package eos.moe.dragoncore;

import java.lang.reflect.Field;
import mezz.jei.config.Config;
import mezz.jei.config.ConfigValues;

public class an {
    private static boolean ALLATORIxDEMO;

    public an() {
        an a2;
    }

    public static void c() {
        if (ALLATORIxDEMO) {
            return;
        }
        try {
            if (Config.isOverlayEnabled()) {
                Field a2 = Config.class.getDeclaredField("values");
                a2.setAccessible(true);
                ConfigValues a3 = (ConfigValues)a2.get(null);
                a3.overlayEnabled = false;
                ALLATORIxDEMO = true;
            }
        }
        catch (Throwable throwable) {
            // empty catch block
        }
    }

    public static void ALLATORIxDEMO() {
        try {
            if (ALLATORIxDEMO) {
                ALLATORIxDEMO = false;
                Field a2 = Config.class.getDeclaredField("values");
                a2.setAccessible(true);
                ConfigValues a3 = (ConfigValues)a2.get(null);
                a3.overlayEnabled = true;
            }
        }
        catch (Throwable throwable) {
            // empty catch block
        }
    }
}

