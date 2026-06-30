/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  amy
 *  anh
 *  anm
 *  aow
 *  awp
 *  awt
 *  cdt
 *  et
 *  nf
 *  rk
 */
package net.optifine;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.optifine.BlockPosM;
import net.optifine.CustomColors;
import net.optifine.config.ConnectedParser;
import net.optifine.config.MatchBlock;
import net.optifine.config.Matches;
import net.optifine.util.TextureUtils;

public class CustomColormap
implements CustomColors.IColorizer {
    public String name = null;
    public String basePath = null;
    private int format = -1;
    private MatchBlock[] matchBlocks = null;
    private String source = null;
    private int color = -1;
    private int yVariance = 0;
    private int yOffset = 0;
    private int width = 0;
    private int height = 0;
    private int[] colors = null;
    private float[][] colorsRgb = null;
    private static final int FORMAT_UNKNOWN = -1;
    private static final int FORMAT_VANILLA = 0;
    private static final int FORMAT_GRID = 1;
    private static final int FORMAT_FIXED = 2;
    public static final String FORMAT_VANILLA_STRING = "vanilla";
    public static final String FORMAT_GRID_STRING = "grid";
    public static final String FORMAT_FIXED_STRING = "fixed";
    public static final String[] FORMAT_STRINGS = new String[]{"vanilla", "grid", "fixed"};
    public static final String KEY_FORMAT = "format";
    public static final String KEY_BLOCKS = "blocks";
    public static final String KEY_SOURCE = "source";
    public static final String KEY_COLOR = "color";
    public static final String KEY_Y_VARIANCE = "yVariance";
    public static final String KEY_Y_OFFSET = "yOffset";

    public CustomColormap(Properties props, String path, int width, int height, String formatDefault) {
        ConnectedParser cp = new ConnectedParser("Colormap");
        this.name = cp.parseName(path);
        this.basePath = cp.parseBasePath(path);
        this.format = this.parseFormat(props.getProperty(KEY_FORMAT, formatDefault));
        this.matchBlocks = cp.parseMatchBlocks(props.getProperty(KEY_BLOCKS));
        this.source = CustomColormap.parseTexture(props.getProperty(KEY_SOURCE), path, this.basePath);
        this.color = ConnectedParser.parseColor(props.getProperty(KEY_COLOR), -1);
        this.yVariance = cp.parseInt(props.getProperty(KEY_Y_VARIANCE), 0);
        this.yOffset = cp.parseInt(props.getProperty(KEY_Y_OFFSET), 0);
        this.width = width;
        this.height = height;
    }

    private int parseFormat(String str) {
        if (str == null) {
            return 0;
        }
        if ((str = str.trim()).equals(FORMAT_VANILLA_STRING)) {
            return 0;
        }
        if (str.equals(FORMAT_GRID_STRING)) {
            return 1;
        }
        if (str.equals(FORMAT_FIXED_STRING)) {
            return 2;
        }
        CustomColormap.warn("Unknown format: " + str);
        return -1;
    }

    public boolean isValid(String path) {
        if (this.format == 0 || this.format == 1) {
            if (this.source == null) {
                CustomColormap.warn("Source not defined: " + path);
                return false;
            }
            this.readColors();
            if (this.colors == null) {
                return false;
            }
            if (this.color < 0) {
                if (this.format == 0) {
                    this.color = this.getColor(127, 127);
                }
                if (this.format == 1) {
                    this.color = this.getColorGrid(anm.c, new et(0, 64, 0));
                }
            }
        } else if (this.format == 2) {
            if (this.color < 0) {
                this.color = 0xFFFFFF;
            }
        } else {
            return false;
        }
        return true;
    }

    public boolean isValidMatchBlocks(String path) {
        if (this.matchBlocks == null) {
            this.matchBlocks = this.detectMatchBlocks();
            if (this.matchBlocks == null) {
                CustomColormap.warn("Match blocks not defined: " + path);
                return false;
            }
        }
        return true;
    }

    private MatchBlock[] detectMatchBlocks() {
        String idStr;
        int id;
        aow block = aow.b((String)this.name);
        if (block != null) {
            return new MatchBlock[]{new MatchBlock(aow.a((aow)block))};
        }
        Pattern p = Pattern.compile("^block([0-9]+).*$");
        Matcher m2 = p.matcher(this.name);
        if (m2.matches() && (id = Config.parseInt(idStr = m2.group(1), -1)) >= 0) {
            return new MatchBlock[]{new MatchBlock(id)};
        }
        ConnectedParser cp = new ConnectedParser("Colormap");
        MatchBlock[] mbs = cp.parseMatchBlock(this.name);
        if (mbs != null) {
            return mbs;
        }
        return null;
    }

    private void readColors() {
        try {
            boolean heightOk;
            this.colors = null;
            if (this.source == null) {
                return;
            }
            String imagePath = this.source + ".png";
            nf loc = new nf(imagePath);
            InputStream is = Config.getResourceStream(loc);
            if (is == null) {
                return;
            }
            BufferedImage img = cdt.a((InputStream)is);
            if (img == null) {
                return;
            }
            int imgWidth = img.getWidth();
            int imgHeight = img.getHeight();
            boolean widthOk = this.width < 0 || this.width == imgWidth;
            boolean bl = heightOk = this.height < 0 || this.height == imgHeight;
            if (!widthOk || !heightOk) {
                CustomColormap.dbg("Non-standard palette size: " + imgWidth + "x" + imgHeight + ", should be: " + this.width + "x" + this.height + ", path: " + imagePath);
            }
            this.width = imgWidth;
            this.height = imgHeight;
            if (this.width <= 0 || this.height <= 0) {
                CustomColormap.warn("Invalid palette size: " + imgWidth + "x" + imgHeight + ", path: " + imagePath);
                return;
            }
            this.colors = new int[imgWidth * imgHeight];
            img.getRGB(0, 0, imgWidth, imgHeight, this.colors, 0, imgWidth);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void dbg(String str) {
        Config.dbg("CustomColors: " + str);
    }

    private static void warn(String str) {
        Config.warn("CustomColors: " + str);
    }

    private static String parseTexture(String texStr, String path, String basePath) {
        int pos2;
        if (texStr != null) {
            String png;
            if ((texStr = texStr.trim()).endsWith(png = ".png")) {
                texStr = texStr.substring(0, texStr.length() - png.length());
            }
            texStr = CustomColormap.fixTextureName(texStr, basePath);
            return texStr;
        }
        String str = path;
        int pos = str.lastIndexOf(47);
        if (pos >= 0) {
            str = str.substring(pos + 1);
        }
        if ((pos2 = str.lastIndexOf(46)) >= 0) {
            str = str.substring(0, pos2);
        }
        str = CustomColormap.fixTextureName(str, basePath);
        return str;
    }

    private static String fixTextureName(String iconName, String basePath) {
        String pathBlocks;
        if (!((iconName = TextureUtils.fixResourcePath(iconName, basePath)).startsWith(basePath) || iconName.startsWith("textures/") || iconName.startsWith("mcpatcher/"))) {
            iconName = basePath + "/" + iconName;
        }
        if (iconName.endsWith(".png")) {
            iconName = iconName.substring(0, iconName.length() - 4);
        }
        if (iconName.startsWith(pathBlocks = "textures/blocks/")) {
            iconName = iconName.substring(pathBlocks.length());
        }
        if (iconName.startsWith("/")) {
            iconName = iconName.substring(1);
        }
        return iconName;
    }

    public boolean matchesBlock(awp blockState) {
        return Matches.block(blockState, this.matchBlocks);
    }

    public int getColorRandom() {
        if (this.format == 2) {
            return this.color;
        }
        int index = CustomColors.random.nextInt(this.colors.length);
        return this.colors[index];
    }

    public int getColor(int index) {
        index = Config.limit(index, 0, this.colors.length - 1);
        return this.colors[index] & 0xFFFFFF;
    }

    public int getColor(int cx, int cy) {
        cx = Config.limit(cx, 0, this.width - 1);
        cy = Config.limit(cy, 0, this.height - 1);
        return this.colors[cy * this.width + cx] & 0xFFFFFF;
    }

    public float[][] getColorsRgb() {
        if (this.colorsRgb == null) {
            this.colorsRgb = CustomColormap.toRgb(this.colors);
        }
        return this.colorsRgb;
    }

    @Override
    public int getColor(awt blockState, amy blockAccess, et blockPos) {
        return this.getColor(blockAccess, blockPos);
    }

    public int getColor(amy blockAccess, et blockPos) {
        anh biome = CustomColors.getColorBiome(blockAccess, blockPos);
        return this.getColor(biome, blockPos);
    }

    @Override
    public boolean isColorConstant() {
        return this.format == 2;
    }

    public int getColor(anh biome, et blockPos) {
        if (this.format == 0) {
            return this.getColorVanilla(biome, blockPos);
        }
        if (this.format == 1) {
            return this.getColorGrid(biome, blockPos);
        }
        return this.color;
    }

    public int getColorSmooth(amy blockAccess, double x, double y, double z, int radius) {
        if (this.format == 2) {
            return this.color;
        }
        int x0 = rk.c((double)x);
        int y0 = rk.c((double)y);
        int z0 = rk.c((double)z);
        int sumRed = 0;
        int sumGreen = 0;
        int sumBlue = 0;
        int count = 0;
        BlockPosM blockPosM = new BlockPosM(0, 0, 0);
        for (int ix = x0 - radius; ix <= x0 + radius; ++ix) {
            for (int iz = z0 - radius; iz <= z0 + radius; ++iz) {
                blockPosM.setXyz(ix, y0, iz);
                int col = this.getColor(blockAccess, (et)blockPosM);
                sumRed += col >> 16 & 0xFF;
                sumGreen += col >> 8 & 0xFF;
                sumBlue += col & 0xFF;
                ++count;
            }
        }
        int r = sumRed / count;
        int g = sumGreen / count;
        int b2 = sumBlue / count;
        return r << 16 | g << 8 | b2;
    }

    private int getColorVanilla(anh biome, et blockPos) {
        double temperature = rk.a((float)biome.a(blockPos), (float)0.0f, (float)1.0f);
        double rainfall = rk.a((float)biome.k(), (float)0.0f, (float)1.0f);
        int cx = (int)((1.0 - temperature) * (double)(this.width - 1));
        int cy = (int)((1.0 - (rainfall *= temperature)) * (double)(this.height - 1));
        return this.getColor(cx, cy);
    }

    private int getColorGrid(anh biome, et blockPos) {
        int biomeId;
        int cx = biomeId = anh.a((anh)biome);
        int cy = blockPos.q() - this.yOffset;
        if (this.yVariance > 0) {
            int seed = blockPos.p() << 16 + blockPos.r();
            int rand = Config.intHash(seed);
            int range = this.yVariance * 2 + 1;
            int diff = (rand & 0xFF) % range - this.yVariance;
            cy += diff;
        }
        return this.getColor(cx, cy);
    }

    public int getLength() {
        if (this.format == 2) {
            return 1;
        }
        return this.colors.length;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    private static float[][] toRgb(int[] cols) {
        float[][] colsRgb = new float[cols.length][3];
        for (int i = 0; i < cols.length; ++i) {
            int col = cols[i];
            float rf = (float)(col >> 16 & 0xFF) / 255.0f;
            float gf = (float)(col >> 8 & 0xFF) / 255.0f;
            float bf = (float)(col & 0xFF) / 255.0f;
            float[] colRgb = colsRgb[i];
            colRgb[0] = rf;
            colRgb[1] = gf;
            colRgb[2] = bf;
        }
        return colsRgb;
    }

    public void addMatchBlock(MatchBlock mb) {
        if (this.matchBlocks == null) {
            this.matchBlocks = new MatchBlock[0];
        }
        this.matchBlocks = (MatchBlock[])Config.addObjectToArray(this.matchBlocks, mb);
    }

    public void addMatchBlock(int blockId, int metadata) {
        MatchBlock mb = this.getMatchBlock(blockId);
        if (mb != null) {
            if (metadata >= 0) {
                mb.addMetadata(metadata);
            }
            return;
        }
        this.addMatchBlock(new MatchBlock(blockId, metadata));
    }

    private MatchBlock getMatchBlock(int blockId) {
        if (this.matchBlocks == null) {
            return null;
        }
        for (int i = 0; i < this.matchBlocks.length; ++i) {
            MatchBlock mb = this.matchBlocks[i];
            if (mb.getBlockId() != blockId) continue;
            return mb;
        }
        return null;
    }

    public int[] getMatchBlockIds() {
        if (this.matchBlocks == null) {
            return null;
        }
        HashSet<Integer> setIds = new HashSet<Integer>();
        for (int i = 0; i < this.matchBlocks.length; ++i) {
            MatchBlock mb = this.matchBlocks[i];
            if (mb.getBlockId() < 0) continue;
            setIds.add(mb.getBlockId());
        }
        Integer[] ints = setIds.toArray(new Integer[setIds.size()]);
        int[] ids = new int[ints.length];
        for (int i = 0; i < ints.length; ++i) {
            ids[i] = ints[i];
        }
        return ids;
    }

    public String toString() {
        return "" + this.basePath + "/" + this.name + ", blocks: " + Config.arrayToString(this.matchBlocks) + ", source: " + this.source;
    }
}

