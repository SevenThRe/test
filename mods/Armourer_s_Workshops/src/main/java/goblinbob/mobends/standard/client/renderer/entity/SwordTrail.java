/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.GlStateManager$DestFactor
 *  net.minecraft.client.renderer.GlStateManager$SourceFactor
 *  net.minecraft.util.EnumHandSide
 *  org.lwjgl.opengl.GL11
 */
package goblinbob.mobends.standard.client.renderer.entity;

import goblinbob.mobends.core.client.model.ModelPartTransform;
import goblinbob.mobends.core.math.Quaternion;
import goblinbob.mobends.core.math.SmoothOrientation;
import goblinbob.mobends.core.math.vector.IVec3f;
import goblinbob.mobends.core.math.vector.Vec3f;
import goblinbob.mobends.core.util.GUtil;
import goblinbob.mobends.standard.data.BipedEntityData;
import java.util.Iterator;
import java.util.LinkedList;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.EnumHandSide;
import org.lwjgl.opengl.GL11;

public class SwordTrail {
    protected LinkedList<TrailPart> trailPartList = new LinkedList();
    protected float colorRed = 1.0f;
    protected float colorGreen = 1.0f;
    protected float colorBlue = 1.0f;

    public void reset() {
        this.trailPartList.clear();
    }

    public void render() {
        GlStateManager.func_179143_c((int)515);
        GlStateManager.func_179147_l();
        GlStateManager.func_187401_a((GlStateManager.SourceFactor)GlStateManager.SourceFactor.SRC_ALPHA, (GlStateManager.DestFactor)GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
        GlStateManager.func_179129_p();
        GlStateManager.func_179140_f();
        GlStateManager.func_179090_x();
        GL11.glHint((int)3152, (int)4354);
        GlStateManager.func_179103_j((int)7425);
        GlStateManager.func_179094_E();
        GlStateManager.func_187447_r((int)7);
        Iterator it = this.trailPartList.iterator();
        boolean first = true;
        while (it.hasNext()) {
            TrailPart part = (TrailPart)it.next();
            part.draw(first, !it.hasNext(), this.colorRed, this.colorGreen, this.colorBlue);
            first = false;
        }
        GlStateManager.func_187437_J();
        GlStateManager.func_179121_F();
        GlStateManager.func_179098_w();
        GlStateManager.func_179129_p();
        GlStateManager.func_179145_e();
    }

    public void add(BipedEntityData<?> entityData, float velocityX, float velocityY, float velocityZ) {
        this.add(entityData, EnumHandSide.RIGHT, 8.0f, velocityX, velocityY, velocityZ);
    }

    public void add(BipedEntityData<?> entityData, EnumHandSide primaryHand, float length, float velocityX, float velocityY, float velocityZ) {
        Object entity = entityData.getEntity();
        TrailPart newPart = new TrailPart(primaryHand, length, velocityX, velocityY, velocityZ);
        newPart.body.syncUp(entityData.body);
        if (primaryHand == EnumHandSide.RIGHT) {
            newPart.arm.syncUp(entityData.rightArm);
            newPart.foreArm.syncUp(entityData.rightForeArm);
            newPart.itemRotation.set(entityData.renderRightItemRotation.getSmooth());
        } else {
            newPart.arm.syncUp(entityData.leftArm);
            newPart.foreArm.syncUp(entityData.leftForeArm);
            newPart.itemRotation.set(entityData.renderLeftItemRotation.getSmooth());
        }
        newPart.renderOffset.set(entityData.globalOffset.getX(), entityData.globalOffset.getY(), entityData.globalOffset.getZ());
        newPart.renderRotation.set(entityData.renderRotation.getSmooth());
        newPart.renderRotation.negate();
        newPart.centerRotation.set(entityData.centerRotation.getSmooth());
        newPart.centerRotation.y = -newPart.centerRotation.y;
        newPart.centerRotation.z = -newPart.centerRotation.z;
        this.trailPartList.add(newPart);
    }

    public void add(BipedEntityData<?> entityData) {
        this.add(entityData, 0.0f, 0.0f, 0.0f);
    }

    public void add(BipedEntityData<?> entityData, float length) {
        this.add(entityData, EnumHandSide.RIGHT, length, 0.0f, 0.0f, 0.0f);
    }

    public void update(float ticksPerFrame) {
        Iterator it = this.trailPartList.iterator();
        while (it.hasNext()) {
            TrailPart trailPart = (TrailPart)it.next();
            trailPart.update(ticksPerFrame);
            if (!(trailPart.ticksExisted > 20.0f)) continue;
            it.remove();
        }
    }

    public void setColor(float red, float green, float blue) {
        this.colorRed = red;
        this.colorGreen = green;
        this.colorBlue = blue;
    }

    protected static class TrailPart {
        protected EnumHandSide primaryHand;
        protected ModelPartTransform body;
        protected ModelPartTransform arm;
        protected ModelPartTransform foreArm;
        protected Quaternion centerRotation = new Quaternion();
        protected Quaternion renderRotation = new Quaternion();
        protected Vec3f renderOffset = new Vec3f();
        protected Quaternion itemRotation = new Quaternion();
        protected Vec3f position = new Vec3f();
        protected float velocityX;
        protected float velocityY;
        protected float velocityZ;
        protected float ticksExisted = 0.0f;
        private float length;

        public TrailPart(EnumHandSide primaryHand, float length, float velocityX, float velocityY, float velocityZ) {
            this.length = length;
            this.body = new ModelPartTransform();
            this.arm = new ModelPartTransform();
            this.foreArm = new ModelPartTransform();
            this.primaryHand = primaryHand;
            this.velocityX = velocityX;
            this.velocityY = velocityY;
            this.velocityZ = velocityZ;
        }

        public void update(float ticksPerFrame) {
            this.ticksExisted += ticksPerFrame;
            this.position.x += this.velocityX * ticksPerFrame;
            this.position.y += this.velocityY * ticksPerFrame;
            this.position.z += this.velocityZ * ticksPerFrame;
        }

        public void draw(boolean first, boolean last, float red, float green, float blue) {
            float alpha = this.ticksExisted / 5.0f;
            alpha = Math.min(alpha, 1.0f);
            alpha = 1.0f - alpha;
            float xxx = this.primaryHand == EnumHandSide.LEFT ? 1.1f : 1.0f;
            float length = this.length * xxx;
            IVec3f[] points = new Vec3f[]{new Vec3f(0.0f, 0.0f, -length + length * alpha), new Vec3f(0.0f, 0.0f, -length - length * alpha)};
            GUtil.translate(points, 0.0f, 0.0f, length * 2.0f);
            GUtil.rotate(points, this.itemRotation);
            GUtil.translate(points, -1.0f, -6.0f, 0.0f);
            this.rotate((Vec3f[])points, this.foreArm, -1.0f);
            GUtil.translate(points, 0.0f, -4.0f, 0.0f);
            this.rotate((Vec3f[])points, this.arm, -1.0f);
            GUtil.translate(points, this.arm.position.x, 10.0f, 0.0f);
            this.rotate((Vec3f[])points, this.body, 1.0f);
            GUtil.translate(points, 0.0f, 12.0f, 0.0f);
            GUtil.translate(points, 0.0f, -0.75f, 0.125f);
            GUtil.rotate(points, this.centerRotation);
            GUtil.translate(points, 0.0f, 0.75f, -0.125f);
            GUtil.rotate(points, this.renderRotation);
            GUtil.translate(points, this.renderOffset.x, this.renderOffset.y, this.renderOffset.z);
            for (IVec3f point : points) {
                point.add(this.position);
            }
            GlStateManager.func_179131_c((float)red, (float)green, (float)blue, (float)alpha);
            if (!first) {
                GlStateManager.func_187435_e((float)((Vec3f)points[1]).x, (float)((Vec3f)points[1]).y, (float)((Vec3f)points[1]).z);
                GlStateManager.func_187435_e((float)((Vec3f)points[0]).x, (float)((Vec3f)points[0]).y, (float)((Vec3f)points[0]).z);
            }
            GlStateManager.func_187435_e((float)((Vec3f)points[0]).x, (float)((Vec3f)points[0]).y, (float)((Vec3f)points[0]).z);
            GlStateManager.func_187435_e((float)((Vec3f)points[1]).x, (float)((Vec3f)points[1]).y, (float)((Vec3f)points[1]).z);
            if (last) {
                GlStateManager.func_187435_e((float)((Vec3f)points[1]).x, (float)((Vec3f)points[1]).y, (float)((Vec3f)points[1]).z);
                GlStateManager.func_187435_e((float)((Vec3f)points[0]).x, (float)((Vec3f)points[0]).y, (float)((Vec3f)points[0]).z);
            }
        }

        private void rotate(Vec3f[] points, ModelPartTransform part, float f2) {
            if (part.isApplyAnimation()) {
                SmoothOrientation smoothOrientation = new SmoothOrientation();
                smoothOrientation.rotateX(part.getRotateAngle().getX());
                smoothOrientation.rotateY(part.getRotateAngle().getY());
                smoothOrientation.rotateZ(part.getRotateAngle().getZ());
                smoothOrientation.finish();
                GUtil.rotate(points, smoothOrientation.getSmooth());
            } else {
                GUtil.rotate(points, part.rotation.getSmooth());
            }
        }
    }
}

