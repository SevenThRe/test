/*
 * Decompiled with CFR 0.152.
 */
package goblinbob.mobends.core.kumo.state;

import goblinbob.mobends.core.Core;
import goblinbob.mobends.core.kumo.state.DriverLayerState;
import goblinbob.mobends.core.kumo.state.IKumoContext;
import goblinbob.mobends.core.kumo.state.IKumoInstancingContext;
import goblinbob.mobends.core.kumo.state.keyframe.KeyframeLayerState;
import goblinbob.mobends.core.kumo.state.template.DriverLayerTemplate;
import goblinbob.mobends.core.kumo.state.template.LayerTemplate;
import goblinbob.mobends.core.kumo.state.template.MalformedKumoTemplateException;
import goblinbob.mobends.core.kumo.state.template.keyframe.KeyframeLayerTemplate;

public interface ILayerState {
    public void start(IKumoContext var1);

    public void update(IKumoContext var1, float var2) throws MalformedKumoTemplateException;

    public static ILayerState createFromTemplate(IKumoInstancingContext context, LayerTemplate template) throws MalformedKumoTemplateException {
        switch (template.getLayerType()) {
            case KEYFRAME: {
                return KeyframeLayerState.createFromTemplate(context, (KeyframeLayerTemplate)template);
            }
            case DRIVER: {
                return new DriverLayerState((DriverLayerTemplate)template);
            }
        }
        Core.LOG.warning(String.format("Unknown layer type was specified in state template: %d", template.getLayerType().ordinal()));
        return null;
    }
}

