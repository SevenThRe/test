/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.ResourceLocation
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.ca;
import eos.moe.dragoncore.gx;
import eos.moe.dragoncore.jv;
import eos.moe.dragoncore.pw;
import java.nio.charset.StandardCharsets;
import java.util.List;
import net.minecraft.util.ResourceLocation;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class ms {
    private static byte[] k = "{\n  \"format_version\": \"1.12.0\",\n  \"minecraft:geometry\": [\n    {\n      \"description\": {\n        \"identifier\": \"geometry.error\",\n        \"texture_width\": 64,\n        \"texture_height\": 64,\n        \"visible_bounds_width\": 2,\n        \"visible_bounds_height\": 2,\n        \"visible_bounds_offset\": [\n          0,\n          0,\n          0\n        ]\n      },\n      \"bones\": [\n        {\n          \"name\": \"bone\",\n          \"pivot\": [\n            0,\n            0,\n            0\n          ],\n          \"cubes\": [\n            {\n              \"origin\": [\n                -8,\n                0,\n                -8\n              ],\n              \"size\": [\n                16,\n                16,\n                16\n              ],\n              \"uv\": [\n                0,\n                0\n              ]\n            }\n          ]\n        }\n      ]\n    }\n  ]\n}".getBytes(StandardCharsets.UTF_8);
    public static List<jv> ALLATORIxDEMO = new gx().c(k);

    public ms() {
        ms a2;
    }

    public static List<jv> ALLATORIxDEMO(ResourceLocation a2) {
        try {
            return new gx().ALLATORIxDEMO(a2);
        }
        catch (Throwable a3) {
            ca.l.ALLATORIxDEMO("\u65e0\u6cd5\u52a0\u8f7d\u9519\u8bef\u6a21\u578b " + a2.toString(), a3);
            ca.l.z("\u65e0\u6cd5\u52a0\u8f7d\u9519\u8bef\u6a21\u578b " + a2.toString() + " -> " + a3.getMessage());
            return ALLATORIxDEMO;
        }
    }

    public static List<jv> ALLATORIxDEMO(String a2, byte[] a3) {
        try {
            return new gx().ALLATORIxDEMO(a3);
        }
        catch (Throwable a4) {
            ca.l.ALLATORIxDEMO("\u65e0\u6cd5\u52a0\u8f7d\u9519\u8bef\u6a21\u578b " + a2.toString(), a4);
            ca.l.z("\u65e0\u6cd5\u52a0\u8f7d\u9519\u8bef\u6a21\u578b " + a2.toString() + " -> " + a4.getMessage());
            return ALLATORIxDEMO;
        }
    }

    public static jv ALLATORIxDEMO(ResourceLocation a2) {
        List<jv> a3 = ms.ALLATORIxDEMO(a2);
        if (a3.size() != 1) {
            ca.l.z("\u65e0\u6cd5\u52a0\u8f7d\u6a21\u578b" + a2.toString() + "\uff0c\u8bf7\u4fdd\u8bc1json\u5185\u53ea\u6709\u4e00\u4e2a\u6a21\u578b");
            return ALLATORIxDEMO.get(0);
        }
        return a3.get(0);
    }

    public static jv ALLATORIxDEMO(String a2, byte[] a3) {
        List<jv> a4 = ms.ALLATORIxDEMO(a2, a3);
        if (a4.size() != 1) {
            ca.l.z("\u65e0\u6cd5\u52a0\u8f7d\u6a21\u578b" + a2.toString() + "\uff0c\u8bf7\u4fdd\u8bc1json\u5185\u53ea\u6709\u4e00\u4e2a\u6a21\u578b");
            return ALLATORIxDEMO.get(0);
        }
        return a4.get(0);
    }

    public static pw ALLATORIxDEMO(ResourceLocation a2) {
        return new pw(ms.ALLATORIxDEMO(a2));
    }

    public static pw ALLATORIxDEMO() {
        return new pw(ALLATORIxDEMO.get(0));
    }

    public static jv ALLATORIxDEMO() {
        return ALLATORIxDEMO.get(0);
    }
}

