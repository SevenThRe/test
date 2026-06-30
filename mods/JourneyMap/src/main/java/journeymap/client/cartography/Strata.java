/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.math.BlockPos
 */
package journeymap.client.cartography;

import java.util.Stack;
import journeymap.client.cartography.IChunkRenderer;
import journeymap.client.cartography.Stratum;
import journeymap.client.cartography.color.RGB;
import journeymap.client.log.JMLogger;
import journeymap.client.model.BlockMD;
import journeymap.client.model.ChunkMD;
import journeymap.common.Journeymap;
import net.minecraft.util.math.BlockPos;

public class Strata {
    final String name;
    final int initialPoolSize;
    final int poolGrowthIncrement;
    private final boolean underground;
    private boolean mapCaveLighting;
    private Integer topY;
    private Integer bottomY;
    private Integer topFluidY;
    private Integer bottomFluidY;
    private Integer maxLightLevel;
    private Integer fluidColor;
    private Integer renderDayColor;
    private Integer renderNightColor;
    private Integer renderCaveColor;
    private int lightAttenuation;
    private boolean blocksFound;
    private Stack<Stratum> unusedStack;
    private Stack<Stratum> stack;

    public Strata(String name, int initialPoolSize, int poolGrowthIncrement, boolean underground) {
        this.mapCaveLighting = Journeymap.getClient().getCoreProperties().mapCaveLighting.get();
        this.topY = null;
        this.bottomY = null;
        this.topFluidY = null;
        this.bottomFluidY = null;
        this.maxLightLevel = null;
        this.fluidColor = null;
        this.renderDayColor = null;
        this.renderNightColor = null;
        this.renderCaveColor = null;
        this.lightAttenuation = 0;
        this.blocksFound = false;
        this.unusedStack = new Stack();
        this.stack = new Stack();
        this.name = name;
        this.underground = underground;
        this.initialPoolSize = initialPoolSize;
        this.poolGrowthIncrement = poolGrowthIncrement;
        this.growFreePool(initialPoolSize);
    }

    private Stratum allocate() {
        if (this.unusedStack.isEmpty()) {
            int amount = this.stack.isEmpty() ? this.initialPoolSize : this.poolGrowthIncrement;
            this.growFreePool(amount);
            Journeymap.getLogger().debug(String.format("Grew Strata pool for '%s' by '%s'. Free: %s, Used: %s", this.name, amount, this.unusedStack.size(), this.stack.size()));
        }
        this.stack.push(this.unusedStack.pop());
        return this.stack.peek();
    }

    private void growFreePool(int amount) {
        for (int i = 0; i < amount; ++i) {
            this.unusedStack.push(new Stratum());
        }
    }

    public void reset() {
        this.setTopY(null);
        this.setBottomY(null);
        this.setTopFluidY(null);
        this.setBottomFluidY(null);
        this.setMaxLightLevel(null);
        this.setFluidColor(null);
        this.setRenderDayColor(null);
        this.setRenderNightColor(null);
        this.setRenderCaveColor(null);
        this.setLightAttenuation(0);
        this.setBlocksFound(false);
        this.mapCaveLighting = Journeymap.getClient().getCoreProperties().mapCaveLighting.get();
        while (!this.stack.isEmpty()) {
            this.release(this.stack.peek());
        }
    }

    public void release(Stratum stratum) {
        if (stratum == null) {
            Journeymap.getLogger().warn("Null stratum in pool.");
            return;
        }
        stratum.clear();
        this.unusedStack.add(0, this.stack.pop());
    }

    public Stratum push(ChunkMD chunkMd, BlockMD blockMD, int x, int y, int z) {
        return this.push(chunkMd, blockMD, x, y, z, null);
    }

    public Stratum push(ChunkMD chunkMd, BlockMD blockMD, int localX, int y, int localZ, Integer lightLevel) {
        try {
            Stratum stratum = this.allocate();
            stratum.set(chunkMd, blockMD, localX, y, localZ, lightLevel);
            this.setTopY(this.getTopY() == null ? y : Math.max(this.getTopY(), y));
            this.setBottomY(this.getBottomY() == null ? y : Math.min(this.getBottomY(), y));
            this.setMaxLightLevel(this.getMaxLightLevel() == null ? stratum.getLightLevel() : Math.max(this.getMaxLightLevel(), stratum.getLightLevel()));
            this.setLightAttenuation(this.getLightAttenuation() + stratum.getLightOpacity());
            this.setBlocksFound(true);
            if (blockMD.isWater() || blockMD.isFluid()) {
                this.setTopFluidY(this.getTopFluidY() == null ? y : Math.max(this.getTopFluidY(), y));
                this.setBottomFluidY(this.getBottomFluidY() == null ? y : Math.min(this.getBottomFluidY(), y));
                if (this.getFluidColor() == null) {
                    BlockPos blockPos = chunkMd.getBlockPos(localX, y, localZ);
                    this.setFluidColor(blockMD.getBlockColor(chunkMd, blockPos));
                }
            }
            return stratum;
        }
        catch (ChunkMD.ChunkMissingException e) {
            throw e;
        }
        catch (Throwable t) {
            JMLogger.logOnce("Couldn't push Stratum into stack: " + t.getMessage(), t);
            return null;
        }
    }

    public Stratum nextUp(IChunkRenderer renderer, boolean ignoreMiddleFluid) {
        Stratum stratum = null;
        stratum = this.stack.peek();
        if (stratum.isUninitialized()) {
            throw new IllegalStateException("Stratum wasn't initialized for Strata.nextUp()");
        }
        this.setLightAttenuation(Math.max(0, this.getLightAttenuation() - stratum.getLightOpacity()));
        if (ignoreMiddleFluid && stratum.isFluid() && this.isFluidAbove(stratum) && !this.stack.isEmpty()) {
            this.release(stratum);
            return this.nextUp(renderer, true);
        }
        renderer.setStratumColors(stratum, this.getLightAttenuation(), this.getFluidColor(), this.isFluidAbove(stratum), this.isUnderground(), this.isMapCaveLighting());
        return stratum;
    }

    int depth() {
        return this.stack.isEmpty() ? 0 : this.getTopY() - this.getBottomY() + 1;
    }

    public boolean isEmpty() {
        return this.stack.isEmpty();
    }

    boolean hasFluid() {
        return this.getTopFluidY() != null;
    }

    boolean isFluidAbove(Stratum stratum) {
        return this.getTopFluidY() != null && this.getTopFluidY() > stratum.getY();
    }

    public String toString() {
        return "Strata{name='" + this.name + '\'' + ", initialPoolSize=" + this.initialPoolSize + ", poolGrowthIncrement=" + this.poolGrowthIncrement + ", stack=" + this.stack.size() + ", unusedStack=" + this.unusedStack.size() + ", stack=" + this.stack.size() + ", topY=" + this.getTopY() + ", bottomY=" + this.getBottomY() + ", topFluidY=" + this.getTopFluidY() + ", bottomFluidY=" + this.getBottomFluidY() + ", maxLightLevel=" + this.getMaxLightLevel() + ", fluidColor=" + RGB.toString(this.getFluidColor()) + ", renderDayColor=" + RGB.toString(this.getRenderDayColor()) + ", renderNightColor=" + RGB.toString(this.getRenderNightColor()) + ", lightAttenuation=" + this.getLightAttenuation() + '}';
    }

    public boolean isMapCaveLighting() {
        return this.mapCaveLighting;
    }

    public boolean isUnderground() {
        return this.underground;
    }

    public Integer getTopY() {
        return this.topY;
    }

    public void setTopY(Integer topY) {
        this.topY = topY;
    }

    public Integer getBottomY() {
        return this.bottomY;
    }

    public void setBottomY(Integer bottomY) {
        this.bottomY = bottomY;
    }

    public Integer getTopFluidY() {
        return this.topFluidY;
    }

    public void setTopFluidY(Integer topFluidY) {
        this.topFluidY = topFluidY;
    }

    public Integer getBottomFluidY() {
        return this.bottomFluidY;
    }

    public void setBottomFluidY(Integer bottomFluidY) {
        this.bottomFluidY = bottomFluidY;
    }

    public Integer getMaxLightLevel() {
        return this.maxLightLevel;
    }

    public void setMaxLightLevel(Integer maxLightLevel) {
        this.maxLightLevel = maxLightLevel;
    }

    public Integer getFluidColor() {
        return this.fluidColor;
    }

    public void setFluidColor(Integer fluidColor) {
        this.fluidColor = fluidColor;
    }

    public Integer getRenderDayColor() {
        return this.renderDayColor;
    }

    public void setRenderDayColor(Integer renderDayColor) {
        this.renderDayColor = renderDayColor;
    }

    public Integer getRenderNightColor() {
        return this.renderNightColor;
    }

    public void setRenderNightColor(Integer renderNightColor) {
        this.renderNightColor = renderNightColor;
    }

    public Integer getRenderCaveColor() {
        return this.renderCaveColor;
    }

    public void setRenderCaveColor(Integer renderCaveColor) {
        this.renderCaveColor = renderCaveColor;
    }

    public int getLightAttenuation() {
        return this.lightAttenuation;
    }

    public void setLightAttenuation(int lightAttenuation) {
        this.lightAttenuation = lightAttenuation;
    }

    public boolean isBlocksFound() {
        return this.blocksFound;
    }

    public void setBlocksFound(boolean blocksFound) {
        this.blocksFound = blocksFound;
    }
}

