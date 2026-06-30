/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.ByteBuf
 *  io.netty.buffer.Unpooled
 *  net.minecraft.client.Minecraft
 *  net.minecraft.network.PacketBuffer
 *  net.minecraft.util.SoundCategory
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.event.entity.EntityJoinWorldEvent
 *  net.minecraftforge.fml.common.FMLCommonHandler
 *  net.minecraftforge.fml.common.Mod
 *  net.minecraftforge.fml.common.Mod$EventHandler
 *  net.minecraftforge.fml.common.event.FMLPreInitializationEvent
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.network.FMLEventChannel
 *  net.minecraftforge.fml.common.network.FMLNetworkEvent$ClientCustomPacketEvent
 *  net.minecraftforge.fml.common.network.FMLNetworkEvent$ClientDisconnectionFromServerEvent
 *  net.minecraftforge.fml.common.network.NetworkRegistry
 *  net.minecraftforge.fml.common.network.internal.FMLProxyPacket
 */
package com.locydragon.mod.abf;

import com.locydragon.mod.abf.Info;
import com.locydragon.mod.abf.MyPlayer;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.JavaSoundAudioDevice;
import net.minecraft.client.Minecraft;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.SoundCategory;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.FMLEventChannel;
import net.minecraftforge.fml.common.network.FMLNetworkEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.internal.FMLProxyPacket;

@Mod(modid="audiobuffer", version="1.0.0", acceptedMinecraftVersions="[1.9,)")
public class AudioBuffer {
    static String MODID = "audiobuffer";
    static String VERSION = "1.0.0";
    public static Float volume;
    public static FMLEventChannel channel;
    public static FMLEventChannel output;
    public static MyPlayer nowPlaying;
    public static Info info;
    public static ExecutorService EXECUTOR;

    @Mod.EventHandler
    public void preload(FMLPreInitializationEvent evt) {
        File readMe = new File(".//resourcepacks//AudioBuffer//Null.test");
        if (!readMe.exists()) {
            readMe.getParentFile().mkdirs();
            try {
                readMe.createNewFile();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        MinecraftForge.EVENT_BUS.register((Object)this);
        FMLCommonHandler.instance().bus().register((Object)this);
        channel = NetworkRegistry.INSTANCE.newEventDrivenChannel("AudioBuffer");
        channel.register((Object)this);
        output = NetworkRegistry.INSTANCE.newEventDrivenChannel("AudioBufferOut");
        output.register((Object)this);
    }

    @SubscribeEvent
    public void onServerQuit(FMLNetworkEvent.ClientDisconnectionFromServerEvent e) {
        EXECUTOR.submit(AudioBuffer::Stop);
    }

    @SubscribeEvent
    public void onWorldChange(EntityJoinWorldEvent e) {
        if (e.getEntity() == Minecraft.getMinecraft().player) {
            EXECUTOR.submit(AudioBuffer::Stop);
        }
    }

    @SubscribeEvent
    public void onClicentPacket(FMLNetworkEvent.ClientCustomPacketEvent evt) {
        EXECUTOR.submit(() -> {
            ByteBuf directBuf = evt.getPacket().payload();
            int length = directBuf.readableBytes();
            byte[] array = new byte[length];
            directBuf.getBytes(directBuf.readerIndex(), array);
            String message = new String(array);
            if (message.equals("[Stop]")) {
                AudioBuffer.Stop();
            } else if (message.startsWith("[Volume]")) {
                AudioBuffer.Volume(Float.parseFloat(message.replace("[Volume]", "")));
            } else if (message.startsWith("[Net]")) {
                this.Play_Net(message.replace("[NET]", ""));
            } else if (message.startsWith("[Local]")) {
                this.Play_Local(message.replace("[Local]", ""));
            } else if (message.startsWith("[Has]")) {
                this.Has(message.replace("[Has]", ""));
            } else if (message.startsWith("[Download]")) {
                this.Download(message.replace("[Download]", ""));
            } else if (AudioBuffer.info.next) {
                AudioBuffer.info.next = false;
                String fileName = AudioBuffer.info.name;
                AudioBuffer.info.name = null;
                File targetFile3 = new File(".//resourcepacks//AudioBuffer//" + fileName);
                if (!targetFile3.exists()) {
                    try {
                        targetFile3.createNewFile();
                    }
                    catch (IOException e3) {
                        e3.printStackTrace();
                    }
                    try {
                        FileOutputStream stream = new FileOutputStream(targetFile3);
                        stream.write(array);
                        stream.close();
                    }
                    catch (IOException e3) {
                        e3.printStackTrace();
                    }
                }
            }
        });
    }

    public static void Stop() {
        if (nowPlaying != null) {
            nowPlaying.close();
        }
    }

    public static void Volume(float volume) {
        if (nowPlaying != null) {
            nowPlaying.setGain(volume);
        }
    }

    public void Play_Net(String url) {
        try {
            AudioBuffer.Stop();
            URL url1 = new URL(url);
            JavaSoundAudioDevice javaSoundAudioDevice = new JavaSoundAudioDevice();
            javaSoundAudioDevice.setDefaultVolume(-88.0f + Minecraft.getMinecraft().gameSettings.getSoundLevel(SoundCategory.MUSIC) * 94.0f);
            nowPlaying = new MyPlayer(url1.openStream(), javaSoundAudioDevice);
            this.Play();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void Play_Local(String file) {
        System.out.println("\u64ad\u653e" + file);
        File targetFile2 = new File(".//resourcepacks//AudioBuffer//" + file);
        try {
            AudioBuffer.Stop();
            JavaSoundAudioDevice javaSoundAudioDevice = new JavaSoundAudioDevice();
            javaSoundAudioDevice.setDefaultVolume(-44.0f + Minecraft.getMinecraft().gameSettings.getSoundLevel(SoundCategory.MUSIC) * 50.0f);
            nowPlaying = new MyPlayer((InputStream)new FileInputStream(targetFile2), javaSoundAudioDevice);
            this.Play();
        }
        catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void Play() {
        new Thread(() -> {
            try {
                nowPlaying.play();
            }
            catch (JavaLayerException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public void Has(String message) {
        String time = message.split("\\*")[0];
        String name = message.split("\\*")[1];
        File targetFile = new File(".//resourcepacks//AudioBuffer//" + name);
        if (targetFile.exists()) {
            byte[] info = (time + "*true").getBytes();
            ByteBuf buf = Unpooled.wrappedBuffer((byte[])info);
            output.sendToServer(new FMLProxyPacket(new PacketBuffer(buf), "AudioBufferOut"));
        } else {
            byte[] info = (time + "*false").getBytes();
            ByteBuf buf = Unpooled.wrappedBuffer((byte[])info);
            output.sendToServer(new FMLProxyPacket(new PacketBuffer(buf), "AudioBufferOut"));
        }
    }

    public void Download(String message) {
        AudioBuffer.info.next = true;
        AudioBuffer.info.name = message;
    }

    static {
        nowPlaying = null;
        info = new Info();
        EXECUTOR = Executors.newFixedThreadPool(1);
    }
}

