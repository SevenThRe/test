/*
 * Decompiled with CFR 0.152.
 */
package blockbuster.components;

import blockbuster.components.IComponentBase;
import blockbuster.emitter.BedrockEmitter;
import blockbuster.emitter.BedrockParticle;

public interface IComponentParticleUpdate
extends IComponentBase {
    public void update(BedrockEmitter var1, BedrockParticle var2);
}

