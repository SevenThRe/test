/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.command.CommandBase
 *  net.minecraft.command.CommandException
 *  net.minecraft.command.ICommandSender
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.server.MinecraftServer
 */
package journeymap.common.command;

import journeymap.common.feature.JourneyMapTeleport;
import journeymap.common.feature.Location;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;

@Deprecated
public class CommandJTP
extends CommandBase {
    public boolean func_184882_a(MinecraftServer server, ICommandSender sender) {
        return true;
    }

    public String func_71517_b() {
        return "jtp";
    }

    public String func_71518_a(ICommandSender sender) {
        return "/jtp <x y z dim>";
    }

    public void func_184881_a(MinecraftServer server, ICommandSender sender, String[] args2) throws CommandException {
        if (args2.length < 4) {
            throw new CommandException(this.func_71518_a(sender), new Object[0]);
        }
        EntityPlayerMP player = CommandJTP.func_71521_c((ICommandSender)sender);
        try {
            double x = Double.parseDouble(args2[0]);
            double y = Double.parseDouble(args2[1]);
            double z = Double.parseDouble(args2[2]);
            int dim = Integer.parseInt(args2[3]);
            Location location = new Location(x, y, z, dim);
            JourneyMapTeleport.instance().attemptTeleport((Entity)player, location);
        }
        catch (NumberFormatException nfe) {
            throw new CommandException("Numbers only! Usage: " + this.func_71518_a(sender) + nfe, new Object[0]);
        }
        catch (Exception e) {
            throw new CommandException("/jtp failed Usage: " + this.func_71518_a(sender), new Object[0]);
        }
    }
}

