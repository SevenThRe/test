/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelBox
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.client.model.TexturedQuad
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.util.text.ITextComponent
 *  net.minecraft.util.text.TextComponentString
 *  net.minecraftforge.fml.relauncher.ReflectionHelper
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 *  org.apache.commons.compress.utils.Charsets
 *  org.apache.commons.io.FileUtils
 */
package eos.moe.dragoncore;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.model.TexturedQuad;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.fml.relauncher.ReflectionHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.commons.compress.utils.Charsets;
import org.apache.commons.io.FileUtils;

@SideOnly(value=Side.CLIENT)
public class zy {
    private File p;
    private ModelBase u;
    private int w = 1;
    private int a;
    private int e;
    private List<String> n = new ArrayList<String>();
    private List<String> j = new ArrayList<String>();
    private List<String> i = new ArrayList<String>();
    private int l = 0;
    private int z = 0;
    private int s = 0;
    private float g;
    private float t;
    private float r;
    private float x;
    private float v;
    private float m;
    private float c;
    private float q;
    private float b;
    private float o;
    private float y;
    private float k;
    private List<String> ALLATORIxDEMO = new ArrayList<String>();

    public zy() {
        zy a2;
    }

    public void ALLATORIxDEMO(String a2, ModelBase a3, String a4) {
        zy a5;
        if (!a5.ALLATORIxDEMO(a2, a4, "obj")) {
            return;
        }
        a5.u = a3;
        a5.a = a5.u.textureWidth;
        a5.e = a5.u.textureHeight;
        try {
            a5.ALLATORIxDEMO(a5.u.boxList);
        }
        catch (IOException a6) {
            a6.printStackTrace();
        }
    }

    private /* synthetic */ void ALLATORIxDEMO(List<ModelRenderer> a2) throws IOException {
        if (a2 == null) {
            return;
        }
        for (ModelRenderer a3 : a2) {
            zy a4;
            a4.f(a3);
        }
    }

    private /* synthetic */ void f(ModelRenderer a2) throws IOException {
        zy a3;
        if (!a2.showModel || a2.isHidden) {
            return;
        }
        a3.g += a2.rotationPointX;
        a3.t += a2.rotationPointY;
        a3.r += a2.rotationPointZ;
        a3.o = a2.rotateAngleX * 57.295776f;
        a3.y = a2.rotateAngleY * 57.295776f;
        a3.k = a2.rotateAngleZ * 57.295776f;
        a3.ALLATORIxDEMO(a2);
        if (a2.cubeList != null) {
            for (ModelBox a4 : a2.cubeList) {
                TexturedQuad[] a5;
                for (TexturedQuad a6 : a5 = (TexturedQuad[])ReflectionHelper.getPrivateValue(ModelBox.class, (Object)a4, (String[])new String[]{"quadList", "displayList"})) {
                    a3.ALLATORIxDEMO(a6);
                }
            }
        }
        a3.ALLATORIxDEMO();
        a3.n.clear();
        a3.j.clear();
        a3.i.clear();
        a3.ALLATORIxDEMO.clear();
        a3.ALLATORIxDEMO(a2.childModels);
        a3.g -= a2.rotationPointX;
        a3.t -= a2.rotationPointY;
        a3.r -= a2.rotationPointZ;
    }

    private /* synthetic */ void c(ModelRenderer a2) {
        zy a3;
        a3.x += a2.rotationPointX;
        a3.v += a2.rotationPointY;
        a3.m += a2.rotationPointZ;
        if (a3.o != 0.0f) {
            a3.t = (float)((double)a3.t * Math.cos(a3.o) + (double)a3.r * Math.sin(a3.o));
            a3.r = (float)((double)a3.t * -Math.sin(a3.o) + (double)a3.r * Math.cos(a3.o));
        }
        if (a3.y != 0.0f) {
            a3.g = (float)((double)a3.g * Math.cos(a3.y) + (double)a3.r * Math.sin(a3.y));
            a3.r = (float)((double)a3.g * -Math.sin(a3.y) + (double)a3.r * Math.cos(a3.y));
        }
        if (a3.k != 0.0f) {
            a3.g = (float)((double)a3.g * Math.cos(a3.k) + (double)a3.t * -Math.sin(a3.k));
            a3.t = (float)((double)a3.g * Math.sin(a3.k) + (double)a3.t * Math.cos(a3.k));
        }
    }

    private /* synthetic */ void ALLATORIxDEMO(float a2, float a3, float a4) {
        a.c = a2;
        a.q = a3;
        a.b = a4;
    }

    private /* synthetic */ void ALLATORIxDEMO() throws IOException {
        zy a2;
        FileUtils.writeLines((File)a2.p, a2.n, (boolean)true);
        FileUtils.writeLines((File)a2.p, a2.j, (boolean)true);
        FileUtils.writeLines((File)a2.p, a2.i, (boolean)true);
        FileUtils.writeLines((File)a2.p, a2.ALLATORIxDEMO, (boolean)true);
    }

    private /* synthetic */ void ALLATORIxDEMO(TexturedQuad a2) {
        Object a3;
        zy a4;
        Vec3d a5 = a2.vertexPositions[1].vector3D.subtractReverse(a2.vertexPositions[0].vector3D);
        Vec3d a6 = a2.vertexPositions[1].vector3D.subtractReverse(a2.vertexPositions[2].vector3D);
        Vec3d a7 = a6.crossProduct(a5).normalize();
        float a8 = (float)a7.x;
        float a9 = (float)a7.y;
        float a10 = (float)a7.z;
        int a11 = a4.l + 1;
        int a12 = a4.z + 1;
        int a13 = a4.s + 1;
        for (int a14 = 0; a14 < 4; ++a14) {
            a3 = a2.vertexPositions[a14];
            a4.ALLATORIxDEMO((float)a3.vector3D.x, (float)a3.vector3D.y, (float)a3.vector3D.z);
            String a15 = "v " + (a4.g + a4.c) + " " + -1.0f * (a4.t + a4.q) + " " + (a4.r + a4.b);
            a4.n.add(a15);
            ++a4.l;
            String a16 = "vt " + a3.texturePositionX + " " + a3.texturePositionY;
            a4.j.add(a16);
            ++a4.z;
        }
        String a17 = "vn " + a8 + " " + a9 + " " + a10;
        a4.i.add(a17);
        ++a4.s;
        a3 = "f " + a11++ + "/" + a12++ + "/" + a13 + " " + a11++ + "/" + a12++ + "/" + a13 + " " + a11++ + "/" + a12++ + "/" + a13 + " " + a11 + "/" + a12 + "/" + a13;
        a4.ALLATORIxDEMO.add((String)a3);
    }

    private /* synthetic */ void ALLATORIxDEMO(ModelRenderer a2) throws IOException {
        zy a3;
        String a4 = a2.boxName == null || a2.boxName.equals("null") ? "Cube." + a3.w++ : a2.boxName;
        FileUtils.write((File)a3.p, (CharSequence)("g " + a4 + "\n"), (Charset)Charsets.UTF_8, (boolean)true);
    }

    private /* synthetic */ boolean ALLATORIxDEMO(String a2, String a3, String a4) {
        boolean a5;
        File a6 = new File(a2);
        if (!a6.exists() && !a6.mkdirs()) {
            Minecraft.getMinecraft().player.sendMessage((ITextComponent)new TextComponentString("\u521b\u5efa\u6587\u4ef6\u5939\u5931\u8d25"));
            return false;
        }
        File a7 = new File(a2 + "/" + a3 + "." + a4);
        if (!a7.exists()) {
            try {
                a5 = a7.createNewFile();
            }
            catch (IOException a8) {
                Minecraft.getMinecraft().player.sendMessage((ITextComponent)new TextComponentString("\u521b\u5efa\u6587\u4ef6\u5931\u8d25"));
                a8.printStackTrace();
                return false;
            }
        }
        FileUtils.deleteQuietly((File)a7);
        try {
            a5 = a7.createNewFile();
        }
        catch (IOException a9) {
            Minecraft.getMinecraft().player.sendMessage((ITextComponent)new TextComponentString("\u521b\u5efa\u6587\u4ef6\u5931\u8d25"));
            a9.printStackTrace();
            return false;
        }
        if (!a5) {
            Minecraft.getMinecraft().player.sendMessage((ITextComponent)new TextComponentString("\u521b\u5efa\u6587\u4ef6\u5931\u8d25"));
            return false;
        }
        a.p = a7;
        return true;
    }
}

