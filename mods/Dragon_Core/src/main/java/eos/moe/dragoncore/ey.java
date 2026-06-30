/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.multiplayer.WorldClient
 *  net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher
 *  net.minecraft.tileentity.TileEntitySign
 *  net.minecraft.util.Tuple
 *  net.minecraft.util.math.BlockPos
 *  net.minecraftforge.client.event.RenderWorldLastEvent
 *  net.minecraftforge.fml.common.Mod$EventBusSubscriber
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.renderer.SignRenderer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.tileentity.TileEntitySign;
import net.minecraft.util.Tuple;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid="dragoncore")
public class ey {
    public static Set<TileEntitySign> ALLATORIxDEMO = new HashSet<TileEntitySign>();

    public ey() {
        ey a2;
    }

    @SubscribeEvent
    public static void ALLATORIxDEMO(RenderWorldLastEvent a2) {
        WorldClient a3 = Minecraft.func_71410_x().field_71441_e;
        FontRenderer a4 = Minecraft.func_71410_x().field_71466_p;
        if (!ALLATORIxDEMO.isEmpty()) {
            ArrayList<Tuple> a6 = new ArrayList<Tuple>();
            for (TileEntitySign tileEntitySign : ALLATORIxDEMO) {
                BlockPos a5 = tileEntitySign.func_174877_v();
                double a8 = SignRenderer.distance((double)a5.func_177958_n() - TileEntityRendererDispatcher.field_147554_b, (double)a5.func_177956_o() - TileEntityRendererDispatcher.field_147555_c, (double)a5.func_177952_p() - TileEntityRendererDispatcher.field_147552_d);
                a6.add(new Tuple((Object)tileEntitySign, (Object)a8));
            }
            a6.sort(Comparator.comparingDouble(Tuple::func_76340_b));
            Collections.reverse(a6);
            for (Tuple tuple : a6) {
                TileEntitySign a5 = (TileEntitySign)tuple.func_76341_a();
                BlockPos a9 = a5.func_174877_v();
                SignRenderer.renderText(a4, a5, (double)a9.func_177958_n() - TileEntityRendererDispatcher.field_147554_b, (double)a9.func_177956_o() - TileEntityRendererDispatcher.field_147555_c, (double)a9.func_177952_p() - TileEntityRendererDispatcher.field_147552_d);
            }
            ALLATORIxDEMO.clear();
        }
    }
}

