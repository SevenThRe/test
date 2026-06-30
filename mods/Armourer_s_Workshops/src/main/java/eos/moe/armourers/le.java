/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.armourers;

import eos.moe.armourers.dc;
import eos.moe.armourers.ya;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class le
extends dc {
    @Override
    public File r(int a2) throws IOException {
        le a3;
        String string;
        String string2 = string = a3.s.getCanonicalPath();
        string = string2.substring(0, string2.lastIndexOf("."));
        return new File(new StringBuilder().insert(0, string).append(ya.y(a2)).toString());
    }

    public le(File a2, boolean a3, int a4) throws FileNotFoundException {
        super(a2, a3, a4);
        le a5;
    }
}

