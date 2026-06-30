/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.annotations.SerializedName
 */
package eos.moe.dragoncore;

import com.google.gson.annotations.SerializedName;
import eos.moe.dragoncore.qz;
import eos.moe.dragoncore.xw;
import java.util.List;

public class vz {
    @SerializedName(value="cubes")
    private List<xw> v;
    @SerializedName(value="pivot")
    private qz m;
    @SerializedName(value="rotation")
    private qz c;
    @SerializedName(value="mirror")
    private boolean q;
    @SerializedName(value="neverrender")
    private boolean b;
    @SerializedName(value="inflate")
    private float o;
    @SerializedName(value="name")
    private String y;
    @SerializedName(value="parent")
    private String k;
    @SerializedName(value="i")
    public List<vz> ALLATORIxDEMO;

    public vz(List<xw> a2, qz a3, qz a4, boolean a5, boolean a6, float a7, String a8, String a9) {
        vz a10;
        a10.v = a2;
        a10.m = a3;
        a10.c = a4;
        a10.q = a5;
        a10.b = a6;
        a10.o = a7;
        a10.y = a8;
        a10.k = a9;
    }
}

