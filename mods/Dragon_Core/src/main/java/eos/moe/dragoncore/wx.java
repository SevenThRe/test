/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.annotations.SerializedName
 */
package eos.moe.dragoncore;

import com.google.gson.annotations.SerializedName;
import eos.moe.dragoncore.cu;
import java.util.LinkedHashMap;
import java.util.Map;

public class wx {
    @SerializedName(value="animations")
    private Map<String, cu> ALLATORIxDEMO = new LinkedHashMap<String, cu>();

    public wx() {
        wx a2;
    }

    public void ALLATORIxDEMO() {
        wx a2;
        for (cu a3 : a2.ALLATORIxDEMO.values()) {
            a3.ALLATORIxDEMO();
        }
    }

    public Map<String, cu> ALLATORIxDEMO() {
        wx a2;
        return a2.ALLATORIxDEMO;
    }

    public String toString() {
        wx a2;
        return "AnimationList{animations=" + a2.ALLATORIxDEMO + '}';
    }
}

