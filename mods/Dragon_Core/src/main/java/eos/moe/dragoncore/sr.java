/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.util.math.Vec3d
 */
package eos.moe.dragoncore;

import java.util.List;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.math.Vec3d;
import org.yaml.snakeyamla.configuration.ConfigurationSection;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class sr {
    private Vec3d o;
    private Vec3d y;
    private Vec3d k;
    private boolean ALLATORIxDEMO;

    public sr(ConfigurationSection a2) {
        sr a3;
        if (a2 == null) {
            return;
        }
        List<Double> a4 = a2.getDoubleList("rotation");
        if (a4.size() == 3) {
            a3.o = new Vec3d(a4.get(0).doubleValue(), a4.get(1).doubleValue(), a4.get(2).doubleValue());
        }
        if ((a4 = a2.getDoubleList("translation")).size() == 3) {
            a3.y = new Vec3d(a4.get(0).doubleValue(), a4.get(1).doubleValue(), a4.get(2).doubleValue());
        }
        if ((a4 = a2.getDoubleList("scale")).size() == 3) {
            a3.k = new Vec3d(a4.get(0).doubleValue(), a4.get(1).doubleValue(), a4.get(2).doubleValue());
        }
        a3.ALLATORIxDEMO = a2.getBoolean("animation");
    }

    public void ALLATORIxDEMO() {
        sr a2;
        if (a2.o != null) {
            GlStateManager.func_179114_b((float)((float)a2.o.field_72450_a), (float)1.0f, (float)0.0f, (float)0.0f);
            GlStateManager.func_179114_b((float)((float)a2.o.field_72448_b), (float)0.0f, (float)1.0f, (float)0.0f);
            GlStateManager.func_179114_b((float)((float)a2.o.field_72449_c), (float)0.0f, (float)0.0f, (float)1.0f);
        }
        if (a2.y != null) {
            GlStateManager.func_179137_b((double)a2.y.field_72450_a, (double)a2.y.field_72448_b, (double)a2.y.field_72449_c);
        }
        if (a2.k != null) {
            GlStateManager.func_179139_a((double)a2.k.field_72450_a, (double)a2.k.field_72448_b, (double)a2.k.field_72449_c);
        }
    }

    public boolean ALLATORIxDEMO() {
        sr a2;
        return a2.ALLATORIxDEMO;
    }
}

