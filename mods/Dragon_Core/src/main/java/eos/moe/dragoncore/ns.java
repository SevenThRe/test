/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.state.IBlockState
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.multiplayer.WorldClient
 *  net.minecraft.client.renderer.BufferBuilder
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.RenderGlobal
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.vertex.DefaultVertexFormats
 *  net.minecraft.client.settings.KeyBinding
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.RayTraceResult
 *  net.minecraft.util.math.RayTraceResult$Type
 *  net.minecraft.util.text.ITextComponent
 *  net.minecraft.util.text.TextComponentString
 *  net.minecraft.util.text.TextFormatting
 *  net.minecraft.world.World
 *  net.minecraftforge.client.event.RenderWorldLastEvent
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.fml.client.FMLClientHandler
 *  net.minecraftforge.fml.client.registry.ClientRegistry
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.gameevent.InputEvent$KeyInputEvent
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessage
 *  org.lwjgl.opengl.GL11
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.fn;
import eos.moe.dragoncore.fx;
import eos.moe.dragoncore.iy;
import eos.moe.dragoncore.ky;
import eos.moe.dragoncore.nr;
import eos.moe.dragoncore.ov;
import eos.moe.dragoncore.rl;
import eos.moe.dragoncore.td;
import java.io.File;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import org.lwjgl.opengl.GL11;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class ns
extends fx {
    public static KeyBinding ALLATORIxDEMO;

    public ns() {
        ns a2;
    }

    @Override
    public void ALLATORIxDEMO() {
        ns a2;
        MinecraftForge.EVENT_BUS.register((Object)a2);
        td a3 = ov.ALLATORIxDEMO();
        a3.ALLATORIxDEMO();
        a3.c();
        ALLATORIxDEMO = new KeyBinding("NBTEdit Shortcut", 0, "key.categories.misc");
        ClientRegistry.registerKeyBinding((KeyBinding)ALLATORIxDEMO);
    }

    @Override
    public File ALLATORIxDEMO() {
        return FMLClientHandler.instance().getClient().field_71412_D;
    }

    @Override
    public void ALLATORIxDEMO(int a2, NBTTagCompound a3) {
        ns a4;
        Minecraft.func_71410_x().func_152344_a((Runnable)new ky(a4, a2, a3));
    }

    @Override
    public void ALLATORIxDEMO(BlockPos a2, NBTTagCompound a3) {
        ns a4;
        Minecraft.func_71410_x().func_152344_a((Runnable)new iy(a4, a2, a3));
    }

    @Override
    public void ALLATORIxDEMO(EntityPlayer a2, String a3, TextFormatting a4) {
        TextComponentString a5 = new TextComponentString(a3);
        a5.func_150256_b().func_150238_a(a4);
        Minecraft.func_71410_x().field_71439_g.func_145747_a((ITextComponent)a5);
    }

    @SubscribeEvent
    public void ALLATORIxDEMO(RenderWorldLastEvent a2) {
        GuiScreen a3 = Minecraft.func_71410_x().field_71462_r;
        if (a3 instanceof nr) {
            ns a4;
            nr a5 = (nr)a3;
            Entity a6 = a5.getEntity();
            if (a6 != null && a6.func_70089_S()) {
                a4.ALLATORIxDEMO(a2.getContext(), a2.getPartialTicks(), a6.func_174813_aQ());
            } else if (a5.isTileEntity()) {
                int a7 = a5.getBlockX();
                int a8 = a5.b;
                int a9 = a5.o;
                WorldClient a10 = Minecraft.func_71410_x().field_71441_e;
                BlockPos a11 = new BlockPos(a7, a8, a9);
                IBlockState a12 = a10.func_180495_p(a11);
                Block a13 = a10.func_180495_p(a11).func_177230_c();
                if (a13 != null) {
                    a4.ALLATORIxDEMO(a2.getContext(), a2.getPartialTicks(), a13.func_180640_a(a12, (World)a10, a11));
                }
            }
        }
    }

    @SubscribeEvent
    public void ALLATORIxDEMO(InputEvent.KeyInputEvent a2) {
        RayTraceResult a3;
        if (ALLATORIxDEMO.func_151468_f() && (a3 = Minecraft.func_71410_x().field_71476_x) != null) {
            if (a3.field_72308_g != null) {
                ov.v.k.sendToServer((IMessage)new rl(a3.field_72308_g.func_145782_y()));
            } else if (a3.field_72313_a == RayTraceResult.Type.BLOCK) {
                ov.v.k.sendToServer((IMessage)new fn(a3.func_178782_a()));
            } else {
                ns a4;
                a4.ALLATORIxDEMO(null, "Error - No tile or entity selected", TextFormatting.RED);
            }
        }
    }

    private /* synthetic */ void ALLATORIxDEMO(RenderGlobal a2, float a3, AxisAlignedBB a4) {
        if (a4 == null) {
            return;
        }
        Entity a5 = Minecraft.func_71410_x().func_175606_aa();
        double a6 = a5.field_70142_S + (a5.field_70165_t - a5.field_70142_S) * (double)a3;
        double a7 = a5.field_70137_T + (a5.field_70163_u - a5.field_70137_T) * (double)a3;
        double a8 = a5.field_70136_U + (a5.field_70161_v - a5.field_70136_U) * (double)a3;
        a4 = a4.func_72314_b(-a6, -a7, -a8);
        GlStateManager.func_179147_l();
        GlStateManager.func_179112_b((int)770, (int)771);
        GlStateManager.func_179131_c((float)1.0f, (float)0.0f, (float)0.0f, (float)0.5f);
        GL11.glLineWidth((float)3.5f);
        GlStateManager.func_179090_x();
        GlStateManager.func_179132_a((boolean)false);
        Tessellator a9 = Tessellator.func_178181_a();
        BufferBuilder a10 = a9.func_178180_c();
        a10.func_181668_a(3, DefaultVertexFormats.field_181706_f);
        a10.func_181662_b(a4.field_72340_a, a4.field_72338_b, a4.field_72339_c);
        a10.func_181662_b(a4.field_72336_d, a4.field_72338_b, a4.field_72339_c);
        a10.func_181662_b(a4.field_72336_d, a4.field_72338_b, a4.field_72334_f);
        a10.func_181662_b(a4.field_72340_a, a4.field_72338_b, a4.field_72334_f);
        a10.func_181662_b(a4.field_72340_a, a4.field_72338_b, a4.field_72339_c);
        a9.func_78381_a();
        a10.func_181668_a(3, DefaultVertexFormats.field_181706_f);
        a10.func_181662_b(a4.field_72340_a, a4.field_72337_e, a4.field_72339_c);
        a10.func_181662_b(a4.field_72336_d, a4.field_72337_e, a4.field_72339_c);
        a10.func_181662_b(a4.field_72336_d, a4.field_72337_e, a4.field_72334_f);
        a10.func_181662_b(a4.field_72340_a, a4.field_72337_e, a4.field_72334_f);
        a10.func_181662_b(a4.field_72340_a, a4.field_72337_e, a4.field_72339_c);
        a9.func_78381_a();
        a10.func_181668_a(1, DefaultVertexFormats.field_181706_f);
        a10.func_181662_b(a4.field_72340_a, a4.field_72338_b, a4.field_72339_c);
        a10.func_181662_b(a4.field_72340_a, a4.field_72337_e, a4.field_72339_c);
        a10.func_181662_b(a4.field_72336_d, a4.field_72338_b, a4.field_72339_c);
        a10.func_181662_b(a4.field_72336_d, a4.field_72337_e, a4.field_72339_c);
        a10.func_181662_b(a4.field_72336_d, a4.field_72338_b, a4.field_72334_f);
        a10.func_181662_b(a4.field_72336_d, a4.field_72337_e, a4.field_72334_f);
        a10.func_181662_b(a4.field_72340_a, a4.field_72338_b, a4.field_72334_f);
        a10.func_181662_b(a4.field_72340_a, a4.field_72337_e, a4.field_72334_f);
        a9.func_78381_a();
        GlStateManager.func_179132_a((boolean)true);
        GlStateManager.func_179098_w();
        GlStateManager.func_179084_k();
    }
}

