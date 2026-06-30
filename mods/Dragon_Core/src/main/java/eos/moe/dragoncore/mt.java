/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelBox
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.client.renderer.BufferBuilder
 *  net.minecraft.client.renderer.GLAllocation
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.Tessellator
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.api.model.AnimationModelRenderer;
import eos.moe.dragoncore.api.model.IModelPiece;
import eos.moe.dragoncore.bax;
import eos.moe.dragoncore.gg;
import eos.moe.dragoncore.jv;
import eos.moe.dragoncore.nk;
import eos.moe.dragoncore.xu;
import java.io.Serializable;
import java.util.List;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;

public class mt
extends ModelRenderer
implements IModelPiece,
AnimationModelRenderer,
Serializable {
    private static final long v = 7269514370568333722L;
    public mt m;
    public List<xu> c;
    public bax q;
    private bax b = new bax(1.0f, 1.0f, 1.0f);
    private boolean o;
    private boolean y = true;
    private int k;
    public Runnable ALLATORIxDEMO;

    public boolean isRender() {
        mt a2;
        return a2.y;
    }

    public void setRender(boolean a2) {
        a.y = a2;
    }

    public mt(jv a2, bax a3, String a4, List<xu> a5, boolean a6) {
        super((ModelBase)a2, a4);
        mt a7;
        a7.q = a3;
        a7.rotateAngleX = a3.getX();
        a7.rotateAngleY = a3.getY();
        a7.rotateAngleZ = a3.getZ();
        a7.showModel = !a6;
        a7.c = a5;
    }

    @Override
    public String getName() {
        mt a2;
        return a2.boxName;
    }

    @Override
    public void setRotateAngle(float a2, float a3, float a4) {
        a.rotateAngleX = a2;
        a.rotateAngleY = a3;
        a.rotateAngleZ = a4;
    }

    @Override
    public void setOffsets(float a2, float a3, float a4) {
        a.offsetX = a2;
        a.offsetY = a3;
        a.offsetZ = a4;
    }

    @Override
    public bax getRotateAngle() {
        mt a2;
        return new bax(a2.rotateAngleX, a2.rotateAngleY, a2.rotateAngleZ);
    }

    @Override
    public bax getOffsets() {
        mt a2;
        return new bax(a2.offsetX, a2.offsetY, a2.offsetZ);
    }

    @Override
    public void render(float a2, boolean a3) {
        mt a4;
        if (a3) {
            a4.render2(a2);
        } else {
            a4.render(a2);
        }
    }

    public void render2(float a2) {
        mt a3;
        if (!a3.isHidden && a3.showModel) {
            GlStateManager.pushMatrix();
            GlStateManager.translate((float)(a3.offsetX * a2), (float)(a3.offsetY * a2), (float)(a3.offsetZ * a2));
            a3.ALLATORIxDEMO(a2);
            GlStateManager.scale((float)a3.b.getX(), (float)a3.b.getY(), (float)a3.b.getZ());
            if (a3.childModels != null) {
                for (ModelRenderer a4 : a3.childModels) {
                    a4.render(a2);
                }
            }
            GlStateManager.scale((float)(1.0f / a3.b.getX()), (float)(1.0f / a3.b.getY()), (float)(1.0f / a3.b.getZ()));
            GlStateManager.popMatrix();
        }
    }

    public void render(float a2) {
        mt a3;
        if (!a3.isHidden && a3.showModel) {
            if (!a3.o) {
                a3.compileDisplayList(a2);
            }
            GlStateManager.pushMatrix();
            GlStateManager.translate((float)(a3.offsetX * a2), (float)(a3.offsetY * a2), (float)(a3.offsetZ * a2));
            a3.ALLATORIxDEMO(a2);
            a3.c(a2);
            GlStateManager.popMatrix();
        }
    }

    public nk position(nk a2, float a3) {
        mt a4;
        if (a4.m != null) {
            a4.m.position(a2, a3);
        }
        a2.ALLATORIxDEMO((double)(a4.offsetX * a3), (double)(a4.offsetY * a3), (double)(a4.offsetZ * a3));
        if (a4.rotationPointX != 0.0f || a4.rotationPointY != 0.0f || a4.rotationPointZ != 0.0f) {
            a2.ALLATORIxDEMO((double)(a4.rotationPointX * a3), (double)(a4.rotationPointY * a3), (double)(a4.rotationPointZ * a3));
        }
        if (a4.rotateAngleZ != 0.0f) {
            a2.ALLATORIxDEMO(gg.o.ALLATORIxDEMO(a4.rotateAngleZ));
        }
        if (a4.rotateAngleY != 0.0f) {
            a2.ALLATORIxDEMO(gg.q.ALLATORIxDEMO(a4.rotateAngleY));
        }
        if (a4.rotateAngleX != 0.0f) {
            a2.ALLATORIxDEMO(gg.m.ALLATORIxDEMO(a4.rotateAngleX));
        }
        a2.x(nk.c(a4.b.getX(), a4.b.getY(), a4.b.getZ()));
        return a2;
    }

    public nk backPosition(nk a2, float a3) {
        mt a4;
        if (a4.rotationPointX != 0.0f || a4.rotationPointY != 0.0f || a4.rotationPointZ != 0.0f) {
            a2.ALLATORIxDEMO((double)(-a4.rotationPointX * a3), (double)(-a4.rotationPointY * a3), (double)(-a4.rotationPointZ * a3));
        }
        if (a4.m != null) {
            a4.m.backPosition(a2, a3);
        }
        return a2;
    }

    public void renderWithRotation(float a2) {
        mt a3;
        if (!a3.isHidden && a3.showModel) {
            if (!a3.o) {
                a3.compileDisplayList(a2);
            }
            GlStateManager.pushMatrix();
            a3.ALLATORIxDEMO(a2);
            GlStateManager.callList((int)a3.k);
            GlStateManager.popMatrix();
        }
    }

    private /* synthetic */ void c(float a2) {
        mt a3;
        GlStateManager.scale((float)a3.b.getX(), (float)a3.b.getY(), (float)a3.b.getZ());
        if (a3.y) {
            GlStateManager.callList((int)a3.k);
        }
        if (a3.childModels != null) {
            for (ModelRenderer a4 : a3.childModels) {
                a4.render(a2);
            }
        }
        GlStateManager.scale((float)(1.0f / a3.b.getX()), (float)(1.0f / a3.b.getY()), (float)(1.0f / a3.b.getZ()));
    }

    public void postRender(float a2) {
        mt a3;
        if (!a3.isHidden && a3.showModel) {
            if (!a3.o) {
                a3.compileDisplayList(a2);
            }
            a3.ALLATORIxDEMO(a2);
        }
    }

    public void applyTransAndRotations(float a2) {
        mt a3;
        GlStateManager.translate((float)(a3.offsetX * a2), (float)(a3.offsetY * a2), (float)(a3.offsetZ * a2));
        a3.ALLATORIxDEMO(a2);
    }

    private /* synthetic */ void ALLATORIxDEMO(float a2) {
        mt a3;
        if (a3.rotationPointX != 0.0f || a3.rotationPointY != 0.0f || a3.rotationPointZ != 0.0f) {
            GlStateManager.translate((float)(a3.rotationPointX * a2), (float)(a3.rotationPointY * a2), (float)(a3.rotationPointZ * a2));
        }
        if (a3.ALLATORIxDEMO != null) {
            a3.ALLATORIxDEMO.run();
        }
        if (a3.rotateAngleZ != 0.0f) {
            GlStateManager.rotate((float)a3.rotateAngleZ, (float)0.0f, (float)0.0f, (float)1.0f);
        }
        if (a3.rotateAngleY != 0.0f) {
            GlStateManager.rotate((float)a3.rotateAngleY, (float)0.0f, (float)1.0f, (float)0.0f);
        }
        if (a3.rotateAngleX != 0.0f) {
            GlStateManager.rotate((float)a3.rotateAngleX, (float)1.0f, (float)0.0f, (float)0.0f);
        }
    }

    public void resetData() {
        mt a2;
        a2.rotateAngleX = a2.q.getX();
        a2.rotateAngleY = a2.q.getY();
        a2.rotateAngleZ = a2.q.getZ();
        a2.offsetX = 0.0f;
        a2.offsetY = 0.0f;
        a2.offsetZ = 0.0f;
        a2.b.set(1.0f, 1.0f, 1.0f);
        if (a2.childModels != null) {
            for (ModelRenderer a3 : a2.childModels) {
                if (!(a3 instanceof mt)) continue;
                ((mt)a3).resetData();
            }
        }
    }

    public void compileDisplayList(float a2) {
        mt a3;
        a3.k = GLAllocation.generateDisplayLists((int)1);
        GlStateManager.glNewList((int)a3.k, (int)4864);
        BufferBuilder a4 = Tessellator.getInstance().getBuffer();
        for (ModelBox modelBox : a3.cubeList) {
            modelBox.render(a4, a2);
        }
        for (xu xu2 : a3.c) {
            xu2.ALLATORIxDEMO(a4, a2);
        }
        GlStateManager.glEndList();
        a3.o = true;
    }

    @Override
    public void setScaleFactor(float a2, float a3, float a4) {
        mt a5;
        a5.b.set(a2, a3, a4);
    }

    @Override
    public bax getScaleFactor() {
        mt a2;
        return a2.b;
    }

    @Override
    public bax getStartRotationAngles() {
        mt a2;
        return a2.q;
    }

    public mt getPiece(String a2) {
        mt a3;
        for (ModelRenderer a4 : a3.childModels) {
            if (!(a4 instanceof mt) || !a2.equalsIgnoreCase(a4.boxName)) continue;
            return (mt)a4;
        }
        return null;
    }

    public ModelRenderer findPiece(String a2) {
        mt a3;
        for (ModelRenderer a4 : a3.childModels) {
            if (a2.equalsIgnoreCase(a4.boxName)) {
                return a4;
            }
            if (!(a4 instanceof mt)) continue;
            mt a5 = (mt)a4;
            return a5.findPiece(a2);
        }
        return null;
    }

    public String getTopParentName() {
        mt a2;
        if (a2.m != null) {
            return a2.m.boxName;
        }
        return a2.boxName;
    }
}

