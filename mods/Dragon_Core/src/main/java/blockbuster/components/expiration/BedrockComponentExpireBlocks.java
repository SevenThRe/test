/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonArray
 *  com.google.gson.JsonElement
 *  javax.vecmath.Vector3d
 *  net.minecraft.block.Block
 *  net.minecraft.init.Blocks
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.BlockPos$MutableBlockPos
 *  net.minecraftforge.fml.common.registry.ForgeRegistries
 *  net.minecraftforge.registries.IForgeRegistryEntry
 */
package blockbuster.components.expiration;

import blockbuster.components.BedrockComponentBase;
import blockbuster.emitter.BedrockEmitter;
import blockbuster.emitter.BedrockParticle;
import blockbuster.math.molang.MolangException;
import blockbuster.math.molang.MolangParser;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import java.util.ArrayList;
import java.util.List;
import javax.vecmath.Vector3d;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistryEntry;

public abstract class BedrockComponentExpireBlocks
extends BedrockComponentBase {
    public List<Block> blocks = new ArrayList<Block>();
    private BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();

    @Override
    public BedrockComponentBase fromJson(JsonElement element, MolangParser parser) throws MolangException {
        if (element.isJsonArray()) {
            for (JsonElement value : element.getAsJsonArray()) {
                ResourceLocation location = new ResourceLocation(value.getAsString());
                Block block = (Block)ForgeRegistries.BLOCKS.getValue(location);
                if (block == null) continue;
                this.blocks.add(block);
            }
        }
        return super.fromJson(element, parser);
    }

    @Override
    public JsonElement toJson() {
        JsonArray array = new JsonArray();
        for (Block block : this.blocks) {
            ResourceLocation rl2 = ForgeRegistries.BLOCKS.getKey((IForgeRegistryEntry)block);
            if (rl2 == null) continue;
            array.add(rl2.toString());
        }
        return array;
    }

    public Block getBlock(BedrockEmitter emitter, BedrockParticle particle) {
        if (emitter.world == null) {
            return Blocks.AIR;
        }
        Vector3d position = particle.getGlobalPosition(emitter);
        this.pos.setPos(position.getX(), position.getY(), position.getZ());
        return emitter.world.getBlockState((BlockPos)this.pos).getBlock();
    }
}

