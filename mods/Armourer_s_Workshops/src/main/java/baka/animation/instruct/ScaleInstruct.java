/*
 * Decompiled with CFR 0.152.
 */
package baka.animation.instruct;

import baka.animation.instruct.InstructBase;
import baka.data.Interpolation;
import baka.data.ScaleEntry;
import goblinbob.mobends.core.client.model.ModelPartTransform;
import goblinbob.mobends.standard.data.PlayerData;
import java.lang.reflect.Field;
import java.util.Map;

public class ScaleInstruct
extends InstructBase {
    protected Map<String, ScaleEntry> scaleEntryMap;
    protected long startTime = -1L;
    protected long stopTime;

    public ScaleInstruct(Map<String, ScaleEntry> scaleEntryMap) {
        this.scaleEntryMap = scaleEntryMap;
    }

    @Override
    public void perform(PlayerData data) {
        super.perform(data);
        long currentTimeMillis = System.currentTimeMillis();
        if (this.startTime == -1L) {
            this.startTime = currentTimeMillis;
            float maxV = 0.0f;
            for (ScaleEntry entry : this.scaleEntryMap.values()) {
                maxV = Math.max(entry.getV(), maxV);
            }
            this.stopTime = this.startTime + (long)(maxV * 1000.0f);
        }
        this.scaleEntryMap.forEach((key, value) -> {
            ModelPartTransform transform = ScaleInstruct.getTransform(key, data);
            if (transform != null) {
                float progress = Interpolation.progress(this.startTime, value.getV(), currentTimeMillis);
                float x2 = Interpolation.cal(0.0f, value.getX(), progress);
                float y2 = Interpolation.cal(0.0f, value.getY(), progress);
                float z2 = Interpolation.cal(0.0f, value.getZ(), progress);
                transform.scale.set(x2, y2, z2);
            }
        });
    }

    @Override
    public boolean isFinish() {
        return System.currentTimeMillis() >= this.stopTime;
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

    public Map<String, ScaleEntry> getScaleEntryMap() {
        return this.scaleEntryMap;
    }

    public void setScaleEntryMap(Map<String, ScaleEntry> scaleEntryMap) {
        this.scaleEntryMap = scaleEntryMap;
    }
}

