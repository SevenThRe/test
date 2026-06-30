/*
 * Decompiled with CFR 0.152.
 */
package imcheck;

public class e {
    public static final int s = -1;
    public static final int g = 0;
    public static final int t = 1;
    public static final int r = 2;
    public static final int x = 3;
    public static final int v = 4;
    public static final int m = 5;
    public static final int c = 6;
    public static final int q = 7;
    public static final int b = 8;
    public static final int o = 9;
    public static final int y = 10;
    public static final int k = 11;
    public static final int ALLATORIxDEMO;

    public e() {
        e a2;
    }

    static {
        String a2 = System.getProperty("os.name");
        if (a2.startsWith("Linux")) {
            if ("dalvik".equals(System.getProperty("java.vm.name").toLowerCase())) {
                ALLATORIxDEMO = 8;
                System.setProperty("jna.nounpack", "true");
            } else {
                ALLATORIxDEMO = 1;
            }
        } else {
            ALLATORIxDEMO = a2.startsWith("AIX") ? 7 : (a2.startsWith("Mac") || a2.startsWith("Darwin") ? 0 : (a2.startsWith("Windows CE") ? 6 : (a2.startsWith("Windows") ? 2 : (a2.startsWith("Solaris") || a2.startsWith("SunOS") ? 3 : (a2.startsWith("FreeBSD") ? 4 : (a2.startsWith("OpenBSD") ? 5 : ("gnu".equalsIgnoreCase(a2) ? 9 : ("gnu/kfreebsd".equalsIgnoreCase(a2) ? 10 : ("netbsd".equalsIgnoreCase(a2) ? 11 : -1)))))))));
        }
    }
}

