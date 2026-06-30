/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.ResourceLocation
 *  org.apache.logging.log4j.Logger
 */
package journeymap.client.service.webmap.kotlin.routes;

import info.journeymap.shaded.kotlin.kotlin.Metadata;
import info.journeymap.shaded.kotlin.kotlin.collections.CollectionsKt;
import info.journeymap.shaded.kotlin.kotlin.jvm.internal.Intrinsics;
import info.journeymap.shaded.kotlin.kotlin.text.StringsKt;
import info.journeymap.shaded.kotlin.spark.kotlin.RouteHandler;
import info.journeymap.shaded.org.javax.servlet.http.HttpServletResponse;
import info.journeymap.shaded.org.jetbrains.annotations.NotNull;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.FileNotFoundException;
import javax.imageio.IIOException;
import javax.imageio.ImageIO;
import journeymap.client.service.webmap.Webmap;
import journeymap.common.Journeymap;
import net.minecraft.util.ResourceLocation;
import org.apache.logging.log4j.Logger;
import org.eclipse.jetty.io.EofException;

@Metadata(mv={1, 1, 16}, bv={1, 0, 3}, k=2, d1={"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0000\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0006"}, d2={"logger", "Lorg/apache/logging/log4j/Logger;", "resourcesGet", "", "handler", "Linfo/journeymap/shaded/kotlin/spark/kotlin/RouteHandler;", "journeymap"})
public final class ResourcesKt {
    private static final Logger logger;

    @NotNull
    public static final Object resourcesGet(@NotNull RouteHandler handler) {
        BufferedImage bufferedImage;
        Intrinsics.checkParameterIsNotNull(handler, "handler");
        BufferedImage img = null;
        String resource = handler.getRequest().queryParams("resource");
        ResourceLocation resourceLocation = new ResourceLocation(resource);
        String string = resource;
        Intrinsics.checkExpressionValueIsNotNull(string, "resource");
        String extension = (String)CollectionsKt.last(StringsKt.split$default((CharSequence)string, new char[]{'.'}, false, 0, 6, null));
        if (StringsKt.contains$default((CharSequence)extension, ":", false, 2, null)) {
            extension = (String)CollectionsKt.first(StringsKt.split$default((CharSequence)extension, new String[]{":"}, false, 0, 6, null));
        }
        try {
            BufferedImage bufferedImage2 = ImageIO.read(journeymap.common.kotlin.extensions.ResourcesKt.getResourceAsStream(resourceLocation));
            Intrinsics.checkExpressionValueIsNotNull(bufferedImage2, "ImageIO.read(resourceLoc\u2026on.getResourceAsStream())");
            bufferedImage = bufferedImage2;
        }
        catch (FileNotFoundException e) {
            logger.warn("File at resource location not found: " + resource);
            handler.status(404);
            BufferedImage bufferedImage3 = ImageIO.read(Webmap.INSTANCE.getClass().getResource("/assets/journeymap/ui/img/marker-dot-32.png"));
            Intrinsics.checkExpressionValueIsNotNull(bufferedImage3, "ImageIO.read(Webmap.java\u2026/img/marker-dot-32.png\"))");
            bufferedImage = bufferedImage3;
        }
        catch (EofException e) {
            logger.info("Connection closed while writing image response. Webmap probably reloaded.");
            return "";
        }
        catch (IIOException e) {
            logger.info("Connection closed while writing image response. Webmap probably reloaded.");
            return "";
        }
        catch (Exception e) {
            logger.error("Exception thrown while retrieving resource at location: " + resource, (Throwable)e);
            handler.status(500);
            BufferedImage bufferedImage4 = ImageIO.read(Webmap.INSTANCE.getClass().getResource("/assets/journeymap/ui/img/marker-dot-32.png"));
            Intrinsics.checkExpressionValueIsNotNull(bufferedImage4, "ImageIO.read(Webmap.java\u2026/img/marker-dot-32.png\"))");
            bufferedImage = bufferedImage4;
        }
        img = bufferedImage;
        HttpServletResponse httpServletResponse = handler.getResponse().raw();
        Intrinsics.checkExpressionValueIsNotNull(httpServletResponse, "handler.response.raw()");
        httpServletResponse.setContentType("image/" + extension);
        RenderedImage renderedImage = img;
        HttpServletResponse httpServletResponse2 = handler.getResponse().raw();
        Intrinsics.checkExpressionValueIsNotNull(httpServletResponse2, "handler.response.raw()");
        ImageIO.write(renderedImage, extension, httpServletResponse2.getOutputStream());
        HttpServletResponse httpServletResponse3 = handler.getResponse().raw();
        Intrinsics.checkExpressionValueIsNotNull(httpServletResponse3, "handler.response.raw()");
        httpServletResponse3.getOutputStream().flush();
        return handler.getResponse();
    }

    static {
        Logger logger = Journeymap.getLogger("webmap/routes/resources");
        Intrinsics.checkExpressionValueIsNotNull(logger, "Journeymap.getLogger(\"webmap/routes/resources\")");
        ResourcesKt.logger = logger;
    }
}

