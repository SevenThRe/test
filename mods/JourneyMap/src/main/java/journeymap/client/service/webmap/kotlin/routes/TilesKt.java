/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.util.math.ChunkPos
 *  net.minecraft.world.World
 *  net.minecraftforge.fml.client.FMLClientHandler
 *  org.apache.logging.log4j.Logger
 */
package journeymap.client.service.webmap.kotlin.routes;

import info.journeymap.shaded.kotlin.kotlin.Metadata;
import info.journeymap.shaded.kotlin.kotlin.io.FilesKt;
import info.journeymap.shaded.kotlin.kotlin.jvm.internal.Intrinsics;
import info.journeymap.shaded.kotlin.kotlin.math.MathKt;
import info.journeymap.shaded.kotlin.spark.kotlin.RouteHandler;
import info.journeymap.shaded.org.javax.servlet.ServletOutputStream;
import info.journeymap.shaded.org.javax.servlet.http.HttpServletResponse;
import info.journeymap.shaded.org.jetbrains.annotations.NotNull;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.OutputStream;
import javax.imageio.IIOException;
import javax.imageio.ImageIO;
import journeymap.client.JourneymapClient;
import journeymap.client.data.WorldData;
import journeymap.client.io.FileHandler;
import journeymap.client.io.RegionImageHandler;
import journeymap.client.model.MapType;
import journeymap.common.Journeymap;
import net.minecraft.client.Minecraft;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.FMLClientHandler;
import org.apache.logging.log4j.Logger;
import org.eclipse.jetty.io.EofException;

@Metadata(mv={1, 1, 16}, bv={1, 0, 3}, k=2, d1={"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0000\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0006"}, d2={"logger", "Lorg/apache/logging/log4j/Logger;", "tilesGet", "", "handler", "Linfo/journeymap/shaded/kotlin/spark/kotlin/RouteHandler;", "journeymap"})
public final class TilesKt {
    private static final Logger logger;

    @NotNull
    public static final Object tilesGet(@NotNull RouteHandler handler) {
        Intrinsics.checkParameterIsNotNull(handler, "handler");
        String string = handler.getRequest().queryParamOrDefault("x", "0");
        Intrinsics.checkExpressionValueIsNotNull(string, "handler.request.queryParamOrDefault(\"x\", \"0\")");
        String string2 = string;
        boolean bl = false;
        int x = Integer.parseInt(string2);
        String string3 = handler.getRequest().queryParamOrDefault("y", "0");
        Intrinsics.checkExpressionValueIsNotNull(string3, "handler.request.queryParamOrDefault(\"y\", \"0\")");
        String string4 = string3;
        boolean bl2 = false;
        Integer y = Integer.parseInt(string4);
        String string5 = handler.getRequest().queryParamOrDefault("z", "0");
        Intrinsics.checkExpressionValueIsNotNull(string5, "handler.request.queryParamOrDefault(\"z\", \"0\")");
        String string6 = string5;
        boolean bl3 = false;
        int z = Integer.parseInt(string6);
        String string7 = handler.getRequest().queryParamOrDefault("dimension", "0");
        Intrinsics.checkExpressionValueIsNotNull(string7, "handler.request.queryPar\u2026Default(\"dimension\", \"0\")");
        String string8 = string7;
        boolean bl4 = false;
        int dimension = Integer.parseInt(string8);
        String string9 = handler.getRequest().queryParamOrDefault("mapTypeString", MapType.Name.day.name());
        Intrinsics.checkExpressionValueIsNotNull(string9, "handler.request.queryPar\u2026\", MapType.Name.day.name)");
        String mapTypeString = string9;
        String string10 = handler.getRequest().queryParamOrDefault("zoom", "0");
        Intrinsics.checkExpressionValueIsNotNull(string10, "handler.request.queryParamOrDefault(\"zoom\", \"0\")");
        String string11 = string10;
        boolean bl5 = false;
        int zoom = Integer.parseInt(string11);
        FMLClientHandler fMLClientHandler = FMLClientHandler.instance();
        Intrinsics.checkExpressionValueIsNotNull(fMLClientHandler, "FMLClientHandler.instance()");
        Minecraft minecraft = fMLClientHandler.getClient();
        Intrinsics.checkExpressionValueIsNotNull(minecraft, "FMLClientHandler.instance().client");
        Minecraft minecraft2 = minecraft;
        World world = (World)minecraft2.world;
        if (world == null) {
            logger.warn("Tiles requested before world loaded");
            handler.status(400);
            return "World not loaded";
        }
        JourneymapClient journeymapClient = Journeymap.getClient();
        Intrinsics.checkExpressionValueIsNotNull(journeymapClient, "Journeymap.getClient()");
        if (!journeymapClient.isMapping().booleanValue()) {
            logger.warn("Tiles requested before JourneyMap started");
            handler.status(400);
            return "JourneyMap is still starting";
        }
        File file = FileHandler.getJMWorldDir(minecraft2);
        Intrinsics.checkExpressionValueIsNotNull(file, "FileHandler.getJMWorldDir(minecraft)");
        File worldDir = file;
        try {
            if (!worldDir.exists() || !worldDir.isDirectory()) {
                logger.warn("JM world directory not found");
                handler.status(404);
                return "World not found";
            }
        }
        catch (NullPointerException e) {
            logger.warn("NPE occurred while locating JM world directory");
            handler.status(404);
            return "World not found";
        }
        MapType.Name mapTypeName = null;
        try {
            mapTypeName = MapType.Name.valueOf(mapTypeString);
        }
        catch (IllegalArgumentException e) {
            logger.warn("Invalid map type supplied during tiles request: " + mapTypeString);
            handler.status(400);
            return "Invalid map type: " + mapTypeString;
        }
        if (mapTypeName != MapType.Name.underground) {
            y = null;
        }
        if (mapTypeName == MapType.Name.underground && WorldData.isHardcoreAndMultiplayer()) {
            logger.debug("Blank tile returned for underground view on a hardcore server");
            HttpServletResponse httpServletResponse = handler.getResponse().raw();
            Intrinsics.checkExpressionValueIsNotNull(httpServletResponse, "handler.response.raw()");
            ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
            Intrinsics.checkExpressionValueIsNotNull(servletOutputStream, "handler.response.raw().outputStream");
            OutputStream output = servletOutputStream;
            HttpServletResponse httpServletResponse2 = handler.getResponse().raw();
            Intrinsics.checkExpressionValueIsNotNull(httpServletResponse2, "handler.response.raw()");
            httpServletResponse2.setContentType("image/png");
            File file2 = RegionImageHandler.getBlank512x512ImageFile();
            Intrinsics.checkExpressionValueIsNotNull(file2, "RegionImageHandler.getBlank512x512ImageFile()");
            output.write(FilesKt.readBytes(file2));
            output.flush();
            return handler.getResponse();
        }
        double d = 2.0;
        boolean bl6 = false;
        int scale = MathKt.roundToInt(Math.pow(d, zoom));
        int distance = 32 / scale;
        int minChunkX = x * distance;
        int minChunkY = z * distance;
        int maxChunkX = minChunkX + distance - 1;
        int maxChunkY = minChunkY + distance - 1;
        ChunkPos startCoord = new ChunkPos(minChunkX, minChunkY);
        ChunkPos endCoord = new ChunkPos(maxChunkX, maxChunkY);
        JourneymapClient journeymapClient2 = Journeymap.getClient();
        Intrinsics.checkExpressionValueIsNotNull(journeymapClient2, "Journeymap.getClient()");
        Boolean bl7 = journeymapClient2.getWebMapProperties().showGrid.get();
        Intrinsics.checkExpressionValueIsNotNull(bl7, "Journeymap.getClient().w\u2026Properties.showGrid.get()");
        boolean showGrid = bl7;
        MapType mapType = new MapType(mapTypeName, y, dimension);
        BufferedImage bufferedImage = RegionImageHandler.getMergedChunks(worldDir, startCoord, endCoord, mapType, true, null, 512, 512, false, showGrid);
        Intrinsics.checkExpressionValueIsNotNull(bufferedImage, "RegionImageHandler.getMe\u2026ZE, false, showGrid\n    )");
        BufferedImage img = bufferedImage;
        HttpServletResponse httpServletResponse = handler.getResponse().raw();
        Intrinsics.checkExpressionValueIsNotNull(httpServletResponse, "handler.response.raw()");
        ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
        Intrinsics.checkExpressionValueIsNotNull(servletOutputStream, "handler.response.raw().outputStream");
        OutputStream output = servletOutputStream;
        try {
            HttpServletResponse httpServletResponse3 = handler.getResponse().raw();
            Intrinsics.checkExpressionValueIsNotNull(httpServletResponse3, "handler.response.raw()");
            httpServletResponse3.setContentType("image/png");
            ImageIO.write((RenderedImage)img, "png", output);
            output.flush();
        }
        catch (EofException e) {
            logger.info("Connection closed while writing image response. Webmap probably reloaded.");
            return "";
        }
        catch (IIOException e) {
            logger.info("Connection closed while writing image response. Webmap probably reloaded.");
            return "";
        }
        return handler.getResponse();
    }

    static {
        Logger logger = Journeymap.getLogger("webmap/routes/tiles");
        Intrinsics.checkExpressionValueIsNotNull(logger, "Journeymap.getLogger(\"webmap/routes/tiles\")");
        TilesKt.logger = logger;
    }
}

