/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.dc;
import eos.moe.dragoncore.ec;
import eos.moe.dragoncore.id;
import eos.moe.dragoncore.ka;
import eos.moe.dragoncore.oi;
import eos.moe.dragoncore.tf;
import eos.moe.dragoncore.wm;
import eos.moe.dragoncore.yk;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;

public class qa {
    public qa() {
        qa a2;
    }

    public static tf ALLATORIxDEMO(dc a2, ec a3, char[] a4) throws IOException {
        wm a5 = null;
        try {
            a5 = qa.ALLATORIxDEMO(a2);
            a5.ALLATORIxDEMO(a3);
            tf a6 = new tf((InputStream)a5, a4);
            if (a6.ALLATORIxDEMO(a3) == null) {
                throw new yk("Could not locate local file header for corresponding file header");
            }
            return a6;
        }
        catch (IOException a7) {
            if (a5 != null) {
                a5.close();
            }
            throw a7;
        }
    }

    public static void ALLATORIxDEMO(ec a2, File a3) {
        try {
            Path a4 = a3.toPath();
            ka.f(a4, a2.f());
            ka.ALLATORIxDEMO(a4, a2.d());
        }
        catch (NoSuchMethodError a5) {
            ka.ALLATORIxDEMO(a3, a2.d());
        }
    }

    public static wm ALLATORIxDEMO(dc a2) throws IOException {
        File a3 = a2.ALLATORIxDEMO();
        if (a3.getName().endsWith(".zip.001")) {
            return new oi(a2.ALLATORIxDEMO(), true, a2.ALLATORIxDEMO().d());
        }
        return new id(a2.ALLATORIxDEMO(), a2.f(), a2.ALLATORIxDEMO().d());
    }
}

