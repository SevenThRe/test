/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.GlStateManager$DestFactor
 *  net.minecraft.client.renderer.GlStateManager$SourceFactor
 *  net.minecraft.client.renderer.tileentity.TileEntityMobSpawnerRenderer
 *  net.minecraft.tileentity.TileEntityMobSpawner
 *  net.minecraft.util.EnumFacing
 */
package eos.moe.armourers.mixins;

import eos.moe.armourers.kd;
import eos.moe.armourers.kf;
import eos.moe.armourers.mk;
import eos.moe.armourers.mn;
import eos.moe.armourers.rk;
import eos.moe.armourers.yl;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntityMobSpawnerRenderer;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.util.EnumFacing;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={TileEntityMobSpawnerRenderer.class})
public class MixinTileEntitySpawnerRenderer {
    private static float SCALE = 0.0625f;

    public MixinTileEntitySpawnerRenderer() {
        MixinTileEntitySpawnerRenderer a2;
    }

    @Inject(method={"render(Lnet/minecraft/tileentity/TileEntityMobSpawner;DDDFIF)V"}, at={@At(value="HEAD")}, cancellable=true)
    public void render(TileEntityMobSpawner a2, double a3, double a4, double a5, float a62, int a72, float a8, CallbackInfo a9) {
        Object a62 = a2.func_189517_E_().func_74775_l("SpawnData").func_74779_i("baka");
        if (!((String)a62).isEmpty() && (a62 = kd.j.getSkin((String)a62)) != null) {
            a9.cancel();
            GlStateManager.func_179094_E();
            GlStateManager.func_179123_a();
            GlStateManager.func_179137_b((double)(a3 + 0.5), (double)(a4 + 0.5), (double)(a5 + 0.5));
            GlStateManager.func_179147_l();
            GlStateManager.func_187401_a((GlStateManager.SourceFactor)GlStateManager.SourceFactor.SRC_ALPHA, (GlStateManager.DestFactor)GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
            a2 = EnumFacing.func_82600_a((int)(a2.func_145832_p() & 7));
            if (a2 == EnumFacing.EAST) {
                GlStateManager.func_179114_b((float)-90.0f, (float)0.0f, (float)1.0f, (float)0.0f);
            }
            if (a2 == EnumFacing.SOUTH) {
                GlStateManager.func_179114_b((float)180.0f, (float)0.0f, (float)1.0f, (float)0.0f);
            }
            if (a2 == EnumFacing.WEST) {
                GlStateManager.func_179114_b((float)90.0f, (float)0.0f, (float)1.0f, (float)0.0f);
            }
            GlStateManager.func_179152_a((float)-1.0f, (float)-1.0f, (float)1.0f);
            int n2 = a2 = 0;
            while (n2 < ((yl)a62).y().size()) {
                kf a72 = ((yl)a62).y().get(a2);
                rk.j.renderPart(new mk(a72, SCALE, new mn(), null, 0.0, true, true, true, null));
                n2 = ++a2;
            }
            GlStateManager.func_179084_k();
            GlStateManager.func_179099_b();
            GlStateManager.func_179121_F();
        }
    }
}

