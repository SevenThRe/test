/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.armourers;

import eos.moe.armourers.k;
import eos.moe.armourers.pc;
import eos.moe.armourers.ph;
import java.io.IOException;
import java.io.OutputStream;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class gc
extends OutputStream
implements k {
    private long m;
    private OutputStream j;

    @Override
    public void write(int a2) throws IOException {
        gc a3;
        byte[] byArray = new byte[1];
        byArray[0] = (byte)a2;
        a3.write(byArray);
    }

    public gc(OutputStream a2) {
        gc a3;
        gc gc2 = a3;
        gc2.m = 0L;
        gc2.j = a2;
    }

    @Override
    public void close() throws IOException {
        gc a2;
        a2.j.close();
    }

    public long h() throws IOException {
        gc a2;
        if (a2.j instanceof pc) {
            return ((pc)a2.j).r();
        }
        return a2.m;
    }

    public boolean r() {
        gc a2;
        return a2.j instanceof pc && ((pc)a2.j).r();
    }

    @Override
    public long r() throws IOException {
        gc a2;
        if (a2.j instanceof pc) {
            return ((pc)a2.j).r();
        }
        return a2.m;
    }

    public long z() {
        gc a2;
        if (a2.r()) {
            return ((pc)a2.j).y();
        }
        return 0L;
    }

    @Override
    public void write(byte[] a2) throws IOException {
        gc a3;
        a3.write(a2, 0, a2.length);
    }

    public boolean r(int a2) throws ph {
        gc a3;
        if (!a3.r()) {
            return false;
        }
        return ((pc)a3.j).r(a2);
    }

    public long y() throws IOException {
        gc a2;
        if (a2.j instanceof pc) {
            return ((pc)a2.j).r();
        }
        return a2.m;
    }

    @Override
    public void write(byte[] a2, int a3, int a4) throws IOException {
        gc a5;
        gc gc2 = a5;
        gc2.j.write(a2, a3, a4);
        gc2.m += (long)a4;
    }

    @Override
    public int r() {
        gc a2;
        if (a2.r()) {
            return ((pc)a2.j).r();
        }
        return 0;
    }
}

