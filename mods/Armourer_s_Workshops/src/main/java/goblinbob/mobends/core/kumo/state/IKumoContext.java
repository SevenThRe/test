/*
 * Decompiled with CFR 0.152.
 */
package goblinbob.mobends.core.kumo.state;

import goblinbob.mobends.core.kumo.state.INodeState;
import goblinbob.mobends.core.kumo.state.condition.ITriggerConditionContext;

public interface IKumoContext
extends ITriggerConditionContext {
    public void setCurrentNode(INodeState var1);
}

