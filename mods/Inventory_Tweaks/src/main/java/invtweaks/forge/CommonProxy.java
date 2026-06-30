/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.netty.channel.ChannelHandler
 *  net.minecraft.client.multiplayer.PlayerControllerMP
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.ClickType
 *  net.minecraft.item.ItemStack
 *  net.minecraft.server.MinecraftServer
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.fml.common.event.FMLInitializationEvent
 *  net.minecraftforge.fml.common.event.FMLPostInitializationEvent
 *  net.minecraftforge.fml.common.event.FMLPreInitializationEvent
 *  net.minecraftforge.fml.common.event.FMLServerAboutToStartEvent
 *  net.minecraftforge.fml.common.event.FMLServerStoppedEvent
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.gameevent.PlayerEvent$PlayerLoggedInEvent
 *  net.minecraftforge.fml.common.network.FMLEmbeddedChannel
 *  net.minecraftforge.fml.common.network.FMLOutboundHandler
 *  net.minecraftforge.fml.common.network.FMLOutboundHandler$OutboundTarget
 *  net.minecraftforge.fml.common.network.NetworkRegistry
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package invtweaks.forge;

import invtweaks.api.IItemTreeListener;
import invtweaks.api.InvTweaksAPI;
import invtweaks.api.SortingMethod;
import invtweaks.api.container.ContainerSection;
import invtweaks.network.ITMessageToMessageCodec;
import invtweaks.network.ITPacketHandlerServer;
import invtweaks.network.packets.ITPacketLogin;
import io.netty.channel.ChannelHandler;
import java.util.EnumMap;
import net.minecraft.client.multiplayer.PlayerControllerMP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ClickType;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerAboutToStartEvent;
import net.minecraftforge.fml.common.event.FMLServerStoppedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.common.network.FMLEmbeddedChannel;
import net.minecraftforge.fml.common.network.FMLOutboundHandler;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class CommonProxy
implements InvTweaksAPI {
    protected static EnumMap<Side, FMLEmbeddedChannel> invtweaksChannel;
    @Nullable
    private static MinecraftServer server;

    public void preInit(FMLPreInitializationEvent e) {
    }

    public void init(FMLInitializationEvent e) {
        invtweaksChannel = NetworkRegistry.INSTANCE.newChannel("InventoryTweaks", new ChannelHandler[]{new ITMessageToMessageCodec()});
        invtweaksChannel.get(Side.SERVER).pipeline().addAfter("ITMessageToMessageCodec#0", "InvTweaks Handler Server", (ChannelHandler)new ITPacketHandlerServer());
        MinecraftForge.EVENT_BUS.register((Object)this);
    }

    public void postInit(FMLPostInitializationEvent e) {
    }

    public void serverAboutToStart(@NotNull FMLServerAboutToStartEvent e) {
        server = e.getServer();
    }

    public void serverStopped(FMLServerStoppedEvent e) {
        server = null;
    }

    public void setServerAssistEnabled(boolean enabled) {
    }

    public void setServerHasInvTweaks(boolean hasInvTweaks) {
    }

    @SideOnly(value=Side.CLIENT)
    public void slotClick(PlayerControllerMP playerController, int windowId, int slot, int data, ClickType action, EntityPlayer player) {
    }

    public void sortComplete() {
    }

    @Override
    public void addOnLoadListener(IItemTreeListener listener) {
    }

    @Override
    public boolean removeOnLoadListener(IItemTreeListener listener) {
        return false;
    }

    @Override
    public void setSortKeyEnabled(boolean enabled) {
    }

    @Override
    public void setTextboxMode(boolean enabled) {
    }

    @Override
    public int compareItems(@NotNull ItemStack i, @NotNull ItemStack j) {
        return 0;
    }

    @Override
    public void sort(ContainerSection section, SortingMethod method) {
    }

    @SubscribeEvent
    public void onPlayerLoggedIn(@NotNull PlayerEvent.PlayerLoggedInEvent e) {
        FMLEmbeddedChannel channel = invtweaksChannel.get(Side.SERVER);
        channel.attr(FMLOutboundHandler.FML_MESSAGETARGET).set((Object)FMLOutboundHandler.OutboundTarget.PLAYER);
        channel.attr(FMLOutboundHandler.FML_MESSAGETARGETARGS).set((Object)e.player);
        channel.writeOutbound(new Object[]{new ITPacketLogin()});
    }

    public void addServerScheduledTask(@NotNull Runnable task) {
        server.func_152344_a(task);
    }

    public void addClientScheduledTask(Runnable task) {
    }
}

