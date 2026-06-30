/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 */
package goblinbob.mobends.core.client.model;

import goblinbob.mobends.core.math.SmoothOrientation;
import goblinbob.mobends.core.math.matrix.IMat4x4d;
import goblinbob.mobends.core.math.vector.IVec3f;
import net.minecraft.entity.Entity;

public interface IModelPart {
    public void applyPreTransform(float var1);

    public void applyPreTransform(float var1, IMat4x4d var2);

    public void applyLocalTransform(float var1);

    public void applyLocalTransform(float var1, IMat4x4d var2);

    default public void applyCharacterTransform(float scale) {
        this.applyPreTransform(scale);
        if (this.getParent() != null) {
            this.getParent().applyCharacterTransform(scale * this.getOffsetScale());
        }
        this.applyLocalTransform(scale);
    }

    default public void applyCharacterTransform(float scale, IMat4x4d dest) {
        this.applyPreTransform(scale, dest);
        if (this.getParent() != null) {
            this.getParent().applyCharacterTransform(scale * this.getOffsetScale(), dest);
        }
        this.applyLocalTransform(scale, dest);
    }

    default public void propagateTransform(float scale) {
        this.applyLocalTransform(scale);
        this.applyPostTransform(scale);
    }

    public void applyPostTransform(float var1);

    public void renderPart(float var1);

    public void renderJustPart(float var1);

    public void update(float var1);

    public void syncUp(IModelPart var1);

    public void setVisible(boolean var1);

    public IVec3f getPosition();

    public IVec3f getScale();

    public IVec3f getOffset();

    public SmoothOrientation getRotation();

    public float getOffsetScale();

    public IVec3f getGlobalOffset();

    public IModelPart getParent();

    public boolean isShowing();

    default public void renderPartSkin(float scale) {
    }

    default public void renderJustPartSkin(float scale) {
    }

    default public void setName(String str) {
    }

    default public void setRenderEntity(Entity entity) {
    }
}

