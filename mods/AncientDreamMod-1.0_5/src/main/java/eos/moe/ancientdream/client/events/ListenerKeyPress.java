/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraftforge.fml.common.Mod$EventBusSubscriber
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.gameevent.InputEvent$KeyInputEvent
 *  org.lwjgl.input.Keyboard
 */
package eos.moe.ancientdream.client.events;

import eos.moe.ancientdream.data.DataManager;
import eos.moe.ancientdream.network.send.MessageSender;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import org.lwjgl.input.Keyboard;

@Mod.EventBusSubscriber(modid="ancientdream")
public class ListenerKeyPress {
    private static long lastTime;

    @SubscribeEvent
    public static void keyPress(InputEvent.KeyInputEvent e) {
        if (Keyboard.isKeyDown((int)48)) {
            // empty if block
        }
        if (Keyboard.getEventKeyState()) {
            int slot = -1;
            switch (Keyboard.getEventKey()) {
                case 16: {
                    slot = 0;
                    break;
                }
                case 19: {
                    slot = 1;
                    break;
                }
                case 33: {
                    slot = 2;
                    break;
                }
                case 34: {
                    slot = 3;
                    break;
                }
                case 47: {
                    slot = 4;
                }
            }
            if (slot != -1 && DataManager.playerData.bindSkills.size() == 5) {
                if (System.currentTimeMillis() - lastTime < 50L) {
                    return;
                }
                lastTime = System.currentTimeMillis();
                String key = DataManager.playerData.bindSkills.get(slot).getKey();
                if (key != null) {
                    MessageSender.sendUseSkill(key);
                }
            }
        }
    }
}

