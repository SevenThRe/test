/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.armourers;

import eos.moe.armourers.bb;
import eos.moe.armourers.ca;
import eos.moe.armourers.kb;
import eos.moe.armourers.ph;
import eos.moe.armourers.rb;
import eos.moe.armourers.ta;
import eos.moe.armourers.vl;
import eos.moe.armourers.ya;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

public class hb
extends ta<bb> {
    @Override
    private /* synthetic */ void r(bb a2) throws IOException {
        bb bb2 = a2;
        File file = bb.r(bb2);
        String string = bb.r(bb2).x() ? (file.getCanonicalFile().getParentFile() == null ? file.getCanonicalPath() : file.getCanonicalFile().getParentFile().getCanonicalPath()) : file.getCanonicalPath();
        bb.r(a2).r(string);
    }

    public void r(bb a2, rb a3) throws IOException {
        hb a4;
        hb hb2 = a4;
        List<File> list = hb2.r(a2);
        hb2.r(a2);
        hb2.r(list, a3, bb.r(a2), (Charset)((Object)a2.j));
    }

    @Override
    public long r(bb a2) throws ph {
        hb a3;
        bb bb2 = a2;
        List<File> list = ya.r(bb.r(a2), bb.r(a2).w(), bb.r(bb2).r(), bb.r(a2).r());
        if (bb.r(bb2).x()) {
            list.add(bb.r(a2));
        }
        return a3.r(list, bb.r(a2));
    }

    private /* synthetic */ List<File> r(bb a2) throws ph {
        bb bb2 = a2;
        List<File> list = ya.r(bb.r(a2), bb.r(a2).w(), bb.r(bb2).r(), bb.r(a2).r());
        if (bb.r(bb2).x()) {
            list.add(bb.r(a2));
        }
        return list;
    }

    public hb(kb a2, char[] a3, vl a4, ca a5) {
        super(a2, a3, a4, a5);
        hb a6;
    }
}

