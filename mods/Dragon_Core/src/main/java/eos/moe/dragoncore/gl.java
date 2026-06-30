/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Predicate
 *  com.google.common.base.Predicates
 *  net.minecraft.client.Minecraft
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.item.EntityArmorStand
 *  net.minecraft.entity.item.EntityItemFrame
 *  net.minecraft.util.EntitySelectors
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.RayTraceResult
 *  net.minecraft.util.math.RayTraceResult$Type
 *  net.minecraft.util.math.Vec3d
 *  org.lwjgl.input.Keyboard
 *  org.lwjgl.input.Mouse
 *  org.lwjgl.opengl.Display
 */
package eos.moe.dragoncore;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import eos.moe.dragoncore.EntityList;
import eos.moe.dragoncore.i;
import eos.moe.dragoncore.ie;
import eos.moe.dragoncore.oa;
import eos.moe.dragoncore.sj;
import eos.moe.dragoncore.ui;
import eos.moe.dragoncore.v;
import eos.moe.dragoncore.wk;
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.item.EntityItemFrame;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class gl {
    public gl() {
        gl a2;
    }

    @i(f={"\u53d6\u6eda\u8f6e\u503c", "mouse_get_wheel"})
    public static int ALLATORIxDEMO() {
        int a2 = Mouse.getEventDWheel();
        return Integer.compare(a2, 0);
    }

    @i(f={"\u53d6\u9f20\u6807x", "Mouse_Get_X"})
    public static double x() {
        return sj.b;
    }

    @i(f={"\u53d6\u9f20\u6807y", "Mouse_Get_Y"})
    public static double f() {
        return sj.o;
    }

    @i(f={"\u53d6\u9f20\u6807deltax", "Mouse_Get_deltaX"})
    public static double c() {
        return sj.y;
    }

    @i(f={"\u53d6\u9f20\u6807deltay", "Mouse_Get_deltaY"})
    public static double ALLATORIxDEMO() {
        return sj.k;
    }

    @i(f={"\u8bbe\u7f6e\u526a\u5207\u677f", "Clipboard_Set"})
    public static void f(String a2) {
        try {
            StringSelection a3 = new StringSelection(a2);
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(a3, null);
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    @i(f={"\u8bbe\u7f6e\u9f20\u6807", "\u8bbe\u7f6e\u9f20\u6807\u8d34\u56fe"}, c=true)
    public static void ALLATORIxDEMO(String a2, String a3) {
        if (a3.isEmpty()) {
            a3 = a2;
        }
        oa.ALLATORIxDEMO(a2, a3);
    }

    @i(f={"\u79fb\u52a8\u9f20\u6807"}, c=true)
    public static void ALLATORIxDEMO(int a2, int a3) {
        Mouse.setCursorPosition((int)a2, (int)(Display.getHeight() - a3));
        Mouse.setGrabbed((boolean)false);
    }

    @i(f={"\u53d6\u526a\u5207\u677f", "Clipboard_Get"})
    public static String c(String a2) {
        try {
            Transferable a3 = Toolkit.getDefaultToolkit().getSystemClipboard().getContents(null);
            if (a3 != null && a3.isDataFlavorSupported(DataFlavor.stringFlavor)) {
                return (String)a3.getTransferData(DataFlavor.stringFlavor);
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
        return "";
    }

    @i(f={"\u952e\u4f4d\u662f\u5426\u6309\u4e0b", "Key_IsPress"})
    public static boolean ALLATORIxDEMO(String a2) {
        switch (a2.toUpperCase(Locale.ROOT)) {
            case "MOUSE_LEFT": {
                return Mouse.isButtonDown((int)0);
            }
            case "MOUSE_RIGHT": {
                return Mouse.isButtonDown((int)1);
            }
            case "MOUSE_MIDDLE": {
                return Mouse.isButtonDown((int)2);
            }
        }
        int a3 = Keyboard.getKeyIndex((String)a2);
        return a3 != 0 && Keyboard.isKeyDown((int)a3);
    }

    @i(f={"\u53d6\u5f53\u524d\u6309\u4e0b\u952e", "Key_Get_Press"})
    public static String ALLATORIxDEMO(ui a2) {
        if (a2 == null) {
            return "";
        }
        return a2.ba;
    }

    @i(f={"\u6a21\u62df\u6309\u952e", "Key_Press"}, c=true)
    public static void ALLATORIxDEMO(String a2, v a3) {
        if (a3 == null || a3 instanceof wk) {
            gl.ALLATORIxDEMO(a2, true);
            ie.ALLATORIxDEMO.execute(() -> {
                try {
                    Thread.sleep(50L);
                    Minecraft.getMinecraft().addScheduledTask(() -> gl.ALLATORIxDEMO(a2, false));
                }
                catch (InterruptedException a3) {
                    a3.printStackTrace();
                }
            });
        } else {
            gl.ALLATORIxDEMO(a2, a3.ALLATORIxDEMO() != 0.0);
        }
    }

    private static /* synthetic */ void ALLATORIxDEMO(String a2, boolean a3) {
        int a4 = Keyboard.getKeyIndex((String)a2);
        if (a4 != 0) {
            gl.ALLATORIxDEMO(a4, a3);
        }
    }

    private static /* synthetic */ void ALLATORIxDEMO(int a2, boolean a3) {
        try {
            long a4 = Minecraft.getSystemTime();
            byte a5 = (byte)(a3 ? 1 : 0);
            Field a6 = Keyboard.class.getDeclaredField("implementation");
            a6.setAccessible(true);
            Object a7 = a6.get(null);
            switch (a7.getClass().getName()) {
                case "org.lwjgl.opengl.WindowsDisplay": {
                    Class<?> a8 = Class.forName("org.lwjgl.opengl.WindowsDisplay");
                    Field a9 = a8.getDeclaredField("keyboard");
                    a9.setAccessible(true);
                    Object a10 = a9.get(a7);
                    Method a11 = a10.getClass().getDeclaredMethod("putEvent", Integer.TYPE, Byte.TYPE, Integer.TYPE, Long.TYPE, Boolean.TYPE);
                    a11.setAccessible(true);
                    a11.invoke(a10, a2, a5, 0, a4, false);
                    Field a12 = a10.getClass().getDeclaredField("key_down_buffer");
                    a12.setAccessible(true);
                    byte[] a13 = (byte[])a12.get(a10);
                    a13[a2] = a5;
                    break;
                }
                case "org.lwjgl.opengl.LinuxDisplay": {
                    Class<?> a14 = Class.forName("org.lwjgl.opengl.LinuxDisplay");
                    Field a15 = a14.getDeclaredField("keyboard");
                    a15.setAccessible(true);
                    Object a16 = a15.get(a7);
                    Method a17 = a16.getClass().getDeclaredMethod("putKeyboardEvent", Integer.TYPE, Byte.TYPE, Integer.TYPE, Long.TYPE, Boolean.TYPE);
                    a17.setAccessible(true);
                    a17.invoke(a16, a2, a5, 0, a4 *= 1000000L, false);
                    Field a18 = a16.getClass().getDeclaredField("key_down_buffer");
                    a18.setAccessible(true);
                    byte[] a19 = (byte[])a18.get(a16);
                    a19[a2] = a5;
                    break;
                }
                case "org.lwjgl.opengl.MacOSXDisplay": {
                    Class<?> a20 = Class.forName("org.lwjgl.opengl.MacOSXDisplay");
                    Field a21 = a20.getDeclaredField("native_mode");
                    a21.setAccessible(true);
                    boolean a22 = (Boolean)a21.get(a7);
                    Field a23 = a22 ? a20.getDeclaredField("keyboard") : a20.getDeclaredField("keyboard_queue");
                    a23.setAccessible(true);
                    Object a24 = a23.get(a7);
                    Method a25 = a24.getClass().getDeclaredMethod("putKeyboardEvent", Integer.TYPE, Byte.TYPE, Integer.TYPE, Long.TYPE, Boolean.TYPE);
                    a25.setAccessible(true);
                    a25.invoke(a24, a2, a5, 0, a4 *= 1000000L, false);
                    Field a26 = a24.getClass().getDeclaredField("key_states");
                    a26.setAccessible(true);
                    byte[] a27 = (byte[])a26.get(a24);
                    a27[a2] = a5;
                }
            }
        }
        catch (Exception a28) {
            a28.printStackTrace();
        }
    }

    @i(f={"\u53d6\u751f\u7269\u540d", "Mouse_Entity_Name"})
    public static String ALLATORIxDEMO(String a2) {
        try {
            UUID a3 = UUID.fromString(a2);
            return EntityList.getLivingEntityByUUID(a3).getName();
        }
        catch (Exception a4) {
            return "exception";
        }
    }

    @i(f={"\u53d6\u751f\u7269\u8840\u91cf", "Mouse_Entity_Health"})
    public static double c(String a2) {
        try {
            UUID a3 = UUID.fromString(a2);
            return EntityList.getLivingEntityByUUID(a3).getHealth();
        }
        catch (Exception a4) {
            return 0.0;
        }
    }

    @i(f={"\u53d6\u751f\u7269\u6700\u5927\u8840\u91cf", "Mouse_Entity_Max_Health"})
    public static double ALLATORIxDEMO(String a2) {
        try {
            UUID a3 = UUID.fromString(a2);
            return EntityList.getLivingEntityByUUID(a3).getMaxHealth();
        }
        catch (Exception a4) {
            return 1.0;
        }
    }

    @i(f={"\u53d6\u6307\u9488\u751f\u7269\u540d", "Mouse_Entity_Name"})
    public static String c(v a2, v a3) {
        RayTraceResult a4 = gl.ALLATORIxDEMO(a2, a3);
        if (a4.entityHit != null) {
            return a4.entityHit.getDisplayName().getFormattedText();
        }
        return "";
    }

    @i(f={"\u53d6\u6307\u9488\u751f\u7269UUID", "Mouse_Entity_UUID"})
    public static String ALLATORIxDEMO(v a2, v a3) {
        RayTraceResult a4 = gl.ALLATORIxDEMO(a2, a3);
        if (a4.entityHit != null) {
            return a4.entityHit.getUniqueID().toString();
        }
        return "";
    }

    @i(f={"\u53d6\u6307\u9488\u751f\u7269\u8840\u91cf", "Mouse_Entity_Health"})
    public static double c(v a2, v a3) {
        RayTraceResult a4 = gl.ALLATORIxDEMO(a2, a3);
        if (a4.entityHit instanceof EntityLivingBase) {
            return ((EntityLivingBase)a4.entityHit).getHealth();
        }
        return 0.0;
    }

    @i(f={"\u53d6\u6307\u9488\u751f\u7269\u6700\u5927\u8840\u91cf", "Mouse_Entity_Max_Health"})
    public static double ALLATORIxDEMO(v a2, v a3) {
        RayTraceResult a4 = gl.ALLATORIxDEMO(a2, a3);
        if (a4.entityHit instanceof EntityLivingBase) {
            return ((EntityLivingBase)a4.entityHit).getMaxHealth();
        }
        return 0.0;
    }

    private static /* synthetic */ RayTraceResult ALLATORIxDEMO(v a2, v a3) {
        if (a2 == wk.k) {
            return Minecraft.getMinecraft().objectMouseOver;
        }
        RayTraceResult a4 = gl.ALLATORIxDEMO(a2.ALLATORIxDEMO(), a3.ALLATORIxDEMO());
        return a4 == null ? Minecraft.getMinecraft().objectMouseOver : a4;
    }

    private static /* synthetic */ RayTraceResult ALLATORIxDEMO(double a3, boolean a4) {
        RayTraceResult a5 = null;
        Minecraft a6 = Minecraft.getMinecraft();
        Entity a7 = a6.getRenderViewEntity();
        if (a7 != null && a6.world != null) {
            double a8 = a3;
            a5 = a7.rayTrace(a8, 0.0f);
            Vec3d a9 = a7.getPositionEyes(0.0f);
            boolean a10 = false;
            double a11 = a8;
            if (a6.playerController.extendedReach() && a11 < 6.0) {
                a8 = 6.0;
                a11 = 6.0;
            } else if (a8 > a3) {
                a10 = true;
            }
            if (a5 != null) {
                a11 = a5.hitVec.distanceTo(a9);
            }
            Vec3d a12 = a7.getLook(0.0f);
            Vec3d a13 = a9.add(a12.x * a8, a12.y * a8, a12.z * a8);
            Entity a14 = null;
            Vec3d a15 = null;
            Predicate a16 = Predicates.and((Predicate)EntitySelectors.NOT_SPECTATING, a2 -> a2 != null && a2.canBeCollidedWith());
            if (a4) {
                a16 = Predicates.and((Predicate)a16, a2 -> !(a2 instanceof EntityArmorStand));
            }
            List a17 = a6.world.getEntitiesInAABBexcluding(a7, a7.getEntityBoundingBox().expand(a12.x * a8, a12.y * a8, a12.z * a8).expand(1.0, 1.0, 1.0), a16);
            double a18 = a11;
            for (Entity a19 : a17) {
                double a20;
                AxisAlignedBB a21 = a19.getEntityBoundingBox().grow((double)a19.getCollisionBorderSize());
                RayTraceResult a22 = a21.calculateIntercept(a9, a13);
                if (a21.contains(a9)) {
                    if (!(a18 >= 0.0)) continue;
                    a14 = a19;
                    a15 = a22 == null ? a9 : a22.hitVec;
                    a18 = 0.0;
                    continue;
                }
                if (a22 == null || !((a20 = a9.distanceTo(a22.hitVec)) < a18) && a18 != 0.0) continue;
                if (a19.getLowestRidingEntity() == a7.getLowestRidingEntity() && !a7.canRiderInteract()) {
                    if (a18 != 0.0) continue;
                    a14 = a19;
                    a15 = a22.hitVec;
                    continue;
                }
                a14 = a19;
                a15 = a22.hitVec;
                a18 = a20;
            }
            if (a14 != null && a10 && a9.distanceTo(a15) > a3) {
                a14 = null;
                a5 = new RayTraceResult(RayTraceResult.Type.MISS, a15, null, new BlockPos(a15));
            }
            if (a14 != null && (a18 < a11 || a5 == null)) {
                a5 = new RayTraceResult(a14, a15);
                if (a14 instanceof EntityLivingBase || a14 instanceof EntityItemFrame) {
                    // empty if block
                }
            }
        }
        return a5;
    }
}

