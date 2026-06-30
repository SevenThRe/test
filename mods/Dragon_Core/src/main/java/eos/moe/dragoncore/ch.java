/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.RayTraceResult
 *  net.minecraft.util.math.RayTraceResult$Type
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.h;
import eos.moe.dragoncore.nr;
import eos.moe.dragoncore.nw;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;

public class ch {
    public ch() {
        ch a2;
    }

    @h(c=1001)
    public static void ALLATORIxDEMO() {
        RayTraceResult a2 = Minecraft.func_71410_x().field_71476_x;
        if (a2 != null) {
            if (a2.field_72308_g != null) {
                nw.ALLATORIxDEMO(a2.field_72308_g.func_145782_y());
            } else if (a2.field_72313_a == RayTraceResult.Type.BLOCK) {
                nw.ALLATORIxDEMO(a2.func_178782_a());
            }
        }
    }

    @h(c=1002)
    public static void ALLATORIxDEMO(BlockPos a2, NBTTagCompound a3) {
        Minecraft.func_71410_x().func_147108_a((GuiScreen)new nr(a2, a3));
    }

    @h(c=1003)
    public static void ALLATORIxDEMO(int a2, NBTTagCompound a3) {
        Minecraft.func_71410_x().func_147108_a((GuiScreen)new nr(a2, a3));
    }

    @h(c=1004)
    public static void ALLATORIxDEMO(NBTTagCompound a2) {
        Minecraft.func_71410_x().func_147108_a((GuiScreen)new nr(a2));
    }
}

