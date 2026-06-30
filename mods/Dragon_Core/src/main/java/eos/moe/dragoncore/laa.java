/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.teamderpy.shouldersurfing.client.ShoulderInstance
 *  com.teamderpy.shouldersurfing.client.ShoulderRenderer
 */
package eos.moe.dragoncore;

import com.teamderpy.shouldersurfing.client.ShoulderInstance;
import com.teamderpy.shouldersurfing.client.ShoulderRenderer;

public class laa {
    public laa() {
        laa a2;
    }

    public static void ALLATORIxDEMO(float a2, float a3, float a4, float a5, float a6) {
        ShoulderRenderer.getInstance().offsetCamera(a2, a3, a4, a5, a6);
    }

    public static boolean ALLATORIxDEMO() {
        return ShoulderInstance.getInstance().doShoulderSurfing();
    }

    public static void ALLATORIxDEMO(boolean a2) {
        ShoulderInstance.getInstance().setShoulderSurfing(a2);
    }
}

