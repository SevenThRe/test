/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.EnumFacing
 *  org.lwjgl.opengl.GL11
 */
package eos.moe.armourers;

import eos.moe.armourers.bo;
import eos.moe.armourers.dn;
import eos.moe.armourers.fk;
import eos.moe.armourers.if;
import eos.moe.armourers.kf;
import eos.moe.armourers.mk;
import eos.moe.armourers.mn;
import eos.moe.armourers.n;
import eos.moe.armourers.oh;
import eos.moe.armourers.rk;
import eos.moe.armourers.vn;
import eos.moe.armourers.yl;
import eos.moe.armourers.zg;
import eos.moe.armourers.zh;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.util.EnumFacing;
import org.lwjgl.opengl.GL11;

public class bj {
    public static float j = 0.0625f;

    public static void k(Entity a2) {
        if (!zh.g && a2 != Minecraft.func_71410_x().field_71439_g) {
            return;
        }
        ArrayList<fk> arrayList = new ArrayList<fk>();
        arrayList.addAll(zg.r(a2, vn.a));
        arrayList.addAll(zg.r(a2, vn.u));
        bj.r(arrayList, "rightArm", "armourers:chest.rightArm", "\u53f3\u80f3\u818a");
    }

    public static void p(Entity a2) {
        if (!zh.g && a2 != Minecraft.func_71410_x().field_71439_g) {
            return;
        }
        Object object = new ArrayList<fk>();
        object.addAll(zg.r(a2, vn.g));
        object.addAll(zg.r(a2, vn.u));
        object = object.iterator();
        while (object.hasNext()) {
            int n2;
            yl yl2 = ((fk)object.next()).r();
            if (yl2 == null) continue;
            ArrayList<kf> arrayList = yl2.y();
            int n3 = n2 = 0;
            while (n3 < arrayList.size()) {
                mk mk2;
                kf kf2 = arrayList.get(n2);
                GL11.glPushMatrix();
                GL11.glTranslated((double)0.0, (double)0.0, (double)(j * 2.0f));
                double d2 = 45.0;
                d2 = bo.r(a2, yl2, n2);
                if (kf2.r().y().equals("armourers:wings.leftWing")) {
                    mk2 = new mk(kf2, new dn(0.0625f, (n)new mn(), oh.l, 0, true, false, false, null), null);
                    bj.y(mk2, d2);
                }
                if (kf2.r().y().equals("armourers:wings.rightWing")) {
                    mk2 = new mk(kf2, new dn(0.0625f, (n)new mn(), oh.l, 0, true, false, false, null), null);
                    bj.r(mk2, -d2);
                }
                GL11.glPopMatrix();
                n3 = ++n2;
            }
            GL11.glColor3f((float)1.0f, (float)1.0f, (float)1.0f);
        }
    }

    public static void a(Entity a2) {
        if (!zh.g && a2 != Minecraft.func_71410_x().field_71439_g) {
            return;
        }
        ArrayList<fk> arrayList = new ArrayList<fk>();
        arrayList.addAll(zg.r(a2, vn.e));
        arrayList.addAll(zg.r(a2, vn.u));
        bj.r(arrayList, "rightLeg", "armourers:legs.rightLeg", "\u53f3\u5c0f\u817f");
    }

    /*
     * Enabled aggressive block sorting
     */
    private static /* synthetic */ void y(mk a2, double a3) {
        GL11.glPushMatrix();
        if if_ = new if(0, 0, 0);
        EnumFacing enumFacing = EnumFacing.DOWN;
        if (a2.r().r() > 0) {
            mk mk2 = a2;
            if_ = mk2.r().r(0);
            enumFacing = mk2.r().r(0);
        }
        GL11.glTranslated((double)(j * 0.5f), (double)(j * 0.5f), (double)(j * 0.5f));
        GL11.glTranslated((double)(j * (float)if_.z()), (double)(j * (float)if_.r()), (double)(j * (float)if_.y()));
        switch (enumFacing) {
            case UP: {
                GL11.glRotated((double)a3, (double)0.0, (double)1.0, (double)0.0);
                break;
            }
            case DOWN: {
                GL11.glRotated((double)a3, (double)0.0, (double)-1.0, (double)0.0);
                break;
            }
            case SOUTH: {
                GL11.glRotated((double)a3, (double)0.0, (double)0.0, (double)-1.0);
                break;
            }
            case NORTH: {
                GL11.glRotated((double)a3, (double)0.0, (double)0.0, (double)1.0);
                break;
            }
            case EAST: {
                GL11.glRotated((double)a3, (double)1.0, (double)0.0, (double)0.0);
                break;
            }
            case WEST: {
                GL11.glRotated((double)a3, (double)-1.0, (double)0.0, (double)0.0);
                break;
            }
        }
        GL11.glTranslated((double)(j * (float)(-if_.z())), (double)(j * (float)(-if_.r())), (double)(j * (float)(-if_.y())));
        GL11.glTranslated((double)(j * -0.5f), (double)(j * -0.5f), (double)(j * -0.5f));
        rk.j.renderPart(a2);
        GL11.glPopMatrix();
    }

    /*
     * Enabled aggressive block sorting
     */
    private static /* synthetic */ void r(mk a2, double a3) {
        GL11.glPushMatrix();
        if if_ = new if(0, 0, 0);
        EnumFacing enumFacing = EnumFacing.DOWN;
        if (a2.r().r() > 0) {
            mk mk2 = a2;
            if_ = mk2.r().r(0);
            enumFacing = mk2.r().r(0);
        }
        GL11.glTranslated((double)(j * 0.5f), (double)(j * 0.5f), (double)(j * 0.5f));
        GL11.glTranslated((double)(j * (float)if_.z()), (double)(j * (float)if_.r()), (double)(j * (float)if_.y()));
        switch (enumFacing) {
            case UP: {
                GL11.glRotated((double)a3, (double)0.0, (double)1.0, (double)0.0);
                break;
            }
            case DOWN: {
                GL11.glRotated((double)a3, (double)0.0, (double)-1.0, (double)0.0);
                break;
            }
            case SOUTH: {
                GL11.glRotated((double)a3, (double)0.0, (double)0.0, (double)-1.0);
                break;
            }
            case NORTH: {
                GL11.glRotated((double)a3, (double)0.0, (double)0.0, (double)1.0);
                break;
            }
            case EAST: {
                GL11.glRotated((double)a3, (double)1.0, (double)0.0, (double)0.0);
                break;
            }
            case WEST: {
                GL11.glRotated((double)a3, (double)-1.0, (double)0.0, (double)0.0);
                break;
            }
        }
        GL11.glTranslated((double)(j * (float)(-if_.z())), (double)(j * (float)(-if_.r())), (double)(j * (float)(-if_.y())));
        GL11.glTranslated((double)(j * -0.5f), (double)(j * -0.5f), (double)(j * -0.5f));
        rk.j.renderPart(a2);
        GL11.glPopMatrix();
    }

    public static void f(Entity a2) {
        if (!zh.g && a2 != Minecraft.func_71410_x().field_71439_g) {
            return;
        }
        ArrayList<fk> arrayList = new ArrayList<fk>();
        arrayList.addAll(zg.r(a2, vn.a));
        arrayList.addAll(zg.r(a2, vn.u));
        bj.r(arrayList, "leftArm", "armourers:chest.leftArm", "\u5de6\u80f3\u818a");
    }

    public static void l(Entity a2) {
        if (!zh.g && a2 != Minecraft.func_71410_x().field_71439_g) {
            return;
        }
        ArrayList<fk> arrayList = new ArrayList<fk>();
        arrayList.addAll(zg.r(a2, vn.w));
        arrayList.addAll(zg.r(a2, vn.u));
        bj.r(arrayList, "rightFoot", "armourers:feet.rightFoot", "\u53f3\u811a");
    }

    public static void n(Entity a2) {
        if (!zh.g && a2 != Minecraft.func_71410_x().field_71439_g) {
            return;
        }
        ArrayList<fk> arrayList = new ArrayList<fk>();
        arrayList.addAll(zg.r(a2, vn.w));
        arrayList.addAll(zg.r(a2, vn.u));
        bj.r(arrayList, "leftFoot", "armourers:feet.leftFoot", "\u5de6\u811a");
    }

    public static void v(Entity a2) {
        if (!zh.g && a2 != Minecraft.func_71410_x().field_71439_g) {
            return;
        }
        ArrayList<fk> arrayList = new ArrayList<fk>();
        arrayList.addAll(zg.r(a2, vn.e));
        arrayList.addAll(zg.r(a2, vn.u));
        bj.r(arrayList, "leftLeg", "armourers:legs.leftLeg", "\u5de6\u5c0f\u817f");
    }

    public static void w(Entity a2) {
        if (!zh.g && a2 != Minecraft.func_71410_x().field_71439_g) {
            return;
        }
        ArrayList<fk> arrayList = new ArrayList<fk>();
        arrayList.addAll(zg.r(a2, vn.a));
        arrayList.addAll(zg.r(a2, vn.u));
        bj.r(arrayList, "leftArm", "armourers:chest.leftArm", "\u5de6\u624b\u81c2");
    }

    public static void s(Entity a2) {
        if (!zh.g && a2 != Minecraft.func_71410_x().field_71439_g) {
            return;
        }
        ArrayList<fk> arrayList = new ArrayList<fk>();
        arrayList.addAll(zg.r(a2, vn.a));
        arrayList.addAll(zg.r(a2, vn.u));
        bj.r(arrayList, "base", "armourers:chest.base", null);
    }

    public bj() {
        bj a2;
    }

    private static /* synthetic */ void r(List<fk> a2, String a3, String a4, String a5) {
        a3 = a2.iterator();
        block0: while (true) {
            Object object = a3;
            while (object.hasNext()) {
                Object object2 = ((fk)a3.next()).r();
                if (object2 == null) {
                    object = a3;
                    continue;
                }
                Object object3 = object2 = ((yl)object2).y().iterator();
                while (true) {
                    if (!object3.hasNext()) continue block0;
                    kf kf2 = (kf)object2.next();
                    GL11.glPushMatrix();
                    if (kf2.r().y().equals(a4)) {
                        GL11.glColor3f((float)1.0f, (float)1.0f, (float)1.0f);
                        rk.j.renderPart(new mk(kf2, new dn(0.0625f, (n)new mn(), oh.l, 0, true, false, false, null), a5));
                    }
                    GL11.glPopMatrix();
                    object3 = object2;
                }
            }
            break;
        }
    }

    public static void x(Entity a2) {
        if (!zh.g && a2 != Minecraft.func_71410_x().field_71439_g) {
            return;
        }
        ArrayList<fk> arrayList = new ArrayList<fk>();
        arrayList.addAll(zg.r(a2, vn.e));
        arrayList.addAll(zg.r(a2, vn.u));
        bj.r(arrayList, "base", "armourers:legs.skirt", null);
    }

    public static void h(Entity a2) {
        if (!zh.g && a2 != Minecraft.func_71410_x().field_71439_g) {
            return;
        }
        ArrayList<fk> arrayList = new ArrayList<fk>();
        arrayList.addAll(zg.r(a2, vn.a));
        arrayList.addAll(zg.r(a2, vn.u));
        bj.r(arrayList, "rightArm", "armourers:chest.rightArm", "\u53f3\u624b\u81c2");
    }

    public static void z(Entity a2) {
        if (!zh.g && a2 != Minecraft.func_71410_x().field_71439_g) {
            return;
        }
        ArrayList<fk> arrayList = new ArrayList<fk>();
        arrayList.addAll(zg.r(a2, vn.l));
        arrayList.addAll(zg.r(a2, vn.u));
        bj.r(arrayList, "base", "armourers:head.base", null);
    }

    public static void y(Entity a2) {
        if (!zh.g && a2 != Minecraft.func_71410_x().field_71439_g) {
            return;
        }
        ArrayList<fk> arrayList = new ArrayList<fk>();
        arrayList.addAll(zg.r(a2, vn.e));
        arrayList.addAll(zg.r(a2, vn.u));
        bj.r(arrayList, "leftLeg", "armourers:legs.leftLeg", "\u5de6\u5927\u817f");
    }

    public static void r(Entity a2) {
        if (!zh.g && a2 != Minecraft.func_71410_x().field_71439_g) {
            return;
        }
        ArrayList<fk> arrayList = new ArrayList<fk>();
        arrayList.addAll(zg.r(a2, vn.e));
        arrayList.addAll(zg.r(a2, vn.u));
        bj.r(arrayList, "rightLeg", "armourers:legs.rightLeg", "\u53f3\u5927\u817f");
    }
}

