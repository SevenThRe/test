/*
 * Decompiled with CFR 0.152.
 */
package imcheck;

import com.sun.jna.Native;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinNT;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class IMManager {
    private static final User32 k;
    private static boolean ALLATORIxDEMO;

    public IMManager() {
        IMManager a2;
    }

    private static native /* synthetic */ WinNT.HANDLE ImmGetContext(WinDef.HWND var0);

    private static native /* synthetic */ WinNT.HANDLE ImmAssociateContext(WinDef.HWND var0, WinNT.HANDLE var1);

    private static native /* synthetic */ boolean ImmReleaseContext(WinDef.HWND var0, WinNT.HANDLE var1);

    private static native /* synthetic */ WinNT.HANDLE ImmCreateContext();

    private static native /* synthetic */ boolean ImmDestroyContext(WinNT.HANDLE var0);

    private static /* synthetic */ void x() {
        WinDef.HWND a2 = k.GetForegroundWindow();
        WinNT.HANDLE a3 = IMManager.ImmGetContext(a2);
        if (a3 == null) {
            a3 = IMManager.ImmCreateContext();
            IMManager.ImmAssociateContext(a2, a3);
        }
        IMManager.ImmReleaseContext(a2, a3);
    }

    private static /* synthetic */ void f() {
        WinDef.HWND a2 = k.GetForegroundWindow();
        WinNT.HANDLE a3 = IMManager.ImmAssociateContext(a2, null);
        if (a3 != null) {
            IMManager.ImmDestroyContext(a3);
        }
        IMManager.ImmReleaseContext(a2, a3);
    }

    private static /* synthetic */ boolean f() {
        WinDef.HWND a2 = k.GetForegroundWindow();
        WinNT.HANDLE a3 = IMManager.ImmGetContext(a2);
        if (a3 == null) {
            a3 = IMManager.ImmCreateContext();
            IMManager.ImmAssociateContext(a2, a3);
            IMManager.ImmReleaseContext(a2, a3);
            return true;
        }
        a3 = IMManager.ImmAssociateContext(a2, null);
        IMManager.ImmDestroyContext(a3);
        IMManager.ImmReleaseContext(a2, a3);
        return false;
    }

    public static void c() {
        if (!ALLATORIxDEMO) {
            IMManager.x();
            ALLATORIxDEMO = true;
        }
    }

    public static void ALLATORIxDEMO() {
        if (ALLATORIxDEMO) {
            IMManager.f();
            ALLATORIxDEMO = false;
        }
    }

    public static void ALLATORIxDEMO(boolean a2) {
        if (ALLATORIxDEMO == a2) {
            return;
        }
        if (a2) {
            IMManager.x();
            ALLATORIxDEMO = true;
        } else {
            IMManager.f();
            ALLATORIxDEMO = false;
        }
    }

    public static boolean c() {
        return ALLATORIxDEMO;
    }

    public static boolean ALLATORIxDEMO() {
        ALLATORIxDEMO = IMManager.f();
        return ALLATORIxDEMO;
    }

    static {
        Native.register("imm32");
        k = User32.INSTANCE;
        ALLATORIxDEMO = true;
    }
}

