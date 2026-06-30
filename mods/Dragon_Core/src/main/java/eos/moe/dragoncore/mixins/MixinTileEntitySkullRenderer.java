/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.authlib.GameProfile
 *  com.mojang.authlib.properties.Property
 *  net.minecraft.block.state.IBlockState
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.DestroyBlockProgress
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.tileentity.TileEntitySkullRenderer
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.tileentity.TileEntitySkull
 *  net.minecraft.util.EnumFacing
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.math.BlockPos
 *  org.jetbrains.annotations.Nullable
 */
package eos.moe.dragoncore.mixins;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import eos.moe.dragoncore.cy;
import eos.moe.dragoncore.er;
import eos.moe.dragoncore.gq;
import eos.moe.dragoncore.jv;
import eos.moe.dragoncore.no;
import eos.moe.dragoncore.vu;
import java.util.List;
import java.util.Map;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.DestroyBlockProgress;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySkullRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntitySkull;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={TileEntitySkullRenderer.class})
public abstract class MixinTileEntitySkullRenderer {
    private static final ResourceLocation[] DESTROY_STAGES = new ResourceLocation[]{new ResourceLocation("textures/blocks/destroy_stage_0.png"), new ResourceLocation("textures/blocks/destroy_stage_1.png"), new ResourceLocation("textures/blocks/destroy_stage_2.png"), new ResourceLocation("textures/blocks/destroy_stage_3.png"), new ResourceLocation("textures/blocks/destroy_stage_4.png"), new ResourceLocation("textures/blocks/destroy_stage_5.png"), new ResourceLocation("textures/blocks/destroy_stage_6.png"), new ResourceLocation("textures/blocks/destroy_stage_7.png"), new ResourceLocation("textures/blocks/destroy_stage_8.png"), new ResourceLocation("textures/blocks/destroy_stage_9.png")};
    private static Map<Integer, DestroyBlockProgress> damagedBlocks;

    public MixinTileEntitySkullRenderer() {
        MixinTileEntitySkullRenderer a2;
    }

    @Shadow
    public abstract void renderSkull(float var1, float var2, float var3, EnumFacing var4, float var5, int var6, @Nullable GameProfile var7, int var8, float var9);

    @Inject(method={"render(Lnet/minecraft/tileentity/TileEntitySkull;DDDFIF)V"}, at={@At(value="HEAD")}, cancellable=true)
    private /* synthetic */ void mixin_render(TileEntitySkull a2, double a3, double a4, double a5, float a6, int a7, float a8, CallbackInfo a9) {
        if (vu.ALLATORIxDEMO((TileEntity)a2)) {
            return;
        }
        float a10 = (float)(a2.getSkullRotation() * 360) / 16.0f;
        gq a11 = cy.q.ALLATORIxDEMO((TileEntity)a2);
        if (a11 != null) {
            a9.cancel();
            cy.q.ALLATORIxDEMO(a11, a2, (float)a3, (float)a4, (float)a5, a10, a7);
            if (a7 >= 0) {
                cy.q.ALLATORIxDEMO(a11, a2, (float)a3, (float)a4, (float)a5, a10, a7);
            }
            cy.q.c(a11);
        } else {
            String a12 = vu.c((TileEntity)a2);
            if (a12 != null && a12.startsWith("none")) {
                return;
            }
            er a13 = vu.ALLATORIxDEMO((TileEntity)a2);
            if (a13 != null) {
                MixinTileEntitySkullRenderer a14;
                if (a7 >= 0) {
                    return;
                }
                List<BlockPos> a15 = a13.ALLATORIxDEMO(a2.getPos(), a10);
                if (a15 != null) {
                    for (BlockPos a16 : a15) {
                        DestroyBlockProgress a17;
                        IBlockState a18 = a2.getWorld().getBlockState(a16);
                        if (!a18.hasCustomBreakingProgress() || (a17 = a14.getDestroyStage(a16)) == null) continue;
                        a7 = Math.max(a17.getPartialBlockDamage(), a7);
                    }
                }
                a14.renderModel(a2, a13, (float)a3, (float)a4, (float)a5, a10, -1);
                if (a7 >= 0) {
                    a14.renderModel(a2, a13, (float)a3, (float)a4, (float)a5, a10, a7);
                }
                jv a19 = no.ALLATORIxDEMO(a13.b);
                a19.clearData();
                a9.cancel();
            }
        }
    }

    @Inject(method={"renderSkull"}, at={@At(value="HEAD")}, cancellable=true)
    public void mixin_renderSkull(float a2, float a3, float a4, EnumFacing a5, float a6, int a7, GameProfile a8, int a9, float a10, CallbackInfo a11) {
        gq a12 = cy.q.ALLATORIxDEMO(a8);
        if (a12 != null) {
            a11.cancel();
            cy.q.ALLATORIxDEMO(a12, null, a2, a3, a4, a6, a9);
            cy.q.c(a12);
        } else {
            Object a13;
            String a14 = null;
            if (a8 != null && (a13 = a8.getProperties().get((Object)"model")).size() != 0) {
                Property a15 = (Property)a13.stream().findFirst().get();
                a14 = a15.getValue();
            }
            if (a14 == null) {
                a14 = vu.f(a8);
            }
            a13 = er.ALLATORIxDEMO(a14);
            if (a14 != null && a14.startsWith("none|")) {
                a11.cancel();
            } else if (a13 != null) {
                MixinTileEntitySkullRenderer a16;
                a11.cancel();
                a16.renderModel(null, (er)a13, a2, a3, a4, a6, a9);
                no.ALLATORIxDEMO(((er)a13).b).clearData();
            }
        }
    }

    private /* synthetic */ void renderModel(TileEntitySkull a2, er a3, float a4, float a5, float a6, float a7, int a8) {
        String a9 = a3.b;
        jv a10 = no.ALLATORIxDEMO(a9);
        if (a2 == null) {
            no.ALLATORIxDEMO(0, 0, 0, a9, a10, "idle");
        } else {
            BlockPos a11 = a2.getPos();
            no.ALLATORIxDEMO(a11.getX(), a11.getY(), a11.getZ(), a9, a10, "idle");
        }
        if (a8 >= 0) {
            Minecraft.getMinecraft().getTextureManager().bindTexture(DESTROY_STAGES[a8]);
            GlStateManager.matrixMode((int)5890);
            GlStateManager.pushMatrix();
            GlStateManager.scale((float)4.0f, (float)2.0f, (float)1.0f);
            GlStateManager.translate((float)0.0625f, (float)0.0625f, (float)0.0625f);
            GlStateManager.matrixMode((int)5888);
        } else {
            no.ALLATORIxDEMO(a9);
        }
        GlStateManager.pushMatrix();
        GlStateManager.disableCull();
        GlStateManager.translate((float)(a4 + 0.5f), (float)a5, (float)(a6 + 0.5f));
        GlStateManager.enableRescaleNormal();
        GlStateManager.scale((float)-1.0f, (float)-1.0f, (float)1.0f);
        GlStateManager.enableAlpha();
        GlStateManager.rotate((float)a7, (float)0.0f, (float)1.0f, (float)0.0f);
        a10.render(0.0625f);
        GlStateManager.popMatrix();
        if (a8 >= 0) {
            GlStateManager.matrixMode((int)5890);
            GlStateManager.popMatrix();
            GlStateManager.matrixMode((int)5888);
        }
    }

    private /* synthetic */ DestroyBlockProgress getDestroyStage(BlockPos a2) {
        if (damagedBlocks == null) {
            damagedBlocks = Minecraft.getMinecraft().renderGlobal.damagedBlocks;
        }
        return damagedBlocks.values().stream().filter(a3 -> a3.getPosition().equals((Object)a2)).findFirst().orElse(null);
    }
}

