/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonArray
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonPrimitive
 */
package eos.moe.dragoncore;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import eos.moe.dragoncore.cq;
import eos.moe.dragoncore.gw;
import eos.moe.dragoncore.qz;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class ft {
    public ft() {
        ft a2;
    }

    public static qz ALLATORIxDEMO(String a2, JsonElement a3) throws cq {
        return ft.ALLATORIxDEMO(ft.ALLATORIxDEMO(a2, a3.getAsJsonObject()));
    }

    public static qz ALLATORIxDEMO(String a2, JsonElement a3, qz a4) throws cq {
        if (a3 == null) {
            return a4;
        }
        JsonElement a5 = a3.getAsJsonObject().get(a2);
        if (a5 != null) {
            return ft.ALLATORIxDEMO(a5);
        }
        return a4;
    }

    public static qz ALLATORIxDEMO(JsonElement a2) throws cq {
        if (a2 instanceof JsonPrimitive) {
            JsonPrimitive a3 = (JsonPrimitive)a2;
            return new qz(a3.getAsFloat(), a3.getAsFloat(), a3.getAsFloat());
        }
        if (a2 instanceof JsonArray) {
            JsonArray a4 = (JsonArray)a2;
            return new qz(a4.get(0).getAsFloat(), a4.get(1).getAsFloat(), a4.get(2).getAsFloat());
        }
        throw new cq("Json\u65e0\u6cd5\u88ab\u8f6c\u6362\u4e3a\u6570\u7ec4\u6570\u636e: " + a2);
    }

    public static gw ALLATORIxDEMO(String a2, JsonElement a3) throws cq {
        JsonElement a4 = ft.ALLATORIxDEMO(a2, a3.getAsJsonObject());
        if (a4 instanceof JsonPrimitive) {
            JsonPrimitive a5 = (JsonPrimitive)a4;
            return new gw(a5.getAsFloat(), a5.getAsFloat());
        }
        if (a4 instanceof JsonArray) {
            JsonArray a6 = (JsonArray)a4;
            return new gw(a6.get(0).getAsFloat(), a6.get(1).getAsFloat());
        }
        throw new cq("Json\u65e0\u6cd5\u88ab\u8f6c\u6362\u4e3a\u6570\u7ec4\u6570\u636e: " + a4);
    }

    public static gw ALLATORIxDEMO(String a2, JsonElement a3, gw a4) throws cq {
        if (a3 == null) {
            return a4;
        }
        JsonElement a5 = a3.getAsJsonObject().get(a2);
        JsonArray a6 = a5 == null ? null : (JsonArray)a5;
        return a6 != null ? new gw(a6.get(0).getAsFloat(), a6.get(1).getAsFloat()) : a4;
    }

    public static boolean ALLATORIxDEMO(String a2, JsonElement a3) throws cq {
        return ft.ALLATORIxDEMO(a2, a3.getAsJsonObject()).getAsBoolean();
    }

    public static boolean ALLATORIxDEMO(String a2, JsonElement a3, boolean a4) throws cq {
        if (a3 == null) {
            return a4;
        }
        JsonElement a5 = a3.getAsJsonObject().get(a2);
        return a5 != null ? a5.getAsBoolean() : a4;
    }

    public static float ALLATORIxDEMO(String a2, JsonElement a3) throws cq {
        return ft.ALLATORIxDEMO(a2, a3.getAsJsonObject()).getAsFloat();
    }

    public static float ALLATORIxDEMO(String a2, JsonElement a3, float a4) throws cq {
        if (a3 == null) {
            return a4;
        }
        JsonElement a5 = a3.getAsJsonObject().get(a2);
        return a5 != null ? a5.getAsFloat() : a4;
    }

    public static int ALLATORIxDEMO(String a2, JsonElement a3) throws cq {
        return ft.ALLATORIxDEMO(a2, a3.getAsJsonObject()).getAsInt();
    }

    public static int ALLATORIxDEMO(String a2, JsonElement a3, int a4) throws cq {
        if (a3 == null) {
            return a4;
        }
        JsonElement a5 = a3.getAsJsonObject().get(a2);
        return a5 != null ? a5.getAsInt() : a4;
    }

    public static String ALLATORIxDEMO(String a2, JsonElement a3) throws cq {
        return ft.ALLATORIxDEMO(a2, a3.getAsJsonObject()).getAsString();
    }

    public static String ALLATORIxDEMO(String a2, JsonElement a3, String a4) throws cq {
        if (a3 == null) {
            return a4;
        }
        JsonElement a5 = a3.getAsJsonObject().get(a2);
        return a5 != null ? a5.getAsString() : a4;
    }

    public static JsonElement ALLATORIxDEMO(String a2, JsonObject a3) throws cq {
        ft.ALLATORIxDEMO(a2, a3);
        return a3.get(a2);
    }

    private static /* synthetic */ void ALLATORIxDEMO(String a2, JsonObject a3) throws cq {
        if (!a3.has(a2)) {
            throw new cq("\u65e0\u6cd5\u5728 " + a3 + " \u5185\u627e\u5230\u8282\u70b9 " + a2);
        }
    }
}

