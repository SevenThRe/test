/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  eos.moe.dragoncore.api.model.AnimationModelRenderer
 *  eos.moe.dragoncore.bax
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelBox
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.client.model.TextureOffset
 *  net.minecraft.client.renderer.BufferBuilder
 *  net.minecraft.client.renderer.GLAllocation
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.entity.Entity
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 *  org.lwjgl.opengl.GL11
 */
package goblinbob.mobends.core.client.model;

import eos.moe.armourers.DragonArmourers;
import eos.moe.dragoncore.api.model.AnimationModelRenderer;
import eos.moe.dragoncore.bax;
import goblinbob.mobends.core.client.model.BoxFactory;
import goblinbob.mobends.core.client.model.IModelPart;
import goblinbob.mobends.core.client.model.MutatedBox;
import goblinbob.mobends.core.math.SmoothOrientation;
import goblinbob.mobends.core.math.TransformUtils;
import goblinbob.mobends.core.math.matrix.IMat4x4d;
import goblinbob.mobends.core.math.physics.AABBoxGroup;
import goblinbob.mobends.core.math.physics.IAABBox;
import goblinbob.mobends.core.math.physics.ICollider;
import goblinbob.mobends.core.math.vector.IVec3f;
import goblinbob.mobends.core.math.vector.Vec3f;
import goblinbob.mobends.core.util.GlHelper;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.model.TextureOffset;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

public class ModelPart
extends ModelRenderer
implements IModelPart,
AnimationModelRenderer {
    private boolean applyAnimation;
    private float rotateAngleXX;
    private float rotateAngleYY;
    private float rotateAngleZZ;
    public static boolean cancelRenderSkin;
    public Vec3f position = new Vec3f();
    public Vec3f scale = new Vec3f(1.0f, 1.0f, 1.0f);
    public Vec3f offset = new Vec3f();
    public SmoothOrientation rotation = new SmoothOrientation();
    public float offsetScale = 1.0f;
    public Vec3f globalOffset = new Vec3f();
    public String name;
    public Entity renderEntity;
    protected List<MutatedBox> mutatedBoxes = new ArrayList<MutatedBox>();
    protected IModelPart parent;
    protected ICollider collider;
    public boolean debug;
    private Field field;

    public ModelPart(ModelBase model, boolean register, int texOffsetX, int texOffsetY) {
        super(model, texOffsetX, texOffsetY);
        if (!register) {
            model.field_78092_r.remove(model.field_78092_r.size() - 1);
        }
    }

    public ModelPart(ModelBase model, boolean register) {
        this(model, register, 0, 0);
    }

    public ModelPart(ModelBase model, int texOffsetX, int texOffsetY) {
        this(model, true, texOffsetX, texOffsetY);
    }

    @SideOnly(value=Side.CLIENT)
    public void func_78785_a(float scale) {
        this.renderPart(scale);
        if (!cancelRenderSkin && this.name != null && this.renderEntity != null) {
            try {
                if (this.field == null) {
                    Class<?> aClass = Class.forName("net.minecraft.client.renderer.GlStateManager$TextureState");
                    try {
                        this.field = aClass.getField("textureName");
                        this.field.setAccessible(true);
                    }
                    catch (NoSuchFieldException ex) {
                        this.field = aClass.getField("field_179059_b");
                        this.field.setAccessible(true);
                    }
                }
                if (this.field != null) {
                    int o2 = (Integer)this.field.get(GlStateManager.field_179174_p[GlStateManager.field_179162_o]);
                    this.renderPartSkin(scale);
                    GlStateManager.func_179144_i((int)o2);
                }
            }
            catch (ClassNotFoundException | IllegalAccessException | NoSuchFieldException e2) {
                e2.printStackTrace();
            }
        }
    }

    @SideOnly(value=Side.CLIENT)
    public void func_78791_b(float scale) {
        this.renderPart(scale);
    }

    @Override
    public void renderPart(float scale) {
        if (this.debug) {
            return;
        }
        if (!this.isShowing()) {
            return;
        }
        if (!this.field_78812_q) {
            this.func_78788_d(scale);
        }
        GlStateManager.func_179094_E();
        this.applyCharacterTransform(scale);
        GlStateManager.func_179148_o((int)this.field_78811_r);
        if (this.field_78805_m != null) {
            for (ModelRenderer childModel : this.field_78805_m) {
                childModel.func_78785_a(scale);
            }
        }
        GlStateManager.func_179121_F();
    }

    @Override
    public void renderJustPart(float scale) {
        if (!this.isShowing()) {
            return;
        }
        if (!this.field_78812_q) {
            this.func_78788_d(scale);
        }
        GlStateManager.func_179094_E();
        this.applyLocalTransform(scale);
        GlStateManager.func_179148_o((int)this.field_78811_r);
        if (this.field_78805_m != null) {
            for (ModelRenderer childModel : this.field_78805_m) {
                childModel.func_78785_a(scale);
            }
        }
        GlStateManager.func_179121_F();
    }

    @Override
    public void renderPartSkin(float scale) {
        if (!this.field_78812_q) {
            this.func_78788_d(scale);
        }
        GlStateManager.func_179094_E();
        this.applyCharacterTransform(scale);
        this.drawSkin(scale);
        if (this.field_78805_m != null) {
            for (ModelRenderer childModel : this.field_78805_m) {
                if (!(childModel instanceof IModelPart)) continue;
                IModelPart model = (IModelPart)childModel;
                model.renderPartSkin(scale);
            }
        }
        GlStateManager.func_179121_F();
    }

    @Override
    public void renderJustPartSkin(float scale) {
        if (!this.field_78812_q) {
            this.func_78788_d(scale);
        }
        GlStateManager.func_179094_E();
        this.applyLocalTransform(scale);
        this.drawSkin(scale);
        if (this.field_78805_m != null) {
            for (ModelRenderer childModel : this.field_78805_m) {
                if (!(childModel instanceof IModelPart)) continue;
                IModelPart model = (IModelPart)childModel;
                model.renderPartSkin(scale);
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
            TransformUtils.translate(matrix, this.position.x * scale * this.offsetScale, this.position.y * scale * this.offsetScale, this.position.z * scale * this.offsetScale);
        }
        if (this.offset.x != 0.0f || this.offset.y != 0.0f || this.offset.z != 0.0f) {
            TransformUtils.translate(matrix, this.offset.x * scale * this.offsetScale, this.offset.y * scale * this.offsetScale, this.offset.z * scale * this.offsetScale);
        }
        TransformUtils.rotate(matrix, this.rotation.getSmooth());
        if (this.scale.x != 0.0f || this.scale.y != 0.0f || this.scale.z != 0.0f) {
            TransformUtils.scale(matrix, this.scale.x, this.scale.y, this.scale.z, matrix);
        }
    }

    @Override
    public void applyPostTransform(float scale) {
    }

    @SideOnly(value=Side.CLIENT)
    protected void func_78788_d(float scale) {
        this.field_78811_r = GLAllocation.func_74526_a((int)1);
        GlStateManager.func_187423_f((int)this.field_78811_r, (int)4864);
        BufferBuilder bufferbuilder = Tessellator.func_178181_a().func_178180_c();
        for (ModelBox modelBox : this.field_78804_l) {
            modelBox.func_178780_a(bufferbuilder, scale);
        }
        GlStateManager.func_187415_K();
        this.field_78812_q = true;
    }

    @Override
    public void update(float ticksPerFrame) {
        this.rotation.update(ticksPerFrame);
    }

    public void func_78793_a(float x2, float y2, float z2) {
    }

    public ModelPart setPosition(float x2, float y2, float z2) {
        this.position.set(x2, y2, z2);
        return this;
    }

    public ModelPart setOffset(float x2, float y2, float z2) {
        this.field_82906_o = x2;
        this.field_82908_p = y2;
        this.field_82907_q = z2;
        return this;
    }

    public ModelPart setScale(float x2, float y2, float z2) {
        this.scale.x = x2;
        this.scale.y = y2;
        this.scale.z = z2;
        return this;
    }

    public ModelPart resetScale() {
        this.scale.set(0.0f, 0.0f, 0.0f);
        return this;
    }

    public BoxFactory developBox(float x2, float y2, float z2, int dx, int dy, int dz, float scaleFactor) {
        return new BoxFactory(x2, y2, z2, dx, dy, dz, scaleFactor).setTarget(this);
    }

    public ModelPart addVanillaBox(ModelBox box) {
        this.field_78804_l.add(box);
        this.field_78812_q = false;
        return this;
    }

    public ModelPart addBox(MutatedBox box) {
        this.mutatedBoxes.add(box);
        this.field_78804_l.add(box);
        this.field_78812_q = false;
        return this;
    }

    public ModelPart addModelBox(float x2, float y2, float z2, int width, int height, int length, float scaleFactor) {
        return this.addBox(new MutatedBox(this, this.field_78803_o, this.field_78813_p, x2, y2, z2, width, height, length, scaleFactor));
    }

    public ModelPart addBox(String partName, float offX, float offY, float offZ, int width, int height, int depth) {
        partName = this.field_78802_n + "." + partName;
        TextureOffset textureoffset = this.field_78810_s.func_78084_a(partName);
        this.func_78784_a(textureoffset.field_78783_a, textureoffset.field_78782_b);
        return this.addBox((MutatedBox)new MutatedBox(this, this.field_78803_o, this.field_78813_p, offX, offY, offZ, width, height, depth, 0.0f).func_78244_a(partName));
    }

    public ModelPart addBox(float offX, float offY, float offZ, int width, int height, int depth) {
        return this.addBox(new MutatedBox(this, this.field_78803_o, this.field_78813_p, offX, offY, offZ, width, height, depth, 0.0f));
    }

    public ModelPart addBox(float offX, float offY, float offZ, int width, int height, int depth, boolean mirrored) {
        return this.addBox(new MutatedBox(this, this.field_78803_o, this.field_78813_p, offX, offY, offZ, width, height, depth, 0.0f, mirrored));
    }

    public void func_78790_a(float x2, float y2, float z2, int width, int height, int length, float scaleFactor) {
        this.addModelBox(x2, y2, z2, width, height, length, scaleFactor);
    }

    public MutatedBox getBox() {
        return this.getBox(0);
    }

    public MutatedBox getBox(int idx) {
        return (MutatedBox)((Object)this.field_78804_l.get(idx));
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
    public IModelPart getParent() {
        return this.parent;
    }

    @Override
    public boolean isShowing() {
        return this.field_78806_j && !this.field_78807_k;
    }

    protected void updateBounds() {
        if (this.mutatedBoxes.size() == 1) {
            this.collider = this.mutatedBoxes.get(0).createAABB();
        } else {
            IAABBox[] bounds = new IAABBox[this.mutatedBoxes.size()];
            for (int i2 = 0; i2 < bounds.length; ++i2) {
                bounds[i2] = this.mutatedBoxes.get(i2).createAABB();
            }
            this.collider = new AABBoxGroup(bounds);
        }
    }

    public ModelPart setMirror(boolean mirror) {
        this.field_78809_i = mirror;
        return this;
    }

    public void finish() {
        this.rotation.finish();
    }

    @Override
    public void setVisible(boolean showModel) {
        this.field_78806_j = showModel;
    }

    public ModelPart setParent(IModelPart parent) {
        this.parent = parent;
        return this;
    }

    public int getTextureOffsetX() {
        return this.field_78803_o;
    }

    public int getTextureOffsetY() {
        return this.field_78813_p;
    }

    public void drawSkin(float scale) {
        if (this.name != null && this.renderEntity != null) {
            GlStateManager.func_179094_E();
            switch (this.name) {
                case "\u5934": {
                    DragonArmourers.renderHead(this.renderEntity);
                    break;
                }
                case "\u8eab\u4f53": {
                    DragonArmourers.renderSkirt(this.renderEntity);
                    GL11.glTranslatef((float)0.0f, (float)(-12.0f * scale), (float)0.0f);
                    DragonArmourers.renderWings(this.renderEntity);
                    DragonArmourers.renderChest(this.renderEntity);
                    break;
                }
                case "\u5de6\u80f3\u818a": {
                    DragonArmourers.renderLeftArm(this.renderEntity);
                    break;
                }
                case "\u5de6\u624b\u81c2": {
                    GL11.glTranslatef((float)-0.005f, (float)(-3.85f * scale), (float)-0.13f);
                    DragonArmourers.renderLeftForeArm(this.renderEntity);
                    break;
                }
                case "\u53f3\u80f3\u818a": {
                    DragonArmourers.renderRightArm(this.renderEntity);
                    break;
                }
                case "\u53f3\u624b\u81c2": {
                    GL11.glTranslatef((float)0.005f, (float)(-3.8f * scale), (float)-0.13f);
                    DragonArmourers.renderRightForeArm(this.renderEntity);
                    break;
                }
                case "\u5de6\u5927\u817f": {
                    DragonArmourers.renderLeftLeg(this.renderEntity);
                    break;
                }
                case "\u5de6\u5c0f\u817f": {
                    GL11.glTranslatef((float)0.0f, (float)(-6.1f * scale), (float)0.12f);
                    DragonArmourers.renderLeftForeLeg(this.renderEntity);
                    DragonArmourers.renderLeftFeet(this.renderEntity);
                    break;
                }
                case "\u53f3\u5927\u817f": {
                    DragonArmourers.renderRightLeg(this.renderEntity);
                    break;
                }
                case "\u53f3\u5c0f\u817f": {
                    GL11.glTranslatef((float)0.0f, (float)(-6.1f * scale), (float)0.12f);
                    DragonArmourers.renderRightForeLeg(this.renderEntity);
                    DragonArmourers.renderRightFeet(this.renderEntity);
                }
            }
            GlStateManager.func_179121_F();
        }
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setRenderEntity(Entity renderEntity) {
        this.renderEntity = renderEntity;
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
        this.offset.set(part.getOffset());
        this.rotation.set(part.getRotation());
        this.scale.set(part.getScale());
        this.offsetScale = part.getOffsetScale();
        this.globalOffset.set(part.getGlobalOffset());
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

