/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.vecmath.Matrix3f
 *  javax.vecmath.SingularMatrixException
 *  javax.vecmath.Tuple3d
 *  javax.vecmath.Tuple3f
 *  javax.vecmath.Vector3d
 *  javax.vecmath.Vector3f
 *  net.minecraft.entity.Entity
 */
package blockbuster.emitter;

import blockbuster.components.appearance.BedrockComponentCollisionAppearance;
import blockbuster.components.appearance.BedrockComponentCollisionTinting;
import blockbuster.emitter.BedrockEmitter;
import blockbuster.math.molang.expressions.MolangExpression;
import blockbuster.utils.MatrixUtils;
import java.util.HashMap;
import java.util.Map;
import javax.vecmath.Matrix3f;
import javax.vecmath.SingularMatrixException;
import javax.vecmath.Tuple3d;
import javax.vecmath.Tuple3f;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;
import net.minecraft.entity.Entity;

public class BedrockParticle {
    public float random1 = (float)Math.random();
    public float random2 = (float)Math.random();
    public float random3 = (float)Math.random();
    public float random4 = (float)Math.random();
    public int age;
    public int lifetime;
    public boolean dead;
    public boolean relativePosition;
    public boolean relativeRotation;
    public boolean relativeDirection;
    public boolean relativeScale;
    public boolean relativeScaleBillboard;
    public boolean relativeAcceleration;
    public boolean realisticCollisionDrag;
    public float linearVelocity;
    public float angularVelocity;
    public boolean gravity;
    public boolean manual;
    private int expireAge = -1;
    private int expirationDelay = -1;
    public Vector3f collisionTime = new Vector3f(-2.0f, -2.0f, -2.0f);
    public HashMap<Entity, Vector3f> entityCollisionTime = new HashMap();
    public boolean collided;
    public int bounces;
    public int firstIntersection = -1;
    public boolean intersected;
    public float rotation;
    public float initialRotation;
    public float prevRotation;
    public float rotationVelocity;
    public float rotationAcceleration;
    public float rotationDrag;
    public Vector3d offset = new Vector3d();
    public Vector3d position = new Vector3d();
    public Vector3d initialPosition = new Vector3d();
    public Vector3d prevPosition = new Vector3d();
    public Matrix3f matrix = new Matrix3f();
    private boolean matrixSet;
    public Vector3f speed = new Vector3f();
    public Vector3f acceleration = new Vector3f();
    public Vector3f accelerationFactor = new Vector3f(1.0f, 1.0f, 1.0f);
    public float drag = 0.0f;
    public float dragFactor = 0.0f;
    public float r = 1.0f;
    public float g = 1.0f;
    public float b = 1.0f;
    public float a = 1.0f;
    private Vector3d global = new Vector3d();

    public BedrockParticle() {
        this.speed.set((float)Math.random() - 0.5f, (float)Math.random() - 0.5f, (float)Math.random() - 0.5f);
        this.speed.normalize();
        this.matrix.setIdentity();
    }

    public boolean isCollisionTexture(BedrockEmitter emitter) {
        return MolangExpression.isOne(emitter.scheme.getOrCreate(BedrockComponentCollisionAppearance.class).enabled) && this.intersected;
    }

    public boolean isCollisionTinting(BedrockEmitter emitter) {
        return MolangExpression.isOne(emitter.scheme.getOrCreate(BedrockComponentCollisionTinting.class).enabled) && this.intersected;
    }

    public int getExpireAge() {
        return this.expireAge;
    }

    public int getExpirationDelay() {
        return this.expirationDelay;
    }

    public BedrockParticle softCopy(BedrockParticle to2) {
        to2.age = this.age;
        to2.expireAge = this.expireAge;
        to2.expirationDelay = this.expirationDelay;
        to2.realisticCollisionDrag = this.realisticCollisionDrag;
        to2.collisionTime = (Vector3f)this.collisionTime.clone();
        to2.entityCollisionTime = new HashMap();
        for (Map.Entry<Entity, Vector3f> entry : this.entityCollisionTime.entrySet()) {
            to2.entityCollisionTime.put(entry.getKey(), (Vector3f)entry.getValue().clone());
        }
        to2.bounces = this.bounces;
        to2.firstIntersection = this.firstIntersection;
        to2.offset = (Vector3d)this.offset.clone();
        to2.position = (Vector3d)this.position.clone();
        to2.initialPosition = (Vector3d)this.initialPosition.clone();
        to2.prevPosition = (Vector3d)this.prevPosition.clone();
        to2.matrix = (Matrix3f)this.matrix.clone();
        to2.matrixSet = this.matrixSet;
        to2.speed = (Vector3f)this.speed.clone();
        to2.acceleration = (Vector3f)this.acceleration.clone();
        to2.accelerationFactor = (Vector3f)this.accelerationFactor.clone();
        to2.dragFactor = this.dragFactor;
        to2.global = (Vector3d)this.global.clone();
        return to2;
    }

    public double getDistanceSq(BedrockEmitter emitter) {
        Vector3d pos = this.getGlobalPosition(emitter);
        double dx2 = emitter.cX - pos.x;
        double dy2 = emitter.cY - pos.y;
        double dz2 = emitter.cZ - pos.z;
        return dx2 * dx2 + dy2 * dy2 + dz2 * dz2;
    }

    public double getAge(float partialTick) {
        return (double)((float)this.age + partialTick) / 20.0;
    }

    public Vector3d getGlobalPosition(BedrockEmitter emitter) {
        return this.getGlobalPosition(emitter, this.position);
    }

    public Vector3d getGlobalPosition(BedrockEmitter emitter, Vector3d vector) {
        double px2 = vector.x;
        double py2 = vector.y;
        double pz2 = vector.z;
        if (this.relativePosition && this.relativeRotation) {
            Vector3f v2 = new Vector3f((float)px2, (float)py2, (float)pz2);
            emitter.rotation.transform((Tuple3f)v2);
            px2 = v2.x;
            py2 = v2.y;
            pz2 = v2.z;
            px2 += emitter.lastGlobal.x;
            py2 += emitter.lastGlobal.y;
            pz2 += emitter.lastGlobal.z;
        }
        this.global.set(px2, py2, pz2);
        return this.global;
    }

    public void update(BedrockEmitter emitter) {
        this.prevRotation = this.rotation;
        this.prevPosition.set((Tuple3d)this.position);
        this.setupMatrix(emitter);
        if (!this.manual) {
            if (this.realisticCollisionDrag && Math.round(this.speed.x * 10000.0f) == 0 && Math.round(this.speed.y * 10000.0f) == 0 && Math.round(this.speed.z * 10000.0f) == 0) {
                this.dragFactor = 0.0f;
                this.speed.scale(0.0f);
            }
            if (this.entityCollisionTime.isEmpty()) {
                this.transformOffsetToGlobal();
            } else {
                for (Map.Entry<Entity, Vector3f> entry : this.entityCollisionTime.entrySet()) {
                    if (entry.getValue().y == (float)this.age) continue;
                    this.transformOffsetToGlobal();
                }
            }
            float rotationAcceleration = this.rotationAcceleration / 20.0f - this.rotationDrag * this.rotationVelocity;
            this.rotationVelocity += rotationAcceleration / 20.0f;
            this.rotation = this.initialRotation + this.rotationVelocity * (float)this.age;
            if (this.age == 0) {
                if (this.relativeDirection) {
                    emitter.rotation.transform((Tuple3f)this.speed);
                }
                if (this.linearVelocity != 0.0f) {
                    Vector3f v2 = new Vector3f(emitter.lastGlobal);
                    v2.x = (float)((double)v2.x - emitter.prevGlobal.x);
                    v2.y = (float)((double)v2.y - emitter.prevGlobal.y);
                    v2.z = (float)((double)v2.z - emitter.prevGlobal.z);
                    this.speed.x += v2.x * this.linearVelocity;
                    this.speed.y += v2.y * this.linearVelocity;
                    this.speed.z += v2.z * this.linearVelocity;
                }
                if (this.angularVelocity != 0.0f) {
                    Matrix3f rotation1 = new Matrix3f(emitter.rotation);
                    Matrix3f identity = new Matrix3f();
                    identity.setIdentity();
                    try {
                        Matrix3f rotation0 = new Matrix3f(emitter.prevRotation);
                        rotation0.invert();
                        rotation1.mul(rotation0);
                        Vector3f angularV = MatrixUtils.getAngularVelocity(rotation1);
                        Vector3f radius = new Vector3f(emitter.translation);
                        radius.x = (float)((double)radius.x + (this.position.x - emitter.lastGlobal.x));
                        radius.y = (float)((double)radius.y + (this.position.y - emitter.lastGlobal.y));
                        radius.z = (float)((double)radius.z + (this.position.z - emitter.lastGlobal.z));
                        Vector3f v3 = new Vector3f();
                        v3.cross(angularV, radius);
                        this.speed.x += v3.x * this.angularVelocity;
                        this.speed.y += v3.y * this.angularVelocity;
                        this.speed.z += v3.z * this.angularVelocity;
                    }
                    catch (SingularMatrixException singularMatrixException) {
                        // empty catch block
                    }
                }
            }
            if (this.relativeAcceleration) {
                emitter.rotation.transform((Tuple3f)this.acceleration);
            }
            Vector3f drag = new Vector3f(this.speed);
            drag.scale(-(this.drag + this.dragFactor));
            if (this.gravity) {
                this.acceleration.y = (float)((double)this.acceleration.y - 9.81);
            }
            this.acceleration.add((Tuple3f)drag);
            this.acceleration.scale(0.05f);
            this.speed.add((Tuple3f)this.acceleration);
            Vector3f speed0 = new Vector3f(this.speed);
            speed0.x *= this.accelerationFactor.x;
            speed0.y *= this.accelerationFactor.y;
            speed0.z *= this.accelerationFactor.z;
            if (this.relativePosition || this.relativeRotation) {
                this.matrix.transform((Tuple3f)speed0);
            }
            this.position.x += (double)(speed0.x / 20.0f);
            this.position.y += (double)(speed0.y / 20.0f);
            this.position.z += (double)(speed0.z / 20.0f);
        }
        if (this.lifetime >= 0 && (this.age >= this.lifetime || this.age >= this.expireAge && this.expireAge != -1)) {
            this.dead = true;
        }
        ++this.age;
    }

    public void setExpirationDelay(double delay) {
        int expirationDelay = (int)delay;
        if (this.age + expirationDelay < this.expireAge || this.expireAge == -1) {
            this.expirationDelay = Math.abs(expirationDelay);
            this.expireAge = this.age + this.expirationDelay;
        }
    }

    public void setupMatrix(BedrockEmitter emitter) {
        if (this.relativePosition) {
            if (this.relativeRotation) {
                this.matrix.setIdentity();
            } else if (!this.matrixSet) {
                this.matrix.set(emitter.rotation);
                this.matrixSet = true;
            }
        } else if (this.relativeRotation) {
            this.matrix.set(emitter.rotation);
        }
    }

    public void transformOffsetToGlobal() {
        this.offset.scale(6.0);
        this.speed.x = (float)((double)this.speed.x + this.offset.x);
        this.speed.y = (float)((double)this.speed.y + this.offset.y);
        this.speed.z = (float)((double)this.speed.z + this.offset.z);
        this.offset.scale(0.0);
    }
}

