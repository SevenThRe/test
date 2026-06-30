/*
 * Decompiled with CFR 0.152.
 */
package goblinbob.mobends.core.pack;

import goblinbob.mobends.core.animation.keyframe.KeyframeAnimation;
import goblinbob.mobends.core.kumo.state.IKumoInstancingContext;
import goblinbob.mobends.core.kumo.state.template.AnimatorTemplate;
import java.util.Map;

public class BendsPackData
implements IKumoInstancingContext {
    public Map<String, AnimatorTemplate> targets;
    public Map<String, KeyframeAnimation> keyframeAnimations;

    @Override
    public KeyframeAnimation getAnimation(String key) {
        return this.keyframeAnimations.get(key);
    }
}

