/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  ahs
 *  ain
 *  aip
 *  ajv
 *  amq
 *  amu
 *  amy
 *  anh
 *  anm
 *  aow
 *  aox
 *  atf
 *  aug
 *  awp
 *  awt
 *  axj
 *  ayn
 *  bcz
 *  bda
 *  bhe
 *  bib
 *  bik
 *  btf
 *  bvp
 *  et
 *  fy
 *  nf
 *  org.apache.commons.lang3.tuple.ImmutablePair
 *  org.apache.commons.lang3.tuple.Pair
 *  rk
 *  uz
 *  vg
 */
package net.optifine;

import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.Set;
import javax.imageio.ImageIO;
import net.optifine.BlockPosM;
import net.optifine.CustomColorFader;
import net.optifine.CustomColormap;
import net.optifine.LightMap;
import net.optifine.LightMapPack;
import net.optifine.config.ConnectedParser;
import net.optifine.config.MatchBlock;
import net.optifine.reflect.Reflector;
import net.optifine.render.RenderEnv;
import net.optifine.util.EntityUtils;
import net.optifine.util.PropertiesOrdered;
import net.optifine.util.ResUtils;
import net.optifine.util.StrUtils;
import net.optifine.util.TextureUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

public class CustomColors {
    private static String paletteFormatDefault = "vanilla";
    private static CustomColormap waterColors = null;
    private static CustomColormap foliagePineColors = null;
    private static CustomColormap foliageBirchColors = null;
    private static CustomColormap swampFoliageColors = null;
    private static CustomColormap swampGrassColors = null;
    private static CustomColormap[] colorsBlockColormaps = null;
    private static CustomColormap[][] blockColormaps = null;
    private static CustomColormap skyColors = null;
    private static CustomColorFader skyColorFader = new CustomColorFader();
    private static CustomColormap fogColors = null;
    private static CustomColorFader fogColorFader = new CustomColorFader();
    private static CustomColormap underwaterColors = null;
    private static CustomColorFader underwaterColorFader = new CustomColorFader();
    private static CustomColormap underlavaColors = null;
    private static CustomColorFader underlavaColorFader = new CustomColorFader();
    private static LightMapPack[] lightMapPacks = null;
    private static int lightmapMinDimensionId = 0;
    private static CustomColormap redstoneColors = null;
    private static CustomColormap xpOrbColors = null;
    private static int xpOrbTime = -1;
    private static CustomColormap durabilityColors = null;
    private static CustomColormap stemColors = null;
    private static CustomColormap stemMelonColors = null;
    private static CustomColormap stemPumpkinColors = null;
    private static CustomColormap myceliumParticleColors = null;
    private static boolean useDefaultGrassFoliageColors = true;
    private static int particleWaterColor = -1;
    private static int particlePortalColor = -1;
    private static int lilyPadColor = -1;
    private static int expBarTextColor = -1;
    private static int bossTextColor = -1;
    private static int signTextColor = -1;
    private static bhe fogColorNether = null;
    private static bhe fogColorEnd = null;
    private static bhe skyColorEnd = null;
    private static int[] spawnEggPrimaryColors = null;
    private static int[] spawnEggSecondaryColors = null;
    private static float[][] wolfCollarColors = null;
    private static float[][] sheepColors = null;
    private static int[] textColors = null;
    private static int[] mapColorsOriginal = null;
    private static int[] potionColors = null;
    private static final awt BLOCK_STATE_DIRT = aox.d.t();
    private static final awt BLOCK_STATE_WATER = aox.j.t();
    public static Random random = new Random();
    private static final IColorizer COLORIZER_GRASS = new IColorizer(){

        @Override
        public int getColor(awt blockState, amy blockAccess, et blockPos) {
            anh biome = CustomColors.getColorBiome(blockAccess, blockPos);
            if (swampGrassColors != null && biome == anm.h) {
                return swampGrassColors.getColor(biome, blockPos);
            }
            return biome.b(blockPos);
        }

        @Override
        public boolean isColorConstant() {
            return false;
        }
    };
    private static final IColorizer COLORIZER_FOLIAGE = new IColorizer(){

        @Override
        public int getColor(awt blockState, amy blockAccess, et blockPos) {
            anh biome = CustomColors.getColorBiome(blockAccess, blockPos);
            if (swampFoliageColors != null && biome == anm.h) {
                return swampFoliageColors.getColor(biome, blockPos);
            }
            return biome.c(blockPos);
        }

        @Override
        public boolean isColorConstant() {
            return false;
        }
    };
    private static final IColorizer COLORIZER_FOLIAGE_PINE = new IColorizer(){

        @Override
        public int getColor(awt blockState, amy blockAccess, et blockPos) {
            if (foliagePineColors != null) {
                return foliagePineColors.getColor(blockAccess, blockPos);
            }
            return amq.a();
        }

        @Override
        public boolean isColorConstant() {
            return foliagePineColors == null;
        }
    };
    private static final IColorizer COLORIZER_FOLIAGE_BIRCH = new IColorizer(){

        @Override
        public int getColor(awt blockState, amy blockAccess, et blockPos) {
            if (foliageBirchColors != null) {
                return foliageBirchColors.getColor(blockAccess, blockPos);
            }
            return amq.b();
        }

        @Override
        public boolean isColorConstant() {
            return foliageBirchColors == null;
        }
    };
    private static final IColorizer COLORIZER_WATER = new IColorizer(){

        @Override
        public int getColor(awt blockState, amy blockAccess, et blockPos) {
            anh biome = CustomColors.getColorBiome(blockAccess, blockPos);
            if (waterColors != null) {
                return waterColors.getColor(biome, blockPos);
            }
            if (Reflector.ForgeBiome_getWaterColorMultiplier.exists()) {
                return Reflector.callInt(biome, Reflector.ForgeBiome_getWaterColorMultiplier, new Object[0]);
            }
            return biome.o();
        }

        @Override
        public boolean isColorConstant() {
            return false;
        }
    };

    public static void update() {
        paletteFormatDefault = "vanilla";
        waterColors = null;
        foliageBirchColors = null;
        foliagePineColors = null;
        swampGrassColors = null;
        swampFoliageColors = null;
        skyColors = null;
        fogColors = null;
        underwaterColors = null;
        underlavaColors = null;
        redstoneColors = null;
        xpOrbColors = null;
        xpOrbTime = -1;
        durabilityColors = null;
        stemColors = null;
        myceliumParticleColors = null;
        lightMapPacks = null;
        particleWaterColor = -1;
        particlePortalColor = -1;
        lilyPadColor = -1;
        expBarTextColor = -1;
        bossTextColor = -1;
        signTextColor = -1;
        fogColorNether = null;
        fogColorEnd = null;
        skyColorEnd = null;
        colorsBlockColormaps = null;
        blockColormaps = null;
        useDefaultGrassFoliageColors = true;
        spawnEggPrimaryColors = null;
        spawnEggSecondaryColors = null;
        wolfCollarColors = null;
        sheepColors = null;
        textColors = null;
        CustomColors.setMapColors(mapColorsOriginal);
        potionColors = null;
        paletteFormatDefault = CustomColors.getValidProperty("mcpatcher/color.properties", "palette.format", CustomColormap.FORMAT_STRINGS, "vanilla");
        String mcpColormap = "mcpatcher/colormap/";
        String[] waterPaths = new String[]{"water.png", "watercolorX.png"};
        waterColors = CustomColors.getCustomColors(mcpColormap, waterPaths, 256, 256);
        CustomColors.updateUseDefaultGrassFoliageColors();
        if (!Config.isCustomColors()) {
            return;
        }
        String[] pinePaths = new String[]{"pine.png", "pinecolor.png"};
        foliagePineColors = CustomColors.getCustomColors(mcpColormap, pinePaths, 256, 256);
        String[] birchPaths = new String[]{"birch.png", "birchcolor.png"};
        foliageBirchColors = CustomColors.getCustomColors(mcpColormap, birchPaths, 256, 256);
        String[] swampGrassPaths = new String[]{"swampgrass.png", "swampgrasscolor.png"};
        swampGrassColors = CustomColors.getCustomColors(mcpColormap, swampGrassPaths, 256, 256);
        String[] swampFoliagePaths = new String[]{"swampfoliage.png", "swampfoliagecolor.png"};
        swampFoliageColors = CustomColors.getCustomColors(mcpColormap, swampFoliagePaths, 256, 256);
        String[] sky0Paths = new String[]{"sky0.png", "skycolor0.png"};
        skyColors = CustomColors.getCustomColors(mcpColormap, sky0Paths, 256, 256);
        String[] fog0Paths = new String[]{"fog0.png", "fogcolor0.png"};
        fogColors = CustomColors.getCustomColors(mcpColormap, fog0Paths, 256, 256);
        String[] underwaterPaths = new String[]{"underwater.png", "underwatercolor.png"};
        underwaterColors = CustomColors.getCustomColors(mcpColormap, underwaterPaths, 256, 256);
        String[] underlavaPaths = new String[]{"underlava.png", "underlavacolor.png"};
        underlavaColors = CustomColors.getCustomColors(mcpColormap, underlavaPaths, 256, 256);
        String[] redstonePaths = new String[]{"redstone.png", "redstonecolor.png"};
        redstoneColors = CustomColors.getCustomColors(mcpColormap, redstonePaths, 16, 1);
        xpOrbColors = CustomColors.getCustomColors(mcpColormap + "xporb.png", -1, -1);
        durabilityColors = CustomColors.getCustomColors(mcpColormap + "durability.png", -1, -1);
        String[] stemPaths = new String[]{"stem.png", "stemcolor.png"};
        stemColors = CustomColors.getCustomColors(mcpColormap, stemPaths, 8, 1);
        stemPumpkinColors = CustomColors.getCustomColors(mcpColormap + "pumpkinstem.png", 8, 1);
        stemMelonColors = CustomColors.getCustomColors(mcpColormap + "melonstem.png", 8, 1);
        String[] myceliumPaths = new String[]{"myceliumparticle.png", "myceliumparticlecolor.png"};
        myceliumParticleColors = CustomColors.getCustomColors(mcpColormap, myceliumPaths, -1, -1);
        Pair<LightMapPack[], Integer> lightMaps = CustomColors.parseLightMapPacks();
        lightMapPacks = (LightMapPack[])lightMaps.getLeft();
        lightmapMinDimensionId = (Integer)lightMaps.getRight();
        CustomColors.readColorProperties("mcpatcher/color.properties");
        blockColormaps = CustomColors.readBlockColormaps(new String[]{mcpColormap + "custom/", mcpColormap + "blocks/"}, colorsBlockColormaps, 256, 256);
        CustomColors.updateUseDefaultGrassFoliageColors();
    }

    private static String getValidProperty(String fileName, String key, String[] validValues, String valDef) {
        try {
            nf loc = new nf(fileName);
            InputStream in = Config.getResourceStream(loc);
            if (in == null) {
                return valDef;
            }
            PropertiesOrdered props = new PropertiesOrdered();
            props.load(in);
            in.close();
            String val = props.getProperty(key);
            if (val == null) {
                return valDef;
            }
            List<String> listValidValues = Arrays.asList(validValues);
            if (!listValidValues.contains(val)) {
                CustomColors.warn("Invalid value: " + key + "=" + val);
                CustomColors.warn("Expected values: " + Config.arrayToString(validValues));
                return valDef;
            }
            CustomColors.dbg("" + key + "=" + val);
            return val;
        }
        catch (FileNotFoundException e) {
            return valDef;
        }
        catch (IOException e) {
            e.printStackTrace();
            return valDef;
        }
    }

    private static Pair<LightMapPack[], Integer> parseLightMapPacks() {
        String lightmapPrefix = "mcpatcher/lightmap/world";
        String lightmapSuffix = ".png";
        String[] pathsLightmap = ResUtils.collectFiles(lightmapPrefix, lightmapSuffix);
        HashMap<Integer, String> mapLightmaps = new HashMap<Integer, String>();
        for (int i = 0; i < pathsLightmap.length; ++i) {
            String path = pathsLightmap[i];
            String dimIdStr = StrUtils.removePrefixSuffix(path, lightmapPrefix, lightmapSuffix);
            int dimId = Config.parseInt(dimIdStr, Integer.MIN_VALUE);
            if (dimId == Integer.MIN_VALUE) {
                CustomColors.warn("Invalid dimension ID: " + dimIdStr + ", path: " + path);
                continue;
            }
            mapLightmaps.put(dimId, path);
        }
        Set setDimIds = mapLightmaps.keySet();
        Object[] dimIds = setDimIds.toArray(new Integer[setDimIds.size()]);
        Arrays.sort(dimIds);
        if (dimIds.length <= 0) {
            return new ImmutablePair(null, (Object)0);
        }
        int minDimId = (Integer)dimIds[0];
        int maxDimId = (Integer)dimIds[dimIds.length - 1];
        int countDim = maxDimId - minDimId + 1;
        CustomColormap[] colormaps = new CustomColormap[countDim];
        for (int i = 0; i < dimIds.length; ++i) {
            Object dimId = dimIds[i];
            String path = (String)mapLightmaps.get(dimId);
            CustomColormap colors = CustomColors.getCustomColors(path, -1, -1);
            if (colors == null) continue;
            if (colors.getWidth() < 16) {
                CustomColors.warn("Invalid lightmap width: " + colors.getWidth() + ", path: " + path);
                continue;
            }
            int lightmapIndex = (Integer)dimId - minDimId;
            colormaps[lightmapIndex] = colors;
        }
        LightMapPack[] lmps = new LightMapPack[colormaps.length];
        for (int i = 0; i < colormaps.length; ++i) {
            LightMapPack lmp;
            CustomColormap cm = colormaps[i];
            if (cm == null) continue;
            String name = cm.name;
            String basePath = cm.basePath;
            CustomColormap cmRain = CustomColors.getCustomColors(basePath + "/" + name + "_rain.png", -1, -1);
            CustomColormap cmThunder = CustomColors.getCustomColors(basePath + "/" + name + "_thunder.png", -1, -1);
            LightMap lm = new LightMap(cm);
            LightMap lmRain = cmRain != null ? new LightMap(cmRain) : null;
            LightMap lmThunder = cmThunder != null ? new LightMap(cmThunder) : null;
            lmps[i] = lmp = new LightMapPack(lm, lmRain, lmThunder);
        }
        return new ImmutablePair((Object)lmps, (Object)minDimId);
    }

    private static int getTextureHeight(String path, int defHeight) {
        try {
            InputStream in = Config.getResourceStream(new nf(path));
            if (in == null) {
                return defHeight;
            }
            BufferedImage bi = ImageIO.read(in);
            in.close();
            if (bi == null) {
                return defHeight;
            }
            return bi.getHeight();
        }
        catch (IOException e) {
            return defHeight;
        }
    }

    private static void readColorProperties(String fileName) {
        try {
            nf loc = new nf(fileName);
            InputStream in = Config.getResourceStream(loc);
            if (in == null) {
                return;
            }
            CustomColors.dbg("Loading " + fileName);
            PropertiesOrdered props = new PropertiesOrdered();
            props.load(in);
            in.close();
            particleWaterColor = CustomColors.readColor((Properties)props, new String[]{"particle.water", "drop.water"});
            particlePortalColor = CustomColors.readColor((Properties)props, "particle.portal");
            lilyPadColor = CustomColors.readColor((Properties)props, "lilypad");
            expBarTextColor = CustomColors.readColor((Properties)props, "text.xpbar");
            bossTextColor = CustomColors.readColor((Properties)props, "text.boss");
            signTextColor = CustomColors.readColor((Properties)props, "text.sign");
            fogColorNether = CustomColors.readColorVec3(props, "fog.nether");
            fogColorEnd = CustomColors.readColorVec3(props, "fog.end");
            skyColorEnd = CustomColors.readColorVec3(props, "sky.end");
            colorsBlockColormaps = CustomColors.readCustomColormaps(props, fileName);
            spawnEggPrimaryColors = CustomColors.readSpawnEggColors(props, fileName, "egg.shell.", "Spawn egg shell");
            spawnEggSecondaryColors = CustomColors.readSpawnEggColors(props, fileName, "egg.spots.", "Spawn egg spot");
            wolfCollarColors = CustomColors.readDyeColors(props, fileName, "collar.", "Wolf collar");
            sheepColors = CustomColors.readDyeColors(props, fileName, "sheep.", "Sheep");
            textColors = CustomColors.readTextColors(props, fileName, "text.code.", "Text");
            int[] mapColors = CustomColors.readMapColors(props, fileName, "map.", "Map");
            if (mapColors != null) {
                if (mapColorsOriginal == null) {
                    mapColorsOriginal = CustomColors.getMapColors();
                }
                CustomColors.setMapColors(mapColors);
            }
            potionColors = CustomColors.readPotionColors(props, fileName, "potion.", "Potion");
            xpOrbTime = Config.parseInt(props.getProperty("xporb.time"), -1);
        }
        catch (FileNotFoundException e) {
            return;
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
     * WARNING - void declaration
     */
    private static CustomColormap[] readCustomColormaps(Properties props, String fileName) {
        void var7_9;
        ArrayList<CustomColormap> list = new ArrayList<CustomColormap>();
        String palettePrefix = "palette.block.";
        HashMap<String, String> map = new HashMap<String, String>();
        Set<Object> keys = props.keySet();
        for (String string : keys) {
            String value = props.getProperty(string);
            if (!string.startsWith(palettePrefix)) continue;
            map.put(string, value);
        }
        String[] propNames = map.keySet().toArray(new String[map.size()]);
        boolean bl = false;
        while (var7_9 < propNames.length) {
            String name = propNames[var7_9];
            String value = props.getProperty(name);
            CustomColors.dbg("Block palette: " + name + " = " + value);
            String path = name.substring(palettePrefix.length());
            String basePath = TextureUtils.getBasePath(fileName);
            path = TextureUtils.fixResourcePath(path, basePath);
            CustomColormap colors = CustomColors.getCustomColors(path, 256, 256);
            if (colors == null) {
                CustomColors.warn("Colormap not found: " + path);
            } else {
                ConnectedParser cp = new ConnectedParser("CustomColors");
                MatchBlock[] mbs = cp.parseMatchBlocks(value);
                if (mbs == null || mbs.length <= 0) {
                    CustomColors.warn("Invalid match blocks: " + value);
                } else {
                    for (int m2 = 0; m2 < mbs.length; ++m2) {
                        MatchBlock mb = mbs[m2];
                        colors.addMatchBlock(mb);
                    }
                    list.add(colors);
                }
            }
            ++var7_9;
        }
        if (list.size() <= 0) {
            return null;
        }
        CustomColormap[] customColormapArray = list.toArray(new CustomColormap[list.size()]);
        return customColormapArray;
    }

    private static CustomColormap[][] readBlockColormaps(String[] basePaths, CustomColormap[] basePalettes, int width, int height) {
        int i;
        Object[] paths = ResUtils.collectFiles(basePaths, new String[]{".properties"});
        Arrays.sort(paths);
        ArrayList blockList = new ArrayList();
        for (i = 0; i < paths.length; ++i) {
            Object path = paths[i];
            CustomColors.dbg("Block colormap: " + (String)path);
            try {
                nf locFile = new nf("minecraft", (String)path);
                InputStream in = Config.getResourceStream(locFile);
                if (in == null) {
                    CustomColors.warn("File not found: " + (String)path);
                    continue;
                }
                PropertiesOrdered props = new PropertiesOrdered();
                props.load(in);
                CustomColormap cm = new CustomColormap(props, (String)path, width, height, paletteFormatDefault);
                if (!cm.isValid((String)path) || !cm.isValidMatchBlocks((String)path)) continue;
                CustomColors.addToBlockList(cm, blockList);
                continue;
            }
            catch (FileNotFoundException e) {
                CustomColors.warn("File not found: " + (String)path);
                continue;
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (basePalettes != null) {
            for (i = 0; i < basePalettes.length; ++i) {
                CustomColormap cm = basePalettes[i];
                CustomColors.addToBlockList(cm, blockList);
            }
        }
        if (blockList.size() <= 0) {
            return null;
        }
        CustomColormap[][] cmArr = CustomColors.blockListToArray(blockList);
        return cmArr;
    }

    private static void addToBlockList(CustomColormap cm, List blockList) {
        int[] ids = cm.getMatchBlockIds();
        if (ids == null || ids.length <= 0) {
            CustomColors.warn("No match blocks: " + Config.arrayToString(ids));
            return;
        }
        for (int i = 0; i < ids.length; ++i) {
            int blockId = ids[i];
            if (blockId < 0) {
                CustomColors.warn("Invalid block ID: " + blockId);
                continue;
            }
            CustomColors.addToList(cm, blockList, blockId);
        }
    }

    private static void addToList(CustomColormap cm, List list, int id) {
        while (id >= list.size()) {
            list.add(null);
        }
        ArrayList<CustomColormap> subList = (ArrayList<CustomColormap>)list.get(id);
        if (subList == null) {
            subList = new ArrayList<CustomColormap>();
            list.set(id, subList);
        }
        subList.add(cm);
    }

    private static CustomColormap[][] blockListToArray(List list) {
        CustomColormap[][] colArr = new CustomColormap[list.size()][];
        for (int i = 0; i < list.size(); ++i) {
            List subList = (List)list.get(i);
            if (subList == null) continue;
            CustomColormap[] subArr = subList.toArray(new CustomColormap[subList.size()]);
            colArr[i] = subArr;
        }
        return colArr;
    }

    private static int readColor(Properties props, String[] names) {
        for (int i = 0; i < names.length; ++i) {
            String name = names[i];
            int col = CustomColors.readColor(props, name);
            if (col < 0) continue;
            return col;
        }
        return -1;
    }

    private static int readColor(Properties props, String name) {
        String str = props.getProperty(name);
        if (str == null) {
            return -1;
        }
        int color = CustomColors.parseColor(str = str.trim());
        if (color < 0) {
            CustomColors.warn("Invalid color: " + name + " = " + str);
            return color;
        }
        CustomColors.dbg(name + " = " + str);
        return color;
    }

    private static int parseColor(String str) {
        if (str == null) {
            return -1;
        }
        str = str.trim();
        try {
            int val = Integer.parseInt(str, 16) & 0xFFFFFF;
            return val;
        }
        catch (NumberFormatException e) {
            return -1;
        }
    }

    private static bhe readColorVec3(Properties props, String name) {
        int col = CustomColors.readColor(props, name);
        if (col < 0) {
            return null;
        }
        int red = col >> 16 & 0xFF;
        int green = col >> 8 & 0xFF;
        int blue = col & 0xFF;
        float redF = (float)red / 255.0f;
        float greenF = (float)green / 255.0f;
        float blueF = (float)blue / 255.0f;
        return new bhe((double)redF, (double)greenF, (double)blueF);
    }

    private static CustomColormap getCustomColors(String basePath, String[] paths, int width, int height) {
        for (int i = 0; i < paths.length; ++i) {
            String path = paths[i];
            path = basePath + path;
            CustomColormap cols = CustomColors.getCustomColors(path, width, height);
            if (cols == null) continue;
            return cols;
        }
        return null;
    }

    public static CustomColormap getCustomColors(String pathImage, int width, int height) {
        try {
            nf loc = new nf(pathImage);
            if (!Config.hasResource(loc)) {
                return null;
            }
            CustomColors.dbg("Colormap " + pathImage);
            PropertiesOrdered props = new PropertiesOrdered();
            String pathProps = StrUtils.replaceSuffix(pathImage, ".png", ".properties");
            nf locProps = new nf(pathProps);
            if (Config.hasResource(locProps)) {
                InputStream in = Config.getResourceStream(locProps);
                props.load(in);
                in.close();
                CustomColors.dbg("Colormap properties: " + pathProps);
            } else {
                ((Properties)props).put("format", paletteFormatDefault);
                ((Properties)props).put("source", pathImage);
                pathProps = pathImage;
            }
            CustomColormap cm = new CustomColormap(props, pathProps, width, height, paletteFormatDefault);
            if (!cm.isValid(pathProps)) {
                return null;
            }
            return cm;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void updateUseDefaultGrassFoliageColors() {
        useDefaultGrassFoliageColors = foliageBirchColors == null && foliagePineColors == null && swampGrassColors == null && swampFoliageColors == null && Config.isSwampColors() && Config.isSmoothBiomes();
    }

    public static int getColorMultiplier(bvp quad, awt blockState, amy blockAccess, et blockPos, RenderEnv renderEnv) {
        IColorizer colorizer;
        aow block = blockState.u();
        awt bs = renderEnv.getBlockState();
        if (blockColormaps != null) {
            CustomColormap cm;
            if (!quad.c()) {
                if (block == aox.c) {
                    bs = BLOCK_STATE_DIRT;
                }
                if (block == aox.af) {
                    return -1;
                }
            }
            if (block == aox.cF && renderEnv.getMetadata() >= 8) {
                blockPos = blockPos.b();
                bs = blockAccess.o(blockPos);
            }
            if ((cm = CustomColors.getBlockColormap(bs)) != null) {
                if (Config.isSmoothBiomes() && !cm.isColorConstant()) {
                    return CustomColors.getSmoothColorMultiplier(blockState, blockAccess, blockPos, cm, renderEnv.getColorizerBlockPosM());
                }
                return cm.getColor(blockAccess, blockPos);
            }
        }
        if (!quad.c()) {
            return -1;
        }
        if (block == aox.bx) {
            return CustomColors.getLilypadColorMultiplier(blockAccess, blockPos);
        }
        if (block == aox.af) {
            return CustomColors.getRedstoneColor(renderEnv.getBlockState());
        }
        if (block instanceof aug) {
            return CustomColors.getStemColorMultiplier(block, blockAccess, blockPos, renderEnv);
        }
        if (useDefaultGrassFoliageColors) {
            return -1;
        }
        int metadata = renderEnv.getMetadata();
        if (block == aox.c || block == aox.H || block == aox.cF) {
            colorizer = COLORIZER_GRASS;
        } else if (block == aox.cF) {
            colorizer = COLORIZER_GRASS;
            if (metadata >= 8) {
                blockPos = blockPos.b();
            }
        } else if (block == aox.t) {
            switch (metadata & 3) {
                case 0: {
                    colorizer = COLORIZER_FOLIAGE;
                    break;
                }
                case 1: {
                    colorizer = COLORIZER_FOLIAGE_PINE;
                    break;
                }
                case 2: {
                    colorizer = COLORIZER_FOLIAGE_BIRCH;
                    break;
                }
                default: {
                    colorizer = COLORIZER_FOLIAGE;
                    break;
                }
            }
        } else if (block == aox.u) {
            colorizer = COLORIZER_FOLIAGE;
        } else if (block == aox.bn) {
            colorizer = COLORIZER_FOLIAGE;
        } else {
            return -1;
        }
        if (Config.isSmoothBiomes() && !colorizer.isColorConstant()) {
            return CustomColors.getSmoothColorMultiplier(blockState, blockAccess, blockPos, colorizer, renderEnv.getColorizerBlockPosM());
        }
        return colorizer.getColor(bs, blockAccess, blockPos);
    }

    protected static anh getColorBiome(amy blockAccess, et blockPos) {
        anh biome = blockAccess.b(blockPos);
        if (biome == anm.h && !Config.isSwampColors()) {
            biome = anm.c;
        }
        return biome;
    }

    private static CustomColormap getBlockColormap(awt blockState) {
        if (blockColormaps == null) {
            return null;
        }
        if (!(blockState instanceof awp)) {
            return null;
        }
        awp bs = (awp)blockState;
        int blockId = bs.getBlockId();
        if (blockId < 0 || blockId >= blockColormaps.length) {
            return null;
        }
        CustomColormap[] cms = blockColormaps[blockId];
        if (cms == null) {
            return null;
        }
        for (int i = 0; i < cms.length; ++i) {
            CustomColormap cm = cms[i];
            if (!cm.matchesBlock(bs)) continue;
            return cm;
        }
        return null;
    }

    private static int getSmoothColorMultiplier(awt blockState, amy blockAccess, et blockPos, IColorizer colorizer, BlockPosM blockPosM) {
        int sumRed = 0;
        int sumGreen = 0;
        int sumBlue = 0;
        int x = blockPos.p();
        int y = blockPos.q();
        int z = blockPos.r();
        BlockPosM posM = blockPosM;
        for (int ix = x - 1; ix <= x + 1; ++ix) {
            for (int iz = z - 1; iz <= z + 1; ++iz) {
                posM.setXyz(ix, y, iz);
                int col = colorizer.getColor(blockState, blockAccess, posM);
                sumRed += col >> 16 & 0xFF;
                sumGreen += col >> 8 & 0xFF;
                sumBlue += col & 0xFF;
            }
        }
        int r = sumRed / 9;
        int g = sumGreen / 9;
        int b2 = sumBlue / 9;
        return r << 16 | g << 8 | b2;
    }

    public static int getFluidColor(amy blockAccess, awt blockState, et blockPos, RenderEnv renderEnv) {
        aow block = blockState.u();
        IColorizer colorizer = CustomColors.getBlockColormap(blockState);
        if (colorizer == null && blockState.a() == bcz.h) {
            colorizer = COLORIZER_WATER;
        }
        if (colorizer == null) {
            return CustomColors.getBlockColors().a(blockState, blockAccess, blockPos, 0);
        }
        if (Config.isSmoothBiomes() && !colorizer.isColorConstant()) {
            return CustomColors.getSmoothColorMultiplier(blockState, blockAccess, blockPos, colorizer, renderEnv.getColorizerBlockPosM());
        }
        return colorizer.getColor(blockState, blockAccess, blockPos);
    }

    public static bik getBlockColors() {
        return bib.z().al();
    }

    public static void updatePortalFX(btf fx2) {
        if (particlePortalColor < 0) {
            return;
        }
        int col = particlePortalColor;
        int red = col >> 16 & 0xFF;
        int green = col >> 8 & 0xFF;
        int blue = col & 0xFF;
        float redF = (float)red / 255.0f;
        float greenF = (float)green / 255.0f;
        float blueF = (float)blue / 255.0f;
        fx2.a(redF, greenF, blueF);
    }

    public static void updateMyceliumFX(btf fx2) {
        if (myceliumParticleColors == null) {
            return;
        }
        int col = myceliumParticleColors.getColorRandom();
        int red = col >> 16 & 0xFF;
        int green = col >> 8 & 0xFF;
        int blue = col & 0xFF;
        float redF = (float)red / 255.0f;
        float greenF = (float)green / 255.0f;
        float blueF = (float)blue / 255.0f;
        fx2.a(redF, greenF, blueF);
    }

    private static int getRedstoneColor(awt blockState) {
        if (redstoneColors == null) {
            return -1;
        }
        int level = CustomColors.getRedstoneLevel(blockState, 15);
        int col = redstoneColors.getColor(level);
        return col;
    }

    public static void updateReddustFX(btf fx2, amy blockAccess, double x, double y, double z) {
        if (redstoneColors == null) {
            return;
        }
        awt state = blockAccess.o(new et(x, y, z));
        int level = CustomColors.getRedstoneLevel(state, 15);
        int col = redstoneColors.getColor(level);
        int red = col >> 16 & 0xFF;
        int green = col >> 8 & 0xFF;
        int blue = col & 0xFF;
        float redF = (float)red / 255.0f;
        float greenF = (float)green / 255.0f;
        float blueF = (float)blue / 255.0f;
        fx2.a(redF, greenF, blueF);
    }

    private static int getRedstoneLevel(awt state, int def) {
        aow block = state.u();
        if (!(block instanceof atf)) {
            return def;
        }
        Comparable val = state.c((axj)atf.e);
        if (!(val instanceof Integer)) {
            return def;
        }
        Integer valInt = (Integer)val;
        return valInt;
    }

    public static float getXpOrbTimer(float timer) {
        if (xpOrbTime <= 0) {
            return timer;
        }
        float kt = 628.0f / (float)xpOrbTime;
        return timer * kt;
    }

    public static int getXpOrbColor(float timer) {
        if (xpOrbColors == null) {
            return -1;
        }
        int index = (int)Math.round((double)((rk.a((float)timer) + 1.0f) * (float)(xpOrbColors.getLength() - 1)) / 2.0);
        int col = xpOrbColors.getColor(index);
        return col;
    }

    public static int getDurabilityColor(float dur, int color) {
        if (durabilityColors == null) {
            return color;
        }
        int index = (int)(dur * (float)durabilityColors.getLength());
        int col = durabilityColors.getColor(index);
        return col;
    }

    public static void updateWaterFX(btf fx2, amy blockAccess, double x, double y, double z, RenderEnv renderEnv) {
        if (waterColors == null && blockColormaps == null && particleWaterColor < 0) {
            return;
        }
        et blockPos = new et(x, y, z);
        renderEnv.reset(BLOCK_STATE_WATER, blockPos);
        int col = CustomColors.getFluidColor(blockAccess, BLOCK_STATE_WATER, blockPos, renderEnv);
        int red = col >> 16 & 0xFF;
        int green = col >> 8 & 0xFF;
        int blue = col & 0xFF;
        float redF = (float)red / 255.0f;
        float greenF = (float)green / 255.0f;
        float blueF = (float)blue / 255.0f;
        if (particleWaterColor >= 0) {
            int redDrop = particleWaterColor >> 16 & 0xFF;
            int greenDrop = particleWaterColor >> 8 & 0xFF;
            int blueDrop = particleWaterColor & 0xFF;
            redF *= (float)redDrop / 255.0f;
            greenF *= (float)greenDrop / 255.0f;
            blueF *= (float)blueDrop / 255.0f;
        }
        fx2.a(redF, greenF, blueF);
    }

    private static int getLilypadColorMultiplier(amy blockAccess, et blockPos) {
        if (lilyPadColor < 0) {
            return CustomColors.getBlockColors().a(aox.bx.t(), blockAccess, blockPos, 0);
        }
        return lilyPadColor;
    }

    private static bhe getFogColorNether(bhe col) {
        if (fogColorNether == null) {
            return col;
        }
        return fogColorNether;
    }

    private static bhe getFogColorEnd(bhe col) {
        if (fogColorEnd == null) {
            return col;
        }
        return fogColorEnd;
    }

    private static bhe getSkyColorEnd(bhe col) {
        if (skyColorEnd == null) {
            return col;
        }
        return skyColorEnd;
    }

    public static bhe getSkyColor(bhe skyColor3d, amy blockAccess, double x, double y, double z) {
        if (skyColors == null) {
            return skyColor3d;
        }
        int col = skyColors.getColorSmooth(blockAccess, x, y, z, 3);
        int red = col >> 16 & 0xFF;
        int green = col >> 8 & 0xFF;
        int blue = col & 0xFF;
        float redF = (float)red / 255.0f;
        float greenF = (float)green / 255.0f;
        float blueF = (float)blue / 255.0f;
        float cRed = (float)skyColor3d.b / 0.5f;
        float cGreen = (float)skyColor3d.c / 0.66275f;
        float cBlue = (float)skyColor3d.d;
        bhe newCol = skyColorFader.getColor(redF *= cRed, greenF *= cGreen, blueF *= cBlue);
        return newCol;
    }

    private static bhe getFogColor(bhe fogColor3d, amy blockAccess, double x, double y, double z) {
        if (fogColors == null) {
            return fogColor3d;
        }
        int col = fogColors.getColorSmooth(blockAccess, x, y, z, 3);
        int red = col >> 16 & 0xFF;
        int green = col >> 8 & 0xFF;
        int blue = col & 0xFF;
        float redF = (float)red / 255.0f;
        float greenF = (float)green / 255.0f;
        float blueF = (float)blue / 255.0f;
        float cRed = (float)fogColor3d.b / 0.753f;
        float cGreen = (float)fogColor3d.c / 0.8471f;
        float cBlue = (float)fogColor3d.d;
        bhe newCol = fogColorFader.getColor(redF *= cRed, greenF *= cGreen, blueF *= cBlue);
        return newCol;
    }

    public static bhe getUnderwaterColor(amy blockAccess, double x, double y, double z) {
        return CustomColors.getUnderFluidColor(blockAccess, x, y, z, underwaterColors, underwaterColorFader);
    }

    public static bhe getUnderlavaColor(amy blockAccess, double x, double y, double z) {
        return CustomColors.getUnderFluidColor(blockAccess, x, y, z, underlavaColors, underlavaColorFader);
    }

    public static bhe getUnderFluidColor(amy blockAccess, double x, double y, double z, CustomColormap underFluidColors, CustomColorFader underFluidColorFader) {
        if (underFluidColors == null) {
            return null;
        }
        int col = underFluidColors.getColorSmooth(blockAccess, x, y, z, 3);
        int red = col >> 16 & 0xFF;
        int green = col >> 8 & 0xFF;
        int blue = col & 0xFF;
        float redF = (float)red / 255.0f;
        float greenF = (float)green / 255.0f;
        float blueF = (float)blue / 255.0f;
        bhe newCol = underFluidColorFader.getColor(redF, greenF, blueF);
        return newCol;
    }

    private static int getStemColorMultiplier(aow blockStem, amy blockAccess, et blockPos, RenderEnv renderEnv) {
        CustomColormap colors = stemColors;
        if (blockStem == aox.bl && stemPumpkinColors != null) {
            colors = stemPumpkinColors;
        }
        if (blockStem == aox.bm && stemMelonColors != null) {
            colors = stemMelonColors;
        }
        if (colors == null) {
            return -1;
        }
        int level = renderEnv.getMetadata();
        return colors.getColor(level);
    }

    public static boolean updateLightmap(amu world, float torchFlickerX, int[] lmColors, boolean nightvision, float partialTicks) {
        if (world == null) {
            return false;
        }
        if (lightMapPacks == null) {
            return false;
        }
        int dimensionId = world.s.q().a();
        int lightMapIndex = dimensionId - lightmapMinDimensionId;
        if (lightMapIndex < 0 || lightMapIndex >= lightMapPacks.length) {
            return false;
        }
        LightMapPack lightMapPack = lightMapPacks[lightMapIndex];
        if (lightMapPack == null) {
            return false;
        }
        return lightMapPack.updateLightmap(world, torchFlickerX, lmColors, nightvision, partialTicks);
    }

    public static bhe getWorldFogColor(bhe fogVec, amu world, vg renderViewEntity, float partialTicks) {
        ayn worldType = world.s.q();
        bib mc = bib.z();
        if (worldType == ayn.b) {
            return CustomColors.getFogColorNether(fogVec);
        }
        if (worldType == ayn.a) {
            return CustomColors.getFogColor(fogVec, (amy)mc.f, renderViewEntity.p, renderViewEntity.q + 1.0, renderViewEntity.r);
        }
        if (worldType == ayn.c) {
            return CustomColors.getFogColorEnd(fogVec);
        }
        return fogVec;
    }

    public static bhe getWorldSkyColor(bhe skyVec, amu world, vg renderViewEntity, float partialTicks) {
        ayn worldType = world.s.q();
        bib mc = bib.z();
        if (worldType == ayn.a) {
            return CustomColors.getSkyColor(skyVec, (amy)mc.f, renderViewEntity.p, renderViewEntity.q + 1.0, renderViewEntity.r);
        }
        if (worldType == ayn.c) {
            return CustomColors.getSkyColorEnd(skyVec);
        }
        return skyVec;
    }

    /*
     * WARNING - void declaration
     */
    private static int[] readSpawnEggColors(Properties props, String fileName, String prefix, String logName) {
        void var8_10;
        ArrayList<Integer> list = new ArrayList<Integer>();
        Set<Object> keys = props.keySet();
        int countColors = 0;
        for (String string : keys) {
            String value = props.getProperty(string);
            if (!string.startsWith(prefix)) continue;
            String name = StrUtils.removePrefix(string, prefix);
            int id = EntityUtils.getEntityIdByName(name);
            if (id < 0) {
                id = EntityUtils.getEntityIdByLocation(new nf(name).toString());
            }
            if (id < 0) {
                CustomColors.warn("Invalid spawn egg name: " + string);
                continue;
            }
            int color = CustomColors.parseColor(value);
            if (color < 0) {
                CustomColors.warn("Invalid spawn egg color: " + string + " = " + value);
                continue;
            }
            while (list.size() <= id) {
                list.add(-1);
            }
            list.set(id, color);
            ++countColors;
        }
        if (countColors <= 0) {
            return null;
        }
        CustomColors.dbg(logName + " colors: " + countColors);
        int[] colors = new int[list.size()];
        boolean bl = false;
        while (var8_10 < colors.length) {
            colors[var8_10] = (Integer)list.get((int)var8_10);
            ++var8_10;
        }
        return colors;
    }

    private static int getSpawnEggColor(ajv item, aip itemStack, int layer, int color) {
        int[] eggColors;
        if (spawnEggPrimaryColors == null && spawnEggSecondaryColors == null) {
            return color;
        }
        fy nbt = itemStack.p();
        if (nbt == null) {
            return color;
        }
        fy tag = nbt.p("EntityTag");
        if (tag == null) {
            return color;
        }
        String loc = tag.l("id");
        int id = EntityUtils.getEntityIdByLocation(loc);
        int[] nArray = eggColors = layer == 0 ? spawnEggPrimaryColors : spawnEggSecondaryColors;
        if (eggColors == null) {
            return color;
        }
        if (id < 0 || id >= eggColors.length) {
            return color;
        }
        int eggColor = eggColors[id];
        if (eggColor < 0) {
            return color;
        }
        return eggColor;
    }

    public static int getColorFromItemStack(aip itemStack, int layer, int color) {
        if (itemStack == null) {
            return color;
        }
        ain item = itemStack.c();
        if (item == null) {
            return color;
        }
        if (item instanceof ajv) {
            return CustomColors.getSpawnEggColor((ajv)item, itemStack, layer, color);
        }
        return color;
    }

    private static float[][] readDyeColors(Properties props, String fileName, String prefix, String logName) {
        ahs[] dyeValues = ahs.values();
        HashMap<String, ahs> mapDyes = new HashMap<String, ahs>();
        for (int i = 0; i < dyeValues.length; ++i) {
            ahs dye = dyeValues[i];
            mapDyes.put(dye.m(), dye);
        }
        float[][] colors = new float[dyeValues.length][];
        int countColors = 0;
        Set<Object> keys = props.keySet();
        for (String string : keys) {
            String value = props.getProperty(string);
            if (!string.startsWith(prefix)) continue;
            String name = StrUtils.removePrefix(string, prefix);
            if (name.equals("lightBlue")) {
                name = "light_blue";
            }
            ahs dye = (ahs)mapDyes.get(name);
            int color = CustomColors.parseColor(value);
            if (dye == null || color < 0) {
                CustomColors.warn("Invalid color: " + string + " = " + value);
                continue;
            }
            float[] rgb = new float[]{(float)(color >> 16 & 0xFF) / 255.0f, (float)(color >> 8 & 0xFF) / 255.0f, (float)(color & 0xFF) / 255.0f};
            colors[dye.ordinal()] = rgb;
            ++countColors;
        }
        if (countColors <= 0) {
            return null;
        }
        CustomColors.dbg(logName + " colors: " + countColors);
        return colors;
    }

    private static float[] getDyeColors(ahs dye, float[][] dyeColors, float[] colors) {
        if (dyeColors == null) {
            return colors;
        }
        if (dye == null) {
            return colors;
        }
        float[] customColors = dyeColors[dye.ordinal()];
        if (customColors == null) {
            return colors;
        }
        return customColors;
    }

    public static float[] getWolfCollarColors(ahs dye, float[] colors) {
        return CustomColors.getDyeColors(dye, wolfCollarColors, colors);
    }

    public static float[] getSheepColors(ahs dye, float[] colors) {
        return CustomColors.getDyeColors(dye, sheepColors, colors);
    }

    private static int[] readTextColors(Properties props, String fileName, String prefix, String logName) {
        int[] colors = new int[32];
        Arrays.fill(colors, -1);
        int countColors = 0;
        Set<Object> keys = props.keySet();
        for (String string : keys) {
            String value = props.getProperty(string);
            if (!string.startsWith(prefix)) continue;
            String name = StrUtils.removePrefix(string, prefix);
            int code = Config.parseInt(name, -1);
            int color = CustomColors.parseColor(value);
            if (code < 0 || code >= colors.length || color < 0) {
                CustomColors.warn("Invalid color: " + string + " = " + value);
                continue;
            }
            colors[code] = color;
            ++countColors;
        }
        if (countColors <= 0) {
            return null;
        }
        CustomColors.dbg(logName + " colors: " + countColors);
        return colors;
    }

    public static int getTextColor(int index, int color) {
        if (textColors == null) {
            return color;
        }
        if (index < 0 || index >= textColors.length) {
            return color;
        }
        int customColor = textColors[index];
        if (customColor < 0) {
            return color;
        }
        return customColor;
    }

    private static int[] readMapColors(Properties props, String fileName, String prefix, String logName) {
        int[] colors = new int[bda.a.length];
        Arrays.fill(colors, -1);
        int countColors = 0;
        Set<Object> keys = props.keySet();
        for (String string : keys) {
            String value = props.getProperty(string);
            if (!string.startsWith(prefix)) continue;
            String name = StrUtils.removePrefix(string, prefix);
            int index = CustomColors.getMapColorIndex(name);
            int color = CustomColors.parseColor(value);
            if (index < 0 || index >= colors.length || color < 0) {
                CustomColors.warn("Invalid color: " + string + " = " + value);
                continue;
            }
            colors[index] = color;
            ++countColors;
        }
        if (countColors <= 0) {
            return null;
        }
        CustomColors.dbg(logName + " colors: " + countColors);
        return colors;
    }

    private static int[] readPotionColors(Properties props, String fileName, String prefix, String logName) {
        int[] colors = new int[CustomColors.getMaxPotionId()];
        Arrays.fill(colors, -1);
        int countColors = 0;
        Set<Object> keys = props.keySet();
        for (String string : keys) {
            String value = props.getProperty(string);
            if (!string.startsWith(prefix)) continue;
            String name = string;
            int index = CustomColors.getPotionId(name);
            int color = CustomColors.parseColor(value);
            if (index < 0 || index >= colors.length || color < 0) {
                CustomColors.warn("Invalid color: " + string + " = " + value);
                continue;
            }
            colors[index] = color;
            ++countColors;
        }
        if (countColors <= 0) {
            return null;
        }
        CustomColors.dbg(logName + " colors: " + countColors);
        return colors;
    }

    private static int getMaxPotionId() {
        int maxId = 0;
        Set keys = uz.b.c();
        for (nf rl : keys) {
            uz potion = (uz)uz.b.c((Object)rl);
            int id = uz.a((uz)potion);
            if (id <= maxId) continue;
            maxId = id;
        }
        return maxId;
    }

    private static int getPotionId(String name) {
        if (name.equals("potion.water")) {
            return 0;
        }
        name = StrUtils.replacePrefix(name, "potion.", "effect.");
        Set keys = uz.b.c();
        for (nf rl : keys) {
            uz potion = (uz)uz.b.c((Object)rl);
            if (!potion.a().equals(name)) continue;
            return uz.a((uz)potion);
        }
        return -1;
    }

    public static int getPotionColor(uz potion, int color) {
        int potionId = 0;
        if (potion != null) {
            potionId = uz.a((uz)potion);
        }
        return CustomColors.getPotionColor(potionId, color);
    }

    public static int getPotionColor(int potionId, int color) {
        if (potionColors == null) {
            return color;
        }
        if (potionId < 0 || potionId >= potionColors.length) {
            return color;
        }
        int potionColor = potionColors[potionId];
        if (potionColor < 0) {
            return color;
        }
        return potionColor;
    }

    private static int getMapColorIndex(String name) {
        if (name == null) {
            return -1;
        }
        if (name.equals("air")) {
            return bda.c.ad;
        }
        if (name.equals("grass")) {
            return bda.d.ad;
        }
        if (name.equals("sand")) {
            return bda.e.ad;
        }
        if (name.equals("cloth")) {
            return bda.f.ad;
        }
        if (name.equals("tnt")) {
            return bda.g.ad;
        }
        if (name.equals("ice")) {
            return bda.h.ad;
        }
        if (name.equals("iron")) {
            return bda.i.ad;
        }
        if (name.equals("foliage")) {
            return bda.j.ad;
        }
        if (name.equals("clay")) {
            return bda.l.ad;
        }
        if (name.equals("dirt")) {
            return bda.m.ad;
        }
        if (name.equals("stone")) {
            return bda.n.ad;
        }
        if (name.equals("water")) {
            return bda.o.ad;
        }
        if (name.equals("wood")) {
            return bda.p.ad;
        }
        if (name.equals("quartz")) {
            return bda.q.ad;
        }
        if (name.equals("gold")) {
            return bda.G.ad;
        }
        if (name.equals("diamond")) {
            return bda.H.ad;
        }
        if (name.equals("lapis")) {
            return bda.I.ad;
        }
        if (name.equals("emerald")) {
            return bda.J.ad;
        }
        if (name.equals("podzol")) {
            return bda.K.ad;
        }
        if (name.equals("netherrack")) {
            return bda.L.ad;
        }
        if (name.equals("snow") || name.equals("white")) {
            return bda.k.ad;
        }
        if (name.equals("adobe") || name.equals("orange")) {
            return bda.r.ad;
        }
        if (name.equals("magenta")) {
            return bda.s.ad;
        }
        if (name.equals("light_blue") || name.equals("lightBlue")) {
            return bda.t.ad;
        }
        if (name.equals("yellow")) {
            return bda.u.ad;
        }
        if (name.equals("lime")) {
            return bda.v.ad;
        }
        if (name.equals("pink")) {
            return bda.w.ad;
        }
        if (name.equals("gray")) {
            return bda.x.ad;
        }
        if (name.equals("silver")) {
            return bda.y.ad;
        }
        if (name.equals("cyan")) {
            return bda.z.ad;
        }
        if (name.equals("purple")) {
            return bda.A.ad;
        }
        if (name.equals("blue")) {
            return bda.B.ad;
        }
        if (name.equals("brown")) {
            return bda.C.ad;
        }
        if (name.equals("green")) {
            return bda.D.ad;
        }
        if (name.equals("red")) {
            return bda.E.ad;
        }
        if (name.equals("black")) {
            return bda.F.ad;
        }
        return -1;
    }

    private static int[] getMapColors() {
        bda[] mapColors = bda.a;
        int[] colors = new int[mapColors.length];
        Arrays.fill(colors, -1);
        for (int i = 0; i < mapColors.length && i < colors.length; ++i) {
            bda mapColor = mapColors[i];
            if (mapColor == null) continue;
            colors[i] = mapColor.ac;
        }
        return colors;
    }

    private static void setMapColors(int[] colors) {
        if (colors == null) {
            return;
        }
        bda[] mapColors = bda.a;
        boolean changed = false;
        for (int i = 0; i < mapColors.length && i < colors.length; ++i) {
            int color;
            bda mapColor = mapColors[i];
            if (mapColor == null || (color = colors[i]) < 0 || mapColor.ac == color) continue;
            mapColor.ac = color;
            changed = true;
        }
        if (changed) {
            bib.z().N().reloadBannerTextures();
        }
    }

    private static void dbg(String str) {
        Config.dbg("CustomColors: " + str);
    }

    private static void warn(String str) {
        Config.warn("CustomColors: " + str);
    }

    public static int getExpBarTextColor(int color) {
        if (expBarTextColor < 0) {
            return color;
        }
        return expBarTextColor;
    }

    public static int getBossTextColor(int color) {
        if (bossTextColor < 0) {
            return color;
        }
        return bossTextColor;
    }

    public static int getSignTextColor(int color) {
        if (signTextColor < 0) {
            return color;
        }
        return signTextColor;
    }

    public static interface IColorizer {
        public int getColor(awt var1, amy var2, et var3);

        public boolean isColorConstant();
    }
}

