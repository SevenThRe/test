/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.authlib.GameProfile
 *  javax.annotation.Nullable
 *  net.minecraft.block.state.IBlockState
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.DestroyBlockProgress
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.RenderGlobal
 *  net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher
 *  net.minecraft.client.renderer.tileentity.TileEntitySkullRenderer
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.tileentity.TileEntitySkull
 *  net.minecraft.util.EnumFacing
 *  net.minecraft.util.math.BlockPos
 *  net.minecraftforge.fml.relauncher.ReflectionHelper
 */
package eos.moe.dragoncore;

import com.mojang.authlib.GameProfile;
import eos.moe.dragoncore.er;
import eos.moe.dragoncore.jv;
import eos.moe.dragoncore.no;
import eos.moe.dragoncore.vu;
import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.DestroyBlockProgress;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.tileentity.TileEntitySkullRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntitySkull;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.relauncher.ReflectionHelper;

public class sv
extends TileEntitySkullRenderer {
    public static TileEntitySkullRenderer y;
    private static final TileEntitySkullRenderer k;
    private static Map<Integer, DestroyBlockProgress> ALLATORIxDEMO;

    public sv() {
        sv a2;
    }

    public void render(TileEntitySkull a2, double a3, double a4, double a5, float a6, int a7, float a8) {
        float a9 = (float)(a2.getSkullRotation() * 360) / 16.0f;
        EnumFacing a10 = EnumFacing.byIndex((int)(a2.getBlockMetadata() & 7));
        float a11 = a2.getAnimationProgress(a6);
        String a12 = vu.c((TileEntity)a2);
        if (a12 != null && a12.startsWith("none")) {
            return;
        }
        er a13 = vu.ALLATORIxDEMO((TileEntity)a2);
        if (a13 != null) {
            sv a14;
            if (a7 >= 0) {
                return;
            }
            List<BlockPos> a15 = a13.ALLATORIxDEMO(a2.getPos(), a9);
            if (a15 != null) {
                for (BlockPos a16 : a15) {
                    DestroyBlockProgress a17;
                    IBlockState a18 = a2.getWorld().getBlockState(a16);
                    if (!a18.hasCustomBreakingProgress() || (a17 = a14.getDestroyStage(a16)) == null) continue;
                    a7 = Math.max(a17.getPartialBlockDamage(), a7);
                }
            }
            a14.renderSkull((float)a3, (float)a4, (float)a5, a10, a9, 0, a2.getPlayerProfile(), -1, a11);
            if (a7 >= 0) {
                a14.renderSkull((float)a3, (float)a4, (float)a5, a10, a9, 0, a2.getPlayerProfile(), a7, a11);
            }
        } else {
            k.render(a2, a3, a4, a5, a6, a7, a8);
        }
    }

    public void setRendererDispatcher(TileEntityRendererDispatcher a2) {
        sv a3;
        super.setRendererDispatcher(a2);
        y = a3;
    }

    public void renderSkull(float x2, float y2, float z2, EnumFacing facing, float rotationIn, int skullType, @Nullable GameProfile profile, int destroyStage, float a2) {
        String a3 = profile != null && profile.getName() != null ? profile.getName() : null;
        er a4 = er.ALLATORIxDEMO(a3);
        if (a4 != null) {
            String a5 = a4.b;
            jv a6 = no.ALLATORIxDEMO(a5);
            no.ALLATORIxDEMO((int)x2, (int)y2, (int)z2, a5, a6, "idle");
            if (destroyStage >= 0) {
                Minecraft.getMinecraft().getTextureManager().bindTexture(DESTROY_STAGES[destroyStage]);
                GlStateManager.matrixMode((int)5890);
                GlStateManager.pushMatrix();
                GlStateManager.scale((float)4.0f, (float)2.0f, (float)1.0f);
                GlStateManager.translate((float)0.0625f, (float)0.0625f, (float)0.0625f);
                GlStateManager.matrixMode((int)5888);
            } else {
                no.ALLATORIxDEMO(a5);
            }
            GlStateManager.pushMatrix();
            GlStateManager.disableCull();
            GlStateManager.translate((float)(x2 + 0.5f), (float)y2, (float)(z2 + 0.5f));
            GlStateManager.enableRescaleNormal();
            GlStateManager.scale((float)-1.0f, (float)-1.0f, (float)1.0f);
            GlStateManager.enableAlpha();
            GlStateManager.rotate((float)rotationIn, (float)0.0f, (float)1.0f, (float)0.0f);
            a6.render(0.0625f);
            a6.clearData();
            GlStateManager.popMatrix();
            if (destroyStage >= 0) {
                GlStateManager.matrixMode((int)5890);
                GlStateManager.popMatrix();
                GlStateManager.matrixMode((int)5888);
            }
        } else {
            k.renderSkull(x2, y2, z2, facing, rotationIn, skullType, profile, destroyStage, a2);
        }
    }

    public DestroyBlockProgress getDestroyStage(BlockPos a2) {
        if (ALLATORIxDEMO == null) {
            ALLATORIxDEMO = (Map)ReflectionHelper.getPrivateValue(RenderGlobal.class, (Object)Minecraft.getMinecraft().renderGlobal, (String[])new String[]{"damagedBlocks", "damagedBlocks"});
        }
        return ALLATORIxDEMO.values().stream().filter(a3 -> a3.getPosition().equals((Object)a2)).findFirst().orElse(null);
    }

    static {
        k = new TileEntitySkullRenderer();
        k.setRendererDispatcher(TileEntityRendererDispatcher.instance);
    }
}

