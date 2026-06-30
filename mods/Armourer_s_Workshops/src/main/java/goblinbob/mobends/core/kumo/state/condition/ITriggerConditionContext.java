/*
 * Decompiled with CFR 0.152.
 */
package goblinbob.mobends.core.kumo.state.condition;

import goblinbob.mobends.core.data.EntityData;
import goblinbob.mobends.core.kumo.state.ILayerState;
import goblinbob.mobends.core.kumo.state.INodeState;

public interface ITriggerConditionContext {
    public EntityData<?> getEntityData();

    public ILayerState getLayerState();

    public INodeState getCurrentNode();
}

