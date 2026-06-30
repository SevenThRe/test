/*
 * Decompiled with CFR 0.152.
 */
package goblinbob.mobends.core.kumo.state.condition;

import goblinbob.mobends.core.kumo.state.condition.ITriggerCondition;
import goblinbob.mobends.core.kumo.state.condition.ITriggerConditionContext;
import goblinbob.mobends.core.kumo.state.condition.TriggerConditionRegistry;
import goblinbob.mobends.core.kumo.state.template.MalformedKumoTemplateException;
import goblinbob.mobends.core.kumo.state.template.TriggerConditionTemplate;
import java.util.LinkedList;
import java.util.List;

public class AndCondition
implements ITriggerCondition {
    private List<ITriggerCondition> conditions = new LinkedList<ITriggerCondition>();

    public AndCondition(Template template) throws MalformedKumoTemplateException {
        for (TriggerConditionTemplate conditionTemplate : template.conditions) {
            if (conditionTemplate == null) continue;
            this.conditions.add(TriggerConditionRegistry.instance.createFromTemplate(conditionTemplate));
        }
    }

    @Override
    public boolean isConditionMet(ITriggerConditionContext context) throws MalformedKumoTemplateException {
        for (ITriggerCondition condition : this.conditions) {
            if (condition.isConditionMet(context)) continue;
            return false;
        }
        return true;
    }

    public static class Template
    extends TriggerConditionTemplate {
        public List<TriggerConditionTemplate> conditions;
    }
}

