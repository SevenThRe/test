/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.annotations.SerializedName
 */
package eos.moe.dragoncore;

import com.google.gson.annotations.SerializedName;
import eos.moe.dragoncore.ma;
import eos.moe.dragoncore.zv;
import java.util.Arrays;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class as
implements ma {
    @SerializedName(value="name")
    public String m;
    @SerializedName(value="from")
    public float[] c;
    @SerializedName(value="to")
    public float[] q;
    @SerializedName(value="rotation")
    public float[] b = new float[]{0.0f, 0.0f, 0.0f};
    @SerializedName(value="origin")
    public float[] o = new float[]{0.0f, 0.0f, 0.0f};
    @SerializedName(value="inflate")
    public float y = 0.0f;
    @SerializedName(value="faces")
    public Map<String, zv> k = new ConcurrentHashMap<String, zv>();
    @SerializedName(value="uuid")
    public UUID ALLATORIxDEMO;

    public as() {
        as a2;
    }

    public String toString() {
        as a2;
        return "BBCube{name='" + a2.m + '\'' + ", from=" + Arrays.toString(a2.c) + ", to=" + Arrays.toString(a2.q) + ", rotation=" + Arrays.toString(a2.b) + ", origin=" + Arrays.toString(a2.o) + ", inflate=" + a2.y + ", faces=" + a2.k + ", uuid=" + a2.ALLATORIxDEMO + '}';
    }
}

