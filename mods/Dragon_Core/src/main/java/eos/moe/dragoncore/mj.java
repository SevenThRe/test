/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.GuiMainMenu
 *  net.minecraft.client.gui.GuiScreen
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.i;
import eos.moe.dragoncore.nw;
import java.util.Collections;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiScreen;

public class mj {
    public mj() {
        mj a2;
    }

    @i(f={"\u6267\u884c\u6309\u952e\u6307\u4ee4", "\u53d1\u9001\u6309\u952e", "Key_Send"})
    public static void ALLATORIxDEMO(String a2) {
        nw.ALLATORIxDEMO(a2, Collections.emptySet());
    }

    @i(f={"\u53d1\u5305", "Packet_Send"})
    public static void ALLATORIxDEMO(String a2, String ... a3) {
        nw.ALLATORIxDEMO(a2, a3);
    }

    @i(f={"\u590d\u6d3b", "Packet_Respawn"})
    public static void c() {
        Minecraft a2 = Minecraft.func_71410_x();
        if (a2.field_71439_g != null) {
            a2.field_71439_g.func_71004_bE();
        }
        a2.func_147108_a(null);
    }

    @i(f={"\u65ad\u5f00\u8fde\u63a5", "Packet_Disconnecting"})
    public static void ALLATORIxDEMO() {
        Minecraft a2 = Minecraft.func_71410_x();
        if (a2.field_71441_e != null) {
            a2.field_71441_e.func_72882_A();
        }
        a2.func_71403_a(null);
        a2.func_147108_a((GuiScreen)new GuiMainMenu());
    }
}

