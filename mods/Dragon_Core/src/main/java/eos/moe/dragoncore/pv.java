/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.ar;
import eos.moe.dragoncore.b;
import eos.moe.dragoncore.bx;
import eos.moe.dragoncore.ds;
import eos.moe.dragoncore.ep;
import eos.moe.dragoncore.fy;
import eos.moe.dragoncore.jw;
import eos.moe.dragoncore.kt;
import eos.moe.dragoncore.lv;
import eos.moe.dragoncore.mw;
import eos.moe.dragoncore.my;
import eos.moe.dragoncore.nh;
import eos.moe.dragoncore.nv;
import eos.moe.dragoncore.or;
import eos.moe.dragoncore.ts;
import eos.moe.dragoncore.vx;
import eos.moe.dragoncore.w;
import eos.moe.dragoncore.wu;
import eos.moe.dragoncore.wz;
import eos.moe.dragoncore.zq;
import eos.moe.dragoncore.zz;

public class pv
implements w {
    public ep ALLATORIxDEMO;

    public pv(ep a2) {
        pv a3;
        a3.ALLATORIxDEMO = a2;
    }

    @Override
    public b ALLATORIxDEMO(nh a2, vx a3, b a4) {
        pv a5;
        b a6 = a2.c(a5.ALLATORIxDEMO());
        switch (a3.ALLATORIxDEMO()) {
            case za: {
                return new or(a4, a6);
            }
            case ia: {
                return new my(a4, a6);
            }
            case sa: {
                return new lv(a4, a6);
            }
            case ga: {
                return new kt(a4, a6);
            }
            case f: {
                return new wu(a4, a6);
            }
            case ra: {
                return new wz(a4, a6);
            }
            case pa: {
                return new nv(a4, a6);
            }
            case la: {
                return new ts(a4, a6);
            }
            case ma: {
                return new mw(a4, a6);
            }
            case ha: {
                return new zz(a4, a6);
            }
            case aa: {
                return new jw(a4, a6);
            }
            case ba: {
                return new ar(a4, a6);
            }
            case h: {
                return new fy(a4, a6);
            }
            case d: {
                return new zq(a4, a6);
            }
            case da: {
                return new ds(a4, a6);
            }
            case ca: {
                return new bx(a4, a6);
            }
        }
        return null;
    }

    @Override
    public ep ALLATORIxDEMO() {
        pv a2;
        return a2.ALLATORIxDEMO;
    }

    public void ALLATORIxDEMO(ep a2) {
        a.ALLATORIxDEMO = a2;
    }
}

