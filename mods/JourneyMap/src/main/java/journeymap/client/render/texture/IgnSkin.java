/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonParser
 *  com.mojang.authlib.GameProfile
 *  com.mojang.authlib.minecraft.MinecraftProfileTexture
 *  com.mojang.authlib.minecraft.MinecraftProfileTexture$Type
 *  com.mojang.authlib.minecraft.MinecraftSessionService
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.resources.DefaultPlayerSkin
 *  net.minecraft.tileentity.TileEntitySkull
 *  net.minecraft.util.ResourceLocation
 *  org.apache.commons.io.IOUtils
 */
package journeymap.client.render.texture;

import com.google.gson.JsonParser;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.minecraft.MinecraftProfileTexture;
import com.mojang.authlib.minecraft.MinecraftSessionService;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Map;
import java.util.UUID;
import javax.imageio.ImageIO;
import journeymap.client.io.RegionImageHandler;
import journeymap.client.render.texture.TextureCache;
import journeymap.common.Journeymap;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.DefaultPlayerSkin;
import net.minecraft.tileentity.TileEntitySkull;
import net.minecraft.util.ResourceLocation;
import org.apache.commons.io.IOUtils;

public class IgnSkin {
    private static String ID_LOOKUP_URL = "https://api.mojang.com/users/profiles/minecraft/%s?at=%s";
    private static String PROFILE_URL = "https://sessionserver.mojang.com/session/minecraft/profile/%s";

    public static BufferedImage getFaceImage(UUID playerId, String username) {
        BufferedImage face = null;
        if (playerId == null) {
            playerId = IgnSkin.lookupPlayerId(username);
        }
        GameProfile profile = TileEntitySkull.updateGameProfile((GameProfile)new GameProfile(playerId, username));
        try {
            MinecraftSessionService mss = Minecraft.getMinecraft().getSessionService();
            Map map = mss.getTextures(profile, false);
            BufferedImage skinImage = null;
            if (map.containsKey(MinecraftProfileTexture.Type.SKIN)) {
                MinecraftProfileTexture mpt = (MinecraftProfileTexture)map.get(MinecraftProfileTexture.Type.SKIN);
                skinImage = IgnSkin.downloadImage(new URL(mpt.getUrl()));
            } else {
                ResourceLocation resourceLocation = DefaultPlayerSkin.getDefaultSkin((UUID)playerId);
                skinImage = TextureCache.getTexture(resourceLocation).getImage();
            }
            face = IgnSkin.cropToFace(skinImage);
        }
        catch (Throwable e) {
            Journeymap.getLogger().warn("Error getting face image for " + username + ": " + e.getMessage());
        }
        return face;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private static UUID lookupPlayerId(String username) {
        URL idLookupUrl = null;
        try {
            idLookupUrl = new URL(String.format(ID_LOOKUP_URL, username, Instant.now().getEpochSecond()));
            HttpURLConnection conn = (HttpURLConnection)idLookupUrl.openConnection(Minecraft.getMinecraft().getProxy());
            HttpURLConnection.setFollowRedirects(true);
            conn.setInstanceFollowRedirects(true);
            conn.setDoInput(true);
            conn.setDoOutput(false);
            conn.connect();
            if (conn.getResponseCode() / 100 != 2) {
                Journeymap.getLogger().debug("Unable to lookup player id: " + idLookupUrl + " : " + conn.getResponseCode());
                return null;
            }
            try (InputStream inputStream = conn.getInputStream();){
                String json = IOUtils.toString((InputStream)inputStream, (Charset)StandardCharsets.UTF_8);
                String id = new JsonParser().parse(json).getAsJsonObject().get("id").getAsString();
                UUID uUID = UUID.nameUUIDFromBytes(id.getBytes(StandardCharsets.UTF_8));
                return uUID;
            }
        }
        catch (Throwable e) {
            Journeymap.getLogger().error("Error getting player id: " + idLookupUrl + " : " + e.getMessage());
        }
        return null;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private static BufferedImage downloadImage(URL imageURL) {
        BufferedImage img = null;
        HttpURLConnection conn = null;
        try {
            conn = (HttpURLConnection)imageURL.openConnection(Minecraft.getMinecraft().getProxy());
            HttpURLConnection.setFollowRedirects(true);
            conn.setInstanceFollowRedirects(true);
            conn.setDoInput(true);
            conn.setDoOutput(false);
            conn.connect();
            if (conn.getResponseCode() / 100 == 2) {
                img = ImageIO.read(conn.getInputStream());
            } else {
                Journeymap.getLogger().debug("Bad Response getting image: " + imageURL + " : " + conn.getResponseCode());
            }
        }
        catch (Throwable e) {
            Journeymap.getLogger().error("Error getting skin image: " + imageURL + " : " + e.getMessage());
        }
        finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
        return img;
    }

    private static BufferedImage cropToFace(BufferedImage playerSkin) {
        BufferedImage result = null;
        if (playerSkin != null) {
            Graphics2D g;
            BufferedImage face = playerSkin.getSubimage(8, 8, 8, 8);
            if (playerSkin.getColorModel().hasAlpha()) {
                g = RegionImageHandler.initRenderingHints(face.createGraphics());
                BufferedImage hat = playerSkin.getSubimage(40, 8, 8, 8);
                g.drawImage(hat, 0, 0, 8, 8, null);
                g.dispose();
            }
            result = new BufferedImage(24, 24, face.getType());
            g = RegionImageHandler.initRenderingHints(result.createGraphics());
            g.drawImage(face, 0, 0, 24, 24, null);
            g.dispose();
        }
        return result;
    }
}

