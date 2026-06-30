/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.armourers;

import eos.moe.armourers.db;
import eos.moe.armourers.fb;
import eos.moe.armourers.gb;
import eos.moe.armourers.lc;
import eos.moe.armourers.nb;
import eos.moe.armourers.tc;
import eos.moe.armourers.yc;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class kb
implements Cloneable {
    private long a;
    private List<nb> e;
    private yc b;
    private long g;
    private File z;
    private boolean t;
    private List<tc> w;
    private lc r;
    private boolean l;
    private fb c;
    private gb v;
    private boolean s;
    private long m;
    private db j;

    public long z() {
        kb a2;
        return a2.a;
    }

    public void y(List<tc> a2) {
        a.w = a2;
    }

    public Object clone() throws CloneNotSupportedException {
        kb a2;
        return super.clone();
    }

    public void r(db a2) {
        a.j = a2;
    }

    public void r(File a2) {
        a.z = a2;
    }

    public void r(fb a2) {
        a.c = a2;
    }

    public void z(long a2) {
        a.m = a2;
    }

    public void z(boolean a2) {
        a.l = a2;
    }

    public void r(yc a2) {
        a.b = a2;
    }

    public List<nb> y() {
        kb a2;
        return a2.e;
    }

    public fb r() {
        kb a2;
        return a2.c;
    }

    public File r() {
        kb a2;
        return a2.z;
    }

    public List<tc> r() {
        kb a2;
        return a2.w;
    }

    public long y() {
        kb a2;
        return a2.m;
    }

    public lc r() {
        kb a2;
        return a2.r;
    }

    public void y(long a2) {
        a.g = a2;
    }

    public void r(lc a2) {
        a.r = a2;
    }

    public yc r() {
        kb a2;
        return a2.b;
    }

    public boolean z() {
        kb a2;
        return a2.s;
    }

    public gb r() {
        kb a2;
        return a2.v;
    }

    public void r(gb a2) {
        a.v = a2;
    }

    public void r(long a2) {
        a.a = a2;
    }

    public boolean y() {
        kb a2;
        return a2.t;
    }

    public kb() {
        kb a2;
        kb kb2 = a2;
        kb kb3 = a2;
        a2.e = new ArrayList<nb>();
        kb3.w = new ArrayList<tc>();
        a2.r = new lc();
        a2.b = new yc();
        a2.v = new gb();
        a2.c = new fb();
        a2.j = new db();
        kb2.l = false;
        kb2.g = -1L;
    }

    public db r() {
        kb a2;
        return a2.j;
    }

    public void y(boolean a2) {
        a.s = a2;
    }

    public boolean r() {
        kb a2;
        return a2.l;
    }

    public long r() {
        kb a2;
        return a2.g;
    }

    public void r(boolean a2) {
        a.t = a2;
    }

    public void r(List<nb> a2) {
        a.e = a2;
    }
}

