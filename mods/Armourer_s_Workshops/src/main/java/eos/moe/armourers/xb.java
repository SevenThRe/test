/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.armourers;

import eos.moe.armourers.ca;
import eos.moe.armourers.kb;
import eos.moe.armourers.ob;
import eos.moe.armourers.ph;
import eos.moe.armourers.rb;
import eos.moe.armourers.ta;
import eos.moe.armourers.tb;
import eos.moe.armourers.vl;
import java.io.IOException;
import java.nio.charset.Charset;

public class xb
extends ta<tb> {
    public void r(tb a2, rb a3) throws IOException {
        xb a4;
        xb xb2 = a4;
        xb2.r(tb.r(a2));
        xb2.r(tb.r(a2), a3, tb.r(a2), (Charset)((Object)a2.j));
    }

    @Override
    public ob r() {
        xb a2;
        return super.r();
    }

    @Override
    public long r(tb a2) throws ph {
        xb a3;
        return a3.r(tb.r(a2), tb.r(a2));
    }

    public xb(kb a2, char[] a3, vl a4, ca a5) {
        super(a2, a3, a4, a5);
        xb a6;
    }
}

