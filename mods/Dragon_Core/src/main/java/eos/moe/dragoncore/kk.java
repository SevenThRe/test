/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.ClickType
 *  net.minecraft.inventory.Container
 *  net.minecraft.inventory.ContainerPlayer
 *  net.minecraft.inventory.Slot
 *  net.minecraft.item.ItemStack
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.i;
import eos.moe.dragoncore.qd;
import eos.moe.dragoncore.qg;
import eos.moe.dragoncore.ui;
import eos.moe.dragoncore.v;
import eos.moe.dragoncore.wi;
import eos.moe.dragoncore.xk;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ClickType;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class kk {
    public kk() {
        kk a2;
    }

    @i(f={"\u5220\u9664\u7269\u54c1", "Container_Delete"})
    public static void ALLATORIxDEMO(String a2, boolean a3) {
        wi.b.ALLATORIxDEMO(a2, a3);
    }

    @i(f={"\u53d6\u7269\u54c1", "\u53d6\u69fd\u4f4d\u7269\u54c1", "Container_Get_Item"})
    public static ItemStack ALLATORIxDEMO(ui a2, String a3) {
        qd<ItemStack, String> a4 = wi.b.ALLATORIxDEMO(a3, a2 != null && a2.isHud());
        if (a4.c() == null) {
            return ItemStack.EMPTY;
        }
        return a4.c();
    }

    @i(f={"\u53d6\u6240\u6709\u7269\u54c1", "Container_Get_All_Items"})
    public static qg c(ui a2) {
        if (a2 == null) {
            return new qg();
        }
        ArrayList<v> a3 = new ArrayList<v>();
        List a4 = ((Container)a2.h).inventorySlots;
        for (Slot a5 : a4) {
            a3.add(new xk(a5.getStack()));
        }
        return new qg(a3);
    }

    @i(f={"\u53d6\u5bb9\u5668\u6240\u6709\u7269\u54c1", "Container_Get_Container_Items"})
    public static qg ALLATORIxDEMO(ui a2) {
        if (a2 == null) {
            return new qg();
        }
        ArrayList<v> a3 = new ArrayList<v>();
        List a4 = ((Container)a2.h).inventorySlots;
        int a5 = a2.h instanceof ContainerPlayer ? 0 : 36;
        for (int a6 = 0; a6 < a4.size() - a5; ++a6) {
            a3.add(new xk(((Slot)a4.get(a6)).getStack()));
        }
        return new qg(a3);
    }

    @i(f={"\u4e22\u5f03\u624b\u4e2d\u7269\u54c1", "Container_Drop"})
    public static void ALLATORIxDEMO() {
        Minecraft a2 = Minecraft.getMinecraft();
        a2.playerController.windowClick(a2.player.openContainer.windowId, -999, 0, ClickType.THROW, (EntityPlayer)a2.player);
    }

    @i(f={"\u6a21\u62df\u70b9\u51fb\u69fd\u4f4d", "Container_Click"})
    public static void ALLATORIxDEMO(String a2, int a3, String a4, double a5) {
        if (a2.isEmpty()) {
            return;
        }
        Minecraft a6 = Minecraft.getMinecraft();
        a4 = a4.isEmpty() ? "PICKUP" : a4;
        Slot a7 = wi.b.ALLATORIxDEMO(a2, a6.player.openContainer);
        if (a7 == null) {
            return;
        }
        try {
            a6.playerController.windowClick(a6.player.openContainer.windowId, a7.slotNumber, a3, ClickType.valueOf((String)a4.toUpperCase(Locale.ROOT)), (EntityPlayer)a6.player);
            if (a5 != 0.0) {
                a6.player.inventory.setItemStack(ItemStack.EMPTY);
            }
        }
        catch (Exception a8) {
            a8.printStackTrace();
        }
    }
}

