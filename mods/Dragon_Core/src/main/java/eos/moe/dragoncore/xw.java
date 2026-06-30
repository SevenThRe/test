/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.annotations.SerializedName
 */
package eos.moe.dragoncore;

import com.google.gson.annotations.SerializedName;
import eos.moe.dragoncore.ew;
import eos.moe.dragoncore.gw;
import eos.moe.dragoncore.qz;
import java.util.List;

public class xw {
    @SerializedName(value="a")
    private qz o;
    @SerializedName(value="b")
    private qz y;
    @SerializedName(value="c")
    private gw k;
    @SerializedName(value="d")
    private List<ew> ALLATORIxDEMO;

    public xw(qz a2, qz a3, gw a4) {
        xw a5;
        a5.o = a2;
        a5.y = a3;
        a5.k = a4;
    }

    public xw(qz a2, qz a3, List<ew> a4) {
        xw a5;
        a5.o = a2;
        a5.y = a3;
        a5.ALLATORIxDEMO = a4;
    }
}

