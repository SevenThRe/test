/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  eos.moe.dragoncore.api.model.AnimationModelRenderer
 *  eos.moe.dragoncore.bax
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package goblinbob.mobends.standard.client.model.armor;

import eos.moe.dragoncore.api.model.AnimationModelRenderer;
import eos.moe.dragoncore.bax;
import goblinbob.mobends.core.client.model.IModelPart;
import goblinbob.mobends.core.math.SmoothOrientation;
import goblinbob.mobends.core.math.TransformUtils;
import goblinbob.mobends.core.math.matrix.IMat4x4d;
import goblinbob.mobends.core.math.physics.ICollider;
import goblinbob.mobends.core.math.vector.IVec3f;
import goblinbob.mobends.core.math.vector.Vec3f;
import goblinbob.mobends.core.util.GlHelper;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class PartContainer
extends ModelRenderer
implements IModelPart,
AnimationModelRenderer {
    private boolean applyAnimation;
    private float rotateAngleXX;
    private float rotateAngleYY;
    private float rotateAngleZZ;
    public Vec3f position;
    public Vec3f offset;
    public Vec3f innerOffset;
    public Vec3f scale;
    public SmoothOrientation rotation;
    public float offsetScale = 1.0f;
    public Vec3f globalOffset = new Vec3f();
    private final ModelRenderer innerModel;
    protected IModelPart parent;
    protected ICollider collider;

    public PartContainer(ModelBase modelBase, ModelRenderer model) {
        super(modelBase, 0, 0);
        this.innerModel = model;
        this.position = new Vec3f();
        this.offset = new Vec3f();
        this.innerOffset = new Vec3f();
        this.scale = new Vec3f(1.0f, 1.0f, 1.0f);
        this.rotation = new SmoothOrientation();
        this.field_78809_i = model.field_78809_i;
    }

    public ModelRenderer getModel() {
        return this.innerModel;
    }

    @Override
    public IModelPart getParent() {
        return this.parent;
    }

    public PartContainer setParent(IModelPart parent) {
        this.parent = parent;
        return this;
    }

    public PartContainer setPosition(float x2, float y2, float z2) {
        this.position.set(x2, y2, z2);
        return this;
    }

    public PartContainer setInnerOffset(float x2, float y2, float z2) {
        this.innerOffset.set(x2, y2, z2);
        return this;
    }

    private void renderContainedModel(float scale) {
        float x2 = this.innerModel.field_78800_c;
        float y2 = this.innerModel.field_78797_d;
        float z2 = this.innerModel.field_78798_e;
        float ox = this.innerModel.field_82906_o;
        float oy = this.innerModel.field_82908_p;
        float oz = this.innerModel.field_82907_q;
        this.innerModel.field_78808_h = 0.0f;
        this.innerModel.field_78796_g = 0.0f;
        this.innerModel.field_78795_f = 0.0f;
        this.innerModel.field_78798_e = 0.0f;
        this.innerModel.field_78797_d = 0.0f;
        this.innerModel.field_78800_c = 0.0f;
        this.innerModel.field_82907_q = 0.0f;
        this.innerModel.field_82908_p = 0.0f;
        this.innerModel.field_82906_o = 0.0f;
        this.innerModel.field_78806_j = true;
        this.innerModel.field_78807_k = false;
        this.innerModel.func_78785_a(scale);
        this.innerModel.field_78800_c = x2;
        this.innerModel.field_78797_d = y2;
        this.innerModel.field_78798_e = z2;
        this.innerModel.field_82906_o = ox;
        this.innerModel.field_82908_p = oy;
        this.innerModel.field_82907_q = oz;
    }

    @SideOnly(value=Side.CLIENT)
    public void func_78785_a(float scale) {
        this.renderPart(scale);
    }

    @Override
    public void renderPart(float scale) {
        if (!this.isShowing()) {
            return;
        }
        GlStateManager.func_179094_E();
        this.applyCharacterTransform(scale);
        if (this.innerOffset.x != 0.0f || this.innerOffset.y != 0.0f || this.innerOffset.z != 0.0f) {
            GlStateManager.func_179109_b((float)(this.innerOffset.x * scale), (float)(this.innerOffset.y * scale), (float)(this.innerOffset.z * scale));
        }
        this.renderContainedModel(scale);
        if (this.field_78805_m != null) {
            for (int k2 = 0; k2 < this.field_78805_m.size(); ++k2) {
                ((ModelRenderer)this.field_78805_m.get(k2)).func_78785_a(scale);
            }
        }
        GlStateManager.func_179121_F();
    }

    @Override
    public void renderJustPart(float scale) {
        if (!this.isShowing()) {
            return;
        }
        GlStateManager.func_179094_E();
        this.applyLocalTransform(scale);
        if (this.innerOffset.x != 0.0f || this.innerOffset.y != 0.0f || this.innerOffset.z != 0.0f) {
            GlStateManager.func_179109_b((float)(this.innerOffset.x * scale), (float)(this.innerOffset.y * scale), (float)(this.innerOffset.z * scale));
        }
        this.renderContainedModel(scale);
        if (this.field_78805_m != null) {
            for (int k2 = 0; k2 < this.field_78805_m.size(); ++k2) {
                ((ModelRenderer)this.field_78805_m.get(k2)).func_78785_a(scale);
            }
        }
        GlStateManager.func_179121_F();
    }

    @SideOnly(value=Side.CLIENT)
    public void func_78794_c(float scale) {
        this.applyCharacterTransform(scale);
        this.applyPostTransform(scale);
    }

    @Override
    public void applyCharacterTransform(float scale) {
        if (this.parent != null) {
            this.parent.applyCharacterTransform(scale);
        }
        this.applyLocalTransform(scale);
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
    public void applyPostTransform(float scale) {
    }

    @Override
    public void update(float ticksPerFrame) {
        this.rotation.update(ticksPerFrame);
    }

    @Override
    public Vec3f getPosition() {
        return this.position;
    }

    @Override
    public Vec3f getScale() {
        return this.scale;
    }

    @Override
    public Vec3f getOffset() {
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

    @Override
    public boolean isShowing() {
        return this.field_78806_j && !this.field_78807_k;
    }

    @Override
    public void setVisible(boolean showModel) {
        this.field_78806_j = showModel;
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
    public void applyLocalTransform(float scale, IMat4x4d matrix) {
        if (this.position.x != 0.0f || this.position.y != 0.0f || this.position.z != 0.0f) {
            TransformUtils.translate(matrix, this.position.x * scale * this.offsetScale, this.position.y * scale * this.offsetScale, this.position.z * scale * this.offsetScale, matrix);
        }
        if (this.offset.x != 0.0f || this.offset.y != 0.0f || this.offset.z != 0.0f) {
            TransformUtils.translate(matrix, this.offset.x * scale * this.offsetScale, this.offset.y * scale * this.offsetScale, this.offset.z * scale * this.offsetScale);
        }
        TransformUtils.rotate(matrix, this.rotation.getSmooth());
        if (this.scale.x != 0.0f || this.scale.y != 0.0f || this.scale.z != 0.0f) {
            TransformUtils.scale(matrix, this.scale.x, this.scale.y, this.scale.z);
        }
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
}

