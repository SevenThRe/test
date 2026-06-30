/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.lwjgl.opengl.ARBShaderObjects
 */
package net.optifine.shaders.uniform;

import java.nio.FloatBuffer;
import net.optifine.shaders.uniform.ShaderUniformBase;
import org.lwjgl.opengl.ARBShaderObjects;

public class ShaderUniformM4
extends ShaderUniformBase {
    private boolean transpose;
    private FloatBuffer matrix;

    public ShaderUniformM4(String name) {
        super(name);
    }

    public void setValue(boolean transpose, FloatBuffer matrix) {
        this.transpose = transpose;
        this.matrix = matrix;
        int location = this.getLocation();
        if (location < 0) {
            return;
        }
        ARBShaderObjects.glUniformMatrix4ARB((int)location, (boolean)transpose, (FloatBuffer)matrix);
        this.checkGLError();
    }

    public float getValue(int row, int col) {
        if (this.matrix == null) {
            return 0.0f;
        }
        int index = this.transpose ? col * 4 + row : row * 4 + col;
        float value = this.matrix.get(index);
        return value;
    }

    @Override
    protected void onProgramSet(int program) {
    }

    @Override
    protected void resetValue() {
        this.matrix = null;
    }
}

