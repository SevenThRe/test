/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.bp;
import eos.moe.dragoncore.fi;
import eos.moe.dragoncore.ov;
import eos.moe.dragoncore.ph;
import eos.moe.dragoncore.su;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class yh {
    private NBTTagCompound k;
    private ph<fi> ALLATORIxDEMO;

    public yh(NBTTagCompound a2) {
        yh a3;
        a3.k = a2;
        a3.c();
    }

    public ph<fi> ALLATORIxDEMO() {
        yh a2;
        return a2.ALLATORIxDEMO;
    }

    public boolean c(ph<fi> a2) {
        yh a3;
        return a2 != a3.ALLATORIxDEMO;
    }

    public boolean ALLATORIxDEMO(ph<fi> a2) {
        yh a3;
        return a2 != null && a2 != a3.ALLATORIxDEMO && a3.ALLATORIxDEMO(a2, a3.ALLATORIxDEMO);
    }

    private /* synthetic */ boolean ALLATORIxDEMO(ph<fi> a2, ph<fi> a3) {
        Iterator a4 = a3.ALLATORIxDEMO().iterator();
        while (a4.hasNext()) {
            yh a5;
            ph a6 = (ph)a4.next();
            if (a6 == a2) {
                a4.remove();
                return true;
            }
            boolean a7 = a5.ALLATORIxDEMO(a2, a6);
            if (!a7) continue;
            return true;
        }
        return false;
    }

    private /* synthetic */ void c() {
        yh a2;
        a2.ALLATORIxDEMO = new ph<fi>(new fi("ROOT", (NBTBase)a2.k.func_74737_b()));
        a2.ALLATORIxDEMO.ALLATORIxDEMO(true);
        a2.ALLATORIxDEMO(a2.ALLATORIxDEMO);
        a2.c(a2.ALLATORIxDEMO);
    }

    public void c(ph<fi> a2) {
        Collections.sort(a2.ALLATORIxDEMO(), ov.x);
        Iterator iterator = a2.ALLATORIxDEMO().iterator();
        while (iterator.hasNext()) {
            yh a3;
            ph a4 = (ph)iterator.next();
            a3.c(a4);
        }
    }

    public void ALLATORIxDEMO(ph<fi> a2) {
        block3: {
            yh a3;
            NBTBase a4;
            block2: {
                a4 = a2.ALLATORIxDEMO().ALLATORIxDEMO();
                if (!(a4 instanceof NBTTagCompound)) break block2;
                Map<String, NBTBase> a5 = bp.ALLATORIxDEMO((NBTTagCompound)a4);
                for (Map.Entry<String, NBTBase> a6 : a5.entrySet()) {
                    NBTBase a7 = a6.getValue();
                    ph<fi> a8 = new ph<fi>(a2, new fi(a6.getKey(), a7));
                    a2.ALLATORIxDEMO(a8);
                    a3.ALLATORIxDEMO(a8);
                }
                break block3;
            }
            if (!(a4 instanceof NBTTagList)) break block3;
            NBTTagList a9 = (NBTTagList)a4;
            for (int a10 = 0; a10 < a9.func_74745_c(); ++a10) {
                NBTBase a11 = bp.ALLATORIxDEMO(a9, a10);
                ph<fi> a12 = new ph<fi>(a2, new fi(a11));
                a2.ALLATORIxDEMO(a12);
                a3.ALLATORIxDEMO(a12);
            }
        }
    }

    public NBTTagCompound ALLATORIxDEMO() {
        yh a2;
        NBTTagCompound a3 = new NBTTagCompound();
        a2.ALLATORIxDEMO(a2.ALLATORIxDEMO, a3);
        return a3;
    }

    public void ALLATORIxDEMO(ph<fi> a2, NBTTagCompound a3) {
        Iterator iterator = a2.ALLATORIxDEMO().iterator();
        while (iterator.hasNext()) {
            yh a4;
            NBTTagCompound a5;
            ph a6 = (ph)iterator.next();
            NBTBase a7 = ((fi)a6.ALLATORIxDEMO()).ALLATORIxDEMO();
            String a8 = ((fi)a6.ALLATORIxDEMO()).ALLATORIxDEMO();
            if (a7 instanceof NBTTagCompound) {
                a5 = new NBTTagCompound();
                a4.ALLATORIxDEMO((ph<fi>)a6, a5);
                a3.func_74782_a(a8, (NBTBase)a5);
                continue;
            }
            if (a7 instanceof NBTTagList) {
                a5 = new NBTTagList();
                a4.ALLATORIxDEMO((ph<fi>)a6, (NBTTagList)a5);
                a3.func_74782_a(a8, (NBTBase)a5);
                continue;
            }
            a3.func_74782_a(a8, a7.func_74737_b());
        }
    }

    public void ALLATORIxDEMO(ph<fi> a2, NBTTagList a3) {
        Iterator iterator = a2.ALLATORIxDEMO().iterator();
        while (iterator.hasNext()) {
            yh a4;
            NBTTagCompound a5;
            ph a6 = (ph)iterator.next();
            NBTBase a7 = ((fi)a6.ALLATORIxDEMO()).ALLATORIxDEMO();
            if (a7 instanceof NBTTagCompound) {
                a5 = new NBTTagCompound();
                a4.ALLATORIxDEMO((ph<fi>)a6, a5);
                a3.func_74742_a((NBTBase)a5);
                continue;
            }
            if (a7 instanceof NBTTagList) {
                a5 = new NBTTagList();
                a4.ALLATORIxDEMO((ph<fi>)a6, (NBTTagList)a5);
                a3.func_74742_a((NBTBase)a5);
                continue;
            }
            a3.func_74742_a(a7.func_74737_b());
        }
    }

    public void ALLATORIxDEMO() {
        yh a2;
        a2.ALLATORIxDEMO(a2.ALLATORIxDEMO, 0);
    }

    private /* synthetic */ void ALLATORIxDEMO(ph<fi> a2, int a3) {
        System.out.println(yh.ALLATORIxDEMO("\t", a3) + su.c(a2.ALLATORIxDEMO()));
        Iterator iterator = a2.ALLATORIxDEMO().iterator();
        while (iterator.hasNext()) {
            yh a4;
            ph a5 = (ph)iterator.next();
            a4.ALLATORIxDEMO((ph<fi>)a5, a3 + 1);
        }
    }

    public List<String> ALLATORIxDEMO() {
        yh a2;
        ArrayList<String> a3 = new ArrayList<String>();
        a2.ALLATORIxDEMO(a3, a2.ALLATORIxDEMO, 0);
        return a3;
    }

    private /* synthetic */ void ALLATORIxDEMO(List<String> a2, ph<fi> a3, int a4) {
        a2.add(yh.ALLATORIxDEMO("   ", a4) + su.c(a3.ALLATORIxDEMO()));
        Iterator iterator = a3.ALLATORIxDEMO().iterator();
        while (iterator.hasNext()) {
            yh a5;
            ph a6 = (ph)iterator.next();
            a5.ALLATORIxDEMO(a2, a6, a4 + 1);
        }
    }

    public static String ALLATORIxDEMO(String a2, int a3) {
        StringBuilder a4 = new StringBuilder(a3 + 1);
        for (int a5 = 0; a5 < a3; ++a5) {
            a4.append(a2);
        }
        return a4.toString();
    }
}

