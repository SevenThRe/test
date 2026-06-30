/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.zk;
import java.io.IOException;

public class yk
extends IOException {
    private static final long k = 1L;
    private zk ALLATORIxDEMO;

    public yk(String a2) {
        super(a2);
        yk a3;
        a3.ALLATORIxDEMO = zk.k;
    }

    public yk(Exception a2) {
        super(a2);
        yk a3;
        a3.ALLATORIxDEMO = zk.k;
    }

    public yk(String a2, Exception a3) {
        super(a2, a3);
        yk a4;
        a4.ALLATORIxDEMO = zk.k;
    }

    public yk(String a2, zk a3) {
        super(a2);
        yk a4;
        a4.ALLATORIxDEMO = zk.k;
        a4.ALLATORIxDEMO = a3;
    }

    public yk(String a2, Throwable a3, zk a4) {
        super(a2, a3);
        yk a5;
        a5.ALLATORIxDEMO = zk.k;
        a5.ALLATORIxDEMO = a4;
    }

    public zk ALLATORIxDEMO() {
        yk a2;
        return a2.ALLATORIxDEMO;
    }
}

