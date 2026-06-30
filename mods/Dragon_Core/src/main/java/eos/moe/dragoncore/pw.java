/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.GlStateManager$DestFactor
 *  net.minecraft.client.renderer.GlStateManager$SourceFactor
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.math.MathHelper
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.api.model.AnimationEntityModel;
import eos.moe.dragoncore.jv;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class pw
extends ModelBase
implements AnimationEntityModel {
    private jv k;
    private Entity ALLATORIxDEMO;

    public pw(jv a2) {
        pw a3;
        a3.k = a2;
        a3.field_78092_r.addAll(a2.field_78092_r);
        a2.field_78092_r.clear();
    }

    public void func_78088_a(Entity a2, float a3, float a4, float a5, float a6, float a7, float a8) {
        pw a9;
        GlStateManager.func_179108_z();
        GlStateManager.func_179147_l();
        GlStateManager.func_187401_a((GlStateManager.SourceFactor)GlStateManager.SourceFactor.SRC_ALPHA, (GlStateManager.DestFactor)GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
        GlStateManager.func_179109_b((float)0.0f, (float)1.501f, (float)0.0f);
        a9.func_78087_a(a3, a4, a5, a6, a7, a8, a2);
        a9.k.render(a8);
        GlStateManager.func_179109_b((float)0.0f, (float)-1.501f, (float)0.0f);
        GlStateManager.func_179084_k();
        GlStateManager.func_179133_A();
    }

    public void clearData() {
        pw a2;
        a2.k.clearData();
    }

    public void func_78087_a(float a2, float a3, float a4, float a5, float a6, float a7, Entity a8) {
        pw a9;
        float a10 = 57.295776f;
        for (ModelRenderer a11 : a9.field_78092_r) {
            String a12 = a11.field_78802_n;
            if (a12.equalsIgnoreCase("core_head")) {
                a11.field_78796_g = a5 / 57.295776f * a10;
                a11.field_78795_f = a6 / 54.11268f * a10;
                continue;
            }
            if (a12.equalsIgnoreCase("core_human_right_arm")) {
                a11.field_78795_f = MathHelper.func_76134_b((float)(0.6662f * a2 + (float)Math.PI)) * 0.75f * a3 * a10;
                continue;
            }
            if (a12.equalsIgnoreCase("core_human_left_arm")) {
                a11.field_78795_f = MathHelper.func_76134_b((float)(0.6662f * a2)) * 0.75f * a3 * a10;
                continue;
            }
            if (a12.equalsIgnoreCase("core_human_right_leg")) {
                a11.field_78795_f = MathHelper.func_76134_b((float)(0.6662f * a2)) * 1.05f * a3 * a10;
                continue;
            }
            if (a12.equalsIgnoreCase("core_human_left_leg")) {
                a11.field_78795_f = MathHelper.func_76134_b((float)(0.6662f * a2 + (float)Math.PI)) * 1.05f * a3 * a10;
                continue;
            }
            if (a12.equalsIgnoreCase("core_beast_right_arm")) {
                a11.field_78795_f = MathHelper.func_76134_b((float)(a2 * 0.6662f)) * 1.4f * a3 * a10;
                continue;
            }
            if (a12.equalsIgnoreCase("core_beast_left_arm")) {
                a11.field_78795_f = MathHelper.func_76134_b((float)(a2 * 0.6662f + (float)Math.PI)) * 1.4f * a3 * a10;
                continue;
            }
            if (a12.equalsIgnoreCase("core_beast_right_leg")) {
                a11.field_78795_f = MathHelper.func_76134_b((float)(a2 * 0.6662f)) * 1.4f * a3 * a10;
                continue;
            }
            if (a12.equalsIgnoreCase("core_beast_left_leg")) {
                a11.field_78795_f = MathHelper.func_76134_b((float)(a2 * 0.6662f + (float)Math.PI)) * 1.4f * a3 * a10;
                continue;
            }
            if (a12.contains("core_tail")) {
                a11.field_78795_f = MathHelper.func_76134_b((float)(a2 * 0.6662f)) * 1.4f * a3 * a10;
                continue;
            }
            if (a12.equalsIgnoreCase("core_right_wing")) {
                a11.field_78796_g = MathHelper.func_76134_b((float)(a4 * 1.3f)) * (float)Math.PI * 0.25f * a10;
                continue;
            }
            if (a12.equalsIgnoreCase("core_left_wing")) {
                a11.field_78796_g = -MathHelper.func_76134_b((float)(a4 * 1.3f)) * (float)Math.PI * 0.25f * a10;
                continue;
            }
            if (a12.equalsIgnoreCase("core_right_wing_out")) {
                a11.field_78796_g = MathHelper.func_76134_b((float)(a4 * 1.3f)) * (float)Math.PI * 0.25f * 0.5f * a10;
                continue;
            }
            if (a12.equalsIgnoreCase("core_left_wing_out")) {
                a11.field_78796_g = -MathHelper.func_76134_b((float)(a4 * 1.3f)) * (float)Math.PI * 0.25f * 0.5f * a10;
                continue;
            }
            if (a8 == null || !a12.equalsIgnoreCase("core_shake")) continue;
            int a13 = a8.field_70170_p.field_73012_v.nextInt(3);
            if (a13 == 0) {
                a11.field_78795_f = MathHelper.func_76134_b((float)(a2 * 0.4444f)) * 1.4f * a3 * a10;
                continue;
            }
            if (a13 == 1) {
                a11.field_78808_h = MathHelper.func_76134_b((float)(a2 * 0.4444f)) * 1.4f * a3 * a10;
                continue;
            }
            a11.field_78796_g = MathHelper.func_76134_b((float)(a2 * 0.4444f)) * 1.4f * a3 * a10;
        }
    }

    @Override
    public jv getBaseModel() {
        pw a2;
        return a2.k;
    }

    @Override
    public Entity getEntity() {
        pw a2;
        return a2.ALLATORIxDEMO;
    }

    @Override
    public void setEntity(Entity a2) {
        a.ALLATORIxDEMO = a2;
    }
}

