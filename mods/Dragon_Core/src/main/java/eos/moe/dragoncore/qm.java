/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.audio.ISound
 *  net.minecraft.client.audio.PositionedSoundRecord
 *  net.minecraft.client.gui.Gui
 *  net.minecraft.client.gui.GuiControls
 *  net.minecraft.client.renderer.BufferBuilder
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.vertex.DefaultVertexFormats
 *  net.minecraft.init.SoundEvents
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraft.util.SoundEvent
 *  org.apache.logging.log4j.Level
 *  org.lwjgl.input.Mouse
 *  org.lwjgl.opengl.GL11
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.bl;
import eos.moe.dragoncore.bp;
import eos.moe.dragoncore.fi;
import eos.moe.dragoncore.gr;
import eos.moe.dragoncore.le;
import eos.moe.dragoncore.ov;
import eos.moe.dragoncore.ph;
import eos.moe.dragoncore.sk;
import eos.moe.dragoncore.su;
import eos.moe.dragoncore.td;
import eos.moe.dragoncore.yh;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiControls;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.init.SoundEvents;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.SoundEvent;
import org.apache.logging.log4j.Level;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

public class qm
extends Gui {
    private Minecraft e = Minecraft.getMinecraft();
    private yh n;
    private List<sk> j;
    private le[] i;
    private bl[] l;
    private final int z = 10;
    private final int s = 10;
    private final int g = 30;
    private final int t;
    private int r;
    private int x;
    private int v;
    private int m;
    private int c;
    private int q;
    private int b;
    private ph<fi> o;
    private int y;
    private gr k;

    public ph<fi> getFocused() {
        qm a2;
        return a2.o;
    }

    public le getFocusedSaveSlot() {
        qm a2;
        return a2.y != -1 ? a2.i[a2.y] : null;
    }

    public yh getNBTTree() {
        qm a2;
        return a2.n;
    }

    public qm(yh a2) {
        qm a3;
        a3.t = Minecraft.getMinecraft().fontRenderer.FONT_HEIGHT + 2;
        a3.n = a2;
        a3.x = -1;
        a3.y = -1;
        a3.j = new ArrayList<sk>();
        a3.l = new bl[16];
        a3.i = new le[7];
    }

    private /* synthetic */ int c() {
        qm a2;
        return a2.ALLATORIxDEMO() - (a2.v - 30 + 2);
    }

    private /* synthetic */ int ALLATORIxDEMO() {
        qm a2;
        return a2.t * a2.j.size();
    }

    public gr getWindow() {
        qm a2;
        return a2.k;
    }

    public void initGUI(int a2, int a3, int a4) {
        qm a5;
        a5.m = a2;
        a5.c = a3;
        a5.v = a4;
        a5.x = -1;
        a5.initGUI(false);
        if (a5.k != null) {
            a5.k.initGUI((a2 - 178) / 2, (a3 - 93) / 2);
        }
    }

    public void updateScreen() {
        qm a2;
        if (a2.k != null) {
            a2.k.update();
        }
        if (a2.y != -1) {
            a2.i[a2.y].update();
        }
    }

    private /* synthetic */ void c(ph<fi> a2) {
        qm a3;
        if (a2 == null) {
            for (bl a4 : a3.l) {
                a4.setEnabled(false);
            }
        } else if (a2.ALLATORIxDEMO().ALLATORIxDEMO() instanceof NBTTagCompound) {
            for (bl a5 : a3.l) {
                a5.setEnabled(true);
            }
            a3.l[12].setEnabled(a2 != a3.n.ALLATORIxDEMO());
            a3.l[11].setEnabled(a2.ALLATORIxDEMO() && !(((fi)((ph)((Object)a2.ALLATORIxDEMO())).ALLATORIxDEMO()).ALLATORIxDEMO() instanceof NBTTagList));
            a3.l[13].setEnabled(true);
            a3.l[14].setEnabled(a2 != a3.n.ALLATORIxDEMO());
            a3.l[15].setEnabled(ov.c != null);
        } else if (a2.ALLATORIxDEMO().ALLATORIxDEMO() instanceof NBTTagList) {
            if (a2.c()) {
                byte a6 = ((fi)((ph)a2.ALLATORIxDEMO().get(0)).ALLATORIxDEMO()).ALLATORIxDEMO().getId();
                for (bl a7 : a3.l) {
                    a7.setEnabled(false);
                }
                a3.l[a6 - 1].setEnabled(true);
                a3.l[12].setEnabled(true);
                a3.l[11].setEnabled(!(((fi)((ph)((Object)a2.ALLATORIxDEMO())).ALLATORIxDEMO()).ALLATORIxDEMO() instanceof NBTTagList));
                a3.l[13].setEnabled(true);
                a3.l[14].setEnabled(true);
                a3.l[15].setEnabled(ov.c != null && ov.c.ALLATORIxDEMO().getId() == a6);
            } else {
                for (bl a8 : a3.l) {
                    a8.setEnabled(true);
                }
            }
            a3.l[11].setEnabled(!(((fi)((ph)((Object)a2.ALLATORIxDEMO())).ALLATORIxDEMO()).ALLATORIxDEMO() instanceof NBTTagList));
            a3.l[13].setEnabled(true);
            a3.l[14].setEnabled(true);
            a3.l[15].setEnabled(ov.c != null);
        } else {
            for (bl a9 : a3.l) {
                a9.setEnabled(false);
            }
            a3.l[12].setEnabled(true);
            a3.l[11].setEnabled(true);
            a3.l[13].setEnabled(true);
            a3.l[14].setEnabled(true);
            a3.l[15].setEnabled(false);
        }
        a3.o = a2;
        if (a3.o != null && a3.y != -1) {
            a3.stopEditingSlot();
        }
    }

    public void initGUI() {
        qm a2;
        a2.initGUI(false);
    }

    public void initGUI(boolean a2) {
        qm a3;
        a3.r = 30;
        a3.j.clear();
        a3.ALLATORIxDEMO(a3.n.ALLATORIxDEMO(), 10);
        a3.d();
        a3.k();
        if (a3.o != null && !a3.ALLATORIxDEMO(a3.o)) {
            a3.c(null);
        }
        if (a3.y != -1) {
            a3.i[a3.y].startEditing();
        }
        a3.q = a3.c();
        if (a3.q <= 0) {
            a3.b = 0;
        } else {
            if (a3.b < -a3.q) {
                a3.b = -a3.q;
            }
            if (a3.b > 0) {
                a3.b = 0;
            }
            for (sk a4 : a3.j) {
                a4.shift(a3.b);
            }
            if (a2 && a3.o != null) {
                a3.ALLATORIxDEMO(a3.o);
            }
        }
    }

    private /* synthetic */ void k() {
        td a2 = ov.ALLATORIxDEMO();
        for (int a3 = 0; a3 < 7; ++a3) {
            qm a4;
            a4.i[a3] = new le(a2.ALLATORIxDEMO(a3), a4.m - 24, 31 + a3 * 25);
        }
    }

    private /* synthetic */ void d() {
        byte a2;
        int a3 = 18;
        int a4 = 4;
        for (a2 = 14; a2 < 17; a2 = (byte)(a2 + 1)) {
            a.l[a2 - 1] = new bl(a2, a3, a4);
            a3 += 15;
        }
        a3 += 30;
        for (a2 = 12; a2 < 14; a2 = (byte)(a2 + 1)) {
            a.l[a2 - 1] = new bl(a2, a3, a4);
            a3 += 15;
        }
        a3 = 18;
        a4 = 17;
        for (a2 = 1; a2 < 12; a2 = (byte)(a2 + 1)) {
            a.l[a2 - 1] = new bl(a2, a3, a4);
            a3 += 9;
        }
    }

    private /* synthetic */ boolean ALLATORIxDEMO(ph<fi> a2) {
        qm a3;
        for (sk a4 : a3.j) {
            if (a4.getNode() != a2) continue;
            a3.c(a2);
            return true;
        }
        return a2.ALLATORIxDEMO() && a3.ALLATORIxDEMO((ph<fi>)((Object)a2.ALLATORIxDEMO()));
    }

    private /* synthetic */ void ALLATORIxDEMO(ph<fi> a2, int a3) {
        qm a4;
        a4.j.add(new sk(a4, a2, a3, a4.r));
        a3 += 10;
        a4.r += a4.t;
        if (a2.f()) {
            Iterator iterator = a2.ALLATORIxDEMO().iterator();
            while (iterator.hasNext()) {
                ph a5 = (ph)iterator.next();
                a4.ALLATORIxDEMO(a5, a3);
            }
        }
    }

    public void draw(int a2, int a3) {
        qm a4;
        int a5 = a2;
        int a6 = a3;
        if (a4.k != null) {
            a5 = -1;
            a6 = -1;
        }
        for (sk a7 : a4.j) {
            if (!a7.shouldDraw(29, a4.v)) continue;
            a7.draw(a5, a6);
        }
        a4.overlayBackground(0, 29, 255, 255);
        a4.overlayBackground(a4.v, a4.c, 255, 255);
        for (bl bl2 : a4.l) {
            bl2.draw(a5, a6);
        }
        for (Gui gui : a4.i) {
            gui.draw(a5, a6);
        }
        a4.ALLATORIxDEMO(a5, a6);
        if (a4.k != null) {
            a4.k.draw(a2, a3);
        }
    }

    private /* synthetic */ void ALLATORIxDEMO(int a2, int a3) {
        qm a4;
        if (a4.q > 0) {
            int a5;
            if (Mouse.isButtonDown((int)0)) {
                if (a4.x == -1) {
                    if (a2 >= a4.m - 20 && a2 < a4.m && a3 >= 29 && a3 < a4.v) {
                        a4.x = a3;
                    }
                } else {
                    int a6;
                    float a7 = 1.0f;
                    a5 = a4.c();
                    if (a5 < 1) {
                        a5 = 1;
                    }
                    if ((a6 = (a4.v - 29) * (a4.v - 29) / a4.ALLATORIxDEMO()) < 32) {
                        a6 = 32;
                    }
                    if (a6 > a4.v - 29 - 8) {
                        a6 = a4.v - 29 - 8;
                    }
                    a4.shift((int)((float)(a4.x - a3) * (a7 /= (float)(a4.v - 29 - a6) / (float)a5)));
                    a4.x = a3;
                }
            } else {
                a4.x = -1;
            }
            qm.drawRect((int)(a4.m - 20), (int)29, (int)a4.m, (int)a4.v, (int)Integer.MIN_VALUE);
            int a8 = (a4.v - 29) * (a4.v - 29) / a4.ALLATORIxDEMO();
            if (a8 < 32) {
                a8 = 32;
            }
            if (a8 > a4.v - 29 - 8) {
                a8 = a4.v - 29 - 8;
            }
            if ((a5 = -a4.b * (a4.v - 29 - a8) / a4.q + 29) < 29) {
                a5 = 29;
            }
            a4.drawGradientRect(a4.m - 20, a5, a4.m, a5 + a8, -2130706433, -2144128205);
        }
    }

    public void overlayBackground(int a2, int a3, int a4, int a5) {
        qm a6;
        Tessellator a7 = Tessellator.getInstance();
        BufferBuilder a8 = a7.getBuffer();
        a6.e.renderEngine.bindTexture(OPTIONS_BACKGROUND);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        float a9 = 32.0f;
        a8.begin(7, DefaultVertexFormats.POSITION_COLOR);
        Color a10 = new Color(0x404040);
        a8.color(a10.getRed(), a10.getGreen(), a10.getBlue(), a5);
        a8.pos(0.0, (double)a3, 0.0).tex(0.0, (double)((float)a3 / a9));
        a8.pos((double)a6.m, (double)a3, 0.0).tex((double)((float)a6.m / a9), (double)((float)a3 / a9));
        a8.color(a10.getRed(), a10.getGreen(), a10.getBlue(), a4);
        a8.pos((double)a6.m, (double)a2, 0.0).tex((double)((float)a6.m / a9), (double)((float)a2 / a9));
        a8.pos(0.0, (double)a2, 0.0).tex(0.0, (double)((float)a2 / a9));
        a7.draw();
    }

    public void mouseClicked(int a2, int a3) {
        qm a4;
        if (a4.k == null) {
            boolean a5 = false;
            for (sk a6 : a4.j) {
                if (!a6.hideShowClicked(a2, a3)) continue;
                a5 = true;
                if (!a6.shouldDrawChildren()) break;
                a4.b = 31 - a6.k + a4.b;
                break;
            }
            if (!a5) {
                for (bl bl2 : a4.l) {
                    if (!bl2.inBounds(a2, a3)) continue;
                    a4.ALLATORIxDEMO(bl2);
                    return;
                }
                for (Gui gui : a4.i) {
                    if (gui.inBoundsOfX(a2, a3)) {
                        gui.reset();
                        ov.ALLATORIxDEMO().c();
                        a4.e.getSoundHandler().playSound((ISound)PositionedSoundRecord.getMasterRecord((SoundEvent)SoundEvents.UI_BUTTON_CLICK, (float)1.0f));
                        return;
                    }
                    if (!gui.inBounds(a2, a3)) continue;
                    a4.ALLATORIxDEMO((le)gui);
                    return;
                }
                if (a3 >= 30 && a2 <= a4.m - 175) {
                    ph<fi> a8 = null;
                    for (sk a9 : a4.j) {
                        if (!a9.clicked(a2, a3)) continue;
                        a8 = a9.getNode();
                        break;
                    }
                    if (a4.y != -1) {
                        a4.stopEditingSlot();
                    }
                    a4.c(a8);
                }
            } else {
                a4.initGUI();
            }
        } else {
            a4.k.click(a2, a3);
        }
    }

    private /* synthetic */ void ALLATORIxDEMO(le a2) {
        qm a3;
        if (a2.m.ALLATORIxDEMO.isEmpty()) {
            ph<fi> a4 = a3.o == null ? a3.n.ALLATORIxDEMO() : a3.o;
            NBTBase a5 = a4.ALLATORIxDEMO().ALLATORIxDEMO();
            String a6 = a4.ALLATORIxDEMO().ALLATORIxDEMO();
            if (a5 instanceof NBTTagList) {
                NBTTagList a7 = new NBTTagList();
                a3.n.ALLATORIxDEMO(a4, a7);
                a2.m.ALLATORIxDEMO.setTag(a6, (NBTBase)a7);
            } else if (a5 instanceof NBTTagCompound) {
                NBTTagCompound a8 = new NBTTagCompound();
                a3.n.ALLATORIxDEMO(a4, a8);
                a2.m.ALLATORIxDEMO.setTag(a6, (NBTBase)a8);
            } else {
                a2.m.ALLATORIxDEMO.setTag(a6, a5.copy());
            }
            a2.saved();
            ov.ALLATORIxDEMO().c();
            a3.e.getSoundHandler().playSound((ISound)PositionedSoundRecord.getMasterRecord((SoundEvent)SoundEvents.UI_BUTTON_CLICK, (float)1.0f));
        } else {
            Map<String, NBTBase> a9 = bp.ALLATORIxDEMO(a2.m.ALLATORIxDEMO);
            if (a9.isEmpty()) {
                ov.ALLATORIxDEMO(Level.WARN, "Unable to copy from save \"" + a2.m.k + "\".");
                ov.ALLATORIxDEMO(Level.WARN, "The save is invalid - a valid save must only contain 1 core NBTBase");
            } else {
                if (a3.o == null) {
                    a3.c(a3.n.ALLATORIxDEMO());
                }
                Map.Entry<String, NBTBase> a10 = a9.entrySet().iterator().next();
                assert (a10 != null);
                String a11 = a10.getKey();
                NBTBase a12 = a10.getValue().copy();
                if (a3.o == a3.n.ALLATORIxDEMO() && a12 instanceof NBTTagCompound && a11.equals("ROOT")) {
                    a3.c(null);
                    a3.n = new yh((NBTTagCompound)a12);
                    a3.initGUI();
                    a3.e.getSoundHandler().playSound((ISound)PositionedSoundRecord.getMasterRecord((SoundEvent)SoundEvents.UI_BUTTON_CLICK, (float)1.0f));
                } else if (a3.ALLATORIxDEMO(a3.o.ALLATORIxDEMO().ALLATORIxDEMO(), a12)) {
                    a3.o.ALLATORIxDEMO(true);
                    Object a13 = a3.o.ALLATORIxDEMO().iterator();
                    while (a13.hasNext()) {
                        if (!((fi)((ph)a13.next()).ALLATORIxDEMO()).ALLATORIxDEMO().equals(a11)) continue;
                        a13.remove();
                        break;
                    }
                    a13 = a3.ALLATORIxDEMO(new fi(a11, a12));
                    a3.n.ALLATORIxDEMO((ph<fi>)a13);
                    a3.n.c((ph<fi>)a13);
                    a3.c((ph<fi>)a13);
                    a3.initGUI(true);
                    a3.e.getSoundHandler().playSound((ISound)PositionedSoundRecord.getMasterRecord((SoundEvent)SoundEvents.UI_BUTTON_CLICK, (float)1.0f));
                }
            }
        }
    }

    private /* synthetic */ void ALLATORIxDEMO(bl a2) {
        block4: {
            qm a3;
            block10: {
                String a4;
                fi a5;
                block9: {
                    block8: {
                        block7: {
                            block6: {
                                block5: {
                                    block3: {
                                        if (a2.getId() != 16) break block3;
                                        a3.x();
                                        break block4;
                                    }
                                    if (a2.getId() != 15) break block5;
                                    a3.c();
                                    break block4;
                                }
                                if (a2.getId() != 14) break block6;
                                a3.f();
                                break block4;
                            }
                            if (a2.getId() != 13) break block7;
                            a3.deleteSelected();
                            break block4;
                        }
                        if (a2.getId() != 12) break block8;
                        a3.ALLATORIxDEMO();
                        break block4;
                    }
                    if (a3.o == null) break block4;
                    a3.o.ALLATORIxDEMO(true);
                    a5 = a3.o.ALLATORIxDEMO();
                    a4 = su.ALLATORIxDEMO(a2.getId());
                    if (!(a3.o.ALLATORIxDEMO().ALLATORIxDEMO() instanceof NBTTagList)) break block9;
                    NBTBase a6 = su.ALLATORIxDEMO(a2.getId());
                    if (a6 == null) break block10;
                    ph<fi> a7 = new ph<fi>(a3.o, new fi("", a6));
                    a5.add(a7);
                    a3.c(a7);
                    break block10;
                }
                if (a5.size() == 0) {
                    a3.c(a3.ALLATORIxDEMO(a4 + "1", a2.getId()));
                } else {
                    for (int a8 = 1; a8 <= a5.size() + 1; ++a8) {
                        String a9 = a4 + a8;
                        if (!a3.ALLATORIxDEMO(a9, (List<ph<fi>>)((Object)a5))) continue;
                        a3.c(a3.ALLATORIxDEMO(a9, a2.getId()));
                        break;
                    }
                }
            }
            a3.initGUI(true);
        }
    }

    private /* synthetic */ boolean ALLATORIxDEMO(String a2, List<ph<fi>> a3) {
        for (ph<fi> a4 : a3) {
            if (!a4.ALLATORIxDEMO().ALLATORIxDEMO().equals(a2)) continue;
            return false;
        }
        return true;
    }

    private /* synthetic */ ph<fi> ALLATORIxDEMO(fi a2) {
        qm a3;
        ph<fi> a4 = new ph<fi>(a3.o, a2);
        if (a3.o.c()) {
            fi a5 = a3.o.ALLATORIxDEMO();
            boolean a6 = false;
            for (int a7 = 0; a7 < a5.size(); ++a7) {
                if (ov.x.ALLATORIxDEMO(a4, (ph)a5.get(a7)) >= 0) continue;
                a5.add(a7, a4);
                a6 = true;
                break;
            }
            if (!a6) {
                a5.add(a4);
            }
        } else {
            a3.o.ALLATORIxDEMO(a4);
        }
        return a4;
    }

    private /* synthetic */ ph<fi> ALLATORIxDEMO(String a2, byte a3) {
        NBTBase a4 = su.ALLATORIxDEMO(a3);
        if (a4 != null) {
            qm a5;
            return a5.ALLATORIxDEMO(new fi(a2, a4));
        }
        return null;
    }

    public void deleteSelected() {
        qm a2;
        if (a2.o != null && a2.n.ALLATORIxDEMO(a2.o)) {
            ph<fi> a3 = a2.o;
            a2.ALLATORIxDEMO(true);
            if (a2.o == a3) {
                a2.c(null);
            }
            a2.initGUI();
        }
    }

    public void editSelected() {
        qm a2;
        if (a2.o != null) {
            NBTBase a3 = a2.o.ALLATORIxDEMO().ALLATORIxDEMO();
            if (a2.o.c() && (a3 instanceof NBTTagCompound || a3 instanceof NBTTagList)) {
                int a4;
                a2.o.ALLATORIxDEMO(!a2.o.f());
                if (a2.o.f() && (a4 = a2.ALLATORIxDEMO(a2.o)) != -1) {
                    a2.b = 31 - a2.j.get((int)a4).k + a2.b;
                }
                a2.initGUI();
            } else if (a2.l[11].isEnabled()) {
                a2.ALLATORIxDEMO();
            }
        } else if (a2.y != -1) {
            a2.stopEditingSlot();
        }
    }

    private /* synthetic */ boolean ALLATORIxDEMO(NBTBase a2, NBTBase a3) {
        if (a2 instanceof NBTTagCompound) {
            return true;
        }
        if (a2 instanceof NBTTagList) {
            NBTTagList a4 = (NBTTagList)a2;
            return a4.tagCount() == 0 || a4.getTagType() == a3.getId();
        }
        return false;
    }

    private /* synthetic */ boolean ALLATORIxDEMO() {
        qm a2;
        return ov.c != null && a2.o != null && a2.ALLATORIxDEMO(a2.o.ALLATORIxDEMO().ALLATORIxDEMO(), ov.c.ALLATORIxDEMO());
    }

    private /* synthetic */ void x() {
        if (ov.c != null) {
            qm a2;
            a2.o.ALLATORIxDEMO(true);
            fi a3 = ov.c.ALLATORIxDEMO();
            if (a2.o.ALLATORIxDEMO().ALLATORIxDEMO() instanceof NBTTagList) {
                a3.ALLATORIxDEMO("");
                ph<fi> a4 = new ph<fi>(a2.o, a3);
                a2.o.ALLATORIxDEMO(a4);
                a2.n.ALLATORIxDEMO(a4);
                a2.n.c(a4);
                a2.c(a4);
            } else {
                fi a5;
                String a6 = a3.ALLATORIxDEMO();
                if (!a2.ALLATORIxDEMO(a6, (List<ph<fi>>)((Object)(a5 = a2.o.ALLATORIxDEMO())))) {
                    for (int a7 = 1; a7 <= a5.size() + 1; ++a7) {
                        String a8 = a6 + "(" + a7 + ")";
                        if (!a2.ALLATORIxDEMO(a8, (List<ph<fi>>)((Object)a5))) continue;
                        a3.ALLATORIxDEMO(a8);
                        break;
                    }
                }
                ph<fi> a9 = a2.ALLATORIxDEMO(a3);
                a2.n.ALLATORIxDEMO(a9);
                a2.n.c(a9);
                a2.c(a9);
            }
            a2.initGUI(true);
        }
    }

    private /* synthetic */ void f() {
        qm a2;
        if (a2.o != null) {
            fi a3 = a2.o.ALLATORIxDEMO();
            if (a3.ALLATORIxDEMO() instanceof NBTTagList) {
                NBTTagList a4 = new NBTTagList();
                a2.n.ALLATORIxDEMO(a2.o, a4);
                ov.c = new fi(a3.ALLATORIxDEMO(), (NBTBase)a4);
            } else if (a3.ALLATORIxDEMO() instanceof NBTTagCompound) {
                NBTTagCompound a5 = new NBTTagCompound();
                a2.n.ALLATORIxDEMO(a2.o, a5);
                ov.c = new fi(a3.ALLATORIxDEMO(), (NBTBase)a5);
            } else {
                ov.c = a2.o.ALLATORIxDEMO().ALLATORIxDEMO();
            }
            a2.c(a2.o);
        }
    }

    private /* synthetic */ void c() {
        qm a2;
        a2.f();
        a2.deleteSelected();
    }

    private /* synthetic */ void ALLATORIxDEMO() {
        qm a2;
        NBTBase a3 = a2.o.ALLATORIxDEMO().ALLATORIxDEMO();
        NBTBase a4 = ((fi)((ph)((Object)a2.o.ALLATORIxDEMO())).ALLATORIxDEMO()).ALLATORIxDEMO();
        a2.k = new gr(a2, a2.o, !(a4 instanceof NBTTagList), !(a3 instanceof NBTTagCompound) && !(a3 instanceof NBTTagList));
        a2.k.initGUI((a2.m - 178) / 2, (a2.c - 93) / 2);
    }

    public void nodeEdited(ph<fi> a2) {
        qm a3;
        fi a4 = a2.ALLATORIxDEMO();
        Collections.sort(((ph)((Object)a4)).ALLATORIxDEMO(), ov.x);
        a3.initGUI(true);
    }

    public void arrowKeyPressed(boolean a2) {
        qm a3;
        if (a3.o == null) {
            a3.shift(a2 ? a3.t : -a3.t);
        } else {
            a3.ALLATORIxDEMO(a2);
        }
    }

    private /* synthetic */ int ALLATORIxDEMO(ph<fi> a2) {
        qm a3;
        for (int a4 = 0; a4 < a3.j.size(); ++a4) {
            if (a3.j.get(a4).getNode() != a2) continue;
            return a4;
        }
        return -1;
    }

    private /* synthetic */ void ALLATORIxDEMO(boolean a2) {
        qm a3;
        int a4 = a3.ALLATORIxDEMO(a3.o);
        if (a4 != -1 && (a4 += a2 ? -1 : 1) >= 0 && a4 < a3.j.size()) {
            a3.c(a3.j.get(a4).getNode());
            a3.shift(a2 ? a3.t : -a3.t);
        }
    }

    private /* synthetic */ void ALLATORIxDEMO(ph<fi> a2) {
        qm a3;
        int a4 = a3.ALLATORIxDEMO(a2);
        if (a4 != -1) {
            sk a5 = a3.j.get(a4);
            a3.shift((a3.v + 30 + 1) / 2 - (a5.k + a5.o));
        }
    }

    public void shift(int a2) {
        qm a3;
        if (a3.q <= 0 || a3.k != null) {
            return;
        }
        int a4 = a3.b + a2;
        if (a4 > 0) {
            a4 = 0;
        }
        if (a4 < -a3.q) {
            a4 = -a3.q;
        }
        for (sk a5 : a3.j) {
            a5.shift(a4 - a3.b);
        }
        a3.b = a4;
    }

    public void closeWindow() {
        a.k = null;
    }

    public boolean isEditingSlot() {
        qm a2;
        return a2.y != -1;
    }

    public void stopEditingSlot() {
        qm a2;
        a2.i[a2.y].stopEditing();
        ov.ALLATORIxDEMO().c();
        a2.y = -1;
    }

    public void keyTyped(char a2, int a3) {
        qm a4;
        if (a4.y != -1) {
            a4.i[a4.y].keyTyped(a2, a3);
        } else {
            if (a3 == 46 && GuiControls.isCtrlKeyDown()) {
                a4.f();
            }
            if (a3 == 47 && GuiControls.isCtrlKeyDown() && a4.ALLATORIxDEMO()) {
                a4.x();
            }
            if (a3 == 45 && GuiControls.isCtrlKeyDown()) {
                a4.c();
            }
        }
    }

    public void rightClick(int a2, int a3) {
        for (int a4 = 0; a4 < 7; ++a4) {
            qm a5;
            if (!a5.i[a4].inBounds(a2, a3)) continue;
            a5.c(null);
            if (a5.y != -1) {
                if (a5.y != a4) {
                    a5.i[a5.y].stopEditing();
                    ov.ALLATORIxDEMO().c();
                } else {
                    return;
                }
            }
            a5.i[a4].startEditing();
            a5.y = a4;
            break;
        }
    }

    private /* synthetic */ void ALLATORIxDEMO(BufferBuilder a2, int a3, int a4) {
        int a5 = a2.getColorIndex(a4);
        int a6 = a3 >> 16 & 0xFF;
        int a7 = a3 >> 8 & 0xFF;
        int a8 = a3 & 0xFF;
        int a9 = a3 >> 24 & 0xFF;
        a2.putColorRGBA(a5, a6, a7, a8, a9);
    }
}

