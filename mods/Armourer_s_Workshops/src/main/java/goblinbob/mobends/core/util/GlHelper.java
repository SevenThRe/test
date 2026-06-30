/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.GlStateManager
 *  org.lwjgl.BufferUtils
 *  org.lwjgl.opengl.GL11
 */
package goblinbob.mobends.core.util;

import goblinbob.mobends.core.math.Quaternion;
import goblinbob.mobends.core.math.QuaternionUtils;
import goblinbob.mobends.core.math.matrix.IMat4x4d;
import goblinbob.mobends.core.math.matrix.MatrixUtils;
import goblinbob.mobends.core.math.vector.IVec3dRead;
import goblinbob.mobends.core.math.vector.IVec3fRead;
import java.nio.FloatBuffer;
import net.minecraft.client.renderer.GlStateManager;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;

public class GlHelper {
    private static final FloatBuffer BUF_FLOAT_16 = BufferUtils.createFloatBuffer((int)16);

    public static void vertex(IVec3fRead vector) {
        GlStateManager.func_187435_e((float)vector.getX(), (float)vector.getY(), (float)vector.getZ());
    }

    public static void vertex(IVec3dRead vector) {
        GL11.glVertex3d((double)vector.getX(), (double)vector.getY(), (double)vector.getZ());
    }

    public static void rotate(Quaternion quaternionIn) {
        GlStateManager.func_179110_a((FloatBuffer)QuaternionUtils.quatToGlMatrix(BUF_FLOAT_16, quaternionIn));
    }

    public static void transform(IMat4x4d matrixIn) {
        GlStateManager.func_179110_a((FloatBuffer)MatrixUtils.matToGlMatrix(matrixIn, BUF_FLOAT_16));
    }
}

