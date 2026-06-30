/*
 * Decompiled with CFR 0.152.
 */
package baka.animation.instruct;

import baka.animation.instruct.InstructBase;
import goblinbob.mobends.core.client.model.ModelPartTransform;
import goblinbob.mobends.standard.data.PlayerData;
import java.lang.reflect.Field;

public class MoveInstruct
extends InstructBase {
    protected final float offsetX;
    protected final float offsetY;
    protected final float offsetZ;

    @Override
    public void perform(PlayerData data) {
        Field[] fields;
        super.perform(data);
        for (Field field : fields = PlayerData.class.getFields()) {
            try {
                Object obj = field.get(data);
                if (!(obj instanceof ModelPartTransform)) continue;
                ModelPartTransform transform = (ModelPartTransform)obj;
                transform.position.add(this.offsetX, this.offsetY, this.offsetZ);
            }
            catch (IllegalAccessException illegalAccessException) {
                // empty catch block
            }
        }
    }

    public MoveInstruct(float offsetX, float offsetY, float offsetZ) {
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.offsetZ = offsetZ;
    }

    public float getOffsetX() {
        return this.offsetX;
    }

    public float getOffsetY() {
        return this.offsetY;
    }

    public float getOffsetZ() {
        return this.offsetZ;
    }
}

