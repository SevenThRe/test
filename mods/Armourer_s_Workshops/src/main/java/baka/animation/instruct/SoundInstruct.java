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
        Minecraft minecraft = Minecraft.getMinecraft();
        AbstractClientPlayer entity = (AbstractClientPlayer)data.getEntity();
        if (Minecraft.getMinecraft().isCallingFromMinecraftThread()) {
            minecraft.getSoundHandler().playSound((ISound)new PositionedSoundRecord(new ResourceLocation(this.sound), SoundCategory.MASTER, 1.0f, 1.0f, false, 0, ISound.AttenuationType.LINEAR, (float)entity.posX, (float)entity.posY, (float)entity.posZ));
        } else {
            Minecraft.getMinecraft().addScheduledTask(() -> minecraft.getSoundHandler().playSound((ISound)new PositionedSoundRecord(new ResourceLocation(this.sound), SoundCategory.MASTER, 1.0f, 1.0f, false, 0, ISound.AttenuationType.LINEAR, (float)entity.posX, (float)entity.posY, (float)entity.posZ)));
        }
    }

    public static void PlayFileSound(String s2, String path, float volume, float pitch, float x2, float y2, float z2, boolean loop) {
        try {
            SoundHandler soundHandler = Minecraft.getMinecraft().getSoundHandler();
            SoundManager sndManager = (SoundManager)ReflectionHelper.getPrivateValue(SoundHandler.class, (Object)soundHandler, (String[])new String[]{"sndManager", "sndManager"});
            SoundSystem sndSystem = (SoundSystem)ReflectionHelper.getPrivateValue(SoundManager.class, (Object)sndManager, (String[])new String[]{"sndSystem", "sndSystem"});
            int type = x2 == 0.0f && y2 == 0.0f && z2 == 0.0f ? ISound.AttenuationType.NONE.getTypeInt() : ISound.AttenuationType.LINEAR.getTypeInt();
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
        String s2 = String.format("%s:%s:%s", "mcsounddomain", p_148612_0_.getNamespace(), p_148612_0_.getPath());
        URLStreamHandler urlstreamhandler = new URLStreamHandler(){

            @Override
            protected URLConnection openConnection(URL p_openConnection_1_) {
                return new URLConnection(p_openConnection_1_){

                    @Override
                    public void connect() throws IOException {
                    }

                    @Override
                    public InputStream getInputStream() throws IOException {
                        return Minecraft.getMinecraft().getResourceManager().getResource(p_148612_0_).getInputStream();
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

