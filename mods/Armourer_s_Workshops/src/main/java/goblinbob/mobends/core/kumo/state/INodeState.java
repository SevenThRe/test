/*
 * Decompiled with CFR 0.152.
 */
package goblinbob.mobends.core.kumo.state;

import goblinbob.mobends.core.animation.keyframe.KeyframeAnimation;
import goblinbob.mobends.core.kumo.state.ConnectionState;
import goblinbob.mobends.core.kumo.state.IKumoContext;
import goblinbob.mobends.core.kumo.state.template.MalformedKumoTemplateException;
import goblinbob.mobends.core.kumo.state.template.keyframe.KeyframeNodeTemplate;
import java.util.List;

public interface INodeState {
    public Iterable<ConnectionState> getConnections();

    public KeyframeAnimation getAnimation();

    public float getProgress();

    public boolean isAnimationFinished();

    public void parseConnections(List<INodeState> var1, KeyframeNodeTemplate var2) throws MalformedKumoTemplateException;

    public void start(IKumoContext var1);

    public void update(IKumoContext var1, float var2);
}

