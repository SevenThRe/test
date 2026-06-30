/*
 * Decompiled with CFR 0.152.
 */
package blockbuster.components.shape;

import blockbuster.components.shape.BedrockComponentShapeBase;
import blockbuster.emitter.BedrockEmitter;
import blockbuster.emitter.BedrockParticle;

public class BedrockComponentShapeEntityAABB
extends BedrockComponentShapeBase {
    @Override
    public void apply(BedrockEmitter emitter, BedrockParticle particle) {
        float centerX = (float)this.offset[0].get();
        float centerY = (float)this.offset[1].get();
        float centerZ = (float)this.offset[2].get();
        float w2 = 0.0f;
        float h2 = 0.0f;
        float d2 = 0.0f;
        if (emitter.target != null) {
            w2 = emitter.target.width;
            h2 = emitter.target.height;
            d2 = emitter.target.width;
        }
        particle.position.x = centerX + ((float)Math.random() - 0.5f) * w2;
        particle.position.y = centerY + ((float)Math.random() - 0.5f) * h2;
        particle.position.z = centerZ + ((float)Math.random() - 0.5f) * d2;
        if (this.surface) {
            int roll = (int)(Math.random() * 6.0 * 100.0) % 6;
            if (roll == 0) {
                particle.position.x = centerX + w2 / 2.0f;
            } else if (roll == 1) {
                particle.position.x = centerX - w2 / 2.0f;
            } else if (roll == 2) {
                particle.position.y = centerY + h2 / 2.0f;
            } else if (roll == 3) {
                particle.position.y = centerY - h2 / 2.0f;
            } else if (roll == 4) {
                particle.position.z = centerZ + d2 / 2.0f;
            } else if (roll == 5) {
                particle.position.z = centerZ - d2 / 2.0f;
            }
        }
        this.direction.applyDirection(particle, centerX, centerY, centerZ);
    }
}

