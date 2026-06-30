/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.armourers;

import eos.moe.armourers.dc;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class fc
extends dc {
    private int j;

    @Override
    public File r(int a2) throws IOException {
        fc a3;
        if (a2 == a3.j) {
            return a3.s;
        }
        String string = a3.s.getCanonicalPath();
        String string2 = ".z0";
        if (a2 >= 9) {
            string2 = ".z";
        }
        String string3 = string;
        return new File(new StringBuilder().insert(0, string3.substring(0, string3.lastIndexOf("."))).append(string2).append(a2 + 1).toString());
    }

    public fc(File a2, boolean a3, int a4) throws FileNotFoundException {
        super(a2, a3, a4);
        fc a5;
        a5.j = a4;
    }
}

