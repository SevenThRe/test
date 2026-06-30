/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Joiner
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.command.CommandException
 *  net.minecraft.command.ICommand
 *  net.minecraft.command.ICommandSender
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.text.TextFormatting
 *  net.minecraftforge.fml.client.FMLClientHandler
 */
package journeymap.client.command;

import com.google.common.base.Joiner;
import java.util.List;
import journeymap.client.JourneymapClient;
import journeymap.client.task.main.IMainThreadTask;
import journeymap.client.ui.waypoint.WaypointChat;
import journeymap.common.Journeymap;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.client.FMLClientHandler;

public class CmdChatPosition
implements ICommand {
    public String func_71517_b() {
        return "~";
    }

    public String func_71518_a(ICommandSender sender) {
        return TextFormatting.AQUA + "~" + TextFormatting.RESET + " : Copy your location into Text";
    }

    public List<String> func_71514_a() {
        return null;
    }

    public void func_184881_a(MinecraftServer server, ICommandSender sender, String[] args2) throws CommandException {
        Object pos;
        String text;
        if (args2.length > 1) {
            text = Joiner.on((String)"").skipNulls().join((Object[])args2);
        } else {
            pos = sender.func_180425_c();
            text = String.format("[x:%s, y:%s, z:%s]", pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p());
        }
        pos = text;
        Journeymap.getClient().queueMainThreadTask(new IMainThreadTask((String)pos){
            final /* synthetic */ String val$pos;
            {
                this.val$pos = string;
            }

            @Override
            public IMainThreadTask perform(Minecraft mc, JourneymapClient jm) {
                FMLClientHandler.instance().getClient().func_147108_a((GuiScreen)new WaypointChat(this.val$pos));
                return null;
            }

            @Override
            public String getName() {
                return "Edit Waypoint";
            }
        });
    }

    public boolean func_184882_a(MinecraftServer server, ICommandSender sender) {
        return true;
    }

    public List<String> func_184883_a(MinecraftServer server, ICommandSender sender, String[] args2, BlockPos pos) {
        return null;
    }

    public boolean func_82358_a(String[] args2, int index) {
        return false;
    }

    public int compareTo(ICommand o) {
        return 0;
    }
}

