/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package goblinbob.mobends.core.kumo.state.condition;

import goblinbob.mobends.core.kumo.state.INodeState;
import goblinbob.mobends.core.kumo.state.condition.AndCondition;
import goblinbob.mobends.core.kumo.state.condition.EquipmentNameCondition;
import goblinbob.mobends.core.kumo.state.condition.ITriggerCondition;
import goblinbob.mobends.core.kumo.state.condition.ITriggerConditionFactory;
import goblinbob.mobends.core.kumo.state.condition.NotCondition;
import goblinbob.mobends.core.kumo.state.condition.OrCondition;
import goblinbob.mobends.core.kumo.state.condition.StateCondition;
import goblinbob.mobends.core.kumo.state.condition.TicksPassedCondition;
import goblinbob.mobends.core.kumo.state.template.MalformedKumoTemplateException;
import goblinbob.mobends.core.kumo.state.template.TriggerConditionTemplate;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nullable;

public class TriggerConditionRegistry {
    public static final TriggerConditionRegistry instance = new TriggerConditionRegistry();
    private Map<String, RegistryEntry<?>> registry = new HashMap();
    private Map<String, ITriggerCondition> pureRegistry = new HashMap<String, ITriggerCondition>();

    private TriggerConditionRegistry() {
        this.register("core:or", OrCondition::new, OrCondition.Template.class);
        this.register("core:and", AndCondition::new, AndCondition.Template.class);
        this.register("core:not", NotCondition::new, NotCondition.Template.class);
        this.register("core:state", StateCondition::new, StateCondition.Template.class);
        this.register("core:ticks_passed", TicksPassedCondition::new, TicksPassedCondition.Template.class);
        this.register("core:equipment_name", EquipmentNameCondition::new, EquipmentNameCondition.Template.class);
        this.register("core:animation_finished", context -> {
            INodeState node = context.getCurrentNode();
            if (node != null) {
                return node.isAnimationFinished();
            }
            return false;
        });
    }

    public <T extends TriggerConditionTemplate> void register(String key, ITriggerConditionFactory<?, T> factory, Class<T> templateType) {
        this.registry.put(key, new RegistryEntry<T>(factory, templateType));
    }

    public void register(String key, ITriggerCondition condition) {
        this.pureRegistry.put(key, condition);
    }

    @Nullable
    public Type getTemplateClass(String key) {
        if (this.registry.containsKey(key)) {
            return this.registry.get((Object)key).templateType;
        }
        if (this.pureRegistry.containsKey(key)) {
            return TriggerConditionTemplate.class;
        }
        return null;
    }

    public <T extends TriggerConditionTemplate> ITriggerCondition createFromTemplate(T template) throws MalformedKumoTemplateException {
        String type = template.getType();
        if (type == null) {
            throw new MalformedKumoTemplateException("No type was specified for trigger condition.");
        }
        if (this.registry.containsKey(type)) {
            RegistryEntry<?> entry = this.registry.get(type);
            return this.createFromTemplate(entry, template);
        }
        if (this.pureRegistry.containsKey(type)) {
            return this.pureRegistry.get(type);
        }
        throw new MalformedKumoTemplateException(String.format("A non-existent trigger condition type was specified: %s", type));
    }

    private <T extends TriggerConditionTemplate> ITriggerCondition createFromTemplate(RegistryEntry<T> entry, T template) throws MalformedKumoTemplateException {
        if (!entry.templateType.equals(template.getClass())) {
            throw new MalformedKumoTemplateException(String.format("The trigger condition registry holds a wrong entry for '%s'", template.getType()));
        }
        return entry.factory.createTriggerCondition(template);
    }

    private static class RegistryEntry<T extends TriggerConditionTemplate> {
        public ITriggerConditionFactory<?, T> factory;
        public Class<T> templateType;

        RegistryEntry(ITriggerConditionFactory<?, T> factory, Class<T> templateType) {
            this.factory = factory;
            this.templateType = templateType;
        }
    }
}

