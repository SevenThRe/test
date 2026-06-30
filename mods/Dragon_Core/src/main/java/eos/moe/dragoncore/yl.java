/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.lwjgl.Sys
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.i;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.lwjgl.Sys;

public class yl {
    private static final SimpleDateFormat ALLATORIxDEMO = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public yl() {
        yl a2;
    }

    @i(f={"\u53d6OpenGL\u65f6\u95f4", "Time_OpenGL"})
    public static double c() {
        return Sys.getTime();
    }

    @i(f={"\u53d6\u5f53\u524d\u65f6\u95f4", "Time_Current"})
    public static double ALLATORIxDEMO() {
        return System.currentTimeMillis();
    }

    @i(f={"\u53d6\u5f53\u524d\u65f6\u95f4\u683c\u5f0f\u5316", "Time_Current_Format"})
    public static String ALLATORIxDEMO(String a2) {
        if (!a2.isEmpty()) {
            return new SimpleDateFormat(a2).format(new Date());
        }
        return ALLATORIxDEMO.format(new Date());
    }

    @i(f={"\u683c\u5f0f\u5316\u65f6\u95f4", "Time_Format"})
    public static String ALLATORIxDEMO(double a2, String a3) {
        if (!a3.isEmpty()) {
            return new SimpleDateFormat(a3).format(new Date((long)a2));
        }
        return ALLATORIxDEMO.format(new Date((long)a2));
    }
}

