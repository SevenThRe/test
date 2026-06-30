/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.armourers;

import eos.moe.armourers.kc;
import eos.moe.armourers.nb;
import eos.moe.armourers.ph;
import eos.moe.armourers.yn;
import eos.moe.armourers.zc;
import java.io.IOException;

public class ac
extends kc<yn> {
    @Override
    public yn r(nb a2, char[] a3) throws ph, IOException {
        ac a4;
        return new yn(a3, a2.r(), a4.y());
    }

    public ac(zc a2, nb a3, char[] a4) throws IOException, ph {
        super(a2, a3, a4);
        ac a5;
    }

    private /* synthetic */ byte[] y() throws IOException {
        ac a2;
        byte[] byArray = new byte[12];
        a2.r(byArray);
        return byArray;
    }
}

