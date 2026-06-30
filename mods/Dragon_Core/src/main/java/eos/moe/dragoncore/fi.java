/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.NBTBase
 */
package eos.moe.dragoncore;

import net.minecraft.nbt.NBTBase;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class fi {
    public String k;
    public NBTBase ALLATORIxDEMO;

    public fi(NBTBase a2) {
        a3("", a2);
        fi a3;
    }

    public fi(String a2, NBTBase a3) {
        fi a4;
        a4.k = a2;
        a4.ALLATORIxDEMO = a3;
    }

    public String ALLATORIxDEMO() {
        fi a2;
        return a2.k;
    }

    public void ALLATORIxDEMO(String a2) {
        a.k = a2;
    }

    public NBTBase ALLATORIxDEMO() {
        fi a2;
        return a2.ALLATORIxDEMO;
    }

    public void ALLATORIxDEMO(NBTBase a2) {
        a.ALLATORIxDEMO = a2;
    }

    public fi ALLATORIxDEMO() {
        fi a2;
        return new fi(a2.k, a2.ALLATORIxDEMO.func_74737_b());
    }
}

