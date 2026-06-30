/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.armourers;

public final class yj
extends Enum<yj> {
    public static final /* enum */ yj c = new yj("SPECIFICATION_VERSION", 0, 51);
    private byte v;
    private static final /* synthetic */ yj[] s;
    public static final /* enum */ yj m;
    public static final /* enum */ yj j;

    public byte r() {
        yj a2;
        return a2.v;
    }

    static {
        j = new yj("WINDOWS", 1, 0);
        m = new yj("UNIX", 2, 3);
        yj[] yjArray = new yj[3];
        yjArray[0] = c;
        yjArray[1] = j;
        yjArray[2] = m;
        s = yjArray;
    }

    /*
     * WARNING - Possible parameter corruption
     * WARNING - void declaration
     */
    private /* synthetic */ yj(byte by) {
        void a2;
        void var2_-1;
        void var1_-1;
        yj a3;
        a3.v = a2;
    }

    public static yj[] values() {
        return (yj[])s.clone();
    }

    public static yj valueOf(String a2) {
        return Enum.valueOf(yj.class, a2);
    }
}

