/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.armourers;

import eos.moe.armourers.ca;
import eos.moe.armourers.ea;
import eos.moe.armourers.gb;
import eos.moe.armourers.kb;
import eos.moe.armourers.oa;
import eos.moe.armourers.ob;
import eos.moe.armourers.pc;
import eos.moe.armourers.ph;
import eos.moe.armourers.rb;
import eos.moe.armourers.vl;
import java.io.IOException;
import java.nio.charset.Charset;

public class ia
extends oa<ea> {
    private kb j;

    @Override
    public long r(ea a2) {
        return 0L;
    }

    /*
     * Loose catch block
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void r(ea a2222222, rb a3222222) throws IOException {
        Throwable throwable;
        pc pc2;
        block14: {
            ia a4;
            if (ea.r(a2222222) == null) {
                throw new ph("comment is null, cannot update Zip file with comment");
            }
            a3222222 = a4.j.r();
            ((gb)a3222222).r(ea.r(a2222222));
            pc2 = new pc(a4.j.r());
            throwable = null;
            if (a4.j.r()) {
                pc2.r(a4.j.r().x());
            } else {
                pc2.r(((gb)a3222222).r());
            }
            a3222222 = new vl();
            ((vl)a3222222).y(a4.j, pc2, (Charset)((Object)a2222222.j));
            if (pc2 == null) return;
            if (throwable == null) break block14;
            try {
                pc2.close();
                return;
            }
            catch (Throwable a3222222) {
                throwable.addSuppressed(a3222222);
                return;
            }
        }
        pc2.close();
        return;
        catch (Throwable a3222222) {
            try {
                throwable = a3222222;
                throw throwable;
            }
            catch (Throwable throwable2) {
                Throwable throwable3;
                if (pc2 != null) {
                    if (throwable != null) {
                        try {
                            pc2.close();
                            throwable3 = throwable2;
                            throw throwable3;
                        }
                        catch (Throwable a2222222) {
                            throwable3 = throwable2;
                            throwable.addSuppressed(a2222222);
                            throw throwable3;
                        }
                    }
                    pc2.close();
                }
                throwable3 = throwable2;
                throw throwable3;
            }
        }
    }

    @Override
    public ob r() {
        return ob.w;
    }

    public ia(kb a2, ca a3) {
        super(a3);
        ia a4;
        a4.j = a2;
    }
}

