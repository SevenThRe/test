/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Joiner
 *  net.minecraft.client.Minecraft
 *  net.minecraft.command.CommandException
 *  net.minecraft.command.ICommand
 *  net.minecraft.command.ICommandSender
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.util.math.BlockPos
 *  org.lwjgl.input.Keyboard
 */
package journeymap.client.command;

import com.google.common.base.Joiner;
import java.util.List;
import journeymap.client.JourneymapClient;
import journeymap.client.log.ChatLog;
import journeymap.client.model.Waypoint;
import journeymap.client.task.main.IMainThreadTask;
import journeymap.client.ui.UIManager;
import journeymap.client.waypoint.WaypointParser;
import journeymap.common.Journeymap;
import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import org.lwjgl.input.Keyboard;

public class CmdEditWaypoint
implements ICommand {
    public String getName() {
        return "wpedit";
    }

    public String getUsage(ICommandSender sender) {
        return null;
    }

    public List<String> getAliases() {
        return null;
    }

    public void execute(MinecraftServer server, ICommandSender sender, String[] args2) throws CommandException {
        String text = Joiner.on((String)" ").skipNulls().join((Object[])args2);
        final Waypoint waypoint = WaypointParser.parse(text);
        if (waypoint != null) {
            final boolean controlDown = Keyboard.isKeyDown((int)29) || Keyboard.isKeyDown((int)157);
            Journeymap.getClient().queueMainThreadTask(new IMainThreadTask(){

                @Override
                public IMainThreadTask perform(Minecraft mc, JourneymapClient jm) {
                    if (controlDown) {
                        if (waypoint.isInPlayerDimension()) {
                            waypoint.setPersistent(false);
                            UIManager.INSTANCE.openFullscreenMap(waypoint);
                        } else {
                            ChatLog.announceError("Location is not in your dimension");
                        }
                    } else {
                        UIManager.INSTANCE.openWaypointEditor(waypoint, true, null);
                    }
                    return null;
                }

                @Override
                public String getName() {
                    return "Edit Waypoint";
                }
            });
        } else {
            ChatLog.announceError("Not a valid waypoint. Use: 'x:3, z:70', etc. : " + text);
        }
    }

    public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
        return true;
    }

    public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args2, BlockPos pos) {
        return null;
    }

    public boolean isUsernameIndex(String[] args2, int index) {
        return false;
    }

    public int compareTo(ICommand o) {
        return 0;
    }
}

