/*
 * Decompiled with CFR 0.152.
 */
package goblinbob.mobends.core.bender;

import goblinbob.mobends.core.bender.BoneMetadata;
import goblinbob.mobends.core.data.EntityData;
import goblinbob.mobends.core.math.vector.IVec3fRead;
import goblinbob.mobends.core.math.vector.Vec3f;
import java.util.Map;

public interface IPreviewer<D extends EntityData<?>> {
    public void prePreview(D var1, String var2);

    public void postPreview(D var1, String var2);

    default public IVec3fRead getAnchorPoint() {
        return Vec3f.ZERO;
    }

    public Map<String, BoneMetadata> getBoneMetadata();
}

