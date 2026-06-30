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
            Minecraft minecraft = Minecraft.getMinecraft();
            this.getMouseOver(Minecraft.getMinecraft().getRenderPartialTicks());
            if (minecraft.player != null) {
                Vec3d direction;
                Vec3d vec3d = direction = this.objectMouseOver != null ? this.objectMouseOver.hitVec : InjectionDelegation.shoulderSurfingLook(40.0).getValue();
                if (this.pointedEntityPos != null) {
                    NetworkManager.sendDirection(direction.x, direction.y, direction.z, true, this.pointedEntityPos.x, this.pointedEntityPos.y, this.pointedEntityPos.z);
                } else {
                    NetworkManager.sendDirection(direction.x, direction.y, direction.z, false, 0.0, 0.0, 0.0);
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
        Minecraft mc = Minecraft.getMinecraft();
        final Entity entity = mc.getRenderViewEntity();
        if (entity != null && mc.world != null) {
            double d0 = 40.0;
            this.pointedEntityPos = null;
            this.objectMouseOver = InjectionDelegation.rayTrace1(entity, d0, partialTicks);
            Vec3d vec3d = entity.getPositionEyes(partialTicks);
            double d1 = d0;
            if (this.objectMouseOver != null) {
                d1 = this.objectMouseOver.hitVec.distanceTo(vec3d);
            }
            Vec3d vec3d1 = entity.getLook(1.0f);
            Map.Entry<Vec3d, Vec3d> entry = InjectionDelegation.shoulderSurfingLook(d0);
            Entity pointedEntity = null;
            Vec3d pointedEntityPos = null;
            AxisAlignedBB grow = entity.getEntityBoundingBox().expand(vec3d1.x * d0, vec3d1.y * d0, vec3d1.z * d0).grow(1.0, 1.0, 1.0);
            List list = mc.world.getEntitiesInAABBexcluding(entity, grow, Predicates.and((Predicate)EntitySelectors.NOT_SPECTATING, (Predicate)new Predicate<Entity>(){

                public boolean apply(Entity p_apply_1_) {
                    if (p_apply_1_ == null || !p_apply_1_.canBeCollidedWith() || p_apply_1_ instanceof EntityArmorStand || p_apply_1_ instanceof EntityVex) {
                        return false;
                    }
                    return ClientEventHandler.isInFront(entity, p_apply_1_.getPositionVector());
                }
            }));
            double d2 = d1;
            for (Entity entity1 : list) {
                double d3;
                AxisAlignedBB axisalignedbb = entity1.getEntityBoundingBox().grow((double)entity1.getCollisionBorderSize());
                RayTraceResult raytraceresult = axisalignedbb.calculateIntercept(entry.getKey(), entry.getValue());
                if (axisalignedbb.contains(vec3d)) {
                    if (!(d2 >= 0.0)) continue;
                    pointedEntity = entity1;
                    pointedEntityPos = raytraceresult == null ? null : raytraceresult.hitVec;
                    d2 = 0.0;
                    continue;
                }
                if (raytraceresult == null || !((d3 = vec3d.distanceTo(raytraceresult.hitVec)) < d2) && d2 != 0.0) continue;
                if (entity1.getLowestRidingEntity() == entity.getLowestRidingEntity() && !entity1.canRiderInteract()) {
                    if (d2 != 0.0) continue;
                    pointedEntity = entity1;
                    pointedEntityPos = raytraceresult.hitVec;
                    continue;
                }
                pointedEntity = entity1;
                pointedEntityPos = raytraceresult.hitVec;
                d2 = d3;
            }
            if (pointedEntity != null && pointedEntityPos != null) {
                this.pointedEntityPos = pointedEntityPos;
            }
        }
    }

    public static boolean isInFront11(Entity entity, Vec3d target) {
        Vec3d dir = entity.getLookVec();
        Vec3d distance = target.subtract(entity.getPositionVector());
        distance = new Vec3d(distance.x, 0.0, distance.z);
        double dot = distance.dotProduct(dir);
        double cosSq = 0.25;
        double value = dot * dot / distance.lengthSquared();
        Minecraft.getMinecraft().player.sendMessage((ITextComponent)new TextComponentString(value + " " + dot));
        return dot > 0.0 && value >= cosSq;
    }

    public static boolean isInFront(Entity entity, Vec3d target) {
        Vec3d dir = entity.getLookVec();
        Vec3d distance = target.subtract(entity.getPositionVector());
        distance = new Vec3d(distance.x, 0.0, distance.z);
        double dot = distance.dotProduct(dir);
        double cosSq = 0.5;
        double value = dot * dot / distance.lengthSquared();
        return dot > 0.0 && value >= cosSq;
    }

    public static boolean isInFront(Entity entity, Entity target, double arc) {
        Vec3d dir = entity.getLookVec();
        double cos = Math.cos(Math.toRadians(arc));
        Vec3d relative = target.getPositionVector().subtract(entity.getPositionVector());
        relative = new Vec3d(relative.x, 0.0, relative.z);
        double dot = relative.dotProduct(dir);
        double cosSq = cos * cos;
        double value = dot * dot / relative.lengthSquared();
        if (arc < 180.0 && dot > 0.0 && value >= cosSq) {
            return true;
        }
        return !(arc < 180.0 || dot <= 0.0 && dot > cosSq);
    }

    @SubscribeEvent
    public void preRenderPlayerEvent(RenderPlayerEvent.Pre event) {
        if (event.isCancelable() && event.getEntityPlayer().equals((Object)Minecraft.getMinecraft().player) && Minecraft.getMinecraft().getRenderManager().isRenderShadow() && ShoulderState.getCameraDistance() < 1.6 && Config.CLIENT.keepCameraOutOfHead() && ShoulderState.doShoulderSurfing()) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent(priority=EventPriority.HIGHEST, receiveCanceled=true)
    public void preRenderGameOverlayEvent(RenderGameOverlayEvent.Pre event) {
        if (event.getType().equals((Object)RenderGameOverlayEvent.ElementType.CROSSHAIRS)) {
            event.setCanceled(true);
            Minecraft.getMinecraft().getTextureManager().bindTexture(Gui.ICONS);
            GlStateManager.enableBlend();
            this.renderAttackIndicator(event.getPartialTicks(), event.getResolution());
        }
    }

    protected void renderAttackIndicator(float partialTicks, ScaledResolution p_184045_2_) {
        Minecraft mc = Minecraft.getMinecraft();
        GameSettings gamesettings = mc.gameSettings;
        if (gamesettings.thirdPersonView == 0 || Perspective.current() == Perspective.SHOULDER_SURFING) {
            if (mc.playerController.isSpectator() && mc.pointedEntity == null) {
                RayTraceResult raytraceresult = mc.objectMouseOver;
                if (raytraceresult == null || raytraceresult.typeOfHit != RayTraceResult.Type.BLOCK) {
                    return;
                }
                BlockPos blockpos = raytraceresult.getBlockPos();
                IBlockState state = mc.world.getBlockState(blockpos);
                if (!state.getBlock().hasTileEntity(state) || !(mc.world.getTileEntity(blockpos) instanceof IInventory)) {
                    return;
                }
            }
            int l = p_184045_2_.getScaledWidth();
            int i1 = p_184045_2_.getScaledHeight();
            if (gamesettings.showDebugInfo && !gamesettings.hideGUI && !mc.player.hasReducedDebug() && !gamesettings.reducedDebugInfo) {
                GlStateManager.pushMatrix();
                GlStateManager.translate((float)(l / 2), (float)(i1 / 2), (float)-90.0f);
                Entity entity = mc.getRenderViewEntity();
                GlStateManager.rotate((float)(entity.prevRotationPitch + (entity.rotationPitch - entity.prevRotationPitch) * partialTicks), (float)-1.0f, (float)0.0f, (float)0.0f);
                GlStateManager.rotate((float)(entity.prevRotationYaw + (entity.rotationYaw - entity.prevRotationYaw) * partialTicks), (float)0.0f, (float)1.0f, (float)0.0f);
                GlStateManager.scale((float)-1.0f, (float)-1.0f, (float)-1.0f);
                OpenGlHelper.renderDirections((int)10);
                GlStateManager.popMatrix();
            } else {
                GlStateManager.tryBlendFuncSeparate((GlStateManager.SourceFactor)GlStateManager.SourceFactor.ONE, (GlStateManager.DestFactor)GlStateManager.DestFactor.ZERO, (GlStateManager.SourceFactor)GlStateManager.SourceFactor.ONE, (GlStateManager.DestFactor)GlStateManager.DestFactor.ZERO);
                GlStateManager.enableAlpha();
                GuiUtils.drawTexturedModalRect((int)(l / 2 - 7), (int)(i1 / 2 - 7), (int)0, (int)0, (int)16, (int)16, (float)-90.0f);
            }
        }
    }

    @SubscribeEvent
    public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
        Config.CLIENT.sync();
    }
}

