/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.settings.KeyBinding
 *  net.minecraft.util.text.ITextComponent
 *  net.minecraft.util.text.TextComponentString
 *  net.minecraftforge.client.event.RenderPlayerEvent$Pre
 *  net.minecraftforge.fml.client.registry.ClientRegistry
 *  net.minecraftforge.fml.common.eventhandler.EventPriority
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.gameevent.InputEvent$KeyInputEvent
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.hka;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;

public class dba {
    private static KeyBinding y;
    private static KeyBinding k;
    private static boolean ALLATORIxDEMO;

    public dba() {
        dba a2;
    }

    public static void c() {
        y = new KeyBinding("\u9690\u85cf\u5b9e\u4f53\u6a21\u578b", 211, "DragonCore");
        ClientRegistry.registerKeyBinding((KeyBinding)y);
    }

    public static void ALLATORIxDEMO() {
        k = new KeyBinding("\u9690\u85cf\u5176\u4ed6\u73a9\u5bb6", 199, "DragonCore");
        ClientRegistry.registerKeyBinding((KeyBinding)k);
    }

    @SubscribeEvent
    public void ALLATORIxDEMO(InputEvent.KeyInputEvent a2) {
        if (y != null && y.func_151468_f()) {
            hka.c = !hka.c;
            Minecraft.func_71410_x().field_71439_g.func_145747_a((ITextComponent)new TextComponentString("\u00a7a\u5df2" + (hka.c ? "\u5173\u95ed" : "\u5f00\u542f") + "\u663e\u793a\u5b9e\u4f53\u6a21\u578b"));
        }
        if (k != null && k.func_151468_f()) {
            ALLATORIxDEMO = !ALLATORIxDEMO;
            Minecraft.func_71410_x().field_71439_g.func_145747_a((ITextComponent)new TextComponentString("\u00a7a\u5df2" + (ALLATORIxDEMO ? "\u5173\u95ed" : "\u5f00\u542f") + "\u663e\u793a\u5176\u4ed6\u73a9\u5bb6(\u4ee5\u53ca\u90e8\u5206NPC)"));
        }
    }

    @SubscribeEvent(priority=EventPriority.HIGHEST)
    public void ALLATORIxDEMO(RenderPlayerEvent.Pre a2) {
        if (!ALLATORIxDEMO) {
            return;
        }
        if (a2.getEntity() == Minecraft.func_71410_x().field_71439_g) {
            return;
        }
        if (a2.getEntity().func_145748_c_().func_150260_c().contains("\u00a7")) {
            return;
        }
        a2.setCanceled(true);
    }

    static {
        ALLATORIxDEMO = false;
    }
}

