/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBiped
 *  net.minecraft.entity.Entity
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package eos.moe.armourers;

import eos.moe.armourers.dn;
import eos.moe.armourers.n;
import eos.moe.armourers.oh;
import eos.moe.armourers.yl;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(value=Side.CLIENT)
public interface p {
    public void render(Entity var1, yl var2, ModelBiped var3, dn var4);

    public void render(Entity var1, yl var2, float var3, float var4, float var5, float var6, float var7);

    public void render(Entity var1, yl var2, ModelBiped var3, boolean var4, n var5, oh var6, boolean var7, double var8, boolean var10);
}

