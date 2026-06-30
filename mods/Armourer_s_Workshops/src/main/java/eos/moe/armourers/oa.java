/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.armourers;

import eos.moe.armourers.ca;
import eos.moe.armourers.ib;
import eos.moe.armourers.jb;
import eos.moe.armourers.ob;
import eos.moe.armourers.ph;
import eos.moe.armourers.rb;
import eos.moe.armourers.uh;
import java.io.IOException;
import java.util.concurrent.ExecutorService;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public abstract class oa<T> {
    private rb s;
    private ExecutorService m;
    private boolean j;

    public abstract long r(T var1) throws ph;

    public abstract void y(T var1, rb var2) throws IOException;

    public abstract ob r();

    public oa(ca a2) {
        oa a3;
        oa oa2 = a3;
        ca ca2 = a2;
        a3.s = ca.r(ca2);
        oa2.j = ca.r(ca2);
        oa2.m = ca.r(a2);
    }

    private /* synthetic */ void r(T a22, rb a3) throws ph {
        try {
            oa a4;
            a4.y(a22, a3);
            a3.r();
            return;
        }
        catch (ph a22) {
            ph ph2 = a22;
            a3.y(ph2);
            throw ph2;
        }
        catch (Exception a22) {
            a3.y(a22);
            throw new ph(a22);
        }
    }

    public void r() throws ph {
        oa a2;
        if (!a2.s.r()) {
            return;
        }
        oa oa2 = a2;
        oa2.s.r(jb.s);
        oa2.s.r(ib.j);
        throw new ph("Task cancelled", uh.l);
    }

    public void r(T a2) throws ph {
        oa a3;
        oa oa2 = a3;
        oa2.s.y();
        oa2.s.r(ib.m);
        oa2.s.r(a3.r());
        if (oa2.j) {
            oa oa3 = a3;
            long l2 = oa3.r(a2);
            oa3.s.y(l2);
            oa3.m.execute(() -> {
                oa a3;
                try {
                    a3.r(a2, a3.s);
                    return;
                }
                catch (ph a22) {
                    return;
                }
                finally {
                    a3.m.shutdown();
                }
            });
            return;
        }
        a3.r(a2, a3.s);
    }
}

