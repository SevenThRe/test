/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.text.ITextComponent
 *  net.minecraft.util.text.TextComponentString
 *  net.minecraft.util.text.TextFormatting
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.ov;
import java.io.File;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class fx {
    public fx() {
        fx a2;
    }

    public void ALLATORIxDEMO() {
    }

    public File ALLATORIxDEMO() {
        return new File(".");
    }

    public void ALLATORIxDEMO(int a2, NBTTagCompound a3) {
    }

    public void ALLATORIxDEMO(BlockPos a2, NBTTagCompound a3) {
    }

    public void ALLATORIxDEMO(EntityPlayer a2, String a3, TextFormatting a4) {
        if (a2 != null) {
            TextComponentString a5 = new TextComponentString(a3);
            a5.func_150256_b().func_150238_a(a4);
            a2.func_145747_a((ITextComponent)a5);
        }
    }

    public boolean ALLATORIxDEMO(EntityPlayer a2) {
        return ov.q ? a2.func_70003_b(4, "nbtedit") : a2.field_71075_bZ.field_75098_d;
    }
}

