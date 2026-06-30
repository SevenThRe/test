/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.network.NetHandlerPlayClient
 */
package journeymap.client.service.webmap.kotlin.routes;

import info.journeymap.shaded.kotlin.kotlin.Metadata;
import info.journeymap.shaded.kotlin.kotlin.jvm.internal.Intrinsics;
import info.journeymap.shaded.kotlin.spark.kotlin.RouteHandler;
import info.journeymap.shaded.org.javax.servlet.http.HttpServletResponse;
import info.journeymap.shaded.org.jetbrains.annotations.NotNull;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.util.UUID;
import javax.imageio.ImageIO;
import journeymap.client.render.texture.IgnSkin;
import net.minecraft.client.Minecraft;
import net.minecraft.client.network.NetHandlerPlayClient;

@Metadata(mv={1, 1, 16}, bv={1, 0, 3}, k=2, d1={"\u0000\u000e\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0010\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0000\u00a8\u0006\u0004"}, d2={"skinGet", "", "handler", "Linfo/journeymap/shaded/kotlin/spark/kotlin/RouteHandler;", "journeymap"})
public final class SkinKt {
    @NotNull
    public static final Object skinGet(@NotNull RouteHandler handler) {
        BufferedImage bufferedImage;
        Intrinsics.checkParameterIsNotNull(handler, "handler");
        UUID uuid = UUID.fromString(handler.params("uuid"));
        Minecraft minecraft = Minecraft.getMinecraft();
        Intrinsics.checkExpressionValueIsNotNull(minecraft, "Minecraft.getMinecraft()");
        NetHandlerPlayClient netHandlerPlayClient = minecraft.getConnection();
        String username = netHandlerPlayClient != null && (netHandlerPlayClient = netHandlerPlayClient.getPlayerInfo(uuid)) != null && (netHandlerPlayClient = netHandlerPlayClient.getGameProfile()) != null ? netHandlerPlayClient.getName() : null;
        BufferedImage img = null;
        if (username == null) {
            bufferedImage = new BufferedImage(24, 24, 2);
        } else {
            BufferedImage bufferedImage2 = IgnSkin.getFaceImage(uuid, username);
            bufferedImage = bufferedImage2;
            Intrinsics.checkExpressionValueIsNotNull(bufferedImage2, "IgnSkin.getFaceImage(uuid, username)");
        }
        img = bufferedImage;
        HttpServletResponse httpServletResponse = handler.getResponse().raw();
        Intrinsics.checkExpressionValueIsNotNull(httpServletResponse, "handler.response.raw()");
        httpServletResponse.setContentType("image/png");
        RenderedImage renderedImage = img;
        HttpServletResponse httpServletResponse2 = handler.getResponse().raw();
        Intrinsics.checkExpressionValueIsNotNull(httpServletResponse2, "handler.response.raw()");
        ImageIO.write(renderedImage, "png", httpServletResponse2.getOutputStream());
        HttpServletResponse httpServletResponse3 = handler.getResponse().raw();
        Intrinsics.checkExpressionValueIsNotNull(httpServletResponse3, "handler.response.raw()");
        httpServletResponse3.getOutputStream().flush();
        return handler.getResponse();
    }
}

