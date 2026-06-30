/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.authlib.GameProfile
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.multiplayer.WorldClient
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.GlStateManager$DestFactor
 *  net.minecraft.client.renderer.GlStateManager$SourceFactor
 *  net.minecraft.client.renderer.OpenGlHelper
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.tileentity.TileEntitySkull
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.text.ITextComponent
 *  net.minecraft.util.text.TextComponentString
 *  net.minecraft.world.EnumSkyBlock
 *  net.minecraftforge.fml.common.Mod$EventBusSubscriber
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.gameevent.TickEvent$ClientTickEvent
 *  net.minecraftforge.fml.common.gameevent.TickEvent$Phase
 */
package eos.moe.dragoncore;

import com.mojang.authlib.GameProfile;
import eos.moe.dragoncore.gq;
import eos.moe.dragoncore.jv;
import eos.moe.dragoncore.kq;
import eos.moe.dragoncore.ms;
import eos.moe.dragoncore.ox;
import eos.moe.dragoncore.vu;
import eos.moe.dragoncore.wr;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntitySkull;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.EnumSkyBlock;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.yaml.snakeyamla.configuration.ConfigurationSection;

@Mod.EventBusSubscriber(modid="dragoncore")
public class cy {
    public static cy q = new cy();
    private static final ResourceLocation[] b = new ResourceLocation[]{new ResourceLocation("textures/blocks/destroy_stage_0.png"), new ResourceLocation("textures/blocks/destroy_stage_1.png"), new ResourceLocation("textures/blocks/destroy_stage_2.png"), new ResourceLocation("textures/blocks/destroy_stage_3.png"), new ResourceLocation("textures/blocks/destroy_stage_4.png"), new ResourceLocation("textures/blocks/destroy_stage_5.png"), new ResourceLocation("textures/blocks/destroy_stage_6.png"), new ResourceLocation("textures/blocks/destroy_stage_7.png"), new ResourceLocation("textures/blocks/destroy_stage_8.png"), new ResourceLocation("textures/blocks/destroy_stage_9.png")};
    private static final ExecutorService o = Executors.newCachedThreadPool();
    public Map<String, gq> y = new ConcurrentHashMap<String, gq>();
    private Map<gq, ox> k = new HashMap<gq, ox>();
    private static int ALLATORIxDEMO;

    public cy() {
        cy a2;
    }

    @SubscribeEvent
    public static void ALLATORIxDEMO(TickEvent.ClientTickEvent a2) {
        if (a2.phase == TickEvent.Phase.END && ALLATORIxDEMO++ > 10 && Minecraft.getMinecraft().player != null) {
            ALLATORIxDEMO = 0;
            WorldClient a3 = Minecraft.getMinecraft().world;
            for (TileEntity a4 : new ArrayList(a3.loadedTileEntityList)) {
                int a5;
                if (!(a4 instanceof TileEntitySkull)) continue;
                gq a6 = q.ALLATORIxDEMO(a4);
                if (a6 != null && gq.ALLATORIxDEMO(a6) > 0 && a3.getLightFor(EnumSkyBlock.BLOCK, a4.getPos()) != gq.ALLATORIxDEMO(a6)) {
                    a3.checkLightFor(EnumSkyBlock.BLOCK, a4.getPos());
                    a3.checkLightFor(EnumSkyBlock.SKY, a4.getPos());
                }
                if ((a5 = vu.ALLATORIxDEMO(a4)) <= 0 || a3.getLightFor(EnumSkyBlock.BLOCK, a4.getPos()) == a5) continue;
                a3.checkLightFor(EnumSkyBlock.BLOCK, a4.getPos());
                a3.checkLightFor(EnumSkyBlock.SKY, a4.getPos());
            }
        }
    }

    public gq ALLATORIxDEMO(GameProfile a2) {
        cy a3;
        String a4 = vu.ALLATORIxDEMO(a2);
        if (a4 == null) {
            return null;
        }
        return a3.y.get(a4);
    }

    public gq ALLATORIxDEMO(TileEntity a2) {
        cy a3;
        String a4 = vu.ALLATORIxDEMO(a2);
        if (a4 == null) {
            return null;
        }
        return a3.y.get(a4);
    }

    public ox ALLATORIxDEMO(gq a2) {
        cy a3;
        if (!a3.k.containsKey(a2)) {
            o.submit(() -> {
                try {
                    cy a3;
                    jv a4 = ms.ALLATORIxDEMO(new ResourceLocation("dragoncore", "models/blocks/" + gq.c(a2)));
                    Map<String, kq> a5 = null;
                    if (gq.ALLATORIxDEMO(a2) != null) {
                        a5 = wr.ALLATORIxDEMO(new ResourceLocation("dragoncore", "models/blocks/" + gq.ALLATORIxDEMO(a2)));
                    }
                    ox a6 = new ox(a4, a5);
                    a3.k.put(a2, a6);
                }
                catch (Exception a7) {
                    a7.printStackTrace();
                    Minecraft.getMinecraft().player.sendMessage((ITextComponent)new TextComponentString("\u00a7c\u65e0\u6cd5\u52a0\u8f7d\u65b9\u5757\u6a21\u578b:match=" + gq.f(a2)));
                }
            });
            a3.k.put(a2, null);
        }
        return a3.k.get(a2);
    }

    public void ALLATORIxDEMO() {
        cy a2;
        a2.k.clear();
    }

    public void ALLATORIxDEMO(ConfigurationSection a2) {
        cy a3;
        gq a4 = new gq(a2);
        a3.y.put(gq.f(a4), a4);
    }

    public void ALLATORIxDEMO(gq a2, TileEntitySkull a3, float a4, float a5, float a6, float a7, int a8) {
        cy a9;
        if (gq.ALLATORIxDEMO(a2)) {
            return;
        }
        ox a10 = a9.ALLATORIxDEMO(a2);
        if (a10 == null) {
            return;
        }
        String a11 = "default";
        if (a3 != null) {
            BlockPos a12 = a3.getPos();
            a11 = a12.getX() + "," + a12.getY() + "," + a12.getZ();
        }
        a10.c(a11);
        a10.ALLATORIxDEMO(a11);
        if (a8 >= 0) {
            Minecraft.getMinecraft().getTextureManager().bindTexture(b[a8]);
            GlStateManager.matrixMode((int)5890);
            GlStateManager.pushMatrix();
            GlStateManager.scale((float)4.0f, (float)2.0f, (float)1.0f);
            GlStateManager.translate((float)0.0625f, (float)0.0625f, (float)0.0625f);
            GlStateManager.matrixMode((int)5888);
        } else {
            a2.ALLATORIxDEMO();
        }
        GlStateManager.enableBlend();
        GlStateManager.blendFunc((GlStateManager.SourceFactor)GlStateManager.SourceFactor.SRC_ALPHA, (GlStateManager.DestFactor)GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
        GlStateManager.pushMatrix();
        GlStateManager.disableCull();
        GlStateManager.translate((float)(a4 + 0.5f), (float)a5, (float)(a6 + 0.5f));
        GlStateManager.enableRescaleNormal();
        GlStateManager.scale((float)-1.0f, (float)-1.0f, (float)1.0f);
        GlStateManager.enableAlpha();
        GlStateManager.rotate((float)a7, (float)0.0f, (float)1.0f, (float)0.0f);
        GlStateManager.scale((double)gq.ALLATORIxDEMO(a2), (double)gq.ALLATORIxDEMO(a2), (double)gq.ALLATORIxDEMO(a2));
        a10.c();
        if (a8 < 0 && a2.ALLATORIxDEMO()) {
            float a13 = OpenGlHelper.lastBrightnessX;
            float a14 = OpenGlHelper.lastBrightnessY;
            OpenGlHelper.setLightmapTextureCoords((int)OpenGlHelper.lightmapTexUnit, (float)240.0f, (float)a14);
            a10.c();
            OpenGlHelper.setLightmapTextureCoords((int)OpenGlHelper.lightmapTexUnit, (float)a13, (float)a14);
        }
        GlStateManager.disableBlend();
        GlStateManager.popMatrix();
        if (a8 >= 0) {
            GlStateManager.matrixMode((int)5890);
            GlStateManager.popMatrix();
            GlStateManager.matrixMode((int)5888);
        }
    }

    public void c(gq a2) {
        cy a3;
        if (gq.ALLATORIxDEMO(a2)) {
            return;
        }
        ox a4 = a3.ALLATORIxDEMO(a2);
        if (a4 == null) {
            return;
        }
        a4.ALLATORIxDEMO();
    }

    public void ALLATORIxDEMO(int a2, int a3, int a4, String a5) {
        cy a6;
        WorldClient a7 = Minecraft.getMinecraft().world;
        TileEntity a8 = a7.getTileEntity(new BlockPos(a2, a3, a4));
        gq a9 = a6.ALLATORIxDEMO(a8);
        if (a9 == null) {
            return;
        }
        ox a10 = a6.ALLATORIxDEMO(a9);
        if (a10 == null) {
            return;
        }
        if (gq.ALLATORIxDEMO(a9)) {
            return;
        }
        String a11 = a2 + "," + a3 + "," + a4;
        a10.ALLATORIxDEMO(a11, a5);
    }
}

