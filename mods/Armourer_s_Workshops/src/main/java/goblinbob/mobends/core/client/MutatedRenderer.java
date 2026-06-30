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
    protected final TextureManager textureManager = Minecraft.getMinecraft().getTextureManager();
    private Runnable runnable;

    public void beforeRender(EntityData<T> data, T entity, float partialTicks) {
        double entityX = ((EntityLivingBase)entity).prevPosX + (((EntityLivingBase)entity).posX - ((EntityLivingBase)entity).prevPosX) * (double)partialTicks;
        double entityY = ((EntityLivingBase)entity).prevPosY + (((EntityLivingBase)entity).posY - ((EntityLivingBase)entity).prevPosY) * (double)partialTicks;
        double entityZ = ((EntityLivingBase)entity).prevPosZ + (((EntityLivingBase)entity).posZ - ((EntityLivingBase)entity).prevPosZ) * (double)partialTicks;
        Entity viewEntity = Minecraft.getMinecraft().getRenderViewEntity();
        double viewX = entityX;
        double viewY = entityY;
        double viewZ = entityZ;
        if (viewEntity != null) {
            viewX = viewEntity.prevPosX + (viewEntity.posX - viewEntity.prevPosX) * (double)partialTicks;
            viewY = viewEntity.prevPosY + (viewEntity.posY - viewEntity.prevPosY) * (double)partialTicks;
            viewZ = viewEntity.prevPosZ + (viewEntity.posZ - viewEntity.prevPosZ) * (double)partialTicks;
        }
        GlStateManager.translate((double)(entityX - viewX), (double)(entityY - viewY), (double)(entityZ - viewZ));
        GlStateManager.rotate((float)(-MutatedRenderer.interpolateRotation(((EntityLivingBase)entity).prevRenderYawOffset, ((EntityLivingBase)entity).renderYawOffset, partialTicks)), (float)0.0f, (float)1.0f, (float)0.0f);
        this.renderLocalAccessories(entity, data, partialTicks);
        float globalScale = entity.isChild() ? this.getChildScale() : 1.0f;
        GlStateManager.translate((float)(data.globalOffset.getX() * 0.0625f * globalScale), (float)(data.globalOffset.getY() * 0.0625f * globalScale), (float)(data.globalOffset.getZ() * 0.0625f * globalScale));
        GlStateManager.translate((double)0.0, (double)0.75, (double)-0.125);
        GlHelper.rotate(data.centerRotation.getSmooth());
        GlStateManager.scale((double)data.scale.x, (double)data.scale.y, (double)data.scale.z);
        GlStateManager.translate((double)0.0, (double)-0.75, (double)0.125);
        GlHelper.rotate(data.renderRotation.getSmooth());
        GlStateManager.translate((float)(data.localOffset.getX() * 0.0625f * globalScale), (float)(data.localOffset.getY() * 0.0625f * globalScale), (float)(data.localOffset.getZ() * 0.0625f * globalScale));
        this.transformLocally(entity, data, partialTicks);
        GlStateManager.rotate((float)MutatedRenderer.interpolateRotation(((EntityLivingBase)entity).prevRenderYawOffset, ((EntityLivingBase)entity).renderYawOffset, partialTicks), (float)0.0f, (float)1.0f, (float)0.0f);
        GlStateManager.translate((double)(viewX - entityX), (double)(viewY - entityY), (double)(viewZ - entityZ));
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
                GlStateManager.translate((double)(entityX - finalViewX), (double)(entityY - finalViewY), (double)(entityZ - finalViewZ));
                GlStateManager.rotate((float)(-MutatedRenderer.interpolateRotation(entity.prevRenderYawOffset, entity.renderYawOffset, partialTicks)), (float)0.0f, (float)1.0f, (float)0.0f);
                GlStateManager.translate((double)0.0, (double)0.75, (double)-0.125);
                GlHelper.rotate(quaternion);
                GlStateManager.translate((double)0.0, (double)-0.75, (double)0.125);
                GlStateManager.rotate((float)MutatedRenderer.interpolateRotation(entity.prevRenderYawOffset, entity.renderYawOffset, partialTicks), (float)0.0f, (float)1.0f, (float)0.0f);
                GlStateManager.translate((double)(finalViewX1 - entityX), (double)(finalViewY1 - entityY), (double)(finalViewZ1 - entityZ));
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

