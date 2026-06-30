/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.util.math.Vec3d
 */
package eos.moe.dragoncore.mixins;

import eos.moe.dragoncore.EntityList;
import eos.moe.dragoncore.ModelLocator;
import eos.moe.dragoncore.dt;
import eos.moe.dragoncore.gg;
import eos.moe.dragoncore.jv;
import eos.moe.dragoncore.kba;
import eos.moe.dragoncore.ll;
import eos.moe.dragoncore.mt;
import eos.moe.dragoncore.nk;
import eos.moe.dragoncore.oka;
import eos.moe.dragoncore.pw;
import eos.moe.dragoncore.raa;
import eos.moe.dragoncore.rda;
import eos.moe.dragoncore.ri;
import eos.moe.dragoncore.xz;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value={RenderManager.class})
public abstract class MixinRenderManager {
    @Shadow
    private double field_78725_b;
    @Shadow
    private double field_78726_c;
    @Shadow
    private double field_78723_d;

    public MixinRenderManager() {
        MixinRenderManager a2;
    }

    @Shadow
    public abstract void func_188391_a(Entity var1, double var2, double var4, double var6, float var8, float var9, boolean var10);

    @Redirect(method={"renderEntityStatic"}, at=@At(value="INVOKE", target="Lnet/minecraft/client/renderer/entity/RenderManager;renderEntity(Lnet/minecraft/entity/Entity;DDDFFZ)V"))
    private /* synthetic */ void mixin_renderEntityStatic(RenderManager a2, Entity a3, double a4, double a5, double a6, float a7, float a8, boolean a9) {
        pw a10;
        rda a11;
        Entity a12;
        MixinRenderManager a13;
        boolean a14 = true;
        if (a3 instanceof EntityLivingBase && kba.ALLATORIxDEMO.containsKey(a3.func_110124_au())) {
            double a15;
            double a16;
            double a17;
            oka a18 = kba.ALLATORIxDEMO.get(a3.func_110124_au());
            EntityLivingBase a19 = EntityList.getLivingEntityByUUID(a18.ALLATORIxDEMO());
            if (a19 == null) {
                return;
            }
            EntityLivingBase a20 = (EntityLivingBase)a3;
            if (a18.f() == 0.0f && a18.ALLATORIxDEMO() == 0.0f) {
                a17 = a19.field_70142_S + (a19.field_70165_t - a19.field_70142_S) * (double)a8;
                a16 = a19.field_70137_T + (a19.field_70163_u - a19.field_70137_T) * (double)a8 + (double)a18.c();
                a15 = a19.field_70136_U + (a19.field_70161_v - a19.field_70136_U) * (double)a8;
            } else {
                Vec3d a21 = new Vec3d((double)a18.f(), 0.0, (double)a18.ALLATORIxDEMO());
                float a22 = ri.ALLATORIxDEMO(a19.field_70760_ar, a19.field_70761_aq, a8);
                Vec3d a23 = a21.func_178785_b(-a22 * ((float)Math.PI / 180) - 1.5707964f);
                a17 = a19.field_70142_S + (a19.field_70165_t - a19.field_70142_S) * (double)a8 + a23.field_72450_a;
                a16 = a19.field_70137_T + (a19.field_70163_u - a19.field_70137_T) * (double)a8 + (double)a18.c();
                a15 = a19.field_70136_U + (a19.field_70161_v - a19.field_70136_U) * (double)a8 + a23.field_72449_c;
            }
            if (a18.c()) {
                a20.field_70760_ar = a19.field_70760_ar;
                a20.field_70761_aq = a19.field_70761_aq;
                a20.field_70758_at = a19.field_70758_at;
                a20.field_70759_as = a19.field_70759_as;
            }
            if (a18.ALLATORIxDEMO()) {
                a20.field_70127_C = a19.field_70127_C;
                a20.field_70125_A = a19.field_70125_A;
            }
            float a24 = a3.field_70126_B + (a3.field_70177_z - a3.field_70126_B) * a8;
            a13.func_188391_a(a3, a17 - a13.field_78725_b, a16 - a13.field_78726_c, a15 - a13.field_78723_d, a24, a8, a9);
            a14 = false;
        } else if (a3.func_184187_bx() != null && (a12 = a3.func_184187_bx()) instanceof EntityLivingBase && (a11 = raa.r.c((EntityLivingBase)a12)) != null && a11.ALLATORIxDEMO().ALLATORIxDEMO() instanceof pw && (a10 = (pw)a11.ALLATORIxDEMO().ALLATORIxDEMO()) != null && a10.getBaseModel().getLocatorMap() != null && a10.getBaseModel().getLocatorMap().containsKey("mount")) {
            ModelLocator a25;
            jv a26;
            mt a27;
            xz a28 = dt.k.getEntityManager(a12.func_110124_au());
            if (a28 != null) {
                a28.ALLATORIxDEMO(a10);
            }
            if ((a27 = (a26 = a10.getBaseModel()).getPiece((a25 = a26.getLocatorMap().get("mount")).getBone())) != null) {
                nk a29 = new nk();
                a29.c();
                float a30 = ri.ALLATORIxDEMO((EntityLivingBase)a12, a8);
                a29.ALLATORIxDEMO(gg.q.ALLATORIxDEMO(a30));
                a27.position(a29, 0.0625f);
                a27.backPosition(a29, 0.0625f);
                a29.ALLATORIxDEMO((double)a25.getOffsetX(), (double)(-a25.getOffsetY()), (double)a25.getOffsetZ());
                ll a31 = new ll(0.0f, 0.0f, 0.0f, 1.0f);
                a31.ALLATORIxDEMO(a29);
                a13.func_188391_a(a3, a4 + (double)a31.x(), a5 - (double)a31.f() - (double)a12.field_70131_O * 0.75, a6 - (double)a31.c(), a7, a8, a9);
                a14 = false;
            }
            a10.clearData();
        }
        if (a14) {
            a13.func_188391_a(a3, a4, a5, a6, a7, a8, a9);
        }
    }
}

