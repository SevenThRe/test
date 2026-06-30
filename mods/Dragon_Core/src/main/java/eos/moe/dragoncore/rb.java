/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.bc;
import eos.moe.dragoncore.mc;
import eos.moe.dragoncore.uc;
import eos.moe.dragoncore.yk;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.CRC32;

public class rb {
    private static final int ALLATORIxDEMO = 16384;

    public rb() {
        rb a2;
    }

    /*
     * WARNING - void declaration
     */
    public static long ALLATORIxDEMO(File a2, uc a3) throws IOException {
        if (a2 == null || !a2.exists() || !a2.canRead()) {
            throw new yk("input file is null or does not exist or cannot read. Cannot calculate CRC for the file");
        }
        byte[] a4 = new byte[16384];
        CRC32 a5 = new CRC32();
        Throwable throwable = null;
        try (FileInputStream a6 = new FileInputStream(a2);){
            int a7;
            while ((a7 = ((InputStream)a6).read(a4)) != -1) {
                a5.update(a4, 0, a7);
                if (a3 == null) continue;
                a3.c(a7);
                if (!a3.c()) continue;
                a3.ALLATORIxDEMO(bc.k);
                a3.ALLATORIxDEMO(mc.y);
                long l2 = 0L;
                return l2;
            }
            long l3 = a5.getValue();
            return l3;
        }
        catch (Throwable a7) {
            void var6_7;
            throwable = a7;
            throw var6_7;
        }
    }
}

