/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.bc;
import eos.moe.dragoncore.jb;
import eos.moe.dragoncore.mc;
import eos.moe.dragoncore.uc;
import eos.moe.dragoncore.vc;
import eos.moe.dragoncore.yk;
import eos.moe.dragoncore.zk;
import java.io.IOException;
import java.util.concurrent.ExecutorService;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public abstract class ob<T> {
    private uc y;
    private boolean k;
    private ExecutorService ALLATORIxDEMO;

    public ob(jb a2) {
        ob a3;
        a3.y = jb.ALLATORIxDEMO(a2);
        a3.k = jb.ALLATORIxDEMO(a2);
        a3.ALLATORIxDEMO = jb.ALLATORIxDEMO(a2);
    }

    public void c(T a2) throws yk {
        ob a3;
        a3.y.c();
        a3.y.ALLATORIxDEMO(mc.k);
        a3.y.ALLATORIxDEMO(a3.ALLATORIxDEMO());
        if (a3.k) {
            long a4 = a3.ALLATORIxDEMO(a2);
            a3.y.ALLATORIxDEMO(a4);
            a3.ALLATORIxDEMO.execute(() -> {
                ob a3;
                try {
                    a3.c(a2, a3.y);
                }
                catch (yk yk2) {
                }
                finally {
                    a3.ALLATORIxDEMO.shutdown();
                }
            });
        } else {
            a3.c(a2, a3.y);
        }
    }

    private /* synthetic */ void c(T a2, uc a3) throws yk {
        try {
            ob a4;
            a4.ALLATORIxDEMO(a2, a3);
            a3.f();
        }
        catch (yk a5) {
            a3.c(a5);
            throw a5;
        }
        catch (Exception a6) {
            a3.c(a6);
            throw new yk(a6);
        }
    }

    public void ALLATORIxDEMO() throws yk {
        ob a2;
        if (!a2.y.c()) {
            return;
        }
        a2.y.ALLATORIxDEMO(bc.k);
        a2.y.ALLATORIxDEMO(mc.y);
        throw new yk("Task cancelled", zk.q);
    }

    public abstract void ALLATORIxDEMO(T var1, uc var2) throws IOException;

    public abstract long ALLATORIxDEMO(T var1) throws yk;

    public abstract vc ALLATORIxDEMO();
}

