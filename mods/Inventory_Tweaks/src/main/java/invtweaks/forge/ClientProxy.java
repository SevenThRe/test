/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.netty.channel.ChannelHandler
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.inventory.GuiContainer
 *  net.minecraft.client.multiplayer.PlayerControllerMP
 *  net.minecraft.client.settings.KeyBinding
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.ClickType
 *  net.minecraft.inventory.Container
 *  net.minecraft.item.ItemStack
 *  net.minecraftforge.fml.client.FMLClientHandler
 *  net.minecraftforge.fml.client.registry.ClientRegistry
 *  net.minecraftforge.fml.common.event.FMLInitializationEvent
 *  net.minecraftforge.fml.common.event.FMLPreInitializationEvent
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.gameevent.PlayerEvent$ItemPickupEvent
 *  net.minecraftforge.fml.common.gameevent.TickEvent$ClientTickEvent
 *  net.minecraftforge.fml.common.gameevent.TickEvent$Phase
 *  net.minecraftforge.fml.common.network.FMLEmbeddedChannel
 *  net.minecraftforge.fml.common.network.FMLNetworkEvent$ClientConnectedToServerEvent
 *  net.minecraftforge.fml.relauncher.Side
 *  org.jetbrains.annotations.NotNull
 */
package invtweaks.forge;

import invtweaks.InvTweaks;
import invtweaks.InvTweaksHandlerSorting;
import invtweaks.InvTweaksItemTreeLoader;
import invtweaks.InvTweaksObfuscation;
import invtweaks.api.IItemTreeListener;
import invtweaks.api.SortingMethod;
import invtweaks.api.container.ContainerSection;
import invtweaks.forge.CommonProxy;
import invtweaks.network.ITPacketHandlerClient;
import invtweaks.network.packets.ITPacketClick;
import invtweaks.network.packets.ITPacketSortComplete;
import io.netty.channel.ChannelHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.multiplayer.PlayerControllerMP;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ClickType;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.network.FMLEmbeddedChannel;
import net.minecraftforge.fml.common.network.FMLNetworkEvent;
import net.minecraftforge.fml.relauncher.Side;
import org.jetbrains.annotations.NotNull;

public class ClientProxy
extends CommonProxy {
    public static final KeyBinding KEYBINDING_SORT = new KeyBinding("invtweaks.key.sort", 19, "invtweaks.key.category");
    public boolean serverSupportEnabled = false;
    public boolean serverSupportDetected = false;
    private InvTweaks instance;

    @Override
    public void preInit(@NotNull FMLPreInitializationEvent e) {
        super.preInit(e);
        InvTweaks.log = e.getModLog();
    }

    @Override
    public void init(FMLInitializationEvent e) {
        super.init(e);
        ((FMLEmbeddedChannel)invtweaksChannel.get(Side.CLIENT)).pipeline().addAfter("ITMessageToMessageCodec#0", "InvTweaks Handler Client", (ChannelHandler)new ITPacketHandlerClient());
        Minecraft mc = FMLClientHandler.instance().getClient();
        this.instance = new InvTweaks(mc);
        ClientRegistry.registerKeyBinding((KeyBinding)KEYBINDING_SORT);
    }

    @SubscribeEvent
    public void onTick(@NotNull TickEvent.ClientTickEvent tick) {
        if (tick.phase == TickEvent.Phase.START) {
            Minecraft mc = FMLClientHandler.instance().getClient();
            if (mc.world != null && mc.player != null) {
                if (mc.currentScreen != null) {
                    this.instance.onTickInGUI(mc.currentScreen);
                } else {
                    this.instance.onTickInGame();
                }
            }
        }
    }

    @SubscribeEvent
    public void notifyPickup(PlayerEvent.ItemPickupEvent e) {
        this.instance.setItemPickupPending(true);
    }

    @Override
    public void setServerAssistEnabled(boolean enabled) {
        this.serverSupportEnabled = this.serverSupportDetected && enabled;
    }

    @Override
    public void setServerHasInvTweaks(boolean hasInvTweaks) {
        this.serverSupportDetected = hasInvTweaks;
        this.serverSupportEnabled = hasInvTweaks && !InvTweaks.getConfigManager().getConfig().getProperty("enableServerItemSwap").equals("false");
    }

    @Override
    public void slotClick(@NotNull PlayerControllerMP playerController, int windowId, int slot, int data, @NotNull ClickType action, @NotNull EntityPlayer player) {
        if (this.serverSupportEnabled) {
            player.openContainer.slotClick(slot, data, action, player);
            ((FMLEmbeddedChannel)invtweaksChannel.get(Side.CLIENT)).writeOutbound(new Object[]{new ITPacketClick(slot, data, action, windowId)});
        } else {
            playerController.windowClick(windowId, slot, data, action, player);
        }
    }

    @Override
    public void sortComplete() {
        if (this.serverSupportEnabled) {
            ((FMLEmbeddedChannel)invtweaksChannel.get(Side.CLIENT)).writeOutbound(new Object[]{new ITPacketSortComplete()});
        }
    }

    @Override
    public void addOnLoadListener(IItemTreeListener listener) {
        InvTweaksItemTreeLoader.addOnLoadListener(listener);
    }

    @Override
    public boolean removeOnLoadListener(IItemTreeListener listener) {
        return InvTweaksItemTreeLoader.removeOnLoadListener(listener);
    }

    @Override
    public void setSortKeyEnabled(boolean enabled) {
        this.instance.setSortKeyEnabled(enabled);
    }

    @Override
    public void setTextboxMode(boolean enabled) {
        this.instance.setTextboxMode(enabled);
    }

    @Override
    public int compareItems(@NotNull ItemStack i, @NotNull ItemStack j) {
        return this.instance.compareItems(i, j);
    }

    @Override
    public void sort(ContainerSection section, SortingMethod method) {
        Minecraft mc = FMLClientHandler.instance().getClient();
        Container currentContainer = mc.player.inventoryContainer;
        if (InvTweaksObfuscation.isGuiContainer(mc.currentScreen)) {
            currentContainer = ((GuiContainer)mc.currentScreen).inventorySlots;
        }
        try {
            new InvTweaksHandlerSorting(mc, InvTweaks.getConfigManager().getConfig(), section, method, InvTweaksObfuscation.getSpecialChestRowSize(currentContainer)).sort();
        }
        catch (Exception e) {
            InvTweaks.logInGameErrorStatic("invtweaks.sort.chest.error", e);
            e.printStackTrace();
        }
    }

    @Override
    public void addClientScheduledTask(@NotNull Runnable task) {
        Minecraft.getMinecraft().addScheduledTask(task);
    }

    @SubscribeEvent
    public void onConnectionToServer(FMLNetworkEvent.ClientConnectedToServerEvent e) {
        this.setServerHasInvTweaks(false);
    }
}

