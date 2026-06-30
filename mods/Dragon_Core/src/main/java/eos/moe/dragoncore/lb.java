/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.dc;
import eos.moe.dragoncore.fb;
import eos.moe.dragoncore.jb;
import eos.moe.dragoncore.ka;
import eos.moe.dragoncore.mg;
import eos.moe.dragoncore.nb;
import eos.moe.dragoncore.uc;
import eos.moe.dragoncore.yk;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

public class lb
extends fb<nb> {
    public lb(dc a2, char[] a3, mg a4, jb a5) {
        super(a2, a3, a4, a5);
        lb a6;
    }

    @Override
    public void ALLATORIxDEMO(nb a2, uc a3) throws IOException {
        lb a4;
        List<File> a5 = a4.ALLATORIxDEMO(a2);
        a4.ALLATORIxDEMO(a2);
        a4.ALLATORIxDEMO(a5, a3, nb.ALLATORIxDEMO(a2), (Charset)((Object)a2.ALLATORIxDEMO));
    }

    @Override
    public long ALLATORIxDEMO(nb a2) throws yk {
        lb a3;
        List<File> a4 = ka.ALLATORIxDEMO(nb.ALLATORIxDEMO(a2), nb.ALLATORIxDEMO(a2).k(), nb.ALLATORIxDEMO(a2).d(), nb.ALLATORIxDEMO(a2).ALLATORIxDEMO());
        if (nb.ALLATORIxDEMO(a2).x()) {
            a4.add(nb.ALLATORIxDEMO(a2));
        }
        return a3.ALLATORIxDEMO(a4, nb.ALLATORIxDEMO(a2));
    }

    private /* synthetic */ void ALLATORIxDEMO(nb a2) throws IOException {
        File a3;
        File a4 = nb.ALLATORIxDEMO(a2);
        String a5 = nb.ALLATORIxDEMO(a2).x() ? ((a3 = a4.getCanonicalFile().getParentFile()) == null ? a4.getCanonicalPath() : a4.getCanonicalFile().getParentFile().getCanonicalPath()) : a4.getCanonicalPath();
        nb.ALLATORIxDEMO(a2).x(a5);
    }

    private /* synthetic */ List<File> ALLATORIxDEMO(nb a2) throws yk {
        List<File> a3 = ka.ALLATORIxDEMO(nb.ALLATORIxDEMO(a2), nb.ALLATORIxDEMO(a2).k(), nb.ALLATORIxDEMO(a2).d(), nb.ALLATORIxDEMO(a2).ALLATORIxDEMO());
        if (nb.ALLATORIxDEMO(a2).x()) {
            a3.add(nb.ALLATORIxDEMO(a2));
        }
        return a3;
    }
}

