/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.armourers;

public final class ce
extends Enum<ce> {
    private static final /* synthetic */ ce[] l;
    public static final /* enum */ ce c = new ce("DEFAULT", 0, 10);
    private int v;
    public static final /* enum */ ce s;
    public static final /* enum */ ce m;
    public static final /* enum */ ce j;

    /*
     * WARNING - Possible parameter corruption
     * WARNING - void declaration
     */
    private /* synthetic */ ce(int n2) {
        void a2;
        void var2_-1;
        void var1_-1;
        ce a3;
        a3.v = a2;
    }

    static {
        m = new ce("DEFLATE_COMPRESSED", 1, 20);
        j = new ce("ZIP_64_FORMAT", 2, 45);
        s = new ce("AES_ENCRYPTED", 3, 51);
        ce[] ceArray = new ce[4];
        ceArray[0] = c;
        ceArray[1] = m;
        ceArray[2] = j;
        ceArray[3] = s;
        l = ceArray;
    }

    public static ce valueOf(String a2) {
        return Enum.valueOf(ce.class, a2);
    }

    public static ce[] values() {
        return (ce[])l.clone();
    }

    public int r() {
        ce a2;
        return a2.v;
    }
}

