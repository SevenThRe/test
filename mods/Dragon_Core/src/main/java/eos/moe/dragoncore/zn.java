/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.fi;
import eos.moe.dragoncore.ph;
import java.util.Comparator;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class zn
implements Comparator<ph<fi>> {
    public zn() {
        zn a2;
    }

    public int ALLATORIxDEMO(ph<fi> a2, ph<fi> a3) {
        NBTBase a4 = a2.ALLATORIxDEMO().ALLATORIxDEMO();
        NBTBase a5 = a3.ALLATORIxDEMO().ALLATORIxDEMO();
        String a6 = a2.ALLATORIxDEMO().ALLATORIxDEMO();
        String a7 = a3.ALLATORIxDEMO().ALLATORIxDEMO();
        if (a4 instanceof NBTTagCompound || a4 instanceof NBTTagList) {
            if (a5 instanceof NBTTagCompound || a5 instanceof NBTTagList) {
                int a8 = a4.getId() - a5.getId();
                return a8 == 0 ? a6.compareTo(a7) : a8;
            }
            return 1;
        }
        if (a5 instanceof NBTTagCompound || a5 instanceof NBTTagList) {
            return -1;
        }
        int a9 = a4.getId() - a5.getId();
        return a9 == 0 ? a6.compareTo(a7) : a9;
    }
}

