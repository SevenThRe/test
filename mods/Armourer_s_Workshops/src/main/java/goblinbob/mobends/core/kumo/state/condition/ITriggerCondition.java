/*
 * Decompiled with CFR 0.152.
 */
package goblinbob.mobends.core.kumo.state.condition;

import goblinbob.mobends.core.kumo.state.condition.ITriggerConditionContext;
import goblinbob.mobends.core.kumo.state.template.MalformedKumoTemplateException;

@FunctionalInterface
public interface ITriggerCondition {
    default public void onNodeStarted(ITriggerConditionContext context) {
    }

    public boolean isConditionMet(ITriggerConditionContext var1) throws MalformedKumoTemplateException;
}

