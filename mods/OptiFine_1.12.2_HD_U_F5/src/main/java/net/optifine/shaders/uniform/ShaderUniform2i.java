/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.lwjgl.opengl.ARBShaderObjects
 */
package net.optifine.shaders.uniform;

import net.optifine.shaders.uniform.ShaderUniformBase;
import org.lwjgl.opengl.ARBShaderObjects;

public class ShaderUniform2i
extends ShaderUniformBase {
    private int[][] programValues;
    private static final int VALUE_UNKNOWN = Integer.MIN_VALUE;

    public ShaderUniform2i(String name) {
        super(name);
        this.resetValue();
    }

    public void setValue(int v0, int v1) {
        int program = this.getProgram();
        int[] valueOld = this.programValues[program];
        if (valueOld[0] == v0 && valueOld[1] == v1) {
            return;
        }
        valueOld[0] = v0;
        valueOld[1] = v1;
        int location = this.getLocation();
        if (location < 0) {
            return;
        }
        ARBShaderObjects.glUniform2iARB((int)location, (int)v0, (int)v1);
        this.checkGLError();
    }

    public int[] getValue() {
        int program = this.getProgram();
        int[] value = this.programValues[program];
        return value;
    }

    @Override
    protected void onProgramSet(int program) {
        if (program >= this.programValues.length) {
            int[][] valuesOld = this.programValues;
            int[][] valuesNew = new int[program + 10][];
            System.arraycopy(valuesOld, 0, valuesNew, 0, valuesOld.length);
            this.programValues = valuesNew;
        }
        if (this.programValues[program] == null) {
            this.programValues[program] = new int[]{Integer.MIN_VALUE, Integer.MIN_VALUE};
        }
    }

    @Override
    protected void resetValue() {
        this.programValues = new int[][]{{Integer.MIN_VALUE, Integer.MIN_VALUE}};
    }
}

