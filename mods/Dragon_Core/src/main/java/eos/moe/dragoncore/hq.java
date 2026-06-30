/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.OpenGlHelper
 *  net.minecraft.client.renderer.block.model.IBakedModel
 *  net.minecraft.client.renderer.texture.TextureAtlasSprite
 *  net.minecraft.client.renderer.vertex.VertexFormat
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.ResourceLocation
 *  net.minecraftforge.client.model.IModel
 *  net.minecraftforge.common.model.IModelState
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.eu;
import eos.moe.dragoncore.ev;
import eos.moe.dragoncore.fr;
import eos.moe.dragoncore.g;
import eos.moe.dragoncore.gz;
import eos.moe.dragoncore.ks;
import eos.moe.dragoncore.rs;
import eos.moe.dragoncore.rx;
import eos.moe.dragoncore.vr;
import eos.moe.dragoncore.wia;
import eos.moe.dragoncore.wp;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.function.Function;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.IModel;
import net.minecraftforge.common.model.IModelState;

public class hq
extends ModelBase
implements IModel,
g {
    public static boolean g = false;
    public wp t;
    public vr r;
    public ArrayList<vr> x;
    public rx v;
    public ResourceLocation m;
    public boolean c;
    public boolean q;
    private boolean b;
    public HashMap<String, rx> o;
    public String y;
    public boolean k;
    private float ALLATORIxDEMO;

    public hq(hq a2) {
        hq a3;
        a3.q = true;
        a3.b = false;
        a3.o = new HashMap(4);
        a3.k = false;
        a3.ALLATORIxDEMO = -1.0f;
        a3.t = new wp(a2.t, a3);
        for (Map.Entry<String, rx> a4 : a2.o.entrySet()) {
            a3.o.put(a4.getKey().toLowerCase(Locale.ROOT), new rx(a4.getKey(), a4.getValue().k, a3));
        }
        a3.b = a2.b;
        a3.k = a2.k;
        a3.ALLATORIxDEMO = a2.ALLATORIxDEMO;
        a3.m = a2.m;
        a3.v = a3.o.get(gz.l.k.toLowerCase(Locale.ROOT));
        a3.c = a2.c;
    }

    public hq(ResourceLocation a2, boolean a3) throws ks {
        hq a4;
        a4.q = true;
        a4.b = false;
        a4.o = new HashMap(4);
        a4.k = false;
        a4.ALLATORIxDEMO = -1.0f;
        a4.c = a3;
        a4.m = a2;
        a4.ALLATORIxDEMO(a4.m);
        a4.ALLATORIxDEMO();
        a4.c();
    }

    public hq(ResourceLocation a2) throws ks {
        a3(a2, false);
        hq a3;
    }

    public float getScale() {
        hq a2;
        return a2.ALLATORIxDEMO;
    }

    public boolean hasAnimations() {
        hq a2;
        return a2.b;
    }

    private /* synthetic */ void c() {
        hq a2;
        for (rx a3 : a2.o.values()) {
            a3.ALLATORIxDEMO(a2.t);
        }
    }

    @Override
    public void renderAll() {
        block2: {
            GlStateManager.shadeModel((int)7425);
            try {
                hq a2;
                a2.t.f(a2.q, 1.0f);
                a2.q = false;
            }
            catch (Exception a3) {
                a3.printStackTrace();
                if (!OpenGlHelper.useVbo()) break block2;
                OpenGlHelper.glBindBuffer((int)OpenGlHelper.GL_ARRAY_BUFFER, (int)0);
            }
        }
        GlStateManager.shadeModel((int)7424);
    }

    public void sendBoneData(wp a2) {
        a.x = a2.z;
        if (!a2.v) {
            a.r = a2.c;
        }
    }

    private /* synthetic */ void ALLATORIxDEMO() {
        hq a2;
        a2.r.d();
        a2.x.forEach(vr::x);
    }

    public void animate() {
        hq a2;
        if (a2.t.r == null) {
            a2.setAnimation(gz.l.k);
        }
        a2.c(a2.t);
        a2.r.c();
        a2.x.forEach(vr::ALLATORIxDEMO);
        a2.ALLATORIxDEMO(a2.t);
        a2.q = true;
    }

    private /* synthetic */ void x(wp a2) {
        eu a3 = a2.r;
        --a3.q;
        if (a2.r.q < 0) {
            a2.r.q = a2.r.o - 1;
            boolean a4 = false;
            try {
                hq a5;
                gz a6 = gz.valueOf(a5.v.y.toUpperCase(Locale.ROOT));
                a4 = a6.y;
            }
            catch (Exception a6) {
                // empty catch block
            }
            a2.r.q = a4 ? a2.r.o - 1 : 0;
        }
    }

    private /* synthetic */ void f(wp a2) {
        Object a32;
        boolean a4 = false;
        try {
            hq a5;
            a32 = gz.valueOf(a5.v.y.toUpperCase(Locale.ROOT));
            a4 = a32.y;
        }
        catch (Exception a32) {
            // empty catch block
        }
        if (!a4) {
            if (a2.r.q > 0) {
                a32 = a2.r;
                ++((eu)a32).q;
            }
        } else {
            a32 = a2.r;
            ++((eu)a32).q;
            if (a2.r.q == a2.r.o) {
                a2.r.q = 0;
            }
        }
    }

    public void setAnimation(String a2) {
        hq a3;
        a3.v = a3.o.containsKey(a2 = a2.toLowerCase(Locale.ROOT)) ? a3.o.get(a2) : a3.o.get(gz.l.k.toLowerCase(Locale.ROOT));
        if (a3.v != null) {
            a3.t.ALLATORIxDEMO(a3.v.ALLATORIxDEMO());
        } else {
            a3.t.ALLATORIxDEMO((eu)null);
        }
    }

    public String getMaterialPath(String a2) {
        int a3;
        hq a4;
        String a5 = "/assets/dragoncore";
        if (!a4.y.startsWith("/")) {
            a5 = a5 + "/";
        }
        a5 = a5 + a4.y;
        if (!a2.startsWith("/")) {
            a5 = a5 + "/";
        }
        a5 = (a3 = (a5 = a5 + a2).lastIndexOf(".")) == -1 ? a5 + ".mat" : a5.substring(0, a3) + ".mat";
        return a5;
    }

    private /* synthetic */ void c(wp a2) {
        if (a2 == null) {
            return;
        }
        a2.l.forEach(ev::f);
    }

    private /* synthetic */ void ALLATORIxDEMO(wp a2) {
        if (a2 == null) {
            return;
        }
        a2.l.forEach(ev::ALLATORIxDEMO);
    }

    public Collection<ResourceLocation> getDependencies() {
        return null;
    }

    public Collection<ResourceLocation> getTextures() {
        return null;
    }

    public IBakedModel bake(IModelState a2, VertexFormat a3, Function<ResourceLocation, TextureAtlasSprite> a4) {
        return null;
    }

    public IModelState getDefaultState() {
        return null;
    }

    public static void ALLATORIxDEMO(Object a2) {
        if (g) {
            System.out.println(a2);
        }
    }

    private /* synthetic */ void ALLATORIxDEMO(ResourceLocation a2) throws ks {
        BufferedInputStream a3 = fr.ALLATORIxDEMO(a2);
        int a4 = 0;
        try (BufferedReader a5 = new BufferedReader(new InputStreamReader(a3));){
            hq a8;
            String a10;
            String[] a11 = null;
            ArrayList<String[]> a12 = new ArrayList<String[]>();
            while ((a10 = a5.readLine()) != null) {
                ++a4;
                String[] a9 = rs.s.split(a10);
                if (a9[0].equalsIgnoreCase("$body")) {
                    a11 = a9;
                    continue;
                }
                if (a9[0].equalsIgnoreCase("$anim")) {
                    a8.b = true;
                    a12.add(a9);
                    continue;
                }
                if (a9[0].equalsIgnoreCase("$cdmaterials")) {
                    a8.k = true;
                    a8.y = a9[1];
                    continue;
                }
                if (!a9[0].equalsIgnoreCase("$scale")) continue;
                a8.ALLATORIxDEMO = Float.parseFloat(a9[1]);
            }
            if (a8.ALLATORIxDEMO == -1.0f) {
                a8.ALLATORIxDEMO = 1.0f;
            }
            ResourceLocation a6 = a8.getResource((String)a11[1]);
            a8.t = new wp(a8, a6);
            HashMap<ResourceLocation, eu> a13 = new HashMap<ResourceLocation, eu>();
            for (String[] stringArray : a12) {
                String a7 = stringArray[1];
                ResourceLocation a62 = a8.getResource(stringArray[2]);
                if (a13.containsKey(a62)) continue;
                eu a15 = new eu(a8, a7, a62);
                a13.put(a62, a15);
            }
            for (String[] stringArray : a12) {
                String a7 = stringArray[1];
                ArrayList<eu> a9 = new ArrayList<eu>();
                for (int a16 = 2; a16 < stringArray.length; ++a16) {
                    ResourceLocation a17 = a8.getResource(stringArray[a16]);
                    if (!a13.containsKey(a17)) continue;
                    a9.add((eu)a13.get(a17));
                }
                a8.o.put(a7.toLowerCase(Locale.ROOT), new rx(a7, a9, a8));
                if (!a7.equalsIgnoreCase("idle")) continue;
                a8.v = a8.o.get(gz.l.k.toLowerCase(Locale.ROOT));
            }
        }
        catch (ks a18) {
            throw a18;
        }
        catch (Exception a19) {
            throw new ks("An error occurred while reading the " + a2.toString() + " PQC file on line #" + a4, a19);
        }
    }

    public ResourceLocation getResource(String a2) {
        hq a3;
        String a4 = a3.m.getPath();
        int a5 = a4.lastIndexOf(47);
        if (a5 == -1) {
            return new ResourceLocation("dragoncore", a2);
        }
        String a6 = a4.substring(0, a5);
        return new ResourceLocation("dragoncore", a6 + "/" + a2);
    }

    public void render(Entity a2, float a3, float a4, float a5, float a6, float a7, float a8) {
        hq a9;
        GlStateManager.pushMatrix();
        GlStateManager.translate((double)0.0, (double)1.501, (double)0.0);
        GlStateManager.rotate((float)90.0f, (float)1.0f, (float)0.0f, (float)0.0f);
        GlStateManager.rotate((float)180.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        GlStateManager.rotate((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        a9.renderAll();
        GlStateManager.popMatrix();
        a9.animate();
        if (a9.v != null && a9.v.ALLATORIxDEMO() != null) {
            if (a2 != null) {
                a9.v.ALLATORIxDEMO().ALLATORIxDEMO(a2.ticksExisted % a9.v.ALLATORIxDEMO().o);
            } else {
                a9.v.ALLATORIxDEMO().ALLATORIxDEMO(wia.k % a9.v.ALLATORIxDEMO().o);
            }
        }
    }
}

