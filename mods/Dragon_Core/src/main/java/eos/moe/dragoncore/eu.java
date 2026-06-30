/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.util.vector.Matrix4f
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.fr;
import eos.moe.dragoncore.hq;
import eos.moe.dragoncore.iq;
import eos.moe.dragoncore.ks;
import eos.moe.dragoncore.mz;
import eos.moe.dragoncore.rs;
import eos.moe.dragoncore.vr;
import eos.moe.dragoncore.wp;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.util.vector.Matrix4f;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class eu {
    public hq v;
    public ArrayList<mz> m = new ArrayList();
    public ArrayList<vr> c = new ArrayList();
    public int q = 0;
    public int b;
    public int o;
    public String y;
    private int k = 0;

    public eu(hq a2, String a3, ResourceLocation a4) throws ks {
        eu a5;
        a5.v = a2;
        a5.y = a3;
        if (a4.getPath().endsWith(".bmd")) {
            a5.ALLATORIxDEMO(a4);
        } else {
            a5.c(a4);
        }
        a5.c();
        a5.ALLATORIxDEMO();
    }

    public eu(eu a3, hq a4) {
        eu a5;
        a5.v = a4;
        a5.y = a3.y;
        for (vr a6 : a3.c) {
            a5.c.add(new vr(a6, a6.r != null ? a5.c.get(a6.r.t) : null, null));
        }
        a5.m.addAll(a3.m.stream().map(a2 -> {
            eu a3;
            return new mz((mz)a2, a3);
        }).collect(Collectors.toList()));
        a5.o = a3.o;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private /* synthetic */ void c(ResourceLocation a2) throws ks {
        BufferedInputStream a3 = fr.ALLATORIxDEMO(a2);
        String a4 = null;
        int a5 = 0;
        try (BufferedReader a6 = new BufferedReader(new InputStreamReader(a3));){
            block11: while ((a4 = a6.readLine()) != null) {
                eu a7;
                ++a5;
                if (a4.startsWith("version")) continue;
                if (a4.startsWith("nodes")) {
                    ++a5;
                    while (true) {
                        if ((a4 = a6.readLine()).startsWith("end")) continue block11;
                        a7.ALLATORIxDEMO(a4, ++a5);
                    }
                }
                if (!a4.startsWith("skeleton")) continue;
                a7.ALLATORIxDEMO(a6, a5, a2);
            }
            return;
        }
        catch (IOException a8) {
            if (a5 != -1) throw new ks("an error occurred reading the SMD file \"" + a2 + "\" on line #" + a5, a8);
            throw new ks("there was a problem opening the model file : " + a2, a8);
        }
    }

    private /* synthetic */ void ALLATORIxDEMO(ResourceLocation a2) throws ks {
        BufferedInputStream a3 = fr.ALLATORIxDEMO(a2);
        try (DataInputStream a4 = new DataInputStream(a3);){
            Object a5;
            short a6;
            short a7;
            eu a8;
            byte a9 = a4.readByte();
            assert (a9 == 1);
            int a10 = a4.readShort();
            fr.ALLATORIxDEMO(a8.c, a10 - 1);
            for (int a11 = 0; a11 < a10; ++a11) {
                a7 = a4.readShort();
                a6 = a4.readShort();
                String a12 = eu.ALLATORIxDEMO(a4);
                a5 = a6 != -1 ? a8.c.get(a6) : null;
                a8.c.set(a7, new vr(a12, a7, (vr)a5, null));
            }
            LinkedHashMap a13 = new LinkedHashMap();
            a7 = a4.readShort();
            a8.o = a7;
            a6 = a7;
            for (short a14 = 0; a14 < a6; a14 = (short)(a14 + 1)) {
                a13.put(a14, new LinkedHashMap());
                a5 = (Map)a13.get(a14);
                int a15 = a4.readShort();
                for (int a16 = 0; a16 < a15; ++a16) {
                    short a17 = a4.readShort();
                    float a18 = a4.readFloat();
                    float a19 = a4.readFloat();
                    float a20 = a4.readFloat();
                    float a21 = a4.readFloat();
                    float a22 = a4.readFloat();
                    float a23 = a4.readFloat();
                    Matrix4f a24 = iq.ALLATORIxDEMO(a18, -a19, -a20, a21, -a22, -a23);
                    a5.put(a17, a24);
                }
                for (a15 = 0; a15 < a10; a15 = (short)(a15 + 1)) {
                    if (a5.containsKey((short)a15)) continue;
                    if (a14 == 0) {
                        throw new IOException("Missing bone definitions in first frame");
                    }
                    a5.put((short)a15, ((Map)a13.get((short)(a14 - 1))).get((short)a15));
                }
                a8.m.add(a14, new mz(a8));
                for (a15 = 0; a15 < a5.size(); a15 = (short)(a15 + 1)) {
                    a8.m.get(a14).ALLATORIxDEMO(a15, (Matrix4f)a5.get((short)a15));
                }
            }
        }
        catch (IOException a25) {
            throw new ks(a25);
        }
    }

    private /* synthetic */ void ALLATORIxDEMO(String a2, int a3) {
        eu a4;
        String[] a5 = a2.split("\"");
        int a6 = Integer.parseInt(rs.t.matcher(a5[0]).replaceAll(""));
        String a7 = a5[1];
        int a8 = Integer.parseInt(rs.t.matcher(a5[2]).replaceAll(""));
        vr a9 = a8 >= 0 ? a4.c.get(a8) : null;
        a4.c.add(a6, new vr(a7, a6, a9, null));
        hq.ALLATORIxDEMO(a7);
    }

    private /* synthetic */ void ALLATORIxDEMO(BufferedReader a2, int a3, ResourceLocation a4) throws ks {
        int a5 = a3;
        int a6 = 0;
        ++a5;
        String a7 = null;
        boolean a8 = a4.getPath().contains("walk");
        try {
            while ((a7 = a2.readLine()) != null) {
                eu a9;
                ++a5;
                String[] a10 = rs.s.split(a7);
                if (a10[0].equalsIgnoreCase("time")) {
                    a6 = Integer.parseInt(a10[1]);
                    a9.m.add(a6, new mz(a9));
                    continue;
                }
                if (a7.startsWith("end")) {
                    a9.o = a9.m.size();
                    hq.ALLATORIxDEMO("Total number of frames = " + a9.o);
                    return;
                }
                int a11 = Integer.parseInt(a10[0]);
                float[] a12 = new float[6];
                for (int a13 = 1; a13 < 7; ++a13) {
                    a12[a13 - 1] = Float.parseFloat(a10[a13]);
                }
                Matrix4f a14 = iq.ALLATORIxDEMO(a12[0], -a12[1], -a12[2], a12[3], -a12[4], -a12[5]);
                a9.m.get(a6).ALLATORIxDEMO(a11, a14);
            }
        }
        catch (Exception a15) {
            throw new ks("an error occurred reading the SMD file \"" + a4 + "\" on line #" + a5, a15);
        }
    }

    public int c() {
        eu a2;
        int a3 = a2.k++;
        return a3;
    }

    private /* synthetic */ void c() {
        eu a2;
        for (int a4 = 0; a4 < a2.c.size(); ++a4) {
            vr a5 = a2.c.get(a4);
            a2.c.stream().filter(a3 -> a3.r == a5).forEach(a5::ALLATORIxDEMO);
        }
    }

    public void ALLATORIxDEMO() {
        eu a2;
        int a3 = a2.v.t.c.t;
        for (mz a4 : a2.m) {
            a4.ALLATORIxDEMO(a3, 0.0f);
            a4.ALLATORIxDEMO();
        }
    }

    public void ALLATORIxDEMO(wp a2) {
        eu a3;
        for (mz a4 : a3.m) {
            a2.ALLATORIxDEMO();
            for (int a5 = 0; a5 < a2.z.size(); ++a5) {
                Matrix4f a6;
                vr a7 = a2.z.get(a5);
                try {
                    a6 = a4.o.get(a5);
                }
                catch (IndexOutOfBoundsException a8) {
                    a6 = new Matrix4f();
                }
                a7.ALLATORIxDEMO(a4, a6);
            }
        }
    }

    public int ALLATORIxDEMO() {
        eu a2;
        return a2.m.size();
    }

    public void ALLATORIxDEMO(int a2) {
        eu a3;
        if (a3.b != a2) {
            a3.q = a2;
            a3.b = a2;
        }
    }

    private static /* synthetic */ String ALLATORIxDEMO(DataInputStream a2) throws IOException {
        StringBuilder a3 = new StringBuilder();
        char a4 = '\u0000';
        do {
            if (a4 == '\u0000') continue;
            a3.append(a4);
        } while ((a4 = a2.readChar()) != '\u0000');
        return a3.toString();
    }
}

