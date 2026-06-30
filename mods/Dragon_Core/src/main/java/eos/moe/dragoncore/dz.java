/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.dragoncore;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Base64;
import javax.imageio.ImageIO;

public class dz {
    public dz() {
        dz a2;
    }

    public static BufferedImage ALLATORIxDEMO(String a2) {
        try {
            return ImageIO.read(new ByteArrayInputStream(Base64.getDecoder().decode(a2.split(",")[1])));
        }
        catch (IOException a3) {
            a3.printStackTrace();
            return null;
        }
    }
}

