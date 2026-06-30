/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.texture.TextureManager
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 */
package goblinbob.mobends.core.client;

import goblinbob.mobends.core.data.EntityData;
import goblinbob.mobends.core.math.Quaternion;
import goblinbob.mobends.core.util.GlHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;

public abstract class MutatedRenderer<T extends EntityLivingBase> {
    protected final float scale = 0.0625f;
    protected final TextureManager textureManager = Minecraft.func_71410_x().func_110434_K();
    private Runnable runnable;

    public void beforeRender(EntityData<T> data, T entity, float partialTicks) {
        double entityX = ((EntityLivingBase)entity).field_70169_q + (((EntityLivingBase)entity).field_70165_t - ((EntityLivingBase)entity).field_70169_q) * (double)partialTicks;
        double entityY = ((EntityLivingBase)entity).field_70167_r + (((EntityLivingBase)entity).field_70163_u - ((EntityLivingBase)entity).field_70167_r) * (double)partialTicks;
        double entityZ = ((EntityLivingBase)entity).field_70166_s + (((EntityLivingBase)entity).field_70161_v - ((EntityLivingBase)entity).field_70166_s) * (double)partialTicks;
        Entity viewEntity = Minecraft.func_71410_x().func_175606_aa();
        double viewX = entityX;
        double viewY = entityY;
        double viewZ = entityZ;
        if (viewEntity != null) {
            viewX = viewEntity.field_70169_q + (viewEntity.field_70165_t - viewEntity.field_70169_q) * (double)partialTicks;
            viewY = viewEntity.field_70167_r + (viewEntity.field_70163_u - viewEntity.field_70167_r) * (double)partialTicks;
            viewZ = viewEntity.field_70166_s + (viewEntity.field_70161_v - viewEntity.field_70166_s) * (double)partialTicks;
        }
        GlStateManager.func_179137_b((double)(entityX - viewX), (double)(entityY - viewY), (double)(entityZ - viewZ));
        GlStateManager.func_179114_b((float)(-MutatedRenderer.interpolateRotation(((EntityLivingBase)entity).field_70760_ar, ((EntityLivingBase)entity).field_70761_aq, partialTicks)), (float)0.0f, (float)1.0f, (float)0.0f);
        this.renderLocalAccessories(entity, data, partialTicks);
        float globalScale = entity.func_70631_g_() ? this.getChildScale() : 1.0f;
        GlStateManager.func_179109_b((float)(data.globalOffset.getX() * 0.0625f * globalScale), (float)(data.globalOffset.getY() * 0.0625f * globalScale), (float)(data.globalOffset.getZ() * 0.0625f * globalScale));
        GlStateManager.func_179137_b((double)0.0, (double)0.75, (double)-0.125);
        GlHelper.rotate(data.centerRotation.getSmooth());
        GlStateManager.func_179139_a((double)data.scale.x, (double)data.scale.y, (double)data.scale.z);
        GlStateManager.func_179137_b((double)0.0, (double)-0.75, (double)0.125);
        GlHelper.rotate(data.renderRotation.getSmooth());
        GlStateManager.func_179109_b((float)(data.localOffset.getX() * 0.0625f * globalScale), (float)(data.localOffset.getY() * 0.0625f * globalScale), (float)(data.localOffset.getZ() * 0.0625f * globalScale));
        this.transformLocally(entity, data, partialTicks);
        GlStateManager.func_179114_b((float)MutatedRenderer.interpolateRotation(((EntityLivingBase)entity).field_70760_ar, ((EntityLivingBase)entity).field_70761_aq, partialTicks), (float)0.0f, (float)1.0f, (float)0.0f);
        GlStateManager.func_179137_b((double)(viewX - entityX), (double)(viewY - entityY), (double)(viewZ - entityZ));
        if (entity instanceof EntityPlayer) {
            double finalViewX = viewX;
            double finalViewY = viewY;
            double finalViewZ = viewZ;
            double finalViewX1 = viewX;
            double finalViewY1 = viewY;
            double finalViewZ1 = viewZ;
            Quaternion smooth = data.centerRotation.getSmooth();
            Quaternion quaternion = new Quaternion(smooth.x, smooth.y, smooth.z, smooth.w);
            quaternion.negate();
            this.runnable = () -> {
                GlStateManager.func_179137_b((double)(entityX - finalViewX), (double)(entityY - finalViewY), (double)(entityZ - finalViewZ));
                GlStateManager.func_179114_b((float)(-MutatedRenderer.interpolateRotation(entity.field_70760_ar, entity.field_70761_aq, partialTicks)), (float)0.0f, (float)1.0f, (float)0.0f);
                GlStateManager.func_179137_b((double)0.0, (double)0.75, (double)-0.125);
                GlHelper.rotate(quaternion);
                GlStateManager.func_179137_b((double)0.0, (double)-0.75, (double)0.125);
                GlStateManager.func_179114_b((float)MutatedRenderer.interpolateRotation(entity.field_70760_ar, entity.field_70761_aq, partialTicks), (float)0.0f, (float)1.0f, (float)0.0f);
                GlStateManager.func_179137_b((double)(finalViewX1 - entityX), (double)(finalViewY1 - entityY), (double)(finalViewZ1 - entityZ));
            };
        }
    }

    public void backtrackBeforeRender(EntityData<T> data, EntityPlayer entity, float partialTicks) {
        if (this.runnable != null) {
            this.runnable.run();
            this.runnable = null;
        }
    }

    public void afterRender(T entity, float partialTicks) {
    }

    protected void renderLocalAccessories(T entity, EntityData<?> data, float partialTicks) {
    }

    protected void transformLocally(T entity, EntityData<?> data, float partialTicks) {
    }

    protected static float interpolateRotation(float prevYawOffset, float yawOffset, float partialTicks) {
        float f2;
        for (f2 = yawOffset - prevYawOffset; f2 < -180.0f; f2 += 360.0f) {
        }
        while (f2 >= 180.0f) {
            f2 -= 360.0f;
        }
        return prevYawOffset + partialTicks * f2;
    }

    protected float getChildScale() {
        return 0.5f;
    }
}

