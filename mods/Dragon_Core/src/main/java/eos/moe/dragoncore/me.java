/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonArray
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 */
package eos.moe.dragoncore;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import eos.moe.dragoncore.acf;
import eos.moe.dragoncore.bax;
import eos.moe.dragoncore.to;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class me {
    public me() {
        me a2;
    }

    public static bax ALLATORIxDEMO(String a2, JsonElement a3) throws to {
        return me.ALLATORIxDEMO(me.ALLATORIxDEMO(a2, a3.getAsJsonObject()));
    }

    public static bax ALLATORIxDEMO(String a2, JsonElement a3, bax a4) throws to {
        if (a3 == null) {
            return a4;
        }
        JsonElement a5 = a3.getAsJsonObject().get(a2);
        if (a5 != null) {
            return me.ALLATORIxDEMO(a5);
        }
        return a4;
    }

    public static bax ALLATORIxDEMO(JsonElement a2) throws to {
        JsonArray a3 = me.ALLATORIxDEMO(a2);
        return new bax(a3.get(0).getAsFloat(), a3.get(1).getAsFloat(), a3.get(2).getAsFloat());
    }

    public static acf ALLATORIxDEMO(String a2, JsonElement a3) throws to {
        JsonArray a4 = me.ALLATORIxDEMO(me.ALLATORIxDEMO(a2, a3.getAsJsonObject()));
        return new acf(a4.get(0).getAsFloat(), a4.get(1).getAsFloat());
    }

    public static acf ALLATORIxDEMO(String a2, JsonElement a3, acf a4) throws to {
        if (a3 == null) {
            return a4;
        }
        JsonElement a5 = a3.getAsJsonObject().get(a2);
        JsonArray a6 = a5 == null ? null : (JsonArray)a5;
        return a6 != null ? new acf(a6.get(0).getAsFloat(), a6.get(1).getAsFloat()) : a4;
    }

    public static boolean ALLATORIxDEMO(String a2, JsonElement a3) throws to {
        return me.ALLATORIxDEMO(a2, a3.getAsJsonObject()).getAsBoolean();
    }

    public static boolean ALLATORIxDEMO(String a2, JsonElement a3, boolean a4) throws to {
        if (a3 == null) {
            return a4;
        }
        JsonElement a5 = a3.getAsJsonObject().get(a2);
        return a5 != null ? a5.getAsBoolean() : a4;
    }

    public static float ALLATORIxDEMO(String a2, JsonElement a3) throws to {
        return me.ALLATORIxDEMO(a2, a3.getAsJsonObject()).getAsFloat();
    }

    public static float ALLATORIxDEMO(String a2, JsonElement a3, float a4) throws to {
        if (a3 == null) {
            return a4;
        }
        JsonElement a5 = a3.getAsJsonObject().get(a2);
        return a5 != null ? a5.getAsFloat() : a4;
    }

    public static int ALLATORIxDEMO(String a2, JsonElement a3) throws to {
        return me.ALLATORIxDEMO(a2, a3.getAsJsonObject()).getAsInt();
    }

    public static int ALLATORIxDEMO(String a2, JsonElement a3, int a4) throws to {
        if (a3 == null) {
            return a4;
        }
        JsonElement a5 = a3.getAsJsonObject().get(a2);
        return a5 != null ? a5.getAsInt() : a4;
    }

    public static String ALLATORIxDEMO(String a2, JsonElement a3) throws to {
        return me.ALLATORIxDEMO(a2, a3.getAsJsonObject()).getAsString();
    }

    public static String ALLATORIxDEMO(String a2, JsonElement a3, String a4) throws to {
        if (a3 == null) {
            return a4;
        }
        JsonElement a5 = a3.getAsJsonObject().get(a2);
        return a5 != null ? a5.getAsString() : a4;
    }

    public static JsonElement ALLATORIxDEMO(String a2, JsonObject a3) throws to {
        me.ALLATORIxDEMO(a2, a3);
        return a3.get(a2);
    }

    private static /* synthetic */ void ALLATORIxDEMO(String a2, JsonObject a3) throws to {
        if (!a3.has(a2)) {
            throw new to("\u65e0\u6cd5\u5728 " + a3 + " \u5185\u627e\u5230\u8282\u70b9 " + a2);
        }
    }

    private static /* synthetic */ JsonArray ALLATORIxDEMO(JsonElement a2) {
        if (a2 instanceof JsonArray) {
            return a2.getAsJsonArray();
        }
        JsonArray a3 = new JsonArray();
        a3.add(a2);
        a3.add(a2);
        a3.add(a2);
        return a3;
    }
}

