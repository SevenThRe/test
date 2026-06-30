/*
 * Decompiled with CFR 0.152.
 */
package goblinbob.mobends.core.kumo.state.keyframe;

import goblinbob.mobends.core.kumo.state.IKumoInstancingContext;
import goblinbob.mobends.core.kumo.state.INodeState;
import goblinbob.mobends.core.kumo.state.template.MalformedKumoTemplateException;
import goblinbob.mobends.core.kumo.state.template.keyframe.KeyframeNodeTemplate;

@FunctionalInterface
public interface IKeyframeNodeFactory<N extends INodeState, T extends KeyframeNodeTemplate> {
    public N createKeyframeNode(IKumoInstancingContext var1, T var2) throws MalformedKumoTemplateException;
}

