/*
 * Decompiled with CFR 0.152.
 */
package goblinbob.mobends.core.kumo.state;

import goblinbob.mobends.core.data.EntityData;
import goblinbob.mobends.core.kumo.state.IKumoContext;
import goblinbob.mobends.core.kumo.state.ILayerState;
import goblinbob.mobends.core.kumo.state.INodeState;

public class KumoContext
implements IKumoContext {
    public EntityData<?> entityData;
    public ILayerState layerState;
    public INodeState currentNode;

    @Override
    public EntityData<?> getEntityData() {
        return this.entityData;
    }

    @Override
    public ILayerState getLayerState() {
        return this.layerState;
    }

    @Override
    public INodeState getCurrentNode() {
        return this.currentNode;
    }

    @Override
    public void setCurrentNode(INodeState node) {
        this.currentNode = node;
    }
}

