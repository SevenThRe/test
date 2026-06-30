/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 */
package blockbuster.components.expiration;

import blockbuster.components.IComponentParticleUpdate;
import blockbuster.components.expiration.BedrockComponentExpireBlocks;
import blockbuster.emitter.BedrockEmitter;
import blockbuster.emitter.BedrockParticle;
import net.minecraft.block.Block;

public class BedrockComponentExpireInBlocks
extends BedrockComponentExpireBlocks
implements IComponentParticleUpdate {
    @Override
    public void update(BedrockEmitter emitter, BedrockParticle particle) {
        if (particle.dead || emitter.world == null) {
            return;
        }
        Block current = this.getBlock(emitter, particle);
        for (Block block : this.blocks) {
            if (block != current) continue;
            particle.dead = true;
            return;
        }
    }
}

