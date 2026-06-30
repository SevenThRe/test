/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.annotations.SerializedName
 */
package eos.moe.dragoncore;

import com.google.gson.annotations.SerializedName;
import eos.moe.dragoncore.mq;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class cu {
    @SerializedName(value="loop")
    private Boolean o = false;
    @SerializedName(value="override_previous_animation")
    private Boolean y = false;
    @SerializedName(value="animation_length")
    private Float k = Float.valueOf(0.0f);
    @SerializedName(value="bones")
    private Map<String, mq> ALLATORIxDEMO = new ConcurrentHashMap<String, mq>();

    public cu() {
        cu a2;
    }

    public void ALLATORIxDEMO() {
        cu a2;
        ConcurrentHashMap<String, mq> a3 = new ConcurrentHashMap<String, mq>();
        for (String a4 : a2.ALLATORIxDEMO.keySet()) {
            a3.put(a4.toLowerCase(), a2.ALLATORIxDEMO.get(a4));
        }
        a2.ALLATORIxDEMO = a3;
        for (mq mq2 : a2.ALLATORIxDEMO.values()) {
        }
    }

    public void c(boolean a2) {
        a.o = a2;
    }

    public void ALLATORIxDEMO(boolean a2) {
        a.y = a2;
    }

    public void ALLATORIxDEMO(float a2) {
        a.k = Float.valueOf(a2);
    }

    public Boolean c() {
        cu a2;
        return a2.y;
    }

    public Boolean ALLATORIxDEMO() {
        cu a2;
        return a2.o;
    }

    public float ALLATORIxDEMO() {
        cu a2;
        return a2.k.floatValue();
    }

    public Map<String, mq> ALLATORIxDEMO() {
        cu a2;
        return a2.ALLATORIxDEMO;
    }

    public String toString() {
        cu a2;
        return "Animation{loop=" + a2.o + ", override_previous_animation=" + a2.y + ", animation_length=" + a2.k + ", bones=" + a2.ALLATORIxDEMO + '}';
    }
}

