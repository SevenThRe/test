/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.armourers;

import eos.moe.armourers.dc;
import eos.moe.armourers.fc;
import eos.moe.armourers.kb;
import eos.moe.armourers.le;
import eos.moe.armourers.oc;
import eos.moe.armourers.pb;
import eos.moe.armourers.ph;
import eos.moe.armourers.ya;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;

public class fa {
    public static dc r(kb a2) throws IOException {
        if (a2.r().getName().endsWith(".zip.001")) {
            return new le(a2.r(), true, a2.r().z());
        }
        return new fc(a2.r(), a2.z(), a2.r().z());
    }

    public static oc r(kb a2, pb a3, char[] a4) throws IOException {
        dc dc2 = null;
        try {
            dc2 = fa.r(a2);
            dc2.r(a3);
            oc oc2 = new oc((InputStream)dc2, a4);
            if (oc2.r(a3) == null) {
                throw new ph("Could not locate local file header for corresponding file header");
            }
            return oc2;
        }
        catch (IOException iOException) {
            if (dc2 != null) {
                dc2.close();
            }
            throw iOException;
        }
    }

    public static void r(pb a2, File a3) {
        try {
            Path path;
            Path path2 = path = a3.toPath();
            ya.y(path2, a2.z());
            ya.r(path2, a2.y());
            return;
        }
        catch (NoSuchMethodError noSuchMethodError) {
            ya.r(a3, a2.y());
            return;
        }
    }

    public fa() {
        fa a2;
    }
}

