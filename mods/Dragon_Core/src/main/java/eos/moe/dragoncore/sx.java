/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.BufferBuilder
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.oy;
import java.util.ArrayList;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class sx {
    public String y;
    public ArrayList<oy> k = new ArrayList();
    public int ALLATORIxDEMO;

    public sx() {
        a2("");
        sx a2;
    }

    public sx(String a2) {
        a3(a2, -1);
        sx a3;
    }

    public sx(String a2, int a3) {
        sx a4;
        a4.y = a2;
        a4.ALLATORIxDEMO = a3;
    }

    @SideOnly(value=Side.CLIENT)
    public void ALLATORIxDEMO(BufferBuilder a2, float a3) {
        sx a4;
        if (a4.k.size() > 0) {
            for (oy a5 : a4.k) {
                a5.ALLATORIxDEMO(a4.ALLATORIxDEMO, a2, a3);
            }
        }
    }
}

