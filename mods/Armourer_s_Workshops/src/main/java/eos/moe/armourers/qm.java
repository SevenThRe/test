/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.commons.io.Charsets
 */
package eos.moe.armourers;

import eos.moe.armourers.hf;
import eos.moe.armourers.in;
import eos.moe.armourers.jm;
import eos.moe.armourers.kf;
import eos.moe.armourers.md;
import eos.moe.armourers.nl;
import eos.moe.armourers.ok;
import eos.moe.armourers.r;
import eos.moe.armourers.vn;
import eos.moe.armourers.yl;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import org.apache.commons.io.Charsets;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class qm {
    private static String b;
    private static String g;
    private static String z;
    private static String t;
    private static String w;
    private static String r;
    private static String l;
    private static String c;
    private static String v;
    private static String s;
    private static int m;
    private static String j;

    static {
        m = -1;
        z = "AW-SKIN-START";
        b = "PROPS-START";
        s = "PROPS-END";
        c = "TYPE-START";
        g = "TYPE-END";
        l = "PAINT-START";
        v = "PAINT-END";
        w = "PART-START";
        j = "PART-END";
        r = "AW-SKIN-END";
        t = "tags";
    }

    public static r r(DataInputStream a2) throws IOException, md {
        CharSequence charSequence;
        int n2;
        CharSequence charSequence2;
        Object object;
        IOException iOException;
        boolean bl;
        int n3;
        block18: {
            Object object2;
            n3 = a2.readInt();
            if (n3 > m) {
                throw new md();
            }
            if (n3 > 12) {
                String string;
                object2 = jm.r(a2, Charsets.US_ASCII);
                if (!((String)object2).equals(z)) {
                    // empty if block
                }
                if (!(string = jm.r(a2, Charsets.US_ASCII)).equals(b)) {
                    // empty if block
                }
            }
            object2 = new in();
            bl = true;
            iOException = null;
            if (n3 < 12) {
                DataInputStream dataInputStream = a2;
                object = dataInputStream.readUTF();
                charSequence2 = dataInputStream.readUTF();
                String string = "";
                string = n3 >= 4 ? a2.readUTF() : "";
                in.fa.r((in)object2, (String)object);
                in.l.r((in)object2, (String)charSequence2);
                if (string != null && !string.equalsIgnoreCase("")) {
                    ((in)object2).r(t, (Object)string);
                }
            } else {
                try {
                    ((in)object2).r(a2, n3);
                    n2 = n3;
                    break block18;
                }
                catch (IOException iOException2) {
                    iOException = iOException2;
                    bl = false;
                }
            }
            n2 = n3;
        }
        if (n2 > 12) {
            object = jm.r(a2, Charsets.US_ASCII);
            if (!((String)object).equals(s)) {
                // empty if block
            }
            if (!((String)(charSequence2 = jm.r(a2, Charsets.US_ASCII))).equals(c)) {
                // empty if block
            }
        }
        object = null;
        if (n3 < 5) {
            if (bl) {
                object = vn.h.r(a2.readByte() - 1);
                return object;
            }
            throw iOException;
        }
        if (bl) {
            charSequence2 = a2.readUTF();
            if (((String)charSequence2).equals(vn.f.y())) {
                charSequence2 = vn.e.y();
            }
            object = vn.h.r((String)charSequence2);
            return object;
        }
        charSequence2 = new StringBuilder();
        do {
            charSequence = charSequence2;
            byte[] byArray = new byte[1];
            byArray[0] = a2.readByte();
            ((StringBuilder)charSequence).append(new String(byArray, "UTF-8"));
        } while (!((StringBuilder)charSequence).toString().endsWith("armourers:"));
        charSequence2 = new StringBuilder();
        ((StringBuilder)charSequence2).append("armourers:");
        while (vn.h.r(((StringBuilder)charSequence2).toString()) == null) {
            byte[] byArray = new byte[1];
            byArray[0] = a2.readByte();
            ((StringBuilder)charSequence2).append(new String(byArray, "UTF-8"));
        }
        object = vn.h.r(((StringBuilder)charSequence2).toString());
        return object;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static yl r(DataInputStream a2) throws IOException, md, ok {
        String string;
        int n2;
        String string2;
        boolean bl;
        int n3;
        int n4;
        Object object;
        Object object2;
        IOException iOException;
        int n5;
        Object object3;
        int n6;
        int n7;
        block41: {
            n7 = a2.readInt();
            n6 = n7;
            if (n6 <= -1) {
                n6 = 13;
            }
            if (n6 > 13) {
                throw new md();
            }
            if (n6 > 12) {
                String string3;
                object3 = jm.r(a2, Charsets.US_ASCII);
                if (!((String)object3).equals(z)) {
                    // empty if block
                }
                if (!(string3 = jm.r(a2, Charsets.US_ASCII)).equals(b)) {
                    // empty if block
                }
            }
            object3 = new in();
            n5 = 1;
            iOException = null;
            if (n6 < 12) {
                DataInputStream dataInputStream = a2;
                object2 = dataInputStream.readUTF();
                object = dataInputStream.readUTF();
                String string4 = "";
                string4 = n6 >= 4 ? a2.readUTF() : "";
                in.fa.r((in)object3, (String)object2);
                in.l.r((in)object3, (String)object);
                if (string4 != null && !string4.equalsIgnoreCase("")) {
                    ((in)object3).r(t, (Object)string4);
                }
            } else {
                try {
                    ((in)object3).r(a2, n6);
                    n4 = n6;
                    break block41;
                }
                catch (IOException iOException2) {
                    iOException = iOException2;
                    n5 = 0;
                }
            }
            n4 = n6;
        }
        if (n4 > 12) {
            object2 = jm.r(a2, Charsets.US_ASCII);
            if (!((String)object2).equals(s)) {
                // empty if block
            }
            if (!((String)(object = jm.r(a2, Charsets.US_ASCII))).equals(c)) {
                // empty if block
            }
        }
        object2 = null;
        if (n6 < 5) {
            if (n5 == 0) throw iOException;
            object2 = vn.h.r(a2.readByte() - 1);
            n3 = n6;
        } else if (n5 != 0) {
            object = a2.readUTF();
            if (((String)object).equals(vn.f.y())) {
                object = vn.e.y();
            }
            object2 = vn.h.r((String)object);
            n3 = n6;
        } else {
            CharSequence charSequence;
            object = new StringBuilder();
            do {
                charSequence = object;
                byte[] byArray = new byte[1];
                byArray[0] = a2.readByte();
                ((StringBuilder)charSequence).append(new String(byArray, "UTF-8"));
            } while (!((StringBuilder)charSequence).toString().endsWith("armourers:"));
            object = new StringBuilder();
            ((StringBuilder)object).append("armourers:");
            while (vn.h.r(((StringBuilder)object).toString()) == null) {
                byte[] byArray = new byte[1];
                byArray[0] = a2.readByte();
                ((StringBuilder)object).append(new String(byArray, "UTF-8"));
            }
            object2 = vn.h.r(((StringBuilder)object).toString());
            n3 = n6;
        }
        if (n3 <= 12 || !((String)(object = jm.r(a2, Charsets.US_ASCII))).equals(g)) {
            // empty if block
        }
        if (object2 == null) {
            throw new ok();
        }
        if (n6 <= 12 || !((String)(object = jm.r(a2, Charsets.US_ASCII))).equals(l)) {
            // empty if block
        }
        object = null;
        if (n6 > 7 && (bl = a2.readBoolean())) {
            object = new int[nl.j];
            int n8 = n5 = 0;
            while (n8 < nl.j) {
                object[n5++] = a2.readInt();
                n8 = n5;
            }
        }
        if (n6 <= 12 || !(string2 = jm.r(a2, Charsets.US_ASCII)).equals(v)) {
            // empty if block
        }
        int n9 = a2.readByte();
        ArrayList<kf> arrayList = new ArrayList<kf>();
        int n10 = n2 = 0;
        while (n10 < n9) {
            Object object4;
            if (n6 <= 12 || !((String)(object4 = jm.r(a2, Charsets.US_ASCII))).equals(w)) {
                // empty if block
            }
            object4 = hf.r(a2, n7);
            if (n6 <= 12 || !jm.r(a2, Charsets.US_ASCII).equals(j)) {
                // empty if block
            }
            arrayList.add((kf)object4);
            n10 = ++n2;
        }
        if (n6 <= 12 || !(string = jm.r(a2, Charsets.US_ASCII)).equals(r)) {
            // empty if block
        }
        if (in.na.r((in)object3).booleanValue()) {
            if (object2 == vn.l) {
                in.t.r((in)object3, true);
            }
            if (object2 == vn.a) {
                in.b.r((in)object3, true);
                in.f.r((in)object3, true);
                in.n.r((in)object3, true);
            }
            if (object2 == vn.e) {
                in.r.r((in)object3, true);
                in.o.r((in)object3, true);
            }
            if (object2 == vn.w) {
                in.r.r((in)object3, true);
                in.o.r((in)object3, true);
            }
            if (object2 == vn.u) {
                // empty if block
            }
            in.na.r((in)object3);
        }
        if (!in.ca.r((in)object3).booleanValue()) return new yl((in)object3, (r)object2, (int[])object, arrayList);
        if (object2 == vn.l) {
            in.w.r((in)object3, true);
        }
        if (object2 == vn.a) {
            in.ra.r((in)object3, true);
            in.c.r((in)object3, true);
            in.d.r((in)object3, true);
        }
        if (object2 == vn.e) {
            in.x.r((in)object3, true);
            in.ua.r((in)object3, true);
        }
        if (object2 == vn.w) {
            in.x.r((in)object3, true);
            in.ua.r((in)object3, true);
        }
        if (object2 == vn.u) {
            // empty if block
        }
        in.ca.r((in)object3);
        return new yl((in)object3, (r)object2, (int[])object, arrayList);
    }

    private /* synthetic */ qm() {
        qm a2;
    }
}

