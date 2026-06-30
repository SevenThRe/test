/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.netty.util.internal.ThreadLocalRandom
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.audio.ISound
 *  net.minecraft.util.math.MathHelper
 *  net.minecraftforge.client.event.sound.PlaySoundEvent
 *  net.minecraftforge.fml.common.Mod$EventBusSubscriber
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.am;
import eos.moe.dragoncore.om;
import io.netty.util.internal.ThreadLocalRandom;
import java.util.Random;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.client.event.sound.PlaySoundEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid="dragoncore")
public class bfa {
    public bfa() {
        bfa a2;
    }

    @SubscribeEvent
    public static void ALLATORIxDEMO(PlaySoundEvent a2) {
        if (a2.getSound() instanceof am) {
            return;
        }
        boolean a3 = a2.getName().endsWith(".ogg");
        if (a3) {
            a2.setResultSound(null);
            ISound a4 = a2.getSound();
            a4.createAccessor(Minecraft.getMinecraft().getSoundHandler());
            String a5 = MathHelper.getRandomUUID((Random)ThreadLocalRandom.current()).toString();
            om.ALLATORIxDEMO(null, a5, a2.getName(), a4.getCategory(), a4.getVolume(), a4.getPitch(), a4.getXPosF(), a4.getYPosF(), a4.getZPosF(), a4.canRepeat());
        }
    }
}

