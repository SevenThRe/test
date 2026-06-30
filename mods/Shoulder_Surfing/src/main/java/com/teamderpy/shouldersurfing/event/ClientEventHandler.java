/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Predicate
 *  com.google.common.base.Predicates
 *  net.minecraft.block.state.IBlockState
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.Gui
 *  net.minecraft.client.gui.ScaledResolution
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.GlStateManager$DestFactor
 *  net.minecraft.client.renderer.GlStateManager$SourceFactor
 *  net.minecraft.client.renderer.OpenGlHelper
 *  net.minecraft.client.settings.GameSettings
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.item.EntityArmorStand
 *  net.minecraft.entity.monster.EntityVex
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.util.EntitySelectors
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.RayTraceResult
 *  net.minecraft.util.math.RayTraceResult$Type
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.util.text.ITextComponent
 *  net.minecraft.util.text.TextComponentString
 *  net.minecraftforge.client.event.RenderGameOverlayEvent$ElementType
 *  net.minecraftforge.client.event.RenderGameOverlayEvent$Pre
 *  net.minecraftforge.client.event.RenderPlayerEvent$Pre
 *  net.minecraftforge.fml.client.config.GuiUtils
 *  net.minecraftforge.fml.client.event.ConfigChangedEvent$OnConfigChangedEvent
 *  net.minecraftforge.fml.common.eventhandler.EventPriority
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.gameevent.TickEvent$ClientTickEvent
 *  net.minecraftforge.fml.common.gameevent.TickEvent$Phase
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package com.teamderpy.shouldersurfing.event;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.teamderpy.shouldersurfing.asm.InjectionDelegation;
import com.teamderpy.shouldersurfing.config.Config;
import com.teamderpy.shouldersurfing.config.Perspective;
import com.teamderpy.shouldersurfing.event.NetworkManager;
import com.teamderpy.shouldersurfing.util.ShoulderState;
import com.teamderpy.shouldersurfing.util.ShoulderSurfingHelper;
import java.util.List;
import java.util.Map;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.monster.EntityVex;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.fml.client.config.GuiUtils;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(value=Side.CLIENT)
public class ClientEventHandler {
    private RayTraceResult objectMouseOver;
    private Vec3d pointedEntityPos;

    @SubscribeEvent
    public void clientTickEvent(TickEvent.ClientTickEvent event) {
        if (event.phase.equals((Object)TickEvent.Phase.START)) {
            Minecraft minecraft = Minecraft.func_71410_x();
            this.getMouseOver(Minecraft.func_71410_x().func_184121_ak());
            if (minecraft.field_71439_g != null) {
                Vec3d direction;
                Vec3d vec3d = direction = this.objectMouseOver != null ? this.objectMouseOver.field_72307_f : InjectionDelegation.shoulderSurfingLook(40.0).getValue();
                if (this.pointedEntityPos != null) {
                    NetworkManager.sendDirection(direction.field_72450_a, direction.field_72448_b, direction.field_72449_c, true, this.pointedEntityPos.field_72450_a, this.pointedEntityPos.field_72448_b, this.pointedEntityPos.field_72449_c);
                } else {
                    NetworkManager.sendDirection(direction.field_72450_a, direction.field_72448_b, direction.field_72449_c, false, 0.0, 0.0, 0.0);
                }
            }
            if (!Perspective.FIRST_PERSON.equals((Object)Perspective.current())) {
                ShoulderState.setSwitchPerspective(false);
            }
            ShoulderState.setAiming(ShoulderSurfingHelper.isHoldingSpecialItem());
            if (!ShoulderState.isAiming() && Perspective.FIRST_PERSON.equals((Object)Perspective.current()) && ShoulderState.doSwitchPerspective()) {
                ShoulderSurfingHelper.setPerspective(Perspective.SHOULDER_SURFING);
            }
        }
    }

    public void getMouseOver(float partialTicks) {
        Minecraft mc = Minecraft.func_71410_x();
        final Entity entity = mc.func_175606_aa();
        if (entity != null && mc.field_71441_e != null) {
            double d0 = 40.0;
            this.pointedEntityPos = null;
            this.objectMouseOver = InjectionDelegation.rayTrace1(entity, d0, partialTicks);
            Vec3d vec3d = entity.func_174824_e(partialTicks);
            double d1 = d0;
            if (this.objectMouseOver != null) {
                d1 = this.objectMouseOver.field_72307_f.func_72438_d(vec3d);
            }
            Vec3d vec3d1 = entity.func_70676_i(1.0f);
            Map.Entry<Vec3d, Vec3d> entry = InjectionDelegation.shoulderSurfingLook(d0);
            Entity pointedEntity = null;
            Vec3d pointedEntityPos = null;
            AxisAlignedBB grow = entity.func_174813_aQ().func_72321_a(vec3d1.field_72450_a * d0, vec3d1.field_72448_b * d0, vec3d1.field_72449_c * d0).func_72314_b(1.0, 1.0, 1.0);
            List list = mc.field_71441_e.func_175674_a(entity, grow, Predicates.and((Predicate)EntitySelectors.field_180132_d, (Predicate)new Predicate<Entity>(){

                public boolean apply(Entity p_apply_1_) {
                    if (p_apply_1_ == null || !p_apply_1_.func_70067_L() || p_apply_1_ instanceof EntityArmorStand || p_apply_1_ instanceof EntityVex) {
                        return false;
                    }
                    return ClientEventHandler.isInFront(entity, p_apply_1_.func_174791_d());
                }
            }));
            double d2 = d1;
            for (Entity entity1 : list) {
                double d3;
                AxisAlignedBB axisalignedbb = entity1.func_174813_aQ().func_186662_g((double)entity1.func_70111_Y());
                RayTraceResult raytraceresult = axisalignedbb.func_72327_a(entry.getKey(), entry.getValue());
                if (axisalignedbb.func_72318_a(vec3d)) {
                    if (!(d2 >= 0.0)) continue;
                    pointedEntity = entity1;
                    pointedEntityPos = raytraceresult == null ? null : raytraceresult.field_72307_f;
                    d2 = 0.0;
                    continue;
                }
                if (raytraceresult == null || !((d3 = vec3d.func_72438_d(raytraceresult.field_72307_f)) < d2) && d2 != 0.0) continue;
                if (entity1.func_184208_bv() == entity.func_184208_bv() && !entity1.canRiderInteract()) {
                    if (d2 != 0.0) continue;
                    pointedEntity = entity1;
                    pointedEntityPos = raytraceresult.field_72307_f;
                    continue;
                }
                pointedEntity = entity1;
                pointedEntityPos = raytraceresult.field_72307_f;
                d2 = d3;
            }
            if (pointedEntity != null && pointedEntityPos != null) {
                this.pointedEntityPos = pointedEntityPos;
            }
        }
    }

    public static boolean isInFront11(Entity entity, Vec3d target) {
        Vec3d dir = entity.func_70040_Z();
        Vec3d distance = target.func_178788_d(entity.func_174791_d());
        distance = new Vec3d(distance.field_72450_a, 0.0, distance.field_72449_c);
        double dot = distance.func_72430_b(dir);
        double cosSq = 0.25;
        double value = dot * dot / distance.func_189985_c();
        Minecraft.func_71410_x().field_71439_g.func_145747_a((ITextComponent)new TextComponentString(value + " " + dot));
        return dot > 0.0 && value >= cosSq;
    }

    public static boolean isInFront(Entity entity, Vec3d target) {
        Vec3d dir = entity.func_70040_Z();
        Vec3d distance = target.func_178788_d(entity.func_174791_d());
        distance = new Vec3d(distance.field_72450_a, 0.0, distance.field_72449_c);
        double dot = distance.func_72430_b(dir);
        double cosSq = 0.5;
        double value = dot * dot / distance.func_189985_c();
        return dot > 0.0 && value >= cosSq;
    }

    public static boolean isInFront(Entity entity, Entity target, double arc) {
        Vec3d dir = entity.func_70040_Z();
        double cos = Math.cos(Math.toRadians(arc));
        Vec3d relative = target.func_174791_d().func_178788_d(entity.func_174791_d());
        relative = new Vec3d(relative.field_72450_a, 0.0, relative.field_72449_c);
        double dot = relative.func_72430_b(dir);
        double cosSq = cos * cos;
        double value = dot * dot / relative.func_189985_c();
        if (arc < 180.0 && dot > 0.0 && value >= cosSq) {
            return true;
        }
        return !(arc < 180.0 || dot <= 0.0 && dot > cosSq);
    }

    @SubscribeEvent
    public void preRenderPlayerEvent(RenderPlayerEvent.Pre event) {
        if (event.isCancelable() && event.getEntityPlayer().equals((Object)Minecraft.func_71410_x().field_71439_g) && Minecraft.func_71410_x().func_175598_ae().func_178627_a() && ShoulderState.getCameraDistance() < 1.6 && Config.CLIENT.keepCameraOutOfHead() && ShoulderState.doShoulderSurfing()) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent(priority=EventPriority.HIGHEST, receiveCanceled=true)
    public void preRenderGameOverlayEvent(RenderGameOverlayEvent.Pre event) {
        if (event.getType().equals((Object)RenderGameOverlayEvent.ElementType.CROSSHAIRS)) {
            event.setCanceled(true);
            Minecraft.func_71410_x().func_110434_K().func_110577_a(Gui.field_110324_m);
            GlStateManager.func_179147_l();
            this.renderAttackIndicator(event.getPartialTicks(), event.getResolution());
        }
    }

    protected void renderAttackIndicator(float partialTicks, ScaledResolution p_184045_2_) {
        Minecraft mc = Minecraft.func_71410_x();
        GameSettings gamesettings = mc.field_71474_y;
        if (gamesettings.field_74320_O == 0 || Perspective.current() == Perspective.SHOULDER_SURFING) {
            if (mc.field_71442_b.func_78747_a() && mc.field_147125_j == null) {
                RayTraceResult raytraceresult = mc.field_71476_x;
                if (raytraceresult == null || raytraceresult.field_72313_a != RayTraceResult.Type.BLOCK) {
                    return;
                }
                BlockPos blockpos = raytraceresult.func_178782_a();
                IBlockState state = mc.field_71441_e.func_180495_p(blockpos);
                if (!state.func_177230_c().hasTileEntity(state) || !(mc.field_71441_e.func_175625_s(blockpos) instanceof IInventory)) {
                    return;
                }
            }
            int l = p_184045_2_.func_78326_a();
            int i1 = p_184045_2_.func_78328_b();
            if (gamesettings.field_74330_P && !gamesettings.field_74319_N && !mc.field_71439_g.func_175140_cp() && !gamesettings.field_178879_v) {
                GlStateManager.func_179094_E();
                GlStateManager.func_179109_b((float)(l / 2), (float)(i1 / 2), (float)-90.0f);
                Entity entity = mc.func_175606_aa();
                GlStateManager.func_179114_b((float)(entity.field_70127_C + (entity.field_70125_A - entity.field_70127_C) * partialTicks), (float)-1.0f, (float)0.0f, (float)0.0f);
                GlStateManager.func_179114_b((float)(entity.field_70126_B + (entity.field_70177_z - entity.field_70126_B) * partialTicks), (float)0.0f, (float)1.0f, (float)0.0f);
                GlStateManager.func_179152_a((float)-1.0f, (float)-1.0f, (float)-1.0f);
                OpenGlHelper.func_188785_m((int)10);
                GlStateManager.func_179121_F();
            } else {
                GlStateManager.func_187428_a((GlStateManager.SourceFactor)GlStateManager.SourceFactor.ONE, (GlStateManager.DestFactor)GlStateManager.DestFactor.ZERO, (GlStateManager.SourceFactor)GlStateManager.SourceFactor.ONE, (GlStateManager.DestFactor)GlStateManager.DestFactor.ZERO);
                GlStateManager.func_179141_d();
                GuiUtils.drawTexturedModalRect((int)(l / 2 - 7), (int)(i1 / 2 - 7), (int)0, (int)0, (int)16, (int)16, (float)-90.0f);
            }
        }
    }

    @SubscribeEvent
    public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
        Config.CLIENT.sync();
    }
}

