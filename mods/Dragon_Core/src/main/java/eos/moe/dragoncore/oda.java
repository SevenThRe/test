/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.vecmath.Vector2f
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.ScaledResolution
 *  net.minecraft.client.renderer.Matrix4f
 *  net.minecraft.client.renderer.culling.ClippingHelperImpl
 *  net.minecraft.util.math.Vec3d
 *  org.lwjgl.util.vector.Matrix4f
 *  org.lwjgl.util.vector.Vector3f
 *  org.lwjgl.util.vector.Vector4f
 */
package eos.moe.dragoncore;

import javax.vecmath.Vector2f;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.Matrix4f;
import net.minecraft.client.renderer.culling.ClippingHelperImpl;
import net.minecraft.util.math.Vec3d;
import org.lwjgl.util.vector.Vector3f;
import org.lwjgl.util.vector.Vector4f;

public class oda {
    public oda() {
        oda a2;
    }

    public static Vector2f ALLATORIxDEMO(Vec3d a2) {
        Matrix4f a3 = new Matrix4f(ClippingHelperImpl.getInstance().clippingMatrix);
        Vector4f a4 = new Vector4f((float)a2.x, (float)a2.y, (float)a2.z, 1.0f);
        Vector4f a5 = Matrix4f.transform((org.lwjgl.util.vector.Matrix4f)a3, (Vector4f)a4, null);
        if (a5.w == 0.0f) {
            return null;
        }
        Vector3f a6 = new Vector3f();
        a6.x = a5.x / a5.w;
        a6.y = a5.y / a5.w;
        a6.z = a5.z / a5.w;
        Vector2f a7 = new Vector2f();
        ScaledResolution a8 = new ScaledResolution(Minecraft.getMinecraft());
        a7.x = (float)oda.c(a6.x, a8.getScaledWidth_double());
        a7.y = (float)oda.ALLATORIxDEMO(a6.y, a8.getScaledHeight_double());
        return a7;
    }

    public static double c(double a2, double a3) {
        if (a2 < 0.0) {
            return a3 * (1.0 - -a2);
        }
        if (a2 > 0.0) {
            return a3 * a2;
        }
        return a3 * 0.5;
    }

    public static double ALLATORIxDEMO(double a2, double a3) {
        if (a2 < 0.0) {
            return a3 * -a2;
        }
        if (a2 > 0.0) {
            return a3 * (1.0 - a2);
        }
        return a3 * 0.5;
    }
}

