/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.RenderGlobal
 *  net.minecraft.client.renderer.culling.ICamera
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 */
package eos.moe.dragoncore.mixins;

import eos.moe.dragoncore.kba;
import eos.moe.dragoncore.raa;
import eos.moe.dragoncore.rda;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.renderer.culling.ICamera;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={RenderGlobal.class})
public class MixinRenderGlobal {
    @Shadow
    @Final
    private RenderManager renderManager;
    @Shadow
    private int countEntitiesRendered;

    public MixinRenderGlobal() {
        MixinRenderGlobal a2;
    }

    @Redirect(method={"renderEntities"}, at=@At(value="INVOKE", target="Lnet/minecraft/client/renderer/entity/RenderManager;shouldRender(Lnet/minecraft/entity/Entity;Lnet/minecraft/client/renderer/culling/ICamera;DDD)Z"))
    private /* synthetic */ boolean mixin_renderEntities(RenderManager a2, Entity a3, ICamera a4, double a5, double a6, double a7) {
        MixinRenderGlobal a8;
        if (a3 instanceof EntityLivingBase) {
            EntityLivingBase a9 = (EntityLivingBase)a3;
            if (kba.ALLATORIxDEMO.containsKey(a3.getUniqueID())) {
                return false;
            }
            rda a10 = raa.r.c(a9);
            if (a10 != null && a10.h()) {
                return false;
            }
        }
        return a8.renderManager.shouldRender(a3, a4, a5, a6, a7);
    }

    @Inject(method={"renderEntities"}, at={@At(value="INVOKE", target="Lnet/minecraft/util/math/BlockPos$PooledMutableBlockPos;release()V", shift=At.Shift.AFTER)})
    private /* synthetic */ void onRender(Entity a2, ICamera a3, float a4, CallbackInfo a5) {
        MixinRenderGlobal a6;
        List a7 = Minecraft.getMinecraft().world.loadedEntityList;
        ArrayList<Entity> a8 = new ArrayList<Entity>();
        for (Entity a9 : a7) {
            if (!(a9 instanceof EntityLivingBase)) continue;
            rda a10 = raa.r.c((EntityLivingBase)a9);
            boolean a11 = kba.ALLATORIxDEMO.containsKey(a9.getUniqueID());
            a11 = a11 || a10 != null && a10.h();
            if (!a11) continue;
            ++a6.countEntitiesRendered;
            a8.add(a9);
        }
        if (!a8.isEmpty()) {
            for (Entity a9 : a8) {
                a6.renderManager.renderEntityStatic(a9, a4, false);
            }
        }
    }
}

