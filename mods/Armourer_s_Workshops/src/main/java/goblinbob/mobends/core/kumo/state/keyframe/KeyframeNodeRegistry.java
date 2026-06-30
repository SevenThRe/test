/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package goblinbob.mobends.core.kumo.state.keyframe;

import goblinbob.mobends.core.kumo.state.IKumoInstancingContext;
import goblinbob.mobends.core.kumo.state.INodeState;
import goblinbob.mobends.core.kumo.state.keyframe.IKeyframeNodeFactory;
import goblinbob.mobends.core.kumo.state.keyframe.MovementKeyframeNode;
import goblinbob.mobends.core.kumo.state.keyframe.StandardKeyframeNode;
import goblinbob.mobends.core.kumo.state.template.MalformedKumoTemplateException;
import goblinbob.mobends.core.kumo.state.template.keyframe.KeyframeNodeTemplate;
import goblinbob.mobends.core.kumo.state.template.keyframe.MovementKeyframeNodeTemplate;
import goblinbob.mobends.core.kumo.state.template.keyframe.StandardKeyframeNodeTemplate;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nullable;

public class KeyframeNodeRegistry {
    public static final KeyframeNodeRegistry INSTANCE = new KeyframeNodeRegistry();
    private Map<String, RegistryEntry<?>> registry = new HashMap();

    private KeyframeNodeRegistry() {
        this.register("core:standard", StandardKeyframeNode::new, StandardKeyframeNodeTemplate.class);
        this.register("core:movement", MovementKeyframeNode::new, MovementKeyframeNodeTemplate.class);
    }

    public <T extends KeyframeNodeTemplate> void register(String key, IKeyframeNodeFactory<?, T> factory, Class<T> templateType) {
        this.registry.put(key, new RegistryEntry<T>(factory, templateType));
    }

    @Nullable
    public Type getTemplateClass(String key) {
        if (this.registry.containsKey(key)) {
            return this.registry.get((Object)key).nodeType;
        }
        return null;
    }

    public <T extends KeyframeNodeTemplate> INodeState createFromTemplate(IKumoInstancingContext context, T template) throws MalformedKumoTemplateException {
        String type = template.getType();
        if (type == null) {
            throw new MalformedKumoTemplateException("No type was specified for KeyframeNode.");
        }
        if (this.registry.containsKey(type)) {
            RegistryEntry<?> entry = this.registry.get(type);
            return this.createFromTemplate(context, entry, template);
        }
        throw new MalformedKumoTemplateException(String.format("A non-existent KeyframeNode type was specified: %s", type));
    }

    private <T extends KeyframeNodeTemplate> INodeState createFromTemplate(IKumoInstancingContext context, RegistryEntry<T> entry, T template) throws MalformedKumoTemplateException {
        if (!entry.nodeType.equals(template.getClass())) {
            throw new MalformedKumoTemplateException(String.format("The KeyframeNode registry holds a wrong entry for '%s'", template.getType()));
        }
        return entry.factory.createKeyframeNode(context, template);
    }

    private static class RegistryEntry<T extends KeyframeNodeTemplate> {
        public IKeyframeNodeFactory<?, T> factory;
        public Class<T> nodeType;

        RegistryEntry(IKeyframeNodeFactory<?, T> factory, Class<T> nodeType) {
            this.factory = factory;
            this.nodeType = nodeType;
        }
    }
}

