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
        return FMLClientHandler.instance().getClient().gameDir;
    }

    @Override
    public void ALLATORIxDEMO(int a2, NBTTagCompound a3) {
        ns a4;
        Minecraft.getMinecraft().addScheduledTask((Runnable)new ky(a4, a2, a3));
    }

    @Override
    public void ALLATORIxDEMO(BlockPos a2, NBTTagCompound a3) {
        ns a4;
        Minecraft.getMinecraft().addScheduledTask((Runnable)new iy(a4, a2, a3));
    }

    @Override
    public void ALLATORIxDEMO(EntityPlayer a2, String a3, TextFormatting a4) {
        TextComponentString a5 = new TextComponentString(a3);
        a5.getStyle().setColor(a4);
        Minecraft.getMinecraft().player.sendMessage((ITextComponent)a5);
    }

    @SubscribeEvent
    public void ALLATORIxDEMO(RenderWorldLastEvent a2) {
        GuiScreen a3 = Minecraft.getMinecraft().currentScreen;
        if (a3 instanceof nr) {
            ns a4;
            nr a5 = (nr)a3;
            Entity a6 = a5.getEntity();
            if (a6 != null && a6.isEntityAlive()) {
                a4.ALLATORIxDEMO(a2.getContext(), a2.getPartialTicks(), a6.getEntityBoundingBox());
            } else if (a5.isTileEntity()) {
                int a7 = a5.getBlockX();
                int a8 = a5.b;
                int a9 = a5.o;
                WorldClient a10 = Minecraft.getMinecraft().world;
                BlockPos a11 = new BlockPos(a7, a8, a9);
                IBlockState a12 = a10.getBlockState(a11);
                Block a13 = a10.getBlockState(a11).getBlock();
                if (a13 != null) {
                    a4.ALLATORIxDEMO(a2.getContext(), a2.getPartialTicks(), a13.getSelectedBoundingBox(a12, (World)a10, a11));
                }
            }
        }
    }

    @SubscribeEvent
    public void ALLATORIxDEMO(InputEvent.KeyInputEvent a2) {
        RayTraceResult a3;
        if (ALLATORIxDEMO.isPressed() && (a3 = Minecraft.getMinecraft().objectMouseOver) != null) {
            if (a3.entityHit != null) {
                ov.v.k.sendToServer((IMessage)new rl(a3.entityHit.getEntityId()));
            } else if (a3.typeOfHit == RayTraceResult.Type.BLOCK) {
                ov.v.k.sendToServer((IMessage)new fn(a3.getBlockPos()));
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
        Entity a5 = Minecraft.getMinecraft().getRenderViewEntity();
        double a6 = a5.lastTickPosX + (a5.posX - a5.lastTickPosX) * (double)a3;
        double a7 = a5.lastTickPosY + (a5.posY - a5.lastTickPosY) * (double)a3;
        double a8 = a5.lastTickPosZ + (a5.posZ - a5.lastTickPosZ) * (double)a3;
        a4 = a4.grow(-a6, -a7, -a8);
        GlStateManager.enableBlend();
        GlStateManager.blendFunc((int)770, (int)771);
        GlStateManager.color((float)1.0f, (float)0.0f, (float)0.0f, (float)0.5f);
        GL11.glLineWidth((float)3.5f);
        GlStateManager.disableTexture2D();
        GlStateManager.depthMask((boolean)false);
        Tessellator a9 = Tessellator.getInstance();
        BufferBuilder a10 = a9.getBuffer();
        a10.begin(3, DefaultVertexFormats.POSITION_COLOR);
        a10.pos(a4.minX, a4.minY, a4.minZ);
        a10.pos(a4.maxX, a4.minY, a4.minZ);
        a10.pos(a4.maxX, a4.minY, a4.maxZ);
        a10.pos(a4.minX, a4.minY, a4.maxZ);
        a10.pos(a4.minX, a4.minY, a4.minZ);
        a9.draw();
        a10.begin(3, DefaultVertexFormats.POSITION_COLOR);
        a10.pos(a4.minX, a4.maxY, a4.minZ);
        a10.pos(a4.maxX, a4.maxY, a4.minZ);
        a10.pos(a4.maxX, a4.maxY, a4.maxZ);
        a10.pos(a4.minX, a4.maxY, a4.maxZ);
        a10.pos(a4.minX, a4.maxY, a4.minZ);
        a9.draw();
        a10.begin(1, DefaultVertexFormats.POSITION_COLOR);
        a10.pos(a4.minX, a4.minY, a4.minZ);
        a10.pos(a4.minX, a4.maxY, a4.minZ);
        a10.pos(a4.maxX, a4.minY, a4.minZ);
        a10.pos(a4.maxX, a4.maxY, a4.minZ);
        a10.pos(a4.maxX, a4.minY, a4.maxZ);
        a10.pos(a4.maxX, a4.maxY, a4.maxZ);
        a10.pos(a4.minX, a4.minY, a4.maxZ);
        a10.pos(a4.minX, a4.maxY, a4.maxZ);
        a9.draw();
        GlStateManager.depthMask((boolean)true);
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }
}

