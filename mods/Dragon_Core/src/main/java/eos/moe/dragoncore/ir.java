/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.command.CommandBase
 *  net.minecraft.command.CommandException
 *  net.minecraft.command.ICommandSender
 *  net.minecraft.command.WrongUsageException
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.util.math.BlockPos
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessage
 *  org.apache.logging.log4j.Level
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.ov;
import eos.moe.dragoncore.pj;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import org.apache.logging.log4j.Level;

public class ir
extends CommandBase {
    public ir() {
        ir a2;
    }

    public String func_71517_b() {
        return "nbtedit";
    }

    public String func_71518_a(ICommandSender a2) {
        return "/nbtedit OR /nbtedit <EntityId> OR /nbtedit <TileX> <TileY> <TileZ>";
    }

    public void func_184881_a(MinecraftServer a2, ICommandSender a3, String[] a4) throws CommandException {
        if (a3 instanceof EntityPlayerMP) {
            EntityPlayerMP a5 = (EntityPlayerMP)a3;
            if (a4.length == 3) {
                int a6 = ir.func_175755_a((String)a4[0]);
                int a7 = ir.func_175755_a((String)a4[1]);
                int a8 = ir.func_175755_a((String)a4[2]);
                ov.ALLATORIxDEMO(Level.TRACE, a3.func_70005_c_() + " issued command \"/nbtedit " + a6 + " " + a7 + " " + a8 + "\"");
                ov.v.ALLATORIxDEMO(a5, new BlockPos(a6, a7, a8));
            } else if (a4.length == 1) {
                int a9 = a4[0].equalsIgnoreCase("me") ? a5.func_145782_y() : ir.func_180528_a((String)a4[0], (int)0);
                ov.ALLATORIxDEMO(Level.TRACE, a3.func_70005_c_() + " issued command \"/nbtedit " + a9 + "\"");
                ov.v.ALLATORIxDEMO(a5, a9);
            } else if (a4.length == 0) {
                ov.ALLATORIxDEMO(Level.TRACE, a3.func_70005_c_() + " issued command \"/nbtedit\"");
                ov.v.k.sendTo((IMessage)new pj(), a5);
            } else {
                String a10 = "";
                for (int a11 = 0; a11 < a4.length; ++a11) {
                    a10 = a10 + a4[a11];
                    if (a11 == a4.length - 1) continue;
                    a10 = a10 + " ";
                }
                ov.ALLATORIxDEMO(Level.TRACE, a3.func_70005_c_() + " issued invalid command \"/nbtedit " + a10 + "\"");
                throw new WrongUsageException("Pass 0, 1, or 3 integers -- ex. /nbtedit", new Object[0]);
            }
        }
    }

    public boolean func_184882_a(MinecraftServer a2, ICommandSender a3) {
        return a3 instanceof EntityPlayer && ov.y.ALLATORIxDEMO((EntityPlayer)a3);
    }
}

