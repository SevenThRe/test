/*
 * Decompiled with CFR 0.152.
 */
package blockbuster.components.appearance;

import blockbuster.components.BedrockComponentBase;
import blockbuster.components.IComponentEmitterInitialize;
import blockbuster.emitter.BedrockEmitter;

public class BedrockComponentAppearanceLighting
extends BedrockComponentBase
implements IComponentEmitterInitialize {
    @Override
    public void apply(BedrockEmitter emitter) {
        emitter.lit = false;
    }

    @Override
    public boolean canBeEmpty() {
        return true;
    }
}

