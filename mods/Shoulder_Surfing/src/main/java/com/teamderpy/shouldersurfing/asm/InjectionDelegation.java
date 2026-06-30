/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockBush
 *  net.minecraft.block.material.Material
 *  net.minecraft.block.state.IBlockState
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.multiplayer.WorldClient
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.EnumFacing
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.util.math.RayTraceResult
 *  net.minecraft.util.math.RayTraceResult$Type
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package com.teamderpy.shouldersurfing.asm;

import com.teamderpy.shouldersurfing.config.Config;
import com.teamderpy.shouldersurfing.util.ShoulderState;
import com.teamderpy.shouldersurfing.util.ShoulderSurfingHelper;
import java.util.AbstractMap;
import java.util.Map;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(value=Side.CLIENT)
public final class InjectionDelegation {
    public static void cameraSetup(float x, float y, float z, float yaw, float pitch) {
        WorldClient world = Minecraft.func_71410_x().field_71441_e;
        if (ShoulderState.doShoulderSurfing() && world != null) {
            Vec3d offset = new Vec3d(Config.CLIENT.getOffsetX(), -Config.CLIENT.getOffsetY(), -Config.CLIENT.getOffsetZ());
            double distance = ShoulderSurfingHelper.cameraDistance((World)world, offset.func_72433_c(), yaw, pitch);
            Vec3d scaled = offset.func_72432_b().func_186678_a(distance);
            ShoulderState.setCameraDistance(distance);
            GlStateManager.func_179137_b((double)scaled.field_72450_a, (double)scaled.field_72448_b, (double)scaled.field_72449_c);
        } else {
            GlStateManager.func_179109_b((float)x, (float)y, (float)z);
        }
    }

    public static RayTraceResult getRayTraceResult(World world, Vec3d vec1, Vec3d vec2) {
        return world.func_147447_a(vec1, vec2, false, true, false);
    }

    public static RayTraceResult rayTrace(Entity entity, double blockReachDistance, float partialTicks) {
        if (ShoulderState.doShoulderSurfing() && !Config.CLIENT.getCrosshairType().isHoldingSpecialItem()) {
            Map.Entry<Vec3d, Vec3d> look = ShoulderSurfingHelper.shoulderSurfingLook(entity, partialTicks, blockReachDistance * blockReachDistance);
            return entity.field_70170_p.func_147447_a(look.getKey(), look.getValue(), false, false, true);
        }
        Vec3d eyes = entity.func_174824_e(partialTicks);
        Vec3d look2 = entity.func_70676_i(partialTicks);
        Vec3d end = eyes.func_72441_c(look2.field_72450_a * blockReachDistance, look2.field_72448_b * blockReachDistance, look2.field_72449_c * blockReachDistance);
        return entity.field_70170_p.func_147447_a(eyes, end, false, false, true);
    }

    public static Map.Entry<Vec3d, Vec3d> shoulderSurfingLook(double blockReach) {
        if (ShoulderState.doShoulderSurfing() && !Config.CLIENT.getCrosshairType().isHoldingSpecialItem()) {
            return ShoulderSurfingHelper.shoulderSurfingLook(Minecraft.func_71410_x().func_175606_aa(), Minecraft.func_71410_x().func_184121_ak(), blockReach * blockReach);
        }
        Entity renderView = Minecraft.func_71410_x().func_175606_aa();
        Vec3d look = renderView.func_70676_i(1.0f);
        Vec3d start = renderView.func_174824_e(Minecraft.func_71410_x().func_184121_ak());
        Vec3d end = start.func_72441_c(look.field_72450_a * blockReach, look.field_72448_b * blockReach, look.field_72449_c * blockReach);
        return new AbstractMap.SimpleEntry<Vec3d, Vec3d>(start, end);
    }

    public static RayTraceResult rayTrace1(Entity entity, double blockReachDistance, float partialTicks) {
        Map.Entry<Vec3d, Vec3d> look = ShoulderSurfingHelper.shoulderSurfingLook(entity, partialTicks, blockReachDistance * blockReachDistance);
        return InjectionDelegation.rayTraceBlocks(entity, entity.field_70170_p, look.getKey(), look.getValue(), false, false, true);
    }

    public static RayTraceResult rayTraceBlocks(Entity entity, World world, Vec3d start, Vec3d end, boolean stopOnLiquid, boolean ignoreBlockWithoutBoundingBox, boolean returnLastUncollidableBlock) {
        Vec3d facing = entity.func_70676_i(1.0f);
        Vec3d pos = entity.func_174791_d();
        if (!(Double.isNaN(start.field_72450_a) || Double.isNaN(start.field_72448_b) || Double.isNaN(start.field_72449_c))) {
            if (!(Double.isNaN(end.field_72450_a) || Double.isNaN(end.field_72448_b) || Double.isNaN(end.field_72449_c))) {
                RayTraceResult raytraceresult;
                int startZ;
                int startY;
                int endX = MathHelper.func_76128_c((double)end.field_72450_a);
                int endY = MathHelper.func_76128_c((double)end.field_72448_b);
                int endZ = MathHelper.func_76128_c((double)end.field_72449_c);
                int startX = MathHelper.func_76128_c((double)start.field_72450_a);
                BlockPos blockpos = new BlockPos(startX, startY = MathHelper.func_76128_c((double)start.field_72448_b), startZ = MathHelper.func_76128_c((double)start.field_72449_c));
                IBlockState iblockstate = world.func_180495_p(blockpos);
                Block block = iblockstate.func_177230_c();
                if (!(block instanceof BlockBush) && InjectionDelegation.isInFront(facing, pos, new Vec3d((double)startX, (double)startY, (double)startZ)) && (!ignoreBlockWithoutBoundingBox || iblockstate.func_185890_d((IBlockAccess)world, blockpos) != Block.field_185506_k) && block.func_176209_a(iblockstate, stopOnLiquid) && (raytraceresult = iblockstate.func_185910_a(world, blockpos, start, end)) != null) {
                    return raytraceresult;
                }
                RayTraceResult raytraceresult2 = null;
                int k1 = 200;
                while (k1-- >= 0) {
                    IBlockState iblockstate1;
                    Block block1;
                    EnumFacing enumfacing;
                    if (Double.isNaN(start.field_72450_a) || Double.isNaN(start.field_72448_b) || Double.isNaN(start.field_72449_c)) {
                        return null;
                    }
                    if (startX == endX && startY == endY && startZ == endZ) {
                        return returnLastUncollidableBlock ? raytraceresult2 : null;
                    }
                    boolean flag2 = true;
                    boolean flag = true;
                    boolean flag1 = true;
                    double d0 = 999.0;
                    double d1 = 999.0;
                    double d2 = 999.0;
                    if (endX > startX) {
                        d0 = (double)startX + 1.0;
                    } else if (endX < startX) {
                        d0 = (double)startX + 0.0;
                    } else {
                        flag2 = false;
                    }
                    if (endY > startY) {
                        d1 = (double)startY + 1.0;
                    } else if (endY < startY) {
                        d1 = (double)startY + 0.0;
                    } else {
                        flag = false;
                    }
                    if (endZ > startZ) {
                        d2 = (double)startZ + 1.0;
                    } else if (endZ < startZ) {
                        d2 = (double)startZ + 0.0;
                    } else {
                        flag1 = false;
                    }
                    double d3 = 999.0;
                    double d4 = 999.0;
                    double d5 = 999.0;
                    double d6 = end.field_72450_a - start.field_72450_a;
                    double d7 = end.field_72448_b - start.field_72448_b;
                    double d8 = end.field_72449_c - start.field_72449_c;
                    if (flag2) {
                        d3 = (d0 - start.field_72450_a) / d6;
                    }
                    if (flag) {
                        d4 = (d1 - start.field_72448_b) / d7;
                    }
                    if (flag1) {
                        d5 = (d2 - start.field_72449_c) / d8;
                    }
                    if (d3 == -0.0) {
                        d3 = -1.0E-4;
                    }
                    if (d4 == -0.0) {
                        d4 = -1.0E-4;
                    }
                    if (d5 == -0.0) {
                        d5 = -1.0E-4;
                    }
                    if (d3 < d4 && d3 < d5) {
                        enumfacing = endX > startX ? EnumFacing.WEST : EnumFacing.EAST;
                        start = new Vec3d(d0, start.field_72448_b + d7 * d3, start.field_72449_c + d8 * d3);
                    } else if (d4 < d5) {
                        enumfacing = endY > startY ? EnumFacing.DOWN : EnumFacing.UP;
                        start = new Vec3d(start.field_72450_a + d6 * d4, d1, start.field_72449_c + d8 * d4);
                    } else {
                        enumfacing = endZ > startZ ? EnumFacing.NORTH : EnumFacing.SOUTH;
                        start = new Vec3d(start.field_72450_a + d6 * d5, start.field_72448_b + d7 * d5, d2);
                    }
                    if ((block1 = (iblockstate1 = world.func_180495_p(blockpos = new BlockPos(startX = MathHelper.func_76128_c((double)start.field_72450_a) - (enumfacing == EnumFacing.EAST ? 1 : 0), startY = MathHelper.func_76128_c((double)start.field_72448_b) - (enumfacing == EnumFacing.UP ? 1 : 0), startZ = MathHelper.func_76128_c((double)start.field_72449_c) - (enumfacing == EnumFacing.SOUTH ? 1 : 0)))).func_177230_c()) instanceof BlockBush || !InjectionDelegation.isInFront(facing, pos, new Vec3d((double)startX, (double)startY, (double)startZ)) || ignoreBlockWithoutBoundingBox && iblockstate1.func_185904_a() != Material.field_151567_E && iblockstate1.func_185890_d((IBlockAccess)world, blockpos) == Block.field_185506_k) continue;
                    if (block1.func_176209_a(iblockstate1, stopOnLiquid)) {
                        RayTraceResult raytraceresult1 = iblockstate1.func_185910_a(world, blockpos, start, end);
                        if (raytraceresult1 == null || !InjectionDelegation.isInFront1(facing, pos, raytraceresult1.field_72307_f)) continue;
                        return raytraceresult1;
                    }
                    raytraceresult2 = new RayTraceResult(RayTraceResult.Type.MISS, start, enumfacing, blockpos);
                }
                return returnLastUncollidableBlock ? raytraceresult2 : null;
            }
            return null;
        }
        return null;
    }

    public static boolean isInFront1(Vec3d dir, Vec3d pos, Vec3d target) {
        Vec3d relative = target.func_178788_d(pos);
        relative = new Vec3d(relative.field_72450_a, 0.0, relative.field_72449_c);
        double dot = relative.func_72430_b(dir);
        double cosSq = 0.25;
        double value = dot * dot / relative.func_189985_c();
        return dot > 0.0 && value >= cosSq;
    }

    public static boolean isInFront(Vec3d dir, Vec3d pos, Vec3d target) {
        Vec3d relative = target.func_178788_d(pos);
        relative = new Vec3d(relative.field_72450_a, 0.0, relative.field_72449_c);
        double dot = relative.func_72430_b(dir);
        double cosSq = 0.25;
        double value = dot * dot / relative.func_189985_c();
        return dot > 0.0 && value >= cosSq;
    }
}

