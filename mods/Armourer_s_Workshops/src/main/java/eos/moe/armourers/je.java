/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.Gui
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraftforge.fml.client.config.GuiUtils
 *  org.lwjgl.input.Mouse
 */
package eos.moe.armourers;

import eos.moe.armourers.af;
import eos.moe.armourers.ge;
import eos.moe.armourers.ql;
import eos.moe.armourers.t;
import eos.moe.armourers.th;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraftforge.fml.client.config.GuiUtils;
import org.lwjgl.input.Mouse;

public class je
extends ge {
    private t w;
    private Minecraft r;
    public int l;
    private String j;

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    @Override
    public void r(int a2, int a32, int a4, int a52, boolean a6, Tessellator a7) {
        List<String> a32 = af.l;
        synchronized (a32) {
            List<String> list;
            int n2;
            je a8;
            if (a2 >= af.l.size()) {
                return;
            }
            if (a8.l == a2) {
                n2 = a2;
                Gui.drawRect((int)(a8.f + 1), (int)(a4 - 1), (int)a8.s, (int)(a4 + 11), (int)-120);
            } else {
                if (a6) {
                    Gui.drawRect((int)(a8.f + 1), (int)(a4 - 1), (int)a8.s, (int)(a4 + 11), (int)-3355444);
                }
                n2 = a2;
            }
            if (af.r(n2)) {
                ql.r("armour-library.png");
                GlStateManager.color((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                GuiUtils.drawTexturedModalRect((int)(a8.f + 1), (int)(a4 + 1), (int)162, (int)0, (int)18, (int)10, (float)0.0f);
                String a52 = af.l.get(a2).substring(4);
                if (a2 == 0 && !af.r.isEmpty()) {
                    a52 = new StringBuilder().insert(0, a52).append("  \u5f53\u524d\u8def\u5f84:").append(af.r.substring(1)).toString();
                }
                a8.r.fontRenderer.drawString(a52, a8.f + 20, a4 + 1, -13697238);
                list = a32;
            } else {
                th.r(a8.f + 8, a4 + 5, 16.0f, 16.0f, af.l.get(a2));
                if (a8.l == a2) {
                    a8.r.fontRenderer.drawString(af.l.get(a2), a8.f + 14, a4 + 1, -16777216);
                    list = a32;
                } else {
                    a8.r.fontRenderer.drawString(af.l.get(a2), a8.f + 14, a4 + 1, 0xFFFFFF);
                    list = a32;
                }
            }
            // ** MonitorExit[v1] (shouldn't be in output)
            return;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public String r() {
        List<String> list = af.l;
        synchronized (list) {
            je a2;
            block6: {
                block5: {
                    if (a2.l < af.l.size() && a2.l >= 0) break block5;
                    return null;
                }
                if (!af.r(a2.l)) break block6;
                return null;
            }
            return af.l.get(a2.l);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void r(int a2, boolean a3) {
        if (a2 >= af.l.size()) {
            return;
        }
        a4.l = a2;
        List<String> list = af.l;
        synchronized (list) {
            if (!af.r(a2)) {
                a4.j = af.l.get(a2);
            }
            if (a3 && af.r(a2)) {
                je je2;
                je a4;
                if (af.l.get(a2).equals("DIR-../")) {
                    af.r = af.r.substring(0, af.r.lastIndexOf("/"));
                    je2 = a4;
                } else {
                    af.r = new StringBuilder().insert(0, af.r).append("/").append(af.l.get(a2).substring(4)).toString();
                    je2 = a4;
                }
                je2.l = -1;
                af.z();
            }
            return;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int y() {
        List<String> list = af.l;
        synchronized (list) {
            return af.l.size();
        }
    }

    @Override
    public void r(int a2, int a3, float a4) {
        je a5;
        je je2 = a5;
        super.r(a2, a3, a4);
        if (je2.j != null) {
            th.r(a2, a3, 25.0f, 25.0f, a5.j);
        }
        if (!Mouse.isButtonDown((int)0) && a5.j != null) {
            a5.w.dragSkin(a5.j, a2, a3);
            a5.j = null;
        }
    }

    public je(int a2, int a3, int a4, int a5, int a6, int a7, int a8, t a9) {
        je a10;
        je je2 = a10;
        int n2 = a3;
        int n3 = a5;
        super(Minecraft.getMinecraft(), a4, n3, n2, n2 + n3, a2, a6, a7, a8);
        je2.l = -1;
        je2.r = Minecraft.getMinecraft();
        je2.w = a9;
    }

    @Override
    public boolean r(int a2) {
        je a3;
        return a3.l == a2;
    }
}

