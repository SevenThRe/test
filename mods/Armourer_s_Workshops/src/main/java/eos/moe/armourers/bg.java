/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.armourers;

import eos.moe.armourers.c;
import eos.moe.armourers.ga;
import eos.moe.armourers.kb;
import eos.moe.armourers.pb;
import eos.moe.armourers.ph;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class bg {
    public static long r(List<pb> a2) {
        long l2 = 0L;
        for (pb pb2 : a2) {
            if (pb2.r() != null && pb2.r().r() > 0L) {
                l2 += pb2.r().r();
                continue;
            }
            l2 += pb2.r();
        }
        return l2;
    }

    public static long r(kb a2) {
        if (a2.r()) {
            return a2.r().x();
        }
        return a2.r().r();
    }

    public static pb y(kb a2, String a3) throws ph {
        pb pb2 = bg.r(a2, a3);
        if (pb2 == null && (pb2 = bg.r(a2, a3 = a3.replaceAll("\\\\", "/"))) == null) {
            a3 = a3.replaceAll("/", "\\\\");
            pb2 = bg.r(a2, a3);
        }
        return pb2;
    }

    /*
     * Enabled aggressive block sorting
     */
    private static /* synthetic */ pb r(kb a2, String a3) throws ph {
        pb pb2;
        String string;
        if (a2 == null) {
            throw new ph(new StringBuilder().insert(0, "zip model is null, cannot determine file header with exact match for fileName: ").append(a3).toString());
        }
        if (!c.r(a3)) {
            throw new ph(new StringBuilder().insert(0, "file name is null, cannot determine file header with exact match for fileName: ").append(a3).toString());
        }
        if (a2.r() == null) {
            throw new ph(new StringBuilder().insert(0, "central directory is null, cannot determine file header with exact match for fileName: ").append(a3).toString());
        }
        if (a2.r().r() == null) {
            throw new ph(new StringBuilder().insert(0, "file Headers are null, cannot determine file header with exact match for fileName: ").append(a3).toString());
        }
        if (a2.r().r().size() == 0) {
            return null;
        }
        Iterator<pb> iterator = a2.r().r().iterator();
        block0: do {
            Iterator<pb> iterator2 = iterator;
            while (true) {
                if (!iterator2.hasNext()) {
                    return null;
                }
                pb2 = iterator.next();
                string = pb2.r();
                if (c.r(string)) continue block0;
                iterator2 = iterator;
            }
        } while (!a3.equalsIgnoreCase(string));
        return pb2;
    }

    public static List<pb> r(List<pb> a2, pb a4) {
        if (!a4.z()) {
            return Collections.emptyList();
        }
        return a2.stream().filter(a3 -> a3.r().startsWith(a4.r())).collect(Collectors.toList());
    }

    public bg() {
        bg a2;
    }

    public static String r(byte[] a2, boolean a32, Charset a4) {
        if (ga.d.equals(a4) && !a32) {
            try {
                return new String(a2, "Cp437");
            }
            catch (UnsupportedEncodingException a32) {
                return new String(a2);
            }
        }
        if (a4 != null) {
            return new String(a2, a4);
        }
        return new String(a2, ga.d);
    }
}

