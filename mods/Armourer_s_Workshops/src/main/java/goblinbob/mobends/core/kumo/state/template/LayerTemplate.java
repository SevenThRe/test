/*
 * Decompiled with CFR 0.152.
 */
package goblinbob.mobends.core.kumo.state.template;

import goblinbob.mobends.core.kumo.state.IKumoValidationContext;
import goblinbob.mobends.core.kumo.state.LayerType;
import goblinbob.mobends.core.kumo.state.template.MalformedKumoTemplateException;

public class LayerTemplate {
    private LayerType type;

    public LayerType getLayerType() {
        return this.type;
    }

    public void validate(IKumoValidationContext context) throws MalformedKumoTemplateException {
    }
}

