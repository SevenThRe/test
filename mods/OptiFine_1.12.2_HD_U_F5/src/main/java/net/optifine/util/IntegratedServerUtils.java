/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  avj
 *  axw
 *  axw$a
 *  aym
 *  ayn
 *  bib
 *  bsb
 *  chd
 *  et
 *  oo
 *  vg
 */
package net.optifine.util;

import java.util.UUID;

public class IntegratedServerUtils {
    public static oo getWorldServer() {
        bib mc = Config.getMinecraft();
        bsb world = mc.f;
        if (world == null) {
            return null;
        }
        if (!mc.D()) {
            return null;
        }
        chd is = mc.F();
        if (is == null) {
            return null;
        }
        aym wp = world.s;
        if (wp == null) {
            return null;
        }
        ayn wd = wp.q();
        try {
            oo ws = is.a(wd.a());
            return ws;
        }
        catch (NullPointerException e) {
            return null;
        }
    }

    public static vg getEntity(UUID uuid) {
        oo ws = IntegratedServerUtils.getWorldServer();
        if (ws == null) {
            return null;
        }
        vg e = ws.a(uuid);
        return e;
    }

    public static avj getTileEntity(et pos) {
        oo ws = IntegratedServerUtils.getWorldServer();
        if (ws == null) {
            return null;
        }
        axw chunk = ws.r().a(pos.p() >> 4, pos.r() >> 4);
        if (chunk == null) {
            return null;
        }
        avj te = chunk.a(pos, axw.a.c);
        return te;
    }
}

