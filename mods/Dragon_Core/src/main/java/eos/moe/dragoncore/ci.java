/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.dragoncore;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class ci {
    public ci() {
        ci a2;
    }

    public static byte ALLATORIxDEMO(String a2) throws NumberFormatException {
        try {
            return Byte.parseByte(a2);
        }
        catch (NumberFormatException a3) {
            throw new NumberFormatException("Not a valid byte");
        }
    }

    public static short ALLATORIxDEMO(String a2) throws NumberFormatException {
        try {
            return Short.parseShort(a2);
        }
        catch (NumberFormatException a3) {
            throw new NumberFormatException("Not a valid short");
        }
    }

    public static int ALLATORIxDEMO(String a2) throws NumberFormatException {
        try {
            return Integer.parseInt(a2);
        }
        catch (NumberFormatException a3) {
            throw new NumberFormatException("Not a valid int");
        }
    }

    public static long ALLATORIxDEMO(String a2) throws NumberFormatException {
        try {
            return Long.parseLong(a2);
        }
        catch (NumberFormatException a3) {
            throw new NumberFormatException("Not a valid long");
        }
    }

    public static float ALLATORIxDEMO(String a2) throws NumberFormatException {
        try {
            return Float.parseFloat(a2);
        }
        catch (NumberFormatException a3) {
            throw new NumberFormatException("Not a valid float");
        }
    }

    public static double ALLATORIxDEMO(String a2) throws NumberFormatException {
        try {
            return Double.parseDouble(a2);
        }
        catch (NumberFormatException a3) {
            throw new NumberFormatException("Not a valid double");
        }
    }

    public static byte[] ALLATORIxDEMO(String a2) throws NumberFormatException {
        try {
            String[] a3 = a2.split(" ");
            byte[] a4 = new byte[a3.length];
            for (int a5 = 0; a5 < a3.length; ++a5) {
                a4[a5] = ci.ALLATORIxDEMO(a3[a5]);
            }
            return a4;
        }
        catch (NumberFormatException a6) {
            throw new NumberFormatException("Not a valid byte array");
        }
    }

    public static int[] ALLATORIxDEMO(String a2) throws NumberFormatException {
        try {
            String[] a3 = a2.split(" ");
            int[] a4 = new int[a3.length];
            for (int a5 = 0; a5 < a3.length; ++a5) {
                a4[a5] = ci.ALLATORIxDEMO(a3[a5]);
            }
            return a4;
        }
        catch (NumberFormatException a6) {
            throw new NumberFormatException("Not a valid int array");
        }
    }
}

