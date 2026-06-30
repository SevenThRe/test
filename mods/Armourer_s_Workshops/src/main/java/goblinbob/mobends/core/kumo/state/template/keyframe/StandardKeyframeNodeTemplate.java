/*
 * Decompiled with CFR 0.152.
 */
package goblinbob.mobends.core.kumo.state.template.keyframe;

import goblinbob.mobends.core.kumo.state.IKumoValidationContext;
import goblinbob.mobends.core.kumo.state.template.MalformedKumoTemplateException;
import goblinbob.mobends.core.kumo.state.template.keyframe.KeyframeNodeTemplate;

public class StandardKeyframeNodeTemplate
extends KeyframeNodeTemplate {
    public String animationKey;
    public int startFrame = 0;
    public float playbackSpeed = 1.0f;
    public boolean looping = false;

    @Override
    public void validate(IKumoValidationContext context) throws MalformedKumoTemplateException {
        super.validate(context);
        if (!context.doesAnimationExist(this.animationKey)) {
            throw new MalformedKumoTemplateException(String.format("Trying to use a missing animation: \"%s\".", this.animationKey));
        }
    }
}

