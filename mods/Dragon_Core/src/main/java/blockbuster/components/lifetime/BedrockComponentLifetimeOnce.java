/*
 * Decompiled with CFR 0.152.
 */
package blockbuster.components.lifetime;

import blockbuster.components.lifetime.BedrockComponentLifetime;
import blockbuster.emitter.BedrockEmitter;

public class BedrockComponentLifetimeOnce
extends BedrockComponentLifetime {
    @Override
    public void update(BedrockEmitter emitter) {
        double time = this.activeTime.get();
        emitter.lifetime = (int)(time * 20.0);
        if (emitter.getAge() >= time) {
            emitter.stop();
            emitter.stopWithOnce();
        }
    }
}

