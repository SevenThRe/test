/*
 * Decompiled with CFR 0.152.
 */
package blockbuster.components.shape;

import blockbuster.components.shape.BedrockComponentShapeBase;
import blockbuster.components.shape.ShapeDirection;
import blockbuster.emitter.BedrockEmitter;
import blockbuster.emitter.BedrockParticle;

public class BedrockComponentShapePoint
extends BedrockComponentShapeBase {
    @Override
    public void apply(BedrockEmitter emitter, BedrockParticle particle) {
        particle.position.x = (float)this.offset[0].get();
        particle.position.y = (float)this.offset[1].get();
        particle.position.z = (float)this.offset[2].get();
        if (this.direction instanceof ShapeDirection.Vector) {
            this.direction.applyDirection(particle, particle.position.x, particle.position.y, particle.position.z);
        }
    }
}

