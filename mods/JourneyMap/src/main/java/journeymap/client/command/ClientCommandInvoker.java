/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.command.CommandBase
 *  net.minecraft.command.CommandException
 *  net.minecraft.command.ICommand
 *  net.minecraft.command.ICommandSender
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.text.ITextComponent
 *  net.minecraft.util.text.TextComponentString
 *  org.apache.logging.log4j.util.Strings
 */
package journeymap.client.command;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import journeymap.common.Journeymap;
import journeymap.common.log.LogFormatter;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import org.apache.logging.log4j.util.Strings;

public class ClientCommandInvoker
extends CommandBase {
    Map<String, ICommand> commandMap = new HashMap<String, ICommand>();

    public ClientCommandInvoker register(ICommand command) {
        this.commandMap.put(command.func_71517_b().toLowerCase(), command);
        return this;
    }

    public String func_71517_b() {
        return "jm";
    }

    public String func_71518_a(ICommandSender sender) {
        StringBuffer sb = new StringBuffer();
        for (ICommand command : this.commandMap.values()) {
            String usage = command.func_71518_a(sender);
            if (Strings.isEmpty((CharSequence)usage)) continue;
            if (sb.length() > 0) {
                sb.append("\n");
            }
            sb.append("/jm ").append(usage);
        }
        return sb.toString();
    }

    public List<String> func_71514_a() {
        return Collections.emptyList();
    }

    public void func_184881_a(MinecraftServer server, ICommandSender sender, String[] args2) throws CommandException {
        try {
            if (args2.length > 0) {
                ICommand command = this.getSubCommand(args2);
                if (command != null) {
                    String[] subArgs = Arrays.copyOfRange(args2, 1, args2.length);
                    command.func_184881_a(server, sender, subArgs);
                }
            } else {
                sender.func_145747_a((ITextComponent)new TextComponentString(this.func_71518_a(sender)));
            }
        }
        catch (Throwable t) {
            Journeymap.getLogger().error(LogFormatter.toPartialString(t));
            throw new CommandException("Error in /jm: " + t, new Object[0]);
        }
    }

    public boolean func_184882_a(MinecraftServer server, ICommandSender sender) {
        return true;
    }

    public List<String> func_184883_a(MinecraftServer server, ICommandSender sender, String[] args2, BlockPos pos) {
        return ClientCommandInvoker.func_71530_a((String[])args2, (String[])new String[]{"~"});
    }

    public ICommand getSubCommand(String[] args2) {
        ICommand command;
        if (args2.length > 0 && (command = this.commandMap.get(args2[0].toLowerCase())) != null) {
            return command;
        }
        return null;
    }

    public boolean func_82358_a(String[] args2, int index) {
        return false;
    }

    public int compareTo(ICommand o) {
        return 0;
    }
}

