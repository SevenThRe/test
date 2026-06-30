/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.armourers;

import eos.moe.armourers.ok;
import eos.moe.armourers.qf;
import eos.moe.armourers.y;
import java.io.DataInputStream;
import java.io.IOException;

public class wg {
    /*
     * Unable to fully structure code
     */
    public static void y(qf a, int a, DataInputStream a, int a, y a) throws IOException, ok {
        v0 = a;
        a = v0.readByte();
        var5_9 = v0.readByte();
        var6_10 = v0.readByte();
        var7_11 = v0.readInt();
        var8_12 = v0.readByte();
        if (a >= 2) ** GOTO lbl31
        a = a.y();
        if (a.equals("armourers:sword.base")) {
            var5_9 = (byte)(var5_9 - 1);
            v1 = a;
        } else if (a.equals("armourers:legs.skirt")) {
            var5_9 = (byte)(var5_9 - 1);
            v1 = a;
        } else if (a.equals("armourers:legs.leftLeg")) {
            var5_9 = (byte)(var5_9 - 1);
            v1 = a;
        } else if (a.equals("armourers:legs.rightLeg")) {
            var5_9 = (byte)(var5_9 - 1);
            v1 = a;
        } else if (a.equals("armourers:feet.leftFoot")) {
            var5_9 = (byte)(var5_9 - 1);
            v1 = a;
        } else {
            if (a.equals("armourers:feet.rightFoot")) {
                var5_9 = (byte)(var5_9 - 1);
            }
lbl31:
            // 4 sources

            v1 = a;
        }
        v1.r(a, var8_12);
        a.r(a, a, (byte)var5_9, var6_10);
        a = (byte)(var7_11 >> 16 & 255);
        a = (byte)(var7_11 >> 8 & 255);
        a = (byte)(var7_11 & 255);
        v2 = var5_9 = 0;
        while (v2 < 6) {
            a.r(a, var5_9++, a, a, a);
            v2 = var5_9;
        }
    }

    public wg() {
        wg a2;
    }

    public static void r(qf a2, int a3, DataInputStream a4, int a5, y a62) throws IOException, ok {
        if (a5 < 3) {
            wg.y(a2, a3, a4, a5, a62);
            return;
        }
        DataInputStream dataInputStream = a4;
        byte a62 = dataInputStream.readByte();
        byte by = dataInputStream.readByte();
        byte by2 = dataInputStream.readByte();
        byte by3 = dataInputStream.readByte();
        byte[] byArray = new byte[6];
        byte[] byArray2 = new byte[6];
        byte[] byArray3 = new byte[6];
        if (a5 < 7) {
            int n2;
            a5 = a4.readInt();
            int n3 = n2 = 0;
            while (n3 < 6) {
                int n4 = n2;
                byArray[n2] = (byte)(a5 >> 16 & 0xFF);
                byArray2[n4] = (byte)(a5 >> 8 & 0xFF);
                byArray3[n4] = (byte)(a5 & 0xFF);
                n3 = ++n2;
            }
        } else {
            int n5 = a5 = 0;
            while (n5 < 6) {
                int n6 = a5;
                byArray[a5] = a4.readByte();
                byArray2[n6] = a4.readByte();
                byArray3[n6] = a4.readByte();
                n5 = ++a5;
            }
        }
        qf qf2 = a2;
        int n7 = a3;
        qf2.r(n7, a62);
        qf2.r(n7, by, by2, by3);
        int n8 = a5 = 0;
        while (n8 < 6) {
            int n9 = a5;
            a2.r(a3, n9, byArray[a5], byArray2[n9], byArray3[a5++]);
            n8 = a5;
        }
    }
}

