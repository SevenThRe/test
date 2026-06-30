/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.Gson
 *  com.google.gson.GsonBuilder
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  io.netty.util.Attribute
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraftforge.fml.common.network.NetworkRegistry
 *  net.minecraftforge.fml.common.network.simpleimpl.MessageContext
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package journeymap.common.network.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import io.netty.util.Attribute;
import java.util.UUID;
import journeymap.common.network.impl.JsonResponse;
import journeymap.common.network.impl.Message;
import journeymap.common.network.impl.NetworkHandler;
import journeymap.common.network.impl.Response;
import journeymap.common.network.impl.utils.AsyncCallback;
import journeymap.common.network.impl.utils.CallbackService;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class MessageProcessor {
    private static final String MESSAGE_KEY = "message_id";
    static final String OBJECT_KEY = "container_object";
    static final String DATA_KEY = "data";
    protected static Gson gson = new GsonBuilder().serializeNulls().create();
    private JsonObject data;
    private String clazz;
    protected UUID id;
    protected Side side;
    protected EntityPlayer player;

    protected abstract JsonObject onServer(Response var1);

    protected abstract JsonObject onClient(Response var1);

    private void reply(JsonObject data) {
        this.data = new JsonObject();
        this.data.addProperty(MESSAGE_KEY, this.getId().toString());
        this.data.addProperty(OBJECT_KEY, this.clazz);
        this.data.add(DATA_KEY, (JsonElement)data);
        if (this.side.isServer()) {
            this.sendToPlayer(data, (EntityPlayerMP)this.player);
        } else {
            this.send(data);
        }
    }

    public static void process(JsonObject response, MessageContext ctx, Class clazz) {
        MessageProcessor messageProcessor = null;
        try {
            messageProcessor = (MessageProcessor)clazz.newInstance();
            messageProcessor.handleResponse(response, ctx);
        }
        catch (IllegalAccessException | InstantiationException e) {
            NetworkHandler.getLogger().warn("Unable to initialize message processor: " + response.get(OBJECT_KEY).getAsString() + " :", (Throwable)e);
        }
    }

    protected void handleResponse(JsonObject message, MessageContext ctx) {
        CallbackService callbackService = CallbackService.getInstance();
        JsonObject reply = null;
        this.side = ctx.side;
        this.data = message.get(DATA_KEY).getAsJsonObject();
        this.id = UUID.fromString(message.get(MESSAGE_KEY).getAsString());
        this.clazz = message.get(OBJECT_KEY).getAsString();
        JsonResponse response = new JsonResponse(message, ctx);
        if (this.side.isServer()) {
            try {
                this.player = ctx.getServerHandler().player;
                reply = this.onServer(response);
            }
            catch (Exception e) {
                NetworkHandler.getLogger().warn("Error handling response on server: " + this.clazz + " :", (Throwable)e);
            }
        } else {
            try {
                reply = this.onClient(response);
            }
            catch (Exception e) {
                NetworkHandler.getLogger().warn("Error handling response on client: " + this.clazz + " :", (Throwable)e);
            }
        }
        if (reply != null) {
            try {
                this.reply(reply);
            }
            catch (Exception e) {
                NetworkHandler.getLogger().warn("Error handling reply on " + ctx.side.name() + ": " + this.clazz + " :", (Throwable)e);
            }
            return;
        }
        if (callbackService.getCallback(this.id) != null) {
            try {
                callbackService.getCallback(this.id).onSuccess(response);
                callbackService.removeCallback(this.id);
            }
            catch (Exception e) {
                NetworkHandler.getLogger().warn("Error handling callback on " + ctx.side.name() + ": " + this.clazz + " :", (Throwable)e);
            }
        }
    }

    protected void buildRequest(JsonObject requestData) {
        if (requestData == null) {
            requestData = new JsonObject();
        }
        this.data = new JsonObject();
        this.data.addProperty(MESSAGE_KEY, this.getId().toString());
        this.data.addProperty(OBJECT_KEY, this.getClass().getName());
        this.data.add(DATA_KEY, (JsonElement)requestData);
    }

    private UUID getId() {
        if (this.id == null) {
            this.id = UUID.randomUUID();
            return this.id;
        }
        return this.id;
    }

    @SideOnly(value=Side.CLIENT)
    public void send() {
        this.send(new JsonObject());
    }

    @SideOnly(value=Side.CLIENT)
    public void send(JsonObject requestData) {
        this.buildRequest(requestData);
        NetworkHandler.getInstance().sendToServer(new Message(gson.toJson((JsonElement)this.data)));
    }

    @SideOnly(value=Side.CLIENT)
    public void send(AsyncCallback callback) {
        this.buildRequest(null);
        CallbackService.getInstance().saveCallback(this.id, callback);
        NetworkHandler.getInstance().sendToServer(new Message(gson.toJson((JsonElement)this.data)));
    }

    @SideOnly(value=Side.CLIENT)
    public void send(JsonObject requestData, AsyncCallback callback) {
        this.buildRequest(requestData);
        CallbackService.getInstance().saveCallback(this.id, callback);
        NetworkHandler.getInstance().sendToServer(new Message(gson.toJson((JsonElement)this.data)));
    }

    public void sendToPlayer(JsonObject requestData, EntityPlayerMP player) {
        this.buildRequest(requestData);
        Attribute marker = player.connection.getNetworkManager().channel().attr(NetworkRegistry.FML_MARKER);
        if (marker.get() != null && ((Boolean)marker.get()).booleanValue()) {
            NetworkHandler.getInstance().sendTo(new Message(gson.toJson((JsonElement)this.data)), player);
        }
    }

    public void sendToPlayer(JsonObject requestData, EntityPlayerMP player, AsyncCallback callback) {
        this.buildRequest(requestData);
        CallbackService.getInstance().saveCallback(this.id, callback);
        if (((Boolean)player.connection.getNetworkManager().channel().attr(NetworkRegistry.FML_MARKER).get()).booleanValue()) {
            NetworkHandler.getInstance().sendTo(new Message(gson.toJson((JsonElement)this.data)), player);
        }
    }
}

