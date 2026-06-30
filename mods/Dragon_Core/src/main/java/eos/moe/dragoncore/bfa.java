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
            a4.func_184366_a(Minecraft.func_71410_x().func_147118_V());
            String a5 = MathHelper.func_180182_a((Random)ThreadLocalRandom.current()).toString();
            om.ALLATORIxDEMO(null, a5, a2.getName(), a4.func_184365_d(), a4.func_147653_e(), a4.func_147655_f(), a4.func_147649_g(), a4.func_147654_h(), a4.func_147651_i(), a4.func_147657_c());
        }
    }
}

