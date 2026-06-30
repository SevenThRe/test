/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  bib
 *  nf
 */
package net.optifine.shaders;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import net.optifine.config.ConnectedParser;
import net.optifine.config.MatchBlock;
import net.optifine.reflect.Reflector;
import net.optifine.reflect.ReflectorForge;
import net.optifine.shaders.BlockAlias;
import net.optifine.shaders.IShaderPack;
import net.optifine.shaders.Shaders;
import net.optifine.shaders.config.MacroProcessor;
import net.optifine.util.PropertiesOrdered;
import net.optifine.util.StrUtils;

public class BlockAliases {
    private static BlockAlias[][] blockAliases = null;
    private static PropertiesOrdered blockLayerPropertes = null;
    private static boolean updateOnResourcesReloaded;

    public static int getBlockAliasId(int blockId, int metadata) {
        if (blockAliases == null) {
            return blockId;
        }
        if (blockId < 0 || blockId >= blockAliases.length) {
            return blockId;
        }
        BlockAlias[] aliases = blockAliases[blockId];
        if (aliases == null) {
            return blockId;
        }
        for (int i = 0; i < aliases.length; ++i) {
            BlockAlias ba = aliases[i];
            if (!ba.matches(blockId, metadata)) continue;
            return ba.getBlockAliasId();
        }
        return blockId;
    }

    public static void resourcesReloaded() {
        if (!updateOnResourcesReloaded) {
            return;
        }
        updateOnResourcesReloaded = false;
        BlockAliases.update(Shaders.getShaderPack());
    }

    public static void update(IShaderPack shaderPack) {
        BlockAliases.reset();
        if (shaderPack == null) {
            return;
        }
        if (Reflector.Loader_getActiveModList.exists() && bib.z().P() == null) {
            Config.dbg("[Shaders] Delayed loading of block mappings after resources are loaded");
            updateOnResourcesReloaded = true;
            return;
        }
        ArrayList<List<BlockAlias>> listBlockAliases = new ArrayList<List<BlockAlias>>();
        String path = "/shaders/block.properties";
        InputStream in = shaderPack.getResourceAsStream(path);
        if (in != null) {
            BlockAliases.loadBlockAliases(in, path, listBlockAliases);
        }
        BlockAliases.loadModBlockAliases(listBlockAliases);
        if (listBlockAliases.size() <= 0) {
            return;
        }
        blockAliases = BlockAliases.toArrays(listBlockAliases);
    }

    private static void loadModBlockAliases(List<List<BlockAlias>> listBlockAliases) {
        String[] modIds = ReflectorForge.getForgeModIds();
        for (int i = 0; i < modIds.length; ++i) {
            String modId = modIds[i];
            try {
                nf loc = new nf(modId, "shaders/block.properties");
                InputStream in = Config.getResourceStream(loc);
                BlockAliases.loadBlockAliases(in, loc.toString(), listBlockAliases);
                continue;
            }
            catch (IOException iOException) {
                // empty catch block
            }
        }
    }

    private static void loadBlockAliases(InputStream in, String path, List<List<BlockAlias>> listBlockAliases) {
        if (in == null) {
            return;
        }
        try {
            in = MacroProcessor.process(in, path);
            PropertiesOrdered props = new PropertiesOrdered();
            props.load(in);
            in.close();
            Config.dbg("[Shaders] Parsing block mappings: " + path);
            ConnectedParser cp = new ConnectedParser("Shaders");
            Set<Object> keys = ((Properties)props).keySet();
            for (String string : keys) {
                String val = props.getProperty(string);
                if (string.startsWith("layer.")) {
                    if (blockLayerPropertes == null) {
                        blockLayerPropertes = new PropertiesOrdered();
                    }
                    blockLayerPropertes.put(string, val);
                    continue;
                }
                String prefix = "block.";
                if (!string.startsWith(prefix)) {
                    Config.warn("[Shaders] Invalid block ID: " + string);
                    continue;
                }
                String blockIdStr = StrUtils.removePrefix(string, prefix);
                int blockId = Config.parseInt(blockIdStr, -1);
                if (blockId < 0) {
                    Config.warn("[Shaders] Invalid block ID: " + string);
                    continue;
                }
                MatchBlock[] matchBlocks = cp.parseMatchBlocks(val);
                if (matchBlocks == null || matchBlocks.length < 1) {
                    Config.warn("[Shaders] Invalid block ID mapping: " + string + "=" + val);
                    continue;
                }
                BlockAlias ba = new BlockAlias(blockId, matchBlocks);
                BlockAliases.addToList(listBlockAliases, ba);
            }
        }
        catch (IOException e) {
            Config.warn("[Shaders] Error reading: " + path);
        }
    }

    private static void addToList(List<List<BlockAlias>> blocksAliases, BlockAlias ba) {
        int[] blockIds = ba.getMatchBlockIds();
        for (int i = 0; i < blockIds.length; ++i) {
            int blockId = blockIds[i];
            while (blockId >= blocksAliases.size()) {
                blocksAliases.add(null);
            }
            List<BlockAlias> blockAliases = blocksAliases.get(blockId);
            if (blockAliases == null) {
                blockAliases = new ArrayList<BlockAlias>();
                blocksAliases.set(blockId, blockAliases);
            }
            BlockAlias baBlock = new BlockAlias(ba.getBlockAliasId(), ba.getMatchBlocks(blockId));
            blockAliases.add(baBlock);
        }
    }

    private static BlockAlias[][] toArrays(List<List<BlockAlias>> listBlocksAliases) {
        BlockAlias[][] bas = new BlockAlias[listBlocksAliases.size()][];
        for (int i = 0; i < bas.length; ++i) {
            List<BlockAlias> listBlockAliases = listBlocksAliases.get(i);
            if (listBlockAliases == null) continue;
            bas[i] = listBlockAliases.toArray(new BlockAlias[listBlockAliases.size()]);
        }
        return bas;
    }

    public static PropertiesOrdered getBlockLayerPropertes() {
        return blockLayerPropertes;
    }

    public static void reset() {
        blockAliases = null;
        blockLayerPropertes = null;
    }
}

