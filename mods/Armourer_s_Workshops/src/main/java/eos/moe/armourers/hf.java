/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.commons.io.Charsets
 *  org.apache.logging.log4j.Level
 */
package eos.moe.armourers;

import eos.moe.armourers.bm;
import eos.moe.armourers.jm;
import eos.moe.armourers.kf;
import eos.moe.armourers.ok;
import eos.moe.armourers.qf;
import eos.moe.armourers.vn;
import eos.moe.armourers.we;
import eos.moe.armourers.y;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import org.apache.commons.io.Charsets;
import org.apache.logging.log4j.Level;

public class hf {
    private /* synthetic */ hf() {
        hf a2;
    }

    public static kf r(DataInputStream a2, int a3) throws IOException, ok {
        int n2 = a3;
        if (a3 <= -1) {
            a3 = 13;
        }
        y y2 = null;
        qf qf2 = null;
        ArrayList<we> arrayList = null;
        if (a3 < 6) {
            y2 = vn.h.r(a2.readByte());
            if (y2 == null) {
                bm.r(Level.ERROR, "Skin part was null");
                throw new IOException("Skin part was null");
            }
        } else {
            String string = null;
            if ((a3 > 12 ? (string = jm.r(a2, Charsets.US_ASCII)) : (string = a2.readUTF())).equals("armourers:skirt.base")) {
                string = "armourers:legs.skirt";
            }
            if (string.equals("armourers:bow.base")) {
                string = "armourers:bow.frame1";
            }
            if (string.equals("armourers:arrow.base")) {
                string = "armourers:bow.arrow";
            }
            if ((y2 = vn.h.r(string)) == null) {
                bm.r(Level.ERROR, new StringBuilder().insert(0, "Skin part was null - reg name: ").append(string).append(" version: ").append(a3).toString());
                throw new IOException(new StringBuilder().insert(0, "Skin part was null - reg name: ").append(string).append(" version: ").append(a3).toString());
            }
        }
        qf2 = new qf();
        qf2.r(a2, a3, y2);
        arrayList = new ArrayList<we>();
        if (a3 > 8) {
            int n3;
            int n4 = a2.readInt();
            int n5 = n3 = 0;
            while (n5 < n4) {
                arrayList.add(new we(a2, a3));
                n5 = ++n3;
            }
        }
        return new kf(qf2, y2, arrayList, n2);
    }
}

