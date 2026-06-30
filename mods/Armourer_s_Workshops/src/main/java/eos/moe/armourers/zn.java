/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.armourers;

import eos.moe.armourers.aa;
import eos.moe.armourers.ba;
import eos.moe.armourers.bb;
import eos.moe.armourers.bc;
import eos.moe.armourers.bg;
import eos.moe.armourers.c;
import eos.moe.armourers.ca;
import eos.moe.armourers.ea;
import eos.moe.armourers.fa;
import eos.moe.armourers.ha;
import eos.moe.armourers.hb;
import eos.moe.armourers.hl;
import eos.moe.armourers.ia;
import eos.moe.armourers.ib;
import eos.moe.armourers.ja;
import eos.moe.armourers.ka;
import eos.moe.armourers.kb;
import eos.moe.armourers.la;
import eos.moe.armourers.ma;
import eos.moe.armourers.na;
import eos.moe.armourers.oc;
import eos.moe.armourers.pb;
import eos.moe.armourers.ph;
import eos.moe.armourers.ra;
import eos.moe.armourers.rb;
import eos.moe.armourers.sa;
import eos.moe.armourers.sb;
import eos.moe.armourers.tb;
import eos.moe.armourers.ua;
import eos.moe.armourers.uh;
import eos.moe.armourers.va;
import eos.moe.armourers.vl;
import eos.moe.armourers.xb;
import eos.moe.armourers.ya;
import eos.moe.armourers.za;
import eos.moe.armourers.zb;
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
public class zn {
    private rb z;
    private vl t;
    private ExecutorService w;
    private File r;
    private kb l;
    private boolean c;
    private Charset v;
    private ThreadFactory s;
    private char[] m;
    private boolean j;

    public void y(pb a2, String a3) throws ph {
        zn a4;
        a4.r(a2, a3, null);
    }

    public zn(String a2) {
        a3(new File(a2), null);
        zn a3;
    }

    public void y(File a2, zb a3) throws ph {
        zn a4;
        a4.r(Collections.singletonList(a2), a3);
    }

    public void y(String a2, String a3) throws ph {
        zn a4;
        if (!eos.moe.armourers.c.r(a2)) {
            throw new ph("file name to be changed is null or empty");
        }
        if (!eos.moe.armourers.c.r(a3)) {
            throw new ph("newFileName is null or empty");
        }
        a4.r(Collections.singletonMap(a2, a3));
    }

    public oc r(pb a2) throws IOException {
        zn a3;
        if (a2 == null) {
            throw new ph("FileHeader is null, cannot get InputStream");
        }
        zn zn2 = a3;
        zn2.r();
        if (zn2.l == null) {
            throw new ph("zip model is null, cannot get inputstream");
        }
        return fa.r(a3.l, a2, a3.m);
    }

    public void r(File a2, zb a3) throws ph {
        zn a4;
        if (a2 == null) {
            throw new ph("input path is null, cannot add folder to zip file");
        }
        if (!a2.exists()) {
            throw new ph("folder does not exist");
        }
        if (!a2.isDirectory()) {
            throw new ph("input folder is not a directory");
        }
        if (!a2.canRead()) {
            throw new ph("cannot read input folder");
        }
        if (a3 == null) {
            throw new ph("input parameters are null, cannot add folder to zip file");
        }
        a4.r(a2, a3, true);
    }

    public void r(String a2, String a3, String a4) throws ph {
        zn a5;
        if (!eos.moe.armourers.c.r(a2)) {
            throw new ph("file to extract is null or empty, cannot extract file");
        }
        zn zn2 = a5;
        zn2.r();
        pb pb2 = bg.y(zn2.l, a2);
        if (pb2 == null) {
            throw new ph(new StringBuilder().insert(0, "No file found with name ").append(a2).append(" in zip file").toString(), uh.s);
        }
        a5.r(pb2, a3, a4);
    }

    public void r(List<File> a2, zb a3, boolean a4, long a5) throws ph {
        zn a6;
        if (a6.r.exists()) {
            throw new ph(new StringBuilder().insert(0, "zip file: ").append(a6.r).append(" already exists. To add files to existing zip file use addFile method").toString());
        }
        if (a2 == null || a2.size() == 0) {
            throw new ph("input file List is null, cannot create zip file");
        }
        zn zn2 = a6;
        zn2.y();
        zn2.l.y(a4);
        zn2.l.y(a5);
        zn zn3 = a6;
        new xb(zn3.l, zn3.m, a6.t, a6.r()).r(new tb(a2, a3, a6.v));
    }

    public ExecutorService r() {
        zn a2;
        return a2.w;
    }

    private /* synthetic */ void r(File a2, zb a3, boolean a4) throws ph {
        zn a5;
        zn zn2 = a5;
        zn2.r();
        if (zn2.l == null) {
            throw new ph("internal error: zip model is null");
        }
        if (a4 && a5.l.z()) {
            throw new ph("This is a split archive. Zip file format does not allow updating split/spanned files");
        }
        zn zn3 = a5;
        new hb(zn3.l, zn3.m, a5.t, a5.r()).r(new bb(a2, a3, a5.v));
    }

    public void r(char[] a2) {
        a.m = a2;
    }

    public void z(File a2) throws ph {
        zn a3;
        if (a2 == null) {
            throw new ph("outputZipFile is null, cannot merge split files");
        }
        if (a2.exists()) {
            throw new ph("output Zip File already exists");
        }
        zn zn2 = a3;
        zn2.r();
        if (zn2.l == null) {
            throw new ph("zip model is null, corrupt zip file?");
        }
        new ja(a3.l, a3.r()).r(new ka(a2, a3.v));
    }

    public String r() throws ph {
        zn a2;
        if (!a2.r.exists()) {
            throw new ph("zip file does not exist, cannot read comment");
        }
        zn zn2 = a2;
        zn2.r();
        if (zn2.l == null) {
            throw new ph("zip model is null, cannot read comment");
        }
        if (a2.l.r() == null) {
            throw new ph("end of central directory record is null, cannot read comment");
        }
        return a2.l.r().r();
    }

    public void y(File a2) throws ph {
        zn a3;
        a3.r(Collections.singletonList(a2), new zb());
    }

    public void h(String a2) throws ph {
        zn a3;
        if (a2 == null) {
            throw new ph("input comment is null, cannot update zip file");
        }
        if (!a3.r.exists()) {
            throw new ph("zip file does not exist, cannot set comment for zip file");
        }
        zn zn2 = a3;
        zn2.r();
        if (zn2.l == null) {
            throw new ph("zipModel is null, cannot update zip file");
        }
        if (a3.l.r() == null) {
            throw new ph("end of central directory is null, cannot set comment");
        }
        new ia(a3.l, a3.r()).r(new ea(a2, a3.v));
    }

    public boolean x() throws ph {
        zn zn2;
        block4: {
            zn a2;
            if (a2.l == null) {
                zn zn3 = a2;
                zn3.r();
                if (zn3.l == null) {
                    throw new ph("Zip Model is null");
                }
            }
            if (a2.l.r() == null || a2.l.r().r() == null) {
                throw new ph("invalid zip file");
            }
            for (pb pb2 : a2.l.r().r()) {
                if (pb2 == null || !pb2.r()) continue;
                zn2 = a2;
                a2.j = true;
                break block4;
            }
            zn2 = a2;
        }
        return zn2.j;
    }

    public void r(pb a2) throws ph {
        zn a3;
        if (a2 == null) {
            throw new ph("input file header is null, cannot remove file");
        }
        a3.y(a2.r());
    }

    public boolean h() throws ph {
        zn a2;
        if (a2.l == null) {
            zn zn2 = a2;
            zn2.r();
            if (zn2.l == null) {
                throw new ph("Zip Model is null");
            }
        }
        return a2.l.z();
    }

    private /* synthetic */ void y() {
        zn a2;
        a2.l = new kb();
        a2.l.r(a2.r);
    }

    public boolean z() {
        block4: {
            zn a2;
            if (!a2.r.exists()) {
                return false;
            }
            try {
                zn zn2 = a2;
                zn2.r();
                if (!zn2.l.z()) break block4;
                zn zn3 = a2;
                if (zn3.r(zn3.y())) break block4;
                return false;
            }
            catch (Exception exception) {
                return false;
            }
        }
        return true;
    }

    public void z(String a2) throws ph {
        zn a3;
        if (!eos.moe.armourers.c.r(a2)) {
            throw new ph("output path is null or invalid");
        }
        if (!eos.moe.armourers.c.r(new File(a2))) {
            throw new ph("invalid output path");
        }
        if (a3.l == null) {
            a3.r();
        }
        if (a3.l == null) {
            throw new ph("Internal error occurred when extracting zip file");
        }
        if (a3.z.r() == ib.m) {
            throw new ph("invalid operation - Zip4j is in busy state");
        }
        zn zn2 = a3;
        new ha(zn2.l, zn2.m, a3.r()).r(new za(a2, a3.v));
    }

    public Charset r() {
        zn a2;
        return a2.v;
    }

    public zn(File a2) {
        a3(a2, null);
        zn a3;
    }

    public pb r(String a2) throws ph {
        zn a3;
        if (!eos.moe.armourers.c.r(a2)) {
            throw new ph("input file name is emtpy or null, cannot get FileHeader");
        }
        zn zn2 = a3;
        zn2.r();
        if (zn2.l == null || a3.l.r() == null) {
            return null;
        }
        return bg.y(a3.l, a2);
    }

    public boolean y() {
        zn a2;
        return a2.m != null;
    }

    public void r(ThreadFactory a2) {
        a.s = a2;
    }

    public rb r() {
        zn a2;
        return a2.z;
    }

    public void r(InputStream a2, zb a3) throws ph {
        zn a4;
        if (a2 == null) {
            throw new ph("inputstream is null, cannot add file to zip");
        }
        if (a3 == null) {
            throw new ph("zip parameters are null");
        }
        zn zn2 = a4;
        zn2.r(false);
        zn2.r();
        if (zn2.l == null) {
            throw new ph("internal error: zip model is null");
        }
        if (a4.r.exists() && a4.l.z()) {
            throw new ph("Zip file already exists. Zip file format does not allow updating split/spanned files");
        }
        zn zn3 = a4;
        new ua(zn3.l, zn3.m, a4.t, a4.r()).r(new ba(a2, a3, a4.v));
    }

    public void y(List<String> a2) throws ph {
        zn a3;
        if (a2 == null) {
            throw new ph("fileNames list is null");
        }
        if (a2.isEmpty()) {
            return;
        }
        if (a3.l == null) {
            a3.r();
        }
        if (a3.l.z()) {
            throw new ph("Zip file format does not allow updating split/spanned files");
        }
        zn zn2 = a3;
        new la(zn2.l, zn2.t, a3.r()).r(new sa(a2, a3.v));
    }

    public File r() {
        zn a2;
        return a2.r;
    }

    public void r(boolean a2) {
        a.c = a2;
    }

    public List<File> y() throws ph {
        zn a2;
        zn zn2 = a2;
        zn2.r();
        return ya.r(zn2.l);
    }

    public void r(List<File> a2) throws ph {
        zn a3;
        a3.r(a2, new zb());
    }

    public void r(Map<String, String> a2) throws ph {
        zn a3;
        if (a2 == null) {
            throw new ph("fileNamesMap is null");
        }
        if (a2.size() == 0) {
            return;
        }
        zn zn2 = a3;
        zn2.r();
        if (zn2.l.z()) {
            throw new ph("Zip file format does not allow updating split/spanned files");
        }
        ca ca2 = a3.r();
        zn zn3 = a3;
        new va(zn3.l, zn3.t, new ra(), a3.v, ca2).r(new ma(a2));
    }

    private /* synthetic */ RandomAccessFile r() throws IOException {
        zn a2;
        if (ya.r(a2.r)) {
            File[] fileArray = ya.r(a2.r);
            bc bc2 = new bc(a2.r, sb.s.r(), fileArray);
            bc2.r();
            return bc2;
        }
        return new RandomAccessFile(a2.r, sb.s.r());
    }

    public zn(File a2, char[] a3) {
        zn a4;
        zn zn2 = a4;
        zn zn3 = a4;
        zn zn4 = a4;
        zn4.t = new vl();
        zn3.v = Charset.forName("gbk");
        zn3.r = a2;
        zn2.m = a3;
        zn2.c = false;
        zn2.z = new rb();
    }

    public void r(List<File> a2, zb a3) throws ph {
        zn a4;
        if (a2 == null || a2.size() == 0) {
            throw new ph("input file List is null or empty");
        }
        if (a3 == null) {
            throw new ph("input parameters are null");
        }
        if (a4.z.r() == ib.m) {
            throw new ph("invalid operation - Zip4j is in busy state");
        }
        zn zn2 = a4;
        zn2.r();
        if (zn2.l == null) {
            throw new ph("internal error: zip model is null");
        }
        if (a4.r.exists() && a4.l.z()) {
            throw new ph("Zip file already exists. Zip file format does not allow updating split/spanned files");
        }
        zn zn3 = a4;
        new xb(zn3.l, zn3.m, a4.t, a4.r()).r(new tb(a2, a3, a4.v));
    }

    public void y(String a2) throws ph {
        zn a3;
        if (!eos.moe.armourers.c.r(a2)) {
            throw new ph("file name is empty or null, cannot remove file");
        }
        a3.y(Collections.singletonList(a2));
    }

    public void r(pb a2, String a3) throws ph {
        zn a4;
        if (a2 == null) {
            throw new ph("File header is null");
        }
        a4.y(a2.r(), a3);
    }

    public void r(String a2, zb a3) throws ph {
        zn a4;
        if (!eos.moe.armourers.c.r(a2)) {
            throw new ph("file to add is null or empty");
        }
        a4.r(Collections.singletonList(new File(a2)), a3);
    }

    public void r(String a2) throws ph {
        zn a3;
        a3.r(a2, new zb());
    }

    private /* synthetic */ boolean r(List<File> a2) {
        a2 = a2.iterator();
        while (a2.hasNext()) {
            if (((File)a2.next()).exists()) continue;
            return false;
        }
        return true;
    }

    public void r(Charset a2) throws IllegalArgumentException {
        if (a2 == null) {
            throw new IllegalArgumentException("charset cannot be null");
        }
        a.v = a2;
    }

    public String toString() {
        zn a2;
        return a2.r.toString();
    }

    public void r(pb a2, String a3, String a4) throws ph {
        zn a5;
        if (a2 == null) {
            throw new ph("input file header is null, cannot extract file");
        }
        if (!eos.moe.armourers.c.r(a3)) {
            throw new ph("destination path is empty or null, cannot extract file");
        }
        if (a5.z.r() == ib.m) {
            throw new ph("invalid operation - Zip4j is in busy state");
        }
        a5.r();
        zn zn2 = a5;
        new na(zn2.l, zn2.m, a5.r()).r(new aa(a3, a2, a4, a5.v));
    }

    public zn(String a2, char[] a3) {
        a4(new File(a2), a3);
        zn a4;
    }

    public void r(String a2, String a3) throws ph {
        zn a4;
        a4.r(a2, a3, null);
    }

    private /* synthetic */ ca r() {
        zn a2;
        if (a2.c) {
            if (a2.s == null) {
                a2.s = Executors.defaultThreadFactory();
            }
            a2.w = Executors.newSingleThreadExecutor(a2.s);
        }
        zn zn2 = a2;
        return new ca(zn2.w, zn2.c, a2.z);
    }

    public List<pb> r() throws ph {
        zn a2;
        zn zn2 = a2;
        zn2.r();
        if (zn2.l == null || a2.l.r() == null) {
            return Collections.emptyList();
        }
        return a2.l.r().r();
    }

    public void r(File a2, zb a3, boolean a4, long a5) throws ph {
        zn a6;
        if (a2 == null) {
            throw new ph("folderToAdd is null, cannot create zip file from folder");
        }
        if (a3 == null) {
            throw new ph("input parameters are null, cannot create zip file from folder");
        }
        if (a6.r.exists()) {
            throw new ph(new StringBuilder().insert(0, "zip file: ").append(a6.r).append(" already exists. To add files to existing zip file use addFolder method").toString());
        }
        zn zn2 = a6;
        zn2.y();
        zn2.l.y(a4);
        if (a4) {
            a6.l.y(a5);
        }
        a6.r(a2, a3, false);
    }

    public void r(File a2) throws ph {
        zn a3;
        a3.r(a2, new zb());
    }

    public boolean r() {
        zn a2;
        return a2.c;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private /* synthetic */ void r() throws ph {
        zn a2;
        if (a2.l != null) {
            return;
        }
        if (!a2.r.exists()) {
            a2.y();
            return;
        }
        if (!a2.r.canRead()) {
            throw new ph("no read access for the input zip file");
        }
        try {
            RandomAccessFile randomAccessFile;
            block16: {
                randomAccessFile = a2.r();
                Throwable throwable = null;
                try {
                    hl hl2 = new hl();
                    zn zn2 = a2;
                    zn2.l = hl2.r(randomAccessFile, zn2.v);
                    zn2.l.r(a2.r);
                    if (randomAccessFile == null) return;
                    if (throwable == null) break block16;
                }
                catch (Throwable throwable2) {
                    try {
                        throwable = throwable2;
                        throw throwable;
                    }
                    catch (Throwable throwable3) {
                        Throwable throwable4;
                        if (randomAccessFile != null) {
                            if (throwable != null) {
                                try {
                                    randomAccessFile.close();
                                    throwable4 = throwable3;
                                    throw throwable4;
                                }
                                catch (Throwable throwable5) {
                                    throwable4 = throwable3;
                                    throwable.addSuppressed(throwable5);
                                    throw throwable4;
                                }
                            }
                            randomAccessFile.close();
                        }
                        throwable4 = throwable3;
                        throw throwable4;
                    }
                }
                try {
                    randomAccessFile.close();
                    return;
                }
                catch (Throwable throwable6) {
                    throwable.addSuppressed(throwable6);
                    return;
                }
            }
            randomAccessFile.close();
            return;
        }
        catch (ph ph2) {
            throw ph2;
        }
        catch (IOException iOException) {
            throw new ph(iOException);
        }
    }
}

