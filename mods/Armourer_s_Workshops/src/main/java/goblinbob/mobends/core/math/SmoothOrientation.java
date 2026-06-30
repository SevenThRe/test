/*
 * Decompiled with CFR 0.152.
 */
package goblinbob.mobends.core.math;

import goblinbob.mobends.core.math.Quaternion;
import goblinbob.mobends.core.util.EnumAxis;

public class SmoothOrientation {
    public static final float PI = (float)Math.PI;
    protected Quaternion start = new Quaternion();
    protected Quaternion end = new Quaternion();
    protected Quaternion smooth = new Quaternion();
    protected float progress = 1.0f;
    protected float smoothness = 1.0f;
    private SmoothOrientation lock;

    public Quaternion getEnd() {
        return this.end;
    }

    public boolean isLock() {
        return this.lock != null;
    }

    public void lock(float x2, float y2, float z2) {
        SmoothOrientation smoothOrientation = new SmoothOrientation();
        smoothOrientation.rotateX(x2).rotateY(y2).rotateZ(z2).finish();
        smoothOrientation.updateSmooth();
        this.lock = smoothOrientation;
    }

    public void unlock() {
        this.lock = null;
    }

    public Quaternion getSmooth() {
        if (this.lock != null) {
            return this.lock.getSmooth();
        }
        return this.smooth;
    }

    public void set(SmoothOrientation other) {
        this.start.set(other.start);
        this.end.set(other.end);
        this.smooth.set(other.smooth);
        this.progress = other.progress;
    }

    public SmoothOrientation setSmoothness(float smoothness) {
        this.smoothness = smoothness;
        return this;
    }

    public SmoothOrientation set(float x2, float y2, float z2, float w2) {
        this.start.set(x2, y2, z2, w2);
        this.start.normalise();
        this.end.set(this.start);
        this.smooth.set(this.start);
        this.progress = 0.0f;
        return this;
    }

    public SmoothOrientation add(float x2, float y2, float z2, float w2) {
        this.start.x += x2;
        this.start.y += y2;
        this.start.z += z2;
        this.start.w += w2;
        this.end.set(this.start);
        this.smooth.set(this.start);
        this.progress = 0.0f;
        return this;
    }

    public SmoothOrientation orient(float angle, float x2, float y2, float z2) {
        this.start.set(this.smooth);
        this.end.setFromAxisAngle(x2, y2, z2, angle / 180.0f * (float)Math.PI);
        this.progress = 0.0f;
        this.updateSmooth();
        return this;
    }

    public SmoothOrientation orientX(float angle) {
        return this.orient(angle, 1.0f, 0.0f, 0.0f);
    }

    public SmoothOrientation orientY(float angle) {
        return this.orient(angle, 0.0f, 1.0f, 0.0f);
    }

    public SmoothOrientation orientZ(float angle) {
        return this.orient(angle, 0.0f, 0.0f, 1.0f);
    }

    public SmoothOrientation orientInstant(float a2, float x2, float y2, float z2) {
        this.end.setFromAxisAngle(x2, y2, z2, a2 / 180.0f * (float)Math.PI);
        this.start.set(this.end);
        this.smooth.set(this.end);
        return this;
    }

    public SmoothOrientation orientInstantX(float angle) {
        return this.orientInstant(angle, 1.0f, 0.0f, 0.0f);
    }

    public SmoothOrientation orientInstantY(float angle) {
        return this.orientInstant(angle, 0.0f, 1.0f, 0.0f);
    }

    public SmoothOrientation orientInstantZ(float angle) {
        return this.orientInstant(angle, 0.0f, 0.0f, 1.0f);
    }

    public SmoothOrientation rotate(float angle, float x2, float y2, float z2) {
        this.end.rotate(x2, y2, z2, angle / 180.0f * (float)Math.PI);
        this.updateSmooth();
        return this;
    }

    public SmoothOrientation rotateX(float angle) {
        return this.rotate(angle, 1.0f, 0.0f, 0.0f);
    }

    public SmoothOrientation rotateY(float angle) {
        return this.rotate(angle, 0.0f, 1.0f, 0.0f);
    }

    public SmoothOrientation rotateZ(float angle) {
        return this.rotate(angle, 0.0f, 0.0f, 1.0f);
    }

    public SmoothOrientation rotateInstant(float angle, float x2, float y2, float z2) {
        Quaternion rotation = new Quaternion();
        rotation.setFromAxisAngle(x2, y2, z2, angle / 180.0f * (float)Math.PI);
        Quaternion.mul(rotation, this.end, this.end);
        this.start.set(this.end);
        this.smooth.set(this.end);
        return this;
    }

    public SmoothOrientation rotateInstantX(float angle) {
        return this.rotateInstant(angle, 1.0f, 0.0f, 0.0f);
    }

    public SmoothOrientation rotateInstantY(float angle) {
        return this.rotateInstant(angle, 0.0f, 1.0f, 0.0f);
    }

    public SmoothOrientation rotateInstantZ(float angle) {
        return this.rotateInstant(angle, 0.0f, 0.0f, 1.0f);
    }

    public SmoothOrientation localRotate(float angle, float x2, float y2, float z2) {
        Quaternion rotation = new Quaternion();
        rotation.setFromAxisAngle(x2, y2, z2, angle / 180.0f * (float)Math.PI);
        Quaternion.mul(this.end, rotation, this.end);
        this.updateSmooth();
        return this;
    }

    public SmoothOrientation localRotateX(float angle) {
        return this.localRotate(angle, 1.0f, 0.0f, 0.0f);
    }

    public SmoothOrientation localRotateY(float angle) {
        return this.localRotate(angle, 0.0f, 1.0f, 0.0f);
    }

    public SmoothOrientation localRotateZ(float angle) {
        return this.localRotate(angle, 0.0f, 0.0f, 1.0f);
    }

    public SmoothOrientation orientZero() {
        this.start.set(this.smooth);
        this.end.setIdentity();
        this.progress = 0.0f;
        this.updateSmooth();
        return this;
    }

    public SmoothOrientation identity() {
        this.start.setIdentity();
        this.end.setIdentity();
        this.smooth.setIdentity();
        this.progress = 1.0f;
        return this;
    }

    public SmoothOrientation finish() {
        this.smooth.set(this.end);
        this.start.set(this.end);
        this.progress = 1.0f;
        this.updateSmooth();
        return this;
    }

    public SmoothOrientation orient(EnumAxis axis, float angle) {
        if (axis == EnumAxis.X) {
            this.orientX(angle);
        } else if (axis == EnumAxis.Y) {
            this.orientY(angle);
        } else if (axis == EnumAxis.Z) {
            this.orientZ(angle);
        }
        return this;
    }

    public SmoothOrientation rotate(EnumAxis axis, float angle) {
        if (axis == EnumAxis.X) {
            this.rotateX(angle);
        } else if (axis == EnumAxis.Y) {
            this.rotateY(angle);
        } else if (axis == EnumAxis.Z) {
            this.rotateZ(angle);
        }
        return this;
    }

    public SmoothOrientation localRotate(EnumAxis axis, float angle) {
        if (axis == EnumAxis.X) {
            this.localRotateX(angle);
        } else if (axis == EnumAxis.Y) {
            this.localRotateY(angle);
        } else if (axis == EnumAxis.Z) {
            this.localRotateZ(angle);
        }
        return this;
    }

    public void update(float ticksPerFrame) {
        this.progress += ticksPerFrame * this.smoothness;
        this.progress = Math.min(this.progress, 1.0f);
        this.updateSmooth();
    }

    public void updateSmooth() {
        this.smooth.set(this.start.x + (this.end.x - this.start.x) * this.progress, this.start.y + (this.end.y - this.start.y) * this.progress, this.start.z + (this.end.z - this.start.z) * this.progress, this.start.w + (this.end.w - this.start.w) * this.progress);
        this.smooth.normalise();
    }
}

