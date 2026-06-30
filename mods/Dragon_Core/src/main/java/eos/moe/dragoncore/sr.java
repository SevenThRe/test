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
            GlStateManager.rotate((float)((float)a2.o.x), (float)1.0f, (float)0.0f, (float)0.0f);
            GlStateManager.rotate((float)((float)a2.o.y), (float)0.0f, (float)1.0f, (float)0.0f);
            GlStateManager.rotate((float)((float)a2.o.z), (float)0.0f, (float)0.0f, (float)1.0f);
        }
        if (a2.y != null) {
            GlStateManager.translate((double)a2.y.x, (double)a2.y.y, (double)a2.y.z);
        }
        if (a2.k != null) {
            GlStateManager.scale((double)a2.k.x, (double)a2.k.y, (double)a2.k.z);
        }
    }

    public boolean ALLATORIxDEMO() {
        sr a2;
        return a2.ALLATORIxDEMO;
    }
}

