/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.dc;
import eos.moe.dragoncore.ec;
import eos.moe.dragoncore.la;
import eos.moe.dragoncore.ta;
import eos.moe.dragoncore.yk;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class cf {
    public cf() {
        cf a2;
    }

    public static ec c(dc a2, String a3) throws yk {
        ec a4 = cf.ALLATORIxDEMO(a2, a3);
        if (a4 == null && (a4 = cf.ALLATORIxDEMO(a2, a3 = a3.replaceAll("\\\\", "/"))) == null) {
            a3 = a3.replaceAll("/", "\\\\");
            a4 = cf.ALLATORIxDEMO(a2, a3);
        }
        return a4;
    }

    public static String ALLATORIxDEMO(byte[] a2, boolean a3, Charset a4) {
        if (la.k.equals(a4) && !a3) {
            try {
                return new String(a2, "Cp437");
            }
            catch (UnsupportedEncodingException a5) {
                return new String(a2);
            }
        }
        if (a4 != null) {
            return new String(a2, a4);
        }
        return new String(a2, la.k);
    }

    public static long ALLATORIxDEMO(dc a2) {
        if (a2.c()) {
            return a2.ALLATORIxDEMO().ALLATORIxDEMO();
        }
        return a2.ALLATORIxDEMO().c();
    }

    public static List<ec> ALLATORIxDEMO(List<ec> a2, ec a4) {
        if (!a4.ALLATORIxDEMO()) {
            return Collections.emptyList();
        }
        return a2.stream().filter(a3 -> a3.ALLATORIxDEMO().startsWith(a4.ALLATORIxDEMO())).collect(Collectors.toList());
    }

    public static long ALLATORIxDEMO(List<ec> a2) {
        long a3 = 0L;
        for (ec a4 : a2) {
            if (a4.ALLATORIxDEMO() != null && a4.ALLATORIxDEMO().c() > 0L) {
                a3 += a4.ALLATORIxDEMO().c();
                continue;
            }
            a3 += a4.ALLATORIxDEMO();
        }
        return a3;
    }

    private static /* synthetic */ ec ALLATORIxDEMO(dc a2, String a3) throws yk {
        if (a2 == null) {
            throw new yk("zip model is null, cannot determine file header with exact match for fileName: " + a3);
        }
        if (!ta.ALLATORIxDEMO(a3)) {
            throw new yk("file name is null, cannot determine file header with exact match for fileName: " + a3);
        }
        if (a2.ALLATORIxDEMO() == null) {
            throw new yk("central directory is null, cannot determine file header with exact match for fileName: " + a3);
        }
        if (a2.ALLATORIxDEMO().ALLATORIxDEMO() == null) {
            throw new yk("file Headers are null, cannot determine file header with exact match for fileName: " + a3);
        }
        if (a2.ALLATORIxDEMO().ALLATORIxDEMO().size() == 0) {
            return null;
        }
        a3 = a3.toLowerCase(Locale.ROOT);
        return a2.ALLATORIxDEMO().ALLATORIxDEMO().get(a3);
    }
}

