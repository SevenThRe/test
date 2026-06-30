/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.api.model.AnimationModel;
import eos.moe.dragoncore.ju;
import java.util.ConcurrentModificationException;
import java.util.List;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public abstract class vy
implements AnimationModel {
    public vy() {
        vy a2;
    }

    public abstract List<ju> ALLATORIxDEMO();

    @SideOnly(value=Side.CLIENT)
    public abstract void ALLATORIxDEMO(float var1);

    @SideOnly(value=Side.CLIENT)
    public abstract void ALLATORIxDEMO(float var1, String ... var2);

    @SideOnly(value=Side.CLIENT)
    public abstract void c(float var1, ju ... var2);

    @SideOnly(value=Side.CLIENT)
    public abstract void ALLATORIxDEMO(float var1, String var2);

    @SideOnly(value=Side.CLIENT)
    public abstract void ALLATORIxDEMO(float var1, ju var2);

    @SideOnly(value=Side.CLIENT)
    public abstract void ALLATORIxDEMO(float var1, ju ... var2);

    public abstract void ALLATORIxDEMO() throws ConcurrentModificationException;

    public abstract boolean ALLATORIxDEMO();

    public abstract void ALLATORIxDEMO(ju var1);
}

