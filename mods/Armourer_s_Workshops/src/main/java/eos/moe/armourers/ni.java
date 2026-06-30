/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraftforge.client.event.RenderItemInFrameEvent
 *  net.minecraftforge.fml.common.Mod$EventBusSubscriber
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  org.lwjgl.opengl.GL11
 */
package eos.moe.armourers;

import eos.moe.armourers.fk;
import eos.moe.armourers.ih;
import eos.moe.armourers.kd;
import eos.moe.armourers.xd;
import eos.moe.armourers.yl;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.client.event.RenderItemInFrameEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.opengl.GL11;

@Mod.EventBusSubscriber(modid="armourers_workshops")
public class ni {
    private static /* synthetic */ List<String> y(ItemStack a2) {
        ArrayList<String> arrayList = new ArrayList<String>();
        for (String string : ni.r(a2)) {
            if (!string.startsWith("\u5c55\u793a\u65f6\u88c5:")) continue;
            arrayList.add(string.replace("\u5c55\u793a\u65f6\u88c5:", ""));
        }
        return arrayList;
    }

    @SubscribeEvent
    public static void r(RenderItemInFrameEvent a2) {
        Object object;
        Object object2 = fk.r(a2.getItem());
        if (object2 != null && (object = ((fk)object2).r()) != null) {
            ni.r((yl)object, 0.0, 0.0, 0.0, ni.r(a2.getItem()), ni.y(a2.getItem()));
        }
        object = ni.y(a2.getItem());
        Object object3 = object2 = object.iterator();
        while (object3.hasNext()) {
            object = (String)object2.next();
            ni.r(kd.j.getSkin((String)object), 0.0, 0.0, 0.0, ni.r(a2.getItem()), ni.y(a2.getItem()));
            object3 = object2;
        }
    }

    private static /* synthetic */ int y(ItemStack a2) {
        try {
            return Integer.parseInt(ni.r(a2).get(1));
        }
        catch (Exception exception) {
            return 0;
        }
    }

    private static /* synthetic */ int r(ItemStack a2) {
        try {
            return Integer.parseInt(ni.r(a2).get(0));
        }
        catch (Exception exception) {
            return 10000;
        }
    }

    private static /* synthetic */ List<String> r(ItemStack a2) {
        if (a2 == null || a2.func_190926_b() || !a2.func_77942_o()) {
            return new ArrayList<String>();
        }
        ArrayList<String> arrayList = new ArrayList<String>();
        NBTTagCompound nBTTagCompound = a2.func_77978_p();
        if (nBTTagCompound.func_150297_b("display", 10) && (nBTTagCompound = nBTTagCompound.func_74775_l("display")).func_150299_b("Lore") == 9 && !(nBTTagCompound = nBTTagCompound.func_150295_c("Lore", 8)).func_82582_d()) {
            int n2;
            int n3 = n2 = 0;
            while (n3 < nBTTagCompound.func_74745_c()) {
                String string = nBTTagCompound.func_150307_f(n2);
                arrayList.add(string);
                n3 = ++n2;
            }
        }
        return arrayList;
    }

    public static void r(yl a2, double a3, double a4, double a5, int a6, int a72) {
        if (a2 == null) {
            return;
        }
        GL11.glPushMatrix();
        GL11.glEnable((int)2977);
        GL11.glTranslated((double)a3, (double)(a4 + 0.5), (double)a5);
        GL11.glTranslated((double)0.0, (double)(2 + a72), (double)0.0);
        GL11.glScalef((float)-1.0f, (float)-1.0f, (float)1.0f);
        float a72 = System.currentTimeMillis() % (long)a6;
        a72 = a72 / (float)a6 * 360.0f;
        GL11.glPushMatrix();
        if (a72 != 0.0f) {
            GL11.glRotatef((float)a72, (float)0.0f, (float)1.0f, (float)0.0f);
        }
        xd.x();
        xd.z();
        ih.r(a2, true, false);
        GL11.glPopMatrix();
        xd.r();
        xd.h();
        GL11.glDisable((int)2977);
        GL11.glPopMatrix();
    }

    public ni() {
        ni a2;
    }
}

