/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.bz;
import eos.moe.dragoncore.kp;
import eos.moe.dragoncore.kv;
import eos.moe.dragoncore.nh;
import eos.moe.dragoncore.qv;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class hw {
    public hw() {
        hw a2;
    }

    public static nh ALLATORIxDEMO(qv a2, String a3) {
        return new nh(a2, new kv(a3));
    }

    public static nh ALLATORIxDEMO(String a2) {
        return new nh(new kv(a2));
    }

    public static nh ALLATORIxDEMO(Path a2) {
        byte[] a3;
        try {
            a3 = Files.readAllBytes(a2);
        }
        catch (IOException a4) {
            a3 = new byte[]{};
        }
        return new nh(new kv(new String(a3, StandardCharsets.UTF_8)));
    }

    public static nh ALLATORIxDEMO(InputStream a2) throws IOException {
        return new nh(new kv(bz.ALLATORIxDEMO(a2)));
    }

    public static qv ALLATORIxDEMO(kp a2) {
        return new qv(a2);
    }

    public static qv ALLATORIxDEMO() {
        return new qv();
    }
}

