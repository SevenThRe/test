/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.armourers;

public final class wm
extends Enum<wm> {
    public static final /* enum */ wm e;
    public static final /* enum */ wm b;
    public static final /* enum */ wm g;
    public static final /* enum */ wm z;
    public static final /* enum */ wm t;
    public static final /* enum */ wm w;
    public static final /* enum */ wm r;
    public static final /* enum */ wm l;
    public static final /* enum */ wm c;
    public static final /* enum */ wm v;
    private static final /* synthetic */ wm[] s;
    public static final /* enum */ wm m;
    private long j;

    static {
        c = new wm("LOCAL_FILE_HEADER", 0, 67324752L);
        w = new wm("EXTRA_DATA_RECORD", 1, 134695760L);
        z = new wm("CENTRAL_DIRECTORY", 2, 33639248L);
        m = new wm("END_OF_CENTRAL_DIRECTORY", 3, 101010256L);
        e = new wm("DIGITAL_SIGNATURE", 4, 84233040L);
        g = new wm("ARCEXTDATREC", 5, 134630224L);
        l = new wm("SPLIT_ZIP", 6, 134695760L);
        r = new wm("ZIP64_END_CENTRAL_DIRECTORY_LOCATOR", 7, 117853008L);
        v = new wm("ZIP64_END_CENTRAL_DIRECTORY_RECORD", 8, 101075792L);
        t = new wm("ZIP64_EXTRA_FIELD_SIGNATURE", 9, 1L);
        b = new wm("AES_EXTRA_DATA_RECORD", 10, 39169L);
        wm[] wmArray = new wm[11];
        wmArray[0] = c;
        wmArray[1] = w;
        wmArray[2] = z;
        wmArray[3] = m;
        wmArray[4] = e;
        wmArray[5] = g;
        wmArray[6] = l;
        wmArray[7] = r;
        wmArray[8] = v;
        wmArray[9] = t;
        wmArray[10] = b;
        s = wmArray;
    }

    public static wm valueOf(String a2) {
        return Enum.valueOf(wm.class, a2);
    }

    /*
     * WARNING - Possible parameter corruption
     * WARNING - void declaration
     */
    private /* synthetic */ wm(long l2) {
        void a2;
        void var2_-1;
        void var1_-1;
        wm a3;
        a3.j = a2;
    }

    public static wm[] values() {
        return (wm[])s.clone();
    }

    public long r() {
        wm a2;
        return a2.j;
    }
}

