/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.armourers;

import eos.moe.armourers.on;
import eos.moe.armourers.th;
import java.util.UUID;

public class SkinRenderHelper {
    public static boolean isArmHidden(UUID a2, boolean a3) {
        on on2 = on.r(a2);
        if (a3) {
            return on2.g;
        }
        return on2.e;
    }

    public SkinRenderHelper() {
        SkinRenderHelper a2;
    }

    public static void renderSkin(String a2, float a3, float a4, float a5, float a6) {
        th.r(a3, a4, a5, a6, a2);
    }
}

