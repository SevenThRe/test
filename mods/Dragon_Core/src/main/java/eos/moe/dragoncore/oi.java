/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.ka;
import eos.moe.dragoncore.wm;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class oi
extends wm {
    public oi(File a2, boolean a3, int a4) throws FileNotFoundException {
        super(a2, a3, a4);
        oi a5;
    }

    @Override
    public File ALLATORIxDEMO(int a2) throws IOException {
        oi a3;
        String a4 = a3.o.getCanonicalPath();
        String a5 = a4.substring(0, a4.lastIndexOf("."));
        return new File(a5 + ka.c(a2));
    }
}

