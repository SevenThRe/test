/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.EntityList;
import eos.moe.dragoncore.i;
import eos.moe.dragoncore.pf;
import eos.moe.dragoncore.v;
import eos.moe.dragoncore.vf;
import java.util.UUID;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class hf {
    public hf() {
        hf a2;
    }

    @i(f={"\u53d6\u5b9e\u4f53"})
    public static v ALLATORIxDEMO(v a2) {
        if (a2 instanceof vf) {
            return a2;
        }
        String a3 = a2.c();
        Entity a4 = null;
        try {
            a4 = Minecraft.func_71410_x().field_71441_e.func_73045_a(Integer.parseInt(a3));
        }
        catch (NumberFormatException a5) {
            try {
                a4 = EntityList.getEntityByUUID(UUID.fromString(a3));
            }
            catch (Exception exception) {
                // empty catch block
            }
        }
        if (a4 != null) {
            return new vf(a4);
        }
        return pf.y;
    }

    @i(f={"\u5b9e\u4f53\u662f\u5426\u5b58\u5728", "Entity_Exists", "Entity_Exist"})
    public static boolean ALLATORIxDEMO(v a2) {
        return hf.ALLATORIxDEMO(a2).ALLATORIxDEMO() == 1.0;
    }

    @i(f={"\u53d6\u5b9e\u4f53\u5750\u6807x"})
    public static double k(v a2) {
        Entity a3 = hf.ALLATORIxDEMO(a2).ALLATORIxDEMO();
        if (a3 != null) {
            return a3.field_70165_t;
        }
        return 0.0;
    }

    @i(f={"\u53d6\u5b9e\u4f53\u5750\u6807y"})
    public static double d(v a2) {
        Entity a3 = hf.ALLATORIxDEMO(a2).ALLATORIxDEMO();
        if (a3 != null) {
            return a3.field_70163_u;
        }
        return 0.0;
    }

    @i(f={"\u53d6\u5b9e\u4f53\u5750\u6807z"})
    public static double x(v a2) {
        Entity a3 = hf.ALLATORIxDEMO(a2).ALLATORIxDEMO();
        if (a3 != null) {
            return a3.field_70161_v;
        }
        return 0.0;
    }

    @i(f={"\u53d6\u5b9e\u4f53\u9ad8\u5ea6"})
    public static double f(v a2) {
        Entity a3 = hf.ALLATORIxDEMO(a2).ALLATORIxDEMO();
        if (a3 != null) {
            return a3.field_70131_O;
        }
        return 0.0;
    }

    @i(f={"\u53d6\u5b9e\u4f53\u8840\u91cf"})
    public static double c(v a2) {
        Entity a3 = hf.ALLATORIxDEMO(a2).ALLATORIxDEMO();
        if (a3 instanceof EntityLivingBase) {
            return ((EntityLivingBase)a3).func_110143_aJ();
        }
        return 0.0;
    }

    @i(f={"\u53d6\u5b9e\u4f53\u6700\u5927\u8840\u91cf"})
    public static double ALLATORIxDEMO(v a2) {
        Entity a3 = hf.ALLATORIxDEMO(a2).ALLATORIxDEMO();
        if (a3 instanceof EntityLivingBase) {
            return ((EntityLivingBase)a3).func_110138_aP();
        }
        return 0.0;
    }
}

