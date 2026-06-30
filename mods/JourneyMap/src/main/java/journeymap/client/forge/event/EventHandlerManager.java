/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.command.ICommand
 *  net.minecraftforge.client.ClientCommandHandler
 *  net.minecraftforge.common.MinecraftForge
 */
package journeymap.client.forge.event;

import java.util.ArrayList;
import java.util.HashMap;
import journeymap.client.cartography.color.ColorManager;
import journeymap.client.command.ClientCommandInvoker;
import journeymap.client.command.CmdChatPosition;
import journeymap.client.command.CmdEditWaypoint;
import journeymap.client.forge.event.ChatEventHandler;
import journeymap.client.forge.event.KeyEventHandler;
import journeymap.client.forge.event.MiniMapOverlayHandler;
import journeymap.client.forge.event.PlayerConnectHandler;
import journeymap.client.forge.event.StateTickHandler;
import journeymap.client.forge.event.TextureAtlasHandler;
import journeymap.client.forge.event.WaypointBeaconHandler;
import journeymap.client.forge.event.WorldEventHandler;
import journeymap.client.world.ChunkMonitor;
import journeymap.common.Journeymap;
import journeymap.common.log.LogFormatter;
import net.minecraft.command.ICommand;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;

public class EventHandlerManager {
    private static HashMap<Class<? extends EventHandler>, EventHandler> handlers = new HashMap();

    public static void registerHandlers() {
        EventHandlerManager.register(KeyEventHandler.INSTANCE);
        EventHandlerManager.register(new ChatEventHandler());
        EventHandlerManager.register(new StateTickHandler());
        EventHandlerManager.register(new WorldEventHandler());
        EventHandlerManager.register(new WaypointBeaconHandler());
        EventHandlerManager.register(new TextureAtlasHandler());
        EventHandlerManager.register(new MiniMapOverlayHandler());
        EventHandlerManager.register(new PlayerConnectHandler());
        ColorManager.INSTANCE.getDeclaringClass();
        ClientCommandInvoker clientCommandInvoker = new ClientCommandInvoker();
        clientCommandInvoker.register(new CmdChatPosition());
        clientCommandInvoker.register(new CmdEditWaypoint());
        ClientCommandHandler.instance.registerCommand((ICommand)clientCommandInvoker);
        EventHandlerManager.register(ChunkMonitor.INSTANCE);
    }

    public static void unregisterAll() {
        ArrayList<Class<? extends EventHandler>> list = new ArrayList<Class<? extends EventHandler>>(handlers.keySet());
        for (Class<? extends EventHandler> handlerClass : list) {
            EventHandlerManager.unregister(handlerClass);
        }
    }

    private static void register(EventHandler handler) {
        Class<?> handlerClass = handler.getClass();
        if (handlers.containsKey(handlerClass)) {
            Journeymap.getLogger().warn("Handler already registered: " + handlerClass.getName());
            return;
        }
        try {
            MinecraftForge.EVENT_BUS.register((Object)handler);
            Journeymap.getLogger().debug("Handler registered: " + handlerClass.getName());
            handlers.put(handler.getClass(), handler);
        }
        catch (Throwable t) {
            Journeymap.getLogger().error(handlerClass.getName() + " registration FAILED: " + LogFormatter.toString(t));
        }
    }

    public static void unregister(Class<? extends EventHandler> handlerClass) {
        EventHandler handler = handlers.remove(handlerClass);
        if (handler != null) {
            try {
                MinecraftForge.EVENT_BUS.unregister((Object)handler);
                Journeymap.getLogger().debug("Handler unregistered: " + handlerClass.getName());
            }
            catch (Throwable t) {
                Journeymap.getLogger().error(handler + " unregistration FAILED: " + LogFormatter.toString(t));
            }
        }
    }

    public static interface EventHandler {
    }
}

