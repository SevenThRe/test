/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.gy;
import java.awt.image.RenderedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.imageio.ImageIO;

public class dx {
    private byte[] y;
    private byte[] k;
    private Map<String, byte[]> ALLATORIxDEMO;

    public dx(String a2, String a3, List<gy> a4, Map<String, String> a5) {
        dx a6;
        a6.y = a2.getBytes(StandardCharsets.UTF_8);
        a6.k = a3.getBytes(StandardCharsets.UTF_8);
        a6.ALLATORIxDEMO = new HashMap<String, byte[]>();
        for (gy a7 : a4) {
            ByteArrayOutputStream a8 = new ByteArrayOutputStream();
            try {
                ImageIO.write((RenderedImage)a7.ALLATORIxDEMO(), "png", a8);
            }
            catch (IOException a9) {
                a9.printStackTrace();
            }
            byte[] a10 = a8.toByteArray();
            a6.ALLATORIxDEMO.put(a7.f(), a10);
            a6.ALLATORIxDEMO.put(a7.ALLATORIxDEMO(), a10);
            if (a5.containsKey(a7.ALLATORIxDEMO())) {
                byte[] a11 = a5.get(a7.ALLATORIxDEMO()).getBytes(StandardCharsets.UTF_8);
                a6.ALLATORIxDEMO.put(a7.f() + ".mcmeta", a11);
                a6.ALLATORIxDEMO.put(a7.ALLATORIxDEMO() + ".mcmeta", a11);
            }
            try {
                a8.close();
            }
            catch (IOException iOException) {}
        }
    }

    public byte[] c() {
        dx a2;
        return a2.y;
    }

    public byte[] ALLATORIxDEMO() {
        dx a2;
        return a2.k;
    }

    public Map<String, byte[]> ALLATORIxDEMO() {
        dx a2;
        return a2.ALLATORIxDEMO;
    }
}

