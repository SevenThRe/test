/*
 * Decompiled with CFR 0.152.
 */
package blockbuster.components;

import blockbuster.components.IComponentBase;
import blockbuster.emitter.BedrockEmitter;
import blockbuster.emitter.BedrockParticle;

public interface IComponentParticleInitialize
extends IComponentBase {
    public void apply(BedrockEmitter var1, BedrockParticle var2);
}

