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
            a5.getStyle().setColor(a4);
            a2.sendMessage((ITextComponent)a5);
        }
    }

    public boolean ALLATORIxDEMO(EntityPlayer a2) {
        return ov.q ? a2.canUseCommand(4, "nbtedit") : a2.capabilities.isCreativeMode;
    }
}

