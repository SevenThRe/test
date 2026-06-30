/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessage
 */
package eos.moe.ancientdream.network.send;

import eos.moe.ancientdream.network.NetworkMessage;
import java.util.UUID;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class MessageSender {
    public static void sendBindSkill(int slot, String skill) {
        NetworkMessage.networkWrapper.sendToServer((IMessage)new NetworkMessage(1, buffer -> {
            buffer.writeInt(slot);
            buffer.func_180714_a(skill);
        }));
    }

    public static void sendUseSkill(String skill) {
        NetworkMessage.networkWrapper.sendToServer((IMessage)new NetworkMessage(2, buffer -> buffer.func_180714_a(skill)));
    }

    public static void sendUpgradeSkill(String skill) {
        NetworkMessage.networkWrapper.sendToServer((IMessage)new NetworkMessage(3, buffer -> buffer.func_180714_a(skill)));
    }

    public static void sendDowngradeSkill(String skill) {
        NetworkMessage.networkWrapper.sendToServer((IMessage)new NetworkMessage(4, buffer -> buffer.func_180714_a(skill)));
    }

    public static void sendCreateTeam(String desc, String texture) {
        NetworkMessage.networkWrapper.sendToServer((IMessage)new NetworkMessage(10, buffer -> {
            buffer.func_180714_a(desc);
            buffer.func_180714_a(texture);
        }));
    }

    public static void sendKickPlayer(UUID uuid) {
        NetworkMessage.networkWrapper.sendToServer((IMessage)new NetworkMessage(11, buffer -> buffer.func_179252_a(uuid)));
    }

    public static void sendLeaveTeam() {
        NetworkMessage.networkWrapper.sendToServer((IMessage)new NetworkMessage(12, null));
    }

    public static void sendInvitePlayer(UUID uuid) {
        NetworkMessage.networkWrapper.sendToServer((IMessage)new NetworkMessage(13, buffer -> buffer.func_179252_a(uuid)));
    }

    public static void sendApplyTeam(UUID leader) {
        NetworkMessage.networkWrapper.sendToServer((IMessage)new NetworkMessage(14, buffer -> buffer.func_179252_a(leader)));
    }

    public static void sendTpPlayer(UUID player) {
        NetworkMessage.networkWrapper.sendToServer((IMessage)new NetworkMessage(15, buffer -> buffer.func_179252_a(player)));
    }

    public static void sendInviteTpPlayer(UUID player) {
        NetworkMessage.networkWrapper.sendToServer((IMessage)new NetworkMessage(16, buffer -> buffer.func_179252_a(player)));
    }

    public static void sendChangeLeader(UUID player) {
        NetworkMessage.networkWrapper.sendToServer((IMessage)new NetworkMessage(17, buffer -> buffer.func_179252_a(player)));
    }

    public static void sendUpdateTeamGui(int type) {
        NetworkMessage.networkWrapper.sendToServer((IMessage)new NetworkMessage(18, buffer -> buffer.writeInt(type)));
    }

    public static void sendConfirmPlayer(UUID uuid) {
        NetworkMessage.networkWrapper.sendToServer((IMessage)new NetworkMessage(19, buffer -> buffer.func_179252_a(uuid)));
    }

    public static void sendRefusePlayer(UUID uuid) {
        NetworkMessage.networkWrapper.sendToServer((IMessage)new NetworkMessage(20, buffer -> buffer.func_179252_a(uuid)));
    }

    public static void sendChangeHealth() {
        NetworkMessage.networkWrapper.sendToServer((IMessage)new NetworkMessage(21, null));
    }

    public static void sendOpenManager() {
        NetworkMessage.networkWrapper.sendToServer((IMessage)new NetworkMessage(51, buffer -> {}));
    }

    public static void sendHead(String texture) {
        NetworkMessage.networkWrapper.sendToServer((IMessage)new NetworkMessage(22, buffer -> buffer.func_180714_a(texture)));
    }

    public static void sendBetonQuest(int index) {
        NetworkMessage.networkWrapper.sendToServer((IMessage)new NetworkMessage(40, buffer -> buffer.writeInt(index)));
    }

    public static void sendChangeGuildData(String head, String message) {
        NetworkMessage.networkWrapper.sendToServer((IMessage)new NetworkMessage(50, buffer -> {
            buffer.func_180714_a(head);
            buffer.func_180714_a(message);
        }));
    }
}

