/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.entity.AbstractClientPlayer
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.util.math.Vec3d
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package goblinbob.mobends.standard.client.model.items;

import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(value=Side.CLIENT)
public class ModelBendsElytra
extends ModelBase {
    private ModelRenderer field_187060_a;
    private ModelRenderer field_187061_b = new ModelRenderer((ModelBase)this, 22, 0);

    public ModelBendsElytra() {
        this.field_187061_b.func_78790_a(-10.0f, 0.0f, 0.0f, 10, 20, 2, 1.0f);
        this.field_187060_a = new ModelRenderer((ModelBase)this, 22, 0);
        this.field_187060_a.field_78809_i = true;
        this.field_187060_a.func_78790_a(0.0f, 0.0f, 0.0f, 10, 20, 2, 1.0f);
    }

    public void func_78088_a(Entity entityIn, float p_78088_2_, float limbSwing, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        GlStateManager.func_179101_C();
        GlStateManager.func_179129_p();
        this.field_187061_b.func_78785_a(scale);
        this.field_187060_a.func_78785_a(scale);
    }

    public void func_78087_a(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
        super.func_78087_a(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);
        float f2 = 0.2617994f;
        float f1 = -0.2617994f;
        float f22 = 0.0f;
        float f3 = 0.0f;
        if (entityIn instanceof EntityLivingBase && ((EntityLivingBase)entityIn).func_184613_cA()) {
            float f4 = 1.0f;
            if (entityIn.field_70181_x < 0.0) {
                Vec3d vec3d = new Vec3d(entityIn.field_70159_w, entityIn.field_70181_x, entityIn.field_70179_y).func_72432_b();
                f4 = 1.0f - (float)Math.pow(-vec3d.field_72448_b, 1.5);
            }
            f2 = f4 * 0.34906584f + (1.0f - f4) * f2;
            f1 = f4 * -1.5707964f + (1.0f - f4) * f1;
        }
        this.field_187061_b.field_78800_c = 5.0f;
        this.field_187061_b.field_78797_d = f22;
        if (entityIn instanceof AbstractClientPlayer) {
            AbstractClientPlayer abstractclientplayer = (AbstractClientPlayer)entityIn;
            abstractclientplayer.field_184835_a = (float)((double)abstractclientplayer.field_184835_a + (double)(f2 - abstractclientplayer.field_184835_a) * 0.1);
            abstractclientplayer.field_184836_b = (float)((double)abstractclientplayer.field_184836_b + (double)(f3 - abstractclientplayer.field_184836_b) * 0.1);
            abstractclientplayer.field_184837_c = (float)((double)abstractclientplayer.field_184837_c + (double)(f1 - abstractclientplayer.field_184837_c) * 0.1);
            this.field_187061_b.field_78795_f = abstractclientplayer.field_184835_a;
            this.field_187061_b.field_78796_g = abstractclientplayer.field_184836_b;
            this.field_187061_b.field_78808_h = abstractclientplayer.field_184837_c;
        } else {
            this.field_187061_b.field_78795_f = f2;
            this.field_187061_b.field_78808_h = f1;
            this.field_187061_b.field_78796_g = f3;
        }
        this.field_187060_a.field_78800_c = -this.field_187061_b.field_78800_c;
        this.field_187060_a.field_78796_g = -this.field_187061_b.field_78796_g;
        this.field_187060_a.field_78797_d = this.field_187061_b.field_78797_d;
        this.field_187060_a.field_78795_f = this.field_187061_b.field_78795_f;
        this.field_187060_a.field_78808_h = -this.field_187061_b.field_78808_h;
    }

    public void func_78086_a(EntityLivingBase entitylivingbaseIn, float p_78086_2_, float p_78086_3_, float partialTickTime) {
        super.func_78086_a(entitylivingbaseIn, p_78086_2_, p_78086_3_, partialTickTime);
    }
}

