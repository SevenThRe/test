/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.armourers;

import java.io.IOException;
import java.io.OutputStream;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class ec
extends OutputStream {
    private long s;
    private boolean m;
    private OutputStream j;

    @Override
    public void close() throws IOException {
    }

    public ec(OutputStream a2) {
        ec a3;
        ec ec2 = a3;
        a3.s = 0L;
        ec2.j = a2;
        ec2.m = false;
    }

    @Override
    public void write(int a2) throws IOException {
        ec a3;
        byte[] byArray = new byte[1];
        byArray[0] = (byte)a2;
        a3.write(byArray);
    }

    @Override
    public void write(byte[] a2) throws IOException {
        ec a3;
        a3.write(a2, 0, a2.length);
    }

    public long r() {
        ec a2;
        return a2.s;
    }

    @Override
    public void write(byte[] a2, int a3, int a4) throws IOException {
        ec a5;
        if (a5.m) {
            throw new IllegalStateException("ZipEntryOutputStream is closed");
        }
        ec ec2 = a5;
        ec2.j.write(a2, a3, a4);
        ec2.s += (long)a4;
    }

    public void r() throws IOException {
        a.m = true;
    }
}

