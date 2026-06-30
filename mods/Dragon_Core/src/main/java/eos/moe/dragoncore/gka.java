/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.teamderpy.shouldersurfing.config.Config
 *  com.teamderpy.shouldersurfing.util.ShoulderState
 *  com.teamderpy.shouldersurfing.util.ShoulderSurfingHelper
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.multiplayer.WorldClient
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.world.World
 */
package eos.moe.dragoncore;

import com.teamderpy.shouldersurfing.config.Config;
import com.teamderpy.shouldersurfing.util.ShoulderState;
import com.teamderpy.shouldersurfing.util.ShoulderSurfingHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class gka {
    public gka() {
        gka a2;
    }

    public static void ALLATORIxDEMO(float a2, float a3, float a4, float a5, float a6) {
        WorldClient a7 = Minecraft.func_71410_x().field_71441_e;
        if (ShoulderState.doShoulderSurfing() && a7 != null) {
            Vec3d a8 = new Vec3d(Config.CLIENT.getOffsetX(), -Config.CLIENT.getOffsetY(), -Config.CLIENT.getOffsetZ() + (double)a4);
            double a9 = ShoulderSurfingHelper.cameraDistance((World)a7, (double)a8.func_72433_c(), (float)a5, (float)a6);
            Vec3d a10 = a8.func_72432_b().func_186678_a(a9);
            ShoulderState.setCameraDistance((double)a9);
            GlStateManager.func_179137_b((double)a10.field_72450_a, (double)a10.field_72448_b, (double)a10.field_72449_c);
        } else {
            GlStateManager.func_179109_b((float)a2, (float)a3, (float)a4);
        }
    }

    public static boolean ALLATORIxDEMO() {
        return ShoulderState.doShoulderSurfing();
    }

    public static void ALLATORIxDEMO(boolean a2) {
        ShoulderState.setEnabled((boolean)a2);
    }
}

