/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.armourers;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class we {
    public byte v;
    public byte s;
    public byte m;
    public byte j;

    public we(DataInputStream a2, int a3) throws IOException {
        we a4;
        we we2 = a4;
        we2.r(a2, a3);
    }

    private /* synthetic */ void r(DataInputStream a2, int a3) throws IOException {
        we a4;
        we we2 = a4;
        DataInputStream dataInputStream = a2;
        a4.m = a2.readByte();
        a4.j = dataInputStream.readByte();
        we2.v = dataInputStream.readByte();
        we2.s = a2.readByte();
    }

    public we(byte a2, byte a3, byte a4, byte a5) {
        we a6;
        we we2 = a6;
        we we3 = a6;
        we3.m = a2;
        we3.j = a3;
        we2.v = a4;
        we2.s = a5;
    }

    public void r(DataOutputStream a2) throws IOException {
        we a3;
        DataOutputStream dataOutputStream = a2;
        we we2 = a3;
        a2.writeByte(a3.m);
        a2.writeByte(we2.j);
        dataOutputStream.writeByte(we2.v);
        dataOutputStream.writeByte(a3.s);
    }

    public String toString() {
        we a2;
        return new StringBuilder().insert(0, "CubeMarkerData [x=").append(a2.m).append(", y=").append(a2.j).append(", z=").append(a2.v).append(", meta=").append(a2.s).append("]").toString();
    }
}

