/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.armourers;

import eos.moe.armourers.bg;
import eos.moe.armourers.ca;
import eos.moe.armourers.kb;
import eos.moe.armourers.oa;
import eos.moe.armourers.pb;
import eos.moe.armourers.ph;
import eos.moe.armourers.rb;
import eos.moe.armourers.ya;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class pa<T>
extends oa<T> {
    public void r(List<pb> a2, kb a3, pb a42, long a5) throws ph {
        pa a6;
        int a42 = a6.r(a2, a42);
        if (a42 == -1) {
            throw new ph("Could not locate modified file header in zipModel");
        }
        int n2 = a42 = a42 + 1;
        while (n2 < a2.size()) {
            pb pb2;
            pb pb3 = pb2 = a2.get(a42);
            pb3.x(pb3.s() + a5);
            if (a3.r() && pb2.r() != null && pb2.r().z() != -1L) {
                pb2.r().r(pb2.r().z() + a5);
            }
            n2 = ++a42;
        }
    }

    public File r(String a2) {
        File file;
        Random random = new Random();
        File file2 = file = new File(new StringBuilder().insert(0, a2).append(random.nextInt(10000)).toString());
        while (file2.exists()) {
            file2 = new File(new StringBuilder().insert(0, a2).append(random.nextInt(10000)).toString());
        }
        return file;
    }

    public pa(ca a2) {
        super(a2);
        pa a3;
    }

    public long r(List<pb> a2, pb a32, kb a4) throws ph {
        pa a5;
        int a32 = a5.r(a2, a32);
        if (a32 == a2.size() - 1) {
            return bg.r(a4);
        }
        return a2.get(a32 + 1).s();
    }

    public List<pb> r(List<pb> a4) {
        a4 = new ArrayList<pb>(a4);
        a4.sort((a2, a3) -> {
            if (a2.r().equals(a3.r())) {
                return 0;
            }
            if (a2.s() < a3.s()) {
                return -1;
            }
            return 1;
        });
        return a4;
    }

    public long r(RandomAccessFile a2, OutputStream a3, long a4, long a5, rb a6) throws IOException {
        long l2 = a4;
        ya.r(a2, a3, l2, l2 + a5, a6);
        return a5;
    }

    private /* synthetic */ void r(File a2, File a3) throws ph {
        if (a2.delete()) {
            if (!a3.renameTo(a2)) {
                throw new ph("cannot rename modified zip file");
            }
        } else {
            throw new ph("cannot delete old zip file");
        }
    }

    private /* synthetic */ int r(List<pb> a2, pb a3) throws ph {
        int n2;
        int n3 = n2 = 0;
        while (n3 < a2.size()) {
            if (a2.get(n2).equals(a3)) {
                return n2;
            }
            n3 = ++n2;
        }
        throw new ph("Could not find file header in list of central directory file headers");
    }

    public void r(boolean a2, File a3, File a4) throws ph {
        if (a2) {
            pa a5;
            a5.r(a3, a4);
            return;
        }
        if (!a4.delete()) {
            throw new ph("Could not delete temporary file");
        }
    }
}

