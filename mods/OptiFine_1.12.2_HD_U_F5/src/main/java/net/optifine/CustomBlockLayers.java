/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  amm
 *  awp
 *  awt
 */
package net.optifine;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import net.optifine.config.ConnectedParser;
import net.optifine.config.MatchBlock;
import net.optifine.shaders.BlockAliases;
import net.optifine.util.PropertiesOrdered;
import net.optifine.util.ResUtils;

public class CustomBlockLayers {
    private static amm[] renderLayers = null;
    public static boolean active = false;

    public static amm getRenderLayer(awt blockState) {
        if (renderLayers == null) {
            return null;
        }
        if (blockState.p()) {
            return null;
        }
        if (!(blockState instanceof awp)) {
            return null;
        }
        awp bsb = (awp)blockState;
        int id = bsb.getBlockId();
        if (id <= 0 || id >= renderLayers.length) {
            return null;
        }
        return renderLayers[id];
    }

    public static void update() {
        PropertiesOrdered propsShaders;
        renderLayers = null;
        active = false;
        ArrayList<amm> list = new ArrayList<amm>();
        String pathProps = "optifine/block.properties";
        Properties props = ResUtils.readProperties(pathProps, "CustomBlockLayers");
        if (props != null) {
            CustomBlockLayers.readLayers(pathProps, props, list);
        }
        if (Config.isShaders() && (propsShaders = BlockAliases.getBlockLayerPropertes()) != null) {
            String pathPropsShaders = "shaders/block.properties";
            CustomBlockLayers.readLayers(pathPropsShaders, propsShaders, list);
        }
        if (list.isEmpty()) {
            return;
        }
        renderLayers = list.toArray(new amm[list.size()]);
        active = true;
    }

    private static void readLayers(String pathProps, Properties props, List<amm> list) {
        Config.dbg("CustomBlockLayers: " + pathProps);
        CustomBlockLayers.readLayer("solid", amm.a, props, list);
        CustomBlockLayers.readLayer("cutout", amm.c, props, list);
        CustomBlockLayers.readLayer("cutout_mipped", amm.b, props, list);
        CustomBlockLayers.readLayer("translucent", amm.d, props, list);
    }

    private static void readLayer(String name, amm layer, Properties props, List<amm> listLayers) {
        String key = "layer." + name;
        String val = props.getProperty(key);
        if (val == null) {
            return;
        }
        ConnectedParser cp = new ConnectedParser("CustomBlockLayers");
        MatchBlock[] mbs = cp.parseMatchBlocks(val);
        if (mbs == null) {
            return;
        }
        for (int i = 0; i < mbs.length; ++i) {
            MatchBlock mb = mbs[i];
            int blockId = mb.getBlockId();
            if (blockId <= 0) continue;
            while (listLayers.size() < blockId + 1) {
                listLayers.add(null);
            }
            if (listLayers.get(blockId) != null) {
                Config.warn("CustomBlockLayers: Block layer is already set, block: " + blockId + ", layer: " + name);
            }
            listLayers.set(blockId, layer);
        }
    }

    public static boolean isActive() {
        return active;
    }
}

