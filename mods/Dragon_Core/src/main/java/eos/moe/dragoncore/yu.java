/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.BlockLadder
 *  net.minecraft.block.BlockVine
 *  net.minecraft.block.properties.IProperty
 *  net.minecraft.block.state.IBlockState
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.item.EnumAction
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.EnumFacing
 *  net.minecraft.util.math.BlockPos
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.gt;
import eos.moe.dragoncore.ls;
import net.minecraft.block.BlockLadder;
import net.minecraft.block.BlockVine;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class yu<E extends EntityLivingBase>
extends gt<E> {
    public float c = 100.0f;
    public float q = 100.0f;
    public float b = 100.0f;
    public float o = 100.0f;
    public float y = 0.0f;
    public boolean k = false;
    public boolean ALLATORIxDEMO = false;

    public yu(E a2) {
        super(a2);
        yu a3;
    }

    public void f(boolean a2) {
        a.ALLATORIxDEMO = a2;
    }

    public float s() {
        yu a2;
        return a2.y;
    }

    public float w() {
        yu a2;
        return a2.c;
    }

    public float z() {
        yu a2;
        return a2.q;
    }

    public float k() {
        yu a2;
        return a2.b;
    }

    public float d() {
        yu a2;
        return a2.o;
    }

    public boolean s() {
        yu a2;
        return a2.ALLATORIxDEMO;
    }

    @Override
    public void ALLATORIxDEMO() {
        yu a2;
        super.ALLATORIxDEMO();
        boolean a3 = a2.k();
        if (a3 & a2.o == false) {
            a2.k();
            a2.o = 1.0f;
        }
        if (((!a3 ? 1 : 0) & a2.o | (a2.v <= 0.0 && a2.q - a2.v > 0.4 && a2.c > 2.0f ? 1 : 0)) != 0) {
            a2.d();
            a2.o = 0.0f;
        }
        if (a2.w()) {
            a2.y = (float)((double)a2.y + a2.q * (double)2.6f);
            a2.ALLATORIxDEMO = true;
        } else {
            a2.ALLATORIxDEMO = false;
        }
        if (((EntityLivingBase)a2.s).isSwingInProgress) {
            if (!a2.k || a2.b > 5.0f) {
                a2.x();
                a2.k = true;
            }
        } else {
            a2.k = false;
        }
    }

    @Override
    public void ALLATORIxDEMO(float a2) {
        yu a3;
        if (a3.x()) {
            a3.q += ls.ALLATORIxDEMO;
        } else {
            a3.c += ls.ALLATORIxDEMO;
            a3.o = a3.q < 0.0 ? (a3.o += ls.ALLATORIxDEMO) : 0.0f;
        }
        a3.b += ls.ALLATORIxDEMO;
    }

    public void k() {
        a.q = 0.0f;
        a.o = 0.0f;
    }

    public void d() {
        a.c = 0.0f;
    }

    public void x() {
        a.b = 0.0f;
    }

    public float x() {
        yu a2;
        return a2.ALLATORIxDEMO().getHorizontalAngle() + 180.0f;
    }

    @Override
    public EnumFacing ALLATORIxDEMO() {
        yu a2;
        BlockPos a3 = new BlockPos(Math.floor(((EntityLivingBase)a2.s).posX), Math.floor(((EntityLivingBase)a2.s).posY), Math.floor(((EntityLivingBase)a2.s).posZ));
        IBlockState a4 = ((EntityLivingBase)a2.s).world.getBlockState(a3);
        IBlockState a5 = ((EntityLivingBase)a2.s).world.getBlockState(a3.add(0, -1, 0));
        IBlockState a6 = ((EntityLivingBase)a2.s).world.getBlockState(a3.add(0, -2, 0));
        if (a4.getBlock() instanceof BlockLadder) {
            return (EnumFacing)a4.getValue((IProperty)BlockLadder.FACING);
        }
        if (a5.getBlock() instanceof BlockLadder) {
            return (EnumFacing)a5.getValue((IProperty)BlockLadder.FACING);
        }
        if (a6.getBlock() instanceof BlockLadder) {
            return (EnumFacing)a6.getValue((IProperty)BlockLadder.FACING);
        }
        return EnumFacing.NORTH;
    }

    public boolean w() {
        yu a2;
        if (a2.s == null || ((EntityLivingBase)a2.s).world == null) {
            return false;
        }
        BlockPos a3 = new BlockPos(Math.floor(((EntityLivingBase)a2.s).posX), Math.floor(((EntityLivingBase)a2.s).posY), Math.floor(((EntityLivingBase)a2.s).posZ));
        IBlockState a4 = ((EntityLivingBase)a2.s).world.getBlockState(a3);
        IBlockState a5 = ((EntityLivingBase)a2.s).world.getBlockState(a3.add(0, -1, 0));
        IBlockState a6 = ((EntityLivingBase)a2.s).world.getBlockState(a3.add(0, -2, 0));
        if (!((EntityLivingBase)a2.s).isOnLadder() || a2.x()) {
            return false;
        }
        boolean a7 = a4.getBlock() instanceof BlockLadder || a5.getBlock() instanceof BlockLadder || a6.getBlock() instanceof BlockLadder;
        boolean a8 = a4.getBlock() instanceof BlockVine || a5.getBlock() instanceof BlockVine || a6.getBlock() instanceof BlockVine;
        return a7 || a8;
    }

    public float c() {
        yu a2;
        float a3 = (float)(((EntityLivingBase)a2.s).posY + (((EntityLivingBase)a2.s).posY - ((EntityLivingBase)a2.s).prevPosY) * (double)ls.y);
        BlockPos a4 = new BlockPos(Math.floor(((EntityLivingBase)a2.s).posX), Math.floor(((EntityLivingBase)a2.s).posY), Math.floor(((EntityLivingBase)a2.s).posZ));
        IBlockState a5 = ((EntityLivingBase)a2.s).world.getBlockState(a4.add(0, 2, 0));
        IBlockState a6 = ((EntityLivingBase)a2.s).world.getBlockState(a4.add(0, 1, 0));
        IBlockState a7 = ((EntityLivingBase)a2.s).world.getBlockState(a4.add(0, 0, 0));
        if (!(a5.getBlock() instanceof BlockLadder)) {
            if (!(a6.getBlock() instanceof BlockLadder)) {
                if (!(a7.getBlock() instanceof BlockLadder)) {
                    return a3 - (float)((int)a3) + 2.0f;
                }
                return a3 - (float)((int)a3) + 1.0f;
            }
            return a3 - (float)((int)a3);
        }
        return -2.0f;
    }

    public boolean z() {
        yu a2;
        if (((EntityLivingBase)a2.s).getItemInUseCount() > 0) {
            ItemStack a3 = ((EntityLivingBase)a2.s).getHeldItemMainhand();
            ItemStack a4 = ((EntityLivingBase)a2.s).getHeldItemOffhand();
            if (!a3.isEmpty() && a3.getItemUseAction() == EnumAction.BOW || !a4.isEmpty() && a4.getItemUseAction() == EnumAction.BOW) {
                return true;
            }
        }
        return false;
    }

    @Override
    public E ALLATORIxDEMO() {
        yu a2;
        return (E)((EntityLivingBase)a2.s);
    }
}

