/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Joiner
 *  net.minecraft.entity.Entity
 *  net.minecraft.server.MinecraftServer
 *  net.minecraftforge.fml.common.FMLCommonHandler
 */
package journeymap.server;

import com.google.common.base.Joiner;
import java.io.File;
import journeymap.common.Journeymap;
import journeymap.server.JourneymapServer;
import net.minecraft.entity.Entity;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.fml.common.FMLCommonHandler;

public class Constants {
    public static MinecraftServer SERVER = FMLCommonHandler.instance().getMinecraftServerInstance();
    private static final Joiner path = Joiner.on((String)File.separator).useForNull("");
    private static final String END = null;
    public static final File MC_DATA_DIR = SERVER.func_71238_n();
    public static String JOURNEYMAP_DIR = "journeymap";
    public static final String SERVER_CONFIG_DIR = path.join((Object)MC_DATA_DIR, (Object)JOURNEYMAP_DIR, new Object[]{"server", Journeymap.JM_VERSION.toMajorMinorString(), END});

    public static boolean isDev(Entity sender) {
        return "a4eb5569-bf38-3aef-bc21-2dbd73d30851".equalsIgnoreCase(sender.func_110124_au().toString()) || "a2039b6c-5a3d-407d-b49c-091405062b85".equalsIgnoreCase(sender.func_110124_au().toString());
    }

    public static boolean debugOverride(Entity sender) {
        return JourneymapServer.DEV_MODE && Constants.isDev(sender);
    }
}

