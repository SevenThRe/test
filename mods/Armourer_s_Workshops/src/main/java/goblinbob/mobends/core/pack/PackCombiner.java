/*
 * Decompiled with CFR 0.152.
 */
package goblinbob.mobends.core.pack;

import goblinbob.mobends.core.animation.keyframe.KeyframeAnimation;
import goblinbob.mobends.core.kumo.state.template.AnimatorTemplate;
import goblinbob.mobends.core.pack.BendsPackData;
import java.util.HashMap;
import java.util.List;

public class PackCombiner {
    public static BendsPackData combineData(List<BendsPackData> packs) {
        BendsPackData combinedData = new BendsPackData();
        combinedData.targets = new HashMap<String, AnimatorTemplate>();
        combinedData.keyframeAnimations = new HashMap<String, KeyframeAnimation>();
        for (int i2 = packs.size() - 1; i2 >= 0; --i2) {
            BendsPackData data = packs.get(i2);
            combinedData.targets.putAll(data.targets);
            combinedData.keyframeAnimations.putAll(data.keyframeAnimations);
        }
        return combinedData;
    }
}

