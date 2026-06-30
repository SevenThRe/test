/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.annotations.SerializedName
 */
package eos.moe.dragoncore;

import com.google.gson.annotations.SerializedName;
import java.awt.image.BufferedImage;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class gy {
    @SerializedName(value="name")
    public String q;
    @SerializedName(value="folder")
    public String b;
    @SerializedName(value="namespace")
    public String o;
    @SerializedName(value="uuid")
    public String y;
    @SerializedName(value="texture")
    public BufferedImage k;
    @SerializedName(value="id")
    public String ALLATORIxDEMO;

    public gy() {
        gy a2;
    }

    public String toString() {
        gy a2;
        return "BBTexture{name='" + a2.q + '\'' + ", folder='" + a2.b + '\'' + ", namespace='" + a2.o + '\'' + ", uuid='" + a2.y + '\'' + ", texture=" + a2.k + '}';
    }

    public String f() {
        gy a2;
        return a2.q;
    }

    public BufferedImage ALLATORIxDEMO() {
        gy a2;
        return a2.k;
    }

    public String c() {
        gy a2;
        return a2.ALLATORIxDEMO;
    }

    public String ALLATORIxDEMO() {
        gy a2;
        return a2.y;
    }
}

