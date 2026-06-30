/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.annotations.SerializedName
 */
package eos.moe.dragoncore;

import com.google.gson.annotations.SerializedName;
import eos.moe.dragoncore.ma;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class tp
implements ma {
    @SerializedName(value="name")
    public String b;
    @SerializedName(value="origin")
    public float[] o = new float[]{0.0f, 0.0f, 0.0f};
    @SerializedName(value="rotation")
    public float[] y = new float[]{0.0f, 0.0f, 0.0f};
    @SerializedName(value="uuid")
    public UUID k;
    @SerializedName(value="children")
    public List<ma> ALLATORIxDEMO = new ArrayList<ma>();

    public tp(String a2) {
        a3();
        tp a3;
        a3.b = a2;
    }

    public tp() {
        tp a2;
    }

    public String toString() {
        tp a2;
        return "BBOutliner{name='" + a2.b + '\'' + ", origin=" + Arrays.toString(a2.o) + ", rotation=" + Arrays.toString(a2.y) + ", uuid=" + a2.k + ", children=" + a2.ALLATORIxDEMO + '}';
    }
}

