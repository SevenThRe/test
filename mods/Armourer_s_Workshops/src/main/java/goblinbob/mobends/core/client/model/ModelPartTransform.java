/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  eos.moe.dragoncore.api.model.AnimationModelRenderer
 *  eos.moe.dragoncore.bax
 *  net.minecraft.client.renderer.GlStateManager
 */
package goblinbob.mobends.core.client.model;

import eos.moe.dragoncore.api.model.AnimationModelRenderer;
import eos.moe.dragoncore.bax;
import goblinbob.mobends.core.client.model.IModelPart;
import goblinbob.mobends.core.math.SmoothOrientation;
import goblinbob.mobends.core.math.TransformUtils;
import goblinbob.mobends.core.math.matrix.IMat4x4d;
import goblinbob.mobends.core.math.vector.IVec3f;
import goblinbob.mobends.core.math.vector.Vec3f;
import goblinbob.mobends.core.util.GlHelper;
import net.minecraft.client.renderer.GlStateManager;

public class ModelPartTransform
implements IModelPart,
AnimationModelRenderer {
    private boolean applyAnimation;
    private float rotateAngleXX;
    private float rotateAngleYY;
    private float rotateAngleZZ;
    public Vec3f position;
    public Vec3f scale;
    public Vec3f offset;
    public SmoothOrientation rotation;
    public float offsetScale = 1.0f;
    public Vec3f globalOffset = new Vec3f();
    public final ModelPartTransform parent;

    public ModelPartTransform(ModelPartTransform parent) {
        this.position = new Vec3f();
        this.scale = new Vec3f(1.0f, 1.0f, 1.0f);
        this.offset = new Vec3f();
        this.rotation = new SmoothOrientation();
        this.parent = parent;
    }

    public ModelPartTransform() {
        this(null);
    }

    @Override
    public void renderPart(float scale) {
    }

    @Override
    public void renderJustPart(float scale) {
    }

    @Override
    public void update(float ticksPerFrame) {
        this.rotation.update(ticksPerFrame);
    }

    @Override
    public IVec3f getPosition() {
        return this.position;
    }

    @Override
    public IVec3f getScale() {
        return this.scale;
    }

    @Override
    public IVec3f getOffset() {
        return this.offset;
    }

    @Override
    public SmoothOrientation getRotation() {
        return this.rotation;
    }

    @Override
    public float getOffsetScale() {
        return this.offsetScale;
    }

    @Override
    public IVec3f getGlobalOffset() {
        return this.globalOffset;
    }

    @Override
    public boolean isShowing() {
        return true;
    }

    @Override
    public void applyPreTransform(float scale) {
        if (this.globalOffset.x != 0.0f || this.globalOffset.y != 0.0f || this.globalOffset.z != 0.0f) {
            GlStateManager.func_179109_b((float)(this.globalOffset.x * scale), (float)(this.globalOffset.y * scale), (float)(this.globalOffset.z * scale));
        }
    }

    @Override
    public void applyPreTransform(float scale, IMat4x4d dest) {
        if (this.globalOffset.x != 0.0f || this.globalOffset.y != 0.0f || this.globalOffset.z != 0.0f) {
            TransformUtils.translate(dest, this.globalOffset.x * scale, this.globalOffset.y * scale, this.globalOffset.z * scale);
        }
    }

    @Override
    public void propagateTransform(float scale) {
        this.applyLocalTransform(scale);
    }

    @Override
    public void applyPostTransform(float scale) {
    }

    @Override
    public void setVisible(boolean showModel) {
    }

    @Override
    public void applyLocalTransform(float scale, IMat4x4d matrix) {
        if (this.position.x != 0.0f || this.position.y != 0.0f || this.position.z != 0.0f) {
            TransformUtils.translate(matrix, this.position.x * scale, this.position.y * scale, this.position.z * scale);
        }
        if (this.offset.x != 0.0f || this.offset.y != 0.0f || this.offset.z != 0.0f) {
            TransformUtils.translate(matrix, this.offset.x * scale * this.offsetScale, this.offset.y * scale * this.offsetScale, this.offset.z * scale * this.offsetScale);
        }
        TransformUtils.rotate(matrix, this.rotation.getSmooth());
    }

    @Override
    public IModelPart getParent() {
        return this.parent;
    }

    @Override
    public void applyLocalTransform(float scale) {
        if (this.position.x != 0.0f || this.position.y != 0.0f || this.position.z != 0.0f) {
            GlStateManager.func_179109_b((float)(this.position.x * scale * this.offsetScale), (float)(this.position.y * scale * this.offsetScale), (float)(this.position.z * scale * this.offsetScale));
        }
        if (this.offset.x != 0.0f || this.offset.y != 0.0f || this.offset.z != 0.0f) {
            GlStateManager.func_179109_b((float)(this.offset.x * scale * this.offsetScale), (float)(this.offset.y * scale * this.offsetScale), (float)(this.offset.z * scale * this.offsetScale));
        }
        if (this.applyAnimation) {
            if (this.rotateAngleZZ != 0.0f) {
                GlStateManager.func_179114_b((float)this.rotateAngleZZ, (float)0.0f, (float)0.0f, (float)1.0f);
            }
            if (this.rotateAngleYY != 0.0f) {
                GlStateManager.func_179114_b((float)this.rotateAngleYY, (float)0.0f, (float)1.0f, (float)0.0f);
            }
            if (this.rotateAngleXX != 0.0f) {
                GlStateManager.func_179114_b((float)this.rotateAngleXX, (float)1.0f, (float)0.0f, (float)0.0f);
            }
        } else {
            GlHelper.rotate(this.rotation.getSmooth());
        }
        if (this.scale.x != 0.0f || this.scale.y != 0.0f || this.scale.z != 0.0f) {
            GlStateManager.func_179152_a((float)this.scale.x, (float)this.scale.y, (float)this.scale.z);
        }
    }

    @Override
    public void syncUp(IModelPart part) {
        if (part == null) {
            return;
        }
        this.position.set(part.getPosition());
        this.rotation.set(part.getRotation());
        this.offset.set(part.getOffset());
        this.scale.set(part.getScale());
        this.offsetScale = part.getOffsetScale();
        AnimationModelRenderer part1 = (AnimationModelRenderer)part;
        this.applyAnimation = part1.isApplyAnimation();
        bax rotateAngle = part1.getRotateAngle();
        this.setRotateAngle(rotateAngle.x, rotateAngle.y, rotateAngle.z);
    }

    public void setRotateAngle(float x2, float y2, float z2) {
        this.rotateAngleXX = x2;
        this.rotateAngleYY = y2;
        this.rotateAngleZZ = z2;
    }

    public void setOffsets(float x2, float y2, float z2) {
        this.offset.set(x2, y2, z2);
    }

    public bax getRotateAngle() {
        return new bax(this.rotateAngleXX, this.rotateAngleYY, this.rotateAngleZZ);
    }

    public bax getOffsets() {
        return new bax(this.offset.x, this.offset.y, this.offset.z);
    }

    public bax getScaleFactor() {
        return new bax(this.scale.x, this.scale.y, this.scale.z);
    }

    public bax getStartRotationAngles() {
        return new bax(0.0f, 0.0f, 0.0f);
    }

    public void setScaleFactor(float scaleX, float scaleY, float scaleZ) {
        this.scale.set(scaleX, scaleY, scaleZ);
    }

    public void setApplyAnimation(boolean applyAnimation) {
        this.applyAnimation = applyAnimation;
    }

    public boolean isApplyAnimation() {
        return this.applyAnimation;
    }

    public void clear() {
        if (this.applyAnimation) {
            this.rotation.identity();
            this.offset.set(0.0f, 0.0f, 0.0f);
            this.scale.set(0.0f, 0.0f, 0.0f);
            this.rotateAngleXX = 0.0f;
            this.rotateAngleYY = 0.0f;
            this.rotateAngleZZ = 0.0f;
        }
        this.applyAnimation = false;
    }
}

