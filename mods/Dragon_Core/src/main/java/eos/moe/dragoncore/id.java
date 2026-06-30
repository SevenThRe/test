/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.wm;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class id
extends wm {
    private int ALLATORIxDEMO;

    public id(File a2, boolean a3, int a4) throws FileNotFoundException {
        super(a2, a3, a4);
        id a5;
        a5.ALLATORIxDEMO = a4;
    }

    @Override
    public File ALLATORIxDEMO(int a2) throws IOException {
        id a3;
        if (a2 == a3.ALLATORIxDEMO) {
            return a3.o;
        }
        String a4 = a3.o.getCanonicalPath();
        String a5 = ".z0";
        if (a2 >= 9) {
            a5 = ".z";
        }
        return new File(a4.substring(0, a4.lastIndexOf(".")) + a5 + (a2 + 1));
    }
}

