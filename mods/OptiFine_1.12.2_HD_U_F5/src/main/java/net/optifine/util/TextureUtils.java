/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  bia
 *  bib
 *  bus
 *  cce
 *  cdm
 *  cdp
 *  cdq
 *  cds
 *  cdv
 *  cen
 *  cep
 *  ceq
 *  nf
 *  org.lwjgl.BufferUtils
 *  org.lwjgl.opengl.GL11
 *  org.lwjgl.opengl.GLContext
 *  rk
 */
package net.optifine.util;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import net.optifine.BetterGrass;
import net.optifine.BetterSnow;
import net.optifine.CustomBlockLayers;
import net.optifine.CustomColors;
import net.optifine.CustomGuis;
import net.optifine.CustomItems;
import net.optifine.CustomLoadingScreens;
import net.optifine.CustomPanorama;
import net.optifine.CustomSky;
import net.optifine.Lang;
import net.optifine.NaturalTextures;
import net.optifine.RandomEntities;
import net.optifine.SmartLeaves;
import net.optifine.TextureAnimations;
import net.optifine.entity.model.CustomEntityModels;
import net.optifine.shaders.MultiTexID;
import net.optifine.shaders.Shaders;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GLContext;

public class TextureUtils {
    public static final String texGrassTop = "grass_top";
    public static final String texStone = "stone";
    public static final String texDirt = "dirt";
    public static final String texCoarseDirt = "coarse_dirt";
    public static final String texGrassSide = "grass_side";
    public static final String texStoneslabSide = "stone_slab_side";
    public static final String texStoneslabTop = "stone_slab_top";
    public static final String texBedrock = "bedrock";
    public static final String texSand = "sand";
    public static final String texGravel = "gravel";
    public static final String texLogOak = "log_oak";
    public static final String texLogBigOak = "log_big_oak";
    public static final String texLogAcacia = "log_acacia";
    public static final String texLogSpruce = "log_spruce";
    public static final String texLogBirch = "log_birch";
    public static final String texLogJungle = "log_jungle";
    public static final String texLogOakTop = "log_oak_top";
    public static final String texLogBigOakTop = "log_big_oak_top";
    public static final String texLogAcaciaTop = "log_acacia_top";
    public static final String texLogSpruceTop = "log_spruce_top";
    public static final String texLogBirchTop = "log_birch_top";
    public static final String texLogJungleTop = "log_jungle_top";
    public static final String texLeavesOak = "leaves_oak";
    public static final String texLeavesBigOak = "leaves_big_oak";
    public static final String texLeavesAcacia = "leaves_acacia";
    public static final String texLeavesBirch = "leaves_birch";
    public static final String texLeavesSpuce = "leaves_spruce";
    public static final String texLeavesJungle = "leaves_jungle";
    public static final String texGoldOre = "gold_ore";
    public static final String texIronOre = "iron_ore";
    public static final String texCoalOre = "coal_ore";
    public static final String texObsidian = "obsidian";
    public static final String texGrassSideOverlay = "grass_side_overlay";
    public static final String texSnow = "snow";
    public static final String texGrassSideSnowed = "grass_side_snowed";
    public static final String texMyceliumSide = "mycelium_side";
    public static final String texMyceliumTop = "mycelium_top";
    public static final String texDiamondOre = "diamond_ore";
    public static final String texRedstoneOre = "redstone_ore";
    public static final String texLapisOre = "lapis_ore";
    public static final String texCactusSide = "cactus_side";
    public static final String texClay = "clay";
    public static final String texFarmlandWet = "farmland_wet";
    public static final String texFarmlandDry = "farmland_dry";
    public static final String texNetherrack = "netherrack";
    public static final String texSoulSand = "soul_sand";
    public static final String texGlowstone = "glowstone";
    public static final String texLeavesSpruce = "leaves_spruce";
    public static final String texLeavesSpruceOpaque = "leaves_spruce_opaque";
    public static final String texEndStone = "end_stone";
    public static final String texSandstoneTop = "sandstone_top";
    public static final String texSandstoneBottom = "sandstone_bottom";
    public static final String texRedstoneLampOff = "redstone_lamp_off";
    public static final String texRedstoneLampOn = "redstone_lamp_on";
    public static final String texWaterStill = "water_still";
    public static final String texWaterFlow = "water_flow";
    public static final String texLavaStill = "lava_still";
    public static final String texLavaFlow = "lava_flow";
    public static final String texFireLayer0 = "fire_layer_0";
    public static final String texFireLayer1 = "fire_layer_1";
    public static final String texPortal = "portal";
    public static final String texGlass = "glass";
    public static final String texGlassPaneTop = "glass_pane_top";
    public static final String texCompass = "compass";
    public static final String texClock = "clock";
    public static cdq iconGrassTop;
    public static cdq iconGrassSide;
    public static cdq iconGrassSideOverlay;
    public static cdq iconSnow;
    public static cdq iconGrassSideSnowed;
    public static cdq iconMyceliumSide;
    public static cdq iconMyceliumTop;
    public static cdq iconWaterStill;
    public static cdq iconWaterFlow;
    public static cdq iconLavaStill;
    public static cdq iconLavaFlow;
    public static cdq iconPortal;
    public static cdq iconFireLayer0;
    public static cdq iconFireLayer1;
    public static cdq iconGlass;
    public static cdq iconGlassPaneTop;
    public static cdq iconCompass;
    public static cdq iconClock;
    public static final String SPRITE_PREFIX_BLOCKS = "minecraft:blocks/";
    public static final String SPRITE_PREFIX_ITEMS = "minecraft:items/";
    private static IntBuffer staticBuffer;

    public static void update() {
        cdp mapBlocks = TextureUtils.getTextureMapBlocks();
        if (mapBlocks == null) {
            return;
        }
        String prefix = SPRITE_PREFIX_BLOCKS;
        iconGrassTop = mapBlocks.getSpriteSafe(prefix + texGrassTop);
        iconGrassSide = mapBlocks.getSpriteSafe(prefix + texGrassSide);
        iconGrassSideOverlay = mapBlocks.getSpriteSafe(prefix + texGrassSideOverlay);
        iconSnow = mapBlocks.getSpriteSafe(prefix + texSnow);
        iconGrassSideSnowed = mapBlocks.getSpriteSafe(prefix + texGrassSideSnowed);
        iconMyceliumSide = mapBlocks.getSpriteSafe(prefix + texMyceliumSide);
        iconMyceliumTop = mapBlocks.getSpriteSafe(prefix + texMyceliumTop);
        iconWaterStill = mapBlocks.getSpriteSafe(prefix + texWaterStill);
        iconWaterFlow = mapBlocks.getSpriteSafe(prefix + texWaterFlow);
        iconLavaStill = mapBlocks.getSpriteSafe(prefix + texLavaStill);
        iconLavaFlow = mapBlocks.getSpriteSafe(prefix + texLavaFlow);
        iconFireLayer0 = mapBlocks.getSpriteSafe(prefix + texFireLayer0);
        iconFireLayer1 = mapBlocks.getSpriteSafe(prefix + texFireLayer1);
        iconPortal = mapBlocks.getSpriteSafe(prefix + texPortal);
        iconGlass = mapBlocks.getSpriteSafe(prefix + texGlass);
        iconGlassPaneTop = mapBlocks.getSpriteSafe(prefix + texGlassPaneTop);
        String prefixItems = SPRITE_PREFIX_ITEMS;
        iconCompass = mapBlocks.getSpriteSafe(prefixItems + texCompass);
        iconClock = mapBlocks.getSpriteSafe(prefixItems + texClock);
    }

    public static BufferedImage fixTextureDimensions(String name, BufferedImage bi) {
        int height;
        int width;
        if ((name.startsWith("/mob/zombie") || name.startsWith("/mob/pigzombie")) && (width = bi.getWidth()) == (height = bi.getHeight()) * 2) {
            BufferedImage scaledImage = new BufferedImage(width, height * 2, 2);
            Graphics2D gr = scaledImage.createGraphics();
            gr.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            gr.drawImage(bi, 0, 0, width, height, null);
            return scaledImage;
        }
        return bi;
    }

    public static int ceilPowerOfTwo(int val) {
        int i;
        for (i = 1; i < val; i *= 2) {
        }
        return i;
    }

    public static int getPowerOfTwo(int val) {
        int i = 1;
        int po2 = 0;
        while (i < val) {
            i *= 2;
            ++po2;
        }
        return po2;
    }

    public static int twoToPower(int power) {
        int val = 1;
        for (int i = 0; i < power; ++i) {
            val *= 2;
        }
        return val;
    }

    public static cds getTexture(nf loc) {
        cds tex = Config.getTextureManager().b(loc);
        if (tex != null) {
            return tex;
        }
        if (!Config.hasResource(loc)) {
            return null;
        }
        tex = new cdm(loc);
        Config.getTextureManager().a(loc, tex);
        return tex;
    }

    public static void resourcesReloaded(cep rm) {
        if (TextureUtils.getTextureMapBlocks() == null) {
            return;
        }
        Config.dbg("*** Reloading custom textures ***");
        CustomSky.reset();
        TextureAnimations.reset();
        TextureUtils.update();
        NaturalTextures.update();
        BetterGrass.update();
        BetterSnow.update();
        TextureAnimations.update();
        CustomColors.update();
        CustomSky.update();
        RandomEntities.update();
        CustomItems.updateModels();
        CustomEntityModels.update();
        Shaders.resourcesReloaded();
        Lang.resourcesReloaded();
        Config.updateTexturePackClouds();
        SmartLeaves.updateLeavesModels();
        CustomPanorama.update();
        CustomGuis.update();
        cce.update();
        CustomLoadingScreens.update();
        CustomBlockLayers.update();
        Config.getTextureManager().e();
    }

    public static cdp getTextureMapBlocks() {
        return bib.z().R();
    }

    public static void registerResourceListener() {
        cep rm = Config.getResourceManager();
        if (rm instanceof cen) {
            cen rrm = (cen)rm;
            ceq rl2 = new ceq(){

                public void a(cep var1) {
                    TextureUtils.resourcesReloaded(var1);
                }
            };
            rrm.a(rl2);
        }
        cdv tto = new cdv(){

            public void e() {
                TextureAnimations.updateAnimations();
            }

            public void a(cep var1) throws IOException {
            }

            public int b() {
                return 0;
            }

            public void b(boolean p_174936_1, boolean p_174936_2) {
            }

            public void a() {
            }

            public MultiTexID getMultiTexID() {
                return null;
            }
        };
        nf ttol = new nf("optifine/TickableTextures");
        Config.getTextureManager().a(ttol, tto);
    }

    public static nf fixResourceLocation(nf loc, String basePath) {
        if (!loc.b().equals("minecraft")) {
            return loc;
        }
        String path = loc.a();
        String pathFixed = TextureUtils.fixResourcePath(path, basePath);
        if (pathFixed != path) {
            loc = new nf(loc.b(), pathFixed);
        }
        return loc;
    }

    public static String fixResourcePath(String path, String basePath) {
        String strAssMc = "assets/minecraft/";
        if (path.startsWith(strAssMc)) {
            path = path.substring(strAssMc.length());
            return path;
        }
        if (path.startsWith("./")) {
            path = path.substring(2);
            if (!basePath.endsWith("/")) {
                basePath = basePath + "/";
            }
            path = basePath + path;
            return path;
        }
        if (path.startsWith("/~")) {
            path = path.substring(1);
        }
        String strMcpatcher = "mcpatcher/";
        if (path.startsWith("~/")) {
            path = path.substring(2);
            path = strMcpatcher + path;
            return path;
        }
        if (path.startsWith("/")) {
            path = strMcpatcher + path.substring(1);
            return path;
        }
        return path;
    }

    public static String getBasePath(String path) {
        int pos = path.lastIndexOf(47);
        if (pos < 0) {
            return "";
        }
        return path.substring(0, pos);
    }

    public static void applyAnisotropicLevel() {
        if (GLContext.getCapabilities().GL_EXT_texture_filter_anisotropic) {
            float maxLevel = GL11.glGetFloat((int)34047);
            float level = Config.getAnisotropicFilterLevel();
            level = Math.min(level, maxLevel);
            GL11.glTexParameterf((int)3553, (int)34046, (float)level);
        }
    }

    public static void bindTexture(int glTexId) {
        bus.i((int)glTexId);
    }

    public static boolean isPowerOfTwo(int x) {
        int x2 = rk.c((int)x);
        return x2 == x;
    }

    public static BufferedImage scaleImage(BufferedImage bi, int w2) {
        int w = bi.getWidth();
        int h2 = bi.getHeight();
        int h22 = h2 * w2 / w;
        BufferedImage bi2 = new BufferedImage(w2, h22, 2);
        Graphics2D g2 = bi2.createGraphics();
        Object method = RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR;
        if (w2 < w || w2 % w != 0) {
            method = RenderingHints.VALUE_INTERPOLATION_BILINEAR;
        }
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, method);
        g2.drawImage(bi, 0, 0, w2, h22, null);
        return bi2;
    }

    public static int scaleToGrid(int size, int sizeGrid) {
        int sizeNew;
        if (size == sizeGrid) {
            return size;
        }
        for (sizeNew = size / sizeGrid * sizeGrid; sizeNew < size; sizeNew += sizeGrid) {
        }
        return sizeNew;
    }

    public static int scaleToMin(int size, int sizeMin) {
        int sizeNew;
        if (size >= sizeMin) {
            return size;
        }
        for (sizeNew = sizeMin / size * size; sizeNew < sizeMin; sizeNew += size) {
        }
        return sizeNew;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static Dimension getImageSize(InputStream in, String suffix) {
        Iterator<ImageReader> iter = ImageIO.getImageReadersBySuffix(suffix);
        while (iter.hasNext()) {
            ImageReader reader = iter.next();
            try {
                ImageInputStream iis = ImageIO.createImageInputStream(in);
                reader.setInput(iis);
                int width = reader.getWidth(reader.getMinIndex());
                int height = reader.getHeight(reader.getMinIndex());
                Dimension dimension = new Dimension(width, height);
                return dimension;
            }
            catch (IOException e) {}
            continue;
            finally {
                reader.dispose();
            }
        }
        return null;
    }

    public static void dbgMipmaps(cdq textureatlassprite) {
        int[][] data = textureatlassprite.a(0);
        for (int l = 0; l < data.length; ++l) {
            int[] ints = data[l];
            if (ints == null) {
                Config.dbg("" + l + ": " + ints);
                continue;
            }
            Config.dbg("" + l + ": " + ints.length);
        }
    }

    public static void saveGlTexture(String name, int textureId, int mipmapLevels, int width, int height) {
        File filePng;
        TextureUtils.bindTexture(textureId);
        GL11.glPixelStorei((int)3333, (int)1);
        GL11.glPixelStorei((int)3317, (int)1);
        File fileBase = new File(name);
        File dir = fileBase.getParentFile();
        if (dir != null) {
            dir.mkdirs();
        }
        for (int i = 0; i < 16; ++i) {
            filePng = new File(name + "_" + i + ".png");
            filePng.delete();
        }
        for (int level = 0; level <= mipmapLevels; ++level) {
            filePng = new File(name + "_" + level + ".png");
            int widthLevel = width >> level;
            int heightLevel = height >> level;
            int sizeLevel = widthLevel * heightLevel;
            IntBuffer buf = BufferUtils.createIntBuffer((int)sizeLevel);
            int[] data = new int[sizeLevel];
            GL11.glGetTexImage((int)3553, (int)level, (int)32993, (int)33639, (IntBuffer)buf);
            buf.get(data);
            BufferedImage image = new BufferedImage(widthLevel, heightLevel, 2);
            image.setRGB(0, 0, widthLevel, heightLevel, data, 0, widthLevel);
            try {
                ImageIO.write((RenderedImage)image, "png", filePng);
                Config.dbg("Exported: " + filePng);
                continue;
            }
            catch (Exception e) {
                Config.warn("Error writing: " + filePng);
                Config.warn("" + e.getClass().getName() + ": " + e.getMessage());
            }
        }
    }

    public static void generateCustomMipmaps(cdq tas, int mipmaps) {
        int w = tas.c();
        int h2 = tas.d();
        if (tas.k() < 1) {
            ArrayList<int[][]> listDatas = new ArrayList<int[][]>();
            int[][] datas = new int[mipmaps + 1][];
            int[] data = new int[w * h2];
            datas[0] = data;
            listDatas.add(datas);
            tas.a(listDatas);
        }
        ArrayList<int[][]> listDatas2 = new ArrayList<int[][]>();
        int frameCount = tas.k();
        for (int i = 0; i < frameCount; ++i) {
            int[] data = TextureUtils.getFrameData(tas, i, 0);
            if (data == null || data.length < 1) {
                data = new int[w * h2];
            }
            if (data.length != w * h2) {
                int dim = (int)Math.round(Math.sqrt(data.length));
                if (dim * dim != data.length) {
                    data = new int[1];
                    dim = 1;
                }
                BufferedImage image = new BufferedImage(dim, dim, 2);
                image.setRGB(0, 0, dim, dim, data, 0, dim);
                BufferedImage image2 = TextureUtils.scaleImage(image, w);
                int[] data2 = new int[w * h2];
                image2.getRGB(0, 0, w, h2, data2, 0, w);
                data = data2;
            }
            int[][] datas2 = new int[mipmaps + 1][];
            datas2[0] = data;
            listDatas2.add(datas2);
        }
        tas.a(listDatas2);
        tas.d(mipmaps);
    }

    public static int[] getFrameData(cdq tas, int frame, int level) {
        List listDatas = tas.getFramesTextureData();
        if (listDatas.size() <= frame) {
            return null;
        }
        int[][] datas = (int[][])listDatas.get(frame);
        if (datas == null || datas.length <= level) {
            return null;
        }
        int[] data = datas[level];
        return data;
    }

    public static int getGLMaximumTextureSize() {
        for (int i = 65536; i > 0; i >>= 1) {
            bus.a((int)32868, (int)0, (int)6408, (int)i, (int)i, (int)0, (int)6408, (int)5121, (IntBuffer)null);
            int err = GL11.glGetError();
            int width = bus.c((int)32868, (int)0, (int)4096);
            if (width == 0) continue;
            return i;
        }
        return -1;
    }

    static {
        staticBuffer = bia.f((int)256);
    }
}

