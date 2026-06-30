/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonElement
 */
package eos.moe.dragoncore;

import com.google.gson.JsonElement;
import eos.moe.dragoncore.acf;
import eos.moe.dragoncore.me;
import eos.moe.dragoncore.to;
import java.io.Serializable;

public class ag
implements Serializable {
    private static final long serialVersionUID = 4229598170213943182L;
    private acf a;
    private acf b;
    private String texture;

    public ag(JsonElement a2, String a3) throws to {
        ag a4;
        JsonElement a5 = a2.getAsJsonObject().get(a3);
        a4.a = me.ALLATORIxDEMO("uv", a5, new acf(0.0f, 0.0f));
        a4.b = me.ALLATORIxDEMO("uv_size", a5, new acf(0.0f, 0.0f));
        if (a2.getAsJsonObject().has("texture")) {
            a4.texture = a2.getAsJsonObject().get("texture").getAsString();
        }
    }

    public acf a() {
        ag a2;
        return a2.a;
    }

    public acf b() {
        ag a2;
        return a2.b;
    }

    public String getTexture() {
        ag a2;
        return a2.texture;
    }
}

