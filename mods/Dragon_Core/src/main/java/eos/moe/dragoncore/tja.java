/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiChat
 *  net.minecraft.client.gui.GuiTextField
 *  net.minecraftforge.client.event.GuiScreenEvent$InitGuiEvent
 *  net.minecraftforge.client.event.GuiScreenEvent$KeyboardInputEvent$Post
 *  net.minecraftforge.fml.common.Mod$EventBusSubscriber
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.relauncher.ReflectionHelper
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.nw;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.GuiTextField;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.ReflectionHelper;

@Mod.EventBusSubscriber(modid="dragoncore")
public class tja {
    private static String ALLATORIxDEMO = "";

    public tja() {
        tja a2;
    }

    @SubscribeEvent
    public static void ALLATORIxDEMO(GuiScreenEvent.KeyboardInputEvent.Post a2) {
        GuiTextField a3;
        if (a2.getGui().getClass() == GuiChat.class && !(a3 = (GuiTextField)ReflectionHelper.getPrivateValue(GuiChat.class, (Object)((GuiChat)a2.getGui()), (String[])new String[]{"inputField", "inputField"})).getText().equals(ALLATORIxDEMO)) {
            nw.ALLATORIxDEMO(ALLATORIxDEMO, a3.getText());
            ALLATORIxDEMO = a3.getText();
        }
    }

    @SubscribeEvent
    public static void ALLATORIxDEMO(GuiScreenEvent.InitGuiEvent a2) {
        if (a2.getGui().getClass() == GuiChat.class) {
            ALLATORIxDEMO = "";
        }
    }
}

