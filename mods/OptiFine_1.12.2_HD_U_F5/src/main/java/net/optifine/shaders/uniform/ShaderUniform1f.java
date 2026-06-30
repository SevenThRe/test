/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.lwjgl.opengl.ARBShaderObjects
 */
package net.optifine.shaders.uniform;

import net.optifine.shaders.uniform.ShaderUniformBase;
import org.lwjgl.opengl.ARBShaderObjects;

public class ShaderUniform1f
extends ShaderUniformBase {
    private float[] programValues;
    private static final float VALUE_UNKNOWN = -3.4028235E38f;

    public ShaderUniform1f(String name) {
        super(name);
        this.resetValue();
    }

    public void setValue(float valueNew) {
        int program = this.getProgram();
        float valueOld = this.programValues[program];
        if (valueNew == valueOld) {
            return;
        }
        this.programValues[program] = valueNew;
        int location = this.getLocation();
        if (location < 0) {
            return;
        }
        ARBShaderObjects.glUniform1fARB((int)location, (float)valueNew);
        this.checkGLError();
    }

    public float getValue() {
        int program = this.getProgram();
        float value = this.programValues[program];
        return value;
    }

    @Override
    protected void onProgramSet(int program) {
        if (program >= this.programValues.length) {
            float[] valuesOld = this.programValues;
            float[] valuesNew = new float[program + 10];
            System.arraycopy(valuesOld, 0, valuesNew, 0, valuesOld.length);
            for (int i = valuesOld.length; i < valuesNew.length; ++i) {
                valuesNew[i] = -3.4028235E38f;
            }
            this.programValues = valuesNew;
        }
    }

    @Override
    protected void resetValue() {
        this.programValues = new float[]{-3.4028235E38f};
    }
}

