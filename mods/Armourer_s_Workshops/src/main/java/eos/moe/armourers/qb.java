/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.armourers;

import eos.moe.armourers.c;
import eos.moe.armourers.cb;
import eos.moe.armourers.eb;
import eos.moe.armourers.mb;
import eos.moe.armourers.wb;
import eos.moe.armourers.wc;
import eos.moe.armourers.yb;
import java.util.List;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public abstract class qb
extends mb {
    private int q;
    private long f;
    private byte[] u;
    private boolean d;
    private long h;
    private int a;
    private boolean e;
    private int b;
    private yb g;
    private wc z;
    private String t;
    private long w;
    private List<eb> r;
    private cb l;
    private long c;
    private boolean v;
    private byte[] s;
    private boolean m;
    private wb j;

    public void z(int a2) {
        a.a = a2;
    }

    public long x() {
        qb a2;
        return eos.moe.armourers.c.r(a2.w);
    }

    public int z() {
        qb a2;
        return a2.b;
    }

    public void y(byte[] a2) {
        a.s = a2;
    }

    public byte[] y() {
        qb a2;
        return a2.u;
    }

    public String r() {
        qb a2;
        return a2.t;
    }

    public void r(wc a2) {
        a.z = a2;
    }

    public void r(String a2) {
        a.t = a2;
    }

    public wc r() {
        qb a2;
        return a2.z;
    }

    public byte[] r() {
        qb a2;
        return a2.s;
    }

    public yb r() {
        qb a2;
        return a2.g;
    }

    public void r(List<eb> a2) {
        a.r = a2;
    }

    public long h() {
        qb a2;
        return a2.c;
    }

    public void r(cb a2) {
        a.l = a2;
    }

    public boolean h() {
        qb a2;
        return a2.m;
    }

    public qb() {
        qb a2;
        qb qb2 = a2;
        a2.c = 0L;
        qb2.h = 0L;
        qb2.f = 0L;
        qb2.g = yb.v;
    }

    public long z() {
        qb a2;
        return a2.h;
    }

    public wb r() {
        qb a2;
        return a2.j;
    }

    public void r(byte[] a2) {
        a.u = a2;
    }

    public boolean z() {
        qb a2;
        return a2.e;
    }

    public List<eb> r() {
        qb a2;
        return a2.r;
    }

    public cb r() {
        qb a2;
        return a2.l;
    }

    public void h(boolean a2) {
        a.e = a2;
    }

    public void h(long a2) {
        a.f = a2;
    }

    public void y(int a2) {
        a.b = a2;
    }

    public int y() {
        qb a2;
        return a2.a;
    }

    public int r() {
        qb a2;
        return a2.q;
    }

    public void z(boolean a2) {
        a.v = a2;
    }

    public long y() {
        qb a2;
        return a2.w;
    }

    public void z(long a2) {
        a.w = a2;
    }

    public void r(yb a2) {
        a.g = a2;
    }

    public long r() {
        qb a2;
        return a2.f;
    }

    public boolean equals(Object a2) {
        qb a3;
        if (a2 == null) {
            return false;
        }
        if (!(a2 instanceof qb)) {
            return false;
        }
        return a3.r().equals(((qb)a2).r());
    }

    public void y(boolean a2) {
        a.d = a2;
    }

    public boolean y() {
        qb a2;
        return a2.v;
    }

    public void r(boolean a2) {
        a.m = a2;
    }

    public void r(wb a2) {
        a.j = a2;
    }

    public void r(int a2) {
        a.q = a2;
    }

    public boolean r() {
        qb a2;
        return a2.d;
    }

    public void y(long a2) {
        a.c = a2;
    }

    public void r(long a2) {
        a.h = a2;
    }
}

