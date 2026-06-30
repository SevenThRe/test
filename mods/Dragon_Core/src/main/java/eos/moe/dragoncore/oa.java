/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.resources.IResource
 *  net.minecraft.client.resources.IResourceManager
 *  net.minecraft.util.ResourceLocation
 *  net.minecraftforge.client.event.GuiOpenEvent
 *  net.minecraftforge.client.event.GuiScreenEvent$MouseInputEvent$Pre
 *  net.minecraftforge.fml.common.Mod$EventBusSubscriber
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  org.lwjgl.LWJGLException
 *  org.lwjgl.input.Cursor
 *  org.lwjgl.input.Mouse
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.ba;
import eos.moe.dragoncore.ca;
import eos.moe.dragoncore.wka;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.IResource;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Cursor;
import org.lwjgl.input.Mouse;

@Mod.EventBusSubscriber(modid="dragoncore")
public class oa {
    private static final Map<String, Cursor> b = new HashMap<String, Cursor>();
    private static Cursor o;
    private static String y;
    private static String k;
    private static boolean ALLATORIxDEMO;

    public oa() {
        oa a2;
    }

    @SubscribeEvent
    public static void ALLATORIxDEMO(GuiOpenEvent a2) {
        y = "Mouse.png";
        k = ALLATORIxDEMO ? "Mouse_Click.png" : "";
        oa.c(y);
    }

    @SubscribeEvent
    public static void ALLATORIxDEMO(GuiScreenEvent.MouseInputEvent.Pre a2) {
        if (Mouse.getEventButton() != 0) {
            return;
        }
        if (Mouse.getEventButtonState() && !k.isEmpty()) {
            oa.c(k);
        } else {
            oa.c(y);
        }
    }

    public static void c(String a2) {
        oa.ALLATORIxDEMO(a2);
        Cursor a3 = b.get(a2);
        if (o == a3) {
            return;
        }
        o = a3;
        try {
            Mouse.setNativeCursor((Cursor)a3);
        }
        catch (Throwable throwable) {
            // empty catch block
        }
    }

    public static void ALLATORIxDEMO(String a2, String a3) {
        y = a2;
        k = a3;
        if (Mouse.isButtonDown((int)0) && !k.isEmpty()) {
            oa.c(k);
        } else {
            oa.c(y);
        }
    }

    public static void c() {
        oa.ALLATORIxDEMO();
        b.clear();
    }

    private static /* synthetic */ void ALLATORIxDEMO(String a2) {
        if (!wka.k) {
            return;
        }
        if (b.containsKey(a2)) {
            return;
        }
        IResourceManager a3 = Minecraft.func_71410_x().func_110442_L();
        try {
            IResource a4 = a3.func_110536_a(new ResourceLocation("dragoncore", a2));
            Cursor a5 = oa.ALLATORIxDEMO(a4.func_110527_b());
            b.put(a2, a5);
        }
        catch (FileNotFoundException a6) {
            ca.l.ALLATORIxDEMO("\u65e0\u6cd5\u52a0\u8f7d\u7684\u9f20\u6807\u56fe\u7247: \u6587\u4ef6\u7f3a\u5931 Mouse.png", a6);
            b.put(a2, null);
        }
        catch (Throwable a7) {
            ca.l.ALLATORIxDEMO("\u65e0\u6cd5\u52a0\u8f7d\u7684\u9f20\u6807\u56fe\u7247:" + a2, a7);
            b.put(a2, null);
        }
    }

    public static Cursor ALLATORIxDEMO(InputStream a2) throws IOException, LWJGLException {
        int a3;
        int a4 = 0;
        int a5 = 0;
        ba a6 = new ba();
        a6.ALLATORIxDEMO(false);
        ByteBuffer a7 = a6.ALLATORIxDEMO(a2, true, true, null);
        for (a3 = 0; a3 < a7.limit(); a3 += 4) {
            byte a8 = a7.get(a3);
            byte a9 = a7.get(a3 + 1);
            byte a10 = a7.get(a3 + 2);
            byte a11 = a7.get(a3 + 3);
            a7.put(a3 + 2, a8);
            a7.put(a3 + 1, a9);
            a7.put(a3, a10);
            a7.put(a3 + 3, a11);
        }
        try {
            a3 = a6.x() - a5 - 1;
            if (a3 < 0) {
                a3 = 0;
            }
            return new Cursor(a6.c(), a6.f(), a4, a3, 1, a7.asIntBuffer(), null);
        }
        catch (Throwable a12) {
            throw new LWJGLException("Chances are you cursor is too small for this platform", a12);
        }
    }

    public static void ALLATORIxDEMO() {
        IResourceManager a2 = Minecraft.func_71410_x().func_110442_L();
        try {
            a2.func_110536_a(new ResourceLocation("dragoncore", "Mouse_Click.png"));
            ALLATORIxDEMO = true;
        }
        catch (Throwable throwable) {
            // empty catch block
        }
    }

    static {
        y = "Mouse.png";
        k = "";
    }
}

