/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.bb;
import eos.moe.dragoncore.cf;
import eos.moe.dragoncore.db;
import eos.moe.dragoncore.dc;
import eos.moe.dragoncore.eb;
import eos.moe.dragoncore.ec;
import eos.moe.dragoncore.gb;
import eos.moe.dragoncore.hb;
import eos.moe.dragoncore.ib;
import eos.moe.dragoncore.ic;
import eos.moe.dragoncore.jb;
import eos.moe.dragoncore.ka;
import eos.moe.dragoncore.kb;
import eos.moe.dragoncore.lb;
import eos.moe.dragoncore.lc;
import eos.moe.dragoncore.mb;
import eos.moe.dragoncore.mc;
import eos.moe.dragoncore.mg;
import eos.moe.dragoncore.mi;
import eos.moe.dragoncore.nb;
import eos.moe.dragoncore.og;
import eos.moe.dragoncore.pb;
import eos.moe.dragoncore.qa;
import eos.moe.dragoncore.qb;
import eos.moe.dragoncore.sb;
import eos.moe.dragoncore.ta;
import eos.moe.dragoncore.tb;
import eos.moe.dragoncore.tf;
import eos.moe.dragoncore.ua;
import eos.moe.dragoncore.uc;
import eos.moe.dragoncore.vb;
import eos.moe.dragoncore.wb;
import eos.moe.dragoncore.yb;
import eos.moe.dragoncore.yk;
import eos.moe.dragoncore.zb;
import eos.moe.dragoncore.zk;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class lk {
    private File x;
    private dc v;
    private boolean m;
    private uc c;
    private boolean q;
    private char[] b;
    private mg o = new mg();
    private Charset y = Charset.forName("gbk");
    private ThreadFactory k;
    private ExecutorService ALLATORIxDEMO;

    public boolean d() {
        lk a2;
        return a2.b != null;
    }

    public lk(String a2) {
        a3(new File(a2), null);
        lk a3;
    }

    public lk(String a2, char[] a3) {
        a4(new File(a2), a3);
        lk a4;
    }

    public lk(File a2) {
        a3(a2, null);
        lk a3;
    }

    public lk(File a2, char[] a3) {
        lk a4;
        a4.x = a2;
        a4.b = a3;
        a4.q = false;
        a4.c = new uc();
    }

    public void ALLATORIxDEMO(List<File> a2, lc a3, boolean a4, long a5) throws yk {
        lk a6;
        if (a6.x.exists()) {
            throw new yk("zip file: " + a6.x + " already exists. To add files to existing zip file use addFile method");
        }
        if (a2 == null || a2.size() == 0) {
            throw new yk("input file List is null, cannot create zip file");
        }
        a6.ALLATORIxDEMO();
        a6.v.f(a4);
        a6.v.ALLATORIxDEMO(a5);
        new gb(a6.v, a6.b, a6.o, a6.ALLATORIxDEMO()).c(new kb(a2, a3, a6.y));
    }

    public void ALLATORIxDEMO(File a2, lc a3, boolean a4, long a5) throws yk {
        lk a6;
        if (a2 == null) {
            throw new yk("folderToAdd is null, cannot create zip file from folder");
        }
        if (a3 == null) {
            throw new yk("input parameters are null, cannot create zip file from folder");
        }
        if (a6.x.exists()) {
            throw new yk("zip file: " + a6.x + " already exists. To add files to existing zip file use addFolder method");
        }
        a6.ALLATORIxDEMO();
        a6.v.f(a4);
        if (a4) {
            a6.v.ALLATORIxDEMO(a5);
        }
        a6.ALLATORIxDEMO(a2, a3, false);
    }

    public void x(String a2) throws yk {
        lk a3;
        a3.ALLATORIxDEMO(a2, new lc());
    }

    public void ALLATORIxDEMO(String a2, lc a3) throws yk {
        lk a4;
        if (!ta.ALLATORIxDEMO(a2)) {
            throw new yk("file to add is null or empty");
        }
        a4.ALLATORIxDEMO(Collections.singletonList(new File(a2)), a3);
    }

    public void f(File a2) throws yk {
        lk a3;
        a3.ALLATORIxDEMO(Collections.singletonList(a2), new lc());
    }

    public void c(File a2, lc a3) throws yk {
        lk a4;
        a4.ALLATORIxDEMO(Collections.singletonList(a2), a3);
    }

    public void c(List<File> a2) throws yk {
        lk a3;
        a3.ALLATORIxDEMO(a2, new lc());
    }

    public void ALLATORIxDEMO(List<File> a2, lc a3) throws yk {
        lk a4;
        if (a2 == null || a2.size() == 0) {
            throw new yk("input file List is null or empty");
        }
        if (a3 == null) {
            throw new yk("input parameters are null");
        }
        if (a4.c.ALLATORIxDEMO() == mc.k) {
            throw new yk("invalid operation - Zip4j is in busy state");
        }
        a4.c();
        if (a4.v == null) {
            throw new yk("internal error: zip model is null");
        }
        if (a4.x.exists() && a4.v.f()) {
            throw new yk("Zip file already exists. Zip file format does not allow updating split/spanned files");
        }
        new gb(a4.v, a4.b, a4.o, a4.ALLATORIxDEMO()).c(new kb(a2, a3, a4.y));
    }

    public void c(File a2) throws yk {
        lk a3;
        a3.ALLATORIxDEMO(a2, new lc());
    }

    public void ALLATORIxDEMO(File a2, lc a3) throws yk {
        lk a4;
        if (a2 == null) {
            throw new yk("input path is null, cannot add folder to zip file");
        }
        if (!a2.exists()) {
            throw new yk("folder does not exist");
        }
        if (!a2.isDirectory()) {
            throw new yk("input folder is not a directory");
        }
        if (!a2.canRead()) {
            throw new yk("cannot read input folder");
        }
        if (a3 == null) {
            throw new yk("input parameters are null, cannot add folder to zip file");
        }
        a4.ALLATORIxDEMO(a2, a3, true);
    }

    private /* synthetic */ void ALLATORIxDEMO(File a2, lc a3, boolean a4) throws yk {
        lk a5;
        a5.c();
        if (a5.v == null) {
            throw new yk("internal error: zip model is null");
        }
        if (a4 && a5.v.f()) {
            throw new yk("This is a split archive. Zip file format does not allow updating split/spanned files");
        }
        new lb(a5.v, a5.b, a5.o, a5.ALLATORIxDEMO()).c(new nb(a2, a3, a5.y));
    }

    public void ALLATORIxDEMO(InputStream a2, lc a3) throws yk {
        lk a4;
        if (a2 == null) {
            throw new yk("inputstream is null, cannot add file to zip");
        }
        if (a3 == null) {
            throw new yk("zip parameters are null");
        }
        a4.ALLATORIxDEMO(false);
        a4.c();
        if (a4.v == null) {
            throw new yk("internal error: zip model is null");
        }
        if (a4.x.exists() && a4.v.f()) {
            throw new yk("Zip file already exists. Zip file format does not allow updating split/spanned files");
        }
        new ib(a4.v, a4.b, a4.o, a4.ALLATORIxDEMO()).c(new qb(a2, a3, a4.y));
    }

    public void f(String a2) throws yk {
        lk a3;
        if (!ta.ALLATORIxDEMO(a2)) {
            throw new yk("output path is null or invalid");
        }
        if (!ta.ALLATORIxDEMO(new File(a2))) {
            throw new yk("invalid output path");
        }
        if (a3.v == null) {
            a3.c();
        }
        if (a3.v == null) {
            throw new yk("Internal error occurred when extracting zip file");
        }
        if (a3.c.ALLATORIxDEMO() == mc.k) {
            throw new yk("invalid operation - Zip4j is in busy state");
        }
        new pb(a3.v, a3.b, a3.ALLATORIxDEMO()).c(new eb(a2, a3.y));
    }

    public void c(ec a2, String a3) throws yk {
        lk a4;
        a4.ALLATORIxDEMO(a2, a3, null);
    }

    public void ALLATORIxDEMO(ec a2, String a3, String a4) throws yk {
        lk a5;
        if (a2 == null) {
            throw new yk("input file header is null, cannot extract file");
        }
        if (!ta.ALLATORIxDEMO(a3)) {
            throw new yk("destination path is empty or null, cannot extract file");
        }
        if (a5.c.ALLATORIxDEMO() == mc.k) {
            throw new yk("invalid operation - Zip4j is in busy state");
        }
        a5.c();
        new yb(a5.v, a5.b, a5.ALLATORIxDEMO()).c(new vb(a3, a2, a4, a5.y));
    }

    public void c(String a2, String a3) throws yk {
        lk a4;
        a4.ALLATORIxDEMO(a2, a3, null);
    }

    public void ALLATORIxDEMO(String a2, String a3, String a4) throws yk {
        lk a5;
        if (!ta.ALLATORIxDEMO(a2)) {
            throw new yk("file to extract is null or empty, cannot extract file");
        }
        a5.c();
        ec a6 = cf.c(a5.v, a2);
        if (a6 == null) {
            throw new yk("No file found with name " + a2 + " in zip file", zk.y);
        }
        a5.ALLATORIxDEMO(a6, a3, a4);
    }

    public List<ec> c() throws yk {
        lk a2;
        a2.c();
        if (a2.v == null || a2.v.ALLATORIxDEMO() == null) {
            return Collections.emptyList();
        }
        return a2.v.ALLATORIxDEMO().ALLATORIxDEMO();
    }

    public ec ALLATORIxDEMO(String a2) throws yk {
        lk a3;
        if (!ta.ALLATORIxDEMO(a2)) {
            throw new yk("input file name is emtpy or null, cannot get FileHeader");
        }
        a3.c();
        if (a3.v == null || a3.v.ALLATORIxDEMO() == null) {
            return null;
        }
        return cf.c(a3.v, a2);
    }

    public boolean x() throws yk {
        lk a2;
        if (a2.v == null) {
            a2.c();
            if (a2.v == null) {
                throw new yk("Zip Model is null");
            }
        }
        if (a2.v.ALLATORIxDEMO() == null || a2.v.ALLATORIxDEMO().ALLATORIxDEMO() == null) {
            throw new yk("invalid zip file");
        }
        for (ec a3 : a2.v.ALLATORIxDEMO().ALLATORIxDEMO()) {
            if (a3 == null || !a3.x()) continue;
            a2.m = true;
            break;
        }
        return a2.m;
    }

    public boolean f() throws yk {
        lk a2;
        if (a2.v == null) {
            a2.c();
            if (a2.v == null) {
                throw new yk("Zip Model is null");
            }
        }
        return a2.v.f();
    }

    public void ALLATORIxDEMO(ec a2) throws yk {
        lk a3;
        if (a2 == null) {
            throw new yk("input file header is null, cannot remove file");
        }
        a3.c(a2.ALLATORIxDEMO());
    }

    public void c(String a2) throws yk {
        lk a3;
        if (!ta.ALLATORIxDEMO(a2)) {
            throw new yk("file name is empty or null, cannot remove file");
        }
        a3.ALLATORIxDEMO(Collections.singletonList(a2));
    }

    public void ALLATORIxDEMO(List<String> a2) throws yk {
        lk a3;
        if (a2 == null) {
            throw new yk("fileNames list is null");
        }
        if (a2.isEmpty()) {
            return;
        }
        if (a3.v == null) {
            a3.c();
        }
        if (a3.v.f()) {
            throw new yk("Zip file format does not allow updating split/spanned files");
        }
        new mb(a3.v, a3.o, a3.ALLATORIxDEMO()).c(new db(a2, a3.y));
    }

    public void ALLATORIxDEMO(ec a2, String a3) throws yk {
        lk a4;
        if (a2 == null) {
            throw new yk("File header is null");
        }
        a4.ALLATORIxDEMO(a2.ALLATORIxDEMO(), a3);
    }

    public void ALLATORIxDEMO(String a2, String a3) throws yk {
        lk a4;
        if (!ta.ALLATORIxDEMO(a2)) {
            throw new yk("file name to be changed is null or empty");
        }
        if (!ta.ALLATORIxDEMO(a3)) {
            throw new yk("newFileName is null or empty");
        }
        a4.ALLATORIxDEMO(Collections.singletonMap(a2, a3));
    }

    public void ALLATORIxDEMO(Map<String, String> a2) throws yk {
        lk a3;
        if (a2 == null) {
            throw new yk("fileNamesMap is null");
        }
        if (a2.size() == 0) {
            return;
        }
        a3.c();
        if (a3.v.f()) {
            throw new yk("Zip file format does not allow updating split/spanned files");
        }
        jb a4 = a3.ALLATORIxDEMO();
        new wb(a3.v, a3.o, new ua(), a3.y, a4).c(new hb(a2));
    }

    public void ALLATORIxDEMO(File a2) throws yk {
        lk a3;
        if (a2 == null) {
            throw new yk("outputZipFile is null, cannot merge split files");
        }
        if (a2.exists()) {
            throw new yk("output Zip File already exists");
        }
        a3.c();
        if (a3.v == null) {
            throw new yk("zip model is null, corrupt zip file?");
        }
        new zb(a3.v, a3.ALLATORIxDEMO()).c(new sb(a2, a3.y));
    }

    public void ALLATORIxDEMO(String a2) throws yk {
        lk a3;
        if (a2 == null) {
            throw new yk("input comment is null, cannot update zip file");
        }
        if (!a3.x.exists()) {
            throw new yk("zip file does not exist, cannot set comment for zip file");
        }
        a3.c();
        if (a3.v == null) {
            throw new yk("zipModel is null, cannot update zip file");
        }
        if (a3.v.ALLATORIxDEMO() == null) {
            throw new yk("end of central directory is null, cannot set comment");
        }
        new bb(a3.v, a3.ALLATORIxDEMO()).c(new tb(a2, a3.y));
    }

    public String ALLATORIxDEMO() throws yk {
        lk a2;
        if (!a2.x.exists()) {
            throw new yk("zip file does not exist, cannot read comment");
        }
        a2.c();
        if (a2.v == null) {
            throw new yk("zip model is null, cannot read comment");
        }
        if (a2.v.ALLATORIxDEMO() == null) {
            throw new yk("end of central directory record is null, cannot read comment");
        }
        return a2.v.ALLATORIxDEMO().ALLATORIxDEMO();
    }

    public tf ALLATORIxDEMO(ec a2, char[] a3) throws IOException {
        lk a4;
        if (a2 == null) {
            throw new yk("FileHeader is null, cannot get InputStream");
        }
        a4.c();
        if (a4.v == null) {
            throw new yk("zip model is null, cannot get inputstream");
        }
        return qa.ALLATORIxDEMO(a4.v, a2, a3);
    }

    public boolean c() {
        lk a2;
        if (!a2.x.exists()) {
            return false;
        }
        try {
            a2.c();
            return !a2.v.f() || a2.ALLATORIxDEMO(a2.ALLATORIxDEMO());
        }
        catch (Exception a3) {
            return false;
        }
    }

    public List<File> ALLATORIxDEMO() throws yk {
        lk a2;
        a2.c();
        return ka.ALLATORIxDEMO(a2.v);
    }

    public void ALLATORIxDEMO(char[] a2) {
        a.b = a2;
    }

    private /* synthetic */ void c() throws yk {
        lk a2;
        if (a2.v != null) {
            return;
        }
        if (!a2.x.exists()) {
            a2.ALLATORIxDEMO();
            return;
        }
        if (!a2.x.canRead()) {
            throw new yk("no read access for the input zip file");
        }
        try (RandomAccessFile a3 = a2.ALLATORIxDEMO();){
            mi a4 = new mi();
            a2.v = a4.ALLATORIxDEMO(a3, a2.y);
            a2.v.ALLATORIxDEMO(a2.x);
        }
        catch (yk a5) {
            throw a5;
        }
        catch (IOException a6) {
            throw new yk(a6);
        }
    }

    private /* synthetic */ void ALLATORIxDEMO() {
        lk a2;
        a2.v = new dc();
        a2.v.ALLATORIxDEMO(a2.x);
    }

    private /* synthetic */ RandomAccessFile ALLATORIxDEMO() throws IOException {
        lk a2;
        if (ka.c(a2.x)) {
            File[] a3 = ka.ALLATORIxDEMO(a2.x);
            og a4 = new og(a2.x, ic.o.ALLATORIxDEMO(), a3);
            a4.ALLATORIxDEMO();
            return a4;
        }
        return new RandomAccessFile(a2.x, ic.o.ALLATORIxDEMO());
    }

    private /* synthetic */ jb ALLATORIxDEMO() {
        lk a2;
        if (a2.q) {
            if (a2.k == null) {
                a2.k = Executors.defaultThreadFactory();
            }
            a2.ALLATORIxDEMO = Executors.newSingleThreadExecutor(a2.k);
        }
        return new jb(a2.ALLATORIxDEMO, a2.q, a2.c);
    }

    private /* synthetic */ boolean ALLATORIxDEMO(List<File> a2) {
        for (File a3 : a2) {
            if (a3.exists()) continue;
            return false;
        }
        return true;
    }

    public uc ALLATORIxDEMO() {
        lk a2;
        return a2.c;
    }

    public boolean ALLATORIxDEMO() {
        lk a2;
        return a2.q;
    }

    public void ALLATORIxDEMO(boolean a2) {
        a.q = a2;
    }

    public File ALLATORIxDEMO() {
        lk a2;
        return a2.x;
    }

    public Charset ALLATORIxDEMO() {
        lk a2;
        return a2.y;
    }

    public void ALLATORIxDEMO(Charset a2) throws IllegalArgumentException {
        if (a2 == null) {
            throw new IllegalArgumentException("charset cannot be null");
        }
        a.y = a2;
    }

    public void ALLATORIxDEMO(ThreadFactory a2) {
        a.k = a2;
    }

    public ExecutorService ALLATORIxDEMO() {
        lk a2;
        return a2.ALLATORIxDEMO;
    }

    public String toString() {
        lk a2;
        return a2.x.toString();
    }
}

