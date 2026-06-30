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
        WorldClient world = Minecraft.getMinecraft().world;
        if (ShoulderState.doShoulderSurfing() && world != null) {
            Vec3d offset = new Vec3d(Config.CLIENT.getOffsetX(), -Config.CLIENT.getOffsetY(), -Config.CLIENT.getOffsetZ());
            double distance = ShoulderSurfingHelper.cameraDistance((World)world, offset.length(), yaw, pitch);
            Vec3d scaled = offset.normalize().scale(distance);
            ShoulderState.setCameraDistance(distance);
            GlStateManager.translate((double)scaled.x, (double)scaled.y, (double)scaled.z);
        } else {
            GlStateManager.translate((float)x, (float)y, (float)z);
        }
    }

    public static RayTraceResult getRayTraceResult(World world, Vec3d vec1, Vec3d vec2) {
        return world.rayTraceBlocks(vec1, vec2, false, true, false);
    }

    public static RayTraceResult rayTrace(Entity entity, double blockReachDistance, float partialTicks) {
        if (ShoulderState.doShoulderSurfing() && !Config.CLIENT.getCrosshairType().isHoldingSpecialItem()) {
            Map.Entry<Vec3d, Vec3d> look = ShoulderSurfingHelper.shoulderSurfingLook(entity, partialTicks, blockReachDistance * blockReachDistance);
            return entity.world.rayTraceBlocks(look.getKey(), look.getValue(), false, false, true);
        }
        Vec3d eyes = entity.getPositionEyes(partialTicks);
        Vec3d look2 = entity.getLook(partialTicks);
        Vec3d end = eyes.add(look2.x * blockReachDistance, look2.y * blockReachDistance, look2.z * blockReachDistance);
        return entity.world.rayTraceBlocks(eyes, end, false, false, true);
    }

    public static Map.Entry<Vec3d, Vec3d> shoulderSurfingLook(double blockReach) {
        if (ShoulderState.doShoulderSurfing() && !Config.CLIENT.getCrosshairType().isHoldingSpecialItem()) {
            return ShoulderSurfingHelper.shoulderSurfingLook(Minecraft.getMinecraft().getRenderViewEntity(), Minecraft.getMinecraft().getRenderPartialTicks(), blockReach * blockReach);
        }
        Entity renderView = Minecraft.getMinecraft().getRenderViewEntity();
        Vec3d look = renderView.getLook(1.0f);
        Vec3d start = renderView.getPositionEyes(Minecraft.getMinecraft().getRenderPartialTicks());
        Vec3d end = start.add(look.x * blockReach, look.y * blockReach, look.z * blockReach);
        return new AbstractMap.SimpleEntry<Vec3d, Vec3d>(start, end);
    }

    public static RayTraceResult rayTrace1(Entity entity, double blockReachDistance, float partialTicks) {
        Map.Entry<Vec3d, Vec3d> look = ShoulderSurfingHelper.shoulderSurfingLook(entity, partialTicks, blockReachDistance * blockReachDistance);
        return InjectionDelegation.rayTraceBlocks(entity, entity.world, look.getKey(), look.getValue(), false, false, true);
    }

    public static RayTraceResult rayTraceBlocks(Entity entity, World world, Vec3d start, Vec3d end, boolean stopOnLiquid, boolean ignoreBlockWithoutBoundingBox, boolean returnLastUncollidableBlock) {
        Vec3d facing = entity.getLook(1.0f);
        Vec3d pos = entity.getPositionVector();
        if (!(Double.isNaN(start.x) || Double.isNaN(start.y) || Double.isNaN(start.z))) {
            if (!(Double.isNaN(end.x) || Double.isNaN(end.y) || Double.isNaN(end.z))) {
                RayTraceResult raytraceresult;
                int startZ;
                int startY;
                int endX = MathHelper.floor((double)end.x);
                int endY = MathHelper.floor((double)end.y);
                int endZ = MathHelper.floor((double)end.z);
                int startX = MathHelper.floor((double)start.x);
                BlockPos blockpos = new BlockPos(startX, startY = MathHelper.floor((double)start.y), startZ = MathHelper.floor((double)start.z));
                IBlockState iblockstate = world.getBlockState(blockpos);
                Block block = iblockstate.getBlock();
                if (!(block instanceof BlockBush) && InjectionDelegation.isInFront(facing, pos, new Vec3d((double)startX, (double)startY, (double)startZ)) && (!ignoreBlockWithoutBoundingBox || iblockstate.getCollisionBoundingBox((IBlockAccess)world, blockpos) != Block.NULL_AABB) && block.canCollideCheck(iblockstate, stopOnLiquid) && (raytraceresult = iblockstate.collisionRayTrace(world, blockpos, start, end)) != null) {
                    return raytraceresult;
                }
                RayTraceResult raytraceresult2 = null;
                int k1 = 200;
                while (k1-- >= 0) {
                    IBlockState iblockstate1;
                    Block block1;
                    EnumFacing enumfacing;
                    if (Double.isNaN(start.x) || Double.isNaN(start.y) || Double.isNaN(start.z)) {
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
                    double d6 = end.x - start.x;
                    double d7 = end.y - start.y;
                    double d8 = end.z - start.z;
                    if (flag2) {
                        d3 = (d0 - start.x) / d6;
                    }
                    if (flag) {
                        d4 = (d1 - start.y) / d7;
                    }
                    if (flag1) {
                        d5 = (d2 - start.z) / d8;
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
                        start = new Vec3d(d0, start.y + d7 * d3, start.z + d8 * d3);
                    } else if (d4 < d5) {
                        enumfacing = endY > startY ? EnumFacing.DOWN : EnumFacing.UP;
                        start = new Vec3d(start.x + d6 * d4, d1, start.z + d8 * d4);
                    } else {
                        enumfacing = endZ > startZ ? EnumFacing.NORTH : EnumFacing.SOUTH;
                        start = new Vec3d(start.x + d6 * d5, start.y + d7 * d5, d2);
                    }
                    if ((block1 = (iblockstate1 = world.getBlockState(blockpos = new BlockPos(startX = MathHelper.floor((double)start.x) - (enumfacing == EnumFacing.EAST ? 1 : 0), startY = MathHelper.floor((double)start.y) - (enumfacing == EnumFacing.UP ? 1 : 0), startZ = MathHelper.floor((double)start.z) - (enumfacing == EnumFacing.SOUTH ? 1 : 0)))).getBlock()) instanceof BlockBush || !InjectionDelegation.isInFront(facing, pos, new Vec3d((double)startX, (double)startY, (double)startZ)) || ignoreBlockWithoutBoundingBox && iblockstate1.getMaterial() != Material.PORTAL && iblockstate1.getCollisionBoundingBox((IBlockAccess)world, blockpos) == Block.NULL_AABB) continue;
                    if (block1.canCollideCheck(iblockstate1, stopOnLiquid)) {
                        RayTraceResult raytraceresult1 = iblockstate1.collisionRayTrace(world, blockpos, start, end);
                        if (raytraceresult1 == null || !InjectionDelegation.isInFront1(facing, pos, raytraceresult1.hitVec)) continue;
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
        Vec3d relative = target.subtract(pos);
        relative = new Vec3d(relative.x, 0.0, relative.z);
        double dot = relative.dotProduct(dir);
        double cosSq = 0.25;
        double value = dot * dot / relative.lengthSquared();
        return dot > 0.0 && value >= cosSq;
    }

    public static boolean isInFront(Vec3d dir, Vec3d pos, Vec3d target) {
        Vec3d relative = target.subtract(pos);
        relative = new Vec3d(relative.x, 0.0, relative.z);
        double dot = relative.dotProduct(dir);
        double cosSq = 0.25;
        double value = dot * dot / relative.lengthSquared();
        return dot > 0.0 && value >= cosSq;
    }
}

