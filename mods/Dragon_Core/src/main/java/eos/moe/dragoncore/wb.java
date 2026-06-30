/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.cf;
import eos.moe.dragoncore.dc;
import eos.moe.dragoncore.ec;
import eos.moe.dragoncore.hb;
import eos.moe.dragoncore.ic;
import eos.moe.dragoncore.jb;
import eos.moe.dragoncore.mg;
import eos.moe.dragoncore.re;
import eos.moe.dragoncore.ta;
import eos.moe.dragoncore.ua;
import eos.moe.dragoncore.ub;
import eos.moe.dragoncore.uc;
import eos.moe.dragoncore.vc;
import eos.moe.dragoncore.yk;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class wb
extends ub<hb> {
    private dc o;
    private mg y;
    private ua k;
    private Charset ALLATORIxDEMO;

    public wb(dc a2, mg a3, ua a4, Charset a5, jb a6) {
        super(a6);
        wb a7;
        a7.o = a2;
        a7.y = a3;
        a7.k = a4;
        a7.ALLATORIxDEMO = a5;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void ALLATORIxDEMO(hb a2, uc a3) throws IOException {
        wb a4;
        Map<String, String> a5 = a4.ALLATORIxDEMO(hb.ALLATORIxDEMO(a2));
        if (a5.size() == 0) {
            return;
        }
        File a6 = a4.ALLATORIxDEMO(a4.o.ALLATORIxDEMO().getPath());
        boolean a7 = false;
        try (RandomAccessFile a8 = new RandomAccessFile(a4.o.ALLATORIxDEMO(), ic.y.ALLATORIxDEMO());
             re a9 = new re(a6);){
            long a10 = 0L;
            List<ec> a11 = a4.ALLATORIxDEMO(a4.o.ALLATORIxDEMO().ALLATORIxDEMO());
            for (ec a12 : a11) {
                Map.Entry<String, String> a13 = a4.ALLATORIxDEMO(a12, a5);
                a3.ALLATORIxDEMO(a12.ALLATORIxDEMO());
                long a14 = a4.ALLATORIxDEMO(a11, a12, a4.o) - a9.ALLATORIxDEMO();
                if (a13 == null) {
                    a10 += a4.ALLATORIxDEMO(a8, a9, a10, a14, a3);
                } else {
                    String a15 = a4.ALLATORIxDEMO(a13.getValue(), a13.getKey(), a12.ALLATORIxDEMO());
                    byte[] a16 = a15.getBytes(a4.ALLATORIxDEMO);
                    int a17 = a16.length - a12.c();
                    a10 = a4.ALLATORIxDEMO(a16, a12, a10, a14, a8, a9, a3);
                    a4.ALLATORIxDEMO(a11, a12, a15, a16, a17);
                }
                a4.ALLATORIxDEMO();
            }
            a4.y.c(a4.o, a9, a4.ALLATORIxDEMO);
            a7 = true;
        }
        finally {
            a4.ALLATORIxDEMO(a7, a4.o.ALLATORIxDEMO(), a6);
        }
    }

    @Override
    public long ALLATORIxDEMO(hb a2) {
        wb a3;
        return a3.o.ALLATORIxDEMO().length();
    }

    @Override
    public vc ALLATORIxDEMO() {
        return vc.k;
    }

    private /* synthetic */ long ALLATORIxDEMO(byte[] a2, ec a3, long a4, long a5, RandomAccessFile a6, OutputStream a7, uc a8) throws IOException {
        wb a9;
        long a10 = a4;
        a10 += a9.ALLATORIxDEMO(a6, a7, a10, 26L, a8);
        a9.k.c(a7, a2.length);
        a10 += 2L;
        a10 += a9.ALLATORIxDEMO(a6, a7, a10, 2L, a8);
        a7.write(a2);
        long a11 = a5 - ((a10 += (long)a3.c()) - a4);
        a10 += a9.ALLATORIxDEMO(a6, a7, a10, a11, a8);
        return a10;
    }

    private /* synthetic */ Map.Entry<String, String> ALLATORIxDEMO(ec a2, Map<String, String> a3) {
        for (Map.Entry<String, String> a4 : a3.entrySet()) {
            if (!a2.ALLATORIxDEMO().startsWith(a4.getKey())) continue;
            return a4;
        }
        return null;
    }

    private /* synthetic */ void ALLATORIxDEMO(List<ec> a2, ec a3, String a4, byte[] a5, int a6) throws yk {
        wb a7;
        ec a8 = cf.c(a7.o, a3.ALLATORIxDEMO());
        if (a8 == null) {
            throw new yk("could not find any header with name: " + a3.ALLATORIxDEMO());
        }
        a8.ALLATORIxDEMO(a4);
        a8.c(a5.length);
        a7.ALLATORIxDEMO(a2, a7.o, a8, a6);
        a7.o.ALLATORIxDEMO().c(a7.o.ALLATORIxDEMO().c() + (long)a6);
        if (a7.o.c()) {
            a7.o.ALLATORIxDEMO().ALLATORIxDEMO(a7.o.ALLATORIxDEMO().ALLATORIxDEMO() + (long)a6);
            a7.o.ALLATORIxDEMO().ALLATORIxDEMO(a7.o.ALLATORIxDEMO().ALLATORIxDEMO() + (long)a6);
        }
    }

    private /* synthetic */ Map<String, String> ALLATORIxDEMO(Map<String, String> a2) throws yk {
        HashMap<String, String> a3 = new HashMap<String, String>();
        for (Map.Entry<String, String> a4 : a2.entrySet()) {
            wb a5;
            ec a6;
            if (!ta.ALLATORIxDEMO(a4.getKey()) || (a6 = cf.c(a5.o, a4.getKey())) == null) continue;
            if (a6.ALLATORIxDEMO() && !a4.getValue().endsWith("/")) {
                a3.put(a4.getKey(), a4.getValue() + "/");
                continue;
            }
            a3.put(a4.getKey(), a4.getValue());
        }
        return a3;
    }

    private /* synthetic */ String ALLATORIxDEMO(String a2, String a3, String a4) throws yk {
        if (a4.equals(a3)) {
            return a2;
        }
        if (a4.startsWith(a3)) {
            String a5 = a4.substring(a3.length());
            return a2 + a5;
        }
        throw new yk("old file name was neither an exact match nor a partial match");
    }
}

