/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.armourers;

import eos.moe.armourers.uh;
import java.io.IOException;

public class ph
extends IOException {
    private static final long m = 1L;
    private uh j;

    public uh r() {
        ph a2;
        return a2.j;
    }

    public ph(String a2, Throwable a3, uh a4) {
        super(a2, a3);
        ph a5;
        a5.j = uh.v;
        a5.j = a4;
    }

    public ph(String a2, uh a3) {
        super(a2);
        ph a4;
        a4.j = uh.v;
        a4.j = a3;
    }

    public ph(String a2, Exception a3) {
        super(a2, a3);
        ph a4;
        a4.j = uh.v;
    }

    public ph(String a2) {
        super(a2);
        ph a3;
        a3.j = uh.v;
    }

    public ph(Exception a2) {
        super(a2);
        ph a3;
        a3.j = uh.v;
    }
}

