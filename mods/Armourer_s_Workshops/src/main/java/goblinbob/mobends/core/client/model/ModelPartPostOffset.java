/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.renderer.GlStateManager
 *  org.lwjgl.util.vector.Vector3f
 */
package goblinbob.mobends.core.client.model;

import goblinbob.mobends.core.client.model.ModelPart;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import org.lwjgl.util.vector.Vector3f;

public class ModelPartPostOffset
extends ModelPart {
    protected Vector3f postOffset = new Vector3f(0.0f, 0.0f, 0.0f);

    public ModelPartPostOffset(ModelBase model, boolean register, int texOffsetX, int texOffsetY) {
        super(model, register, texOffsetY, texOffsetY);
    }

    public ModelPartPostOffset(ModelBase model, boolean register) {
        super(model, register);
    }

    public ModelPartPostOffset(ModelBase model, int texOffsetX, int texOffsetY) {
        super(model, texOffsetX, texOffsetY);
    }

    public ModelPartPostOffset setPostOffset(float x2, float y2, float z2) {
        this.postOffset.set(x2, y2, z2);
        return this;
    }

    @Override
    public void propagateTransform(float scale) {
        super.propagateTransform(scale);
    }

    @Override
    public void applyPostTransform(float scale) {
        GlStateManager.translate((float)(this.postOffset.x * scale), (float)(this.postOffset.y * scale), (float)(this.postOffset.z * scale));
    }
}

