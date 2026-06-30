/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.ResourceLocation
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.da;
import eos.moe.dragoncore.kq;
import eos.moe.dragoncore.st;
import eos.moe.dragoncore.wr;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Map;
import net.minecraft.util.ResourceLocation;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class ht {
    public ht() {
        ht a2;
    }

    public static Map<String, kq> ALLATORIxDEMO(ResourceLocation a2) {
        return wr.ALLATORIxDEMO(a2, true);
    }

    public static Map<String, kq> ALLATORIxDEMO(ResourceLocation a2, boolean a3) {
        return wr.ALLATORIxDEMO(a2, a3);
    }

    public static Map<String, kq> ALLATORIxDEMO(String a2, InputStream a3) {
        return wr.ALLATORIxDEMO(a2, a3);
    }

    public static Map<String, kq> ALLATORIxDEMO(String a2, byte[] a3) {
        return wr.ALLATORIxDEMO(a2, new ByteArrayInputStream(a3));
    }

    public static kq ALLATORIxDEMO(kq a2) {
        return a2.ALLATORIxDEMO();
    }

    public static st ALLATORIxDEMO(kq a2) {
        return new st(a2);
    }

    public static void ALLATORIxDEMO(st a2, da a3, String a4) {
        a2.ALLATORIxDEMO(a3, a4);
    }

    public static void ALLATORIxDEMO(da a2, String a3) {
        a2.ALLATORIxDEMO(a3);
    }
}

