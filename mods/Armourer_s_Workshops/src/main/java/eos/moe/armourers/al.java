/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  eos.moe.dragoncore.api.PlayerModelLoader
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.texture.TextureManager
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.text.ITextComponent
 *  net.minecraft.util.text.TextComponentString
 *  net.minecraftforge.client.model.ModelLoaderRegistry
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.event.entity.EntityJoinWorldEvent
 *  net.minecraftforge.fml.common.eventhandler.EventPriority
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.gameevent.InputEvent$KeyInputEvent
 *  net.minecraftforge.fml.common.network.FMLNetworkEvent$ClientConnectedToServerEvent
 *  net.minecraftforge.fml.common.network.FMLNetworkEvent$ClientDisconnectionFromServerEvent
 *  net.minecraftforge.fml.relauncher.ReflectionHelper
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 *  org.lwjgl.input.Keyboard
 */
package eos.moe.armourers;

import eos.moe.armourers.kd;
import eos.moe.armourers.nf;
import eos.moe.armourers.nn;
import eos.moe.armourers.on;
import eos.moe.armourers.sg;
import eos.moe.armourers.uk;
import eos.moe.armourers.zg;
import eos.moe.armourers.zh;
import eos.moe.armourers.zj;
import eos.moe.dragoncore.api.PlayerModelLoader;
import java.io.IOException;
import java.util.Deque;
import java.util.List;
import java.util.Map;
import java.util.Set;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent;
import net.minecraftforge.fml.relauncher.ReflectionHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;

public class al {
    private long m;
    public static boolean j;

    @SubscribeEvent
    public void r(EntityJoinWorldEvent a2) {
        if (a2.getEntity() != null && (a2.getEntity() instanceof EntityPlayer || nn.j.contains(a2.getEntity().getClass()))) {
            zg.m.remove(a2.getEntity().func_110124_au());
            nf.r(a2.getEntity().func_110124_au());
        }
    }

    public static void z() {
        new al();
    }

    @SubscribeEvent
    public void r(FMLNetworkEvent.ClientDisconnectionFromServerEvent a2) {
        zg.m.clear();
        zg.j.clear();
        zg.c.clear();
        on.c.clear();
    }

    public al() {
        al a2;
        MinecraftForge.EVENT_BUS.register((Object)a2);
    }

    public static void y() {
        if (!j) {
            return;
        }
        Minecraft.func_71410_x().func_152344_a(() -> {
            if (Minecraft.func_71410_x().field_71439_g != null) {
                PlayerModelLoader.clear();
                zg.v.clear();
                kd.j.clear();
                uk.j.clear();
                String[] stringArray = new String[1];
                stringArray[0] = "cache";
                Map map = (Map)ReflectionHelper.getPrivateValue(ModelLoaderRegistry.class, null, (String[])stringArray);
                String[] stringArray2 = new String[1];
                stringArray2[0] = "loadingModels";
                Deque deque = (Deque)ReflectionHelper.getPrivateValue(ModelLoaderRegistry.class, null, (String[])stringArray2);
                String[] stringArray3 = new String[1];
                stringArray3[0] = "textures";
                Set set = (Set)ReflectionHelper.getPrivateValue(ModelLoaderRegistry.class, null, (String[])stringArray3);
                String[] stringArray4 = new String[1];
                stringArray4[0] = "aliases";
                Map map2 = (Map)ReflectionHelper.getPrivateValue(ModelLoaderRegistry.class, null, (String[])stringArray4);
                map.entrySet().removeIf(a2 -> ((ResourceLocation)a2.getKey()).func_110624_b().equals("dragonarmourers"));
                deque.removeIf(a2 -> a2.func_110624_b().equals("dragonarmourers"));
                set.removeIf(a2 -> a2.func_110624_b().equals("dragonarmourers"));
                map2.entrySet().removeIf(a2 -> ((ResourceLocation)a2.getKey()).func_110624_b().equals("dragonarmourers"));
                String[] stringArray5 = new String[2];
                stringArray5[0] = "listTickables";
                stringArray5[1] = "field_110583_b";
                ((List)ReflectionHelper.getPrivateValue(TextureManager.class, (Object)Minecraft.func_71410_x().func_110434_K(), (String[])stringArray5)).removeIf(a2 -> a2 instanceof zj);
                String[] stringArray6 = new String[2];
                stringArray6[0] = "mapTextureObjects";
                stringArray6[1] = "field_110585_a";
                ((Map)ReflectionHelper.getPrivateValue(TextureManager.class, (Object)Minecraft.func_71410_x().func_110434_K(), (String[])stringArray6)).entrySet().removeIf(a2 -> {
                    if (a2.getValue() instanceof zj) {
                        ((zj)((Object)((Object)((Object)a2.getValue())))).func_147631_c();
                        return true;
                    }
                    return false;
                });
                Minecraft.func_71410_x().field_71439_g.func_145747_a((ITextComponent)new TextComponentString("[\u9f99\u4e4b\u65f6\u88c5] \u5df2\u6e05\u9664\u5ba2\u6237\u7aef\u65f6\u88c5\u7f13\u5b58\u6570\u636e"));
            }
        });
    }

    @SubscribeEvent(priority=EventPriority.HIGHEST)
    public void r(FMLNetworkEvent.ClientConnectedToServerEvent a2) {
        zg.m.clear();
        zg.j.clear();
        zg.c.clear();
        nn.y();
    }

    @SideOnly(value=Side.CLIENT)
    @SubscribeEvent
    public void y(InputEvent.KeyInputEvent a2) {
        if (sg.v.func_151468_f()) {
            if (zh.g) {
                Minecraft.func_71410_x().field_71439_g.func_145747_a((ITextComponent)new TextComponentString("\u00a7a\u5df2\u5173\u95ed\u663e\u793a\u4ed6\u4eba\u65f6\u88c5"));
            } else {
                Minecraft.func_71410_x().field_71439_g.func_145747_a((ITextComponent)new TextComponentString("\u00a7a\u5df2\u5f00\u542f\u663e\u793a\u4ed6\u4eba\u65f6\u88c5"));
            }
            zh.g = !zh.g;
        }
    }

    @SubscribeEvent
    public void r(InputEvent.KeyInputEvent a2) throws IOException {
        if (Keyboard.isKeyDown((int)38) && Keyboard.isKeyDown((int)25) && Minecraft.func_71410_x().field_71439_g != null) {
            al a3;
            if (System.currentTimeMillis() - a3.m < 100L) {
                return;
            }
            a3.m = System.currentTimeMillis();
            j = true;
            Minecraft.func_71410_x().field_71439_g.func_145747_a((ITextComponent)new TextComponentString("[\u9f99\u4e4b\u65f6\u88c5] \u5df2\u542f\u7528/sz reload\u91cd\u8f7d\u65f6\u6e05\u9664\u5ba2\u6237\u7aef\u7f13\u5b58\u65f6\u88c5\u6570\u636e,\u8be5\u6548\u679c\u91cd\u542f\u6e38\u620f\u65f6\u5931\u6548"));
        }
    }
}

