/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraftforge.common.config.Config
 *  net.minecraftforge.common.config.Config$Comment
 *  net.minecraftforge.common.config.Config$Name
 *  net.minecraftforge.common.config.Config$Type
 */
package journeymap.server.config;

import net.minecraftforge.common.config.Config;

@Config(modid="journeymap", type=Config.Type.INSTANCE, name="journeymap_server", category="server")
public class ForgeConfig {
    @Config.Name(value="Ops Admin Access")
    @Config.Comment(value={"Default, all Ops have access to Server Admin UI in the Options screen.", "If set to false, only users in the Admin List will have access.", "If set to true, all ops and users in the Admin List will have access."})
    public static Boolean opAccess = true;
    @Config.Name(value="Journeymap Server Admins")
    @Config.Comment(value={"Players in this list have access to the Journeymap's Server Admin Panel", "Add users by name or UUID, Prefer UUID as it is more secure!", "Each value on a new line with the example format provided. (please delete the default values)"})
    public static String[] playerNames = new String[]{"mysticdrew", "12341234132"};
}

