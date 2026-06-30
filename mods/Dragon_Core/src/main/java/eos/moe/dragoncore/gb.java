/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.dc;
import eos.moe.dragoncore.fb;
import eos.moe.dragoncore.jb;
import eos.moe.dragoncore.kb;
import eos.moe.dragoncore.mg;
import eos.moe.dragoncore.uc;
import eos.moe.dragoncore.vc;
import eos.moe.dragoncore.yk;
import java.io.IOException;
import java.nio.charset.Charset;

public class gb
extends fb<kb> {
    public gb(dc a2, char[] a3, mg a4, jb a5) {
        super(a2, a3, a4, a5);
        gb a6;
    }

    @Override
    public void ALLATORIxDEMO(kb a2, uc a3) throws IOException {
        gb a4;
        a4.ALLATORIxDEMO(kb.ALLATORIxDEMO(a2));
        a4.ALLATORIxDEMO(kb.ALLATORIxDEMO(a2), a3, kb.ALLATORIxDEMO(a2), (Charset)((Object)a2.ALLATORIxDEMO));
    }

    @Override
    public long ALLATORIxDEMO(kb a2) throws yk {
        gb a3;
        return a3.ALLATORIxDEMO(kb.ALLATORIxDEMO(a2), kb.ALLATORIxDEMO(a2));
    }

    @Override
    public vc ALLATORIxDEMO() {
        gb a2;
        return super.ALLATORIxDEMO();
    }
}

