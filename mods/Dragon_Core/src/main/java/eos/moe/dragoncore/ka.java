/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.bc;
import eos.moe.dragoncore.dc;
import eos.moe.dragoncore.la;
import eos.moe.dragoncore.lc;
import eos.moe.dragoncore.q;
import eos.moe.dragoncore.qc;
import eos.moe.dragoncore.ta;
import eos.moe.dragoncore.uc;
import eos.moe.dragoncore.xb;
import eos.moe.dragoncore.yk;
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
public class ka {
    public static final byte[] k = new byte[]{0, 0, -128, -127};
    public static final byte[] ALLATORIxDEMO = new byte[]{0, 0, -128, 65};

    public ka() {
        ka a2;
    }

    public static void f(Path a2, byte[] a3) {
        if (a3 == null || a3.length == 0) {
            return;
        }
        if (ka.f()) {
            ka.c(a2, a3);
        } else if (ka.c() || ka.ALLATORIxDEMO()) {
            ka.ALLATORIxDEMO(a2, a3);
        }
    }

    public static void ALLATORIxDEMO(Path a2, long a3) {
        if (a3 <= 0L || !Files.exists(a2, new LinkOption[0])) {
            return;
        }
        try {
            Files.setLastModifiedTime(a2, FileTime.fromMillis(ta.c(a3)));
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public static void ALLATORIxDEMO(File a2, long a3) {
        a2.setLastModified(ta.c(a3));
    }

    public static byte[] ALLATORIxDEMO(File a2) {
        try {
            if (a2 == null || !Files.isSymbolicLink(a2.toPath()) && !a2.exists()) {
                return new byte[4];
            }
            Path a3 = a2.toPath();
            if (ka.f()) {
                return ka.c(a3);
            }
            if (ka.c() || ka.ALLATORIxDEMO()) {
                return ka.ALLATORIxDEMO(a3);
            }
            return new byte[4];
        }
        catch (NoSuchMethodError a4) {
            return new byte[4];
        }
    }

    public static List<File> ALLATORIxDEMO(File a2, boolean a3, boolean a4) throws yk {
        return ka.ALLATORIxDEMO(a2, a3, a4, null);
    }

    public static List<File> ALLATORIxDEMO(File a2, boolean a3, boolean a4, q a5) throws yk {
        if (a2 == null) {
            throw new yk("input path is null, cannot read files in the directory");
        }
        ArrayList<File> a6 = new ArrayList<File>();
        File[] a7 = a2.listFiles();
        if (!a2.isDirectory() || !a2.canRead() || a7 == null) {
            return a6;
        }
        for (File a8 : a7) {
            if (a5 != null && a5.ALLATORIxDEMO(a8) || a8.isHidden() && (a8.isDirectory() ? !a4 : !a3)) continue;
            a6.add(a8);
            if (!a8.isDirectory()) continue;
            a6.addAll(ka.ALLATORIxDEMO(a8, a3, a4, a5));
        }
        return a6;
    }

    public static String c(String a2) {
        int a3 = a2.lastIndexOf(".");
        if (a3 == -1) {
            return a2;
        }
        return a2.substring(0, a3);
    }

    public static String ALLATORIxDEMO(String a2) throws yk {
        if (!ta.ALLATORIxDEMO(a2)) {
            throw new yk("zip file name is empty or null, cannot determine zip file name");
        }
        String a3 = a2;
        if (a2.contains(System.getProperty("file.separator"))) {
            a3 = a2.substring(a2.lastIndexOf(System.getProperty("file.separator")) + 1);
        }
        if (a3.endsWith(".zip")) {
            a3 = a3.substring(0, a3.lastIndexOf("."));
        }
        return a3;
    }

    public static List<File> ALLATORIxDEMO(dc a2) throws yk {
        if (a2 == null) {
            throw new yk("cannot get split zip files: zipmodel is null");
        }
        if (a2.ALLATORIxDEMO() == null) {
            return null;
        }
        if (!a2.ALLATORIxDEMO().exists()) {
            throw new yk("zip file does not exist");
        }
        ArrayList<File> a3 = new ArrayList<File>();
        File a4 = a2.ALLATORIxDEMO();
        if (!a2.f()) {
            a3.add(a4);
            return a3;
        }
        int a5 = a2.ALLATORIxDEMO().d();
        if (a5 == 0) {
            a3.add(a4);
            return a3;
        }
        for (int a6 = 0; a6 <= a5; ++a6) {
            if (a6 == a5) {
                a3.add(a2.ALLATORIxDEMO());
                continue;
            }
            String a7 = ".z0";
            if (a6 >= 9) {
                a7 = ".z";
            }
            String a8 = a4.getName().contains(".") ? a4.getPath().substring(0, a4.getPath().lastIndexOf(".")) : a4.getPath();
            a8 = a8 + a7 + (a6 + 1);
            a3.add(new File(a8));
        }
        return a3;
    }

    public static String ALLATORIxDEMO(File a2, lc a3) throws yk {
        String a4;
        String a5;
        try {
            a5 = a2.getCanonicalPath();
            if (ta.ALLATORIxDEMO(a3.x())) {
                String a6;
                Object a7;
                File a8 = new File(a3.x());
                String a9 = a8.getCanonicalPath();
                if (!a9.endsWith(la.b)) {
                    a9 = a9 + la.b;
                }
                if (ka.ALLATORIxDEMO(a2)) {
                    a7 = new File(a2.getParentFile().getCanonicalFile().getPath() + File.separator + a2.getCanonicalFile().getName()).getPath();
                    a6 = ((String)a7).substring(a9.length());
                } else {
                    a6 = a5.substring(a9.length());
                }
                if (a6.startsWith(System.getProperty("file.separator"))) {
                    a6 = a6.substring(1);
                }
                if (((File)(a7 = new File(a5))).isDirectory()) {
                    a6 = a6.replaceAll("\\\\", "/");
                    a6 = a6 + "/";
                } else {
                    String a10 = a6.substring(0, a6.lastIndexOf(((File)a7).getName()));
                    a10 = a10.replaceAll("\\\\", "/");
                    a6 = a10 + ka.ALLATORIxDEMO((File)a7, a3.f());
                }
                a4 = a6;
            } else {
                File a11 = new File(a5);
                a4 = ka.ALLATORIxDEMO(a11, a3.f());
                if (a11.isDirectory()) {
                    a4 = a4 + "/";
                }
            }
        }
        catch (IOException a12) {
            throw new yk(a12);
        }
        a5 = a3.c();
        if (ta.ALLATORIxDEMO(a5)) {
            if (!a5.endsWith("\\") && !a5.endsWith("/")) {
                a5 = a5 + la.b;
            }
            a5 = a5.replaceAll("\\\\", "/");
            a4 = a5 + a4;
        }
        return a4;
    }

    private static /* synthetic */ String ALLATORIxDEMO(File a2, String a3) throws IOException {
        if (ta.ALLATORIxDEMO(a3)) {
            return a3;
        }
        if (ka.ALLATORIxDEMO(a2)) {
            return a2.toPath().toRealPath(LinkOption.NOFOLLOW_LINKS).getFileName().toString();
        }
        return a2.getName();
    }

    public static boolean ALLATORIxDEMO(String a2) {
        return a2.endsWith("/") || a2.endsWith("\\");
    }

    public static void ALLATORIxDEMO(RandomAccessFile a2, OutputStream a3, long a4, long a5, uc a6) throws yk {
        if (a4 < 0L || a5 < 0L || a4 > a5) {
            throw new yk("invalid offsets");
        }
        if (a4 == a5) {
            return;
        }
        try {
            int a7;
            a2.seek(a4);
            long a8 = 0L;
            long a9 = a5 - a4;
            byte[] a10 = a5 - a4 < 4096L ? new byte[(int)a9] : new byte[4096];
            while ((a7 = a2.read(a10)) != -1) {
                a3.write(a10, 0, a7);
                a6.c(a7);
                if (a6.c()) {
                    a6.ALLATORIxDEMO(bc.k);
                    return;
                }
                if ((a8 += (long)a7) != a9) {
                    if (a8 + (long)a10.length <= a9) continue;
                    a10 = new byte[(int)(a9 - a8)];
                    continue;
                }
                break;
            }
        }
        catch (IOException a11) {
            throw new yk(a11);
        }
    }

    public static void ALLATORIxDEMO(List<File> a2, qc a3) throws yk {
        for (File a4 : a2) {
            if (ka.ALLATORIxDEMO(a4)) {
                if (!a3.equals((Object)qc.k) && !a3.equals((Object)qc.y)) continue;
                ka.ALLATORIxDEMO(a4);
                continue;
            }
            ka.c(a4);
        }
    }

    public static boolean c(File a2) {
        return a2.getName().endsWith(".zip.001");
    }

    public static String c(File a2) {
        String a3 = a2.getName();
        if (!a3.contains(".")) {
            return "";
        }
        return a3.substring(a3.lastIndexOf(".") + 1);
    }

    public static File[] ALLATORIxDEMO(File a2) {
        String a5 = ka.c(a2.getName());
        Object[] a6 = a2.getParentFile().listFiles((a3, a4) -> a4.startsWith(a5 + "."));
        if (a6 == null) {
            return new File[0];
        }
        Arrays.sort(a6);
        return a6;
    }

    public static String c(int a2) {
        return "." + ka.ALLATORIxDEMO(a2) + (a2 + 1);
    }

    public static boolean ALLATORIxDEMO(File a2) {
        try {
            return Files.isSymbolicLink(a2.toPath());
        }
        catch (Error | Exception a3) {
            return false;
        }
    }

    public static String ALLATORIxDEMO(File a2) {
        try {
            return Files.readSymbolicLink(a2.toPath()).toString();
        }
        catch (Error | Exception a3) {
            return "";
        }
    }

    public static byte[] ALLATORIxDEMO(boolean a2) {
        byte[] a3 = new byte[4];
        if (ka.ALLATORIxDEMO() || ka.c()) {
            if (a2) {
                System.arraycopy(ALLATORIxDEMO, 0, a3, 0, a3.length);
            } else {
                System.arraycopy(k, 0, a3, 0, a3.length);
            }
        }
        return a3;
    }

    public static boolean f() {
        String a2 = System.getProperty("os.name").toLowerCase();
        return a2.contains("win");
    }

    public static boolean c() {
        String a2 = System.getProperty("os.name").toLowerCase();
        return a2.contains("mac");
    }

    public static boolean ALLATORIxDEMO() {
        String a2 = System.getProperty("os.name").toLowerCase();
        return a2.contains("nux");
    }

    private static /* synthetic */ String ALLATORIxDEMO(int a2) {
        if (a2 < 9) {
            return "00";
        }
        if (a2 < 99) {
            return "0";
        }
        return "";
    }

    private static /* synthetic */ void c(Path a2, byte[] a3) {
        if (a3[0] == 0) {
            return;
        }
        DosFileAttributeView a4 = Files.getFileAttributeView(a2, DosFileAttributeView.class, LinkOption.NOFOLLOW_LINKS);
        try {
            a4.setReadOnly(xb.ALLATORIxDEMO(a3[0], 0));
            a4.setHidden(xb.ALLATORIxDEMO(a3[0], 1));
            a4.setSystem(xb.ALLATORIxDEMO(a3[0], 2));
            a4.setArchive(xb.ALLATORIxDEMO(a3[0], 5));
        }
        catch (IOException iOException) {
            // empty catch block
        }
    }

    private static /* synthetic */ void ALLATORIxDEMO(Path a2, byte[] a3) {
        if (a3[2] == 0 && a3[3] == 0) {
            return;
        }
        try {
            HashSet<PosixFilePermission> a4 = new HashSet<PosixFilePermission>();
            ka.ALLATORIxDEMO(a3[3], 0, a4, PosixFilePermission.OWNER_READ);
            ka.ALLATORIxDEMO(a3[2], 7, a4, PosixFilePermission.OWNER_WRITE);
            ka.ALLATORIxDEMO(a3[2], 6, a4, PosixFilePermission.OWNER_EXECUTE);
            ka.ALLATORIxDEMO(a3[2], 5, a4, PosixFilePermission.GROUP_READ);
            ka.ALLATORIxDEMO(a3[2], 4, a4, PosixFilePermission.GROUP_WRITE);
            ka.ALLATORIxDEMO(a3[2], 3, a4, PosixFilePermission.GROUP_EXECUTE);
            ka.ALLATORIxDEMO(a3[2], 2, a4, PosixFilePermission.OTHERS_READ);
            ka.ALLATORIxDEMO(a3[2], 1, a4, PosixFilePermission.OTHERS_WRITE);
            ka.ALLATORIxDEMO(a3[2], 0, a4, PosixFilePermission.OTHERS_EXECUTE);
            Files.setPosixFilePermissions(a2, a4);
        }
        catch (IOException iOException) {
            // empty catch block
        }
    }

    private static /* synthetic */ byte[] c(Path a2) {
        byte[] a3 = new byte[4];
        try {
            DosFileAttributeView a4 = Files.getFileAttributeView(a2, DosFileAttributeView.class, LinkOption.NOFOLLOW_LINKS);
            DosFileAttributes a5 = a4.readAttributes();
            byte a6 = 0;
            a6 = ka.ALLATORIxDEMO(a5.isReadOnly(), a6, 0);
            a6 = ka.ALLATORIxDEMO(a5.isHidden(), a6, 1);
            a6 = ka.ALLATORIxDEMO(a5.isSystem(), a6, 2);
            a3[0] = a6 = ka.ALLATORIxDEMO(a5.isArchive(), a6, 5);
        }
        catch (IOException iOException) {
            // empty catch block
        }
        return a3;
    }

    private static /* synthetic */ void c(File a2) throws yk {
        if (!a2.exists()) {
            throw new yk("File does not exist: " + a2);
        }
    }

    private static /* synthetic */ void ALLATORIxDEMO(File a2) throws yk {
        if (!a2.exists()) {
            throw new yk("Symlink target '" + ka.ALLATORIxDEMO(a2) + "' does not exist for link '" + a2 + "'");
        }
    }

    private static /* synthetic */ byte[] ALLATORIxDEMO(Path a2) {
        byte[] a3 = new byte[4];
        try {
            PosixFileAttributeView a4 = Files.getFileAttributeView(a2, PosixFileAttributeView.class, LinkOption.NOFOLLOW_LINKS);
            Set<PosixFilePermission> a5 = a4.readAttributes().permissions();
            a3[3] = ka.ALLATORIxDEMO(Files.isRegularFile(a2, new LinkOption[0]), a3[3], 7);
            a3[3] = ka.ALLATORIxDEMO(Files.isDirectory(a2, new LinkOption[0]), a3[3], 6);
            a3[3] = ka.ALLATORIxDEMO(Files.isSymbolicLink(a2), a3[3], 5);
            a3[3] = ka.ALLATORIxDEMO(a5.contains((Object)PosixFilePermission.OWNER_READ), a3[3], 0);
            a3[2] = ka.ALLATORIxDEMO(a5.contains((Object)PosixFilePermission.OWNER_WRITE), a3[2], 7);
            a3[2] = ka.ALLATORIxDEMO(a5.contains((Object)PosixFilePermission.OWNER_EXECUTE), a3[2], 6);
            a3[2] = ka.ALLATORIxDEMO(a5.contains((Object)PosixFilePermission.GROUP_READ), a3[2], 5);
            a3[2] = ka.ALLATORIxDEMO(a5.contains((Object)PosixFilePermission.GROUP_WRITE), a3[2], 4);
            a3[2] = ka.ALLATORIxDEMO(a5.contains((Object)PosixFilePermission.GROUP_EXECUTE), a3[2], 3);
            a3[2] = ka.ALLATORIxDEMO(a5.contains((Object)PosixFilePermission.OTHERS_READ), a3[2], 2);
            a3[2] = ka.ALLATORIxDEMO(a5.contains((Object)PosixFilePermission.OTHERS_WRITE), a3[2], 1);
            a3[2] = ka.ALLATORIxDEMO(a5.contains((Object)PosixFilePermission.OTHERS_EXECUTE), a3[2], 0);
        }
        catch (IOException iOException) {
            // empty catch block
        }
        return a3;
    }

    private static /* synthetic */ byte ALLATORIxDEMO(boolean a2, byte a3, int a4) {
        if (a2) {
            a3 = xb.c(a3, a4);
        }
        return a3;
    }

    private static /* synthetic */ void ALLATORIxDEMO(byte a2, int a3, Set<PosixFilePermission> a4, PosixFilePermission a5) {
        if (xb.ALLATORIxDEMO(a2, a3)) {
            a4.add(a5);
        }
    }
}

