/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.annotations.SerializedName
 */
package eos.moe.dragoncore;

import com.google.gson.annotations.SerializedName;
import eos.moe.dragoncore.ix;
import java.util.LinkedHashMap;
import java.util.Map;

public class mq {
    @SerializedName(value="rotation")
    private Map<Float, ix> y = new LinkedHashMap<Float, ix>();
    @SerializedName(value="scale")
    private Map<Float, ix> k = new LinkedHashMap<Float, ix>();
    @SerializedName(value="position")
    private Map<Float, ix> ALLATORIxDEMO = new LinkedHashMap<Float, ix>();

    public mq() {
        mq a2;
    }

    public void ALLATORIxDEMO(String a2, String[] a3, float a4, String a5) {
        ix a6 = new ix(a3, a5);
        switch (a2) {
            case "rotation": {
                mq a7;
                a7.y.put(Float.valueOf(a4), a6);
                break;
            }
            case "scale": {
                mq a7;
                a7.k.put(Float.valueOf(a4), a6);
                break;
            }
            case "position": {
                mq a7;
                a7.ALLATORIxDEMO.put(Float.valueOf(a4), a6);
            }
        }
    }

    public String toString() {
        mq a2;
        return "AnimationBone{rotation=" + a2.y + ", scale=" + a2.k + ", position=" + a2.ALLATORIxDEMO + '}';
    }

    public Map<Float, ix> f() {
        mq a2;
        return a2.y;
    }

    public Map<Float, ix> c() {
        mq a2;
        return a2.k;
    }

    public Map<Float, ix> ALLATORIxDEMO() {
        mq a2;
        return a2.ALLATORIxDEMO;
    }
}

