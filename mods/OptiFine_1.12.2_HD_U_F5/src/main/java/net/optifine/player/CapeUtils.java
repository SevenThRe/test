/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  bib
 *  bua
 *  but
 *  cdh
 *  cdm
 *  cdr
 *  cds
 *  nf
 */
package net.optifine.player;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.regex.Pattern;
import net.optifine.player.CapeImageBuffer;

public class CapeUtils {
    private static final Pattern PATTERN_USERNAME = Pattern.compile("[a-zA-Z0-9_]+");

    public static void downloadCape(bua player) {
        String username = player.getNameClear();
        if (username != null && !username.isEmpty() && !username.contains("\u0000") && PATTERN_USERNAME.matcher(username).matches()) {
            String ofCapeUrl = "http://s.optifine.net/capes/" + username + ".png";
            nf rl2 = new nf("capeof/" + username);
            cdr textureManager = bib.z().N();
            cds tex = textureManager.b(rl2);
            if (tex != null && tex instanceof cdh) {
                cdh tdid = (cdh)tex;
                if (tdid.imageFound != null) {
                    if (tdid.imageFound.booleanValue()) {
                        player.setLocationOfCape(rl2);
                        if (tdid.getImageBuffer() instanceof CapeImageBuffer) {
                            CapeImageBuffer cib = (CapeImageBuffer)tdid.getImageBuffer();
                            player.setElytraOfCape(cib.isElytraOfCape());
                        }
                    }
                    return;
                }
            }
            CapeImageBuffer cib = new CapeImageBuffer(player, rl2);
            cdh textureCape = new cdh(null, ofCapeUrl, null, (but)cib);
            textureCape.pipeline = true;
            textureManager.a(rl2, (cds)textureCape);
        }
    }

    public static BufferedImage parseCape(BufferedImage img) {
        int imageHeight;
        int imageWidth = 64;
        BufferedImage srcImg = img;
        int srcWidth = srcImg.getWidth();
        int srcHeight = srcImg.getHeight();
        for (imageHeight = 32; imageWidth < srcWidth || imageHeight < srcHeight; imageWidth *= 2, imageHeight *= 2) {
        }
        BufferedImage imgNew = new BufferedImage(imageWidth, imageHeight, 2);
        Graphics g = imgNew.getGraphics();
        g.drawImage(img, 0, 0, null);
        g.dispose();
        return imgNew;
    }

    public static boolean isElytraCape(BufferedImage imageRaw, BufferedImage imageFixed) {
        return imageRaw.getWidth() > imageFixed.getHeight();
    }

    public static void reloadCape(bua player) {
        String nameClear = player.getNameClear();
        nf rl2 = new nf("capeof/" + nameClear);
        cdr textureManager = Config.getTextureManager();
        cds tex = textureManager.b(rl2);
        if (tex instanceof cdm) {
            cdm simpleTex = (cdm)tex;
            simpleTex.c();
            textureManager.c(rl2);
        }
        player.setLocationOfCape(null);
        player.setElytraOfCape(false);
        CapeUtils.downloadCape(player);
    }
}

