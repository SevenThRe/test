/*
 * Decompiled with CFR 0.152.
 */
package optifine;

import java.lang.reflect.Field;
import java.util.Map;
import optifine.Utils;

public class LaunchUtils {
    private static Boolean forgeServer = null;

    public static boolean isForgeServer() {
        if (forgeServer == null) {
            try {
                Class<?> cls = Class.forName("net.minecraft.launchwrapper.Launch");
                Field fieldBlackboard = cls.getField("blackboard");
                Map blackboard = (Map)fieldBlackboard.get(null);
                Map launchArgs = (Map)blackboard.get("launchArgs");
                String accessToken = (String)launchArgs.get("--accessToken");
                String version = (String)launchArgs.get("--version");
                boolean onServer = accessToken == null && Utils.equals(version, "UnknownFMLProfile");
                forgeServer = onServer;
            }
            catch (Throwable e) {
                System.out.println("Error checking Forge server: " + e.getClass().getName() + ": " + e.getMessage());
                forgeServer = Boolean.FALSE;
            }
        }
        return forgeServer;
    }
}

