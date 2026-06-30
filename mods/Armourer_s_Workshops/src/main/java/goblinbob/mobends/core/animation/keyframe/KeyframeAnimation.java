/*
 * Decompiled with CFR 0.152.
 */
package goblinbob.mobends.core.animation.keyframe;

import goblinbob.mobends.core.animation.keyframe.Bone;
import goblinbob.mobends.core.animation.keyframe.Keyframe;
import java.util.Map;

public class KeyframeAnimation {
    public Map<String, Bone> bones;

    public void mirrorRotationYZ(String boneName) {
        Bone bone = this.bones.get(boneName);
        if (bone != null) {
            for (Keyframe keyframe : bone.keyframes) {
                keyframe.mirrorRotationYZ();
            }
        }
    }

    public void swapRotationYZ(String boneName) {
        Bone bone = this.bones.get(boneName);
        if (bone != null) {
            for (Keyframe keyframe : bone.keyframes) {
                keyframe.swapRotationYZ();
            }
        }
    }
}

