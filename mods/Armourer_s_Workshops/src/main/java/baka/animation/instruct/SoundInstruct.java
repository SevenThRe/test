/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.audio.ISound
 *  net.minecraft.client.audio.ISound$AttenuationType
 *  net.minecraft.client.audio.PositionedSoundRecord
 *  net.minecraft.client.audio.SoundHandler
 *  net.minecraft.client.audio.SoundManager
 *  net.minecraft.client.entity.AbstractClientPlayer
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.SoundCategory
 *  net.minecraftforge.fml.relauncher.ReflectionHelper
 *  paulscode.sound.SoundSystem
 */
package baka.animation.instruct;

import baka.animation.instruct.InstructBase;
import goblinbob.mobends.standard.data.PlayerData;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.audio.SoundManager;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraftforge.fml.relauncher.ReflectionHelper;
import paulscode.sound.SoundSystem;

public class SoundInstruct
extends InstructBase {
    protected final String actionName;
    protected final String sound;

    public SoundInstruct(String actionName, String sound) {
        this.actionName = actionName;
        this.sound = sound;
    }

    @Override
    public void perform(PlayerData data) {
        super.perform(data);
        Minecraft minecraft = Minecraft.func_71410_x();
        AbstractClientPlayer entity = (AbstractClientPlayer)data.getEntity();
        if (Minecraft.func_71410_x().func_152345_ab()) {
            minecraft.func_147118_V().func_147682_a((ISound)new PositionedSoundRecord(new ResourceLocation(this.sound), SoundCategory.MASTER, 1.0f, 1.0f, false, 0, ISound.AttenuationType.LINEAR, (float)entity.field_70165_t, (float)entity.field_70163_u, (float)entity.field_70161_v));
        } else {
            Minecraft.func_71410_x().func_152344_a(() -> minecraft.func_147118_V().func_147682_a((ISound)new PositionedSoundRecord(new ResourceLocation(this.sound), SoundCategory.MASTER, 1.0f, 1.0f, false, 0, ISound.AttenuationType.LINEAR, (float)entity.field_70165_t, (float)entity.field_70163_u, (float)entity.field_70161_v)));
        }
    }

    public static void PlayFileSound(String s2, String path, float volume, float pitch, float x2, float y2, float z2, boolean loop) {
        try {
            SoundHandler soundHandler = Minecraft.func_71410_x().func_147118_V();
            SoundManager sndManager = (SoundManager)ReflectionHelper.getPrivateValue(SoundHandler.class, (Object)soundHandler, (String[])new String[]{"sndManager", "field_147694_f"});
            SoundSystem sndSystem = (SoundSystem)ReflectionHelper.getPrivateValue(SoundManager.class, (Object)sndManager, (String[])new String[]{"sndSystem", "field_148620_e"});
            int type = x2 == 0.0f && y2 == 0.0f && z2 == 0.0f ? ISound.AttenuationType.NONE.func_148586_a() : ISound.AttenuationType.LINEAR.func_148586_a();
            sndSystem.newStreamingSource(true, s2, SoundInstruct.getURLForSoundResource(new ResourceLocation("dragoncore", path)), path, loop, x2, y2, z2, type, 16.0f);
            sndSystem.setPitch(s2, pitch);
            sndSystem.setVolume(s2, volume);
            sndSystem.play(s2);
        }
        catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private static URL getURLForSoundResource(final ResourceLocation p_148612_0_) {
        String s2 = String.format("%s:%s:%s", "mcsounddomain", p_148612_0_.func_110624_b(), p_148612_0_.func_110623_a());
        URLStreamHandler urlstreamhandler = new URLStreamHandler(){

            @Override
            protected URLConnection openConnection(URL p_openConnection_1_) {
                return new URLConnection(p_openConnection_1_){

                    @Override
                    public void connect() throws IOException {
                    }

                    @Override
                    public InputStream getInputStream() throws IOException {
                        return Minecraft.func_71410_x().func_110442_L().func_110536_a(p_148612_0_).func_110527_b();
                    }
                };
            }
        };
        try {
            return new URL(null, s2, urlstreamhandler);
        }
        catch (MalformedURLException var4) {
            throw new Error("TODO: Sanely handle url exception! :D");
        }
    }
}

