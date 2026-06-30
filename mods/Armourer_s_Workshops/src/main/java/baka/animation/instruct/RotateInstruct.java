/*
 * Decompiled with CFR 0.152.
 */
package baka.animation.instruct;

import baka.animation.instruct.InstructBase;
import baka.data.Interpolation;
import baka.data.RotateEntry;
import goblinbob.mobends.core.client.model.ModelPartTransform;
import goblinbob.mobends.core.math.SmoothOrientation;
import goblinbob.mobends.standard.data.PlayerData;
import java.util.Map;

public class RotateInstruct
extends InstructBase {
    protected Map<String, RotateEntry> rotateEntryMap;
    protected long startTime = -1L;
    protected long stopTime;
    protected long previousTimeMillis = 0L;

    public RotateInstruct(Map<String, RotateEntry> rotateEntryMap) {
        this.rotateEntryMap = rotateEntryMap;
    }

    @Override
    public synchronized void start() {
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
    }

    @Override
    public long getWaitTime(PlayerData data) {
        return this.stopTime - System.currentTimeMillis();
    }

    @Override
    public void perform(PlayerData data) {
        super.perform(data);
        long currentTimeMillis = System.currentTimeMillis();
        this.rotateEntryMap.forEach((key, value) -> {
            SmoothOrientation rotation = null;
            if (key.equals("renderRightItemRotation")) {
                rotation = data.renderRightItemRotation;
            } else if (key.equals("renderLeftItemRotation")) {
                rotation = data.renderLeftItemRotation;
            } else {
                ModelPartTransform transform1 = RotateInstruct.getTransform(key, data);
                if (transform1 != null) {
                    rotation = transform1.rotation;
                }
            }
            if (rotation == null) {
                return;
            }
            float previousProgress = Interpolation.progress(this.startTime, value.getV(), this.previousTimeMillis);
            float progress = Interpolation.progress(this.startTime, value.getV(), currentTimeMillis);
            float x2 = Interpolation.cal(0.0f, value.getX(), progress) - Interpolation.cal(0.0f, value.getX(), previousProgress);
            float y2 = Interpolation.cal(0.0f, value.getY(), progress) - Interpolation.cal(0.0f, value.getY(), previousProgress);
            float z2 = Interpolation.cal(0.0f, value.getZ(), progress) - Interpolation.cal(0.0f, value.getZ(), previousProgress);
            rotation.rotateX(x2).rotateY(y2).rotateZ(z2).finish();
            if ("body".equals(key)) {
                data.head.rotation.rotateY(-y2).finish();
            }
        });
        this.previousTimeMillis = currentTimeMillis;
    }

    @Override
    public boolean isFinish() {
        return this.startTime > 0L && System.currentTimeMillis() >= this.stopTime;
    }

    protected static ModelPartTransform getTransform(String name, PlayerData data) {
        Object partForName = data.getPartForName(name);
        if (partForName instanceof ModelPartTransform) {
            return (ModelPartTransform)partForName;
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

