/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraftforge.common.config.Configuration
 *  net.minecraftforge.fml.common.event.FMLPreInitializationEvent
 */
package eos.moe.dragongps;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

/*
 * Renamed from eos.moe.dragongps.IiIIIIiIii
 */
public class iiiiiiiiii_0 {
    public static int IiIIiiIIiI;
    public static int iIIIiIIIIi;
    public static Configuration iIiIIiIiiI;
    public static int iiIIIiIiII;
    public static boolean IIiiIiiIII;
    public static int iIIiIIiIii;
    public static int iIIIIiiIII;
    public static int IIiIiIIIiI;
    public static int IIiiiiiIIi;

    public iiiiiiiiii_0(FMLPreInitializationEvent IIiiiiiIIi) {
        iiiiiiiiii_0 IIiiiiiIIi2;
        iIiIIiIiiI = new Configuration(IIiiiiiIIi.getSuggestedConfigurationFile());
        iIiIIiIiiI.load();
        IIiIiIIIiI = iIiIIiIiiI.get("DragonGPS:QQ448780139", "paneHeight", 200, "\u4efb\u52a1\u9762\u677f\u6eda\u52a8\u6846\u9ad8\u5ea6").getInt();
        IIiiIiiIII = iIiIIiIiiI.get("DragonGPS:QQ448780139", "hidePointRender", false, "\u662f\u5426\u9690\u85cf \u5750\u6807\u70b9/\u5bfc\u822a\u7ebf \u7684\u6e32\u67d3").getBoolean();
        IiIIiiIIiI = iIiIIiIiiI.get("DragonGPS:QQ448780139", "distance", 2, "\u8ddd\u79bb\u591a\u5c11\u7c73\u5185\u505c\u6b62\u6e32\u67d3\u5750\u6807\u70b9").getInt();
        iIIIiIIIIi = iIiIIiIiiI.get("DragonGPS:QQ448780139", "textureHeight", 350, "\u5bfc\u822a\u7ebf\u7ed8\u5236\u56fe\u7247\u7684\u9ad8\u5ea6").getInt();
        iiiiiiiiii_0.IIiiiiiIIi = iIiIIiIiiI.get("DragonGPS:QQ448780139", "textureWidth", 260, "\u5bfc\u822a\u7ebf\u7ed8\u5236\u56fe\u7247\u7684\u5bbd\u5ea6").getInt();
        iiIIIiIiII = iIiIIiIiiI.get("DragonGPS:QQ448780139", "startX", 0, "\u4efb\u52a1\u9762\u677f\u7684\u7ed8\u5236\u5750\u6807\u70b9").getInt();
        iIIiIIiIii = iIiIIiIiiI.get("DragonGPS:QQ448780139", "startY", 0, "\u4efb\u52a1\u9762\u677f\u7684\u7ed8\u5236\u5750\u6807\u70b9").getInt();
        iIiIIiIiiI.save();
    }

    public static void IIIiiiIiii() {
        iIiIIiIiiI.get("DragonGPS:QQ448780139", "startX", 0, "\u4efb\u52a1\u9762\u677f\u7684\u7ed8\u5236\u5750\u6807\u70b9").setValue(iiIIIiIiII);
        iIiIIiIiiI.get("DragonGPS:QQ448780139", "startY", 0, "\u4efb\u52a1\u9762\u677f\u7684\u7ed8\u5236\u5750\u6807\u70b9").setValue(iIIiIIiIii);
        iIiIIiIiiI.save();
    }

    static {
        iIIIiIIIIi = 350;
        IIiiiiiIIi = 260;
    }

    public static void IIIiiiIiii(boolean IIiiiiiIIi) {
        IIiiIiiIII = IIiiiiiIIi;
        iIiIIiIiiI.get("DragonGPS:QQ448780139", "hidePointRender", false, "\u662f\u5426\u9690\u85cf \u5750\u6807\u70b9/\u5bfc\u822a\u7ebf \u7684\u6e32\u67d3").setValue(IIiiiiiIIi);
        iIiIIiIiiI.save();
    }
}

