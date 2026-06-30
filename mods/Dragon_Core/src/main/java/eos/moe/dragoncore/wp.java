/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.OpenGlHelper
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.BufferUtils
 *  org.lwjgl.opengl.ARBBufferObject
 *  org.lwjgl.opengl.GL11
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.eu;
import eos.moe.dragoncore.ev;
import eos.moe.dragoncore.fr;
import eos.moe.dragoncore.hq;
import eos.moe.dragoncore.iq;
import eos.moe.dragoncore.ks;
import eos.moe.dragoncore.mz;
import eos.moe.dragoncore.ow;
import eos.moe.dragoncore.rs;
import eos.moe.dragoncore.rw;
import eos.moe.dragoncore.uv;
import eos.moe.dragoncore.vr;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.ARBBufferObject;
import org.lwjgl.opengl.GL11;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class wp {
    public hq j;
    public ArrayList<rw> i;
    public ArrayList<ev> l;
    public ArrayList<vr> z;
    public HashMap<String, vr> s;
    public HashMap<String, uv> g;
    public HashMap<uv, ArrayList<rw>> t;
    public eu r;
    private int x;
    public boolean v;
    public int m;
    public vr c;
    public int q;
    public int b;
    public int o;
    private FloatBuffer y;
    private FloatBuffer k;

    public wp(wp a3, hq a4) {
        wp a7;
        a7.i = new ArrayList(0);
        a7.l = new ArrayList(0);
        a7.z = new ArrayList(0);
        a7.s = new HashMap();
        a7.x = 0;
        a7.m = -1;
        a7.q = -1;
        a7.b = -1;
        a7.o = -1;
        a7.j = a4;
        a7.v = a3.v;
        for (rw rw2 : a3.i) {
            ev[] a8 = new ev[rw2.y.length];
            for (int a9 = 0; a9 < a8.length; ++a9) {
                ev a10 = new ev(rw2.y[a9]);
                fr.ALLATORIxDEMO(a7.l, a10.c);
                a7.l.set(a10.c, a10);
            }
        }
        a7.i.addAll(a3.i.stream().map(a2 -> {
            wp a3;
            return new rw((rw)a2, a3.l);
        }).collect(Collectors.toList()));
        for (int a5 = 0; a5 < a3.z.size(); ++a5) {
            vr vr2 = a3.z.get(a5);
            a7.z.add(new vr(vr2, null, a7));
        }
        for (int a5 = 0; a5 < a3.z.size(); ++a5) {
            vr vr3 = a3.z.get(a5);
            vr3.s.ALLATORIxDEMO(vr3, a7.z);
        }
        a7.c = a3.c.s;
        a4.sendBoneData(a7);
    }

    public wp(hq a2, ResourceLocation a3) throws ks {
        wp a4;
        a4.i = new ArrayList(0);
        a4.l = new ArrayList(0);
        a4.z = new ArrayList(0);
        a4.s = new HashMap();
        a4.x = 0;
        a4.m = -1;
        a4.q = -1;
        a4.b = -1;
        a4.o = -1;
        a4.j = a2;
        a4.v = false;
        if (a3.getPath().endsWith(".bmd")) {
            a4.ALLATORIxDEMO(a3, null);
        } else {
            a4.c(a3, null);
        }
        a4.f();
        a4.c();
        a2.sendBoneData(a4);
        hq.ALLATORIxDEMO("Number of vertices = " + a4.l.size());
    }

    public wp(hq a2, ResourceLocation a3, wp a4) throws ks {
        wp a5;
        a5.i = new ArrayList(0);
        a5.l = new ArrayList(0);
        a5.z = new ArrayList(0);
        a5.s = new HashMap();
        a5.x = 0;
        a5.m = -1;
        a5.q = -1;
        a5.b = -1;
        a5.o = -1;
        a5.j = a2;
        a5.v = true;
        if (a3.getPath().endsWith(".bmd")) {
            a5.ALLATORIxDEMO(a3, a4);
        } else {
            a5.c(a3, a4);
        }
        a5.f();
        a5.c();
        a2.sendBoneData(a5);
    }

    private /* synthetic */ void c(ResourceLocation a2, wp a3) throws ks {
        wp a4;
        BufferedInputStream a5 = fr.ALLATORIxDEMO(a2);
        try (BufferedReader a6 = new BufferedReader(new InputStreamReader(a5));){
            String a7;
            a4.m = 0;
            while ((a7 = a6.readLine()) != null) {
                ++a4.m;
                if (a7.startsWith("version")) continue;
                if (a7.startsWith("nodes")) {
                    ++a4.m;
                    while (!(a7 = a6.readLine()).startsWith("end")) {
                        a4.ALLATORIxDEMO(a7, ++a4.m, a3);
                    }
                    hq.ALLATORIxDEMO("Number of model bones = " + a4.z.size());
                    continue;
                }
                if (a7.startsWith("skeleton")) {
                    ++a4.m;
                    a6.readLine();
                    ++a4.m;
                    while (!(a7 = a6.readLine()).startsWith("end")) {
                        ++a4.m;
                        if (a4.v) continue;
                        a4.ALLATORIxDEMO(a7, a4.m);
                    }
                    continue;
                }
                if (!a7.startsWith("triangles")) continue;
                ++a4.m;
                while (!(a7 = a6.readLine()).startsWith("end")) {
                    uv a8 = a4.j.k ? a4.ALLATORIxDEMO(a7) : null;
                    String[] a9 = new String[3];
                    for (int a10 = 0; a10 < 3; ++a10) {
                        ++a4.m;
                        a9[a10] = a6.readLine();
                    }
                    a4.ALLATORIxDEMO(a9, a4.m, a8);
                }
            }
        }
        catch (Exception a11) {
            if (a4.m == -1) {
                throw new ks("there was a problem opening the model file : " + a2, a11);
            }
            throw new ks("an error occurred reading the SMD file \"" + a2 + "\" on line #" + a4.m, a11);
        }
        hq.ALLATORIxDEMO("Number of faces = " + a4.i.size());
    }

    private /* synthetic */ void ALLATORIxDEMO(ResourceLocation a3, wp a4) throws ks {
        BufferedInputStream a5 = fr.ALLATORIxDEMO(a3);
        try (DataInputStream a6 = new DataInputStream(a5);){
            int a7;
            int a8;
            short a9;
            int a10;
            wp a11;
            byte a12 = a6.readByte();
            assert (a12 == 1);
            int a13 = a6.readShort();
            fr.ALLATORIxDEMO(a11.z, a13 - 1);
            for (a10 = 0; a10 < a13; ++a10) {
                a9 = a6.readShort();
                a8 = a6.readShort();
                String a14 = wp.ALLATORIxDEMO(a6);
                vr a15 = a8 != -1 ? a11.z.get(a8) : null;
                a11.z.set(a9, new vr(a14, a9, a15, a11));
            }
            a10 = a6.readShort();
            for (a9 = 0; a9 < a10; a9 = (short)(a9 + 1)) {
                a8 = a6.readShort();
                for (int a16 = 0; a16 < a8; ++a16) {
                    short a17 = a6.readShort();
                    float a18 = a6.readFloat();
                    float a19 = a6.readFloat();
                    float a20 = a6.readFloat();
                    float a21 = a6.readFloat();
                    float a22 = a6.readFloat();
                    float a23 = a6.readFloat();
                    vr a24 = a11.z.get(a17);
                    a24.c(iq.ALLATORIxDEMO(a18, -a19, -a20, a21, -a22, -a23));
                }
            }
            ArrayList<String> a25 = new ArrayList<String>();
            a8 = a6.readShort();
            for (a7 = 0; a7 < a8; ++a7) {
                a25.add(wp.ALLATORIxDEMO(a6));
            }
            a8 = 0;
            a7 = a6.readShort();
            for (int a26 = 0; a26 < a7; ++a26) {
                String a27 = (String)a25.get(a6.readByte());
                ev[] a28 = new ev[3];
                ow[] a29 = new ow[3];
                for (int a30 = 0; a30 < 3; ++a30) {
                    a6.readShort();
                    float a31 = a6.readFloat();
                    float a32 = -a6.readFloat();
                    float a33 = -a6.readFloat();
                    float a34 = a6.readFloat();
                    float a35 = -a6.readFloat();
                    float a36 = -a6.readFloat();
                    float a37 = a6.readFloat();
                    float a38 = a6.readFloat();
                    int a39 = a8++;
                    ev a40 = a11.ALLATORIxDEMO(a31, a32, a33);
                    a28[a30] = a40 == null ? new ev(a31, a32, a33, a34, a35, a36, a11.x) : a40;
                    int a41 = a6.readByte();
                    for (int a42 = 0; a42 < a41; ++a42) {
                        short a43 = a6.readShort();
                        float a44 = a6.readFloat();
                        a11.z.get(a43).ALLATORIxDEMO(a28[a30], a44);
                    }
                    fr.ALLATORIxDEMO(a11.l, a39);
                    a11.l.set(a39, a28[a30]);
                    a29[a30] = new ow(a37, 1.0f - a38);
                }
                rw a45 = new rw(a28, a29);
                a45.y = a28;
                a45.k = a29;
                a11.i.add(a45);
                if (!a11.j.k) continue;
                uv a46 = a11.ALLATORIxDEMO(a27);
                if (a11.t == null) {
                    a11.t = new HashMap();
                }
                ArrayList a47 = a11.t.computeIfAbsent(a46, a2 -> new ArrayList());
                a47.add(a45);
            }
        }
        catch (IOException a48) {
            throw new ks("An error occurred while reading BMD " + a3.getPath(), a48);
        }
    }

    private /* synthetic */ uv ALLATORIxDEMO(String a2) throws ks {
        uv a3;
        wp a4;
        if (!a4.j.k) {
            return null;
        }
        if (a4.g == null) {
            a4.g = new HashMap();
        }
        if ((a3 = a4.g.get(a2)) != null) {
            return a3;
        }
        String a5 = a4.j.getMaterialPath(a2);
        URL a6 = wp.class.getResource(a5);
        try {
            File a7 = new File(a6.toURI());
            a3 = new uv(a7);
            a4.g.put(a2, a3);
            return a3;
        }
        catch (Exception a8) {
            throw new ks(a8);
        }
    }

    private /* synthetic */ void ALLATORIxDEMO(String a2, int a3, wp a4) {
        wp a5;
        vr a6;
        String[] a7 = a2.split("\"");
        int a8 = Integer.parseInt(rs.t.matcher(a7[0]).replaceAll(""));
        String a9 = a7[1];
        vr vr2 = a6 = a4 != null ? a4.ALLATORIxDEMO(a9) : null;
        if (a6 == null) {
            int a10 = Integer.parseInt(rs.t.matcher(a7[2]).replaceAll(""));
            vr a11 = a10 >= 0 ? a5.z.get(a10) : null;
            a6 = new vr(a9, a8, a11, a5);
        }
        fr.ALLATORIxDEMO(a5.z, a8);
        a5.z.set(a8, a6);
        a5.s.put(a9, a6);
        hq.ALLATORIxDEMO(a9);
    }

    private /* synthetic */ void ALLATORIxDEMO(String a2, int a3) {
        wp a4;
        String[] a5 = rs.s.split(a2);
        int a6 = Integer.parseInt(a5[0]);
        float[] a7 = new float[6];
        for (int a8 = 1; a8 < 7; ++a8) {
            a7[a8 - 1] = Float.parseFloat(a5[a8]);
        }
        vr a9 = a4.z.get(a6);
        a9.c(iq.ALLATORIxDEMO(a7[0], -a7[1], -a7[2], a7[3], -a7[4], -a7[5]));
    }

    private /* synthetic */ void ALLATORIxDEMO(String[] a2, int a3, uv a4) {
        wp a5;
        Object a6;
        ev[] a7 = new ev[3];
        ow[] a8 = new ow[3];
        for (int a9 = 0; a9 < 3; ++a9) {
            a6 = rs.s.split(a2[a9]);
            float a10 = Float.parseFloat(a6[1]);
            float a11 = -Float.parseFloat(a6[2]);
            float a12 = -Float.parseFloat(a6[3]);
            float a13 = Float.parseFloat(a6[4]);
            float a14 = -Float.parseFloat(a6[5]);
            float a15 = -Float.parseFloat((String)a6[6]);
            ev a16 = a5.ALLATORIxDEMO(a10, a11, a12);
            if (a16 == null) {
                a7[a9] = new ev(a10, a11, a12, a13, a14, a15, a5.x);
                fr.ALLATORIxDEMO(a5.l, a5.x);
                a5.l.set(a5.x, a7[a9]);
                ++a5.x;
            } else {
                a7[a9] = a16;
            }
            a8[a9] = new ow(Float.parseFloat((String)a6[7]), 1.0f - Float.parseFloat((String)a6[8]));
            if (((Object)a6).length <= 10) continue;
            a5.ALLATORIxDEMO((String[])a6, a7[a9]);
        }
        rw a17 = new rw(a7, a8);
        a17.y = a7;
        a17.k = a8;
        a5.i.add(a17);
        if (a4 != null) {
            if (a5.t == null) {
                a5.t = new HashMap();
            }
            if ((a6 = a5.t.get(a4)) == null) {
                a6 = new ArrayList();
                a5.t.put(a4, (ArrayList<rw>)a6);
            }
            ((ArrayList)a6).add(a17);
        }
    }

    private /* synthetic */ ev ALLATORIxDEMO(float a2, float a3, float a4) {
        wp a5;
        for (ev a6 : a5.l) {
            if (!a6.ALLATORIxDEMO(a2, a3, a4)) continue;
            return a6;
        }
        return null;
    }

    private /* synthetic */ void ALLATORIxDEMO(String[] a2, ev a3) {
        int a4;
        int a5 = Integer.parseInt(a2[9]);
        float[] a6 = new float[a5];
        float a7 = 0.0f;
        for (a4 = 0; a4 < a5; ++a4) {
            a6[a4] = Float.parseFloat(a2[a4 * 2 + 11]);
            a7 += a6[a4];
        }
        for (a4 = 0; a4 < a5; ++a4) {
            wp a8;
            int a9 = Integer.parseInt(a2[a4 * 2 + 10]);
            float a10 = a6[a4] / a7;
            a8.z.get(a9).ALLATORIxDEMO(a3, a10);
        }
    }

    private /* synthetic */ void f() {
        wp a2;
        for (int a4 = 0; a4 < a2.z.size(); ++a4) {
            vr a5 = a2.z.get(a4);
            a2.z.stream().filter(a3 -> a3.r == a5).forEach(a5::ALLATORIxDEMO);
        }
    }

    private /* synthetic */ void c() {
        wp a2;
        for (vr a3 : a2.z) {
            if (a3.r != null || a3.y.isEmpty()) continue;
            a2.c = a3;
            break;
        }
        if (a2.c == null) {
            for (vr a3 : a2.z) {
                if (a3.g.equals("blender_implicit")) continue;
                a2.c = a3;
                break;
            }
        }
    }

    public void ALLATORIxDEMO(eu a2) {
        a.r = a2;
    }

    public vr ALLATORIxDEMO(int a2) {
        try {
            wp a3;
            return a3.z.get(a2);
        }
        catch (IndexOutOfBoundsException a4) {
            return null;
        }
    }

    public vr ALLATORIxDEMO(String a2) {
        wp a3;
        for (vr a4 : a3.z) {
            if (!a4.g.equals(a2)) continue;
            return a4;
        }
        return null;
    }

    public mz ALLATORIxDEMO() {
        wp a2;
        return a2.r == null ? null : (a2.r.m == null ? null : (a2.r.m.isEmpty() ? null : a2.r.m.get(a2.r.q)));
    }

    public void ALLATORIxDEMO() {
        wp a2;
        a2.l.forEach(ev::f);
    }

    public void f(boolean a2, float a3) {
        wp a4;
        boolean a5 = true;
        if (!OpenGlHelper.useVbo()) {
            GL11.glBegin((int)4);
            if (!a4.j.k) {
                for (rw a6 : a4.i) {
                    a6.ALLATORIxDEMO(a5);
                }
            } else {
                for (Map.Entry<uv, ArrayList<rw>> a7 : a4.t.entrySet()) {
                    if (a7.getKey() == null) continue;
                    a7.getKey().c();
                    for (rw a8 : a7.getValue()) {
                        a8.ALLATORIxDEMO(a5);
                    }
                    a7.getKey().ALLATORIxDEMO();
                }
            }
            GL11.glEnd();
        } else {
            if (a2) {
                if (a4.q == -1) {
                    a4.q = ARBBufferObject.glGenBuffersARB();
                    a4.b = ARBBufferObject.glGenBuffersARB();
                    a4.o = ARBBufferObject.glGenBuffersARB();
                    a4.ALLATORIxDEMO(a5, a3);
                } else {
                    a4.c(a5, a3);
                }
            }
            OpenGlHelper.glBindBuffer((int)OpenGlHelper.GL_ARRAY_BUFFER, (int)a4.q);
            GL11.glVertexPointer((int)3, (int)5126, (int)0, (long)0L);
            OpenGlHelper.glBindBuffer((int)OpenGlHelper.GL_ARRAY_BUFFER, (int)a4.b);
            GL11.glTexCoordPointer((int)2, (int)5126, (int)0, (long)0L);
            OpenGlHelper.glBindBuffer((int)OpenGlHelper.GL_ARRAY_BUFFER, (int)a4.o);
            GL11.glNormalPointer((int)5126, (int)0, (long)0L);
            GL11.glEnableClientState((int)32884);
            GL11.glEnableClientState((int)32888);
            GL11.glEnableClientState((int)32885);
            GL11.glDrawArrays((int)4, (int)0, (int)(a4.i.size() * 3));
            GL11.glDisableClientState((int)32884);
            GL11.glDisableClientState((int)32888);
            GL11.glDisableClientState((int)32885);
            OpenGlHelper.glBindBuffer((int)OpenGlHelper.GL_ARRAY_BUFFER, (int)0);
        }
    }

    private /* synthetic */ void c(boolean a2, float a3) {
        wp a4;
        a4.y.clear();
        a4.k.clear();
        for (rw a5 : a4.i) {
            a5.ALLATORIxDEMO(a4.y, a4.k, a2, a3);
        }
        a4.y.flip();
        OpenGlHelper.glBindBuffer((int)OpenGlHelper.GL_ARRAY_BUFFER, (int)a4.q);
        ARBBufferObject.glBufferDataARB((int)OpenGlHelper.GL_ARRAY_BUFFER, (FloatBuffer)a4.y, (int)35044);
        a4.k.flip();
        OpenGlHelper.glBindBuffer((int)OpenGlHelper.GL_ARRAY_BUFFER, (int)a4.o);
        ARBBufferObject.glBufferDataARB((int)OpenGlHelper.GL_ARRAY_BUFFER, (FloatBuffer)a4.k, (int)35044);
    }

    private /* synthetic */ void ALLATORIxDEMO(boolean a2, float a3) {
        wp a4;
        a4.y = BufferUtils.createFloatBuffer((int)(a4.i.size() * 9));
        FloatBuffer a5 = BufferUtils.createFloatBuffer((int)(a4.i.size() * 6));
        a4.k = BufferUtils.createFloatBuffer((int)(a4.i.size() * 9));
        for (rw a6 : a4.i) {
            a6.ALLATORIxDEMO(a4.y, a5, a4.k, a2, a3);
        }
        a4.y.flip();
        OpenGlHelper.glBindBuffer((int)OpenGlHelper.GL_ARRAY_BUFFER, (int)a4.q);
        ARBBufferObject.glBufferDataARB((int)OpenGlHelper.GL_ARRAY_BUFFER, (FloatBuffer)a4.y, (int)35044);
        a5.flip();
        OpenGlHelper.glBindBuffer((int)OpenGlHelper.GL_ARRAY_BUFFER, (int)a4.b);
        ARBBufferObject.glBufferDataARB((int)OpenGlHelper.GL_ARRAY_BUFFER, (FloatBuffer)a5, (int)35044);
        a4.k.flip();
        OpenGlHelper.glBindBuffer((int)OpenGlHelper.GL_ARRAY_BUFFER, (int)a4.o);
        ARBBufferObject.glBufferDataARB((int)OpenGlHelper.GL_ARRAY_BUFFER, (FloatBuffer)a4.k, (int)35044);
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

