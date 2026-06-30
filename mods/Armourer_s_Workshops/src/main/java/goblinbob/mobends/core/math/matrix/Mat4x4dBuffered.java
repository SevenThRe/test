/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.lwjgl.BufferUtils
 */
package goblinbob.mobends.core.math.matrix;

import goblinbob.mobends.core.math.matrix.Mat4x4d;
import goblinbob.mobends.core.math.matrix.MatrixUtils;
import java.nio.FloatBuffer;
import org.lwjgl.BufferUtils;

public class Mat4x4dBuffered
extends Mat4x4d {
    private final FloatBuffer buffer = BufferUtils.createFloatBuffer((int)16);

    public void updateBuffer() {
        MatrixUtils.matToGlMatrix(this, this.buffer);
    }

    public FloatBuffer getBuffer() {
        return this.buffer;
    }
}

