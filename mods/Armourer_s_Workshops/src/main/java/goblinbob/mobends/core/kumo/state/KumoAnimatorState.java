/*
 * Decompiled with CFR 0.152.
 */
package goblinbob.mobends.core.kumo.state;

import goblinbob.mobends.core.data.EntityData;
import goblinbob.mobends.core.kumo.state.IKumoInstancingContext;
import goblinbob.mobends.core.kumo.state.ILayerState;
import goblinbob.mobends.core.kumo.state.KumoContext;
import goblinbob.mobends.core.kumo.state.template.AnimatorTemplate;
import goblinbob.mobends.core.kumo.state.template.LayerTemplate;
import goblinbob.mobends.core.kumo.state.template.MalformedKumoTemplateException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class KumoAnimatorState<D extends EntityData<?>> {
    private List<ILayerState> layerStates = new ArrayList<ILayerState>();
    private KumoContext context = new KumoContext();
    private boolean started = false;

    public KumoAnimatorState(AnimatorTemplate animatorTemplate, IKumoInstancingContext dataProvider) throws MalformedKumoTemplateException {
        if (animatorTemplate.layers == null) {
            throw new MalformedKumoTemplateException("No layers were specified");
        }
        for (LayerTemplate template : animatorTemplate.layers) {
            this.layerStates.add(ILayerState.createFromTemplate(dataProvider, template));
        }
    }

    public void update(D entityData, float deltaTime) throws MalformedKumoTemplateException {
        this.context.entityData = entityData;
        Iterator<ILayerState> iterator = this.layerStates.iterator();
        while (iterator.hasNext()) {
            ILayerState layer;
            this.context.layerState = layer = iterator.next();
            if (!this.started) {
                layer.start(this.context);
            }
            layer.update(this.context, deltaTime);
        }
        this.started = true;
    }
}

