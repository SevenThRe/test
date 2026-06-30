/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 */
package eos.moe.armourers;

import eos.moe.armourers.ModelData;
import eos.moe.armourers.hd;
import eos.moe.armourers.interfaces.ISkinStorage;
import eos.moe.armourers.kd;
import eos.moe.armourers.yl;
import eos.moe.armourers.zg;
import java.util.Locale;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class fk {
    public String j;

    public String r() {
        fk a2;
        if (a2.j == null) {
            return null;
        }
        Object object = zg.v.get(a2.j);
        if (object != null) {
            return ((ModelData)object).getSkinType().toLowerCase(Locale.ROOT);
        }
        object = kd.j.getSkin(a2.j);
        if (object != null) {
            return ((yl)object).r().r().toLowerCase(Locale.ROOT);
        }
        return null;
    }

    public static fk r(ItemStack a2) {
        String string;
        if (a2 == null || a2.func_190926_b() || a2.func_77978_p() == null) {
            return null;
        }
        String string2 = null;
        ItemStack itemStack = a2;
        NBTTagCompound nBTTagCompound = itemStack.func_77978_p();
        ISkinStorage iSkinStorage = (ISkinStorage)itemStack;
        if (iSkinStorage.getSkin() != null) {
            string = string2 = iSkinStorage.getSkin();
        } else {
            NBTTagCompound nBTTagCompound2 = nBTTagCompound;
            if (nBTTagCompound.func_74764_b("skin")) {
                string = string2 = nBTTagCompound2.func_74779_i("skin");
            } else {
                NBTTagCompound nBTTagCompound3 = nBTTagCompound;
                string = nBTTagCompound2.func_74775_l("armourersWorkshop").func_74775_l("identifier").func_74764_b("dragonname") ? (string2 = nBTTagCompound3.func_74775_l("armourersWorkshop").func_74775_l("identifier").func_74779_i("dragonname")) : (nBTTagCompound3.func_74775_l("armourersWorkshops").func_74775_l("identifier").func_74764_b("dragonname") ? (string2 = nBTTagCompound.func_74775_l("armourersWorkshops").func_74775_l("identifier").func_74779_i("dragonname")) : (string2 = fk.r(a2)));
            }
        }
        if (string != null && !string2.isEmpty()) {
            if (fk.r(string2) == null && fk.r(string2) == null) {
                return null;
            }
            new fk().j = string2;
            return new fk();
        }
        return null;
    }

    public yl r() {
        fk a2;
        if (a2.j == null) {
            return null;
        }
        return kd.j.getSkin(a2.j);
    }

    public fk(String a2) {
        fk a3;
        a3.j = a2;
    }

    public String toString() {
        fk a2;
        return new StringBuilder().insert(0, "SkinInfo{skinName='").append(a2.j).append('\'').append('}').toString();
    }

    public static yl r(String a2) {
        return kd.j.getSkin(a2);
    }

    public fk() {
        fk a2;
    }

    private static /* synthetic */ String r(ItemStack a2) {
        int n2;
        NBTTagCompound nBTTagCompound = a2.func_77978_p();
        if (nBTTagCompound == null) {
            return null;
        }
        if (!nBTTagCompound.func_150297_b("display", 10)) {
            return null;
        }
        if ((nBTTagCompound = nBTTagCompound.func_74775_l("display")).func_150299_b("Lore") != 9) {
            return null;
        }
        if ((nBTTagCompound = nBTTagCompound.func_150295_c("Lore", 8)).func_82582_d()) {
            return null;
        }
        int n3 = n2 = 0;
        while (n3 < nBTTagCompound.func_74745_c()) {
            String string = nBTTagCompound.func_150307_f(n2);
            string = hd.r(string.replace(" ", ""));
            if ((string = zg.j.get(string)) != null) {
                ((ISkinStorage)a2).setSkin(string);
                return string;
            }
            n3 = ++n2;
        }
        ((ISkinStorage)a2).setSkin("");
        return null;
    }

    public ModelData r() {
        fk a2;
        return zg.v.get(a2.j);
    }

    public static ModelData r(String a2) {
        return zg.v.get(a2);
    }
}

