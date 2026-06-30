/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonElement
 *  com.google.gson.annotations.SerializedName
 */
package eos.moe.dragoncore;

import com.google.gson.JsonElement;
import com.google.gson.annotations.SerializedName;
import eos.moe.dragoncore.cq;
import eos.moe.dragoncore.ft;
import eos.moe.dragoncore.gw;

public class ew {
    @SerializedName(value="uv")
    private gw k;
    @SerializedName(value="uv_size")
    private gw ALLATORIxDEMO;

    public ew(JsonElement a2, String a3) throws cq {
        ew a4;
        JsonElement a5 = a2.getAsJsonObject().get(a3);
        a4.k = ft.ALLATORIxDEMO("uv", a5, new gw(0.0f, 0.0f));
        a4.ALLATORIxDEMO = ft.ALLATORIxDEMO("uv_size", a5, new gw(0.0f, 0.0f));
    }

    public gw c() {
        ew a2;
        return a2.k;
    }

    public gw ALLATORIxDEMO() {
        ew a2;
        return a2.ALLATORIxDEMO;
    }
}

