/*
 * Decompiled with CFR 0.152.
 */
package goblinbob.mobends.core.kumo.state.condition;

import goblinbob.mobends.core.kumo.state.condition.ITriggerCondition;
import goblinbob.mobends.core.kumo.state.template.MalformedKumoTemplateException;
import goblinbob.mobends.core.kumo.state.template.TriggerConditionTemplate;

@FunctionalInterface
public interface ITriggerConditionFactory<C extends ITriggerCondition, T extends TriggerConditionTemplate> {
    public C createTriggerCondition(T var1) throws MalformedKumoTemplateException;
}

