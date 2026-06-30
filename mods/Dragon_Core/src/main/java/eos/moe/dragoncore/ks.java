/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.qt;
import java.io.PrintStream;
import java.io.PrintWriter;

public class ks
extends Exception {
    private static final String o = "Uhh, nope. It's just ";
    private static final String y = "Hopefully, it will have been worth the weight.";
    private static final String k = "And my favorite class is the Spy";
    private static final long ALLATORIxDEMO = -3533996017850476032L;

    public ks(String a2, Throwable a3) {
        super(a2, a3);
        ks a4;
    }

    public ks(String a2) {
        super(a2);
        ks a3;
    }

    public ks(Throwable a2) {
        super(a2);
        ks a3;
    }

    @Override
    public void printStackTrace(PrintStream a2) {
        ks a3;
        a2.print(o);
        super.printStackTrace(a2);
        boolean a4 = qt.ALLATORIxDEMO.nextBoolean();
        a2.println(a4 ? y : k);
    }

    @Override
    public void printStackTrace(PrintWriter a2) {
        ks a3;
        a2.print(o);
        super.printStackTrace(a2);
        boolean a4 = qt.ALLATORIxDEMO.nextBoolean();
        a2.println(a4 ? y : k);
    }
}

