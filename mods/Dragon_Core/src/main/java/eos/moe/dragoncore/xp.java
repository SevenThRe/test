/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.annotations.SerializedName
 */
package eos.moe.dragoncore;

import com.google.gson.annotations.SerializedName;
import eos.moe.dragoncore.as;
import eos.moe.dragoncore.gy;
import eos.moe.dragoncore.ma;
import eos.moe.dragoncore.wx;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class xp {
    @SerializedName(value="boxUV")
    public boolean v;
    @SerializedName(value="name")
    public String m;
    @SerializedName(value="width")
    public int c;
    @SerializedName(value="height")
    public int q;
    @SerializedName(value="cubes")
    public List<as> b = new ArrayList<as>();
    @SerializedName(value="elements")
    public List<ma> o = new ArrayList<ma>();
    @SerializedName(value="textures")
    public List<gy> y = new ArrayList<gy>();
    @SerializedName(value="animations")
    public wx k = new wx();
    @SerializedName(value="mcmetas")
    public Map<String, String> ALLATORIxDEMO = new LinkedHashMap<String, String>();

    public xp() {
        xp a2;
    }

    public String toString() {
        xp a2;
        return "BlockbenchModel{boxUV=" + a2.v + ", name='" + a2.m + '\'' + ", width=" + a2.c + ", height=" + a2.q + ", cubes=" + a2.b + ", elements=" + a2.o + ", textures=" + a2.y + ", animations=" + a2.k + '}';
    }
}

