/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.cf;
import eos.moe.dragoncore.dc;
import eos.moe.dragoncore.ec;
import eos.moe.dragoncore.jb;
import eos.moe.dragoncore.ka;
import eos.moe.dragoncore.ob;
import eos.moe.dragoncore.uc;
import eos.moe.dragoncore.yk;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class ub<T>
extends ob<T> {
    public ub(jb a2) {
        super(a2);
        ub a3;
    }

    public File ALLATORIxDEMO(String a2) {
        Random a3 = new Random();
        File a4 = new File(a2 + a3.nextInt(10000));
        while (a4.exists()) {
            a4 = new File(a2 + a3.nextInt(10000));
        }
        return a4;
    }

    public void ALLATORIxDEMO(List<ec> a2, dc a3, ec a4, long a5) throws yk {
        ub a6;
        int a7 = a6.ALLATORIxDEMO(a2, a4);
        if (a7 == -1) {
            throw new yk("Could not locate modified file header in zipModel");
        }
        for (int a8 = a7 + 1; a8 < a2.size(); ++a8) {
            ec a9 = a2.get(a8);
            a9.d(a9.k() + a5);
            if (!a3.c() || a9.ALLATORIxDEMO() == null || a9.ALLATORIxDEMO().ALLATORIxDEMO() == -1L) continue;
            a9.ALLATORIxDEMO().ALLATORIxDEMO(a9.ALLATORIxDEMO().ALLATORIxDEMO() + a5);
        }
    }

    public void ALLATORIxDEMO(boolean a2, File a3, File a4) throws yk {
        if (a2) {
            ub a5;
            a5.ALLATORIxDEMO(a3, a4);
        } else if (!a4.delete()) {
            throw new yk("Could not delete temporary file");
        }
    }

    public long ALLATORIxDEMO(RandomAccessFile a2, OutputStream a3, long a4, long a5, uc a6) throws IOException {
        ka.ALLATORIxDEMO(a2, a3, a4, a4 + a5, a6);
        return a5;
    }

    public List<ec> ALLATORIxDEMO(List<ec> a4) {
        ArrayList<ec> a5 = new ArrayList<ec>(a4);
        a5.sort((a2, a3) -> {
            if (a2.ALLATORIxDEMO().equals(a3.ALLATORIxDEMO())) {
                return 0;
            }
            return a2.k() < a3.k() ? -1 : 1;
        });
        return a5;
    }

    public long ALLATORIxDEMO(List<ec> a2, ec a3, dc a4) throws yk {
        ub a5;
        int a6 = a5.ALLATORIxDEMO(a2, a3);
        if (a6 == a2.size() - 1) {
            return cf.ALLATORIxDEMO(a4);
        }
        return a2.get(a6 + 1).k();
    }

    private /* synthetic */ void ALLATORIxDEMO(File a2, File a3) throws yk {
        if (a2.delete()) {
            if (!a3.renameTo(a2)) {
                throw new yk("cannot rename modified zip file");
            }
        } else {
            throw new yk("cannot delete old zip file");
        }
    }

    private /* synthetic */ int ALLATORIxDEMO(List<ec> a2, ec a3) throws yk {
        for (int a4 = 0; a4 < a2.size(); ++a4) {
            ec a5 = a2.get(a4);
            if (!a5.equals(a3)) continue;
            return a4;
        }
        throw new yk("Could not find file header in list of central directory file headers");
    }
}

