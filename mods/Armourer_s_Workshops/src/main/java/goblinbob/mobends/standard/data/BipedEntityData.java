/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.EntityLivingBase
 */
package goblinbob.mobends.standard.data;

import goblinbob.mobends.core.client.model.ModelPartTransform;
import goblinbob.mobends.core.data.LivingEntityData;
import goblinbob.mobends.core.math.SmoothOrientation;
import goblinbob.mobends.core.math.vector.Vec3d;
import goblinbob.mobends.standard.client.renderer.entity.SwordTrail;
import net.minecraft.entity.EntityLivingBase;

public abstract class BipedEntityData<E extends EntityLivingBase>
extends LivingEntityData<E> {
    public ModelPartTransform head;
    public ModelPartTransform body;
    public ModelPartTransform rightArm;
    public ModelPartTransform leftArm;
    public ModelPartTransform rightLeg;
    public ModelPartTransform leftLeg;
    public ModelPartTransform rightForeArm;
    public ModelPartTransform leftForeArm;
    public ModelPartTransform rightForeLeg;
    public ModelPartTransform leftForeLeg;
    public SmoothOrientation renderRightItemRotation;
    public SmoothOrientation renderLeftItemRotation;
    public SwordTrail swordTrail;
    public SwordTrail leftSwordTrail;
    public Vec3d rightItemOffset = new Vec3d();
    public Vec3d rightItemScale = new Vec3d();
    public Vec3d rightItemRotation = new Vec3d();
    public Vec3d leftItemOffset = new Vec3d();
    public Vec3d leftItemScale = new Vec3d();
    public Vec3d leftItemRotation = new Vec3d();

    public BipedEntityData(E entity) {
        super(entity);
    }

    @Override
    public void initModelPose() {
        super.initModelPose();
        this.body = new ModelPartTransform();
        this.head = new ModelPartTransform(this.body);
        this.rightArm = new ModelPartTransform(this.body);
        this.leftArm = new ModelPartTransform(this.body);
        this.rightLeg = new ModelPartTransform();
        this.leftLeg = new ModelPartTransform();
        this.rightForeArm = new ModelPartTransform(this.rightArm);
        this.leftForeArm = new ModelPartTransform(this.leftArm);
        this.rightForeLeg = new ModelPartTransform(this.rightLeg);
        this.leftForeLeg = new ModelPartTransform(this.leftLeg);
        this.renderRightItemRotation = new SmoothOrientation();
        this.renderLeftItemRotation = new SmoothOrientation();
        this.swordTrail = new SwordTrail();
        this.leftSwordTrail = new SwordTrail();
        this.nameToPartMap.put("body", this.body);
        this.nameToPartMap.put("head", this.head);
        this.nameToPartMap.put("leftArm", this.leftArm);
        this.nameToPartMap.put("rightArm", this.rightArm);
        this.nameToPartMap.put("leftLeg", this.leftLeg);
        this.nameToPartMap.put("rightLeg", this.rightLeg);
        this.nameToPartMap.put("leftForeArm", this.leftForeArm);
        this.nameToPartMap.put("rightForeArm", this.rightForeArm);
        this.nameToPartMap.put("leftForeLeg", this.leftForeLeg);
        this.nameToPartMap.put("rightForeLeg", this.rightForeLeg);
        this.nameToPartMap.put("renderRightItemRotation", this.renderRightItemRotation);
        this.nameToPartMap.put("renderLeftItemRotation", this.renderLeftItemRotation);
        this.body.position.set(0.0f, 12.0f, 0.0f);
        this.head.position.set(0.0f, -12.0f, 0.0f);
        this.rightArm.position.set(-6.0f, -10.0f, 0.0f);
        this.leftArm.position.set(6.0f, -10.0f, 0.0f);
        this.rightLeg.position.set(-2.0f, 12.0f, 0.0f);
        this.leftLeg.position.set(2.0f, 12.0f, 0.0f);
        this.rightForeArm.position.set(0.0f, 4.0f, 2.0f);
        this.leftForeArm.position.set(0.0f, 4.0f, 2.0f);
        this.leftForeLeg.position.set(0.0f, 6.0f, -2.0f);
        this.rightForeLeg.position.set(0.0f, 6.0f, -2.0f);
    }

    @Override
    public void updateParts(float ticksPerFrame) {
        super.updateParts(ticksPerFrame);
        this.head.update(ticksPerFrame);
        this.body.update(ticksPerFrame);
        this.rightArm.update(ticksPerFrame);
        this.leftArm.update(ticksPerFrame);
        this.rightLeg.update(ticksPerFrame);
        this.leftLeg.update(ticksPerFrame);
        this.rightForeArm.update(ticksPerFrame);
        this.leftForeArm.update(ticksPerFrame);
        this.rightForeLeg.update(ticksPerFrame);
        this.leftForeLeg.update(ticksPerFrame);
        this.globalOffset.update(ticksPerFrame);
        this.renderRotation.update(ticksPerFrame);
        this.renderRightItemRotation.update(ticksPerFrame);
        this.renderLeftItemRotation.update(ticksPerFrame);
        this.swordTrail.update(ticksPerFrame);
        this.leftSwordTrail.update(ticksPerFrame);
    }

    @Override
    public E getEntity() {
        return (E)((EntityLivingBase)this.entity);
    }
}

