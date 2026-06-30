/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Joiner
 *  com.google.common.base.Objects
 *  com.google.common.base.Strings
 *  com.google.common.cache.CacheLoader
 *  com.google.common.collect.ComparisonChain
 *  com.google.common.collect.ImmutableCollection
 *  com.google.common.collect.Ordering
 *  javax.annotation.Nonnull
 *  javax.annotation.Nullable
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockAir
 *  net.minecraft.block.state.IBlockState
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.EnumBlockRenderType
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.text.translation.I18n
 *  net.minecraftforge.common.property.IExtendedBlockState
 *  net.minecraftforge.registries.GameData
 *  org.apache.logging.log4j.Logger
 */
package journeymap.client.model;

import com.google.common.base.Joiner;
import com.google.common.base.Objects;
import com.google.common.base.Strings;
import com.google.common.collect.ComparisonChain;
import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.Ordering;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.EnumSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import journeymap.client.data.DataCache;
import journeymap.client.mod.IBlockColorProxy;
import journeymap.client.mod.IBlockSpritesProxy;
import journeymap.client.mod.ModBlockDelegate;
import journeymap.client.model.BlockFlag;
import journeymap.client.model.ChunkMD;
import journeymap.client.world.JmBlockAccess;
import journeymap.common.Journeymap;
import journeymap.common.log.LogFormatter;
import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.common.property.IExtendedBlockState;
import net.minecraftforge.registries.GameData;
import org.apache.logging.log4j.Logger;

public class BlockMD
implements Comparable<BlockMD> {
    public static final EnumSet<BlockFlag> FlagsPlantAndCrop = EnumSet.of(BlockFlag.Plant, BlockFlag.Crop);
    public static final EnumSet<BlockFlag> FlagsNormal = EnumSet.complementOf(EnumSet.of(BlockFlag.Error, BlockFlag.Ignore));
    public static final BlockMD AIRBLOCK = new BlockMD(Blocks.field_150350_a.func_176223_P(), "minecraft:air", "0", "Air", Float.valueOf(0.0f), EnumSet.of(BlockFlag.Ignore), false);
    public static final BlockMD VOIDBLOCK = new BlockMD(Blocks.field_150350_a.func_176223_P(), "journeymap:void", "0", "Void", Float.valueOf(0.0f), EnumSet.of(BlockFlag.Ignore), false);
    private static Logger LOGGER = Journeymap.getLogger();
    private final IBlockState blockState;
    private final String blockId;
    private final String blockStateId;
    private final String name;
    private EnumSet<BlockFlag> flags;
    private Integer color;
    private float alpha;
    private IBlockSpritesProxy blockSpritesProxy;
    private IBlockColorProxy blockColorProxy;
    private boolean noShadow;
    private boolean isIgnore;
    private boolean isWater;
    private boolean isLava;
    private boolean isFluid;
    private boolean isFire;
    private boolean isIce;
    private boolean isFoliage;
    private boolean isGrass;
    private boolean isPlantOrCrop;
    private boolean isError;

    private BlockMD(@Nonnull IBlockState blockState) {
        this(blockState, BlockMD.getBlockId(blockState), BlockMD.getBlockStateId(blockState), BlockMD.getBlockName(blockState));
    }

    private BlockMD(@Nonnull IBlockState blockState, String blockId, String blockStateId, String name) {
        this(blockState, blockId, blockStateId, name, Float.valueOf(1.0f), EnumSet.noneOf(BlockFlag.class), true);
    }

    private BlockMD(@Nonnull IBlockState blockState, String blockId, String blockStateId, String name, Float alpha, EnumSet<BlockFlag> flags, boolean initDelegates) {
        this.blockState = blockState;
        this.blockId = blockId;
        this.blockStateId = blockStateId;
        this.name = name;
        this.alpha = alpha.floatValue();
        this.flags = flags;
        if (initDelegates) {
            ModBlockDelegate.INSTANCE.initialize(this);
        }
        this.updateProperties();
    }

    public Set<BlockMD> getValidStateMDs() {
        return this.getBlock().func_176194_O().func_177619_a().stream().map(BlockMD::get).collect(Collectors.toSet());
    }

    private void updateProperties() {
        boolean bl = this.isIgnore = this.blockState == null || this.hasFlag(BlockFlag.Ignore) || this.blockState.func_177230_c() instanceof BlockAir || this.blockState.func_185901_i() == EnumBlockRenderType.INVISIBLE;
        if (this.isIgnore) {
            this.color = -1;
            this.setAlpha(0.0f);
            this.flags.add(BlockFlag.Ignore);
            this.flags.add(BlockFlag.OpenToSky);
            this.flags.add(BlockFlag.NoShadow);
        }
        if (this.blockState != null) {
            Block block = this.blockState.func_177230_c();
            this.isLava = block == Blocks.field_150353_l || block == Blocks.field_150356_k;
            this.isIce = block == Blocks.field_150432_aD;
            this.isFire = block == Blocks.field_150480_ab;
        }
        this.isFluid = this.hasFlag(BlockFlag.Fluid);
        this.isWater = this.hasFlag(BlockFlag.Water);
        this.noShadow = this.hasFlag(BlockFlag.NoShadow);
        this.isFoliage = this.hasFlag(BlockFlag.Foliage);
        this.isGrass = this.hasFlag(BlockFlag.Grass);
        this.isPlantOrCrop = this.hasAnyFlag(FlagsPlantAndCrop);
        this.isError = this.hasFlag(BlockFlag.Error);
    }

    public Block getBlock() {
        return this.blockState.func_177230_c();
    }

    public static void reset() {
        DataCache.INSTANCE.resetBlockMetadata();
    }

    public static Set<BlockMD> getAll() {
        return StreamSupport.stream(GameData.getBlockStateIDMap().spliterator(), false).map(BlockMD::get).collect(Collectors.toSet());
    }

    public static Set<BlockMD> getAllValid() {
        return BlockMD.getAll().stream().filter(blockMD -> !blockMD.isIgnore() && !blockMD.hasFlag(BlockFlag.Error)).collect(Collectors.toSet());
    }

    public static Set<BlockMD> getAllMinecraft() {
        return StreamSupport.stream(GameData.getBlockStateIDMap().spliterator(), false).filter(blockState1 -> blockState1.func_177230_c().getRegistryName().func_110624_b().equals("minecraft")).map(BlockMD::get).collect(Collectors.toSet());
    }

    public static BlockMD getBlockMDFromChunkLocal(ChunkMD chunkMd, int localX, int y, int localZ) {
        return BlockMD.getBlockMD(chunkMd, chunkMd.getBlockPos(localX, y, localZ));
    }

    public static BlockMD getBlockMD(ChunkMD chunkMd, BlockPos blockPos) {
        try {
            if (blockPos.func_177956_o() >= 0) {
                IBlockState blockState = chunkMd != null && chunkMd.hasChunk() ? chunkMd.getChunk().func_177435_g(blockPos) : JmBlockAccess.INSTANCE.func_180495_p(blockPos);
                return BlockMD.get(blockState);
            }
            return VOIDBLOCK;
        }
        catch (Exception e) {
            LOGGER.error(String.format("Can't get blockId/meta for chunk %s,%s at %s : %s", chunkMd.getChunk().field_76635_g, chunkMd.getChunk().field_76647_h, blockPos, LogFormatter.toString(e)));
            return AIRBLOCK;
        }
    }

    public static BlockMD get(IBlockState blockState) {
        return DataCache.INSTANCE.getBlockMD(blockState);
    }

    public static String getBlockId(BlockMD blockMD) {
        return BlockMD.getBlockId(blockMD.getBlockState());
    }

    public static String getBlockId(IBlockState blockState) {
        return ((ResourceLocation)Block.field_149771_c.func_177774_c((Object)blockState.func_177230_c())).toString();
    }

    public static String getBlockStateId(BlockMD blockMD) {
        return BlockMD.getBlockStateId(blockMD.getBlockState());
    }

    public static String getBlockStateId(IBlockState blockState) {
        ImmutableCollection properties = blockState.func_177228_b().values();
        if (properties.isEmpty()) {
            return Integer.toString(blockState.func_177230_c().func_176201_c(blockState));
        }
        return Joiner.on((String)",").join((Iterable)properties);
    }

    private static String getBlockName(IBlockState blockState) {
        String displayName = null;
        try {
            Block block = blockState.func_177230_c();
            Item item = Item.func_150898_a((Block)block);
            if (item != null) {
                ItemStack idPicked = new ItemStack(item, 1, block.func_176201_c(blockState));
                displayName = I18n.func_74838_a((String)(item.func_77667_c(idPicked) + ".name"));
            }
            if (Strings.isNullOrEmpty(displayName)) {
                displayName = block.func_149732_F();
            }
        }
        catch (Exception e) {
            LOGGER.debug(String.format("Couldn't get display name for %s: %s ", blockState, e));
        }
        if (Strings.isNullOrEmpty((String)displayName) || displayName.contains("tile")) {
            displayName = blockState.func_177230_c().getClass().getSimpleName().replaceAll("Block", "");
        }
        return displayName;
    }

    public static void setAllFlags(Block block, BlockFlag ... flags) {
        BlockMD defaultBlockMD = BlockMD.get(block.func_176223_P());
        for (BlockMD blockMD : defaultBlockMD.getValidStateMDs()) {
            blockMD.addFlags(flags);
        }
        LOGGER.debug(block.func_149739_a() + " flags set: " + flags);
    }

    public boolean hasFlag(BlockFlag checkFlag) {
        return this.flags.contains((Object)checkFlag);
    }

    public boolean hasAnyFlag(EnumSet<BlockFlag> checkFlags) {
        for (BlockFlag flag : checkFlags) {
            if (!this.flags.contains((Object)flag)) continue;
            return true;
        }
        return false;
    }

    public void addFlags(BlockFlag ... addFlags) {
        Collections.addAll(this.flags, addFlags);
        this.updateProperties();
    }

    public void removeFlags(BlockFlag ... removeFlags) {
        for (BlockFlag flag : removeFlags) {
            this.flags.remove((Object)flag);
        }
        this.updateProperties();
    }

    public void removeFlags(Collection<BlockFlag> removeFlags) {
        this.flags.removeAll(removeFlags);
        this.updateProperties();
    }

    public void addFlags(Collection<BlockFlag> addFlags) {
        this.flags.addAll(addFlags);
        this.updateProperties();
    }

    public int getBlockColor(ChunkMD chunkMD, BlockPos blockPos) {
        return this.blockColorProxy.getBlockColor(chunkMD, this, blockPos);
    }

    public int getTextureColor(@Nullable ChunkMD chunkMD, @Nullable BlockPos blockPos) {
        if (this.color == null && !this.isError && this.blockColorProxy != null) {
            this.color = this.blockColorProxy.deriveBlockColor(this, chunkMD, blockPos);
        }
        if (this.color == null) {
            this.color = 0;
        }
        return this.color;
    }

    public int getTextureColor() {
        return this.getTextureColor(null, null);
    }

    public void clearColor() {
        this.color = null;
    }

    public int setColor(int baseColor) {
        this.color = baseColor;
        return baseColor;
    }

    public boolean hasColor() {
        return this.color != null;
    }

    public void setBlockSpritesProxy(IBlockSpritesProxy blockSpritesProxy) {
        this.blockSpritesProxy = blockSpritesProxy;
    }

    public IBlockSpritesProxy getBlockSpritesProxy() {
        return this.blockSpritesProxy;
    }

    public void setBlockColorProxy(IBlockColorProxy blockColorProxy) {
        this.blockColorProxy = blockColorProxy;
    }

    public IBlockColorProxy getBlockColorProxy() {
        return this.blockColorProxy;
    }

    public float getAlpha() {
        return this.alpha;
    }

    public void setAlpha(float alpha) {
        this.alpha = alpha;
        if (alpha < 1.0f) {
            this.flags.add(BlockFlag.Transparency);
        } else {
            this.flags.remove((Object)BlockFlag.Transparency);
        }
    }

    public boolean hasNoShadow() {
        if (this.noShadow) {
            return true;
        }
        return this.isPlantOrCrop && Journeymap.getClient().getCoreProperties().mapPlantShadows.get() == false;
    }

    public IBlockState getBlockState() {
        return this.blockState;
    }

    public boolean hasTransparency() {
        return this.alpha < 1.0f;
    }

    public boolean isIgnore() {
        return this.isIgnore;
    }

    public boolean isIce() {
        return this.isIce;
    }

    public boolean isWater() {
        return this.isWater;
    }

    public boolean isFluid() {
        return this.isFluid;
    }

    public boolean isLava() {
        return this.isLava;
    }

    public boolean isFire() {
        return this.isFire;
    }

    public boolean isFoliage() {
        return this.isFoliage;
    }

    public boolean isGrass() {
        return this.isGrass;
    }

    public String getName() {
        return this.name;
    }

    public String getBlockId() {
        return this.blockId;
    }

    public String getBlockStateId() {
        return this.blockStateId;
    }

    public String getBlockDomain() {
        return this.getBlock().getRegistryName().func_110624_b();
    }

    public EnumSet<BlockFlag> getFlags() {
        return this.flags;
    }

    public boolean isVanillaBlock() {
        return this.getBlockDomain().equals("minecraft");
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BlockMD)) {
            return false;
        }
        BlockMD blockMD = (BlockMD)o;
        return Objects.equal((Object)this.getBlockId(), (Object)blockMD.getBlockId()) && Objects.equal((Object)this.getBlockStateId(), (Object)blockMD.getBlockStateId());
    }

    public int hashCode() {
        return Objects.hashCode((Object[])new Object[]{this.getBlockId(), this.getBlockStateId()});
    }

    public String toString() {
        return String.format("BlockMD [%s] (%s)", this.blockState, Joiner.on((String)",").join(this.flags));
    }

    @Override
    public int compareTo(BlockMD that) {
        Ordering ordering = Ordering.natural().nullsLast();
        return ComparisonChain.start().compare((Object)this.blockId, (Object)that.blockId, (Comparator)ordering).compare((Object)this.blockStateId, (Object)that.blockStateId, (Comparator)ordering).result();
    }

    public static class CacheLoader
    extends com.google.common.cache.CacheLoader<IBlockState, BlockMD> {
        public BlockMD load(@Nonnull IBlockState blockState) throws Exception {
            try {
                IBlockState clean;
                if (blockState instanceof IExtendedBlockState && (clean = ((IExtendedBlockState)blockState).getClean()) != null) {
                    blockState = clean;
                }
                if (blockState == null || blockState.func_185901_i() == EnumBlockRenderType.INVISIBLE) {
                    return AIRBLOCK;
                }
                if (blockState.func_177230_c().getRegistryName() == null) {
                    LOGGER.warn("Unregistered block will be treated like air: " + blockState);
                    return AIRBLOCK;
                }
                return new BlockMD(blockState);
            }
            catch (Exception e) {
                LOGGER.error(String.format("Can't get BlockMD for %s : %s", blockState, LogFormatter.toPartialString(e)));
                return AIRBLOCK;
            }
        }
    }
}

