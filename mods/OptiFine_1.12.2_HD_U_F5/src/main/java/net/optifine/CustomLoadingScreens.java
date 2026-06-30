/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  hv
 *  org.apache.commons.lang3.tuple.ImmutablePair
 *  org.apache.commons.lang3.tuple.Pair
 */
package net.optifine;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Properties;
import java.util.Set;
import net.optifine.CustomLoadingScreen;
import net.optifine.util.ResUtils;
import net.optifine.util.StrUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

public class CustomLoadingScreens {
    private static CustomLoadingScreen[] screens = null;
    private static int screensMinDimensionId = 0;

    public static CustomLoadingScreen getCustomLoadingScreen() {
        if (screens == null) {
            return null;
        }
        int dimension = hv.lastDimensionId;
        int index = dimension - screensMinDimensionId;
        CustomLoadingScreen scr = null;
        if (index >= 0 && index < screens.length) {
            scr = screens[index];
        }
        return scr;
    }

    public static void update() {
        screens = null;
        screensMinDimensionId = 0;
        Pair<CustomLoadingScreen[], Integer> pair = CustomLoadingScreens.parseScreens();
        screens = (CustomLoadingScreen[])pair.getLeft();
        screensMinDimensionId = (Integer)pair.getRight();
    }

    private static Pair<CustomLoadingScreen[], Integer> parseScreens() {
        String prefix = "optifine/gui/loading/background";
        String suffix = ".png";
        String[] paths = ResUtils.collectFiles(prefix, suffix);
        HashMap<Integer, String> map = new HashMap<Integer, String>();
        for (int i = 0; i < paths.length; ++i) {
            String path = paths[i];
            String dimIdStr = StrUtils.removePrefixSuffix(path, prefix, suffix);
            int dimId = Config.parseInt(dimIdStr, Integer.MIN_VALUE);
            if (dimId == Integer.MIN_VALUE) {
                CustomLoadingScreens.warn("Invalid dimension ID: " + dimIdStr + ", path: " + path);
                continue;
            }
            map.put(dimId, path);
        }
        Set setDimIds = map.keySet();
        Object[] dimIds = setDimIds.toArray(new Integer[setDimIds.size()]);
        Arrays.sort(dimIds);
        if (dimIds.length <= 0) {
            return new ImmutablePair(null, (Object)0);
        }
        String pathProps = "optifine/gui/loading/loading.properties";
        Properties props = ResUtils.readProperties(pathProps, "CustomLoadingScreens");
        int minDimId = (Integer)dimIds[0];
        int maxDimId = (Integer)dimIds[dimIds.length - 1];
        int countDim = maxDimId - minDimId + 1;
        CustomLoadingScreen[] scrs = new CustomLoadingScreen[countDim];
        for (int i = 0; i < dimIds.length; ++i) {
            Object dimId = dimIds[i];
            String path = (String)map.get(dimId);
            scrs[((Integer)dimId).intValue() - minDimId] = CustomLoadingScreen.parseScreen(path, (Integer)dimId, props);
        }
        return new ImmutablePair((Object)scrs, (Object)minDimId);
    }

    public static void warn(String str) {
        Config.warn("CustomLoadingScreen: " + str);
    }

    public static void dbg(String str) {
        Config.dbg("CustomLoadingScreen: " + str);
    }
}

