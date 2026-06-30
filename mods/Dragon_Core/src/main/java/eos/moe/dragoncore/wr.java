/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.ResourceLocation
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.ca;
import eos.moe.dragoncore.kq;
import eos.moe.dragoncore.wy;
import java.io.InputStream;
import java.util.Map;
import net.minecraft.util.ResourceLocation;

public class wr {
    public wr() {
        wr a2;
    }

    public static Map<String, kq> ALLATORIxDEMO(ResourceLocation a2) throws Exception {
        return new wy().ALLATORIxDEMO(a2);
    }

    public static Map<String, kq> ALLATORIxDEMO(ResourceLocation a2, boolean a3) {
        try {
            return new wy().ALLATORIxDEMO(a2);
        }
        catch (Throwable a4) {
            ca.l.ALLATORIxDEMO("\u65e0\u6cd5\u52a0\u8f7d\u52a8\u753b " + a2.toString(), a4);
            if (a3) {
                ca.l.z("\u65e0\u6cd5\u52a0\u8f7d\u52a8\u753b " + a2 + " -> " + a4.getMessage());
            }
            throw new RuntimeException();
        }
    }

    public static Map<String, kq> ALLATORIxDEMO(String a2, InputStream a3) {
        try {
            return new wy().ALLATORIxDEMO(new ResourceLocation("dragoncore", a2), a3);
        }
        catch (Throwable a4) {
            ca.l.ALLATORIxDEMO("\u65e0\u6cd5\u52a0\u8f7d\u52a8\u753b " + a2.toString(), a4);
            ca.l.z("\u65e0\u6cd5\u52a0\u8f7d\u52a8\u753b " + a2 + " -> " + a4.getMessage());
            throw new RuntimeException();
        }
    }
}

