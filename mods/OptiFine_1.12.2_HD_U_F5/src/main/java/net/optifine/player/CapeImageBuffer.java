/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  bua
 *  buz
 *  nf
 */
package net.optifine.player;

import java.awt.image.BufferedImage;
import net.optifine.player.CapeUtils;

public class CapeImageBuffer
extends buz {
    private bua player;
    private nf resourceLocation;
    private boolean elytraOfCape;

    public CapeImageBuffer(bua player, nf resourceLocation) {
        this.player = player;
        this.resourceLocation = resourceLocation;
    }

    public BufferedImage a(BufferedImage imageRaw) {
        BufferedImage image = CapeUtils.parseCape(imageRaw);
        this.elytraOfCape = CapeUtils.isElytraCape(imageRaw, image);
        return image;
    }

    public void a() {
        if (this.player != null) {
            this.player.setLocationOfCape(this.resourceLocation);
            this.player.setElytraOfCape(this.elytraOfCape);
        }
        this.cleanup();
    }

    public void cleanup() {
        this.player = null;
    }

    public boolean isElytraOfCape() {
        return this.elytraOfCape;
    }
}

