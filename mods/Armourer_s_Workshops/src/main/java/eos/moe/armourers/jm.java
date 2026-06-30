/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.commons.io.Charsets
 */
package eos.moe.armourers;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import org.apache.commons.io.Charsets;

public class jm {
    private static /* synthetic */ int r(DataInputStream a2) throws IOException {
        return a2.readShort() & 0xFFFF;
    }

    public static String y(DataInputStream a2) throws IOException {
        return jm.r(a2, Charsets.US_ASCII);
    }

    public static void y(DataOutputStream a2, String a3) throws IOException {
        jm.r(a2, Charsets.US_ASCII, a3);
    }

    public static String r(DataInputStream a2, Charset a3) throws IOException {
        DataInputStream dataInputStream = a2;
        int n2 = jm.r(dataInputStream);
        byte[] byArray = new byte[n2];
        dataInputStream.read(byArray, 0, n2);
        return new String(byArray, a3);
    }

    public static void r(DataOutputStream a2, String a3) throws IOException {
        jm.r(a2, Charsets.UTF_8, a3);
    }

    private /* synthetic */ jm() {
        jm a2;
    }

    public static void r(DataOutputStream a2, Charset a3, String a42) throws IOException {
        byte[] byArray = a42.getBytes((Charset)a3);
        a3 = byArray;
        int a42 = byArray.length;
        DataOutputStream dataOutputStream = a2;
        jm.r(dataOutputStream, a42);
        dataOutputStream.write((byte[])a3);
    }

    public static String r(DataInputStream a2) throws IOException {
        return jm.r(a2, Charsets.UTF_8);
    }

    private static /* synthetic */ void r(DataOutputStream a2, int a3) throws IOException {
        if (a3 > 65535) {
            throw new IOException("String is over the max length allowed.");
        }
        a2.writeInt(a3);
    }
}

