/*
 * Decompiled with CFR 0.152.
 */
package baka.animation.instruct;

import baka.animation.instruct.InstructBase;
import baka.data.Interpolation;
import baka.data.RotateEntry;
import baka.util.QuaternionHelper;
import goblinbob.mobends.core.client.model.ModelPartTransform;
import goblinbob.mobends.core.math.SmoothOrientation;
import goblinbob.mobends.core.math.vector.Vec3d;
import goblinbob.mobends.standard.data.PlayerData;
import java.lang.reflect.Field;
import java.util.Map;

public class OrientInstruct
extends InstructBase {
    protected Map<String, RotateEntry> rotateEntryMap;
    protected long startTime = -1L;
    protected long stopTime;
    protected long previousTimeMillis = 0L;
    private boolean finished = false;

    public OrientInstruct(Map<String, RotateEntry> rotateEntryMap) {
        this.rotateEntryMap = rotateEntryMap;
    }

    @Override
    public void perform(PlayerData data) {
        super.perform(data);
        long currentTimeMillis = System.currentTimeMillis();
        if (this.previousTimeMillis == 0L) {
            this.previousTimeMillis = currentTimeMillis;
        }
        if (this.startTime == -1L) {
            this.startTime = currentTimeMillis;
            float maxV = 0.0f;
            for (RotateEntry entry : this.rotateEntryMap.values()) {
                maxV = Math.max(entry.getV(), maxV);
            }
            this.stopTime = this.startTime + (long)(maxV * 1000.0f);
        }
        this.rotateEntryMap.forEach((key, value) -> {
            ModelPartTransform transform = OrientInstruct.getTransform(key, data);
            if (transform != null) {
                SmoothOrientation rotation = transform.rotation;
                float progress = Interpolation.progress(this.startTime, value.getV(), currentTimeMillis);
                Vec3d now = QuaternionHelper.getEuler(rotation.getSmooth());
                Vec3d target = new Vec3d(value.getX(), value.getY(), value.getZ());
                float x2 = (float)(now.x + (target.x - now.x) * (double)progress);
                float y2 = (float)(now.y + (target.y - now.y) * (double)progress);
                float z2 = (float)(now.z + (target.z - now.z) * (double)progress);
                if (x2 != 0.0f) {
                    rotation.orientX(x2);
                }
                if (y2 != 0.0f) {
                    rotation.orientY(y2);
                }
                if (z2 != 0.0f) {
                    rotation.orientZ(z2);
                }
                rotation.finish();
            }
        });
        this.previousTimeMillis = currentTimeMillis;
        if (this.startTime > 0L && currentTimeMillis >= this.stopTime) {
            this.finished = true;
        }
    }

    @Override
    public boolean isFinish() {
        return this.finished;
    }

    protected static ModelPartTransform getTransform(String name, PlayerData data) {
        Field[] fields;
        for (Field field : fields = data.getClass().getFields()) {
            Object obj = null;
            try {
                obj = field.get(data);
            }
            catch (IllegalAccessException e2) {
                return null;
            }
            if (obj == null) {
                return null;
            }
            if (!field.getName().equals(name) || !(obj instanceof ModelPartTransform)) continue;
            return (ModelPartTransform)obj;
        }
        return null;
    }

    public Map<String, RotateEntry> getRotateEntryMap() {
        return this.rotateEntryMap;
    }

    public void setRotateEntryMap(Map<String, RotateEntry> rotateEntryMap) {
        this.rotateEntryMap = rotateEntryMap;
    }
}

