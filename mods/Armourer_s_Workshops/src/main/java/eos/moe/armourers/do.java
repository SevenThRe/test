/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelZombieVillager
 *  net.minecraft.client.renderer.entity.RenderZombie
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 *  org.lwjgl.opengl.GL11
 */
package eos.moe.armourers;

import eos.moe.armourers.ki;
import eos.moe.armourers.r;
import eos.moe.armourers.vn;
import net.minecraft.client.model.ModelZombieVillager;
import net.minecraft.client.renderer.entity.RenderZombie;
import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

@SideOnly(value=Side.CLIENT)
public class do
extends ki {
    public void setRotTranForPartType(EntityLivingBase a2, r a3, float a42, float a5, float a6, float a7, float a8, float a9, float a10) {
        do a11;
        if (a11.s instanceof RenderZombie) {
            a2 = (RenderZombie)a11.s;
            boolean a42 = false;
            a42 = a2.func_177087_b() instanceof ModelZombieVillager;
            if (a42 & a3 == vn.l) {
                GL11.glTranslated((double)0.0, (double)(-2.0f * a10), (double)0.0);
            }
        }
    }

    public do(RenderZombie a2) {
        super(a2);
        do a3;
    }
}

