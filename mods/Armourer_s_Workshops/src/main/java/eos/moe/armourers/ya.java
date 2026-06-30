/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.armourers;

import eos.moe.armourers.c;
import eos.moe.armourers.ga;
import eos.moe.armourers.h;
import eos.moe.armourers.jb;
import eos.moe.armourers.kb;
import eos.moe.armourers.ph;
import eos.moe.armourers.rb;
import eos.moe.armourers.ub;
import eos.moe.armourers.wa;
import eos.moe.armourers.zb;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.attribute.DosFileAttributeView;
import java.nio.file.attribute.DosFileAttributes;
import java.nio.file.attribute.FileTime;
import java.nio.file.attribute.PosixFileAttributeView;
import java.nio.file.attribute.PosixFilePermission;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class ya {
    public static final byte[] m;
    public static final byte[] j;

    public static boolean r(String a2) {
        return a2.endsWith("/") || a2.endsWith("\\");
    }

    public static byte[] r(boolean a2) {
        byte[] byArray = new byte[4];
        if (ya.z() || ya.y()) {
            if (a2) {
                System.arraycopy(j, 0, byArray, 0, byArray.length);
                return byArray;
            }
            System.arraycopy(m, 0, byArray, 0, byArray.length);
        }
        return byArray;
    }

    private static /* synthetic */ void y(File a2) throws ph {
        if (!a2.exists()) {
            throw new ph(new StringBuilder().insert(0, "Symlink target '").append(ya.y(a2)).append("' does not exist for link '").append(a2).append("'").toString());
        }
    }

    public static boolean y(File a2) {
        try {
            return Files.isSymbolicLink(a2.toPath());
        }
        catch (Error | Exception throwable) {
            return false;
        }
    }

    public static void r(List<File> a2, ub a3) throws ph {
        for (File file : a2) {
            if (ya.y(file)) {
                if (!a3.equals((Object)ub.j) && !a3.equals((Object)ub.s)) continue;
                ya.y(file);
                continue;
            }
            ya.r(file);
        }
    }

    public static String y(File a2) {
        try {
            return Files.readSymbolicLink(a2.toPath()).toString();
        }
        catch (Error | Exception throwable) {
            return "";
        }
    }

    private static /* synthetic */ void r(byte a2, int a3, Set<PosixFilePermission> a4, PosixFilePermission a5) {
        if (wa.r(a2, a3)) {
            a4.add(a5);
        }
    }

    /*
     * Unable to fully structure code
     */
    public static void r(RandomAccessFile a, OutputStream a, long a, long a, rb a) throws ph {
        block9: {
            if (a < 0L || a < 0L || a > a) {
                throw new ph("invalid offsets");
            }
            if (a == a) {
                return;
            }
            a.seek(a);
            var7_7 = 0L;
            var9_8 = a - a;
            if (a - a >= 4096L) break block9;
            var3_9 = new byte[(int)var9_8];
            v0 = a;
            ** GOTO lbl22
        }
        try {
            var3_9 = new byte[4096];
            while (true) {
                v0 = a;
lbl22:
                // 2 sources

                a = v0.read(var3_9);
                if (a != -1) {
                    v1 = a;
                    a.write(var3_9, 0, a);
                    v1.r((long)a);
                    if (v1.r()) {
                        a.r(jb.s);
                        return;
                    }
                    if ((var7_7 += (long)a) == var9_8) {
                        return;
                    }
                    if (var7_7 + (long)var3_9.length <= var9_8) continue;
                    var3_9 = new byte[(int)(var9_8 - var7_7)];
                    continue;
                }
                break;
            }
        }
        catch (IOException a) {
            throw new ph(a);
        }
    }

    public static boolean z() {
        return System.getProperty("os.name").toLowerCase().contains("nux");
    }

    public static File[] r(File a2) {
        File file = a2;
        Object[] objectArray = ya.r(file.getName());
        objectArray = file.getParentFile().listFiles((arg_0, arg_1) -> ya.r((String)objectArray, arg_0, arg_1));
        if (objectArray == null) {
            return new File[0];
        }
        Arrays.sort(objectArray);
        return objectArray;
    }

    private static /* synthetic */ void z(Path a2, byte[] a32) {
        if (a32[0] == 0) {
            return;
        }
        LinkOption[] linkOptionArray = new LinkOption[1];
        linkOptionArray[0] = LinkOption.NOFOLLOW_LINKS;
        DosFileAttributeView dosFileAttributeView = Files.getFileAttributeView(a2, DosFileAttributeView.class, linkOptionArray);
        try {
            DosFileAttributeView dosFileAttributeView2 = dosFileAttributeView;
            dosFileAttributeView2.setReadOnly(wa.r(a32[0], 0));
            dosFileAttributeView2.setHidden(wa.r(a32[0], 1));
            dosFileAttributeView.setSystem(wa.r(a32[0], 2));
            dosFileAttributeView.setArchive(wa.r(a32[0], 5));
            return;
        }
        catch (IOException a32) {
            return;
        }
    }

    public static List<File> r(File a2, boolean a3, boolean a4, h a5) throws ph {
        int n2;
        if (a2 == null) {
            throw new ph("input path is null, cannot read files in the directory");
        }
        ArrayList<File> arrayList = new ArrayList<File>();
        File file = a2;
        File[] fileArray = file.listFiles();
        if (!file.isDirectory() || !a2.canRead() || fileArray == null) {
            return arrayList;
        }
        int n3 = fileArray.length;
        int n4 = n2 = 0;
        while (n4 < n3) {
            File file2 = fileArray[n2];
            if (!(a5 != null && a5.r(file2) || file2.isHidden() && (file2.isDirectory() ? !a4 : !a3))) {
                arrayList.add(file2);
                if (file2.isDirectory()) {
                    arrayList.addAll(ya.r(file2, a3, a4, a5));
                }
            }
            n4 = ++n2;
        }
        return arrayList;
    }

    public ya() {
        ya a2;
    }

    private static /* synthetic */ byte r(boolean a2, byte a3, int a4) {
        if (a2) {
            a3 = wa.y(a3, a4);
        }
        return a3;
    }

    public static void y(Path a2, byte[] a3) {
        if (a3 == null || a3.length == 0) {
            return;
        }
        if (ya.r()) {
            ya.z(a2, a3);
            return;
        }
        if (ya.y() || ya.z()) {
            ya.r(a2, a3);
        }
    }

    private static /* synthetic */ boolean r(String a2, File a3, String a4) {
        return a4.startsWith(a2 + ".");
    }

    public static void r(File a2, long a3) {
        a2.setLastModified(c.r(a3));
    }

    private static /* synthetic */ byte[] y(Path a2) {
        byte[] byArray = new byte[4];
        try {
            LinkOption[] linkOptionArray = new LinkOption[1];
            linkOptionArray[0] = LinkOption.NOFOLLOW_LINKS;
            PosixFileAttributeView posixFileAttributeView = Files.getFileAttributeView(a2, PosixFileAttributeView.class, linkOptionArray);
            Set<PosixFilePermission> set = posixFileAttributeView.readAttributes().permissions();
            byArray[3] = ya.r(Files.isRegularFile(a2, new LinkOption[0]), byArray[3], 7);
            byArray[3] = ya.r(Files.isDirectory(a2, new LinkOption[0]), byArray[3], 6);
            byArray[3] = ya.r(Files.isSymbolicLink(a2), byArray[3], 5);
            byArray[3] = ya.r(set.contains((Object)PosixFilePermission.OWNER_READ), byArray[3], 0);
            byArray[2] = ya.r(set.contains((Object)PosixFilePermission.OWNER_WRITE), byArray[2], 7);
            byArray[2] = ya.r(set.contains((Object)PosixFilePermission.OWNER_EXECUTE), byArray[2], 6);
            byArray[2] = ya.r(set.contains((Object)PosixFilePermission.GROUP_READ), byArray[2], 5);
            byArray[2] = ya.r(set.contains((Object)PosixFilePermission.GROUP_WRITE), byArray[2], 4);
            byArray[2] = ya.r(set.contains((Object)PosixFilePermission.GROUP_EXECUTE), byArray[2], 3);
            byArray[2] = ya.r(set.contains((Object)PosixFilePermission.OTHERS_READ), byArray[2], 2);
            byArray[2] = ya.r(set.contains((Object)PosixFilePermission.OTHERS_WRITE), byArray[2], 1);
            byArray[2] = ya.r(set.contains((Object)PosixFilePermission.OTHERS_EXECUTE), byArray[2], 0);
            return byArray;
        }
        catch (IOException iOException) {
            return byArray;
        }
    }

    private static /* synthetic */ void r(File a2) throws ph {
        if (!a2.exists()) {
            throw new ph(new StringBuilder().insert(0, "File does not exist: ").append(a2).toString());
        }
    }

    public static List<File> r(File a2, boolean a3, boolean a4) throws ph {
        return ya.r(a2, a3, a4, null);
    }

    public static boolean y() {
        return System.getProperty("os.name").toLowerCase().contains("mac");
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static String r(File a2, zb a3) throws ph {
        String string;
        String string2;
        block13: {
            try {
                string2 = a2.getCanonicalPath();
                if (c.r(a3.h())) {
                    String string3;
                    String string4;
                    String string5;
                    Object object;
                    File file = new File(a3.h());
                    String string6 = file.getCanonicalPath();
                    if (!string6.endsWith(ga.e)) {
                        string6 = new StringBuilder().insert(0, string6).append(ga.e).toString();
                    }
                    if (ya.y(a2)) {
                        object = new File(new StringBuilder().insert(0, a2.getParentFile().getCanonicalFile().getPath()).append(File.separator).append(a2.getCanonicalFile().getName()).toString()).getPath();
                        string4 = string5 = ((String)object).substring(string6.length());
                    } else {
                        string4 = string5 = string2.substring(string6.length());
                    }
                    if (string4.startsWith(System.getProperty("file.separator"))) {
                        string5 = string5.substring(1);
                    }
                    if (((File)(object = new File(string2))).isDirectory()) {
                        string5 = string5.replaceAll("\\\\", "/");
                        string3 = string5 = new StringBuilder().insert(0, string5).append("/").toString();
                    } else {
                        string6 = string5.substring(0, string5.lastIndexOf(((File)object).getName()));
                        string6 = string6.replaceAll("\\\\", "/");
                        string3 = string5 = new StringBuilder().insert(0, string6).append(ya.r((File)object, a3.r())).toString();
                    }
                    string = string3;
                    break block13;
                }
                File file = new File(string2);
                string = ya.r(file, a3.r());
                if (file.isDirectory()) {
                    string = new StringBuilder().insert(0, string).append("/").toString();
                }
            }
            catch (IOException iOException) {
                throw new ph(iOException);
            }
        }
        string2 = a3.z();
        if (!c.r(string2)) return string;
        if (!string2.endsWith("\\") && !string2.endsWith("/")) {
            string2 = new StringBuilder().insert(0, string2).append(ga.e).toString();
        }
        string2 = string2.replaceAll("\\\\", "/");
        return new StringBuilder().insert(0, string2).append(string).toString();
    }

    public static boolean r() {
        return System.getProperty("os.name").toLowerCase().contains("win");
    }

    public static byte[] r(File a2) {
        Path path;
        block6: {
            block5: {
                byte[] byArray;
                try {
                    if (a2 != null && (Files.isSymbolicLink(a2.toPath()) || a2.exists())) break block5;
                    byArray = new byte[4];
                }
                catch (NoSuchMethodError noSuchMethodError) {
                    return new byte[4];
                }
                return byArray;
            }
            path = a2.toPath();
            if (!ya.r()) break block6;
            return ya.r(path);
        }
        if (ya.y() || ya.z()) {
            return ya.y(path);
        }
        byte[] byArray = new byte[4];
        return byArray;
    }

    private static /* synthetic */ String r(File a2, String a3) throws IOException {
        if (c.r(a3)) {
            return a3;
        }
        if (ya.y(a2)) {
            LinkOption[] linkOptionArray = new LinkOption[1];
            linkOptionArray[0] = LinkOption.NOFOLLOW_LINKS;
            return a2.toPath().toRealPath(linkOptionArray).getFileName().toString();
        }
        return a2.getName();
    }

    private static /* synthetic */ void r(Path a2, byte[] a3) {
        if (a3[2] == 0 && a3[3] == 0) {
            return;
        }
        try {
            HashSet<PosixFilePermission> hashSet = new HashSet<PosixFilePermission>();
            byte[] byArray = a3;
            ya.r(a3[3], 0, hashSet, PosixFilePermission.OWNER_READ);
            ya.r(a3[2], 7, hashSet, PosixFilePermission.OWNER_WRITE);
            ya.r(a3[2], 6, hashSet, PosixFilePermission.OWNER_EXECUTE);
            ya.r(a3[2], 5, hashSet, PosixFilePermission.GROUP_READ);
            ya.r(a3[2], 4, hashSet, PosixFilePermission.GROUP_WRITE);
            ya.r(a3[2], 3, hashSet, PosixFilePermission.GROUP_EXECUTE);
            ya.r(byArray[2], 2, hashSet, PosixFilePermission.OTHERS_READ);
            ya.r(byArray[2], 1, hashSet, PosixFilePermission.OTHERS_WRITE);
            HashSet<PosixFilePermission> hashSet2 = hashSet;
            ya.r(a3[2], 0, hashSet2, PosixFilePermission.OTHERS_EXECUTE);
            Files.setPosixFilePermissions(a2, hashSet2);
            return;
        }
        catch (IOException iOException) {
            return;
        }
    }

    public static String y(String a2) throws ph {
        if (!c.r(a2)) {
            throw new ph("zip file name is empty or null, cannot determine zip file name");
        }
        String string = a2;
        if (string.contains(System.getProperty("file.separator"))) {
            String string2 = a2;
            string = string2.substring(string2.lastIndexOf(System.getProperty("file.separator")) + 1);
        }
        if (string.endsWith(".zip")) {
            String string3 = string;
            string = string3.substring(0, string3.lastIndexOf("."));
        }
        return string;
    }

    private static /* synthetic */ byte[] r(Path a2) {
        byte[] byArray = new byte[4];
        try {
            LinkOption[] linkOptionArray = new LinkOption[1];
            linkOptionArray[0] = LinkOption.NOFOLLOW_LINKS;
            DosFileAttributeView dosFileAttributeView = Files.getFileAttributeView(a2, DosFileAttributeView.class, linkOptionArray);
            DosFileAttributes dosFileAttributes = dosFileAttributeView.readAttributes();
            byte by = 0;
            DosFileAttributes dosFileAttributes2 = dosFileAttributes;
            by = ya.r(dosFileAttributes2.isReadOnly(), by, 0);
            by = ya.r(dosFileAttributes2.isHidden(), by, 1);
            by = ya.r(dosFileAttributes2.isSystem(), by, 2);
            byArray[0] = by = ya.r(dosFileAttributes2.isArchive(), by, 5);
            return byArray;
        }
        catch (IOException iOException) {
            return byArray;
        }
    }

    static {
        byte[] byArray = new byte[4];
        byArray[0] = 0;
        byArray[1] = 0;
        byArray[2] = -128;
        byArray[3] = -127;
        m = byArray;
        byte[] byArray2 = new byte[4];
        byArray2[0] = 0;
        byArray2[1] = 0;
        byArray2[2] = -128;
        byArray2[3] = 65;
        j = byArray2;
    }

    public static String r(File a2) {
        String string = a2.getName();
        if (!string.contains(".")) {
            return "";
        }
        String string2 = string;
        return string2.substring(string2.lastIndexOf(".") + 1);
    }

    public static void r(Path a2, long a32) {
        block5: {
            block4: {
                if (a32 <= 0L) break block4;
                if (Files.exists(a2, new LinkOption[0])) break block5;
            }
            return;
        }
        try {
            Files.setLastModifiedTime(a2, FileTime.fromMillis(c.r(a32)));
            return;
        }
        catch (Exception a32) {
            return;
        }
    }

    public static boolean r(File a2) {
        return a2.getName().endsWith(".zip.001");
    }

    public static String y(int a2) {
        return new StringBuilder().insert(0, ".").append(ya.r(a2)).append(a2 + 1).toString();
    }

    public static String r(String a2) {
        int n2 = a2.lastIndexOf(".");
        if (n2 == -1) {
            return a2;
        }
        return a2.substring(0, n2);
    }

    public static List<File> r(kb a2) throws ph {
        int n2;
        if (a2 == null) {
            throw new ph("cannot get split zip files: zipmodel is null");
        }
        if (a2.r() == null) {
            return null;
        }
        if (!a2.r().exists()) {
            throw new ph("zip file does not exist");
        }
        ArrayList<File> arrayList = new ArrayList<File>();
        kb kb2 = a2;
        File file = kb2.r();
        if (!kb2.z()) {
            ArrayList<File> arrayList2 = arrayList;
            arrayList2.add(file);
            return arrayList2;
        }
        int n3 = a2.r().z();
        if (n3 == 0) {
            ArrayList<File> arrayList3 = arrayList;
            arrayList3.add(file);
            return arrayList3;
        }
        int n4 = n2 = 0;
        while (n4 <= n3) {
            if (n2 == n3) {
                arrayList.add(a2.r());
            } else {
                String string = ".z0";
                if (n2 >= 9) {
                    string = ".z";
                }
                File file2 = file;
                String string2 = file.getName().contains(".") ? file2.getPath().substring(0, file.getPath().lastIndexOf(".")) : file2.getPath();
                string2 = new StringBuilder().insert(0, string2).append(string).append(n2 + 1).toString();
                arrayList.add(new File(string2));
            }
            n4 = ++n2;
        }
        return arrayList;
    }

    private static /* synthetic */ String r(int a2) {
        if (a2 < 9) {
            return "00";
        }
        if (a2 < 99) {
            return "0";
        }
        return "";
    }
}

