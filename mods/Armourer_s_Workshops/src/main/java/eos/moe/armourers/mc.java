/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.armourers;

import eos.moe.armourers.ec;
import eos.moe.armourers.ph;
import eos.moe.armourers.v;
import eos.moe.armourers.zb;
import java.io.IOException;
import java.io.OutputStream;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public abstract class mc<T extends v>
extends OutputStream {
    private T m;
    private ec j;

    public long r() {
        mc a2;
        return a2.j.r();
    }

    @Override
    public void write(int a2) throws IOException {
        mc a3;
        a3.j.write(a2);
    }

    public abstract T r(OutputStream var1, zb var2, char[] var3) throws IOException, ph;

    public mc(ec a2, zb a3, char[] a4) throws IOException, ph {
        mc a5;
        a5.j = a2;
        a5.m = a5.r(a2, a3, a4);
    }

    @Override
    public void close() throws IOException {
        mc a2;
        a2.j.close();
    }

    public T r() {
        mc a2;
        return a2.m;
    }

    public void r(byte[] a2) throws IOException {
        mc a3;
        a3.j.write(a2);
    }

    @Override
    public void write(byte[] a2, int a3, int a4) throws IOException {
        mc a5;
        a5.m.r(a2, a3, a4);
        a5.j.write(a2, a3, a4);
    }

    @Override
    public void write(byte[] a2) throws IOException {
        mc a3;
        a3.j.write(a2);
    }

    public void r() throws IOException {
        mc a2;
        a2.j.r();
    }
}

