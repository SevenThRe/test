/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraftforge.common.config.Configuration
 *  net.minecraftforge.fml.common.event.FMLPreInitializationEvent
 *  org.apache.logging.log4j.Logger
 */
package eos.moe.armourers;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class zh {
    public static boolean g = true;
    private static Configuration z;
    public static boolean t;
    public static boolean w;
    public static boolean r;
    public static boolean l;
    public static float c;
    private static Logger v;
    public static boolean s;
    public static boolean m;
    public static boolean j;

    public static Logger r() {
        return v;
    }

    static {
        s = true;
    }

    public static void r() {
        v.info("Started loading config. ");
        String string = "DragonArmourers";
        t = z.getBoolean("ChangeSkinLibrary", string, false, "\u662f\u5426\u5c06\u8d44\u6e90\u6587\u4ef6\u8f6c\u5230resourcepacks\u5185(\u5165\u9a7b\u7f51\u6613\u53ef\u80fd\u9700\u8981\u4fee\u6539\u6b64\u9009\u9879\u4e3atrue)");
        m = z.getBoolean("ChangeChannel", string, false, "\u662f\u5426\u6539\u53d8\u901a\u8bafID(\u4e0d\u7528\u7406\u4f1a\u8be5\u9009\u9879)");
        l = z.getBoolean("ShowItemSkin", string, true, "\u662f\u5426\u663e\u793a\u80cc\u5305/\u6389\u843d\u7269\u7684\u65f6\u88c5\u6a21\u578b");
        w = z.getBoolean("ShowItemSkinHand", string, true, "\u662f\u5426\u663e\u793a\u624b\u4e2d\u7684\u65f6\u88c5\u6a21\u578b");
        r = z.getBoolean("ShowItemSkinTip", string, false, "\u662f\u5426\u5728\u7269\u54c1\u60ac\u6d6e\u4fe1\u606f\u5185\u663e\u793a\u65f6\u88c5\u540d\u79f0");
        z.save();
        v.info("Finished loading config. ");
    }

    public zh(FMLPreInitializationEvent a2) {
        zh a3;
        v = a2.getModLog();
        z = new Configuration(a2.getSuggestedConfigurationFile());
        z.load();
        zh.r();
    }
}

