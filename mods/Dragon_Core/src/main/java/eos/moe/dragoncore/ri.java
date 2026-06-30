/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonPrimitive
 *  javax.vecmath.Matrix3f
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.world.World
 *  net.minecraftforge.client.event.RenderWorldLastEvent
 *  net.minecraftforge.event.world.WorldEvent$Unload
 *  net.minecraftforge.fml.common.Mod$EventBusSubscriber
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.gameevent.TickEvent$ClientTickEvent
 *  net.minecraftforge.fml.common.gameevent.TickEvent$Phase
 *  net.minecraftforge.fml.common.network.FMLNetworkEvent$ClientDisconnectionFromServerEvent
 *  org.apache.commons.lang3.math.NumberUtils
 */
package eos.moe.dragoncore;

import blockbuster.BedrockScheme;
import blockbuster.emitter.BedrockEmitter;
import blockbuster.math.molang.expressions.MolangExpression;
import blockbuster.utils.Interpolations;
import blockbuster.utils.MatrixUtils;
import blockbuster.utils.texture.GifHandler;
import blockbuster.utils.texture.GifTexture;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import eos.moe.dragoncore.dt;
import eos.moe.dragoncore.fba;
import eos.moe.dragoncore.li;
import eos.moe.dragoncore.pm;
import eos.moe.dragoncore.vk;
import eos.moe.dragoncore.xz;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import javax.vecmath.Matrix3f;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent;
import org.apache.commons.lang3.math.NumberUtils;

@Mod.EventBusSubscriber(modid="dragoncore")
public class ri {
    private static final Map<String, BedrockEmitter> y = new HashMap<String, BedrockEmitter>();
    private static final Map<String, BedrockEmitter> k = new HashMap<String, BedrockEmitter>();
    private static boolean ALLATORIxDEMO;

    public ri() {
        ri a2;
    }

    public static void ALLATORIxDEMO(String a2, String a3, String a4, String a5, String a6, int a7) {
        String[] a8;
        String[] a9;
        int a10 = 0;
        if (a2.contains("|")) {
            a9 = a2.split("\\|", 2);
            a10 = NumberUtils.toInt((String)a9[1]);
            a2 = a9[0];
        }
        a9 = li.k.ALLATORIxDEMO(a2);
        BedrockEmitter a11 = new BedrockEmitter();
        a11.renderType = a10;
        a11.setScheme((BedrockScheme)a9);
        a11.maxLifeTicks = a7;
        a11.setWorld((World)Minecraft.func_71410_x().field_71441_e);
        try {
            a8 = a6.split(",");
            if (a8.length == 3) {
                a11.extraX = vk.f(a8[0]);
                a11.extraY = vk.f(a8[1]);
                a11.extraZ = vk.f(a8[2]);
            }
        }
        catch (Exception a12) {
            a12.printStackTrace();
            return;
        }
        try {
            String[] a13;
            a8 = a4.split(",");
            if (a8.length == 3) {
                a11.lastGlobal.x = Double.parseDouble(a8[0]);
                a11.lastGlobal.y = Double.parseDouble(a8[1]);
                a11.lastGlobal.z = Double.parseDouble(a8[2]);
            } else {
                a13 = UUID.fromString(a4);
                EntityLivingBase a14 = pm.ALLATORIxDEMO((UUID)a13);
                a11.setTarget(a14);
                a11.targetUUID = a13;
                if (a14 != null) {
                    a11.lastGlobal.x = Interpolations.lerp(a14.field_70169_q, a14.field_70165_t, 0.0);
                    a11.lastGlobal.y = Interpolations.lerp(a14.field_70167_r, a14.field_70163_u, 0.0);
                    a11.lastGlobal.z = Interpolations.lerp(a14.field_70166_s, a14.field_70161_v, 0.0);
                }
            }
            a5 = a5.replace("headyaw", "variable.heady").replace("yaw", "variable.bodyyaw").replace("pitch", "variable.headpitch");
            a13 = a5.split(",");
            if (a13.length == 1 && "look".equalsIgnoreCase(a5)) {
                a11.look = true;
            } else if (a13.length == 3 && a11.scheme != null) {
                a11.rotationsParser = new MolangExpression[]{a11.scheme.parser.parseJson((JsonElement)new JsonPrimitive(a13[0])), a11.scheme.parser.parseJson((JsonElement)new JsonPrimitive(a13[1])), a11.scheme.parser.parseJson((JsonElement)new JsonPrimitive(a13[2]))};
            }
        }
        catch (Exception a15) {
            a15.printStackTrace();
            return;
        }
        if (ALLATORIxDEMO) {
            k.put(a3, a11);
        } else {
            y.put(a3, a11);
        }
    }

    public static void ALLATORIxDEMO(String a2) {
        BedrockEmitter a3 = y.get(a2);
        if (a3 != null) {
            a3.maxLifeTicks = 1;
        }
    }

    public static void ALLATORIxDEMO(UUID a2) {
        ALLATORIxDEMO = true;
        for (Map.Entry<String, BedrockEmitter> a3 : y.entrySet()) {
            if (!a2.equals(a3.getValue().targetUUID)) continue;
            a3.getValue().maxLifeTicks = 1;
            return;
        }
        ALLATORIxDEMO = false;
    }

    private static /* synthetic */ void f() {
        if (!k.isEmpty()) {
            y.putAll(k);
            k.clear();
        }
    }

    public static void c() {
        for (Entity a2 : Minecraft.func_71410_x().field_71441_e.func_72910_y()) {
            xz xz2 = dt.k.getEntityManager(a2.func_110124_au());
            if (xz2 == null || !(a2 instanceof EntityLivingBase) || xz2.c().isEmpty()) continue;
            for (BedrockEmitter a4 : xz2.c().values()) {
                a4.setTarget((EntityLivingBase)a2);
                a4.update();
            }
        }
        ArrayList<String> a5 = new ArrayList<String>();
        ALLATORIxDEMO = true;
        for (Map.Entry<String, BedrockEmitter> entry : y.entrySet()) {
            BedrockEmitter a6 = entry.getValue();
            a6.updateTarget();
            a6.update();
            boolean bl2 = a6.running = a6.maxLifeTicks == 0 || a6.sanityTicks < a6.maxLifeTicks;
            if (a6.maxLifeTicks == 0 && a6.end && a6.particles.isEmpty()) {
                a5.add(entry.getKey());
                continue;
            }
            if (a6.maxLifeTicks == 0 || a6.running || !a6.particles.isEmpty()) continue;
            a5.add(entry.getKey());
        }
        if (!a5.isEmpty()) {
            for (String string : a5) {
                y.remove(string);
            }
        }
        ri.f();
        ALLATORIxDEMO = false;
    }

    public static void ALLATORIxDEMO(float a2) {
        fba.ALLATORIxDEMO(a2);
        if (!y.isEmpty()) {
            ALLATORIxDEMO = true;
            for (BedrockEmitter a3 : y.values()) {
                MolangExpression[] a4;
                if (a3.targetUUID != null) {
                    a4 = a3.target;
                    if (a4 == null) continue;
                    a3.lastGlobal.x = Interpolations.lerp(a4.field_70169_q, a4.field_70165_t, (double)a2);
                    a3.lastGlobal.y = Interpolations.lerp(a4.field_70167_r, a4.field_70163_u, (double)a2);
                    a3.lastGlobal.z = Interpolations.lerp(a4.field_70166_s, a4.field_70161_v, (double)a2);
                }
                a3.prevRotation.set(a3.rotation);
                a3.rotation.setIdentity();
                a4 = a3.rotationsParser;
                try {
                    if (a3.look) {
                        if (a3.target != null) {
                            Matrix3f a5 = MatrixUtils.getZYXrotationMatrix(0.0f, (float)Math.toRadians(-a3.target.field_70759_as), (float)Math.toRadians(a3.target.field_70125_A));
                            a3.rotation.set(a5);
                        }
                    } else if (a4 != null && a4.length == 3) {
                        float a6;
                        float a7;
                        if (a3.target != null && a3.scheme != null) {
                            float a8 = ri.ALLATORIxDEMO(a3.target.field_70758_at, a3.target.field_70759_as, a2);
                            a7 = ri.ALLATORIxDEMO(a3.target.field_70760_ar, a3.target.field_70761_aq, a2);
                            a6 = ri.ALLATORIxDEMO(a3.target.field_70127_C, a3.target.field_70125_A, a2);
                            a3.scheme.parser.setValue("variable.heady", a8);
                            a3.scheme.parser.setValue("variable.bodyyaw", a7);
                            a3.scheme.parser.setValue("variable.headpitch", a6);
                        }
                        float a9 = (float)a4[0].get();
                        a7 = (float)a4[1].get();
                        a6 = (float)a4[2].get();
                        Matrix3f a10 = MatrixUtils.getXYZrotationMatrix((float)Math.toRadians(a9), (float)Math.toRadians(a7), (float)Math.toRadians(a6));
                        a3.rotation.set(a10);
                    }
                }
                catch (Exception a11) {
                    a11.printStackTrace();
                }
                GlStateManager.func_179094_E();
                a3.render(a2);
                GlStateManager.func_179121_F();
            }
            ri.f();
            ALLATORIxDEMO = false;
        }
    }

    public static float ALLATORIxDEMO(EntityLivingBase a2, float a3) {
        boolean a4;
        float a5 = ri.ALLATORIxDEMO(a2.field_70760_ar, a2.field_70761_aq, a3);
        float a6 = ri.ALLATORIxDEMO(a2.field_70758_at, a2.field_70759_as, a3);
        float a7 = a6 - a5;
        boolean bl2 = a4 = a2.func_184218_aH() && a2.func_184187_bx() != null && a2.func_184187_bx().shouldRiderSit();
        if (a4 && a2.func_184187_bx() instanceof EntityLivingBase) {
            EntityLivingBase a8 = (EntityLivingBase)a2.func_184187_bx();
            a5 = ri.ALLATORIxDEMO(a8.field_70760_ar, a8.field_70761_aq, a3);
            a7 = a6 - a5;
            float a9 = MathHelper.func_76142_g((float)a7);
            if (a9 < -85.0f) {
                a9 = -85.0f;
            }
            if (a9 >= 85.0f) {
                a9 = 85.0f;
            }
            a5 = a6 - a9;
            if (a9 * a9 > 2500.0f) {
                a5 += a9 * 0.2f;
            }
        }
        return a5;
    }

    public static float ALLATORIxDEMO(String a2, EntityLivingBase a3, float a4) {
        if (a3 != null) {
            if (a2.contains("headyaw")) {
                a2 = a2.replace("headyaw", String.valueOf(ri.ALLATORIxDEMO(a3.field_70758_at, a3.field_70759_as, a4)));
            }
            if (a2.contains("yaw")) {
                a2 = a2.replace("yaw", String.valueOf(ri.ALLATORIxDEMO(a3.field_70760_ar, a3.field_70761_aq, a4)));
            }
            if (a2.contains("pitch")) {
                a2 = a2.replace("pitch", String.valueOf(ri.ALLATORIxDEMO(a3.field_70127_C, a3.field_70125_A, a4)));
            }
        }
        return (float)vk.f(a2);
    }

    public static float ALLATORIxDEMO(float a2, float a3, float a4) {
        float a5;
        for (a5 = a3 - a2; a5 < -180.0f; a5 += 360.0f) {
        }
        while (a5 >= 180.0f) {
            a5 -= 360.0f;
        }
        return a2 + a4 * a5;
    }

    public static void ALLATORIxDEMO() {
        y.clear();
    }

    @SubscribeEvent
    public static void ALLATORIxDEMO(WorldEvent.Unload a2) {
        ri.ALLATORIxDEMO();
    }

    @SubscribeEvent
    public static void ALLATORIxDEMO(TickEvent.ClientTickEvent a2) {
        if (a2.phase == TickEvent.Phase.START) {
            return;
        }
        if (Minecraft.func_71410_x().field_71441_e == null) {
            return;
        }
        ri.c();
    }

    @SubscribeEvent
    public static void ALLATORIxDEMO(FMLNetworkEvent.ClientDisconnectionFromServerEvent a2) {
        Minecraft.func_71410_x().func_152344_a(ri::ALLATORIxDEMO);
    }

    @SubscribeEvent
    public static void ALLATORIxDEMO(RenderWorldLastEvent a2) {
        for (GifTexture a3 : GifHandler.gifs.values()) {
            a3.func_110550_d();
        }
    }
}

