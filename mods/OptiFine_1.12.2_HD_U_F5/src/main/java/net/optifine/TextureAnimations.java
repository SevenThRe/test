/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  bid
 *  cer
 *  nf
 */
package net.optifine;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;
import javax.imageio.ImageIO;
import net.optifine.SmartAnimations;
import net.optifine.TextureAnimation;
import net.optifine.util.PropertiesOrdered;
import net.optifine.util.ResUtils;
import net.optifine.util.TextureUtils;

public class TextureAnimations {
    private static TextureAnimation[] textureAnimations = null;
    private static int countAnimationsActive = 0;
    private static int frameCountAnimations = 0;

    public static void reset() {
        textureAnimations = null;
    }

    public static void update() {
        textureAnimations = null;
        countAnimationsActive = 0;
        cer[] rps = Config.getResourcePacks();
        textureAnimations = TextureAnimations.getTextureAnimations(rps);
        TextureAnimations.updateAnimations();
    }

    public static void updateAnimations() {
        if (textureAnimations == null || !Config.isAnimatedTextures()) {
            countAnimationsActive = 0;
            return;
        }
        int countActive = 0;
        for (int i = 0; i < textureAnimations.length; ++i) {
            TextureAnimation anim = textureAnimations[i];
            anim.updateTexture();
            if (!anim.isActive()) continue;
            ++countActive;
        }
        int frameCount = Config.getMinecraft().o.aj;
        if (frameCount != frameCountAnimations) {
            countAnimationsActive = countActive;
            frameCountAnimations = frameCount;
        }
        if (SmartAnimations.isActive()) {
            SmartAnimations.resetTexturesRendered();
        }
    }

    private static TextureAnimation[] getTextureAnimations(cer[] rps) {
        ArrayList<TextureAnimation> list = new ArrayList<TextureAnimation>();
        for (int i = 0; i < rps.length; ++i) {
            cer rp = rps[i];
            TextureAnimation[] tas = TextureAnimations.getTextureAnimations(rp);
            if (tas == null) continue;
            list.addAll(Arrays.asList(tas));
        }
        TextureAnimation[] anims = list.toArray(new TextureAnimation[list.size()]);
        return anims;
    }

    private static TextureAnimation[] getTextureAnimations(cer rp) {
        String[] animPropNames = ResUtils.collectFiles(rp, "mcpatcher/anim/", ".properties", null);
        if (animPropNames.length <= 0) {
            return null;
        }
        ArrayList<TextureAnimation> list = new ArrayList<TextureAnimation>();
        for (int i = 0; i < animPropNames.length; ++i) {
            String propName = animPropNames[i];
            Config.dbg("Texture animation: " + propName);
            try {
                nf propLoc = new nf(propName);
                InputStream in = rp.a(propLoc);
                PropertiesOrdered props = new PropertiesOrdered();
                props.load(in);
                TextureAnimation anim = TextureAnimations.makeTextureAnimation(props, propLoc);
                if (anim == null) continue;
                nf locDstTex = new nf(anim.getDstTex());
                if (Config.getDefiningResourcePack(locDstTex) != rp) {
                    Config.dbg("Skipped: " + propName + ", target texture not loaded from same resource pack");
                    continue;
                }
                list.add(anim);
                continue;
            }
            catch (FileNotFoundException e) {
                Config.warn("File not found: " + e.getMessage());
                continue;
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        TextureAnimation[] anims = list.toArray(new TextureAnimation[list.size()]);
        return anims;
    }

    private static TextureAnimation makeTextureAnimation(Properties props, nf propLoc) {
        String texFrom = props.getProperty("from");
        String texTo = props.getProperty("to");
        int x = Config.parseInt(props.getProperty("x"), -1);
        int y = Config.parseInt(props.getProperty("y"), -1);
        int width = Config.parseInt(props.getProperty("w"), -1);
        int height = Config.parseInt(props.getProperty("h"), -1);
        if (texFrom == null || texTo == null) {
            Config.warn("TextureAnimation: Source or target texture not specified");
            return null;
        }
        if (x < 0 || y < 0 || width < 0 || height < 0) {
            Config.warn("TextureAnimation: Invalid coordinates");
            return null;
        }
        texFrom = texFrom.trim();
        texTo = texTo.trim();
        String basePath = TextureUtils.getBasePath(propLoc.a());
        texFrom = TextureUtils.fixResourcePath(texFrom, basePath);
        texTo = TextureUtils.fixResourcePath(texTo, basePath);
        byte[] imageBytes = TextureAnimations.getCustomTextureData(texFrom, width);
        if (imageBytes == null) {
            Config.warn("TextureAnimation: Source texture not found: " + texTo);
            return null;
        }
        int countPixels = imageBytes.length / 4;
        int countFrames = countPixels / (width * height);
        int countPixelsAllFrames = countFrames * (width * height);
        if (countPixels != countPixelsAllFrames) {
            Config.warn("TextureAnimation: Source texture has invalid number of frames: " + texFrom + ", frames: " + (float)countPixels / (float)(width * height));
            return null;
        }
        nf locTexTo = new nf(texTo);
        try {
            InputStream inTexTo = Config.getResourceStream(locTexTo);
            if (inTexTo == null) {
                Config.warn("TextureAnimation: Target texture not found: " + texTo);
                return null;
            }
            BufferedImage imgTexTo = TextureAnimations.readTextureImage(inTexTo);
            if (x + width > imgTexTo.getWidth() || y + height > imgTexTo.getHeight()) {
                Config.warn("TextureAnimation: Animation coordinates are outside the target texture: " + texTo);
                return null;
            }
            TextureAnimation anim = new TextureAnimation(texFrom, imageBytes, texTo, locTexTo, x, y, width, height, props);
            return anim;
        }
        catch (IOException e) {
            Config.warn("TextureAnimation: Target texture not found: " + texTo);
            return null;
        }
    }

    private static byte[] getCustomTextureData(String imagePath, int tileWidth) {
        byte[] imageBytes = TextureAnimations.loadImage(imagePath, tileWidth);
        if (imageBytes == null) {
            imageBytes = TextureAnimations.loadImage("/anim" + imagePath, tileWidth);
        }
        return imageBytes;
    }

    private static byte[] loadImage(String name, int targetWidth) {
        bid options = Config.getGameSettings();
        try {
            nf locRes = new nf(name);
            InputStream in = Config.getResourceStream(locRes);
            if (in == null) {
                return null;
            }
            BufferedImage image = TextureAnimations.readTextureImage(in);
            in.close();
            if (image == null) {
                return null;
            }
            if (targetWidth > 0 && image.getWidth() != targetWidth) {
                double aspectHW = image.getHeight() / image.getWidth();
                int targetHeight = (int)((double)targetWidth * aspectHW);
                image = TextureAnimations.scaleBufferedImage(image, targetWidth, targetHeight);
            }
            int width = image.getWidth();
            int height = image.getHeight();
            int[] ai = new int[width * height];
            byte[] byteBuf = new byte[width * height * 4];
            image.getRGB(0, 0, width, height, ai, 0, width);
            for (int l = 0; l < ai.length; ++l) {
                int alpha = ai[l] >> 24 & 0xFF;
                int red = ai[l] >> 16 & 0xFF;
                int green = ai[l] >> 8 & 0xFF;
                int blue = ai[l] & 0xFF;
                if (options != null && options.g) {
                    int j3 = (red * 30 + green * 59 + blue * 11) / 100;
                    int l3 = (red * 30 + green * 70) / 100;
                    int j4 = (red * 30 + blue * 70) / 100;
                    red = j3;
                    green = l3;
                    blue = j4;
                }
                byteBuf[l * 4 + 0] = (byte)red;
                byteBuf[l * 4 + 1] = (byte)green;
                byteBuf[l * 4 + 2] = (byte)blue;
                byteBuf[l * 4 + 3] = (byte)alpha;
            }
            return byteBuf;
        }
        catch (FileNotFoundException e) {
            return null;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static BufferedImage readTextureImage(InputStream par1InputStream) throws IOException {
        BufferedImage var2 = ImageIO.read(par1InputStream);
        par1InputStream.close();
        return var2;
    }

    private static BufferedImage scaleBufferedImage(BufferedImage image, int width, int height) {
        BufferedImage scaledImage = new BufferedImage(width, height, 2);
        Graphics2D gr = scaledImage.createGraphics();
        gr.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        gr.drawImage(image, 0, 0, width, height, null);
        return scaledImage;
    }

    public static int getCountAnimations() {
        if (textureAnimations == null) {
            return 0;
        }
        return textureAnimations.length;
    }

    public static int getCountAnimationsActive() {
        return countAnimationsActive;
    }
}

