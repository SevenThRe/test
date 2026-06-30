/*
 * Decompiled with CFR 0.152.
 */
package goblinbob.mobends.core.kumo.state.template.keyframe;

import goblinbob.mobends.core.kumo.state.IKumoValidationContext;
import goblinbob.mobends.core.kumo.state.template.MalformedKumoTemplateException;
import goblinbob.mobends.core.kumo.state.template.keyframe.ConnectionTemplate;
import java.util.List;

public class KeyframeNodeTemplate {
    private String type = "core:standard";
    public List<ConnectionTemplate> connections;

    public String getType() {
        return this.type;
    }

    public void validate(IKumoValidationContext context) throws MalformedKumoTemplateException {
        for (ConnectionTemplate connectionTemplate : this.connections) {
            connectionTemplate.validate(context);
        }
    }
}

