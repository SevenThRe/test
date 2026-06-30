/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.audio.SoundEventAccessor
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.SoundCategory
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.am;
import net.minecraft.client.audio.SoundEventAccessor;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;

public class hm {
    public String b;
    public ResourceLocation o;
    public SoundEventAccessor y;
    public am k;
    public SoundCategory ALLATORIxDEMO;

    public hm(ResourceLocation a2, SoundCategory a3) throws NullPointerException {
        hm a4;
        if (a2 == null) {
            throw new NullPointerException("Sound location is NULL!");
        }
        a4.o = a2;
        a4.ALLATORIxDEMO = a3;
        if (a4.ALLATORIxDEMO == null) {
            a4.ALLATORIxDEMO = SoundCategory.MASTER;
        }
        a4.ALLATORIxDEMO();
    }

    public boolean ALLATORIxDEMO() {
        try {
            hm a2;
            a2.k = new am(a2, a2.o, a2.ALLATORIxDEMO, 1.0f, 1.0f);
            a2.y = new SoundEventAccessor(a2.o, null);
            return true;
        }
        catch (Exception a3) {
            a3.printStackTrace();
            return false;
        }
    }
}

