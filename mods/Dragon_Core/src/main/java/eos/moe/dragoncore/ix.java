/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.annotations.SerializedName
 */
package eos.moe.dragoncore;

import com.google.gson.annotations.SerializedName;
import java.util.Arrays;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class ix {
    @SerializedName(value="post")
    private String[] k;
    @SerializedName(value="lerp_mode")
    private String ALLATORIxDEMO;

    public ix(String[] a2, String a3) {
        ix a4;
        a4.k = a2;
        a4.ALLATORIxDEMO = a3;
    }

    public String toString() {
        ix a2;
        return "Keyframe{post=" + Arrays.toString(a2.k) + ", lerp_mode='" + a2.ALLATORIxDEMO + '\'' + '}';
    }

    public String[] ALLATORIxDEMO() {
        ix a2;
        return a2.k;
    }

    public String ALLATORIxDEMO() {
        ix a2;
        return a2.ALLATORIxDEMO;
    }
}

