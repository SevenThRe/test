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
package goblinbob.mobends.core.data;

import goblinbob.mobends.core.client.event.DataUpdateHandler;
import goblinbob.mobends.core.data.EntityData;
import goblinbob.mobends.core.data.OverridableProperty;
import net.minecraft.block.BlockLadder;
import net.minecraft.block.BlockVine;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;

public abstract class LivingEntityData<E extends EntityLivingBase>
extends EntityData<E> {
    protected float ticksInAir = 100.0f;
    protected float ticksAfterTouchdown = 100.0f;
    protected float ticksAfterAttack = 100.0f;
    protected float ticksFalling = 100.0f;
    protected float climbingCycle = 0.0f;
    protected boolean alreadyAttacked = false;
    protected boolean climbing = false;
    public OverridableProperty<Float> limbSwing = new OverridableProperty<Float>(Float.valueOf(0.0f));
    public OverridableProperty<Float> limbSwingAmount = new OverridableProperty<Float>(Float.valueOf(0.0f));
    public OverridableProperty<Float> swingProgress = new OverridableProperty<Float>(Float.valueOf(0.0f));
    public OverridableProperty<Float> headYaw = new OverridableProperty<Float>(Float.valueOf(0.0f));
    public OverridableProperty<Float> headPitch = new OverridableProperty<Float>(Float.valueOf(0.0f));

    public LivingEntityData(E entity) {
        super(entity);
    }

    public void setClimbing(boolean flag) {
        this.climbing = flag;
    }

    public float getClimbingCycle() {
        return this.climbingCycle;
    }

    public float getTicksInAir() {
        return this.ticksInAir;
    }

    public float getTicksAfterTouchdown() {
        return this.ticksAfterTouchdown;
    }

    public float getTicksAfterAttack() {
        return this.ticksAfterAttack;
    }

    public float getTicksFalling() {
        return this.ticksFalling;
    }

    public boolean isClimbing() {
        return this.climbing;
    }

    @Override
    public void updateClient() {
        super.updateClient();
        boolean calcOnGroundResult = this.calcOnGround();
        if (calcOnGroundResult & !this.onGround) {
            this.onTouchdown();
            this.onGround = true;
        }
        if (!calcOnGroundResult & this.onGround | (this.prevMotionY <= 0.0 && this.motionY - this.prevMotionY > 0.4 && this.ticksInAir > 2.0f)) {
            this.onLiftoff();
            this.onGround = false;
        }
        if (this.calcClimbing()) {
            this.climbingCycle = (float)((double)this.climbingCycle + this.motionY * (double)2.6f);
            this.climbing = true;
        } else {
            this.climbing = false;
        }
        if (((EntityLivingBase)this.entity).field_82175_bq) {
            if (!this.alreadyAttacked || this.ticksAfterAttack > 5.0f) {
                this.onAttack();
                this.alreadyAttacked = true;
            }
        } else {
            this.alreadyAttacked = false;
        }
    }

    @Override
    public void update(float partialTicks) {
        super.update(partialTicks);
        if (this.isOnGround()) {
            this.ticksAfterTouchdown += DataUpdateHandler.ticksPerFrame;
        } else {
            this.ticksInAir += DataUpdateHandler.ticksPerFrame;
            this.ticksFalling = this.motionY < 0.0 ? (this.ticksFalling += DataUpdateHandler.ticksPerFrame) : 0.0f;
        }
        this.ticksAfterAttack += DataUpdateHandler.ticksPerFrame;
    }

    public void onTouchdown() {
        this.ticksAfterTouchdown = 0.0f;
        this.ticksFalling = 0.0f;
    }

    public void onLiftoff() {
        this.ticksInAir = 0.0f;
    }

    public void onAttack() {
        this.ticksAfterAttack = 0.0f;
    }

    public float getClimbingRotation() {
        return this.getLadderFacing().func_185119_l() + 180.0f;
    }

    private static boolean isBlockClimbable(IBlockState state) {
        return state.func_177230_c() instanceof BlockLadder || state.func_177230_c() instanceof BlockVine;
    }

    private static EnumFacing getClimbableBlockFacing(IBlockState state) {
        if (state.func_177230_c() instanceof BlockLadder) {
            return (EnumFacing)state.func_177229_b((IProperty)BlockLadder.field_176382_a);
        }
        if (state.func_177230_c() instanceof BlockVine) {
            if (((Boolean)state.func_177229_b((IProperty)BlockVine.field_176278_M)).booleanValue()) {
                return EnumFacing.WEST;
            }
            if (((Boolean)state.func_177229_b((IProperty)BlockVine.field_176280_O)).booleanValue()) {
                return EnumFacing.EAST;
            }
            if (((Boolean)state.func_177229_b((IProperty)BlockVine.field_176273_b)).booleanValue()) {
                return EnumFacing.SOUTH;
            }
            if (((Boolean)state.func_177229_b((IProperty)BlockVine.field_176279_N)).booleanValue()) {
                return EnumFacing.NORTH;
            }
        }
        return EnumFacing.NORTH;
    }

    public EnumFacing getLadderFacing() {
        BlockPos position = new BlockPos(Math.floor(((EntityLivingBase)this.entity).field_70165_t), Math.floor(((EntityLivingBase)this.entity).field_70163_u), Math.floor(((EntityLivingBase)this.entity).field_70161_v));
        IBlockState block = ((EntityLivingBase)this.entity).field_70170_p.func_180495_p(position);
        IBlockState blockBelow = ((EntityLivingBase)this.entity).field_70170_p.func_180495_p(position.func_177982_a(0, -1, 0));
        IBlockState blockBelow2 = ((EntityLivingBase)this.entity).field_70170_p.func_180495_p(position.func_177982_a(0, -2, 0));
        EnumFacing facing = EnumFacing.NORTH;
        facing = LivingEntityData.getClimbableBlockFacing(block);
        if (facing == EnumFacing.NORTH) {
            facing = LivingEntityData.getClimbableBlockFacing(blockBelow);
        }
        if (facing == EnumFacing.NORTH) {
            facing = LivingEntityData.getClimbableBlockFacing(blockBelow2);
        }
        return facing;
    }

    public boolean calcClimbing() {
        if (this.entity == null || ((EntityLivingBase)this.entity).field_70170_p == null) {
            return false;
        }
        BlockPos position = new BlockPos(Math.floor(((EntityLivingBase)this.entity).field_70165_t), Math.floor(((EntityLivingBase)this.entity).field_70163_u), Math.floor(((EntityLivingBase)this.entity).field_70161_v));
        IBlockState block = ((EntityLivingBase)this.entity).field_70170_p.func_180495_p(position);
        IBlockState blockBelow = ((EntityLivingBase)this.entity).field_70170_p.func_180495_p(position.func_177982_a(0, -1, 0));
        IBlockState blockBelow2 = ((EntityLivingBase)this.entity).field_70170_p.func_180495_p(position.func_177982_a(0, -2, 0));
        return ((EntityLivingBase)this.entity).func_70617_f_() && !this.isOnGround() && (LivingEntityData.isBlockClimbable(block) || LivingEntityData.isBlockClimbable(blockBelow) || LivingEntityData.isBlockClimbable(blockBelow2));
    }

    public float getLedgeHeight() {
        float clientY = (float)(((EntityLivingBase)this.entity).field_70163_u + (((EntityLivingBase)this.entity).field_70163_u - ((EntityLivingBase)this.entity).field_70167_r) * (double)DataUpdateHandler.partialTicks);
        BlockPos position = new BlockPos(Math.floor(((EntityLivingBase)this.entity).field_70165_t), Math.floor(((EntityLivingBase)this.entity).field_70163_u), Math.floor(((EntityLivingBase)this.entity).field_70161_v));
        IBlockState block = ((EntityLivingBase)this.entity).field_70170_p.func_180495_p(position.func_177982_a(0, 2, 0));
        IBlockState blockBelow = ((EntityLivingBase)this.entity).field_70170_p.func_180495_p(position.func_177982_a(0, 1, 0));
        IBlockState blockBelow2 = ((EntityLivingBase)this.entity).field_70170_p.func_180495_p(position.func_177982_a(0, 0, 0));
        if (!LivingEntityData.isBlockClimbable(block)) {
            if (!LivingEntityData.isBlockClimbable(blockBelow)) {
                if (!LivingEntityData.isBlockClimbable(blockBelow2)) {
                    return clientY - (float)((int)clientY) + 2.0f;
                }
                return clientY - (float)((int)clientY) + 1.0f;
            }
            return clientY - (float)((int)clientY);
        }
        return -2.0f;
    }

    public boolean isDrawingBow() {
        if (((EntityLivingBase)this.entity).func_184605_cv() > 0) {
            ItemStack mainItemStack = ((EntityLivingBase)this.entity).func_184614_ca();
            ItemStack offItemStack = ((EntityLivingBase)this.entity).func_184592_cb();
            if (!mainItemStack.func_190926_b() && mainItemStack.func_77975_n() == EnumAction.BOW || !offItemStack.func_190926_b() && offItemStack.func_77975_n() == EnumAction.BOW) {
                return true;
            }
        }
        return false;
    }

    @Override
    public E getEntity() {
        return (E)((EntityLivingBase)this.entity);
    }
}

