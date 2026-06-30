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
        if (a2.phase == TickEvent.Phase.END && ALLATORIxDEMO++ > 10 && Minecraft.func_71410_x().field_71439_g != null) {
            ALLATORIxDEMO = 0;
            WorldClient a3 = Minecraft.func_71410_x().field_71441_e;
            for (TileEntity a4 : new ArrayList(a3.field_147482_g)) {
                int a5;
                if (!(a4 instanceof TileEntitySkull)) continue;
                gq a6 = q.ALLATORIxDEMO(a4);
                if (a6 != null && gq.ALLATORIxDEMO(a6) > 0 && a3.func_175642_b(EnumSkyBlock.BLOCK, a4.func_174877_v()) != gq.ALLATORIxDEMO(a6)) {
                    a3.func_180500_c(EnumSkyBlock.BLOCK, a4.func_174877_v());
                    a3.func_180500_c(EnumSkyBlock.SKY, a4.func_174877_v());
                }
                if ((a5 = vu.ALLATORIxDEMO(a4)) <= 0 || a3.func_175642_b(EnumSkyBlock.BLOCK, a4.func_174877_v()) == a5) continue;
                a3.func_180500_c(EnumSkyBlock.BLOCK, a4.func_174877_v());
                a3.func_180500_c(EnumSkyBlock.SKY, a4.func_174877_v());
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
                    Minecraft.func_71410_x().field_71439_g.func_145747_a((ITextComponent)new TextComponentString("\u00a7c\u65e0\u6cd5\u52a0\u8f7d\u65b9\u5757\u6a21\u578b:match=" + gq.f(a2)));
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
            BlockPos a12 = a3.func_174877_v();
            a11 = a12.func_177958_n() + "," + a12.func_177956_o() + "," + a12.func_177952_p();
        }
        a10.c(a11);
        a10.ALLATORIxDEMO(a11);
        if (a8 >= 0) {
            Minecraft.func_71410_x().func_110434_K().func_110577_a(b[a8]);
            GlStateManager.func_179128_n((int)5890);
            GlStateManager.func_179094_E();
            GlStateManager.func_179152_a((float)4.0f, (float)2.0f, (float)1.0f);
            GlStateManager.func_179109_b((float)0.0625f, (float)0.0625f, (float)0.0625f);
            GlStateManager.func_179128_n((int)5888);
        } else {
            a2.ALLATORIxDEMO();
        }
        GlStateManager.func_179147_l();
        GlStateManager.func_187401_a((GlStateManager.SourceFactor)GlStateManager.SourceFactor.SRC_ALPHA, (GlStateManager.DestFactor)GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
        GlStateManager.func_179094_E();
        GlStateManager.func_179129_p();
        GlStateManager.func_179109_b((float)(a4 + 0.5f), (float)a5, (float)(a6 + 0.5f));
        GlStateManager.func_179091_B();
        GlStateManager.func_179152_a((float)-1.0f, (float)-1.0f, (float)1.0f);
        GlStateManager.func_179141_d();
        GlStateManager.func_179114_b((float)a7, (float)0.0f, (float)1.0f, (float)0.0f);
        GlStateManager.func_179139_a((double)gq.ALLATORIxDEMO(a2), (double)gq.ALLATORIxDEMO(a2), (double)gq.ALLATORIxDEMO(a2));
        a10.c();
        if (a8 < 0 && a2.ALLATORIxDEMO()) {
            float a13 = OpenGlHelper.lastBrightnessX;
            float a14 = OpenGlHelper.lastBrightnessY;
            OpenGlHelper.func_77475_a((int)OpenGlHelper.field_77476_b, (float)240.0f, (float)a14);
            a10.c();
            OpenGlHelper.func_77475_a((int)OpenGlHelper.field_77476_b, (float)a13, (float)a14);
        }
        GlStateManager.func_179084_k();
        GlStateManager.func_179121_F();
        if (a8 >= 0) {
            GlStateManager.func_179128_n((int)5890);
            GlStateManager.func_179121_F();
            GlStateManager.func_179128_n((int)5888);
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
        WorldClient a7 = Minecraft.func_71410_x().field_71441_e;
        TileEntity a8 = a7.func_175625_s(new BlockPos(a2, a3, a4));
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

