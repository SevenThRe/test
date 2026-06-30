/*
 * Decompiled with CFR 0.152.
 */
package goblinbob.mobends.core.kumo.state.keyframe;

import goblinbob.mobends.core.animation.keyframe.ArmatureMask;
import goblinbob.mobends.core.animation.keyframe.Bone;
import goblinbob.mobends.core.animation.keyframe.Keyframe;
import goblinbob.mobends.core.animation.keyframe.KeyframeAnimation;
import goblinbob.mobends.core.client.model.IModelPart;
import goblinbob.mobends.core.data.EntityData;
import goblinbob.mobends.core.kumo.state.ConnectionState;
import goblinbob.mobends.core.kumo.state.IKumoContext;
import goblinbob.mobends.core.kumo.state.IKumoInstancingContext;
import goblinbob.mobends.core.kumo.state.ILayerState;
import goblinbob.mobends.core.kumo.state.INodeState;
import goblinbob.mobends.core.kumo.state.keyframe.KeyframeNodeRegistry;
import goblinbob.mobends.core.kumo.state.template.MalformedKumoTemplateException;
import goblinbob.mobends.core.kumo.state.template.keyframe.ConnectionTemplate;
import goblinbob.mobends.core.kumo.state.template.keyframe.KeyframeLayerTemplate;
import goblinbob.mobends.core.kumo.state.template.keyframe.KeyframeNodeTemplate;
import goblinbob.mobends.core.util.KeyframeUtils;
import goblinbob.mobends.core.util.Tween;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class KeyframeLayerState
implements ILayerState {
    private List<INodeState> nodeStates = new ArrayList<INodeState>();
    private ArmatureMask mask;
    private INodeState previousNode;
    private INodeState currentNode;
    private float transitionProgress = 0.0f;
    private float transitionDuration = 0.0f;
    private ConnectionTemplate.Easing transitionEasing = ConnectionTemplate.Easing.EASE_IN_OUT;

    public KeyframeLayerState(IKumoInstancingContext context, KeyframeLayerTemplate layerTemplate) throws MalformedKumoTemplateException {
        this.mask = layerTemplate.mask;
        for (KeyframeNodeTemplate nodeTemplate : layerTemplate.nodes) {
            this.nodeStates.add(KeyframeNodeRegistry.INSTANCE.createFromTemplate(context, nodeTemplate));
        }
        for (int i2 = 0; i2 < this.nodeStates.size(); ++i2) {
            this.nodeStates.get(i2).parseConnections(this.nodeStates, layerTemplate.nodes.get(i2));
        }
        try {
            this.currentNode = this.nodeStates.get(layerTemplate.entryNode);
        }
        catch (IndexOutOfBoundsException e2) {
            throw new MalformedKumoTemplateException("Entry node index is out of bounds.");
        }
    }

    @Override
    public void start(IKumoContext context) {
        this.currentNode.start(context);
    }

    @Override
    public void update(IKumoContext context, float deltaTime) throws MalformedKumoTemplateException {
        KeyframeAnimation animation;
        EntityData<?> data = context.getEntityData();
        if (this.currentNode != null && (animation = this.currentNode.getAnimation()) != null) {
            this.applyRestPose(data, animation);
            if (this.previousNode != null) {
                float t2 = this.transitionProgress / this.transitionDuration;
                switch (this.transitionEasing) {
                    case EASE_IN: {
                        t2 = (float)Tween.easeIn(t2, 2.0);
                        break;
                    }
                    case EASE_OUT: {
                        t2 = (float)Tween.easeOut(t2, 2.0);
                        break;
                    }
                    case EASE_IN_OUT: {
                        t2 = (float)Tween.easeInOut(t2, 2.0);
                        break;
                    }
                }
                KeyframeAnimation previousAnimation = this.previousNode.getAnimation();
                this.applyKeyframeAnimation(data, previousAnimation, this.previousNode.getProgress(), 1.0f - t2);
                this.applyKeyframeAnimation(data, animation, this.currentNode.getProgress(), t2);
                this.transitionProgress += deltaTime;
                if (this.transitionProgress >= this.transitionDuration) {
                    this.previousNode = null;
                }
            } else {
                this.applyKeyframeAnimation(data, animation, this.currentNode.getProgress(), 1.0f);
            }
        }
        context.setCurrentNode(this.currentNode);
        for (INodeState node : this.nodeStates) {
            node.update(context, deltaTime);
        }
        for (ConnectionState connection : this.currentNode.getConnections()) {
            if (!connection.triggerCondition.isConditionMet(context)) continue;
            this.transitionDuration = connection.transitionDuration;
            this.transitionEasing = connection.transitionEasing;
            if (this.transitionDuration == 0.0f) {
                this.previousNode = null;
            } else {
                this.previousNode = this.currentNode;
                this.transitionProgress = 0.0f;
            }
            this.currentNode = connection.targetNode;
            this.currentNode.start(context);
            break;
        }
    }

    public void applyRestPose(EntityData<?> entityData, KeyframeAnimation animation) {
        if (this.shouldPartBeAffected("root") && animation.bones.containsKey("root")) {
            entityData.globalOffset.set(0.0f, 0.0f, 0.0f);
        }
        if (this.shouldPartBeAffected("root") && animation.bones.containsKey("root") || this.shouldPartBeAffected("centerRotation") && animation.bones.containsKey("centerRotation")) {
            entityData.centerRotation.set(0.0f, 0.0f, 0.0f, 0.0f);
        }
        for (Map.Entry<String, Bone> entry : animation.bones.entrySet()) {
            Object part;
            String key = entry.getKey();
            if (!this.shouldPartBeAffected(key) || !((part = entityData.getPartForName(key)) instanceof IModelPart)) continue;
            ((IModelPart)part).getRotation().set(0.0f, 0.0f, 0.0f, 0.0f);
            ((IModelPart)part).getOffset().set(0.0f, 0.0f, 0.0f);
        }
    }

    public void applyKeyframeAnimation(EntityData<?> entityData, KeyframeAnimation animation, float keyframeIndex, float amount) {
        Keyframe nextFrame;
        Keyframe keyframe;
        Bone rootBone;
        int frameA = (int)keyframeIndex;
        int frameB = (int)keyframeIndex + 1;
        float tween = keyframeIndex - (float)frameA;
        if (this.shouldPartBeAffected("root") && animation.bones.containsKey("root")) {
            rootBone = animation.bones.get("root");
            keyframe = rootBone.keyframes.get(frameA);
            nextFrame = rootBone.keyframes.get(frameB);
            if (keyframe != null && nextFrame != null) {
                KeyframeUtils.tweenVectorAdditive(entityData.globalOffset, keyframe.position, nextFrame.position, tween, amount);
            }
        }
        if (this.shouldPartBeAffected("centerRotation") && animation.bones.containsKey("centerRotation")) {
            rootBone = animation.bones.get("centerRotation");
            keyframe = rootBone.keyframes.get(frameA);
            nextFrame = rootBone.keyframes.get(frameB);
            if (keyframe != null && nextFrame != null) {
                KeyframeUtils.tweenOrientationAdditive(entityData.centerRotation, keyframe.rotation, nextFrame.rotation, tween, amount);
                KeyframeUtils.tweenVectorAdditive(entityData.globalOffset, keyframe.position, nextFrame.position, tween, amount);
            }
        }
        for (Map.Entry<String, Bone> entry : animation.bones.entrySet()) {
            String key = entry.getKey();
            if (!this.shouldPartBeAffected(key)) continue;
            Bone bone = entry.getValue();
            Object part = entityData.getPartForName(key);
            if (part == null) continue;
            Keyframe keyframe2 = bone.keyframes.get(frameA);
            Keyframe nextFrame2 = bone.keyframes.get(frameB);
            if (keyframe2 == null || nextFrame2 == null || !(part instanceof IModelPart)) continue;
            KeyframeUtils.tweenOrientationAdditive(((IModelPart)part).getRotation(), keyframe2.rotation, nextFrame2.rotation, tween, amount);
            KeyframeUtils.tweenVectorAdditive(((IModelPart)part).getOffset(), keyframe2.position, nextFrame2.position, tween, -amount);
        }
    }

    private boolean shouldPartBeAffected(String partName) {
        return this.mask == null || this.mask.doesAllow(partName);
    }

    public static KeyframeLayerState createFromTemplate(IKumoInstancingContext data, KeyframeLayerTemplate layerTemplate) throws MalformedKumoTemplateException {
        return new KeyframeLayerState(data, layerTemplate);
    }
}

