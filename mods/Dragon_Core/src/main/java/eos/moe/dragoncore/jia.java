/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.ResourceLocation
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.dj;
import eos.moe.dragoncore.fia;
import eos.moe.dragoncore.qd;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;

public class jia {
    public static ConcurrentHashMap<fia, qd<Long, Long>> ALLATORIxDEMO = new ConcurrentHashMap();

    public jia() {
        jia a2;
    }

    public static qd<Long, Long> ALLATORIxDEMO(ItemStack a4) {
        ALLATORIxDEMO.entrySet().removeIf(a2 -> System.currentTimeMillis() > (Long)((qd)a2.getValue()).ALLATORIxDEMO());
        ArrayList<qd<Integer, qd<Long, Long>>> a5 = new ArrayList<qd<Integer, qd<Long, Long>>>();
        for (Map.Entry<fia, qd<Long, Long>> a6 : ALLATORIxDEMO.entrySet()) {
            Object a7;
            fia a8 = a6.getKey();
            int a9 = 0;
            if (!fia.f(a8).isEmpty()) {
                a7 = (ResourceLocation)Item.REGISTRY.getNameForObject((Object)a4.getItem());
                if (a7 != null && !fia.f(a8).toUpperCase().equals(a7.getPath().toUpperCase())) continue;
                ++a9;
            }
            if (!fia.c(a8).isEmpty()) {
                if (!fia.c(a8).equals(a4.getDisplayName())) continue;
                ++a9;
            }
            if (!fia.ALLATORIxDEMO(a8).isEmpty()) {
                a7 = dj.ALLATORIxDEMO(a4, false);
                String a10 = String.join((CharSequence)"|~|", (Iterable<? extends CharSequence>)a7);
                if (!fia.ALLATORIxDEMO(a8).equals(a10)) continue;
                ++a9;
            }
            if (!fia.ALLATORIxDEMO(a8).isEmpty()) {
                a7 = a4.getTagCompound();
                if (a7 == null) continue;
                boolean a11 = false;
                for (Map.Entry a12 : fia.ALLATORIxDEMO(a8).entrySet()) {
                    String a13 = jia.ALLATORIxDEMO((NBTTagCompound)a7, (String)a12.getKey());
                    if (a13.equals(a12.getValue())) continue;
                    a11 = true;
                    break;
                }
                if (a11) continue;
                ++a9;
            }
            a5.add(new qd<Integer, qd<Long, Long>>(a9, a6.getValue()));
        }
        a5.sort((a2, a3) -> (Integer)a3.c() - (Integer)a2.c());
        if (a5.size() == 0) {
            return null;
        }
        return (qd)((qd)a5.get(0)).ALLATORIxDEMO();
    }

    public static String ALLATORIxDEMO(NBTTagCompound a2, String a3) {
        String[] a4 = a3.split("\\.");
        NBTTagCompound a5 = a2;
        for (int a6 = 0; a6 < a4.length - 1; ++a6) {
            a5 = a5.getCompoundTag(a4[a6]);
        }
        return a5.getString(a4[a4.length - 1]);
    }
}

