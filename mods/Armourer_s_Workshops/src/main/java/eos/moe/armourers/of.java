/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.GLAllocation
 *  org.lwjgl.opengl.GL11
 */
package eos.moe.armourers;

import java.util.concurrent.atomic.AtomicInteger;
import net.minecraft.client.renderer.GLAllocation;
import org.lwjgl.opengl.GL11;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class of {
    private static AtomicInteger s = new AtomicInteger(0);
    private boolean m = false;
    private int j;

    public void h() {
        of a2;
        if (a2.m) {
            GL11.glCallList((int)a2.j);
        }
    }

    public of() {
        of a2;
    }

    public void finalize() throws Throwable {
        of a2;
        if (a2.m) {
            a2.y();
        }
        super.finalize();
    }

    public void z() {
        of a2;
        if (a2.m) {
            a2.y();
        }
        a2.j = GLAllocation.func_74526_a((int)1);
        s.incrementAndGet();
        GL11.glNewList((int)a2.j, (int)4864);
    }

    public static int r() {
        return s.get();
    }

    public void y() {
        of a2;
        GLAllocation.func_74523_b((int)a2.j);
        s.decrementAndGet();
        a2.m = false;
    }

    public void r() {
        GL11.glEndList();
        a.m = true;
    }

    public boolean r() {
        of a2;
        return a2.m;
    }
}

