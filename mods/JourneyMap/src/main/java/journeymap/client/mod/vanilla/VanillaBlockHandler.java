/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ListMultimap
 *  com.google.common.collect.MultimapBuilder$ListMultimapBuilder
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockBeacon
 *  net.minecraft.block.BlockBush
 *  net.minecraft.block.BlockCrops
 *  net.minecraft.block.BlockDoublePlant
 *  net.minecraft.block.BlockDoublePlant$EnumBlockHalf
 *  net.minecraft.block.BlockFence
 *  net.minecraft.block.BlockFenceGate
 *  net.minecraft.block.BlockFlower
 *  net.minecraft.block.BlockFlowerPot
 *  net.minecraft.block.BlockGlowstone
 *  net.minecraft.block.BlockGrass
 *  net.minecraft.block.BlockLeaves
 *  net.minecraft.block.BlockLog
 *  net.minecraft.block.BlockRailBase
 *  net.minecraft.block.BlockRedstoneWire
 *  net.minecraft.block.BlockSeaLantern
 *  net.minecraft.block.BlockTorch
 *  net.minecraft.block.BlockVine
 *  net.minecraft.block.material.Material
 *  net.minecraft.block.state.IBlockState
 *  net.minecraft.init.Blocks
 *  net.minecraft.util.EnumBlockRenderType
 *  net.minecraftforge.common.IPlantable
 *  net.minecraftforge.fluids.IFluidBlock
 */
package journeymap.client.mod.vanilla;

import com.google.common.collect.ListMultimap;
import com.google.common.collect.MultimapBuilder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import journeymap.client.mod.IModBlockHandler;
import journeymap.client.mod.vanilla.FlowerBlockProxy;
import journeymap.client.model.BlockFlag;
import journeymap.client.model.BlockMD;
import journeymap.client.properties.CoreProperties;
import journeymap.common.Journeymap;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBeacon;
import net.minecraft.block.BlockBush;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.BlockDoublePlant;
import net.minecraft.block.BlockFence;
import net.minecraft.block.BlockFenceGate;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.BlockFlowerPot;
import net.minecraft.block.BlockGlowstone;
import net.minecraft.block.BlockGrass;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockLog;
import net.minecraft.block.BlockRailBase;
import net.minecraft.block.BlockRedstoneWire;
import net.minecraft.block.BlockSeaLantern;
import net.minecraft.block.BlockTorch;
import net.minecraft.block.BlockVine;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.fluids.IFluidBlock;

public final class VanillaBlockHandler
implements IModBlockHandler {
    ListMultimap<Material, BlockFlag> materialFlags = MultimapBuilder.ListMultimapBuilder.linkedHashKeys().arrayListValues().build();
    ListMultimap<Class<?>, BlockFlag> blockClassFlags = MultimapBuilder.ListMultimapBuilder.linkedHashKeys().arrayListValues().build();
    ListMultimap<Block, BlockFlag> blockFlags = MultimapBuilder.ListMultimapBuilder.linkedHashKeys().arrayListValues().build();
    HashMap<Material, Float> materialAlphas = new HashMap();
    HashMap<Block, Float> blockAlphas = new HashMap();
    HashMap<Class<?>, Float> blockClassAlphas = new HashMap();
    private boolean mapPlants;
    private boolean mapPlantShadows;
    private boolean mapCrops;

    public VanillaBlockHandler() {
        this.preInitialize();
    }

    private void preInitialize() {
        CoreProperties coreProperties = Journeymap.getClient().getCoreProperties();
        this.mapPlants = coreProperties.mapPlants.get();
        this.mapCrops = coreProperties.mapCrops.get();
        this.mapPlantShadows = coreProperties.mapPlantShadows.get();
        this.setFlags(Material.field_175972_I, BlockFlag.Ignore);
        this.setFlags(Material.field_151579_a, BlockFlag.Ignore);
        this.setFlags(Material.field_151592_s, Float.valueOf(0.4f), BlockFlag.Transparency);
        this.setFlags(Material.field_151577_b, BlockFlag.Grass);
        if (coreProperties.caveIgnoreGlass.get().booleanValue()) {
            this.setFlags(Material.field_151592_s, BlockFlag.OpenToSky);
        }
        this.setFlags(Material.field_151587_i, Float.valueOf(1.0f), BlockFlag.NoShadow);
        this.setFlags(Material.field_151586_h, Float.valueOf(0.25f), BlockFlag.Water, BlockFlag.NoShadow);
        this.materialAlphas.put(Material.field_151588_w, Float.valueOf(0.8f));
        this.materialAlphas.put(Material.field_151598_x, Float.valueOf(0.8f));
        this.setFlags(Blocks.field_150411_aY, Float.valueOf(0.4f), BlockFlag.Transparency);
        this.setFlags((Block)Blocks.field_150480_ab, BlockFlag.NoShadow);
        this.setFlags(Blocks.field_150468_ap, BlockFlag.OpenToSky);
        this.setFlags(Blocks.field_150431_aC, BlockFlag.NoTopo, BlockFlag.NoShadow);
        this.setFlags(Blocks.field_150473_bD, BlockFlag.Ignore);
        this.setFlags((Block)Blocks.field_150479_bC, BlockFlag.Ignore);
        this.setFlags(Blocks.field_150321_G, BlockFlag.OpenToSky, BlockFlag.NoShadow);
        this.setFlags(BlockBush.class, BlockFlag.Plant);
        this.setFlags(BlockFence.class, Float.valueOf(0.4f), BlockFlag.Transparency);
        this.setFlags(BlockFenceGate.class, Float.valueOf(0.4f), BlockFlag.Transparency);
        this.setFlags(BlockGrass.class, BlockFlag.Grass);
        this.setFlags(BlockLeaves.class, BlockFlag.OpenToSky, BlockFlag.Foliage, BlockFlag.NoTopo);
        this.setFlags(BlockLog.class, BlockFlag.OpenToSky, BlockFlag.NoTopo);
        this.setFlags(BlockRailBase.class, BlockFlag.NoShadow, BlockFlag.NoTopo);
        this.setFlags(BlockRedstoneWire.class, BlockFlag.Ignore);
        this.setFlags(BlockTorch.class, BlockFlag.Ignore);
        this.setFlags(BlockVine.class, Float.valueOf(0.2f), BlockFlag.OpenToSky, BlockFlag.Foliage, BlockFlag.NoShadow);
        this.setFlags(IPlantable.class, BlockFlag.Plant, BlockFlag.NoTopo);
    }

    @Override
    public void initialize(BlockMD blockMD) {
        Block block = blockMD.getBlockState().func_177230_c();
        Material material = blockMD.getBlockState().func_185904_a();
        IBlockState blockState = blockMD.getBlockState();
        if (blockState.func_185901_i() == EnumBlockRenderType.INVISIBLE) {
            blockMD.addFlags(BlockFlag.Ignore);
            return;
        }
        blockMD.addFlags(this.materialFlags.get((Object)material));
        Float alpha = this.materialAlphas.get(material);
        if (alpha != null) {
            blockMD.setAlpha(alpha.floatValue());
        }
        if (this.blockFlags.containsKey((Object)block)) {
            blockMD.addFlags(this.blockFlags.get((Object)block));
        }
        if ((alpha = this.blockAlphas.get(block)) != null) {
            blockMD.setAlpha(alpha.floatValue());
        }
        for (Class parentClass : this.blockClassFlags.keys()) {
            if (!parentClass.isAssignableFrom(block.getClass())) continue;
            blockMD.addFlags(this.blockClassFlags.get((Object)parentClass));
            alpha = this.blockClassAlphas.get(parentClass);
            if (alpha == null) break;
            blockMD.setAlpha(alpha.floatValue());
            break;
        }
        if (block instanceof IFluidBlock) {
            blockMD.addFlags(BlockFlag.Fluid, BlockFlag.NoShadow);
            blockMD.setAlpha(0.7f);
        }
        if (material == Material.field_151592_s && (block instanceof BlockGlowstone || block instanceof BlockSeaLantern || block instanceof BlockBeacon)) {
            blockMD.removeFlags(BlockFlag.OpenToSky, BlockFlag.Transparency);
            blockMD.setAlpha(1.0f);
        }
        if (block instanceof BlockBush && blockMD.getBlockState().func_177228_b().get((Object)BlockDoublePlant.field_176492_b) == BlockDoublePlant.EnumBlockHalf.UPPER) {
            blockMD.addFlags(BlockFlag.Ignore);
        }
        if (block instanceof BlockCrops) {
            blockMD.addFlags(BlockFlag.Crop);
        }
        if (block instanceof BlockFlower || block instanceof BlockFlowerPot) {
            blockMD.setBlockColorProxy(FlowerBlockProxy.INSTANCE);
        }
        if (blockMD.isVanillaBlock()) {
            return;
        }
        String uid = blockMD.getBlockId();
        if (uid.toLowerCase().contains("torch")) {
            blockMD.addFlags(BlockFlag.Ignore);
            return;
        }
    }

    public void postInitialize(BlockMD blockMD) {
        if (blockMD.hasFlag(BlockFlag.Crop)) {
            blockMD.removeFlags(BlockFlag.Plant);
        }
        if (blockMD.hasAnyFlag(BlockMD.FlagsPlantAndCrop)) {
            if (!this.mapPlants && blockMD.hasFlag(BlockFlag.Plant) || !this.mapCrops && blockMD.hasFlag(BlockFlag.Crop)) {
                blockMD.addFlags(BlockFlag.Ignore);
            } else if (!this.mapPlantShadows) {
                blockMD.addFlags(BlockFlag.NoShadow);
            }
        }
        if (blockMD.isIgnore()) {
            blockMD.removeFlags(BlockMD.FlagsNormal);
        }
    }

    private void setFlags(Material material, BlockFlag ... flags) {
        this.materialFlags.putAll((Object)material, new ArrayList<BlockFlag>(Arrays.asList(flags)));
    }

    private void setFlags(Material material, Float alpha, BlockFlag ... flags) {
        this.materialAlphas.put(material, alpha);
        this.setFlags(material, flags);
    }

    private void setFlags(Class parentClass, BlockFlag ... flags) {
        this.blockClassFlags.putAll((Object)parentClass, new ArrayList<BlockFlag>(Arrays.asList(flags)));
    }

    private void setFlags(Class parentClass, Float alpha, BlockFlag ... flags) {
        this.blockClassAlphas.put(parentClass, alpha);
        this.setFlags(parentClass, flags);
    }

    private void setFlags(Block block, BlockFlag ... flags) {
        this.blockFlags.putAll((Object)block, new ArrayList<BlockFlag>(Arrays.asList(flags)));
    }

    private void setFlags(Block block, Float alpha, BlockFlag ... flags) {
        this.blockAlphas.put(block, alpha);
        this.setFlags(block, flags);
    }
}

