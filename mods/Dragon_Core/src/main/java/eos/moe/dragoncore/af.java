/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  com.google.common.collect.Sets
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.gui.inventory.GuiContainer
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.OpenGlHelper
 *  net.minecraft.client.renderer.RenderHelper
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.init.Items
 *  net.minecraft.inventory.ClickType
 *  net.minecraft.inventory.Container
 *  net.minecraft.inventory.Slot
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.util.text.TextFormatting
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 *  org.lwjgl.input.Keyboard
 */
package eos.moe.dragoncore;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import eos.moe.dragoncore.de;
import eos.moe.dragoncore.dj;
import eos.moe.dragoncore.jj;
import eos.moe.dragoncore.nh;
import eos.moe.dragoncore.od;
import eos.moe.dragoncore.qd;
import eos.moe.dragoncore.qn;
import eos.moe.dragoncore.sd;
import eos.moe.dragoncore.sj;
import eos.moe.dragoncore.wi;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.ClickType;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;

@SideOnly(value=Side.CLIENT)
public abstract class af
extends GuiContainer {
    public Container h;
    public Slot f;
    public Slot d;
    private Slot p;
    private boolean u;
    private ItemStack w = ItemStack.EMPTY;
    private int a;
    private int e;
    private Slot n;
    private long j;
    private ItemStack i = ItemStack.EMPTY;
    private Slot l;
    private long z;
    public final Set<Slot> s = Sets.newHashSet();
    public boolean g;
    private int t;
    private int r;
    private boolean x;
    private int v;
    private long m;
    private Slot c;
    private int q;
    private boolean b;
    private ItemStack o = ItemStack.EMPTY;
    private ItemStack y = new ItemStack(Items.APPLE);
    private jj k = null;
    private long ALLATORIxDEMO;

    public af(Container a2) {
        super(a2);
        af a3;
        a3.h = a2;
        a3.x = true;
    }

    public void initGui() {
        af a2;
        a2.mc = Minecraft.getMinecraft();
        Keyboard.enableRepeatEvents((boolean)true);
        a2.mc.player.openContainer = a2.h;
        a2.fontRenderer = a2.mc.fontRenderer;
        a2.itemRender = a2.mc.getRenderItem();
    }

    public void drawScreen(int a2, int a3, float a4) {
        int a5;
        ItemStack a6;
        af a7;
        GlStateManager.disableRescaleNormal();
        RenderHelper.disableStandardItemLighting();
        GlStateManager.disableLighting();
        GlStateManager.enableDepth();
        RenderHelper.enableGUIStandardItemLighting();
        GlStateManager.color((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GlStateManager.enableRescaleNormal();
        a7.f = a7.c(a2, a3);
        a7.d = a7.ALLATORIxDEMO(a2, a3);
        OpenGlHelper.setLightmapTextureCoords((int)OpenGlHelper.lightmapTexUnit, (float)240.0f, (float)240.0f);
        GlStateManager.color((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        RenderHelper.disableStandardItemLighting();
        RenderHelper.enableGUIStandardItemLighting();
        InventoryPlayer a8 = a7.mc.player.inventory;
        ItemStack itemStack = a6 = a7.w.isEmpty() ? a8.getItemStack() : a7.w;
        if (!a6.isEmpty()) {
            int a9 = 8;
            a5 = a7.w.isEmpty() ? 8 : 16;
            String a10 = null;
            if (!a7.w.isEmpty() && a7.u) {
                a6 = a6.copy();
                a6.setCount(MathHelper.ceil((float)((float)a6.getCount() / 2.0f)));
            } else if (a7.g && a7.s.size() > 1) {
                a6 = a6.copy();
                a6.setCount(a7.v);
                if (a6.isEmpty()) {
                    a10 = "" + TextFormatting.YELLOW + "0";
                }
            }
            GlStateManager.pushMatrix();
            GlStateManager.translate((float)(a2 - 8 + 8), (float)(a3 - a5 + 8), (float)0.0f);
            double a11 = a7.getCurrentItemScale().c() / 16.0;
            GlStateManager.scale((double)a11, (double)a11, (double)1.0);
            GlStateManager.translate((float)-8.0f, (float)-8.0f, (float)0.0f);
            a7.ALLATORIxDEMO(a6, 0, 0, a10);
            GlStateManager.popMatrix();
        }
        if (!a7.i.isEmpty()) {
            float a12 = (float)(Minecraft.getSystemTime() - a7.j) / 100.0f;
            if (a12 >= 1.0f) {
                a12 = 1.0f;
                a7.i = ItemStack.EMPTY;
            }
            a5 = a7.n.xPos - a7.a;
            int a13 = a7.n.yPos - a7.e;
            int a14 = a7.a + (int)((float)a5 * a12);
            int a15 = a7.e + (int)((float)a13 * a12);
            a7.ALLATORIxDEMO(a7.i, a14, a15, null);
        }
        GlStateManager.enableLighting();
        GlStateManager.enableDepth();
        RenderHelper.enableStandardItemLighting();
    }

    private /* synthetic */ void ALLATORIxDEMO(ItemStack a2, int a3, int a4, String a5) {
        af a6;
        GlStateManager.translate((float)0.0f, (float)0.0f, (float)32.0f);
        a6.zLevel = 200.0f;
        a6.itemRender.zLevel = 200.0f;
        FontRenderer a7 = a2.getItem().getFontRenderer(a2);
        if (a7 == null) {
            a7 = a6.fontRenderer;
        }
        a6.itemRender.renderItemAndEffectIntoGUI(a2, a3, a4);
        a6.itemRender.renderItemOverlayIntoGUI(a7, a2, a3, a4 - (a6.w.isEmpty() ? 0 : 8), a5);
        a6.zLevel = 0.0f;
        a6.itemRender.zLevel = 0.0f;
    }

    public void drawGuiContainerBackgroundLayer(float a2, int a3, int a4) {
    }

    public void drawHoverTip(float a3, int a4, int a5) {
        Object a6;
        jj a72;
        af a8;
        if (a8.mc.player.inventory.getItemStack().isEmpty() && a8.f != null && a8.f.getHasStack()) {
            a8.renderToolTip(a8.f.getStack(), a4, a5);
            return;
        }
        jj a9 = a8.getHoveredComponent();
        if (a8.isThrough()) {
            for (jj a72 : a8.getHoveredComponents()) {
                if (!(a72 instanceof qn)) continue;
                a9 = a72;
            }
        }
        if (a8.mc.player.inventory.getItemStack().isEmpty() && a9 instanceof qn && !(a72 = wi.b.ALLATORIxDEMO(((nh)(a6 = ((qn)a9).y)).c(), a8.isHud()).c()).isEmpty()) {
            a8.renderToolTip(wi.b.ALLATORIxDEMO(((nh)a6).c(), a8.isHud()).c(), a4, a5);
            return;
        }
        if (a8.isThrough()) {
            for (jj a72 : a8.getHoveredComponents()) {
                if (a72.q.f() <= 0) continue;
                a9 = a72;
            }
        }
        if (a8.mc.player.inventory.getItemStack().isEmpty() && a9 != null && a9.q.f() > 0) {
            a6 = de.o;
            a8.width = a6.getScaledWidth();
            a8.height = a6.getScaledHeight();
            if (a8.k != a9 || System.currentTimeMillis() - a8.ALLATORIxDEMO > 500L) {
                a8.y = new ItemStack(Items.APPLE);
                a8.ALLATORIxDEMO = System.currentTimeMillis();
            }
            a8.k = a9;
            a8.y.setStackDisplayName("DragonCore_ToolTip_" + a9.k.getName());
            a72 = Minecraft.getMinecraft().fontRenderer;
            List<String> a10 = a9.getTipStringTexts();
            ArrayList<String> a11 = new ArrayList<String>();
            for (String a12 : a10) {
                a11.addAll(sj.ALLATORIxDEMO(a12, 0, null));
            }
            a10.clear();
            for (String a12 : a11) {
                int a13 = a12.indexOf("container_");
                if (a13 != -1) {
                    String a14 = a12.substring(a13 + 10);
                    try {
                        Integer.parseInt(a14);
                        qd<ItemStack, String> a15 = wi.b.ALLATORIxDEMO(a12, a8.isHud());
                        a10.addAll(dj.c(a15.c()));
                    }
                    catch (NumberFormatException a16) {
                        qd<ItemStack, String> a17 = wi.b.ALLATORIxDEMO(a14, a8.isHud());
                        a10.addAll(dj.c(a17.c()));
                    }
                    continue;
                }
                a10.add(a12);
            }
            a10.replaceAll(a2 -> a2.replace("&", "\u00a7"));
            if (a8.ALLATORIxDEMO(a10)) {
                return;
            }
            dj.ALLATORIxDEMO(a8.y, a10);
            sd.ALLATORIxDEMO(a8.y, a10, a4, a5, a8.width, a8.height, -1, (FontRenderer)a72);
        }
    }

    private /* synthetic */ boolean ALLATORIxDEMO(List<String> a2) {
        for (String a3 : a2) {
            if (a3.isEmpty()) continue;
            return false;
        }
        return true;
    }

    public qd<ItemStack, String> getSlotItemStack(Slot a2) {
        af a3;
        if (!a2.isEnabled()) {
            return new qd<ItemStack, Object>(ItemStack.EMPTY, null);
        }
        ItemStack a4 = a2.getStack();
        boolean a5 = false;
        boolean a6 = a2 == a3.p && !a3.w.isEmpty() && !a3.u;
        ItemStack a7 = a3.mc.player.inventory.getItemStack();
        String a8 = null;
        if (a2 == a3.p && !a3.w.isEmpty() && a3.u && !a4.isEmpty()) {
            a4 = a4.copy();
            a4.setCount(a4.getCount() / 2);
        } else if (a3.g && a3.s.contains(a2) && !a7.isEmpty()) {
            if (a3.s.size() == 1) {
                return new qd<ItemStack, Object>(ItemStack.EMPTY, null);
            }
            if (Container.canAddItemToSlot((Slot)a2, (ItemStack)a7, (boolean)true) && a3.h.canDragIntoSlot(a2)) {
                a4 = a7.copy();
                a5 = true;
                Container.computeStackSize(a3.s, (int)a3.t, (ItemStack)a4, (int)(a2.getStack().isEmpty() ? 0 : a2.getStack().getCount()));
                int a9 = Math.min(a4.getMaxStackSize(), a2.getItemStackLimit(a4));
                if (a4.getCount() > a9) {
                    a8 = TextFormatting.YELLOW.toString() + a9;
                    a4.setCount(a9);
                }
            } else {
                a3.s.remove(a2);
                a3.ALLATORIxDEMO();
            }
        }
        if (a6) {
            return qd.ALLATORIxDEMO(ItemStack.EMPTY, null);
        }
        if (a5) {
            if (a8 == null) {
                a8 = String.valueOf(a4.getCount());
            }
            a8 = a8 + "\u00a7c";
        }
        return qd.ALLATORIxDEMO(a4, a8);
    }

    private /* synthetic */ void ALLATORIxDEMO() {
        af a2;
        ItemStack a3 = a2.mc.player.inventory.getItemStack();
        if (!a3.isEmpty() && a2.g) {
            if (a2.t == 2) {
                a2.v = a3.getMaxStackSize();
            } else {
                a2.v = a3.getCount();
                for (Slot a4 : a2.s) {
                    ItemStack a5 = a3.copy();
                    ItemStack a6 = a4.getStack();
                    int a7 = a6.isEmpty() ? 0 : a6.getCount();
                    Container.computeStackSize(a2.s, (int)a2.t, (ItemStack)a5, (int)a7);
                    int a8 = Math.min(a5.getMaxStackSize(), a4.getItemStackLimit(a5));
                    if (a5.getCount() > a8) {
                        a5.setCount(a8);
                    }
                    a2.v -= a5.getCount() - a7;
                }
            }
        }
    }

    private /* synthetic */ Slot c(int a2, int a3) {
        af a4;
        ArrayList a5 = a4.isThrough() ? a4.getHoveredComponents() : Lists.newArrayList((Object[])new jj[]{a4.getHoveredComponent()});
        Slot a6 = null;
        for (jj a7 : a5) {
            Slot a8;
            String a9;
            if (!(a7 instanceof qn) || !(a9 = ((qn)a7).y.c()).startsWith("container_") && !a9.startsWith("player_") || (a8 = wi.b.ALLATORIxDEMO(a9, a4.h)) == null || !a8.isEnabled()) continue;
            a6 = a8;
        }
        return a6;
    }

    private /* synthetic */ Slot ALLATORIxDEMO(int a2, int a3) {
        af a4;
        ArrayList a5 = a4.isThrough() ? a4.getHoveredComponents() : Lists.newArrayList((Object[])new jj[]{a4.getHoveredComponent()});
        Slot a6 = null;
        for (jj a7 : a5) {
            Slot a8;
            if (!(a7 instanceof qn)) continue;
            String a9 = ((qn)a7).y.c();
            if ((!a7.xa.c() || !a9.startsWith("container_")) && !a9.startsWith("player_") || (a8 = wi.b.ALLATORIxDEMO(a9, a4.h)) == null || !a8.isEnabled()) continue;
            a6 = a8;
        }
        return a6;
    }

    public void mouseClicked(int a2, int a3, int a4) throws IOException {
        af a5;
        boolean a6 = a5.mc.gameSettings.keyBindPickBlock.isActiveAndMatches(a4 - 100);
        Slot a7 = a5.ALLATORIxDEMO(a2, a3);
        long a8 = Minecraft.getSystemTime();
        a5.b = a5.c == a7 && a8 - a5.m < 250L && a5.q == a4;
        a5.x = false;
        if (a4 == 0 || a4 == 1 || a6) {
            boolean a9 = a5.hasClickedOutside(a2, a3);
            if (a7 != null) {
                a9 = false;
            }
            int a10 = -1;
            if (a7 != null) {
                a10 = a7.slotNumber;
            }
            if (a9) {
                a10 = -999;
            }
            if (a5.mc.gameSettings.touchscreen && a9 && a5.mc.player.inventory.getItemStack().isEmpty()) {
                a5.mc.displayGuiScreen(null);
                return;
            }
            if (a10 != -1) {
                if (a5.mc.gameSettings.touchscreen) {
                    if (a7 != null && a7.getHasStack()) {
                        a5.p = a7;
                        a5.w = ItemStack.EMPTY;
                        a5.u = a4 == 1;
                    } else {
                        a5.p = null;
                    }
                } else if (!a5.g) {
                    if (a5.mc.player.inventory.getItemStack().isEmpty()) {
                        if (a5.mc.gameSettings.keyBindPickBlock.isActiveAndMatches(a4 - 100)) {
                            a5.handleMouseClick(a7, a10, a4, ClickType.CLONE);
                        } else {
                            boolean a11 = a10 != -999 && (Keyboard.isKeyDown((int)42) || Keyboard.isKeyDown((int)54));
                            ClickType a12 = ClickType.PICKUP;
                            if (a11) {
                                a5.o = a7 != null && a7.getHasStack() ? a7.getStack().copy() : ItemStack.EMPTY;
                                a12 = ClickType.QUICK_MOVE;
                            } else if (a10 == -999) {
                                a12 = ClickType.THROW;
                            }
                            a5.handleMouseClick(a7, a10, a4, a12);
                        }
                        a5.x = true;
                    } else {
                        a5.g = true;
                        a5.r = a4;
                        a5.s.clear();
                        if (a4 == 0) {
                            a5.t = 0;
                        } else if (a4 == 1) {
                            a5.t = 1;
                        } else if (a5.mc.gameSettings.keyBindPickBlock.isActiveAndMatches(a4 - 100)) {
                            a5.t = 2;
                        }
                    }
                }
            }
        }
        a5.c = a7;
        a5.m = a8;
        a5.q = a4;
    }

    public boolean hasClickedOutside(int a2, int a3) {
        af a4;
        if (a4.getGuiType() != od.q) {
            return false;
        }
        return a4.getHoveredComponent() == null;
    }

    public void mouseClickMove(int a2, int a3, int a4, long a5) {
        af a6;
        Slot a7 = a6.ALLATORIxDEMO(a2, a3);
        ItemStack a8 = a6.mc.player.inventory.getItemStack();
        if (a6.p != null && a6.mc.gameSettings.touchscreen) {
            if (a4 == 0 || a4 == 1) {
                if (a6.w.isEmpty()) {
                    if (a7 != a6.p && !a6.p.getStack().isEmpty()) {
                        a6.w = a6.p.getStack().copy();
                    }
                } else if (a6.w.getCount() > 1 && a7 != null && Container.canAddItemToSlot((Slot)a7, (ItemStack)a6.w, (boolean)false)) {
                    long a9 = Minecraft.getSystemTime();
                    if (a6.l == a7) {
                        if (a9 - a6.z > 500L) {
                            a6.handleMouseClick(a6.p, a6.p.slotNumber, 0, ClickType.PICKUP);
                            a6.handleMouseClick(a7, a7.slotNumber, 1, ClickType.PICKUP);
                            a6.handleMouseClick(a6.p, a6.p.slotNumber, 0, ClickType.PICKUP);
                            a6.z = a9 + 750L;
                            a6.w.shrink(1);
                        }
                    } else {
                        a6.l = a7;
                        a6.z = a9;
                    }
                }
            }
        } else if (a6.g && a7 != null && !a8.isEmpty() && (a8.getCount() > a6.s.size() || a6.t == 2) && Container.canAddItemToSlot((Slot)a7, (ItemStack)a8, (boolean)true) && a7.isItemValid(a8) && a6.h.canDragIntoSlot(a7)) {
            a6.s.add(a7);
            a6.ALLATORIxDEMO();
        }
    }

    public void mouseReleased(int a2, int a3, int a4) {
        af a5;
        Slot a6 = a5.ALLATORIxDEMO(a2, a3);
        boolean a7 = a5.hasClickedOutside(a2, a3);
        if (a6 != null) {
            a7 = false;
        }
        int a8 = -1;
        if (a6 != null) {
            a8 = a6.slotNumber;
        }
        if (a7) {
            a8 = -999;
        }
        if (a5.b && a6 != null && a4 == 0 && a5.h.canMergeSlot(ItemStack.EMPTY, a6)) {
            if (af.isShiftKeyDown()) {
                if (!a5.o.isEmpty()) {
                    for (Slot a9 : a5.h.inventorySlots) {
                        if (a9 == null || !a9.canTakeStack((EntityPlayer)a5.mc.player) || !a9.getHasStack() || !a9.isSameInventory(a6) || !Container.canAddItemToSlot((Slot)a9, (ItemStack)a5.o, (boolean)true)) continue;
                        a5.handleMouseClick(a9, a9.slotNumber, a4, ClickType.QUICK_MOVE);
                    }
                }
            } else {
                a5.handleMouseClick(a6, a8, a4, ClickType.PICKUP_ALL);
            }
            a5.b = false;
            a5.m = 0L;
        } else {
            if (a5.g && a5.r != a4) {
                a5.g = false;
                a5.s.clear();
                a5.x = true;
                return;
            }
            if (a5.x) {
                a5.x = false;
                return;
            }
            if (a5.p != null && a5.mc.gameSettings.touchscreen) {
                if (a4 == 0 || a4 == 1) {
                    if (a5.w.isEmpty() && a6 != a5.p) {
                        a5.w = a5.p.getStack();
                    }
                    boolean a10 = Container.canAddItemToSlot((Slot)a6, (ItemStack)a5.w, (boolean)false);
                    if (a8 != -1 && !a5.w.isEmpty() && a10) {
                        a5.handleMouseClick(a5.p, a5.p.slotNumber, a4, ClickType.PICKUP);
                        a5.handleMouseClick(a6, a8, 0, ClickType.PICKUP);
                        if (a5.mc.player.inventory.getItemStack().isEmpty()) {
                            a5.i = ItemStack.EMPTY;
                        } else {
                            a5.handleMouseClick(a5.p, a5.p.slotNumber, a4, ClickType.PICKUP);
                            a5.a = a2;
                            a5.e = a3;
                            a5.n = a5.p;
                            a5.i = a5.w;
                            a5.j = Minecraft.getSystemTime();
                        }
                    } else if (!a5.w.isEmpty()) {
                        a5.a = a2;
                        a5.e = a3;
                        a5.n = a5.p;
                        a5.i = a5.w;
                        a5.j = Minecraft.getSystemTime();
                    }
                    a5.w = ItemStack.EMPTY;
                    a5.p = null;
                }
            } else if (a5.g && !a5.s.isEmpty()) {
                a5.handleMouseClick(null, -999, Container.getQuickcraftMask((int)0, (int)a5.t), ClickType.QUICK_CRAFT);
                for (Slot a11 : a5.s) {
                    a5.handleMouseClick(a11, a11.slotNumber, Container.getQuickcraftMask((int)1, (int)a5.t), ClickType.QUICK_CRAFT);
                }
                a5.handleMouseClick(null, -999, Container.getQuickcraftMask((int)2, (int)a5.t), ClickType.QUICK_CRAFT);
            } else if (!a5.mc.player.inventory.getItemStack().isEmpty()) {
                if (a5.mc.gameSettings.keyBindPickBlock.isActiveAndMatches(a4 - 100)) {
                    a5.handleMouseClick(a6, a8, a4, ClickType.CLONE);
                } else {
                    boolean a12;
                    boolean bl2 = a12 = a8 != -999 && (Keyboard.isKeyDown((int)42) || Keyboard.isKeyDown((int)54));
                    if (a12) {
                        a5.o = a6 != null && a6.getHasStack() ? a6.getStack().copy() : ItemStack.EMPTY;
                    }
                    a5.handleMouseClick(a6, a8, a4, a12 ? ClickType.QUICK_MOVE : ClickType.PICKUP);
                }
            }
        }
        if (a5.mc.player.inventory.getItemStack().isEmpty()) {
            a5.m = 0L;
        }
        a5.g = false;
    }

    public void handleMouseClick(Slot a2, int a3, int a4, ClickType a5) {
        af a6;
        if (a2 != null) {
            a3 = a2.slotNumber;
        }
        a6.mc.playerController.windowClick(a6.h.windowId, a3, a4, a5, (EntityPlayer)a6.mc.player);
    }

    public void keyTyped(char a2, int a3) throws IOException {
        af a4;
        if (a4.f == a4.d) {
            a4.checkHotbarKeys(a3);
            if (a4.f != null && a4.f.getHasStack()) {
                if (a4.mc.gameSettings.keyBindPickBlock.isActiveAndMatches(a3)) {
                    a4.handleMouseClick(a4.f, a4.f.slotNumber, 0, ClickType.CLONE);
                } else if (a4.mc.gameSettings.keyBindDrop.isActiveAndMatches(a3)) {
                    a4.handleMouseClick(a4.f, a4.f.slotNumber, af.isCtrlKeyDown() ? 1 : 0, ClickType.THROW);
                }
            }
        }
    }

    public boolean checkHotbarKeys(int a2) {
        af a3;
        if (a3.mc.player.inventory.getItemStack().isEmpty() && a3.f != null) {
            for (int a4 = 0; a4 < 9; ++a4) {
                if (!a3.mc.gameSettings.keyBindsHotbar[a4].isActiveAndMatches(a2)) continue;
                a3.handleMouseClick(a3.f, a3.f.slotNumber, a4, ClickType.SWAP);
                return true;
            }
        }
        return false;
    }

    public void onGuiClosed() {
        af a2;
        if (a2.mc.player != null) {
            a2.h.onContainerClosed((EntityPlayer)a2.mc.player);
        }
    }

    public boolean doesGuiPauseGame() {
        return false;
    }

    public void updateScreen() {
        af a2;
        if (a2.getMatch().equals("GuiGameOver")) {
            return;
        }
        if (!a2.mc.player.isEntityAlive() || a2.mc.player.isDead) {
            a2.mc.player.closeScreen();
        }
    }

    public Slot getSlotUnderMouse() {
        af a2;
        return a2.f;
    }

    public abstract Map<String, jj> getComponents();

    public abstract jj getHoveredComponent();

    public abstract List<jj> getHoveredComponents();

    public abstract nh getCurrentItemScale();

    public boolean isHud() {
        af a2;
        return a2.getGuiType() == od.b;
    }

    public abstract od getGuiType();

    public abstract boolean isThrough();

    public abstract String getMatch();
}

