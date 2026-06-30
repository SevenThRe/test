/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.audio.ISound
 *  net.minecraft.client.audio.PositionedSoundRecord
 *  net.minecraft.client.audio.SoundHandler
 *  net.minecraft.init.SoundEvents
 *  net.minecraft.util.SoundEvent
 */
package goblinbob.mobends.core.util;

import goblinbob.mobends.core.Core;
import java.net.URI;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.SoundEvent;

public class GuiHelper {
    public static void closeGui() {
        Minecraft.func_71410_x().func_147108_a(null);
    }

    public static void playButtonSound(SoundHandler soundHandler) {
        soundHandler.func_147682_a((ISound)PositionedSoundRecord.func_184371_a((SoundEvent)SoundEvents.field_187909_gi, (float)1.0f));
    }

    public static boolean openUrlInBrowser(String url) {
        try {
            Class<?> oclass = Class.forName("java.awt.Desktop");
            Object object = oclass.getMethod("getDesktop", new Class[0]).invoke(null, new Object[0]);
            oclass.getMethod("browse", URI.class).invoke(object, new URI(url));
            return true;
        }
        catch (Throwable throwable) {
            Core.LOG.warning(String.format("Couldn't open link %s", url));
            return false;
        }
    }
}

