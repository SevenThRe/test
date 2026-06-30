/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.shaders;

import java.util.ArrayList;
import java.util.HashSet;
import net.optifine.config.MatchBlock;

public class BlockAlias {
    private int blockAliasId;
    private MatchBlock[] matchBlocks;

    public BlockAlias(int blockAliasId, MatchBlock[] matchBlocks) {
        this.blockAliasId = blockAliasId;
        this.matchBlocks = matchBlocks;
    }

    public int getBlockAliasId() {
        return this.blockAliasId;
    }

    public boolean matches(int id, int metadata) {
        for (int i = 0; i < this.matchBlocks.length; ++i) {
            MatchBlock matchBlock = this.matchBlocks[i];
            if (!matchBlock.matches(id, metadata)) continue;
            return true;
        }
        return false;
    }

    public int[] getMatchBlockIds() {
        HashSet<Integer> blockIdSet = new HashSet<Integer>();
        for (int i = 0; i < this.matchBlocks.length; ++i) {
            MatchBlock matchBlock = this.matchBlocks[i];
            int blockId = matchBlock.getBlockId();
            blockIdSet.add(blockId);
        }
        Integer[] blockIdsArr = blockIdSet.toArray(new Integer[blockIdSet.size()]);
        int[] blockIds = Config.toPrimitive(blockIdsArr);
        return blockIds;
    }

    public MatchBlock[] getMatchBlocks(int matchBlockId) {
        ArrayList<MatchBlock> listMatchBlock = new ArrayList<MatchBlock>();
        for (int i = 0; i < this.matchBlocks.length; ++i) {
            MatchBlock mb = this.matchBlocks[i];
            if (mb.getBlockId() != matchBlockId) continue;
            listMatchBlock.add(mb);
        }
        MatchBlock[] mbs = listMatchBlock.toArray(new MatchBlock[listMatchBlock.size()]);
        return mbs;
    }

    public String toString() {
        return "block." + this.blockAliasId + "=" + Config.arrayToString(this.matchBlocks);
    }
}

