/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.util.text.ITextComponent
 *  net.minecraft.util.text.TextComponentString
 *  net.minecraftforge.fml.common.Mod$EventBusSubscriber
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.gameevent.InputEvent$KeyInputEvent
 *  org.lwjgl.input.Keyboard
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.ca;
import eos.moe.dragoncore.dj;
import eos.moe.dragoncore.vq;
import java.io.IOException;
import net.minecraft.client.Minecraft;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import org.lwjgl.input.Keyboard;

@Mod.EventBusSubscriber(modid="dragoncore")
public class qia {
    private static long ALLATORIxDEMO;

    public qia() {
        qia a2;
    }

    @SubscribeEvent
    public static void ALLATORIxDEMO(InputEvent.KeyInputEvent a2) throws IOException {
        if (Keyboard.isKeyDown((int)23) && Keyboard.isKeyDown((int)25)) {
            if (System.currentTimeMillis() - ALLATORIxDEMO < 100L) {
                return;
            }
            ALLATORIxDEMO = System.currentTimeMillis();
            Minecraft.getMinecraft().player.sendMessage((ITextComponent)new TextComponentString("\u00a76\u5df2\u4e3a\u4f60\u8f93\u51fa\u624b\u4e2d\u7269\u54c1\u7684NBT\u603b\u548c,\u5176\u4e2d\u6fcf(se)\u4e3a\u989c\u8272\u7b26\u53f7"));
            Minecraft.getMinecraft().player.sendMessage((ITextComponent)new TextComponentString(dj.ALLATORIxDEMO(Minecraft.getMinecraft().player.inventory.getCurrentItem(), false, false).replace("\u00a7", "\u6fcf")));
        }
        if (Keyboard.isKeyDown((int)24) && Keyboard.isKeyDown((int)25) && Keyboard.getEventKeyState()) {
            if (System.currentTimeMillis() - ALLATORIxDEMO < 500L) {
                return;
            }
            vq.ALLATORIxDEMO();
            ca.l.z("\u91cd\u8f7d\u5b8c\u6210");
            ALLATORIxDEMO = System.currentTimeMillis();
        }
    }
}

