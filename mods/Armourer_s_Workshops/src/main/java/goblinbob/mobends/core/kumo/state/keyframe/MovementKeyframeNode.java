/*
 * Decompiled with CFR 0.152.
 */
package goblinbob.mobends.core.kumo.state.keyframe;

import goblinbob.mobends.core.animation.keyframe.Bone;
import goblinbob.mobends.core.animation.keyframe.KeyframeAnimation;
import goblinbob.mobends.core.data.LivingEntityData;
import goblinbob.mobends.core.kumo.state.ConnectionState;
import goblinbob.mobends.core.kumo.state.IKumoContext;
import goblinbob.mobends.core.kumo.state.IKumoInstancingContext;
import goblinbob.mobends.core.kumo.state.INodeState;
import goblinbob.mobends.core.kumo.state.template.MalformedKumoTemplateException;
import goblinbob.mobends.core.kumo.state.template.keyframe.ConnectionTemplate;
import goblinbob.mobends.core.kumo.state.template.keyframe.KeyframeNodeTemplate;
import goblinbob.mobends.core.kumo.state.template.keyframe.MovementKeyframeNodeTemplate;
import java.util.ArrayList;
import java.util.List;

public class MovementKeyframeNode
implements INodeState {
    public final KeyframeAnimation animation;
    private int animationDuration;
    private final int startFrame;
    private final float playbackSpeed;
    List<ConnectionState> connections = new ArrayList<ConnectionState>();
    private float progress;

    public MovementKeyframeNode(IKumoInstancingContext context, MovementKeyframeNodeTemplate nodeTemplate) {
        this(nodeTemplate.animationKey != null ? context.getAnimation(nodeTemplate.animationKey) : null, nodeTemplate.startFrame, nodeTemplate.playbackSpeed);
    }

    public MovementKeyframeNode(KeyframeAnimation animation, int startFrame, float playbackSpeed) {
        this.animation = animation;
        this.startFrame = startFrame;
        this.playbackSpeed = playbackSpeed;
        if (animation != null) {
            this.animationDuration = 0;
            for (Bone bone : animation.bones.values()) {
                if (bone.keyframes.size() <= this.animationDuration) continue;
                this.animationDuration = bone.keyframes.size();
            }
        }
        this.progress = this.startFrame;
    }

    @Override
    public Iterable<ConnectionState> getConnections() {
        return this.connections;
    }

    @Override
    public KeyframeAnimation getAnimation() {
        return this.animation;
    }

    @Override
    public float getProgress() {
        return this.progress;
    }

    @Override
    public boolean isAnimationFinished() {
        return false;
    }

    @Override
    public void parseConnections(List<INodeState> nodeStates, KeyframeNodeTemplate template) throws MalformedKumoTemplateException {
        if (template.connections != null) {
            for (ConnectionTemplate connectionTemplate : template.connections) {
                this.connections.add(ConnectionState.createFromTemplate(nodeStates, connectionTemplate));
            }
        }
    }

    @Override
    public void start(IKumoContext context) {
        this.progress = this.startFrame;
        for (ConnectionState connection : this.connections) {
            connection.triggerCondition.onNodeStarted(context);
        }
    }

    @Override
    public void update(IKumoContext context, float deltaTime) {
        LivingEntityData data = (LivingEntityData)context.getEntityData();
        if (this.animation != null) {
            float PI = (float)Math.PI;
            float limbSwing = data.limbSwing.get().floatValue() * 0.6662f;
            float limbSwingAmount = data.limbSwingAmount.get().floatValue() * 0.5f / (float)Math.PI * 180.0f;
            this.progress = this.playbackSpeed * limbSwing;
            this.progress %= (float)(this.animationDuration - 1);
        }
    }
}

